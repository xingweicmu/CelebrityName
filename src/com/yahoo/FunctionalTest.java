package com.yahoo;

/**
 * This class is the functional test for this program. It simulate how users are
 * going to use the program: specifying the input and output file. The program
 * read in the input file and write the result to the output file. It covers the
 * following test cases:
 * 
 * 1. input file with valid names;
 * 2. input file with names with illegal format;
 * 3. empty input file;
 * 4. input file with case-sensitive names;
 * 
 * @author xingwei
 * @version 1.0
 *
 */
public class FunctionalTest {
	public static void main(String[] args) {

		System.out.println("Starting functional test...\n");

		CelebrityName test = new CelebrityName();

		System.out.println("1. Testing input file with valid input");
		System.out.println("Start testing with inputfile1...");
		test.play("inputfile1.txt", "outputfile1.txt");
		System.out.println("Testing Done.\n");

		System.out.println("2. Testing input file with illegal input");
		System.out.println("Start testing with inputfile2...");
		test.play("inputfile2.txt", "outputfile2.txt");
		System.out.println("Testing Done.\n");

		System.out.println("3. Testing input file with empty input");
		System.out.println("Start testing with inputfile3...");
		test.play("inputfile3.txt", "outputfile3.txt");
		System.out.println("Testing Done.\n");

		System.out.println("4. Testing input file with Case-Sensitive Characters");
		System.out.println("Start testing with inputfile4...");
		test.play("inputfile4.txt", "outputfile4.txt");
		System.out.println("Testing Done.\n");
	}
}
