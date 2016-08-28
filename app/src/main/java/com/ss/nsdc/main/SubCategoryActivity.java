package com.ss.nsdc.main;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.ss.nsdc.R;

public class SubCategoryActivity extends AppCompatActivity {

	private RecyclerView mRecyclerView;
	private RecyclerView.Adapter mAdapter;
	private RecyclerView.LayoutManager mLayoutManager;
	private String category;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub_category);
		
		Bundle bundle = getIntent().getExtras();
		
		 if (bundle != null) {
		      category = bundle.getString("Category");
		 }else{
			  category = "none";
		 }
		 
		Toolbar toolbar = (Toolbar) findViewById(R.id.sub_cat_toolbar);
		setSupportActionBar(toolbar); //
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle("Sub Category Listing");

		/*Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			categoryId = Integer.parseInt(bundle.getString("categoryId"));
		}
		
		NSDCDBController dbcontroller=new NSDCDBController(this);
		getSubCategoryList=dbcontroller.getSubCategoryList(categoryId);
		dbcontroller.close();
		*/
		
		mRecyclerView = (RecyclerView) findViewById(R.id.sub_cat_recycler_view);

		// use this setting to improve performance if you know that changes
		// in content do not change the layout size of the RecyclerView
		mRecyclerView.setHasFixedSize(true);

		// use a linear layout manager
		mLayoutManager = new LinearLayoutManager(this);
		mRecyclerView.setLayoutManager(mLayoutManager);

		ArrayList<String> myDataset =  new ArrayList<String>();
		for (int i = 1; i < 5; i++) {
			
			myDataset.add("Class Room "+i);
		}
		// specify an adapter (see also next example)
	//	mAdapter = new SubCategoryAdapter(myDataset, category);
		mRecyclerView.setAdapter(mAdapter);
	}
}