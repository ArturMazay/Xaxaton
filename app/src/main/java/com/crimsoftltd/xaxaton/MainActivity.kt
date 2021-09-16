package com.crimsoftltd.xaxaton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.crimsoftltd.xaxaton.ui.theme.FitnessViewModel
import com.crimsoftltd.xaxaton.ui.theme.XaxatonTheme
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {
    val vm by viewModel<FitnessViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyyApp {
                Column() {
                    Greeting(vm)


                }
            }
        }
    }
}

@Composable
fun MyyApp(content: @Composable () -> Unit) {
    XaxatonTheme() {
        Surface(color = Color.White) {
            content()

        }
    }
}


@Composable
fun Greeting(vm: FitnessViewModel) {
    val list by vm.data.observeAsState()
    LazyColumn {
        item {
            list?.forEach { placeItem ->
            TitleName(name = placeItem.name.toString())
                Divider()
                DescriptionContent(description = placeItem.address.toString())
            }
        }
    }

}


@Composable
private fun TitleName(name: String) {

    Text(
        text = name,
        style = MaterialTheme.typography.h5,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .wrapContentWidth(Alignment.CenterHorizontally), //"match_parent" в коде XML//ставит текс в середину
        color = Color.Black
    )
}


@Composable
fun DescriptionContent(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.body1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .wrapContentWidth(Alignment.CenterHorizontally),
        color = Color.Black
    )
}

@Composable
fun CoilImage(image: String) {
    Image(
        painter = rememberImagePainter(
            data = image
        ),
        contentDescription = null,
        modifier = Modifier
            .size(360.dp)
            .fillMaxSize()
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    XaxatonTheme {
        MyyApp {
            //Greeting()
            TitleName("Apple")
        }
    }
}
