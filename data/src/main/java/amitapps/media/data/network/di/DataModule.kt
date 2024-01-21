package amitapps.media.data.network.di

import amitapps.media.common.Constants
import amitapps.media.data.network.ApiService
import amitapps.media.data.repository.GetBlogDetailsRepoImpl
import amitapps.media.data.repository.GetBlogsRepositoryImpl
import amitapps.media.data.room.BlogDao
import amitapps.media.data.room.BlogDataBase
import amitapps.media.domain.repository.GetBlogDetailsRepo
import amitapps.media.domain.repository.GetBlogsRepository
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideGetBlogsRepository(apiService: ApiService) : GetBlogsRepository {
        return GetBlogsRepositoryImpl(apiService = apiService)
    }

    @Provides
    fun provideDataBase(@ApplicationContext context: Context) : BlogDataBase {
        return BlogDataBase.getInstance(context)
    }

    @Provides
    fun provideDao(blogDataBase: BlogDataBase) : BlogDao {
        return blogDataBase.getBlogDao()
    }

    @Provides
    fun provideGetBlogDetailsRepo(apiService: ApiService): GetBlogDetailsRepo {
        return GetBlogDetailsRepoImpl(apiService)
    }

}
