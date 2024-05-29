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
import by.ssrlab.bible.databinding.FragmentBooksBinding
import by.ssrlab.bible.db.objects.BaseBibleData
import by.ssrlab.bible.db.objects.data.Bible
import by.ssrlab.bible.ui.BaseFragmentActions
import by.ssrlab.bible.utils.rv.BooksAdapter
import by.ssrlab.bible.utils.vm.BooksVM

class BooksFragment: Fragment(), BaseFragmentActions {

    private lateinit var binding: FragmentBooksBinding
    private lateinit var adapter: BooksAdapter

    private lateinit var bible: Bible
    private val booksVM: BooksVM by viewModels {
        BooksVM.Factory(bible)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_books, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bible = arguments?.let {
            BooksFragmentArgs.fromBundle(it).bible
        } ?: Bible("-", "-", "-")

        binding.apply {
            baseFragment = this@BooksFragment
            viewModel = booksVM
            lifecycleOwner = viewLifecycleOwner

            initAdapter()
            setUpListObserver()
        }
    }

    override fun initAdapter() {
        adapter = booksVM.listOfEntities.value?.let {
            BooksAdapter(it) { book ->
                moveNext(book)
            }
        }!!

        binding.apply {
            booksRv.layoutManager = LinearLayoutManager(requireContext())
            booksRv.adapter = adapter
        }
    }

    override fun setUpListObserver() {
        booksVM.listOfEntities.apply {
            observe(viewLifecycleOwner) {
                adapter.notifyItemInserted(this.value!!.size)
            }
        }
    }

    override fun moveNext(bundle: BaseBibleData?) {
        super.moveNext(bundle)
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }
}