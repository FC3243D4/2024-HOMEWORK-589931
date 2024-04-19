package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza{
	private String direzioneBloccata;
	private String attrezzoSbloccante;

	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata=direzioneBloccata;
		this.attrezzoSbloccante=attrezzoSbloccante;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		Stanza stanza = null;
		for(int i=0; i<super.getDirezioni().length; i++)
			if (super.getDirezioni()[i].equals(direzioneBloccata)) {
				if(super.hasAttrezzo(attrezzoSbloccante))
					stanza=super.getStanzaAdiacente(direzione);
				else stanza=this;
			}
			else if (super.getDirezioni()[i].equals(direzione))
				stanza = super.getStanzaAdiacente(direzione);
		return stanza;
	}
	
	@Override
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(super.getNome());
		risultato.append("\nUscite:");
		for (String direzione : super.getDirezioni())
			if (direzione!=null) {
				risultato.append(" " + direzione);
				if(direzione.equals(this.direzioneBloccata))
					risultato.append(" (bloccata)");
			}
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : super.getAttrezzi()) {
			if(attrezzo!=null)
				risultato.append(attrezzo.toString()+" ");
		}
		return risultato.toString();
	}

}
