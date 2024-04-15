package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando{
	private String parametro;

	@Override
	public void esegui(Partita partita) {
		if(parametro==null) {
			System.out.println("specificare l'attrezzo da posare");
			return;
		}
		if(partita.getGiocatore().getBorsa().isEmpty()) {
			System.out.println("nessun attrezzo nella borsa");
			return;
		}
		Attrezzo a = partita.getGiocatore().getBorsa().getAttrezzo(parametro);
		if(a!=null) {
			partita.getStanzaCorrente().addAttrezzo(a);
			partita.getGiocatore().getBorsa().removeAttrezzo(parametro);
			System.out.println(parametro+" rimosso dalla borsa e posato in "+partita.getStanzaCorrente().getNome());
		}
		else System.out.println(parametro + " non presente in borsa");
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

}
