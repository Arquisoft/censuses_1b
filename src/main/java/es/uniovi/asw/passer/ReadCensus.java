package es.uniovi.asw.passer;

import java.util.List;

import es.uniovi.asw.logica.Votante;

public interface ReadCensus {

	public List<Votante> loadCenso(String rute) throws Exception;
	
}
