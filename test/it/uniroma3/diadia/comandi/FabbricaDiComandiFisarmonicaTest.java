package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOSimulator;

public class FabbricaDiComandiFisarmonicaTest {
	private FabbricaDiComandiFisarmonica fabbrica;
	private IOSimulator io;

	@Before
	public void setUp(){
		this.fabbrica = new FabbricaDiComandiFisarmonica();
		this.io = new IOSimulator();
	}

	@Test
	public void TestCostruisciComando_ComandoVai() {
		Comando comandoVai = this.fabbrica.costruisciComando("vai direzione", io);
		assertEquals("vai",comandoVai.getNome());
		assertEquals("direzione",comandoVai.getParametro());
	}
	
	@Test
	public void TestCostruisciComando_ComandoPrendi() {
		Comando comandoPrendi = this.fabbrica.costruisciComando("prendi attrezzo", io);
		assertEquals("prendi",comandoPrendi.getNome());
		assertEquals("attrezzo",comandoPrendi.getParametro());
	}
	
	@Test
	public void TestCostruisciComando_ComandoPosa() {
		Comando comandoPosa = this.fabbrica.costruisciComando("posa attrezzo", io);
		assertEquals("posa",comandoPosa.getNome());
		assertEquals("attrezzo",comandoPosa.getParametro());
	}
	
	@Test
	public void TestCostruisciComando_ComandoAiuto() {
		Comando comandoAiuto = this.fabbrica.costruisciComando("aiuto", io);
		assertEquals("aiuto",comandoAiuto.getNome());
	}
	
	@Test
	public void TestCostruisciComando_ComandoFine() {
		Comando comandoFine = this.fabbrica.costruisciComando("fine", io);
		assertEquals("fine",comandoFine.getNome());
	}
	
	@Test
	public void TestCostruisciComando_ComandoGuarda() {
		Comando comandoGuarda = this.fabbrica.costruisciComando("guarda", io);
		assertEquals("guarda",comandoGuarda.getNome());
	}
	
	@Test
	public void TestCostruisciComando_ComandoNonValido() {
		Comando comandoNonValido = this.fabbrica.costruisciComando("abcd", io);
		assertEquals("comando non valido",comandoNonValido.getNome());
	}

}
