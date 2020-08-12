package edu.upenn.cit594.processor;

import edu.upenn.cit594.datamanagement.ReadProperties;

public interface AverageProcessor {

	ReadProperties rp = new ReadProperties();
	ZipCodeProcessor zipProcessor = new ZipCodeProcessor();
	public double getAverage(String fileName, int zip);
}
