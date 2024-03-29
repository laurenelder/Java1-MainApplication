// Devin "Lauren" Elder
// Java 1 Week 2 Project
package com.DevinElder.java1mainapplication.classData;


public class Cities {
	
	public String city;
	public String summary;
	public String icon;
	public int rainChance;
	public float temp;
	public float wind;
	public timeZone currentTimeZone;
	public 
	static enum timeZone {
		EDT,
		CDT,
		MDT,
		MST,
		PDT
	};

	public Cities(String thisCity, String thisSummary, String thisTimeZone, 
			String thisIcon, int thisRainChance, int thisTemp, int thisWind) {
		// TODO Auto-generated constructor stub
		this.city = thisCity;
		this.summary = thisSummary;
		this.icon = thisIcon;
		this.rainChance = thisRainChance;
		this.temp = thisTemp;
		this.wind = thisWind;
		if (thisTimeZone == "America/New_York") {
			this.currentTimeZone = timeZone.EDT;
		}
		if (thisTimeZone == "America/Los_Angeles") {
			this.currentTimeZone = timeZone.PDT;	
				}
		if (thisTimeZone == "America/Chicago") {
			this.currentTimeZone = timeZone.CDT;
		}
		if (thisTimeZone == "America/Phoenix") {
			this.currentTimeZone = timeZone.MST;
		}
		if (thisTimeZone == "America/Denver") {
			this.currentTimeZone = timeZone.MST;
		}
	}
	
	public String toString() {
		return city;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
