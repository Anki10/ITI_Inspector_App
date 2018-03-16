package com.ss.nsdc.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ss.nsdc.R;
import com.ss.nsdc.dao.ITIDBController;
import com.ss.nsdc.dao.ToolsAboveImage;
import com.ss.nsdc.entity.Accommodation;
import com.ss.nsdc.entity.Classroom;
import com.ss.nsdc.entity.InstructorInfo;
import com.ss.nsdc.entity.TechincalInfo;
import com.ss.nsdc.entity.TradeWiseTool;
import com.ss.nsdc.entity.WorkshopAreaModel;
import com.ss.nsdc.main.FormActivity;
import com.ss.nsdc.main.SubCategoryToolsAbove;
import com.ss.nsdc.main.SubCategoryTradeWiseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radhika on 5/12/2017.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder>{
    private Context context;
    private String category,inspectionType;
    private List<TechincalInfo> technicalInfoList;
    private List<InstructorInfo> instructorInfoList;
    private List<Accommodation> accommodationInfoList;
    private List<Accommodation> toolsAbove10K;
    private List<Classroom> getclassList;
    private List<WorkshopAreaModel> workshopAreaList;
    private List<TradeWiseTool> tradeWiseTools = new ArrayList<>();
    private List<Accommodation> accommodationWiseList = new ArrayList<>();
    private List<ToolsAboveImage> toolsAboveImageList = new ArrayList<>();
    int count = 1;




    public SubCategoryAdapter(List getInfoList, String category, Context context) {

        if(category.equalsIgnoreCase("technical")){
            this.technicalInfoList = getInfoList;
        }else if(category.equalsIgnoreCase("instructor")){
            this.instructorInfoList = getInfoList;
        }else if(category.equalsIgnoreCase("trades")){
            this.accommodationInfoList = getInfoList;
        }else if(category.equalsIgnoreCase("toolsAbove10K")){
            this.toolsAbove10K = getInfoList;
        }else if(category.equalsIgnoreCase("class")){
            this.getclassList = getInfoList;
        }else if(category.equalsIgnoreCase("workshopArea")){//Added by PN 28 May '17 - WorkshopArea
            this.workshopAreaList = getInfoList;
        }else if(category.equalsIgnoreCase("tradesWise")){
            this.accommodationWiseList = getInfoList;
        }else if(category.equalsIgnoreCase("toolsAbovePhoto")){
            this.toolsAboveImageList = getInfoList;
        }


        this.context = context;
        this.category = category;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtSubCatName;
        public TextView txtSubCatDesignation;
        public ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            txtSubCatName = (TextView) v.findViewById(R.id.sub_cat_name);
            txtSubCatDesignation = (TextView) v.findViewById(R.id.sub_cat_designation);
            imageView = (ImageView) v.findViewById(R.id.status);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        // create a new view
        View v = LayoutInflater.from(context).inflate(
                R.layout.sub_category_list_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final  ViewHolder holder, final int position) {
        if (category.equalsIgnoreCase("technical")) {
            //Toast.makeText(context,"name"+technicalInfoList.get(position).getName(),Toast.LENGTH_LONG).show();
            holder.txtSubCatName.setText(technicalInfoList.get(position).getName());
            holder.txtSubCatDesignation.setText(technicalInfoList.get(position).getDesignation());
            if (technicalInfoList.get(position).getProc_tracker() == 1) {// New Data
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
            } else if (technicalInfoList.get(position).getProc_tracker() == 2) { // not synced
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.progress));
            } else if (technicalInfoList.get(position).getProc_tracker() == 3) { // synced Data
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
            }
        }else if (category.equalsIgnoreCase("instructor")) {
            //Toast.makeText(context,"name"+instructorInfoList.get(position).getName(),Toast.LENGTH_LONG).show();
            holder.txtSubCatName.setText(instructorInfoList.get(position).getName());
            holder.txtSubCatDesignation.setText(instructorInfoList.get(position).getTradeName());
            if (instructorInfoList.get(position).getProc_tracker() == 1) {// New Data
                Log.e("value", String.valueOf(instructorInfoList.get(position).getProc_tracker()));
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
            } else if (instructorInfoList.get(position).getProc_tracker() == 2) { // not synced
                Log.e("value", String.valueOf(instructorInfoList.get(position).getProc_tracker()));
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.progress));
            } else if (instructorInfoList.get(position).getProc_tracker() == 3) { // synced Data
                Log.e("value", String.valueOf(instructorInfoList.get(position).getProc_tracker()));
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
            }
        }else if (category.equalsIgnoreCase("trades")) {
            //Toast.makeText(context,"name"+instructorInfoList.get(position).get(),Toast.LENGTH_LONG).show();
            holder.txtSubCatName.setText(accommodationInfoList.get(position).getNameofTrade());
            Log.e("name", accommodationInfoList.get(position).getNameofTrade());
            holder.txtSubCatDesignation.setVisibility(View.GONE);
            if (accommodationInfoList.get(position).getProc_tracker() == 1) {// New Data
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
                Log.e("value", String.valueOf(accommodationInfoList.get(position).getProc_tracker()));
            } else if (accommodationInfoList.get(position).getProc_tracker() == 2) { // not synced
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.progress));
                Log.e("value", String.valueOf(accommodationInfoList.get(position).getProc_tracker()));
            } else if (accommodationInfoList.get(position).getProc_tracker() == 3) { // synced Data
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
                Log.e("value", String.valueOf(accommodationInfoList.get(position).getProc_tracker()));
            }
        }else if (category.equalsIgnoreCase("toolsAbove10K")) {
            //Toast.makeText(context,"name"+instructorInfoList.get(position).get(),Toast.LENGTH_LONG).show();
            holder.txtSubCatName.setText(toolsAbove10K.get(position).getNameofTrade());
            Log.e("name",toolsAbove10K.get(position).getNameofTrade());
            holder.txtSubCatDesignation.setVisibility(View.GONE);
            if (toolsAbove10K.get(position).getProc_tracker() == 1) {// New Data
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
            } else if (toolsAbove10K.get(position).getProc_tracker() == 2) { // not synced
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.progress));
            } else if (toolsAbove10K.get(position).getProc_tracker() == 3) { // synced Data
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
            }
        }else if (category.equalsIgnoreCase("class")) {
            //Toast.makeText(context,"name"+instructorInfoList.get(position).get(),Toast.LENGTH_LONG).show();
            holder.txtSubCatName.setText(getclassList.get(position).getClassroomName());
           // Log.e("name",accommodationInfoList.get(position).getNameofTrade());
            holder.txtSubCatDesignation.setVisibility(View.GONE);
            if (getclassList.get(position).getProc_tracker() == 1) {// New Data
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
            } else if (getclassList.get(position).getProc_tracker() == 2) { // not synced
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.progress));
            } else if (getclassList.get(position).getProc_tracker() == 3) { // synced Data
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
            }
        }else if (category.equalsIgnoreCase("workshopArea")) {//Added by PN 28 May '17 - WorkshopArea
            holder.txtSubCatName.setText(workshopAreaList.get(position).getTradeName());
            holder.txtSubCatDesignation.setVisibility(View.GONE);
            if (workshopAreaList.get(position).getProc_tracker() == 1) {// New Data
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
            } else if (workshopAreaList.get(position).getProc_tracker() == 2) { // not synced
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.progress));
            } else if (workshopAreaList.get(position).getProc_tracker() == 3) { // synced Data
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
            }
        }else if(category.equalsIgnoreCase("tradesWise")){
            holder.txtSubCatName.setText(accommodationWiseList.get(position).getNameofTrade());
            holder.txtSubCatDesignation.setVisibility(View.GONE);
            if (accommodationWiseList.get(position).getProc_tracker() == 1) {// New Data
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
            } else if (accommodationWiseList.get(position).getProc_tracker() == 2) { // not synced
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.progress));
            } else if (accommodationWiseList.get(position).getProc_tracker() == 3) { // synced Data
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
            }
        }else if(category.equalsIgnoreCase("tradesWise")){
            holder.txtSubCatName.setText(accommodationWiseList.get(position).getNameofTrade());
            holder.txtSubCatDesignation.setVisibility(View.GONE);
            if (accommodationWiseList.get(position).getProc_tracker() == 1) {// New Data
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
            } else if (accommodationWiseList.get(position).getProc_tracker() == 2) { // not synced
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.progress));
            } else if (accommodationWiseList.get(position).getProc_tracker() == 3) { // synced Data
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
            }
        }



        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(category.equalsIgnoreCase("technical")){
//                    if (technicalInfoList.get(position).getProc_tracker() == 1) {// New Data
                        Intent intent = new Intent(context, FormActivity.class);
                        intent.putExtra("Category", category);
                        intent.putExtra("YearWiseCollegeId", technicalInfoList.get(position).getYearwisecollegeid());
                        intent.putExtra("refId",technicalInfoList.get(position).getRefId());
                        Log.e("refId", String.valueOf(technicalInfoList.get(position).getRefId()));
                        context.startActivity(intent);
//                    } else if (technicalInfoList.get(position).getProc_tracker() == 2) {
//                        Toast.makeText(context, "This Data is waiting to be synced and cannot be edited", Toast.LENGTH_SHORT).show();
//                    } else if (technicalInfoList.get(position).getProc_tracker() == 3) { //
//                        Toast.makeText(context, "This Data is synced and cannot be edited", Toast.LENGTH_SHORT).show();
//                    }
                }else if(category.equalsIgnoreCase("instructor")){
//                    if (instructorInfoList.get(position).getProc_tracker() == 1) {// New Data
                        Intent intent = new Intent(context, FormActivity.class);
                        intent.putExtra("Category", category);
                        intent.putExtra("YearWiseCollegeId", instructorInfoList.get(position).getYearwisecollegeid());
                        intent.putExtra("refId",instructorInfoList.get(position).getRefId());
                        context.startActivity(intent);
//                    } else if (instructorInfoList.get(position).getProc_tracker() == 2) {
//                        Toast.makeText(context, "This Data is waiting to be synced and cannot be edited", Toast.LENGTH_SHORT).show();
//                    } else if (instructorInfoList.get(position).getProc_tracker() == 3) { //
//                        Toast.makeText(context, "This Data is synced and cannot be edited", Toast.LENGTH_SHORT).show();
//                    }
                }else if(category.equalsIgnoreCase("trades")){
                    ITIDBController controller = new ITIDBController(context);


//                    if (accommodationInfoList.get(position).getProc_tracker() == 1) {// New Data
                        Intent intent = new Intent(context, SubCategoryTradeWiseActivity.class);
                        intent.putExtra("Category", category);
                        intent.putExtra("YearWiseCollegeId", accommodationInfoList.get(position).getYearWiseCollegeid());
                        intent.putExtra("tradeId", accommodationInfoList.get(position).getTradeid());
                        context.startActivity(intent);
//                    } else if (accommodationInfoList.get(position).getProc_tracker() == 2) {
//                        Toast.makeText(context, "This Data is waiting to be synced and cannot be edited", Toast.LENGTH_SHORT).show();
//                    } else if (accommodationInfoList.get(position).getProc_tracker() == 3) { //
//                        Toast.makeText(context, "This Data is synced and cannot be edited", Toast.LENGTH_SHORT).show();
//                    }
                }else if(category.equalsIgnoreCase("toolsAbove10K")){
                    //if (toolsAbove10K.get(position).getProc_tracker() == 1) {// New Data
                        //Toast.makeText(context,"Feature under Development",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, SubCategoryToolsAbove.class);
                        intent.putExtra("Category", category);
                        //Log.e("year",accommodationInfoList.get(position).getYearWiseCollegeid());
                        intent.putExtra("YearWiseCollegeId", toolsAbove10K.get(position).getYearWiseCollegeid());
                        intent.putExtra("tradeId",toolsAbove10K.get(position).getTradeid());
                        context.startActivity(intent);
//                    } else if (toolsAbove10K.get(position).getProc_tracker() == 2) {
//                        Toast.makeText(context, "This Data is waiting to be synced and cannot be edited", Toast.LENGTH_SHORT).show();
//                    } else if (toolsAbove10K.get(position).getProc_tracker() == 3) { //
//                        Toast.makeText(context, "This Data is synced and cannot be edited", Toast.LENGTH_SHORT).show();
//                    }
                }else if(category.equalsIgnoreCase("class")){
//                    if (getclassList.get(position).getProc_tracker() == 1) {// New Data
                        Intent intent = new Intent(context, FormActivity.class);
                        intent.putExtra("Category", category);
                        intent.putExtra("YearWiseCollegeId", getclassList.get(position).getYearwisecollegeid());
                        intent.putExtra("refId",getclassList.get(position).getRefId());
                        context.startActivity(intent);
//                    } else if (getclassList.get(position).getProc_tracker() == 2) {
//                        Toast.makeText(context, "This Data is waiting to be synced and cannot be edited", Toast.LENGTH_SHORT).show();
//                    } else if (getclassList.get(position).getProc_tracker() == 3) { //
//                        Toast.makeText(context, "This Data is synced and cannot be edited", Toast.LENGTH_SHORT).show();
//                    }
                }else if(category.equalsIgnoreCase("workshopArea")){//Added by PN 28 May '17 - WorkshopArea
//                    if (workshopAreaList.get(position).getProc_tracker() == 1) {// New Data
                        Intent intent = new Intent(context, FormActivity.class);
                        intent.putExtra("Category", category);
                        intent.putExtra("YearWiseCollegeId", workshopAreaList.get(position).getYearWiseCollegeid());
                        intent.putExtra("refId",workshopAreaList.get(position).getId());
                        context.startActivity(intent);
//                    } else if (workshopAreaList.get(position).getProc_tracker() == 2) {
//                        Toast.makeText(context, "This Data is waiting to be synced and cannot be edited", Toast.LENGTH_SHORT).show();
//                    } else if (workshopAreaList.get(position).getProc_tracker() == 3) { //
//                        Toast.makeText(context, "This Data is synced and cannot be edited", Toast.LENGTH_SHORT).show();
//                    }
                }else if(category.equalsIgnoreCase("tradesWise")){
                ITIDBController controller = new ITIDBController(context);


                  //  if (accommodationWiseList.get(position).getProc_tracker() == 1) {// New Data
                Intent intent = new Intent(context, SubCategoryTradeWiseActivity.class);
                intent.putExtra("Category", category);
                intent.putExtra("YearWiseCollegeId", accommodationWiseList.get(position).getYearWiseCollegeid());
                intent.putExtra("tradeId", accommodationWiseList.get(position).getTradeid());
                intent.putExtra("value",count);
                context.startActivity(intent);
//                    } else if (accommodationWiseList.get(position).getProc_tracker() == 2) {
//                        Toast.makeText(context, "This Data is waiting to be synced and cannot be edited", Toast.LENGTH_SHORT).show();
//                    } else if (accommodationWiseList.get(position).getProc_tracker() == 3) { //
//                        Toast.makeText(context, "This Data is synced and cannot be edited", Toast.LENGTH_SHORT).show();
//                    }
            }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(category.equalsIgnoreCase("technical")){
            return technicalInfoList.size();
        }else if(category.equalsIgnoreCase("instructor")){
            return instructorInfoList.size();
        }else if(category.equalsIgnoreCase("trades")){
            return accommodationInfoList.size();
        }else if(category.equalsIgnoreCase("toolsAbove10K")){
            return toolsAbove10K.size();
        }else if(category.equalsIgnoreCase("class")){
            return getclassList.size();
        }else if(category.equalsIgnoreCase("workshopArea")){//Added by PN 28 May '17 - WorkshopArea
            return workshopAreaList.size();
        }else if(category.equalsIgnoreCase("tradesWise")){//Added by PN 28 May '17 - WorkshopArea
            return accommodationWiseList.size();
        }


        return 0;
    }
}
