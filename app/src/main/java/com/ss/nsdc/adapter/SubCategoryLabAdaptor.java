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
import com.ss.nsdc.dao.SubCategoryLab;
import com.ss.nsdc.main.FormActivity;

public class SubCategoryLabAdaptor extends
RecyclerView.Adapter<SubCategoryLabAdaptor.ViewHolder> {

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
public SubCategoryLabAdaptor(List<SubCategoryLab> getListLab, String category) {
this.subCategoryLabList = getListLab;
this.category = category;
}

// Create new views (invoked by the layout manager)
@Override
public SubCategoryLabAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent,
            int viewType) {
	context = parent.getContext();
    // create a new view
    View v = LayoutInflater.from(context).inflate(R.layout.sub_category_list_row, parent, false);
    // set the view's size, margins, paddings and layout parameters
    ViewHolder vh = new ViewHolder(v);
    return vh;
}

// Replace the contents of a view (invoked by the layout manager)
@Override
public void onBindViewHolder(ViewHolder holder, int position) {
    // - get element from your dataset at this position
    // - replace the contents of the view with that element
    
    holder.txtSubCat.setText(subCategoryLabList.get(position).getLAB_Name());
    holder.imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	/*Intent intent = new Intent(context, FormActivity.class);
            	intent.putExtra("category", category);
            	intent.putExtra("subcategoryId", subCategoryLabList.get(position).getLabId());
            	intent.putExtra("yearWiseCollageId", subCategoryLabList.get(position).getYearWiseCollegeId());
            	intent.putExtra("ApplicationId", subCategoryLabList.get(position).getApplicationNo());
            	
            	context.startActivity(intent);*/
            }
        });
}

// Return the size of your dataset (invoked by the layout manager)
@Override
public int getItemCount() {
    return subCategoryLabList.size();
}
}
