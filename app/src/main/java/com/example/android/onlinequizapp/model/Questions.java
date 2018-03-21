package com.example.android.onlinequizapp.model;

/**
 * Created by Ahmed El Hendawy on 2018/03/20.
 */

public class Questions {
	private String Question, AnswerA, AnswerB, AnswerC, AnswerD, CaregoryId, CorrectAnswer, IsImageQuestion;
	
	public Questions() {
	}
	
	public Questions(String question, String answerA, String answerB, String answerC, String answerD, String caregoryId, String correctAnswer, String isImageQuestion) {
		Question = question;
		AnswerA = answerA;
		AnswerB = answerB;
		AnswerC = answerC;
		AnswerD = answerD;
		CaregoryId = caregoryId;
		CorrectAnswer = correctAnswer;
		IsImageQuestion = isImageQuestion;
	}
	
	public String getQuestion() {
		return Question;
	}
	
	public void setQuestion(String question) {
		Question = question;
	}
	
	public String getAnswerA() {
		return AnswerA;
	}
	
	public void setAnswerA(String answerA) {
		AnswerA = answerA;
	}
	
	public String getAnswerB() {
		return AnswerB;
	}
	
	public void setAnswerB(String answerB) {
		AnswerB = answerB;
	}
	
	public String getAnswerC() {
		return AnswerC;
	}
	
	public void setAnswerC(String answerC) {
		AnswerC = answerC;
	}
	
	public String getAnswerD() {
		return AnswerD;
	}
	
	public void setAnswerD(String answerD) {
		AnswerD = answerD;
	}
	
	public String getCaregoryId() {
		return CaregoryId;
	}
	
	public void setCaregoryId(String caregoryId) {
		CaregoryId = caregoryId;
	}
	
	public String getCorrectAnswer() {
		return CorrectAnswer;
	}
	
	public void setCorrectAnswer(String correctAnswer) {
		CorrectAnswer = correctAnswer;
	}
	
	public String getIsImageQuestion() {
		return IsImageQuestion;
	}
	
	public void setIsImageQuestion(String isImageQuestion) {
		IsImageQuestion = isImageQuestion;
	}
}
