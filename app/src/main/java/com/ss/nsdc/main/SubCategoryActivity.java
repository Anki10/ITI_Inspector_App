package com.ss.nsdc.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ss.nsdc.R;

import java.util.ArrayList;

public class SubCategoryActivity extends AppCompatActivity {

	private RecyclerView mRecyclerView;
	private RecyclerView.Adapter mAdapter;
	private RecyclerView.LayoutManager mLayoutManager;
	private String category;
	private String[] mPlanetTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
    Context context = this;

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
		setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle("Sub Category Listing");

        mPlanetTitles = getResources().getStringArray(R.array.categories);
        //mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mPlanetTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(context, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
            }
        });
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