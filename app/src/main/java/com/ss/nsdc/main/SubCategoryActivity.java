package com.ss.nsdc.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ss.nsdc.R;
import com.ss.nsdc.adapter.SubCategoryAdapter;
import com.ss.nsdc.dao.ITIDBController;
import com.ss.nsdc.dao.ToolsAboveImage;
import com.ss.nsdc.entity.Accommodation;
import com.ss.nsdc.entity.Classroom;
import com.ss.nsdc.entity.InstructorInfo;
import com.ss.nsdc.entity.TechincalInfo;
import com.ss.nsdc.entity.WorkshopAreaModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radhika on 5/12/2017.
 */

public class SubCategoryActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public String category, yearWiseCollegeId, applicationId, classId, filter,refId;
    ProgressDialog ringProgressDialog;
    Context context = this;
    List<TechincalInfo> getTechnicalInfoList = new ArrayList<TechincalInfo>();
    List<InstructorInfo> getInstructorInfoList = new ArrayList<InstructorInfo>();
    List<Accommodation> getTradeInfoList = new ArrayList<Accommodation>();
    List<Accommodation> getToolsAboveTenKInfoList = new ArrayList<Accommodation>();
    List<Classroom> getclassroom = new ArrayList<Classroom>();
    List<WorkshopAreaModel> workshopAreaList = new ArrayList<WorkshopAreaModel>();//Added by PN 28 May '17 - WorkshopArea
    List<Accommodation> tradeWiseList = new ArrayList<>();
    List<ToolsAboveImage> toolsAboveImageList = new ArrayList<>();




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            category = bundle.getString("Category");
            yearWiseCollegeId = bundle.getString("YearWiseCollegeId");
        } else {
            category = "none";
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.sub_cat_toolbar);
        setSupportActionBar(toolbar); //
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sub Category Listing");

        ITIDBController controller = new ITIDBController(this);
        if(category.equalsIgnoreCase("technical")){
        getTechnicalInfoList = controller.getTechnicalInfoList(yearWiseCollegeId);
        }else if(category.equalsIgnoreCase("instructor")){
            getInstructorInfoList = controller.getInstructorInfoList(yearWiseCollegeId);
        }else if(category.equalsIgnoreCase("trades")){
            getTradeInfoList = controller.getTradesInfoList(yearWiseCollegeId);
        }else if(category.equalsIgnoreCase("toolsAbove10K")){
            getToolsAboveTenKInfoList = controller.getToolsAboveTenkInfoList(yearWiseCollegeId);
        }else if(category.equalsIgnoreCase("class")){
            getclassroom = controller.getClassroomInfoList(yearWiseCollegeId);
        }else if(category.equalsIgnoreCase("workshopArea")){ //Added by PN 28 May '17 - WorkshopArea
            workshopAreaList = controller.getWorkshopAreaList(yearWiseCollegeId);
        }else if(category.equalsIgnoreCase("tradesWise")){
            tradeWiseList = controller.getTradesWiseInfoList(yearWiseCollegeId);
        }else if(category.equalsIgnoreCase("toolsAbovePhoto")){
            toolsAboveImageList = controller.getToolsAboveCheckImageInfobyYearwiseCollegeId(yearWiseCollegeId);
        }


        controller.close();

        mRecyclerView = (RecyclerView) findViewById(R.id.sub_cat_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        if(category.equalsIgnoreCase("technical")){
            actionBar.setTitle("Select Technical Staff");
            mAdapter = new SubCategoryAdapter(getTechnicalInfoList,category,context);
        }else if(category.equalsIgnoreCase("instructor")){
            actionBar.setTitle("Select Instructor Staff");
            mAdapter = new SubCategoryAdapter(getInstructorInfoList,category,context);
        }else if(category.equalsIgnoreCase("trades")){
            actionBar.setTitle("Select Accommodation");
            mAdapter = new SubCategoryAdapter(getTradeInfoList,category,context);
        }else if(category.equalsIgnoreCase("toolsAbove10K")){
            actionBar.setTitle("Select Tools Above 10000");
            mAdapter = new SubCategoryAdapter(getToolsAboveTenKInfoList,category,context);
        }else if(category.equalsIgnoreCase("class")){
            actionBar.setTitle("Select Classroom");
            mAdapter = new SubCategoryAdapter(getclassroom,category,context);
        }else if(category.equalsIgnoreCase("workshopArea")){//Added by PN 28 May '17 - WorkshopArea
            actionBar.setTitle("Select Workshop Area");
            mAdapter = new SubCategoryAdapter(workshopAreaList,category,context);
        }else if(category.equalsIgnoreCase("tradesWise")){//Added by PN 28 May '17 - WorkshopArea
            actionBar.setTitle("Select Accommodation");
            mAdapter = new SubCategoryAdapter(tradeWiseList,category,context);
        }else if(category.equalsIgnoreCase("toolsAbovePhoto")){
            actionBar.setTitle("Select Tools Above 10000");
            mAdapter = new SubCategoryAdapter(toolsAboveImageList,category,context);
        }



        mRecyclerView.setAdapter(mAdapter);
        //mAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sub_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.category_list:
                Intent intent = new Intent(context, CategoryActivity.class);
                intent.putExtra("YearWiseCollegeId", yearWiseCollegeId);
                intent.putExtra("applicationNo", applicationId);
                //intent.putExtra("instituteName", context.getExtras().getString("ApplicationId"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
                return true;

            case R.id.institute_list:
                Intent intent1 = new Intent(context, InstituteActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
                return true;

            case R.id.menuExit:
                Intent intent2 = new Intent(Intent.ACTION_MAIN);
                intent2.addCategory(Intent.CATEGORY_HOME);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(context, CategoryActivity.class);
        intent.putExtra("YearWiseCollegeId", yearWiseCollegeId);
        //intent.putExtra("instituteName", context.getExtras().getString("ApplicationId"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
}
