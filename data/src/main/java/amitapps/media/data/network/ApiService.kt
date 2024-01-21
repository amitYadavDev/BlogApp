package amitapps.media.data.network

import amitapps.media.common.Constants
import amitapps.media.data.network.model.BlogDTO
import amitapps.media.data.network.model.BlogsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("post")
    suspend fun getBlogs(@Header("app-id") appId: String = Constants.APP_ID): Response<BlogsDTO>

    @GET("post")
    suspend fun getBlogsPagination(
        @Header("app-id") appId: String = Constants.APP_ID,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ) : Response<BlogsDTO>

    @GET("post/{id}")
    suspend fun getBlogDetails (
        @Header("app-id") appId: String = Constants.APP_ID,
        @Path("id") id: String
    ) : Response<BlogDTO>
}