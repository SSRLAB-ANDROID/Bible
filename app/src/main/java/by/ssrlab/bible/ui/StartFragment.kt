package by.ssrlab.bible.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.ssrlab.bible.R
import by.ssrlab.bible.databinding.FragmentStartBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartFragment: Fragment() {

    private lateinit var binding: FragmentStartBinding
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        loadLogo()
    }

    private fun loadLogo() {
        if (!isLoading) {
            binding.logo.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.alpha_in))

            CoroutineScope(Dispatchers.Main).launch {
                delay(300)
                findNavController().navigate(R.id.action_startFragment_to_listFragment)
            }

            isLoading = true
        }
    }
}