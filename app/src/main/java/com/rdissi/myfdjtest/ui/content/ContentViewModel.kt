package com.rdissi.myfdjtest.ui.content

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rdissi.myfdjtest.common.Result
import com.rdissi.myfdjtest.common.handle
import com.rdissi.myfdjtest.domain.model.League
import com.rdissi.myfdjtest.domain.model.Team
import com.rdissi.myfdjtest.domain.usecase.GetLeaguesUseCase
import com.rdissi.myfdjtest.domain.usecase.GetTeamsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ContentViewModel
@Inject
constructor(
    private val getLeaguesUseCase: GetLeaguesUseCase,
    private val getTeamsUseCase: GetTeamsUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    data class UiState(
        val query: String = "",
        val isLoading: Boolean = false,
        val leagues: List<League> = emptyList(),
        val teams: List<Team> = emptyList(),
        val error: String? = null,
    )

    fun getAllLeagues() {
        getLeaguesUseCase()
            .onEach { result ->
                result.handle(
                    onLoading = {
                        _uiState.update { it.copy(isLoading = true, error = null) }
                    },
                    onSuccess = { data ->
                        _uiState.update { it.copy(leagues = data, isLoading = false, error = null) }
                    },
                    onError = { message ->
                        _uiState.update { it.copy(error = message, isLoading = false) }
                    }
                )
            }.launchIn(viewModelScope)
    }

    fun getTeamsByLeague(leagueName: String) {
        getTeamsUseCase
            .getTeamByLeagueName(leagueName)
            .onEach { result ->
                result.handle(
                    onLoading = {
                        _uiState.update { it.copy(isLoading = true, error = null) }
                    },
                    onSuccess = { data ->
                        _uiState.update { it.copy(teams = data, isLoading = false, error = null) }
                    },
                    onError = { message ->
                        _uiState.update { it.copy(error = message, isLoading = false) }
                    }
                )
            }.launchIn(viewModelScope)
    }

    fun cancelSearch() {
        _uiState.update {
            it.copy(
                isLoading = false,
                leagues = emptyList(),
                teams = emptyList(),
                error = null,
            )
        }
    }

    // API call only on the first input
    // to avoid triggering a request on every keystroke
    fun onQueryChange(query: String) {
        _uiState.update {
            it.copy(
                query = query,
            )
        }
        val state = _uiState.value
        if (query.isBlank()) {
            _uiState.update {
                it.copy(
                    leagues = emptyList(),
                    teams = emptyList(),
                    isLoading = false
                )
            }
            return
        }
        if (state.leagues.isEmpty() && !state.isLoading) {
            getAllLeagues()
        }
    }
}
