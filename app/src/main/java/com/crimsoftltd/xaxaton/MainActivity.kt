package com.crimsoftltd.xaxaton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.core.view.WindowCompat
import com.crimsoftltd.xaxaton.domain.OnExploreItemClicked
import com.crimsoftltd.xaxaton.domain.PlacesItemDomain
import com.crimsoftltd.xaxaton.map.launchDetailsActivity
import com.crimsoftltd.xaxaton.screens.FitnessHome
import com.crimsoftltd.xaxaton.screens.LandingScreen
import com.crimsoftltd.xaxaton.ui.theme.XaxatonTheme
import com.google.accompanist.insets.ProvideWindowInsets


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ProvideWindowInsets {
                XaxatonTheme {
                    MainScreen(
                        onExploreItemClicked = {
                            launchDetailsActivity(
                                context = this,
                                item = it
                            )
                        }
                    )
                }
            }
        }
    }
}

/*private fun launchDetailsActivity(context: MainActivity, item: PlacesItemDomain) {

}*/

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(onExploreItemClicked: OnExploreItemClicked) {
    Surface(color = MaterialTheme.colors.primary) {
        var showLandingScreen by remember { mutableStateOf(true) }
        if (showLandingScreen) {
            LandingScreen(onTimeout = { showLandingScreen = false })
        } else {
            FitnessHome(onExploreItemClicked = onExploreItemClicked)
        }
    }
}


