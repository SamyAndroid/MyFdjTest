package com.rdissi.myfdjtest.domain.repository

import com.rdissi.myfdjtest.domain.model.LeaguesResponse
import com.rdissi.myfdjtest.domain.model.League

interface LeaguesRepository {
    suspend fun getLeagues(): LeaguesResponse?
    suspend fun getLeagueById(leagueId: String): League?
}
