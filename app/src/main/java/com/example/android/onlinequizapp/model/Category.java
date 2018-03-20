package com.example.android.onlinequizapp.model;

/**
 * Created by Ahmed El Hendawy on 2018/03/19.
 */

public class Category {
	
	private String Name;
	private String Image;
	
	public Category(String name, String image) {
		Name = name;
		Image = image;
	}
	
	public Category() {
	
	}
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}
	
	public String getImage() {
		return Image;
	}
	
	public void setImage(String image) {
		Image = image;
	}
}
