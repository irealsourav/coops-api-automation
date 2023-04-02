package com.takeaway.coopsAPIAutomation.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.takeaway.coopsAPIAutomation.endpoints.APIEndpoints;
import com.takeaway.coopsAPIAutomation.model.response.CommonResponseData;
import com.takeaway.coopsAPIAutomation.utils.FileManager;
import com.takeaway.coopsAPIAutomation.utils.SpecBuilder;

import io.restassured.RestAssured;

public class TC_003_feed_chickens_verification {
	int count = 0;
	private FileManager mgr, testDataMgr;
	private String configFileName = "config.properties";
	private String testDataFileName = "testData.properties";

	@Test(invocationCount = 2)
	public void verifyFeedChicken(ITestContext testContext) throws IOException {
		count++;
		mgr = new FileManager(configFileName);
		testDataMgr = new FileManager(testDataFileName);

		CommonResponseData commonResp = RestAssured
				.given(SpecBuilder.getRequestSpec(mgr.getPropertiesData("authToken"))).when()
				.post(APIEndpoints.getFeedChickenPath().replace("{userId}", mgr.getPropertiesData("userId"))).then()
				.spec(SpecBuilder.getResponseSpec()).statusCode(200).extract().body().as(CommonResponseData.class);
		assertEquals(commonResp.getAction(), testDataMgr.getPropertiesData("user.feedChickenAction"));

		if (count == 1)
			assertTrue(commonResp.getMessage().contains(testDataMgr.getPropertiesData("user.feedChickenMessage1")));
		if (count == 2) {
			assertFalse(commonResp.getMessage().contains(testDataMgr.getPropertiesData("user.feedChickenMessage1")));
			assertTrue(commonResp.getMessage().contains(testDataMgr.getPropertiesData("user.feedChickenMessage2")));
		}
		assertTrue(commonResp.getSuccess());

	}
}
