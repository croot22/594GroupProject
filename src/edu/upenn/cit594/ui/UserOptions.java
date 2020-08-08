package edu.upenn.cit594.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import edu.upenn.cit594.processor.Questions;

public class UserOptions {

	public int userOptions() {
		
		/*
		 * Initial screen
		 */
		
		System.out.println("Would you like to play a game?");
		System.out.println("0: Exit");
		System.out.println("1: Total Population for all zipcodes");
		System.out.println("2: Total Parking fines per capita for each zipcode");
		System.out.println("3: Average market value for a specified zipcode");
		System.out.println("4: Average total livable area for a specified zipcode");
		System.out.println("5: Average total residential market value per capita for a specified zipcode");
		System.out.println("6: Custom! Ratio of average total residential market value per capita to average fine per capita");
		
		Scanner i = new Scanner(System.in);
		int selected_option = i.nextInt();
		return selected_option;
	}
	
	/*
	 * Select the method corresponding the selection
	 * passing necessary files for each
	 */
	
	/*
	 * @Cayde
	 * below method probably better in processor package
	 */
	
	public void followOption(int selected_option, String parkingfiletype, String parkingfilename, 
			String propertiesfilename, String populationfilename) throws IOException {
		Questions q = new Questions();
		if (selected_option == 0) {
			Scanner i = new Scanner(System.in);
			i.close();    // only close scanner on system exit to decrease resource leak
			System.exit(0);
		}
		else if (selected_option == 1) {    //should each of these methods be their own classes?  
			q.q1TotalPopulation(populationfilename);
		}
		else if (selected_option == 2) {
			q.q2TotalFinesPerCapita(parkingfiletype, parkingfilename, populationfilename);
		}
		else if (selected_option == 3) {
			q.q3AverageMarketValue(propertiesfilename);
		}
		else if (selected_option == 4) {
			q.q4AverageLivableArea(propertiesfilename);
		}
		else if (selected_option == 5) {
			q.q5TotalMarketValuePerCapita(propertiesfilename, populationfilename);
		}
		else if (selected_option == 6) {
			q.q6TotalMarketValuePerTotalFinesPerCapita(parkingfiletype, propertiesfilename, populationfilename, parkingfilename);
		}
		
		/*
		 * Restart cycle
		 */
		
		selected_option = userOptions();
		followOption(selected_option, parkingfiletype, parkingfilename, propertiesfilename, populationfilename);
		
	}
	
	
}
