package org.alcl00.weather.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class WeatherTest {
    Weather weather = new Weather(
            "London",
            "City of London, Greater London",
            "United Kingdom",
            "Sunny",
            "23.0",
            "73.4"
    );

    @Test
    public void testGetTown() {
        String expected = "London";
        String result = weather.getTown();
        assertEquals(result, expected);
    }

    @Test
    public void testGetRegion() {
        String expected = "City of London, Greater London";
        String result = weather.getRegion();
        assertEquals(result, expected);
    }

    @Test
    public void testGetCountry() {
        String expected = "United Kingdom";
        String result = weather.getCountry();
        assertEquals(result, expected);
    }

    @Test
    public void testGetText() {
        String expected = "Sunny";
        String result = weather.getText();
        assertEquals(result, expected);
    }

    @Test
    public void testGetTempInC() {
        String expected = "23.0";
        String result = weather.getTempInC();
        assertEquals(result, expected);
    }

    @Test
    public void testGetTempInF() {
        String expected = "73.4";
        String result = weather.getTempInF();
        assertEquals(result, expected);
    }

}
