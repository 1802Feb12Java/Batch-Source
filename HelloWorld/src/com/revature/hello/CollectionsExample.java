package com.revature.hello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CollectionsExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> sList = new ArrayList<String>();
		sList.add("First String");
		sList.addAll(Arrays.asList(new String[]{"Hello", "World", "THis", "is an array"}));
		
		System.out.println(sList);
		
		Iterator<String> sIter = sList.iterator();
		
		Map<Integer, String> myMap = new TreeMap<Integer, String>();
		
		while(sList.iterator().hasNext()){
			
			System.out.println("The next String is " + sIter.next());
			
		}

	}

}
