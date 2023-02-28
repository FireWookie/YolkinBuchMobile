package ktor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import models.ErrorApp
import org.koin.dsl.module


val ktorModule = module {
    single {
        HttpEngineFactory().createEngine()
    }
    single {
        HttpClient {
            expectSuccess = true
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json { isLenient = true; ignoreUnknownKeys = true })
            }
            install(HttpTimeout) {
                connectTimeoutMillis = 15000
                requestTimeoutMillis = 30000
            }
            HttpResponseValidator {
                handleResponseExceptionWithRequest(block = { t, _ ->
                    val clientException = t as? ClientRequestException ?: return@handleResponseExceptionWithRequest
                    val eResponse = clientException.response
                    when (eResponse.status.value) {
                        in 400..499 -> {
                            val error: ErrorApp.ErrorCauses = try {
                                eResponse.body()
                            } catch (e: Exception) {
                                ErrorApp.ErrorCauses(detail = "unexpected_error")
                            }
                            throw NetworkClientException(error, eResponse.status.value)
                        }
                    }
                })
            }
            defaultRequest {
                header("Content-Type", "application/json; charset=UTF-8")
                url("http://192.168.0.106/api/v1/")
            }
        }
    }
}