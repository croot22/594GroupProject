package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.TreeMap;

import edu.upenn.cit594.datamanagement.ReadParkingCSV;
import edu.upenn.cit594.datamanagement.ReadParkingJson;

public class FileDecision {

	public TreeMap<Integer, Integer> fileDecision (String filetype, String parkingfilename) {
		
		/*
		 * Read the appropriate parking file based on file type
		 */
		
		TreeMap<Integer, Integer> fines = new TreeMap<Integer, Integer>(); 
		if (filetype.equals("csv")) {
			ReadParkingCSV parkingFile = new ReadParkingCSV(parkingfilename);
			fines = parkingFile.readCsvFile();
		}
		else if (filetype.equals("json")) {
			ReadParkingJson parkingFile = new ReadParkingJson(parkingfilename);
			fines = parkingFile.readJsonFile();
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
