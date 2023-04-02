package com.takeaway.coopsAPIAutomation.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import com.takeaway.coopsAPIAutomation.endpoints.APIEndpoints;
import com.takeaway.coopsAPIAutomation.model.response.CommonResponseData;
import com.takeaway.coopsAPIAutomation.utils.FileManager;
import com.takeaway.coopsAPIAutomation.utils.SpecBuilder;

import io.restassured.RestAssured;

public class TC_002_toilet_seat_down_verification {
	int count = 0;

	private FileManager mgr, testDataMgr;
	private String configFileName = "config.properties";
	private String testDataFileName = "testData.properties";

	TC_002_toilet_seat_down_verification() throws FileNotFoundException {
		mgr = new FileManager(configFileName);
		testDataMgr = new FileManager(testDataFileName);
	}

	@Test(invocationCount = 2)
	public void verifyToiletSitDown() throws IOException {
		count++;
		CommonResponseData commonResp = RestAssured
				.given(SpecBuilder.getRequestSpec(mgr.getPropertiesData("authToken"))).when()
				.post(APIEndpoints.getToiletSeatDownPath().replace("{userId}", mgr.getPropertiesData("userId"))).then()
				.spec(SpecBuilder.getResponseSpec()).statusCode(200).extract().body().as(CommonResponseData.class);
		assertEquals(commonResp.getAction(), testDataMgr.getPropertiesData("user.toiletSitDownAction"));
		if (count == 1)
			assertEquals(commonResp.getMessage(), testDataMgr.getPropertiesData("user.toiletSitDownMessage1"));
		if (count == 2) {

			assertNotEquals(commonResp.getMessage(), testDataMgr.getPropertiesData("user.toiletSitDownMessage1"));
			assertEquals(commonResp.getMessage(), testDataMgr.getPropertiesData("user.toiletSitDownMessage2"));
		}
		assertTrue(commonResp.getSuccess());

	}

}
