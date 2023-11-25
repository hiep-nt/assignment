package com.kai.amarisassignment.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kai.amarisassignment.R
import com.kai.amarisassignment.domain.model.ListMovieResponse.Movie
import com.kai.amarisassignment.extention.loadImage

class MovieAdapter : ListAdapter<Movie, MovieAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ivPoster: ImageView = itemView.findViewById(R.id.iv_poster)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvYear: TextView = itemView.findViewById(R.id.tv_year)

        fun bind(item: Movie) {
            ivPoster.loadImage(item.poster)
            tvName.text = item.title
            tvYear.text = item.year
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.imdbID == newItem.imdbID
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.title == newItem.title && oldItem.poster == newItem.poster
    }
}