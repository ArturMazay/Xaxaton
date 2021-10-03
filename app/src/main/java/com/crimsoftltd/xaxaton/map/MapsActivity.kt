package com.crimsoftltd.xaxaton.map

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import com.crimsoftltd.xaxaton.domain.PlacesItemDomain
import com.crimsoftltd.xaxaton.ui.theme.FitnessViewModel
import com.crimsoftltd.xaxaton.ui.theme.XaxatonTheme
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.ktx.addMarker
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

internal const val KEY_ARG_DETAILS_CITY_NAME = "KEY_ARG_DETAILS_CITY_NAME"

fun launchDetailsActivity(context: Context, item: PlacesItemDomain) {
    context.startActivity(createDetailsActivityIntent(context, item))
}


fun createDetailsActivityIntent(context: Context, item: PlacesItemDomain): Intent {
    val intent = Intent(context, MapsActivity::class.java)
    intent.putExtra(KEY_ARG_DETAILS_CITY_NAME, item.id)
    return intent
}


class MapsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            XaxatonTheme {
                Surface {
                    DetailsScreen(
                      //  onErrorLoading = { finish() },
                        modifier = Modifier
                            .statusBarsPadding()
                            .navigationBarsPadding()
                    )
                }
            }
        }
    }
}


private data class DetailsScreenUiState(
    val exploreModel: PlacesItemDomain? = null,
    val isLoading: Boolean = false,
    val throwError: Boolean = false
)


@Composable
fun DetailsScreen(
   // onErrorLoading: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FitnessViewModel = get()
) {
    val vm by viewModel.dataMaps.observeAsState()
    vm?.let {data-> DetailsContent(data) }
}
@Composable
fun DetailsContent(
    exploreModel: PlacesItemDomain,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        Spacer(Modifier.height(32.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = exploreModel.nameToDisplay,
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = exploreModel.address.toString(),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(16.dp))
        exploreModel.lat?.let { exploreModel.lng?.let { it1 -> CityMapView(it, it1) } }
    }
}


@Composable
private fun CityMapView(latitude: Double?, longitude: Double?) {
    val mapView = rememberMapViewWithLifecycle()
    latitude?.let { longitude?.let { it1 -> MapViewContainer(mapView, it, it1) } }
}


@Composable
private fun MapViewContainer(
    map: MapView,
    latitude: Double,
    longitude: Double
) {
    val cameraPosition = remember(latitude, longitude) {
        LatLng(latitude, longitude)
    }

    LaunchedEffect(map) {
        val googleMap = map.awaitMap()
        googleMap.addMarker { position(cameraPosition) }
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(cameraPosition))
    }

    var zoom by rememberSaveable(map) { mutableStateOf(InitialZoom) }
    ZoomControls(zoom) {
        zoom = it.coerceIn(MinZoom, MaxZoom)
    }

    val coroutineScope = rememberCoroutineScope()
    AndroidView({ map }) { mapView ->
        val mapZoom = zoom
        coroutineScope.launch {
            val googleMap = mapView.awaitMap()
            googleMap.setZoom(mapZoom)
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(cameraPosition))
        }
    }
}

@Composable
private fun ZoomControls(
    zoom: Float,
    onZoomChanged: (Float) -> Unit
) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        ZoomButton("-", onClick = { onZoomChanged(zoom * 0.8f) })
        ZoomButton("+", onClick = { onZoomChanged(zoom * 1.2f) })
    }
}

@Composable
private fun ZoomButton(text: String, onClick: () -> Unit) {
    Button(
        modifier = Modifier.padding(8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.onPrimary,
            contentColor = MaterialTheme.colors.primary
        ),
        onClick = onClick
    ) {
        Text(text = text, style = MaterialTheme.typography.h5)
    }
}

private const val InitialZoom = 8f
const val MinZoom = 2f
const val MaxZoom = 20f