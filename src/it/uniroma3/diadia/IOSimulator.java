package it.uniroma3.diadia;

public class IOSimulator implements IO{
	private String[] output;
	private int contatoreOutput;
	private String[] input;
	private int contatoreInput;
	
	public IOSimulator() {
		this.output=new String[100];
		this.contatoreInput=0;
		this.contatoreOutput=0;
	}
	
	public void setInput(String[] comandi) {
		this.input=comandi;
	}
	
	public String[] getOutput() {
		return this.output;
	}
	
	public int getContatoreOutput() {
		return this.contatoreOutput-1;
	}
	

	@Override
	public void mostraMessaggio(String messaggio) {
		this.output[contatoreOutput]=messaggio;
		this.contatoreOutput++;
	}

	@Override
	public String leggiRiga() {
		String comando=this.input[this.contatoreInput];
		this.contatoreInput++;
		return comando;
	}

}
