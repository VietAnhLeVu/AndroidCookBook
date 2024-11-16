package com.example.androidcookbook

import android.app.Application
import com.example.androidcookbook.data.AppContainer
import com.example.androidcookbook.data.AuthContainer
import com.example.androidcookbook.data.TheMealDBContainer

class CookbookApplication: Application() {
    lateinit var theMealDBContainer: AppContainer
    lateinit var authContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        theMealDBContainer = TheMealDBContainer()
        authContainer = AuthContainer()
    }
}