package amitapps.media.data.network.model

import amitapps.media.domain.model.Owner

data class OwnerDTO(
    val firstName: String?,
    val id: String?,
    val lastName: String?,
    val picture: String?,
    val title: String?
) {
    fun toDomain(): Owner {
        return Owner(
            firstName = firstName ?: "",
            id = id ?: "",
            lastName = lastName ?: "",
            picture = picture ?: "",
            title = title ?: ""
        )
    }
}