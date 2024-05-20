package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando{

	public ComandoFine(IO io) {
		super("fine", io);
	}
	
	@Override
	public void esegui(Partita partita) {
		this.getIo().mostraMessaggio("Grazie di aver giocato!");
		partita.setFinita();
	}

}
