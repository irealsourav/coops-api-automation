package com.takeaway.coopsAPIAutomation.test;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import com.takeaway.coopsAPIAutomation.endpoints.APIEndpoints;
import com.takeaway.coopsAPIAutomation.model.response.UserDetails;
import com.takeaway.coopsAPIAutomation.utils.FileManager;
import com.takeaway.coopsAPIAutomation.utils.SpecBuilder;

import io.restassured.RestAssured;

import io.restassured.response.Response;

public class SetUpTest {

	private FileManager mgr, testDataMgr;
	private String configFileName = "config.properties";
	private String testDataFileName = "testData.properties";

	SetUpTest() throws FileNotFoundException {
		mgr = new FileManager(configFileName);
		testDataMgr = new FileManager(testDataFileName);
	}

	@Test(priority = 1)
	public void createAuthToken() throws IOException {
		System.out.println("I am in first test method");

		Response res = RestAssured.given().baseUri(APIEndpoints.getBaseurl()).contentType("multipart/form-data")
				.multiPart("client_id", mgr.getPropertiesData("client_id"))
				.multiPart("client_secret", mgr.getPropertiesData("client_secret"))
				.multiPart("grant_type", mgr.getPropertiesData("grant_type")).when()
				.post(APIEndpoints.getAuthtokenpath()).then().statusCode(200).extract().response();
		mgr.setPropertiesData("authToken", res.path("token_type") + " " + res.path("access_token").toString());

	}

	@Test(priority = 2)
	public void getUserId() throws IOException {

		UserDetails user = RestAssured.given(SpecBuilder.getRequestSpec(mgr.getPropertiesData("authToken"))).when()
				.get(APIEndpoints.getUserdetailspath()).then().spec(SpecBuilder.getResponseSpec()).statusCode(200)
				.extract().body().as(UserDetails.class);
		assertEquals(user.getFirstName(), testDataMgr.getPropertiesData("user.firstName"));
		assertEquals(user.getLastName(), testDataMgr.getPropertiesData("user.lastName"));
		assertEquals(user.getEmail(), testDataMgr.getPropertiesData("user.email"));
		mgr.setPropertiesData("userId", user.getId());

	}

}
