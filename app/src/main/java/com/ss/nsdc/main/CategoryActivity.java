package com.ss.nsdc.main;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.ss.nsdc.R;
import com.ss.nsdc.adapter.CategoryAdapter;

public class CategoryActivity extends AppCompatActivity {
	
	private RecyclerView mRecyclerView;
	private RecyclerView.Adapter mAdapter;
	private RecyclerView.LayoutManager mLayoutManager;
	String instituteId,applicationId,instituteName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		
		Toolbar toolbar = (Toolbar) findViewById(R.id.cat_toolbar);
		setSupportActionBar(toolbar); 
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle("Category Listing");

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
		    instituteId = bundle.getString("YearWiseCollegeId");
		    applicationId=bundle.getString("applicationNo");
		    instituteName=bundle.getString("instituteName");
		}
		
		/*NSDCDBController dbcontroller=new NSDCDBController(this);
		getCategoryList=dbcontroller.getCategoryList(instituteId);
		dbcontroller.close();*/
		//prepareInstituteData();

		mRecyclerView = (RecyclerView) findViewById(R.id.cat_recycler_view);

		// use this setting to improve performance if you know that changes
		// in content do not change the layout size of the RecyclerView
		mRecyclerView.setHasFixedSize(true);

		// use a linear layout manager
		mLayoutManager = new LinearLayoutManager(this);
		mRecyclerView.setLayoutManager(mLayoutManager);

		ArrayList<String> myDataset1 =  new ArrayList<String>();

		myDataset1.add("General");
		myDataset1.add("Job Roles");
		myDataset1.add("Classrooms Details");
		myDataset1.add("Laboratories Details");
		myDataset1.add("Office Areas");
		myDataset1.add("Residential Facilities");
		myDataset1.add("Facilities");
		myDataset1.add("Add Trainer's Detail");
		myDataset1.add("Support Staff");
		myDataset1.add("Equipments");
		myDataset1.add("Upload Images");
		
		// specify an adapter (see also next example)
		mAdapter = new CategoryAdapter(myDataset1,instituteId,applicationId);
		mRecyclerView.setAdapter(mAdapter);
	}
}
