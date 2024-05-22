package by.ssrlab.bible.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.ssrlab.bible.MainActivity
import by.ssrlab.bible.R
import by.ssrlab.bible.databinding.FragmentListBinding
import by.ssrlab.bible.utils.ListAdapter
import by.ssrlab.bible.utils.vm.FragmentListVM

class ListFragment: Fragment() {

    private lateinit var binding: FragmentListBinding
    private val listVM: FragmentListVM by viewModels()

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

    private fun moveToAddress() {
        findNavController().navigate(R.id.list_rv)
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
            ListAdapter(it) {
                moveToAddress()
            }
        }!!

        binding.apply {
            listRv.layoutManager = LinearLayoutManager(requireContext())
            listRv.adapter = adapter
        }
    }
}