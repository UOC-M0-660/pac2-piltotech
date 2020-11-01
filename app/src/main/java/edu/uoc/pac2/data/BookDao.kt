package edu.uoc.pac2.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Book Dao (Data Access Object) for accessing Book Table functions.
 */

@Dao
interface BookDao {

    @Query("SELECT * FROM book_entity")
    fun getAllBooks(): List<Book>

    @Query("SELECT * FROM book_entity WHERE book_entity.uid = :id")
    fun getBookById(id: Int): Book?

    @Query("SELECT * FROM book_entity WHERE book_entity.title = :titleBook")
    fun getBookByTitle(titleBook: String): Book?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveBook(book: Book): Long
}