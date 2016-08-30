package com.ss.nsdc.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ss.nsdc.R;
import com.ss.nsdc.constant.AppConstants;
import com.ss.nsdc.dao.JobRolesModel;
import com.ss.nsdc.dao.NSDCDBController;
import com.ss.nsdc.dao.SubCategoryClass;
import com.ss.nsdc.dao.SubCategoryLab;
import com.ss.nsdc.dao.SubCategorySupportStaffDAO;
import com.ss.nsdc.entity.Proc_Track;
import com.ss.nsdc.entity.SubCategorySupportStaff;
import com.ss.nsdc.entity.SubListEquipment;
import com.ss.nsdc.entity.SubListOffice;
import com.ss.nsdc.main.SubCategoryClassActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SubCatFilterAdapter extends
        RecyclerView.Adapter<SubCatFilterAdapter.ViewHolder> {

    private List<String> filterList;
    private Context context;
    String instituteId, applicationId,category;
    ProgressDialog ringProgressDialog;
    int imageposition;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtView;
        public ImageView imageView1;
        public ImageView imageView_sync;
        public ViewHolder(View v) {
            super(v);
            txtView = (TextView) v.findViewById(R.id.category);
            imageView1 = (ImageView) v.findViewById(R.id.cat_status);
            imageView_sync = (ImageView) v.findViewById(R.id.cat_sync);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SubCatFilterAdapter(List<String> filterList, String instituteId,
                               String applicationId,String category) {
        this.filterList = filterList;
        this.instituteId = instituteId;
        this.applicationId = applicationId;
        this.category = category;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SubCatFilterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {

        context = parent.getContext();
        // create a new view
        View v = LayoutInflater.from(context).inflate(
                R.layout.category_list_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.txtView.setText(filterList.get(position));

        //holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.download));
        NSDCDBController controller = new NSDCDBController(context);
        if (category == "equipment_jobroles") { // equipment_jobroles
                String job_name = filterList.get(position);
                Proc_Track syncStatus = controller.getSyncStatusforEquipmentFilter(job_name);
                if (syncStatus.getProc_track1Count() == 0) { // New Data
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
                } else {
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
                }
                if (syncStatus.getProc_track2Count() > 0) {
                    holder.imageView_sync.setVisibility(View.VISIBLE);
                }

        }
        controller.close();

        holder.imageView1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (category == "equipment_jobroles") { // equipment_jobroles
                    NSDCDBController controller = new NSDCDBController(context);
                    List<SubListEquipment> list = controller.getEquipmentListbyYearWiseCollageId(instituteId,filterList.get(position));
                    controller.close();
                    if (list != null && list.size() != 0) {
                        Intent intent = new Intent(context, SubCategoryClassActivity.class);
                        intent.putExtra("Category", "equipment");
                        intent.putExtra("Filter", filterList.get(position));
                        intent.putExtra("YearWiseCollegeId", instituteId);
                        intent.putExtra("applicationId", applicationId);
                        context.startActivity(intent);
                    } else {
                        Toast.makeText(context, "No equipment under this Job Role..", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        holder.imageView_sync.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(context, "Sync functionality is still in development", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return filterList.size();
    }
}
