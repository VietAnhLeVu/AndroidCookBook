package com.example.androidcookbook

import android.app.Application
import com.example.androidcookbook.data.containers.AppContainer
import com.example.androidcookbook.data.containers.AuthContainer
import com.example.androidcookbook.data.containers.TheMealDBContainer

class CookbookApplication: Application() {
    lateinit var theMealDBContainer: AppContainer
    lateinit var authContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        theMealDBContainer = TheMealDBContainer()
        authContainer = AuthContainer()
    }
}