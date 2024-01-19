package amitapps.media.data.repository

import amitapps.media.domain.model.Blog
import amitapps.media.domain.repository.BlogsRepository
import retrofit2.Response

class BlogsRepositoryImpl : BlogsRepository {
    override suspend fun getBlogs(): Response<List<Blog>> {

    }

}