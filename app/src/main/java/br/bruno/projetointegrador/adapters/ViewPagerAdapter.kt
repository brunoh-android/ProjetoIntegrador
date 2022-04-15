package br.bruno.projetointegrador.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(supportFragmentManager: FragmentManager) : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var listaDeFragmentos = ArrayList<Fragment>()
    var listaDeTitulos = ArrayList<String>()

    fun add(fragment: Fragment,titulo:String){
        listaDeFragmentos.add(fragment)
        listaDeTitulos.add(titulo)
    }

    override fun getCount(): Int {
        return listaDeFragmentos.size
    }

    override fun getItem(position: Int): Fragment {
        return listaDeFragmentos[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listaDeTitulos[position]
    }
}