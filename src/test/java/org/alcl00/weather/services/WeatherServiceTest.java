package org.alcl00.weather.services;

import org.alcl00.weather.models.Weather;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherServiceTest {

    WeatherService weatherService = new WeatherService();
    @Test
    public void testFetchWeatherWithCityName() {
        String city = "London";
        Weather result = weatherService.fetchWeather(city);
        assertInstanceOf(Weather.class, result);
        assertEquals(result.getTown(), city);
    }

    @Test
    public void testFetchWeatherWithUKPostCode() {
        String city = "London";
        String postcode = "SW1A2AB";
        Weather result = weatherService.fetchWeather(postcode);
        assertInstanceOf(Weather.class, result);
        assertEquals(result.getTown(), city);
    }

    @Test
    public void testFetchWeatherWithUSZipCode() {
        String city = "New York";
        String zipCode = "10001";
        Weather result = weatherService.fetchWeather(zipCode);
        assertInstanceOf(Weather.class, result);
        assertEquals(result.getTown(), city);
    }

    @Test
    public void testFetchWeatherWithCanadaPostalCode() {
        String city = "Toronto";
        String postalCode = "M5V3L9";
        Weather result = weatherService.fetchWeather(postalCode);
        assertInstanceOf(Weather.class, result);
        assertEquals(result.getTown(), city);
    }

    @Test
    public void testFetchWeatherInvalidInput() {
        String location = "Erinsborough";
        assertThrows(
                RuntimeException.class,
                () -> weatherService.fetchWeather(location),
                "Weather info for location Erinsborough could not be found."
        );
    }
}
