package amitapps.media.blogapp.navigation

sealed class NavigationItem(val route: String) {

    object Home: NavigationItem("Home")
    object DetailsScreen: NavigationItem("Details/{blogId}")
}