package by.ssrlab.bible.ui

import by.ssrlab.bible.db.objects.BaseBibleData

interface BaseFragmentActions {

    fun onBackPressed()

    fun moveNext(bundle: BaseBibleData? = null) {
        if (bundle == null) return
    }

    fun setUpListObserver() {
        return
    }

    fun initAdapter() {
        return
    }
}