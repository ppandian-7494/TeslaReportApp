package com.iptech.tesla;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TeslaReportApp {

	public static void main(String[] args) {
		CSVReader reader = new CSVReader();
		List<SalesData> model3SalesData = reader.loadSalesData("model3.csv");
		List<SalesData> modelSSalesData = reader.loadSalesData("modelS.csv");
		List<SalesData> modelXSalesData = reader.loadSalesData("modelX.csv");
		
		createReport(model3SalesData, "Model 3");
		createReport(modelSSalesData, "Model S");
		createReport(modelXSalesData, "Model X");
	}

	private static void createReport(List<SalesData> carSalesData, String modelType) {
		System.out.println(modelType + " Yearly Sales Report");
		System.out.println("---------------------------");
		
		Map<Integer, List<SalesData>> salesDataGroupedByYear = carSalesData.stream()
				.collect(Collectors.groupingBy((d -> d.getDate().getYear())));
		
		String totalYearlySales = salesDataGroupedByYear.entrySet()
				.stream()
				.map(d -> d.getKey() + " -> " + d.getValue().stream()
						.collect(Collectors.summingInt(SalesData::getSales)))
				.collect(Collectors.joining("\n"));
		
		System.out.println(totalYearlySales);
		System.out.println("");
		
		Optional<SalesData> maxSalesData = carSalesData.stream()
						.max((SalesData o1, SalesData o2) -> o1.getSales().compareTo(o2.getSales()));
		Optional<SalesData> minSalesData = carSalesData.stream()
						.min((SalesData o1, SalesData o2) -> o1.getSales().compareTo(o2.getSales()));
		
		System.out.println("The best month for " + modelType + " was: " + maxSalesData.orElse(new SalesData("Jan-01", "0")).getDate());
		System.out.println("The worst month for " + modelType + " was: " + minSalesData.orElse(new SalesData("Jan-01", "0")).getDate());
		System.out.println("");
	}
}
