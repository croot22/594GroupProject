package edu.upenn.cit594.main;

import java.io.IOException;

import edu.upenn.cit594.logging.Logging;
import edu.upenn.cit594.ui.UserInterface;

public class Main {

	public static String logFileName;
	public static String fileType;
	public static String parkingFileName;
	public static String propertiesFileName;	
	public static String populationFileName;
	
	public static void main(String args[]) throws IOException {

		if (args.length != 5) {
			System.out.println("Error incorrect number of arguments");
			System.exit(0);
		}
		
		/*
		 * Store arguments in static variables
		 */
		
		Main.fileType = args[0];
		Main.parkingFileName = args[1];
		Main.propertiesFileName = args[2];	
		Main.populationFileName = args[3];
		Main.logFileName = args[4];
		
		/*
		 * log current time at start of program
		 */
		Logging logger = Logging.getInstance();
		String currentTime = logger.getCurrentTime();
		logger.log(currentTime);
		
		
		/*
		 * Start cycle of user options
		 */
		
		UserInterface ui = new UserInterface();
		ui.start();

	}
}
