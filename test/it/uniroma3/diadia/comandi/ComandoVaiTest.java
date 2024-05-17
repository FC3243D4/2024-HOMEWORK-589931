package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVaiTest {

	private static final String DIREZIONE_NULLA = null;
	private static final String DIREZIONE = "direzione";
	private static final Stanza NULLA = null;

	private ComandoVai comando;
	private Partita partita;
	private Stanza corrente,adiacente;
	private LabirintoBuilder builder;
	private IOSimulator io;
	private Labirinto bilocale;

	@Before
	public void setUp(){
		this.comando = new ComandoVai();
		this.io = new IOSimulator();
		this.comando.setIo(io);
		this.builder = new LabirintoBuilder();
		this.partita = new Partita(this.builder.getLabirinto().LabirintoDiaDia());
		this.corrente = new Stanza("corrente");
		this.adiacente = new Stanza("adiacente");
		this.corrente.impostaStanzaAdiacente(DIREZIONE, this.adiacente);
		this.partita.setStanzaCorrente(this.corrente);
		this.bilocale = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera").addAttrezzo("letto",10) 

				.addAdiacenza("salotto", "camera", "nord") 

				.getLabirinto();
	}


	@Test
	public void testEsegui_NonAgisceSeLaStanzaDoveVoglioSpostarmiIsLaStanzaNulla() {
		this.corrente.impostaStanzaAdiacente(DIREZIONE, NULLA);
		this.comando.setParametro(DIREZIONE);
		this.comando.esegui(this.partita);
		assertEquals("corrente", this.partita.getStanzaCorrente().getNome());
	}

	@Test
	public void testesegui_NonAgisceSeIlParametroImpostatoIsLaDirezioneNulla() {
		this.comando.setParametro(DIREZIONE_NULLA);
		this.comando.esegui(this.partita);
		assertEquals("corrente", this.partita.getStanzaCorrente().getNome());
	}

	@Test
	public void testEsegui_AgisceSeIlParametroImpostatoIsLaDirezioneNonNullaEStanzaNonNullaVediSetUp() {
		this.comando.setParametro(DIREZIONE);
		this.comando.esegui(this.partita);
		assertEquals("adiacente", this.partita.getStanzaCorrente().getNome());
	}

	@Test 
	public void testEsegui_ValidoModificaICfuDelGiocatoreDellaPartita() {
		this.comando.setParametro(DIREZIONE);
		this.comando.esegui(this.partita);
		assertEquals(19, this.partita.getGiocatore().getCfu());

	}
	
	@Test
	public void testEsegui_SuBilocale() {
		this.partita=new Partita(bilocale);
		this.comando.setParametro("nord");
		this.comando.esegui(this.partita);
		assertEquals(this.partita.getStanzaCorrente().getNome(),"camera");
	}

}
