package com.kalashnyk.denys.moduleproject.remote_data_source.di

import android.app.Application
import com.google.gson.Gson
import com.kalashnyk.denys.moduleproject.utils.ApplicationUtils
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.rxrelay3.PublishRelay
import com.kalashnyk.denys.moduleproject.remote_data_source.*
import com.kalashnyk.denys.moduleproject.remote_data_source.api.interceptors.HttpStatusAndErrorInterceptor
import com.kalashnyk.denys.moduleproject.remote_data_source.api.interceptors.IDHeadersInterceptor
import com.kalashnyk.denys.moduleproject.remote_data_source.api.interceptors.StandardHeadersInterceptor
import com.kalashnyk.denys.moduleproject.remote_data_source.api.pojo_error.*
import com.kalashnyk.denys.moduleproject.remote_data_source.communicator.ApiService
import com.kalashnyk.denys.moduleproject.remote_data_source.communicator.ServerCommunicator
import com.kalashnyk.denys.moduleproject.utils.BuildConfig
import com.kalashnyk.denys.moduleproject.utils.preference.PreferencesManager
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

    companion object {
        private val API_URL="https://dev.moroz.cc/"
    }

    @Provides
    @ApiScope
    fun providesApiStatusRelay(): PublishRelay<ApiStatus> {
        return PublishRelay.create()
    }

    @Provides
    @ApiScope
    fun providesApiErrorRelay(): PublishRelay<ApiError> {
        return PublishRelay.create()
    }

    @Provides
    @ApiScope
    fun providesRequestErrorRelay(): PublishRelay<RequestError> {
        return PublishRelay.create()
    }

    @Provides
    @ApiScope
    fun provideStandardHeadersInterceptor(applicationUtils: ApplicationUtils): StandardHeadersInterceptor {
        return StandardHeadersInterceptor(applicationUtils)
    }

    @Provides
    @ApiScope
    fun provideIDHeadersInterceptor(preferencesManager: PreferencesManager): IDHeadersInterceptor {
        return IDHeadersInterceptor(preferencesManager)
    }

    @Provides
    @ApiScope
    fun provideHttpStatusAndErrorInterceptor(
        apiStatusPublishRelay: PublishRelay<ApiStatus>,
        apiErrorPublishRelay: PublishRelay<ApiError>,
        requestErrorPublishRelay: PublishRelay<RequestError>,
        gson: Gson
    ): HttpStatusAndErrorInterceptor {
        return HttpStatusAndErrorInterceptor(
            apiStatusPublishRelay,
            apiErrorPublishRelay,
            requestErrorPublishRelay,
            gson
        )
    }

    @Provides
    @ApiScope
    fun provideOkHttpClient(
        headersInterceptor: StandardHeadersInterceptor,
        idHeadersInterceptor: IDHeadersInterceptor,
        apiStatusAndErrorInterceptor: HttpStatusAndErrorInterceptor
    ): OkHttpClient {
        //Custom interceptor to add our header to every request
        //with timeout and delay
        var httpClient=OkHttpClient.Builder()
            .connectionPool(ConnectionPool(5, 30, TimeUnit.SECONDS))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(headersInterceptor)
            .addInterceptor(idHeadersInterceptor)
            .addInterceptor(apiStatusAndErrorInterceptor)

        if (BuildConfig.DEBUG) {
            httpClient=httpClient
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level=HttpLoggingInterceptor.Level.BODY
                    }
                )
                .addNetworkInterceptor(StethoInterceptor())
        }

        return httpClient.build()
    }

    @Provides
    @ApiScope
    fun provideRetrofitBuilder(client: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    @Provides
    @ApiScope
    fun provideRetrofit(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl(API_URL).build()
    }

    @Provides
    @ApiScope
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create<ApiService>(
            ApiService::class.java)
    }

    @Provides
    @ApiScope
    fun provideCommunicator(apiService: ApiService): ServerCommunicator {
        return ServerCommunicator(
            apiService
        )
    }

    @Provides
    @ApiScope
    fun providesFeedRemoteDataSource(serverCommunicator: ServerCommunicator): FeedRemoteDataSource {
        return FeedRemoteDataSourceImpl(serverCommunicator)
    }

    @Provides
    @ApiScope
    fun providesUsersRemoteDataSource(serverCommunicator: ServerCommunicator): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(serverCommunicator)
    }

    @Provides
    @ApiScope
    fun providesMessagesRemoteDataSource(serverCommunicator: ServerCommunicator): MessagesRemoteDataSource {
        return MessagesRemoteDataSourceImpl(serverCommunicator)
    }

}
