package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuia extends Stanza{
	private String attrezzoCheFaLuce;

	public StanzaBuia(String nome, String attrezzoCheFaLuce) {
		super(nome);
		this.attrezzoCheFaLuce=attrezzoCheFaLuce;
	}
	
	@Override
	public String toString() {
		if(!super.hasAttrezzo(attrezzoCheFaLuce))
			return "qui c'è un buio pesto";
		StringBuilder risultato = new StringBuilder();
		risultato.append(super.getNome());
		risultato.append("\nUscite:");
		for (String direzione : super.getDirezioni())
			if (direzione!=null)
				risultato.append(" " + direzione);
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : super.getAttrezzi()) {
			if(attrezzo!=null)
				risultato.append(attrezzo.toString()+" ");
		}
		return risultato.toString();
	}

}
