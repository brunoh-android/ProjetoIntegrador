package br.bruno.projetointegrador.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    var listaDeFragmentos = mutableListOf<Fragment>()
    var listaDeTitulos = mutableListOf<String>()

    fun add(fragment: Fragment,titulo:String){
        listaDeFragmentos.add(fragment)
        listaDeTitulos.add(titulo)
    }

    override fun getCount(): Int {
        return listaDeFragmentos.size
    }

    override fun getItem(position: Int): Fragment {
        return listaDeFragmentos.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listaDeTitulos[position]
    }
}