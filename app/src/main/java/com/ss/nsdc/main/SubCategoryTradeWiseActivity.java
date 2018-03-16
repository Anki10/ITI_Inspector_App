package com.ss.nsdc.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ss.nsdc.R;
import com.ss.nsdc.adapter.SubCategoryTradeWiseAdapter;
import com.ss.nsdc.constant.AppConstants;
import com.ss.nsdc.dao.ITIDBController;
import com.ss.nsdc.entity.Accommodation;
import com.ss.nsdc.entity.TradeWiseTool;
import com.ss.nsdc.utility.UtilityService;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radhika on 5/12/2017.
 */

public class SubCategoryTradeWiseActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public String category, yearWiseCollegeId, applicationId, tradeId, refId;
    ProgressDialog ringProgressDialog;
    Context context = this;
    List<TradeWiseTool> getTradeWiseTools = new ArrayList<>();
    Accommodation tradeWiseToolInfo;
    TradeWiseTool tradesInfo;

    UtilityService utility = UtilityService.getInstance();



    Button btn_signup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_tradewise);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            category = bundle.getString("Category");
            yearWiseCollegeId = bundle.getString("YearWiseCollegeId");
            tradeId = bundle.getString("tradeId");
            refId = bundle.getString("refId");


        } else {
            category = "none";
        }
       // new UpdateTradeWiseTools().execute();

        btn_signup=(Button)findViewById(R.id.btn_signup);

        Toolbar toolbar = (Toolbar) findViewById(R.id.sub_cat_toolbar);
        setSupportActionBar(toolbar); //
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sub Category Listing");

        ITIDBController controller = new ITIDBController(context);
         getTradeWiseTools = controller.getTradesWiseInfoList(yearWiseCollegeId,tradeId);
        controller.close();

        ITIDBController controller1 = new ITIDBController(context);
        tradesInfo =  controller.getTradewiseToolsInfo(yearWiseCollegeId,tradeId);
        controller1.close();


        mRecyclerView = (RecyclerView) findViewById(R.id.sub_cat_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

            actionBar.setTitle("Trade Wise Tools ");
            mAdapter = new SubCategoryTradeWiseAdapter(getTradeWiseTools,category,context);

        mRecyclerView.setAdapter(mAdapter);


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(SubCategoryTradeWiseActivity.this)
                        .setTitle("Confirmation")
                        .setMessage(R.string.alert_submit_data)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(R.string.submit_later, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                if(utility.getConnectivityStatus(context)){
                                    JSONObject dataToSyncClass = utility.getTradewisetoolsSyncData(getTradeWiseTools);
                                    new ExecuteSyncOperation().execute(new String[]{AppConstants.URL_TRADEWISETOOLS_UPLOAD_UPLOAD,
                                            dataToSyncClass.toString().replace("&", "and")});
                                }

                            }
                        })
                        .setNegativeButton(R.string.no, null).show();


            }
        });

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





    class ExecuteSyncOperation extends AsyncTask<String, Integer, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(context, android.R.style.Theme_Holo), null, " Data Synchronizing ...", true);
            ringProgressDialog.setCancelable(false);
            ringProgressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected JSONObject doInBackground(String... data) {
            Log.e("job submit json", data[1]);
            String response = "";
            JSONObject response_json = null;
            try {
                URL url = new URL(data[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json");

                String str = data[1];
                byte[] outputInBytes = str.getBytes("UTF-8");
                conn.getOutputStream().write(outputInBytes);
                conn.connect();

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }
                } else if (responseCode == HttpURLConnection.HTTP_CLIENT_TIMEOUT) {
                    response_json = new JSONObject();
                    response_json.put("error", "Connection to server lost..");
                }

                response_json = new JSONObject(response);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response_json;
        }


        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);
            ringProgressDialog.cancel();

            try{
                int status = result.optInt("status");
                if (status == 0) {
                    Toast.makeText(context, "Data Sync Successful", Toast.LENGTH_LONG).show();
                    ITIDBController controller = new ITIDBController(context);
                    //getTradeWiseTools = controller.getTradesWiseInfoList(yearWiseCollegeId);
                    boolean updation_status = controller.saveTradeWiseInfo(tradesInfo, "complete");
                    tradeWiseToolInfo = controller.getTradeInfo(yearWiseCollegeId,tradeId);
                    boolean updation_status1 = controller.saveTradesWiseInfo(tradeWiseToolInfo, "complete");
                    controller.close();
                    if (updation_status && updation_status1) {
                        btn_signup.setEnabled(false);
                        //draftButton.setEnabled(false);
                        navigate();
                    } else {
                        Toast.makeText(context, "Error in updation of data..", Toast.LENGTH_LONG).show();
                    }
                    Log.e("Result", result.toString());

                } else {
                    ringProgressDialog.cancel();
                    Toast.makeText(context, "Data Syc failed...", Toast.LENGTH_LONG).show();
                }

            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }

    private void navigate() {

        Intent intent = new Intent(context, SubCategoryActivity.class);
        intent.putExtra("YearWiseCollegeId", tradeWiseToolInfo.getYearWiseCollegeid());
        intent.putExtra("Category","tradesWise");
        //intent.putExtra("refId",classroomInfo.getRefId());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }


}
