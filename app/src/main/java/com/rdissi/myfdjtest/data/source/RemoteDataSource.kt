package com.rdissi.myfdjtest.data.source

import com.rdissi.myfdjtest.data.remote.model.TeamsResponseJson
import com.rdissi.myfdjtest.data.remote.model.LeaguesResponseJson
import com.rdissi.myfdjtest.data.remote.service.ApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
) {
    suspend fun fetchLeagues(): LeaguesResponseJson? = apiService.getAllLeagues()
    suspend fun fetchTeamsByLeague(league: String): TeamsResponseJson? = apiService.getTeamsByLeague(league)
}
