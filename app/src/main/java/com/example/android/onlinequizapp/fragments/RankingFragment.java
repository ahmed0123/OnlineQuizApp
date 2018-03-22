package com.example.android.onlinequizapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.onlinequizapp.R;
import com.example.android.onlinequizapp.model.QuestionScore;
import com.example.android.onlinequizapp.utils.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankingFragment extends Fragment {
	
	int sum = 0;
	View myFragment;
	FirebaseDatabase database;
	DatabaseReference question_Score;
	
	public RankingFragment() {
		// Required empty public constructor
	}
	
	public static RankingFragment newInstance() {
		
		RankingFragment rankingFragment = new RankingFragment();
		return rankingFragment;
	}
	
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		database = FirebaseDatabase.getInstance();
		question_Score = database.getReference("Question_Score");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		myFragment = inflater.inflate(R.layout.fragment_ranking, container, false);
		updateScore(Common.currentUser.getUserName());
		return myFragment;
	}
	
	private void updateScore(String userName) {
		question_Score.orderByChild("user").equalTo(userName).addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
					QuestionScore quest = postSnapshot.getValue(QuestionScore.class);
					sum += Integer.parseInt(quest.getScore());
				}
			}
			
			@Override
			public void onCancelled(DatabaseError databaseError) {
			
			}
		});
		
	}
	
}
