package edu.uoc.pac2.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import edu.uoc.pac2.MyApplication
import edu.uoc.pac2.R
import edu.uoc.pac2.data.Book


/**
 * An activity representing a list of Books.
 */
class BookListActivity : AppCompatActivity() {

    private val TAG = "BookListActivity"

    private lateinit var adapter: BooksListAdapter

    private var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        // Init UI
        initToolbar()
        initRecyclerView()

        // Get Books
        val hasIntenet : Boolean = ((applicationContext as? MyApplication)?.hasInternetConnection()) ?: false
        if (hasIntenet)
                getBooks()

        // TODO: Add books data to Firestore [Use once for new projects with empty Firestore Database]
    }

    // Init Top Toolbar
    private fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title
    }

    // Init RecyclerView
    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.book_list)
        // Set Layout Manager
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        // Init Adapter
        val initlist: List<Book> = (applicationContext as? MyApplication)?.getBooksInteractor()?.getAllBooks() ?: emptyList()
        adapter = BooksListAdapter(initlist)
        recyclerView.adapter = adapter
    }

    // TODO: Get Books and Update UI
    private fun getBooks() {
        val docRef = db.collection("books").get()
        docRef.addOnSuccessListener { documents ->
            for (document in documents) {
                Log.d(TAG, "BookData:" + "${document.id} => ${document.data}")
                //Log.d(TAG, "BookData:" + "${document.id} => ${document.data.getValue("title").toString()}")
            }
            val books: List <Book> = documents.mapNotNull {it.toObject (Book::class .java)}
            adapter.setBooks(books)

            (applicationContext as? MyApplication)?.getBooksInteractor()?.saveBooks(books)

        }
        docRef.addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents: ", exception)
        }


    }

    // TODO: Load Books from Room
    private fun loadBooksFromLocalDb() {
        throw NotImplementedError()
    }

    // TODO: Save Books to Local Storage
    private fun saveBooksToLocalDatabase(books: List<Book>) {
        throw NotImplementedError()
    }
}