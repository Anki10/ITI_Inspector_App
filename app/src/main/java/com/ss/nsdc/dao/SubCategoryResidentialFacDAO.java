package com.ss.nsdc.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.ss.nsdc.constant.AppConstants;
import com.ss.nsdc.entity.Proc_Track;
import com.ss.nsdc.entity.SubCategoryResidentialFac;
import com.ss.nsdc.entity.SubCategorySupportStaff;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arjit on 31-08-2016.
 */
public class SubCategoryResidentialFacDAO extends SQLiteOpenHelper {

    Context context;
    public static final String TAG = "ResidentialFacilityDAO";
    public static final int database_version=1;
    public static final String database_name="NSDC.db";

    public SubCategoryResidentialFacDAO(Context ctx) {
        super(ctx, database_name, null, database_version);
        this.context = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public Proc_Track getSyncStatus() {
        String staffQuery = "Select proc_tracker,count(*) from ResidentialFacility group by proc_tracker";
        Proc_Track obj = new Proc_Track();

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(staffQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getString(0).equalsIgnoreCase("1")) {
                        obj.setProc_track1Count(cursor.getInt(1));
                    } else if (cursor.getString(0).equalsIgnoreCase("2")) {
                        obj.setProc_track2Count(cursor.getInt(1));
                    } else if (cursor.getString(0).equalsIgnoreCase("3")) {
                        obj.setProc_track3Count(cursor.getInt(1));
                    }
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            Log.e("getSyncStatus-ResFacDAO", e.getMessage());
            return null;
        }
        return obj;
    }

    public List<SubCategoryResidentialFac> getResFacListByYearWiseCollegeId(String instituteId) {
        SQLiteDatabase db = null;
        String selectQueryQues = "SELECT YearWiseCollegeId,"
                + " ResFacId,"
                + " Type,"
                + " TotalArea, "
                + " NoOfRooms, "
                + " ResCapacity, "
                + " IsPowerBackup, "
                + " IsCCTVCamera, "
                + " proc_tracker, "
                + " Remarks from ResidentialFacility where YearWiseCollegeId = " + instituteId;

        List<SubCategoryResidentialFac> objList = new ArrayList<SubCategoryResidentialFac>();
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    SubCategoryResidentialFac obj = new SubCategoryResidentialFac();
                    obj.setYearWiseCollegeId(cursor.getString(0));
                    obj.setResFacId(cursor.getString(1));
                    obj.setResFacType(cursor.getString(2));
                    obj.setTotalArea(cursor.getString(3));
                    obj.setNoOfRooms(cursor.getInt(4));
                    obj.setResCapacity(cursor.getInt(5));
                    obj.setPowerBackupAvailable(cursor.getString(6));
                    obj.setCctvCameraFacility(cursor.getString(7));
                    obj.setProc_tracker(cursor.getInt(8));
                    obj.setRemarks(cursor.getString(9));

                    objList.add(obj);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("getResFacList", e.getMessage());
            return null;
        }
        return objList;
    }

    public SubCategoryResidentialFac getResFacDataByStaffId(String instituteId, String staffId) {
        SubCategoryResidentialFac facilityDetails = null;
        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId, "
                + " ResFacId,"
                + " Type,"
                + " TotalArea, "
                + " NoOfRooms, "
                + " ResCapacity, "
                + " IsPowerBackup, "
                + " IsCCTVCamera, "
                + " proc_tracker, "
                + " Remarks from ResidentialFacility where YearWiseCollegeId = " + instituteId + " and StaffId = " + staffId;

        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    facilityDetails = new SubCategoryResidentialFac();
                    facilityDetails.setYearWiseCollegeId(cursor.getString(0));
                    facilityDetails.setResFacId(cursor.getString(1));
                    facilityDetails.setResFacType(cursor.getString(2));
                    facilityDetails.setTotalArea(cursor.getString(3));
                    facilityDetails.setNoOfRooms(cursor.getInt(4));
                    facilityDetails.setResCapacity(cursor.getInt(5));
                    facilityDetails.setPowerBackupAvailable(cursor.getString(6));
                    facilityDetails.setCctvCameraFacility(cursor.getString(7));
                    facilityDetails.setProc_tracker(cursor.getInt(8));
                    facilityDetails.setRemarks(cursor.getString(9));

                } while (cursor.moveToNext());
            }
        } catch(Exception e) {
            Log.e("getResFacDetailsById", e.getMessage());
            return null;
        }

        return facilityDetails;
    }

    public boolean addResidentialFacilityData(JSONObject jsonObj, String yearWiseCollegeId, String applicationId) {
        SQLiteDatabase database = getWritableDatabase();

        if (jsonObj != null) {
            try {
                if (jsonObj.has(AppConstants.KEY_API_RESPONSE_CODE)) {
                    if (Integer.parseInt(jsonObj.getString(AppConstants.KEY_API_RESPONSE_CODE)) == 2
                            && jsonObj.has(AppConstants.KEY_API_ROW_COUNT)) {

                        for (int i = 0; i < jsonObj.getInt(AppConstants.KEY_API_ROW_COUNT); i++) {
                            if (jsonObj.has(String.valueOf(i))) {

                                ContentValues values = new ContentValues();
                                JSONObject rowValue = new JSONObject(jsonObj.getString(String.valueOf(i)));
                                values.put("ResFacId", rowValue.getString("id"));
                                values.put("ApplicationNo", applicationId);
                                values.put("YearWiseCollegeId", yearWiseCollegeId);
                                values.put("FacilityType", rowValue.getString("Category"));
                                values.put("TotalArea", rowValue.getString("Name"));
                                values.put("NoOfRooms", rowValue.getString("Name"));
                                values.put("ResCapacity", rowValue.getString("Name"));
                                values.put("isPowerBackup", rowValue.getString("Work"));
                                values.put("isCCTVCamera", rowValue.getString("Work"));
                                values.put("Remarks",
                                        (rowValue.getString("Remarks") != "null") ? rowValue
                                                .getString("Remarks") : null);

                                values.put("proc_tracker", 1);

                                database.insert("ResidentialFacility", null, values);
                            }
                        }

                    } else if (Integer.parseInt(jsonObj.getString(AppConstants.KEY_API_RESPONSE_CODE)) == 0) {
                        Toast.makeText(context, "No Data Found..", Toast.LENGTH_LONG).show();
                    }
                }

            } catch (NumberFormatException e) {
                Log.e("NFE_SAVE_RES_FAC",e.toString());
                e.printStackTrace();
                return false;

            } catch (JSONException e) {
                Log.e("JSON_EX_SAVE_RES_FAC",e.toString());
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public boolean saveResidentialFacData(SubCategoryResidentialFac subCategoryResidentialFac, String mode) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values= new ContentValues();
        try{

            /*if(mode.equalsIgnoreCase("draft")) {
                values.put("ResFacType", subCategoryResidentialFac.getStaffType());
                values.put("StaffName", subCategoryResidentialFac.getStaffName());
                values.put("Remarks", subCategoryResidentialFac.getRemarks());
                database.update("SupportStaff", values, " YearWiseCollegeId= "+ subCategorySupportStaff.getYearWiseCollegeId() + " and StaffId = " + subCategorySupportStaff.getStaffId(), null);
            } else {
                values.put("StaffType", subCategorySupportStaff.getStaffType());
                values.put("StaffName", subCategorySupportStaff.getStaffName());
                values.put("Remarks", subCategorySupportStaff.getRemarks());
                database.update("SupportStaff", values, " YearWiseCollegeId= "+ subCategorySupportStaff.getYearWiseCollegeId() + " and StaffId = " + subCategorySupportStaff.getStaffId(), null);
            }*/
        } catch(Exception e) {
            Log.e("saveResidentialFacData", e.getMessage());
        }
        finally{
            database.close();
        }

        return true;
    }
}
