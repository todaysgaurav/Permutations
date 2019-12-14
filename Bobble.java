/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package bobble;

/**
 *
 * @author GaUrAv
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Bobble {

	public static void main(String[] args) {
		
		readCSV(args[0]);
	}

	static void readCSV(String filePath) {
		try {
			BufferedReader CSVFile = new BufferedReader(new FileReader(filePath));
			List<List<String>> listTowD = new ArrayList<>();
			String dataRow = CSVFile.readLine();
			// Read first line.
			// The while checks to see if the data is null. If
			// it is, we've hit the end of the file. If not,
			// process the data.

			while (dataRow != null) {
				listTowD.add(Arrays.asList(dataRow.split(",")));
				// Read next line of data.
				dataRow = CSVFile.readLine();

			}
			// Close the file once all data has been read.
			CSVFile.close();
			String[][] twoDarray = new String[listTowD.size()][];
			int index = 0;
			for (List<String> nestedList : listTowD) {
				twoDarray[index++] = nestedList.toArray(new String[nestedList.size()]);
			}
			printData(twoDarray);
		} catch (Exception e) {
			System.err.println("File Not Found readCSV");
			e.printStackTrace();
		}

	}

	static void printData(String[][] arr) {
		// number of arrays
		int n = arr.length;
		// System.out.print(arr[0][0]);
		// to keep track of next element in each of
		// the n arrays
		int[] indices = new int[n];

		// initialize with first element's index
		for (int i = 0; i < n; i++)
			indices[i] = 0;

		while (true) {

			// print current combination
			for (int i = 0; i < n; i++)
				System.out.print(arr[i][indices[i]]);

			System.out.println();
			// find the rightmost array that has more
			// elements left after the current element
			// in that array
			int next = n - 1;
			while (next >= 0 && (indices[next] + 1 >= arr[next].length))
				next--;

			// no such array is found so no more
			// combinations left
			if (next < 0)
				return;

			// if found move to next element in that
			// array
			indices[next]++;

			// for all arrays to the right of this
			// array current index again points to
			// first element
			for (int i = next + 1; i < n; i++)
				indices[i] = 0;
		}
	}

}