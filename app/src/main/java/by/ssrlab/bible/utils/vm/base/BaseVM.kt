package by.ssrlab.bible.utils.vm.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import by.ssrlab.bible.client.ApiService
import by.ssrlab.bible.db.objects.BaseBibleData
import kotlinx.coroutines.CoroutineScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseVM<T: BaseBibleData>: ViewModel(), KoinComponent {

    abstract val listOfEntities: LiveData<ArrayList<T>>
    abstract var title: String
    abstract val networkScope: CoroutineScope

    abstract fun downloadList()

    val api: ApiService by inject()
    fun logException(e: Exception) {
        Log.e("Retrofit exception", e.message.toString())
    }
}