package amitapps.media.data.repository

import amitapps.media.data.mappers.toDomain
import amitapps.media.data.network.ApiService
import amitapps.media.data.network.utils.SafeApiRequest
import amitapps.media.domain.model.Blog
import amitapps.media.domain.repository.GetBlogsRepository
import javax.inject.Inject

class GetBlogsRepositoryImpl @Inject constructor(private val apiService: ApiService) : GetBlogsRepository, SafeApiRequest() {
    override suspend fun getBlogs(): List<Blog> {
        val response = safeApiRequest { apiService.getBlogs() }
        return response.data?.toDomain()?: emptyList()
    }
}