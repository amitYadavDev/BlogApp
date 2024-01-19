package amitapps.media.data.mappers

import amitapps.media.data.network.model.BlogDTO
import amitapps.media.data.network.model.BlogsDTO
import amitapps.media.data.network.model.OwnerDTO
import amitapps.media.domain.model.Blog
import amitapps.media.domain.model.Owner

fun List<BlogDTO>.toDomain():List<Blog> {
    return map {
        Blog(
            id = it.id,
            image = it.image,
            likes = it.likes,
            owner = it.owner.toDomain(),
            publishDate = it.publishDate,
            tags = it.tags,
            text = it.text
        )
    }

}