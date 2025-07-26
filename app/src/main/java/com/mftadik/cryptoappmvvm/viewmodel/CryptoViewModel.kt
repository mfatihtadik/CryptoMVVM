package com.mftadik.cryptoappmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mftadik.cryptoappmvvm.model.CryptoModel
import com.mftadik.cryptoappmvvm.repository.CryptoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(private val repository: CryptoRepository) : ViewModel() {

    private val _liveData = MutableLiveData<List<CryptoModel>>()
    val liveData  : LiveData<List<CryptoModel>> = _liveData

    fun getCryptoData() {
        viewModelScope.launch {
            try {
                _liveData.value = repository.getCryptos()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}