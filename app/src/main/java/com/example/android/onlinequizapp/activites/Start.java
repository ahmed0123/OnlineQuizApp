package com.example.android.onlinequizapp.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.onlinequizapp.R;
import com.example.android.onlinequizapp.model.Questions;
import com.example.android.onlinequizapp.utils.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;

public class Start extends AppCompatActivity {
	
	Button playButton;
	FirebaseDatabase database;
	DatabaseReference questions;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		//firebase set up
		database = FirebaseDatabase.getInstance();
		questions = database.getReference("Questions");
		playButton = findViewById(R.id.playButton);
		playButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(Start.this, Playing.class));
				finish();
				
			}
		});
		
		loadQuestions(Common.categoryId);
	}
	
	private void loadQuestions(String categoryId) {
		
		if (Common.questionsList.size() > 0)
			Common.questionsList.clear();
		
		questions.orderByChild("CaregoryId").equalTo(categoryId).addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
					Questions questions = postSnapshot.getValue(Questions.class);
					Common.questionsList.add(questions);
				}
			}
			
			@Override
			public void onCancelled(DatabaseError databaseError) {
			
			}
		});
		Collections.shuffle(Common.questionsList);
	}
}
