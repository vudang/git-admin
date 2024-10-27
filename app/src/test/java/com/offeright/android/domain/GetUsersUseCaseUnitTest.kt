package com.offeright.android.domain

import com.git.admin.data.model.response.DataResult
import com.git.admin.domain.model.UiState
import com.git.admin.domain.model.UserQuery
import com.git.admin.domain.repository.user.GetUserRepository
import com.git.admin.domain.repository.user.StoreUserRepository
import com.git.admin.domain.usecase.user.GetUsersUseCase
import com.offeright.android.fake_data.UserFake
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
    private val mockUsers = listOf(UserFake.user, UserFake.user)
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
}