package com.paru.bookapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paru.bookapp.R
import com.paru.bookapp.activity.Description_Activity
import com.paru.bookapp.model.Book
import com.squareup.picasso.Picasso
import java.util.ArrayList

class DashboardRecyclerAdapter (val context:Context,val itemList:ArrayList<Book>):RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>(){
    class DashboardViewHolder(view:View):RecyclerView.ViewHolder(view){

        val txtBookName:TextView=view.findViewById(R.id.txtBookName)
        val txtAuthorName:TextView=view.findViewById(R.id.txtAuthorName)
        val txtPrice:TextView=view.findViewById(R.id.txtPrice)
        val txtRating:TextView=view.findViewById(R.id.txtRating)
        val imgBookImage:ImageView=view.findViewById(R.id.imgBookImage)

        val rlContent:RelativeLayout=view.findViewById(R.id.rlContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.recycler_dashboard_single_row,parent,false)
        return DashboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val book = itemList[position]
        holder.txtBookName.text = book.bookName
        holder.txtAuthorName.text = book.bookAuthor
        holder.txtPrice.text = book.bookPrice
        holder.txtRating.text = book.bookRating
        holder.imgBookImage.setImageResource(book.bookImage)
    }
}