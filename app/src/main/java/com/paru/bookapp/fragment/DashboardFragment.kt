package com.paru.bookapp.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paru.bookapp.R
import com.paru.bookapp.adapter.DashboardRecyclerAdapter
import com.paru.bookapp.model.Book

lateinit var recyclerView: RecyclerView
lateinit var layoutManager:RecyclerView.LayoutManager
lateinit var recyclerAdapter:DashboardRecyclerAdapter
val bookInfoList = arrayListOf<Book>(
                        Book("12","A","B","4.8","890",R.drawable.default_book_cover),
    Book("11","C","M","4","785",R.drawable.default_book_cover),
    Book("14","D","N","3.7","490",R.drawable.default_book_cover),
    Book("2","E","O","4.4","15",R.drawable.default_book_cover),
    Book("1","F","P","5","350",R.drawable.default_book_cover),
    Book("18","G","Q","4.1","450",R.drawable.default_book_cover),
    Book("56","H","R","3.8","620",R.drawable.default_book_cover),
    Book("78","I","S","4.5","730",R.drawable.default_book_cover),
    Book("89","J","T","3.9","120",R.drawable.default_book_cover),
    Book("67","K","U","2.8","250",R.drawable.default_book_cover),
    Book("98","L","V","4.8","290",R.drawable.default_book_cover)
    )

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
        recyclerAdapter= DashboardRecyclerAdapter(activity as Context, bookInfoList)
        recyclerView.adapter= recyclerAdapter
        recyclerView.layoutManager= layoutManager


        return view
    }

}
