package com.homecalapp.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.homecalapp.PREFS_EMAIL
import com.homecalapp.app.R
import com.homecalapp.defaultSharedPreferences
import com.homecalapp.homework.CreateHomeworkFragment
import com.homecalapp.login.LoginActivity
import com.homecalapp.toast

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var tabLayout: TabLayout
    lateinit var pager: ViewPager2
    lateinit var button: ExtendedFloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (PREFS_EMAIL !in defaultSharedPreferences) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        setContentView(R.layout.activity_main)
        toolbar = findViewById<Toolbar>(R.id.toolbar).also { setSupportActionBar(it) }
        tabLayout = findViewById(R.id.tabLayout)
        pager = findViewById(R.id.pager)
        button = findViewById(R.id.button)

        val adapter = PagerAdapter(supportFragmentManager, lifecycle)
        pager.adapter = adapter
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = adapter.getTitle(position)
        }.attach()

        button.setOnClickListener {
            CreateHomeworkFragment().show(supportFragmentManager, "TAG")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        toast("Not yet implemented, sorry.")
        return super.onOptionsItemSelected(item)
    }

    fun refresh() {
        (pager.adapter as PagerAdapter).homeworkFragment.refresh()
    }

    class PagerAdapter(manager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(manager, lifecycle) {
        val homeworkFragment = HomeworkFragment()

        override fun getItemCount(): Int = 3
        override fun createFragment(position: Int): Fragment = when (position) {
            1 -> homeworkFragment
            else -> StubFragment()
        }

        fun getTitle(position: Int): String = when (position) {
            0 -> "Calendar"
            1 -> "Homework"
            else -> "Class"
        }
    }
}
