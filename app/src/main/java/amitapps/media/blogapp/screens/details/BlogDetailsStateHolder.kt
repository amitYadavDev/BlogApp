package amitapps.media.blogapp.screens.details

import amitapps.media.domain.model.Blog

data class BlogDetailsStateHolder (
    val isLoading: Boolean = false,
    val data: Blog? = null,
    val error: String = ""
)