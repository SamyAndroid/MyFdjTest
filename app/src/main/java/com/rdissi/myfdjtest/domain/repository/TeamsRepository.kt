package com.rdissi.myfdjtest.domain.repository

import com.rdissi.myfdjtest.domain.model.Team
import com.rdissi.myfdjtest.domain.model.TeamsResponse

interface TeamsRepository {
    suspend fun getTeamsByLeague(leagueName: String): TeamsResponse?
    suspend fun getTeamById(teamId: String): Team?
}
