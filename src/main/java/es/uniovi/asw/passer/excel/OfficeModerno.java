package es.uniovi.asw.passer.excel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.uniovi.asw.passer.Parser;

public class OfficeModerno implements Parser {

	@Override
	public List<String> loadCenso(String rute) throws Exception {
		
		List<String> datos = new ArrayList<String>();
		
		InputStream input = new FileInputStream(rute);
		XSSFWorkbook wb = new XSSFWorkbook(input);
		
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		
		Iterator rows = sheet.iterator();
		
		while(rows.hasNext()){
			
			row = (XSSFRow) rows.next();
			Iterator cells = row.iterator();
			
			String text_row = "";
			
			while(cells.hasNext()){
				cell = (XSSFCell) cells.next();
				
				text_row += "\t" + cell + "\t";
			}
			
			text_row += "\n";
			datos.add(text_row);
			
		}

		return datos;
	}

}
