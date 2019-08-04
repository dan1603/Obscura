package com.kalashnyk.denys.defaultproject.utils.glide

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
open class GlideModule : AppGlideModule() {

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