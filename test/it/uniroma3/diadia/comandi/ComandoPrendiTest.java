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

public class ComandoPrendiTest {
	private ComandoPrendi comando;
	private Partita partita;
	private Stanza stanza;
	private Attrezzo attrezzo;
	private LabirintoBuilder builder;
	private IOSimulator io;
	private Labirinto monolocale;


	@Before
	public void setUp(){
		this.comando = new ComandoPrendi(io);
		this.io = new IOSimulator();
		this.builder = new LabirintoBuilder();
		this.partita = new Partita(this.builder.getLabirinto().LabirintoDiaDia());
		this.stanza = new Stanza("stanza");
		this.partita.setStanzaCorrente(stanza);
		this.attrezzo = new Attrezzo("attrezzo", 1);
		stanza.addAttrezzo(attrezzo);
		this.monolocale = new LabirintoBuilder()
				.addStanzaIniziale("camera").addAttrezzo("letto",10)

				.getLabirinto();
	}

	@Test
	public void testEsegui_NonAgisceSeIlNomeDellAttrezzoIsLaStringaNulla() {
		this.comando.setParametro(null);
		this.comando.esegui(partita);
		assertTrue(this.partita.getGiocatore().getBorsa().isEmpty());
	}

	@Test
	public void testEsegui_SuAttrezzoNonInStanzaNonAgisce() {
		this.comando.setParametro("nonInStanza");
		this.comando.esegui(partita);
		assertTrue(this.partita.getGiocatore().getBorsa().isEmpty());
	}

	@Test
	public void testEsegui_SuAttrezzoInStanzaAggiungeLAttrezzoAllaBorsa() {
		this.comando.setParametro("attrezzo");
		this.comando.esegui(partita);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));	
	}

	@Test
	public void testEsegui_SuAttrezzoInStanzaRimuoveLAttrezzoDallaStanza() {
		this.comando.setParametro("attrezzo");
		this.comando.esegui(partita);
		assertFalse(this.stanza.hasAttrezzo("attrezzo"));
	}
	
	@Test
	public void testEsegui_SuMonolocale() {
		this.partita=new Partita(monolocale);
		this.comando.setParametro("letto");
		this.comando.esegui(this.partita);
		assertEquals(true,this.partita.getGiocatore().getBorsa().hasAttrezzo("letto"));	
	}


}
