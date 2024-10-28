package com.git.admin.data

import com.git.admin.data.datasource.local.db.AppDatabase
import com.git.admin.data.datasource.local.db.dao.UserDao
import com.git.admin.data.model.response.DataResult
import com.git.admin.data.repository.auth.StoreUserRepositoryImpl
import com.git.admin.domain.model.User
import com.git.admin.domain.model.toStore
import com.git.admin.mock_data.UserMock
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

class StoreUserRepositoryImplTest {

    private lateinit var repository: StoreUserRepositoryImpl
    private lateinit var appDatabase: AppDatabase
    private lateinit var userDAO: UserDao

    @Before
    fun setup() {
        userDAO = mockk()
        appDatabase = mockk {
            every { userDAO() } returns userDAO
        }
        repository = StoreUserRepositoryImpl(appDatabase)
    }

    @Test
    fun `storeUsers returns success when database operation succeeds`() = runTest {
        // Given
        val users = listOf(UserMock.user)
        val userStoreList = users.map { it.toStore() }

        coEvery { userDAO.insertUsers(userStoreList) } just Runs

        // When
        val result = repository.storeUsers(users).first()

        // Then
        assertTrue(result is DataResult.Success)
        coVerify(exactly = 1) { userDAO.insertUsers(userStoreList) }
    }

    @Test
    fun `storeUsers returns error when database operation fails with IOException`() = runTest {
        // Given
        val users = listOf(UserMock.user)
        val userStoreList = users.map { it.toStore() }

        coEvery { userDAO.insertUsers(userStoreList) } throws IOException("Database error")

        // When
        val result = repository.storeUsers(users).first()

        // Then
        assertTrue(result is DataResult.Error)
        coVerify(exactly = 1) { userDAO.insertUsers(userStoreList) }
    }

    @Test
    fun `storeUsers returns error when database operation fails with SQLiteException`() = runTest {
        // Given
        val users = listOf(UserMock.user)
        val userStoreList = users.map { it.toStore() }

        coEvery { userDAO.insertUsers(userStoreList) } throws android.database.sqlite.SQLiteException("Constraint violation")

        // When
        val result = repository.storeUsers(users).first()

        // Then
        assertTrue(result is DataResult.Error)
        coVerify(exactly = 1) { userDAO.insertUsers(userStoreList) }
    }

    @Test
    fun `storeUsers handles empty list correctly`() = runTest {
        // Given
        val emptyUserList = emptyList<User>()

        coEvery { userDAO.insertUsers(emptyList()) } just Runs

        // When
        val result = repository.storeUsers(emptyUserList).first()

        // Then
        assertTrue(result is DataResult.Success)
        coVerify(exactly = 1) { userDAO.insertUsers(emptyList()) }
    }

    @Test
    fun `storeUsers transforms User to UserStore correctly`() = runTest {
        // Given
        val user = UserMock.user
        val expectedUserStore = user.toStore()

        coEvery { userDAO.insertUsers(listOf(expectedUserStore)) } just Runs

        // When
        val result = repository.storeUsers(listOf(user)).first()

        // Then
        assertTrue(result is DataResult.Success)
        coVerify { userDAO.insertUsers(withArg { userStoreList ->
            assertEquals(1, userStoreList.size)
            assertEquals(expectedUserStore, userStoreList[0])
        })}
    }
}