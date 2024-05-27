package by.ssrlab.bible.utils.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.ssrlab.bible.client.ApiService
import by.ssrlab.bible.db.objects.data.Bible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BiblesVM: ViewModel(), KoinComponent {

    val listOfEntities = MutableLiveData<ArrayList<Bible>>(arrayListOf())
    val title = MutableLiveData<String>()

    private val listOfIds = arrayListOf("17c44f6c89de00db-01", "17c44f6c89de00db-02", "b52bc8b7af3bdc6f-03")
    private val api: ApiService by inject()

    private val job = Job()
    private val networkScope = CoroutineScope(Dispatchers.IO + job)

    private fun initListOfEntities() {
        networkScope.launch {
            try {
                for (i in listOfIds) {
                    val response = api.getBible(i).execute()
                    if (response.isSuccessful) {
                        val data = response.body()?.data

                        if (data != null)
                            withContext(Dispatchers.Main) {
                                val currentList = listOfEntities.value
                                currentList?.add(data)
                                listOfEntities.value = currentList!!
                            }
                    }
                }
            } catch (e: Exception) {
                Log.e("Retrofit exception", e.message.toString())
            }
        }
    }

    private fun initTitle() {
        title.value = "Біблія"
    }

    init {
        initListOfEntities()
        initTitle()
    }
}