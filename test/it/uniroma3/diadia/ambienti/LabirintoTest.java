package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {
	private Labirinto labirinto;
	
	@BeforeEach
	void setUp() {
		this.labirinto=new Labirinto();
		this.labirinto.creaStanze();
	}

	/* Test creaStanze */
	@Test
	void testCreaStanza_AttrezzoLanternaInN10() {
		this.labirinto.setStanzaIniziale(this.labirinto.getStanzaIniziale().getStanzaAdiacente("sud"));
		assertTrue(this.labirinto.getStanzaIniziale().hasAttrezzo("lanterna"));
	}
	
	@Test
	void testCreaStanze_AttrezzoOssoInAtrio(){
		assertTrue(this.labirinto.getStanzaIniziale().hasAttrezzo("osso"));
	}
	
	@Test
	void testCreaStanze_BibliotecaNordAtrioEAtrioSudBiblioteca() {
		assertEquals(this.labirinto.getStanzaVincente(),this.labirinto.getStanzaIniziale().getStanzaAdiacente("nord"));
		assertEquals(this.labirinto.getStanzaIniziale(),this.labirinto.getStanzaVincente().getStanzaAdiacente("sud"));
	}
}
