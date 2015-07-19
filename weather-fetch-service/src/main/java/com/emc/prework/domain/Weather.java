package com.emc.prework.domain;

public class Weather {

    public String zip;
    public int precipitation;
    
    public Weather() {
    }
    
    public Weather(String zip, int precipitation) {
        this.zip = zip;
        this.precipitation = precipitation;
    }

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
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + precipitation;
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Weather other = (Weather) obj;
		if (precipitation != other.precipitation)
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}

    @Override
    public String toString() {
        return zip + ": " + precipitation + "% precipitation";
    }
}
