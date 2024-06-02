package by.ssrlab.bible.client

import by.ssrlab.bible.db.objects.DataArrayWrapper
import by.ssrlab.bible.db.objects.DataWrapper
import by.ssrlab.bible.db.objects.data.Bible
import by.ssrlab.bible.db.objects.data.Book
import by.ssrlab.bible.db.objects.data.Chapter
import by.ssrlab.bible.db.objects.data.Verse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

private const val BIBLE_ID = "bibleId"
private const val BOOK_ID = "bookId"
private const val CHAPTER_ID = "chapterId"
private const val VERSE_ID = "verseId"

interface ApiService {

    //Getting Bible data to show in the list of available Bibles
    @GET("bibles/{$BIBLE_ID}")
    fun getBible(@Path(BIBLE_ID) bibleId: String): Call<DataWrapper<Bible>>

    //Getting all books of a Bible provided in the field, then getting a provided copy of a book id and sending it to the method
    //below to get chapters data
    @GET("bibles/{$BIBLE_ID}/books")
    fun getBooks(@Path(BIBLE_ID) bibleId: String): Call<DataArrayWrapper<Book>>

    //Getting an id and names of the chapters provided by the book of a Bible, then sending this id to get all verses of the chapter
    @GET("bibles/{$BIBLE_ID}/books/{$BOOK_ID}/chapters")
    fun getChapters(@Path(BIBLE_ID) bibleId: String, @Path(BOOK_ID) bookId: String): Call<DataArrayWrapper<Chapter>>

    //Getting an upper information of all the verses of chapter, like id and name, then sending id to the method below to get
    //content (text) of the verse
    @GET("bibles/{$BIBLE_ID}/chapters/{$CHAPTER_ID}/verses")
    fun getVerses(@Path(BIBLE_ID) bibleId: String, @Path(CHAPTER_ID) chapterId: String): Call<DataWrapper<Verse>>

    //Getting the text of the verse
    @GET("bibles/{$BIBLE_ID}/verses/{$VERSE_ID}")
    fun getVerse(@Path(BIBLE_ID) bibleId: String, @Path(VERSE_ID) verseId: String): Call<DataWrapper<Verse>>
}