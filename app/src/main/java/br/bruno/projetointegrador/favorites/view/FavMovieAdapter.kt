package br.bruno.projetointegrador.favorites.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import br.bruno.projetointegrador.R
import br.bruno.projetointegrador.databinding.ItemFavListBinding
import br.bruno.projetointegrador.favorites.data.FavMovie
import br.bruno.projetointegrador.utils.IMAGE_URL
import br.bruno.projetointegrador.utils.MyGlide

class FavMovieAdapter(
    private val context: Context,
    private val onBtnDeleteClicked: (favMovie: FavMovie) -> Unit,
    private val clickListener: (FavMovie) -> Unit
) : RecyclerView.Adapter<FavMovieAdapter.FavViewHolder>() {

    private val movies: MutableList<FavMovie> = emptyList<FavMovie>().toMutableList()

    fun addData(movie: List<FavMovie>) {
        movies.addAll(movie)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder =
        FavViewHolder(context,
            ItemFavListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ) {
            clickListener(movies[it])
        }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size


    inner class FavViewHolder(
        private val context: Context,
        private val binding: ItemFavListBinding,
        clickAtPosition: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        private val icon = itemView.findViewById<ImageView>(R.id.movieIcon_favList)
        private val title = itemView.findViewById<TextView>(R.id.movieTittle_favList)
        private val deleteBtn = itemView.findViewById<Button>(R.id.deleteBtn)


        init {
            itemView.findViewById<TextView>(R.id.movieTittle_favList).setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }

        fun bind(movie: FavMovie) {
            title.text = movie.title
            deleteBtn.setOnClickListener { openDeleteDialog(movie) }
            MyGlide().build(context, IMAGE_URL, movie.poster_path, icon, icon.width, icon.height)
        }

        private fun openDeleteDialog(movie: FavMovie) {
            AlertDialog.Builder(binding.root.context)
                .setTitle("Are you sure that you want to delete this task?")
                .setPositiveButton("Yes, I am sure") { _, _ ->
                    onBtnDeleteClicked.invoke(movie)
                }
                .setNegativeButton(
                    "No, cancel it"
                ) { dialog, _ -> dialog?.dismiss() }
                .show()
        }

    }
}
