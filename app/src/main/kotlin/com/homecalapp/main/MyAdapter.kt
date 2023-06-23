package com.homecalapp.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.ListFragment

internal class MyAdapter(var context: Context, fm: FragmentManager, var totalTabs: Int) :
    FragmentPagerAdapter(fm) {

    override fun getItem(postion: Int): Fragment = when (postion) {
        0 -> CalendarFragment()
        1 -> ListFragment()
        2 -> ClassFragment()
        else -> getItem(postion)
    }

    override fun getCount(): Int = totalTabs
}
