package com.offeright.android.data


import com.git.admin.data.datasource.local.db.AppDatabase
import com.git.admin.data.datasource.local.db.dao.UserDao
import com.git.admin.data.datasource.local.db.entity.user.UserStore
import com.git.admin.data.datasource.remote.NetworkService
import com.git.admin.data.model.response.DataResult
import com.git.admin.domain.model.User
import com.git.admin.data.model.response.UserEntity
import com.git.admin.data.repository.auth.GetUserRepositoryImpl
import io.mockk.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetUserRepositoryImplTest {

    private lateinit var repository: GetUserRepositoryImpl
    private lateinit var appDatabase: AppDatabase
    private lateinit var networkService: NetworkService
    private lateinit var userDao: UserDao

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        appDatabase = mockk()
        networkService = mockk()
        userDao = mockk()

        coEvery { appDatabase.userDAO() } returns userDao

        repository = GetUserRepositoryImpl(
            appDatabase = appDatabase,
            networkService = networkService
        )
    }

    @Test
    fun `getRemoteUsers should return success with user list when API call succeeds`() = runTest {
        // Given
        val page = 0
        val size = 20
        val since = page * size

        val userResponses = listOf(
            UserEntity(
                id = 1,
                login = "user1",
                avatarUrl = "https://example.com/avatar1.jpg",
                type = "User"
            ),
            UserEntity(
                id = 2,
                login = "user2",
                avatarUrl = "https://example.com/avatar2.jpg",
                type = "User"
            )
        )

        val expectedUsers = userResponses.map {
            User(
                id = it.id,
                login = it.login,
                avatarUrl = it.avatarUrl,
                type = it.type
            )
        }

        coEvery { networkService.getListUser(size, since) } returns userResponses

        // When
        val result = repository.getRemoteUsers(page, size).first()

        // Then
        assertTrue(result is DataResult.Success)
        assertEquals(expectedUsers, result.data)
        coVerify { networkService.getListUser(size, since) }
    }

    @Test
    fun `getRemoteUsers should return error when API call fails with HttpException`() = runTest {
        // Given
        val page = 0
        val size = 20
        val since = page * size
        val httpException = HttpException(Response.error<Any>(404, mockk(relaxed = true)))

        coEvery { networkService.getListUser(size, since) } throws httpException

        // When
        val result = repository.getRemoteUsers(page, size).first()

        // Then
        assertTrue(result is DataResult.Error)
        coVerify { networkService.getListUser(size, since) }
    }

    @Test
    fun `getRemoteUsers should return error when API call fails with generic exception`() = runTest {
        // Given
        val page = 0
        val size = 20
        val since = page * size
        val exception = Exception("Network error")

        coEvery { networkService.getListUser(size, since) } throws exception

        // When
        val result = repository.getRemoteUsers(page, size).first()

        // Then
        assertTrue(result is DataResult.Error)
        coVerify { networkService.getListUser(size, since) }
    }

    @Test
    fun `getLocalUsers should return success with user list when database query succeeds`() = runTest {
        // Given
        val page = 0
        val size = 20

        val userEntities = mutableListOf(
            UserStore(
                id = 1,
                login = "user1",
                avatarUrl = "https://example.com/avatar1.jpg",
                type = "User"
            ),
            UserStore(
                id = 2,
                login = "user2",
                avatarUrl = "https://example.com/avatar2.jpg",
                type = "User"
            )
        )

        val expectedUsers = userEntities.map {
            User(
                id = it.id,
                login = it.login,
                avatarUrl = it.avatarUrl,
                type = it.type
            )
        }

        coEvery { userDao.getUsers(page, size) } returns userEntities

        // When
        val result = repository.getLocalUsers(page, size).first()

        // Then
        assertTrue(result is DataResult.Success)
        assertEquals(expectedUsers, result.data)
        coVerify {
            appDatabase.userDAO()
            userDao.getUsers(page, size)
        }
    }

    @Test
    fun `getLocalUsers should return error when database query fails`() = runTest {
        // Given
        val page = 0
        val size = 20
        val exception = Exception("Database error")

        coEvery { userDao.getUsers(page, size) } throws exception

        // When
        val result = repository.getLocalUsers(page, size).first()

        // Then
        assertTrue(result is DataResult.Error)
        coVerify {
            appDatabase.userDAO()
            userDao.getUsers(page, size)
        }
    }

    @Test
    fun `getLocalUsers should handle empty database result`() = runTest {
        // Given
        val page = 0
        val size = 20

        coEvery { userDao.getUsers(page, size) } returns emptyList()

        // When
        val result = repository.getLocalUsers(page, size).first()

        // Then
        assertTrue(result is DataResult.Success)
        assertEquals(emptyList(), result.data)
        coVerify {
            appDatabase.userDAO()
            userDao.getUsers(page, size)
        }
    }

    @Test
    fun `pagination parameters should be correctly calculated for remote users`() = runTest {
        // Given
        val pages = listOf(0, 1, 2)
        val size = 20

        coEvery { networkService.getListUser(any(), any()) } returns emptyList()

        // When & Then
        pages.forEach { page ->
            repository.getRemoteUsers(page, size).first()
            coVerify { networkService.getListUser(size, page * size) }
        }
    }

    @Test
    fun `pagination parameters should be correctly passed for local users`() = runTest {
        // Given
        val pages = listOf(0, 1, 2)
        val size = 20

        coEvery { userDao.getUsers(any(), any()) } returns emptyList()

        // When & Then
        pages.forEach { page ->
            repository.getLocalUsers(page, size).first()
            coVerify { userDao.getUsers(page, size) }
        }
    }
}