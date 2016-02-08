package es.uniovi.asw;

import es.uniovi.asw.logica.Votante;
import es.uniovi.asw.passer.Parser;
import es.uniovi.asw.passer.impl.ExcelParser;

/**
 * Main application
 * 
 * @author Labra
 *
 */
public class LoadUsers {

	public static void main(String... args) {
		LoadUsers runner = new LoadUsers();
		runner.run(args);
	}

	// TODO
	void run(String... args) {
		try {
			
			Parser parser = new ExcelParser();
			
			for(Votante votante : parser.loadCenso("./censo.xlsx")){
				System.out.println(votante);
				
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
