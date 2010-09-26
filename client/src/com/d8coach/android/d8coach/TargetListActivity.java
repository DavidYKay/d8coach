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
import android.widget.ImageView;
import android.widget.TextView;

import com.d8coach.android.d8coach.model.Target;

public class TargetListActivity extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		Resources res = getResources();
		String[] names = res.getStringArray(R.array.female_names);

		ArrayList<Target> list = new ArrayList<Target>();

		for (String s : names) {
			list.add(new Target(s));
		}

		setListAdapter(new TargetAdapter(list));
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
