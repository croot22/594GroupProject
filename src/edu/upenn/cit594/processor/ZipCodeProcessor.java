package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.OverallData;
import edu.upenn.cit594.data.ZipCodeData;

public class ZipCodeProcessor {

	public int fineTotal(int zipCode) {
		int totalFines = 0;
		if (OverallData.zipCodeMap.containsKey(zipCode)) {  
			ZipCodeData zipMap = OverallData.zipCodeMap.get(zipCode);
			for(int fine: zipMap.fines) {
				totalFines += fine;
			}
		}

		return totalFines;
	}
	
	public int populationTotal() {
		int totalPopulation = 0;
		for(Integer zip: OverallData.zipCodeMap.keySet()) {
			
			ZipCodeData zipMap = OverallData.zipCodeMap.get(zip);
			totalPopulation += zipMap.population;
		}
		return totalPopulation;
	}
	
	public double averageFinePerCapita(int zip) {
		int avgFine = 0;
	
		ZipCodeData zipCode = OverallData.zipCodeMap.get(zip);
		avgFine = zipCode.totalFines / zipCode.population;

		return avgFine;
	}
	
	public double totalMarketValue(int zip) {
		double totalMarketValue = 0;
		
		ZipCodeData zipCode = OverallData.zipCodeMap.get(zip);
		for(double value: zipCode.marketValue) {
			totalMarketValue += value;
		}
		
		return totalMarketValue;
	}
	
	public double totalLivableAreas(int zip) {
		double totalLivableAreas = 0;
		
		ZipCodeData zipCode = OverallData.zipCodeMap.get(zip);
		for(double value: zipCode.livableArea) {
			totalLivableAreas += value;
		}
		
		return totalLivableAreas;
	}
	
	public double averageMarketValue(int zip) {
		double averageMarketValue = 0;
		
		ZipCodeData zipCode = OverallData.zipCodeMap.get(zip);
		averageMarketValue = zipCode.totalMarketValue / zipCode.households;
		
		return averageMarketValue;
	}
	
	public double averageLivableArea(int zip) {
		double averageLivableArea = 0;
		
		ZipCodeData zipCode = OverallData.zipCodeMap.get(zip);
		averageLivableArea = zipCode.totalLivableArea / zipCode.households;
		
		return averageLivableArea;
	}
	
	public double marketValuePerCapita() {
		return 0;
	}
	
	public double marketValuePerFinesPerCapita() {
		return 0;
	}
}
