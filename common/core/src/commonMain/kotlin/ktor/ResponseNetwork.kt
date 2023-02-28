package ktor;

import models.ErrorApp

sealed class ResponseNetwork<out Data : Any, out Failure : Any> {

    /**
     * Успешный запрос
     */
    data class Ok<Data : Any>(val data: Data, val code: Int) : ResponseNetwork<Data, Nothing>()

    /**
     * Ошибка API
     */
    data class ApiError(val error: ErrorApp.ErrorCauses, val code: Int) : ResponseNetwork<Nothing, Nothing>()

    /**
     * Сетевая ошибка
     */
    data class NetworkError(val error: Throwable?) : ResponseNetwork<Nothing, Nothing>()
}

typealias Response<Data> = ResponseNetwork<Data, ErrorApp>