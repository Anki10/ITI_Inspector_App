package com.ss.nsdc.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
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
import com.ss.nsdc.adapter.SubCategoryToolsAboveAdapter;
import com.ss.nsdc.constant.AppConstants;
import com.ss.nsdc.dao.ITIDBController;
import com.ss.nsdc.entity.Accommodation;
import com.ss.nsdc.entity.ToolsAbove;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radhika on 5/25/2017.
 */

public class SubCategoryToolsAbove extends BaseActivity {

    private RecyclerView mRecyclerView;
    private SubCategoryToolsAboveAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public String category, yearWiseCollegeId, applicationId, tradeId;
            int count;
    ProgressDialog ringProgressDialog;
    Context context = this;
    List<ToolsAbove> getToolsAbove = new ArrayList<>();
    Accommodation toolsAboveInfo;

    List getToolsAbove100  = new ArrayList();

    Button btn_signup;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_above);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            category = bundle.getString("Category");
            yearWiseCollegeId = bundle.getString("YearWiseCollegeId");
            tradeId = bundle.getString("tradeId");
            count = bundle.getInt("value");

        } else {
            category = "none";
        }
      //  new UpdateToolsAbove().execute();

//        ITIDBController controller = new ITIDBController(context);
//        controller.deleteAllData(context);
//        controller.close();

        btn_signup=(Button)findViewById(R.id.btn_signup);
        //if (count == 1){
          //  new UpdateToolsAbove().execute();
        //}


        Toolbar toolbar = (Toolbar) findViewById(R.id.sub_cat_toolbar);
        setSupportActionBar(toolbar); //
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sub Category Listing");

        actionBar.setTitle("Tools Above 10000 ");

        mRecyclerView = (RecyclerView) findViewById(R.id.sub_cat_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);
        ITIDBController controller = new ITIDBController(context);
        getToolsAbove = controller.getToolsAbovebyTradeId(yearWiseCollegeId,tradeId);
        Log.e("list1",getToolsAbove.toString());
        controller.close();

        mAdapter = new SubCategoryToolsAboveAdapter(getToolsAbove,category,context);

        mRecyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();




        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new SubmitData().execute("");

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

    private class SubmitData extends AsyncTask<String, String, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(context, android.R.style.Theme_Holo), null, " Data Synchronizing ...", true);
            ringProgressDialog.setCancelable(false);
            ringProgressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            String response = "";
            JSONObject responseJSON = null;
            //   String data = null;

            try {
                JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < SubCategoryToolsAboveAdapter.toolsAboveList.size(); i++) {
                    JSONObject jsonObject = new JSONObject();
                    //Log.e("str",SubCategoryToolsAboveAdapter.toolsAboveList.get(i).getRequired());
                    jsonObject.put("refId",SubCategoryToolsAboveAdapter.toolsAboveList.get(i).getRefId());  //refid
                    jsonObject.put("tradeId", tradeId);
                    jsonObject.put("equipmentid",SubCategoryToolsAboveAdapter.toolsAboveList.get(i).getEquipmentId());
                    String groutingAvailable = String.valueOf(SubCategoryToolsAboveAdapter.toolsAboveList.get(i).getGroutingAvailable());
                    if(groutingAvailable.equalsIgnoreCase("yes")) {
                        jsonObject.put("isGroutingandEngravingRequired", "yes");
                    }else {
                        jsonObject.put("isGroutingandEngravingRequired", "no");
                    }
                    jsonObject.put("isGroutingandEngravingRequired_Remarks", "");
                    jsonObject.put("isGroutingandEngravingRequired_Nc", 0);
                    String engravingAvailable = String.valueOf(SubCategoryToolsAboveAdapter.toolsAboveList.get(i).getEngravingAvailable());
                    if(engravingAvailable.equalsIgnoreCase("yes")) {
                        jsonObject.put("isGroutingandengravingAvailable","yes");
                    }else {
                        jsonObject.put("isGroutingandengravingAvailable","no");
                    }

                    String engravingRequired = String.valueOf(SubCategoryToolsAboveAdapter.toolsAboveList.get(i).getEngravingRequired());
                    //String engravingAvailable = String.valueOf(SubCategoryToolsAboveAdapter.toolsAboveList.get(i).getEngravingAvailable());
                    if(engravingRequired.equalsIgnoreCase("yes")) {
                        jsonObject.put("isEngravingRequired","yes");
                    }else {
                        jsonObject.put("isEngravingRequired","no");
                    }

                    String groutingRequired = String.valueOf(SubCategoryToolsAboveAdapter.toolsAboveList.get(i).getGroutingRequired());
                    if(groutingRequired.equalsIgnoreCase("yes")) {
                        jsonObject.put("isGroutingRequired","yes");
                    }else {
                        jsonObject.put("isGroutingRequired","no");
                    }

                    //qty
                    jsonObject.put("isGroutingandengravingAvailable_Remarks", "");
                    jsonObject.put("isGroutingandengravingAvailable_Nc", "");
                    jsonObject.put("billno","");
                    jsonObject.put("billnoRemarks","");
                    jsonObject.put("billnoNc","");
                    jsonObject.put("billDate","");
                    jsonObject.put("vatno","");
                    jsonObject.put("vatnoRemarks","");
                    jsonObject.put("vatnoNc","");
                    jsonArray.put(jsonObject);
                }

                JSONObject GeneralDetailsJsonObj = new JSONObject();
                GeneralDetailsJsonObj.put("toolsAbove10000", jsonArray);
                GeneralDetailsJsonObj.put("yearwisecollegeid", yearWiseCollegeId);

                JSONObject payloadJsonObj = new JSONObject();
                payloadJsonObj.put("payload", GeneralDetailsJsonObj);

                JSONObject resultJsonObj = new JSONObject();
                resultJsonObj.put("result", payloadJsonObj);
                Log.e("json",resultJsonObj.toString());


                URL url = new URL(AppConstants.URL_TOOLS_UPLOAD_UPLOAD);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //  conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json");
                //   conn.setFixedLengthStreamingMode(params[0].getBytes().length);
                String str = resultJsonObj.toString();
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
                }
                responseJSON = new JSONObject(response);

            }catch (Exception e) {
                Log.e("ERROR_SAVE_FACILITIES", e.toString());
            }

            return responseJSON;
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
                    toolsAboveInfo = controller.getToolsAboveTenkInfo(yearWiseCollegeId,tradeId);
                    boolean updation_status = controller.saveToolsAboveInfo(toolsAboveInfo, "complete");
                    controller.close();
                    if (updation_status) {
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
        intent.putExtra("YearWiseCollegeId", toolsAboveInfo.getYearWiseCollegeid());
        intent.putExtra("Category","toolsAbove10K");
        //intent.putExtra("refId",classroomInfo.getRefId());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    private class UpdateToolsAbove extends AsyncTask<String,Integer,JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(
                    context, android.R.style.Theme_Holo), null, "Loading data....", true);
            ringProgressDialog.setCancelable(false);
            ringProgressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            String response = "";
            JSONObject response_json = null;
            try {
                URL url = new URL(AppConstants.URL_TOOLSABOVE_INFO);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                String str = "YearWiseCollegeId=" + yearWiseCollegeId  + "&tradeId=" + tradeId;
                Log.e("string",str);
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

        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);
            ringProgressDialog.cancel();
            try{

                int status = result.optInt("status");
                if(status == 0){
//                    ITIDBController sqlite = new ITIDBController(context);
//                    sqlite.addToolabove10000InfoData(result,yearWiseCollegeId,tradeId);
//                    sqlite.close();

                    getToolsAbove.clear();

                    JSONObject payload = result.optJSONObject("payload");
                    JSONArray toolsAbove10000Arr = payload.optJSONArray("toolsAbove10000");
                    for (int i = 0 ;i< toolsAbove10000Arr.length();i++){
                        JSONObject toolsAbove10000Obj = toolsAbove10000Arr.optJSONObject(i);
                        ToolsAbove above = new ToolsAbove();
                        above.setEquipmentName(toolsAbove10000Obj.optString("equipmentName"));
                        above.setEquipmentId(toolsAbove10000Obj.getString("equipmentId"));
                        above.setRefId(toolsAbove10000Obj.getString("refId"));
                        getToolsAbove.add(above);

                        mAdapter = new SubCategoryToolsAboveAdapter(getToolsAbove,category,context);

                        mRecyclerView.setAdapter(mAdapter);



                    }






                }else {
                    Toast.makeText(context,"No Data For the tools above",Toast.LENGTH_SHORT);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
