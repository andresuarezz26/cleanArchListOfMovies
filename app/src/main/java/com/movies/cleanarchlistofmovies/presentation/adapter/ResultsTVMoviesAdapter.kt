package com.movies.cleanarchlistofmovies.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.movies.cleanarchlistofmovies.R
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies

class ResultsTVMoviesAdapter(private val listener: ((Int) -> Unit)?) : RecyclerView.Adapter<ResultsTVMoviesAdapter.ResultsTVMoviesVH>() {

    private var list = mutableListOf<ResultTVMovies>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsTVMoviesVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_results_tv_movies, parent, false)
        return ResultsTVMoviesVH(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ResultsTVMoviesVH, position: Int) {
        val element = list[position]
        holder.title.text = element.title ?: ""
        holder.overview.text = element.overview ?: ""
        holder.voteAverage.text = element.voteAverage.toString()
        Glide.with(holder.image).load(element.posterPath).into(holder.image)
        holder.container.setOnClickListener { listener?.invoke(element.id) }
    }

    fun addAll(list: List<ResultTVMovies>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class ResultsTVMoviesVH(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = itemView.findViewById(R.id.textResultsTitle)
        val overview: TextView = itemView.findViewById(R.id.textResultsOverview)
        val image: ImageView = itemView.findViewById(R.id.imageResultsPoster)
        val voteAverage: TextView = itemView.findViewById(R.id.textResultsVoteAverage)
        val container: View = itemView.findViewById(R.id.containerItemResultsTvMovies)
    }
}