package com.apiassidgment.tests;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Test1  
{
	@Test(priority =1)
	public void testUserId9()
	{
		 int count = 0;
		//Specify the base URL
		RestAssured.baseURI = "http://jsonplaceholder.typicode.com/";
		
		//Create request object
		RequestSpecification httpRequest = RestAssured.given();
		
		//Create the response object
		Response response = httpRequest.request(Method.GET, "posts");
		
		//get  status Code
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		Reporter.log("Status Code is : "+statusCode,true);
		
		//get response body as String
		String responseBody = response.getBody().asString();
		String[] eachpost = responseBody.split(",");
		//Verify How many post did a user with user ID 9 as published
		for (String post : eachpost) 
		{
			if(post.contains("\"userId\": 9"))
			{
				count++;
			}
		}
		Reporter.log(count +" posts as published by the user with user ID 9.",true);
		
	}
	@Test(priority = 2)
	public void testUserId9_two() throws ParseException
	{
		 int count = 0;
		//Specify the base URL
		RestAssured.baseURI = "http://jsonplaceholder.typicode.com/";
		
		//Create request object
		RequestSpecification httpRequest = RestAssured.given();
		
		//Create the response object
		Response response = httpRequest.request(Method.GET, "posts?userId=9");
		
		//get  status Code
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		Reporter.log("Status Code is : "+statusCode,true);
		
		//get response body as String
		JSONParser jp = new JSONParser();
		JSONArray ja = (JSONArray) jp.parse(response.getBody().asString());
		count = ja.size();
		
		Reporter.log(count +" posts as published by the user with user ID 9.",true);
		
	}

}
