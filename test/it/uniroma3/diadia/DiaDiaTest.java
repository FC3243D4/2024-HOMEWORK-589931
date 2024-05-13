package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class DiaDiaTest {
	private DiaDia diaDiaTest;
	private IOSimulator simulatore;
	private ArrayList<String> comandi;
	
	@Before
	public void setUp() {
		this.comandi = new ArrayList<String>();
	}

	@Test
	public void testVaiNordVittoria() {
		this.comandi.add("vai nord");
		this.simulatore = new IOSimulator(comandi);
		this.diaDiaTest=new DiaDia(simulatore);
		this.diaDiaTest.gioca();
		assertEquals("Hai vinto!",this.simulatore.getOutput().get(simulatore.getOutput().size()-1));
	}
	
	@Test
	public void testFinireCfuFiniscePartita() {	
		for(int i=0; i<20; i++) {
			comandi.add("vai ovest");
		}
		this.simulatore = new IOSimulator(this.comandi);
		this.diaDiaTest=new DiaDia(simulatore);
		this.diaDiaTest.gioca();
		assertEquals("Hai finito i cfu\nGame Over",this.simulatore.getOutput().get(this.simulatore.getOutput().size()-1));
	}
	
	@Test
	public void testAttrezzoPresoEPosatoInBorsa() {
		this.comandi.add("prendi osso");
		this.comandi.add("fine");
		this.simulatore = new IOSimulator(this.comandi);
		this.diaDiaTest=new DiaDia(simulatore);
		this.diaDiaTest.gioca();
		assertEquals(true,this.diaDiaTest.getPartita().getGiocatore().getBorsa().hasAttrezzo("osso"));
	}

}
