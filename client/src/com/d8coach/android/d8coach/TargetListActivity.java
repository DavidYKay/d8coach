package com.d8coach.android.d8coach;

import java.util.ArrayList;

import android.app.Activity;
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
import android.widget.ListView;
import android.widget.TextView;

import com.d8coach.android.d8coach.model.Target;
import com.d8coach.android.d8coach.util.Constants;

public class TargetListActivity extends ListActivity {

	public static final int NUM_TARGETS = 6;
	
	private boolean selectMode;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setTitle("D8Coach: Targets");

		Intent intent = getIntent();
		selectMode = intent.getBooleanExtra(Constants.SELECT_MODE, false);

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
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (selectMode) {
			changeTarget(position);
		} else {

		}
	}

	/**
	 * Mainly to handle responses from TargetAddActivity
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == Constants.ADD_TARGET_CODE) {
			Log.v("onActivityResult", "Add Target");
			
		} else {
			Log.v("onActivityResult", "Result code: " + resultCode);
			Log.v("onActivityResult", "Request code: " + requestCode);
		}
	}

    private void changeTarget(int position) {
    	int resultCode = Activity.RESULT_OK;
    	Intent intent = new Intent();
		//Add data to intent bundle
		Target target = getModel(position);

		intent.putExtra(
			Constants.TargetName,
			target.getName()
		);

    	setResult(resultCode, intent);

		Log.v("changeTarget", "position: " + position);
    	finish();
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
        		//TargetListActivity.this.startActivity(myIntent);
        		TargetListActivity.this.startActivityForResult(myIntent, Constants.ADD_TARGET_CODE);
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
