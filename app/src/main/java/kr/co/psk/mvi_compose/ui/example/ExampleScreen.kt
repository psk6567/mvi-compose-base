package kr.co.psk.mvi_compose.ui.example

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kr.co.psk.mvi_compose.ui.example.datastore.ExampleDataStoreScreen
import kr.co.psk.mvi_compose.ui.example.datastore.ExampleDataStoreViewModel
import kr.co.psk.mvi_compose.ui.example.retrofit2.ExampleRetrofit2Screen
import kr.co.psk.mvi_compose.ui.example.retrofit2.ExampleRetrofit2ViewModel
import kr.co.psk.mvi_compose.ui.example.room.ExampleRoomScreen
import kr.co.psk.mvi_compose.ui.example.room.ExampleRoomViewModel
import kr.co.psk.mvi_compose.ui.example.socket.ExampleSocketScreen

enum class ExampleTab() {
    RETROFIT2,
    ROOM,
    DATASTORE,
    SOCKET
}

@Composable
fun ExampleScreen() {
    val navigationController = rememberNavController()
    val navBackStackEntry by navigationController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ExampleTab.RETROFIT2.name
    Scaffold(
        topBar = {
        },
        bottomBar = {
            NavigationBottomBar(
                currentRoute = currentRoute,
                onClickTab = { toRoute ->
                    navigationController.navigate(
                        toRoute
                    ){
                        popUpTo(currentRoute) {
                            saveState = true
                            inclusive = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) {
        Box(
            Modifier
                .padding(it)
                .fillMaxSize()
        )
        {
            NavigationGraph(navigationController)
        }
    }
}

@Composable
private fun NavigationGraph(navController: NavHostController) {
    NavHost(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        navController = navController,
        startDestination = ExampleTab.RETROFIT2.name,
    ) {
        addRetrofitScreen()
        addRoomScreen()
        addDataStoreScreen()
        composable(
            route = ExampleTab.SOCKET.name
        ) {
            ExampleSocketScreen()
        }
    }
}

@Composable
private fun NavigationBottomBar(
    currentRoute: String,
    onClickTab : (String) -> Unit
) {
    NavigationBar (
        modifier = Modifier.height(50.dp),
        containerColor = MaterialTheme.colorScheme.onBackground
    ){
        ExampleTab.values().forEach { tab ->
            NavigationBarItem(
                selected = currentRoute==tab.name,
                onClick = { 
                    onClickTab(tab.name)
                },
                icon = {  },
                label = {
                    Text(text = tab.name)
                },
                colors =
                with(MaterialTheme.colorScheme) {
                    NavigationBarItemDefaults.colors(
                        selectedIconColor = onPrimary,
                        selectedTextColor = onPrimary,
                        unselectedIconColor = secondary,
                        unselectedTextColor = secondary,
                        indicatorColor = onBackground
                    )
                }
            )
        }
    }
}

private fun NavGraphBuilder.addRetrofitScreen(){
    composable(
        route = ExampleTab.RETROFIT2.name
    ) {
        val viewModel : ExampleRetrofit2ViewModel = hiltViewModel()
        ExampleRetrofit2Screen(viewModel)
    }
}

private fun NavGraphBuilder.addRoomScreen(){
    composable(
        route = ExampleTab.ROOM.name
    ) {
        val viewModel : ExampleRoomViewModel = hiltViewModel()
        ExampleRoomScreen(viewModel)
    }
}

private fun NavGraphBuilder.addDataStoreScreen(){
    composable(
        route = ExampleTab.DATASTORE.name
    ) {
        val viewModel : ExampleDataStoreViewModel = hiltViewModel()
        ExampleDataStoreScreen(viewModel)
    }
}