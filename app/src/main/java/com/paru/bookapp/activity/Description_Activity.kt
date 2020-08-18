package com.paru.bookapp.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.paru.bookapp.R
import com.paru.bookapp.database.BookDatabase
import com.paru.bookapp.database.BookEntity
import com.paru.bookapp.util.ConnectionManager
import com.squareup.picasso.Picasso
import org.json.JSONObject

lateinit var txtBookName: TextView
lateinit var txtAuthorName: TextView
lateinit var txtPrice: TextView
lateinit var txtRating: TextView
lateinit var imgBookImage: ImageView
lateinit var txtDescription: TextView
lateinit var btnAddToFav: Button
lateinit var progressLayout: RelativeLayout
lateinit var progressBar: ProgressBar
lateinit var dtoolbar: Toolbar

var bookId:String?="100"

class Description_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description_)

        txtBookName = findViewById(R.id.txtBookName)
        txtAuthorName = findViewById(R.id.txtAuthorName)
        txtPrice = findViewById(R.id.txtPrice)
        txtRating = findViewById(R.id.txtRating)
        imgBookImage = findViewById(R.id.imgBookImage)
        txtDescription = findViewById(R.id.txtDescription)
        btnAddToFav = findViewById(R.id.btnAddToFav)
        progressBar = findViewById(R.id.progressBar)

        progressBar.visibility = View.VISIBLE
        progressLayout = findViewById(R.id.progressLayout)
        progressLayout.visibility = View.VISIBLE

        dtoolbar=findViewById(R.id.dtoolbar)
        setSupportActionBar(dtoolbar)
        supportActionBar?.title = "Book Details"

        if (intent != null) {
            bookId = intent.getStringExtra("book_id")
        } else {
            finish()
            Toast.makeText(
                this@Description_Activity,
                "Some unexpected Error occurred!",
                Toast.LENGTH_SHORT
            ).show()
        }
        if (bookId == "100") {
            finish()
            Toast.makeText(
                this@Description_Activity,
                "Some unexpected Error occurred!",
                Toast.LENGTH_SHORT
            ).show()
        }
        val queue = Volley.newRequestQueue(this@Description_Activity)
        val url = "http://13.235.250.119/v1/book/get_book/"

        val jsonParams = JSONObject()
        jsonParams.put("book_id", bookId)

        if (ConnectionManager().checkConnectivity(this@Description_Activity)) {
            val jsonRequest =
                object : JsonObjectRequest(Request.Method.POST, url, jsonParams, Response.Listener {

                    try {
                        val success = it.getBoolean("success")
                        if (success) {
                            val bookJsonObject = it.getJSONObject("book_data")
                            progressLayout.visibility = View.GONE
                            btnAddToFav.visibility=View.VISIBLE

                            val bookImageUrl = bookJsonObject.getString("image")
                            Picasso.get().load(bookJsonObject.getString("image"))
                                .error(R.drawable.default_book_cover).into(imgBookImage)

                            txtBookName.text = bookJsonObject.getString("name")
                            txtAuthorName.text = bookJsonObject.getString("author")
                            txtPrice.text = bookJsonObject.getString("price")
                            txtRating.text = bookJsonObject.getString("rating")
                            txtDescription.text = bookJsonObject.getString("description")

                            val bookEntity=BookEntity(
                                bookId?.toInt()as Int,
                                txtBookName.text.toString(),
                                txtAuthorName.text.toString(),
                                txtPrice.text.toString(),
                                txtRating.text.toString(),
                                txtDescription.text.toString(),
                                bookImageUrl
                            )

                            val checkFav=DBAsyncTask(applicationContext,bookEntity,1).execute()
                            val isFav=checkFav.get()

                            if(isFav)
                            {
                                btnAddToFav.text="Remove from Favourites"
                                val favColor=ContextCompat.getColor(applicationContext,R.color.colorFavourite)
                                btnAddToFav.setBackgroundColor(favColor)
                            }
                            else{
                                btnAddToFav.text="Add to Favourites"
                                val noFavColor=ContextCompat.getColor(applicationContext,R.color.colorPrimary)
                                btnAddToFav.setBackgroundColor(noFavColor)
                            }

                            btnAddToFav.setOnClickListener {

                                if (!DBAsyncTask(applicationContext, bookEntity, 1).execute()
                                        .get()
                                ) {

                                    val async =
                                        DBAsyncTask(applicationContext, bookEntity, 2).execute()
                                    val result = async.get()
                                    if (result) {
                                        Toast.makeText(
                                            this@Description_Activity,
                                            "Book added to Favourites",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        btnAddToFav.text = "Remove from Favourites"
                                        val favColor = ContextCompat.getColor(
                                            applicationContext,
                                            R.color.colorFavourite
                                        )
                                        btnAddToFav.setBackgroundColor(favColor)
                                    } else {
                                        Toast.makeText(
                                            this@Description_Activity,
                                            "Some Error Occurred",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                } else {
                                    val async =
                                        DBAsyncTask(applicationContext, bookEntity, 3).execute()
                                    val result = async.get()
                                    if (result) {
                                        Toast.makeText(
                                            this@Description_Activity,
                                            "Book removed from Favourites",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        btnAddToFav.text = "Add to Favourites"
                                        val noFavColor = ContextCompat.getColor(
                                            applicationContext,
                                            R.color.colorPrimary
                                        )
                                        btnAddToFav.setBackgroundColor(noFavColor)
                                    } else {
                                        Toast.makeText(
                                            this@Description_Activity,
                                            "Some Error Occurred",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        } else {
                            Toast.makeText(
                                this@Description_Activity,
                                "Some Error Occurred!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(
                            this@Description_Activity,
                            "Some Error Occurred!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }, Response.ErrorListener {
                    Toast.makeText(
                        this@Description_Activity,
                        "Volley Error $it",
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                    override fun getHeaders(): MutableMap<String, String> {
                        val headers = HashMap<String, String>()
                        headers["Content-type"] = "application/json"
                        headers["token"] = "05ed6a9a010009"
                        return headers
                    }
                }
            queue.add(jsonRequest)
        }else
        {
            val dialog= AlertDialog.Builder(this@Description_Activity)
            dialog.setTitle("Error")
            dialog.setMessage("Internet Connection Found")
            dialog.setPositiveButton("Open Settings"){ test,listener->
                val settingsIntent= Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingsIntent)
                finish()
            }
            dialog.setNegativeButton("Exit"){ test,listener->

                ActivityCompat.finishAffinity(this@Description_Activity)
            }
            dialog.create()
            dialog.show()
        }
    }
    class DBAsyncTask(val context: Context, val bookEntity: BookEntity, val mode:Int): AsyncTask<Void, Void, Boolean>(){
        val db= Room.databaseBuilder(context, BookDatabase::class.java,"books-db").build()

        override fun doInBackground(vararg params: Void?): Boolean {

            when(mode){

                1->{
                    //Check DB if the book is favourite or not
                    val book:BookEntity?=db.bookDao().getBookById(bookEntity.book_id.toString())
                    db.close()
                    return book!=null
                }
                2->{
                    //Save the book in DB as favourite
                    db.bookDao().insertBook(bookEntity)
                    db.close()
                    return true
                }
                3->{
                    //Remove the favourite book
                    db.bookDao().deleteBook(bookEntity)
                    db.close()
                    return true
                }
            }

            return false
        }
    }
}
