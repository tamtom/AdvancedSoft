package com.google.advancedsoft.di

import com.google.advancedsoft.HomeNetworkClient
import com.google.advancedsoft.HomeRepo
import com.google.advancedsoft.network.DashBoardServiceAPI
import com.google.advancedsoft.ui.DashBoardViewModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val DEFAULT_TIMEOUT = 90
private const val BASE_URL = "https://jsonkeeper.com/b/"

val viewModelsModule: Module = module {
    viewModel {DashBoardViewModel(get())}
}
val networkModule = module {

    single {
        GsonBuilder().create()
    }
    single<Converter.Factory> { GsonConverterFactory.create(get()) }
    single {
        OkHttpClient.Builder()
            .readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(get())
            .build()

    }
    single { get<Retrofit>().create(DashBoardServiceAPI::class.java) }

    factory {
        HomeRepo(
            HomeNetworkClient(get())
        )
    }
}