package edu.upenn.cit594.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import edu.upenn.cit594.main.Main;

public class Logging {
	
	

	private PrintWriter out;
	
	/*
	 * 1. Private Constructor
	 */
	
	private Logging(String fileName) {
			try {
				out = new PrintWriter(new File(fileName));
			} catch (FileNotFoundException e) {
				
				File file = new File(fileName);
				
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

