package by.ssrlab.bible.utils.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.ssrlab.bible.client.ApiService
import by.ssrlab.bible.db.objects.data.Bible
import by.ssrlab.bible.db.objects.data.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BooksVM(private val bible: Bible): ViewModel(), KoinComponent {

    class Factory(
        private val bible: Bible
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T: ViewModel> create(modelClass: Class<T>): T {
            return BooksVM(bible) as T
        }
    }

    private val _listOfEntities = MutableLiveData<ArrayList<Book>>(arrayListOf())
    val listOfEntities: LiveData<ArrayList<Book>>
        get() = _listOfEntities

    var title = ""

    private val api: ApiService by inject()
    private val networkScope = CoroutineScope(Dispatchers.IO + Job())

    private fun downloadList() {
        networkScope.launch {
            try {
                val response = api.getBooks(bible.id).execute()
                if (response.isSuccessful) {
                    val data = response.body()?.data

                    if (data != null) {
                        withContext(Dispatchers.Main) {
                            data.forEach {
                                val currentList = _listOfEntities.value
                                currentList?.add(it)
                                _listOfEntities.value = currentList!!
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("Retrofit exception", e.message.toString())
            }
        }
    }

    init {
        downloadList()
        title = bible.name
    }
}