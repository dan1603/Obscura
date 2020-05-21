package com.kalashnyk.denys.defaultproject.utils.binding

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class SimpleTextModel(
    /**
     *
     */
    val text: String,
    /**
     *
     */
    private val textSizeRes: Int,
    /**
     *
     */
    private val textColorRes: Int
    ) : BaseBindingModel() {
}