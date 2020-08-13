package edu.upenn.cit594.main;

import edu.upenn.cit594.logging.Logging;
import edu.upenn.cit594.processor.ArgumentsProcessor;
import edu.upenn.cit594.ui.UserInterface;

public class Main {

	public static String fileType;
	public static String parkingFileName;
	public static String propertiesFileName;	
	public static String populationFileName;
	public static String logFileName;
	
	public static void main(String args[]) {

		if (args.length != 5) {
			System.out.println("Error incorrect number of arguments");
			System.exit(0);
		}
		
		/*
		 * Store arguments in static variables
		 */
		
		Main.fileType = args[0].toLowerCase();
		Main.parkingFileName = args[1];
		Main.propertiesFileName = args[2];	
		Main.populationFileName = args[3];
		Main.logFileName = args[4];
		
		ArgumentsProcessor am = new ArgumentsProcessor();
		am.checkValidType(Main.fileType);
		
		if (!am.checkValidFile(Main.parkingFileName) || !am.checkValidFile(Main.propertiesFileName) || 
				!am.checkValidFile(Main.populationFileName)) {
			System.out.println("Error JSON file does not exist or can not be read");
			System.exit(0);
		}
		
		
		/*
		 * log current time at start of program
		 */
		Logging logger = Logging.getInstance();
		String currentTime = logger.getCurrentTime();
		String logMessage = currentTime +" "+ Main.fileType +" "+ Main.parkingFileName +" "+ 
				Main.propertiesFileName + " "+ Main.populationFileName +" "+ Main.logFileName;
		logger.log(logMessage);

		
		/*
		 * Start cycle of user options
		 */
		
		UserInterface ui = new UserInterface();
		ui.start();

	}
}
