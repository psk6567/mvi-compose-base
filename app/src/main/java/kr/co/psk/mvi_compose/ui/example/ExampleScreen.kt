package kr.co.psk.mvi_compose.ui.example

import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kr.co.psk.common.CommonUtils
import kr.co.psk.common.R
import kr.co.psk.common.extention.findActivity
import kr.co.psk.mvi_compose.ui.example.datastore.ExampleDataStoreScreen
import kr.co.psk.mvi_compose.ui.example.datastore.ExampleDataStoreViewModel
import kr.co.psk.mvi_compose.ui.example.retrofit2.ExampleRetrofit2Screen
import kr.co.psk.mvi_compose.ui.example.retrofit2.ExampleRetrofit2ViewModel
import kr.co.psk.mvi_compose.ui.example.room.ExampleRoomScreen
import kr.co.psk.mvi_compose.ui.example.room.ExampleRoomViewModel
import kr.co.psk.mvi_compose.ui.example.socket.ExampleSocketScreen
import kr.co.psk.mvi_compose.ui.example.socket.ExampleSocketViewModel

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

    BackOnPressed()

    Scaffold(
        topBar = {
        },
        bottomBar = {
            NavigationBottomBar(
                currentRoute = currentRoute,
                onClickTab = { toRoute ->
                    //중복호출 방지
                    if (currentRoute == toRoute) return@NavigationBottomBar
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
        addSocketScreen()
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

private fun NavGraphBuilder.addSocketScreen(){
    composable(
        route = ExampleTab.SOCKET.name
    ) {
        val viewModel : ExampleSocketViewModel = hiltViewModel()
        ExampleSocketScreen(viewModel)
    }
}

@Composable
private fun BackOnPressed() {
    val context = LocalContext.current.findActivity()
    var backPressedState by remember { mutableStateOf(true) }
    var backPressedTime = 0L

    BackHandler(enabled = backPressedState) {
        if (System.currentTimeMillis() - backPressedTime <= 2000L) {
            // 앱 종료
            context.finish()
        } else {
            backPressedState = true
            CommonUtils.toast(context, context.getString(R.string.app_finish_double_press))
        }
        backPressedTime = System.currentTimeMillis()
    }
}