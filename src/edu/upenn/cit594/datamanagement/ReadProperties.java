package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import edu.upenn.cit594.data.OverallData;
import edu.upenn.cit594.data.ZipCodeData;
import edu.upenn.cit594.logging.Logging;

public class ReadProperties {

	public void readProperties(int selectedOption, 
			String propertiesFileName, int zipCode) {


		//log relevant info
		Logging logger = Logging.getInstance();
		String currentTime = logger.getCurrentTime();
		String logMessage = currentTime +" "+ propertiesFileName;
		logger.log(logMessage);

		/*
		 * File readers that improve efficiency
		 */

		try {
			FileInputStream fileInputStream = new FileInputStream(propertiesFileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));


			String firstLineRow = " ";
			firstLineRow = reader.readLine();
			String nextLine;
			String firstLine[] = firstLineRow.split(",");
			int zipCodeColumn = 0, livableAreaColumn = 0, marketValueColumn = 0;
			int marketValue = 0, livableArea = 0;
			ZipCodeData zip = new ZipCodeData();
			if(OverallData.zipCodeMap.containsKey(zipCode)) {
				zip = OverallData.zipCodeMap.get(zipCode);
			}


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


			/*
			 * If Option 4, store relevant livable areas for separate processing
			 */


			if (selectedOption == 4) {
				zip.households = 0;
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
							zip.livableArea.add(livableArea);
							zip.households += 1;
						}
					} 
				} 

			}

			/*
			 * If Option 3,5,or 6 store relevant market values for separate processing
			 */

			else if ((selectedOption == 3) || (selectedOption > 4)) {
				zip.households = 0;
				while((nextLine = reader.readLine()) != null) {  
					
					String[] line = nextLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");  //specialized tokenizer
					if ((line[zipCodeColumn].length() >= 5) && 
							((int) Double.parseDouble (line[zipCodeColumn].substring(0,5)) == zipCode)) {
						if (isNumeric(line[marketValueColumn])) {
							marketValue = (int) Double.parseDouble(line[marketValueColumn]);

							zip.marketValue.add(marketValue);
							zip.households += 1;
						}
					}
				}

			}	
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


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
