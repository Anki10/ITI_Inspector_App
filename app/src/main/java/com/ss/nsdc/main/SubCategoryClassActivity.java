package com.ss.nsdc.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;

import com.ss.nsdc.R;
import com.ss.nsdc.adapter.SubCategoryClassAdapter;
import com.ss.nsdc.dao.JobRolesModel;
import com.ss.nsdc.dao.NSDCDBController;
import com.ss.nsdc.dao.SubCategoryClass;
import com.ss.nsdc.dao.SubCategoryLab;
import com.ss.nsdc.entity.SubListEquipment;
import com.ss.nsdc.entity.SubListOffice;

public class SubCategoryClassActivity extends AppCompatActivity {
	private RecyclerView mRecyclerView;
	private RecyclerView.Adapter mAdapter;
	private RecyclerView.LayoutManager mLayoutManager;
	private String category,yearWiseCollegeId,applicationId,classId;
	ProgressDialog ringProgressDialog;
	Context context=this;
	List<SubCategoryClass> getListClass=new ArrayList<SubCategoryClass>();
	List<SubCategoryLab> getListLab=new ArrayList<SubCategoryLab>();
	List<SubListOffice> getOfficeList=new ArrayList<SubListOffice>();
	List<SubListEquipment> getEquipmentList = new ArrayList<SubListEquipment>();
	List<JobRolesModel> getJobRolesList = new ArrayList<JobRolesModel>();

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
		     
		 }else{
			  category = "none";
		 }
		 
		Toolbar toolbar = (Toolbar) findViewById(R.id.sub_cat_toolbar);
		setSupportActionBar(toolbar); //
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle("Sub Category Listing");
		NSDCDBController dbcontroller=new NSDCDBController(this);
		if(category.equalsIgnoreCase("class")){
		getListClass=dbcontroller.getClassListbyYearWiseCollageId(yearWiseCollegeId);
		}else if(category.equalsIgnoreCase("lab"))
		{
			getListLab=dbcontroller.getLabListbyYearWiseCollageId(yearWiseCollegeId);
		}else if(category.equalsIgnoreCase("office"))
		{
			getOfficeList=dbcontroller.getOfficeListbyYearWiseCollageId(yearWiseCollegeId);
		}else if(category.equalsIgnoreCase("eqipment"))
		{
			getEquipmentList=dbcontroller.getEquipmentListbyYearWiseCollageId(yearWiseCollegeId);
		}
		else if(category.equalsIgnoreCase("jobRoles"))
		{
			getJobRolesList=dbcontroller.getJobRolesList(yearWiseCollegeId);
		}
		dbcontroller.close();
		if(getListClass.size()>0){
			
		}
		else{
		
		}
		
		mRecyclerView = (RecyclerView) findViewById(R.id.sub_cat_recycler_view);

		// use this setting to improve performance if you know that changes
		// in content do not change the layout size of the RecyclerView
		mRecyclerView.setHasFixedSize(true);

		// use a linear layout manager
		mLayoutManager = new LinearLayoutManager(this);
		mRecyclerView.setLayoutManager(mLayoutManager);
		
		
		// specify an adapter (see also next example)
		if(category.equalsIgnoreCase("class")){
			mAdapter = new SubCategoryClassAdapter(getListClass, category,context);
		}else if(category.equalsIgnoreCase("lab"))
		{
			mAdapter = new SubCategoryClassAdapter(getListLab, category,context);
		}else if (category.equalsIgnoreCase("office")) {
			mAdapter = new SubCategoryClassAdapter(getOfficeList, category,
					context);
		}else if(category.equalsIgnoreCase("equipment")) {
			mAdapter = new SubCategoryClassAdapter(getEquipmentList, category,context);
		}
		else if(category.equalsIgnoreCase("jobRoles")) {
			mAdapter = new SubCategoryClassAdapter(getJobRolesList, category,context);
		}

		mRecyclerView.setAdapter(mAdapter);
		
	}
/*	 class UpdateData extends AsyncTask<String , Integer, JSONObject>{
		 	String yearWiseCollegeId;
		 	String type;

	    	@Override
	    	protected void onPreExecute() {
	    		// TODO Auto-generated method stub
	    		super.onPreExecute();
	    		ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(context, android.R.style.Theme_Holo), null,"Getting Class List", true);
	    		ringProgressDialog.setCancelable(false);
	    		ringProgressDialog.setCanceledOnTouchOutside(false);
	    	}
	    	@Override
	    	protected JSONObject doInBackground(String... urldetail) {
	    		yearWiseCollegeId=urldetail[2];
	    		type=urldetail[1];
	    	
	    		JSONObject response=updateSubCategoryList(urldetail);
	    		return response;
	    	
	    	}
	    	@Override
	    	protected void onPostExecute(JSONObject result) {
	    		
	    		
	    		if(type.equalsIgnoreCase("class")){
	    		
	    		NSDCDBController sqllite = new NSDCDBController(context);
	    		sqllite.addClassData(result,yearWiseCollegeId,applicationId);
	    		getListClass=sqllite.getClassListbyYearWiseCollageId(yearWiseCollegeId);
	    		sqllite.close();	
	    		
	    		}
	    		else if(type.equalsIgnoreCase("lab")){
	    			NSDCDBController sqllite = new NSDCDBController(context);
		    		sqllite.addLabData(result);
		    		sqllite.getLabList();
		    		sqllite.close();	
	    		}
	    		
	    		ringProgressDialog.cancel();
	    		mAdapter.notifyDataSetChanged();
	    		
	    		mRecyclerView.invalidate();
	    		
	    	}
	    	
	    	
	    }*/
	    public JSONObject updateSubCategoryList(String[] urldetail)
	    {
	    	String response="";
	    	JSONObject response_json=null;
	    	try{
	    		URL url = new URL(urldetail[0]);
	    		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    		conn.setReadTimeout(10000);
	    		conn.setConnectTimeout(15000);
	    		conn.setRequestMethod("POST");
	    		conn.setDoInput(true);
	    		conn.setDoOutput(true);
	    		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    		String str="YearWiseCollegeId="+urldetail[2]+"&Token=bnNkYzd0ZWNoaWVzYXBp";
	    		byte[] outputInBytes = str.getBytes("UTF-8");
	    		conn.getOutputStream().write(outputInBytes);
	    		conn.connect();
	    		int responsecode=conn.getResponseCode();
	    		 if (responsecode == HttpURLConnection.HTTP_OK) {
	                 String line;
	                 BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
	                 while ((line=br.readLine()) != null) {
	                     response+=line;
	                 }
	             }
	    		 response_json=new JSONObject(response);
	    	}catch(Exception e)
	    	{
	    		e.printStackTrace();
	    		return response_json;
	    	}
	    	return response_json;
	    }
}

