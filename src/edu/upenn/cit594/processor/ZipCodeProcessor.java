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
		if(OverallData.zipCodeMap.containsKey(zip)) {
			zipData = OverallData.zipCodeMap.get(zip);
		}

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

	public double averageValue(double numerator, double denominator) {
		double averageValue =  numerator / denominator;

		return averageValue;
	}


	public double marketValuePerCapita(int zip) {
		double marketValuePerCapita = 0;

		zipData = OverallData.zipCodeMap.get(zip);
		marketValuePerCapita = zipData.totalMarketValue / zipData.population;

		return marketValuePerCapita;
	}

	public double marketValuePerFinesPerCapita(int zip) {
		double marketValuePerCapita = marketValuePerCapita(zip);
		double finesPerCapita = averageFinePerCapita(zip);
		double marketValuePerFinesPerCapita = finesPerCapita / marketValuePerCapita; 

		return marketValuePerFinesPerCapita;
	}
}
