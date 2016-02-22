package es.uniovi.asw;

import static org.junit.Assert.*;

import org.junit.*;

import es.uniovi.asw.BD.DBUpdate;
import es.uniovi.asw.logica.Votante;

import java.io.*;

/**
 * Clase para probar los accesos a la base de datos.
 *
 */
public class DBTest {
	
	private static DBUpdate db = new DBUpdate();
	
	@BeforeClass
	public static void beforeClass() {
		db.conectar();
	}

	@Test
	public void testInsertarVotante() {
		Votante votante = new Votante("Nombre", "nombre@mail.com", "12345678X", "qwerty");
		db.insert(votante);
		Votante votanteBD = db.select(votante.getNif());
		assertEquals(votante.getNombre(), votanteBD.getNombre());
		assertEquals(votante.getMail(), votanteBD.getMail());
		assertEquals(votante.getNif(), votanteBD.getNif());
		assertEquals(votante.getCodigoColegio(), votanteBD.getCodigoColegio());
	}
	
	@Test
	public void testInsertarVariosVotantes() {
		Votante votante1 = new Votante("Nombre1", "nombre1@mail.com", "00000000P", "codigo1");
		Votante votante2 = new Votante("Nombre2", "nombre2@mail.com", "99999999C", "codigo2");
		Votante votante3 = new Votante("Nombre3", "nombre3@mail.com", "55555555F", "codigo3");
		db.insert(votante1);
		db.insert(votante2);
		db.insert(votante3);
		assertTrue(db.exists(votante1.getNif()));
		assertTrue(db.exists(votante2.getNif()));
		assertTrue(db.exists(votante3.getNif()));
		assertEquals(3, db.count());
	}
	
	@Test(expected=NullPointerException.class)
	public void testInsertarNull() {
		Votante votante = null;
		db.insert(votante);
	}
	
	@Test
	public void testInsertarDosVotantesConMismoNif() {
		Votante votante = new Votante("Nombre", "nombre@mail.com", "12345678X", "codigo1");
		Votante votante2 = new Votante("Nombre2", "nombre2@mail.com", "12345678X", "codigo2");
		assertEquals(0, db.count());
		db.insert(votante);
		db.insert(votante2);
		assertTrue(leeLog().contains("Ya existe el registro en la base de datos."));
		assertEquals(1, db.count());
		Votante votanteBD = db.select(votante.getNif());
		assertEquals(votante.getNombre(), votanteBD.getNombre());
		assertEquals(votante.getMail(), votanteBD.getMail());
		assertEquals(votante.getNif(), votanteBD.getNif());
		assertEquals(votante.getCodigoColegio(), votanteBD.getCodigoColegio());		
	}
	
	@Test
	public void testInsertarDosVotantesConMismoMail() {
		Votante votante = new Votante("Nombre", "nombre@mail.com", "12345678X", "codigo1");
		Votante votante2 = new Votante("Nombre2", "nombre@mail.com", "00000000P", "codigo2");
		assertEquals(0, db.count());
		db.insert(votante);
		db.insert(votante2);
		assertTrue(leeLog().contains("Ya existe el registro en la base de datos."));
		assertEquals(1, db.count());
		Votante votanteBD = db.select(votante.getNif());
		assertEquals(votante.getNombre(), votanteBD.getNombre());
		assertEquals(votante.getMail(), votanteBD.getMail());
		assertEquals(votante.getNif(), votanteBD.getNif());
		assertEquals(votante.getCodigoColegio(), votanteBD.getCodigoColegio());
	}
	
	@After
	public void doAfter() {
		new File("report.log").delete();
		db.deleteAll();
	}
	
	private String leeLog() {		
 		FileReader f;
 	    String mensajeReport = "";
 		try {
 			f = new FileReader("report.log");
 			BufferedReader b = new BufferedReader(f);
 		    mensajeReport = b.readLine();
 		    b.close();
 		    f.close();
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		return mensajeReport;
 		
 	}
	
	@AfterClass
	public static void afterClass() {
		db.desconectar();
	}

}
