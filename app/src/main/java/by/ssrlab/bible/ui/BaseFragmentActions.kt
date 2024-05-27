package by.ssrlab.bible.ui

import by.ssrlab.bible.db.objects.BaseBibleData

interface BaseFragmentActions {

    fun onBackPressed()

    fun moveNext(bundle: BaseBibleData? = null, address: Int? = null) {
        if (address == null) return
    }
}