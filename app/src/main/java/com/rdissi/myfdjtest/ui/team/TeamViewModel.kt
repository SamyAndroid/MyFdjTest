package com.rdissi.myfdjtest.ui.team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import com.rdissi.myfdjtest.common.Result
import com.rdissi.myfdjtest.domain.model.Team
import com.rdissi.myfdjtest.domain.usecase.GetTeamsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val getTeamsUseCase: GetTeamsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    sealed class UiState {
        data object Loading : UiState()
        data class Success(val team: Team) : UiState()
        data class Error(val message: String?) : UiState()
    }

    sealed class UiEvent {
        data class Toast(val message: String) : UiEvent()
    }

    fun getTeamById(id: String) {
        getTeamsUseCase.getTeamById(id).onEach { result ->
            when (result) {
                is Result.Loading -> {
                    _uiState.update { UiState.Loading }
                }
                is Result.Success -> {
                    _uiState.update { UiState.Success(team = result.data)}
                }
                is Result.Error -> {
                    _uiState.update { UiState.Error(result.message) }
                }
            }
        }.launchIn(viewModelScope)
    }

}
