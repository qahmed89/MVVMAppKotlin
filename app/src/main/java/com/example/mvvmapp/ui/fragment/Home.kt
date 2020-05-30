package com.example.mvvmapp.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmapp.BaseApplication
import com.example.mvvmapp.DataBinderMapperImpl
import com.example.mvvmapp.R
import com.example.mvvmapp.databinding.FragmentHomeBinding
import com.example.mvvmapp.viewmodel.HomeViewModel
import com.example.mvvmapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class Home : Fragment() {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var homeViewModel: HomeViewModel

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity?.application as BaseApplication).getSharedComponent().inject(this)
        fragmentHomeBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val apikey = sharedPreferences.getString("APIKEY", "00000")
        (activity?.application as BaseApplication).getSharedComponent().inject(this)

        homeViewModel = ViewModelProvider(this, viewModelFactory!!).get(HomeViewModel::class.java)


        if (apikey != null) {
            homeViewModel.isLoading().observe(viewLifecycleOwner, Observer { isloading ->
                if (isloading) {
                    fragmentHomeBinding.addressLookingUp.show()
                } else {
                    fragmentHomeBinding.addressLookingUp.hide()
                }
            })
            homeViewModel.errorDisplay().observe(viewLifecycleOwner, Observer { error ->
                Log.e("Home", error.toString())
            })
                homeViewModel.getBalanceMutableLiveData(apikey)
                    .observe(viewLifecycleOwner, Observer { data ->

                        fragmentHomeBinding.balance = data

                    })

            }}


    }