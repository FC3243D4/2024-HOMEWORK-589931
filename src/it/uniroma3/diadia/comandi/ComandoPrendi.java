package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando{
	private String parametro;
	private IO io;

	@Override
	public void esegui(Partita partita) {
		if(partita.getStanzaCorrente().getNumeroAttrezzi()==0) { 
			this.io.mostraMessaggio("nessun attrezzo presente nella stanza");
			return;
		}
		else if(parametro == null) 
			this.io.mostraMessaggio("spceificare l'attrezzo da prendere");
		else {
			Attrezzo a = partita.getStanzaCorrente().getAttrezzo(parametro);
			if(a!=null) {
				partita.getGiocatore().getBorsa().addAttrezzo(a);
				partita.getStanzaCorrente().removeAttrezzo(a);
				this.io.mostraMessaggio(parametro+" preso da "+partita.getStanzaCorrente().getNome()+" e messo in borsa");
			}
			else this.io.mostraMessaggio(parametro+" non presente nella stanza");
		}

	}

	@Override
	public void setParametro(String parametro) {
		this.parametro=parametro;
	}
	
	@Override
	public void setIo(IO io) {
		this.io = io;
	}

}
