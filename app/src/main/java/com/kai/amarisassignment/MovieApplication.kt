package com.kai.amarisassignment

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import dagger.hilt.android.HiltAndroidApp
import java.util.Locale
import java.util.TimeZone

@HiltAndroidApp
class MovieApplication: Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

        val config = Configuration()
        base.createConfigurationContext(config)
    }
}