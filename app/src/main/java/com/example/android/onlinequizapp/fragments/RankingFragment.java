package com.example.android.onlinequizapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.onlinequizapp.Interface.RankingCallBack;
import com.example.android.onlinequizapp.R;
import com.example.android.onlinequizapp.model.QuestionScore;
import com.example.android.onlinequizapp.model.Ranking;
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
	DatabaseReference question_Score, rankingTable;
	
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
		rankingTable = database.getReference("Ranking");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		myFragment = inflater.inflate(R.layout.fragment_ranking, container, false);
		updateScore(Common.currentUser.getUserName(), new RankingCallBack<Ranking>() {
			@Override
			public void callBack(Ranking ranking) {
				rankingTable.child(ranking.getUsername()).setValue(ranking);
				
			}
		});
		return myFragment;
	}
	
	private void updateScore(final String userName, final RankingCallBack<Ranking> callBack) {
		question_Score.orderByChild("user").equalTo(userName).addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
					QuestionScore quest = postSnapshot.getValue(QuestionScore.class);
					sum += Integer.parseInt(quest.getScore());
				}
				Ranking ranking = new Ranking(userName, sum);
				callBack.callBack(ranking);
			}
			
			@Override
			public void onCancelled(DatabaseError databaseError) {
			
			}
		});
		
	}
	
}
