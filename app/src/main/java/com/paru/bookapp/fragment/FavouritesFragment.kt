package com.paru.bookapp.fragment

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.paru.bookapp.R
import com.paru.bookapp.adapter.FavouriteRecyclerAdapter
import com.paru.bookapp.database.BookDatabase
import com.paru.bookapp.database.BookEntity


lateinit var recyclerFavourite: RecyclerView
lateinit var progressLayout1: RelativeLayout
lateinit var progressBar1: ProgressBar
lateinit var layoutManager1: RecyclerView.LayoutManager
lateinit var recyclerAdapter1: FavouriteRecyclerAdapter

var dbBookList= listOf<BookEntity>()

class FavouritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view=inflater.inflate(R.layout.fragment_favourites, container, false)

        recyclerFavourite=view.findViewById(R.id.recyclerFavourite)
        progressLayout1=view.findViewById(R.id.progressLayout)
        progressBar1=view.findViewById(R.id.progressBar)
        layoutManager1= GridLayoutManager(activity as Context,2)

        dbBookList=RetrieveFavourites(activity as Context).execute().get()
        if( activity!=null)
        {
            progressLayout1.visibility=View.GONE
            recyclerAdapter1= FavouriteRecyclerAdapter(activity as Context,dbBookList)
            recyclerFavourite.adapter=recyclerAdapter1
            recyclerFavourite.layoutManager=layoutManager1

        }

        return view
    }


    class RetrieveFavourites(val context: Context): AsyncTask<Void, Void, List<BookEntity>>() {
        override fun doInBackground(vararg params: Void?): List<BookEntity> {
            val db= Room.databaseBuilder(context, BookDatabase::class.java,"books-db").build()

            return db.bookDao().getAllBooks()
        }
    }
}
