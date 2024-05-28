package by.ssrlab.bible.ui.fragments

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
import by.ssrlab.bible.databinding.FragmentBiblesBinding
import by.ssrlab.bible.db.objects.BaseBibleData
import by.ssrlab.bible.db.objects.data.Bible
import by.ssrlab.bible.ui.BaseFragmentActions
import by.ssrlab.bible.utils.rv.BiblesAdapter
import by.ssrlab.bible.utils.vm.BiblesVM

class BiblesFragment: Fragment(), BaseFragmentActions {

    private lateinit var binding: FragmentBiblesBinding
    private val biblesVM: BiblesVM by viewModels()

    private lateinit var adapter: BiblesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_bibles, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewModel = biblesVM
            lifecycleOwner = viewLifecycleOwner

            initAdapter()
            setUpListObserver()
        }
    }

    private fun setUpListObserver() {
        biblesVM.listOfEntities.apply {
            observe(viewLifecycleOwner) {
                adapter.notifyItemInserted(this.value!!.size)
            }
        }
    }

    private fun initAdapter() {
        adapter = biblesVM.listOfEntities.value?.let {
            BiblesAdapter(it) { book ->
                moveNext(book, R.id.action_biblesFragment_to_pagesFragment)
            }
        }!!

        binding.apply {
            biblesRv.layoutManager = LinearLayoutManager(requireContext())
            biblesRv.adapter = adapter
        }
    }

    override fun onBackPressed() {
        (requireActivity() as MainActivity).finish()
    }

    override fun moveNext(bundle: BaseBibleData?, address: Int?) {
        var movingPackage = bundleOf()
        if (bundle != null && bundle is Bible)
            movingPackage = bundleOf("title" to bundle.name)

        if (address != null)
            findNavController().navigate(address, movingPackage)
    }
}