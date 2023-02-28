package ktor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.*
import models.ErrorApp

/**
 * Кастомное исключение для сетевого клиента
 */
class NetworkClientException(
    val error: ErrorApp.ErrorCauses,
    val statusCode: Int,
) : Exception()

/**
 * Обработчик всех сетевых ошибок
 */
fun <T : Any> handleAllError(ex: Throwable): ResponseNetwork<T, ErrorApp> {
    println("Data: $ex")
    return when (ex) {
        is NetworkClientException -> ResponseNetwork.ApiError(ex.error, ex.statusCode)
        else -> ResponseNetwork.NetworkError(ex)
    }
}

/**
 * Функция расширения для безопасного GET запроса
 */
suspend inline fun <reified T : Any> HttpClient.getResponse(
    url: String,
    block: HttpRequestBuilder.() -> Unit = {},
): ResponseNetwork<T, ErrorApp> {
    return try {
        get(url, block).let {
            ResponseNetwork.Ok(it.body(), it.status.value)
        }
    } catch (e: Throwable) {
        handleAllError(e)
    }
}

/**
 * Функция расширения для безопасного POST запроса
 */
suspend inline fun <reified T : Any> HttpClient.postResponse(
    url: String,
    block: HttpRequestBuilder.() -> Unit = {},
): ResponseNetwork<T, ErrorApp> {
    return try {
        post(url, block).let {
            ResponseNetwork.Ok(it.body(), it.status.value)
        }
    } catch (e: Throwable) {
        handleAllError(e)
    }
}

/**
 * Функция расширения для безопасного PUT запроса
 */
suspend inline fun <reified T : Any> HttpClient.putResponse(
    url: String,
    block: HttpRequestBuilder.() -> Unit = {},
): ResponseNetwork<T, ErrorApp> {
    return try {
        put(url, block).let {
            ResponseNetwork.Ok(it.body(), it.status.value)
        }
    } catch (e: Throwable) {
        handleAllError(e)
    }
}

/**
 * Функция расширения для безопасного DELETE запроса
 */
suspend inline fun <reified T : Any> HttpClient.deleteResponse(
    url: String,
    block: HttpRequestBuilder.() -> Unit = {},
): ResponseNetwork<T, ErrorApp> {
    return try {
        delete(url, block).let {
            ResponseNetwork.Ok(it.body(), it.status.value)
        }
    } catch (e: Throwable) {
        handleAllError(e)
    }
}
