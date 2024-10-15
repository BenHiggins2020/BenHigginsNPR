package com.ben.benhigginsnpr

import com.ben.benhigginsnpr.data.RetrofitInstance
import com.ben.benhigginsnpr.data.RetrofitInstance.nprService
import com.ben.benhigginsnpr.data.headline.classes.Attributes
import com.ben.benhigginsnpr.data.headline.classes.AttributesX
import com.ben.benhigginsnpr.data.headline.classes.Image
import com.ben.benhigginsnpr.data.headline.classes.Item
import com.ben.benhigginsnpr.data.headline.classes.Links
import com.ben.benhigginsnpr.data.headline.classes.NPRHeadlineItem
import com.ben.benhigginsnpr.domain.HeadlineDataManager
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class HeadlineDataManagerTest {

    val headlineDM:HeadlineDataManager = HeadlineDataManager()

    val mockApi = mock(RetrofitInstance::class.java)

    @Before
    fun setup(){

    }
    @Test
    fun `test api data mapped`() = runTest {

    }

    @Test
    fun `test null api response`() = runTest{
        `when`(mockApi.nprService.getHeadlines().body()).thenReturn(null)

        val result = headlineDM.getHeadlinesList()

        assertEquals(null,result)

    }

}