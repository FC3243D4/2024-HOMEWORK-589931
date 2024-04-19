package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando{
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};
	private IO io;

	@Override
	public void esegui(Partita partita) {
		StringBuilder elenco = new StringBuilder();
		for(int i=0; i< elencoComandi.length; i++)
			elenco.append(elencoComandi[i]+"    ");
		this.io.mostraMessaggio(elenco.toString());
	}

	@Override
	public void setParametro(String parametro) {
	}

	@Override
	public void setIo(IO io) {
		this.io = io;
	}

	@Override
	public String getNome() {
		return "aiuto";
	}

	@Override
	public String getParametro() {
		return null;
	}
}
