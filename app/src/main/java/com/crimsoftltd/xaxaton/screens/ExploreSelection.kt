package com.crimsoftltd.xaxaton.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.crimsoftltd.xaxaton.R
import com.crimsoftltd.xaxaton.domain.PlacesItemDomain
import com.crimsoftltd.xaxaton.map.CityMapView
import com.crimsoftltd.xaxaton.ui.theme.BottomSheetShape
import com.crimsoftltd.xaxaton.ui.theme.fitness_caption
import com.crimsoftltd.xaxaton.ui.theme.fitness_divider_color
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import com.google.gson.Gson
import kotlinx.coroutines.launch

const val BASE_IMAGE_URL = "http://resivalex.com:5001"

@Composable
fun ExploreSection(
    modifier: Modifier = Modifier,
    title: String,
    exploreList: List<PlacesItemDomain>,
    navController: NavController
) {
    Surface(modifier = modifier.fillMaxSize(), color = Color.White, shape = BottomSheetShape) {
        Column(modifier = Modifier.padding(start = 24.dp, top = 20.dp, end = 24.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.caption.copy(color = fitness_caption)
            )
            Spacer(Modifier.height(8.dp))
            Box(Modifier.weight(1f)) {
                val listState = rememberLazyListState()
                ExploreList(exploreList, listState = listState, navController = navController)
                val showButton by remember {
                    derivedStateOf {
                        listState.firstVisibleItemIndex > 0
                    }
                }
                if (showButton) {
                    val coroutineScope = rememberCoroutineScope()
                    FloatingActionButton(
                        backgroundColor = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .navigationBarsPadding()
                            .padding(bottom = 8.dp),
                        onClick = {
                            coroutineScope.launch {
                                listState.scrollToItem(0)
                            }
                        }
                    ) {
                        Text("Up!")
                    }
                }
            }
        }
    }
}

@Composable
private fun ExploreList(
    exploreList: List<PlacesItemDomain>,
    //onItemClicked: Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
) {
    LazyColumn(modifier = modifier, state = listState) {
        items(exploreList) { exploreItem ->
            Column(Modifier.fillParentMaxWidth()) {
                ExploreItem(
                    modifier = Modifier.fillParentMaxWidth(),
                    item = exploreItem,
                    // onItemClicked = onItemClicked,
                    navController = navController
                )
                Divider(color = fitness_divider_color)
            }
        }
        item {
            Spacer(modifier = Modifier.navigationBarsHeight())
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ExploreItem(
    modifier: Modifier = Modifier,
    item: PlacesItemDomain,
    // onItemClicked: Unit
    navController: NavController
) {
    fun navigateToMap() {
        val placesItem = Gson().toJson(item)
        navController.navigate("ScreenMap")
    }
    Row(
        modifier = modifier
            .clickable { navigateToMap() }
            .padding(top = 12.dp, bottom = 12.dp)
    ) {
        ExploreImageContainer {
            Box {
                val painter = rememberImagePainter(
                    data = item.pictureUrl,
                    builder = {
                        crossfade(true)
                    }
                )
                Box(){
                    CityMapView(latitude = 55.078765, longitude =55.078765 )
                }

                if (painter.state is ImagePainter.State.Loading) {

                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_sports_handball_24),
                        contentDescription = null,
                        modifier = Modifier
                            .size(46.dp)
                            .align(Alignment.Center),
                    )
                }
            }
        }
        Spacer(Modifier.width(24.dp))
        Row {
            Text(
                text = item.nameToDisplay,
                style = MaterialTheme.typography.h6
            )
            Spacer(Modifier.height(8.dp))
           /* Text(
                text = item.address.toString(), //?????? ???????????????? ???? ??????????????????
                style = MaterialTheme.typography.caption.copy(color = fitness_caption)
            )*/
        }
    }
}


@Composable
 fun ExploreImageContainer(content: @Composable () -> Unit) {
    Surface(Modifier.size(width = 60.dp, height = 60.dp), RoundedCornerShape(4.dp)) {
        content()
    }
}
