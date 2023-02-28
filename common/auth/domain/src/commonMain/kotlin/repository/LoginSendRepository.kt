package repository

import ktor.Response
import models.response.SuccessLoginModel
import models.send.LoginModel

interface LoginSendRepository {
    suspend fun login(auth: LoginModel): Response<SuccessLoginModel>
}