package models.send

@kotlinx.serialization.Serializable
data class LoginModel(
    val username: String,
    val password: String
)
