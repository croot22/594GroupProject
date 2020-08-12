package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.OverallData;
import edu.upenn.cit594.data.ZipCodeData;

public class ZipCodeProcessor {

	
	ZipCodeData zipData = new ZipCodeData();
	
	public int fineTotal(int zipCode) {
		int totalFines = 0;
		if (OverallData.zipCodeMap.containsKey(zipCode)) {  
			zipData = OverallData.zipCodeMap.get(zipCode);
			for(int fine: zipData.fines) {
				totalFines += fine;
			}
		}

		return totalFines;
	}
	
	public int populationTotal() {
		int totalPopulation = 0;
		for(Integer zip: OverallData.zipCodeMap.keySet()) {
			
			zipData = OverallData.zipCodeMap.get(zip);
			totalPopulation += zipData.population;
		}
		return totalPopulation;
	}
	
	public double averageFinePerCapita(int zip) {
		int avgFine = 0;
	
		zipData = OverallData.zipCodeMap.get(zip);
		avgFine = zipData.totalFines / zipData.population;

		return avgFine;
	}
	
	public double totalMarketValue(int zip) {
		double totalMarketValue = 0;
		
		zipData = OverallData.zipCodeMap.get(zip);
		for(double value: zipData.marketValue) {
			totalMarketValue += value;
		}
		
		return totalMarketValue;
	}
	
	public double totalLivableAreas(int zip) {
		double totalLivableAreas = 0;
		
		zipData = OverallData.zipCodeMap.get(zip);
		for(double value: zipData.livableArea) {
			totalLivableAreas += value;
		}
		
		return totalLivableAreas;
	}
	
	public double averageMarketValue(int zip) {
		double averageMarketValue = 0;
		
		zipData = OverallData.zipCodeMap.get(zip);
		averageMarketValue = zipData.totalMarketValue / zipData.households;
		
		return averageMarketValue;
	}
	
	public double averageLivableArea(int zip) {
		double averageLivableArea = 0;
		
		zipData = OverallData.zipCodeMap.get(zip);
		averageLivableArea = zipData.totalLivableArea / zipData.households;
		
		return averageLivableArea;
	}
	
	public double marketValuePerCapita(int zip) {
		
		
		return 0;
	}
	
	public double marketValuePerFinesPerCapita(int zip) {
		
		return 0;
	}
}
