package com.d8coach.android.d8coach;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Simple login activity. Fires user data up to the web servie 
 * @author dk
 *
 */
public class LoginActivity extends Activity {

    EditText mEmailText;
    EditText mPasswordText;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

		setTitle("D8Coach: Login");

        mEmailText    = (EditText)this.findViewById(R.id.LoginActivity_email);
        mPasswordText = (EditText)this.findViewById(R.id.LoginActivity_password);

		final Button okButton  = (Button)this.findViewById(R.id.LoginActivity_okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
                login();
        	}        	
        });
		final Button registerButton  = (Button)this.findViewById(R.id.LoginActivity_registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
                register();
        	}        	
        });
    }

    /**
     * Grab entries from the fields
     */
    private void login() {
        final String emailString = mEmailText.getText().toString();
        final String passwordString = mPasswordText.getText().toString();
//        final String firstNameString = mFirstNameText.getText().toString();
//        final String lastNameString = mLastNameText.getText().toString();
        String logString = emailString + " " + passwordString;
        Log.v("Login", logString);

        //reach out to web service
    }

    /**
     * Grab entries from the fields
     */
    private void register() {
        Log.v("Register", "Register");
        //Fire up register activity
        Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        LoginActivity.this.startActivity(myIntent);
    }
}
