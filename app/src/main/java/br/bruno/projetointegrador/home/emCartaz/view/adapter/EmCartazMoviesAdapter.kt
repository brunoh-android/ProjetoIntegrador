package br.bruno.projetointegrador.home.emCartaz.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.bruno.projetointegrador.R
import br.bruno.projetointegrador.home.emCartaz.vo.EmCartazMoviesVO
import br.bruno.projetointegrador.utils.IMAGE_URL
import br.bruno.projetointegrador.utils.MyGlide

class EmCartazMoviesAdapter(
    private val context: Context,
    private val movies: List<EmCartazMoviesVO>,
    private val clickListener: (EmCartazMoviesVO) -> Unit,
) :
    RecyclerView.Adapter<EmCartazMoviesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmCartazMoviesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.em_cartaz_moives_item, parent, false)
        return EmCartazMoviesViewHolder(context, itemView){
            clickListener(movies[it])
        }
    }

    override fun onBindViewHolder(holder: EmCartazMoviesViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)

    }

    override fun getItemCount(): Int = movies.size
}

class EmCartazMoviesViewHolder(
    private val context: Context,
    itemView: View,
    clickAtPosition: (Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val tittle = itemView.findViewById<TextView>(R.id.title)
    private val avarege = itemView.findViewById<TextView>(R.id.avarege)
    private val overview = itemView.findViewById<TextView>(R.id.overview)
    private val poster = itemView.findViewById<ImageView>(R.id.poster_iv)

    init {
        itemView.setOnClickListener {
            clickAtPosition(adapterPosition)
        }
    }

    fun bind(movie: EmCartazMoviesVO) {
        tittle.text = movie.original_title
        avarege.text = movie.vote_average.toString()
        overview.text = movie.overview


        MyGlide().build(context, IMAGE_URL, movie.poster_path, poster, 400, 400)
    }


}
