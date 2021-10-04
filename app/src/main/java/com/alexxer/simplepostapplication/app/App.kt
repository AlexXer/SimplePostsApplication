package com.alexxer.simplepostapplication.app

import android.app.Application
import com.alexxer.simplepostapplication.di.component.AppComponent

class App: Application() {

    val appComponent: AppComponent by lazy { AppComponent(this) }

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)
    }
}