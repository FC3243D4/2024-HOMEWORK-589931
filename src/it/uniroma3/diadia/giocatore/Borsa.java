package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}

	/**
	 * Aggiunge un attrezzo alla borsa dopo aver controllato che 
	 * l'aggiunta non faccia superare il peso massimo disponibile
	 * @param attrezzo
	 * @return true in caso di aggiunta false altrimenti
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi==10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}

	public int getPesoMax() {
		return pesoMax;
	}

	/**
	 * Cerca nella borsa l'attrezzo con il nome passato come 
	 * parametro, in caso lo trovi lo restituisce in uscita
	 * @param nomeAttrezzo
	 * @return l'attrezzo cercato o null in caso non venga trovato
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			if(attrezzi[i]!=null)
				if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
					a = attrezzi[i];

		return a;
	}

	public int getPeso() {
		int peso = 0;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();

		return peso;
	}

	/*+
	 * controlla se la borsa sia vuota
	 */
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}

	/**
	 * Controlla se all'interno della borsa esista 
	 * un attrezzo con il nome passato per parametro
	 * @param nomeAttrezzo
	 * @return true se esiste false altrimenti
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}

	/**
	 * cerca l'attrezzo dal nome e in caso sia presente lo rimuove dalla borsa ritornandolo
	 * @param nomeAttrezzo
	 * @return l'attrezzo cercato o null in caso non sia presente
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		boolean trovato=false;
		int i=0;
		if(nomeAttrezzo!=null) {
			for(Attrezzo b: this.attrezzi) {
				if(b!=null && trovato==false)
					if(b.getNome().equals(nomeAttrezzo)) {
						trovato=true;
						a=this.attrezzi[i];
						this.attrezzi[i]=null;
						this.numeroAttrezzi--;
					}
				i++;

			}
		}
		return a;
	}

	/**
	 * Restituisce una stringa con all'interno una descrizione del contenuto della borsa
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}