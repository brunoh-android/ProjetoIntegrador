package br.bruno.projetointegrador.home.view.tabItens.maisVotados.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.bruno.projetointegrador.R
import br.bruno.projetointegrador.home.view.tabItens.maisVotados.vo.MaisVotadosMoviesVO
import br.bruno.projetointegrador.util.MyGlide
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.FieldPosition

class MaisVotadosMoviesAdapter(
    private val context: Context,
    private val movies: List<MaisVotadosMoviesVO>,
    private val clickListener: (MaisVotadosMoviesVO) -> Unit,
) :
    RecyclerView.Adapter<MaisVotadosMoivesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaisVotadosMoivesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.mais_votado_moives_item, parent, false)
        return MaisVotadosMoivesViewHolder(context, itemView) {
            clickListener(movies[it])
        }
    }

    override fun onBindViewHolder(holder: MaisVotadosMoivesViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)

    }

    override fun getItemCount(): Int = movies.size
}

class MaisVotadosMoivesViewHolder(
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

    fun bind(movie: MaisVotadosMoviesVO) {
        tittle.text = movie.original_title
        avarege.text = movie.vote_average.toString()
        overview.text = movie.overview

        MyGlide().build(context, movie.base_url_image, movie.poster_path, poster, 400, 400)
    }


}
