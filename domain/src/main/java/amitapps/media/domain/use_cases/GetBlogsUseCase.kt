package amitapps.media.domain.use_cases

import amitapps.media.common.Resource
import amitapps.media.domain.model.Blog
import amitapps.media.domain.repository.GetBlogsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBlogsUseCase @Inject constructor(private val getBlogsRepository: GetBlogsRepository) {
    suspend operator fun invoke() : Flow<Resource<List<Blog>>> = flow {
        emit(Resource.Loading(null))
        try {
            val response = getBlogsRepository.getBlogs()
            emit(Resource.Success(data = response))
        } catch (e: Exception) {
            emit(Resource.Error("error happened"))
        }
    }
}