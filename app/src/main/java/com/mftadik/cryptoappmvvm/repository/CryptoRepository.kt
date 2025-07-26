package com.mftadik.cryptoappmvvm.repository

import com.mftadik.cryptoappmvvm.model.CryptoModel
import com.mftadik.cryptoappmvvm.service.CryptoAPI
import javax.inject.Inject

class CryptoRepository @Inject constructor(private val api: CryptoAPI) {
    suspend fun getCryptos() : List<CryptoModel>{
        return api.getCrypto()
    }
}