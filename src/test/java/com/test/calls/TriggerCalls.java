package com.test.calls;

import static com.jayway.restassured.RestAssured.*;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.config.HttpClientConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.test.util.Configure;

public class TriggerCalls {
	Response response;

	/**
	 * Rest Assured setup method
	 */

	public static void setup() {
		RestAssured.config = RestAssuredConfig.newConfig()
				.httpClient(HttpClientConfig.httpClientConfig().reuseHttpClientInstance());

	}

	public void setURI() {
		RestAssured.baseURI = Configure.getValue("BASE_URL");
		RestAssured.port = Integer.parseInt(Configure.getValue("PORT"));
	}

	/**
	 * method to trigger GET API call
	 * 
	 * @param numberOfRecords
	 * @return Response object for the GET call
	 */

	public Response getRecord(int record) {
		setURI();
		if (record == 0) {
			response = given().when().get(Configure.getValue("API"));
		} else {
			response = given().when().get(Configure.getValue("API") + "/" + record);
		}
		return response;
	}

	/**
	 * method to validate schema
	 * 
	 * @param response
	 * @param record
	 * @return TRUE if the schema matches
	 */

	public boolean validateSchema(Response response) {
		boolean status = false;
		JSONObject JSONResponseBody = new JSONObject(response.body().asString());
		if (((JSONResponseBody.get("title") instanceof String))
				&& ((JSONResponseBody.get("body") instanceof String)) && ((JSONResponseBody.get("userId") instanceof Integer))) {
			status = true;
		}
		return status;
	}
	public boolean validateRecord(Response response,int record) {
		boolean status = false;
		if (record == 0) {
			JSONArray JSONResponseBody = new JSONArray(response.body().asString());
			if(JSONResponseBody.length() >= 100) {
				status = true;
			}
		}
		else {
			JSONObject JSONResponseBody = new JSONObject(response.body().asString()); 
			int id = Integer.parseInt(JSONResponseBody.get("id").toString());
			if(id == record) {
				status = true;
			}
			
		}
		
		return status;
	}	

	/**
	 * method to trigger POST call
	 * @return Response object for the POST call
	 */
	
	public Response postRecord() {
		setURI();
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBody(Configure.getValue("BODY"));
		builder.setContentType("application/json; charset=UTF-8");
		builder.setConfig(config);
		RequestSpecification requestSpec = builder.build();
		response = given().spec(requestSpec).when().post(Configure.getValue("API"));
		return response;
		
	}
	/**
	 * method to trigger PUT API
	 * @param record
	 * @return
	 */
	
	public Response putRecord(String record) {
		setURI();
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBody(Configure.getValue("BODY"));
		builder.setContentType("application/json; charset=UTF-8");
		builder.setConfig(config);
		RequestSpecification requestSpec = builder.build();
		response = given().spec(requestSpec).when().put(Configure.getValue("API") + "/" + record);
		return response;
	}
	
	/**
	 * 
	 * @param response
	 * @return TRUE if the response validation matches
	 */
	public boolean validateResponseRecords(Response response,String actualBody) {
		boolean status = false;
		JSONObject JSONResponseBody = new JSONObject(response.body().asString());
		String title = JSONResponseBody.get("title").toString();
		String body = JSONResponseBody.get("body").toString();
		String userId = JSONResponseBody.get("userId").toString();		
			if((actualBody.contains(title)) && (actualBody.contains(body) && (actualBody.contains(userId)))) {
				status = true;
			}			
		return status;
	}

	
	public boolean validatePutResponseRecords(Response response,int record) {
		boolean status = false;
		JSONObject JSONResponseBody = new JSONObject(response.body().asString());
		int id = Integer.parseInt(JSONResponseBody.get("id").toString());
		if(id == record) {
			status = true;
		}
		
		return status;
	}
	
	/**
	 * method deletes the given record
	 * @param record
	 * @return delete response object
	 */
	
	public Response deleteGivenRecord(String record) {
		setURI();
		return response = given().when().delete(Configure.getValue("API") + "/" + record);	
	}
	
	/**
	 * method to validate empty response body
	 * @param response
	 * @return true if the response body is empty
	 */
	
	public boolean validateEmptyResponse(Response response) {
		boolean status = false;
		JSONObject JSONResponseBody = new JSONObject(response.body().asString());
		if(JSONResponseBody.isEmpty()){
			status = true;
		}
		return status;
	}
	
	public Response makeInvalidCall() {
		setURI();
		return response = given().when().get(Configure.getValue("INVALID_API"));
	}
	
}
