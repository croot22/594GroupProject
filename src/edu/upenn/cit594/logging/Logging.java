package edu.upenn.cit594.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Logging {
	
	/*
	 * Set up singleton design of logfile
	 */
	
	public static String filename;
	private PrintWriter out;
	
	/*
	 * 1. Private Constructor
	 */
	
	private Logging(String filename) {
		try {
			out = new PrintWriter(new File(filename));
		} catch (Exception e) { }
	} 
	
	/*
	 * 2. Singleton instance with parameter of file name in main
	 */
	
	private static Logging instance = new Logging(filename);
	
	/*
	 * 3. Singleton accessor method
	 */
	
	public static Logging getInstance() {
		return instance;
	}
	
	/*
	 * Method for writing to logfile
	 */
	
	public void logFile() throws IOException {
		FileWriter fileWriter = new FileWriter(filename);
	    PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.close();
	}
}

