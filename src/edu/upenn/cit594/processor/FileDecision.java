package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.TreeMap;

import edu.upenn.cit594.datamanagement.ReadParkingCSV;
import edu.upenn.cit594.datamanagement.ReadParkingJson;

public class FileDecision {

	public TreeMap<Integer, Integer> fileDecision (String fileType, String parkingFileName) {
		
		/*
		 * Read the appropriate parking file based on file type
		 */
		
		TreeMap<Integer, Integer> fines = new TreeMap<Integer, Integer>(); 
		if (fileType.equals("csv")) {
			ReadParkingCSV readParkingFile = new ReadParkingCSV(parkingFileName);
			fines = readParkingFile.readCsvFile();
		}
		else if (fileType.equals("json")) {
			ReadParkingJson readParkingFile = new ReadParkingJson(parkingFileName);
			fines = readParkingFile.readJsonFile();
		}
		
		/*
		 * If neither text or JSON will terminate
		 */	
		
		else {
			System.out.println("Error, not text or json file");
			System.exit(0);
		}
		return fines;
	}
	
}
