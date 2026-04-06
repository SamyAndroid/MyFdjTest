package com.rdissi.myfdjtest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rdissi.myfdjtest.data.local.dao.TournamentDao
import com.rdissi.myfdjtest.data.local.model.LeagueEntity
import com.rdissi.myfdjtest.data.local.model.TeamEntity

@Database(
    entities = [
        LeagueEntity::class,
        TeamEntity::class
    ],
    version = 1,
    exportSchema = false,
)

abstract class TournamentDatabase : RoomDatabase() {
    abstract fun tournamentDao(): TournamentDao
}
