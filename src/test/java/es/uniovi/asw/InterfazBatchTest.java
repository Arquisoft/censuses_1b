package es.uniovi.asw;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InterfazBatchTest {
	
	@Before @After
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
		new File("report.log").delete();
		
	}

	@Test
	public void testParserExcelSinEspeficarGeneradorCartas() {
		
		LoadUsers.main(new String[]{"-e", "./src/test/resources/censo_correcto.xlsx"});
		
		File carta = new File("65432123A.txt");
		assertTrue(carta.exists());
		
	}
	
	@Test
	public void testParserExcelrGeneradorCartasTXT() {
		
		LoadUsers.main(new String[]{"-e", "./src/test/resources/censo_correcto.xlsx", "-c", "txt"});
		
		File carta = new File("65432123A.txt");
		assertTrue(carta.exists());
		
	}
	
	@Test
	public void testParserExcelrGeneradorCartasPDF() {
		
		LoadUsers.main(new String[]{"-e", "./src/test/resources/censo_correcto.xlsx", "-c", "pdf"});
		
		File carta = new File("65432123A.pdf");
		assertTrue(carta.exists());
		
	}
	
	@Test
	public void testParserExcelSinArchivoCorrecto() {
		
		LoadUsers.main(new String[]{"-e", "./src/tedst/resources/censo_correcto.xlsx"});
		
		File carta = new File("65432123A.txt");
		assertTrue(!carta.exists());
		
		File log = new File("report.log");
		assertTrue(log.exists());
		
	}
	
	@Test
	public void testAyuda(){
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		LoadUsers.main(new String[]{"-help"});
		
		String salida = outContent.toString();
		assertTrue(salida.contains("usage: Censuses"));
		assertTrue(salida.contains("-e"));
		assertTrue(salida.contains("-c"));
		assertTrue(salida.contains("-help"));

	}
	
	@Test
	public void testSinPasarNada(){
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		LoadUsers.main(new String[]{""});
		
		String salida = outContent.toString();
		assertTrue(salida.contains("Utiliza -help para ver la ayuda."));

	}
	
	@Test
	public void testSinOpcionCorrecta(){
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		LoadUsers.main(new String[]{"-p"});
		
		String salida = outContent.toString();
		assertTrue(salida.contains("No se reconoce esa entrada, utliza -help para ver la ayuda."));

	}

}
