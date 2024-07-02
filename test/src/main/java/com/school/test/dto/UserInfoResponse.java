package com.school.test.dto;



public class UserInfoResponse {

	private String accessToken;
	private String refreshtoken;

	public String getRefreshtoken() {
		return refreshtoken;
	}

	public void setRefreshtoken(String refreshtoken) {
		this.refreshtoken = refreshtoken;
	}

	public UserInfoResponse(String accesstoken,String refreshtoken) {

		this.accessToken =accesstoken;
		this.refreshtoken=refreshtoken;
		
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}