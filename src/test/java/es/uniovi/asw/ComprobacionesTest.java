package es.uniovi.asw;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uniovi.asw.logica.Votante;
import es.uniovi.asw.passer.impl.HashedGenerator;
import es.uniovi.asw.util.Comprobaciones;

public class ComprobacionesTest {

	@Test
	public void votanteSinNombre() {
		Votante v = new Votante("", "pepe@gmail.com","71342546S","55");
		v.setContraseña(new HashedGenerator().generar(v));
		
		assertTrue(v.getNombre().equals(""));
		assertEquals(false, Comprobaciones.isVotanteCorreto(v));
	}
	
	@Test
	public void votanteSinCodigoColegio() {
		Votante v = new Votante("pepe", "pepe@gmail.com","71342546S","");
		v.setContraseña(new HashedGenerator().generar(v));
		
		assertTrue(v.getCodigoColegio().equals(""));
		assertEquals(false, Comprobaciones.isVotanteCorreto(v));
	}
	
	@Test
	public void votanteSinContraseña() {
		Votante v = new Votante("pepe", "pepe@gmail.com","71342546S","55");
		
		assertTrue(v.getContraseña() == null);
		assertEquals(false, Comprobaciones.isVotanteCorreto(v));
	}
	
	@Test
	public void votanteSinMail() {
		Votante v = new Votante("pepe", "", "71342546S","55");
		v.setContraseña(new HashedGenerator().generar(v));
		
		assertTrue(v.getMail().equals(""));
		assertEquals(false, Comprobaciones.isVotanteCorreto(v));
	}
	
	@Test
	public void votanteSinNIF() {
		Votante v = new Votante("pepe", "pepe@gmail.com","","55");
		v.setContraseña(new HashedGenerator().generar(v));
		
		assertTrue(v.getNif().equals(""));
		assertEquals(false, Comprobaciones.isVotanteCorreto(v));
	}
	
	@Test
	public void votanteConNIFIncorrecto() {
		Votante v = new Votante("pepe", "pepe@gmail.com","734246S","55");
		v.setContraseña(new HashedGenerator().generar(v));
		
		assertEquals(false, Comprobaciones.isVotanteCorreto(v));
	}
	
	@Test
	public void votanteCorrecto() {
		Votante v = new Votante("pepe", "pepe@gmail.com","7342546S","55");
		v.setContraseña(new HashedGenerator().generar(v));
		
		assertEquals(false, Comprobaciones.isVotanteCorreto(v));
	}

}
