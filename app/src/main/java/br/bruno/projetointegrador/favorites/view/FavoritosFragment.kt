package br.bruno.projetointegrador.favorites.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.bruno.projetointegrador.favorites.model.ListaDeFavoritos
import br.bruno.projetointegrador.R
import br.bruno.projetointegrador.favorites.view.FavoritosRvAdpter


class FavoritosFragment : Fragment(R.layout.favoritos_fragments) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var listaDeFavoritos : MutableList<ListaDeFavoritos> = mutableListOf(
            ListaDeFavoritos("O dia depois de amanha",R.drawable.ic_person)
        )

        val adapter = FavoritosRvAdpter(listaDeFavoritos)
        view.findViewById<RecyclerView>(R.id.favoritos_Rv).adapter = adapter


    }
}