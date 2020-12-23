package com.alvinsenjaya.menuhariini.dto;

import java.util.List;

import com.alvinsenjaya.menuhariini.model.MenuOverview;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuOverviewListResponse {
	private String method;
	private boolean status;
	private List<MenuOverview> results;
	
	protected MenuOverviewListResponse() {
		
	}
	
	public MenuOverviewListResponse(String method, boolean status, List<MenuOverview> results) {
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
	public List<MenuOverview> getResults() {
		return results;
	}
	public void setResults(List<MenuOverview> results) {
		this.results = results;
	}
	
}
