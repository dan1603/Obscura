package com.kalashnyk.denys.defaultproject.utils.extention

fun <E> MutableList<E>.mergeNewItemList(items: List<E>) {
    items.forEach { item ->
        if (contains(item)) {
            val position = indexOf(item)
            removeAt(position)
            add(position, item)
        } else {
            add(item)
        }
    }
}

fun <E> MutableList<E>.mergeItem(item: E) {
    if (contains(item)) {
        val position = indexOf(item)
        removeAt(position)
        add(position, item)
    } else {
        add(item)
    }
}