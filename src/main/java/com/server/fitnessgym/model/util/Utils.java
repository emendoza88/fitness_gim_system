package com.server.fitnessgym.model.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class Utils {
	
	private static final DateTimeFormatter dateTimeformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat  simpleformatDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DecimalFormat numberFormatter = new DecimalFormat("###,###.##");
	
	public static String dateToString(TemporalAccessor date) {
		return dateTimeformatter.format(date);
	}
	
	public static String dateToString(Date date) {
		return simpleformatDateFormatter.format(date);
	}
	
	public static String dateTimeToString(Timestamp date) {
		return simpleformatDateFormatter.format(date);
	}
	
	public static String numberToString(Number number) {
		return numberFormatter.format(number);
	}
}