package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.ambienti.Stanza;

public class Giocatore {
	static final private int CFU_INIZIALI = 20;
	
	private int cfu;
	private Borsa borsa;
	//private Stanza stanzaCorrente;
	
	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}
	
	public int getCfu() {
		return cfu;
	}
	
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	
	public Borsa getBorsa() {
		return borsa;
	}
	
	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}
	/*
	public Stanza getStanzaCorrente() {
		return stanzaCorrente;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}
*/
	public void riduciCfu() {
		this.cfu--;
	}
	
	public void riduciCfu(int riduzione) {
		if(riduzione<=0) return;
		this.cfu-=riduzione;
	}
}
