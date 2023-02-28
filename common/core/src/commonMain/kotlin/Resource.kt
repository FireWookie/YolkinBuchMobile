import exception.model.ServerErrorModel

sealed class Resource<T>(val data: T? = null, val error: ServerErrorModel? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(error: ServerErrorModel? = null, val response: T? = null) : Resource<T>(response, error)
}