package com.takeaway.coopsAPIAutomation.utils;

import com.takeaway.coopsAPIAutomation.endpoints.APIEndpoints;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

	public static RequestSpecification getRequestSpec(String token) {
		return new RequestSpecBuilder().setBaseUri(APIEndpoints.getBaseurl())
				.addHeader("Authorization", token).setContentType(ContentType.JSON).log(LogDetail.ALL)
				.build();
	};

	public static ResponseSpecification getResponseSpec() {
		return new ResponseSpecBuilder().log(LogDetail.ALL).build();
	}

}
