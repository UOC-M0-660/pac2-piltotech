package edu.uoc.pac2.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import edu.uoc.pac2.MyApplication
import edu.uoc.pac2.R
import edu.uoc.pac2.data.Book
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.fragment_book_detail.view.*
import com.squareup.picasso.Picasso

/**
 * A fragment representing a single Book detail screen.
 * This fragment is contained in a [BookDetailActivity].
 */
class BookDetailFragment : Fragment() {

    lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_book_detail, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get Book for this detail screen
        loadBook()
    }


    // TODO: Get Book for the given {@param ARG_ITEM_ID} Book id
    private fun loadBook() {
        if (arguments?.containsKey(ARG_ITEM_ID)!!) {
            //(applicationContext as?MyApplication)?.getBooksInteractor()?.getBookById(arguments?.getInt(ARG_ITEM_ID)!!)
            var mitem: Book? = (getActivity()?.getApplicationContext() as MyApplication).getBooksInteractor()?.getBookById(arguments?.getInt(ARG_ITEM_ID)!!)
            if (mitem != null) {
                //Log.d("BookListActivityFrag", "Libro:" + "${mitem.id} => ${mitem.author}")
                initUI(mitem)
            }
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

 //       //throw NotImplementedError()
    }

    // TODO: Share Book Title and Image URL
    private fun shareContent(book: Book) {
        throw NotImplementedError()
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