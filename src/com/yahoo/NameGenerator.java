package com.yahoo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is the class used to generate names(first and last name). The idea is
 * every time we generate two random numbers from 1 to 110. We then use the two
 * number as indexes to retrieve the names from a name pool to compose a legal
 * name (containing first and last name). In this way, we could easily get a
 * name list which contains name chains.
 * 
 * @author xingwei
 * @version 1.0
 *
 */
public class NameGenerator {
	
	/**
	 * The entry point to start the program.
	 * @param args parameters
	 */
	public static void main(String[] args) {
		NameGenerator generator = new NameGenerator();

		// Load the source file
		String[] sourceArray = generator.loadFile("source");
		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream("generated.txt"), "utf-8"))) {

			// Randomly generate 100 names (including first name and last name)
			for (int i = 0; i < 100; i++) {
				// To generate two random number without duplication
				Random random = new Random();
				int first = random.nextInt(110) + 1;
				int second = -1;
				while (second == -1 || first == second) {
					second = random.nextInt(110) + 1;
				}
				
				// Write the name to the target file
				writer.write(sourceArray[first - 1] + " " + sourceArray[second - 1] + "\n");
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
				arrayList.add(sCurrentLine);
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
}
