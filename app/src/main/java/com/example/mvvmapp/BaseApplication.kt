package com.example.mvvmapp

import android.app.Application
import com.example.mvvmapp.di.DaggerSharedComponent
import com.example.mvvmapp.di.SharedComponent
import com.example.mvvmapp.di.module.SharedModule


class BaseApplication :Application(){
 private   lateinit var component: SharedComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerSharedComponent.builder().sharedModule(
            SharedModule(
                this
            )
        ).build()


    }

    fun getSharedComponent(): SharedComponent {
        return component
    }
}
