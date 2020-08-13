package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.OverallData;
import edu.upenn.cit594.data.ZipCodeData;

public class AverageMarketValueProcessor implements AverageProcessor{

	/*
	 * method to calculate average for market value
	 */
	@Override
	public double getAverage(String fileName, int zip) {
		ZipCodeData zipData = OverallData.zipCodeMap.get(zip);
		if(zipData == null) {
			rp.readProperties(5, fileName, zip);
			zipData.totalMarketValue = zipProcessor.totalMarketValue(zip);
			zipData.averageMarketValue = zipProcessor.averageValue(zipData.totalMarketValue, 
					zipData.households);
		}
		if (zipData.averageMarketValue == 0) {
			if (zipData.totalMarketValue == 0) {
				if (zipData.marketValue == null) {
					rp.readProperties(5, fileName, zip);
				}	
				zipData.totalMarketValue = zipProcessor.totalMarketValue(zip);
			}

			zipData.averageMarketValue = zipProcessor.averageValue(zipData.totalMarketValue, 
					zipData.households);
		}
		
		return zipData.averageMarketValue;
	}

}
