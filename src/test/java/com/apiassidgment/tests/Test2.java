package com.apiassidgment.tests;

import java.util.Iterator;

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
import io.restassured.specification.RequestSpecification;

public class Test2 {
	String[] postIds;

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
			// Verify How many post did a user with user ID 9 as published
			for (String post : eachpost) {
				if (post.contains("\"postId\": " + postIds[i])) {
					count++;
				}
			}
			totalCount += count;
		}

		return totalCount;
	}

	public JSONArray getUserPosts(String userId) throws ParseException {
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
			 //System.out.println(job.get("id"));
			postIds[counter] = job.get("id").toString();
			counter++;
		}

		return postIds;
	}

	@Test
	public void testPostId3() throws ParseException {
		String[] ids = getUserPostIds("3");
		int count = getCountOfComments(ids);
		Reporter.log(count + "  comments recived for User with user Id 3 for all his posts.", true);

	}

}
