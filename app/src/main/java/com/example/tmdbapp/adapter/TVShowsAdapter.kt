package com.example.tmdbapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbapp.PopularShowQuery
import com.example.tmdbapp.databinding.ItemTvShowBinding

class TVShowsAdapter: ListAdapter<PopularShowQuery.Show, TVShowsAdapter.TVShowHolder>(ITEM_DIF) {

    companion object{
        val ITEM_DIF = object : DiffUtil.ItemCallback<PopularShowQuery.Show>(){
            override fun areItemsTheSame(
                oldItem: PopularShowQuery.Show,
                newItem: PopularShowQuery.Show
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: PopularShowQuery.Show,
                newItem: PopularShowQuery.Show
            ): Boolean {
                return oldItem == newItem
            }
        }
    }


    inner class TVShowHolder(private val binding: ItemTvShowBinding): RecyclerView.ViewHolder(binding.root){
        fun bind() {
            val show = getItem(adapterPosition)
            with(binding){
                Glide.with(root).load("https://image.tmdb.org/t/p/w500${show.poster_path}").into(ivMovie)
                tvName.text = show.name
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowHolder {
        return TVShowHolder(ItemTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TVShowHolder, position: Int) {
        holder.bind()
    }
}