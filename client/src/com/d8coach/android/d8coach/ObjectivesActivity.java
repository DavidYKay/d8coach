package com.d8coach.android.d8coach;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

public class ObjectivesActivity extends ListActivity {

	public static final int NUM_OBJECTIVES = 6;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		Resources res = getResources();
		String[] lines = res.getStringArray(R.array.pickup_lines);

		ArrayList<RowModel> list = new ArrayList<RowModel>();

		//for (String s : lines) {
		for (int i = 0; i < NUM_OBJECTIVES; i++) {
			String s = lines[i];
			list.add(new RowModel(s));
		}

		setListAdapter(new RatingAdapter(list));
	}

	private RowModel getModel(int position) {
		return(((RatingAdapter)getListAdapter()).getItem(position));
	}

	class RatingAdapter extends ArrayAdapter<RowModel> {
		RatingAdapter(ArrayList<RowModel> list) {
			super(ObjectivesActivity.this, R.layout.row, list);
		}

		public View getView(int position, View convertView,
				ViewGroup parent) {
			View row=convertView;
			ViewWrapper wrapper;
			RatingBar rate;									

			if (row==null) {		
				LayoutInflater inflater=getLayoutInflater();

				row=inflater.inflate(R.layout.row, parent, false);
				wrapper=new ViewWrapper(row);
				row.setTag(wrapper);
				rate=wrapper.getRatingBar();

				RatingBar.OnRatingBarChangeListener l=
					new RatingBar.OnRatingBarChangeListener() {
					public void onRatingChanged(RatingBar ratingBar,
							float rating,
							boolean fromTouch)	{
						Integer myPosition=(Integer)ratingBar.getTag();
						RowModel model=getModel(myPosition);

						model.rating=rating;

						LinearLayout parent=(LinearLayout)ratingBar.getParent();
						TextView label=(TextView)parent.findViewById(R.id.label);

						label.setText(model.toString());
					}
				};

				rate.setOnRatingBarChangeListener(l);
			}
			else {
				wrapper=(ViewWrapper)row.getTag();
				rate=wrapper.getRatingBar();
			}

			RowModel model=getModel(position);

			wrapper.getLabel().setText(model.toString());
			rate.setTag(new Integer(position));
			rate.setRating(model.rating);

			return(row);
		}
	}

	class RowModel {
		String label;
		float rating=2.0f;

		RowModel(String label) {
			this.label=label;
		}

		public String toString() {
			if (rating>=3.0) {
				return(label.toUpperCase());
			}

			return(label);
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
		RatingBar rate=null;
		TextView label=null;

		ViewWrapper(View base) {
			this.base=base;
		}

		RatingBar getRatingBar() {
			if (rate==null) {
				rate=(RatingBar)base.findViewById(R.id.rate);
			}

			return(rate);
		}

		TextView getLabel() {
			if (label==null) {
				label=(TextView)base.findViewById(R.id.label);
			}

			return(label);
		}
	}
}
