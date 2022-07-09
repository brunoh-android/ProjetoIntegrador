package br.bruno.projetointegrador.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import br.bruno.projetointegrador.R
import br.bruno.projetointegrador.home.tabItens.ViewPagerAdapter
import br.bruno.projetointegrador.home.tabItens.emCartaz.view.EmCartazFragment
import br.bruno.projetointegrador.home.tabItens.maisVotados.view.MaisVotadoFragment
import br.bruno.projetointegrador.home.tabItens.popular.view.PopularesFragment
import br.bruno.projetointegrador.home.tabItens.proximasEstreias.view.ProximasEstreiasFragment
import com.google.android.material.tabs.TabLayout


class HomeFragment : Fragment(R.layout.home_fragments) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        setUpTabs()
    }

    private fun setUpTabs() {
        val supportFragmentManager = childFragmentManager

        val viewPager = requireView().findViewById<ViewPager>(R.id.viewPager)
        val tabLayout = requireView().findViewById<TabLayout>(R.id.tabLayout)

        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.add(PopularesFragment(), "Populares")
        adapter.add(MaisVotadoFragment(), "Mais Votados")
        adapter.add(EmCartazFragment(), "Em Cartaz")
        adapter.add(ProximasEstreiasFragment(), "Estreias")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }


}


