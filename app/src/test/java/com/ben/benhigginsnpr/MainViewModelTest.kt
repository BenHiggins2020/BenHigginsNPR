package com.ben.benhigginsnpr

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ben.benhigginsnpr.Util.getLiveDataValue
import com.ben.benhigginsnpr.domain.HeadLineItem
import com.ben.benhigginsnpr.domain.HeadlineDataManager
import com.ben.benhigginsnpr.presentation.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
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

    @get:Rule val instantTestRule = InstantTaskExecutorRule()

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
    fun `test liveData is updated after fetch call`() {

        runTest {
            `when`(mockHeadlineDataManager.getHeadlinesList()).thenReturn(list)
             mainVM.fetchHeadlines()
        }

        val value = getLiveDataValue(mainVM.getLiveDataList())
        assertEquals(list, value)
    }

    @Test
    fun `test liveData gets null value`() {

        runTest {
            `when`(mockHeadlineDataManager.getHeadlinesList()).thenReturn(emptyList())
            mainVM.fetchHeadlines()
        }

        val value = getLiveDataValue(mainVM.getLiveDataList())

        println("liveData: ${mainVM.getLiveDataList().value}")
        assertEquals(emptyList<HeadLineItem>(), value)

    }

}