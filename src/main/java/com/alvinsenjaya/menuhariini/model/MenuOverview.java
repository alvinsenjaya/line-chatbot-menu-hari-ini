package com.alvinsenjaya.menuhariini.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuOverview {
	private String title;
	private String thumb;
	private String key;
	private String times;
	private String serving;
	private String difficulty;
	
	protected MenuOverview() {
		
	}
	
	public MenuOverview(String title, String thumb, String key, String times, String serving, String difficulty) {
		super();
		this.title = title;
		this.thumb = thumb;
		this.key = key;
		this.times = times;
		this.serving = serving;
		this.difficulty = difficulty;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public String getServing() {
		return serving;
	}
	public void setServing(String serving) {
		this.serving = serving;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	
}
