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
import com.ss.nsdc.dao.NSDCDBController;
import com.ss.nsdc.dao.SubCategoryClass;
import com.ss.nsdc.dao.SubCategoryLab;
import com.ss.nsdc.main.FormActivity;

public class SubCategoryClassAdapter extends
		RecyclerView.Adapter<SubCategoryClassAdapter.ViewHolder> {

	private List<SubCategoryClass> subCategoryClassList;
	private List<SubCategoryLab> subCategoryLabList;

	private Context context;
	private String category;

	// Provide a reference to the views for each data item
	// Complex data items may need more than one view per item, and
	// you provide access to all the views for a data item in a view holder
	public class ViewHolder extends RecyclerView.ViewHolder {
		// each data item is just a string in this case
		public TextView txtSubCat;
		public ImageView imageView;

		public ViewHolder(View v) {
			super(v);
			txtSubCat = (TextView) v.findViewById(R.id.sub_category);
			imageView = (ImageView) v.findViewById(R.id.sub_cat_status);
		}
	}

	// Provide a suitable constructor (depends on the kind of dataset)
	public SubCategoryClassAdapter(List getList, String category,
			Context context) {
		if (category.equalsIgnoreCase("class")) {
			this.subCategoryClassList = getList;
		} else if (category.equalsIgnoreCase("lab")) {
			this.subCategoryLabList = getList;
		}
		this.context = context;
		this.category = category;
	}

	// Create new views (invoked by the layout manager)
	@Override
	public SubCategoryClassAdapter.ViewHolder onCreateViewHolder(
			ViewGroup parent, int viewType) {
		context = parent.getContext();
		// create a new view
		View v = LayoutInflater.from(context).inflate(
				R.layout.sub_category_list_row, parent, false);
		// set the view's size, margins, paddings and layout parameters
		ViewHolder vh = new ViewHolder(v);
		return vh;
	}

	// Replace the contents of a view (invoked by the layout manager)
	@Override
	public void onBindViewHolder(final ViewHolder holder, final int position) {
		// - get element from your dataset at this position
		// - replace the contents of the view with that element

		if (category.equalsIgnoreCase("class")) {
			holder.txtSubCat.setText(subCategoryClassList.get(position)
					.getClassroom_Name());
		} else if (category.equalsIgnoreCase("lab")) {
			holder.txtSubCat.setText(subCategoryLabList.get(position)
					.getLAB_Name());
		}

		holder.imageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, FormActivity.class);
				intent.putExtra("category", category);
				NSDCDBController controller = new NSDCDBController(context);

				if (category.equalsIgnoreCase("class")) {
					intent.putExtra("subcategoryId",
							subCategoryClassList.get(position).getClassId());
					intent.putExtra("yearWiseCollageId", subCategoryClassList
							.get(position).getYearWiseCollegeId());
					if (subCategoryClassList.get(position).getProc_tracker() == 1) {
						holder.imageView.setImageDrawable(context
								.getResources().getDrawable(R.drawable.start));
						subCategoryClassList.get(position).setProc_tracker(2);
						controller.updateClassProc_tracker(subCategoryClassList.get(position));
					} else if (subCategoryClassList.get(position)
							.getProc_tracker() == 2) {
						holder.imageView.setImageDrawable(context
								.getResources()
								.getDrawable(R.drawable.progress));
					} else if (subCategoryClassList.get(position)
							.getProc_tracker() == 3) {
						holder.imageView.setImageDrawable(context
								.getResources()
								.getDrawable(R.drawable.ok_green));
					}
					/*
					 * intent.putExtra("ApplicationId",
					 * subCategoryClassList.get(position).getApplicationNo());
					 */
				} else if (category.equalsIgnoreCase("Lab")) {
					intent.putExtra("subcategoryId",
							subCategoryLabList.get(position).getLabId());
					intent.putExtra("yearWiseCollageId", subCategoryLabList
							.get(position).getYearWiseCollegeId());
					if (subCategoryLabList.get(position).getProc_tracker() == 1) {
						holder.imageView.setImageDrawable(context
								.getResources().getDrawable(R.drawable.start));
						subCategoryLabList.get(position).setProc_tracker(2);
						controller.updateLabProc_tracker(subCategoryLabList.get(position));
					} else if (subCategoryLabList.get(position)
							.getProc_tracker() == 2) {
						holder.imageView.setImageDrawable(context
								.getResources()
								.getDrawable(R.drawable.progress));
					} else if (subCategoryLabList.get(position)
							.getProc_tracker() == 3) {
						holder.imageView.setImageDrawable(context
								.getResources()
								.getDrawable(R.drawable.ok_green));
					}
					/*
					 * intent.putExtra("ApplicationId",
					 * subCategoryLabList.get(position).getApplicationNo());
					 */
				}
				controller.close();
				context.startActivity(intent);
			}
		});
	}

	// Return the size of your dataset (invoked by the layout manager)
	@Override
	public int getItemCount() {
		if (category.equalsIgnoreCase("class")) {

			return subCategoryClassList.size();
		} else if (category.equalsIgnoreCase("Lab")) {
			return subCategoryLabList.size();
		}
		return 0;
	}
}
