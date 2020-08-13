package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import edu.upenn.cit594.data.OverallData;
import edu.upenn.cit594.data.ZipCodeData;
import edu.upenn.cit594.logging.Logging;


public class ReadParkingCSV {

	public String parkingFileName;

	public ReadParkingCSV(String parkingfilename) {
		this.parkingFileName = parkingfilename;
	}

	/*
	 * Read text files
	 */


	public void readCsvFile() {
		File parkingList = new File(parkingFileName);
		Scanner scanner;
		String nextLine;


		try {
			scanner = new Scanner(parkingList);

			/*
			 * If text file does not exist or can not be read
			 */



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
					ZipCodeData zipCodeData = new ZipCodeData();
					//checking static map to see if zipcode is already memoized
					if(OverallData.zipCodeMap.containsKey(zipCode)) {
						zipCodeData = OverallData.zipCodeMap.get(zipCode);
						
					}
					else {
						 zipCodeData.zipCode = zipCode;
						 
					}
					
					zipCodeData.fines.add(fine);
					OverallData.zipCodeMap.put(zipCode, zipCodeData);
							
				}    
			}	
			scanner.close();

		} catch (FileNotFoundException e) {
		System.out.println("The selected file could not be found.");
		
		}

	}

}
