package com.example.mvvmapp.repository

import com.example.mvvmapp.model.Balance
import com.example.mvvmapp.service.BalanceService
import io.reactivex.Single
import javax.inject.Inject

class BalanceRepo  @Inject constructor(private val  balanceService :BalanceService) {
    fun getBalance (apikey:String ): Single<Balance>{
        return balanceService.getBalance(apikey)
    }
}