package com.kalashnyk.denys.defaultproject.presentation.widget.pageview.model

import android.content.Context
import androidx.annotation.StringRes
import com.kalashnyk.denys.defaultproject.R

/**
 * type tab
 */
enum class TabPages(
    /**
     * message string resources id
     */
    @StringRes var resource: Int
){

    /**
     * for tab following
     */
    TAB_FOLLOWING(R.string.tab_following),
    /**
     * for tab people
     */
    TAB_PEOPLE(R.string.tab_people),
    /**
     * for tab themes
     */
    TAB_THEMES(R.string.tab_themes),
    /**
     * for tab events
     */
    TAB_EVENTS(R.string.tab_events),
    /**
     * for tab articles
     */
    TAB_ARTICLES(R.string.tab_articles),
    /**
     *
     */
    TAB_FEED(R.string.tab_feed),
    /**
     *
     */
    TAB_MESSAGES(R.string.tab_messages);

    /**
     * for show message body
     */
    fun toString(context: Context):String = context.getString(resource)
}