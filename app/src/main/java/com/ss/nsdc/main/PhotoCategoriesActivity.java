package com.ss.nsdc.main;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.ss.nsdc.R;
import com.ss.nsdc.adapter.PhotoCategoriesAdapter;
import com.ss.nsdc.constant.AppConstants;
import com.ss.nsdc.dao.ITIDBController;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Radhika on 5/18/2017.
 */

public class PhotoCategoriesActivity extends AppCompatActivity {
    ListView listViewCategories;
    Activity currentActivity;
    String yearWiseCollegeId;
    String applicationId;
    String category;
    Boolean status;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_categories);

        Toolbar toolbar = (Toolbar) findViewById(R.id.sub_cat_toolbar);
        setSupportActionBar(toolbar); //
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sub Category Listing");

        actionBar.setTitle("Upload Document");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            category = bundle.getString("Category");
            yearWiseCollegeId = bundle.getString("YearWiseCollegeId");
            status = bundle.getBoolean("status");
            Log.e("status", String.valueOf(status));
        } else {
            category = "none";
        }



        currentActivity = PhotoCategoriesActivity.this;
        Resources res = getResources();

        String[] categories = res.getStringArray(R.array.photo_categories);

        listViewCategories = (ListView) findViewById(R.id.lstViewCategories);
        listViewCategories.setAdapter(new PhotoCategoriesAdapter(currentActivity, categories, yearWiseCollegeId,status));


        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO},
                1);


//           ITIDBController controller = new ITIDBController(currentActivity);
//           controller.deleteToolsAboveImage(currentActivity);
//           controller.close();
//            new DownloadToolsAboveUploadImageData().execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sub_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.category_list:
                Intent intent = new Intent(currentActivity, CategoryActivity.class);
                intent.putExtra("YearWiseCollegeId", yearWiseCollegeId);
                intent.putExtra("applicationNo", applicationId);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                currentActivity.startActivity(intent);
                return true;

            case R.id.institute_list:
                Intent intent1 = new Intent(currentActivity, InstituteActivity.class);
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

//    class DownloadToolsAboveUploadImageData extends AsyncTask<String, Integer, JSONObject> {
//
//        @Override
//        protected JSONObject doInBackground(String... params) {
//            String response = "";
//            JSONObject responseJSON = null;
//
//            try {
//
//                URL url = new URL(AppConstants.URL_TOOLABOVE_UPLOAD);
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setReadTimeout(10000);
//                conn.setConnectTimeout(15000);
//                conn.setRequestMethod("POST");
//                conn.setDoInput(true);
//                conn.setDoOutput(true);
//                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//
//                String str = "YearWiseCollegeId=" + yearWiseCollegeId;
//                byte[] outputInBytes = str.getBytes("UTF-8");
//                conn.getOutputStream().write(outputInBytes);
//                conn.connect();
//
//                int responseCode = conn.getResponseCode();
//                if (responseCode == HttpURLConnection.HTTP_OK) {
//                    String line;
//                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                    while ((line = br.readLine()) != null) {
//                        response += line;
//                    }
//                }
//                responseJSON = new JSONObject(response);
//
//            } catch (Exception ex) {
//                Log.e("DOWNLOAD_CD_IMAGE", ex.toString());
//            }
//
//            return responseJSON;
//        }
//
//        @Override
//        protected void onPostExecute(JSONObject result) {
//            super.onPostExecute(result);
//            if (result != null) {
//                ITIDBController controller = new ITIDBController(currentActivity);
//                controller.addToolsAboveUploadImageData(result, yearWiseCollegeId);
//                controller.close();
//                Log.e("success6", "success6");
//            }
//        }
//    }
}
