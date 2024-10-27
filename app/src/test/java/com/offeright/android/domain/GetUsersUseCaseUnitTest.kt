package com.offeright.android.domain

import com.git.admin.data.model.response.DataResult
import com.git.admin.domain.model.UiState
import com.git.admin.domain.model.UserQuery
import com.git.admin.domain.repository.user.GetUserRepository
import com.git.admin.domain.repository.user.StoreUserRepository
import com.git.admin.domain.usecase.user.GetUsersUseCase
import com.offeright.android.fake_data.UserFake
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

class GetUsersUseCaseUnitTest {
    private lateinit var getUsersRepository: GetUserRepository
    private lateinit var storeUserRepository: StoreUserRepository
    private lateinit var getUsersUseCase: GetUsersUseCase
    private val query = UserQuery(0, 20)

    @Before
    fun setup() {
        getUsersRepository = mock(GetUserRepository::class.java)
        storeUserRepository = mock(StoreUserRepository::class.java)
        getUsersUseCase = GetUsersUseCase(getUsersRepository, storeUserRepository)
    }

    @Test
    fun `execute should return list of users when successful`() = runTest {
        // Given
        val users = listOf(UserFake.user)

        // Mock
        whenever(getUsersRepository.getLocalUsers(query.page, query.size)).thenReturn(
            flowOf(DataResult.Success(users))
        )
        whenever(getUsersRepository.getRemoteUsers(query.page, query.size)).thenReturn(
            flowOf(DataResult.Success(emptyList()))
        )

        // When
        val result = getUsersUseCase.execute(query).toList()

        // Then
        assertEquals(1, result.size)
        assertEquals(UiState.Success(users), result.first())
        verify(getUsersRepository).getLocalUsers(query.page, query.size)
    }

}