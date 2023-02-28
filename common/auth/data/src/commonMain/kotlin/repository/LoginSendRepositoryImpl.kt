package repository

import ktor.Response
import ktor.handleCustomException
import models.response.SuccessLoginModel
import models.send.LoginModel
import network.AuthApi

class LoginSendRepositoryImpl(
    private val network: AuthApi
): LoginSendRepository {
    override suspend fun login(auth: LoginModel): Response<SuccessLoginModel> {
        return network.login(auth)
    }
}