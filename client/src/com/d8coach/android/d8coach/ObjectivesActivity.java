package com.d8coach.android.d8coach;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.d8coach.android.d8coach.model.Objective;
import com.d8coach.android.d8coach.util.Constants;
import com.d8coach.android.d8coach.view.TargetFooter;

public class ObjectivesActivity extends ListActivity {

	public static final int NUM_OBJECTIVES = 6;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.objectives);

		setTitle("D8Coach: Objectives");

		final TargetFooter targetFooter = (TargetFooter) this.findViewById(R.id.target_footer);
		
		final TextView currentTargetLabel  = (TextView)targetFooter.findViewById(R.id.label);
		//currentTargetLabel.setText("Current Target: Zane Doe");
		currentTargetLabel.setText("Current Target: Jane Doe");

		final LinearLayout currentTargetButton  = (LinearLayout)targetFooter.findViewById(R.id.currentTargetButton);
        currentTargetButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
				Log.v("currentTargetButton", "click!");
        		//Intent myIntent = new Intent(ObjectivesActivity.this, TargetListActivity.class);
        		//ObjectivesActivity.this.startActivity(myIntent);
        	}        	
        });
		
		final ImageButton changeTargetButton  = (ImageButton)targetFooter.findViewById(R.id.changeTargetButton);
        changeTargetButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Intent myIntent = new Intent(ObjectivesActivity.this, TargetListActivity.class);
				myIntent.putExtra(Constants.SELECT_MODE, true);
        		ObjectivesActivity.this.startActivityForResult(myIntent, Constants.CHANGE_TARGET_CODE);
        	}
        });
		
		Resources res = getResources();
		String[] lines = res.getStringArray(R.array.pickup_lines);

		ArrayList<Objective> list = new ArrayList<Objective>();

		//for (String s : lines) {
		for (int i = 0; i < NUM_OBJECTIVES; i++) {
			String s = lines[i];
			list.add(new Objective(
				s,
				Objective.ActionType.PICK_UP_LINE
			));
		}

		setListAdapter(new ObjectiveAdapter(list));
	}

	/**
	 * Mainly to handle responses from TargetListActivity
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == Constants.CHANGE_TARGET_CODE) {
			Log.v("onActivityResult", "Change Target");
			
		} else {
			Log.v("onActivityResult", "Result code: " + resultCode);
			Log.v("onActivityResult", "Request code: " + requestCode);
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		//Add quick actions here
		Log.v("onListItemClick", Integer.toString(position));
		
	}

	private Objective getModel(int position) {
		return(((ObjectiveAdapter)getListAdapter()).getItem(position));
	}

	class ObjectiveAdapter extends ArrayAdapter<Objective> {
		ObjectiveAdapter(ArrayList<Objective> list) {
			super(ObjectivesActivity.this, R.layout.objective_row, list);
		}

		public View getView(int position, View convertView,
				ViewGroup parent) {
			View row = convertView;
			ViewWrapper wrapper;

			if (row == null) {
				LayoutInflater inflater = getLayoutInflater();

				row = inflater.inflate(R.layout.objective_row, parent, false);
				wrapper = new ViewWrapper(row);
				row.setTag(wrapper);
			}
			else {
				wrapper = (ViewWrapper)row.getTag();
			}

			Objective model = getModel(position);

			wrapper.getLabel().setText(model.toString());
			//rate.setTag(new Integer(position));
			//rate.setRating(model.rating);

			return(row);
		}
	}

	/**
	 * Wraps a given objective row.
	 * Primarily here as an optimization.
	 * @author dk
	 *
	 */
	class ViewWrapper {
		View base;
		TextView label = null;
		Button completeButton = null;
		Button vetoButton = null;

		ViewWrapper(View base) {
			this.base = base;
		}

		Button getCompleteButton() {
			if (completeButton == null) {
				completeButton = (Button)base.findViewById(R.id.completeButton);
			}

			return(completeButton);
		}
		
		Button getVetoButton() {
			if (vetoButton == null) {
				vetoButton = (Button)base.findViewById(R.id.vetoButton);
			}

			return(vetoButton);
		}

		TextView getLabel() {
			if (label == null) {
				label = (TextView)base.findViewById(R.id.label);
			}

			return(label);
		}
	}
}
