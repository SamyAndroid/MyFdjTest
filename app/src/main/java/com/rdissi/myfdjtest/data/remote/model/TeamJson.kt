package com.rdissi.myfdjtest.data.remote.model

import com.google.gson.annotations.SerializedName

data class TeamJson(
    @SerializedName("idTeam")
    val idTeam: String,
    @SerializedName("strTeam")
    val strTeam: String,
    @SerializedName("strBadge")
    val strBadge: String,
    @SerializedName("strSport")
    val strSport: String,
    @SerializedName("strDescriptionEN")
    val strDescriptionEN: String,
    @SerializedName("strLeague")
    val strLeague: String,
    @SerializedName("strLeague2")
    val strLeague2: String,
    @SerializedName("strStadium")
    val strStadium: String,
    @SerializedName("intFormedYear")
    val intFormedYear: String,

)
