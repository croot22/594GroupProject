package edu.upenn.cit594.data;

import java.util.LinkedList;

public class ZipCodeData {
	
	//data to be memoized for each zipcode
	public int zipCode = 0;
	public double population = 0;
	public LinkedList<Integer> fines = new LinkedList<Integer>();
	public LinkedList<Integer> livableArea = new LinkedList<Integer>();
	public LinkedList<Integer> marketValue = new LinkedList<Integer>();
	public double totalFines = 0;
	public double totalFinesPerCapita = 0;
	public double totalMarketValue = 0;
	public double averageMarketValue = 0;
	public double totalLivableArea = 0;
	public double averageLivableArea = 0;
	public int households = 0;
	public double marketValuePerCapita = 0;
	public double marketValuePerFinePerCapita = 0;
	
}

