package by.ssrlab.bible.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.ssrlab.bible.R
import by.ssrlab.bible.databinding.FragmentStartBinding
import by.ssrlab.bible.utils.vm.StartVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartFragment: Fragment() {

    private lateinit var binding: FragmentStartBinding
    private val startVM: StartVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        if (!startVM.moveNext.value!!)
            startVM.switchMoveNext()
        loadLogo()
    }

    override fun onStop() {
        super.onStop()

        startVM.switchMoveNext()
    }

    private fun loadLogo() {
        if (!startVM.isLoading.value!!) {
            binding.logo.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.alpha_in))
            navigateNext(2000)

            startVM.isLoading.value = true
        } else {
            navigateNext(1000)
        }
    }

    private fun navigateNext(delay: Long) {
        CoroutineScope(Dispatchers.Main).launch {
            delay(delay)
            if (startVM.moveNext.value!!)
                findNavController().navigate(R.id.action_startFragment_to_listFragment)
        }
    }
}