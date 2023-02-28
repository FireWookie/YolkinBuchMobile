import exception.exceptionModule
import json.serializationModule
import ktor.ktorModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.PlatformConfiguration
import settings.settingsModule

object PlatformSDK {
    private val listCoreModule =
        listOf(ktorModule, serializationModule, settingsModule, exceptionModule)

    fun init(
        configuration: PlatformConfiguration,
        androidModule: Module ?= null
    ) = startKoin {
        modules(listCoreModule)
        val umbrellaModule = module {
            single {
                configuration
            }
        }
        modules(umbrellaModule)
        androidModule?.let {
            modules(it)
        }
        modules(authModule)
    }
}