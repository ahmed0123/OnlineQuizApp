package com.example.android.onlinequizapp.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.onlinequizapp.Interface.ItemClickListiner;
import com.example.android.onlinequizapp.Interface.RankingCallBack;
import com.example.android.onlinequizapp.R;
import com.example.android.onlinequizapp.model.QuestionScore;
import com.example.android.onlinequizapp.model.Ranking;
import com.example.android.onlinequizapp.utils.Common;
import com.example.android.onlinequizapp.viewHolder.RankingViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankingFragment extends Fragment {
	
	int sum = 0;
	View myFragment;
	FirebaseDatabase database;
	RecyclerView rankingList;
	LinearLayoutManager layoutManager;
	FirebaseRecyclerAdapter<Ranking, RankingViewHolder> adapter;
	Query query;
	FirebaseRecyclerOptions<Ranking> options;
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
		query = FirebaseDatabase.getInstance()
				.getReference()
				.child("Ranking")
				.orderByChild("score")
				.limitToLast(5);
		options = new FirebaseRecyclerOptions.Builder<Ranking>()
				.setQuery(query, Ranking.class)
				.build();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		myFragment = inflater.inflate(R.layout.fragment_ranking, container, false);
		rankingList = myFragment.findViewById(R.id.rankingList);
		layoutManager = new LinearLayoutManager(getActivity());
		rankingList.setHasFixedSize(true);
		layoutManager.setReverseLayout(true);
		layoutManager.setStackFromEnd(true);
		rankingList.setLayoutManager(layoutManager);
		
		updateScore(Common.currentUser.getUserName(), new RankingCallBack<Ranking>() {
			@Override
			public void callBack(Ranking ranking) {
				rankingTable.child(ranking.getUsername()).setValue(ranking);
				showRanking();
			}
		});
		adapter = new FirebaseRecyclerAdapter<Ranking, RankingViewHolder>(options) {
			@NonNull
			@Override
			public RankingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
				
				return new RankingViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.ranking_list_item, parent, false));
			}
			
			@Override
			protected void onBindViewHolder(@NonNull RankingViewHolder holder, int position, @NonNull Ranking model) {
				
				holder.txtName.setText(model.getUsername());
				holder.txtScore.setText(String.valueOf(model.getScore()));
				holder.setItemClickListiner(new ItemClickListiner() {
					@Override
					public void onClick(View view, int position, boolean isLongClick) {
					
					}
				});
				
			}
		};
		
		adapter.notifyDataSetChanged();
		rankingList.setAdapter(adapter);
		
		return myFragment;
	}
	
	private void showRanking() {
	
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
	
	@Override
	public void onStart() {
		super.onStart();
		adapter.startListening();
	}
	
	@Override
	public void onStop() {
		super.onStop();
		adapter.startListening();
	}
	
}
