package edu.upenn.cit594.data;

import java.util.LinkedList;

public class OverallData {
	
	//overall stats
	public static double totalMarketValues = 0 ;
	public static double totalLivableAreas = 0 ;
	public static double averageMarketValues = 0 ;
	public static double averageLivableAreas = 0 ;
	public static double marketPerCapitas = 0 ;
	public static double marketPerFinePerCapitas = 0 ;
	public static int totalPopulation = 0;
	
	//checks for individual zip memoization
	public static boolean averageFinesPerCapitaStored = false;
	public static boolean finesStored = false;
	public static boolean totalFinesStored = false;
	public static boolean finesPerCapitaStored = false;

}
