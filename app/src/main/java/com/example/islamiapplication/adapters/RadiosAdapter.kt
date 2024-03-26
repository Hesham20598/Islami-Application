package com.example.islamiapplication.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.islamiapplication.databinding.ItemRadioBinding
import com.example.islamiapplication.model.RadiosItem

class RadiosAdapter() : RecyclerView.Adapter<RadiosAdapter.MyViewHolder>() {

    var radiosList: List<RadiosItem>? = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun changeRadiosList(radiosList: List<RadiosItem>?) {
        this.radiosList = radiosList
        notifyDataSetChanged()
    }

    fun changeRadio(position: Int, isPlayed: Boolean) {

        radiosList?.get(position)?.isPlayed = isPlayed
        notifyItemChanged(position)


    }

    class MyViewHolder(val binding: ItemRadioBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(radio: RadiosItem) {
            binding.tvApiValue.text = radio.name

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemRadioBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = radiosList?.size ?: 0

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val radio = radiosList!![position]
        holder.bind(radio)
        onPlayPauseClickListener?.let {
            holder.binding.icPlay.setOnClickListener {

                onPlayPauseClickListener?.onItemClick(position, radio)
            }
        }

    }

    var onPlayPauseClickListener: OnItemClickListener? = null
}

fun interface OnItemClickListener {
    fun onItemClick(position: Int, radio: RadiosItem)
}