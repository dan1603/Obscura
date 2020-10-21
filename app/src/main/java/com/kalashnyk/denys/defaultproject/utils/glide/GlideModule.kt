package com.kalashnyk.denys.defaultproject.utils.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import okhttp3.OkHttpClient
import java.io.InputStream

@GlideModule
open class GlideModule : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
        val client = OkHttpClient.Builder()
            .addNetworkInterceptor { chain ->
                val request = chain.request()
                val response = chain.proceed(request)
                val listener = DispatchingProgressManager()
                response.newBuilder()
                    .body(OkHttpProgressResponseBody(request.url(), response.body()!!, listener))
                    .build()
            }
            .build()
        glide.registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(client))
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
//        clearGlideCache(context)
    }

//    private fun clearGlideCache(context: Context) {
//        val expirationCacheCalendar = Calendar.getInstance()
//        expirationCacheCalendar.add(Calendar.DAY_OF_MONTH, -14)
//        Single.just(expirationCacheCalendar.time)
//            .flatMap { expirationCacheDate ->
//                Single.fromCallable {
//                    var count = 0L
//                    File(GlideApp.getPhotoCacheDir(context)?.absolutePath)
//                        .listFiles()
//                        .forEach {
//                            val date = Date(it.lastModified())
//                            if (it.isFile && date.before(expirationCacheDate)) {
//                                if (it.delete()) {
//                                    count++
//                                }
//                            }
//                        }
//                    count
//                }
//            }
//            .subscribe({}, {})
//    }
}