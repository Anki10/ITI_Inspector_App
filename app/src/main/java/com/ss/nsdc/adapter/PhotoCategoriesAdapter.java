package com.ss.nsdc.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ss.nsdc.R;
import com.ss.nsdc.constant.AppConstants;
import com.ss.nsdc.dao.ClassroomImage;
import com.ss.nsdc.dao.GeneralImageModel;
import com.ss.nsdc.dao.ITIDBController;
import com.ss.nsdc.dao.InstructorImage;
import com.ss.nsdc.dao.LabImage;
import com.ss.nsdc.dao.LandImage;
import com.ss.nsdc.dao.OrganisationImage;
import com.ss.nsdc.dao.PowerImage;
import com.ss.nsdc.dao.TechnicalImage;
import com.ss.nsdc.dao.ToolsAboveImage;
import com.ss.nsdc.dao.ToolsBelow;
import com.ss.nsdc.entity.Workshop;
import com.ss.nsdc.main.ClassroomImageActivity;
import com.ss.nsdc.main.GeneralDetailImageActivity;
import com.ss.nsdc.main.InstructorImageActivity;
import com.ss.nsdc.main.LabImageActivity;
import com.ss.nsdc.main.LandBuildingDetails;
import com.ss.nsdc.main.OrganisationImageActivity;
import com.ss.nsdc.main.PhotoCategoriesActivity;
import com.ss.nsdc.main.PowerSupplyImageActivity;
import com.ss.nsdc.main.TechnicalImageActivity;
import com.ss.nsdc.main.ToolAboveImageActivity;
import com.ss.nsdc.main.ToolsBelowActivity;
import com.ss.nsdc.main.WorkshopImageActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radhika on 5/18/2017.
 */

public class PhotoCategoriesAdapter extends BaseAdapter {

    String[] categories;
    Activity currentActivity;
    String yearWiseCollegeId;
    String applicationId;
    ProgressDialog ringProgressDialog;
    private static LayoutInflater inflater = null;
    List<GeneralImageModel>  generalImageModel = new ArrayList<>();
    List<ClassroomImage>  classroomImages = new ArrayList<>();
    List<OrganisationImage> organisationImages = new ArrayList<>();
    List<LandImage> landImages = new ArrayList<>();
    List<TechnicalImage> technicalImages = new ArrayList<>();
    List<InstructorImage> instructorImages = new ArrayList<>();
    List<LabImage> labImages = new ArrayList<>();
    List<PowerImage> powerImages = new ArrayList<>();
    List<ToolsAboveImage> toolsAboveImages = new ArrayList<>();
    List<ToolsBelow> toolsBelows = new ArrayList<>();
    List<Workshop> workshops = new ArrayList<>();
    Boolean status;
    //List<ToolsBelow> toolsBelows = new ArrayList<>();
    public PhotoCategoriesAdapter(Activity activity, String[] categoryNameList, String yearWiseCollegeId) {

    }

    public PhotoCategoriesAdapter(Activity activity, String[] categoryNameList, String yearWiseCollegeId, Boolean status) {

        this.currentActivity = activity;
        this.categories = categoryNameList;
        this.yearWiseCollegeId = yearWiseCollegeId;
        this.status = status;
        inflater = (LayoutInflater) currentActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return categories.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    public static class Holder {
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.category_list, null);
            holder = new Holder();

            holder.tv = (TextView) convertView.findViewById(R.id.textCategoryName);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.tv.setText(categories[position]);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = null;
                Resources res = currentActivity.getResources();

                if (categories[position] != null && categories[position].equals(res.getString(R.string.text_general_details))) {
                    ITIDBController controller = new ITIDBController(currentActivity);
                    generalImageModel = controller.getGeneralDetailsImageInfobyYearwiseCollegeId(yearWiseCollegeId);
                    controller.close();

                    if(generalImageModel != null && generalImageModel.size()>0) {

                        intent = new Intent(currentActivity, GeneralDetailImageActivity.class);
                        intent.putExtra("yearWiseCollegeId", yearWiseCollegeId);
                        currentActivity.startActivity(intent);
                    }else {
                        Toast.makeText(currentActivity,"Data is not available in this section",Toast.LENGTH_LONG).show();
                    }

                }else if (categories[position] != null && categories[position].equals(res.getString(R.string.text_Organisation))) {

                    ITIDBController controller = new ITIDBController(currentActivity);
                    organisationImages = controller.getOrganisationImageInfobyYearwiseCollegeId(yearWiseCollegeId);
                    controller.close();

                    if(organisationImages != null && organisationImages.size()>0) {

                        intent = new Intent(currentActivity, OrganisationImageActivity.class);
                        intent.putExtra("yearWiseCollegeId", yearWiseCollegeId);
                        currentActivity.startActivity(intent);
                    }else {
                        Toast.makeText(currentActivity,"Data is not available in this section",Toast.LENGTH_LONG).show();
                    }


                }else if (categories[position] != null && categories[position].equals(res.getString(R.string.text_Land))) {
                    ITIDBController controller = new ITIDBController(currentActivity);
                    landImages = controller.getLandImageInfobyYearwiseCollegeId(yearWiseCollegeId);
                    controller.close();

                    if(landImages != null && landImages.size()>0) {

                        intent = new Intent(currentActivity, LandBuildingDetails.class);
                        intent.putExtra("yearWiseCollegeId", yearWiseCollegeId);
                        currentActivity.startActivity(intent);
                    }else {
                        Toast.makeText(currentActivity,"Data is not available in this section",Toast.LENGTH_LONG).show();
                    }


                }else if (categories[position] != null && categories[position].equals(res.getString(R.string.text_TechnicalStaffDocuments))) {
                    ITIDBController controller = new ITIDBController(currentActivity);
                    technicalImages = controller.getTechnicalImageInfobyYearwiseCollegeId(yearWiseCollegeId);
                    controller.close();

                    if(technicalImages != null && technicalImages.size()>0) {

                        intent = new Intent(currentActivity, TechnicalImageActivity.class);
                        intent.putExtra("yearWiseCollegeId", yearWiseCollegeId);
                        currentActivity.startActivity(intent);
                    }else {
                        Toast.makeText(currentActivity,"Data is not available in this section",Toast.LENGTH_LONG).show();
                    }


                }else if (categories[position] != null && categories[position].equals(res.getString(R.string.text_InstructorStaffDocuments))) {
                    ITIDBController controller = new ITIDBController(currentActivity);
                    instructorImages = controller.getInstructorImageInfobyYearwiseCollegeId(yearWiseCollegeId);
                    controller.close();

                    if(instructorImages != null && instructorImages.size()>0) {

                        intent = new Intent(currentActivity, InstructorImageActivity.class);
                        intent.putExtra("yearWiseCollegeId", yearWiseCollegeId);
                        currentActivity.startActivity(intent);
                    }else {
                        Toast.makeText(currentActivity,"Data is not available in this section",Toast.LENGTH_LONG).show();
                    }




                }else if (categories[position] != null && categories[position].equals(res.getString(R.string.text_ITLAB))) {

                    ITIDBController controller = new ITIDBController(currentActivity);
                    labImages = controller.getLabImageInfobyYearwiseCollegeId(yearWiseCollegeId);
                    controller.close();

                    if(labImages != null && labImages.size()>0) {

                        intent = new Intent(currentActivity, LabImageActivity.class);
                        intent.putExtra("yearWiseCollegeId", yearWiseCollegeId);
                        currentActivity.startActivity(intent);
                    }else {
                        Toast.makeText(currentActivity,"Data is not available in this section",Toast.LENGTH_LONG).show();
                    }

                }else if (categories[position] != null && categories[position].equals(res.getString(R.string.power))) {
                    ITIDBController controller = new ITIDBController(currentActivity);
                    powerImages = controller.getPowerImageInfobyYearwiseCollegeId(yearWiseCollegeId);
                    controller.close();

                    if(powerImages != null && powerImages.size()>0) {

                        intent = new Intent(currentActivity, PowerSupplyImageActivity.class);
                        intent.putExtra("yearWiseCollegeId", yearWiseCollegeId);
                        currentActivity.startActivity(intent);
                    }else {
                        Toast.makeText(currentActivity,"Data is not available in this section",Toast.LENGTH_LONG).show();
                    }


                }else if (categories[position] != null && categories[position].equals(res.getString(R.string.text_Classroom))) {
                    ITIDBController controller = new ITIDBController(currentActivity);
                    classroomImages = controller.getClassroomImageInfobyYearwiseCollegeId(yearWiseCollegeId);
                    controller.close();

                    if(classroomImages!=null && classroomImages.size()>0) {

                        intent = new Intent(currentActivity, ClassroomImageActivity.class);
                        intent.putExtra("yearWiseCollegeId", yearWiseCollegeId);
                        currentActivity.startActivity(intent);
                    }else {
                        Toast.makeText(currentActivity,"Data is not available in this section",Toast.LENGTH_LONG).show();
                    }
                }else if (categories[position] != null && categories[position].equals(res.getString(R.string.toolbelow100000))) {
                    ITIDBController controller = new ITIDBController(currentActivity);
                    toolsBelows = controller.getToolsBelowImageInfobyYearwiseCollegeId(yearWiseCollegeId);
                    controller.close();

                    if(toolsBelows!=null && toolsBelows.size()>0) {

                        intent = new Intent(currentActivity, ToolsBelowActivity.class);
                        intent.putExtra("yearWiseCollegeId", yearWiseCollegeId);
                        intent.putExtra("tradeId",toolsBelows.get(0).getTradeId());
                        currentActivity.startActivity(intent);
                    }else {
                        Toast.makeText(currentActivity,"Data is not available in this section",Toast.LENGTH_LONG).show();
                    }
                }else if (categories[position] != null && categories[position].equals(res.getString(R.string.toolabove100000))) {

                    ITIDBController controller = new ITIDBController(currentActivity);
                    toolsAboveImages = controller.getToolsAboveCheckImageInfobyYearwiseCollegeId(yearWiseCollegeId);
                    controller.close();

                    if(toolsAboveImages != null && toolsAboveImages.size()>0) {

                        intent = new Intent(currentActivity, ToolAboveImageActivity.class);
                        intent.putExtra("yearWiseCollegeId", yearWiseCollegeId);
                        currentActivity.startActivity(intent);
                    }else {
                        Toast.makeText(currentActivity,"Data is not available in this section",Toast.LENGTH_LONG).show();
                    }
                }
                else if (categories[position] != null && categories[position].equals(res.getString(R.string.text_Workshop))) {


                    ITIDBController controller = new ITIDBController(currentActivity);
                    workshops = controller.getWorkshopImageInfobyYearwiseCollegeId(yearWiseCollegeId);
                    controller.close();

                    if(workshops != null && workshops.size()>0) {

                        intent = new Intent(currentActivity, WorkshopImageActivity.class);
                        intent.putExtra("yearWiseCollegeId", yearWiseCollegeId);
                        currentActivity.startActivity(intent);
                    }else {
                        Toast.makeText(currentActivity,"Data is not available in this section",Toast.LENGTH_LONG).show();
                    }
                }


            }
        });
        return convertView;
    }





}
