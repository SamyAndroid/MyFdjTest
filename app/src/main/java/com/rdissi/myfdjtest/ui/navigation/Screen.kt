package com.rdissi.myfdjtest.ui.navigation

import androidx.annotation.StringRes
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.rdissi.myfdjtest.R

sealed class Screen(
    val route: String,
    val routeWithArgs: String = "",
    val arguments: List<NamedNavArgument> = emptyList(),
    @StringRes val title: Int
) {

    data object HomeScreen: Screen(
        route = "home_screen",
        title = R.string.author
    )

    data object Team: Screen(
        route = "team_screen",
        routeWithArgs = "team_screen/{id}",
        arguments = listOf(
            navArgument("id") { type = NavType.StringType }
        ),
        title = R.string.team_page,
    )
}
