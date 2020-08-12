package edu.upenn.cit594.processor;

import java.text.DecimalFormat;
import edu.upenn.cit594.data.OverallData;
import edu.upenn.cit594.data.ZipCodeData;
import edu.upenn.cit594.datamanagement.ReadPopulationFile;
import edu.upenn.cit594.datamanagement.ReadProperties;

public class Questions {

	/*
	 * Repeated method is check if already memoized,
	 * If not, then perform file reading and memoization
	 */
	ZipCodeData zipData = new ZipCodeData();
	ReadProperties rp = new ReadProperties();
	FileDecision fd = new FileDecision();
	ReadPopulationFile rpf = new ReadPopulationFile();
	ZipCodeProcessor zipProcessor = new ZipCodeProcessor();
	
	public void q1TotalPopulation(String populationFileName) {
		if (OverallData.totalPopulation == 0) {
			totalPop(populationFileName);
		}

		System.out.println(OverallData.totalPopulation);
	}

	public void q2TotalFinesPerCapita(String fileType, String parkingFileName, 
			String populationFileName) {
		
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

			for (Integer zipCode : OverallData.zipCodeMap.keySet()) {

				zipData = OverallData.zipCodeMap.get(zipCode);
				zipData.totalFines = zipProcessor.fineTotal(zipCode);

			}
		}
		if (OverallData.averageFinesPerCapitaStored == false) {

			for (Integer zipCode : OverallData.zipCodeMap.keySet()) {
				zipData = OverallData.zipCodeMap.get(zipCode);
				zipData.totalFinesPerCapita = zipProcessor.averageFinePerCapita(zipCode);
				System.out.println(zipCode + " $" + 
						decForm.format(zipData.totalFinesPerCapita));
			}
		}
		else {
			for (Integer zipCode : OverallData.zipCodeMap.keySet()) {
				zipData = OverallData.zipCodeMap.get(zipCode);
				System.out.println(zipCode + " $" + 
						decForm.format(zipData.totalFinesPerCapita));
			}
		}
	}

	private void totalPop(String populationFileName) {
		
		rpf.readPopulationFile(populationFileName);
		OverallData.totalPopulation = zipProcessor.populationTotal();
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
		zipData = OverallData.zipCodeMap.get(zip);
		
		if (zipData.marketValue == null) {
			
			rp.readProperties(5, propertiesFileName, zip);
		}	
		
		if (zipData.totalMarketValue == 0) {
			zipData.totalMarketValue = zipProcessor.totalMarketValue(zip);
		}
		if (zipData.averageMarketValue == 0) {
			zipData.averageMarketValue = zipProcessor.averageMarketValue(zip);
		}
		System.out.println("Average Market value for " + zip + " is " + 
				Math.round(zipData.averageMarketValue));
	}

	public void q4AverageLivableArea(String propertiesFileName, int zip) {
		zipData = OverallData.zipCodeMap.get(zip);
		
		if (zipData.marketValue == null) {

			rp.readProperties(5, propertiesFileName, zip);
		}	
		if (zipData.totalLivableArea == 0) {
			zipData.totalLivableArea = zipProcessor.totalLivableAreas(zip);
		}
		if (zipData.averageLivableArea == 0) {
			zipData.averageLivableArea = zipProcessor.averageLivableArea(zip);
		}
		System.out.println("Average livable area for " + zip + " is " + 
		Math.round(zipData.averageLivableArea));
	}

	public void q5TotalMarketValuePerCapita(String propertiesFileName, 
			String populationFileName, int zip) {
		zipData = OverallData.zipCodeMap.get(zip);
		
		if (zipData.marketValuePerCapita == 0) {
			if (zipData.totalMarketValue == 0) {
				if (zipData.marketValue == null) {
					
					rp.readProperties(5, propertiesFileName, zip);
				}
				zipData.totalMarketValue = zipProcessor.totalMarketValue(zip);
				
			}
			if (OverallData.totalPopulation == 0) {
				totalPop(populationFileName);
			}
			zipData.marketValuePerCapita = zipProcessor.marketValuePerCapita(zip);
			
		}
		System.out.println("Total Market Value per Capital for " + zip + " is " + 
		Math.round(zipData.marketValuePerCapita));
	}

	public void q6TotalMarketValuePerTotalFinesPerCapita(String parkingFileType, 
			String propertiesFileName, String populationFileName, String parkingFileName, int zip) {
		zipData = OverallData.zipCodeMap.get(zip);
		if (zipData.marketValuePerFinePerCapita == 0) {
			if (zipData.totalMarketValue == 0) {
				if (zipData.marketValue == null) {
					
					rp.readProperties(5, propertiesFileName, zip);
					
				}
				zipData.totalMarketValue = zipProcessor.totalMarketValue(zip);
				
			}
			if (zipData.population == 0) {
				totalPop(populationFileName);
			    
			}
			if (zipData.totalFinesPerCapita == 0) {
				
				fd.fileDecision(parkingFileType, parkingFileName);
			}
			zipData.marketValuePerFinePerCapita = zipProcessor.marketValuePerFinesPerCapita(zip);
			
		}
		System.out.println(Math.round(zipData.marketValuePerFinePerCapita));
	}


}
