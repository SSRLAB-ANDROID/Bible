package by.ssrlab.bible.utils.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StartVM: ViewModel() {

    val isLoading = MutableLiveData(false)
    val moveNext = MutableLiveData(true)

    fun switchMoveNext() {
        moveNext.value = !moveNext.value!!
    }
}