package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.upenn.cit594.data.OverallData;
import edu.upenn.cit594.data.ZipCodeData;
import edu.upenn.cit594.logging.Logging;


public class ReadParkingJson {
	
	public String parkingFileName;
	
	public ReadParkingJson(String parkingFileName) {
		this.parkingFileName = parkingFileName;
	}
	
	public void readJsonFile() {
		
		File parkingList = new File(parkingFileName);
		
		/*
		 * If JSON file does not exist or can't be read
		 */
		
		if ((parkingList.canRead()) && (parkingList.exists()) == false) {
			System.out.println("Error JSON file does not exist or can not be read");
			System.exit(0);
		}
		
		Logging logger = Logging.getInstance();
		String currentTime = logger.getCurrentTime();
		String logMessage = currentTime +" "+ parkingFileName;
		logger.log(logMessage);
		
		// create a parser
		JSONParser parser = new JSONParser();
		// open the file and get the array of JSON objects
		JSONArray objects = null;
		try {
			objects = (JSONArray)parser.parse(new FileReader(parkingFileName));
		} catch (IOException | ParseException e) {
			
			e.printStackTrace();
		}
		// use an iterator to iterate over each element of the array
		Iterator iter = objects.iterator();
		// iterate while there are more objects in array
		while (iter.hasNext()) {
		// get the next JSON object
			JSONObject object = (JSONObject) iter.next();
			String stringZipCode = (String) object.get("zip_code");
			if (stringZipCode.equals("") == false)  {
				long longFine = (long) object.get("fine");
				int fine = (int) longFine;    
				int zipCode = Integer.parseInt(stringZipCode);
				
				//create zip object if not already created, and add fine
				ZipCodeData zipData = new ZipCodeData();
				if(OverallData.zipCodeMap.containsKey(zipCode)) {
					zipData = OverallData.zipCodeMap.get(zipCode);
				}
				zipData.fines.add(fine);
			} 
		}

	} 
}
