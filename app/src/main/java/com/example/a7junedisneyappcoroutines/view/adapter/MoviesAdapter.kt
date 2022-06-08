package com.example.a7junedisneyappcoroutines.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a7junedisneyappcoroutines.R
import com.example.a7junedisneyappcoroutines.databinding.ItemLayoutBinding
import com.example.a7junedisneyappcoroutines.model.dataclasses.Data


class MoviesAdapter(private var context: Context, val moviesData: List<Data>) :
    RecyclerView.Adapter<MoviesAdapter.NewsViewHolder>() {

    private lateinit var binding: ItemLayoutBinding

    override fun getItemCount() = moviesData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemLayoutBinding>(inflater, R.layout.item_layout, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(moviesData[position])
    }

    inner class NewsViewHolder(var binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind( moviesData:Data) {
            binding.data = moviesData
            Glide.with(context)
                .load(moviesData.imageUrl)
                .into(binding.ivMovie)
            binding.textViewMovieName.text = moviesData.name
            binding.tvId.text = moviesData._id.toString()

        }
    }

}