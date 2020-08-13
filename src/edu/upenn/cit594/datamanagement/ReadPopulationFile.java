package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.upenn.cit594.data.OverallData;
import edu.upenn.cit594.data.ZipCodeData;
import edu.upenn.cit594.logging.Logging;

public class ReadPopulationFile {

	public void readPopulationFile(String populationFileName) {
		File populationList = new File(populationFileName);
		Scanner scanner;
		
		try {
			scanner = new Scanner(populationList);
			
			
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
				    ZipCodeData zip = new ZipCodeData();
				    if (OverallData.zipCodeMap.containsKey(zipCode)) {
				    	zip = OverallData.zipCodeMap.get(zipCode);
				    }
			    	zip.population = population;
			    	OverallData.zipCodeMap.put(zipCode, zip);
				}
			}	
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File with the given name was not found.");
			System.exit(0);
		}  
		



	}
	
}
