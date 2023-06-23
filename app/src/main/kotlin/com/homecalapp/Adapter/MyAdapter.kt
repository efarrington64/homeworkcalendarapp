package com.homecalapp.Adapter

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.ListFragment
import com.homecalapp.Home.CalendarFragment
import com.homecalapp.Home.ClassFragment
import com.homecalapp.app.R

import com.homecalapp.toast

internal class MyAdapter(var context: Context, fm: FragmentManager, var totalTabs: Int): FragmentPagerAdapter(fm) {


    override fun getItem(postion: Int): Fragment {
 return when (postion){
     0 -> {
         CalendarFragment()
     }
            1-> {
                ListFragment()
            }
            2-> {
                ClassFragment()

            }
            else -> getItem(postion)
 }
    }

    override fun getCount():Int {
 return totalTabs
    }
}
