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
		/*
		 * check if average livable area has been calculated, if not, do so and memoize
		 */
		if (zipData.averageLivableArea == 0) {
			/*
			 * check if total livable area has been calculated, if not, do so and memoize
			 */
			if (zipData.totalLivableArea == 0) {
				/*
				 * check if list of livable area has been calculated, if not, do so and memoize
				 */
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
