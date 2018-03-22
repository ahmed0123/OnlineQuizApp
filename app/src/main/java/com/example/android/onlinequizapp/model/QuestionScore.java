package com.example.android.onlinequizapp.model;

/**
 * Created by Ahmed El Hendawy on 2018/03/20.
 */

public class QuestionScore {
	
	private String Question_Score;
	private String User;
	private String Score;
	private String CaregoryId;
	private String CategoryName;
	
	public QuestionScore() {
	
	}
	
	public QuestionScore(String question_Score, String user, String score, String caregoryId, String categoryName) {
		Question_Score = question_Score;
		User = user;
		Score = score;
		CaregoryId = caregoryId;
		CategoryName = categoryName;
	}
	
	public String getQuestion_Score() {
		return Question_Score;
	}
	
	public void setQuestion_Score(String question_Score) {
		Question_Score = question_Score;
	}
	
	public String getUser() {
		return User;
	}
	
	public void setUser(String user) {
		User = user;
	}
	
	public String getScore() {
		return Score;
	}
	
	public void setScore(String score) {
		Score = score;
	}
	
	public String getCaregoryId() {
		return CaregoryId;
	}
	
	public void setCaregoryId(String caregoryId) {
		CaregoryId = caregoryId;
	}
	
	public String getCategoryName() {
		return CategoryName;
	}
	
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
}
