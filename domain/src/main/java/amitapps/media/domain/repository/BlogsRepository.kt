package amitapps.media.domain.repository

import amitapps.media.domain.model.Blog
import retrofit2.Response

interface BlogsRepository {

    suspend fun getBlogs(): Response<List<Blog>>
}