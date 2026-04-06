package com.rdissi.myfdjtest.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class TeamEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val sport: String,
    val badgeUrl: String,
    val description: String,
    val league: String,
    val league2: String,
    val stadium: String,
    val formedYear: String
)