package amitapps.media.data.paging

import amitapps.media.common.Resource
import amitapps.media.data.room.BlogDao
import amitapps.media.data.room.BlogKey
import amitapps.media.domain.model.Blog
import amitapps.media.domain.repository.GetBlogsRepository
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class BlogRemoteMediator @Inject constructor(
    private val initPage: Int = 1,
    private val getPagerBlogsRepo: GetBlogsRepository,
    private val blogDao: BlogDao
) : RemoteMediator<Int, Blog>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Blog>): MediatorResult {

        return try {
            val page: Int = when (loadType) {
                LoadType.APPEND -> {
                    val remoteKey = getLastKey(state)
                    remoteKey?.next?:return MediatorResult.Success(true)
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(true)
                }
                LoadType.REFRESH -> {
                    val remoteKeys = getClosestKey(state)
                    remoteKeys?.next?.minus(1)?:initPage
                }
            }

            val response = getPagerBlogsRepo.getPagerBlogs(page=page, limit=state.config.pageSize)
            val endOfPagination = response.data.size!! < state.config.pageSize

            when(response) {
                is Resource.Success->{
                    val body = response.data

                    if(loadType == LoadType.REFRESH) {
                        blogDao.deleteAllBlogKeys()
                        blogDao.deleteAllItems()
                    }
                    val prev = if(page == initPage) initPage else initPage - 1
                    val next = if(endOfPagination) null else page + 1

                    val list = body?.map {
                        BlogKey(id = it.id, prev, next)
                    }

                    list?.let {
                        blogDao.insertAllBlogKeys(list)
                    }
                    body?.let {blogDao.insertAllBlogs(body)  }





                }
                is Resource.Loading -> {}
                is Resource.Error -> {}
            }

            if(response is Resource.Success) {
                if(endOfPagination) MediatorResult.Success(true)
                else MediatorResult.Success(false)
            } else {
                MediatorResult.Success(true)
            }


        } catch (e: Exception) {
            MediatorResult.Error(e)
        }

    }

    suspend fun getLastKey(state: PagingState<Int, Blog>): BlogKey? {
        return state.lastItemOrNull()?.let {
            blogDao.getAllKeys(it.id)
        }
    }

    suspend fun getClosestKey(state: PagingState<Int, Blog>): BlogKey? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.let {
                blogDao.getAllKeys(it.id)
            }
        }
    }
}