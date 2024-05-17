package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {
	private ComandoPosa comando;
	private Partita partita;
	private Stanza stanza;
	private LabirintoBuilder builder;
	private Attrezzo attrezzoInBorsa;
	private IOSimulator io;
	private Labirinto monolocale;


	@Before
	public void setUp(){
		this.comando = new ComandoPosa();
		this.builder = new LabirintoBuilder();
		this.io = new IOSimulator();
		this.comando.setIo(io);
		this.partita = new Partita(this.builder.getLabirinto().LabirintoDiaDia());
		this.stanza = new Stanza("stanza");
		this.partita.setStanzaCorrente(stanza);
		this.attrezzoInBorsa = new Attrezzo("attrezzo", 1);
		this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzoInBorsa);
		this.monolocale = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("salotto") 
				.getLabirinto();
	}

	@Test
	public void testEsegui_NonAgisceSeIlNomeDellAttrezzoIsLaStringaNulla() {
		this.comando.setParametro(null);
		this.comando.esegui(partita);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));
	}

	@Test
	public void testEsegui_SuAttrezzoNonInBorsaNonAgisce() {
		this.comando.setParametro("nonInBorsa");
		this.comando.esegui(partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("nonInBorsa"));
	}

	@Test
	public void testEsegui_SuAttrezzoInBorsaAggiungeLAttrezzoAllaStanza() {
		this.comando.setParametro("attrezzo");
		this.comando.esegui(partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("attrezzo"));	
	}

	@Test
	public void testEsegui_SuAttrezzoInBorsaLoRimuove() {
		this.comando.setParametro("attrezzo");
		this.comando.esegui(partita);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));
	}
	
	@Test
	public void testEsegui_SuMonolocale() {
		this.partita=new Partita(monolocale);
		this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzoInBorsa);
		this.comando.setParametro("attrezzo");
		this.comando.esegui(this.partita);
		assertEquals(true,this.partita.getStanzaCorrente().hasAttrezzo("attrezzo"));
	}

}
