package amitapps.media.domain.repository

import amitapps.media.domain.model.Blog

interface GetBlogDetailsRepo {

    suspend fun getBlogDetails(id: String): Blog
}