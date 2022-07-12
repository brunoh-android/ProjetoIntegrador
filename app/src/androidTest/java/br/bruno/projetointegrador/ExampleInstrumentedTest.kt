package br.bruno.projetointegrador

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.bruno.projetointegrador.home.view.PlayingNowFragment
import br.bruno.projetointegrador.home.view.TopRatedFragment
import br.bruno.projetointegrador.home.view.PopularFragment
import br.bruno.projetointegrador.home.tabItens.peoximasEstreias.view.UpComingFragment
import junit.framework.Assert

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {

        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("br.bruno.projetointegrador", appContext.packageName)
    }

    @Test
    fun fragmentEmCartazIsNotNull(){
        Assert.assertNotNull(PlayingNowFragment());
    }

    @Test
    fun fragmentMaisVotadosIsNotNull(){
        Assert.assertNotNull(TopRatedFragment());
    }

    @Test
    fun fragmentPopularIsNotNull(){
        Assert.assertNotNull(PopularFragment());
    }

    @Test
    fun fragmentProximasEstreiasIsNotNull(){
        Assert.assertNotNull(UpComingFragment());
    }
}