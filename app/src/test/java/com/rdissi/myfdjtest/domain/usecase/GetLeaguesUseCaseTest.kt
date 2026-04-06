package com.rdissi.myfdjtest.domain.usecase

import app.cash.turbine.test
import com.rdissi.myfdjtest.domain.model.LeaguesResponse
import com.rdissi.myfdjtest.domain.repository.LeaguesRepository
import com.rdissi.myfdjtest.common.Result
import com.rdissi.myfdjtest.domain.model.League
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given
import org.mockito.kotlin.mock


@RunWith(MockitoJUnitRunner::class)
class GetLeaguesUseCaseTest {

    @Mock
    private lateinit var leaguesRepository: LeaguesRepository

    @Test
    fun `GetLeaguesUseCaseTest will return a flow on Success`() = runTest {
        // Given
        val leaguesResponseMock = mock<LeaguesResponse> {}
        given(leaguesRepository.getLeagues()).willReturn(leaguesResponseMock)

        //Then
        GetLeaguesUseCase(leaguesRepository).invoke().test{
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(emptyList<League>()), awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `GetLeaguesUseCaseTest will return a flow on Error`() = runTest {
        //Given
        given(leaguesRepository.getLeagues()).willReturn(null)

        //Then
        GetLeaguesUseCase(leaguesRepository).invoke().test{
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Error("Error=java.lang.Exception: leagues null"), awaitItem())
            awaitComplete()
        }
    }

}