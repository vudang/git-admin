package com.offeright.android.data

import com.git.admin.data.datasource.remote.NetworkService
import com.git.admin.data.model.base.APIError
import com.git.admin.data.model.response.DataResult
import com.git.admin.data.model.response.UserDetailEntity
import com.git.admin.data.repository.auth.GetUserDetailRepositoryImpl
import com.git.admin.domain.model.UserDetail
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class GetUserDetailRepositoryImplTest {

    private lateinit var repository: GetUserDetailRepositoryImpl
    private lateinit var networkService: NetworkService

    @Before
    fun setup() {
        networkService = mockk()
        repository = GetUserDetailRepositoryImpl(networkService)
    }

    @Test
    fun `getUserDetail returns success when network call succeeds`() = runTest {
        // Given
        val username = "testUser"
        val mockUserDetail = mockk<UserDetail>()
        val mockResponse = mockk<UserDetailEntity> {
            coEvery { toModel() } returns mockUserDetail
        }
        coEvery { networkService.getUserDetail(username) } returns mockResponse

        // When
        val result = repository.getUserDetail(username).first()

        // Then
        assertTrue(result is DataResult.Success)
        assertEquals(mockUserDetail, (result as DataResult.Success).data)
        coVerify { networkService.getUserDetail(username) }
    }

    @Test
    fun `getUserDetail returns error when network call fails with HttpException`() = runTest {
        // Given
        val username = "testUser"
        val mockHttpException = HttpException(Response.error<Any>(404, mockk(relaxed = true)))
        coEvery { networkService.getUserDetail(username) } throws mockHttpException

        // When
        val result = repository.getUserDetail(username).first()

        // Then
        assertTrue(result is DataResult.Error)
        coVerify { networkService.getUserDetail(username) }
    }

    @Test
    fun `getUserDetail returns error when network call fails with generic exception`() = runBlocking {
        // Given
        val username = "testUser"
        val exception = RuntimeException("Network error")

        coEvery { networkService.getUserDetail(username) } throws exception

        // When
        val result = repository.getUserDetail(username).first()

        // Then
        assertTrue(result is DataResult.Error)
        coVerify { networkService.getUserDetail(username) }
    }
}