package es.uniovi.asw;

import es.uniovi.asw.logica.Votante;
import es.uniovi.asw.passer.ReadCensus;
import es.uniovi.asw.passer.impl.ReadCensusExcel;

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
			
			ReadCensus parser = new ReadCensusExcel();
			
			for(Votante votante : parser.loadCenso("./censo.xlsx")){
				System.out.println(votante);
				
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
