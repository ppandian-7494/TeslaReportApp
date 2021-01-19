package com.iptech.tesla;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
	public List<SalesData> loadSalesData (String fileName) {
		List<SalesData> salesData = new ArrayList<>();
		BufferedReader reader = null;
		try {
			try {
//				read the header
				reader = new BufferedReader(new FileReader(fileName));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			String line;
			try {
				line = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				while((line = reader.readLine()) != null) {
					String[] values = line.split(",");
					salesData.add(new SalesData(values[0], values[1]));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return salesData;
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
