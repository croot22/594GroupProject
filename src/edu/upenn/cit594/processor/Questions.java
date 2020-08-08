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
	public static TreeMap<Integer, Double> fines_per_capitas = new TreeMap<Integer, Double>();
	public static HashMap<Integer, ArrayList <Integer>> market_values = new HashMap<Integer, ArrayList<Integer>>();
	public static HashMap<Integer, ArrayList<Integer>> livable_areas = new HashMap<Integer, ArrayList<Integer>>();
	public static HashMap<Integer, Long> total_market_values = new HashMap<Integer, Long>();
	public static HashMap<Integer, Long> total_livable_areas = new HashMap<Integer, Long>();
	public static HashMap<Integer, Double> average_market_values = new HashMap<Integer, Double>();
	public static HashMap<Integer, Double> average_livable_areas = new HashMap<Integer, Double>();
	public static HashMap<Integer, Double> market_per_capitas = new HashMap<Integer, Double>();
	public static HashMap<Integer, Double> market_per_fine_per_capitas = new HashMap<Integer, Double>();
	
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
		if (fines_per_capitas.isEmpty()) {
			fines = fd.fileDecision(filetype, parkingfilename);
		}
		for (Integer zipcode : fines.keySet()) {
			
			/*
			 * @Cayde
			 * checking the fines_per_capita hashmap for already having solution
			 */
			
			if ((fines.get(zipcode) != null) && populations.get(zipcode) != null) {
				double avgfinepercap = (double) fines.get(zipcode) / (double) populations.get(zipcode);
				fines_per_capitas.put(zipcode, avgfinepercap);    //Memoize if not already stored
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
		if (market_values.containsKey(zip) == false) {
			ReadProperties rp = new ReadProperties();
			market_value_list = rp.readProperties(3, propertiesfilename, zip);
			market_values.put(zip, market_value_list);  			//Memoize if not already stored
		}	
		if (total_market_values.containsKey(zip) == false) {   	//@Cayde this may be unnecessary step or previous may be
			long total_market_value = 0; 						//@Cayde probably could be double
			for (int market_value: market_values.get(zip)) {
				total_market_value += market_value;
			}
			total_market_values.put(zip, total_market_value);  		//Memoize if not already stored
		}
		if (average_market_values.containsKey(zip) == false) {
			double average_market_value = (double) total_market_values.get(zip) /
					(double) market_values.get(zip).size();
			average_market_values.put(zip, average_market_value);  	//Memoize if not already stored
		}
		System.out.println("Average Market value for " + zip + " is " + Math.round(average_market_values.get(zip)));
	}
	
	public void q4AverageLivableArea(String propertiesfilename) throws IOException {
		System.out.println("Please pick a zipcode");
		Scanner i = new Scanner(System.in);							//@Cayde use strategy design pattern to decrease repetition
		int zip = 0;
		zip = i.nextInt();
		ArrayList<Integer> livable_area_list = new ArrayList<Integer>();
		if (livable_areas.containsKey(zip) == false) {
			ReadProperties rp = new ReadProperties();
			livable_area_list = rp.readProperties(4, propertiesfilename, zip);
			livable_areas.put(zip, livable_area_list);  			//Memoize if not already stored
		}	
		if (total_livable_areas.containsKey(zip) == false) {
			long total_livable_area = 0;
			for (int livable_area: livable_areas.get(zip)) {
				total_livable_area += livable_area;
			}
			total_livable_areas.put(zip, total_livable_area);		//Memoize if not already stored
		}
		if (average_livable_areas.containsKey(zip) == false) {
			double average_livable_area = (double) total_livable_areas.get(zip) /
					(double) livable_areas.get(zip).size();
			average_livable_areas.put(zip, average_livable_area);	//Memoize if not already stored
		}
		System.out.println("Average livable area for " + zip + " is " + Math.round(average_livable_areas.get(zip)));
	}
	
	public void q5TotalMarketValuePerCapita(String propertiesfilename, String populationfilename) throws IOException {
		System.out.println("Please pick a zipcode");
		Scanner i = new Scanner(System.in);
		int zip = 0;
		zip = i.nextInt();
		//i.close();
		ArrayList<Integer> market_value_list = new ArrayList<Integer>();
		if (market_per_capitas.containsKey(zip) == false) {
			if (total_market_values.containsKey(zip) == false) {
				if (market_values.containsKey(zip) == false) {
					ReadProperties rp = new ReadProperties();
					market_value_list = rp.readProperties(3, propertiesfilename, zip);
					market_values.put(zip, market_value_list);				//Memoize if not already stored
				}
				long total_market_value = 0;
				for (int market_value: market_values.get(zip)) {
					total_market_value += market_value;
				}
				total_market_values.put(zip, total_market_value);			//Memoize if not already stored
			}
			if (populations.containsKey(zip) == false) {
				ReadPopulationFile rpf = new ReadPopulationFile();
				populations = rpf.readPopulationFile(populationfilename);
			}
			double market_per_capita = (double) total_market_values.get(zip) / 
				(double) populations.get(zip);
			market_per_capitas.put(zip, market_per_capita);					//Memoize if not already stored
		}
		System.out.println("Total Market Value per Capital for " + zip + " is " + Math.round(market_per_capitas.get(zip)));
	}
	
	public void q6TotalMarketValuePerTotalFinesPerCapita(String parkingfiletype, String propertiesfilename, 
			String populationfilename, String parkingfilename) throws IOException {
		System.out.println("Please pick a zipcode");
		Scanner i = new Scanner(System.in);
		int zip = 0;
		zip = i.nextInt();
		ArrayList<Integer> market_value_list = new ArrayList<Integer>();
		if (market_per_fine_per_capitas.containsKey(zip) == false) {
			if (total_market_values.containsKey(zip) == false) {
				if (market_values.containsKey(zip) == false) {
					ReadProperties rp = new ReadProperties();
					market_value_list = rp.readProperties(3, propertiesfilename, zip);
					market_values.put(zip, market_value_list);				//Memoize if not already stored
				}
				long total_market_value = 0;
				for (int market_value: market_values.get(zip)) {
					total_market_value += market_value;
				}
				total_market_values.put(zip, total_market_value);			//Memoize if not already stored
			}
			if (populations.containsKey(zip) == false) {
				ReadPopulationFile rpf = new ReadPopulationFile();
				populations = rpf.readPopulationFile(populationfilename);	//Memoize if not already stored
			}
			if (fines_per_capitas.isEmpty()) {
				FileDecision fd = new FileDecision();
				fines = fd.fileDecision(parkingfiletype, parkingfilename);	//Memoize if not already stored
			}
			double market_per_fine_per_capita = ((double) total_market_values.get(zip) / (double) fines.get(zip)) /
				(double) populations.get(zip);
			market_per_fine_per_capitas.put(zip, market_per_fine_per_capita);//Memoize if not already stored
		}
		System.out.println(Math.round(market_per_fine_per_capitas.get(zip)));
	}
}
