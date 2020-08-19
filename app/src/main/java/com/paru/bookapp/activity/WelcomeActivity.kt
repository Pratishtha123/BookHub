package com.paru.bookapp.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.paru.bookapp.R


lateinit var etName:EditText
lateinit var etEmail:EditText
lateinit var btnLogin:Button
lateinit var sharedpreferences: SharedPreferences

var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedpreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        val isLoggedIn= sharedpreferences.getBoolean("isLoggedIn",false)

        if (isLoggedIn)
        {
            val intent=Intent(this@WelcomeActivity,MainActivity::class.java)
            startActivity(intent)
        }
        else
        {
            setContentView(R.layout.activity_welcome)
        }

        etName=findViewById(R.id.etName)
        etEmail=findViewById(R.id.etEmail)
        btnLogin=findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener{
            val name= etName.text.toString()
            val email= etEmail.text.toString()
            if(name=="")
            {
                Toast.makeText(this@WelcomeActivity,"Please enter Name", Toast.LENGTH_SHORT).show()
            }
            else if(email=="")
            {
                Toast.makeText(this@WelcomeActivity,"Please enter Email address", Toast.LENGTH_SHORT).show()
            }
            else if(!etEmail.text.toString().trim().matches(emailPattern.toRegex()))
                Toast.makeText(this@WelcomeActivity,"Enter a valid Email Id",Toast.LENGTH_LONG).show()
            else
            {
                sharedpreferences.edit().putString("Name",name).apply()
                sharedpreferences.edit().putString("Email",email).apply()
                sharedpreferences.edit().putBoolean("isLoggedIn",true).apply()
                val intent=Intent(this@WelcomeActivity,MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
