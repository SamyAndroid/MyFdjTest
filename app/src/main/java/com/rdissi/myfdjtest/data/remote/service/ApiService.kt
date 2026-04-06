package com.rdissi.myfdjtest.data.remote.service

import com.rdissi.myfdjtest.data.remote.model.TeamsResponseJson
import com.rdissi.myfdjtest.data.remote.model.LeaguesResponseJson
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/v1/json/123/all_leagues.php")
    suspend fun getAllLeagues(): LeaguesResponseJson?

    @GET("api/v1/json/123/search_all_teams.php")
    suspend fun getTeamsByLeague(@Query("l") league: String): TeamsResponseJson?
}