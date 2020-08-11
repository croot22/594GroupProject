package edu.upenn.cit594.main;

import java.io.IOException;

import edu.upenn.cit594.logging.Logging;
import edu.upenn.cit594.ui.UserOptions;

public class Main {

	public static String logFileName;
	
	public static void main(String args[]) throws IOException {

		if (args.length != 5) {
			System.out.println("Error incorrect number of arguments");
			System.exit(0);
		}
		
		/*
		 * Store arguments
		 */
		
		String fileType = args[0];
		String parkingFileName = args[1];
		String propertiesFileName = args[2];	
		String populationFileName = args[3];
		Main.logFileName = args[4];
		
		/*
		 * Start cycle of user options
		 */
		
		
		/*
		 * @Cayde consider whether best style
		 * 
		 */
		
		UserOptions uo = new UserOptions();
		int selectedOption = uo.userOptions();
		uo.followOption(selectedOption, fileType, parkingFileName, propertiesFileName, populationFileName);	
		
		/*
		 * @Cayde need singleton design pattern for logging
		 * need timestamps
		 */
		
		Logging logger = Logging.getInstance();
	}
}
