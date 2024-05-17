
package it.uniroma3.diadia.ambienti;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LabirintoBuilderTest {
	private LabirintoBuilder builder;
	/*private Labirinto monolocale;
	private Labirinto bilocale;
	private Labirinto trilocale;*/

	@Before
	public void setUp() throws Exception {
		this.builder = new LabirintoBuilder()
				.addStanzaIniziale("Atrio").addAttrezzo("osso", 1)
				.addStanzaVincente("Biblioteca")
				.addStanzaBloccata("Bloccata", "est", "chiave")
				.addStanzaBuia("Buia", "lanterna")
				.addStanzaMagica("Magica").addAttrezzo("Attrezzo",1).addAttrezzo("Attrezzo",1).addAttrezzo("Attrezzo",1).addAttrezzo("Attrezzo",1)

				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.addAdiacenza("Atrio", "Bloccata", "est")
				.addAdiacenza("Atrio", "Magica", "ovest")
				.addAdiacenza("Atrio", "Buia", "sud");

		/*this.monolocale = new LabirintoBuilder()
				.addStanzaIniziale("salotto") // aggiunge una stanza, che sarà anche iniziale
				.addStanzaVincente("salotto") // specifica quala stanza sarà vincente
				.getLabirinto(); // restituisce il Labirinto così specificato
		
		this.bilocale = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera").addAttrezzo("letto",10) // dove? fa riferimento all’ultima stanza aggiunta: la “camera”
				
				.addAdiacenza("salotto", "camera", "nord") // camera si trova a nord di salotto
				
				.getLabirinto(); // restituisce il Labirinto così specificato
		
		this.trilocale = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanza("cucina").addAttrezzo("pentola",1) // dove? fa riferimento all’ultima stanza aggiunta: la “cucina”
				.addStanzaVincente("camera")
				
				.addAdiacenza("salotto", "cucina", "nord")
				.addAdiacenza("cucina", "camera", "est")
				
				.getLabirinto(); // restituisce il Labirinto così specificato*/
	}

	@Test
	public void testAddStanzaIniziale() {
		assertEquals(builder.getLabirinto().getStanzaIniziale().getNome(),"Atrio");
	}

	@Test
	public void testAddStanzaVincente() {
		assertEquals(builder.getLabirinto().getStanzaVincente().getNome(),"Biblioteca");
	}

	@Test
	public void testAddAdiacenza_AdiacenzaImpostataCorrettamente() {
		assertEquals(builder.getLabirinto().getStanzaIniziale().getStanzaAdiacente("nord").getNome(), "Biblioteca");
	}

	@Test
	public void testAddAdiacenza_AdiacnzaReciprocaImpostataCorrettamente() {
		assertEquals(builder.getLabirinto().getStanzaVincente().getStanzaAdiacente("sud").getNome(), "Atrio");
	}

	@Test
	public void testAddAttrezzo_AttrezzoAggiuntoCorrettamente() {
		assertEquals(builder.getLabirinto().getStanzaIniziale().hasAttrezzo("osso"), true);
		assertEquals(builder.getLabirinto().getStanzaIniziale().getAttrezzo("osso").getPeso(), 1);
	}

	@Test
	public void testAddStanzaBloccata_StanzaBloccataAggiuntaCorrettamente() {
		assertEquals(builder.getLabirinto().getStanzaIniziale().getStanzaAdiacente("est").getDescrizione(),"la porta a " + "est" + " è bloccata\n"
				+ "ti serve l' oggetto " + "chiave" + " nella stanza per aprirla...");
	}

	@Test
	public void testAddStanzaMagica_StanzaMagicaAggiuntaCorrettamente() {
		assertEquals(builder.getLabirinto().getStanzaIniziale().getStanzaAdiacente("ovest").hasAttrezzo("ozzerttA"),true);
	}

	@Test
	public void testAddStanzaBuia_StanzaBuiaAggiuntaCorrettamente() {
		assertEquals(builder.getLabirinto().getStanzaIniziale().getStanzaAdiacente("sud").getDescrizione(),"qui c'è un buio pesto");
	}

}
