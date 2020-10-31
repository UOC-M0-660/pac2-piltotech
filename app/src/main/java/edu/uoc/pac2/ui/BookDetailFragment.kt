package edu.uoc.pac2.ui

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import edu.uoc.pac2.MyApplication
import edu.uoc.pac2.R
import edu.uoc.pac2.data.Book
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.fragment_book_detail.view.*

/**
 * A fragment representing a single Book detail screen.
 * This fragment is contained in a [BookDetailActivity].
 */

class BookDetailFragment : Fragment()  {

    lateinit var rootView: View

    var mitem: Book? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_book_detail, container, false)

        val fab: View? = getActivity()?.findViewById(R.id.fab)
        if (fab != null) {
            fab.setOnClickListener { view ->
                mitem?.let { shareContent(it) }
            }
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get Book for this detail screen¡
        AsyncTask.execute {
            loadBook()
            getActivity()?.runOnUiThread {
                mitem?.let { initUI(it) }
            }
        }
    }


    // TODO: Get Book for the given {@param ARG_ITEM_ID} Book id
    private fun loadBook() {
        if (arguments?.containsKey(ARG_ITEM_ID)!!) {
            mitem = (getActivity()?.getApplicationContext() as MyApplication).getBooksInteractor().getBookById(arguments?.getInt(ARG_ITEM_ID)!!)
        }
        //throw NotImplementedError()
    }

    // TODO: Init UI with book details
    private fun initUI(book: Book) {

        activity?.toolbar_layout?.title = book.title

        rootView.book_author.text = book.author
        rootView.book_date.text = book.publicationDate
        rootView.book_detail.text = book.description

        Picasso
                .with(getActivity()?.getApplicationContext())
                .load(book.urlImage)
                .into(rootView.book_image)

        //throw NotImplementedError()
    }

    // TODO: Share Book Title and Image URL
    private fun shareContent(book: Book) {
        val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TITLE, book.title)
                putExtra(Intent.EXTRA_TEXT,  book.urlImage)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

 //       throw NotImplementedError()
    }

    companion object {
        /**
         * The fragment argument representing the item title that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "itemIdKey"

        fun newInstance(itemId: Int): BookDetailFragment {
            val fragment = BookDetailFragment()
            val arguments = Bundle()
            arguments.putInt(ARG_ITEM_ID, itemId)
            fragment.arguments = arguments
            return fragment
        }
    }
}
