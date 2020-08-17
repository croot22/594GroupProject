package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.OverallData;
import edu.upenn.cit594.data.ZipCodeData;

public class AverageLivableAreaProcessor implements AverageProcessor{
	ZipCodeData zipData = new ZipCodeData();
	/*
	 * method to get average for livable area
	 */
	@Override
	public double getAverage(String fileName, int zip, int selectedOption) {
		if(OverallData.zipCodeMap.containsKey(zip)) {
			ZipCodeData zipData = OverallData.zipCodeMap.get(zip);
		}

		if (zipData.averageLivableArea == 0) {
			if (zipData.totalLivableArea == 0) {
				if (zipData.livableArea.isEmpty()) {
					rp.readProperties(selectedOption, fileName, zip);
					zipData = OverallData.zipCodeMap.get(zip);
				}	
				zipData.totalLivableArea = zipProcessor.totalLivableAreas(zip);
			}

			zipData.averageLivableArea = zipProcessor.averageValue(zipData.totalLivableArea, 
					zipData.households);
		}

		return zipData.averageLivableArea;
	}

}
