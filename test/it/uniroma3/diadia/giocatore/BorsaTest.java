package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {
	private Borsa borsa;
	private Attrezzo a1;
	private Attrezzo a2;
	private Attrezzo peso10;
	private Attrezzo peso9;
	
	@BeforeEach
	void setUp() {
		this.borsa = new Borsa(10);
		this.a1 = new Attrezzo("a1",1);
		this.a2 = new Attrezzo("a2",2);
		this.peso10 = new Attrezzo("peso", 10);
		this.peso9 = new Attrezzo("peso", 9);
	}
	
	/* Test addAttrezzo */
	@Test
	void testAddAttrezzo_BorsaVuota() {
		this.borsa.addAttrezzo(a1);
		assertTrue(this.borsa.hasAttrezzo("a1"));
	}
	
	@Test
	void testAddAttrezzo_BorsaNonVuota() {
		this.borsa.addAttrezzo(a1);
		this.borsa.addAttrezzo(a2);
		assertTrue(this.borsa.hasAttrezzo("a1"));
		assertTrue(this.borsa.hasAttrezzo("a2"));
	}
	
	@Test
	void testAddAttrezzo_BorsaPiena() {
		this.borsa.addAttrezzo(peso10);
		assertFalse(this.borsa.addAttrezzo(a1));
	}
	
	@Test
	void testAddAttrezzo_BorsaQuasiPienaEAggiuntaSupererebbeLimite() {
		this.borsa.addAttrezzo(peso9);
		assertFalse(this.borsa.addAttrezzo(a2));
	}
	
	/* Test getAttrezzo */
	@Test
	void testGetAttrezzo_OggettoInBorsa(){
		this.borsa.addAttrezzo(a1);
		assertEquals(a1,this.borsa.getAttrezzo("a1"));
	}
	
	@Test
	void testGetAttrezzo_BorsaVuota() {
		assertNull(this.borsa.getAttrezzo("a1"));
	}
	
	@Test
	void testGetAttrezzo_BorsaNonVuotaOggettoNonInBorsa() {
		this.borsa.addAttrezzo(a1);
		assertNull(this.borsa.getAttrezzo("a2"));
	}
	
	/* Test hasAttrezzo */
	@Test
	void testHasAttrezzo_OggettoInBorsa() {
		this.borsa.addAttrezzo(a1);
		assertTrue(this.borsa.hasAttrezzo("a1"));
	}
	
	@Test
	void testHasAttrezzo_BorsaVuota() {
		assertFalse(this.borsa.hasAttrezzo("a1"));
	}
	
	@Test
	void testHasAttrezzo_BorsaNonVuotaOggettoNonInBorsa() {
		this.borsa.addAttrezzo(a1);
		assertFalse(this.borsa.hasAttrezzo("a2"));
	}
	
	/* Test removeAttrezzo */
	@Test
	void testRemoveAttrezzo_InBorsa() {
		this.borsa.addAttrezzo(a1);
		assertEquals(a1,this.borsa.removeAttrezzo("a1"));
	}
	
	@Test
	void testRemoveAttrezzo_NonInBorsa() {
		this.borsa.addAttrezzo(a1);
		assertNull(this.borsa.removeAttrezzo("a2"));
	}
	
	@Test
	void testRemoveAttrezzo_SecondoAttrezzo() {
		this.borsa.addAttrezzo(a1);
		this.borsa.addAttrezzo(a2);
		assertEquals(a2,this.borsa.removeAttrezzo("a2"));
	}
	
	@Test
	void testRemoveAttrezzo_BorsaVuota() {
		assertNull(this.borsa.removeAttrezzo("a1"));
	}
	
	/* Test isEmpty */
	@Test
	void testIsEmpty_BorsaVuota() {
		assertTrue(this.borsa.isEmpty());
	}
	
	@Test
	void testIsEmpty_BorsaNonVuota() {
		this.borsa.addAttrezzo(a1);
		assertFalse(this.borsa.isEmpty());
	}
	
	@Test
	void testIsEmpty_BorsaPiena() {
		this.borsa.addAttrezzo(peso10);
		assertFalse(this.borsa.isEmpty());
	}
}
