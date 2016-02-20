package es.uniovi.asw;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.asw.BD.DBUpdate;
import es.uniovi.asw.logica.Votante;

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
		assertTrue(db.exists(votante));
		db.delete(votante);
	}
	
	@Test
	public void testInsertarVariosVotantes() {
		Votante votante1 = new Votante("Nombre1", "nombre1@mail.com", "00000000P", "123xx");
		Votante votante2 = new Votante("Nombre2", "nombre2@mail.com", "99999999C", "ppsxx");
		db.insert(votante1);
		db.insert(votante2);
		assertTrue(db.exists(votante1));
		assertTrue(db.exists(votante2));
		db.delete(votante1);
		db.delete(votante2);
	}
	
	@Test(expected=NullPointerException.class)
	public void testInsertarNull() {
		Votante votante = null;
		db.insert(votante);
	}
	
	@AfterClass
	public static void afterClass() {
		db.desconectar();
	}	

}
