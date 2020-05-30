package com.example.mvvmapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmapp.model.Balance
import com.example.mvvmapp.repository.BalanceRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val balanceRepo: BalanceRepo) : ViewModel() {
    private val disposable  =CompositeDisposable()
    private val balanceMutableLiveData = MutableLiveData<Balance>()
    private val isLoading = MutableLiveData<Boolean>()
    private val errorDisplay = MutableLiveData<String>()
fun isLoading():LiveData<Boolean>{
    return isLoading
}
    fun errorDisplay():LiveData<String>{
        return errorDisplay
    }
  fun getBalanceMutableLiveData(apikey:String) :MutableLiveData<Balance>{
        loadData(apikey)
        return balanceMutableLiveData
    }

    private fun loadData(apikey:String) {
        isLoading.value=true
        disposable.add(balanceRepo.getBalance(apikey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data->
             getBalanceMutableLiveData(apikey).value=data
            isLoading.value=false
        },{
            error->
            isLoading.value=false
            Log.e("Home",error.toString())
        }))
    }
}