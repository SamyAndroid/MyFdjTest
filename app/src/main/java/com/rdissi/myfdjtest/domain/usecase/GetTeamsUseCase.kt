package com.rdissi.myfdjtest.domain.usecase

import android.util.Log
import com.rdissi.myfdjtest.common.Result
import com.rdissi.myfdjtest.domain.model.Team
import com.rdissi.myfdjtest.domain.model.TeamsResponse
import com.rdissi.myfdjtest.domain.model.filterOneOutOfTwo
import com.rdissi.myfdjtest.domain.model.sortedByDescending
import com.rdissi.myfdjtest.domain.repository.TeamsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTeamsUseCase @Inject constructor(
    private val teamsRepository: TeamsRepository
) {

    fun getTeamByLeagueName(leagueName: String): Flow<Result<List<Team>>> = flow {
        emit(Result.Loading)
        runCatching {
            val teamsResponse: TeamsResponse? = teamsRepository.getTeamsByLeague(leagueName)
            teamsResponse?.teams?.sortedByDescending()?.filterOneOutOfTwo() ?: throw Exception("teams null")
        }.onSuccess { teams ->
            emit(Result.Success(teams))
        }.onFailure {
            emit(Result.Error("Error=$it"))
            Log.e("GetTeamsUseCase", it.toString())
        }
    }

    fun getTeamById(teamId: String): Flow<Result<Team>> = flow {
        emit(Result.Loading)
        runCatching {
            val team: Team = teamsRepository.getTeamById(teamId) ?: throw Exception("Team null")
            team
        }.onSuccess { team ->
            emit(Result.Success(team))
        }.onFailure {
            emit(Result.Error("Error=$it"))
            Log.e("getTeamById", it.toString())
        }
    }
}
