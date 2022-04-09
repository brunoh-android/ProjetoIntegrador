package br.bruno.projetointegrador.fragments.BottonNavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.bruno.projetointegrador.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.home_fragments, container, false)
    }

    val teste = arrayListOf("A", "B")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        TabLayoutMediator(view.findViewById(R.id.tabLayout),view.findViewById(R.id.viewPager)){
            tab, position ->
            tab.text = teste[position]
        }
    }

//
//        var adapter = ViewPagerAdapter(childFragmentManager)
//        adapter.add(EmCartazFragment(),"Em Cartaz")
//        adapter.add(MaisVotadoFragment(),"Mais Votados")
//        adapter.add(PopularesFragment(),"Popular")
//        adapter.add(ProximasEstreiasFragment(),"Proximas Estreias")


}


