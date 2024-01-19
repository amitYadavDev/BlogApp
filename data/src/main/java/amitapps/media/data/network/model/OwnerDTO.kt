package amitapps.media.data.network.model

import amitapps.media.domain.model.Owner

data class OwnerDTO(
    val firstName: String,
    val id: String,
    val lastName: String,
    val picture: String,
    val title: String
) {
    fun toDomain(): Owner {
        return Owner(
            firstName, id, lastName, picture, title
        )
    }
}