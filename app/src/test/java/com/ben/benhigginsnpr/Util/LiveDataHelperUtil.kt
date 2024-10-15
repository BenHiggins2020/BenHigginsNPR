package com.ben.benhigginsnpr.Util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

        /*fun <T> LiveData<T>.getOrAwaitValue(
            time:Long = 2,
            timeUnit:TimeUnit = TimeUnit.SECONDS
        ):T{
            var data:T? = null
            val latch = CountDownLatch(1)
            val observer = object:Observer<T> {

                override fun onChanged(value: T) {
                    data = value
                    latch.countDown()
                    this@getOrAwaitValue.removeObserver(this)

                }
            }

            this.observeForever(observer)

            if(!latch.await(time,timeUnit)){
                throw TimeoutException("LiveData did not return in time")
            }
            return data as T
        }*/


suspend fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {
   return withContext(Dispatchers.Main){


    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }

    this@getOrAwaitValue.observeForever(observer)

    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
        throw TimeoutException("LiveData value was never set.")
    }

    @Suppress("UNCHECKED_CAST")
    return@withContext data as T
    }
}