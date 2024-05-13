package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {
	private Labirinto labirinto;
	private Stanza stanza1;
	private Stanza stanza2;
	private Partita partita;
	
	@Before
	public void setUp() {
		this.labirinto = new Labirinto();
		this.stanza1 = new Stanza("stanza1");
		this.stanza2 = new Stanza("stanza2");
		this.labirinto.setStanzaIniziale(stanza1);
		this.labirinto.setStanzaVincente(stanza2);
		this.partita = new Partita(this.labirinto);
		this.partita.getLabirinto().getStanzaIniziale().impostaStanzaAdiacente("sud", stanza1);
		this.partita.setStanzaCorrente(this.labirinto.getStanzaIniziale());
	}
	
	/* Test vinta */
	@Test
	public void testVinta_Vittoria() {
		this.partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.vinta());
	}
	
	@Test
	public void testVinta_NonAncoraVinta() {
		assertFalse(this.partita.vinta());
	}
	
	@Test
	public void testVinta_NonAncoraVintaDopoSpostamentoInStanzaNonVincente() {
		this.partita.setStanzaCorrente(this.partita.getStanzaCorrente().getStanzaAdiacente(this.partita.getStanzaCorrente().getDirezioni().get(0)));
		assertFalse(this.partita.vinta());
	}
	
	@Test
	public void testVinta_VittoriaDopoSpostamentoInStanzaVincente() {
		this.partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.vinta());
	}
	
	/* Test isFinita */
	@Test
	public void testIsFinita_ZeroCfu() {
		this.partita.getGiocatore().setCfu(0);
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinita_FinitaTrue() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinita_Vinta() {
		this.partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinita_NonAncoraFinita() {
		assertFalse(this.partita.isFinita());
	}

}
