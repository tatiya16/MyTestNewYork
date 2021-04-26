package com.example.thenewyorktime.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thenewyorktime.databinding.ItemListHomeBinding
import com.example.thenewyorktime.presentation.home.model.HomeUIModel


class HomeAdapter(private val listenerHome: OnItemHomeSelectedListener) : ListAdapter<HomeUIModel, HomeAdapter.ViewHolder>(HomeAdapterDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bindItem(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemListHomeBinding) : RecyclerView.ViewHolder(binding.root){

        init {
            itemView.setOnClickListener {
                if(adapterPosition != RecyclerView.NO_POSITION){
                    listenerHome.onSelected(adapterPosition, getItem(adapterPosition))
                }
            }
        }

        fun bindItem(homeUIModel: HomeUIModel){
            binding.itemTitle.text = homeUIModel.title
            val multiMedia = homeUIModel.multiMedia.find { it.format == "superJumbo" }
            multiMedia?.let {
                Glide.with(binding.root)
                    .load(it.url)
                    .centerCrop()
                    .into(binding.itemImg)
            }

        }
    }

    class HomeAdapterDiffUtil : DiffUtil.ItemCallback<HomeUIModel>(){
        override fun areItemsTheSame(oldUI: HomeUIModel, newUI: HomeUIModel): Boolean {
            return oldUI.id == newUI.id
        }

        override fun areContentsTheSame(oldUI: HomeUIModel, newUI: HomeUIModel): Boolean {
            return oldUI == newUI
        }
    }
}