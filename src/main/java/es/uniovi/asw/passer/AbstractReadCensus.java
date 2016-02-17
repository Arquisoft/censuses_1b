package es.uniovi.asw.passer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.DocumentException;

import es.uniovi.asw.logica.Votante;
import es.uniovi.asw.passer.impl.GeneradorCartasTXT;
import es.uniovi.asw.passer.impl.HashedGenerator;
import es.uniovi.asw.reports.ReportWriter;
import es.uniovi.asw.util.Comprobaciones;

public abstract class AbstractReadCensus implements ReadCensus{
	
	protected String ruta;
	protected GeneradorCartas generadorCartas;
	protected GeneradorContraseñas generadorContraseñas = new HashedGenerator();
	protected ReportWriter rW = new ReportWriter();

	public AbstractReadCensus(String ruta) {

		this(ruta, new GeneradorCartasTXT());

	}
	
	public AbstractReadCensus(String ruta, GeneradorCartas generadorCartas) {
		
		this.ruta = ruta;
		this.generadorCartas = generadorCartas;
		
	}
	
	protected abstract List<Votante> parserArchivo(File archivo) throws IOException;
	
	public List<Votante> loadCenso() throws DocumentException, IOException{
		
		File archivo = new File(ruta);
		
		if(archivo.exists()){
			
			List<Votante> votantesLeidos = parserArchivo(archivo);
			List<Votante> votantes = new ArrayList<Votante>();
			for(Votante votante : votantesLeidos){
				votante.setContraseña(generadorContraseñas.generar(votante));
				if(Comprobaciones.isVotanteCorreto(votante)){
					generadorCartas.generarCarta(votante);
					votantes.add(votante);
				}
			}
			return votantes;
			
		}
		else{
			rW.WriteReport(ruta, "no se encuentra el archivo");
			return null;
		}
		
		
	}

}
