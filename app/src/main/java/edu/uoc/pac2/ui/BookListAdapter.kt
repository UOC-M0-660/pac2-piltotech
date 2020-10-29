package edu.uoc.pac2.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import edu.uoc.pac2.R
import edu.uoc.pac2.data.Book
import java.io.Serializable

/**
 * Adapter for a list of Books.
 */

class BooksListAdapter(private var books: List<Book>) : RecyclerView.Adapter<BooksListAdapter.ViewHolder>() {

    private val evenViewType = 0
    private val oddViewType = 1

    private fun getBook(position: Int): Book {
        return books[position]
    }

    fun setBooks(books: List<Book>) {
        this.books = books
        // Reloads the RecyclerView with new adapter data
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) {
            evenViewType
        } else {
            oddViewType
        }
    }

    // Creates View Holder for re-use
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = when (viewType) {
            evenViewType -> {
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.row_book_list_content_even, parent, false)
            }
            oddViewType -> {
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.row_book_list_content_odd, parent, false)
            }
            else -> {
                throw IllegalStateException("Unsupported viewType $viewType")
            }
        }
        return ViewHolder(view)
    }

    // Binds re-usable View for a given position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = getBook(position)
        holder.titleView.text = book.title
        holder.authorView.text = book.author

        // TODO: Set View Click Listener
        holder.mView.setOnClickListener(View.OnClickListener { v ->
            /*if (mTwoPane) {
                //if table mode, get details in fragment
                val fragment = BookDetailFragment.newInstance(holder.mitema)
                val ft: FragmentTransaction = getSupportFragmentManager().beginTransaction()
                ft.replace(R.id.book_detail, fragment)
                ft.commit()
            } else
            {*/
                //if mobile mode, get details in new activity
                val intent = Intent(v.context, BookDetailActivity::class.java)
                intent.putExtra(BookDetailFragment.ARG_ITEM_ID, book.uid)
                v.context.startActivity(intent)
            //}
        })
    }

    // Returns total items in Adapter
    override fun getItemCount(): Int {
        return books.size
    }

    // Holds an instance to the view for re-use
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.title)
        val authorView: TextView = view.findViewById(R.id.author)
        var mView: View = view}

}
