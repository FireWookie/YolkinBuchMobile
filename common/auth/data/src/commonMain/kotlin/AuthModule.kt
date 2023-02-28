import network.AuthApi
import network.AuthApiImpl
import org.koin.dsl.module
import repository.LoginSendRepository
import repository.LoginSendRepositoryImpl

val authModule = module {
    single<AuthApi> {
        AuthApiImpl(get())
    }

    single<LoginSendRepository> {
        LoginSendRepositoryImpl(get())
    }
}