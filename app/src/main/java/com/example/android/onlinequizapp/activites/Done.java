package com.example.android.onlinequizapp.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.onlinequizapp.R;
import com.example.android.onlinequizapp.model.QuestionScore;
import com.example.android.onlinequizapp.utils.Common;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Done extends AppCompatActivity {
	
	Button tryAgainButton;
	TextView txtResultScore, getTextResultQuestion;
	ProgressBar progressBar;
	FirebaseDatabase database;
	DatabaseReference question_score;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_done);
		
		database = FirebaseDatabase.getInstance();
		question_score = database.getReference("Question_Score");
		
		tryAgainButton = findViewById(R.id.tryAgainButton);
		
		txtResultScore = findViewById(R.id.txtTotalScore);
		getTextResultQuestion = findViewById(R.id.txtTotalQuestion);
		
		progressBar = findViewById(R.id.doneProgressBar);
		
		tryAgainButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent homeIntent = new Intent(Done.this, Home.class);
				startActivity(homeIntent);
				finish();
				
			}
		});
		Bundle extra = getIntent().getExtras();
		if (extra != null) {
			int score = extra.getInt("SCORE");
			int totalQuestion = extra.getInt("TOTAL");
			int correctAnswer = extra.getInt("CORRECT");
			
			txtResultScore.setText(String.format("SCORE : %d", score));
			getTextResultQuestion.setText(String.format("PASSED : %d / %d", correctAnswer, totalQuestion));
			progressBar.setMax(totalQuestion);
			question_score.child(String.format("%s_%s", Common.currentUser.getUserName(), Common.categoryId))
					.setValue(new QuestionScore(String.format("%s_%s", Common.currentUser.getUserName(), Common.categoryId)
							, Common.currentUser.getUserName(),
							String.valueOf(score)));
			
		}
	}
}
