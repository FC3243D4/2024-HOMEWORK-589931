package it.uniroma3.diadia;



import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

public class PartitaTest {
	private Labirinto labirinto;
	private Stanza stanza1;
	private Stanza stanza2;
	private Partita partita;
	private Giocatore giocatore;

	@Before
	public void setUp() {
		this.labirinto = new Labirinto();
		this.giocatore = new Giocatore();
		this.stanza1 = new Stanza("stanza1");
		this.stanza2 = new Stanza("stanza2");
		this.labirinto.setStanzaIniziale(stanza1);
		this.labirinto.setStanzaVincente(stanza2);
		this.partita = new Partita(this.labirinto);
		this.partita.getLabirinto().getStanzaIniziale().impostaStanzaAdiacente("sud", stanza1);
		this.giocatore.setStanzaCorrente(this.labirinto.getStanzaIniziale());
	}
	
	/* Test vinta */
	@Test
	public void testVinta_Vittoria() {
		this.partita.getGiocatore().setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.vinta());
	}
	
	@Test
	public void testVinta_NonAncoraVinta() {
		assertFalse(this.partita.vinta());
	}
	
	@Test
	public void testVinta_NonAncoraVintaDopoSpostamentoInStanzaNonVincente() {
		this.partita.getGiocatore().setStanzaCorrente(this.partita.getGiocatore().getStanzaCorrente().getStanzaAdiacente(this.partita.getGiocatore().getStanzaCorrente().getDirezioni()[1]));
		assertFalse(this.partita.vinta());
	}
	
	@Test
	public void testVinta_VittoriaDopoSpostamentoInStanzaVincente() {
		this.partita.getGiocatore().setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
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
		this.partita.getGiocatore().setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinita_NonAncoraFinita() {
		assertFalse(this.partita.isFinita());
	}

}
