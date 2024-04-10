package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StanzaTest {
	private Stanza stanza1;
	private Stanza stanza2;
	private Stanza stanza3;
	private Stanza stanza4;
	private Stanza stanza5;
	private Stanza stanza6;
	private Attrezzo a1;
	private Attrezzo a2;
	
	@BeforeEach
	void setUp() {
		this.stanza1 =new Stanza("stanza1");
		this.stanza2 =new Stanza("stanza2");
		this.stanza3 =new Stanza("stanza3");
		this.stanza4 =new Stanza("stanza4");
		this.stanza5 =new Stanza("stanza5");
		this.stanza6 =new Stanza("stanza6");
		this.a1 = new Attrezzo("a1",1);
		this.a2 = new Attrezzo("a2",2);
		this.stanza1.addAttrezzo(a1);
		this.stanza2.addAttrezzo(a2);
		this.stanza1.impostaStanzaAdiacente("nord", stanza2);
		this.stanza1.impostaStanzaAdiacente("est", stanza3);
		this.stanza1.impostaStanzaAdiacente("sud", stanza4);
		this.stanza1.impostaStanzaAdiacente("ovest", stanza5);
	}
	
	/* Test addAttrezzo */
	@Test 
	void testAddAttrezzo_AggiuntaAttrezzo(){
		assertEquals(a1,this.stanza1.getAttrezzi()[0]);
	}
	
	@Test
	void testAddAttrezzo_NumeroMassimoRaggiunto() {
		for(int i=2; i<=10; i++)
			this.stanza1.addAttrezzo(a1);
		assertFalse(this.stanza1.addAttrezzo(a1));
	}
	
	@Test
	void testAddAttrezzo_AggiungeAttrezziInCodaCorrettamente() {
		this.stanza1.addAttrezzo(a2);
		assertEquals(a2,this.stanza1.getAttrezzi()[1]);
	}
	
	@Test
	void testAddAttrezzo_ParametroNull() {
		assertFalse(this.stanza1.addAttrezzo(null));
	}
	
	/* Test hasAttrezzo*/
	@Test
	void testHasAttrezzo_AttrezzoPresente() {
		assertTrue(this.stanza1.hasAttrezzo("a1"));
	}
	
	@Test
	void testHasAttrezzo_AttrezzoNonPresente() {
		assertFalse(this.stanza1.hasAttrezzo("a2"));
	}
	
	@Test
	void testHasAttrezzo_NessunAttrezzoInStanza() {
		assertFalse(this.stanza3.hasAttrezzo("a1"));
	}
	
	@Test
	void testHasAttrezzo_ParametroNull() {
		assertFalse(this.stanza1.hasAttrezzo(null));
	}
	
	/* Test getAttrezzo */
	@Test
	void testGetAttrezzo_AttrezzoPresente() {
		assertEquals(a1,this.stanza1.getAttrezzo("a1"));
	}
	
	@Test
	void testGetAttrezzo_AttrezzoNonPresente() {
		assertNull(this.stanza1.getAttrezzo("a2"));
	}
	
	@Test
	void testGetAttrezzo_NessunAttrezzo() {
		assertNull(this.stanza3.getAttrezzo("a1"));
	}
	
	@Test
	void testGetAttrezzo_ParametroNull() {
		assertNull(this.stanza1.getAttrezzo(null));
	}
	
	/* Test removeAttrezzo */
	
	@Test
	void testRemoveAttrezzo_AttrezzoPresente() {
		assertTrue(this.stanza1.removeAttrezzo(a1));
		assertNull(this.stanza1.getAttrezzi()[0]);
	}
	
	@Test
	void testRemoveAttrezzo_AttrezzoNonPresente() {
		assertFalse(this.stanza1.removeAttrezzo(a2));
	}
	
	@Test
	void testRemoveAttrezzo_StanzaPrivaDiAttrezzi() {
		assertFalse(this.stanza3.removeAttrezzo(a1));
	}
	
	@Test
	void testRemoveAttrezzo_ParametroNull() {
		assertFalse(this.stanza1.removeAttrezzo(null));
	}
	
	/* Test getStanzaAdiacente */
	
	@Test
	void testGetStanzaAdiacente_StanzaPresente() {
		assertNotNull(this.stanza1.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testGetStanzaAdiacente_StanzaInQuellaDirezioneNonEsistente() {
		assertNull(this.stanza2.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testGetStanzaAdiacente_StanzaRitornataCorretta() {
		assertEquals(stanza2,this.stanza1.getStanzaAdiacente("nord"));
	}
	
	/* Test impostaStanzaAdiacente*/
	@Test
	void testImpostaStanzaAdiacente_AggiuntaQuattroStanzeInDirezioniDiverse() {
		assertEquals(stanza2,this.stanza1.getStanzaAdiacente("nord"));
		assertEquals(stanza3,this.stanza1.getStanzaAdiacente("est"));
		assertEquals(stanza4,this.stanza1.getStanzaAdiacente("sud"));
		assertEquals(stanza5,this.stanza1.getStanzaAdiacente("ovest"));
	}
	
	@Test
	void testImpostaStanzaAdiacente_SovrascrizioneStanza() {
		this.stanza1.impostaStanzaAdiacente("nord", stanza6);
		assertEquals(stanza6,this.stanza1.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testImpostaStanzaAdiacente_ParametroStanzaNull(){
		this.stanza1.impostaStanzaAdiacente("nord", null);
		assertNull(this.stanza1.getStanzaAdiacente("nord"));
	}
	
	/* test getDirezioni */
	@Test
	void testGetDirezioni_StanzaConQuattroDirezioniOccupate() {
		assertEquals("nord",this.stanza1.getDirezioni()[0]);
		assertEquals("est",this.stanza1.getDirezioni()[1]);
		assertEquals("sud",this.stanza1.getDirezioni()[2]);
		assertEquals("ovest",this.stanza1.getDirezioni()[3]);
	}
	
	@Test
	void testGetDirezioni_StanzaConUnaSolaDirezione() {
		this.stanza2.impostaStanzaAdiacente("ovest", stanza6);
		assertEquals("ovest",this.stanza2.getDirezioni()[0]);
	}
	
	@Test
	void testGetDirezioni_StanzaSenzaAdiacenze() {
		assertEquals(0,this.stanza6.getDirezioni().length);
	}


}
