package amitapps.media.blogapp.screens.home

import amitapps.media.domain.model.Blog

data class HomeState (
    var isLoading: Boolean = false,
    var data: List<Blog>?= null,
    var error: String = ""
)