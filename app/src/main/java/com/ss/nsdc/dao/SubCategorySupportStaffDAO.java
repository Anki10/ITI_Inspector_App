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
import com.ss.nsdc.entity.SubCategorySupportStaff;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arjit on 29-08-2016.
 */
public class SubCategorySupportStaffDAO extends SQLiteOpenHelper {

    Context context;
    public static final String TAG = "SupportStaffDAO";
    public static final int database_version=1;
    public static final String database_name="NSDC.db";

    public SubCategorySupportStaffDAO(Context ctx) {
        super(ctx, database_name, null, database_version);
        this.context = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String subStaffTableQuery = "CREATE TABLE SupportStaff (Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ApplicationNo TEXT, " +
                "YearWiseCollegeId  TEXT NOT NULL, " +
                "StaffId TEXT, " +
                "StaffName TEXT, " +
                "StaffType TEXT, " +
                "Work TEXT, " +
                "Remarks TEXT, " +
                "InsWork TEXT, " +
                "InsRemarks TEXT, " +
                "proc_tracker INTEGER)";

        try {
            dropTable(db, "SupportStaff");
            db.execSQL(subStaffTableQuery);
        } catch (Exception e) {
            Log.e("SupportStaff", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private void dropTable(SQLiteDatabase db, String tableName) {
        String query;
        try{
            query = "DROP TABLE IF EXISTS " + tableName;
            db.execSQL(query);
        } catch(Exception e) {
            Log.e("dropTable", e.getMessage());
        }
    }

    public Proc_Track getSyncStatus() {
        String staffQuery = "Select proc_tracker,count(*) from SupportStaff group by proc_tracker";
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
            Log.e("getSyncStatus-StaffDAO", e.getMessage());
            return null;
        }
        return obj;
    }

    public List<SubCategorySupportStaff> getStaffListByYearWiseCollegeId(String instituteId) {
        SQLiteDatabase db = null;
        String selectQueryQues = "SELECT YearWiseCollegeId,"
                + " StaffId,"
                + " StaffType,"
                + " StaffName,"
                + " proc_tracker, "
                + " Remarks from SupportStaff where YearWiseCollegeId = " + instituteId;

        List<SubCategorySupportStaff> objList = new ArrayList<SubCategorySupportStaff>();
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    SubCategorySupportStaff obj = new SubCategorySupportStaff();
                    obj.setYearWiseCollegeId(cursor.getString(0));
                    obj.setStaffId(cursor.getString(1));
                    obj.setStaffType(cursor.getString(2));
                    obj.setStaffName(cursor.getString(3));
                    obj.setProc_tracker(cursor.getInt(4));
                    obj.setRemarks(cursor.getString(5));

                    objList.add(obj);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("getStaffList", e.getMessage());
            return null;
        }
        return objList;
    }

    public SubCategorySupportStaff getStaffDataByStaffId(String instituteId, String staffId) {
        SubCategorySupportStaff staffDetails = null;
        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId, "
                + " StaffId,"
                + " StaffType,"
                + " StaffName,"
                + " proc_tracker, "
                + "	Remarks from SupportStaff where YearWiseCollegeId = " + instituteId + " and StaffId = " + staffId;

        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    staffDetails = new SubCategorySupportStaff();
                    staffDetails.setYearWiseCollegeId(cursor.getString(0));
                    staffDetails.setStaffId(cursor.getString(1));
                    staffDetails.setStaffType(cursor.getString(2));
                    staffDetails.setStaffName(cursor.getString(3));
                    staffDetails.setProc_tracker(cursor.getInt(4));
                    staffDetails.setRemarks(cursor.getString(5));
                } while (cursor.moveToNext());
            }
        } catch(Exception e) {
            Log.e("getStaffDetailsById", e.getMessage());
            return null;
        }

        return staffDetails;
    }

    public boolean addStaffData(JSONObject jsonObj, String yearWiseCollegeId, String applicationId) {
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
                                values.put("StaffId", rowValue.getString("id"));
                                values.put("ApplicationNo", applicationId);
                                values.put("YearWiseCollegeId", yearWiseCollegeId);
                                values.put("StaffType", rowValue.getString("Category"));
                                values.put("StaffName", rowValue.getString("Name"));
                                values.put("Work", rowValue.getString("Work"));
                                values.put("Remarks",
                                        (rowValue.getString("Remarks") != "null") ? rowValue
                                                .getString("Remarks") : null);
                                values.put("InsWork",
                                        (rowValue.getString("InsWork") != "null") ? rowValue
                                                .getString("InsWork") : null);
                                values.put("InsRemarks",
                                        (rowValue.getString("InsRemarks") != "null") ? rowValue
                                                .getString("InsRemarks") : null);
                                values.put("proc_tracker", 1);

                                database.insert("SupportStaff", null, values);
                            }
                        }

                    } else if (Integer.parseInt(jsonObj.getString(AppConstants.KEY_API_RESPONSE_CODE)) == 0) {
                        Toast.makeText(context, "No Data Found..", Toast.LENGTH_LONG).show();
                    }
                }

            } catch (NumberFormatException e) {
                Log.e("NFE_SAVE_STAFF",e.toString());
                e.printStackTrace();
                return false;

            } catch (JSONException e) {
                Log.e("JSON_EX_SAVE_STAFF",e.toString());
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public boolean saveStaffData(SubCategorySupportStaff subCategorySupportStaff, String mode) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values= new ContentValues();
        try{

            if(mode.equalsIgnoreCase("draft")) {
                values.put("StaffType", subCategorySupportStaff.getStaffType());
                values.put("StaffName", subCategorySupportStaff.getStaffName());
                values.put("Remarks", subCategorySupportStaff.getRemarks());
                database.update("SupportStaff", values, " YearWiseCollegeId= "+ subCategorySupportStaff.getYearWiseCollegeId() + " and StaffId = " + subCategorySupportStaff.getStaffId(), null);
            } else {
                values.put("StaffType", subCategorySupportStaff.getStaffType());
                values.put("StaffName", subCategorySupportStaff.getStaffName());
                values.put("Remarks", subCategorySupportStaff.getRemarks());
                database.update("SupportStaff", values, " YearWiseCollegeId= "+ subCategorySupportStaff.getYearWiseCollegeId() + " and StaffId = " + subCategorySupportStaff.getStaffId(), null);
            }
        } catch(Exception e) {
            Log.e("saveSupportStaffData", e.getMessage());
        }
        finally{
            database.close();
        }

        return true;
    }
}
