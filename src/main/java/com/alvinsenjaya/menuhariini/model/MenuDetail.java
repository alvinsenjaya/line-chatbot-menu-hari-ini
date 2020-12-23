package com.alvinsenjaya.menuhariini.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuDetail {
	private String title;
	private String thumb;
	private String servings;
	private String times;
	private String dificulty;
	private Author author;
	private String desc;
	private List<NeedItem> needItem;
	private List<String> ingredient;
	private List<String> step;
	
	protected MenuDetail() {
		
	}
	
	public MenuDetail(String title, String thumb, String times, String servings, String dificulty, Author author, String desc, List<NeedItem> needItem, List<String> ingredient, List<String> step) {
		super();
		this.title = title;
		this.thumb = thumb;
		this.times = times;
		this.servings = servings;
		this.dificulty = dificulty;
		this.author = author;
		this.desc = desc;
		this.needItem = needItem;
		this.ingredient = ingredient;
		this.step = step;
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
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public String getServings() {
		return servings;
	}
	public void setServings(String servings) {
		this.servings = servings;
	}
	public String getDificulty() {
		return dificulty;
	}
	public void setDificulty(String dificulty) {
		this.dificulty = dificulty;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<String> getIngredient() {
		return ingredient;
	}
	public void setIngredient(List<String> ingredient) {
		this.ingredient = ingredient;
	}
	public List<String> getStep() {
		return step;
	}
	public void setStep(List<String> step) {
		this.step = step;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public List<NeedItem> getNeedItem() {
		return needItem;
	}
	public void setNeedItem(List<NeedItem> needItem) {
		this.needItem = needItem;
	}
	
}

