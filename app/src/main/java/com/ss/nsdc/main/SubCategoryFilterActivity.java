package com.ss.nsdc.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.ss.nsdc.R;
import com.ss.nsdc.adapter.CategoryAdapter;
import com.ss.nsdc.adapter.SubCatFilterAdapter;
import com.ss.nsdc.adapter.SubCategoryClassAdapter;
import com.ss.nsdc.dao.NSDCDBController;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryFilterActivity extends AppCompatActivity {
	private RecyclerView mRecyclerView;
	private RecyclerView.Adapter mAdapter;
	private RecyclerView.LayoutManager mLayoutManager;
	private String category,yearWiseCollegeId,applicationId,classId;
	ProgressDialog ringProgressDialog;
	Context context=this;
    List<String> getEquipmentJobRoles=new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub_category);
		
		Bundle bundle = getIntent().getExtras();
		 if (bundle != null) {
		      category = bundle.getString("Category");
		      yearWiseCollegeId=bundle.getString("YearWiseCollegeId");
		      applicationId=bundle.getString("applicationId");
		      classId=bundle.getString("ClassId");
		 } else {
			  category = "none";
		 }
		 
		Toolbar toolbar = (Toolbar) findViewById(R.id.sub_cat_toolbar);
		setSupportActionBar(toolbar); //
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		ActionBar actionBar = getSupportActionBar();
        if(category.equalsIgnoreCase("equipment")) {
            actionBar.setTitle("Job Roles");
        }else {
            actionBar.setTitle("Filter Listing");
        }

		NSDCDBController dbcontroller = new NSDCDBController(this);

		if(category.equalsIgnoreCase("equipment")) {
			getEquipmentJobRoles=dbcontroller.getEquipmentJobRoles();
		}
		dbcontroller.close();

		mRecyclerView = (RecyclerView) findViewById(R.id.sub_cat_recycler_view);

		// use this setting to improve performance if you know that changes
		// in content do not change the layout size of the RecyclerView
		mRecyclerView.setHasFixedSize(true);

		// use a linear layout manager
		mLayoutManager = new LinearLayoutManager(this);
		mRecyclerView.setLayoutManager(mLayoutManager);
		
		
		// specify an adapter (see also next example)
		if(category.equalsIgnoreCase("equipment")) {
			//mAdapter = new SubCategoryClassAdapter(getEquipmentJobRoles, "equipment_jobroles", context);
			mAdapter = new SubCatFilterAdapter(getEquipmentJobRoles,yearWiseCollegeId,applicationId,"equipment_jobroles");
		}

		mRecyclerView.setAdapter(mAdapter);
		
	}
}

