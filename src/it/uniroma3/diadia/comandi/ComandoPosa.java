package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando{
	private String nomeAttrezzo;
	private IO io;

	@Override
	public void esegui(Partita partita) {
		if(nomeAttrezzo==null) {
			this.io.mostraMessaggio("specificare l'attrezzo da posare");
			return;
		}
		if(partita.getGiocatore().getBorsa().isEmpty()) {
			this.io.mostraMessaggio("nessun attrezzo nella borsa");
			return;
		}
		Attrezzo a = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		if(a!=null) {
			partita.getStanzaCorrente().addAttrezzo(a);
			partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
			this.io.mostraMessaggio(nomeAttrezzo+" rimosso dalla borsa e posato in "+partita.getStanzaCorrente().getNome());
		}
		else this.io.mostraMessaggio(nomeAttrezzo + " non presente in borsa");
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
	
	@Override
	public void setIo(IO io) {
		this.io = io;
	}

	@Override
	public String getNome() {
		return "posa";
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

}
