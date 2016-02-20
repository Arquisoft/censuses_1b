package es.uniovi.asw;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.logica.Votante;
import es.uniovi.asw.reports.ReportWriter;

public class ReportWritterTest {

	@Before
	public void setUp() throws Exception {
		//limpiar el fichero .log antes de cada test
		Writer output;
		output = new BufferedWriter(new FileWriter("report.log"));
		output.write("");
		output.close();
	}

	
	
	@Test(expected = IllegalArgumentException.class) 
	public void testVotanteNull() throws Exception {
		//comprobar que no se puede pasar info incorrecta
		ReportWriter r = new ReportWriter();
		Votante v=null;
		r.WriteReport(v, "noExiste", "SinRazon");
	}
	@Test(expected = IllegalArgumentException.class) 
	public void testRazonVacia() throws Exception {
		//comprobar que no se puede pasar info incorrecta
		ReportWriter r = new ReportWriter();
		Votante v=new Votante("NombreFalso", "MailFalso", "dniFalso", "CodigoFalso");
		r.WriteReport(v, "noExiste", "");
	}
	@Test(expected = IllegalArgumentException.class) 
	public void testFicheroVacio() throws Exception {
		//comprobar que no se puede pasar info incorrecta
		ReportWriter r = new ReportWriter();
		Votante v=new Votante("NombreFalso", "MailFalso", "dniFalso", "CodigoFalso");
		r.WriteReport(v, "", "Razon falsa");
	}
	
	
	@Test
	public void testGuardaUnaLineaEnElFichero() throws Exception {
		
		//comprobar que guarda una linea con la informacion correcta
		ReportWriter r = new ReportWriter();
		r.WriteReport("origen", "RazonFalsa");
		
		BufferedReader lector= new BufferedReader(new FileReader("report.log"));
		String linea= lector.readLine();
		assertTrue(linea.contains("RazonFalsa"));
		assertTrue(linea.contains("origen"));
		lector.close();
		
	}
	
	@Test
	public void testGuardaDosLineaEnElFichero() throws Exception {
		//comprobar que guarda varios reportes en lineas diferentes con la informacion correcta
		ReportWriter r = new ReportWriter();
		r.WriteReport("origen", "RazonFalsa");
		r.WriteReport("origenDiferente", "Razon2");
		BufferedReader lector= new BufferedReader(new FileReader("report.log"));
		String linea= lector.readLine();
		assertTrue(linea.contains("RazonFalsa"));
		assertTrue(linea.contains("origen"));
		linea= lector.readLine();
		assertTrue(linea.contains("Razon2"));
		assertTrue(linea.contains("origenDiferente"));
		lector.close();
	}
	
	@Test
	public void testLlamandoALosDosMetodosRepetidasVeces() throws Exception {
		//comprobar que se pueden llamar a ambos metodos 
		ReportWriter r = new ReportWriter();
		r.WriteReport("origen", "RazonFalsa");
		r.WriteReport("origenDiferente", "Razon2");
		Votante v=new Votante("NombreFalso", "MailFalso", "dniFalso", "CodigoFalso");
		r.WriteReport(v, "ahoraConVotante", "3 lineas");
		BufferedReader lector= new BufferedReader(new FileReader("report.log"));
		String linea= lector.readLine();
		assertTrue(linea.contains("RazonFalsa"));
		assertTrue(linea.contains("origen"));
		linea= lector.readLine();
		assertTrue(linea.contains("Razon2"));
		assertTrue(linea.contains("origenDiferente"));
		linea= lector.readLine();
		assertTrue(linea.contains("dniFalso"));
		assertTrue(linea.contains("ahoraConVotante"));
		assertTrue(linea.contains("3 lineas"));
		lector.close();	
		
	}

}
