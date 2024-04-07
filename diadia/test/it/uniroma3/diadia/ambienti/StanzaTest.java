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
	void testAddAttrezzoAggiuntaAttrezzo(){
		assertEquals(a1,this.stanza1.getAttrezzi()[0]);
	}
	
	@Test
	void testAddAttrezzoNumeroMassimoRaggiunto() {
		for(int i=2; i<=10; i++)
			this.stanza1.addAttrezzo(a1);
		assertFalse(this.stanza1.addAttrezzo(a1));
	}
	
	@Test
	void testAddAttrezzoAggiungeAttrezziInCodaCorrettamente() {
		this.stanza1.addAttrezzo(a2);
		assertEquals(a2,this.stanza1.getAttrezzi()[1]);
	}
	
	@Test
	void testAddAttrezzoParametroNull() {
		assertFalse(this.stanza1.addAttrezzo(null));
	}
	
	/* Test hasAttrezzo*/
	@Test
	void testHasAttrezzoAttrezzoPresente() {
		assertTrue(this.stanza1.hasAttrezzo("a1"));
	}
	
	@Test
	void testHasAttrezzoAttrezzoNonPresente() {
		assertFalse(this.stanza1.hasAttrezzo("a2"));
	}
	
	@Test
	void testHasAttrezzoNessunAttrezzoInStanza() {
		assertFalse(this.stanza3.hasAttrezzo("a1"));
	}
	
	@Test
	void testHasAttrezzoParametroNull() {
		assertFalse(this.stanza1.hasAttrezzo(null));
	}
	
	/* Test getAttrezzo */
	@Test
	void testGetAttrezzoAttrezzoPresente() {
		assertEquals(a1,this.stanza1.getAttrezzo("a1"));
	}
	
	@Test
	void testGetAttrezzoAttrezzoNonPresente() {
		assertEquals(null,this.stanza1.getAttrezzo("a2"));
	}
	
	@Test
	void testGetAttrezzoNessunAttrezzo() {
		assertEquals(null,this.stanza3.getAttrezzo("a1"));
	}
	
	@Test
	void testGetAttrezzoParametroNull() {
		assertEquals(null,this.stanza1.getAttrezzo(null));
	}
	
	/* Test removeAttrezzo */
	
	@Test
	void testRemoveAttrezzoPresente() {
		assertTrue(this.stanza1.removeAttrezzo(a1));
		assertTrue(null == this.stanza1.getAttrezzi()[0]);
	}
	
	@Test
	void testRemoveAttrezzoNonPresente() {
		assertFalse(this.stanza1.removeAttrezzo(a2));
	}
	
	@Test
	void testRemoveAttrezzoStanzaPrivaDiAttrezzi() {
		assertFalse(this.stanza3.removeAttrezzo(a1));
	}
	
	@Test
	void testRemoveAttrezzoParametroNull() {
		assertFalse(this.stanza1.removeAttrezzo(null));
	}
	
	/* Test getStanzaAdiacente */
	
	@Test
	void testGetStanzaAdiacenteStanzaPresente() {
		assertTrue(null!=this.stanza1.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testGetStanzaAdiacenteStanzaInQuellaDirezioneNonEsistente() {
		assertEquals(null,this.stanza2.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testGetStanzaAdiacenteStanzaRitornataCorretta() {
		assertEquals(stanza2,this.stanza1.getStanzaAdiacente("nord"));
	}
	
	/* Test impostaStanzaAdiacente*/
	@Test
	void testImpostaStanzaAdiacenteAggiuntaQuattroStanzeInDirezioniDiverse() {
		assertEquals(stanza2,this.stanza1.getStanzaAdiacente("nord"));
		assertEquals(stanza3,this.stanza1.getStanzaAdiacente("est"));
		assertEquals(stanza4,this.stanza1.getStanzaAdiacente("sud"));
		assertEquals(stanza5,this.stanza1.getStanzaAdiacente("ovest"));
	}
	
	@Test
	void testImpostaStanzaAdiacenteSovrascrizioneStanza() {
		this.stanza1.impostaStanzaAdiacente("nord", stanza6);
		assertEquals(stanza6,this.stanza1.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testImpostaStanzaAdiacenteParametroStanzaNull(){
		this.stanza1.impostaStanzaAdiacente("nord", null);
		assertEquals(null,this.stanza1.getStanzaAdiacente("nord"));
	}
	
	/* test getDirezioni */
	@Test
	void testGetDirezioniStanzaConQuattroDirezioniOccupate() {
		assertEquals("nord",this.stanza1.getDirezioni()[0]);
		assertEquals("est",this.stanza1.getDirezioni()[1]);
		assertEquals("sud",this.stanza1.getDirezioni()[2]);
		assertEquals("ovest",this.stanza1.getDirezioni()[3]);
	}
	
	@Test
	void testGetDirezioniStanzaConUnaSolaDirezione() {
		this.stanza2.impostaStanzaAdiacente("ovest", stanza6);
		assertEquals("ovest",this.stanza2.getDirezioni()[0]);
	}
	
	@Test
	void testGetDirezioniStanzaSenzaAdiacenze() {
		assertEquals(0,this.stanza6.getDirezioni().length);
	}


}
