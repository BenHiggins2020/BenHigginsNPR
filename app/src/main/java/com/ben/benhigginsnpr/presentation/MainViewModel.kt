package com.ben.benhigginsnpr.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ben.benhigginsnpr.data.headline.data.NPRHeadlineItem
import com.ben.benhigginsnpr.domain.HeadlineDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

//@HiltViewModel
class MainViewModel constructor(
): ViewModel() {
    private val headlineDataManager: HeadlineDataManager

    private val liveData = MutableLiveData<NPRHeadlineItem>()
    init {
        headlineDataManager = HeadlineDataManager()
    }

    suspend fun getNPRHeadlines()  {
        println("BEN - getNPRHeadlines called")
        headlineDataManager.getHeadlines().collect {
            println("BEN COLLECTING LIST DATA ${it?.items?.get(0)}")
            liveData.postValue(it)
        }
    }

    fun getLiveDataList() = liveData as LiveData<NPRHeadlineItem>
}