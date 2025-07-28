package com.gsm.dogsworld

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.gsm.dogsworld.network.NetworkMonitor
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class DogsWorldApplication : Application() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(networkMonitor)
    }
}