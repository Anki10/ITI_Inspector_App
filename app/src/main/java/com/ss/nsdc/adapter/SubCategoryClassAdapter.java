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
import android.widget.Toast;

import com.ss.nsdc.R;
import com.ss.nsdc.dao.JobRolesModel;
import com.ss.nsdc.dao.NSDCDBController;
import com.ss.nsdc.dao.SubCategoryClass;
import com.ss.nsdc.dao.SubCategoryLab;
import com.ss.nsdc.entity.SubListEquipment;
import com.ss.nsdc.entity.SubListOffice;
import com.ss.nsdc.main.FormActivity;

public class SubCategoryClassAdapter extends
        RecyclerView.Adapter<SubCategoryClassAdapter.ViewHolder> {

    private List<SubCategoryClass> subCategoryClassList;
    private List<SubCategoryLab> subCategoryLabList;
    private List<SubListOffice> subListOffice;
    private List<SubListEquipment> subListEquipment;
    private List<JobRolesModel> jobRolesModelList;
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
        } else if (category.equalsIgnoreCase("office")) {
            this.subListOffice = getList;
        } else if (category.equalsIgnoreCase("equipment")) {
            this.subListEquipment = getList;
        } else if (category.equalsIgnoreCase("jobRoles")) {
            this.jobRolesModelList = getList;
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
            holder.txtSubCat.setText(subCategoryClassList.get(position).getClassroom_Name());
            if (subCategoryClassList.get(position).getProc_tracker() == 1) { // New Data
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
            } else if (subCategoryClassList.get(position).getProc_tracker() == 2) { // not synced
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.progress));
            } else if (subCategoryClassList.get(position).getProc_tracker() == 3) { // synced Data
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
            }
        } else if (category.equalsIgnoreCase("lab")) {
            holder.txtSubCat.setText(subCategoryLabList.get(position).getLAB_Name());
            if (subCategoryLabList.get(position).getProc_tracker() == 1) { // New Data
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
            } else if (subCategoryLabList.get(position).getProc_tracker() == 2) { // not synced
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.progress));
            } else if (subCategoryLabList.get(position).getProc_tracker() == 3) { // synced Data
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
            }
        } else if (category.equalsIgnoreCase("office")) {
            holder.txtSubCat.setText(subListOffice.get(position).getAreaType());
            if (subListOffice.get(position).getProc_tracker() == 1) { // New Data
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
            } else if (subListOffice.get(position).getProc_tracker() == 2) { // not synced
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.progress));
            } else if (subListOffice.get(position).getProc_tracker() == 3) { // synced Data
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
            }
        } else if (category.equalsIgnoreCase("equipment")) {
            holder.txtSubCat.setText(subListEquipment.get(position).getEquipment_Name());
            if (subListEquipment.get(position).getProc_tracker() == 1) { // New Data
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
            } else if (subListEquipment.get(position).getProc_tracker() == 2) { // not synced
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.progress));
            } else if (subListEquipment.get(position).getProc_tracker() == 3) { // synced Data
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
            }
        } else if (category.equalsIgnoreCase("jobRoles")) {
            holder.txtSubCat.setText(jobRolesModelList.get(position).getJobName());
            if (jobRolesModelList.get(position).getProc_tracker() == 1) { // New Data
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
            } else if (jobRolesModelList.get(position).getProc_tracker() == 2) { // not synced
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.progress));
            } else if (jobRolesModelList.get(position).getProc_tracker() == 3) { // synced Data
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
            }
        }


        holder.imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //NSDCDBController controller = new NSDCDBController(context);
                if (category.equalsIgnoreCase("class")) {
                    if (subCategoryClassList.get(position).getProc_tracker() == 1) {// New Data
                        Intent intent = new Intent(context, FormActivity.class);
                        intent.putExtra("category", category);
                        intent.putExtra("subcategoryId", subCategoryClassList.get(position).getClassId());
                        intent.putExtra("yearWiseCollageId", subCategoryClassList.get(position).getYearWiseCollegeId());
                        context.startActivity(intent);
                    } else if (subCategoryClassList.get(position).getProc_tracker() == 2) {
                        Toast.makeText(context, "This Data is waiting to be synced and cannot be edited", Toast.LENGTH_SHORT).show();
                    } else if (subCategoryClassList.get(position).getProc_tracker() == 3) { //
                        Toast.makeText(context, "This Data is synced and cannot be edited", Toast.LENGTH_SHORT).show();
                    }
                } else if (category.equalsIgnoreCase("Lab")) {
                    if (subCategoryLabList.get(position).getProc_tracker() == 1) { // New Data
                        Intent intent = new Intent(context, FormActivity.class);
                        intent.putExtra("category", category);
                        intent.putExtra("subcategoryId", subCategoryLabList.get(position).getLabId());
                        intent.putExtra("yearWiseCollageId", subCategoryLabList.get(position).getYearWiseCollegeId());
                        context.startActivity(intent);
                    } else if (subCategoryLabList.get(position).getProc_tracker() == 2) {
                        Toast.makeText(context, "This Data is waiting to be synced and cannot be edited", Toast.LENGTH_SHORT).show();
                    } else if (subCategoryLabList.get(position).getProc_tracker() == 3) { //
                        Toast.makeText(context, "This Data is synced and cannot be edited", Toast.LENGTH_SHORT).show();
                    }
                } else if (category.equalsIgnoreCase("office")) {
                    if (subListOffice.get(position).getProc_tracker() == 1) { // New Data
                        Intent intent = new Intent(context, FormActivity.class);
                        intent.putExtra("category", category);
                        intent.putExtra("subcategoryId", subListOffice.get(position).getOfficeId());
                        intent.putExtra("yearWiseCollageId", subListOffice.get(position).getYearWiseCollegeId());
                        context.startActivity(intent);
                    } else if (subListOffice.get(position).getProc_tracker() == 2) {
                        Toast.makeText(context, "This Data is waiting to be synced and cannot be edited", Toast.LENGTH_SHORT).show();
                    } else if (subListOffice.get(position).getProc_tracker() == 3) { //
                        Toast.makeText(context, "This Data is synced and cannot be edited", Toast.LENGTH_SHORT).show();
                    }
                } else if (category.equalsIgnoreCase("equipment")) {
                    if (subListEquipment.get(position).getProc_tracker() == 1) { // New Data
                        Intent intent = new Intent(context, FormActivity.class);
                        intent.putExtra("category", category);
                        intent.putExtra("subcategoryId", subListEquipment.get(position).getJob_Id());
                        intent.putExtra("yearWiseCollageId", subListEquipment.get(position).getYearWiseCollegeId());
                        context.startActivity(intent);
                    } else if (subListEquipment.get(position).getProc_tracker() == 2) {
                        Toast.makeText(context, "This Data is waiting to be synced and cannot be edited", Toast.LENGTH_SHORT).show();
                    } else if (subListEquipment.get(position).getProc_tracker() == 3) { //
                        Toast.makeText(context, "This Data is synced and cannot be edited", Toast.LENGTH_SHORT).show();
                    }
                } else if (category.equalsIgnoreCase("jobRoles")) {
                    if (jobRolesModelList.get(position).getProc_tracker() == 1) { // New Data
                        Intent intent = new Intent(context, FormActivity.class);
                        intent.putExtra("category", category);
                        intent.putExtra("subcategoryId", jobRolesModelList.get(position).getJobID());
                        intent.putExtra("yearWiseCollageId", jobRolesModelList.get(position).getYearWiseCollegeId());
                        context.startActivity(intent);
                    } else if (subListEquipment.get(position).getProc_tracker() == 2) {
                        Toast.makeText(context, "This Data is waiting to be synced and cannot be edited", Toast.LENGTH_SHORT).show();
                    } else if (subListEquipment.get(position).getProc_tracker() == 3) { //
                        Toast.makeText(context, "This Data is synced and cannot be edited", Toast.LENGTH_SHORT).show();
                    }
                }
                //controller.close();
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
        } else if (category.equalsIgnoreCase("office")) {
            return subListOffice.size();
        } else if (category.equalsIgnoreCase("equipment")) {
            return subListEquipment.size();
        } else if (category.equalsIgnoreCase("jobRoles")) {
            return jobRolesModelList.size();
        }
        return 0;
    }
}
