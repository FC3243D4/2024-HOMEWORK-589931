package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {
	private Borsa borsa;
	private Attrezzo a1;
	private Attrezzo a2;
	private Attrezzo peso10;
	private Attrezzo peso9;
	
	@Before
	public void setUp() {
		this.borsa = new Borsa(10);
		this.a1 = new Attrezzo("a1",1);
		this.a2 = new Attrezzo("a2",2);
		this.peso10 = new Attrezzo("peso", 10);
		this.peso9 = new Attrezzo("peso", 9);
	}
	
	/* Test addAttrezzo */
	@Test
	public void testAddAttrezzo_BorsaVuota() {
		this.borsa.addAttrezzo(a1);
		assertTrue(this.borsa.hasAttrezzo("a1"));
	}
	
	@Test
	public void testAddAttrezzo_BorsaNonVuota() {
		this.borsa.addAttrezzo(a1);
		this.borsa.addAttrezzo(a2);
		assertTrue(this.borsa.hasAttrezzo("a1"));
		assertTrue(this.borsa.hasAttrezzo("a2"));
	}
	
	@Test
	public void testAddAttrezzo_BorsaPiena() {
		this.borsa.addAttrezzo(peso10);
		assertFalse(this.borsa.addAttrezzo(a1));
	}
	
	@Test
	public void testAddAttrezzo_BorsaQuasiPienaEAggiuntaSupererebbeLimite() {
		this.borsa.addAttrezzo(peso9);
		assertFalse(this.borsa.addAttrezzo(a2));
	}
	
	/* Test getAttrezzo */
	@Test
	public void testGetAttrezzo_OggettoInBorsa(){
		this.borsa.addAttrezzo(a1);
		assertEquals(a1,this.borsa.getAttrezzo("a1"));
	}
	
	@Test
	public void testGetAttrezzo_BorsaVuota() {
		assertNull(this.borsa.getAttrezzo("a1"));
	}
	
	@Test
	public void testGetAttrezzo_BorsaNonVuotaOggettoNonInBorsa() {
		this.borsa.addAttrezzo(a1);
		assertNull(this.borsa.getAttrezzo("a2"));
	}
	
	/* Test hasAttrezzo */
	@Test
	public void testHasAttrezzo_OggettoInBorsa() {
		this.borsa.addAttrezzo(a1);
		assertTrue(this.borsa.hasAttrezzo("a1"));
	}
	
	@Test
	public void testHasAttrezzo_BorsaVuota() {
		assertFalse(this.borsa.hasAttrezzo("a1"));
	}
	
	@Test
	public void testHasAttrezzo_BorsaNonVuotaOggettoNonInBorsa() {
		this.borsa.addAttrezzo(a1);
		assertFalse(this.borsa.hasAttrezzo("a2"));
	}
	
	/* Test removeAttrezzo */
	@Test
	public void testRemoveAttrezzo_InBorsa() {
		this.borsa.addAttrezzo(a1);
		assertEquals(a1,this.borsa.removeAttrezzo("a1"));
	}
	
	@Test
	public void testRemoveAttrezzo_NonInBorsa() {
		this.borsa.addAttrezzo(a1);
		assertNull(this.borsa.removeAttrezzo("a2"));
	}
	
	@Test
	public void testRemoveAttrezzo_SecondoAttrezzo() {
		this.borsa.addAttrezzo(a1);
		this.borsa.addAttrezzo(a2);
		assertEquals(a2,this.borsa.removeAttrezzo("a2"));
	}
	
	@Test
	public void testRemoveAttrezzo_BorsaVuota() {
		assertNull(this.borsa.removeAttrezzo("a1"));
	}
	
	/* Test isEmpty */
	@Test
	public void testIsEmpty_BorsaVuota() {
		assertTrue(this.borsa.isEmpty());
	}
	
	@Test
	public void testIsEmpty_BorsaNonVuota() {
		this.borsa.addAttrezzo(a1);
		assertFalse(this.borsa.isEmpty());
	}
	
	@Test
	public void testIsEmpty_BorsaPiena() {
		this.borsa.addAttrezzo(peso10);
		assertFalse(this.borsa.isEmpty());
	}
}
