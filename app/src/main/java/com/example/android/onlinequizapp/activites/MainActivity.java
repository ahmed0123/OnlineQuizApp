package com.example.android.onlinequizapp.activites;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.onlinequizapp.R;
import com.example.android.onlinequizapp.model.User;
import com.example.android.onlinequizapp.utils.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {
	
	FirebaseDatabase database;
	DatabaseReference users;
	private MaterialEditText signUpUserName, signUpPassword, signUpEmail;
	private MaterialEditText signInUserName, signInPassword;
	private Button signUpButton, signInButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//firebase static setup
		database = FirebaseDatabase.getInstance();
		users = database.getReference("Users");
		
		//for sign in layout
		signInUserName = findViewById(R.id.userNameSignIn);
		signInPassword = findViewById(R.id.passwordSignIn);
		
		signInButton = findViewById(R.id.signInButton);
		signUpButton = findViewById(R.id.signUpButton);
		
		signInButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SignIn(signInUserName.getText().toString(), signInPassword.getText().toString());
			}
		});
		
		signUpButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showSignUpDialog();
			}
		});
		
		
	}
	
	private void SignIn(final String user, final String password) {
		
		users.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				if (dataSnapshot.child(user).exists()) {
					
					if (!user.isEmpty()) {
						User login = dataSnapshot.child(user).getValue(User.class);
						if (login.getPassword().equals(password)) {
							Intent intent = new Intent(MainActivity.this, Home.class);
							Common.currentUser = login;
							startActivity(intent);
							finish();
							ClearEditText();
						} else {
							Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(MainActivity.this, "Please Enter your user name", Toast.LENGTH_SHORT).show();
						
					}
				} else {
					Toast.makeText(MainActivity.this, "user is not exists", Toast.LENGTH_SHORT).show();
					
				}
			}
			
			@Override
			public void onCancelled(DatabaseError databaseError) {
			
			}
		});
		
	}
	
	private void showSignUpDialog() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
		alertDialog.setTitle("Sign Up");
		alertDialog.setMessage("Please enter full information");
		
		LayoutInflater inflater = this.getLayoutInflater();
		View signUpView = inflater.inflate(R.layout.sign_up_layout, null);
		
		signUpUserName = signUpView.findViewById(R.id.userNameSignUp);
		signUpPassword = signUpView.findViewById(R.id.passwordSignUp);
		signUpEmail = signUpView.findViewById(R.id.emailSignUp);
		
		alertDialog.setView(signUpView);
		alertDialog.setIcon(R.drawable.ic_account_circle_black_24dp);
		
		alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		
		alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				final User user = new User(
						signUpUserName.getText().toString(),
						signUpPassword.getText().toString(),
						signUpEmail.getText().toString()
				);
				
				users.addValueEventListener(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot dataSnapshot) {
						if (dataSnapshot.child(user.getUserName()).exists()) {
							Toast.makeText(MainActivity.this, "User Already exists", Toast.LENGTH_SHORT).show();
						} else {
							users.child(user.getUserName()).setValue(user);
							Toast.makeText(MainActivity.this, "User Register Successfully", Toast.LENGTH_SHORT).show();
						}
					}
					
					@Override
					public void onCancelled(DatabaseError databaseError) {
					
					}
				});
				dialog.dismiss();
			}
		});
		
		alertDialog.show();
		
	}
	
	private void ClearEditText() {
		signInUserName.setText("");
		signInPassword.setText("");
	}
	
}
