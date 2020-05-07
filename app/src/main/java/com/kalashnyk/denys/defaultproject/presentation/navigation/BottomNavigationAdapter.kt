package com.kalashnyk.denys.defaultproject.presentation.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class BottomNavigationAdapter(manager: FragmentManager): FragmentStatePagerAdapter(manager) {

    private var fragments: MutableList<Fragment> = ArrayList()

    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment = fragments[position]

    fun add(fragment: Fragment) {
        fragments.add(fragment)
    }

    fun set(fragments: ArrayList<Fragment>) {
        this.fragments.clear()
        this.fragments = fragments
    }
}