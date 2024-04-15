package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando{

	@Override
	public void esegui(Partita partita) {
		System.out.println(partita.getStanzaCorrente().getDescrizione());
		System.out.println("cfu correnti: "+partita.getGiocatore().getCfu());
	}

	@Override
	public void setParametro(String parametro) {
	}

}
