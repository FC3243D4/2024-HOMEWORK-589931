package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class GiocatoreTest {
	private Giocatore giocatore;
	private Attrezzo a;

	@BeforeEach
	void setUp() {
		this.giocatore = new Giocatore();
		this.a = new Attrezzo("a",1);
	}
	
	/* Test costruttore */
	@Test
	void testCfuInizializzatiCorrettamente() {
		assertEquals(20,this.giocatore.getCfu());
	}
	
	@Test
	void testBorsaEffettivamenteBorsa() {
		assertEquals("Borsa",this.giocatore.getBorsa().getClass().getSimpleName());
	}
	
	@Test
	void testBorsaModificabile() {
		this.giocatore.getBorsa().addAttrezzo(a);
		assertTrue(this.giocatore.getBorsa().hasAttrezzo("a"));
	}
}
