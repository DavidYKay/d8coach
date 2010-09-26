package com.d8coach.android.d8coach;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class TargetAddActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_target);
	
		final Button addTargetButton = (Button)findViewById(R.id.add_target_button);
        addTargetButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
				addTarget();
        	}        	
        });
	}

    private void addTarget() {
    	int resultCode = Activity.RESULT_OK;
    	Intent intent = new Intent();
		//Add data to intent bundle
		

    	setResult(resultCode, intent);
    	//setResult(resultCode);

		Log.v("addTarget", "resultCode: " + resultCode);
    	finish();
    }
}
