package com.rdissi.myfdjtest.data.repository

import android.util.Log
import com.rdissi.myfdjtest.data.source.EntityConverter.toTeam
import com.rdissi.myfdjtest.data.source.EntityConverter.toTeamsResponseEntity
import com.rdissi.myfdjtest.data.source.JsonConverter.toTeams
import com.rdissi.myfdjtest.data.source.LocalDataSource
import com.rdissi.myfdjtest.data.source.RemoteDataSource
import com.rdissi.myfdjtest.di.IoDispatcher
import com.rdissi.myfdjtest.domain.model.Team
import com.rdissi.myfdjtest.domain.model.TeamsResponse
import com.rdissi.myfdjtest.domain.repository.TeamsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TeamsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : TeamsRepository {

    override suspend fun getTeamsByLeague(leagueName: String): TeamsResponse? = withContext(dispatcher) {
        try {
            val teams: TeamsResponse? = remoteDataSource.fetchTeamsByLeague(leagueName)?.toTeams()
            if (teams != null) {
                updateTeamsDB(teams)
            }
            teams
        } catch (exception: Exception) {
            Log.e("TeamsRepositoryImpl", "getTeamsByLeague()", exception)
            throw exception
        }
    }

    override suspend fun getTeamById(teamId: String): Team = withContext(dispatcher) {
        localDataSource.getTeamEntityById(teamId).toTeam()
    }

    private suspend fun updateTeamsDB(teamsResponse: TeamsResponse) {
        localDataSource.updateTeamsResponseEntity(teamsResponse.toTeamsResponseEntity())
    }
}
