package com.emc.prework.domain;

public class WeatherInfo {

	private String zip;
	private int precipitation;
	private boolean waterOrNot;
	private String message;
	
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public int getPrecipitation() {
		return precipitation;
	}
	public void setPrecipitation(int precipitation) {
		this.precipitation = precipitation;
	}
	public boolean isWaterOrNot() {
		return waterOrNot;
	}
	public void setWaterOrNot(boolean waterOrNot) {
		this.waterOrNot = waterOrNot;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}	
}
