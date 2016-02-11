package es.uniovi.asw;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

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
		
		CommandLineParser cmd = new DefaultParser();
		
		Options options = new Options();
		Option help = new Option("help", "muestra la ayuda de la aplicación.");
		Option excel = Option.builder("e")
							 .argName("file")
							 .hasArg()
							 .desc("indica que el tipo de documento"
									+ " de entrada es un libro Excel.")
							 .build();
		
		options.addOption(help);
		options.addOption(excel);		
		
		try {
			CommandLine line = cmd.parse(options, args);
			
			if(line.hasOption("help")){
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp( "Censuses", options );
			}
			else if(line.hasOption("e")){
				ReadCensus readExcel = new ReadCensusExcel(line.getOptionValue("e"));
				List<Votante> votantes = readExcel.loadCenso();
				for(Votante votante : votantes){
					System.out.println(votante);
				}
			}
						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
}
