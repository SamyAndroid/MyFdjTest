package com.rdissi.myfdjtest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rdissi.myfdjtest.ui.content.ContentPage
import com.rdissi.myfdjtest.ui.team.TeamScreen

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.HomeScreen.route,
    ) {
        // Main Page
        composable(route = Screen.HomeScreen.route) {
            ContentPage(
                onTeamSelected = { team ->
                    navController.navigate(route = Screen.Team.route + "/" + team.id)
                },
            )
        }
        // Team Page
        composable(
            route = Screen.Team.routeWithArgs,
            arguments = Screen.Team.arguments,
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("id") ?: ""
            TeamScreen( leagueId = id)
        }
    }
}
