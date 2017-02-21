package com.hl.tools;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CollectionHelper {

	public static Map<String, Integer> sortMapByValues(Map<String, Integer> aMap) {

		Set<Entry<String, Integer>> mapEntries = aMap.entrySet();

		System.out.println("Values and Keys before sorting ");
		for (Entry<String, Integer> entry : mapEntries) {
			System.out.println(entry.getValue() + " - " + entry.getKey());
		}

		// used linked list to sort, because insertion of elements in linked
		// list is faster than an array list.
		List<Entry<String, Integer>> aList = new LinkedList<Entry<String, Integer>>(
				mapEntries);

		// sorting the List
		Collections.sort(aList, new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> ele1,
					Entry<String, Integer> ele2) {

				return ele2.getValue().compareTo(ele1.getValue());
			}
		});

		// Storing the list into Linked HashMap to preserve the order of
		// insertion.
		Map<String, Integer> aMap2 = new LinkedHashMap<String, Integer>();
		for (Entry<String, Integer> entry : aList) {
			aMap2.put(entry.getKey(), entry.getValue());
		}

		// printing values after soring of map
		System.out.println("Value " + " - " + "Key");
		for (Entry<String, Integer> entry : aMap2.entrySet()) {
			System.out.println(entry.getValue() + " - " + entry.getKey());
		}

		return aMap2;

	}
	
	public static Map<String, Float> sortMapByValuesFloat(Map<String, Float> aMap) {

		Set<Entry<String, Float>> mapEntries = aMap.entrySet();

		//System.out.println("Values and Keys before sorting ");
		//for (Entry<String, Float> entry : mapEntries) {
		//	System.out.println(entry.getValue() + " - " + entry.getKey());
		//}

		// used linked list to sort, because insertion of elements in linked
		// list is faster than an array list.
		List<Entry<String, Float>> aList = new LinkedList<Entry<String, Float>>(
				mapEntries);

		// sorting the List
		Collections.sort(aList, new Comparator<Entry<String, Float>>() {

			@Override
			public int compare(Entry<String, Float> ele1,
					Entry<String, Float> ele2) {

				return ele2.getValue().compareTo(ele1.getValue());
			}
		});

		// Storing the list into Linked HashMap to preserve the order of
		// insertion.
		Map<String, Float> aMap2 = new LinkedHashMap<String, Float>();
		for (Entry<String, Float> entry : aList) {
			aMap2.put(entry.getKey(), entry.getValue());
		}

		// printing values after soring of map
		//System.out.println("Value " + " - " + "Key");
		//for (Entry<String, Float> entry : aMap2.entrySet()) {
		////	System.out.println(entry.getValue() + " - " + entry.getKey());
		//}

		return aMap2;

	}
}
