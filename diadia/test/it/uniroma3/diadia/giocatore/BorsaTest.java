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
	
	@BeforeEach
	void setUp() {
		this.borsa = new Borsa(10);
		this.a1 = new Attrezzo("a1",1);
		this.a2 = new Attrezzo("a2",1);
		this.peso10 = new Attrezzo("peso", 10);
	}
	
	/* Test addAttrezzo */
	@Test
	void testAddAttrezzoBorsaVuota() {
		this.borsa.addAttrezzo(a1);
		assertTrue(this.borsa.hasAttrezzo("a1"));
	}
	
	@Test
	void testAddAttrezzoBorsaNonVuota() {
		this.borsa.addAttrezzo(a1);
		this.borsa.addAttrezzo(a2);
		assertTrue(this.borsa.hasAttrezzo("a1"));
		assertTrue(this.borsa.hasAttrezzo("a2"));
	}
	
	@Test
	void testAddAttrezzoBorsaPiena() {
		this.borsa.addAttrezzo(peso10);
		assertFalse(this.borsa.addAttrezzo(a1));
	}
	
	/* Test getAttrezzo */
	@Test
	void testGetAttrezzoOggettoInBorsa(){
		this.borsa.addAttrezzo(a1);
		assertEquals(a1,this.borsa.getAttrezzo("a1"));
	}
	
	@Test
	void testGetAttrezzoBorsaVuota() {
		assertEquals(null,this.borsa.getAttrezzo("a1"));
	}
	
	@Test
	void testGetAttrezzoBorsaNonVuotaOggettoNonInBorsa() {
		this.borsa.addAttrezzo(a1);
		assertEquals(null,this.borsa.getAttrezzo("a2"));
	}
	
	/* Test hasAttrezzo */
	@Test
	void testHasAttrezzoOggettoInBorsa() {
		this.borsa.addAttrezzo(a1);
		assertTrue(this.borsa.hasAttrezzo("a1"));
	}
	
	@Test
	void testHasAttrezzoBorsaVuota() {
		assertFalse(this.borsa.hasAttrezzo("a1"));
	}
	
	@Test
	void testHasAttrezzoBorsaNonVuotaOggettoNonInBorsa() {
		this.borsa.addAttrezzo(a1);
		assertFalse(this.borsa.hasAttrezzo("a2"));
	}
	
	/* Test removeAttrezzo */
	@Test
	void testRemoveAttrezzoInBorsa() {
		this.borsa.addAttrezzo(a1);
		assertEquals(a1,this.borsa.removeAttrezzo("a1"));
	}
	
	@Test
	void testRemoveAttrezzoNonInBorsa() {
		this.borsa.addAttrezzo(a1);
		assertEquals(null,this.borsa.removeAttrezzo("a2"));
	}
	
	@Test
	void testRemoveAttrezzoSecondoAttrezzo() {
		this.borsa.addAttrezzo(a1);
		this.borsa.addAttrezzo(a2);
		assertEquals(a2,this.borsa.removeAttrezzo("a2"));
	}
	
	@Test
	void testRemoveAttrezzoBorsaVuota() {
		assertEquals(null,this.borsa.removeAttrezzo("a1"));
	}
	
	/* Test isEmpty */
	@Test
	void testIsEmptyBorsaVuota() {
		assertTrue(this.borsa.isEmpty());
	}
	
	@Test
	void testIsEmptyBorsaNonVuota() {
		this.borsa.addAttrezzo(a1);
		assertFalse(this.borsa.isEmpty());
	}
	
	@Test
	void testIsEmptyBorsaPiena() {
		this.borsa.addAttrezzo(peso10);
		assertFalse(this.borsa.isEmpty());
	}
}
