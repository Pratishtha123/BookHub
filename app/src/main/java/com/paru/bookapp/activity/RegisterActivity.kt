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

class RegisterActivity : AppCompatActivity() {

    lateinit var etRegName: EditText
    lateinit var etRegEmail: EditText
    lateinit var etPhone: EditText
    lateinit var etAddress: EditText
    lateinit var btnRegister: Button
    lateinit var sharedprefs: SharedPreferences

    var mailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    var mobilePattern = "[7-9][0-9]{9}"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etRegName = findViewById(R.id.etRegName)
        etRegEmail = findViewById(R.id.etRegEmail)
        etPhone = findViewById(R.id.etPhone)
        etAddress= findViewById(R.id.etAddress)
        btnRegister = findViewById(R.id.btnRegister)

        sharedprefs = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)

        btnRegister.setOnClickListener {

            if (etRegName.text.toString().isEmpty())
                Toast.makeText(this@RegisterActivity, "Enter Name", Toast.LENGTH_LONG).show()
            else if (etRegEmail.text.toString().isEmpty())
                Toast.makeText(this@RegisterActivity, "Enter Email Id", Toast.LENGTH_LONG).show()
            else if (etPhone.text.toString().isEmpty())
                Toast.makeText(this@RegisterActivity, "Enter Mobile Number", Toast.LENGTH_LONG)
                    .show()
            else if (etAddress.text.toString().isEmpty())
                Toast.makeText(this@RegisterActivity, "Enter your Address", Toast.LENGTH_LONG).show()
            else if (!etRegEmail.text.toString().trim().matches(mailPattern.toRegex()))
                Toast.makeText(this@RegisterActivity, "Enter a valid Email Id", Toast.LENGTH_LONG)
                    .show()
            else if (!etPhone.text.toString().trim().matches(mobilePattern.toRegex()))
                Toast.makeText(
                    this@RegisterActivity,
                    "Enter a valid Mobile number",
                    Toast.LENGTH_LONG
                ).show()
            else {
                sharedprefs.edit().putString("name",etRegName.text.toString()).apply()
                sharedprefs.edit().putString("email",etRegEmail.text.toString() ).apply()
                sharedprefs.edit().putString("phone",etPhone.text.toString() ).apply()
                sharedprefs.edit().putString("add",etAddress.text.toString() ).apply()

                    val intent=Intent(this@RegisterActivity,MainActivity::class.java)
                    startActivity(intent)
                }
        }
    }
}
