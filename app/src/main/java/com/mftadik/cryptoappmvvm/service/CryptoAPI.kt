package com.mftadik.cryptoappmvvm.service

import com.mftadik.cryptoappmvvm.model.CryptoModel
import retrofit2.http.GET

interface CryptoAPI {
    @GET("/atilsamancioglu/IA32-CryptoComposeData/refs/heads/main/cryptolist.json")
    suspend fun getCrypto(): List<CryptoModel>
}