package com.alvinsenjaya.menuhariini.model;

public class Author{
	private String user;
	private String datePublished;
	
	protected Author() {
		
	}
	
	public Author(String user, String datePublished) {
		super();
		this.user = user;
		this.datePublished = datePublished;
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getDatePublished() {
		return datePublished;
	}
	public void setDatePublished(String datePublished) {
		this.datePublished = datePublished;
	}
	
}