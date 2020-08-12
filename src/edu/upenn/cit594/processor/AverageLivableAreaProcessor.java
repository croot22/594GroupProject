package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.OverallData;
import edu.upenn.cit594.data.ZipCodeData;
import edu.upenn.cit594.datamanagement.ReadProperties;

public class AverageLivableAreaProcessor implements AverageProcessor{
	ZipCodeData zipData = new ZipCodeData();
	ReadProperties rp = new ReadProperties();
	ZipCodeProcessor zipProcessor = new ZipCodeProcessor();
	
	@Override
	public double getAverage(String fileName, int zip) {
		zipData = OverallData.zipCodeMap.get(zip);

		if (zipData.averageLivableArea == 0) {
			if (zipData.totalLivableArea == 0) {
				if (zipData.livableArea == null) {
					rp.readProperties(5, fileName, zip);
				}	
				zipData.totalLivableArea = zipProcessor.totalMarketValue(zip);
			}

			zipData.averageLivableArea = zipProcessor.averageValue(zipData.totalLivableArea, 
					zipData.households);
		}
		
		return zipData.averageMarketValue;
	}

}
