package com.apiassidgment.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class PrintAllUsers {
	String[] postIds;

	public JSONArray getUserPosts(String userId) throws ParseException {
		int count = 0;
		// Specify the base URL
		RestAssured.baseURI = "http://jsonplaceholder.typicode.com/";

		// Create request object
		RequestSpecification httpRequest = RestAssured.given();

		// Create the response object
		Response response = httpRequest.request(Method.GET, "/posts?userId=" + userId);

		// get response body as String
		String responseBody = response.getBody().asString();
		JSONParser jsonParser = new JSONParser();
		JSONArray jsonArray = (JSONArray) jsonParser.parse(responseBody);
		return jsonArray;
	}

	public String[] getUserPostIds(String userId) throws ParseException {

		JSONArray jsonArray = getUserPosts(userId);
		Iterator<JSONObject> iterator = jsonArray.iterator();
		postIds = new String[jsonArray.size()];
		int counter = 0;
		while (iterator.hasNext()) {
			JSONObject job = iterator.next();
			// System.out.println(job.get("id"));
			postIds[counter] = job.get("id").toString();
			counter++;
		}

		return postIds;
	}

	public int getCountOfComments(String[] postIds) {
		int totalCount = 0;
		for (int i = 0; i < postIds.length; i++) {
			int count = 0;
			// Specify the base URL
			RestAssured.baseURI = "http://jsonplaceholder.typicode.com/";

			// Create request object
			RequestSpecification httpRequest = RestAssured.given();

			// Create the response object
			Response response = httpRequest.request(Method.GET, "/posts/" + postIds[i] + "/comments");

			// get response body as String
			String responseBody = response.getBody().asString();
			String[] eachpost = responseBody.split(",");
			// Verify How many post did a user published
			for (String post : eachpost) {
				if (post.contains("\"postId\": " + postIds[i])) {
					count++;
				}
			}
			totalCount += count;
		}

		return totalCount;
	}

	@Test
	public void testPrintAllUsers() throws ParseException {
		// Specify the base URL
		RestAssured.baseURI = "http://jsonplaceholder.typicode.com/";

		// Create request object
		RequestSpecification httpRequest = RestAssured.given();

		// Create the response object
		Response response = httpRequest.request(Method.GET, "/users");

		// get status Code
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		Reporter.log("Status Code is : " + statusCode, true);

		// get response body as String
		String responseBody = response.getBody().asString();
		JSONParser jsonParser = new JSONParser();
		JSONArray jsonArray;

		jsonArray = (JSONArray) jsonParser.parse(responseBody);

		String[] userDetails = new String[4];
		Reporter.log("|User Name  |Full Name  |Email Id |Total number of posts|Total number of Commets received |",true);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = (JSONObject) jsonArray.get(i);

			userDetails[0] = jsonObject.get("id").toString();
			userDetails[1] = jsonObject.get("username").toString();
			userDetails[2] = jsonObject.get("name").toString();
			userDetails[3] = jsonObject.get("email").toString();
			int postCount = getUserPostIds(userDetails[0]).length;
			int commentsCount = getCountOfComments(getUserPostIds(userDetails[0]));
			Reporter.log("|" + userDetails[1] + "|" + userDetails[2] + "|" + userDetails[3] + "|" + postCount
					+ "|" + commentsCount + "|",true);

		}

	}
}
