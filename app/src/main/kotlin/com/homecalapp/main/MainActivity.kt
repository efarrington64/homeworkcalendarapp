package com.homecalapp.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.homecalapp.PREFS_EMAIL
import com.homecalapp.app.R
import com.homecalapp.login.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (PREFS_EMAIL !in PreferenceManager.getDefaultSharedPreferences(this)) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
