package com.takeaway.coopsAPIAutomation.endpoints;

public class APIEndpoints {

	private static final String userDetailsPath = "/api/me";
	private static final String baseUrl = "http://coop.apps.symfonycasts.com";
	private static final String authTokenPath = "/token";
	private static String barnUnlockPath = "/api/{userId}/barn-unlock";
	private static String toiletSeatDownPath = "/api/{userId}/toiletseat-down";
	private static String feedChickenPath = "/api/{userId}/chickens-feed";
	private static String collectEggsPath = "/api/{userId}/eggs-collect";

	public static String getBarnUnlockPath() {
		return barnUnlockPath;
	}

	public static String getCollectEggsPath() {
		return collectEggsPath;
	}

	public static String getToiletSeatDownPath() {
		return toiletSeatDownPath;
	}

	public static String getFeedChickenPath() {
		return feedChickenPath;
	}

	public static String getUserdetailspath() {
		return userDetailsPath;
	}

	public static String getBaseurl() {
		return baseUrl;
	}

	public static String getAuthtokenpath() {
		return authTokenPath;
	}

}
