package es.uniovi.asw.util;

import es.uniovi.asw.logica.Votante;

public class Comprobaciones {
	
	public static boolean isVotanteCorreto(Votante votante){
		return (!isStringVacio(votante.getNombre()) &&
				!isStringVacio(votante.getCodigoColegio()) &&
				!isStringVacio(votante.getContraseña()) &&
				!isStringVacio(votante.getMail()) &&
				!isStringVacio(votante.getNif())) && 
				(votante.getNif().length() == 9);
	}
	
	private static boolean isStringVacio(String texto){
		return texto.trim().length() == 0;
	}

}
