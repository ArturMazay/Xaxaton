package com.crimsoftltd.xaxaton.map

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.NavType.Companion.FloatType
import androidx.navigation.NavType.Companion.StringType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.crimsoftltd.xaxaton.domain.OnExploreItemClicked
import com.crimsoftltd.xaxaton.domain.PlacesItemDomain
import com.crimsoftltd.xaxaton.screens.FitnessHome
import com.google.gson.Gson

@ExperimentalMaterialApi
@Composable
fun NavigationApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "ScreenTittle") {
        composable("ScreenTittle") { FitnessHome(navController = navController) }
        composable("ScreenMap") { DetailsScreen() }
     /*   composable(
            "ScreenMap",
            arguments = listOf(navArgument("data1") { type = NavType.StringType })
        ) { backStackEnty ->
            backStackEnty.arguments?.getString("data1").let { json ->
                val data = Gson().fromJson(json, PlacesItemDomain::class.java)
                CityMapView(latitude = data.lat, longitude = data.lng)
            }*/
        //}
    }
}