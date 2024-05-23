package by.ssrlab.bible.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.ssrlab.bible.MainActivity
import by.ssrlab.bible.R
import by.ssrlab.bible.databinding.FragmentListBinding
import by.ssrlab.bible.db.objects.book.Book
import by.ssrlab.bible.utils.rv.ListAdapter
import by.ssrlab.bible.utils.vm.ListVM

class ListFragment: Fragment() {

    private lateinit var binding: FragmentListBinding
    private val listVM: ListVM by viewModels()

    private lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewModel = listVM
            mainActivity = requireActivity() as MainActivity
            lifecycleOwner = viewLifecycleOwner

            initAdapter()
            setUpListObserver()
        }
    }

    private fun moveToAddress(book: Book) {
        val bundle = bundleOf("title" to book.name)
        findNavController().navigate(R.id.action_listFragment_to_pagesFragment, bundle)
    }

    private fun setUpListObserver() {
        listVM.listOfEntities.apply {
            observe(viewLifecycleOwner) {
                adapter.notifyItemInserted(this.value!!.size)
            }
        }
    }

    private fun initAdapter() {
        adapter = listVM.listOfEntities.value?.let {
            ListAdapter(it) { book ->
                moveToAddress(book)
            }
        }!!

        binding.apply {
            listRv.layoutManager = LinearLayoutManager(requireContext())
            listRv.adapter = adapter
        }
    }
}