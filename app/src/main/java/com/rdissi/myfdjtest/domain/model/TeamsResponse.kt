package com.rdissi.myfdjtest.domain.model

data class TeamsResponse(
    val teams: List<Team>
)

fun List<Team>.sortedByDescending(): List<Team> {
    return this.sortedByDescending { it.name }
}

fun List<Team>.filterOneOutOfTwo(): List<Team> {
    return this.filterIndexed { index, _ ->  index % 2 == 0}
}