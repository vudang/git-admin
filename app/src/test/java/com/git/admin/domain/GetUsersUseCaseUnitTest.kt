package com.git.admin.domain

import com.git.admin.data.model.base.APIError
import com.git.admin.data.model.response.DataResult
import com.git.admin.domain.model.UiState
import com.git.admin.domain.model.UserQuery
import com.git.admin.domain.repository.user.GetUserRepository
import com.git.admin.domain.repository.user.StoreUserRepository
import com.git.admin.domain.usecase.user.GetUsersUseCase
import com.git.admin.mock_data.UserMock
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetUsersUseCaseUnitTest {
    private lateinit var getUserRepository: GetUserRepository
    private lateinit var storeUserRepository: StoreUserRepository
    private lateinit var getUsersUseCase: GetUsersUseCase

    // Test data
    private val mockUsers = listOf(UserMock.user, UserMock.user)
    private val userQuery = UserQuery(0, 20)


    @Before
    fun setup() {
        getUserRepository = mockk()
        storeUserRepository = mockk()
        getUsersUseCase = GetUsersUseCase(getUserRepository, storeUserRepository)
    }

    @Test
    fun `when local data exists, return local data without fetching remote`() = runTest {
        // Given
        coEvery { getUserRepository.getLocalUsers(userQuery.page, userQuery.size) } returns flowOf(DataResult.Success(mockUsers))
        coEvery { getUserRepository.getRemoteUsers(userQuery.page, userQuery.size) } returns flowOf(DataResult.Success(emptyList()))
        coEvery { storeUserRepository.storeUsers(any()) } returns flowOf(DataResult.Success(Unit))

        // When
        val result = getUsersUseCase.execute(userQuery).toList()

        // Then
        assertEquals(1, result.size)
        assertTrue(result[0] is UiState.Success)
        assertEquals(mockUsers, (result[0] as UiState.Success).data)

        // Verify repository calls
        verify(exactly = 1) { getUserRepository.getLocalUsers(userQuery.page, userQuery.size) }
        verify(exactly = 1) { storeUserRepository.storeUsers(mockUsers) }
        verify(exactly = 0) { getUserRepository.getRemoteUsers(userQuery.page, userQuery.size) }
    }

    @Test
    fun `when local data is empty, fetch from remote`() = runTest {
        // Given
        coEvery { getUserRepository.getLocalUsers(userQuery.page, userQuery.size) } returns flowOf(DataResult.Success(emptyList()))
        coEvery { getUserRepository.getRemoteUsers(userQuery.page, userQuery.size) } returns flowOf(DataResult.Success(mockUsers))
        coEvery { storeUserRepository.storeUsers(any()) } returns flowOf(DataResult.Success(Unit))

        // When
        val result = getUsersUseCase.execute(userQuery).toList()

        // Then
        assertEquals(1, result.size)
        assertTrue(result[0] is UiState.Success)
        assertEquals(mockUsers, (result[0] as UiState.Success).data)

        // Verify repository calls
        verify(exactly = 1) { getUserRepository.getLocalUsers(userQuery.page, userQuery.size) }
        verify(exactly = 1) { getUserRepository.getRemoteUsers(userQuery.page, userQuery.size) }
        verify(exactly = 1) { storeUserRepository.storeUsers(mockUsers) }
    }

    @Test
    fun `when local database error occurs, fetch from remote`() = runTest {
        // Given
        val error = APIError(message = "Database error")
        coEvery { getUserRepository.getLocalUsers(userQuery.page, userQuery.size) } returns flowOf(DataResult.Error(error))
        coEvery { getUserRepository.getRemoteUsers(userQuery.page, userQuery.size) } returns flowOf(DataResult.Success(mockUsers))
        coEvery { storeUserRepository.storeUsers(any()) } returns flowOf(DataResult.Success(Unit))

        // When
        val result = getUsersUseCase.execute(userQuery).toList()

        // Then
        assertEquals(1, result.size)
        assertTrue(result[0] is UiState.Success)
        assertEquals(mockUsers, (result[0] as UiState.Success).data)

        // Verify repository calls
        verify(exactly = 1) { getUserRepository.getLocalUsers(userQuery.page, userQuery.size) }
        verify(exactly = 1) { getUserRepository.getRemoteUsers(userQuery.page, userQuery.size) }
        verify(exactly = 1) { storeUserRepository.storeUsers(mockUsers) }
    }

    @Test
    fun `when both local and remote fail, return error state`() = runTest {
        // Given
        val error = APIError(message = "Network error")
        coEvery { getUserRepository.getLocalUsers(userQuery.page, userQuery.size) } returns flowOf(DataResult.Error(error))
        coEvery { getUserRepository.getRemoteUsers(userQuery.page, userQuery.size) } returns flowOf(DataResult.Error(error))

        // When
        val result = getUsersUseCase.execute(userQuery).toList()

        // Then
        assertEquals(1, result.size)
        assertTrue(result[0] is UiState.Error)
        assertEquals(error.message, (result[0] as UiState.Error).errorMessage)

        // Verify repository calls
        verify(exactly = 1) { getUserRepository.getLocalUsers(userQuery.page, userQuery.size) }
        verify(exactly = 1) { getUserRepository.getRemoteUsers(userQuery.page, userQuery.size) }
        verify(exactly = 0) { storeUserRepository.storeUsers(any()) }
    }

    @Test
    fun `when storing data fails, should return success state`() = runTest {
        // Given
        val error = APIError(message = "Storage error")
        coEvery { getUserRepository.getLocalUsers(any(), any()) } returns flowOf(DataResult.Success(emptyList()))
        coEvery { getUserRepository.getRemoteUsers(any(), any()) } returns flowOf(DataResult.Success(mockUsers))
        coEvery { storeUserRepository.storeUsers(any()) } returns flowOf(DataResult.Error(error))

        // When
        val result = getUsersUseCase.execute(userQuery).toList()

        // Then
        assertEquals(1, result.size)
        assertTrue(result[0] is UiState.Success)
        assertEquals(mockUsers, (result[0] as UiState.Success).data)

        // Verify repository calls
        verify(exactly = 1) { getUserRepository.getLocalUsers(userQuery.page, userQuery.size) }
        verify(exactly = 1) { getUserRepository.getRemoteUsers(userQuery.page, userQuery.size) }
        verify(exactly = 1) { storeUserRepository.storeUsers(mockUsers) }
    }
}