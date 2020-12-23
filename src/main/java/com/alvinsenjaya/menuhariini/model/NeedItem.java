package com.alvinsenjaya.menuhariini.model;

public class NeedItem{
	private String item_name;
	private String thumb_item;
	
	protected NeedItem() {
		
	}
	
	public NeedItem(String item_name, String thumb_item) {
		super();
		this.item_name = item_name;
		this.thumb_item = thumb_item;
	}

	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getThumb_item() {
		return thumb_item;
	}
	public void setThumb_item(String thumb_item) {
		this.thumb_item = thumb_item;
	}
	
}