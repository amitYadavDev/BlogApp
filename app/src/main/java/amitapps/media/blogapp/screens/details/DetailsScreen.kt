package amitapps.media.blogapp.screens.details

import amitapps.media.blogapp.navigation.NavigationItem
import amitapps.media.blogapp.screens.home.PostItem
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun DetailsScreen(detailsViewModel: DetailsViewModel = hiltViewModel()) {
    val res = detailsViewModel.details.value

    if (res.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

    if (res.error.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = res.error.toString(), modifier = Modifier.align(Alignment.Center))
        }
    }

    res.data?.let {
        PostItem(it = it, l = {})
        Text(text = it.likes.toString() + " Likes", modifier = Modifier.padding(12.dp))
    }
}

@Composable
fun TagItem(it: String) {
    Card(modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight()) {

    }

}