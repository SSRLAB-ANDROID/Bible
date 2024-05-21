package by.ssrlab.bible.client

import by.ssrlab.bible.db.objects.book.Book
import by.ssrlab.bible.db.objects.book.BookData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("bibles/{bibleId}")
    fun getBook(@Path("bibleId") bibleId: String): Call<BookData>
}