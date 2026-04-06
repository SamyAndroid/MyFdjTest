package com.rdissi.myfdjtest.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leagues")
data class LeagueEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val sport: String
)