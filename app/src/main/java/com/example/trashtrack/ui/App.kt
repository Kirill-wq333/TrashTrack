package com.example.trashtrack.ui

import android.app.Application
import com.my.tracker.MyTracker
import dagger.hilt.android.HiltAndroidApp

private const val MYTRACKER_KEY = "23523622622626"

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        MyTracker.initTracker(MYTRACKER_KEY, this)
        MyTracker.getTrackerConfig().bufferingPeriod = 30
    }
}