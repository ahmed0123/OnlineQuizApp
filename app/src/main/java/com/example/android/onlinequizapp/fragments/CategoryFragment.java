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
import android.widget.Toast;

import com.example.android.onlinequizapp.R;
import com.example.android.onlinequizapp.interfac.ItemClickListiner;
import com.example.android.onlinequizapp.model.Category;
import com.example.android.onlinequizapp.viewHolder.CategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;


public class CategoryFragment extends Fragment {
	
	View myFragment;
	RecyclerView listCategory;
	RecyclerView.LayoutManager layoutManager;
	FirebaseRecyclerAdapter<Category, CategoryViewHolder> adapter;
	
	Query query = FirebaseDatabase.getInstance()
			.getReference()
			.child("Category")
			.limitToLast(5);
	FirebaseRecyclerOptions<Category> options =
			new FirebaseRecyclerOptions.Builder<Category>()
					.setQuery(query, Category.class)
					.build();
	
	public CategoryFragment() {
		// Required empty public constructor
	}
	
	public static CategoryFragment newInstance() {
		
		CategoryFragment categoryFragment = new CategoryFragment();
		return categoryFragment;
	}
	
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		myFragment = inflater.inflate(R.layout.fragment_catogery, container, false);
		listCategory = myFragment.findViewById(R.id.listCategory);
		listCategory.setHasFixedSize(true);
		layoutManager = new LinearLayoutManager(container.getContext());
		listCategory.setLayoutManager(layoutManager);
		loadCategories();
		return myFragment;
	}
	
	private void loadCategories() {
		adapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(options) {
			@Override
			protected void onBindViewHolder(@NonNull CategoryViewHolder holder, int position, @NonNull final Category model) {
				holder.categoryName.setText(model.getName());
				Picasso
						.get()
						.load(model.getImage())
						.into(holder.categoryImage);
				
				holder.setItemClickListiner(new ItemClickListiner() {
					@Override
					public void onClick(View view, int position, boolean isLongClick) {
						Toast.makeText(getActivity(), String.format("%s|%s", adapter.getRef(position).getKey(), model.getName()), Toast.LENGTH_SHORT).show();
						
					}
				});
			}
			
			@Override
			public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
				return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item, parent, false));
			}
		};
		
		adapter.notifyDataSetChanged();
		listCategory.setAdapter(adapter);
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
