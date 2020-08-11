package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadProperties {
	
	public ArrayList<Integer> readProperties(int selectedOption, String propertiesFileName, int zipCode) throws IOException {
		
		/*
		 * If file does not exist or can not be read
		 */
		File checkFile = new File(propertiesFileName);
		if ((checkFile.canRead()) && (checkFile.exists()) == false) {
			System.out.println("Error text file does not exist or can not be read");
			System.exit(0);
		}
		
		/*
		 * File readers that improve efficiency
		 */

		FileInputStream fileInputStream = new FileInputStream(propertiesFileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
		
		//@Cayde try/catch probably better here
		
		
		String firstLineRow = " ";
		firstLineRow = reader.readLine();
		String nextLine;
		String firstLine[] = firstLineRow.split(",");
		int zipCodeColumn = 0, livableAreaColumn = 0, marketValueColumn = 0;
		
		/*
		 * Identify columns with relevant variables
		 */
		
		for (int j = 0; j < firstLine.length; j++) {
			if (firstLine[j].equals("zip_code")) {
				zipCodeColumn = j;
			}
			else if (firstLine[j].equals("total_livable_area")) {
				livableAreaColumn = j;
			}
			else if (firstLine[j].equals("market_value")) {
				marketValueColumn = j;
			}
		}
		int livableArea = 0, marketValue = 0;
		ArrayList<Integer> marketValues = new ArrayList<Integer>();
		ArrayList<Integer> livableAreas = new ArrayList<Integer>();
		HashMap<Integer, ArrayList<Integer>> allMarketValues = new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, ArrayList<Integer>> allLivableAreas = new HashMap<Integer, ArrayList<Integer>>();
		
		/*
		 * If Option 4, store relevant livable areas for separate processing
		 */
		
		
		/*
		 * @Cayde I put if statement for which question it was which info would be read,
		 * probably better in the strategy design pattern
		 */
		
		if (selectedOption == 4) {
			while((nextLine = reader.readLine()) != null) {  
				String[] line = nextLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");  //specialized tokenizer
		
		/*
		 * First checks zipcode has 5 characters before getting substring
		 * and compares substring to inputted zipcode		
		 */
				
				if ((line[zipCodeColumn].length() > 5) && 
					((int) Double.parseDouble (line[zipCodeColumn].substring(0,5)) == zipCode)) {
					if (isNumeric(line[livableAreaColumn])) {
						livableArea = (int) Double.parseDouble(line[livableAreaColumn]);
						livableAreas.add(livableArea);
					}
				} 
			} 
			return livableAreas;
		}
		
		/*
		 * If Option 3,5,or 6 store relevant market values for separate processing
		 */
		
		else if ((selectedOption == 3) || (selectedOption == 5) || (selectedOption == 6)) {
			while((nextLine = reader.readLine()) != null) {  
				String[] line = nextLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");  //specialized tokenizer
				if ((line[zipCodeColumn].length() > 5) && 
					((int) Double.parseDouble (line[zipCodeColumn].substring(0,5)) == zipCode)) {
					if (isNumeric(line[marketValueColumn])) {
						marketValue = (int) Double.parseDouble(line[marketValueColumn]);
						marketValues.add(marketValue);
					}
				}
			}
			return marketValues;
		}	
		reader.close();
		return marketValues;
	}
	
	/*
	 * Simple helper method to check if value is numeric
	 */
	
	public boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
	}
}
