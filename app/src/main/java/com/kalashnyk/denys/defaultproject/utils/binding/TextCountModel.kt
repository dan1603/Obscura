package com.kalashnyk.denys.defaultproject.utils.binding

import android.content.Context

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class TextCountModel(
    /**
     *
     */
    var count: Int = 0,

    /**
     *
     */
    private val resFormatText: Int,
    /**
     *
     */
    private val resDefaultText: Int,
    /**
     *
     */
    private val resFormatTextColor: Int,
    /**
     *
     */
    private val resDefaultTextColor: Int
) : BaseBindingModel() {

    fun prepareContent(context: Context) : Pair<String, Int> =
    count.takeIf { it > 0 }?.let { Pair(context.resources.getString(resFormatText, count), resFormatTextColor)} ?: Pair(context.resources.getString(resDefaultText), resDefaultTextColor)
}