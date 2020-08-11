package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import edu.upenn.cit594.logging.Logging;
import edu.upenn.cit594.main.Main;

public class ReadPopulationFile {

	public HashMap<Integer, Integer> readPopulationFile(String populationFileName) {
		File populationList = new File(populationFileName);
		Scanner scanner;
		HashMap<Integer, Integer> populations = new HashMap<Integer, Integer>();
		try {
			scanner = new Scanner(populationList);
		} catch (FileNotFoundException e) {
			return populations;
			}  
		
		/*
		 * If text file does not exist or can not be read
		 */
		
		if ((populationList.canRead()) && (populationList.exists()) == false) {
			System.out.println("Error text file does not exist or can not be read");
			System.exit(0);
		}
		
		
		Logging logger = Logging.getInstance();
		String currentTime = logger.getCurrentTime();
		String logMessage = currentTime +" "+ populationFileName;
		logger.log(logMessage);
		
		String nextline;
		
		/* 
		 * Create population list
		 */
		
		while(scanner.hasNextLine()) {  
			nextline = scanner.nextLine();  
			String dataValue[] = nextline.split(" ");
			if (dataValue[1] != null) {
				int zipCode = Integer.parseInt(dataValue[0]); 			     
			    int population = Integer.parseInt(dataValue[1]);
				if (populations.containsKey(zipCode)) {							//@Cayde this sum totaling probably should be moved to processor
					int totalpopulation = populations.get(zipCode) + population;
					populations.put(zipCode, totalpopulation);
				}
				else {
					populations.put(zipCode, population);
				}	
			}
		}	
		scanner.close();
		return populations;
	}
	
}
