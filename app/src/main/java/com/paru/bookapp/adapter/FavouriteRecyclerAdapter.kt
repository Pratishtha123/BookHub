package com.paru.bookapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paru.bookapp.R
import com.paru.bookapp.activity.Description_Activity
import com.paru.bookapp.database.BookEntity
import com.squareup.picasso.Picasso

class FavouriteRecyclerAdapter(val context: Context, val bookList: List<BookEntity>) : RecyclerView.Adapter<FavouriteRecyclerAdapter.FavouriteViewHolder>(){
    class FavouriteViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val txtBookName: TextView =view.findViewById(R.id.txtFavBookTitle)
        val txtBookAuthor: TextView =view.findViewById(R.id.txtFavBookAuthor)
        val txtBookPrice: TextView =view.findViewById(R.id.txtFavBookPrice)
        val txtBookRating: TextView =view.findViewById(R.id.txtFavBookRating)
        val imgBookImage: ImageView =view.findViewById(R.id.imgFavBookImage)
        val llContent: LinearLayout =view.findViewById(R.id.llFavContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val  view= LayoutInflater.from(parent.context).inflate(R.layout.recycler_favourite_single_row,parent,false)
        return FavouriteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {

        val book=bookList[position]

        holder.txtBookName.text=book.bookName
        holder.txtBookAuthor.text=book.bookAuthor
        holder.txtBookPrice.text=book.bookPrice
        holder.txtBookRating.text=book.bookRating
        Picasso.get().load(book.bookImage).error(R.drawable.default_book_cover).into(holder.imgBookImage)

        holder.llContent.setOnClickListener {
            val intent = Intent(context, Description_Activity::class.java)
            intent.putExtra("book_id", book.book_id)
            context.startActivity(intent)
        }
    }
}