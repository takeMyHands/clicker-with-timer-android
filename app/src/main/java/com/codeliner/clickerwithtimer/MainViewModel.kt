package com.codeliner.clickerwithtimer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codeliner.clickerwithtimer.domains.scores.Score
import com.codeliner.clickerwithtimer.domains.scores.ScoreDatabaseDao

class MainViewModel(
        app: Application,
        dataSourceDao: ScoreDatabaseDao
) : AndroidViewModel(app) {

    private val _scoreLatest = dataSourceDao.getScoreLatest()
    val scoreLatest: LiveData<Score?> get() = _scoreLatest

    val isExit: LiveData<Boolean> get() = _isExit
    fun exitComplete() {
        _isExit.value = false
    }

    // note. drawer layout
    private val _drawerIsOpened = MutableLiveData<Boolean>()
    val drawerIsOpened: LiveData<Boolean> get() = _drawerIsOpened
    fun onDrawerOpened() {
        _drawerIsOpened.value = true
    }

    fun onDrawerClosed() {
        _drawerIsOpened.value = false

    }

    init {
        _isExit.value = false
        _drawerIsOpened.value = false
    }

    companion object {
        private val _isExit = MutableLiveData<Boolean>()
        fun exit() {
            _isExit.value = true
        }
    }
}