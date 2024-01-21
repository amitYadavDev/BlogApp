package amitapps.media.data.repository

import amitapps.media.data.network.ApiService
import amitapps.media.data.network.utils.SafeApiRequest
import amitapps.media.domain.model.Blog
import amitapps.media.domain.model.Owner
import amitapps.media.domain.repository.GetBlogDetailsRepo
import javax.inject.Inject

class GetBlogDetailsRepoImpl @Inject constructor(private val apiService: ApiService) : GetBlogDetailsRepo, SafeApiRequest() {
    override suspend fun getBlogDetails(id: String): Blog {
        val response = safeApiRequest { apiService.getBlogDetails(id = id) }

        val blog = Blog(
            id = response.id?:"",
            image = response.image?:"",
            likes = response.likes?:0,
            owner = response.owner?.toDomain()?: Owner("","","","",""),
            publishDate = response.publishDate?:"",
            tags = response.tags?: emptyList(),
            text = response.text?:""
        )

        return blog
    }
}