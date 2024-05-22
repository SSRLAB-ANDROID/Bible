package by.ssrlab.bible.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.ssrlab.bible.databinding.FragmentPagesBinding

class PagesFragment: Fragment() {

    private lateinit var binding: FragmentPagesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPagesBinding.inflate(layoutInflater)
        return binding.root
    }
}