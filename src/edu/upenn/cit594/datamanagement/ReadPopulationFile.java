package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ReadPopulationFile {

	public HashMap<Integer, Integer> readPopulationFile(String populationfilename) {
		File populationlist = new File(populationfilename);
		Scanner i;
		HashMap<Integer, Integer> populations = new HashMap<Integer, Integer>();
		try {
			i = new Scanner(populationlist);
		} catch (FileNotFoundException e) {
			return populations;
			}  
		
		/*
		 * If text file does not exist or can not be read
		 */
		
		if ((populationlist.canRead()) && (populationlist.exists()) == false) {
			System.out.println("Error text file does not exist or can not be read");
			System.exit(0);
		}
		
		String nextline;
		
		/* 
		 * Create population list
		 */
		
		while(i.hasNextLine()) {  
			nextline = i.nextLine();  
			String datavalue[] = nextline.split(" ");
			if (datavalue[1] != null) {
				int zipcode = Integer.parseInt(datavalue[0]); 			     
			    int population = Integer.parseInt(datavalue[1]);
				if (populations.containsKey(zipcode)) {							//@Cayde this sum totaling probably should be moved to processor
					int totalpopulation = populations.get(zipcode) + population;
					populations.put(zipcode, totalpopulation);
				}
				else {
					populations.put(zipcode, population);
				}	
			}
		}	
		i.close();
		return populations;
	}
	
}
