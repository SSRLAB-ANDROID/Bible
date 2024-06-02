package by.ssrlab.bible.utils.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.ssrlab.bible.db.objects.data.Bible
import by.ssrlab.bible.db.objects.data.Book
import by.ssrlab.bible.utils.vm.base.BaseVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BooksVM(private val bible: Bible): BaseVM<Book>() {

    class Factory(
        private val bible: Bible
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T: ViewModel> create(modelClass: Class<T>): T {
            return BooksVM(bible) as T
        }
    }

    private val _listOfEntities = MutableLiveData<ArrayList<Book>>(arrayListOf())
    override val listOfEntities: LiveData<ArrayList<Book>>
        get() = _listOfEntities

    override var title = ""
    override val networkScope = CoroutineScope(Dispatchers.IO + Job())

    override fun downloadList() {
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
                logException(e)
            }
        }
    }

    init {
        downloadList()
        title = bible.name
    }
}