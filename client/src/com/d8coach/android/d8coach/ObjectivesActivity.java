package com.d8coach.android.d8coach;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.d8coach.android.d8coach.model.Objective;

public class ObjectivesActivity extends ListActivity {

	public static final int NUM_OBJECTIVES = 6;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.objectives);

		setTitle("D8Coach: Objectives");
		
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
