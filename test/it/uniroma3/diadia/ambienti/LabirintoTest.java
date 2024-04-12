package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LabirintoTest {
	private Labirinto labirinto;
	
	@Before
	public void setUp() {
		this.labirinto=new Labirinto();
		this.labirinto.creaStanze();
	}

	/* Test creaStanze */
	@Test
	public void testCreaStanza_AttrezzoLanternaInN10() {
		this.labirinto.setStanzaIniziale(this.labirinto.getStanzaIniziale().getStanzaAdiacente("sud"));
		assertTrue(this.labirinto.getStanzaIniziale().hasAttrezzo("lanterna"));
	}
	
	@Test
	public void testCreaStanze_AttrezzoOssoInAtrio(){
		assertTrue(this.labirinto.getStanzaIniziale().hasAttrezzo("osso"));
	}
	
	@Test
	public void testCreaStanze_BibliotecaNordAtrioEAtrioSudBiblioteca() {
		assertEquals(this.labirinto.getStanzaVincente(),this.labirinto.getStanzaIniziale().getStanzaAdiacente("nord"));
		assertEquals(this.labirinto.getStanzaIniziale(),this.labirinto.getStanzaVincente().getStanzaAdiacente("sud"));
	}
}
