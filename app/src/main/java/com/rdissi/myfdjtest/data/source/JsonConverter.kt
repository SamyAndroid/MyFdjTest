package com.rdissi.myfdjtest.data.source

import com.rdissi.myfdjtest.data.remote.model.LeagueJson
import com.rdissi.myfdjtest.data.remote.model.TeamJson
import com.rdissi.myfdjtest.data.remote.model.TeamsResponseJson
import com.rdissi.myfdjtest.data.remote.model.LeaguesResponseJson
import com.rdissi.myfdjtest.domain.model.League
import com.rdissi.myfdjtest.domain.model.Team
import com.rdissi.myfdjtest.domain.model.TeamsResponse
import com.rdissi.myfdjtest.domain.model.LeaguesResponse

object JsonConverter {

    fun LeaguesResponseJson.toLeaguesResponse() =
        LeaguesResponse(
            leagues = this.leagues.toLeagues(),
        )

    fun TeamsResponseJson.toTeams() =
        TeamsResponse(
            teams = this.teams.toTeams(),
        )

    fun List<TeamJson>.toTeams(): List<Team> =
        map {
            it.toTeam()
        }

    private fun TeamJson.toTeam() =
        Team(
            id = this.idTeam,
            name = this.strTeam,
            badgeUrl = this.strBadge,
            sport = this.strSport,
            description = this.strDescriptionEN,
            league = this.strLeague,
            league2 = this.strLeague2,
            stadium = this.strStadium,
            formedYear = this.intFormedYear
        )

    private fun LeagueJson.toLeague() =
        League(
            id = this.idLeague,
            name = this.strLeague,
            sport = this.strSport,
        )

    private fun List<LeagueJson>.toLeagues(): List<League> =
        map {
            it.toLeague()
        }
}
