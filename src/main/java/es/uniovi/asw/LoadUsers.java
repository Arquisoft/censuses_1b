package es.uniovi.asw;

import es.uniovi.asw.logica.Votante;
import es.uniovi.asw.passer.Parser;
import es.uniovi.asw.passer.excel.OfficeModerno;

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
			
			Parser parser = new OfficeModerno();
			
			for(String row : parser.loadCenso("./censo.xlsx")){
				System.out.println(row);
				
			}
			Votante v= new Votante("prueba", "preuba@prueba.es", "aaa","333333");
			System.out.println(v.getPass());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
