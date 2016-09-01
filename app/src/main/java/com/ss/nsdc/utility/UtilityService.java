package com.ss.nsdc.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.provider.Settings;
import com.ss.nsdc.dao.SubCategoryClass;
import com.ss.nsdc.dao.SubCategoryLab;
import com.ss.nsdc.entity.SubListEquipment;
import com.ss.nsdc.entity.SubCategorySupportStaff;
import com.ss.nsdc.entity.SubListOffice;

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

    public JSONObject getLabSycData(List<SubCategoryLab> labData) {
        JSONObject resultfinal = null;
        JSONArray result = new JSONArray();
        if (labData != null && labData.size() > 0) {
            try {
                for (int i = 0; i < labData.size(); i++) {

                    JSONObject labdetails = new JSONObject();
                    labdetails.put("YearWiseCollegeId", labData.get(i).getYearWiseCollegeId());
                    labdetails.put("ID", labData.get(i).getLabId());
                    labdetails.put("Lab_type", labData.get(i).getLab_type());
                    labdetails.put("LAB_Name", labData.get(i).getLAB_Name());
                    labdetails.put("labSameAsClass", labData.get(i).getLabSameAsClass());
                    labdetails.put("Carpet_Area", labData.get(i).getCarpet_Area());
                    labdetails.put("Seating_Capacity", labData.get(i).getSeating_Capacity());
                    labdetails.put("Ave_num_bat_run", labData.get(i).getAve_num_bat_run());
                    labdetails.put("Num_IT_Com_lab", labData.get(i).getNum_IT_Com_lab());
                    labdetails.put("JOB_role", labData.get(i).getJOB_role());
                    labdetails.put("Availability_Of_Internet_WIFI_connection", labData.get(i).getAvailability_Of_Internet_WIFI_connection());
                    labdetails.put("Availability_Of_AC", labData.get(i).getAvailability_Of_AC());
                    labdetails.put("Availability_Of_Power_BackUp", labData.get(i).getAvailability_Of_Power_BackUp());
                    labdetails.put("Area_under_CCTV_Coverage", labData.get(i).getArea_under_CCTV_Coverage());
                    labdetails.put("Remarks", labData.get(i).getRemarks());
                    labdetails.put("InslabSameAsClass", labData.get(i).getInslabSameAsClass());
                    labdetails.put("InsCarpet_Area", labData.get(i).getInsCarpet_Area());
                    labdetails.put("InsSeating_Capacity", labData.get(i).getInsSeating_Capacity());
                    labdetails.put("InsAve_num_bat_run", labData.get(i).getInsAve_num_bat_run());
                    labdetails.put("InsNum_IT_Com_lab", labData.get(i).getInsNum_IT_Com_lab());
                    labdetails.put("InsAvailability_Of_Internet_WIFI_connection", labData.get(i).getInsAvailability_Of_Internet_WIFI_connection());
                    labdetails.put("InsAvailability_Of_AC", labData.get(i).getInsAvailability_Of_AC());
                    labdetails.put("InsAvailability_Of_Power_BackUp", labData.get(i).getInsAvailability_Of_Power_BackUp());
                    labdetails.put("InsArea_under_CCTV_Coverage", labData.get(i).getInsArea_under_CCTV_Coverage());
                    labdetails.put("InsRemarks", labData.get(i).getInsRemarks());
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

    public JSONObject getClassDataSync(List<SubCategoryClass> categoryClass) {
        JSONObject resultfinal = null;
        JSONArray result = new JSONArray();
        if (categoryClass != null && categoryClass.size() > 0) {

            try {
                for (int i = 0; i < categoryClass.size(); i++) {
                    JSONObject classData = new JSONObject();

                    classData.put("YearWiseCollegeId",
                            categoryClass.get(i).getYearWiseCollegeId());
                    classData.put("Id", categoryClass.get(i).getClassId());
                    classData.put("Classroom_Name",
                            categoryClass.get(i).getClassroom_Name());
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

    public JSONObject getOfficeSycData(List<SubListOffice> officedata) {
        JSONObject resultfinal = null;
        JSONArray result = new JSONArray();
        if (officedata != null && officedata.size() > 0) {
            try {
                for (int i = 0; i < officedata.size(); i++) {
                    JSONObject offdetails = new JSONObject();
                    offdetails.put("id", officedata.get(i).getOfficeId());
                    offdetails.put("AreaType", officedata.get(i).getAreaType());
                    offdetails.put("CarpetArea", officedata.get(i).getInsCarpetArea());
                    offdetails.put("Internet", officedata.get(i).getInsInternet());
                    offdetails.put("AC", officedata.get(i).getInsAC());
                    offdetails.put("PowerBackup", officedata.get(i).getInsBackUp());
                    offdetails.put("CCTV", officedata.get(i).getInsCCTV());
                    offdetails.put("Remarks", officedata.get(i).getInsremarks());
                    offdetails.put("YearWiseCollegeId", officedata.get(i).getYearWiseCollegeId());
                    result.put(offdetails);
                }
                resultfinal = new JSONObject();
                resultfinal.put("OfficeDetails", result);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return resultfinal;
    }

    public JSONObject getEquipmentSycData(List<SubListEquipment> dataList) {
        JSONObject resultfinal = null;
        JSONArray result = new JSONArray();
        if (dataList != null && dataList.size() > 0) {
            try {
                for (int i = 0; i < dataList.size(); i++) {
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("Job_Id", dataList.get(i).getJob_Id());
                    jsonObj.put("Equipment_Id", dataList.get(i).getEquipment_Id());
                    jsonObj.put("Quantity", dataList.get(i).getInsTotalNo());
                    jsonObj.put("Remarks", dataList.get(i).getInsRemarks());
                    jsonObj.put("YearWiseCollegeId", dataList.get(i).getYearWiseCollegeId());
                    result.put(jsonObj);
                }
                resultfinal = new JSONObject();
                resultfinal.put("EquipmentDetails", result);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return resultfinal;
    }

	public JSONObject getOfficeSycData(SubListOffice officedata)
	{
		JSONObject offdetails=new JSONObject();
		try {
			offdetails.put("id", officedata.getOfficeId());
			offdetails.put("AreaType", officedata.getAreaType());
			offdetails.put("CarpetArea",officedata.getCarpetArea());
			offdetails.put("Internet",officedata.getInternet());
			offdetails.put("AC",officedata.getAC());
			offdetails.put("BackUp",officedata.getBackUp());
			offdetails.put("CCTV",officedata.getCCTV());
			offdetails.put("remarks",officedata.getRemarks());
			offdetails.put("InsCarpetArea",officedata.getInsCarpetArea());
			offdetails.put("InsInternet",officedata.getInsInternet());
			offdetails.put("InsAC",officedata.getInsAC());
			offdetails.put("InsBackUp","");
			offdetails.put("InsCCTV",officedata.getInsCCTV());
			offdetails.put("Insremarks",officedata.getInsremarks());
			
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return offdetails;
	}

	public JSONObject getStaffSycData(SubCategorySupportStaff staffData) {
		JSONObject staffDetails = new JSONObject();
		try {
			staffDetails.put("StaffId", staffData.getStaffId());
			staffDetails.put("StaffType", staffData.getStaffType());
			staffDetails.put("StaffName",staffData.getStaffName());
			staffDetails.put("Remarks",staffData.getRemarks());
			staffDetails.put("Work",staffData.getWork());
			staffDetails.put("InsWork",staffData.getInsWork());
			staffDetails.put("InsRemarks",staffData.getInsRemarks());

		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return staffDetails;
	}
	
	public static void exportDatabse(String databaseName) {
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                String currentDBPath = "\\data\\user\\0\\com.ss.nsdc\\databases\\NSDC.db";
                String backupDBPath = "backupname.db";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            }
        } catch (Exception e) {

        }
    }
}
