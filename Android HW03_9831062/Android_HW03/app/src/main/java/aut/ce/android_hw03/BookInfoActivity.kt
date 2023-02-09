package aut.ce.android_hw03

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import values.Datasource


class BookInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_info)

        val bookInfo = Datasource().loadBookInfo()

        val position = intent!!.extras!!.getInt("position")

        val coverView: ImageView = findViewById(R.id.book_cover)
        val titleView: TextView = findViewById(R.id.book_title)
        val authorView: TextView = findViewById(R.id.book_author)
        val summaryView: TextView = findViewById(R.id.book_Summary)

        val item = bookInfo[position]
        titleView.text = resources.getString(item.titleResourceId)
        val authorName = resources.getString(item.authorResourceId)
        val pubDate = resources.getString(item.dateResourceId)
        authorView.text = resources.getString(R.string.pub_info, authorName,  pubDate)
        summaryView.text = resources.getString(item.summaryResourceId)
        coverView.setImageResource(item.ImageResourceId)

        summaryView.setMovementMethod(ScrollingMovementMethod())

        val actionBar = supportActionBar
        actionBar!!.title = resources.getString(item.titleResourceId)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}