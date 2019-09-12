package com.kalashnyk.denys.defaultproject.presentation.navigation.model

import android.view.View

class TransitionBundle(val views: Array<View> = arrayOf(),
                       val animation: TransitionAnimation = TransitionAnimation.NONE) {

    constructor(animation: TransitionAnimation) : this(arrayOf(), animation)

}