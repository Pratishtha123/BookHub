package com.paru.bookapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paru.bookapp.R

lateinit var recyclerView: RecyclerView
lateinit var layoutManager:RecyclerView.LayoutManager

class DashboardFargment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_dashboard, container, false)

        recyclerView =view.findViewById(
            R.id.recyclerView
        )
        layoutManager =LinearLayoutManager(activity)


        return view
    }

}
