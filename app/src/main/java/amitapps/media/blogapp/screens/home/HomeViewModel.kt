package amitapps.media.blogapp.screens.home

import amitapps.media.common.Resource
import amitapps.media.data.paging.BlogRemoteMediator
import amitapps.media.data.room.BlogDao
import amitapps.media.domain.repository.GetBlogsRepository
import amitapps.media.domain.use_cases.GetBlogsUseCase
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val getBlogsUseCase: GetBlogsUseCase,
    private val getPagerBlogsRepo: GetBlogsRepository,
    private val blogDao: BlogDao) : ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val pager = Pager(config = PagingConfig(pageSize = 10, prefetchDistance = 5), remoteMediator = BlogRemoteMediator(
        getPagerBlogsRepo = getPagerBlogsRepo, blogDao = blogDao)) {
        blogDao.getAllBlogItems()
    }.flow.cachedIn(viewModelScope)

    private val _blogs = mutableStateOf<HomeState>(HomeState())
    val blogs: State<HomeState> = _blogs

    fun getBlogs() {
        getBlogsUseCase().onEach {
           when(it) {
               is Resource.Loading -> {
                   _blogs.value = HomeState(isLoading = true)
               }
               is Resource.Success -> {
                   _blogs.value = HomeState(data = it.data)
               }
               is Resource.Error -> {
                    _blogs.value = HomeState(error = it.message.toString())
               }
           }
        }
    }

}