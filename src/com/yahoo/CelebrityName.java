package com.yahoo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This program simulate playing the celebrity name game that generates a
 * name-chain from the first and last names of people. It finds the longest name
 * chain for a given input file and write the result to a output file.
 * 
 * @author xingwei
 * @version 1.0
 *
 */
public class CelebrityName {

	/**
	 * The main function to run this program. As an example, here it takes
	 * generated.txt as input file and output.txt as the output file.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		CelebrityName test = new CelebrityName();
		test.play("generated.txt", "output.txt");
	}

	/**
	 * Start point to run the program. It first loads the input file, finds the
	 * longest name chain and then writes the result to the output file.
	 * 
	 * @param inputfile
	 *            the filename of input file
	 * @param outputfile
	 *            the filename of output file
	 */
	public void play(String inputfile, String outputfile) {
		System.out.println("[Running Info] Loading input file...");
		String[] testSet = loadFile(inputfile);
		System.out.println("[Running Info] Finding the longest name chain...");
		try {
			ArrayList<String> result = findLongestNameChain(testSet);
			System.out.println("[Running Info] Writing to the output file...");
			writeFile(result, outputfile);
			System.out.println("[Running Info] Written Done!");
		} catch (Exception e) {
			System.out.println("[Running Info] Invalid input. Please check your input file.");
		}

	}

	/**
	 * Write data from memory to output file.
	 * 
	 * @param result
	 *            the arrayList of String
	 * @param outputfile
	 *            the filename of output file
	 */
	public void writeFile(ArrayList<String> result, String outputfile) {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputfile), "utf-8"))) {
			for (String s : result) {
				writer.write(s + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Read the input file and load the data to the memory.
	 * 
	 * @param filename
	 * @return
	 */
	public String[] loadFile(String filename) {
		ArrayList<String> arrayList = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String sCurrentLine = "";
			while ((sCurrentLine = br.readLine()) != null) {
				arrayList.add(sCurrentLine.trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] result = new String[arrayList.size()];
		int i = 0;
		for (String s : arrayList) {
			result[i] = s;
			i++;
		}
		return result;
	}

	/**
	 * Find the longest name chains given a String array.
	 * 
	 * @param names
	 *            a String array containing names(first name and last name)
	 * @return an arrayList of String containing the longest name chains
	 */
	public ArrayList<String> findLongestNameChain(String[] names) {
		// First load all names to hashmap
		Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		for (int i = 0; i < names.length; i++) {
			String[] parts = names[i].split(" ");
			String firstName = parts[0];
			String lastName = parts[1];
			if (map.containsKey(firstName)) {
				map.get(firstName).add(lastName);
			} else {
				ArrayList<String> lastNameList = new ArrayList<String>();
				lastNameList.add(lastName);
				map.put(firstName, lastNameList);
			}
		}

		// For each of the entry in the hashmap, try to find the longest name
		// chains starting with it, then store them into an arrayList.
		ArrayList<String> result = new ArrayList<String>();
		for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
			String key = entry.getKey();
			ArrayList<String> value = entry.getValue();
			helper(map, value, new StringBuilder(key), result);
		}

		// Go through the arrayList of longest name chains to find the longest
		// ones, and copy them to maxArray to return.
		String maxString = "";
		int max = -1;
		for (String one : result) {
//			System.out.println(one);
			if (one.split(" ").length >= max) {
				maxString = one;
				max = one.split(" ").length;
			}
		}
		ArrayList<String> maxArray = new ArrayList<String>();
		for (String one : result) {
			if (one.split(" ").length == max) {
				maxArray.add(one);
			}
		}
		return maxArray;
	}

	/**
	 * The recursion method to build up the name chain for a given starting
	 * String.
	 * 
	 * @param map
	 *            the map containing all the loaded names
	 * @param array
	 *            the array contain the last name
	 * @param sb
	 *            the StringBuilder for the first name
	 * @param result
	 *            the arrayList containing all the name chains
	 */
	public void helper(Map<String, ArrayList<String>> map, ArrayList<String> array, StringBuilder sb,
			ArrayList<String> result) {
		String lastStatus = sb.toString();
		for (String s : array) {
			sb = new StringBuilder(lastStatus);
			// If the map has a first name which is same to the String s
			// !lastStatus.contains(s) is used to avoid circle in the name chain
			if (!lastStatus.contains(s) && map.containsKey(s)) {
				sb.append(" " + s);
				helper(map, map.get(s), sb, result);
			} else {
				lastStatus = sb.toString();
				if (!lastStatus.contains(s))	// To avoid circle in name chain
					sb.append(" " + s);
//				System.out.println(sb.toString());
				result.add(sb.toString());	//TODO
			}
		}
	}
}
