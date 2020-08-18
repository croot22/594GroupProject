package edu.upenn.cit594.processor;

import edu.upenn.cit594.datamanagement.ReadProperties;

public interface AverageProcessor {
	//interface class to be used in strategy method for q3 and q4
	ReadProperties rp = new ReadProperties();
	ZipCodeProcessor zipProcessor = new ZipCodeProcessor();
	public double getAverage(String fileName, int zip, int selectedOption);
}
