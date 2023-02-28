package network

import io.ktor.client.*
import io.ktor.client.request.*
import ktor.Response
import ktor.postResponse
import models.response.SuccessLoginModel
import models.send.LoginModel

class AuthApiImpl(
    private val httpClient: HttpClient
): AuthApi {
    override suspend fun login(auth: LoginModel): Response<SuccessLoginModel> {
        return httpClient.postResponse("user/auth/") {
            setBody(auth)
        }
    }
}