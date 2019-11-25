package com.kalashnyk.denys.defaultproject.utils.extention

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kalashnyk.denys.defaultproject.utils.FIRST_LIST_POSITION

fun RecyclerView.scrollToPosition(position: Int) {
    if (position == FIRST_LIST_POSITION) {
        scrollListToTop()
    } else {
        this.scrollToPosition(position)
    }
}

private fun  RecyclerView.scrollListToTop() {
    val manager=this.layoutManager
    if (manager is LinearLayoutManager) {
        if (manager.findFirstCompletelyVisibleItemPosition() > 10) {
            this.scrollToPosition(FIRST_LIST_POSITION)
        } else {
            this.smoothScrollToPosition(FIRST_LIST_POSITION)
        }
    }
}