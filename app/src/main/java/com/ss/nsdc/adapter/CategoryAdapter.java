package com.ss.nsdc.adapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.ss.nsdc.R;
import com.ss.nsdc.application.NsdcApplication;
import com.ss.nsdc.constant.AppConstants;
import com.ss.nsdc.dao.NSDCDBController;
import com.ss.nsdc.dao.SubCategoryClass;
import com.ss.nsdc.dao.SubCategoryLab;
import com.ss.nsdc.main.SubCategoryActivity;
import com.ss.nsdc.main.SubCategoryClassActivity;

public class CategoryAdapter extends
        RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<String> instituteList;
    private Context context;
    String instituteId, applicationId;
    ProgressDialog ringProgressDialog;
    int imageposition;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtCat;
        public ImageView imageView1;

        public ViewHolder(View v) {
            super(v);
            txtCat = (TextView) v.findViewById(R.id.category);
            imageView1 = (ImageView) v.findViewById(R.id.cat_status);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CategoryAdapter(ArrayList<String> instituteList, String instituteId, String applicationId) {
        this.instituteList = instituteList;
        this.instituteId = instituteId;
        this.applicationId = applicationId;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {

        context = parent.getContext();
        // create a new view
        View v = LayoutInflater.from(context).inflate(R.layout.category_list_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.txtCat.setText(instituteList.get(position));

        holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.download));
        NSDCDBController controller = new NSDCDBController(context);

        if (position == 2) {
            List<SubCategoryClass> listClass = controller.getClassListbyYearWiseCollageId(instituteId);
            if (listClass != null && listClass.size() != 0) {

                holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));

            }


        } else if (position == 3) {
            List<SubCategoryLab> listLab = controller.getLabListbyYearWiseCollageId(instituteId);
            if (listLab != null && listLab.size() != 0) {

                holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));

            }


        }
        controller.close();
           /* if(listClass!=null && listClass.size()!=0){
                if(imageposition==position){
            	  holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
            	}
            }else{
            holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.download));
            }*/
        holder.imageView1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 1)/** ClassRoom **/ {
                    NSDCDBController controller = new NSDCDBController(context);
                    List<SubCategoryClass> listClass = controller.getClassListbyYearWiseCollageId(instituteId);
                    controller.close();
                    if (listClass != null && listClass.size() != 0) {
                        Intent intent = new Intent(context,
                                SubCategoryClassActivity.class);
                        intent.putExtra("Category", "jobRoles");
                        intent.putExtra("YearWiseCollegeId", instituteId);
                        intent.putExtra("applicationId", applicationId);
                        //intent.putExtra("ClassId", listClass.get(position).getClassId());
                        //holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.progress));
                        context.startActivity(intent);
                    } else {
                        imageposition = position;
                        new UpdateData().execute(new String[]{AppConstants.URL_JOB_ROLES, "jobRoles", instituteId});

                    }
                } else if (position == 2)/** ClassRoom **/ {
                    NSDCDBController controller = new NSDCDBController(context);
                    List<SubCategoryClass> listClass = controller.getClassListbyYearWiseCollageId(instituteId);
                    controller.close();
                    if (listClass != null && listClass.size() != 0) {
                        Intent intent = new Intent(context,
                                SubCategoryClassActivity.class);
                        intent.putExtra("Category", "class");
                        intent.putExtra("YearWiseCollegeId", instituteId);
                        intent.putExtra("applicationId", applicationId);
                        //intent.putExtra("ClassId", listClass.get(position).getClassId());
                        //holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.progress));
                        context.startActivity(intent);
                    } else {
                        imageposition = position;
                        new UpdateData().execute(new String[]{"http://nsdc.qci.org.in/api/CAAF/DisplayClassroomDetails.php", "class", instituteId});

                    }
                } else if (position == 3)/** Laboratory **/ {
                    NSDCDBController controller = new NSDCDBController(context);
                    List<SubCategoryLab> listLab = controller.getLabListbyYearWiseCollageId(instituteId);
                    controller.close();
                    if (listLab != null && listLab.size() != 0) {
                        Intent intent = new Intent(context,
                                SubCategoryClassActivity.class);
                        intent.putExtra("Category", "lab");
                        intent.putExtra("YearWiseCollegeId", instituteId);
                        intent.putExtra("applicationId", applicationId);
                        //intent.putExtra("ClassId", listLab.get(position).getLabId());
                        holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.progress));
                        context.startActivity(intent);
                    } else {

                        new UpdateData().execute(new String[]{"http://nsdc.qci.org.in/api/CAAF/DisplayLabDetails.php", "lab", instituteId});

                    }
                }
            }
        });

    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return instituteList.size();
    }

    class UpdateData extends AsyncTask<String, Integer, JSONObject> {
        String yearWiseCollegeId;
        String type;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(context, android.R.style.Theme_Holo), null, "Downloading data....", true);
            ringProgressDialog.setCancelable(false);
            ringProgressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected JSONObject doInBackground(String... urldetail) {
            yearWiseCollegeId = urldetail[2];
            type = urldetail[1];

            JSONObject response = updateSubCategoryList(urldetail);
            return response;

        }

        @Override
        protected void onPostExecute(JSONObject result) {


            if (type.equalsIgnoreCase("class")) {

                NSDCDBController sqllite = new NSDCDBController(context);
                sqllite.addClassData(result, yearWiseCollegeId, applicationId);
                sqllite.close();

            } else if (type.equalsIgnoreCase("lab")) {

                NSDCDBController sqllite = new NSDCDBController(context);
                sqllite.addLabData(result, yearWiseCollegeId, applicationId);
                sqllite.close();
            } else if (type.equalsIgnoreCase("lab")) {

                NSDCDBController sqllite = new NSDCDBController(context);
                sqllite.addJobRoles(result, yearWiseCollegeId, applicationId);
                sqllite.close();
            }
            ringProgressDialog.dismiss();
            notifyDataSetChanged();

        }


    }


    public JSONObject updateSubCategoryList(String[] urldetail) {
        String response = "";
        JSONObject response_json = null;
        try {
            URL url = new URL(urldetail[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            String str = "YearWiseCollegeId=" + urldetail[2] + "&Token=bnNkYzd0ZWNoaWVzYXBp";
            byte[] outputInBytes = str.getBytes("UTF-8");
            conn.getOutputStream().write(outputInBytes);
            conn.connect();
            int responsecode = conn.getResponseCode();
            if (responsecode == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            }
            response_json = new JSONObject(response);
        } catch (Exception e) {
            e.printStackTrace();
            return response_json;
        }
        return response_json;
    }


    public JSONObject getJobRoles(String[] urldetail) {
        String response = "";
        JSONObject response_json = null;
        try {
            URL url = new URL(AppConstants.URL_JOB_ROLES);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            String str = "YearWiseCollegeId=" + urldetail[2] + "&Token=bnNkYzd0ZWNoaWVzYXBp";
            byte[] outputInBytes = str.getBytes("UTF-8");
            conn.getOutputStream().write(outputInBytes);
            conn.connect();
            int responsecode = conn.getResponseCode();
            if (responsecode == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            }
            response_json = new JSONObject(response);
        } catch (Exception e) {
            e.printStackTrace();
            return response_json;
        }
        return response_json;
    }

}
