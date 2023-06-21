package com.homecalapp.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.homecalapp.app.R

class LoginActivity : AppCompatActivity() {
    lateinit var pager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        pager = findViewById(R.id.pager)
        pager.adapter = PagerAdapter(supportFragmentManager, lifecycle)
    }

    class PagerAdapter(manager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(manager, lifecycle) {
        override fun getItemCount(): Int = 2
        override fun createFragment(position: Int): Fragment = when (position) {
            0 -> LoginSigninFragment()
            else -> LoginSignupFragment()
        }
    }
}
