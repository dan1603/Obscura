package com.kalashnyk.denys.defaultproject.api.interceptors

import com.google.gson.Gson
import com.jakewharton.rxrelay2.PublishRelay
import com.kalashnyk.denys.defaultproject.api.pojo_error.*
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.HttpsURLConnection

class HttpStatusAndErrorInterceptor (
    private val apiStatusPublishRelay: PublishRelay<ApiStatus>,
    private val apiErrorPublishRelay: PublishRelay<ApiError>,
    private val requestErrorPublishRelay: PublishRelay<RequestError>,
    private val gson: Gson
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        try {
            return chain.proceed(original.newBuilder().build()).also {
                apiStatusPublishRelay.accept(
                    ApiStatus(
                        it.code(),
                        it.request().url().url()
                    )
                )
            }.apply {
                checkForApiError(this)
            }
        } catch (e: Throwable) {
            throw checkRequestError(original, e)
        }
    }

    private fun checkRequestError(request: Request, error: Throwable): Throwable {
        when {
            error is SocketTimeoutException -> {
                apiStatusPublishRelay.accept(
                    ApiStatus(
                        HttpsURLConnection.HTTP_CLIENT_TIMEOUT,
                        request.url().url()
                    )
                )
            }
            error.isInternetConnectionError() -> {
                requestErrorPublishRelay.accept(ConnectionError(error))
            }
            (error is IOException && error.message == "Canceled") -> {
                //Ignore errors that was caused by canceling request by user
            }
            else -> {
                requestErrorPublishRelay.accept(RequestError(error))
                return RequestException(
                    request.url().url().toString() + " " + error.errorLog,
                    error
                )
            }
        }
        return error
    }

    private fun checkForApiError(response: Response): Response {
        if (!response.isSuccessful && response.body() != null) {
            val body = response.peekBody(Long.MAX_VALUE)
            val error = retrofit2.Response.error<ApiError>(body, response)
            val errorJSON = error.errorBody()?.string() ?: ""
            if (errorJSON.isNotEmpty()) {
                val apiError = gson.fromJson(errorJSON, ApiError::class.java)
                apiErrorPublishRelay.accept(apiError)
                throw ApiErrorException(apiError)
            }
        }
        return response
    }
}

fun Throwable.isInternetConnectionError() =
    this is UnknownHostException || this is ConnectException

val Throwable.errorLog: String
    get() = when (this) {
        is ApiErrorException -> "Code: ${apiError.code} Message: ${apiError.errorMessage}"
        else -> ""
    }

class RequestException(urlRequest: String, throwable: Throwable) : Exception(urlRequest, throwable)