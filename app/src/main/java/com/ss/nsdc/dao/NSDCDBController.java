package com.ss.nsdc.dao;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import com.ss.nsdc.entity.Proc_Track;
import com.ss.nsdc.entity.SubListEquipment;
import com.ss.nsdc.entity.SubListOffice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class NSDCDBController extends SQLiteOpenHelper {
    Context application_context;
    public static final String TAG = "NSDCDBController";
    public static final int database_version = 1;
    public static final String database_name = "NSDC.db";
    public static final String databasepath = Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + "/NSDC_BACKUP.db";
    public static final String databasepath_sd = Environment
            .getExternalStorageDirectory() + File.separator + database_name;
    SimpleDateFormat sdf_ss = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
    SimpleDateFormat sdf_db = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS",
            Locale.ENGLISH);
    String currDate = sdf_ss.format(new Date());
    String currDateValue = sdf_db.format(new Date());

    public NSDCDBController(Context context) {
        super(context, database_name, null, database_version);
        this.application_context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // PROC TRACKER >> 1- new Data , 2- draft(not synced), 3-completed
        String userTableQuery = "";
        String instituteTableQuery = "";
        String categoryTableQuery = "";
        String subClassroomTableQuery = "";
        String subLabTableQuery = "", subOfficeTableQuery = "", subEquipmentTableQuery = "";
        String subJobRolesTableQuery = "";
        /*
         * String questionTableQuery; String optionTableQuery; String
		 * surveyAnswerTable;
		 */
        userTableQuery = " CREATE TABLE user ( loginId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Userid INTEGER NOT NULL, Username TEXT NOT NULL,Password TEXT NOT NULL, status INTEGER, date TEXT NOT NULL )";
        instituteTableQuery = " CREATE TABLE allocation ( Id INTEGER PRIMARY KEY AUTOINCREMENT, YearWiseCollegeId TEXT NOT NULL, ApplicationNo TEXT NOT NULL, Name_ParentOrg TEXT, Name_Training_Center TEXT, DistrictName TEXT,StateName TEXT,Stage TEXT, CreationDate TEXT,SubmissionDate TEXT,inspectionFromDate TEXT,inspectionToDate TEXT,email_spoc TEXT,contact_spoc TEXT,proc_tracker INTEGER )";
        // categoryTableQuery=" CREATE TABLE surveyCategory ( cId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, YearWiseCollegeId TEXT NOT NULL, surveyCategoryId INTEGER, surveyCategoryName TEXT, surveySubCategory INTEGER NOT NULL, surveyCategoryStatus TEXT, surveyCategoryOrder INTEGER NOT NULL )";
        subClassroomTableQuery = "CREATE TABLE Classroom ( Id INTEGER PRIMARY KEY AUTOINCREMENT,YearWiseCollegeId TEXT NOT NULL,ClassId TEXT,ApplicationNo TEXT,Category TEXT,Classroom_Name TEXT,Carpet_Area TEXT,Seating_Capacity TEXT,Avg_Batches TEXT,Availability_Of_AC TEXT,Availability_Of_Power_BackUp TEXT,Area_under_CCTV_Coverage TEXT,Availability_of_Overhead_Projector TEXT,avail_Internet TEXT,Remarks TEXT,InsCarpet_Area TEXT,InsSeating_Capacity TEXT,InsAvg_Batches TEXT,InsAvailability_Of_AC TEXT,InsAvailability_Of_Power_BackUp TEXT,InsArea_under_CCTV_Coverage TEXT,InsAvailability_of_Overhead_Projector TEXT,Insavail_Internet TEXT,InsRemarks TEXT,proc_tracker INTEGER  )";
        subLabTableQuery = "CREATE TABLE Lab ( lId INTEGER PRIMARY KEY AUTOINCREMENT, YearWiseCollegeId  TEXT ,ApplicationNo TEXT, LabId TEXT, Lab_type TEXT,LAB_Name TEXT,labSameAsClass TEXT,Carpet_Area TEXT,Seating_Capacity TEXT,Ave_num_bat_run TEXT,Num_IT_Com_lab TEXT,JOB_role TEXT,Availability_Of_Internet_WIFI_connection TEXT,Availability_Of_AC TEXT,Availability_Of_Power_BackUp TEXT,Area_under_CCTV_Coverage TEXT,Remarks TEXT,InslabSameAsClass TEXT,InsCarpet_Area TEXT,InsSeating_Capacity TEXT,InsAve_num_bat_run TEXT,InsNum_IT_Com_lab TEXT,InsAvailability_Of_Internet_WIFI_connection TEXT,InsAvailability_Of_AC TEXT,InsAvailability_Of_Power_BackUp TEXT,InsArea_under_CCTV_Coverage TEXT,InsRemarks TEXT,proc_tracker INTEGER )";
        subOfficeTableQuery = "CREATE TABLE Office ( Id INTEGER PRIMARY KEY AUTOINCREMENT,YearWiseCollegeId TEXT NOT NULL,ApplicationNo TEXT,OfficeId TEXT,Internet TEXT,AreaType TEXT,CarpetArea TEXT,Ac TEXT,BackUp TEXT,CCTV TEXT,remarks TEXT,InsCarpetArea TEXT,InsInternet TEXT,InsAC TEXT,InsBackUp TEXT,InsCCTV TEXT,Insremarks TEXT,proc_tracker INTEGER)";
        subEquipmentTableQuery = "CREATE TABLE Equipment ( Id INTEGER PRIMARY KEY AUTOINCREMENT,Equipment_Id TEXT,YearWiseCollegeId TEXT NOT NULL,ApplicationNo TEXT,Job_Id TEXT,Internet TEXT,Job_Name TEXT,Equipment_Name TEXT,TotalNo TEXT,Remarks TEXT,InsTotalNo TEXT,InsRemarks TEXT,proc_tracker INTEGER)";
        subJobRolesTableQuery = "CREATE TABLE Jobroles ( Id INTEGER PRIMARY KEY AUTOINCREMENT,JobID TEXT ,ApplicationNo TEXT, YearWiseCollegeId  TEXT  NOT NULL, JobName TEXT,HandbookAvailable TEXT,Trainees TEXT,Batch INTEGER,Remark TEXT,InsHandbookAvailable TEXT,InsTrainees TEXT,Insbatch TEXT,Insremark TEXT,proc_tracker INTEGER )";
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

        String subResidentialFacTableQuery = "CREATE TABLE ResidentialFacility (Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ApplicationNo TEXT, " +
                "YearWiseCollegeId  TEXT NOT NULL, " +
                "ResFacId TEXT, " +
                "Type TEXT, " +
                "TotalArea TEXT, " +
                "NoOfRooms INTEGER, " +
                "ResCapacity INTEGER, " +
                "IsPowerBackup TEXT, " +
                "IsCCTVCamera TEXT, " +
                "Remarks TEXT, " +
                "proc_tracker INTEGER)";

		/*
         * questionTableQuery="Create table question()";
		 * optionTableQuery="Create table options()";
		 * surveyAnswerTable="Create table surveyAnswers()";
		 */

        try {
            try {
                dropTable(db, "user");
                db.execSQL(userTableQuery);

            } catch (Exception e) {
                Log.e(TAG + "" + "user", e.getMessage());
            }
            try {
                dropTable(db, "allocation");
                db.execSQL(instituteTableQuery);

            } catch (Exception e) {
                Log.e(TAG + "" + "allocation", e.getMessage());
            }

            try {
                dropTable(db, "Classroom");
                db.execSQL(subClassroomTableQuery);
            } catch (Exception e) {
                Log.e(TAG + "" + "Classroom", e.getMessage());
            }

            try {
                dropTable(db, "Lab");
                db.execSQL(subLabTableQuery);
            } catch (Exception e) {
                Log.e(TAG + "" + "surveySubCategory", e.getMessage());
            }
            try {
                dropTable(db, "Office");
                db.execSQL(subOfficeTableQuery);
            } catch (Exception e) {
                Log.e(TAG + "" + "Office Area", e.getMessage());
            }
            try {
                dropTable(db, "Equipment");
                db.execSQL(subEquipmentTableQuery);
            } catch (Exception e) {
                Log.e(TAG + "" + "Equipment", e.getMessage());
            }

            try {
                dropTable(db, "Jobroles");
                db.execSQL(subJobRolesTableQuery);
            } catch (Exception e) {
                Log.e(TAG + "" + "subJobRolesTableQuery", e.getMessage());
            }

            try {
                dropTable(db, "SupportStaff");
                db.execSQL(subStaffTableQuery);
            } catch (Exception e) {
                Log.e("SupportStaff", e.getMessage());
            }

            try {
                dropTable(db, "ResidentialFacility");
                db.execSQL(subResidentialFacTableQuery);
            } catch (Exception e) {
                Log.e("ResidentialFacility", e.getMessage());
            }

        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private void dropTable(SQLiteDatabase db, String tableName) {
        // TODO Auto-generated method stub
        String query;
        try {
            query = "DROP TABLE IF EXISTS " + tableName;
            db.execSQL(query);
        } catch (Exception e) {
            Log.e(TAG + "" + "dropTable", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    public List<Institute> getInstituteList() {
        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , ApplicationNo , Name_ParentOrg , Name_Training_Center , DistrictName ,StateName ,Stage , CreationDate ,SubmissionDate, inspectionFromDate ,inspectionToDate from allocation ";
        List<Institute> instituteList = null;
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                instituteList = new ArrayList<Institute>();
                do {
                    Institute institute = new Institute();
                    institute.setYearWiseCollegeId(cursor.getString(0));
                    institute.setApplicationNo(cursor.getString(1));
                    institute.setName_ParentOrg(cursor.getString(2));
                    institute.setName_Training_Center(cursor.getString(3));
                    institute.setDistrictName(cursor.getString(4));
                    institute.setStateName(cursor.getString(5));
                    institute.setStage(cursor.getString(6));
                    institute.setCreationDate(cursor.getString(7));
                    institute.setSubmissionDate(cursor.getString(8));
                    institute.setInspectionFromDate(cursor.getString(9));
                    institute.setInspectionToDate(cursor.getString(10));
                    instituteList.add(institute);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG + "---" + "getInstituteList", e.getMessage());
            return null;

        }

        return instituteList;
    }

    public List<SubCategoryClass> getClassList() {
        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , ApplicationNo,ClassId,Classroom_Name,Carpet_Area,Seating_Capacity,Avg_Batches,Availability_Of_AC,Availability_Of_Power_BackUp,Area_under_CCTV_Coverage,Availability_of_Overhead_Projector,avail_Internet,Remarks,InsCarpet_Area,InsSeating_Capacity,InsAvg_Batches,InsAvailability_Of_AC,InsAvailability_Of_Power_BackUp,InsArea_under_CCTV_Coverage,InsAvailability_of_Overhead_Projector,Insavail_Internet,InsRemarks from Classroom ";
        List<SubCategoryClass> classList = new ArrayList<SubCategoryClass>();
        ;
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    SubCategoryClass classdetials = new SubCategoryClass();
                    classdetials.setYearWiseCollegeId(cursor.getString(0));
                    classdetials.setApplicationNo(cursor.getString(1));
                    classdetials.setClassId(cursor.getString(2));
                    classdetials.setClassroom_Name(cursor.getString(3));
                    classdetials.setCarpet_Area(cursor.getString(4));
                    classdetials.setSeating_Capacity(cursor.getString(5));
                    classdetials.setAvg_Batches(cursor.getString(6));
                    classdetials.setAvailability_Of_Power_BackUp(cursor
                            .getString(7));
                    classdetials.setArea_under_CCTV_Coverage(cursor
                            .getString(8));
                    classdetials.setAvailability_of_Overhead_Projector(cursor
                            .getString(9));
                    classdetials.setAvail_Internet(cursor.getString(10));
                    classdetials.setRemarks(cursor.getString(11));
                    classdetials.setInsCarpet_Area(cursor.getString(12));
                    classdetials.setInsSeating_Capacity(cursor.getString(13));
                    classdetials.setInsAvg_Batches(cursor.getString(14));
                    classdetials.setInsAvailability_Of_AC(cursor.getString(15));
                    classdetials.setInsAvailability_Of_Power_BackUp(cursor
                            .getString(16));
                    classdetials.setInsArea_under_CCTV_Coverage(cursor
                            .getString(17));

                    classList.add(classdetials);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG + "---" + "getInstituteList", e.getMessage());
            return null;

        }

        return classList;
    }

    public List<SubCategory> getSubCategoryList(int categoryId) {
        SQLiteDatabase database = getReadableDatabase();
        List<SubCategory> listOfSubCategory = new ArrayList<SubCategory>();
        SubCategory surveySubCategory = null;
        String subCategory_Query = "SELECT  surveySubCategoryId,surveySubCategoryName,surveySubCategoryStatus,surveySubCategoryOrder FROM surveySubCategory where surveyCategoryId = "
                + categoryId + "  order by surveySubCategoryOrder ASC";

        try {
            Cursor cursor = database.rawQuery(subCategory_Query, null);

            if (cursor.moveToFirst()) {
                do {
                    surveySubCategory = new SubCategory();
                    surveySubCategory.setSurveySubCategoryId(cursor.getInt(0));
                    surveySubCategory.setSurveySubCategoryName(cursor
                            .getString(1));
                    surveySubCategory.setSurveySubCategoryStatus(cursor
                            .getString(3));
                    surveySubCategory.setSurveySubCategoryOrder(cursor
                            .getInt(4));
                    listOfSubCategory.add(surveySubCategory);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e(TAG + "---" + "getCategoryList", e.getMessage());
            return null;
        }
        return listOfSubCategory;

    }

    public User getLoggedUser() {
        SQLiteDatabase database = getReadableDatabase();
        User user = null;
        String selectSQLUser = "SELECT  Userid,Username,Password,status FROM user where status = 1 ";
        Cursor cursor = database.rawQuery(selectSQLUser, null);

        if (cursor.moveToFirst()) {
            user = new User();
            user.setUserid(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setStatus(cursor.getInt(3));

        }
        return user;
    }

    public User getLoggedUserbyUserId(int _userId) {
        SQLiteDatabase database = getReadableDatabase();
        User user = null;
        String selectSQLUser = "SELECT Userid,Username,Password,status FROM user where status != 1 and Userid = "
                + _userId;
        Cursor cursor = database.rawQuery(selectSQLUser, null);

        if (cursor.moveToFirst()) {
            user = new User();
            user.setUserid(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setStatus(cursor.getInt(3));

        }
        return user;
    }

    public boolean addLoggedUser(String userName, String password, int _userId) {
        User user = getLoggedUserbyUserId(_userId);
        SQLiteDatabase database = getWritableDatabase();
        if (user == null) {
            try {
                // Inserting into Users
                ContentValues values = new ContentValues();

                values.put("Userid", _userId);
                values.put("Username", userName);
                values.put("Password", password);
                values.put("status", 1);
                values.put("date", currDate);
                database.insert("user", null, values);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            String whereSql = "userId = " + _userId;
            try {
                ContentValues values = new ContentValues();
                values.put("Username", userName);
                values.put("Password", password);
                values.put("status", 1);

                database.update("user", values, whereSql, null);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public boolean addInstituteData(JSONObject jsonobj) {

        SQLiteDatabase database = getWritableDatabase();

        if (jsonobj != null) {
            try {
                if (jsonobj.has("responsecode")) {
                    if (Integer.parseInt(jsonobj.getString("responsecode")) == 2
                            && jsonobj.has("rowcount")) {
                        for (int i = 0; i < jsonobj.getInt("rowcount"); i++) {

                            if (jsonobj.has(String.valueOf(i))) {
                                ContentValues values = new ContentValues();
                                JSONObject rowValue = new JSONObject(
                                        jsonobj.getString(String.valueOf(i)));
                                values.put("YearWiseCollegeId",
                                        rowValue.getString("YearWiseCollegeId"));
                                values.put("ApplicationNo",
                                        rowValue.getString("ApplicationNo"));
                                values.put("Stage", rowValue.getString("Stage"));
                                try {
                                    values.put(
                                            "CreationDate",
                                            sdf_ss.format(sdf.parse(rowValue
                                                    .getString("CreationDate"))));
                                    values.put(
                                            "SubmissionDate",
                                            sdf_ss.format(sdf.parse(rowValue
                                                    .getString("SubmissionDate"))));
                                    values.put(
                                            "inspectionFromDate",
                                            sdf_ss.format(sdf.parse(rowValue
                                                    .getString("inspectionFromDate"))));
                                    values.put(
                                            "inspectionToDate",
                                            sdf_ss.format(sdf.parse(rowValue
                                                    .getString("inspectionToDate"))));
                                } catch (ParseException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                values.put("Name_ParentOrg",
                                        rowValue.getString("Name_ParentOrg"));
                                values.put("Name_Training_Center", rowValue
                                        .getString("Name_Training_Center"));
                                values.put("contact_spoc",
                                        rowValue.getString("contact_spoc"));
                                values.put("email_spoc",
                                        rowValue.getString("email_spoc"));
                                values.put("DistrictName",
                                        rowValue.getString("DistrictName"));
                                values.put("StateName",
                                        rowValue.getString("StateName"));
                                database.insert("allocation", null, values);
                            }

                        }
                    } else if (Integer.parseInt(jsonobj
                            .getString("responsecode")) == 0
                            || Integer.parseInt(jsonobj
                            .getString("responsecode")) == 1) {
                        Toast.makeText(application_context, "No data found",
                                Toast.LENGTH_LONG).show();
                        return false;
                    }
                }
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public boolean addClassData(JSONObject jsonobj, String yearWiseCollegeId,
                                String applicationId) {

        SQLiteDatabase database = getWritableDatabase();

        if (jsonobj != null) {
            try {
                if (jsonobj.has("responsecode")) {
                    if (Integer.parseInt(jsonobj.getString("responsecode")) == 2
                            && jsonobj.has("rowcount")) {
                        for (int i = 0; i < jsonobj.getInt("rowcount"); i++) {

                            if (jsonobj.has(String.valueOf(i))) {
                                ContentValues values = new ContentValues();
                                JSONObject rowValue = new JSONObject(
                                        jsonobj.getString(String.valueOf(i)));
                                values.put("ClassId", rowValue.getString("Id"));
                                values.put("ApplicationNo", applicationId);
                                values.put("YearWiseCollegeId",
                                        yearWiseCollegeId);
                                values.put("Classroom_Name",
                                        rowValue.getString("Classroom_Name"));
                                values.put("Carpet_Area",
                                        rowValue.getString("Carpet_Area"));
                                values.put("Seating_Capacity",
                                        rowValue.getString("Seating_Capacity"));
                                values.put("Avg_Batches",
                                        rowValue.getString("Avg_Batches"));
                                values.put("Availability_Of_AC", rowValue
                                        .getString("Availability_Of_AC"));
                                values.put(
                                        "Availability_Of_Power_BackUp",
                                        rowValue.getString("Availability_Of_Power_BackUp"));
                                values.put("Area_under_CCTV_Coverage", rowValue
                                        .getString("Area_under_CCTV_Coverage"));
                                values.put(
                                        "Availability_of_Overhead_Projector",
                                        rowValue.getString("Availability_of_Overhead_Projector"));
                                values.put("avail_Internet",
                                        rowValue.getString("avail_Internet"));
                                values.put("Remarks",
                                        rowValue.getString("Remarks"));
                                values.put("InsCarpet_Area",
                                        rowValue.getString("InsCarpet_Area"));
                                values.put("InsSeating_Capacity", rowValue
                                        .getString("InsSeating_Capacity"));
                                values.put("InsAvg_Batches",
                                        rowValue.getString("InsAvg_Batches"));
                                values.put("InsAvailability_Of_AC", rowValue
                                        .getString("InsAvailability_Of_AC"));
                                values.put(
                                        "InsAvailability_Of_Power_BackUp",
                                        rowValue.getString("InsAvailability_Of_Power_BackUp"));
                                values.put(
                                        "InsArea_under_CCTV_Coverage",
                                        rowValue.getString("InsArea_under_CCTV_Coverage"));
                                values.put(
                                        "InsAvailability_of_Overhead_Projector",
                                        rowValue.getString("InsAvailability_of_Overhead_Projector"));
                                values.put("Insavail_Internet",
                                        rowValue.getString("Insavail_Internet"));
                                values.put("InsRemarks",
                                        rowValue.getString("InsRemarks"));
                                values.put("proc_tracker", 1);
                                database.insert("Classroom", null, values);
                            }

                        }
                    } else if (Integer.parseInt(jsonobj
                            .getString("responsecode")) == 0
                            || Integer.parseInt(jsonobj
                            .getString("responsecode")) == 1) {
                        Toast.makeText(application_context, "No Data Found..",
                                Toast.LENGTH_LONG).show();
                    }
                }
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public boolean addLabData(JSONObject jsonobj, String yearWiseCollegeId,
                              String applicationId) {

        SQLiteDatabase database = getWritableDatabase();

        if (jsonobj != null) {
            try {
                if (jsonobj.has("responsecode")) {
                    if (Integer.parseInt(jsonobj.getString("responsecode")) == 2
                            && jsonobj.has("rowcount")) {
                        for (int i = 0; i < jsonobj.getInt("rowcount"); i++) {

                            if (jsonobj.has(String.valueOf(i))) {
                                ContentValues values = new ContentValues();
                                JSONObject rowValue = new JSONObject(
                                        jsonobj.getString(String.valueOf(i)));
                                values.put("LabId", rowValue.getString("ID"));
                                values.put("ApplicationNo", applicationId);
                                values.put("YearWiseCollegeId",
                                        yearWiseCollegeId);
                                values.put("Lab_type",
                                        rowValue.getString("Lab_type"));
                                values.put("LAB_Name",
                                        rowValue.getString("LAB_Name"));
                                values.put("labSameAsClass",
                                        rowValue.getString("labSameAsClass"));
                                values.put("Carpet_Area",
                                        rowValue.getString("Carpet_Area"));
                                values.put("Seating_Capacity",
                                        rowValue.getString("Seating_Capacity"));
                                values.put("Ave_num_bat_run",
                                        rowValue.getString("Ave_num_bat_run"));
                                values.put("Num_IT_Com_lab",
                                        rowValue.getString("Num_IT_Com_lab"));
                                values.put("JOB_role",
                                        rowValue.getString("JOB_role"));
                                values.put(
                                        "Availability_Of_Internet_WIFI_connection",
                                        rowValue.getString("Availability_Of_Internet_WIFI_connection"));
                                values.put("Availability_Of_AC", rowValue
                                        .getString("Availability_Of_AC"));
                                values.put(
                                        "Availability_Of_Power_BackUp",
                                        rowValue.getString("Availability_Of_Power_BackUp"));
                                values.put("Area_under_CCTV_Coverage", rowValue
                                        .getString("Area_under_CCTV_Coverage"));
                                values.put("Remarks",
                                        rowValue.getString("Remarks"));
                                values.put("InslabSameAsClass",
                                        rowValue.getString("InslabSameAsClass"));
                                values.put("InsCarpet_Area",
                                        rowValue.getString("InsCarpet_Area"));
                                values.put("InsSeating_Capacity", rowValue
                                        .getString("InsSeating_Capacity"));
                                values.put("InsAve_num_bat_run", rowValue
                                        .getString("InsAve_num_bat_run"));
                                values.put("InsNum_IT_Com_lab",
                                        rowValue.getString("InsNum_IT_Com_lab"));
                                values.put(
                                        "InsAvailability_Of_Internet_WIFI_connection",
                                        rowValue.getString("InsAvailability_Of_Internet_WIFI_connection"));
                                values.put("InsAvailability_Of_AC", rowValue
                                        .getString("InsAvailability_Of_AC"));
                                values.put(
                                        "InsAvailability_Of_Power_BackUp",
                                        rowValue.getString("InsAvailability_Of_Power_BackUp"));
                                values.put(
                                        "InsArea_under_CCTV_Coverage",
                                        rowValue.getString("InsArea_under_CCTV_Coverage"));
                                values.put("InsRemarks",
                                        rowValue.getString("InsRemarks"));
                                values.put("proc_tracker", 1);
                                database.insert("Lab", null, values);
                            }

                        }
                    } else if (Integer.parseInt(jsonobj
                            .getString("responsecode")) == 0) {
                        Toast.makeText(application_context, "No Data Found..",
                                Toast.LENGTH_LONG).show();
                    }
                }

            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public List<SubCategoryClass> getClassListbyYearWiseCollageId(
            String YearWiseCollegeId) {
        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , ApplicationNo,ClassId,Classroom_Name,"
                + " Carpet_Area,Seating_Capacity,Avg_Batches,Availability_Of_AC,Availability_Of_Power_BackUp"
                + " ,Area_under_CCTV_Coverage,Availability_of_Overhead_Projector,avail_Internet,"
                + " Remarks,InsCarpet_Area,InsSeating_Capacity,InsAvg_Batches,InsAvailability_Of_AC,"
                + " InsAvailability_Of_Power_BackUp,InsArea_under_CCTV_Coverage,"
                + " InsAvailability_of_Overhead_Projector,Insavail_Internet,InsRemarks,proc_tracker"
                + " from Classroom where YearWiseCollegeId = "
                + YearWiseCollegeId;

        List<SubCategoryClass> classList = new ArrayList<SubCategoryClass>();
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    SubCategoryClass classdetials = new SubCategoryClass();
                    classdetials.setYearWiseCollegeId(cursor.getString(0));
                    classdetials.setApplicationNo(cursor.getString(1));
                    classdetials.setClassId(cursor.getString(2));
                    classdetials.setClassroom_Name(cursor.getString(3));
                    classdetials.setCarpet_Area(cursor.getString(4));
                    classdetials.setSeating_Capacity(cursor.getString(5));
                    classdetials.setAvg_Batches(cursor.getString(6));
                    classdetials.setAvailability_Of_AC(cursor.getString(7));
                    classdetials.setAvailability_Of_Power_BackUp(cursor
                            .getString(8));
                    classdetials.setArea_under_CCTV_Coverage(cursor
                            .getString(9));
                    classdetials.setAvailability_of_Overhead_Projector(cursor
                            .getString(10));
                    classdetials.setAvail_Internet(cursor.getString(11));
                    classdetials.setRemarks(cursor.getString(12));
                    classdetials.setInsCarpet_Area(cursor.getString(13));
                    classdetials.setInsSeating_Capacity(cursor.getString(14));
                    classdetials.setInsAvg_Batches(cursor.getString(15));
                    classdetials.setInsAvailability_Of_AC(cursor.getString(16));
                    classdetials.setInsAvailability_Of_Power_BackUp(cursor
                            .getString(17));
                    classdetials.setInsArea_under_CCTV_Coverage(cursor
                            .getString(18));
                    classdetials
                            .setInsAvailability_of_Overhead_Projector(cursor
                                    .getString(19));
                    classdetials.setInsavail_Internet(cursor.getString(20));
                    classdetials.setInsRemarks(cursor.getString(21));
                    classdetials.setProc_tracker(cursor.getInt(22));

                    classList.add(classdetials);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG + "---" + "getInstituteList", e.getMessage());
            return null;

        }

        return classList;
    }

    public SubCategoryClass getClassDataByClassId(String instituteId,
                                                  String classId) {
        SubCategoryClass classdetials = null;
        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId ,"
                + " ApplicationNo," + "ClassId,Classroom_Name,"
                + "Carpet_Area,Seating_Capacity,"
                + "Avg_Batches,Availability_Of_AC,"
                + "Availability_Of_Power_BackUp," + "Area_under_CCTV_Coverage,"
                + "Availability_of_Overhead_Projector,"
                + "avail_Internet,Remarks,InsCarpet_Area,"
                + "InsSeating_Capacity,InsAvg_Batches,"
                + "InsAvailability_Of_AC," + "InsAvailability_Of_Power_BackUp,"
                + "InsArea_under_CCTV_Coverage,"
                + "InsAvailability_of_Overhead_Projector,"
                + "Insavail_Internet," + "InsRemarks," + "proc_tracker"
                + " from Classroom where ClassId=" + classId
                + " and YearWiseCollegeId=" + instituteId;

        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    classdetials = new SubCategoryClass();
                    classdetials.setYearWiseCollegeId(cursor.getString(0));
                    classdetials.setApplicationNo(cursor.getString(1));
                    classdetials.setClassId(cursor.getString(2));
                    classdetials.setClassroom_Name(cursor.getString(3));
                    classdetials.setCarpet_Area(cursor.getString(4));
                    classdetials.setSeating_Capacity(cursor.getString(5));
                    classdetials.setAvg_Batches(cursor.getString(6));
                    classdetials.setAvailability_Of_AC(cursor.getString(7));
                    classdetials.setAvailability_Of_Power_BackUp(cursor
                            .getString(8));
                    classdetials.setArea_under_CCTV_Coverage(cursor
                            .getString(9));
                    classdetials.setAvailability_of_Overhead_Projector(cursor
                            .getString(10));
                    classdetials.setAvail_Internet(cursor.getString(11));
                    classdetials.setRemarks(cursor.getString(12));
                    classdetials.setInsCarpet_Area(null);
                    classdetials.setInsSeating_Capacity(null);
                    classdetials.setInsAvg_Batches(null);
                    classdetials.setInsAvailability_Of_AC(null);
                    classdetials.setInsAvailability_Of_Power_BackUp(null);
                    classdetials.setInsArea_under_CCTV_Coverage(null);
                    classdetials.setInsAvailability_of_Overhead_Projector(null);
                    classdetials.setInsavail_Internet(null);
                    classdetials.setInsRemarks(null);
                    classdetials.setProc_tracker(cursor.getInt(22));

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG + "---" + "getInstituteList", e.getMessage());
            return null;

        }

        return classdetials;

    }

    public List<SubCategoryLab> getLabListbyYearWiseCollageId(
            String YearWiseCollegeId) {
        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId, " + " LabId ,"
                + " Lab_type, " + " LAB_Name ," + " labSameAsClass ,"
                + " Carpet_Area ," + " Seating_Capacity,"
                + " Ave_num_bat_run ," + " Num_IT_Com_lab ," + " JOB_role ,"
                + " Availability_Of_Internet_WIFI_connection ,"
                + " Availability_Of_AC ," + " Availability_Of_Power_BackUp ,"
                + " Area_under_CCTV_Coverage ," + " Remarks ,"
                + " InslabSameAsClass ," + " InsCarpet_Area ,"
                + " InsSeating_Capacity ," + " InsAve_num_bat_run ,"
                + "	InsNum_IT_Com_lab ,"
                + "	InsAvailability_Of_Internet_WIFI_connection ,"
                + " InsAvailability_Of_AC ,"
                + " InsAvailability_Of_Power_BackUp ,"
                + " InsArea_under_CCTV_Coverage ," + "	InsRemarks ,"
                + "	proc_tracker from Lab where YearWiseCollegeId = "
                + YearWiseCollegeId;
        List<SubCategoryLab> LabList = new ArrayList<SubCategoryLab>();
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    SubCategoryLab Labdetials = new SubCategoryLab();
                    Labdetials.setYearWiseCollegeId(cursor.getString(0));
                    Labdetials.setLabId(cursor.getString(1));
                    Labdetials.setLab_type(cursor.getString(2));
                    Labdetials.setLAB_Name(cursor.getString(3));
                    Labdetials.setLabSameAsClass(cursor.getString(4));
                    Labdetials.setCarpet_Area(cursor.getString(5));
                    Labdetials.setSeating_Capacity(cursor.getString(6));
                    Labdetials.setAve_num_bat_run(cursor.getString(7));
                    Labdetials.setNum_IT_Com_lab(cursor.getString(8));
                    Labdetials.setJOB_role(cursor.getString(9));
                    Labdetials
                            .setAvailability_Of_Internet_WIFI_connection(cursor
                                    .getString(10));
                    Labdetials.setAvailability_Of_AC(cursor.getString(11));
                    Labdetials.setAvailability_Of_Power_BackUp(cursor
                            .getString(12));
                    Labdetials
                            .setArea_under_CCTV_Coverage(cursor.getString(13));
                    Labdetials.setRemarks(cursor.getString(14));
                    Labdetials.setInslabSameAsClass(cursor.getString(15));
                    Labdetials.setInsCarpet_Area(cursor.getString(16));
                    Labdetials.setInsAvailability_Of_Power_BackUp(cursor
                            .getString(17));
                    Labdetials.setInsArea_under_CCTV_Coverage(cursor
                            .getString(18));
                    Labdetials.setInsRemarks(cursor.getString(19));
                    Labdetials.setProc_tracker(cursor.getInt(25));
                    LabList.add(Labdetials);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG + "---" + "getInstituteList", e.getMessage());
            return null;

        }

        return LabList;
    }

    public JSONObject getClassDataSync(SubCategoryClass categoryClass) {
        JSONObject classData = new JSONObject();
        try {
            classData.put("Id", categoryClass.getClassId());
            classData.put("Classroom_Name", categoryClass.getClassroom_Name());
            classData.put("Carpet_Area", categoryClass.getCarpet_Area());
            classData.put("Seating_Capacity",
                    categoryClass.getSeating_Capacity());
            classData.put("Avg_Batches", categoryClass.getAvg_Batches());
            classData.put("Availability_Of_AC",
                    categoryClass.getAvailability_Of_AC());
            classData.put("Availability_Of_Power_BackUp",
                    categoryClass.getAvailability_Of_Power_BackUp());
            classData.put("Area_under_CCTV_Coverage",
                    categoryClass.getArea_under_CCTV_Coverage());
            classData.put("Availability_of_Overhead_Projector",
                    categoryClass.getAvailability_of_Overhead_Projector());
            classData.put("avail_Internet", categoryClass.getAvail_Internet());
            classData.put("Remarks", categoryClass.getRemarks());
            classData.put("InsCarpet_Area", categoryClass.getInsCarpet_Area());

            classData.put("InsSeating_Capacity",
                    categoryClass.getInsSeating_Capacity());
            classData.put("InsAvg_Batches", categoryClass.getInsAvg_Batches());
            classData.put("InsAvailability_Of_AC",
                    categoryClass.getInsAvailability_Of_AC());
            classData.put("InsAvailability_Of_Power_BackUp",
                    categoryClass.getInsAvailability_Of_Power_BackUp());

            classData.put("InsArea_under_CCTV_Coverage",
                    categoryClass.getInsArea_under_CCTV_Coverage());
            classData.put("InsAvailability_of_Overhead_Projector",
                    categoryClass.getInsAvailability_of_Overhead_Projector());
            classData.put("Insavail_Internet",
                    categoryClass.getInsavail_Internet());
            classData.put("InsRemarks", categoryClass.getInsRemarks());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return classData;
    }

    public SubCategoryLab getLabDataByLabId(String instituteId, String labId) {
        SubCategoryLab labdetails = null;
        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId, "

                + " LabId ," + " Lab_type, " + " LAB_Name ," + " labSameAsClass ,"
                + " Carpet_Area ," + " Seating_Capacity,"
                + " Ave_num_bat_run ," + " Num_IT_Com_lab ," + " JOB_role ,"
                + " Availability_Of_Internet_WIFI_connection ,"
                + " Availability_Of_AC ," + " Availability_Of_Power_BackUp ,"
                + " Area_under_CCTV_Coverage ," + " Remarks ,"
                + " InslabSameAsClass ," + " InsCarpet_Area ,"
                + " InsSeating_Capacity ," + " InsAve_num_bat_run ,"
                + "	InsNum_IT_Com_lab ,"
                + "	InsAvailability_Of_Internet_WIFI_connection ,"
                + " InsAvailability_Of_AC ,"
                + " InsAvailability_Of_Power_BackUp ,"
                + " InsArea_under_CCTV_Coverage ," + "	InsRemarks ,"
                + "	proc_tracker from Lab where YearWiseCollegeId = "
                + instituteId + " and LabId = " + labId;

        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    labdetails = new SubCategoryLab();
                    labdetails.setYearWiseCollegeId(cursor.getString(0));
                    labdetails.setLabId(cursor.getString(1));
                    labdetails.setLab_type(cursor.getString(2));
                    labdetails.setLAB_Name(cursor.getString(3));
                    labdetails.setLabSameAsClass(cursor.getString(4));
                    labdetails.setCarpet_Area(cursor.getString(5));
                    labdetails.setSeating_Capacity(cursor.getString(6));
                    labdetails.setAve_num_bat_run(cursor.getString(7));
                    labdetails.setNum_IT_Com_lab(cursor.getString(8));
                    labdetails.setJOB_role(cursor.getString(9));
                    labdetails
                            .setAvailability_Of_Internet_WIFI_connection(cursor
                                    .getString(10));
                    labdetails.setAvailability_Of_AC(cursor.getString(11));
                    labdetails.setAvailability_Of_Power_BackUp(cursor
                            .getString(12));
                    labdetails
                            .setArea_under_CCTV_Coverage(cursor.getString(13));
                    labdetails.setRemarks(cursor.getString(14));
                    labdetails.setInslabSameAsClass(null);
                    labdetails.setInsCarpet_Area(null);
                    labdetails.setInsSeating_Capacity(null);
                    labdetails.setInsAve_num_bat_run(null);
                    labdetails.setInsNum_IT_Com_lab(null);
                    labdetails
                            .setInsAvailability_Of_Internet_WIFI_connection(null);
                    labdetails.setInsAvailability_Of_AC(null);
                    labdetails.setInsAvailability_Of_Power_BackUp(null);
                    labdetails.setInsArea_under_CCTV_Coverage(null);
                    labdetails.setInsRemarks(null);
                    labdetails.setProc_tracker(cursor.getInt(25));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG + "---" + "getInstituteList", e.getMessage());
            return null;

        }

        return labdetails;

    }

    public boolean saveLabData(SubCategoryLab subCategoryLab, String mode) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        try {
            if (mode.equalsIgnoreCase("draft")) {
                values.put("InslabSameAsClass", subCategoryLab.getInslabSameAsClass());
                values.put("InsCarpet_Area", subCategoryLab.getInsCarpet_Area());
                values.put("InsSeating_Capacity", subCategoryLab.getInsSeating_Capacity());
                values.put("InsAve_num_bat_run", subCategoryLab.getInsAve_num_bat_run());
                values.put("InsNum_IT_Com_lab", subCategoryLab.getInsNum_IT_Com_lab());
                values.put("InsAvailability_Of_Internet_WIFI_connection",
                        subCategoryLab.getInsAvailability_Of_Internet_WIFI_connection());
                values.put("InsAvailability_Of_AC", subCategoryLab.getInsAvailability_Of_AC());
                values.put("InsAvailability_Of_Power_BackUp", subCategoryLab.getInsAvailability_Of_Power_BackUp());
                values.put("InsArea_under_CCTV_Coverage", subCategoryLab.getInsArea_under_CCTV_Coverage());
                values.put("InsRemarks", subCategoryLab.getInsRemarks());
                values.put("proc_tracker", 2);
                database.update("Lab", values, " YearWiseCollegeId= "
                        + subCategoryLab.getYearWiseCollegeId() + " and LabId="
                        + subCategoryLab.getLabId(), null);
            } else {
                values.put("InslabSameAsClass", subCategoryLab.getInslabSameAsClass());
                values.put("InsCarpet_Area", subCategoryLab.getInsCarpet_Area());
                values.put("InsSeating_Capacity", subCategoryLab.getInsSeating_Capacity());
                values.put("InsAve_num_bat_run", subCategoryLab.getInsAve_num_bat_run());
                values.put("InsNum_IT_Com_lab", subCategoryLab.getInsNum_IT_Com_lab());
                values.put("InsAvailability_Of_Internet_WIFI_connection",
                        subCategoryLab.getInsAvailability_Of_Internet_WIFI_connection());
                values.put("InsAvailability_Of_AC", subCategoryLab.getInsAvailability_Of_AC());
                values.put("InsAvailability_Of_Power_BackUp", subCategoryLab.getInsAvailability_Of_Power_BackUp());
                values.put("InsArea_under_CCTV_Coverage", subCategoryLab.getInsArea_under_CCTV_Coverage());
                values.put("InsRemarks", subCategoryLab.getInsRemarks());
                values.put("proc_tracker", 3);
                database.update("Lab", values, " YearWiseCollegeId= "
                        + subCategoryLab.getYearWiseCollegeId() + " and LabId="
                        + subCategoryLab.getLabId(), null);
            }
        } catch (Exception e) {
            Log.e(TAG + "saveLabData", e.getMessage());
        } finally {
            database.close();
        }
        return true;

    }

    public boolean saveClassData(SubCategoryClass subCategoryClass, String mode) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        try {
            if (mode.equalsIgnoreCase("draft")) {
                int result = database.delete("Classroom", "ClassId="
                        + subCategoryClass.getClassId(), null);
                if (result > 0) {
                    values.put("ClassId", subCategoryClass.getClassId());
                    values.put("ApplicationNo",
                            subCategoryClass.getApplicationNo());
                    values.put("YearWiseCollegeId",
                            subCategoryClass.getYearWiseCollegeId());
                    values.put("Classroom_Name",
                            subCategoryClass.getClassroom_Name());
                    values.put("Carpet_Area", subCategoryClass.getCarpet_Area());
                    values.put("Seating_Capacity",
                            subCategoryClass.getSeating_Capacity());
                    values.put("Avg_Batches", subCategoryClass.getAvg_Batches());
                    values.put("Availability_Of_AC",
                            subCategoryClass.getAvailability_Of_AC());
                    values.put("Availability_Of_Power_BackUp",
                            subCategoryClass.getAvailability_Of_Power_BackUp());
                    values.put("Area_under_CCTV_Coverage",
                            subCategoryClass.getArea_under_CCTV_Coverage());
                    values.put("Availability_of_Overhead_Projector",
                            subCategoryClass
                                    .getAvailability_of_Overhead_Projector());
                    values.put("avail_Internet",
                            subCategoryClass.getAvail_Internet());
                    values.put("Remarks", subCategoryClass.getRemarks());
                    values.put("InsCarpet_Area",
                            subCategoryClass.getInsCarpet_Area());
                    values.put("InsSeating_Capacity",
                            subCategoryClass.getInsSeating_Capacity());
                    values.put("InsAvg_Batches",
                            subCategoryClass.getInsAvg_Batches());
                    values.put("InsAvailability_Of_AC",
                            subCategoryClass.getInsAvailability_Of_AC());
                    values.put("InsAvailability_Of_Power_BackUp",
                            subCategoryClass
                                    .getInsAvailability_Of_Power_BackUp());
                    values.put("InsArea_under_CCTV_Coverage",
                            subCategoryClass.getInsArea_under_CCTV_Coverage());
                    values.put("InsAvailability_of_Overhead_Projector",
                            subCategoryClass
                                    .getInsAvailability_of_Overhead_Projector());
                    values.put("Insavail_Internet",
                            subCategoryClass.getInsavail_Internet());
                    values.put("InsRemarks", subCategoryClass.getInsRemarks());
                    values.put("proc_tracker", 2);
                    database.insert("Classroom", null, values);
                    // database.update("Classroom", values,
                    // " YearWiseCollegeId= "+subCategoryClass.getYearWiseCollegeId()+" and ClassId="+subCategoryClass.getClassId(),
                    // null);
                }
            } else {
                int result = database.delete("Classroom", "ClassId="
                        + subCategoryClass.getClassId(), null);
                if (result > 0) {
                    values.put("ClassId", subCategoryClass.getClassId());
                    values.put("ApplicationNo",
                            subCategoryClass.getApplicationNo());
                    values.put("YearWiseCollegeId",
                            subCategoryClass.getYearWiseCollegeId());
                    values.put("Classroom_Name",
                            subCategoryClass.getClassroom_Name());
                    values.put("Carpet_Area", subCategoryClass.getCarpet_Area());
                    values.put("Seating_Capacity",
                            subCategoryClass.getSeating_Capacity());
                    values.put("Avg_Batches", subCategoryClass.getAvg_Batches());
                    values.put("Availability_Of_AC",
                            subCategoryClass.getAvailability_Of_AC());
                    values.put("Availability_Of_Power_BackUp",
                            subCategoryClass.getAvailability_Of_Power_BackUp());
                    values.put("Area_under_CCTV_Coverage",
                            subCategoryClass.getArea_under_CCTV_Coverage());
                    values.put("Availability_of_Overhead_Projector",
                            subCategoryClass
                                    .getAvailability_of_Overhead_Projector());
                    values.put("avail_Internet",
                            subCategoryClass.getAvail_Internet());
                    values.put("Remarks", subCategoryClass.getRemarks());
                    values.put("InsCarpet_Area",
                            subCategoryClass.getInsCarpet_Area());
                    values.put("InsSeating_Capacity",
                            subCategoryClass.getInsSeating_Capacity());
                    values.put("InsAvg_Batches",
                            subCategoryClass.getInsAvg_Batches());
                    values.put("InsAvailability_Of_AC",
                            subCategoryClass.getInsAvailability_Of_AC());
                    values.put("InsAvailability_Of_Power_BackUp",
                            subCategoryClass
                                    .getInsAvailability_Of_Power_BackUp());
                    values.put("InsArea_under_CCTV_Coverage",
                            subCategoryClass.getInsArea_under_CCTV_Coverage());
                    values.put("InsAvailability_of_Overhead_Projector",
                            subCategoryClass
                                    .getInsAvailability_of_Overhead_Projector());
                    values.put("Insavail_Internet",
                            subCategoryClass.getInsavail_Internet());
                    values.put("InsRemarks", subCategoryClass.getInsRemarks());
                    values.put("proc_tracker", 3);
                    database.insert("Classroom", null, values);
                    // database.update("Classroom", values,
                    // " YearWiseCollegeId= "+subCategoryClass.getYearWiseCollegeId()+" and ClassId="+subCategoryClass.getClassId(),
                    // null);
                }
            }
        } catch (Exception e) {
            Log.e(TAG + "saveLabData", e.getMessage());
            return false;
        }
        return true;

    }


    public List<JobRolesModel> getJobRolesList(String YearWiseCollegeId) {


        SQLiteDatabase db = null;
        String selectQueryQues = "Select ID,JobName,HandbookAvailable,Trainees,Batch,Remark," +
                "InsHandbookAvailable,InsTrainees,Insbatch,Insremark,proc_tracker,YearWiseCollegeId,JobID from Jobroles where YearWiseCollegeId = " + YearWiseCollegeId;


        List<JobRolesModel> jobRolesModelList = new ArrayList<JobRolesModel>();
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    JobRolesModel jobRolesModel = new JobRolesModel();
                    jobRolesModel.setId(cursor.getInt(0));
                    jobRolesModel.setJobName(cursor.getString(1));
                    jobRolesModel.setHandbookAvailable(cursor.getString(2));
                    jobRolesModel.setTrainees(cursor.getString(3));
                    jobRolesModel.setBatch(cursor.getInt(4));
                    jobRolesModel.setRemark(cursor.getString(5));
                    jobRolesModel.setInsHandbookAvailable(cursor.getString(6));
                    jobRolesModel.setInsTrainees(cursor.getString(7));
                    jobRolesModel.setInsbatch(cursor.getString(8));
                    jobRolesModel.setInsRemark(cursor.getString(9));
                    jobRolesModel.setProc_tracker(cursor.getInt(10));
                    jobRolesModel.setYearWiseCollegeId(cursor.getString(11));
                    jobRolesModel.setJobID(cursor.getString(12));


                    jobRolesModelList.add(jobRolesModel);


                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG + "---" + "getJobRolesList", e.getMessage());
            return null;

        }

        return jobRolesModelList;
    }

    public JobRolesModel getJobDataByJobId(String YearWiseCollegeId, String jobId) {


        SQLiteDatabase db = null;
        String selectQueryQues = "Select ID,JobName,HandbookAvailable,Trainees,Batch,Remark," +
                "InsHandbookAvailable,InsTrainees,Insbatch,Insremark,proc_tracker,YearWiseCollegeId " +
                "from Jobroles where YearWiseCollegeId = " + YearWiseCollegeId
                + " and JobID= " + jobId;
        ;


        JobRolesModel jobRolesModel = null;
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    jobRolesModel = new JobRolesModel();
                    jobRolesModel.setId(cursor.getInt(0));
                    jobRolesModel.setJobName(cursor.getString(1));
                    jobRolesModel.setHandbookAvailable(cursor.getString(2));
                    jobRolesModel.setTrainees(cursor.getString(3));
                    jobRolesModel.setBatch(cursor.getInt(4));
                    jobRolesModel.setRemark(cursor.getString(5));
                    jobRolesModel.setInsHandbookAvailable(cursor.getString(6));
                    jobRolesModel.setInsTrainees(cursor.getString(7));
                    jobRolesModel.setInsbatch(cursor.getString(8));
                    jobRolesModel.setInsRemark(cursor.getString(9));
                    jobRolesModel.setYearWiseCollegeId(cursor.getString(10));


                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG + "---" + "getJobRolesData", e.getMessage());
            return null;

        }

        return jobRolesModel;
    }


    public void updateInstitueProc_tracker(Institute institute) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        try {

            values.put("proc_tracker", institute.getProc_tracker());
            database.update("allocation", values, " YearWiseCollegeId="
                    + institute.getYearWiseCollegeId(), null);
        } catch (Exception e) {
            Log.e(TAG + "updateInstituteProc_tracker", e.getMessage());
        }
    }

    public void updateClassProc_tracker(SubCategoryClass subCategoryClass) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        try {

            values.put("proc_tracker", subCategoryClass.getProc_tracker());
            database.update("Classroom", values, " YearWiseCollegeId="
                    + subCategoryClass.getYearWiseCollegeId() + " and ClassId="
                    + subCategoryClass.getClassId(), null);
        } catch (Exception e) {
            Log.e(TAG + "updateClassProc_tracker", e.getMessage());
        }
    }

    public void updateLabProc_tracker(SubCategoryLab subCategoryLab) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        try {

            values.put("proc_tracker", subCategoryLab.getProc_tracker());
            database.update("Lab", values, " YearWiseCollegeId= "
                    + subCategoryLab.getYearWiseCollegeId() + " and LabId="
                    + subCategoryLab.getLabId(), null);
        } catch (Exception e) {
            Log.e(TAG + "updateClassProc_tracker", e.getMessage());
        }
    }

    public void updateOfficeProc_tracker(SubListOffice obj) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        try {

            values.put("proc_tracker", obj.getProc_tracker());
            database.update("Office", values,
                    " YearWiseCollegeId= " + obj.getYearWiseCollegeId()
                            + " and OfficeId=" + obj.getOfficeId(), null);
        } catch (Exception e) {
            Log.e(TAG + "updateClassProc_tracker", e.getMessage());
        }
    }

    public boolean addOfficeData(JSONObject jsonobj, String yearWiseCollegeId,
                                 String applicationId) {
        SQLiteDatabase database = getWritableDatabase();

        if (jsonobj != null) {
            try {
                if (jsonobj.has("responsecode")) {
                    if (Integer.parseInt(jsonobj.getString("responsecode")) == 2
                            && jsonobj.has("rowcount")) {
                        for (int i = 0; i < jsonobj.getInt("rowcount"); i++) {

                            if (jsonobj.has(String.valueOf(i))) {
                                ContentValues values = new ContentValues();
                                JSONObject rowValue = new JSONObject(
                                        jsonobj.getString(String.valueOf(i)));
                                values.put("OfficeId", rowValue.getString("id"));
                                values.put("ApplicationNo", applicationId);
                                values.put("YearWiseCollegeId",
                                        yearWiseCollegeId);
                                values.put("AreaType",
                                        rowValue.getString("AreaType"));
                                values.put("CarpetArea",
                                        rowValue.getString("CarpetArea"));
                                values.put("Internet",
                                        rowValue.getString("Internet"));
                                values.put("Ac", rowValue.getString("AC"));
                                values.put("BackUp",
                                        rowValue.getString("BackUp"));
                                values.put("CCTV", rowValue.getString("CCTV"));
                                values.put("remarks",
                                        rowValue.getString("remarks"));
                                values.put(
                                        "InsCarpetArea",
                                        (rowValue.getString("InsCarpetArea") != "null") ? rowValue
                                                .getString("InsCarpetArea")
                                                : null);
                                values.put(
                                        "InsInternet",
                                        (rowValue.getString("InsInternet") != "null") ? rowValue
                                                .getString("InsInternet")
                                                : null);
                                values.put(
                                        "InsAC",
                                        (rowValue.getString("InsAC") != "null") ? rowValue
                                                .getString("InsAC") : null);
                                values.put(
                                        "InsBackUp",
                                        (rowValue.getString("InsBackUp") != "null") ? rowValue
                                                .getString("InsBackUp") : null);
                                values.put(
                                        "InsCCTV",
                                        (rowValue.getString("InsCCTV") != "null") ? rowValue
                                                .getString("InsCCTV") : null);
                                values.put(
                                        "Insremarks",
                                        (rowValue.getString("Insremarks") != "null") ? rowValue
                                                .getString("Insremarks") : null);
                                values.put("proc_tracker", 1);

                                database.insert("Office", null, values);
                            }
                        }
                    } else if (Integer.parseInt(jsonobj
                            .getString("responsecode")) == 0) {
                        Toast.makeText(application_context, "No Data Found..",
                                Toast.LENGTH_LONG).show();
                    }
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
                return false;
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;

    }

    public List<SubListOffice> getOfficeListbyYearWiseCollageId(
            String instituteId) {
        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId,ApplicationNo,OfficeId,id,AreaType,CarpetArea,Internet,AC,BackUp,CCTV,remarks,InsCarpetArea,"
                + "InsInternet,InsAC,InsBackUp,InsCCTV,Insremarks,proc_tracker from Office where YearWiseCollegeId = "
                + instituteId;
        List<SubListOffice> officeList = new ArrayList<SubListOffice>();
        ;
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    SubListOffice officeDetails = new SubListOffice();
                    officeDetails.setYearWiseCollegeId(cursor.getString(0));
                    officeDetails.setApplicationNo(cursor.getString(1));
                    officeDetails.setOfficeId(cursor.getString(2));
                    officeDetails.setId(cursor.getString(3));
                    officeDetails.setAreaType(cursor.getString(4));
                    officeDetails.setCarpetArea(cursor.getString(5));
                    officeDetails.setInternet(cursor.getString(6));
                    officeDetails.setAC(cursor.getString(7));
                    officeDetails.setBackUp(cursor.getString(8));
                    officeDetails.setCCTV(cursor.getString(9));
                    officeDetails.setRemarks(cursor.getString(10));
                    officeDetails.setInsCarpetArea(cursor.getString(11));
                    officeDetails.setInsInternet(cursor.getString(12));
                    officeDetails.setInsAC(cursor.getString(13));
                    officeDetails.setInsBackUp(cursor.getString(14));
                    officeDetails.setInsCCTV(cursor.getString(15));
                    officeDetails.setInsremarks(cursor.getString(16));
                    officeDetails.setProc_tracker(cursor.getInt(17));

                    officeList.add(officeDetails);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG + "---" + "getOfficeList", e.getMessage());
            return null;
        }
        return officeList;
    }

    public SubListOffice getOfficeDataByOfficeId(String yearWiseCollegeId,
                                                 String officeId) {
        SQLiteDatabase db = null;
        SubListOffice officeDetails = new SubListOffice();
        String selectQueryQues = "select YearWiseCollegeId,ApplicationNo,OfficeId,id,AreaType,CarpetArea,Internet,AC,BackUp,CCTV,remarks,InsCarpetArea,"
                + "InsInternet,InsAC,InsBackUp,InsCCTV,Insremarks,proc_tracker from Office "
                + "where YearWiseCollegeId = "
                + yearWiseCollegeId
                + " and OfficeId=" + officeId;
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    officeDetails.setYearWiseCollegeId(cursor.getString(0));
                    officeDetails.setApplicationNo(cursor.getString(1));
                    officeDetails.setOfficeId(cursor.getString(2));
                    officeDetails.setId(cursor.getString(3));
                    officeDetails.setAreaType(cursor.getString(4));
                    officeDetails.setCarpetArea(cursor.getString(5));
                    officeDetails.setInternet(cursor.getString(6));
                    officeDetails.setAC(cursor.getString(7));
                    officeDetails.setBackUp(cursor.getString(8));
                    officeDetails.setCCTV(cursor.getString(9));
                    officeDetails.setRemarks(cursor.getString(10));
                    officeDetails.setInsCarpetArea(cursor.getString(11));
                    officeDetails.setInsInternet(cursor.getString(12));
                    officeDetails.setInsAC(cursor.getString(13));
                    officeDetails.setInsBackUp(cursor.getString(14));
                    officeDetails.setInsCCTV(cursor.getString(15));
                    officeDetails.setInsremarks(cursor.getString(16));
                    officeDetails.setProc_tracker(cursor.getInt(17));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG + "---" + "getOfficeList", e.getMessage());
            return null;
        }
        return officeDetails;
    }

    public boolean saveOfficeData(SubListOffice rowValue, String mode) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        try {
            if (mode.equalsIgnoreCase("draft")) {
                values.put("InsCarpetArea", rowValue.getInsCarpetArea());
                values.put(
                        "InsInternet", rowValue.getInsInternet());
                values.put("InsAC", rowValue.getInsAC());
                values.put("InsBackUp", rowValue.getInsBackUp());
                values.put("InsCCTV", rowValue.getInsCCTV());
                values.put("Insremarks", rowValue.getInsremarks());
                values.put("proc_tracker", 2);
                database.update("Office", values, " YearWiseCollegeId= "
                        + rowValue.getYearWiseCollegeId() + " and OfficeId="
                        + rowValue.getOfficeId(), null);
            } else {
                values.put("InsCarpetArea", rowValue.getInsCarpetArea());
                values.put(
                        "InsInternet", rowValue.getInsInternet());
                values.put("InsAC", rowValue.getInsAC());
                values.put("InsBackUp", rowValue.getInsBackUp());
                values.put("InsCCTV", rowValue.getInsCCTV());
                values.put("Insremarks", rowValue.getInsremarks());
                values.put("proc_tracker", 3);
                database.update("Office", values, " YearWiseCollegeId= "
                        + rowValue.getYearWiseCollegeId() + " and OfficeId="
                        + rowValue.getOfficeId(), null);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            database.close();
        }
        return true;
    }

    public boolean addEquipmentData(JSONObject jsonobj,
                                    String yearWiseCollegeId, String applicationId) {
        SQLiteDatabase database = getWritableDatabase();
        if (jsonobj != null) {
            try {
                if (jsonobj.has("responsecode")) {
                    if (Integer.parseInt(jsonobj.getString("responsecode")) == 2
                            && jsonobj.has("rowcount")) {
                        for (int i = 0; i < jsonobj.getInt("rowcount"); i++) {

                            if (jsonobj.has(String.valueOf(i))) {
                                ContentValues values = new ContentValues();
                                JSONObject rowValue = new JSONObject(
                                        jsonobj.getString(String.valueOf(i)));
                                values.put("Job_Id",
                                        rowValue.getString("Job_Id"));
                                values.put("ApplicationNo", applicationId);
                                values.put("YearWiseCollegeId",
                                        yearWiseCollegeId);
                                values.put("Job_Name",
                                        rowValue.getString("Job_Name"));
                                values.put("Equipment_Name",
                                        rowValue.getString("Equipment_Name"));
                                values.put("TotalNo",
                                        rowValue.getString("TotalNo"));
                                values.put("Remarks",
                                        rowValue.getString("Remarks"));
                                values.put("Equipment_Id",
                                        rowValue.getString("Equipment_Id"));
                                values.put(
                                        "InsTotalNo",
                                        (rowValue.getString("InsTotalNo") != "null") ? rowValue
                                                .getString("InsTotalNo") : null);
                                values.put(
                                        "InsRemarks",
                                        (rowValue.getString("InsRemarks") != "null") ? rowValue
                                                .getString("InsRemarks") : null);

                                database.insert("Equipment", null, values);
                            }
                        }
                    } else if (Integer.parseInt(jsonobj
                            .getString("responsecode")) == 0) {
                        Toast.makeText(application_context, "No Data Found..",
                                Toast.LENGTH_LONG).show();
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return false;
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;

    }


    public boolean addJobRolesData(JSONObject jsonobj,
                                    String yearWiseCollegeId, String applicationId) {


        SQLiteDatabase database = getWritableDatabase();
        if (jsonobj != null) {
            try {
                if (jsonobj.has("responsecode")) {
                    if (Integer.parseInt(jsonobj.getString("responsecode")) == 2
                            && jsonobj.has("rowcount")) {
                        for (int i = 0; i < jsonobj.getInt("rowcount"); i++) {

                            if (jsonobj.has(String.valueOf(i))) {
                                ContentValues values = new ContentValues();
                                JSONObject rowValue = new JSONObject(
                                        jsonobj.getString(String.valueOf(i)));
                                values.put("JobID",
                                        rowValue.getString("ID"));
                                values.put("ApplicationNo", applicationId);
                                values.put("YearWiseCollegeId",
                                        yearWiseCollegeId);
                                values.put("JobName",
                                        rowValue.getString("Jobname"));
                                values.put("HandbookAvailable",
                                        rowValue.getString("Handbook_Available"));
                                values.put("Trainees",
                                        rowValue.getString("Trainees"));
                                values.put("Batch",
                                        rowValue.getInt("batch"));
                                values.put(
                                        "Remark",
                                        (rowValue.getString("remark") != "null") ? rowValue
                                                .getString("remark") : null);
                                values.put(
                                        "InsHandbookAvailable",
                                        (rowValue.getString("InsHandbook_Available") != "null") ? rowValue
                                                .getString("InsHandbook_Available") : null);
                                values.put(
                                        "InsTrainees",
                                        (rowValue.getString("InsTrainees") != "null") ? rowValue
                                                .getString("InsTrainees") : null);
                                values.put(
                                        "Insbatch",
                                        (rowValue.getString("Insbatch") != "null") ? rowValue
                                                .getString("Insbatch") : null);
                                values.put(
                                        "Insremark",
                                        (rowValue.getString("Insremark") != "null") ? rowValue
                                                .getString("Insremark") : null);
                                values.put("proc_tracker", 1);
                                database.insert("Jobroles", null, values);
                            }
                        }
                    } else if (Integer.parseInt(jsonobj
                            .getString("responsecode")) == 0) {
                        Toast.makeText(application_context, "No Data Found..",
                                Toast.LENGTH_LONG).show();
                    }
                }
            } catch (NumberFormatException e) {
                Log.e("no format error in job roles",e.toString());
                e.printStackTrace();
                return false;
            } catch (JSONException e) {
                Log.e("json exe  error in job roles",e.toString());
                e.printStackTrace();
                return false;
            }
        }
        return true;

    }


    public List<SubListEquipment> getEquipmentListbyYearWiseCollageId(String instituteId,String job_name) {
        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId,ApplicationNo,Job_Id,id,Job_Name,Equipment_Name,TotalNo,"
                + "Remarks,InsTotalNo,InsRemarks,proc_tracker from Equipment where YearWiseCollegeId = "
                + instituteId;
        if(job_name!=null)
            selectQueryQues+=" and Job_Name = "+job_name;

        List<SubListEquipment> objList = new ArrayList<SubListEquipment>();
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    SubListEquipment obj = new SubListEquipment();
                    obj.setYearWiseCollegeId(cursor.getString(0));
                    obj.setApplicationNo(cursor.getString(1));
                    obj.setJob_Id(cursor.getString(2));
                    obj.setId(cursor.getString(3));
                    obj.setJob_Name(cursor.getString(4));
                    obj.setEquipment_Name(cursor.getString(5));
                    obj.setTotalNo(cursor.getString(6));
                    obj.setRemarks(cursor.getString(7));
                    obj.setInsTotalNo(cursor.getString(8));
                    obj.setInsRemarks(cursor.getString(9));
                    obj.setProc_tracker(cursor.getInt(10));
                    objList.add(obj);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG , e.getMessage());
            return null;
        }
        return objList;
    }

    public SubListEquipment getEquipmentDataByLabId(String yearWiseCollegeId,
                                                    String classId) {
        SQLiteDatabase db = null;
        SubListEquipment obj = new SubListEquipment();
        String selectQueryQues = "select YearWiseCollegeId,ApplicationNo,Job_Id,id,Job_Name,Equipment_Name,TotalNo,"
                + "Remarks,InsTotalNo,InsRemarks,proc_tracker from Equipment where YearWiseCollegeId = "
                + yearWiseCollegeId + " and Job_Id=" + classId;
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                do {
                    obj.setYearWiseCollegeId(cursor.getString(0));
                    obj.setApplicationNo(cursor.getString(1));
                    obj.setJob_Id(cursor.getString(2));
                    obj.setId(cursor.getString(3));
                    obj.setJob_Name(cursor.getString(4));
                    obj.setEquipment_Name(cursor.getString(5));
                    obj.setTotalNo(cursor.getString(6));
                    obj.setRemarks(cursor.getString(7));
                    obj.setInsTotalNo(cursor.getString(8));
                    obj.setInsRemarks(cursor.getString(9));
                    obj.setProc_tracker(cursor.getInt(10));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
        return obj;
    }

    public Proc_Track getSyncStatus(String category) {
        String classRoomQuery = "Select proc_tracker,count(*) from Classroom group by proc_tracker";
        String labQuery = "Select proc_tracker,count(*) from Lab group by proc_tracker";
        String officeQuery = "Select proc_tracker,count(*) from Office group by proc_tracker";
        String equipmentQuery = "Select proc_tracker,count(*) from Equipment group by proc_tracker";
        String jobRolesQuery = "Select proc_tracker,count(*) from Jobroles group by proc_tracker";
        String finalSql = "";
        Proc_Track obj = new Proc_Track();
        try {
            if (category.equalsIgnoreCase("class")) {
                finalSql = classRoomQuery;
            } else if (category.equalsIgnoreCase("lab")) {
                finalSql = labQuery;
            } else if (category.equalsIgnoreCase("office")) {
                finalSql = officeQuery;
            } else if (category.equalsIgnoreCase("equipment")) {
                finalSql = equipmentQuery;
            } else if (category.equalsIgnoreCase("Jobroles")) {
                finalSql = jobRolesQuery;
            }
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(finalSql, null);
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
            Log.e(TAG , e.getMessage());
            return null;
        }
        return obj;
    }

    public List<String> getEquipmentJobRoles(){
        SQLiteDatabase db = null;
        String selectQueryQues = "select distinct(Job_Name) from Equipment";
        List<String> jobList = new ArrayList<String>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    jobList.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
        return jobList;
    }

    public Proc_Track getSyncStatusforEquipmentFilter(String job_name) {
        String equipmentQuery = "Select proc_tracker,count(*) from Equipment group by proc_tracker where Job_Name = '"+job_name+"'";
        String finalSql = "";
        Proc_Track obj = new Proc_Track();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(equipmentQuery, null);
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
            Log.e(TAG, e.getMessage());
            return null;
        }
        return obj;
    }

    public boolean saveEquipmentData(SubListEquipment rowValue, String mode) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        try {
            if (mode.equalsIgnoreCase("draft")) {
                values.put("InsTotalNo", rowValue.getInsTotalNo());
                values.put("InsRemarks", rowValue.getInsRemarks());
                values.put("proc_tracker", 2);
                database.update("Equipment", values, " YearWiseCollegeId= "
                        + rowValue.getYearWiseCollegeId() + " and Equipment_Id="
                        + rowValue.getEquipment_Id(), null);
            } else {
                values.put("InsTotalNo", rowValue.getInsTotalNo());
                values.put("InsRemarks", rowValue.getInsRemarks());
                values.put("proc_tracker", 3);
                database.update("Equipment", values, " YearWiseCollegeId= "
                        + rowValue.getYearWiseCollegeId() + " and OfficeId="
                        + rowValue.getEquipment_Id(), null);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            database.close();
        }
        return true;
    }
}
