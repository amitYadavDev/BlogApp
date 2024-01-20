package amitapps.media.data.paging

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
            val page: Int = while (loadType) {
                LoadType.APPEND -> {}
                LoadType.PREPEND -> {}
                LoadType.REFRESH -> {}
            }

            return MediatorResult.Success(true)
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