package com.ss.nsdc.main;


import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ss.nsdc.R;
import com.ss.nsdc.adapter.InstituteAdapter;
import com.ss.nsdc.dao.Institute;
import com.ss.nsdc.dao.NSDCDBController;
import com.ss.nsdc.utility.UtilityService;

public class InstituteActivity extends AppCompatActivity {

	private RecyclerView mRecyclerView;
	private RecyclerView.Adapter mAdapter;
	private RecyclerView.LayoutManager mLayoutManager;
	private DrawerLayout mDrawerLayout;
	private NavigationView mNavigationView;
	protected ActionBarDrawerToggle mDrawerToggle;
	Context context=this;
	NSDCDBController db;
	List<Institute> getInstituteList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_institute);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar); //
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle("Allocated Institutes");

		setupToolbar();
		//initNavigationDrawer();
		
		mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

		// use this setting to improve performance if you know that changes
		// in content do not change the layout size of the RecyclerView
		mRecyclerView.setHasFixedSize(true);

		// use a linear layout manager
		mLayoutManager = new LinearLayoutManager(this);
		mRecyclerView.setLayoutManager(mLayoutManager);

		NSDCDBController sqliController=new NSDCDBController(context);
		getInstituteList=sqliController.getInstituteList();
		sqliController.close();

		// specify an adapter (see also next example)
		mAdapter = new InstituteAdapter(getInstituteList);
		mRecyclerView.setAdapter(mAdapter);
	}


	private void setupToolbar() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar); //
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle("Allocated Institutes");

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();		
	}
/*	private void initNavigationDrawer() {

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

	}
	
    @Override
    public void onBackPressed() {
        if (isNavDrawerOpen()) {
            closeNavDrawer();
        } else {
            super.onBackPressed();
        }
    }

    protected boolean isNavDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    protected void closeNavDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }*/

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {

            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.nav_bckp:
            	UtilityService.exportDatabse(null);
            	Toast.makeText(getApplicationContext(), "Backing db", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}

