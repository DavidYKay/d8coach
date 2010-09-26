package com.d8coach.android.d8coach;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.d8coach.android.d8coach.model.Target;

public class TargetListActivity extends ListActivity {

	public static final int NUM_TARGETS = 6;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setTitle("D8Coach: Targets");

		Resources res = getResources();
		String[] names = res.getStringArray(R.array.female_names);

		ArrayList<Target> list = new ArrayList<Target>();

		//for (String s : names) {
		for (int i = 0; i < NUM_TARGETS; i++) {
			String s = names[i];
			list.add(new Target(s));
		}

		setListAdapter(new TargetAdapter(list));
	}

	/*******************************
	 * Menu Methods
	 ******************************/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.target_menu, menu);
		return true;
	}

	//public boolean onPrepareOptionsMenu(Menu menu) {
	//    return true;
    //}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//int currentTab = mTabHost.getCurrentTab();
		int itemId = item.getItemId();
		Log.v("onOptionsItemSelected", Integer.toString(itemId));
		switch (itemId) {
			case R.id.add_target:
				//Fire up the "add target" activity
        		Intent myIntent = new Intent(TargetListActivity.this, TargetAddActivity.class);
        		TargetListActivity.this.startActivity(myIntent);
				return true;
			case 1:
				//((IciView) mTabHost.getCurrentView()).onOptionsItemSelected(item);
				break;
			case 2:
				//((IciView) mTabHost.getCurrentView()).onOptionsItemSelected(item);
					break;
        }
		return true;
	}
	
	private Target getModel(int position) {
		return(((TargetAdapter)getListAdapter()).getItem(position));
	}
	
	class TargetAdapter extends ArrayAdapter<Target> {
		TargetAdapter(ArrayList<Target> list) {
			super(TargetListActivity.this, R.layout.target_row, list);
		}

		public View getView(int position, View convertView,
				ViewGroup parent) {
			View row = convertView;
			ViewWrapper wrapper;

			if (row == null) {		
				LayoutInflater inflater = getLayoutInflater();

				row = inflater.inflate(R.layout.target_row, parent, false);
				wrapper = new ViewWrapper(row);
				row.setTag(wrapper);
			}
			else {
				wrapper = (ViewWrapper)row.getTag();
			}

			Target model = getModel(position);

			String string = model.toString();
			Log.v("Model string: ", string);
			wrapper.getLabel().setText(model.toString());
			wrapper.getImageView().setImageResource(R.drawable.user_female);

			//rate.setTag(new Integer(position));
			//rate.setRating(model.rating);

			return(row);
		}
	}

	/**
	 * Wraps a given target row.
	 * Primarily here as an optimization.
	 * @author dk
	 *
	 */
	class ViewWrapper {
		View base;
		ImageView image = null;
		TextView label = null;

		ViewWrapper(View base) {
			this.base = base;
		}
		
		ImageView getImageView() {
			if (image == null) {
				image = (ImageView)base.findViewById(R.id.image);
			}

			return(image);
		}

		TextView getLabel() {
			if (label == null) {
				label = (TextView)base.findViewById(R.id.label);
			}

			return(label);
		}
	}

}
