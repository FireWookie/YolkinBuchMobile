package network

import ktor.Response
import models.response.SuccessLoginModel
import models.send.LoginModel

interface AuthApi {
    suspend fun login(auth: LoginModel): Response<SuccessLoginModel>
}