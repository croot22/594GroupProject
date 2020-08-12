package edu.upenn.cit594.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

public class ZipCodeData {
	
	public int zipCode = 0;
	public int population = 0;
	public LinkedList<Integer> fines = new LinkedList<Integer>();
	public LinkedList<Integer> livableArea = new LinkedList<Integer>();
	public LinkedList<Integer> marketValue = new LinkedList<Integer>();
	public int totalFines = 0;
	public double totalFinesPerCapita = 0;
	public double totalMarketValue = 0;
	public double averageMarketValue = 0;
	public double totalLivableAreas = 0;
	
	

	
	//HashMap to hold and memoize zipcode and related data
	public static HashMap<Integer, ZipCodeData> zipCodeMap = new HashMap<Integer, ZipCodeData>();
	
}

