package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.OverallData;
import edu.upenn.cit594.data.ZipCodeData;

public class ZipCodeProcessor {


	ZipCodeData zipData = new ZipCodeData();

	/*
	 * sums fines for a zipcode
	 */
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
	/*
	 * sums population for all zipcodes
	 */
	public int populationTotal() {
		int totalPopulation = 0;
		for(Integer zip: OverallData.zipCodeMap.keySet()) {

			zipData = OverallData.zipCodeMap.get(zip);
			totalPopulation += zipData.population;
		}
		return totalPopulation;
	}
	
	/*
	 * calcs average fine per population of a speicifc zipcode
	 */
	public double totalFinePerCapita(int zip) {
		double avgFine = 0;

		zipData = OverallData.zipCodeMap.get(zip);
		avgFine = zipData.totalFines / zipData.population;

		return avgFine;
	}
	/*
	 * calcs the total market value for a specific zipcode
	 */
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
	/*
	 * calcs the total livable area for a specific zipcode
	 */
	public double totalLivableAreas(int zip) {
		double totalLivableAreas = 0;

		zipData = OverallData.zipCodeMap.get(zip);
		for(double value: zipData.livableArea) {
			totalLivableAreas += value;
		}

		return totalLivableAreas;
	}
	/*
	 * calcs generic average
	 */
	public double averageValue(double numerator, double denominator) {
		double averageValue =  numerator / denominator;

		return averageValue;
	}

	/*
	 * calcs market value per population of a specific zipcode
	 */
	public double marketValuePerCapita(int zip) {
		double marketValuePerCapita = 0;

		zipData = OverallData.zipCodeMap.get(zip);
		marketValuePerCapita = zipData.totalMarketValue / zipData.population;

		return marketValuePerCapita;
	}
	/*
	 * calcs ration of market value per capita to fines per capita of a specific zipcode
	 */
	public double marketValuePerFinesPerCapita(int zip) {
		double marketValuePerCapita = marketValuePerCapita(zip);
		double finesPerCapita = totalFinePerCapita(zip);
		double marketValuePerFinesPerCapita = finesPerCapita / marketValuePerCapita; 

		return marketValuePerFinesPerCapita;
	}
}
