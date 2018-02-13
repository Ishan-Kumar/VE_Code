//this class holds all the application constants
package com.base.Initialisers;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Constants {
	
	//Browser :- Select Browsers Firefox/ Chrome
	public static final String browser = "Chrome";
	
	//Application Url
	public static final String urlArma = "https://store.fashionunited.com/nl/nl/locators/arma";
	public static final String urlNono = "https://store.fashionunited.com/nl/nl/locators/nono";
	
	// BUILD GENERATOR
	static Calendar c = Calendar.getInstance();
	static SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy.HHmmss");
	static String build = sdf.format(c.getTime());
	public static final String BUILD = build;





}
