package es.uniovi.asw.passer.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import es.uniovi.asw.logica.Votante;
import es.uniovi.asw.passer.GeneradorCartas;
import es.uniovi.asw.passer.GeneradorContraseñas;
import es.uniovi.asw.passer.ReadCensus;
import es.uniovi.asw.reports.ReportWriter;

public class ReadCensusExcel implements ReadCensus {
	
	InputStream archivo;
	ReportWriter rW = new ReportWriter();
	GeneradorCartas generadorCartas;
	
	public ReadCensusExcel(String ruta) throws IOException {
		this(ruta, new GeneradorCartasTXT());
	}

	public ReadCensusExcel(String ruta, GeneradorCartas generadorCartas) throws IOException {
		try {
			archivo = new FileInputStream(ruta);
			this.generadorCartas = generadorCartas;
		} catch (FileNotFoundException e) {
			rW.WriteReport(ruta, "no se encuentra el archivo");
		}
	}

	@Override
	public List<Votante> loadCenso() throws Exception {
		List<Votante> votantes = new ArrayList<Votante>(); 
		
		if(archivo != null){
			
			Workbook wb = WorkbookFactory.create(archivo);
			
			org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			
			//Quitamos la primera fila que contiene los nombres de las columnas
			if(rows.hasNext()) rows.next(); 
			
			//Procesamos el documento
			while(rows.hasNext()){
				
				Row row = (Row) rows.next();
				Iterator<Cell> cells = row.cellIterator();
				List<String> datosVotante = new ArrayList<String>();
				
				while(cells.hasNext()){
					Cell cell = (Cell) cells.next();
					datosVotante.add(cell.toString());
				}
				
				if(datosVotante.size() == 4){
					Votante v=new Votante(datosVotante.get(0),
							datosVotante.get(1),
							datosVotante.get(2),
							datosVotante.get(3));
					
					GeneradorContraseñas gp= new HashedGenerator();
					
					v.setContraseña(gp.generar(v));
					votantes.add(v);
					generadorCartas.generarCarta(v);
				}
				
			}
			
			return votantes;
			
		}
		else{
			
			return null;
			
		}
		
		
	}

}
