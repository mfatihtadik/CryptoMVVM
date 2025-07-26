package com.mftadik.cryptoappmvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mftadik.cryptoappmvvm.R
import com.mftadik.cryptoappmvvm.model.CryptoModel

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
    fun updateList(newList: List<CryptoModel>) {
        myList = newList
        notifyDataSetChanged()
    }
}