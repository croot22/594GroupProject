package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

import edu.upenn.cit594.data.ZipCode;
import edu.upenn.cit594.logging.Logging;


public class ReadParkingCSV {

	public String parkingFileName;

	public ReadParkingCSV(String parkingfilename) {
		this.parkingFileName = parkingfilename;
	}

	/*
	 * Read text files
	 */


	public TreeMap<Integer, Integer> readCsvFile() {
		File parkingList = new File(parkingFileName);
		TreeMap<Integer, Integer> fines = new TreeMap<Integer, Integer>();
		Scanner scanner;
		String nextLine;


		try {
			scanner = new Scanner(parkingList);

			/*
			 * If text file does not exist or can not be read
			 */

			if ((parkingList.canRead()) && (parkingList.exists()) == false) {
				System.out.println("Error text file does not exist or can not be read");
				System.exit(0);
			}

			Logging logger = Logging.getInstance();
			String currentTime = logger.getCurrentTime();
			String logMessage = currentTime +" "+ parkingFileName;
			logger.log(logMessage);

			while(scanner.hasNextLine()) {   //populates the array
				nextLine = scanner.nextLine();  //scan through each line
				String dataValue[] = nextLine.split(",");
				if (dataValue.length == 7) {
					int fine = Integer.parseInt(dataValue[1]);
					int zipCode = Integer.parseInt(dataValue[6]);
					ZipCode zipCodeData = new ZipCode();
					//checking static map to see if zipcode is already memoized
					if(ZipCode.zipCodeMap.containsKey(zipCode)) {
						zipCodeData = ZipCode.zipCodeMap.get(zipCode);
						
					}
					else {
						 zipCodeData.zipCode = zipCode;
						 
					}
					
					zipCodeData.fines.add(fine);
					ZipCode.zipCodeMap.put(zipCode, zipCodeData);
					
					if (fines.containsKey(zipCode)) {      //@Cayde should the sum totaling be moved to processor?  
						int totalfine = fines.get(zipCode) + fine;
						fines.put(zipCode, totalfine);
					}
					else {
						fines.put(zipCode, fine);
					}			
				}    
			}	
			scanner.close();

		} catch (FileNotFoundException e) {
			return fines;
		}

		return fines;
	}

}
