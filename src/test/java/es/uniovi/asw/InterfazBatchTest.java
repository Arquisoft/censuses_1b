package es.uniovi.asw;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class InterfazBatchTest {
	
	@Before
	public void setUp() throws Exception {
		
		//Borro las cartas generadas previamente antes de los test
		File archivos[] = new File(".").listFiles();
		for (File archivo : archivos) {
			
			String nombre = archivo.getName();
		    if ((nombre.endsWith(".txt") || nombre.endsWith(".pdf")) &&
		    		(!nombre.equals("PLAYGROUND.txt") && !nombre.equals("Script_BD.txt"))) {	
		    	archivo.delete();
		    }
		    
		}
		
	}

	@Test
	public void testParserExcelSinEspeficarGeneradorCartas() {
		
		/*
		File prueba = new File("src/test/resources/censo_correcto.xlsx");
		System.out.println(prueba.exists());
		
		String[] args = {"-e " + prueba.getAbsolutePath()};
		LoadUsers.main(args);
		
		File carta = new File("65432123A.txt");
		assertTrue(carta.exists());
		*/
		
	}

}
