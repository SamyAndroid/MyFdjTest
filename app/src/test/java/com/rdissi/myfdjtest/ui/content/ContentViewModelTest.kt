package com.rdissi.myfdjtest.ui.content

import app.cash.turbine.test
import com.nhaarman.mockito_kotlin.given
import com.rdissi.myfdjtest.domain.usecase.GetLeaguesUseCase
import com.rdissi.myfdjtest.domain.usecase.GetTeamsUseCase
import com.rdissi.myfdjtest.common.Result
import com.rdissi.myfdjtest.domain.model.League
import com.rdissi.myfdjtest.domain.model.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ContentViewModelTest {

    @Mock
    private lateinit var getLeaguesUseCase: GetLeaguesUseCase
    @Mock
    private lateinit var getTeamsUseCase: GetTeamsUseCase

    private lateinit var contentViewModel: ContentViewModel

    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getAllLeagues() will return an UI success state`(): Unit = runTest {
        //Given
        val leagues = mock<List<League>>()
        val flow = flowOf(Result.Loading, Result.Success(leagues))

        given(getLeaguesUseCase.invoke()).willReturn(flow)
        contentViewModel = ContentViewModel(getLeaguesUseCase, getTeamsUseCase)

        //When
        contentViewModel.getAllLeagues()

        //Then
        getLeaguesUseCase().test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(leagues), awaitItem())
            awaitComplete()
        }
        contentViewModel.uiState.test(timeout = 5.seconds) {
            assertEquals(ContentViewModel.UiState(leagues = leagues), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `getAllLeagues() will return an UI error state`(): Unit = runTest {
        //Given
        val errorMessage = "An error has occurred"
        val flow = flowOf(Result.Loading, Result.Error(errorMessage))

        given(getLeaguesUseCase.invoke()).willReturn(flow)
        contentViewModel = ContentViewModel(getLeaguesUseCase, getTeamsUseCase)

        //When
        contentViewModel.getAllLeagues()

        //Then
        getLeaguesUseCase().test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Error(errorMessage), awaitItem())
            awaitComplete()
        }
        contentViewModel.uiState.test(timeout = 5.seconds) {
            assertEquals(ContentViewModel.UiState(error = errorMessage), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `getTeamsByLeague() will return an UI success state`(): Unit = runTest {
        //Given
        val leagueName = "League 1"
        val teams = mock<List<Team>>()
        val flow = flowOf(Result.Loading, Result.Success(teams))

        given(getTeamsUseCase.getTeamByLeagueName(leagueName)).willReturn(flow)
        contentViewModel = ContentViewModel(getLeaguesUseCase, getTeamsUseCase)

        //When
        contentViewModel.getTeamsByLeague(leagueName)

        //Then
        getTeamsUseCase.getTeamByLeagueName(leagueName).test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(teams), awaitItem())
            awaitComplete()
        }
        contentViewModel.uiState.test(timeout = 5.seconds) {
            assertEquals(ContentViewModel.UiState(teams = teams), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `getTeamsByLeague() will return an UI error state`(): Unit = runTest {
        //Given
        val leagueName = "League 1"
        val errorMessage = "An error has occurred"
        val flow = flowOf(Result.Loading, Result.Error(errorMessage))

        given(getTeamsUseCase.getTeamByLeagueName(leagueName)).willReturn(flow)
        contentViewModel = ContentViewModel(getLeaguesUseCase, getTeamsUseCase)

        //When
        contentViewModel.getTeamsByLeague(leagueName)

        //Then
        getTeamsUseCase.getTeamByLeagueName(leagueName).test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Error(errorMessage), awaitItem())
            awaitComplete()
        }
        contentViewModel.uiState.test(timeout = 5.seconds) {
            assertEquals(ContentViewModel.UiState(error = errorMessage), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }


}