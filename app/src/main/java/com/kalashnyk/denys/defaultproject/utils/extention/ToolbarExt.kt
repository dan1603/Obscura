package com.kalashnyk.denys.defaultproject.utils.extention

import android.app.ActionBar
import android.app.Activity
import android.widget.Toolbar

/**
 * @param actionBar
 * @param activity
 */
fun Toolbar.initializeToolbar(actionBar : ActionBar, activity : Activity) : Toolbar {
    this.apply {
        setNavigationOnClickListener { activity.onBackPressed() }
        activity.setActionBar(this)
        actionBar.title = ""
    }
    return this
}

/**
 * @param title
 */
fun Toolbar.setToolbarTitle(title: CharSequence)  {
    this.apply {
        this.title = title
    }
}