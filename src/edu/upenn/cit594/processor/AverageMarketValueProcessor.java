package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.OverallData;
import edu.upenn.cit594.data.ZipCodeData;

public class AverageMarketValueProcessor implements AverageProcessor{
	ZipCodeData zipData = new ZipCodeData();
	/*
	 * method to calculate average for market value
	 */
	@Override
	public double getAverage(String fileName, int zip, int selectedOption) {
		if(OverallData.zipCodeMap.containsKey(zip)) {
			zipData = OverallData.zipCodeMap.get(zip);
		}
		/*
		 * check if average market value has been calculated, if not, do so and memoize
		 */
		if (zipData.averageMarketValue == 0) {
			/*
			 * check if total market value has been calculated, if not, do so and memoize
			 */
			if (zipData.totalMarketValue == 0) {
				/*
				 * check if list of market value has been read, if not, do so and memoize
				 */
				if (zipData.marketValue.isEmpty()) {
					rp.readProperties(selectedOption, fileName, zip);
					zipData = OverallData.zipCodeMap.get(zip);
				}	
				zipData.totalMarketValue = zipProcessor.totalMarketValue(zip);
			}

			zipData.averageMarketValue = zipProcessor.averageValue(zipData.totalMarketValue, 
					zipData.households);
		}
		
		return zipData.averageMarketValue;
	}

}
