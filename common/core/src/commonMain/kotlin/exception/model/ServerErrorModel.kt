package exception.model

import kotlinx.serialization.Serializable

@Serializable
data class ServerErrorModel(
    val code: String,
    var message: String? = null
)