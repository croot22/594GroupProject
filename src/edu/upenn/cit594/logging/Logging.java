package edu.upenn.cit594.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import edu.upenn.cit594.main.Main;
import edu.upenn.cit594.processor.ArgumentsProcessor;

public class Logging {



	private PrintWriter out;

	/*
	 * 1. Private Constructor
	 */

	private Logging(String fileName) {


		try {
			
			File logFile = new File(fileName);
			if ( logFile.exists() && !logFile.isDirectory() ) {
			    this.out = new PrintWriter(new FileOutputStream(new File(fileName), true));
			}
			else {
			    this.out = new PrintWriter(logFile);
			}
			

			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	} 

	/*
	 * 2. Singleton instance with parameter of file name in main
	 */

	private static Logging instance = new Logging(Main.logFileName);

	/*
	 * 3. Singleton accessor method
	 */

	public static Logging getInstance() {
		return instance;
	}

	/*
	 * Helper method to get current time as string
	 */
	public String getCurrentTime() {
		Long time = System.currentTimeMillis();
		String currentTimeAsString = time.toString();
		return currentTimeAsString;
	}
	/*
	 * Method for writing to logfile
	 */

	public void log(String msg) {
		out.println(msg);
		out.flush();
	}
}

