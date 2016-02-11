package es.uniovi.asw.passer.impl;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import es.uniovi.asw.logica.Votante;
import es.uniovi.asw.passer.GeneradorCartas;

public class GeneradorCartasTXT implements GeneradorCartas {

	@Override
	public void generarCarta(Votante v) throws FileNotFoundException {
		PrintWriter ficherito = new PrintWriter(v.getNif()+".txt");
		ficherito.println("Usuario:"+v.getMail());
		ficherito.println("Contraseña:"+v.getContraseña());
		ficherito.close();

	}


}
