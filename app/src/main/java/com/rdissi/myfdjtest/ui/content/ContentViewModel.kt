package com.rdissi.myfdjtest.ui.content

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rdissi.myfdjtest.common.Result
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
        val hasRequestedLeagues: Boolean = false,
    )

    fun getAllLeagues() {
        getLeaguesUseCase()
            .onEach { result ->
                when (result) {
                    is Result.Loading -> {
                        _uiState.update {
                            it.copy(
                                isLoading = true,
                                error = null,
                                hasRequestedLeagues = true,
                            )
                        }
                    }

                    is Result.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                leagues = result.data,
                                error = null,
                                hasRequestedLeagues = false,
                            )
                        }
                    }

                    is Result.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = result.message,
                                hasRequestedLeagues = false,
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun getTeamsByLeague(leagueName: String) {
        getTeamsUseCase
            .getTeamByLeagueName(leagueName)
            .onEach { result ->
                when (result) {
                    is Result.Loading -> {
                        _uiState.update {
                            it.copy(isLoading = true, error = null)
                        }
                    }

                    is Result.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                teams = result.data,
                                error = null,
                            )
                        }
                    }

                    is Result.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = result.message,
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun cancel() {
        _uiState.update {
            it.copy(
                isLoading = false,
                leagues = emptyList(),
                teams = emptyList(),
                error = null,
            )
        }
    }

    fun onQueryChange(query: String) {
        _uiState.update {
            if (query.isNotBlank()) {
                it.copy(query = query)
            } else {
                it.copy(
                    query = query,
                    isLoading = false,
                    leagues = emptyList(),
                    teams = emptyList(),
                    error = null,
                )
            }
        }
        if (
            uiState.value.leagues.isEmpty() &&
            query.isNotBlank() &&
            !uiState.value.hasRequestedLeagues
        ) {
            getAllLeagues()
        }
    }
}
