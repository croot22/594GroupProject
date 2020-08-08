package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ReadParkingJson {
	
	public String parkingfilename;
	
	public ReadParkingJson(String parkingfilename) {
		this.parkingfilename = parkingfilename;
	}
	
	public TreeMap<Integer, Integer> readJsonFile() {
		TreeMap<Integer, Integer> fines = new TreeMap<Integer, Integer>();
		File parkinglist = new File(parkingfilename);
		
		/*
		 * If JSON file does not exist or can't be read
		 */
		
		if ((parkinglist.canRead()) && (parkinglist.exists()) == false) {
			System.out.println("Error JSON file does not exist or can not be read");
			System.exit(0);
		}
		
		
		// create a parser
		JSONParser parser = new JSONParser();
		// open the file and get the array of JSON objects
		JSONArray objects = null;
		try {
			objects = (JSONArray)parser.parse(new FileReader(parkingfilename));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// use an iterator to iterate over each element of the array
		Iterator iter = objects.iterator();
		// iterate while there are more objects in array
		while (iter.hasNext()) {
		// get the next JSON object
			JSONObject object = (JSONObject) iter.next();
			String stringzipcode = (String) object.get("zip_code");
			if (stringzipcode.equals("") == false)  {
				long longfine = (long) object.get("fine");
				int fine = (int) longfine;    
				int zipcode = Integer.parseInt(stringzipcode);
				if (fines.containsKey(zipcode)) {       //@Cayde should this be in processor?
					int totalfine = fines.get(zipcode) + fine;
					fines.put(zipcode, totalfine);
				}
				else {
					fines.put(zipcode, fine);
				}	
			} 
		}
		//for (int zipcode : fines.keySet()) {
		//	System.out.println(zipcode + " " + fines.get(zipcode));
		//}
		return fines;
	} 
}
