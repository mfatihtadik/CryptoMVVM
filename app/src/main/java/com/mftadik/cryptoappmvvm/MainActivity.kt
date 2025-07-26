package com.mftadik.cryptoappmvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {

    val BASE_URL = "https://raw.githubusercontent.com/"
    private lateinit var cryptoAdapter: CryptoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var myList: List<CryptoModel>
        val response = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoApi::class.java)

        lifecycleScope.launch {
            myList =response.getCrypto()

            val recyclerView = findViewById<RecyclerView>(R.id.cryptoRecyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            cryptoAdapter = CryptoAdapter(myList)
            recyclerView.adapter = cryptoAdapter
        }
    }

    interface CryptoApi {
        @GET("/atilsamancioglu/IA32-CryptoComposeData/refs/heads/main/cryptolist.json")
        suspend fun getCrypto(): List<CryptoModel>
    }

    data class CryptoModel(
        val currency : String,
        val price : String
    )

    class CryptoAdapter(var myList: List<CryptoModel>) : RecyclerView.Adapter<CryptoAdapter.ArtViewHolder>(){

        class ArtViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row,parent,false)
            return ArtViewHolder(view)
        }
        override fun getItemCount(): Int {
            return myList.size
        }
        override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
            val currencyText = holder.itemView.findViewById<TextView>(R.id.currencyTextView)
            val priceText = holder.itemView.findViewById<TextView>(R.id.priceTextview)

            currencyText.text = myList[position].currency
            priceText.text = myList[position].price
        }
    }
}

