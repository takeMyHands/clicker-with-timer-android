package com.codeliner.clickerwithtimer.titles

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.codeliner.clickerwithtimer.MainViewModel
import com.codeliner.clickerwithtimer.R
import com.codeliner.clickerwithtimer.clicks.ClickViewModel
import com.codeliner.clickerwithtimer.databinding.FragmentTitleBinding

class TitleFragment: Fragment() {

    private val viewModel: TitleViewModel by lazy {
        ViewModelProvider(this).get(TitleViewModel::class.java)
    }

    private var backCallback: OnBackPressedCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backCallback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            MainViewModel.exit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        backCallback?.remove()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTitleBinding.inflate(inflater)
        binding.viewModel = viewModel
        // note. for view model lifecycle
        binding.lifecycleOwner = this

        binding.gameStart.setOnClickListener(
            Navigation.createNavigateOnClickListener(TitleFragmentDirections.actionTitleFragmentToClickFragment())
        )

        return binding.root
    }
}