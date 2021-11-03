package com.flightbooking.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.flightbooking.BaseActivity
import com.flightbooking.databinding.ActivitySplashBinding
import com.flightbooking.ui.dashboard.DashBoardActivity

/**
 * Created by Nihal Srivastava on 03/11/21.
 */
class SplashActivity : BaseActivity() {
    private val SPLASH_TIME_OUT = 3000
    private var binding: ActivitySplashBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
    }

    override fun onResume() {
        super.onResume()
        Handler(Looper.myLooper()!!).postDelayed({
            val intent = Intent(this@SplashActivity, DashBoardActivity::class.java)
            this@SplashActivity.startActivity(intent)
            this@SplashActivity.finish()

        }, SPLASH_TIME_OUT.toLong())
    }
}