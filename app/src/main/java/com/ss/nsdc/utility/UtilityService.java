package com.ss.nsdc.utility;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ss.nsdc.dao.SubCategoryClass;
import com.ss.nsdc.dao.SubCategoryLab;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;

public class UtilityService {

	private static UtilityService mInstance;

	public static UtilityService getInstance() {
		if (mInstance == null)
			mInstance = new UtilityService();
		return mInstance;
	}

	public void showIntenetSettingsAlert(final Context context) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

		alertDialog.setTitle("Internet Disabled");

		alertDialog
		.setMessage("Internet is Disabled.Go to the settings to Enable Internet");

		// On pressing Settings button
		alertDialog.setPositiveButton("Settings",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(Settings.ACTION_SETTINGS);
				context.startActivity(intent);
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}

	public boolean getConnectivityStatus(final Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		if (null != activeNetwork) {
			if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
				return true;

			if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
				return true;
		}
		return false;
	}	
	public JSONObject getLabSycData(List<SubCategoryLab> labData)
	{
		JSONObject resultfinal=null;
		JSONArray result = new JSONArray();
		if (labData!=null && labData.size()>0) {
			try {
			for(int i=0;i<labData.size();i++)
			{
		
			JSONObject labdetails=new JSONObject();
			labdetails.put("YearWiseCollegeId", labData.get(i).getYearWiseCollegeId());
			labdetails.put("ID", labData.get(i).getLabId());
			labdetails.put("Lab_type", labData.get(i).getLab_type());
			labdetails.put("LAB_Name",labData.get(i).getLAB_Name());
			labdetails.put("labSameAsClass",labData.get(i).getLabSameAsClass());
			labdetails.put("Carpet_Area",labData.get(i).getCarpet_Area());
			labdetails.put("Seating_Capacity",labData.get(i).getSeating_Capacity());
			labdetails.put("Ave_num_bat_run",labData.get(i).getAve_num_bat_run());
			labdetails.put("Num_IT_Com_lab",labData.get(i).getNum_IT_Com_lab());
			labdetails.put("JOB_role",labData.get(i).getJOB_role());
			labdetails.put("Availability_Of_Internet_WIFI_connection",labData.get(i).getAvailability_Of_Internet_WIFI_connection());
			labdetails.put("Availability_Of_AC",labData.get(i).getAvailability_Of_AC());
			labdetails.put("Availability_Of_Power_BackUp",labData.get(i).getAvailability_Of_Power_BackUp());
			labdetails.put("Area_under_CCTV_Coverage",labData.get(i).getArea_under_CCTV_Coverage());
			labdetails.put("Remarks",labData.get(i).getRemarks());
			labdetails.put("InslabSameAsClass",labData.get(i).getInslabSameAsClass());
			labdetails.put("InsCarpet_Area",labData.get(i).getInsCarpet_Area());
			labdetails.put("InsSeating_Capacity",labData.get(i).getInsSeating_Capacity());
			labdetails.put("InsAve_num_bat_run",labData.get(i).getInsAve_num_bat_run());
			labdetails.put("InsNum_IT_Com_lab",labData.get(i).getInsNum_IT_Com_lab());
			labdetails.put("InsAvailability_Of_Internet_WIFI_connection",labData.get(i).getInsAvailability_Of_Internet_WIFI_connection());
			labdetails.put("InsAvailability_Of_AC",labData.get(i).getInsAvailability_Of_AC());
			labdetails.put("InsAvailability_Of_Power_BackUp",labData.get(i).getInsAvailability_Of_Power_BackUp());
			labdetails.put("InsArea_under_CCTV_Coverage",labData.get(i).getInsArea_under_CCTV_Coverage());
			labdetails.put("InsRemarks",labData.get(i).getInsRemarks());
			result.put(labdetails);
			}
			resultfinal = new JSONObject();
			resultfinal.put("LabDetails", result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		}
		
	
	
		return resultfinal;
	}
	public JSONObject getClassDataSync(List<SubCategoryClass> categoryClass)
	{	JSONObject resultfinal=null;
		JSONArray result = new JSONArray();
	if (categoryClass!=null && categoryClass.size()>0) {
		
		try {
		for(int i=0;i<categoryClass.size();i++){
			JSONObject classData = new JSONObject();
			
				classData.put("YearWiseCollegeId",
						categoryClass.get(i).getYearWiseCollegeId());
				classData.put("Id", categoryClass.get(i).getClassId());
				classData.put("Classroom_Name",
						categoryClass.get(i).getClassroom_Name());
				/*classData.put("Carpet_Area", categoryClass.get(i).getCarpet_Area());
				classData.put("Seating_Capacity", categoryClass.get(i).getSeating_Capacity());
				classData.put("Avg_Batches", categoryClass.get(i).getAvg_Batches());
				classData.put("Availability_Of_AC", categoryClass.get(i).getAvailability_Of_AC());
				classData.put("Availability_Of_Power_BackUp", categoryClass.get(i).getAvailability_Of_Power_BackUp());
				classData.put("Area_under_CCTV_Coverage", categoryClass.get(i).getArea_under_CCTV_Coverage());
				classData.put("Availability_of_Overhead_Projector", categoryClass.get(i).getAvailability_of_Overhead_Projector());
				classData.put("avail_Internet", categoryClass.get(i).getAvail_Internet());
				classData.put("Remarks", categoryClass.get(i).getRemarks());*/
				classData.put("InsCarpet_Area",
						categoryClass.get(i).getInsCarpet_Area());

				classData.put("InsSeating_Capacity",
						categoryClass.get(i).getInsSeating_Capacity());
				classData.put("InsAvg_Batches",
						categoryClass.get(i).getInsAvg_Batches());
				classData.put("InsAvailability_Of_AC",
						categoryClass.get(i).getInsAvailability_Of_AC());
				classData.put("InsAvailability_Of_Power_BackUp",
						categoryClass.get(i).getInsAvailability_Of_Power_BackUp());

				classData.put("InsArea_under_CCTV_Coverage",
						categoryClass.get(i).getInsArea_under_CCTV_Coverage());
				classData.put("InsAvailability_of_Overhead_Projector",
						categoryClass.get(i)
						.getInsAvailability_of_Overhead_Projector());
				classData.put("Insavail_Internet",
						categoryClass.get(i).getInsavail_Internet());
				classData.put("InsRemarks", categoryClass.get(i).getInsRemarks());

				
				result.put(classData);
			}
			resultfinal = new JSONObject();
			resultfinal.put("ClassDetails", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	return resultfinal;
	}
}