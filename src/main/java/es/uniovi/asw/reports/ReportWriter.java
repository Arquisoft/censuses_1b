package es.uniovi.asw.reports;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.io.FileWriter;
import java.util.Calendar;





import es.uniovi.asw.logica.Votante;

public class ReportWriter {
	
	public void WriteReport(Votante v,String ficheroExcelProcedencia, String razon) throws IOException{
		if(v==null ||ficheroExcelProcedencia==null || razon==null){
			throw new IllegalArgumentException("Parametros no pueden ser null");
		}
		if(ficheroExcelProcedencia.isEmpty() || razon.isEmpty()){
			throw new IllegalArgumentException("Informacion sobre la razon del reporte y el origen del fichero necesaria");
		}
		Writer output;
		output = new BufferedWriter(new FileWriter("report.log", true));
		
		Calendar hoy= Calendar.getInstance();
			
		int anyo=hoy.get(Calendar.YEAR);
		int mes = hoy.get(Calendar.MONTH)+1;
		int dia= hoy.get(Calendar.DAY_OF_MONTH);
		
		int hora= hoy.get(Calendar.HOUR);
		int minuto= hoy.get(Calendar.MINUTE);
		int segundo= hoy.get(Calendar.SECOND);
		
		String report="["+(anyo)+"/"+mes+"/"+dia+"::"+hora+":"+minuto+":"+segundo+ "] @ "+ficheroExcelProcedencia+" votante con problemas:"+v.getNif()+" Razon: "+razon+System.getProperty( "line.separator" );
		output.append(report);
		output.close();
	}
	//git arreglate por favor
	public void WriteReport(String ficheroExcelProcedencia,String razon) throws IOException{
		if(ficheroExcelProcedencia==null || razon==null){
			throw new IllegalArgumentException("Parametros no pueden ser null");
		}
		if(ficheroExcelProcedencia.isEmpty() || razon.isEmpty()){
			throw new IllegalArgumentException("Informacion sobre la razon del reporte y el origen del fichero necesaria");
		}
		Writer output;
		output = new BufferedWriter(new FileWriter("report.log", true));
		
		Calendar hoy= Calendar.getInstance();
			
		int anyo=hoy.get(Calendar.YEAR);
		int mes = hoy.get(Calendar.MONTH)+1;
		int dia= hoy.get(Calendar.DAY_OF_MONTH);
		
		int hora= hoy.get(Calendar.HOUR);
		int minuto= hoy.get(Calendar.MINUTE);
		int segundo= hoy.get(Calendar.SECOND);
		
		String report="["+(anyo)+"/"+mes+"/"+dia+"::"+hora+":"+minuto+":"+segundo+ "] @ "+ficheroExcelProcedencia+" Problema debido a: "+razon +System.getProperty( "line.separator" );
		output.append(report);
		output.close();
	}

}
