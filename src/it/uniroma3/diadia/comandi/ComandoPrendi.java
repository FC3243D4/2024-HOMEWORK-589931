package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando{
	private String parametro;

	@Override
	public void esegui(Partita partita) {
		if(partita.getStanzaCorrente().getNumeroAttrezzi()==0) { 
			System.out.println("nessun attrezzo presente nella stanza");
			return;
		}
		else if(parametro == null) 
			System.out.println("spceificare l'attrezzo da prendere");
		else {
			Attrezzo a = partita.getStanzaCorrente().getAttrezzo(parametro);
			if(a!=null) {
				partita.getGiocatore().getBorsa().addAttrezzo(a);
				partita.getStanzaCorrente().removeAttrezzo(a);
				System.out.println(parametro+" preso da "+partita.getStanzaCorrente().getNome()+" e messo in borsa");
			}
			else System.out.println(parametro+" non presente nella stanza");
		}

	}

	@Override
	public void setParametro(String parametro) {
		this.parametro=parametro;
	}

}
