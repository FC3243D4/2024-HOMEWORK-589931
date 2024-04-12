package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	private IOConsole io;

	public DiaDia(IOConsole console) {
		Labirinto labirinto = new Labirinto();
		labirinto.creaStanze();
		this.partita = new Partita(labirinto);
		this.io = console;
	}

	public void gioca() {
		String istruzione;

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		io.mostraMessaggio("Attualmente ti trovi in");
		//io.mostraMessaggio(partita.getGiocatore().getStanzaCorrente().getDescrizione());
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		do {
			istruzione = io.leggiRiga();
		}while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		if(istruzione.isBlank()) return false;
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai")) {
			this.vai(comandoDaEseguire.getParametro());
		}
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("posa")) {
			this.posa(comandoDaEseguire.getParametro());
		}
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else
			io.mostraMessaggio("Comando sconosciuto");
		if(this.partita.isFinita()) {
			if (this.partita.vinta())
				io.mostraMessaggio("\nHai vinto!");
			else
				io.mostraMessaggio("\nHai finito i CFU\nGame Over");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		StringBuilder elenco = new StringBuilder();
		for(int i=0; i< elencoComandi.length; i++)
			elenco.append(elencoComandi[i]+" ");
		io.mostraMessaggio(elenco.toString());
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			io.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		//prossimaStanza = this.partita.getGiocatore().getStanzaCorrente().getStanzaAdiacente(direzione);
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			io.mostraMessaggio("Direzione inesistente");
		else {
			//this.partita.getGiocatore().setStanzaCorrente(prossimaStanza);
			this.partita.setStanzaCorrente(prossimaStanza);
			this.partita.getGiocatore().riduciCfu();
		}
		//io.mostraMessaggio(partita.getGiocatore().getStanzaCorrente().getDescrizione());
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	/**
	 * Cerca nella stanza un oggetto con il nome corrispondente 
	 * al parametro e nel caso esista questo viene rimosso dalla 
	 * stanza corrente e inserito nella borsa del giocatore
	 * @param nomeAttrezzo
	 */
	private void prendi(String nomeAttrezzo) {
		//if(this.partita.getGiocatore().getStanzaCorrente().getNumeroAttrezzi()==0) { 
		if(this.partita.getStanzaCorrente().getNumeroAttrezzi()==0) { 
			io.mostraMessaggio("nessun attrezzo presente nella stanza");
			return;
		}
		else if(nomeAttrezzo == null) 
			io.mostraMessaggio("spceificare l'attrezzo da prendere");
		//else {Attrezzo a = this.partita.getGiocatore().getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		else {
			Attrezzo a = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			if(a!=null) {
				this.partita.getGiocatore().getBorsa().addAttrezzo(a);
				//this.partita.getGiocatore().getStanzaCorrente().removeAttrezzo(a);
				//io.mostraMessaggio(nomeAttrezzo+" preso da "+this.partita.getGiocatore().getStanzaCorrente().getNome()+" e messo in borsa");
				this.partita.getStanzaCorrente().removeAttrezzo(a);
				io.mostraMessaggio(nomeAttrezzo+" preso da "+this.partita.getStanzaCorrente().getNome()+" e messo in borsa");
			}
			else io.mostraMessaggio(nomeAttrezzo+" non presente nella stanza");
		}
	}

	/**
	 * Cerca nella borsa un oggetto con il nome corrispondente al 
	 * parametro e in caso esista questo viene posato nella stanza corrente
	 * @param nomeAttrezzo
	 */
	private void posa(String nomeAttrezzo) {
		if(nomeAttrezzo==null) {
			io.mostraMessaggio("specificare l'attrezzo da posare");
			return;
		}
		if(this.partita.getGiocatore().getBorsa().isEmpty()) {
			io.mostraMessaggio("nessun attrezzo nella borsa");
			return;
		}
		Attrezzo a = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		if(a!=null) {
			//this.partita.getGiocatore().getStanzaCorrente().addAttrezzo(a);
			this.partita.getStanzaCorrente().addAttrezzo(a);
			this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
			//io.mostraMessaggio(nomeAttrezzo+" rimosso dalla borsa e posato in "+this.partita.getGiocatore().getStanzaCorrente().getNome());
			io.mostraMessaggio(nomeAttrezzo+" rimosso dalla borsa e posato in "+this.partita.getStanzaCorrente().getNome());
		}
		else io.mostraMessaggio(nomeAttrezzo + " non presente in borsa");
	}

	public static void main(String[] argc) {
		IOConsole io = new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}
}