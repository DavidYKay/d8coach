package com.d8coach.android.d8coach;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


		final Button settingsButton  = (Button)this.findViewById(R.id.MainActivity_Register);
        settingsButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		//Intent myIntent = new Intent(CardTactics.this, SkirmishActivity.class);
        		Intent myIntent = new Intent(MainActivity.this, RegisterActivity.class);
        		//Bundle bundle = new Bundle();
        		//Pass the relevant mode to GameClient
        		//bundle.putInt("com.kongentertainment.android.cardtactics.gamemode", 1);
        		//myIntent.putExtras(bundle);
        		MainActivity.this.startActivity(myIntent);
        		/*   	
        		Intent myIntent = new Intent(mainmenu_this, PersonScreen.class);
        		Bundle bundle = new Bundle();
        		bundle.putDouble("com.jvdk.stb.taxPercent", taxPercent);
        		bundle.putDouble("com.jvdk.stb.tipPercent", tipPercent);
        		bundle.putString("com.jvdk.stb.userName", userName);
        		myIntent.putExtras(bundle);
        		mainmenu_this.startActivity(myIntent);
        		*/
        	}        	
        });
    }
}
