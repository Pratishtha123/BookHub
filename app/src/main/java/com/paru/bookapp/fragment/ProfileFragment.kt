package com.paru.bookapp.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.paru.bookapp.R

lateinit var sharedPref:SharedPreferences
lateinit var txtName:TextView
lateinit var txtMail:TextView

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

        txtName.text=sharedPref.getString("Name",null)
        txtMail.text=sharedPref.getString("Email",null)
        return view
    }

}
