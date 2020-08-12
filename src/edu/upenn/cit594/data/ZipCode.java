package edu.upenn.cit594.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

public class ZipCode {
	
	public int zipCode = -1;
	public int population = -1;
	public LinkedList<Integer> fines = new LinkedList<Integer>();
	public LinkedList<Integer> livableArea = new LinkedList<Integer>();
	public LinkedList<Integer> marketValue = new LinkedList<Integer>();
	public int totalFines = -1;
	public LinkedList<Double> finesPerCapita = new LinkedList<Double>();
	public LinkedList<Double> marketValues = new LinkedList<Double>();
	public LinkedList<Double> livableAreas = new LinkedList<Double>();
	public LinkedList<Double> totalMarketValues = new LinkedList<Double>();
	public LinkedList<Double> totalLivableAreas = new LinkedList<Double>();
	public LinkedList<Double> averageMarketValues = new LinkedList<Double>();
	public LinkedList<Double> averageLivableAreas = new LinkedList<Double>();
	public LinkedList<Double> marketPerCapitas = new LinkedList<Double>();
	public LinkedList<Double> marketPerFinePerCapitas = new LinkedList<Double>();
	
	//HashMap to hold and memoize zipcode and related data
	public static HashMap<Integer, ZipCode> zipCodeMap = new HashMap<Integer, ZipCode>();
	
}

