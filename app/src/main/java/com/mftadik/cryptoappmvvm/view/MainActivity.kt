package com.mftadik.cryptoappmvvm.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mftadik.cryptoappmvvm.R
import com.mftadik.cryptoappmvvm.adapter.CryptoAdapter
import com.mftadik.cryptoappmvvm.model.CryptoModel
import com.mftadik.cryptoappmvvm.viewmodel.CryptoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var cryptoAdapter: CryptoAdapter
    private val viewModel : CryptoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.cryptoRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        cryptoAdapter = CryptoAdapter(emptyList())
        recyclerView.adapter = cryptoAdapter

        viewModel.getCryptoData()
        viewModel.liveData.observe(this, Observer {
            cryptoAdapter.updateList(it)
        })
    }
}

