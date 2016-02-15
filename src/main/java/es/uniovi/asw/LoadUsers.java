package es.uniovi.asw;

import java.io.IOException;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.UnrecognizedOptionException;

import es.uniovi.asw.BD.DBUpdate;
import es.uniovi.asw.logica.Votante;
import es.uniovi.asw.passer.ReadCensus;
import es.uniovi.asw.passer.impl.GeneradorCartasPDF;
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
		
		CommandLineParser cmd = new DefaultParser();
		
		Options options = createOptions();
		
		try {
			CommandLine line = cmd.parse(options, args);
			
			if(line.hasOption("help")){
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp( "Censuses", options );
			}
			else if(line.hasOption("e")){
				
				System.out.println("Cargando datos del censo.");
				ReadCensus readExcel = null;
				String ruta = line.getOptionValue("e");
				if(line.hasOption("c")){
					if(line.getOptionValue("c").toLowerCase().equals("pdf")){
						readExcel = new ReadCensusExcel(ruta, new GeneradorCartasPDF());
					}
					else{
						readExcel = new ReadCensusExcel(ruta);
					}
				}
				else{
					readExcel = new ReadCensusExcel(ruta);
				}
				List<Votante> votantes=readExcel.loadCenso();
				if( votantes != null){
					System.out.println("Censo cargado con exito.");
					DBUpdate db= new DBUpdate();
					for(Votante v: votantes){
						db.insert(v);
					}
				}
				else{
					System.out.println("Ha habiado un problema al cargar el censo, consulte el log.");
				}
			}
			else if(line.getOptions().length == 0){
				System.out.println("Utiliza -help para ver la ayuda.");
			}
						
			
		} catch (UnrecognizedOptionException e) {
			System.out.println("No se reconoce esa entrada, utliza -help para ver la ayuda.");
		} catch (IOException e) {
			System.out.println("Se ha producido un error al escribir el log.");
		} catch (Exception e) {
			e.printStackTrace();
		} 

		
		
	}
	
	Options createOptions(){
		
		Options options = new Options();
		Option help = new Option("help", "muestra la ayuda de la aplicación.");
		Option excel = Option.builder("e")
							 .hasArg()
							 .argName("archivo")
							 .desc("indica que el tipo de documento"
									+ " de entrada es un libro Excel.")
							 .build();
		Option tipoCarta = Option.builder("c")
								 .hasArg()
								 .argName("tipo")
								 .desc("selecciona el formato de las cartas"
								 		+ "que se generan")
								 .build();
		
		options.addOption(help);
		options.addOption(excel);	
		options.addOption(tipoCarta);
		
		return options;
	}
}
