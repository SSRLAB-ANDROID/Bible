package by.ssrlab.bible.app

import android.app.Application
import by.ssrlab.bible.client.ApiService
import by.ssrlab.bible.client.RetrofitClient
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit

class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()

        val networkModule = module {
            single { RetrofitClient.create() }
            single { get<Retrofit>().create(ApiService::class.java) }
        }

        startKoin {
            modules(networkModule)
        }
    }
}