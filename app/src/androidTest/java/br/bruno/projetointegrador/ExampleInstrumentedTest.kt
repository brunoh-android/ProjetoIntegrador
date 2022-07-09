package br.bruno.projetointegrador

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.bruno.projetointegrador.home.tabItens.emCartaz.view.EmCartazFragment
import br.bruno.projetointegrador.home.tabItens.maisVotados.view.MaisVotadoFragment
import br.bruno.projetointegrador.home.tabItens.popular.view.PopularesFragment
import br.bruno.projetointegrador.home.tabItens.proximasEstreias.view.ProximasEstreiasFragment
import junit.framework.Assert

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.util.regex.Pattern.matches


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