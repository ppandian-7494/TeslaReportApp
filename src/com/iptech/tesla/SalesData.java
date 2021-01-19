package com.iptech.tesla;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class SalesData {
	private YearMonth date;
	private Integer sales;
	
	public YearMonth getDate() {
		return date;
	}

	public Integer getSales() {
		return sales;
	}

	
	public void setDate(YearMonth date) {
		this.date = date;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	
	public SalesData(String date, String sales) {
		this.date = YearMonth.parse(date, DateTimeFormatter.ofPattern("MMM-yy"));
		this.sales = Integer.parseInt(sales);
	}
}
