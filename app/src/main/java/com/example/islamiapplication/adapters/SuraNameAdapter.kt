package com.example.islamiapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.islamiapplication.databinding.ItemSuraNameBinding
import com.example.islamiapplication.model.SuraNameIndex

class SuraNameAdapter(val suraItems: List<SuraNameIndex>?) : Adapter<SuraNameAdapter.SuraNameIndexViewHolder>() {
    class SuraNameIndexViewHolder(val suraNameBinding: ItemSuraNameBinding) :
        ViewHolder(suraNameBinding.root) {
        fun bind(item: SuraNameIndex) {
            suraNameBinding.tvSuraNameItem.text = item.name
            suraNameBinding.tvSuraIndexItem.text = "${item.index}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuraNameIndexViewHolder {
        val binding = ItemSuraNameBinding.inflate(LayoutInflater.from(parent.context))
        return SuraNameIndexViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return suraItems?.size ?: 0
    }

    override fun onBindViewHolder(holder: SuraNameIndexViewHolder, position: Int) {
        val item = suraItems?.get(position)?:return
        holder.bind(item)
    }
}