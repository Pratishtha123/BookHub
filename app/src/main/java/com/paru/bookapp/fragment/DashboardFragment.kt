package com.paru.bookapp.fragment

import android.app.DownloadManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.paru.bookapp.R
import com.paru.bookapp.adapter.DashboardRecyclerAdapter
import com.paru.bookapp.model.Book
import com.paru.bookapp.util.ConnectionManager
import java.util.*

lateinit var recyclerView: RecyclerView
lateinit var layoutManager:RecyclerView.LayoutManager
lateinit var recyclerAdapter:DashboardRecyclerAdapter
lateinit var btnInter:Button

val bookInfoList = arrayListOf<Book>()
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

        btnInter=view.findViewById(R.id.btnInter)
        btnInter.setOnClickListener{
            if (ConnectionManager().checkConnectivity(activity as Context))
            {
                Toast.makeText(context,"Internet Available",Toast.LENGTH_LONG).show()
            }
            else
            {
                Toast.makeText(context,"Internet Not Available",Toast.LENGTH_LONG).show()
            }
        }
        layoutManager =LinearLayoutManager(activity)


        val queue=Volley.newRequestQueue(activity as Context)
        val url="http://13.235.250.119/v1/book/fetch_books/"
        val jsonObjectRequest= object :JsonObjectRequest(Request.Method.GET,url,null,Response.Listener {
            val success=it.getBoolean("success")
            if (success){
                val data=it.getJSONArray("data")
                for (i in 0 until data.length()){
                    val bookJsonObject=data.getJSONObject(i)
                    val bookObject=Book(
                        bookJsonObject.getString("book_id"),
                        bookJsonObject.getString("name"),
                        bookJsonObject.getString("author"),
                        bookJsonObject.getString("rating"),
                        bookJsonObject.getString("price"),
                        bookJsonObject.getString("image")
                    )
                    bookInfoList.add(bookObject)
                    recyclerAdapter = DashboardRecyclerAdapter(activity as Context, bookInfoList)

                    recyclerView.adapter = recyclerAdapter
                    recyclerView.layoutManager = layoutManager
                }
            }
            else{
                Toast.makeText(activity as Context,"Some Error Occurred!",Toast.LENGTH_LONG).show()
            }
        },Response.ErrorListener {

        }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-type"] = "application/json"
                headers["token"] = "05ed6a9a010009"
                return headers
            }
        }
        queue.add(jsonObjectRequest)

        return view
    }

}
