package com.example.commerceapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CommerceApplication:Application() {
    override fun onCreate() {
        super.onCreate()
    }
}