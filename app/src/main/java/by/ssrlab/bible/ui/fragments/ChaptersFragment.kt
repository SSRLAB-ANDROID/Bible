package by.ssrlab.bible.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.ssrlab.bible.R
import by.ssrlab.bible.databinding.FragmentChaptersBinding
import by.ssrlab.bible.db.objects.BaseBibleData
import by.ssrlab.bible.db.objects.data.Bible
import by.ssrlab.bible.db.objects.data.Book
import by.ssrlab.bible.ui.BaseFragmentActions
import by.ssrlab.bible.utils.rv.ChaptersAdapter
import by.ssrlab.bible.utils.vm.ChaptersVM

class ChaptersFragment: Fragment(), BaseFragmentActions {

    private lateinit var binding: FragmentChaptersBinding
    private lateinit var adapter: ChaptersAdapter

    private lateinit var bible: Bible
    private lateinit var book: Book
    private val chaptersVM: ChaptersVM by viewModels {
        ChaptersVM.Factory(bible, book)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_chapters, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            bible = ChaptersFragmentArgs.fromBundle(it).bible
            book = ChaptersFragmentArgs.fromBundle(it).book
        }

        binding.apply {
            baseFragment = this@ChaptersFragment
            viewModel = chaptersVM
            lifecycleOwner = viewLifecycleOwner

            initAdapter()
            setUpListObserver()
        }
    }

    override fun initAdapter() {
        adapter = chaptersVM.listOfEntities.value?.let {
            ChaptersAdapter(it)
        }!!

        binding.apply {
            chaptersRv.layoutManager = LinearLayoutManager(requireContext())
            chaptersRv.adapter = adapter
        }
    }

    override fun setUpListObserver() {
        chaptersVM.listOfEntities.apply {
            observe(viewLifecycleOwner) {
                adapter.notifyItemInserted(this.value!!.size)
            }
        }
    }

    override fun moveNext(bundle: List<BaseBibleData>?) {
        findNavController().navigate(R.id.action_chaptersFragment_to_pagesFragment)
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }
}