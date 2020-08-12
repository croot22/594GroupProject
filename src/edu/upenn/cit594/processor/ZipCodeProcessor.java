package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.OverallData;
import edu.upenn.cit594.data.ZipCodeData;
import edu.upenn.cit594.datamanagement.ReadPopulationFile;

public class ZipCodeProcessor {

	public static int fineTotal(int zipCode) {
		int totalFines = 0;
		if (ZipCodeData.zipCodeMap.containsKey(zipCode)) {  
			ZipCodeData zipMap = ZipCodeData.zipCodeMap.get(zipCode);
			for(int fine: zipMap.fines) {
				totalFines += fine;
			}
		}
		// show memoization of totalFines
		OverallData.totalFinesStored = true;
		return totalFines;
	}
	
	public static int populationTotal() {
		int totalPopulation = 0;
		for(Integer zip: ZipCodeData.zipCodeMap.keySet()) {
			
			ZipCodeData zipMap = ZipCodeData.zipCodeMap.get(zip);
			totalPopulation += zipMap.population;
		}
		return totalPopulation;
	}
	
	
	
	
}
