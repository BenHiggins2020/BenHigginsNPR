package com.ben.benhigginsnpr.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ben.benhigginsnpr.data.headline.classes.NPRHeadlineItem
import com.ben.benhigginsnpr.domain.HeadLineItem
import com.ben.benhigginsnpr.domain.HeadlineDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class MainViewModel  @Inject constructor(
    private val headlineDataManager: HeadlineDataManager
): ViewModel() {

    private val TAG = MainViewModel::class.java.simpleName
    private val liveData = MutableLiveData<List<HeadLineItem>>()

    suspend fun fetchHeadlines() {
        liveData.postValue(headlineDataManager.getHeadlinesList())
    }
    fun getLiveDataList() = liveData as LiveData<List<HeadLineItem>>
}