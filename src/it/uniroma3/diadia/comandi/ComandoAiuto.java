package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando{

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "interagisci"};
	
	public ComandoAiuto(IO io) {
		super("aiuto", io);
	}

	@Override
	public void esegui(Partita partita) {
		StringBuilder elenco = new StringBuilder();
		for(int i=0; i< elencoComandi.length; i++)
			elenco.append(elencoComandi[i]+"    ");
		this.getIo().mostraMessaggio(elenco.toString());
	}
}
