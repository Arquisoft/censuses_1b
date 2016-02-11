package es.uniovi.asw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import es.uniovi.asw.logica.Votante;
import es.uniovi.asw.passer.ReadCensus;
import es.uniovi.asw.passer.impl.ReadCensusExcel;

public class ReadCensusExcelTest {

	@Test
	public void testExcelModernoNumeroVotantes() throws Exception {
		ReadCensus readCensus = new ReadCensusExcel("src/test/resources/censo_correcto.xlsx");
		List<Votante> votantes = readCensus.loadCenso(); 
		
		//Comprobamos número de votantes
		assertEquals(votantes.size(), 3);
	}
	
	@Test
	public void testExcelAntiguoNumeroVotantes() throws Exception {
		ReadCensus readCensus = new ReadCensusExcel("src/test/resources/censo_correcto.xlsx");
		List<Votante> votantes = readCensus.loadCenso(); 
		
		//Comprobamos número de votantes
		assertEquals(votantes.size(), 3);
	}
	
	@Test
	public void testExcelModernoDatosCorrectos() throws Exception {
		ReadCensus readCensus = new ReadCensusExcel("src/test/resources/censo_correcto.xlsx");
		List<Votante> votantes = readCensus.loadCenso(); 
		
		//Comprobamos los datos del votante 1
		Votante votante1 = votantes.get(0);
		assertTrue(votante1.getNombre().equals("Pepe"));
		assertTrue(votante1.getMail().equals("pepe@yomolomucho.es"));
		assertTrue(votante1.getNif().equals("65432123A"));
		assertTrue(votante1.getCodigoColegio().equals("32.0"));
		
		//Comprobamos los datos del votante 2
		Votante votante2 = votantes.get(1);
		assertTrue(votante2.getNombre().equals("Juan"));
		assertTrue(votante2.getMail().equals("juan@gmail.com"));
		assertTrue(votante2.getNif().equals("75643234W"));
		assertTrue(votante2.getCodigoColegio().equals("43.0"));

		//Comprobamos los datos del votante 3
		Votante votante3 = votantes.get(2);
		assertTrue(votante3.getNombre().equals("Sergio"));
		assertTrue(votante3.getMail().equals("sergio@yomolomucho.es"));
		assertTrue(votante3.getNif().equals("12321543P"));
		assertTrue(votante3.getCodigoColegio().equals("54.0"));
	}
	
	@Test
	public void testExcelAntiguoDatosCorrectos() throws Exception {
		ReadCensus readCensus = new ReadCensusExcel("src/test/resources/censo_correcto.xlsx");
		List<Votante> votantes = readCensus.loadCenso(); 
		
		//Comprobamos los datos del votante 1
		Votante votante1 = votantes.get(0);
		assertTrue(votante1.getNombre().equals("Pepe"));
		assertTrue(votante1.getMail().equals("pepe@yomolomucho.es"));
		assertTrue(votante1.getNif().equals("65432123A"));
		assertTrue(votante1.getCodigoColegio().equals("32.0"));

		//Comprobamos los datos del votante 2
		Votante votante2 = votantes.get(1);
		assertTrue(votante2.getNombre().equals("Juan"));
		assertTrue(votante2.getMail().equals("juan@gmail.com"));
		assertTrue(votante2.getNif().equals("75643234W"));
		assertTrue(votante2.getCodigoColegio().equals("43.0"));

		//Comprobamos los datos del votante 3
		Votante votante3 = votantes.get(2);
		assertTrue(votante3.getNombre().equals("Sergio"));
		assertTrue(votante3.getMail().equals("sergio@yomolomucho.es"));
		assertTrue(votante3.getNif().equals("12321543P"));
		assertTrue(votante3.getCodigoColegio().equals("54.0"));
	}
	
	@Test
	public void testExcelVelocidad() throws Exception{
		
		long inicio = System.currentTimeMillis();
		ReadCensus readCensus = new ReadCensusExcel("src/test/resources/censo_velocidad.xlsx");
		readCensus.loadCenso(); 
		long fin = System.currentTimeMillis();
		
		assertTrue(1000 > fin - inicio);
		
	}

}
