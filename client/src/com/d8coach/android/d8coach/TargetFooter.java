package com.d8coach.android.d8coach;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class TargetFooter extends LinearLayout {

	public TargetFooter(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOrientation(HORIZONTAL);
		//setGravity(Gravity.CENTER);
		//setWeightSum(1.0f);
		
		// TODO Auto-generated constructor stub
		LayoutInflater.from(context).inflate(R.layout.target_footer, this, true);
	}

}
