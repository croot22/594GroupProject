package edu.upenn.cit594.datamanagement;

import java.text.DecimalFormat;
import edu.upenn.cit594.data.OverallData;
import edu.upenn.cit594.data.ZipCodeData;
import edu.upenn.cit594.processor.AverageLivableAreaProcessor;
import edu.upenn.cit594.processor.AverageMarketValueProcessor;
import edu.upenn.cit594.processor.ZipCodeProcessor;

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
			OverallData.finesStored = true;
		}
		if (OverallData.totalFinesStored == false){

			for (Integer zipCode : OverallData.zipCodeMap.keySet()) {

				zipData = OverallData.zipCodeMap.get(zipCode);
				if (!zipData.fines.isEmpty()) {
					zipData.totalFines = zipProcessor.fineTotal(zipCode);
					OverallData.zipCodeMap.put(zipCode, zipData);
				}

			}
			OverallData.totalFinesStored = true;
		}
		if (OverallData.averageFinesPerCapitaStored == false) {

			for (Integer zipCode : OverallData.zipCodeMap.keySet()) {
				zipData = OverallData.zipCodeMap.get(zipCode);
				if (zipData.totalFines >= .01 && zipData.population != 0) {
					OverallData.finesMap.put(zipCode, zipProcessor.averageFinePerCapita(zipCode));
					System.out.println(zipCode + " $" + 
							decForm.format(OverallData.finesMap.get(zipCode)));
				}


				OverallData.zipCodeMap.put(zipCode, zipData);
			}
			OverallData.averageFinesPerCapitaStored = true;
		}
		else {
			for (Integer zipCode : OverallData.zipCodeMap.keySet()) {
				zipData = OverallData.zipCodeMap.get(zipCode);
				if (zipData.totalFinesPerCapita >= .01) {
					System.out.println(zipCode + " $" + 
							decForm.format(zipData.totalFinesPerCapita));
				}
			}
		}
	}
	/*
	 * helper method to read and store total population
	 */
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


	public void q3AverageMarketValue(String propertiesFileName, int zip) {
		AverageMarketValueProcessor amvp = new AverageMarketValueProcessor();
		double averageMarketValue = amvp.getAverage(propertiesFileName, zip, 3);
		System.out.println("Average Market value for " + zip + " is " + 
				Math.round(averageMarketValue));
	}

	public void q4AverageLivableArea(String propertiesFileName, int zip) {
		AverageLivableAreaProcessor alap = new AverageLivableAreaProcessor();
		double averageLivableArea = alap.getAverage(propertiesFileName, zip, 4);
		System.out.println("Average livable area for " + zip + " is " + 
				Math.round(averageLivableArea));
	}

	public void q5TotalMarketValuePerCapita(String propertiesFileName, 
			String populationFileName, int zip) {
		if(OverallData.zipCodeMap.containsKey(zip)) {
			zipData = OverallData.zipCodeMap.get(zip);
		}
		else {
			zipData.zipCode = zip;
		}

		if (zipData.marketValuePerCapita == 0) {
			if (zipData.totalMarketValue == 0) {
				if (zipData.marketValue.isEmpty()) {

					rp.readProperties(5, propertiesFileName, zip);
					zipData = OverallData.zipCodeMap.get(zip);
				}
				zipData.totalMarketValue = zipProcessor.totalMarketValue(zip);

			}
			if (OverallData.totalPopulation == 0) {
				totalPop(populationFileName);
			}
			zipData.marketValuePerCapita = zipProcessor.marketValuePerCapita(zip);
			OverallData.zipCodeMap.put(zip, zipData);

		}
		System.out.println("Total Market Value per Capita for " + zip + " is " + 
				Math.round(zipData.marketValuePerCapita));
	}

	public void q6TotalMarketValuePerTotalFinesPerCapita(String parkingFileType, 
			String propertiesFileName, String populationFileName, String parkingFileName, int zip) {
		if(OverallData.zipCodeMap.containsKey(zip)) {
			zipData = OverallData.zipCodeMap.get(zip);
		}
		else {
			zipData.zipCode = zip;
		}
		DecimalFormat decForm = new DecimalFormat("##.##");

		if (zipData.marketValuePerFinePerCapita == 0) {
			if (zipData.marketValuePerCapita == 0) {
				if (zipData.totalMarketValue == 0) {
					if (zipData.marketValue.isEmpty()) {
						rp.readProperties(5, propertiesFileName, zip);
						zipData = OverallData.zipCodeMap.get(zip);
					}
					zipData.totalMarketValue = zipProcessor.totalMarketValue(zip);

				}
				if (OverallData.totalPopulation == 0) {
					totalPop(populationFileName);
				}
				zipData.marketValuePerCapita = zipProcessor.marketValuePerCapita(zip);
				OverallData.zipCodeMap.put(zip, zipData);

			}

			if (OverallData.averageFinesPerCapitaStored == false) {
				if(OverallData.totalFinesStored == false) {
					if(OverallData.finesStored == false) {
						fd.fileDecision(parkingFileType, parkingFileName);
					}

					for (Integer zipCode : OverallData.zipCodeMap.keySet()) {

						zipData = OverallData.zipCodeMap.get(zipCode);
						zipData.totalFines = zipProcessor.fineTotal(zipCode);
						OverallData.zipCodeMap.put(zipCode, zipData);
					}
					OverallData.totalFinesStored = true;
				}

				for (Integer zipCode : OverallData.zipCodeMap.keySet()) {
					zipData = OverallData.zipCodeMap.get(zipCode);
					zipData.totalFinesPerCapita = zipProcessor.averageFinePerCapita(zipCode);
					OverallData.zipCodeMap.put(zipCode, zipData);
				}
				OverallData.averageFinesPerCapitaStored = true;
			}
			zipData = OverallData.zipCodeMap.get(zip);
			zipData.marketValuePerFinePerCapita = zipProcessor.marketValuePerFinesPerCapita(zip);
			OverallData.zipCodeMap.put(zip, zipData);

		}
		System.out.println(zipData.marketValuePerFinePerCapita);
	}


}
