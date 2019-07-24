package com.kalashnyk.denys.defaultproject.presentation.activities.splash

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.ActivitySplashBinding
import com.kalashnyk.denys.defaultproject.presentation.activities.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private val timer = 3000L
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(MainActivity.newInstance(this))
            finish()
        }, timer)
    }
}
