package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {
	private StanzaMagica stanzaMagicaSoglia0;
	private StanzaMagica stanzaMagicaSogliaStandard;
	private Attrezzo peso1;
	private int pesoOriginale;
	private int pesoDoppio;

	@Before
	public void setUp() {
		this.stanzaMagicaSoglia0 = new StanzaMagica("stanzaMagicaSoglia1",0);
		this.stanzaMagicaSogliaStandard = new StanzaMagica("stanzaMagicaSogliaStandard");
		this.peso1 = new Attrezzo("peso1",1);
		this.pesoOriginale = this.peso1.getPeso();
		this.pesoDoppio = pesoOriginale*2;
	}
	
	@Test
	public void testAddAttrezzo_AttrezzoPosatoSogliaNonSuperata() {
		assertTrue(this.stanzaMagicaSogliaStandard.addAttrezzo(peso1));
		assertEquals(this.pesoOriginale,this.stanzaMagicaSogliaStandard.getAttrezzi()[0].getPeso());
	}

	@Test
	public void testAddAttrezzoEModificaAttrezzo_AttrezzoPosatoConSogliaSuperataPesoDoppio() {
		assertTrue(this.stanzaMagicaSoglia0.addAttrezzo(peso1));
		assertEquals(this.pesoDoppio,this.stanzaMagicaSoglia0.getAttrezzi()[0].getPeso());
	}
	
	@Test
	public void  testAddAttrezzo_AttrezzoNull() {
		assertFalse(this.stanzaMagicaSogliaStandard.addAttrezzo(null));
	}

}
