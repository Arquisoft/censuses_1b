package es.uniovi.asw;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

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
		
		try {
			CommandLine line = cmd.parse(options, args);
			
			if(line.hasOption("help")){
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp( "Censuses", options );
			}
			else if(line.hasOption("e")){
				System.out.println("Cargando datos del censo");
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
				readExcel.loadCenso();
				System.out.println("Censo cargado con exito");
			}
						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
}
