package edu.uoc.pac2

import android.app.Application
import edu.uoc.pac2.data.*

/**
 * Entry point for the Application.
 */
class MyApplication : Application() {

    private lateinit var booksInteractor: BooksInteractor

    override fun onCreate() {
        super.onCreate()
        // TODO: Init Room Database
        // TODO: Init BooksInteractor

        // populate firestores database
        //val mfirestoreBookData = FirestoreBookData;
        //mfirestoreBookData.addBooksDataToFirestoreDatabase();

    }

    fun getBooksInteractor(): BooksInteractor {
        return booksInteractor
    }

    fun hasInternetConnection(): Boolean {
        // TODO: Add Internet Check logic.
        return true
    }
}