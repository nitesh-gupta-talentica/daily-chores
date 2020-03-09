package com.daily.chores.checklist.domain;

import java.util.List;

public class CheckListModel {
	
	private String title ;
	private List<ItemModel> items;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<ItemModel> getItems() {
		return items;
	}
	public void setItems(List<ItemModel> items) {
		this.items = items;
	}
	

}
