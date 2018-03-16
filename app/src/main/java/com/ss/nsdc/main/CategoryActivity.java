package com.ss.nsdc.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Process;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.ss.nsdc.R;
import com.ss.nsdc.adapter.CategoryAdapter;
import com.ss.nsdc.constant.AppConstants;
import com.ss.nsdc.dao.User;
import com.ss.nsdc.utility.GPSTracker;
import com.ss.nsdc.utility.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CategoryActivity extends AppCompatActivity
        implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String instituteId, applicationId, instituteName;

    Context context = this;

    Button btnStartInspection;
    int flag, rowcount, rowcount1;
    Button btnStopInspection;
    Activity currentActivity;

    boolean isStartInspection;

    String latitude;
    String longitude, inspectionFromDate, inspectionToDate, inspectionType;
    String section, pending, section1, pending1;
    GPSTracker gps;

    ProgressDialog ringProgressDialog;

    SharedPreferences sharedPreferences;

    ProgressDialog pdialog;

    boolean inspectionStartStopFlag;
    View customDialogView;
    View customDialogView1, customDialogView2;
    Dialog customDialog;
    Button Cancel, Cancel1, Cancel2;
    TextView report, heading;
    TextView finalText, pending_text;
    String currDate;
    Date From, To, Current;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        currentActivity = CategoryActivity.this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.cat_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Category Listing");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            instituteId = bundle.getString("YearWiseCollegeId");
            instituteName = bundle.getString("instituteName");

        }

        mRecyclerView = (RecyclerView) findViewById(R.id.cat_recycler_view);
        btnStartInspection = (Button) findViewById(R.id.btnStartInspection);
        btnStopInspection = (Button) findViewById(R.id.btnStopInspection);

        btnStartInspection.setOnClickListener(this);
        btnStopInspection.setOnClickListener(this);


        //    sharedPreferences.contains("isStarted");

        if (sharedPreferences.getBoolean("isStarted", false)) {
            btnStartInspection.setEnabled(false);
        }
        if (sharedPreferences.getBoolean("isCompleted", false)) {
            btnStopInspection.setEnabled(false);
        }

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<String> myDataset1 = new ArrayList<String>();
        myDataset1.add("General");//0
        myDataset1.add("Accommodation and Environmental Conditions");//1
        myDataset1.add("Equipment");//2
        myDataset1.add("Material");//3
        myDataset1.add("Staffing");//4
        myDataset1.add("Documentation");//5
        myDataset1.add("Health and Safety");//6
        myDataset1.add("Safety and Waste Disposal");//7
        myDataset1.add("Transport of Pathology Specimens");//8
        myDataset1.add("Packaging");//9
        myDataset1.add("Complaints / Feedback");//10


        // specify an adapter (see also next example)
        mAdapter = new CategoryAdapter(myDataset1, instituteId);
        mRecyclerView.setAdapter(mAdapter);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), InstituteActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.da_report, menu);
//        return true;
//    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnStartInspection: {
                isStartInspection = true;
                alert(currentActivity, getString(R.string.alert), getString(R.string.labelStartInspection), getString(android.R.string.ok), getString(R.string.cancel), true, false);

                break;
            }
            case R.id.btnStopInspection: {
                isStartInspection = false;
                alert(currentActivity, getString(R.string.alert), getString(R.string.labelCompleteInspection), getString(android.R.string.ok), getString(R.string.cancel), true, false);

                break;
            }
        }
    }


    public void alert(Context context, String title, String message, String positiveButton, String negativeButton, boolean isNegativeButton, boolean isTitle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (isTitle) {
            builder.setTitle(title);
        }

        builder.setMessage(message);
        builder.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

                if (isStartInspection) {

                    if (getCompleteLocation()) {
                        new ExecuteSendStartInspectionLocation().execute();
                    }

                } else {
                    if (getCompleteLocation()) {
                        new ExecuteSendComleteInspectionLocation().execute();
                    }
                }
            }
        });
        if (isNegativeButton) {
            builder.setNegativeButton(negativeButton, null);
        }

        builder.show();
    }

    private boolean getCompleteLocation() {
        boolean result = Utility.checkLocationPermission(currentActivity);
        if (result) {
            gps = GPSTracker.getInstance(getApplicationContext());
        }

        if (gps == null) {
            Utility.showLocationSettingsAlertInMarshmallow(currentActivity);
        } else {
            if (!gps.isGPS()) {
                Utility.showGPSSettingsAlert(currentActivity);
            } else {
                if (gps.getLocation() != null || gps.getLatitude() != 0.0 || gps.getLongitude() != 0.0) {
                    latitude = String.valueOf(gps.getLatitude());
                    longitude = String.valueOf(gps.getLongitude());
                    return true;

                } else {
                    showAppRestartAlert(CategoryActivity.this);

                    //  Toast.makeText(currentActivity, "Location Unavailable Please wait...", Toast.LENGTH_LONG).show();
                }
            }
        }
        return false;

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {

            case Utility.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    gps = GPSTracker.getInstance(getApplicationContext());

                    if (isStartInspection) {

                        if (getCompleteLocation()) {
                            new ExecuteSendStartInspectionLocation().execute();
                        }

                    } else {
                        if (getCompleteLocation()) {
                            new ExecuteSendComleteInspectionLocation().execute();

                        }
                    }

                } else {
                    // showAppRestartAlert(CategoryActivity.this);
                    Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access location data.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    public JSONObject sendStartInspectionLocation() {
        String response = "";
        JSONObject response_json = null;
        try {
            JSONObject resultObj = new JSONObject();
            JSONObject payloadObj = new JSONObject();
            JSONObject startObj = new JSONObject();
            JSONObject startObj1 = new JSONObject();
            startObj1.put("Latitude1", latitude);
            startObj1.put("Longitude1", longitude);
            startObj.put("Start", startObj1);
            payloadObj.put("payload", startObj);
            startObj.put("yearWiseCollegeId", instituteId);
            resultObj.put("result", payloadObj);
            URL url = new URL(AppConstants.URL_START_LOCATION);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            String str = resultObj.toString();
            Log.e("str", str);
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


    public JSONObject sendStopInspectionLocation() {
        String response = "";
        JSONObject response_json = null;
        try {
            JSONObject resultObj = new JSONObject();
            JSONObject payloadObj = new JSONObject();
            JSONObject startObj = new JSONObject();
            JSONObject startObj1 = new JSONObject();
            startObj1.put("Latitude2", latitude);
            startObj1.put("Longitude2", longitude);
            startObj.put("Stop", startObj1);
            payloadObj.put("payload", startObj);
            startObj.put("yearWiseCollegeId", instituteId);
            resultObj.put("result", payloadObj);
            Log.e("result", resultObj.toString());
            URL url = new URL(AppConstants.URL_STOP_LOCATION);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            String str = resultObj.toString();
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

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Category Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


    class ExecuteSendStartInspectionLocation extends AsyncTask<User, Integer, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (ringProgressDialog != null) {
                if (!ringProgressDialog.isShowing()) {
                    ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(context, android.R.style.Theme_Holo), null, "Sending location on  Server..", true);
                    ringProgressDialog.setCancelable(false);
                    ringProgressDialog.setCanceledOnTouchOutside(false);
                }
            } else {
                ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(context, android.R.style.Theme_Holo), null, "Sending location on  Server..", true);
                ringProgressDialog.setCancelable(false);
                ringProgressDialog.setCanceledOnTouchOutside(false);
            }

        }

        @Override
        protected JSONObject doInBackground(User... user) {
            JSONObject response = sendStartInspectionLocation();
            return response;
        }


        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);
            ringProgressDialog.cancel();
            try {
                int status = result.optInt("status");
                if (status == 0) {
                    ringProgressDialog.cancel();
                    (sharedPreferences.edit()).putBoolean("isStarted", true).apply();
                    (sharedPreferences.edit()).putString("instituteId", instituteId).apply();
                    (sharedPreferences.edit()).commit();
                    btnStartInspection.setEnabled(false);
                } else {
                    ringProgressDialog.cancel();
                    //Toast.makeText(context, "Unable to start inspection", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    class ExecuteSendComleteInspectionLocation extends AsyncTask<User, Integer, JSONObject> {
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            if (ringProgressDialog != null) {
                if (!ringProgressDialog.isShowing()) {
                    ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(context, android.R.style.Theme_Holo), null, "Sending location on  Server..", true);
                    ringProgressDialog.setCancelable(false);
                    ringProgressDialog.setCanceledOnTouchOutside(false);
                }
            } else {
                ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(context, android.R.style.Theme_Holo), null, "Sending location on  Server..", true);
                ringProgressDialog.setCancelable(false);
                ringProgressDialog.setCanceledOnTouchOutside(false);
            }


        }

        @Override
        protected JSONObject doInBackground(User... user) {
            JSONObject response = sendStopInspectionLocation();
            return response;
        }


        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);

            ringProgressDialog.cancel();
            try {
                int status = result.optInt("status");
                if (status == 0) {
                    ringProgressDialog.cancel();
                    ringProgressDialog.cancel();
                    (sharedPreferences.edit()).clear().apply();
                    (sharedPreferences.edit()).commit();
                    btnStopInspection.setEnabled(false);
                } else {
                    ringProgressDialog.cancel();
                    //Toast.makeText(context, "Unable to start inspection", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void showAppRestartAlert(final Activity activity) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setTitle("Location Unavailable");
        //alertDialog.setMessage("Location unavailable in the app. Restart your app for change in location settings to take effect.");

        // On pressing Settings button
        alertDialog.setPositiveButton("CLOSE APP", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                Process.killProcess(Process.myPid());
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }


    public void progressDialog(Context context, String title, String message, boolean cancelable, boolean isTitle) {
        if (pdialog == null) {
            pdialog = new ProgressDialog(context);
        }


        if (isTitle) {
            pdialog.setTitle(title);
        }

        pdialog.setMessage(message);

        if (!cancelable) {
            pdialog.setCancelable(false);
        }

        if (!pdialog.isShowing()) {
            pdialog.show();

        }

    }

    public void cancelProgressDialog() {
        pdialog.cancel();
    }


}