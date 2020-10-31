package edu.uoc.pac2

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import androidx.room.Room
import edu.uoc.pac2.data.*

/**
 * Entry point for the Application.
 */
class MyApplication : Application() {

    private lateinit var booksInteractor: BooksInteractor
    lateinit var localDB: ApplicationDatabase

    override fun onCreate() {
        super.onCreate()
        // TODO: Init Room Database
        localDB = Room.databaseBuilder(this,
                ApplicationDatabase::class.java,"basedatos-app").build()
        //TMP!!!! remove allowMainThreadQueries

        // TODO: Init BooksInteractor
        booksInteractor = BooksInteractor(localDB.bookDao())

        // populate firestores database
        //val mfirestoreBookData = FirestoreBookData;
        //mfirestoreBookData.addBooksDataToFirestoreDatabase();

    }

    fun getBooksInteractor(): BooksInteractor {
        return booksInteractor
    }

    fun hasInternetConnection(): Boolean {
        // TODO: Add Internet Check logic.

        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var isConnected: Boolean
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw      = cm.activeNetwork ?: return false
            val actNw = cm.getNetworkCapabilities(nw) ?: return false
            isConnected =  when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                //for check internet over Bluetooth
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            val nwInfo = cm.activeNetworkInfo ?: return false
            isConnected =  nwInfo.isConnected
        }

        return isConnected
    }
}