package edu.upenn.cit594.processor;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
import edu.upenn.cit594.datamanagement.ReadPopulationFile;
import edu.upenn.cit594.datamanagement.ReadProperties;

public class Questions {
	
	/*
	 * Create static maps for memoization
	 * Many maps for many possible future uses
	 * Repeated method is check if already memoized,
	 * If not, then perform file reading and memoization
	 */
	
	public static HashMap<Integer, Integer> populations = new HashMap<Integer, Integer>();
	public static TreeMap<Integer, Integer> fines = new TreeMap<Integer, Integer>();
	public static TreeMap<Integer, Double> finesPerCapitas = new TreeMap<Integer, Double>();
	public static HashMap<Integer, ArrayList <Integer>> marketValues = new HashMap<Integer, ArrayList<Integer>>();
	public static HashMap<Integer, ArrayList<Integer>> livableAreas = new HashMap<Integer, ArrayList<Integer>>();
	public static HashMap<Integer, Long> totalMarketValues = new HashMap<Integer, Long>();
	public static HashMap<Integer, Long> totalLivableAreas = new HashMap<Integer, Long>();
	public static HashMap<Integer, Double> averageMarketValues = new HashMap<Integer, Double>();
	public static HashMap<Integer, Double> averageLivableAreas = new HashMap<Integer, Double>();
	public static HashMap<Integer, Double> marketPerCapitas = new HashMap<Integer, Double>();
	public static HashMap<Integer, Double> marketPerFinePerCapitas = new HashMap<Integer, Double>();
	
	public void q1TotalPopulation(String populationfilename) {
		if (populations.isEmpty()) {
			ReadPopulationFile rpf = new ReadPopulationFile();
			populations = rpf.readPopulationFile(populationfilename);
		}
		int total_population = 0;
		for (int population : populations.keySet()) {         //Simply totaling the individual populations
			total_population +=  populations.get(population);
		}
		System.out.println(total_population);
	}

	public void q2TotalFinesPerCapita(String filetype, String parkingfilename, String populationfilename) {
		FileDecision fd = new FileDecision();
		
		/*
		 * If not already stored or memoized, obtain
		 * Otherwise use stored information
		 */
		
		if (populations.isEmpty()) {
			ReadPopulationFile rpf = new ReadPopulationFile();
			populations = rpf.readPopulationFile(populationfilename);
		}
		if (finesPerCapitas.isEmpty()) {
			fines = fd.fileDecision(filetype, parkingfilename);
		}
		for (Integer zipcode : fines.keySet()) {
			
			/*
			 * @Cayde
			 * checking the fines_per_capita hashmap for already having solution
			 */
			
			if ((fines.get(zipcode) != null) && populations.get(zipcode) != null) {
				double avgfinepercap = (double) fines.get(zipcode) / (double) populations.get(zipcode);
				finesPerCapitas.put(zipcode, avgfinepercap);    //Memoize if not already stored
				DecimalFormat df = new DecimalFormat("##.##");
				System.out.println(zipcode + " $" + df.format(avgfinepercap));
			}
		}
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
	public void q3AverageMarketValue(String propertiesfilename) throws IOException {
		System.out.println("Please pick a zipcode");
		Scanner i = new Scanner(System.in);
		int zip = 0;
		zip = i.nextInt();;
		ArrayList<Integer> market_value_list = new ArrayList<Integer>();  //all the market values for a zip code
		if (marketValues.containsKey(zip) == false) {
			ReadProperties rp = new ReadProperties();
			market_value_list = rp.readProperties(3, propertiesfilename, zip);
			marketValues.put(zip, market_value_list);  			//Memoize if not already stored
		}	
		if (totalMarketValues.containsKey(zip) == false) {   	//@Cayde this may be unnecessary step or previous may be
			long total_market_value = 0; 						//@Cayde probably could be double
			for (int market_value: marketValues.get(zip)) {
				total_market_value += market_value;
			}
			totalMarketValues.put(zip, total_market_value);  		//Memoize if not already stored
		}
		if (averageMarketValues.containsKey(zip) == false) {
			double average_market_value = (double) totalMarketValues.get(zip) /
					(double) marketValues.get(zip).size();
			averageMarketValues.put(zip, average_market_value);  	//Memoize if not already stored
		}
		System.out.println("Average Market value for " + zip + " is " + Math.round(averageMarketValues.get(zip)));
	}
	
	public void q4AverageLivableArea(String propertiesfilename) throws IOException {
		System.out.println("Please pick a zipcode");
		Scanner i = new Scanner(System.in);							//@Cayde use strategy design pattern to decrease repetition
		int zip = 0;
		zip = i.nextInt();
		ArrayList<Integer> livable_area_list = new ArrayList<Integer>();
		if (livableAreas.containsKey(zip) == false) {
			ReadProperties rp = new ReadProperties();
			livable_area_list = rp.readProperties(4, propertiesfilename, zip);
			livableAreas.put(zip, livable_area_list);  			//Memoize if not already stored
		}	
		if (totalLivableAreas.containsKey(zip) == false) {
			long total_livable_area = 0;
			for (int livable_area: livableAreas.get(zip)) {
				total_livable_area += livable_area;
			}
			totalLivableAreas.put(zip, total_livable_area);		//Memoize if not already stored
		}
		if (averageLivableAreas.containsKey(zip) == false) {
			double average_livable_area = (double) totalLivableAreas.get(zip) /
					(double) livableAreas.get(zip).size();
			averageLivableAreas.put(zip, average_livable_area);	//Memoize if not already stored
		}
		System.out.println("Average livable area for " + zip + " is " + Math.round(averageLivableAreas.get(zip)));
	}
	
	public void q5TotalMarketValuePerCapita(String propertiesfilename, String populationfilename) throws IOException {
		System.out.println("Please pick a zipcode");
		Scanner i = new Scanner(System.in);
		int zip = 0;
		zip = i.nextInt();
		//i.close();
		ArrayList<Integer> market_value_list = new ArrayList<Integer>();
		if (marketPerCapitas.containsKey(zip) == false) {
			if (totalMarketValues.containsKey(zip) == false) {
				if (marketValues.containsKey(zip) == false) {
					ReadProperties rp = new ReadProperties();
					market_value_list = rp.readProperties(3, propertiesfilename, zip);
					marketValues.put(zip, market_value_list);				//Memoize if not already stored
				}
				long total_market_value = 0;
				for (int market_value: marketValues.get(zip)) {
					total_market_value += market_value;
				}
				totalMarketValues.put(zip, total_market_value);			//Memoize if not already stored
			}
			if (populations.containsKey(zip) == false) {
				ReadPopulationFile rpf = new ReadPopulationFile();
				populations = rpf.readPopulationFile(populationfilename);
			}
			double market_per_capita = (double) totalMarketValues.get(zip) / 
				(double) populations.get(zip);
			marketPerCapitas.put(zip, market_per_capita);					//Memoize if not already stored
		}
		System.out.println("Total Market Value per Capital for " + zip + " is " + Math.round(marketPerCapitas.get(zip)));
	}
	
	public void q6TotalMarketValuePerTotalFinesPerCapita(String parkingfiletype, String propertiesfilename, 
			String populationfilename, String parkingfilename) throws IOException {
		System.out.println("Please pick a zipcode");
		Scanner i = new Scanner(System.in);
		int zip = 0;
		zip = i.nextInt();
		ArrayList<Integer> market_value_list = new ArrayList<Integer>();
		if (marketPerFinePerCapitas.containsKey(zip) == false) {
			if (totalMarketValues.containsKey(zip) == false) {
				if (marketValues.containsKey(zip) == false) {
					ReadProperties rp = new ReadProperties();
					market_value_list = rp.readProperties(3, propertiesfilename, zip);
					marketValues.put(zip, market_value_list);				//Memoize if not already stored
				}
				long total_market_value = 0;
				for (int market_value: marketValues.get(zip)) {
					total_market_value += market_value;
				}
				totalMarketValues.put(zip, total_market_value);			//Memoize if not already stored
			}
			if (populations.containsKey(zip) == false) {
				ReadPopulationFile rpf = new ReadPopulationFile();
				populations = rpf.readPopulationFile(populationfilename);	//Memoize if not already stored
			}
			if (finesPerCapitas.isEmpty()) {
				FileDecision fd = new FileDecision();
				fines = fd.fileDecision(parkingfiletype, parkingfilename);	//Memoize if not already stored
			}
			double market_per_fine_per_capita = ((double) totalMarketValues.get(zip) / (double) fines.get(zip)) /
				(double) populations.get(zip);
			marketPerFinePerCapitas.put(zip, market_per_fine_per_capita);//Memoize if not already stored
		}
		System.out.println(Math.round(marketPerFinePerCapitas.get(zip)));
	}
}
