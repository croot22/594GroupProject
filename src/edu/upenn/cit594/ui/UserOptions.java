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
		
		Scanner scanner = new Scanner(System.in);
		int selectedOption = scanner.nextInt();
		return selectedOption;
	}
	
	/*
	 * Select the method corresponding the selection
	 * passing necessary files for each
	 */
	
	/*
	 * @Cayde
	 * below method probably better in processor package
	 */
	
	public void followOption(int selectedOption, String parkingFileType, String parkingFileName, 
			String propertiesFileName, String populationFileName){
		Questions question = new Questions();
		if (selectedOption == 0) {
			Scanner i = new Scanner(System.in);
			i.close();    // only close scanner on system exit to decrease resource leak
			System.exit(0);
		}
		else if (selectedOption == 1) {    //should each of these methods be their own classes?  
			question.q1TotalPopulation(populationFileName);
		}
		else if (selectedOption == 2) {
			question.q2TotalFinesPerCapita(parkingFileType, parkingFileName, populationFileName);
		}
		else if (selectedOption == 3) {
			question.q3AverageMarketValue(propertiesFileName);
		}
		else if (selectedOption == 4) {
			question.q4AverageLivableArea(propertiesFileName);
		}
		else if (selectedOption == 5) {
			question.q5TotalMarketValuePerCapita(propertiesFileName, populationFileName);
		}
		else if (selectedOption == 6) {
			question.q6TotalMarketValuePerTotalFinesPerCapita(parkingFileType, propertiesFileName, populationFileName, parkingFileName);
		}
		
		/*
		 * Restart cycle
		 */
		
		selectedOption = userOptions();
		followOption(selectedOption, parkingFileType, parkingFileName, propertiesFileName, populationFileName);
		
	}
	
	
}
