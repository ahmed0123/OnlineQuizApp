package com.example.android.onlinequizapp.activites;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.onlinequizapp.R;
import com.example.android.onlinequizapp.utils.Common;
import com.squareup.picasso.Picasso;

public class Playing extends AppCompatActivity implements View.OnClickListener {
	
	int progressVlaue = 0;
	
	CountDownTimer mCountDown;
	int index = 0, score = 0, thisQuestion = 0, totalQuestion, correctAnswer;
	
	
	ProgressBar progressBar;
	ImageView imageView;
	Button answerA, answerB, answerC, answerD;
	TextView txtScore, textQuestionNum, questionText;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_playing);
		
		//Set Views
		txtScore = findViewById(R.id.scoreText);
		textQuestionNum = findViewById(R.id.totalQuestionText);
		questionText = findViewById(R.id.questionText);
		
		imageView = findViewById(R.id.questionImage);
		
		progressBar = findViewById(R.id.progressBar);
		
		answerA = findViewById(R.id.answerA);
		answerB = findViewById(R.id.answerB);
		answerC = findViewById(R.id.answerC);
		answerD = findViewById(R.id.answerD);
		
		answerA.setOnClickListener(this);
		answerB.setOnClickListener(this);
		answerC.setOnClickListener(this);
		answerD.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View view) {
		mCountDown.cancel();
		if (index < totalQuestion) {
			Button clickedButton = (Button) view;
			if (clickedButton.getText().equals(Common.questionsList.get(index).getCorrectAnswer())) {
				score++;
				correctAnswer++;
				showQuestion(++index);
			} else {
				Intent intent = new Intent(Playing.this, Done.class);
				Bundle bundle = new Bundle();
				bundle.putInt("SCORE", score);
				bundle.putInt("TOTAL", totalQuestion);
				bundle.putInt("CORRECT", correctAnswer);
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
			}
			
			txtScore.setText(String.format("%d", score));
		}
	}
	
	@Override
	protected void onPostResume() {
		super.onPostResume();
		totalQuestion = Common.questionsList.size();
		
		mCountDown = new CountDownTimer(Common.TIMEOUT, Common.INTERVAL) {
			@Override
			public void onTick(long minsec) {
				progressBar.setProgress(progressVlaue);
				progressVlaue++;
				
			}
			
			@Override
			public void onFinish() {
				mCountDown.cancel();
				showQuestion(++index);
			}
		};
		showQuestion(index);
	}
	
	private void showQuestion(int index) {
		if (index < totalQuestion) {
			thisQuestion++;
			textQuestionNum.setText(String.format("%d / %d", thisQuestion, totalQuestion));
			progressBar.setProgress(0);
			progressVlaue = 0;
			if (Common.questionsList.get(index).getIsImageQuestion().equals("true")) {
				Picasso.get().load(Common.questionsList.get(index).getQuestion()).into(imageView);
				imageView.setVisibility(View.VISIBLE);
				questionText.setVisibility(View.INVISIBLE);
			} else {
				questionText.setText(Common.questionsList.get(index).getQuestion());
				imageView.setVisibility(View.INVISIBLE);
				questionText.setVisibility(View.VISIBLE);
				
			}
			
			answerA.setText(Common.questionsList.get(index).getAnswerA());
			answerB.setText(Common.questionsList.get(index).getAnswerB());
			answerC.setText(Common.questionsList.get(index).getAnswerC());
			answerD.setText(Common.questionsList.get(index).getAnswerD());
			
			mCountDown.start();
		} else {
			Intent intent = new Intent(Playing.this, Done.class);
			Bundle bundle = new Bundle();
			bundle.putInt("SCORE", score);
			bundle.putInt("TOTAL", totalQuestion);
			bundle.putInt("CORRECT", correctAnswer);
			intent.putExtras(bundle);
			startActivity(intent);
			finish();
		}
	}
}
