package com.alvinsenjaya.menuhariini.dto;

import com.alvinsenjaya.menuhariini.model.MenuDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuDetailResponse {
	private String method;
	private boolean status;
	private MenuDetail results;
	
	protected MenuDetailResponse() {
		
	}
	
	public MenuDetailResponse(String method, boolean status, MenuDetail results) {
		super();
		this.method = method;
		this.status = status;
		this.results = results;
	}

	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public MenuDetail getResults() {
		return results;
	}
	public void setResults(MenuDetail results) {
		this.results = results;
	}
	
}
