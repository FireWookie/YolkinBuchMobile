package exception

import org.koin.dsl.module

val exceptionModule = module {
    single {
        ExceptionRepository.Base()
    }
}