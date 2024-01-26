package amitapps.media.blogapp.screens.details

import amitapps.media.common.Resource
import amitapps.media.domain.use_cases.GetBlogDetailsUseCase
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getBlogDetailsUseCase: GetBlogDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val details = mutableSetOf(BlogDetailsStateHolder())

    init {
        savedStateHandle.getLiveData<String>("blogId").value?.let {
            getBlogDetails(it)
        }

    }

    fun getBlogDetails(id: String) {
        getBlogDetailsUseCase(id).onEach {
            when(it) {
                is Resource.Loading -> {
                    details.value = BlogDetailsStateHolder(isLoading = true)
                }
                is Resource.Success -> {
                    details.value = BlogDetailsStateHolder(data = it.data)
                }
                is Resource.Error -> {
                    details.value = BlogDetailsStateHolder(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}