package com.ben.benhigginsnpr.Util

import androidx.lifecycle.AtomicReference
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

fun <T> getLiveDataValue(liveData: LiveData<T>): T? {
    val latch = CountDownLatch(1)
    val data = AtomicReference<T>()
    liveData.observeForever { t ->
        data.set(t)
        latch.countDown()
        liveData.removeObserver { t }
    }
    latch.await(2, TimeUnit.SECONDS) // Adjust timeout as needed
    return data.get()
}
