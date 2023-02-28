package models.response

@kotlinx.serialization.Serializable
data class SuccessLoginModel(
    val access_token: String,
    val refresh_token: String
)
