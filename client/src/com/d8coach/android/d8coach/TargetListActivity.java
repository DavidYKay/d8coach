package com.d8coach.android.d8coach;

import java.util.ArrayList;

import android.app.ListActivity;
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
	String[] names = {"lorem", "ipsum", "dolor", "sit", "amet",
			"consectetuer", "adipiscing", "elit", "morbi", "vel",
			"ligula", "vitae", "arcu", "aliquet", "mollis",
			"etiam", "vel", "erat", "placerat", "ante",
			"porttitor", "sodales", "pellentesque", "augue",
	"purus"};

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		ArrayList<Target> list = new ArrayList<Target>();
		//ArrayList<RowModel> list=new ArrayList<RowModel>();
		//ArrayList<String> list = new ArrayList<String>();

		for (String s : names) {
			list.add(new Target(s));
			//list.add(s);
		}

		//setListAdapter(new RatingAdapter(list));
		setListAdapter(new TargetAdapter(list));

		//setListAdapter(new ArrayAdapter<String>(
		//	this,
		//	android.R.layout.simple_list_item_1,
		//	list
		//));
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
			//RatingBar rate;									

			if (row == null) {		
				LayoutInflater inflater = getLayoutInflater();

				row = inflater.inflate(R.layout.target_row, parent, false);
				wrapper = new ViewWrapper(row);
				row.setTag(wrapper);
				//rate = wrapper.getRatingBar();

				//RatingBar.OnRatingBarChangeListener l = 
				//	new RatingBar.OnRatingBarChangeListener() {
				//	public void onRatingChanged(RatingBar ratingBar,
				//			float rating,
				//			boolean fromTouch)	{
				//		Integer myPosition = (Integer)ratingBar.getTag();
				//		//RowModel model = getModel(myPosition);
				//		Target model = getModel(myPosition);

				//		//model.rating = rating;

				//		LinearLayout parent = (LinearLayout)ratingBar.getParent();
				//		TextView label = (TextView)parent.findViewById(R.id.label);

				//		label.setText(model.toString());
				//	}
				//};

				//rate.setOnRatingBarChangeListener(l);
			}
			else {
				wrapper = (ViewWrapper)row.getTag();
				//rate = wrapper.getRatingBar();
			}

			//RowModel model = getModel(position);
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
	 * Wraps a given objective row.
	 * Primarily here as an optimization.
	 * @author dk
	 *
	 */
	class ViewWrapper {
		View base;
		//RatingBar rate = null;
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

		//RatingBar getRatingBar() {
		//	if (rate == null) {
		//		rate = (RatingBar)base.findViewById(R.id.rate);
		//	}

		//	return(rate);
		//}

		TextView getLabel() {
			if (label == null) {
				label = (TextView)base.findViewById(R.id.label);
			}

			return(label);
		}
	}

}
