package libraryapp.kchen52.com.surreypubliclibraryclone.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.book_list_item.view.*
import libraryapp.kchen52.com.surreypubliclibraryclone.R
import libraryapp.kchen52.com.surreypubliclibraryclone.book.Book

class BookAdapter(private val books : ArrayList<Book>, private val context: Context?) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.book_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvBookTitle.text = books[position].title
        holder.tvBookSubtitle.text = books[position].subtitle
        holder.tvDueDate.text = books[position].dueDate
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvBookTitle: TextView = view.tv_book_title
        val tvBookSubtitle: TextView = view.tv_book_subtitle
        val tvDueDate: TextView = view.tv_due_date
    }

}