package es.uniovi.asw.passer.excel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import es.uniovi.asw.passer.Parser;

public class OfficeAntiguo implements Parser {

	@Override
	public List<String> loadCenso(String rute) throws Exception {
		
		List<String> datos = new ArrayList<String>();
		
		InputStream input = new FileInputStream(rute);
		HSSFWorkbook wb = new HSSFWorkbook(input);
		
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row;
		HSSFCell cell;
		
		Iterator rows = sheet.iterator();
		
		while(rows.hasNext()){
			
			row = (HSSFRow) rows.next();
			Iterator cells = row.iterator();
			
			String text_row = "";
			
			while(cells.hasNext()){
				cell = (HSSFCell) cells.next();
				
				text_row += "\t" + cell + "\t";
			}
			
			text_row += "\n";
			datos.add(text_row);
			
		}

		return datos;
	}

}
