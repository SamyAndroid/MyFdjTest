package com.rdissi.myfdjtest.data.remote.model

import com.google.gson.annotations.SerializedName

data class LeagueJson(
    @SerializedName("idLeague")
    val idLeague: String,
    @SerializedName("strLeague")
    val strLeague: String,
    @SerializedName("strSport")
    val strSport: String
)