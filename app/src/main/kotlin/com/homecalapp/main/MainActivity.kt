package com.homecalapp.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout
import com.homecalapp.Adapter.MyAdapter
import com.homecalapp.Adapter.classAdaptor
import com.homecalapp.Model.ClassModel
import com.homecalapp.PREFS_EMAIL
import com.homecalapp.app.R
import com.homecalapp.toast


class MainActivity : AppCompatActivity() {


    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    private lateinit var topAppBar: MaterialToolbar



    private lateinit var list :ArrayList<Int>

    private  var classlist :ArrayList<ClassModel>()
    private lateinit var recyclerView :RecyclerView
    private lateinit var adapter: classAdaptor


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView =findViewById(R.id.recyclerview)
        recyclerView.setHasFixedSize(true)

        list = ArrayList()

recyclerView.adapter= adapter
       adapter = classAdaptor(this, classlist)
        recyclerView.layoutManager =StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)








        topAppBar = findViewById(R.id.TopAppBar)
        topAppBar.setNavigationOnClickListener {
            Toast.makeText(this, "Navigation Icon", Toast.LENGTH_SHORT).show()

        }
        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search -> {
                    Toast.makeText(this, "Search action Clicked", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.notification -> {
                    Toast.makeText(this, "notification action Clicked", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.share -> {
                    Toast.makeText(this, "share action Clicked", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> {
                    false
                }

            }
        }



        viewPager = findViewById(R.id.viewPager)

        tabLayout.addTab(tabLayout.newTab().setText("Calendar"))
        tabLayout.addTab(tabLayout.newTab().setText("List"))
        tabLayout.addTab(tabLayout.newTab().setText("Class"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = MyAdapter(
            this, supportFragmentManager,
            tabLayout.tabCount
        )
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })


    }

}

