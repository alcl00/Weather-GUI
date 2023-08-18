package org.alcl00.weather.services;

import io.github.cdimascio.dotenv.Dotenv;
import org.alcl00.weather.models.Weather;
import org.bspfsystems.simplejson.JSONObject;
import org.bspfsystems.simplejson.parser.JSONException;
import org.bspfsystems.simplejson.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherService {

    private Dotenv dotenv = Dotenv.configure().load();
    private String API_KEY = dotenv.get("API_KEY");
    private String defaultUrl = "http://api.weatherapi.com/v1/current.json?key=" + API_KEY +"&q='";

    /**
     * Connects to api and fetches weather data using locationInput as a query
     * @param locationInput - Location to get current weather report
     * @return Weather info
     */
    public Weather fetchWeather(String locationInput) throws RuntimeException{

        try {

            URL url = new URL(defaultUrl + locationInput.replaceAll("\s", "%20"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if(responseCode != 200) {
                throw new RuntimeException("Weather info for location " + locationInput + " could not be found.");
            }
            else {
                String informationString = getWeatherInformation(url);
                JSONObject dataObject = JSONParser.deserializeObject(informationString);
                System.out.println("Data: " + dataObject.toString());
                JSONObject locationObject = dataObject.getObject("location");
                System.out.println("Location data: " + locationObject.toString());
                JSONObject weatherObject = dataObject.getObject("current");
                JSONObject conditionObject = weatherObject.getObject("condition");

                String location = locationObject.getString("name");
                String region = locationObject.getString("region");
                String country = locationObject.getString("country");
                String text = conditionObject.getString("text");
                String tempInC = weatherObject.getString("temp_c");
                String tempInF = weatherObject.getString("temp_f");

                return new Weather(location, region, country, text, tempInC, tempInF);
                
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Iterates through API response to gather information in a String
     * @param url - URL connection to API
     * @return Weather information as a string
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
