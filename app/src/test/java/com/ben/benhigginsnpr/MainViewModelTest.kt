package com.ben.benhigginsnpr

import androidx.compose.runtime.produceState
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.ben.benhigginsnpr.Util.getOrAwaitValue
import com.ben.benhigginsnpr.domain.HeadLineItem
import com.ben.benhigginsnpr.domain.HeadlineDataManager
import com.ben.benhigginsnpr.presentation.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    val mockHeadlineDataManager:HeadlineDataManager = mock(HeadlineDataManager::class.java)

    lateinit var mainVM:MainViewModel

    val testDispatcher = StandardTestDispatcher()

    val list = listOf(
        HeadLineItem("title","img","article")
    )
    @Before
    fun setup(){
        mainVM = MainViewModel(mockHeadlineDataManager)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun finish(){
        Dispatchers.resetMain()
    }

    @Test
    fun test(){
        assertEquals(mainVM.getLiveDataList().value,null)
    }

    @Test
    fun `test liveData is updated after fetch call`() = runTest{
        `when`(mockHeadlineDataManager.getHeadlinesList()).thenReturn(list)

        assertEquals(list,mockHeadlineDataManager.getHeadlinesList())

        mainVM = MainViewModel(mockHeadlineDataManager)

        launch(this@MainViewModelTest.testDispatcher) {
            mainVM.fetchHeadlines()
        }

        advanceUntilIdle()
        assertEquals(
            list,
            mainVM.getLiveDataList().value
        )


    }

    @Test
    fun `test liveData with runBlocking`() = runBlocking(testDispatcher){
        `when`(mockHeadlineDataManager.getHeadlinesList()).thenReturn(list)

        assertEquals(list,mockHeadlineDataManager.getHeadlinesList())

        mainVM = MainViewModel(mockHeadlineDataManager)

        mainVM.fetchHeadlines()

        mainVM.getLiveDataList().observe(mock(LifecycleOwner::class.java)){
            println("observe: $it")
        }

        assertEquals(
            list,
            mainVM.getLiveDataList().value
        )
    }

}