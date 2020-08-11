package edu.upenn.cit594.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.upenn.cit594.main.Main;
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
		Questions question = new Questions();
		
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

		/*
		 * Select the method corresponding the selection
		 */
		if(selectedOption == 0) {
			System.exit(0);
		}
		else if (selectedOption == 1) {
			question.q1TotalPopulation(Main.populationFileName);
		}
		else if (selectedOption == 2) {
			question.q2TotalFinesPerCapita(Main.fileType, Main.parkingFileName, Main.populationFileName);
		}
		else if (selectedOption == 3) {
			question.q3AverageMarketValue(Main.propertiesFileName);
		}
		else if (selectedOption == 4) {
			question.q4AverageLivableArea(Main.propertiesFileName);
		}
		else if(selectedOption == 5) {
			question.q5TotalMarketValuePerCapita(Main.propertiesFileName, Main.populationFileName);
		}
		else {
			question.q6TotalMarketValuePerTotalFinesPerCapita(Main.fileType, Main.propertiesFileName, Main.populationFileName, Main.parkingFileName);
		}
		start();
	}


	public static int getZipCode() {
		System.out.println("Please pick a zipcode");
		Scanner scanner = new Scanner(System.in);
		int zip = 0;
		zip = scanner.nextInt();
		scanner.close();
		return zip;
	}


}
