package com.example.thenewyorktime.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thenewyorktime.databinding.ItemSearchBookBinding
import com.example.thenewyorktime.presentation.search.model.SearchBookUIModel

class SearchAdapter(private val listener: OnItemSearchSelectedListener): ListAdapter<SearchBookUIModel, SearchAdapter.ViewHolder>(SearchBookDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {
       val binding = ItemSearchBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemSearchBookBinding) : RecyclerView.ViewHolder(binding.root){

        init {
            itemView.setOnClickListener {
                if(adapterPosition != RecyclerView.NO_POSITION){
                    listener.onSelected(adapterPosition, getItem(adapterPosition))
                }
            }
        }
        fun bindItem(searchBookUIModel: SearchBookUIModel){
            binding.itemTvTitle.text = searchBookUIModel.title
            binding.itemTvAbstract.text = searchBookUIModel.abstract
            binding.itemTvByline.text = searchBookUIModel.byline
            val multiMedia = searchBookUIModel.multiMedia.find { it.format == "Normal" }
            multiMedia?.let {
                Glide.with(binding.root)
                    .load(it.url)
                    .centerCrop()
                    .into(binding.itemImg)
            }

        }
    }

    class SearchBookDiffUtil: DiffUtil.ItemCallback<SearchBookUIModel>() {
        override fun areItemsTheSame(
            oldItem: SearchBookUIModel,
            newItem: SearchBookUIModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SearchBookUIModel,
            newItem: SearchBookUIModel
        ): Boolean {
            return oldItem == newItem
        }

    }

}


