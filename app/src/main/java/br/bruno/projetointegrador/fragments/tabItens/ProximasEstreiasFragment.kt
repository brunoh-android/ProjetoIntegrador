package br.bruno.projetointegrador.fragments.tabItens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.bruno.projetointegrador.R


class ProximasEstreiasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        return inflater.inflate(R.layout.fragments_proximas_estreias, container, false)
    }

}