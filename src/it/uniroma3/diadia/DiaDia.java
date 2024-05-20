package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

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

	private Partita partita;
	private IO io;

	public DiaDia(IO io) {
		Labirinto labirinto = new Labirinto().LabirintoDiaDia();
		this.partita = new Partita(labirinto);
		this.io = io;
	}
	
	public DiaDia(IO io, Labirinto labirinto) {
		this.partita = new Partita(labirinto);
		this.io =io;
	}

	public void gioca() {
		String istruzione;

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		io.mostraMessaggio("Attualmente ti trovi in "+partita.getStanzaCorrente().getNome());
		do {
			istruzione = io.leggiRiga();
		}while (!processaIstruzione(istruzione,io));
	}   

	private boolean processaIstruzione(String istruzione,IO io) {
		AbstractComando comandoDaEseguire;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica();

		comandoDaEseguire = factory.costruisciComando(istruzione,io);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
			io.mostraMessaggio("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			io.mostraMessaggio("Hai finito i cfu\nGame Over");

		return this.partita.isFinita();
	}
	
	public Partita getPartita() {
		return this.partita;
	}

	public static void main(String[] argc) {
		Scanner scanner = new Scanner(System.in);
		IO io = new IOConsole(scanner);
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}
}