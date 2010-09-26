package com.d8coach.android.d8coach;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
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
		currentTargetLabel.setText("Current Target: Jane Doe");

		final LinearLayout currentTargetButton  = (LinearLayout)targetFooter.findViewById(R.id.currentTargetButton);
        currentTargetButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
				Log.v("currentTargetButton", "click!");
				//Fire up the detail page
				
        		Intent myIntent = new Intent(ObjectivesActivity.this, TargetDetailActivity.class);
				//Pass the relevant target ID
        		ObjectivesActivity.this.startActivity(myIntent);
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
	 * method to mark objective as unsuitable
	 */
	//private void completeObjective(final Objective objective) {
	private void completeObjective(final int position) {
		final Objective objective = getModel(position);
		Log.v("completeObjective", objective.toString());
		//launch confirmation dialog
		
		//Resources res = getResources();
		//String[] outcomes = res.getStringArray(R.array.objective_outcomes);
		
		final DialogInterface.OnClickListener listener = 
		new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
				Log.v("dismissObjective", objective.toString());
				switch (which) {
					case DialogInterface.BUTTON_POSITIVE:
						Log.v("dismissObjective", "Yes");
						removeModel(objective);
						break;
					case DialogInterface.BUTTON_NEGATIVE:
						Log.v("dismissObjective", "No");
						removeModel(objective);
						break;
					case DialogInterface.BUTTON_NEUTRAL:
						Log.v("dismissObjective", "Meh");
						break;
				}
            }
        };
		new AlertDialog.Builder(this)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .setTitle(R.string.objective_complete)
        .setMessage(R.string.objective_complete_prompt)
        .setPositiveButton(R.string.good, listener)
        .setNegativeButton(R.string.bad, listener)
        //.setItems(outcomes,
        //.setItems(R.array.objective_outcomes,
        //.setItems(array, listener)
        .show();
	}

	/**
	 * method to mark objective as unsuitable
	 */
	private void dismissObjective(int position) {
	//private void dismissObjective(final Objective objective) {
		final Objective objective = getModel(position);
		Log.v("dismissObjective", objective.toString());
		//launch confirmation dialog
		new AlertDialog.Builder(this)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .setTitle(R.string.dismiss_objective)
        .setMessage(R.string.really_dismiss_objective)
        .setPositiveButton(R.string.delete_it, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
				Log.v("dismissObjective", objective.toString());
				switch (which) {
					case DialogInterface.BUTTON_POSITIVE:
						Log.v("dismissObjective", "Yes");
						removeModel(objective);
						break;
					case DialogInterface.BUTTON_NEGATIVE:
						Log.v("dismissObjective", "No");
						break;
				}
            }
        })
        .setNegativeButton(R.string.keep_it, null)
        .show();
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

	private void removeModel(Objective objective) {
		((ObjectiveAdapter) getListAdapter()).remove(objective);
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
			
			final int fixedPos = position;
			wrapper.getCompleteButton().setOnClickListener( new OnClickListener() {
				public void onClick(View v) {
					//Objective objective = getModel(fixedPos);
					//completeObjective(objective);
					completeObjective(fixedPos);
				}
			});
			
			wrapper.getVetoButton().setOnClickListener( new OnClickListener() {
				public void onClick(View v) {
					//dismissObjective(objective);
					dismissObjective(fixedPos);
				}
			});
			
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
		ImageButton completeImageButton = null;
		ImageButton vetoImageButton = null;

		ViewWrapper(View base) {
			this.base = base;
		}

		ImageButton getCompleteButton() {
			if (completeImageButton == null) {
				completeImageButton = (ImageButton)base.findViewById(R.id.completeButton);
			}

			return(completeImageButton);
		}
		
		ImageButton getVetoButton() {
			if (vetoImageButton == null) {
				vetoImageButton = (ImageButton)base.findViewById(R.id.vetoButton);
			}

			return(vetoImageButton);
		}

		TextView getLabel() {
			if (label == null) {
				label = (TextView)base.findViewById(R.id.label);
			}

			return(label);
		}
	}
}
