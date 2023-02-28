import com.yolkin.buch.MR
import dev.icerock.moko.mvvm.flow.*
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.StringResource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ktor.ResponseNetwork
import ktor.SearchErrorString
import models.ErrorApp
import models.send.LoginModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import repository.LoginSendRepository
import kotlin.math.log

class SignInViewModel: ViewModel(), KoinComponent {
    private val loginRepository by inject<LoginSendRepository>()

    val login: CMutableStateFlow<String> = MutableStateFlow("").cMutableStateFlow()
    val password: CMutableStateFlow<String> = MutableStateFlow("").cMutableStateFlow()
    private val _isLoading: CMutableStateFlow<Boolean> = MutableStateFlow(false).cMutableStateFlow()
    val isLoading: CStateFlow<Boolean> = _isLoading.cStateFlow()

    private val _lastError: CMutableStateFlow<String> =
        MutableStateFlow("").cMutableStateFlow()

    val isButtonEnabled: CStateFlow<Boolean> =
        combine(isLoading, login, password) { isLoading, login, password ->
            isLoading.not() && login.isNotBlank() && password.isNotBlank() && password.length > 4
        }.stateIn(viewModelScope, SharingStarted.Eagerly, false).cStateFlow()

    private val _actions = Channel<Action>()
    val actions: CFlow<Action> get() = _actions.receiveAsFlow().cFlow()

    fun onCloseError() {
        _lastError.value = ""
    }

    fun loginPressed() {
        viewModelScope.launch {
            _isLoading.value = true
            val authModel = LoginModel(
                username = login.value,
                password = password.value
            )
            when(val result = loginRepository.login(authModel)) {
                is ResponseNetwork.ApiError -> {
                    val searcher = SearchErrorString.Base().search(result.error.detail)
                    _lastError.value = ""
                }
                is ResponseNetwork.NetworkError -> {
                    _actions.send(Action.NetworkException)
                }
                is ResponseNetwork.Ok -> {
                    _actions.send(Action.LoginSuccess)
                }
            }
            _isLoading.value = false
        }
    }

    sealed interface Action {
        object LoginSuccess: Action
        object NetworkException: Action
    }
}