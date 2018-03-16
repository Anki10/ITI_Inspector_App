package com.ss.nsdc.adapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ss.nsdc.R;
import com.ss.nsdc.dao.ITIDBController;
import com.ss.nsdc.dao.Institute;
import com.ss.nsdc.main.CategoryActivity;

public class InstituteAdapter extends
        RecyclerView.Adapter<InstituteAdapter.ViewHolder> {

    private List<Institute> instituteList;
    private Context context;
    String inspectionType;

    SharedPreferences sharedPreferences;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtInstitute;
        public TextView txtCity;
        public TextView txtphone;
        public TextView txtemail;
        public ImageView imageView;
        public ImageView imageViewInspectionType;

        public ViewHolder(View v) {
            super(v);
            txtInstitute = (TextView) v.findViewById(R.id.title);
            txtCity = (TextView) v.findViewById(R.id.city);
            imageView = (ImageView) v.findViewById(R.id.status);
        }
    }

    public InstituteAdapter(List<Institute> instituteList) {
        this.instituteList = instituteList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public InstituteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        context = parent.getContext();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
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

        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        Date dateobj = new Date();
        String currDate = df.format(dateobj);

        holder.txtInstitute.setText(instituteList.get(position).getOrganisationName());
        holder.txtCity.setText(instituteList.get(position).getContactDetails());

        Log.e("Yearwise",instituteList.get(position).getYearWiseCollegeId());
        ITIDBController controller = new ITIDBController(context);

        holder.imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sharedPreferences.getBoolean("isStarted", false)) {

                    if (sharedPreferences.getString("instituteId", "").equals(instituteList.get(position).getYearWiseCollegeId())) {

                        Intent intent = new Intent(context, CategoryActivity.class);
                        intent.putExtra("YearWiseCollegeId", instituteList.get(position).getYearWiseCollegeId());
                        intent.putExtra("instituteName", instituteList.get(position).getOrganisationName());
                        instituteList.get(position).setProc_tracker(2);
                        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                    } else {
                        Toast.makeText(context, "Please complete the started inspection first", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    Intent intent = new Intent(context, CategoryActivity.class);
                    intent.putExtra("YearWiseCollegeId", instituteList.get(position).getYearWiseCollegeId());
                    intent.putExtra("instituteName", instituteList.get(position).getOrganisationName());
                    instituteList.get(position).setProc_tracker(2);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);

                }

            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return instituteList.size();
    }
}
