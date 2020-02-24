package com.pmesa.chiperreddit

import android.app.Application
import com.facebook.stetho.Stetho
import retrofit2.Retrofit


class ChiperRedditApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this);
    }
}