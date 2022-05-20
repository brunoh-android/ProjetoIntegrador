package br.bruno.projetointegrador.home.view.tabItens.popular.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.bruno.projetointegrador.R
import br.bruno.projetointegrador.home.view.tabItens.popular.vo.PopularMoviesVO
import com.squareup.picasso.Picasso

class MoviesAdapter(
    private val movies: List<PopularMoviesVO>,
) :
    RecyclerView.Adapter<MoivesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoivesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.popular_moives_item, parent, false)
        return MoivesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoivesViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)

    }

    override fun getItemCount(): Int = movies.size
}

class MoivesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tittle = itemView.findViewById<TextView>(R.id.title)
    private val avarege = itemView.findViewById<TextView>(R.id.avarege)
    private val overview = itemView.findViewById<TextView>(R.id.overview)
    private val poster = itemView.findViewById<ImageView>(R.id.poster_iv)

    fun bind(movie: PopularMoviesVO, ) {
        tittle.text = movie.original_title
        avarege.text = movie.vote_average.toString()
        overview.text = movie.overview
        Picasso.with(itemView.context).load("${movie.base_url_image}{movie.poster_size.first()}${movie.poster_path}").into(poster)
    }


}
