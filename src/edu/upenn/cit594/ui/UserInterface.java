package edu.upenn.cit594.ui;

import java.util.InputMismatchException;
import java.util.Scanner;
import edu.upenn.cit594.processor.Questions;

public class UserInterface {

	protected Scanner scanner;


	public void start() {

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

		scanner = new Scanner(System.in);
		int selectedOption = -1;
		try {
			selectedOption = scanner.nextInt();
			while (selectedOption > 6 || selectedOption < 0) {
				System.out.println("Please input a number corresponding to one of the options.");
				selectedOption = scanner.nextInt();
			}
		} catch(InputMismatchException e) {
			System.out.println("Please input a number corresponding to one of the options.");
		}
		scanner.close();

		if(selectedOption == 0) {
			System.exit(0);
		}
		else if (selectedOption == 1) {
			question.q1TotalPopulation(populationFileName);
		}
		else if (selectedOption == 2) {
			
		}
		else if (selectedOption == 3) {
			
		}
		else if (selectedOption == 4) {
			
		}
		else if(selectedOption == 5) {
			
		}
		else {
			
		}
	}

	/*
	 * Select the method corresponding the selection
	 * passing necessary files for each
	 */
	public static int getZipCode() {
		System.out.println("Please pick a zipcode");
		Scanner scanner = new Scanner(System.in);
		int zip = 0;
		zip = scanner.nextInt();
		scanner.close();
		return zip;
	}
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

		selectedOption = start();
		followOption(selectedOption, parkingFileType, parkingFileName, propertiesFileName, populationFileName);

	}


}
