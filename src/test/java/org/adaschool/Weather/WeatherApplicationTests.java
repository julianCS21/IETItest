package org.adaschool.Weather;

import org.adaschool.Weather.controller.WeatherReportController;
import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import static org.mockito.Mockito.*;

@SpringBootTest
class WeatherApplicationTests {


	private WeatherReportController wrc;

	private WeatherReportService wrs;

	@Test
	public void WhenISendARequestShouldGiveMeAWeatherData() {
		RestTemplate restTemplate = new RestTemplate();
		wrc = Mockito.mock(WeatherReportController.class);
		String url =  "https://api.openweathermap.org/data/2.5/weather"+  "?lat=" + 44.34 + "&lon=" + 10.99 + "&appid=" + "34d12fc0d8c3e777cba621b3f4083380";
		WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);

		WeatherReport report = new WeatherReport();
		WeatherApiResponse.Main main = response.getMain();
		report.setTemperature(main.getTemperature());
		report.setHumidity(main.getHumidity());

		Mockito.when(wrc.getWeatherReport(44.34,10.99)).thenReturn(report);
		WeatherReport wr = wrc.getWeatherReport(44.34,10.99);
		Mockito.verify(wrc).getWeatherReport(44.34,10.99);

	}


	@Test
	public void WhenIUseWeatherReportServiceShouldGiveMeAWeatherData(){
		wrs = Mockito.mock(WeatherReportService.class);
		RestTemplate restTemplate = new RestTemplate();
		String url =  "https://api.openweathermap.org/data/2.5/weather"+  "?lat=" + 44.34 + "&lon=" + 10.99 + "&appid=" + "34d12fc0d8c3e777cba621b3f4083380";
		WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);

		WeatherReport report = new WeatherReport();
		WeatherApiResponse.Main main = response.getMain();
		report.setTemperature(main.getTemperature());
		report.setHumidity(main.getHumidity());

		Mockito.when(wrs.getWeatherReport(44.34,10.99)).thenReturn(report);
		WeatherReport wr = wrs.getWeatherReport(44.34,10.99);
		Mockito.verify(wrs).getWeatherReport(44.34,10.99);



	}

}
