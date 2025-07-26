package com.mftadik.cryptoappmvvm.module

import com.mftadik.cryptoappmvvm.repository.CryptoRepository
import com.mftadik.cryptoappmvvm.service.CryptoAPI
import com.mftadik.cryptoappmvvm.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit() : CryptoAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(service : CryptoAPI) : CryptoRepository {
        return CryptoRepository(service)
    }
}