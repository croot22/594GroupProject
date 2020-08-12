package edu.upenn.cit594.datamanagement;

import edu.upenn.cit594.data.OverallData;

public class FileDecision {

	public void fileDecision (String fileType, String parkingFileName) {
		
		/*
		 * Read the appropriate parking file based on file type
		 */
		
		if (fileType.equals("csv")) {
			ReadParkingCSV readParkingFile = new ReadParkingCSV(parkingFileName);
			readParkingFile.readCsvFile();
		}
		else if (fileType.equals("json")) {
			ReadParkingJson readParkingFile = new ReadParkingJson(parkingFileName);
			readParkingFile.readJsonFile();
		}
		
		/*
		 * If neither text nor JSON, will terminate
		 */	
		
		else {
			System.out.println("Error, not text or json file");
			System.exit(0);
		}
		OverallData.finesStored = true;
	}
	
}
