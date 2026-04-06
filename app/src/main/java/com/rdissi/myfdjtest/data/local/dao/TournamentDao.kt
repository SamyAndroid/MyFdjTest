package com.rdissi.myfdjtest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.rdissi.myfdjtest.data.local.model.LeaguesResponseEntity
import com.rdissi.myfdjtest.data.local.model.LeagueEntity
import com.rdissi.myfdjtest.data.local.model.TeamEntity
import com.rdissi.myfdjtest.data.local.model.TeamsResponseEntity

@Dao
interface TournamentDao {

    @Query("SELECT * FROM leagues")
    suspend fun getLeagues(): List<LeagueEntity>

    @Query("SELECT * FROM leagues WHERE id = :leagueId")
    suspend fun getLeagueById(leagueId: String): LeagueEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeagues(leagues: Collection<LeagueEntity>)

    @Query("DELETE FROM leagues")
    suspend fun deleteLeagues()

    @Query("SELECT * FROM teams")
    suspend fun getTeams(): List<TeamEntity>

    @Query("SELECT * FROM teams WHERE id = :teamId")
    suspend fun getTeamById(teamId: String): TeamEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeams(teams: Collection<TeamEntity>)

    @Query("DELETE FROM teams")
    suspend fun deleteTeams()

    @Transaction
    suspend fun updateLeagues(
        leaguesResponseEntity: LeaguesResponseEntity
    ) {
        deleteLeagues()
        insertLeagues(leaguesResponseEntity.leagueEntities)
    }

    @Transaction
    suspend fun updateTeams(
        teamsResponseEntity: TeamsResponseEntity
    ) {
        deleteTeams()
        insertTeams(teamsResponseEntity.teamEntities)
    }
}
