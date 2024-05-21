package by.ssrlab.bible.db.objects.book

import com.google.gson.annotations.SerializedName

data class Book(

    @SerializedName("id")
    val id: String,

    @SerializedName("nameLocal")
    val name: String,

    @SerializedName("copyright")
    val author: String,
)