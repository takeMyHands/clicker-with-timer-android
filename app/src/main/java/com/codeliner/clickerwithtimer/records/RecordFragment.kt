package com.codeliner.clickerwithtimer.records

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codeliner.clickerwithtimer.databinding.FragmentRecordBinding
import com.codeliner.clickerwithtimer.domains.scores.ScoreDatabase
import timber.log.Timber

class RecordFragment: Fragment() {

    private lateinit var viewModel: RecordViewModel
    private lateinit var viewModelFactory: RecordViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentRecordBinding.inflate(inflater)
        binding.lifecycleOwner = this

        initViewModel(binding)
        initObservers()

        return binding.root
    }

    private fun initViewModel(binding: FragmentRecordBinding) {
        val application = requireNotNull(activity).application
        val dataSourceDao = ScoreDatabase.getInstance(requireContext().applicationContext).scoreDatabaseDao
        viewModelFactory = RecordViewModelFactory(
                application,
                dataSourceDao
        )
        viewModel = ViewModelProvider(this, viewModelFactory).get(RecordViewModel::class.java)
        binding.viewModel = viewModel
    }

    private fun initObservers() {
        viewModel.scoreList.observe(viewLifecycleOwner, Observer { list ->
            Timber.i("scoreListSize: ${list.size}}")
        })
    }
}