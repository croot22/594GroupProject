package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;


public class ReadParkingCSV {

	public String parkingfilename;
	
	public ReadParkingCSV(String parkingfilename) {
		this.parkingfilename = parkingfilename;
	}

	/*
	 * Read text files
	 */
	
	
	public TreeMap<Integer, Integer> readCsvFile() {
		File parkinglist = new File(parkingfilename);
		TreeMap<Integer, Integer> fines = new TreeMap<Integer, Integer>();
		Scanner i;
		String nextline;

		
		try {
			i = new Scanner(parkinglist);
			
		} catch (FileNotFoundException e) {
			return fines;
			}   
		
		/*
		 * If text file does not exist or can not be read
		 */
		
		if ((parkinglist.canRead()) && (parkinglist.exists()) == false) {
			System.out.println("Error text file does not exist or can not be read");
			System.exit(0);
		}
		
		
		while(i.hasNextLine()) {   //populates the array
			nextline = i.nextLine();  //scan through each line
			String datavalue[] = nextline.split(",");
			if (datavalue.length == 7) {
				int fine = Integer.parseInt(datavalue[1]);
				int zipcode = Integer.parseInt(datavalue[6]);
				if (fines.containsKey(zipcode)) {      //@Cayde should the sum totaling be moved to processor?  
					int totalfine = fines.get(zipcode) + fine;
					fines.put(zipcode, totalfine);
				}
				else {
					fines.put(zipcode, fine);
				}			
			}    
		}	
		i.close();
		return fines;
	}
	
}
