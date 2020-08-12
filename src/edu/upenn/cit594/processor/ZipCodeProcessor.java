package edu.upenn.cit594.processor;

import java.text.DecimalFormat;

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
	
	public static double averageFinePerCapita(int zip) {
		int avgFine = 0;
	
		ZipCodeData zipCode = ZipCodeData.zipCodeMap.get(zip);
		avgFine = zipCode.totalFines / zipCode.population;

		return avgFine;
	}
	
	public static double totalMarketValue(int zip) {
		double totalMarketValue = 0;
		
		ZipCodeData zipCode = ZipCodeData.zipCodeMap.get(zip);
		for(double value: zipCode.marketValue) {
			totalMarketValue += value;
		}
		
		return totalMarketValue;
	}
	
	public static double totalLivableAreas(int zip) {
		double totalLivableAreas = 0;
		
		ZipCodeData zipCode = ZipCodeData.zipCodeMap.get(zip);
		for(double value: zipCode.livableArea) {
			totalLivableAreas += value;
		}
		
		return totalLivableAreas;
	}
	
	public static double averageMarketValue(int zip) {
		double averageMarketValue = 0;
		
		ZipCodeData zipCode = ZipCodeData.zipCodeMap.get(zip);
		averageMarketValue = zipCode.totalMarketValue / zipCode.livableArea;
		
		return averageMarketValue;
	}
	
}
