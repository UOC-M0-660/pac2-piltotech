package edu.uoc.pac2.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * A book Model representing a piece of content.
 */

@Entity(tableName = "book_entity")
data class Book(
        @PrimaryKey
        val uid: Int = 0,
        val title: String? = null,
        val author: String? = null,
        val description: String? = null,
        val publicationDate: String? = null,
        val urlImage: String? = null

)