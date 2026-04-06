package com.rdissi.myfdjtest.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rdissi.myfdjtest.ui.component.MyTopAppBar
import com.rdissi.myfdjtest.ui.navigation.Navigation
import com.rdissi.myfdjtest.ui.navigation.Screen

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val context = LocalContext.current
    val topAppBarInfo = updateTopBar(navController)

    Scaffold(
        topBar = {
            if (topAppBarInfo.isVisible) {
                MyTopAppBar(
                    title = topAppBarInfo.title,
                    canNavigateBack = topAppBarInfo.showBackIcon,
                    navigateUp = {
                        navController.navigateUp()
                    },
                    showShareIcon = topAppBarInfo.showShareIcon,
                    onShareClick = {
                        viewModel.shareStory(context)
                    },
                )
            }
        },
    ) { innerPadding ->
        Navigation(
            modifier = modifier.padding(innerPadding),
            navController = navController,
        )
    }
}

@Composable
fun updateTopBar(navController: NavHostController): TopBarInfo {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen =
        when (backStackEntry?.destination?.route) {
            Screen.HomeScreen.route -> Screen.HomeScreen
            Screen.Team.routeWithArgs -> Screen.Team
            else -> Screen.HomeScreen
        }
    return when (currentScreen) {
        Screen.HomeScreen ->
            TopBarInfo(
                title = stringResource(id = currentScreen.title),
                showBackIcon = false,
                showShareIcon = false,
                isVisible = true,
            )
        Screen.Team ->
            TopBarInfo(
                title = stringResource(id = currentScreen.title),
                showBackIcon = true,
                showShareIcon = true,
                isVisible = true,
            )
    }
}

data class TopBarInfo(
    val title: String,
    val showBackIcon: Boolean,
    val showShareIcon: Boolean,
    val isVisible: Boolean,
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
