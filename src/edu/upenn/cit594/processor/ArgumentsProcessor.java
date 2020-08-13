package edu.upenn.cit594.processor;

import java.io.File;

public class ArgumentsProcessor {

	public void checkValidType(String fileType) {
		if(!fileType.equals("json") && !fileType.equals("text")){
			System.out.println("The file format is neither \"json\" nor \"text.\"");
			System.exit(0);
		}
	}
	
	public boolean checkValidFile(String fileName) {
		File file = new File(fileName);
		
		if (!file.canRead() || !file.exists()) {
			return false;
		}
		return true;
	}
}
