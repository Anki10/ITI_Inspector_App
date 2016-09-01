package com.ss.nsdc.adapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

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
import com.ss.nsdc.dao.SubCategoryResidentialFacDAO;
import com.ss.nsdc.dao.SubCategorySupportStaffDAO;
import com.ss.nsdc.entity.GeneralInfo;
import com.ss.nsdc.entity.Proc_Track;
import com.ss.nsdc.entity.SubCategoryResidentialFac;
import com.ss.nsdc.entity.SubCategorySupportStaff;
import com.ss.nsdc.entity.SubListEquipment;
import com.ss.nsdc.entity.SubListOffice;
import com.ss.nsdc.main.FormActivity;
import com.ss.nsdc.main.SubCategoryClassActivity;
import com.ss.nsdc.main.SubCategoryFilterActivity;
import com.ss.nsdc.utility.UtilityService;

public class CategoryAdapter extends
        RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<String> instituteList;
    private Context context;
    String instituteId, applicationId;
    ProgressDialog ringProgressDialog;
    int imageposition;
    ImageView tmpImageView_sync = null;
    UtilityService utility = UtilityService.getInstance();

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtCat;
        public ImageView imageView1;
        public ImageView imageView_sync;

        public ViewHolder(View v) {
            super(v);
            txtCat = (TextView) v.findViewById(R.id.category);
            imageView1 = (ImageView) v.findViewById(R.id.cat_status);
            imageView_sync = (ImageView) v.findViewById(R.id.cat_sync);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CategoryAdapter(ArrayList<String> instituteList, String instituteId,
                           String applicationId) {
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

        holder.txtCat.setText(instituteList.get(position));

        holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.download));
        NSDCDBController controller = new NSDCDBController(context);
        if (position == 1) { // Class
            List<JobRolesModel> jobRolesModelList = controller.getJobRolesList(instituteId);
            if (jobRolesModelList != null && jobRolesModelList.size() != 0) {
                Proc_Track syncStatus = controller.getSyncStatus("Jobroles");
                if (syncStatus.getProc_track1Count() == 0) { // New Data
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
                } else {
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
                }
                if (syncStatus.getProc_track2Count() > 0) {
                    holder.imageView_sync.setVisibility(View.VISIBLE);
                }
            }
        } else if (position == 2) { // Class
            List<SubCategoryClass> listClass = controller.getClassListbyYearWiseCollageId(instituteId);
            if (listClass != null && listClass.size() != 0) {
                Proc_Track syncStatus = controller.getSyncStatus("class");
                if (syncStatus.getProc_track1Count() == 0) { // New Data
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
                } else {
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
                }
                if (syncStatus.getProc_track2Count() > 0) {
                    holder.imageView_sync.setVisibility(View.VISIBLE);
                }
            }
        } else if (position == 3) { // Lab
            List<SubCategoryLab> listLab = controller.getLabListbyYearWiseCollageId(instituteId);
            if (listLab != null && listLab.size() != 0) {
                Proc_Track syncStatus = controller.getSyncStatus("lab");
                if (syncStatus.getProc_track1Count() == 0) { // New Data
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
                } else {
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
                }
                if (syncStatus.getProc_track2Count() > 0) {
                    holder.imageView_sync.setVisibility(View.VISIBLE);
                }
            }
        } else if (position == 4) { // OFFICE
            List<SubListOffice> listOffice = controller.getOfficeListbyYearWiseCollageId(instituteId);
            if (listOffice != null && listOffice.size() != 0) {
                Proc_Track syncStatus = controller.getSyncStatus("office");
                if (syncStatus.getProc_track1Count() == 0) { // New Data
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
                } else {
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
                }
                if (syncStatus.getProc_track2Count() > 0) {
                    holder.imageView_sync.setVisibility(View.VISIBLE);
                }
            }
        } else if (position == 8) { // Support Staff
            SubCategorySupportStaffDAO staffDAO = new SubCategorySupportStaffDAO(context);
            List<SubCategorySupportStaff> listStaff = staffDAO.getStaffListByYearWiseCollegeId(instituteId);

            if (listStaff != null && listStaff.size() != 0) {
                Proc_Track syncStatus = staffDAO.getSyncStatus();
                if (syncStatus.getProc_track1Count() == 0) { // New Data
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
                } else {
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
                }
                if (syncStatus.getProc_track2Count() > 0) {
                    holder.imageView_sync.setVisibility(View.VISIBLE);
                }
            }
            staffDAO.close();

        } else if (position == 9) { // EQUIPMENT
            List<SubListEquipment> listEquip = controller.getEquipmentListbyYearWiseCollageId(instituteId,null);
            if (listEquip != null && listEquip.size() != 0) {
                Proc_Track syncStatus = controller.getSyncStatus("equipment");
                if (syncStatus.getProc_track1Count() == 0) { // New Data
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
                } else {
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
                }
                if (syncStatus.getProc_track2Count() > 0) {
                    holder.imageView_sync.setVisibility(View.VISIBLE);
                }
            }
        }
        controller.close();

        holder.imageView1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0)/** General **/ {
                    NSDCDBController controller = new NSDCDBController(context);
                    GeneralInfo generalObj = controller.getGeneralInfo(instituteId);
                    controller.close();
                    if (generalObj != null) {
                        Intent intent = new Intent(context, FormActivity.class);
                        intent.putExtra("category", "general");
                        intent.putExtra("subcategoryId", "");
                        intent.putExtra("yearWiseCollageId", generalObj.getYearWiseCollegeId());
                        context.startActivity(intent);
                    } else {
                        imageposition = position;
                        new UpdateData().execute(new String[]{AppConstants.URL_GEN_INFO, "general", instituteId});

                    }
                }
                else if (position == 1)/** Job Roles **/ {
                    NSDCDBController controller = new NSDCDBController(context);
                    List<JobRolesModel> listClass = controller.getJobRolesList(instituteId);
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
                        // intent.putExtra("ClassId",
                        // listClass.get(position).getClassId());
                        // holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.progress));
                        context.startActivity(intent);
                    } else {
                        imageposition = position;
                        new UpdateData()
                                .execute(new String[]{AppConstants.URL_CLASS_DETAILS,"class", instituteId});

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
                        // intent.putExtra("ClassId",
                        // listLab.get(position).getLabId());
                        // holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.progress));
                        context.startActivity(intent);
                    } else {
                        new UpdateData().execute(new String[]{AppConstants.URL_LAB_DETAILS,"lab", instituteId});
                    }
                } else if (position == 4) {
                    /** Office Area **/
                    NSDCDBController controller = new NSDCDBController(context);
                    List<SubListOffice> listOffice = controller
                            .getOfficeListbyYearWiseCollageId(instituteId);
                    controller.close();
                    if (listOffice != null && listOffice.size() != 0) {
                        Intent intent = new Intent(context,
                                SubCategoryClassActivity.class);
                        intent.putExtra("Category", "office");
                        intent.putExtra("YearWiseCollegeId", instituteId);
                        intent.putExtra("applicationId", applicationId);
                        // intent.putExtra("ClassId",
                        // listLab.get(position).getLabId());
                        context.startActivity(intent);
                    } else {
                        new UpdateData().execute(new String[]{AppConstants.URL_OFFICE_DETAILS,"office", instituteId});
                    }

                } else if (position == 5) {/** Residential Facility **/
                    SubCategoryResidentialFacDAO resFacDAO = new SubCategoryResidentialFacDAO(context);
                    List<SubCategoryResidentialFac> list = resFacDAO.getResFacListByYearWiseCollegeId(instituteId);
                    resFacDAO.close();

                    if (list != null && list.size() != 0) {
                        Intent intent = new Intent(context, SubCategoryClassActivity.class);
                        intent.putExtra("Category", AppConstants.TEXT_RESIDENTIAL_FACILITY);
                        intent.putExtra("YearWiseCollegeId", instituteId);
                        intent.putExtra("applicationId", applicationId);
                        context.startActivity(intent);
                    } else {
                        new UpdateData().execute(new String[]{AppConstants.API_URL + "DisplayResidentialAreaDetails.php",
                                AppConstants.TEXT_RESIDENTIAL_FACILITY, instituteId});
                    }

                } else if (position == 8) {/** Support Staff **/
                    SubCategorySupportStaffDAO staffDAO = new SubCategorySupportStaffDAO(context);
                    List<SubCategorySupportStaff> list = staffDAO.getStaffListByYearWiseCollegeId(instituteId);
                    staffDAO.close();

                    if (list != null && list.size() != 0) {
                        Intent intent = new Intent(context, SubCategoryClassActivity.class);
                        intent.putExtra("Category", AppConstants.TEXT_SUPPORT_STAFF);
                        intent.putExtra("YearWiseCollegeId", instituteId);
                        intent.putExtra("applicationId", applicationId);
                        context.startActivity(intent);
                    } else {
                        new UpdateData().execute(new String[]{AppConstants.API_URL + "DisplayNtDetails.php",
                                AppConstants.TEXT_SUPPORT_STAFF, instituteId});
                    }

                } else if (position == 9) {/** Equipments **/
                    NSDCDBController controller = new NSDCDBController(context);
                    List<SubListEquipment> list = controller
                            .getEquipmentListbyYearWiseCollageId(instituteId,null);
                    controller.close();
                    if (list != null && list.size() != 0) {
                        //Intent intent = new Intent(context, SubCategoryClassActivity.class);
                        Intent intent = new Intent(context, SubCategoryFilterActivity.class);
                        intent.putExtra("Category", "equipment");
                        intent.putExtra("YearWiseCollegeId", instituteId);
                        intent.putExtra("applicationId", applicationId);
                        context.startActivity(intent);
                    } else {
                        new UpdateData().execute(new String[]{AppConstants.URL_EQUIPMENTS,"equipment", instituteId});
                    }
                }
            }
        });

        holder.imageView_sync.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                tmpImageView_sync = holder.imageView_sync;
                if (position == 2)/** Class **/ {
                    NSDCDBController controller = new NSDCDBController(context);
                    List<SubCategoryClass> classResults = controller.getClassDatatoSync(instituteId);
                    JSONObject datatoSycClass = utility.getClassDataSync(classResults);
                    new ExecuteSyncOperation().execute(new String[]{AppConstants.URL_CLASS_SYNC,datatoSycClass.toString(),"class"});
                }else if (position == 3)/** Lab **/ {
                    NSDCDBController controller = new NSDCDBController(context);
                    List<SubCategoryLab> results = controller.getLabDatatoSync(instituteId);
                    JSONObject datatoSycClass = utility.getLabSycData(results);
                    new ExecuteSyncOperation().execute(new String[]{AppConstants.URL_LAB_SYNC, datatoSycClass.toString(),"lab"});
                }else if (position == 4)/** Office **/ {
                    NSDCDBController controller = new NSDCDBController(context);
                    List<SubListOffice> results = controller.getOfficeDatatoSync(instituteId);
                    JSONObject datatoSycClass = utility.getOfficeSycData(results);
                    new ExecuteSyncOperation().execute(new String[]{AppConstants.URL_OFFICE_SYNC, datatoSycClass.toString(),"office"});
                }else if (position == 9)/** Equipment **/ {
                    NSDCDBController controller = new NSDCDBController(context);
                    List<SubListEquipment> results = controller.getEquipmentDatatoSync(instituteId);
                    JSONObject datatoSycClass = utility.getEquipmentSycData(results);
                    new ExecuteSyncOperation().execute(new String[]{AppConstants.URL_EQUIPMENT_SYNC, datatoSycClass.toString(),"equipment"});
                }
                //Toast.makeText(context, "Sync functionality is still in development", Toast.LENGTH_SHORT).show();
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
            super.onPreExecute();
            ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(
                            context, android.R.style.Theme_Holo), null,"Downloading data....", true);
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
            } else if (type.equalsIgnoreCase("office")) {
                NSDCDBController sqllite = new NSDCDBController(context);
                sqllite.addOfficeData(result, yearWiseCollegeId, applicationId);
                sqllite.close();
            } else if (type.equalsIgnoreCase("equipment")) {

                NSDCDBController sqllite = new NSDCDBController(context);
                sqllite.addEquipmentData(result, yearWiseCollegeId,
                        applicationId);
                sqllite.close();
            } else if (type.equalsIgnoreCase("jobRoles")) {

                NSDCDBController sqllite = new NSDCDBController(context);
                sqllite.addJobRolesData(result, yearWiseCollegeId,
                        applicationId);
                sqllite.close();

            } else if (type.equalsIgnoreCase(AppConstants.TEXT_SUPPORT_STAFF)) {
                SubCategorySupportStaffDAO staffDAO = new SubCategorySupportStaffDAO(context);
                staffDAO.addStaffData(result, yearWiseCollegeId, applicationId);
                staffDAO.close();

            } else if (type.equalsIgnoreCase(AppConstants.TEXT_RESIDENTIAL_FACILITY)) {
                SubCategoryResidentialFacDAO resFacDAO = new SubCategoryResidentialFacDAO(context);
                resFacDAO.addResidentialFacilityData(result, yearWiseCollegeId, applicationId);
                resFacDAO.close();
            }else if (type.equalsIgnoreCase("general")) {
                NSDCDBController sqllite = new NSDCDBController(context);
                sqllite.addGeneralInfoData(result, yearWiseCollegeId, applicationId);
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
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            String str = "YearWiseCollegeId=" + urldetail[2]+ "&Token="+AppConstants.API_TOKEN_VALUE;
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

    //################################Code to SYNC PENDING DATA#############################################
    class ExecuteSyncOperation extends AsyncTask<String, Integer, JSONObject> {
        String type;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(context, android.R.style.Theme_Holo), null,
                    " Data Synchronizing ...", true);
            ringProgressDialog.setCancelable(false);
            ringProgressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected JSONObject doInBackground(String... data) {
            String response = "";
            type = data[2];
            JSONObject response_json = null;
            try {
                URL url = new URL(data[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                String str = "result=" + data[1] + "&Token=" + AppConstants.API_TOKEN_VALUE;
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
                } else if (responsecode == HttpURLConnection.HTTP_CLIENT_TIMEOUT) {
                    response_json = new JSONObject();
                    response_json.put("error", "Connection to server lost..");
                }
                response_json = new JSONObject(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response_json;

        }

        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);
            if (result != null && result.has("responsecode")) {
                try {
                    if (Integer.valueOf(result.get("responsecode").toString()) == 2) {
                        ringProgressDialog.cancel();
                        Toast.makeText(context, "Data Sync Successful",Toast.LENGTH_LONG).show();
                        NSDCDBController controller = new NSDCDBController(context);
                        String inserted_data = result.get("inserted_data").toString();
                        boolean updation_status = controller.updateSyncDataStatus(inserted_data, type,instituteId);
                        controller.close();
                        if (updation_status) {
                            if(tmpImageView_sync!=null)
                            tmpImageView_sync.setVisibility(View.GONE);
                        } else {
                            Toast.makeText(context, "Error in syncing data..", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        ringProgressDialog.cancel();
                        Toast.makeText(context, "Data Sync failed...",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                ringProgressDialog.dismiss();
                Toast.makeText(context, "Data Sync failed...",Toast.LENGTH_LONG).show();
            }
        }
    }
    //#################################################################################################################


}
