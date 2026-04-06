package com.rdissi.myfdjtest.domain.model

data class Team(
    val id: String,
    val name: String,
    val badgeUrl: String,
    val sport: String,
    val description: String,
    val league: String,
    val league2: String,
    val stadium: String,
    val formedYear: String
)