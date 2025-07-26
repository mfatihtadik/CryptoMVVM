package com.mftadik.cryptoappmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mftadik.cryptoappmvvm.model.CryptoModel
import com.mftadik.cryptoappmvvm.repository.CryptoRepository
import kotlinx.coroutines.launch


class CryptoViewModel : ViewModel() {

    private val repository = CryptoRepository()

    private val _liveData = MutableLiveData<List<CryptoModel>>()
    val liveData  : LiveData<List<CryptoModel>> = _liveData

    fun getCryptoData() {
        viewModelScope.launch {
            _liveData.value = repository.getCryptos()
        }
    }
}