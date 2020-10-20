package com.kalashnyk.denys.defaultproject.utils

import android.content.Context
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nshmura.snappysmoothscroller.LinearLayoutScrollVectorDetector
import com.nshmura.snappysmoothscroller.SnappySmoothScroller

/**
 *
 */
class FeedLayoutManager(context: Context) : LinearLayoutManager(context, RecyclerView.VERTICAL, false) {

    override fun smoothScrollToPosition(recyclerView: RecyclerView, state: RecyclerView.State, position: Int) {
        val scroller=SnappySmoothScroller.Builder()
            .setPosition(position)
            .setScrollVectorDetector(LinearLayoutScrollVectorDetector(this))
            .build(recyclerView.context)

        startSmoothScroll(scroller)
    }

    override fun onFocusSearchFailed(
        focused: View, focusDirection: Int,
        recycler: RecyclerView.Recycler, state: RecyclerView.State
    ): View {
        // https://issuetracker.google.com/issues/37067220
        // We will return focused view to keep focus on EditText
        // Default implementation in LinearLayoutManager returns next view in
        // focusDirection which is a CardView in our case
        // Issue is not fixed in latest support library
        //todo refactor !!
        return focused as? EditText ?: super.onFocusSearchFailed(focused, focusDirection, recycler, state)!!

    }
}
