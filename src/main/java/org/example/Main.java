package org.example;


import io.github.cdimascio.dotenv.Dotenv;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure().load();
        String API_KEY = dotenv.get("API_KEY");
        // API: http://api.weatherapi.com/v1/current.json?key=c41af58f80d14636b88175137231906&q=08520&aqi=no
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


        try {
            System.out.print("Please enter a location: ");
            String locationInput = in.readLine();
            String urlString = "http://api.weatherapi.com/v1/current.json?key=" + API_KEY + "&q=" + locationInput + "&aqi=no";

            URL url = new URL(urlString);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            // 200 OK
            if(responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            }
            else {
                StringBuilder informationString = new StringBuilder();

                Scanner scanner = new Scanner(url.openStream());

                while(scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                JSONParser parser = new JSONParser();
                JSONObject dataObject = (JSONObject) parser.parse(String.valueOf(informationString));
                JSONObject locationObject = (JSONObject) dataObject.get("location");
                JSONObject weatherObject = (JSONObject) dataObject.get("current");
                JSONObject conditionObject = (JSONObject) weatherObject.get("condition");

                //JSONArray dataObject = (JSONArray) parser.parse(String.valueOf(informationString));

                String location = locationObject.get("name").toString();
                String region = locationObject.get("region").toString();
                String country = locationObject.get("country").toString();
                String text = conditionObject.get("text").toString();
                String tempInC = weatherObject.get("temp_c").toString();
                String tempInF = weatherObject.get("temp_f").toString();

                System.out.println("It is " + text + " in " + location + ", " + region + ", " + country);
                System.out.println("The current temperature is " + tempInF + " Fahrenheit, " + tempInC + " Celsius");

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}