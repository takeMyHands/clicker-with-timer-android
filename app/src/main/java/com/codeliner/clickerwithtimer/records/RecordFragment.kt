package com.codeliner.clickerwithtimer.records

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codeliner.clickerwithtimer.scores.ScoreAdapter
import com.codeliner.clickerwithtimer.databinding.FragmentRecordBinding
import com.codeliner.clickerwithtimer.domains.scores.Score
import com.codeliner.clickerwithtimer.domains.scores.ScoreDatabase
import com.codeliner.clickerwithtimer.scores.ScoreListener
import timber.log.Timber

class RecordFragment: Fragment()
    ,ScoreListener
{

    private lateinit var binding: FragmentRecordBinding
    private lateinit var viewModel: RecordViewModel
    private lateinit var viewModelFactory: RecordViewModelFactory
    private lateinit var scoreAdater: ScoreAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRecordBinding.inflate(inflater)
        binding.lifecycleOwner = this

        initViewModel()
        initObservers()

        return binding.root
    }

    private fun initViewModel() {
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
        scoreAdater = ScoreAdapter(this)
        binding.fragmentRecordScoreList.adapter = scoreAdater

        viewModel.scoreList.observe(viewLifecycleOwner, Observer { list ->
            scoreAdater.submitList(list)
        })
    }

    override fun onClick(score: Score) {
        Timber.i("onClick")
    }

    override fun onScore(score: Score) {
        Timber.i("onScore")
    }

    override fun onCreated(score: Score) {
        Timber.i("onCreated")
    }
}