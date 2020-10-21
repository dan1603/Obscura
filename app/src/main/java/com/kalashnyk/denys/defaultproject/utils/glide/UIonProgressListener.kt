package com.kalashnyk.denys.defaultproject.utils.glide

interface UIonProgressListener {
    val granularityPercentage: Float

    fun onProgress(bytesRead: Long, expectedLength: Long)
}