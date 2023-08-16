package org.alcl00.weather;


import org.alcl00.weather.models.Weather;
import org.alcl00.weather.services.WeatherService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Please enter a location: ");
            String locationInput = in.readLine();

            WeatherService weatherService = new WeatherService();
            Weather weather = weatherService.fetchWeather(locationInput);
            String location = weather.getTown();
            String region = weather.getRegion();
            String country = weather.getCountry();
            String text = weather.getText();
            String tempInC = weather.getTempInC();
            String tempInF = weather.getTempInF();

            System.out.println("It is " + text + " in " + location + ", " + region + ", " + country);
            System.out.println("The current temperature is " + tempInF + " Fahrenheit, " + tempInC + " Celsius");

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}