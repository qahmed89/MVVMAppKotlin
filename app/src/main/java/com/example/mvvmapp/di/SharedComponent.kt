package com.example.mvvmapp.di


import com.example.mvvmapp.BaseApplication
import com.example.mvvmapp.MainActivity
import com.example.mvvmapp.di.module.NetworkModule
import com.example.mvvmapp.di.module.SharedModule
import com.example.mvvmapp.ui.fragment.Home
import dagger.Component
import javax.inject.Singleton
@Singleton
@Component(modules = [SharedModule::class, NetworkModule::class])
interface SharedComponent {
    fun inject(application: BaseApplication)
    fun inject(application: MainActivity)
    fun inject(home: Home)

}
