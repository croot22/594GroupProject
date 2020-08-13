package edu.upenn.cit594.processor;

import java.io.File;

public class ArgumentsProcessor {

	/*
	 * checks for valid file type from args
	 */
	public void checkValidType(String fileType) {
		if(!fileType.equals("json") && !fileType.equals("csv")){
			System.out.println("The file format is neither \"json\" nor \"csv.\"");
			System.exit(0);
		}
	}
	
	/*
	 * checks for valid file, if it can be read and opened
	 */
	public boolean checkValidFile(String fileName) {
		File file = new File(fileName);
		
		if (!file.canRead() || !file.exists()) {
			return false;
		}
		return true;
	}
}
