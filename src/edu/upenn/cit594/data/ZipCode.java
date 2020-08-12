package edu.upenn.cit594.data;

import java.util.HashMap;
import java.util.LinkedList;

public class ZipCode {
	
	public int zipCode = -1;
	public int population = -1;
	public LinkedList<Integer> fines = new LinkedList<Integer>();
	public LinkedList<Integer> livableArea = new LinkedList<Integer>();
	public LinkedList<Integer> marketValue = new LinkedList<Integer>();
	
	//HashMap to hold and memoize zipcode and related data
	public static HashMap<Integer, ZipCode> zipCodeMap = new HashMap<Integer, ZipCode>();
	
}

