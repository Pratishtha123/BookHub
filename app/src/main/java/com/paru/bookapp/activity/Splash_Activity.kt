package com.paru.bookapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.paru.bookapp.R

class Splash_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_)

        Handler().postDelayed({
            val intent= Intent(this@Splash_Activity, WelcomeActivity::class.java)
            startActivity(intent)
        },3100)
    }
    override fun onPause() {
        super.onPause()
        finish()
    }
}
