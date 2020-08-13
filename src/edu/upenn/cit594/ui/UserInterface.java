package edu.upenn.cit594.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.upenn.cit594.datamanagement.Questions;
import edu.upenn.cit594.main.Main;

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
		System.out.println("5: Average total residential market value per capita for "
				+ "a specified zipcode");
		System.out.println("6: Custom! Ratio of average total residential market value "
				+ "per capita to average fine per capita");

		Scanner scanner = new Scanner(System.in);

		Integer selectedOption = -1;
		/*
		 * get selected option from user
		 */
		try {
			selectedOption = scanner.nextInt();
		} catch(InputMismatchException e) {
			System.out.println("Selected option is outside of range (0-6).");
			System.exit(0);
		}
		
		/*
		 * error check for inputs ouside of range
		 */
		if (selectedOption > 6 || selectedOption < 0) {
			System.out.println("Selected option is outside of range (0-6).");
			System.exit(0);
		}
		Questions question = new Questions();

		//Select the method corresponding to the selection
		if(selectedOption == 0) {
			scanner.close();
			System.exit(0);
		}
		else if (selectedOption == 1) {
			question.q1TotalPopulation(Main.populationFileName);
		}
		else if (selectedOption == 2) {
			question.q2TotalFinesPerCapita(Main.fileType, Main.parkingFileName, 
					Main.populationFileName);
		}
		else if (selectedOption == 3) {
			int zip = getZipCode();
			question.q3AverageMarketValue(Main.propertiesFileName, zip);
		}
		else if (selectedOption == 4) {
			int zip = getZipCode();
			question.q4AverageLivableArea(Main.propertiesFileName, zip);
		}
		else if(selectedOption == 5) {
			int zip = getZipCode();
			question.q5TotalMarketValuePerCapita(Main.propertiesFileName, 
					Main.populationFileName, zip);
		}
		else {
			int zip = getZipCode();
			question.q6TotalMarketValuePerTotalFinesPerCapita(Main.fileType, Main.propertiesFileName, 
					Main.populationFileName, Main.parkingFileName, zip);
		}
		start();


	}

	/*
	 * Helper method to get zipcode from user
	 */
	private int getZipCode() {
		System.out.println("Please pick a zipcode");
		Scanner scanner = new Scanner(System.in);
		int zip = 0;
		try {
			zip = scanner.nextInt();
		}
		catch(InputMismatchException e) {
			System.out.println("The zipcode input is not in the correct 5 digit format.");
			System.exit(0);
		}
		return zip;
	}


}
