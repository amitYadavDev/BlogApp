package amitapps.media.blogapp.screens.details

import amitapps.media.domain.use_cases.GetBlogDetailsUseCase
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getBlogDetailsUseCase: GetBlogDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {

    }

    fun getBlogDetails(id: String)
}