package by.ssrlab.bible.db.objects.book

import com.google.gson.annotations.SerializedName

data class BookData(

    @SerializedName("data")
    val data: Book
)
