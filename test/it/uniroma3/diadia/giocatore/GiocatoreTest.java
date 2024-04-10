package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class GiocatoreTest {
	private Giocatore giocatore;
	private Attrezzo a;
	int cfuMax;

	@BeforeEach
	void setUp() {
		this.giocatore = new Giocatore();
		this.a = new Attrezzo("a",1);
		this.cfuMax=this.giocatore.getCfu();
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
	
	/* Test riduciCfu */
	@Test
	void testRiduciCfuSenzaArgomento() {
		this.giocatore.riduciCfu();
		assertEquals(this.cfuMax-1,this.giocatore.getCfu());
	}
	
	@Test
	void testRiduciCfuConArgomentoInteroPositivo() {
		this.giocatore.riduciCfu(3);
		assertEquals(this.cfuMax-3,this.giocatore.getCfu());
	}
	
	@Test
	void testRiduciCfuConArgomentoInteroNegativo() {
		this.giocatore.riduciCfu(-1);
		assertEquals(20,this.giocatore.getCfu());
	}
}
