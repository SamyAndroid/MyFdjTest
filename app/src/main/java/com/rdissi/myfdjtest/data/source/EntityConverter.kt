package com.rdissi.myfdjtest.data.source

import com.rdissi.myfdjtest.data.local.model.LeagueEntity
import com.rdissi.myfdjtest.data.local.model.TeamEntity
import com.rdissi.myfdjtest.data.local.model.TeamsResponseEntity
import com.rdissi.myfdjtest.data.local.model.LeaguesResponseEntity
import com.rdissi.myfdjtest.domain.model.League
import com.rdissi.myfdjtest.domain.model.Team
import com.rdissi.myfdjtest.domain.model.TeamsResponse
import com.rdissi.myfdjtest.domain.model.LeaguesResponse

object EntityConverter {

    fun LeaguesResponseEntity.toLeaguesResponse() =
        LeaguesResponse(
            leagues = this.leagueEntities.toLeagues(),
        )

    fun LeaguesResponse.toLeaguesResponseEntity() =
        LeaguesResponseEntity(
            leagueEntities = this.leagues.toLeagueEntities(),
        )

    fun LeagueEntity.toLeague() =
        League(
            id = this.id,
            name = this.title,
            sport = this.sport,
        )

    fun TeamsResponse.toTeamsResponseEntity() =
        TeamsResponseEntity(
            teamEntities = this.teams.toTeamEntities(),
        )

    fun TeamEntity.toTeam() =
        Team(
            id = this.id,
            name = this.name,
            sport = this.sport,
            badgeUrl = this.badgeUrl,
            description = this.description,
            league = this.league,
            league2 = this.league2,
            stadium = this.stadium,
            formedYear = this.formedYear
        )

    private fun League.toLeagueEntity() =
        LeagueEntity(
            id = this.id,
            title = this.name,
            sport = this.sport,
        )

    private fun List<LeagueEntity>.toLeagues(): List<League> =
        map {
            it.toLeague()
        }

    private fun List<League>.toLeagueEntities(): List<LeagueEntity> =
        map {
            it.toLeagueEntity()
        }

    private fun Team.toTeamEntity() =
        TeamEntity(
            id = this.id,
            name = this.name,
            sport = this.sport,
            badgeUrl = this.badgeUrl,
            description = this.description,
            league = this.league,
            league2 = this.league2,
            stadium = this.stadium,
            formedYear = this.formedYear
        )

    private fun List<Team>.toTeamEntities(): List<TeamEntity> =
        map {
            it.toTeamEntity()
        }
}
