package br.bruno.projetointegrador.home.popular.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.bruno.projetointegrador.R
import br.bruno.projetointegrador.home.popular.vo.PopularMoviesVO
import br.bruno.projetointegrador.utils.IMAGE_URL
import br.bruno.projetointegrador.utils.MyGlide

class MoviesAdapter(
    private val clickListener: (PopularMoviesVO) -> Unit,

    ) :
    RecyclerView.Adapter<MoviesViewHolder>() {

    private val movies: MutableList<PopularMoviesVO> = emptyList<PopularMoviesVO>().toMutableList()

    fun addData(addMovies: List<PopularMoviesVO>) {
        movies.addAll(addMovies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.popular_moives_item, parent, false)

        return MoviesViewHolder(itemView) {
            clickListener(movies[it])
        }
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size

}

class MoviesViewHolder(
    itemView: View,
    clickAtPosition: (Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val tittle = itemView.findViewById<TextView>(R.id.title)
    private val average = itemView.findViewById<TextView>(R.id.avarege)
    private val overview = itemView.findViewById<TextView>(R.id.overview)
    private val poster = itemView.findViewById<ImageView>(R.id.poster_iv)

    init {
        itemView.setOnClickListener {
            clickAtPosition(adapterPosition)
        }
    }


    fun bind(movie: PopularMoviesVO) {
        tittle.text = movie.title
        average.text = movie.vote_average.toString()
        overview.text = movie.overview

        MyGlide().build(itemView.context, IMAGE_URL, movie.poster_path, poster, 400, 400)
    }


}
