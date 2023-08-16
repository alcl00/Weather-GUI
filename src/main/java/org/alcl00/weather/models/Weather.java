package org.alcl00.weather.models;

public class Weather {

    private String town;
    private String region;
    private String country;
    private String text;
    private String tempInC;
    private String tempInF;

    public Weather(String town, String region, String country, String text, String tempInC, String tempInF) {
        this.town = town;
        this.region = region;
        this.country = country;
        this.text = text;
        this.tempInC = tempInC;
        this.tempInF = tempInF;
    }

    public String getTown() {
        return town;
    }

    public String getRegion() {
        return region;
    }

    public String getCountry() {
        return country;
    }

    public String getText() {
        return text;
    }

    public String getTempInC() {
        return tempInC;
    }

    public String getTempInF() {
        return tempInF;
    }
}
