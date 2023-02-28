package ktor

import com.yolkin.buch.MR
import dev.icerock.moko.resources.StringResource
import ktor.Response
import ktor.ResponseNetwork

class CustomException(message: String?): Exception(message)


inline fun <reified T : Any> handleCustomException(response: Response<T>): ResponseNetwork.Ok<T>  {
    when(response){
        is ResponseNetwork.ApiError -> throw CustomException(response.error.toString())
        is ResponseNetwork.NetworkError -> throw CustomException(response.error?.message.toString())
        is ResponseNetwork.Ok -> return ResponseNetwork.Ok(response.data, response.code)
    }
}



interface SearchErrorString {
    fun search(detail: String): StringResource

    class Base: SearchErrorString {
        override fun search(detail: String): StringResource {
            return when(detail) {
                "not_found" -> MR.strings.not_found
                "unexpected_error" -> MR.strings.unexpected_error
                "bad_password" -> MR.strings.bad_password
                "user_not_found" -> MR.strings.user_not_found
                else -> MR.strings.unexpected_error
            }
        }

    }
}