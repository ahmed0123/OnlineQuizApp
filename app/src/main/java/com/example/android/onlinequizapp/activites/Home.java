package com.example.android.onlinequizapp.activites;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.android.onlinequizapp.R;
import com.example.android.onlinequizapp.fragments.CategoryFragment;
import com.example.android.onlinequizapp.fragments.RankingFragment;

public class Home extends AppCompatActivity {
	
	BottomNavigationView bottomNavigationView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		bottomNavigationView = findViewById(R.id.naviagtion);
		
		bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				Fragment selectedFragment = null;
				
				switch (item.getItemId()) {
					case R.id.action_catogery:
						selectedFragment = CategoryFragment.newInstance();
						break;
					case R.id.action_ranking:
						selectedFragment = RankingFragment.newInstance();
						break;
				}
				FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
				fragmentTransaction.replace(R.id.frame_layout, selectedFragment);
				fragmentTransaction.commit();
				
				return true;
			}
		});
		
		setDeafultFragment();
	}
	
	private void setDeafultFragment() {
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.replace(R.id.frame_layout, CategoryFragment.newInstance());
		fragmentTransaction.commit();
	}
}
