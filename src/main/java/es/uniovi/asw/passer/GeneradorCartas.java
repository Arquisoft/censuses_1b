package es.uniovi.asw.passer;

import java.io.FileNotFoundException;

import es.uniovi.asw.logica.Votante;

public interface GeneradorCartas {

	void generarCarta(Votante v) throws FileNotFoundException;

}
