package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando{
	private String direzione;

	/**
	 * esecuzione del comando
	 */
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if(direzione==null) {
			System.out.println("Dove vuoi andare?\nDevi specificare una direzione");
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		if(prossimaStanza==null) {
			System.out.println("Direzione inesistente");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		System.out.println(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().riduciCfu();
	}
	
	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}

}
