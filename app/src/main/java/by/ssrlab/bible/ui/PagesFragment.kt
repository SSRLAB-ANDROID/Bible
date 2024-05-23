package by.ssrlab.bible.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.ssrlab.bible.R
import by.ssrlab.bible.databinding.FragmentPagesBinding
import by.ssrlab.bible.utils.TimeReceiver
import by.ssrlab.bible.utils.vm.PagesVM

class PagesFragment: Fragment() {

    private lateinit var binding: FragmentPagesBinding
    private lateinit var timeReceiver: TimeReceiver
    private val pagesVM: PagesVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        timeReceiver = TimeReceiver(pagesVM).apply {
            register(requireContext())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_pages, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pagesVM.title.value = arguments?.getString("title") ?: ""

        binding.apply {
            viewModel = pagesVM
            lifecycleOwner = viewLifecycleOwner

            listToolbarBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        timeReceiver.unregister(requireContext())
    }
}