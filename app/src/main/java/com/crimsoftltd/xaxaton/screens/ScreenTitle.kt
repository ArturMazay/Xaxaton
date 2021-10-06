package com.crimsoftltd.xaxaton.screens

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.crimsoftltd.xaxaton.domain.PlacesItemDomain
import com.crimsoftltd.xaxaton.ui.theme.FitnessViewModel
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

enum class FitnessScreen {
    Active, Future, Plan
}

@ExperimentalMaterialApi
@Composable
fun FitnessHome(
    navController: NavController,
    modifier: Modifier = Modifier
) {


    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.statusBarsPadding(),
        drawerContent = {
            //  FitnessDrawer()
        }
    ) {
        val scope = rememberCoroutineScope()
        FitnessHomeContent(
            modifier = modifier,
            openDrawer = {
                scope.launch {
                    // scaffoldState.drawerState.open()
                }
            }, navController = navController
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun FitnessHomeContent(
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    vm: FitnessViewModel = get(), navController: NavController
) {

    val suggestedDestinations by vm.data.observeAsState()
    // val suggestedDestinations by vm.suggestedDestinations.collectAsState()
    // val onPeopleChanged: (Int) -> Unit = { vm.updatePeople(it) }
    var tabSelected by remember { mutableStateOf(FitnessScreen.Active) }

    BackdropScaffold(
        modifier = modifier,
        scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed), //выставляет видимость ботомшеет
        frontLayerScrimColor = Color.Unspecified,
        appBar = {
            HomeTabBar(openDrawer, tabSelected, onTabSelected = { tabSelected = it })
        },
        backLayerContent = {
            /*    SearchContent(
                    tabSelected,
                    vm,
                    onPeopleChanged
                )*/
        },
        frontLayerContent = {
            when (tabSelected) {
                FitnessScreen.Active -> {
                    suggestedDestinations?.let { places ->
                        ExploreSection(
                            title = "Выберети место для тренировки.",
                            exploreList = places as List<PlacesItemDomain>,
                            navController = navController
                        )
                    }
                }
                FitnessScreen.Future -> {
                    /*     ExploreSection(
                              title = "Ознакомтесь с недостающими обьектами",
                              exploreList = vm.future,
                              onItemClicked = onExploreItemClicked
                          )*/
                }
                FitnessScreen.Plan -> {
                    suggestedDestinations?.let { futureList ->
                        ExploreSection(
                            title = "Ознакомтесь с недостающими обьектами",
                            exploreList = futureList as List<PlacesItemDomain>,navController = navController
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun HomeTabBar(
    openDrawer: () -> Unit,
    tabSelected: FitnessScreen,
    onTabSelected: (FitnessScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    FitnessTabBar(
        modifier = modifier,
        onMenuClicked = openDrawer
    ) { tabBarModifier ->
        FitnessTab(
            modifier = tabBarModifier,
            titles = FitnessScreen.values().map { it.name },
            tabSelected = tabSelected,
            onTabSelected = { newTab -> onTabSelected(FitnessScreen.values()[newTab.ordinal]) }
        )
    }
}
