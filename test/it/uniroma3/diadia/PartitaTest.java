package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

class PartitaTest {
	private Labirinto labirinto;
	private Stanza stanza1;
	private Stanza stanza2;
	private Partita partita;

	@BeforeEach
	void setUp() {
		this.labirinto = new Labirinto();
		this.stanza1 = new Stanza("stanza1");
		this.stanza2 = new Stanza("stanza2");
		this.labirinto.setStanzaCorrente(stanza1);
		this.labirinto.setStanzaVincente(stanza2);
		this.partita = new Partita();
		this.partita.getLabirinto().getStanzaCorrente().impostaStanzaAdiacente("sud", stanza1);
	}
	
	/* Test vinta */
	@Test
	void testVinta_Vittoria() {
		this.labirinto.setStanzaCorrente(stanza2);
		this.partita.setLabirinto(labirinto);
		assertTrue(this.partita.vinta());
	}
	
	@Test
	void testVinta_NonAncoraVinta() {
		assertFalse(this.partita.vinta());
	}
	
	@Test
	void testVinta_NonAncoraVintaDopoSpostamentoInStanzaNonVincente() {
		this.partita.getLabirinto().setStanzaCorrente(this.partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(this.partita.getLabirinto().getStanzaCorrente().getDirezioni()[1]));
		assertFalse(this.partita.vinta());
	}
	
	@Test
	void testVinta_VittoriaDopoSpostamentoInStanzaVincente() {
		this.partita.getLabirinto().setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.vinta());
	}
	
	/* Test isFinita */
	@Test
	void testIsFinita_ZeroCfu() {
		this.partita.getGiocatore().setCfu(0);
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	void testIsFinita_FinitaTrue() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	void testIsFinita_Vinta() {
		this.labirinto.setStanzaCorrente(stanza2);
		this.partita.setLabirinto(labirinto);
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	void testIsFinita_NonAncoraFinita() {
		assertFalse(this.partita.isFinita());
	}

}
