package com.ss.nsdc.main;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ss.nsdc.R;
import com.ss.nsdc.adapter.InstituteAdapter;
import com.ss.nsdc.constant.AppConstants;
import com.ss.nsdc.dao.ITIDBController;
import com.ss.nsdc.dao.Institute;
import com.ss.nsdc.dao.User;
import com.ss.nsdc.utility.DataUtils;

import org.json.JSONObject;

public class InstituteActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    protected ActionBarDrawerToggle mDrawerToggle;
    Context context = this;
    ITIDBController db;
    List<Institute> getInstituteList;
    String currDate;

    ProgressDialog ringProgressDialog;

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

        ITIDBController sqliController = new ITIDBController(context);
        getInstituteList = sqliController.getInstituteList();
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.institute, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_logout:
                new AlertDialog.Builder(context)
                        .setTitle("Confirmation")
                        .setMessage("Please Sync all the pending data before Logout to avoid any loss of data.")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ITIDBController controller = new ITIDBController(context);
                                boolean result = controller.deleteAllData(InstituteActivity.this);


                                DataUtils.deleteSharedPref(InstituteActivity.this);


                                if (result) {
                                    Intent intent = new Intent(context, Login.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(context, "Logout Failed..", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton(android.R.string.cancel, null).show();
                return true;

            case R.id.action_refresh:
                ITIDBController sqllite = new ITIDBController(context);
                User user = sqllite.getLoggedUser();
                sqllite.close();
                new UpdateInstituteData().execute(user);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class UpdateInstituteData extends AsyncTask<User, Integer, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(context, android.R.style.Theme_Holo), null, "Fetching Allocations from server..", true);
            ringProgressDialog.setCancelable(false);
            ringProgressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected JSONObject doInBackground(User... user) {
            JSONObject response = updateInstituteList(user);
            return response;

        }

        @Override
        protected void onPostExecute(JSONObject result) {
            try {
                Log.e("result",result.toString());
                int status = result.optInt("status");
                if (status == 0) {
                    ITIDBController sqllite = new ITIDBController(context);
                    boolean add_status = sqllite.addInstituteData(result);
                    ringProgressDialog.cancel();
                    if (add_status) {
                        Toast.makeText(context, "Allocation Updated Successfully", Toast.LENGTH_LONG).show();
                        getInstituteList = sqllite.getInstituteList();
                        mAdapter.notifyDataSetChanged();
                    }
                }else {
                    Toast.makeText(context, "No Data Found..", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Log.e("Login", "Allocation Data Failed..");
            }

        }
    }

    public JSONObject updateInstituteList(User... user) {
        String response = "";
        JSONObject response_json = null;
        try {
            URL url = new URL(AppConstants.URL_ALLOCATION_DETAILS);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            String str = "Userid=" + user[0].getUserid();
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

