package models

import dev.icerock.moko.resources.StringResource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class ErrorApp {
    /**
     * Ошибка сети
     */
    @Serializable
    object ErrorNetwork : ErrorApp()

    /**
     * Неищвестная ошибка
     */
    @Serializable
    object ErrorUnknown : ErrorApp() {
        const val error = "Неизвестная ошибка"
    }

    /**
     * Ошибка с причиной
     */
    @Serializable
    data class ErrorCauses(
        val detail: String
    ) : ErrorApp()
}
