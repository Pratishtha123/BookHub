package com.paru.bookapp.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.paru.bookapp.R
import com.paru.bookapp.activity.RegisterActivity

lateinit var sharedPref:SharedPreferences
lateinit var txtName:TextView
lateinit var txtMail:TextView
lateinit var txtMob:TextView
lateinit var txtAdd:TextView
lateinit var btnUp:Button

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_profile, container, false)

        sharedPref= (activity as FragmentActivity).getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        txtName=view.findViewById(R.id.txtName)
        txtMail=view.findViewById(R.id.txtMail)
        txtMob=view.findViewById(R.id.txtMob)
        txtAdd=view.findViewById(R.id.txtAdd)
        btnUp=view.findViewById(R.id.btnUp)

        txtName.text= sharedPref.getString("name","Username")
        txtMail.text= sharedPref.getString("email","Email")
        val mobile= sharedPref.getString("phone","Mobile No.")
        txtMob.text="+91-${mobile}"
        txtAdd.text= sharedPref.getString("add","India")

        btnUp.setOnClickListener{
            val intent= Intent(activity as Context,RegisterActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}
