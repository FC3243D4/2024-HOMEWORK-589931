package it.uniroma3.diadia.ambienti;

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

    /**
     * Crea tutte le stanze e le porte di collegamento
     */
	public Labirinto LabirintoDiaDia() {
		return new LabirintoBuilder()
				.addStanzaIniziale("Atrio").addAttrezzo("osso", 1)
				.addStanzaVincente("Biblioteca")
				.addStanza("Aula N11")
				.addStanza("Aula N10").addAttrezzo("lanterna", 3)
				.addStanza("Laboratorio Campus")
				
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.addAdiacenza("Atrio", "Aula N11", "est")
				.addAdiacenza("Atrio", "Aula N10", "sud")
				.addAdiacenza("Atrio", "Laboratorio Campus", "ovest")
				.addAdiacenza("Laboratorio Campus", "Aula N11", "ovest")
				
				.getLabirinto();
	}
}
