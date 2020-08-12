package edu.upenn.cit594.processor;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

import edu.upenn.cit594.data.OverallData;
import edu.upenn.cit594.data.ZipCodeData;
import edu.upenn.cit594.datamanagement.ReadPopulationFile;
import edu.upenn.cit594.datamanagement.ReadProperties;
import edu.upenn.cit594.ui.UserInterface;

public class Questions {

	/*
	 * Create static maps for memoization
	 * Many maps for many possible future uses
	 * Repeated method is check if already memoized,
	 * If not, then perform file reading and memoization
	 */



	public void q1TotalPopulation(String populationFileName) {
		if (OverallData.totalPopulation == 0) {
			totalPop(populationFileName);
		}

		System.out.println(OverallData.totalPopulation);
	}

	public void q2TotalFinesPerCapita(String fileType, String parkingFileName, 
			String populationFileName) {
		FileDecision fd = new FileDecision();
		DecimalFormat decForm = new DecimalFormat("##.##");

		/*
		 * If not already stored or memoized, obtain
		 * Otherwise use stored information
		 */

		if (OverallData.totalPopulation == 0) {
			totalPop(populationFileName);
		}
		if (OverallData.finesStored == false) {
			fd.fileDecision(fileType, parkingFileName);
		}
		if (OverallData.totalFinesStored == false){

			for (Integer zipCode : ZipCodeData.zipCodeMap.keySet()) {

				ZipCodeData zip = ZipCodeData.zipCodeMap.get(zipCode);
				zip.totalFines = ZipCodeProcessor.fineTotal(zipCode);

			}
		}
		if (OverallData.averageFinesPerCapitaStored == false) {

			for (Integer zipCode : ZipCodeData.zipCodeMap.keySet()) {
				ZipCodeData zip = ZipCodeData.zipCodeMap.get(zipCode);
				zip.totalFinesPerCapita = ZipCodeProcessor.averageFinePerCapita(zipCode);
				System.out.println(zipCode + " $" + 
						decForm.format(zip.totalFinesPerCapita));
			}
		}
		else {
			for (Integer zipCode : ZipCodeData.zipCodeMap.keySet()) {
				ZipCodeData zip = ZipCodeData.zipCodeMap.get(zipCode);
				System.out.println(zipCode + " $" + 
						decForm.format(zip.totalFinesPerCapita));
			}
		}
	}

	private void totalPop(String populationFileName) {
		ReadPopulationFile readPopulationFile = new ReadPopulationFile();
		readPopulationFile.readPopulationFile(populationFileName);
		OverallData.totalPopulation = ZipCodeProcessor.populationTotal();
	}


	/*
	 * If not already stored or memoized, obtain
	 * Note do not assume if we have market values for a zip
	 * code already have total.  This allows for increased modularity
	 * if there were other prompts regarding the market values.
	 */


	/*  @Cayde
	 * Use strategy design pattern and/or separate methods
	 * to decrease repetition
	 */
	public void q3AverageMarketValue(String propertiesFileName, int zip) {
		ZipCodeData zipData = ZipCodeData.zipCodeMap.get(zip);
		
		if (zipData.marketValue == null) {
			ReadProperties readProperties = new ReadProperties();
		}	
		
		if (zipData.totalMarketValue == 0) {
			zipData.totalMarketValue = ZipCodeProcessor.totalMarketValue(zip);
		}
		if (zipData.totalLivableArea == 0) {
			zipData.totalLivableArea = ZipCodeProcessor.totalLivableAreas(zip);
		}
		if (zipData.averageMarketValue == 0) {
			zipData.averageMarketValue = ZipCodeProcessor.averageMarketValue(zip);
		}
		System.out.println("Average Market value for " + zip + " is " + 
				Math.round(zipData.averageMarketValue));
	}

	public void q4AverageLivableArea(String propertiesFileName, int zip) {
		ZipCodeData zipData = ZipCodeData.zipCodeMap.get(zip);
		
		if (zipData.marketValue == null) {
			ReadProperties rp = new ReadProperties();
		}	
		if (zipData.totalLivableArea == 0) {
			zipData.totalLivableArea = ZipCodeProcessor.totalLivableAreas(zip);
		}
		if (zipData.averageLivableArea == 0) {
			zipData.averageLivableArea = ZipCodeProcessor.averageLivableArea(zip);
		}
		System.out.println("Average livable area for " + zip + " is " + 
		Math.round(zipData.averageLivableArea));
	}

	public void q5TotalMarketValuePerCapita(String propertiesFileName, 
			String populationFileName, int zip) {
		ZipCodeData zipData = ZipCodeData.zipCodeMap.get(zip);
		
		if (zipData.marketValuePerCapita == 0) {
			if (zipData.totalMarketValue == 0) {
				if (zipData.marketValue == null) {
					ReadProperties rp = new ReadProperties();

				}
				double totalMarketValue = ZipCodeProcessor.totalMarketValue(zip);
				
			}
			if (zipData.population == 0) {
				ReadPopulationFile rpf = new ReadPopulationFile();
			}
			zipData.marketValuePerCapita = ZipCodeProcessor.marketValuePerCapita();
			
		}
		System.out.println("Total Market Value per Capital for " + zip + " is " + 
		Math.round(zipData.marketValuePerCapita));
	}

	public void q6TotalMarketValuePerTotalFinesPerCapita(String parkingFileType, String propertiesFileName, 
			String populationFileName, String parkingFileName, int zip) {
		ZipCodeData zipData = ZipCodeData.zipCodeMap.get(zip);
		if (zipData.marketValuePerFinePerCapita == 0) {
			if (zipData.totalMarketValue == 0) {
				if (zipData.marketValue == null) {
					ReadProperties rp = new ReadProperties();
					
				}
				zipData.totalMarketValue = ZipCodeProcessor.totalMarketValue(zip);
				
			}
			if (zipData.population == 0) {
				ReadPopulationFile rpf = new ReadPopulationFile();
			    
			}
			if (zipData.totalFinesPerCapita == 0) {
				FileDecision fd = new FileDecision();
				
			}
			zipData.marketValuePerFinePerCapita = ZipCodeProcessor.marketValuePerFinesPerCapita();
			
		}
		System.out.println(Math.round(zipData.marketValuePerFinePerCapita));
	}


}
