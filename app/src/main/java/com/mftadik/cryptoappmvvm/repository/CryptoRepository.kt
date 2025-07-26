package com.mftadik.cryptoappmvvm.repository

import com.mftadik.cryptoappmvvm.model.CryptoModel
import com.mftadik.cryptoappmvvm.service.CryptoAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CryptoRepository {
    suspend fun getCryptos() : List<CryptoModel> {
        val BASE_URL = "https://raw.githubusercontent.com/"
        val response = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoAPI::class.java)

        return response.getCrypto()
    }
}