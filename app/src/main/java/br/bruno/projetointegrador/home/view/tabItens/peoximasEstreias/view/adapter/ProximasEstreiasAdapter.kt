package br.bruno.projetointegrador.home.view.tabItens.peoximasEstreias.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.bruno.projetointegrador.R
import br.bruno.projetointegrador.home.view.tabItens.peoximasEstreias.vo.ProximasEstreiasMoviesVO
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ProximasEstreiasAdapter(
    private val context: Context,
    private val movies: List<ProximasEstreiasMoviesVO>,
    private val clickListener: (ProximasEstreiasMoviesVO) -> Unit,
) :
    RecyclerView.Adapter<ProximasEstreiasMoviesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProximasEstreiasMoviesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.proximas_estreias_moives_item, parent, false)
        return ProximasEstreiasMoviesViewHolder(context,itemView){
            clickListener(movies[it])
        }
    }

    override fun onBindViewHolder(holder: ProximasEstreiasMoviesViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)

    }

    override fun getItemCount(): Int = movies.size
}

class ProximasEstreiasMoviesViewHolder(private val context: Context, itemView: View,clickAtPosition: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {

    private val tittle = itemView.findViewById<TextView>(R.id.title)
    private val avarege = itemView.findViewById<TextView>(R.id.avarege)
    private val overview = itemView.findViewById<TextView>(R.id.overview)
    private val poster = itemView.findViewById<ImageView>(R.id.poster_iv)

    init {
        itemView.setOnClickListener{
            clickAtPosition(adapterPosition)
        }
    }

    fun bind(movie: ProximasEstreiasMoviesVO, ) {
        tittle.text = movie.title
        avarege.text = movie.vote_average.toString()
        overview.text = movie.overview

        Glide.with(context).asDrawable().load(movie.base_url_image + movie.poster_path)
            .apply(RequestOptions().override(400, 400).centerInside().placeholder(R.drawable.placehoder)).into(poster)


    }


}
