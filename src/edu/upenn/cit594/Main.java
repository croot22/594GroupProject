package edu.upenn.cit594;

import java.io.IOException;

import edu.upenn.cit594.logging.Logging;
import edu.upenn.cit594.ui.UserOptions;

public class Main {

	public static void main(String args[]) throws IOException {

		if (args.length != 5) {
			System.out.println("Error incorrect number of arguments");
			System.exit(0);
		}
		
		/*
		 * Store arguments
		 */
		
		String filetype = args[0];
		String parkingfilename = args[1];
		String propertiesfilename = args[2];	
		String populationfilename = args[3];
		String logfile = args[4];
		
		/*
		 * Start cycle of user options
		 */
		
		
		/*
		 * @Cayde consider whether best style
		 * 
		 */
		
		UserOptions uo = new UserOptions();
		int selected_option = uo.userOptions();
		uo.followOption(selected_option, filetype, parkingfilename, propertiesfilename, populationfilename);	
		
		/*
		 * @Cayde need singleton design pattern for logging
		 * need timestamps
		 */
		
		Logging l = Logging.getInstance();
	}
}
