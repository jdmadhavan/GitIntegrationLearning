package tests.rest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;


public class TC01GetCityWeather extends RESTAssuredBase{

	@BeforeTest
	public void setValues() {
		testCaseName = "Get 5 City Maximum temperature and Wind Speed,Sunset Details";
		testDescription = "Weather Details OF 5 Cities";
		nodes = "Get Weather of 5 Cities";
		authors = "Madhavan";
		category = "API";
		dataFileName = "TC01";
		dataFileType = "Excel";
	}

	@Test(dataProvider = "fetchData")
	public void getCityWeatherDetails(String file) {		
	
		Map<String,String> parameterRequest=new HashMap<String, String>();
		parameterRequest.put("q",file);
		parameterRequest.put("appid", "01bca4841fa478520c64a12db004f782");
		
		// Post the request
	//	Response response = postWithBodyAsFileAndUrl(file,"weather");
		Response response = postWithQueryParam(parameterRequest, "weather");
	// Verify the response status code
		verifyResponseCode(response, 200);	
		
		// Verify the response time
		verifyResponseTime(response, 10000);
		
		//Verify Content 		
		verifyContentWithKey(response, "sys.country", "IN");
		
		JsonPath jsonPath = response.jsonPath();
		float MaximumTemp = jsonPath.get("main.temp_max");
		float WindSpead=jsonPath.get("wind.speed");
		int sunSet= jsonPath.get("sys.sunset");
        String cityName= jsonPath.get("name");
		
		System.out.println("Maximum Temprature---------> "+cityName);
		System.out.println("Maximum Temprature---------> "+MaximumTemp);
		System.out.println("Wind Speed ----------------> "+WindSpead);
		System.out.println("SunSet--------------------> "+sunSet);
		System.out.println("********************************");
		
		
		
		
		
	}


}




