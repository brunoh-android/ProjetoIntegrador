package br.bruno.projetointegrador

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.bruno.projetointegrador.home.emCartaz.view.EmCartazFragment
import br.bruno.projetointegrador.home.maisVotados.view.MaisVotadoFragment
import br.bruno.projetointegrador.home.popular.view.PopularesFragment
import br.bruno.projetointegrador.home.proximasEstreias.view.ProximasEstreiasFragment
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
        Assert.assertNotNull(EmCartazFragment());
    }

    @Test
    fun fragmentMaisVotadosIsNotNull(){
        Assert.assertNotNull(MaisVotadoFragment());
    }

    @Test
    fun fragmentPopularIsNotNull(){
        Assert.assertNotNull(PopularesFragment());
    }

    @Test
    fun fragmentProximasEstreiasIsNotNull(){
        Assert.assertNotNull(ProximasEstreiasFragment());
    }
}