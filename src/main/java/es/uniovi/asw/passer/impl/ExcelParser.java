package es.uniovi.asw.passer.impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import es.uniovi.asw.logica.Votante;
import es.uniovi.asw.passer.Parser;

public class ExcelParser implements Parser {

	@Override
	public List<Votante> loadCenso(String rute) throws Exception {
		List<Votante> votantes = new ArrayList<Votante>(); 
		
		InputStream input = new FileInputStream(rute);
		Workbook wb = WorkbookFactory.create(input);
		
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
				votantes.add(new Votante(datosVotante.get(0),
						datosVotante.get(1),
						datosVotante.get(2),
						datosVotante.get(3)));
			}
			else{
				System.err.println("Algo ha ido mal con estos datos: ");
				for(String dato : datosVotante){
					System.err.print(dato);
				}
				System.err.println();
			}
			
		}
		
		return votantes;
	}

}
