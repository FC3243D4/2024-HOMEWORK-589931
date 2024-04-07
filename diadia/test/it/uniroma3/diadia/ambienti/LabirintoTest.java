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
	void testAttrezzoLanternaInN10() {
		this.labirinto.setStanzaCorrente(this.labirinto.getStanzaCorrente().getStanzaAdiacente("sud"));
		assertTrue(this.labirinto.getStanzaCorrente().hasAttrezzo("lanterna"));
	}
	
	@Test
	void testAttrezzoOssoInAtrio(){
		assertTrue(this.labirinto.getStanzaCorrente().hasAttrezzo("osso"));
	}
	
	@Test
	void testBibliotecaNordAtrioEAtrioSudBiblioteca() {
		assertEquals(this.labirinto.getStanzaVincente(),this.labirinto.getStanzaCorrente().getStanzaAdiacente("nord"));
		assertEquals(this.labirinto.getStanzaCorrente(),this.labirinto.getStanzaVincente().getStanzaAdiacente("sud"));
	}
}
