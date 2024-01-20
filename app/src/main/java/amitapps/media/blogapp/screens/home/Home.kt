package amitapps.media.blogapp.screens.home

import amitapps.media.domain.model.Blog
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    val res = viewModel.blogs.value

    if(res.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

    if(res.error.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = res.error, modifier = Modifier.align(Alignment.Center))
        }
    }

    LazyColumn {
        res.data?.let {
            items(it) {
                PostItem(it)

            }
        }
    }

}

@Composable
fun PostItem(it: Blog) {

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {

        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            CircularImage()
        }

    }
}

@Composable
fun CircularImage(width: Double, height: Double, radius: Double, imageUrl: String) {
    Card(modifier = Modifier.width(width = width.dp).height(height = height.dp),
        shape = RoundedCornerShape(radius.dp)
    ) {
        Image(painter = rememberImagePainter(), contentDescription = null)
    }

}
