package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadProperties {
	
	public ArrayList<Integer> readProperties(int selected_option, String propertiesfilename, int zipcode) throws IOException {
		
		/*
		 * If file does not exist or can not be read
		 */
		File checkfile = new File(propertiesfilename);
		if ((checkfile.canRead()) && (checkfile.exists()) == false) {
			System.out.println("Error text file does not exist or can not be read");
			System.exit(0);
		}
		
		/*
		 * File readers that improve efficiency
		 */

		FileInputStream fileInputStream = new FileInputStream(propertiesfilename);
		BufferedReader i = new BufferedReader(new InputStreamReader(fileInputStream));
		
		//@Cayde try/catch probably better here
		
		
		String firstlinerow = " ";
		firstlinerow = i.readLine();
		String nextline;
		String firstline[] = firstlinerow.split(",");
		int zip_code_column = 0, livable_area_column = 0, market_value_column = 0;
		
		/*
		 * Identify columns with relevant variables
		 */
		
		for (int j = 0; j < firstline.length; j++) {
			if (firstline[j].equals("zip_code")) {
				zip_code_column = j;
			}
			else if (firstline[j].equals("total_livable_area")) {
				livable_area_column = j;
			}
			else if (firstline[j].equals("market_value")) {
				market_value_column = j;
			}
		}
		int livable_area = 0, market_value = 0;
		ArrayList<Integer> market_values = new ArrayList<Integer>();
		ArrayList<Integer> livable_areas = new ArrayList<Integer>();
		HashMap<Integer, ArrayList<Integer>> all_market_values = new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, ArrayList<Integer>> all_livable_areas = new HashMap<Integer, ArrayList<Integer>>();
		
		/*
		 * If Option 4, store relevant livable areas for separate processing
		 */
		
		
		/*
		 * @Cayde I put if statement for which question it was which info would be read,
		 * probably better in the strategy design pattern
		 */
		
		if (selected_option == 4) {
			while((nextline = i.readLine()) != null) {  
				String[] line = nextline.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");  //specialized tokenizer
		
		/*
		 * First checks zipcode has 5 characters before getting substring
		 * and compares substring to inputted zipcode		
		 */
				
				if ((line[zip_code_column].length() > 5) && 
					((int) Double.parseDouble (line[zip_code_column].substring(0,5)) == zipcode)) {
					if (isNumeric(line[livable_area_column])) {
						livable_area = (int) Double.parseDouble(line[livable_area_column]);
						livable_areas.add(livable_area);
					}
				} 
			} 
			return livable_areas;
		}
		
		/*
		 * If Option 3,5,or 6 store relevant market values for separate processing
		 */
		
		else if ((selected_option == 3) || (selected_option == 5) || (selected_option == 6)) {
			while((nextline = i.readLine()) != null) {  
				String[] line = nextline.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");  //specialized tokenizer
				if ((line[zip_code_column].length() > 5) && 
					((int) Double.parseDouble (line[zip_code_column].substring(0,5)) == zipcode)) {
					if (isNumeric(line[market_value_column])) {
						market_value = (int) Double.parseDouble(line[market_value_column]);
						market_values.add(market_value);
					}
				}
			}
			return market_values;
		}	
		i.close();
		return market_values;
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
