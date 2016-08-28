package com.ss.nsdc.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ss.nsdc.R;
import com.ss.nsdc.dao.Institute;
import com.ss.nsdc.dao.NSDCDBController;
import com.ss.nsdc.main.CategoryActivity;

public class InstituteAdapter extends
		RecyclerView.Adapter<InstituteAdapter.ViewHolder> {

	private List<Institute> instituteList;
	private Context context;

	// Provide a reference to the views for each data item
	// Complex data items may need more than one view per item, and
	// you provide access to all the views for a data item in a view holder
	public class ViewHolder extends RecyclerView.ViewHolder {
		// each data item is just a string in this case
		public TextView txtInstitute;
		public TextView txtCity;
		public TextView txtDate;
		public TextView txtphone;
		public TextView txtemail;
		public ImageView imageView;

		public ViewHolder(View v) {
			super(v);
			txtInstitute = (TextView) v.findViewById(R.id.title);
			txtCity = (TextView) v.findViewById(R.id.city);
			txtDate = (TextView) v.findViewById(R.id.date);
			txtphone = (TextView) v.findViewById(R.id.phoneno);
			txtemail = (TextView) v.findViewById(R.id.emailid);

			imageView = (ImageView) v.findViewById(R.id.status);
		}
	}

	// Provide a suitable constructor (depends on the kind of dataset)
	public InstituteAdapter(List<Institute> instituteList) {
		this.instituteList = instituteList;
	}

	// Create new views (invoked by the layout manager)
	@Override
	public InstituteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
			int viewType) {
		context = parent.getContext();
		// create a new view
		View v = LayoutInflater.from(context).inflate(
				R.layout.institute_list_row, parent, false);
		// set the view's size, margins, paddings and layout parameters
		ViewHolder vh = new ViewHolder(v);
		return vh;
	}

	// Replace the contents of a view (invoked by the layout manager)
	@Override
	public void onBindViewHolder(ViewHolder holder, final int position) {
		// - get element from your dataset at this position
		// - replace the contents of the view with that element

		holder.txtInstitute.setText(instituteList.get(position)
				.getName_Training_Center());
		NSDCDBController controller = new NSDCDBController(context);

		holder.imageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, CategoryActivity.class);
				intent.putExtra("YearWiseCollegeId", instituteList
						.get(position).getYearWiseCollegeId());
				intent.putExtra("applicationNo", instituteList.get(position)
						.getApplicationNo());
				intent.putExtra("instituteName", instituteList.get(position)
						.getName_Training_Center());
				instituteList.get(position).setProc_tracker(2);
				context.startActivity(intent);
			}
		});

		holder.txtDate.setText(instituteList.get(position)
				.getInspectionFromDate());

		String footer = "";

		if (instituteList.get(position).getDistrictName() != null
				&& footer != "") {
			footer += "," + instituteList.get(position).getDistrictName();
		} else {
			footer += instituteList.get(position).getDistrictName();
		}
		if (instituteList.get(position).getStateName() != null && footer != "") {
			footer += "," + instituteList.get(position).getStateName();
		} else {
			footer += instituteList.get(position).getStateName();
		}

		/*if (instituteList.get(position).getProc_tracker() == 1) {
			holder.imageView.setImageDrawable(context.getResources()
					.getDrawable(R.drawable.start));
		
		} else if (instituteList.get(position).getProc_tracker() == 2) {
			holder.imageView.setImageDrawable(context.getResources()
					.getDrawable(R.drawable.start));
		} else if (instituteList.get(position).getProc_tracker() == 3) {
			holder.imageView.setImageDrawable(context.getResources()
					.getDrawable(R.drawable.progress));
		}*/
		holder.txtCity.setText(footer);

		holder.txtphone.setText("76786876767"/*
											 * instituteList.get(position).
											 * getContactno()
											 */);
		holder.txtemail.setText("asdkakl@kjdf.von"/*
												 * instituteList.get(position).
												 * getEmailId()
												 */);

	}

	// Return the size of your dataset (invoked by the layout manager)
	@Override
	public int getItemCount() {
		return instituteList.size();
	}
}
