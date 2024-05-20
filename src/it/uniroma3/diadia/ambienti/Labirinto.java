package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class Labirinto {
	private Stanza stanzaVincente;
	private Stanza stanzaIniziale;
	
	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}
	
	public static LabirintoBuilder newBuilder(){
		return new LabirintoBuilder();
	}

	public static class LabirintoBuilder {

		private Labirinto labirinto;
		private Stanza ultimaStanzaAggiunta;
		private Map<String, Stanza> mappaStanze;

		public LabirintoBuilder() {
			this.labirinto = new Labirinto();
			this.mappaStanze = new HashMap<String, Stanza>();
		}
		
		public Map<String, Stanza> getMappaStanze() {
			return mappaStanze;
		}

		public Labirinto getLabirinto() {
			return this.labirinto;
		}

		public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
			Stanza i = new Stanza(stanzaIniziale);
			this.labirinto.setStanzaIniziale(i);
			this.UltimaStanzaAggiuntaEAggiorna(i);
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
			Stanza s = new Stanza(stanzaVincente);
			this.labirinto.setStanzaVincente(s);
			this.UltimaStanzaAggiuntaEAggiorna(s);
			return this;
		}

		public LabirintoBuilder addStanza(String stanza) {
			Stanza s = new Stanza(stanza);
			this.UltimaStanzaAggiuntaEAggiorna(s);
			return this;
		}	

		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
			if(nomeAttrezzo==null) return this;
			Attrezzo a = new Attrezzo(nomeAttrezzo, peso);
			if(this.ultimaStanzaAggiunta==null)
				return this;
			this.ultimaStanzaAggiunta.addAttrezzo(a);
			return this;
		}
		
		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso, String nomeStanza) {
			if(nomeStanza==null || nomeAttrezzo==null) return this;
			Attrezzo a = new Attrezzo(nomeAttrezzo, peso);
			this.mappaStanze.get(nomeStanza).addAttrezzo(a);
			return this;
		}

		public LabirintoBuilder addAdiacenza(String stanzaCorrente, String stanzaAdiecente, Direzioni direzione) {
			int dir = direzione.ordinal();
			int dirOpposta = 3-dir;
			Stanza c = this.mappaStanze.get(stanzaCorrente);
			Stanza a = this.mappaStanze.get(stanzaAdiecente);
			c.impostaStanzaAdiacente(Direzioni.fromOrdinal(dir), a);
			a.impostaStanzaAdiacente(Direzioni.fromOrdinal(dirOpposta), c);
			return this;
		}
		
		public LabirintoBuilder addStanzaMagica(String nome) {
			Stanza stanza = new StanzaMagica(nome);
			this.UltimaStanzaAggiuntaEAggiorna(stanza);
			return this;
		}
		
		public LabirintoBuilder addStanzaMagica(String nome, int capacita) {
			Stanza stanza = new StanzaMagica(nome,capacita);
			this.UltimaStanzaAggiuntaEAggiorna(stanza);
			return this;
		}
		
		public LabirintoBuilder addStanzaBuia(String nome, String attrezzoPerVedere) {
			Stanza stanza = new StanzaBuia(nome,attrezzoPerVedere);
			this.UltimaStanzaAggiuntaEAggiorna(stanza);
			return this;
		}
		
		public LabirintoBuilder addStanzaBloccata(String nome, Direzioni direzioneBloccata, String attrezzoSbloccante) {
			Stanza stanza = new StanzaBloccata(nome, direzioneBloccata, attrezzoSbloccante);
			this.UltimaStanzaAggiuntaEAggiorna(stanza);
			return this;
		}
		
		public void UltimaStanzaAggiuntaEAggiorna(Stanza stanza) {
			this.ultimaStanzaAggiunta = stanza;
			this.mappaStanze.put(stanza.getNome(), stanza);
		}
		
		public void addPersonaggioAStanza(AbstractPersonaggio personaggio, String nomeStanza) {
			this.mappaStanze.get(nomeStanza).setPersonaggio(personaggio);
		}
	}
}
