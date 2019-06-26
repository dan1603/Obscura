package com.kalashnyk.denys.defaultproject.di.module

import com.kalashnyk.denys.defaultproject.BuildConfig
import com.kalashnyk.denys.defaultproject.di.scope.ApiScope
import com.kalashnyk.denys.defaultproject.usecases.repository.server.ApiService
import com.kalashnyk.denys.defaultproject.usecases.repository.server.ServerCommunicator
import dagger.Module
import dagger.Provides
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class ApiModule {

    @Provides
    @ApiScope
    fun provideCommunicator(apiService: ApiService): ServerCommunicator {
        return ServerCommunicator(apiService)
    }

    @Provides
    @ApiScope
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create<ApiService>(ApiService::class.java!!)
    }

    @Provides
    @ApiScope
    fun provideRetrofit(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl(API_URL).build()
    }

    @Provides
    @ApiScope
    fun provideRetrofitBuilder(): Retrofit.Builder {
        val builder = OkHttpClient.Builder()
            .connectionPool(ConnectionPool(5, 30, TimeUnit.SECONDS))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(httpLoggingInterceptor)
        }

        return Retrofit.Builder()
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    companion object {
        private val API_URL = "https://dev.moroz.cc/"
    }

}
