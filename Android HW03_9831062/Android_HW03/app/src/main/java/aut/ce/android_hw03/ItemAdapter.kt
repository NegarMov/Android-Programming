package aut.ce.android_hw03

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class ItemAdapter(
    private val context: Context,
    private val bookInfo: List<BookInfo>
    ) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {


    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val cardView: MaterialCardView = view.findViewById(R.id.card)
        val titleView: TextView = view.findViewById(R.id.book_title)
        val authorView: TextView = view.findViewById(R.id.book_author)
        val coverView: ImageView = view.findViewById(R.id.book_cover)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = bookInfo[position]
        holder.titleView.text = context.resources.getString(item.titleResourceId)
        val authorName = context.resources.getString(item.authorResourceId)
        holder.authorView.text = context.resources.getString(R.string.author_info, authorName)
        holder.coverView.setImageResource(item.ImageResourceId)

        holder.cardView.setOnClickListener {
            val intent = Intent(context, BookInfoActivity::class.java)
            intent.putExtra("position", position)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return bookInfo.size
    }
}