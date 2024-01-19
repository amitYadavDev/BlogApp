package amitapps.media.domain.repository

import amitapps.media.domain.model.Blog

interface GetBlogsRepository {

    suspend fun getBlogs() : List<Blog>
}