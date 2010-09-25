package com.d8coach.android.d8coach;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Activity for managing user registration 
 *
 * @author dk
 *
 */
public class RegisterActivity extends Activity {

    EditText mEmailText;
    EditText mFirstNameText;
    EditText mLastNameText;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        mEmailText = (EditText)this.findViewById(R.id.RegisterActivity_email);
        mFirstNameText = (EditText)this.findViewById(R.id.RegisterActivity_first_name);
        mLastNameText = (EditText)this.findViewById(R.id.RegisterActivity_last_name);

		final Button okButton  = (Button)this.findViewById(R.id.RegisterActivity_okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
                register();
        	}        	
        });
    }

    /**
     * Grab entries from the fields
     */
    private void register() {
        final String emailString = mEmailText.getText().toString();
//        final String firstNameString = mFirstNameText.getText().toString();
//        final String lastNameString = mLastNameText.getText().toString();

        String logString = emailString;
        Log.v("Register", logString);

        //reach out to web service
    }
}
