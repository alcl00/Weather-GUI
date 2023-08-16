package org.alcl00.weather.services;

import io.github.cdimascio.dotenv.Dotenv;
import org.alcl00.weather.models.Weather;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherService {

    private Dotenv dotenv = Dotenv.configure().load();
    private String API_KEY = dotenv.get("API_KEY");
    private String defaultUrl = "http://api.weatherapi.com/v1/current.json?key=" + API_KEY +"&q=";

    public Weather fetchWeather(String locationInput) {

        try {
            URL url = new URL(defaultUrl + locationInput);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if(responseCode != 200) {
                throw new RuntimeException("Weather info for location  " + locationInput + " could not be found.");
            }
            else {
                String informationString = getWeatherInformation(url);
                JSONParser parser = new JSONParser();
                JSONObject dataObject = (JSONObject) parser.parse(informationString);
                JSONObject locationObject = (JSONObject) dataObject.get("location");
                JSONObject weatherObject = (JSONObject) dataObject.get("current");
                JSONObject conditionObject = (JSONObject) weatherObject.get("condition");

                String location = locationObject.get("name").toString();
                String region = locationObject.get("region").toString();
                String country = locationObject.get("country").toString();
                String text = conditionObject.get("text").toString();
                String tempInC = weatherObject.get("temp_c").toString();
                String tempInF = weatherObject.get("temp_f").toString();

                return new Weather(location, region, country, text, tempInC, tempInF);
                
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Iterates through API response to gather information in a String
     * @param url
     * @return
     * @throws IOException
     */
    private static String getWeatherInformation(URL url) throws IOException {
        StringBuilder informationString = new StringBuilder();

        Scanner scanner = new Scanner(url.openStream());

        while (scanner.hasNext()) {
            informationString.append(scanner.nextLine());
        }
        scanner.close();
        return informationString.toString();
    }

}
