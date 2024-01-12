package amitapps.media.data.network

import amitapps.media.data.network.model.BlogsDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("post")
    suspend fun getBlogs(): Response<BlogsDTO>
}