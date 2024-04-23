package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DiaDiaTest {
	private DiaDia diaDiaTest;
	private IOSimulator simulatore;
	private String[] comandi;
	
	@Before
	public void setUp() {
		this.simulatore = new IOSimulator();
	}

	@Test
	public void testVaiNordVittoria() {
		this.comandi = new String[1];
		this.comandi[0]="vai nord";
		this.simulatore.setInput(comandi);
		this.diaDiaTest=new DiaDia(simulatore);
		this.diaDiaTest.gioca();
		assertEquals("Hai vinto!",this.simulatore.getOutput()[this.simulatore.getContatoreOutput()]);
	}
	
	@Test
	public void testFinireCfuFiniscePartita() {	
		this.comandi = new String[20];
		for(int i=0; i<20; i++)
			this.comandi[i]="vai est";
		this.simulatore.setInput(this.comandi);
		this.diaDiaTest=new DiaDia(simulatore);
		this.diaDiaTest.gioca();
		assertEquals("Hai finito i cfu\nGame Over",this.simulatore.getOutput()[this.simulatore.getContatoreOutput()]);
	}
	
	@Test
	public void testAttrezzoPresoEPosatoInBorsa() {
		this.comandi = new String[2];
		this.comandi[0]="prendi osso";
		this.comandi[1]="fine";
		this.simulatore.setInput(this.comandi);
		this.diaDiaTest=new DiaDia(simulatore);
		this.diaDiaTest.gioca();
		assertTrue(this.diaDiaTest.getPartita().getGiocatore().getBorsa().hasAttrezzo("osso"));
	}

}
