package com.rdissi.myfdjtest.data.source

import com.rdissi.myfdjtest.data.local.dao.TournamentDao
import com.rdissi.myfdjtest.data.local.model.TeamsResponseEntity
import com.rdissi.myfdjtest.data.local.model.LeaguesResponseEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val tournamentDao: TournamentDao
) {
    suspend fun getLeaguesResponseEntity() = LeaguesResponseEntity(
        leagueEntities = tournamentDao.getLeagues()
    )

    suspend fun isLeaguesNotEmpty() = tournamentDao.getLeagues().isEmpty().not()

    suspend fun updateLeaguesResponseEntity(leaguesResponseEntity: LeaguesResponseEntity) {
        tournamentDao.updateLeagues(leaguesResponseEntity)
    }

    suspend fun updateTeamsResponseEntity(teamsResponseEntity: TeamsResponseEntity) {
        tournamentDao.updateTeams(teamsResponseEntity)
    }

    suspend fun getLeagueEntityById(leagueId: String) = tournamentDao.getLeagueById(leagueId)

    suspend fun getTeamEntityById(teamId: String) = tournamentDao.getTeamById(teamId)
}

