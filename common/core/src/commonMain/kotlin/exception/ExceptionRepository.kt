package exception

import exception.model.ServerErrorModel
import io.ktor.client.call.*
import io.ktor.client.plugins.*

interface ExceptionRepository { // Обработчик ошибок с сервера
    suspend fun apiException(exc: Exception): ServerErrorModel
    fun parsStatusError(params: ServerErrorModel): ServerErrorModel

    class Base : ExceptionRepository {
        override suspend fun apiException(exc: Exception): ServerErrorModel {
            if (exc is ClientRequestException) {
                return try {
                    val result = exc.response.body<ServerErrorModel>()
                    result
                } catch (e: Exception) {
                    ServerErrorModel(code = "unexpected")
                }

            }
            return ServerErrorModel(code = "unexpected")
        }

        override fun parsStatusError(params: ServerErrorModel): ServerErrorModel {
            when (params.code) {
                "unexpected" -> {
                    params.message = "Проверьте подключение к интернету"
                }
                "wrong_phone" -> {
                    params.message = "Введен некорректный номер телефона"
                }
                "more_otp" -> {
                    params.message = "Слишком много смс отправлено в последнее время"
                }
                "otp_not_found" -> {
                    params.message = "Введен некорректный смс-код"
                }
                "username_exist" -> {
                    params.message = "Пользователь с таким username уже существует"
                }
                else -> {
                    params.message = "Unexpected error"
                }
            }
            return params
        }
    }
}