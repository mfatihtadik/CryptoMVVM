package com.mftadik.cryptoappmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mftadik.cryptoappmvvm.model.CryptoModel
import com.mftadik.cryptoappmvvm.service.CryptoAPI
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CryptoViewModel : ViewModel() {

    val BASE_URL = "https://raw.githubusercontent.com/"

    private val _liveData = MutableLiveData<List<CryptoModel>>()
    val liveData  : LiveData<List<CryptoModel>> = _liveData

    val response = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CryptoAPI::class.java)

    fun getCryptoData() {
        viewModelScope.launch {
            _liveData.value = response.getCrypto()
        }
    }
}