package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando{
	private IO io;

	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("Comando Sconosciuto");
		
	}

	@Override
	public void setParametro(String parametro) {
	}
	
	@Override
	public void setIo(IO io) {
		this.io = io;
	}

	@Override
	public String getNome() {
		return "comando non valido";
	}

	@Override
	public String getParametro() {
		return null;
	}

}
