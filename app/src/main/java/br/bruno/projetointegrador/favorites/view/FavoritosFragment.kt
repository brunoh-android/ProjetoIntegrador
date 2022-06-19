package br.bruno.projetointegrador.favorites.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import br.bruno.projetointegrador.favorites.data.ListaDeFavoritos
import br.bruno.projetointegrador.R
import br.bruno.projetointegrador.movieDetails.view.MovieDetailsFragmentArgs


class FavoritosFragment : Fragment(R.layout.favoritos_fragments) {

    private val args: FavoritosFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var listaDeFavoritos : MutableList<ListaDeFavoritos> = mutableListOf(
            ListaDeFavoritos(args.tittle,R.drawable.ic_person)
        )

        val adapter = FavoritosRvAdpter(listaDeFavoritos)
        view.findViewById<RecyclerView>(R.id.favoritos_Rv).adapter = adapter


    }
}