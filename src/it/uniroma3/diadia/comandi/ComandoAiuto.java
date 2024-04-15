package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando{
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	@Override
	public void esegui(Partita partita) {
		StringBuilder elenco = new StringBuilder();
		for(int i=0; i< elencoComandi.length; i++)
			elenco.append(elencoComandi[i]+"    ");
		System.out.println(elenco.toString());
	}

	@Override
	public void setParametro(String parametro) {
	}
}
