package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.OverallData;
import edu.upenn.cit594.data.ZipCodeData;
import edu.upenn.cit594.datamanagement.ReadProperties;

public class AverageMarketValueProcessor implements AverageProcessor{
	ZipCodeData zipData = new ZipCodeData();
	ReadProperties rp = new ReadProperties();
	ZipCodeProcessor zipProcessor = new ZipCodeProcessor();
	
	@Override
	public double getAverage(String fileName, int zip) {
		zipData = OverallData.zipCodeMap.get(zip);

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