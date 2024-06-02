package by.ssrlab.bible.utils.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.ssrlab.bible.db.objects.data.Bible
import by.ssrlab.bible.utils.vm.base.BaseVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BiblesVM: BaseVM<Bible>() {

    private val _listOfEntities = MutableLiveData<ArrayList<Bible>>(arrayListOf())
    override val listOfEntities: LiveData<ArrayList<Bible>>
        get() = _listOfEntities

    override var title = "Біблія"
    private val listOfIds = arrayListOf("17c44f6c89de00db-01", "17c44f6c89de00db-02", "b52bc8b7af3bdc6f-03")
    override val networkScope = CoroutineScope(Dispatchers.IO + Job())

    override fun downloadList() {
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
                                _listOfEntities.value = currentList!!
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
    }
}