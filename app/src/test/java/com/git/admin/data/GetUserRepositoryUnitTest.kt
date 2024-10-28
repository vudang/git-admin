package com.git.admin.data


import com.git.admin.data.datasource.local.db.AppDatabase
import com.git.admin.data.datasource.local.db.dao.UserDao
import com.git.admin.data.datasource.remote.NetworkService
import com.git.admin.data.model.response.DataResult
import com.git.admin.data.repository.auth.GetUserRepositoryImpl
import com.git.admin.mock_data.UserMock
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
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
        val userResponses = UserMock.userResponses
        val expectedUsers = userResponses.map { it.toModel() }

        coEvery { networkService.getListUser(size, since) } returns userResponses

        // When
        val result = repository.getRemoteUsers(page, size).first()

        // Then
        assertTrue(result is DataResult.Success)
        assertEquals(expectedUsers.size, result.data.size)
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
        val userEntities = UserMock.userStores
        val expectedUsers = userEntities.map { it.toModel() }

        coEvery { userDao.getUsers(page, size) } returns userEntities

        // When
        val result = repository.getLocalUsers(page, size).first()

        // Then
        assertTrue(result is DataResult.Success)
        assertEquals(expectedUsers.size, result.data.size)
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