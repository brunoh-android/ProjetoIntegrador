package br.bruno.projetointegrador.home.view

import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import br.bruno.projetointegrador.R
import br.bruno.projetointegrador.home.tabItens.peoximasEstreias.view.UpComingFragment
import com.google.android.material.tabs.TabLayout


class MoviesFragment : Fragment(R.layout.movies_fragments) {

    override fun onStart() {
        super.onStart()
        setUpTabs()
    }

    private fun setUpTabs() {
        val supportFragmentManager = childFragmentManager

        val viewPager = requireView().findViewById<ViewPager>(R.id.viewPager)
        val tabLayout = requireView().findViewById<TabLayout>(R.id.tabLayout)

        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.add(PopularFragment(), "Populares")
        adapter.add(TopRatedFragment(), "Mais Votados")
        adapter.add(PlayingNowFragment(), "Em Cartaz")
        adapter.add(UpComingFragment(), "Estreias")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }


}


