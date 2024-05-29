package by.ssrlab.bible.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.ssrlab.bible.R
import by.ssrlab.bible.databinding.FragmentChaptersBinding
import by.ssrlab.bible.db.objects.BaseBibleData
import by.ssrlab.bible.ui.BaseFragmentActions

class ChaptersFragment: Fragment(), BaseFragmentActions {

    private lateinit var binding: FragmentChaptersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_chapters, container, false)
        return binding.root
    }

    override fun initAdapter() {

    }

    override fun setUpListObserver() {

    }

    override fun moveNext(bundle: BaseBibleData?) {
        super.moveNext(bundle)
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }
}