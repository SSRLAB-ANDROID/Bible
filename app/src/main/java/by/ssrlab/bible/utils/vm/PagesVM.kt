package by.ssrlab.bible.utils.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PagesVM: ViewModel() {

    val time = MutableLiveData("")
    val title = MutableLiveData("")
}