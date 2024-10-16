package com.ben.benhigginsnpr

import android.annotation.SuppressLint
import com.ben.benhigginsnpr.Util.NPRDataUtil
import com.ben.benhigginsnpr.data.NPRHeadlinesApi
import com.ben.benhigginsnpr.data.headline.classes.NPRHeadlineItem
import com.ben.benhigginsnpr.domain.HeadLineItem
import com.ben.benhigginsnpr.domain.HeadlineDataManager
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class HeadlineDataManagerTest {


    val mockApi:NPRHeadlinesApi = mock(NPRHeadlinesApi::class.java)

    val headlineDM:HeadlineDataManager = HeadlineDataManager(mockApi)


    @SuppressLint("CheckResult")
    @Test
    fun `test null api response`() = runTest {

        val successfulResponse = Response.success(NPRDataUtil.itemFilledLinks)

        `when`(mockApi.getHeadlines()).thenReturn(successfulResponse)

        val result = headlineDM.getHeadlinesList()

        assertEquals(
            listOf(
                HeadLineItem(
                    title = "Breaking Headline 1",
                    imageUrl = "https://www.npr.org/images/image3.jpg",
                    articleUrl = "https://www.npr.org/web-link1"
                ),
                HeadLineItem(
                    title = "Breaking Headline 2",
                    imageUrl = "https://www.npr.org/images/image3.jpg",
                    articleUrl = "https://www.npr.org/web-link1"
                ),
                HeadLineItem(
                    title = "Breaking Headline 3",
                    imageUrl = "https://www.npr.org/images/image3.jpg",
                    articleUrl = "https://www.npr.org/web-link1"
                )
            ),
            result
        )

    }

    @Test
    fun `test null api response empty`() = runTest {

        val successfulResponse = Response.success(NPRDataUtil.itemEmptyLinks)

        `when`(mockApi.getHeadlines()).thenReturn(successfulResponse)

        val result = headlineDM.getHeadlinesList()

        assertEquals(
            emptyList<HeadLineItem>(),
            result
        )
    }
    @Test
    fun `test null api response null`() = runTest {

        val errorResponse = Response.error<NPRHeadlineItem>(
            404,
            ResponseBody.create(null,"Failed to get data")
        )
        `when`(mockApi.getHeadlines()).thenReturn(errorResponse)

        val result = headlineDM.getHeadlinesList()

        assertEquals(
            emptyList<HeadLineItem>(),
            result
        )

    }

}