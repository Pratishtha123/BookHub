package com.paru.bookapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splash_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_)

        Handler().postDelayed({
            val intent= Intent(this@Splash_Activity, MainActivity::class.java)
            startActivity(intent)
        },5000)
    }
    override fun onPause() {
        super.onPause()
        finish()
    }
}
