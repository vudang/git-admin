package com.git.admin.domain

import com.git.admin.data.model.base.APIError
import com.git.admin.data.model.base.ErrorCode
import com.git.admin.data.model.response.DataResult
import com.git.admin.domain.model.UiState
import com.git.admin.domain.model.User
import com.git.admin.domain.model.UserDetail
import com.git.admin.domain.repository.user.GetUserDetailRepository
import com.git.admin.domain.usecase.user.GetUserDetailUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetUserDetailUseCaseTest {

    private lateinit var getUserDetailRepository: GetUserDetailRepository
    private lateinit var getUserDetailUseCase: GetUserDetailUseCase

    @Before
    fun setUp() {
        getUserDetailRepository = mockk()
        getUserDetailUseCase = GetUserDetailUseCase(getUserDetailRepository)
    }

    @Test
    fun `execute should return error when username is empty`() = runBlocking {
        // Given
        val user = User(login = "")

        // When
        val result = getUserDetailUseCase.execute(user).first()

        // Then
        assertTrue(result is UiState.Error)
        assertEquals(ErrorCode.INVALID_REQUEST, result.errorCode)

        // Verify repository calls
        verify(exactly = 0) { getUserDetailRepository.getUserDetail(any()) }
    }

    @Test
    fun `execute should return error when username is null`() = runBlocking {
        // Given
        val user = User(login = null)

        // When
        val result = getUserDetailUseCase.execute(user).first()

        // Then
        assertTrue(result is UiState.Error)
        assertEquals(ErrorCode.INVALID_REQUEST, result.errorCode)

        // Verify repository calls
        verify(exactly = 0) { getUserDetailRepository.getUserDetail(any()) }
    }

    @Test
    fun `execute should return success with user details when repository returns success`() = runBlocking {
        // Given
        val username = "testUser"
        val user = User(login = username)
        val expectedUserDetail = UserDetail(
            login = username,
            name = "Test User"
        )
        coEvery { getUserDetailRepository.getUserDetail(username) } returns flowOf(DataResult.Success(expectedUserDetail))

        // When
        val result = getUserDetailUseCase.execute(user).first()

        // Then
        assertTrue(result is UiState.Success)
        assertEquals(expectedUserDetail, result.data)

        // Verify repository calls
        verify(exactly = 1) { getUserDetailRepository.getUserDetail(any()) }
    }

    @Test
    fun `execute should return error when repository returns failure`() = runBlocking {
        // Given
        val username = "testUser"
        val user = User(login = username)
        val error = APIError("Network error", ErrorCode.NETWORK_ERROR)

        coEvery { getUserDetailRepository.getUserDetail(username) } returns flowOf(DataResult.Error(error))

        // When
        val result = getUserDetailUseCase.execute(user).first()

        // Then
        assertTrue(result is UiState.Error)
        assertEquals(error.code, result.errorCode)

        // Verify repository calls
        verify(exactly = 1) { getUserDetailRepository.getUserDetail(any()) }
    }
}