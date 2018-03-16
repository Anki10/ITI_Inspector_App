package com.ss.nsdc.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.ss.nsdc.entity.Accommodation;
import com.ss.nsdc.entity.Classroom;
import com.ss.nsdc.entity.GenralInfo;
import com.ss.nsdc.entity.ITLab;
import com.ss.nsdc.entity.InstructorInfo;
import com.ss.nsdc.entity.LandandBuilding;
import com.ss.nsdc.entity.MaterialInfo;
import com.ss.nsdc.entity.PowerSupplyITIModel;
import com.ss.nsdc.entity.Staffing;
import com.ss.nsdc.entity.Proc_Track;
import com.ss.nsdc.entity.TechincalInfo;
import com.ss.nsdc.entity.ToolsAbove;
import com.ss.nsdc.entity.TradeWiseTool;
import com.ss.nsdc.entity.EquipmentInfo;
import com.ss.nsdc.entity.Workshop;
import com.ss.nsdc.entity.WorkshopAreaModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ITIDBController extends SQLiteOpenHelper {
    Context application_context;
    public static final String TAG = "ITIDBController";
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
    ContentValues values = new ContentValues();
    public ITIDBController(Context context) {
        super(context, database_name, null, database_version);
        this.application_context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // PROC TRACKER >> 1- new Data , 2- draft(not synced), 3-completed
        String userTableQuery = "";
        String instituteTableQuery = "";
        String subGeneralInfoTableQuery = "";
        String trustGeneralInfoQuery = "";
        String organisationGeneralInfoQuery = "";
        String technicalInfoQuery = "";
        String instructorInfoQuery = "";
        String tradeInfoQuery = "";
        String ToolsAboveTenK = "";
        String labInfoQuery = "";
        String landandBuildingInfoQuery = "";
        String tradewiseToolQuery = "";
        String premisesInfoQuery = "";
        String classroomInfoQuery = "";
        String subUploadImageTableQuery = "";
        String subGeneralInfoImageQuery = "";
        String subLandAndBuildingInfoImageQuery = "";
        String subOrganisationDetailsInfoImageQuery = "";
        String subTechnicalInfoImageQuery = "";
        String subInstructorInfoImageQuery = "";
        String subitLabInfoImageQuery = "";
        String subpowersupplyInfoImageQuery = "";
        String classroomInfoImageQuery = "";
        String toolsaboveQuery = "";
        String workshopaboveQuery = "";



        /*
         * String questionTableQuery; String optionTableQuery; String
       * surveyAnswerTable;
       */
        userTableQuery = " CREATE TABLE user ( loginId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Userid INTEGER NOT NULL, Username TEXT NOT NULL,Password TEXT NOT NULL, status INTEGER, date TEXT NOT NULL )";
        instituteTableQuery = " CREATE TABLE allocation ( Id INTEGER PRIMARY KEY AUTOINCREMENT, yearWiseCollegeId TEXT NOT NULL, organisationName TEXT NOT NULL, headOrganisation TEXT, contactDetails TEXT, startedYear TEXT,proc_tracker INTEGER)";
        subGeneralInfoTableQuery = "CREATE TABLE GeneralInfo( Id INTEGER PRIMARY KEY AUTOINCREMENT,YearWiseCollegeId TEXT NOT NULL,typeInstitute TEXT,typeInstituteRemarks TEXT,typeInstituteNC TEXT,Date TEXT,DateRemarks TEXT,DateNC TEXT,instituteType TEXT,instituteTypeRemarks TEXT,instituteTypeNC TEXT,phoneNo TEXT,phoneNoRemarks TEXT,phoneNoNC TEXT,specialStatus TEXT,specialStatusRemarks TEXT,specialStatusNC TEXT, faxNo TEXT,faxNoRemarks TEXT,faxNoNC TEXT, instituteLocation TEXT,instituteLocationRemarks TEXT,instituteLocationNC TEXT, mobile TEXT,mobileRemarks TEXT,mobileNC TEXT, specialLocation TEXT,specialLocationRemarks TEXT,specialLocationNC TEXT, email TEXT,emailRemarks TEXT, emailNC TEXT, principalName TEXT,principalNameRemarks TEXT,principalNameNC TEXT, instituteRunning TEXT,instituteRunningRemarks TEXT,instituteRunningNC TEXT, address TEXT,addressRemarks TEXT,addressNC, landmark TEXT,landmarkRemarks TEXT,landmarkNC TEXT, district TEXT,districtRemarks TEXT, districtNC TEXT, state TEXT,stateRemarks TEXT, stateNC TEXT, tehsil TEXT,tehsilRemarks TEXT,tehsilNC TEXT, pincode TEXT,pincodeRemarks TEXT,pincodeNC TEXT, block TEXT,blockRemarks TEXT,blockNC TEXT, website TEXT,websiteRemarks TEXT,websiteNC TEXT,itiName TEXT,itiNameRemarks TEXT,itiNameNC TEXT,applicationno TEXT,applicationnoRemarks TEXT,applicationnoNC TEXT,signage TEXT,signageRemarks TEXT, signageNC TEXT,bharat TEXT,bharatRemarks TEXT,bharatNC,biometric TEXT,biometricRemarks TEXT,biometricNC TEXT,combined TEXT,combinedRemarks TEXT,combinedNC TEXT,demarcated TEXT,demarcatedRemarks TEXT,demarcatedNC TEXT,required TEXT,requiredRemarks TEXT,requiredNC TEXT,available TEXT,availableRemarks TEXT,availableNC TEXT,refId TEXT,proc_tracker INTEGER)";
        trustGeneralInfoQuery= "CREATE TABLE TrustGeneralInfo(Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "YearWiseCollegeId  TEXT NOT NULL, " +
                "instituteOwned TEXT, " +
                "instituteOwnedRemarks TEXT, " +
                "isRegistered TEXT, " +
                "isRegisteredRemarks TEXT, " +
                "isRegisteredNC TEXT, " +
                "registration TEXT, " +
                "registrationNo TEXT, " +
                "registrationNoRemarks TEXT, " +
                "registrationNoNC TEXT, " +
                "trustName TEXT, " +
                "trustNameRemarks TEXT, " +
                "trustNameNC TEXT, " +
                "registrationDate TEXT, " +
                "trustValidity TEXT, " +
                "trustValidityRemarks TEXT, " +
                "trustValidityNC TEXT, " +
                "panNumber TEXT, " +
                "panNumberRemarks TEXT, " +
                "panNumberNC TEXT, " +
                "experience TEXT, " +
                "experienceRemarks TEXT, " +
                "experienceNC TEXT, " +
                "educationalType TEXT, " +
                "educationalTypeRemarks TEXT, " +
                "educationalTypeNC TEXT, " +
                "remarks TEXT, " +
                "commonNC TEXT, " +
                "refId TEXT," +
                "proc_tracker INTEGER)";
        organisationGeneralInfoQuery= "CREATE TABLE OrganisationGeneralInfo(Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "yearwisecollegeid  TEXT NOT NULL, " +
                "Organization_Name TEXT, " +
                "Organization_NameRemarks TEXT, " +
                "Organization_NameNC TEXT, " +
                "Authorized_Person TEXT, " +
                "Authorized_PersonRemarks TEXT, " +
                "Authorized_PersonNC TEXT, " +
                "Org_Mobile_no TEXT, " +
                "Org_Mobile_noRemarks TEXT, " +
                "Org_Mobile_noNC TEXT, " +
                "Org_Email TEXT, " +
                "Org_EmailRemarks TEXT, " +
                "Org_EmailNC TEXT, " +
                "Org_website TEXT, " +
                "Org_websiteRemarks TEXT, " +
                "Org_websiteNC TEXT, " +
                "Org_Telephone_no TEXT, " +
                "Org_Telephone_noRemarks TEXT, " +
                "Org_Telephone_noNC TEXT, " +
                "Org_Postal_Address TEXT, " +
                "Org_Postal_AddressRemarks TEXT, " +
                "Org_Postal_AddressNC TEXT, " +
                "StateName TEXT, " +
                "StateNameRemarks TEXT, " +
                "StateNameNC TEXT, " +
                "DistrictName TEXT, " +
                "DistrictNameRemarks TEXT, " +
                "DistrictNameNC TEXT, " +
                "MandalName TEXT, " +
                "MandalNameRemarks TEXT, " +
                "MandalNameNC TEXT, " +
                "Org_Pincode TEXT, " +
                "Org_PincodeRemarks TEXT, " +
                "Org_PincodeNC TEXT, " +
                "Org_LandMark TEXT, " +
                "Org_LandMarkRemarks TEXT, " +
                "Org_LandMarkNC TEXT, " +
                "Authorized_UID TEXT, " +
                "Authorized_UIDRemarks TEXT, " +
                "Authorized_UIDNC TEXT, " +
                "NCVT TEXT,"+
                "NCVTRemarks TEXT,"+
                "NCVTNC TEXT,"+
                "referenceNumber TEXT,"+
                "referenceNumberRemarks TEXT,"+
                "referenceNumberNC TEXT,"+
                "affilationNumber TEXT,"+
                "affilationNumberRemarks TEXT,"+
                "affilationNumberNC TEXT,"+
                "misCode TEXT," +
                "misCodeRemarks TEXT," +
                "misCodeNC TEXT," +
                "refId TEXT," +
                "proc_tracker INTEGER)";

        technicalInfoQuery= "CREATE TABLE TechnicalInfo(Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "yearwisecollegeid  TEXT NOT NULL, " +
                "designation TEXT, " +
                "designationRemarks TEXT, " +
                "designationNC TEXT, " +
                "name TEXT, " +
                "nameRemarks TEXT, " +
                "nameNC TEXT, " +
                "fatherName TEXT, " +
                "fatherNameRemarks TEXT, " +
                "fatherNameNC TEXT, " +
                "dob TEXT, " +
                "dobRemarks TEXT, " +
                "dobNC TEXT, " +
                "joinDate TEXT, " +
                "joinDateRemarks TEXT, " +
                "joinDateNC TEXT, " +
                "qualification TEXT, " +
                "qualificationRemarks TEXT, " +
                "qualificationNC TEXT, " +
                "stream TEXT, " +
                "streamRemarks TEXT, " +
                "streamNC TEXT, " +
                "passingYear TEXT, " +
                "passingYearRemarks TEXT, " +
                "passingYearNC TEXT, " +
                "totalYoe TEXT, " +
                "totalYoeRemarks TEXT, " +
                "totalYoeNC TEXT, " +
                "account TEXT, " +
                "accountRemarks TEXT, " +
                "accountNC TEXT, " +
                "bankName TEXT, " +
                "bankNameRemarks TEXT, " +
                "bankNameNC TEXT, " +
                "branchName TEXT, " +
                "branchNameRemarks TEXT, " +
                "branchNameNC TEXT, " +
                "adharNo TEXT, " +
                "adharNoRemarks TEXT, " +
                "adharNoNC TEXT, " +
                "salary TEXT, " +
                "salaryRemarks TEXT, " +
                "salaryNC TEXT, " +
                "panCard TEXT, " +
                "panCardRemarks TEXT, " +
                "panCardNC TEXT, " +
                "remarks TEXT, " +
                "commonNc TEXT,"+
                "remarks_Remarks TEXT," +
                "remarks_Nc TEXT,"+
                "refId TEXT, "+
                "proc_tracker INTEGER)";

        instructorInfoQuery= "CREATE TABLE InstructorITIInfo(Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "yearwisecollegeid  TEXT NOT NULL, " +
                "tradename TEXT, " +
                "tradenameRemarks TEXT, " +
                "instructor TEXT, "+
                "instructorRemarks TEXT, "+
                "instructorNC TEXT, "+
                "name TEXT, " +
                "nameRemarks TEXT, " +
                "nameNC TEXT, " +
                "fatherName TEXT, " +
                "fatherNameRemarks TEXT, " +
                "fatherNameNC TEXT, " +
                "dateofbirth TEXT, " +
                "dateofbirthRemarks TEXT, " +
                "dateofbirthNC TEXT, " +
                "joiningdate TEXT, " +
                "joiningdateRemarks TEXT, " +
                "joiningdateNC TEXT, " +
                "qualification TEXT, " +
                "qualificationRemarks TEXT, " +
                "qualificationNC TEXT, " +
                "passingyear TEXT, " +
                "passingyearRemarks TEXT, " +
                "passingyearNC TEXT, " +
                "stream TEXT, " +
                "streamRemarks TEXT, " +
                "streamNC TEXT, " +
                "totalexp TEXT, " +
                "totalexpRemarks TEXT, " +
                "totalexpNC TEXT, " +
                "bankname TEXT, " +
                "banknameRemarks TEXT, " +
                "banknameNC TEXT, " +
                "branchname TEXT, " +
                "branchnameRemarks TEXT, " +
                "branchnameNC TEXT, " +
                "accountno TEXT, " +
                "accountnoRemarks TEXT, " +
                "accountnoNC TEXT, " +
                "aadharno TEXT, " +
                "aadharnoRemarks TEXT, " +
                "aadharnoNC TEXT, " +
                "salary TEXT,"+
                "salaryRemarks TEXT,"+
                "salaryNC TEXT,"+
                "remarks TEXT,"+
                "remarksRemarks TEXT,"+
                "remarksNC TEXT,"+
                "commonNC TEXT,"+
                "refId TEXT, "+
                "tradeId TEXT,"+
                "proc_tracker INTEGER)";
        tradeInfoQuery= "CREATE TABLE TradeInfo(Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "YearWiseCollegeId  TEXT NOT NULL, " +
                "tradeid TEXT, " +
                "nameofTrade TEXT, "+
                "name TEXT, " +
                "shift1 TEXT, " +
                "shift2 TEXT, " +
                "shift3 TEXT, " +
                "total TEXT, " +
                "proc_tracker INTEGER)";

        String tradeWiseInfoQuery = "";

        tradeWiseInfoQuery= "CREATE TABLE TradeWiseInfo(Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "YearWiseCollegeId  TEXT NOT NULL, " +
                "tradeid TEXT, " +
                "nameofTrade TEXT, "+
                "name TEXT, " +
                "shift1 TEXT, " +
                "shift2 TEXT, " +
                "shift3 TEXT, " +
                "total TEXT, " +
                "proc_tracker INTEGER)";

        ToolsAboveTenK= "CREATE TABLE ToolsAboveTenK(Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "YearWiseCollegeId  TEXT NOT NULL, " +
                "tradeid TEXT, " +
                "nameofTrade TEXT, "+
                "name TEXT, " +
                "shift1 TEXT, " +
                "shift2 TEXT, " +
                "shift3 TEXT, " +
                "total TEXT, " +
                "proc_tracker INTEGER)";

        labInfoQuery= "CREATE TABLE LabInfo(Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "yearWiseCollegeid  TEXT NOT NULL, " +
                "nameofequipment TEXT, " +
                "category TEXT, "+
                "quantity TEXT, " +
                "refId TEXT, " +
                "available TEXT, " +
                "Area TEXT, " +
                "AreaRemarks TEXT, " +
                "AreaNc TEXT, " +
                "Lab TEXT,"+
                "LabRemarks TEXT,"+
                "LabNC TEXT," +
                "Internet TEXT,"+
                "InternetRemarks TEXT,"+
                "InternetNC TEXT,"+
                "Roof TEXT,"+
                "RoofRemarks TEXT,"+
                "RoofNC TEXT,"+
                "Height TEXT,"+
                "HeightRemarks TEXT,"+
                "HeightNC TEXT,"+
                "Tin TEXT,"+
                "TinRemarks TEXT,"+
                "TinNC TEXT,"+
                "Floor TEXT,"+
                "FloorRemarks TEXT,"+
                "FloorNC TEXT,"+
                "proc_tracker INTEGER)";

        landandBuildingInfoQuery= "CREATE TABLE LandInfo(Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "yearwisecollegeid  TEXT NOT NULL, " +
                "buildingType TEXT, " +
                "buildingTypeRemarks TEXT, " +
                "buildingTypeNC TEXT, " +
                "dateofOccupancy TEXT, " +
                "dateofOccupancyRemarks TEXT, " +
                "dateofOccupancyNC TEXT, " +
                "openArea TEXT, " +
                "openAreaRemarks TEXT, " +
                "openAreaNC TEXT, " +
                "buildArea TEXT, " +
                "buildAreaRemarks TEXT, " +
                "buildAreaNC TEXT, " +
                "land TEXT, " +
                "landRemarks TEXT, " +
                "landNC TEXT, " +
                "liftInstitute TEXT, " +
                "liftInstituteRemarks TEXT, " +
                "liftInstituteNC TEXT, " +
                "noFloor TEXT, " +
                "noFloorRemarks TEXT, " +
                "noFloorNC TEXT, " +
                "orgInstalledLift TEXT, " +
                "orgInstalledLiftRemarks TEXT, " +
                "orgInstalledLiftNC TEXT, " +
                "capacityofLift TEXT, " +
                "capacityofLiftRemarks TEXT, " +
                "capacityofLiftNC TEXT, " +
                "safetyLift TEXT, " +
                "safetyLiftRemarks TEXT, " +
                "safetyLiftNC TEXT, " +
                "workshopRoof TEXT, " +
                "workshopRoofRemarks TEXT, " +
                "workshopRoofNC TEXT, " +
                "buildingPlan TEXT, " +
                "buildingPlanRemarks TEXT, " +
                "buildingPlanNC TEXT, " +
                "wallOfIti TEXT,"+
                "wallOfItiRemarks TEXT,"+
                "wallOfItiNC TEXT,"+
                "floorisCemented TEXT,"+
                "floorisCementedRemarks TEXT,"+
                "floorisCementedNC TEXT,"+
                "sameCampus TEXT,"+
                "sameCampusRemarks TEXT,"+
                "sameCampusNC TEXT,"+
                "seperateWashroom TEXT,"+
                "seperateWashroomRemarks TEXT,"+
                "seperateWashroomNC TEXT,"+
                "washroomFunctional TEXT,"+
                "washroomFunctionalRemarks TEXT,"+
                "washroomFunctionalNC TEXT,"+
                "fire TEXT,"+
                "fireRemarks TEXT,"+
                "fireNC TEXT,"+
                "durationlease TEXT,"+
                "durationleaseRemarks TEXT,"+
                "durationleaseNC TEXT,"+
                "expiryofAgreement TEXT,"+
                "expiryofAgreementRemarks TEXT,"+
                "expiryofAgreementNC TEXT,"+
                "safeWater  TEXT,"+
                "safeWaterRemarks  TEXT,"+
                "safeWaterNC  TEXT,"+
                "houseKeeping  TEXT,"+
                "houseKeepingRemarks  TEXT,"+
                "houseKeepingNC  TEXT,"+
                "placementCell  TEXT,"+
                "placementCellRemarks  TEXT,"+
                "placementCellNC  TEXT,"+
                "masterPlan TEXT,"+
                "masterPlanRemarks TEXT,"+
                "masterPlanNC TEXT, "+
                "comptent TEXT,"+
                "comptentRemarks TEXT," +
                "comptentNC TEXT,"+
                "sitmap TEXT,"+
                "sitmapRemarks TEXT," +
                "sitmapNC TEXT,"+
                "dimension TEXT,"+
                "dimensionRemarks TEXT," +
                "dimensionNC TEXT,"+
                "map TEXT,"+
                "mapRemarks TEXT," +
                "mapNC TEXT,"+
                "approachRoad TEXT,"+
                "approachRoadRemarks TEXT," +
                "approachRoadNC TEXT,"+
                "entranceRoad TEXT,"+
                "entranceRoadRemarks TEXT," +
                "entranceRoadNC TEXT,"+
                "soundProof TEXT,"+
                "soundProofRemarks TEXT," +
                "soundProofNC TEXT,"+
                "shared TEXT,"+
                "sharedRemarks TEXT," +
                "sharedNC TEXT,"+
                "seperateEntrance TEXT,"+
                "seperateEntranceRemarks TEXT," +
                "seperateEntranceNC TEXT,"+
                "switchBoard TEXT,"+
                "switchBoardRemarks TEXT," +
                "switchBoardNC TEXT,"+
                "ventilated TEXT,"+
                "ventilatedRemarks TEXT," +
                "ventilatedNC TEXT,"+
                "plotSize TEXT,"+
                "plotSizeRemarks TEXT," +
                "plotSizeNC TEXT,"+
                "BCC TEXT,"+
                "BCCRemarks TEXT," +
                "BCCNC TEXT,"+
                "staircase TEXT,"+
                "staircaseRemarks TEXT," +
                "staircaseNC TEXT,"+
                "refId TEXT,"+
                "proc_tracker INTEGER)";

        tradewiseToolQuery= "CREATE TABLE TradeWiseToolInfo(Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "yearWiseCollegeId  TEXT NOT NULL, " +
                "tradeName TEXT, " +
                "tradeId TEXT, " +
                "refId TEXT, " +
                "equipmentName TEXT, " +
                "equipmentId TEXT, " +
                "reqUnit TEXT, " +
                "markTools TEXT, " +
                "requnitRemarks TEXT, " +
                "reqNC TEXT, " +
                "qty TEXT, " +
                "qtyRemarks TEXT, " +
                "qtyNc TEXT, " +
                "availableUnit TEXT,"+
                "proc_tracker INTEGER)";

        String ToolQuery = "";

        ToolQuery= "CREATE TABLE ToolsAboveInfo(Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "yearWiseCollegeId  TEXT NOT NULL, " +
                "tradeId TEXT, " +
                "refId TEXT, " +
                "equipmentName TEXT, " +
                "equipmentId TEXT, " +
                "isGroutingandEngravingRequired TEXT,"+
                "isGroutingandengravingAvailable TEXT,"+
                "isEngravingRequired TEXT,"+
                "isGroutingRequired TEXT,"+
                "proc_tracker INTEGER)";

        premisesInfoQuery= "CREATE TABLE PremisesShiftingInfo(Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "yearWiseCollegeId  TEXT NOT NULL, " +
                "change TEXT," +
                "changeRemarks TEXT," +
                "changeNC TEXT," +
                "toPostalAddress TEXT, " +
                "toPostalAddressRemarks TEXT, " +
                "toPostalAddressNC TEXT, " +
                "tolandmark TEXT, " +
                "tolandmarkRemarks TEXT, " +
                "tolandmarkNC TEXT, " +
                "toState TEXT, " +
                "toStateRemarks TEXT, " +
                "toStateNC TEXT, " +
                "toDistrict TEXT, " +
                "toDistrictRemarks TEXT, " +
                "toDistrictNC TEXT, " +
                "toTehsil TEXT, " +
                "toTehsilRemarks TEXT, " +
                "toTehsilNC TEXT, " +
                "toBlock TEXT, " +
                "toBlockRemarks TEXT, " +
                "toBlockNC TEXT, " +
                "toPincode TEXT, " +
                "toPincodeRemarks TEXT, " +
                "toPincodeNC TEXT, " +
                "totelno TEXT, " +
                "totelnoRemarks TEXT, " +
                "totelnoNC TEXT, " +
                "toFaxno TEXT, " +
                "toFaxnoRemarks TEXT, " +
                "toFaxnoNC TEXT, " +
                "remarks TEXT," +
                "refId TEXT,"+
                "proc_tracker INTEGER)";

        classroomInfoQuery= "CREATE TABLE ClassroomInfo(Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "yearwisecollegeid  TEXT NOT NULL, " +
                "classroomName TEXT, " +
                "classroomNameRemarks TEXT, " +
                "classroomNameNC TEXT, " +
                "classroomArea TEXT, " +
                "classroomAreaRemarks TEXT, " +
                "classroomAreaNC TEXT, " +
                "available TEXT,"+
                "refId TEXT,"+
                "width TEXT,"+
                "widthRemarks TEXT,"+
                "widthNC TEXT,"+
                "flag TEXT,"+
                "tradeId TEXT,"+
                "ceiling TEXT,"+
                "ceilingRemarks TEXT,"+
                "ceilingNC TEXT,"+
                "height TEXT,"+
                "heightRemarks TEXT,"+
                "heightNC TEXT,"+
                "wall TEXT,"+
                "wallRemarks TEXT,"+
                "wallNC TEXT,"+
                "proc_tracker INTEGER)";

        subUploadImageTableQuery = "CREATE TABLE UploadImages (Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "YearWiseCollegeId  TEXT NOT NULL, " +
                "IsImageDownloaded INTEGER," +
                "proc_tracker INTEGER)";

        subGeneralInfoImageQuery = "CREATE TABLE GeneralInfoImage (Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "YearWiseCollegeId  TEXT NOT NULL, " +
                "docId TEXT," +
                "section TEXT," +
                "refId TEXT," +
                "pictureType TEXT," +
                "helpText TEXT," +
                "remarks TEXT," +
                "latitude TEXT," +
                "longitude TEXT," +
                "name TEXT," +
                "photo TEXT," +
                "nc TEXT," +
                "proc_tracker INTEGER)";

        subLandAndBuildingInfoImageQuery = "CREATE TABLE LandAndBuildingInfoImage (Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "YearWiseCollegeId  TEXT NOT NULL, " +
                "docId TEXT," +
                "section TEXT," +
                "refId TEXT," +
                "pictureType TEXT," +
                "helpText TEXT," +
                "remarks TEXT," +
                "latitude TEXT," +
                "longitude TEXT," +
                "name TEXT," +
                "photo TEXT," +
                "nc TEXT," +
                "proc_tracker INTEGER)";

        subOrganisationDetailsInfoImageQuery = "CREATE TABLE OrganisationDetailsInfoImage (Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "YearWiseCollegeId  TEXT NOT NULL, " +
                "docId TEXT," +
                "section TEXT," +
                "refId TEXT," +
                "pictureType TEXT," +
                "helpText TEXT," +
                "remarks TEXT," +
                "latitude TEXT," +
                "longitude TEXT," +
                "name TEXT," +
                "photo TEXT," +
                "nc TEXT," +
                "proc_tracker INTEGER)";

        subTechnicalInfoImageQuery = "CREATE TABLE TechnicalInfoImage (Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "YearWiseCollegeId  TEXT NOT NULL, " +
                "docId TEXT," +
                "section TEXT," +
                "refId TEXT," +
                "pictureType TEXT," +
                "helpText TEXT," +
                "remarks TEXT," +
                "latitude TEXT," +
                "longitude TEXT," +
                "name TEXT," +
                "photo TEXT," +
                "nc TEXT," +
                "proc_tracker INTEGER)";

        subInstructorInfoImageQuery = "CREATE TABLE InstructorInfoImage (Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "YearWiseCollegeId  TEXT NOT NULL, " +
                "docId TEXT," +
                "section TEXT," +
                "refId TEXT," +
                "pictureType TEXT," +
                "helpText TEXT," +
                "remarks TEXT," +
                "latitude TEXT," +
                "longitude TEXT," +
                "name TEXT," +
                "photo TEXT," +
                "nc TEXT," +
                "proc_tracker INTEGER)";
        subitLabInfoImageQuery = "CREATE TABLE ITLabInfoImage (Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "YearWiseCollegeId  TEXT NOT NULL, " +
                "docId TEXT," +
                "section TEXT," +
                "refId TEXT," +
                "pictureType TEXT," +
                "helpText TEXT," +
                "remarks TEXT," +
                "latitude TEXT," +
                "longitude TEXT," +
                "name TEXT," +
                "photo TEXT," +
                "nc TEXT," +
                "proc_tracker INTEGER)";
        subpowersupplyInfoImageQuery = "CREATE TABLE PowerSupplyInfoImage (Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "YearWiseCollegeId  TEXT NOT NULL, " +
                "docId TEXT," +
                "section TEXT," +
                "refId TEXT," +
                "pictureType TEXT," +
                "helpText TEXT," +
                "remarks TEXT," +
                "latitude TEXT," +
                "longitude TEXT," +
                "name TEXT," +
                "photo TEXT," +
                "nc TEXT," +
                "proc_tracker INTEGER)";

        classroomInfoImageQuery = "CREATE TABLE ClassroomInfoImage (Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "YearWiseCollegeId  TEXT NOT NULL, " +
                "docId TEXT," +
                "section TEXT," +
                "refId TEXT," +
                "pictureType TEXT," +
                "helpText TEXT," +
                "remarks TEXT," +
                "latitude TEXT," +
                "longitude TEXT," +
                "name TEXT," +
                "photo TEXT," +
                "nc TEXT," +
                "flag TEXT,"+
                "tradeId TEXT,"+
                "proc_tracker INTEGER)";


        toolsaboveQuery = "CREATE TABLE ToolsAboveInfoImage (Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "YearWiseCollegeId  TEXT NOT NULL, " +
                "docId TEXT," +
                "section TEXT," +
                "refId TEXT," +
                "pictureType TEXT," +
                "helpText TEXT," +
                "remarks TEXT," +
                "latitude TEXT," +
                "longitude TEXT," +
                "name TEXT," +
                "photo TEXT," +
                "nc TEXT," +
                "proc_tracker INTEGER)";

       workshopaboveQuery = "CREATE TABLE WorkshopInfoImage (Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "YearWiseCollegeId  TEXT NOT NULL, " +
                "docId TEXT," +
                "section TEXT," +
                "refId TEXT," +
                "pictureType TEXT," +
                "helpText TEXT," +
                "remarks TEXT," +
                "latitude TEXT," +
                "longitude TEXT," +
                "name TEXT," +
                "photo TEXT," +
                "nc TEXT," +
                "flag TEXT," +
                "proc_tracker INTEGER)";

        String powerSupplyITITableQuery = "";
        String workshopAreaQuery = "";

        powerSupplyITITableQuery = "CREATE TABLE PowerSupplyITI( Id INTEGER PRIMARY KEY AUTOINCREMENT,YearWiseCollegeId TEXT NOT NULL,isConnectionNameIti TEXT,connectionIssued TEXT,dateConnection TEXT,PowerSupplyNeededAsPer TEXT,proc_tracker INTEGER)";
        workshopAreaQuery = "CREATE TABLE WorkshopArea (Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "YearWiseCollegeId TEXT NOT NULL, " +
                "tradeId TEXT," +
                "tradeName TEXT," +
                "shiftsUnit TEXT," +
                "shiftsunitRemarks TEXT," +
                "shiftsNc TEXT," +
                "ncvtNorms TEXT," +
                "ncvtnormsRemarks TEXT," +
                "ncvtNc TEXT," +
                "actualArea TEXT," +
                "actualareaRemarks TEXT," +
                "actualareaNc TEXT," +
                "shortageArea TEXT," +
                "shortageareaRemarks TEXT," +
                "shortageAreaNc TEXT," +
                "refId TEXT," +
                "istheWorkshopRectangular TEXT," +
                "istheWorkshopRectangularRemarks TEXT," +
                "istheWorkshopRectangularNc TEXT," +
                "widthOftheWorkshop TEXT," +
                "widthOftheWorkshopRemarks TEXT," +
                "widthOftheWorkshopNc TEXT," +
                "aretheWorkshopWallsofTin TEXT," +
                "aretheWorkshopWallsofTinRemarks TEXT," +
                "aretheWorkshopWallsofTinNc TEXT," +
                "istheheavyMachineryLocated TEXT," +
                "istheheavyMachineryLocatedRemarks TEXT," +
                "istheheavyMachineryLocatedNc TEXT," +
                "itihaveCombinedWorkshop TEXT," +
                "itihaveCombinedWorkshopRemarks TEXT," +
                "itihaveCombinedWorkshopNc TEXT," +
                "istheDemarcated TEXT," +
                "istheDemarcatedRemarks TEXT," +
                "istheDemarcatedNc TEXT," +
                "emergencyContactNumberDisplay TEXT," +
                "emergencyContactNumberDisplayRemarks TEXT," +
                "emergencyContactNumberDisplayNc TEXT," +
                "WorkShopRoof TEXT," +
                "WorkShopRoof_Remarks TEXT," +
                "WorkShopRoof_Nc TEXT," +
                "ceilingHeightlessthan12Feet TEXT," +
                "ceilingHeightlessthan12Feet_Remarks TEXT," +
                "ceilingHeightlessthan12Feet_Nc TEXT," +
                "ceilingHeightlessthan10Feet TEXT," +
                "ceilingHeightlessthan10Feet_Remarks TEXT," +
                "isWiresandBoardsCovered  TEXT," +
                "isFireExtinguisherAvailable  TEXT," +
                "isEmergencyExit  TEXT," +
                "isWiresandBoardsCoveredRemarks  TEXT," +
                "isFireExtinguisherAvailableRemarks  TEXT," +
                "isEmergencyExitRemarks  TEXT," +
                "isWiresandBoardsCoveredNc  TEXT," +
                "isFireExtinguisherAvailableNc  TEXT," +
                "workshopName  TEXT," +
                "machinaryTools TEXT," +
                "machinaryToolsRemarks TEXT,"+
                "machinaryToolsNC TEXT,"+
                "machineComplying TEXT," +
                "machineComplyingRemarks TEXT,"+
                "machineComplyingNC TEXT,"+
                "rubberMat TEXT," +
                "rubberMatRemarks TEXT,"+
                "rubberMatNC TEXT,"+
                "dgRequired TEXT," +
                "dgRequiredRemarks TEXT,"+
                "dgRequiredNC TEXT,"+
                "dgInstalled TEXT," +
                "dgInstalledRemarks TEXT,"+
                "dgInstalledNC TEXT,"+
                "latheRequired TEXT," +
                "latheRequiredRemarks TEXT,"+
                "latheRequiredNC TEXT,"+
                "latheInstalled TEXT," +
                "latheInstalledRemarks TEXT,"+
                "latheInstalledNC TEXT,"+
                "majorMachine TEXT," +
                "majorMachineRemarks TEXT,"+
                "majorMachineNC TEXT,"+
                "flag TEXT,"+
                "Proc_tracker INTEGER)";
        String toolsBelowImage = "";

        toolsBelowImage = "CREATE TABLE ToolsBelowInfoImage (Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "YearWiseCollegeId  TEXT NOT NULL, " +
                "docId TEXT," +
                "tradeId TEXT,"+
                "section TEXT," +
                "refId TEXT," +
                "pictureType TEXT," +
                "helpText TEXT," +
                "remarks TEXT," +
                "latitude TEXT," +
                "longitude TEXT," +
                "name TEXT," +
                "photo TEXT," +
                "nc TEXT," +
                "proc_tracker INTEGER)";


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
                Log.e(TAG, e.getMessage());
                Log.e("allocation", e.getMessage());
            }

            try {
                dropTable(db, "GeneralInfo");
                db.execSQL(subGeneralInfoTableQuery);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            try {
                dropTable(db, "TrustGeneralInfo");
                db.execSQL(trustGeneralInfoQuery);
                //Log.e("come under affiliation cr", "yes");
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            try {
                dropTable(db, "OrganisationGeneralInfo");
                db.execSQL(organisationGeneralInfoQuery);
                //Log.e("come under affiliation cr", "yes");
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
            try {
                dropTable(db, "TechnicalInfo");
                db.execSQL(technicalInfoQuery);
                //Log.e("come under affiliation cr", "yes");
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            try {
                dropTable(db, "InstructorITIInfo");
                db.execSQL(instructorInfoQuery);
                //Log.e("come under affiliation cr", "yes");
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
            try {
                dropTable(db, "TradeInfo");
                db.execSQL(tradeInfoQuery);
                //Log.e("come under affiliation cr", "yes");
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }


            try {
                dropTable(db, "ToolsAboveTenK");
                db.execSQL(ToolsAboveTenK);
                //Log.e("come under affiliation cr", "yes");
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
            try {
                dropTable(db, "LabInfo");
                db.execSQL(labInfoQuery);
                //Log.e("come under affiliation cr", "yes");
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
            try {
                dropTable(db, "LandInfo");
                db.execSQL(landandBuildingInfoQuery);
                //Log.e("come under affiliation cr", "yes");
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            try {
                dropTable(db, "TradeWiseToolInfo");
                db.execSQL(tradewiseToolQuery);
                //Log.e("come under affiliation cr", "yes");
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            try {
                dropTable(db, "TradeWiseInfo");
                db.execSQL(tradeWiseInfoQuery);
                //Log.e("come under affiliation cr", "yes");
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            try {
                dropTable(db, "PremisesShiftingInfo");
                db.execSQL(premisesInfoQuery);
                //Log.e("come under affiliation cr", "yes");
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
            try {
                dropTable(db, "ClassroomInfo");
                db.execSQL(classroomInfoQuery);
                //Log.e("come under affiliation cr", "yes");
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
            try {
                dropTable(db, "UploadImages");
                db.execSQL(subUploadImageTableQuery);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            try {
                dropTable(db, "GeneralInfoImage");
                db.execSQL(subGeneralInfoImageQuery);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            try {
                dropTable(db, "LandAndBuildingInfoImage");
                db.execSQL(subLandAndBuildingInfoImageQuery);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            try {
                dropTable(db, "OrganisationDetailsInfoImage");
                db.execSQL(subOrganisationDetailsInfoImageQuery);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            try {
                dropTable(db, "PowerSupplyInfoImage");
                db.execSQL(subpowersupplyInfoImageQuery);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            try {
                dropTable(db, "ITLabInfoImage");
                db.execSQL(subitLabInfoImageQuery);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            try {
                dropTable(db, "TechnicalInfoImage");
                db.execSQL(subTechnicalInfoImageQuery);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            try {
                dropTable(db, "InstructorInfoImage");
                db.execSQL(subInstructorInfoImageQuery);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            try {
                dropTable(db, "ToolsBelowInfoImage");
                db.execSQL(toolsBelowImage);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            try {
                dropTable(db, "ClassroomInfoImage");
                db.execSQL(classroomInfoImageQuery);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            try {
                dropTable(db, "ToolsAboveInfoImage");
                db.execSQL(toolsaboveQuery);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            try {
                dropTable(db, "WorkshopInfoImage");
                db.execSQL(workshopaboveQuery
                );
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            try {
                dropTable(db, "WorkshopInfoImage");
                db.execSQL(workshopaboveQuery
                );
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            try {
                dropTable(db, "PowerSupplyITI");
                db.execSQL(powerSupplyITITableQuery);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            try {
                dropTable(db, "ToolsAboveinfo");
                db.execSQL(ToolQuery);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
            try {
                dropTable(db, "WorkshopArea");
                db.execSQL(workshopAreaQuery);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
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
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    public List<Institute> getInstituteList() {
        SQLiteDatabase db = null;
        String selectQueryQues = "select yearWiseCollegeId,organisationName , headOrganisation, contactDetails, startedYear from allocation ";
        List<Institute> instituteList = null;
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                instituteList = new ArrayList<Institute>();
                do {
                    Institute institute = new Institute();
                    institute.setYearWiseCollegeId(cursor.getString(0));
                    institute.setOrganisationName(cursor.getString(1));
                    institute.setHeadOrganisation(cursor.getString(2));
                    institute.setContactDetails(cursor.getString(3));
                    institute.setStartedYear(cursor.getString(4));
                    instituteList.add(institute);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;

        }

        return instituteList;
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
                if (jsonobj.has("status")) {
                    if (Integer.parseInt(jsonobj.getString("status")) == 0 && jsonobj.has("payload")) {
                        //database.delete("allocation", null, null);
                        Log.e("status", "deleted successfully");
                        JSONObject payloadObj = jsonobj.optJSONObject("payload");
                        JSONArray GetAllocatedArr = payloadObj.optJSONArray("GetAllocated");
                        for (int i = 0;i<GetAllocatedArr.length();i++) {
                            JSONObject GetAllocatedObj = GetAllocatedArr.optJSONObject(i);
                            ContentValues values = new ContentValues();
                            values.put("yearWiseCollegeId", GetAllocatedObj.optString("yearWiseCollegeId"));
                            values.put("organisationName", GetAllocatedObj.optString("organisationName"));
                            values.put("headOrganisation", GetAllocatedObj.optString("headOrganisation"));
                            values.put("contactDetails", GetAllocatedObj.optString("contactDetails"));
                            values.put("startedYear", GetAllocatedObj.optString("startedYear"));
                            database.insert("allocation", null, values);
                        }

                    } else {
                        Toast.makeText(application_context, "No data found", Toast.LENGTH_LONG).show();
                        return false;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public Proc_Track getSyncStatus(String category) {
        String generalInfoQuery = "Select proc_tracker,count(*) from GeneralInfo group by proc_tracker";
        String trustInfoQuery = "Select proc_tracker,count(*) from TrustGeneralInfo group by proc_tracker";
        String organisationInfoQuery = "Select proc_tracker,count(*) from OrganisationGeneralInfo group by proc_tracker";
        String technicalInfoQuery = "Select proc_tracker,count(*) from TechnicalInfo group by proc_tracker";
        String instructorInfoQuery = "Select proc_tracker,count(*) from InstructorITIInfo group by proc_tracker";
        String tradeInfoQuery = "Select proc_tracker,count(*) from TradeInfo group by proc_tracker";
        String toolsAboveTenKInfoQuery = "Select proc_tracker,count(*) from ToolsAboveTenK group by proc_tracker";
        String labInfoQuery = "Select proc_tracker,count(*) from LabInfo group by proc_tracker";
        String landInfoQuery = "Select proc_tracker,count(*) from LandInfo group by proc_tracker";
        String tradewiseInfoQuery = "Select proc_tracker,count(*) from TradeWiseToolInfo group by proc_tracker";
        String premisesInfoQuery = "Select proc_tracker,count(*) from PremisesShiftingInfo group by proc_tracker";
        String classroomInfoQuery = "Select proc_tracker,count(*) from ClassroomInfo group by proc_tracker";
        String powerSupplyITIQuery = "Select proc_tracker,count(*) from PowerSupplyITI group by proc_tracker";
        String workshopAreaQuery = "Select proc_tracker,count(*) from WorkshopArea group by proc_tracker";
        String tradeWiseQuery = "Select proc_tracker,count(*) from TradeWiseInfo group by proc_tracker";

        String finalSql = "";
        Proc_Track obj = new Proc_Track();
        try {
            if (category.equalsIgnoreCase("genInfo")) {
                finalSql = generalInfoQuery;
            }else  if (category.equalsIgnoreCase("trust")) {
                finalSql = trustInfoQuery;
            } else if(category.equalsIgnoreCase("organisation")){
                finalSql = organisationInfoQuery;
            }else if(category.equalsIgnoreCase("technical")){
                finalSql = technicalInfoQuery;
            }else if(category.equalsIgnoreCase("instructor")){
                finalSql = instructorInfoQuery;
            }else if(category.equalsIgnoreCase("trades")){
                finalSql = tradeInfoQuery;
            }else if(category.equalsIgnoreCase("toolsAbove10K")){
                finalSql = toolsAboveTenKInfoQuery;
            }else if(category.equalsIgnoreCase("lab")) {
                finalSql = labInfoQuery;
            }else if(category.equalsIgnoreCase("landandbuilding")){
                finalSql = landInfoQuery;
            }else if(category.equalsIgnoreCase("premises")){
                finalSql = premisesInfoQuery;
            }else if(category.equalsIgnoreCase("class")){
                finalSql = classroomInfoQuery;
            }else if (category.equalsIgnoreCase("powerSupplyITI")) {
                finalSql = powerSupplyITIQuery;
            }else if (category.equalsIgnoreCase("workshopArea")) {
                finalSql = workshopAreaQuery;
            }else if (category.equalsIgnoreCase("tradesWise")) {
                finalSql = tradeWiseQuery;
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
            Log.e(TAG, e.getMessage());
            return null;
        }
        return obj;
    }

    public boolean updateSyncDataStatus(String type, String instituteId) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        try {

            values.put("proc_tracker", 3);
           if(type.equalsIgnoreCase("general")){
               database.update("GeneralInfo", values, " YearWiseCollegeId= " + instituteId , null);

           }if(type.equalsIgnoreCase("trust")){
                database.update("TrustGeneralInfo", values, " YearWiseCollegeId= " + instituteId , null);
            }
            if(type.equalsIgnoreCase("Org")){
                database.update("OrganisationGeneralInfo", values, " YearWiseCollegeId= " + instituteId , null);
            }
            if(type.equalsIgnoreCase("premises")){
                database.update("PremisesShiftingInfo", values, " YearWiseCollegeId= " + instituteId , null);
            }
            if(type.equalsIgnoreCase("class")){
                database.update("ClassroomInfo", values, " YearWiseCollegeId= " + instituteId , null);
            }
            if(type.equalsIgnoreCase("technical")){
                database.update("TechnicalInfo", values, " YearWiseCollegeId= " + instituteId , null);
            }
            if(type.equalsIgnoreCase("instructor")){
                database.update("InstructorITIInfo", values, " YearWiseCollegeId= " + instituteId , null);
            }
            if(type.equalsIgnoreCase("land")){
                database.update("LandInfo", values, " YearWiseCollegeId= " + instituteId , null);
            }

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            database.close();
        }
        return true;
    }

    public boolean deleteToolsAbove(Context context) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.delete("ToolsAboveInfo",null,null);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            db.close();
        }
        return true;
    }

    public boolean deleteToolsAboveImage(Context context) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.delete("ToolsAboveInfoImage",null,null);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            db.close();
        }
        return true;
    }

    public boolean deleteAllData(Context context) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.delete("user", null, null);
            db.delete("allocation", null, null);
            db.delete("GeneralInfo", null, null);
            db.delete("TrustGeneralInfo", null, null);
            db.delete("OrganisationGeneralInfo",null,null);
            db.delete("TechnicalInfo",null,null);
            db.delete("InstructorITIInfo",null,null);
            db.delete("TradeInfo",null,null);
            db.delete("LabInfo",null,null);
            db.delete("LandInfo",null,null);
            db.delete("TradeWiseToolInfo",null,null);
            db.delete("PremisesShiftingInfo",null,null);
            db.delete("ClassroomInfo",null,null);
            db.delete("UploadImages",null,null);
            db.delete("GeneralInfoImage",null,null);
            db.delete("PowerSupplyITI", null, null);
            db.delete("WorkshopArea",null,null);
            db.delete("LandAndBuildingInfoImage",null,null);
            db.delete("OrganisationDetailsInfoImage",null,null);
            db.delete("TechnicalInfoImage",null,null);
            db.delete("InstructorInfoImage",null,null);
            db.delete("ITLabInfoImage",null,null);
            db.delete("PowerSupplyInfoImage",null,null);
            db.delete("ClassroomInfoImage",null,null);
            db.delete("ToolsBelowInfoImage",null,null);
            db.delete("ToolsAboveInfoImage",null,null);
            db.delete("WorkshopInfoImage",null,null);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            db.close();
        }

        context.deleteDatabase(database_name);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application_context);
        sharedPreferences.edit().clear().apply();
        return true;
    }


    public List<GenralInfo> getGenInfoList(String YearWiseCollegeId) {

        SQLiteDatabase db = null;
        String selectQueryQues ="Select YearWiseCollegeId,typeInstitute,typeInstituteRemarks,typeInstituteNC,Date,DateRemarks,DateNC,instituteType," +
                "instituteTypeRemarks,instituteTypeNC,phoneNo,phoneNoRemarks,phoneNoNC,specialStatus,specialStatusRemarks," +
                "specialStatusNC,faxNo,faxNoRemarks,faxNoNC," +
                "instituteLocation,instituteLocationRemarks,instituteLocationNC,mobile,mobileRemarks,mobileNC," +
                "specialLocation,specialLocationRemarks," +
                "specialLocationNC,email,emailRemarks,emailNC,principalName,principalNameRemarks,principalNameNC,instituteRunning," +
                "instituteRunningRemarks,instituteRunningNC,address,addressRemarks,addressNC,landmark,landmarkRemarks,landmarkNC," +
                "district,districtRemarks,districtNC,state,stateRemarks,stateNC,tehsil,tehsilRemarks,tehsilNC,pincode,pincodeRemarks," +
                "pincodeNC" +
                ",block,blockRemarks,blockNC,website,websiteRemarks,websiteNC,itiName,itiNameRemarks,itiNameNC," +
                "applicationno,applicationnoRemarks," +
                "applicationnoNC,signage,signageRemarks,signageNC,bharat,bharatRemarks,bharatNC,biometric,biometricRemarks,biometricNC" +
                ",combined,combinedRemarks,combinedNC,demarcated,demarcatedRemarks,demarcatedNC,required,requiredRemarks,requiredNC" +
                ",available,availableRemarks,availableNC,refId,proc_tracker from GeneralInfo where YearWiseCollegeId = " + YearWiseCollegeId;

        List<GenralInfo> genInfoModelList = new ArrayList<GenralInfo>();
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    GenralInfo genInfoModel = new GenralInfo();
                    genInfoModel.setYearWiseCollegeid(cursor.getString(0));
                    genInfoModel.setTypeInstitute(cursor.getString(1));
                    genInfoModel.setTypeInstituteRemark(cursor.getString(2));
                    genInfoModel.setTypeInstituteNC(cursor.getInt(3));
                    genInfoModel.setDate(cursor.getString(4));
                    genInfoModel.setDateRemarks(cursor.getString(5));
                    genInfoModel.setDateNc(cursor.getInt(6));
                    genInfoModel.setInstituteType(cursor.getString(7));
                    genInfoModel.setInstitutetypeRemarks(cursor.getString(8));
                    genInfoModel.setInstitutetypeNc(cursor.getInt(9));
                    genInfoModel.setPhoneNo(cursor.getString(10));
                    genInfoModel.setPhonenoRemarks(cursor.getString(11));
                    genInfoModel.setPhonenoNc(cursor.getInt(12));
                    genInfoModel.setSpecialStatus(cursor.getString(13));
                    genInfoModel.setSpecialstatusRemarks(cursor.getString(14));
                    genInfoModel.setSpecialstatusNc(cursor.getString(15));
                    genInfoModel.setFaxNo(cursor.getString(16));
                    genInfoModel.setFaxnoRemarks(cursor.getString(17));
                    genInfoModel.setFaxnoNc(cursor.getString(18));
                    genInfoModel.setInstituteLocation(cursor.getString(19));
                    genInfoModel.setInstitutelocationRemarks(cursor.getString(20));
                    genInfoModel.setInstitutelocationNc(cursor.getString(21));
                    genInfoModel.setMobile(cursor.getString(22));
                    genInfoModel.setMobileRemarks(cursor.getString(23));
                    genInfoModel.setMobileNc(cursor.getInt(24));
                    genInfoModel.setSpecialLocation(cursor.getString(25));
                    genInfoModel.setSpeciallocationRemarks(cursor.getString(26));
                    genInfoModel.setSpeciallocationNc(cursor.getInt(27));
                    genInfoModel.setEmail(cursor.getString(28));
                    genInfoModel.setEmailRemarks(cursor.getString(29));
                    genInfoModel.setEmailNc(cursor.getInt(30));
                    genInfoModel.setPrincipalName(cursor.getString(31));
                    genInfoModel.setPrincipalnameRemarks(cursor.getString(32));
                    genInfoModel.setPrincipalnameNc(cursor.getInt(33));
                    genInfoModel.setInstituteRunning(cursor.getString(34));
                    genInfoModel.setInstituterunningRemarks(cursor.getString(35));
                    genInfoModel.setInstituterunningNC(cursor.getInt(36));
                    genInfoModel.setAddress(cursor.getString(37));
                    genInfoModel.setAddressRemarks(cursor.getString(38));
                    genInfoModel.setAddressNc(cursor.getInt(39));
                    genInfoModel.setLandmark(cursor.getString(40));
                    genInfoModel.setLandmarkRemarks(cursor.getString(41));
                    genInfoModel.setLandmarkNc(cursor.getInt(42));
                    genInfoModel.setDistrict(cursor.getString(43));
                    genInfoModel.setDistrictRemarks(cursor.getString(44));
                    genInfoModel.setDistrictNc(cursor.getInt(45));
                    genInfoModel.setState(cursor.getString(46));
                    genInfoModel.setStateRemarks(cursor.getString(47));
                    genInfoModel.setStateNc(cursor.getInt(48));
                    genInfoModel.setTehsil(cursor.getString(49));
                    genInfoModel.setTehsilRemarks(cursor.getString(50));
                    genInfoModel.setTehsilNc(cursor.getInt(51));
                    genInfoModel.setPincode(cursor.getString(52));
                    genInfoModel.setPincodeRemarks(cursor.getString(53));
                    genInfoModel.setPincodeNc(cursor.getString(54));
                    genInfoModel.setBlock(cursor.getString(55));
                    genInfoModel.setBlockRemarks(cursor.getString(56));
                    genInfoModel.setBlockNc(cursor.getInt(57));
                    genInfoModel.setWebsite(cursor.getString(58));
                    genInfoModel.setWebsiteRemarks(cursor.getString(59));
                    genInfoModel.setWebsiteNc(cursor.getInt(60));;
                    genInfoModel.setItiName(cursor.getString(61));
                    genInfoModel.setItiNameRemarks(cursor.getString(62));
                    genInfoModel.setItiNameNc(cursor.getInt(63));
                    genInfoModel.setApplication(cursor.getString(64));
                    genInfoModel.setApplicationRemarks(cursor.getString(65));
                    genInfoModel.setApplicationNC(cursor.getInt(66));
                    genInfoModel.setItiSignage(cursor.getString(67));
                    genInfoModel.setItiSignageRemarks(cursor.getString(68));
                    genInfoModel.setItiSignageNc(cursor.getInt(69));
                    genInfoModel.setItiBharat(cursor.getString(70));
                    genInfoModel.setItiBharatRemarks(cursor.getString(71));
                    genInfoModel.setItiBharatNc(cursor.getInt(72));
                    genInfoModel.setBiometricDevice(cursor.getString(73));
                    genInfoModel.setBiometricdeviceRemarks(cursor.getString(74));
                    genInfoModel.setBiometricdeviceNc(cursor.getInt(75));
                    genInfoModel.setCombined(cursor.getString(76));
                    genInfoModel.setCombinedRemarks(cursor.getString(77));
                    genInfoModel.setCombinedNc(cursor.getInt(78));
                    genInfoModel.setDemarcated(cursor.getString(79));
                    genInfoModel.setDemarcatedRemarks(cursor.getString(80));
                    genInfoModel.setDemarcatedNc(cursor.getInt(81));
                    genInfoModel.setRequired(cursor.getString(82));
                    genInfoModel.setRequiredRemarks(cursor.getString(83));
                    genInfoModel.setRequiredNC(cursor.getInt(84));
                    genInfoModel.setAvailable(cursor.getString(85));
                    genInfoModel.setAvailableRemarks(cursor.getString(86));
                    genInfoModel.setAvailableNC(cursor.getInt(87));
                    genInfoModel.setRefId(cursor.getInt(88));
                    genInfoModel.setProc_tracker(cursor.getInt(89));

                    genInfoModelList.add(genInfoModel);


                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return genInfoModelList;

    }

    public boolean addGenInfoData(JSONObject result, String yearWiseCollegeId) {
       try{

           SQLiteDatabase database = getWritableDatabase();

           int status = result.optInt("status");
           if(status == 0){
               JSONObject payload = result.optJSONObject("payload");
               JSONObject genralInfoObj = payload.optJSONObject("generalinfo");
               ContentValues values = new ContentValues();
               values.put("YearWiseCollegeId",yearWiseCollegeId);
               values.put("refId",genralInfoObj.optString("refId"));
               values.put("itiName",genralInfoObj.optString("itiName"));
               values.put("applicationno",genralInfoObj.optString("applicationno"));
               values.put("typeInstitute",genralInfoObj.optString("typeInstitute"));
               values.put("Date",genralInfoObj.optString("Date"));
               values.put("instituteType",genralInfoObj.optString("instituteType"));
               values.put("phoneNo",genralInfoObj.optString("phoneNo"));
               values.put("specialStatus",genralInfoObj.optString("specialStatus"));
               values.put("faxNo",genralInfoObj.optString("faxNo"));
               values.put("instituteLocation",genralInfoObj.optString("instituteLocation"));
               values.put("mobile",genralInfoObj.optString("mobile"));
               values.put("specialLocation",genralInfoObj.optString("specialLocation"));
               values.put("email",genralInfoObj.optString("email"));
               values.put("principalName",genralInfoObj.optString("principalName"));
               values.put("instituteRunning",genralInfoObj.optString("instituteRunning"));
               values.put("address",genralInfoObj.optString("address"));
               values.put("landmark",genralInfoObj.optString("address"));
               values.put("district",genralInfoObj.optString("district"));
               values.put("state",genralInfoObj.optString("state"));
               values.put("tehsil",genralInfoObj.optString("tehsil"));
               values.put("pincode",genralInfoObj.optString("pincode"));
               values.put("block",genralInfoObj.optString("block"));
               values.put("website",genralInfoObj.optString("website"));
               values.put("proc_tracker", 1);
               database.insert("GeneralInfo", null, values);
           }else {
               Toast.makeText(application_context, "No Data Found..", Toast.LENGTH_LONG).show();
           }

       }catch (Exception e){
           e.printStackTrace();
       }

        return true;
    }

    public List<EquipmentInfo> getTrustInfoList(String YearWiseCollegeId) {
        SQLiteDatabase db = null;
        String selectQueryQues ="Select YearWiseCollegeId,instituteOwned,instituteOwnedRemarks,isRegistered,isRegisteredRemarks," +
                "isRegisteredNC" +
                ",registration,registrationNo,registrationNoRemarks,registrationNoNC,trustName,trustNameRemarks,trustNameNC," +
                "registrationDate," +
                "trustValidity,trustValidityRemarks,trustValidityNC,panNumber,panNumberRemarks,panNumberNC,experience" +
                ",experienceRemarks,experienceNC,educationalType,educationalTypeRemarks,educationalTypeNC,remarks,commonNC" +
                ",refId,proc_tracker from TrustGeneralInfo where YearWiseCollegeId = " + YearWiseCollegeId;

        List<EquipmentInfo> equipmentInfoModelList = new ArrayList<EquipmentInfo>();
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    EquipmentInfo equipmentInfoModel = new EquipmentInfo();
                    equipmentInfoModel.setYearWiseCollegeid(cursor.getString(0));
                    equipmentInfoModel.setInstituteOwned(cursor.getString(1));
                    equipmentInfoModel.setInstituteOwnedRemrks(cursor.getString(2));
                    equipmentInfoModel.setIsRegistered(cursor.getString(3));
                    equipmentInfoModel.setIsregisteredRemarks(cursor.getString(4));
                    equipmentInfoModel.setIsregisteredNc(cursor.getInt(5));
                    equipmentInfoModel.setRegistration(cursor.getString(6));
                    equipmentInfoModel.setRegistrationNo(cursor.getString(7));
                    equipmentInfoModel.setRegistrationnoRemarks(cursor.getString(8));
                    equipmentInfoModel.setRegistrationnoNc(cursor.getInt(9));
                    equipmentInfoModel.setTrustName(cursor.getString(10));
                    equipmentInfoModel.setTrustnameRemarks(cursor.getString(11));
                    equipmentInfoModel.setTrustnameNc(cursor.getInt(12));
                    equipmentInfoModel.setRegistrationDate(cursor.getString(13));
                    equipmentInfoModel.setTrustValidity(cursor.getString(14));
                    equipmentInfoModel.setTrustvalidityRemarks(cursor.getString(15));
                    equipmentInfoModel.setTrustvalidityNc(cursor.getInt(16));
                    equipmentInfoModel.setPanNumber(cursor.getString(17));
                    equipmentInfoModel.setPannumberRemarks(cursor.getString(18));
                    equipmentInfoModel.setPannumberNc(cursor.getInt(19));
                    equipmentInfoModel.setExperience(cursor.getString(20));
                    equipmentInfoModel.setExperienceRemarks(cursor.getString(21));
                    equipmentInfoModel.setExperienceNc(cursor.getInt(22));
                    equipmentInfoModel.setType(cursor.getString(23));
                    equipmentInfoModel.setTypeRemarks(cursor.getString(24));
                    equipmentInfoModel.setTypeNc(cursor.getInt(25));
                    equipmentInfoModel.setRemarks(cursor.getString(26));
                    equipmentInfoModel.setCommonNc(cursor.getInt(27));
                    equipmentInfoModel.setRefId(cursor.getInt(28));
                    equipmentInfoModel.setProc_tracker(cursor.getInt(29));

                    equipmentInfoModelList.add(equipmentInfoModel);


                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
        db.close();
    }

        return equipmentInfoModelList;
    }

    public boolean addTrustInfoData(JSONObject result, String yearWiseCollegeId) {

        try{

            SQLiteDatabase database = getWritableDatabase();

            int status = result.optInt("status");
            if(status == 0){
                JSONObject payload = result.optJSONObject("payload");
                JSONObject genralInfoObj = payload.optJSONObject("trust");
                ContentValues values = new ContentValues();
                values.put("YearWiseCollegeId",yearWiseCollegeId);
                values.put("refId",genralInfoObj.optString("refId"));
                values.put("instituteOwned",genralInfoObj.optString("instituteOwned"));
                values.put("isRegistered",genralInfoObj.optString("isRegistered"));
                values.put("registration",genralInfoObj.optString("registration"));
                values.put("registrationNo",genralInfoObj.optString("registrationNo"));
                values.put("trustName",genralInfoObj.optString("trustName"));
                values.put("registrationDate",genralInfoObj.optString("registrationDate"));
                values.put("trustValidity",genralInfoObj.optString("trustValidity"));
                values.put("panNumber",genralInfoObj.optString("panNumber"));
                values.put("proc_tracker", 1);
                database.insert("TrustGeneralInfo", null, values);
            }else {
                Toast.makeText(application_context, "No Data Found..", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    public GenralInfo getGeneralInfobyYearwiseCollegeId(String YearWiseCollegeId) {
        SQLiteDatabase db = null;
        String selectQueryQues ="Select YearWiseCollegeId,typeInstitute,typeInstituteRemarks,typeInstituteNC,Date,DateRemarks,DateNC,instituteType," +
                "instituteTypeRemarks,instituteTypeNC,phoneNo,phoneNoRemarks,phoneNoNC,specialStatus,specialStatusRemarks," +
                "specialStatusNC,faxNo,faxNoRemarks,faxNoNC," +
                "instituteLocation,instituteLocationRemarks,instituteLocationNC,mobile,mobileRemarks,mobileNC," +
                "specialLocation,specialLocationRemarks," +
                "specialLocationNC,email,emailRemarks,emailNC,principalName,principalNameRemarks,principalNameNC,instituteRunning," +
                "instituteRunningRemarks,instituteRunningNC,address,addressRemarks,addressNC,landmark,landmarkRemarks,landmarkNC," +
                "district,districtRemarks,districtNC,state,stateRemarks,stateNC,tehsil,tehsilRemarks,tehsilNC,pincode,pincodeRemarks," +
                "pincodeNC" +
                ",block,blockRemarks,blockNC,website,websiteRemarks,websiteNC,itiName,itiNameRemarks,itiNameNC," +
                "applicationno,applicationnoRemarks," +
                "applicationnoNC,signage,signageRemarks,signageNC,bharat,bharatRemarks,bharatNC,biometric,biometricRemarks,biometricNC" +
                ",combined,combinedRemarks,combinedNC,demarcated,demarcatedRemarks,demarcatedNC,required,requiredRemarks,requiredNC" +
                ",available,availableRemarks,availableNC,refId,proc_tracker from GeneralInfo where YearWiseCollegeId = " + YearWiseCollegeId;
        GenralInfo genInfoModel = null;
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    genInfoModel = new GenralInfo();
                    genInfoModel.setYearWiseCollegeid(cursor.getString(0));
                    genInfoModel.setTypeInstitute(cursor.getString(1));
                    genInfoModel.setTypeInstituteRemark(cursor.getString(2));
                    genInfoModel.setTypeInstituteNC(cursor.getInt(3));
                    genInfoModel.setDate(cursor.getString(4));
                    genInfoModel.setDateRemarks(cursor.getString(5));
                    genInfoModel.setDateNc(cursor.getInt(6));
                    genInfoModel.setInstituteType(cursor.getString(7));
                    genInfoModel.setInstitutetypeRemarks(cursor.getString(8));
                    genInfoModel.setInstitutetypeNc(cursor.getInt(9));
                    genInfoModel.setPhoneNo(cursor.getString(10));
                    genInfoModel.setPhonenoRemarks(cursor.getString(11));
                    genInfoModel.setPhonenoNc(cursor.getInt(12));
                    genInfoModel.setSpecialStatus(cursor.getString(13));
                    genInfoModel.setSpecialstatusRemarks(cursor.getString(14));
                    genInfoModel.setSpecialstatusNc(cursor.getString(15));
                    genInfoModel.setFaxNo(cursor.getString(16));
                    genInfoModel.setFaxnoRemarks(cursor.getString(17));
                    genInfoModel.setFaxnoNc(cursor.getString(18));
                    genInfoModel.setInstituteLocation(cursor.getString(19));
                    genInfoModel.setInstitutelocationRemarks(cursor.getString(20));
                    genInfoModel.setInstitutelocationNc(cursor.getString(21));
                    genInfoModel.setMobile(cursor.getString(22));
                    genInfoModel.setMobileRemarks(cursor.getString(23));
                    genInfoModel.setMobileNc(cursor.getInt(24));
                    genInfoModel.setSpecialLocation(cursor.getString(25));
                    genInfoModel.setSpeciallocationRemarks(cursor.getString(26));
                    genInfoModel.setSpeciallocationNc(cursor.getInt(27));
                    genInfoModel.setEmail(cursor.getString(28));
                    genInfoModel.setEmailRemarks(cursor.getString(29));
                    genInfoModel.setEmailNc(cursor.getInt(30));
                    genInfoModel.setPrincipalName(cursor.getString(31));
                    genInfoModel.setPrincipalnameRemarks(cursor.getString(32));
                    genInfoModel.setPrincipalnameNc(cursor.getInt(33));
                    genInfoModel.setInstituteRunning(cursor.getString(34));
                    genInfoModel.setInstituterunningRemarks(cursor.getString(35));
                    genInfoModel.setInstituterunningNC(cursor.getInt(36));
                    genInfoModel.setAddress(cursor.getString(37));
                    genInfoModel.setAddressRemarks(cursor.getString(38));
                    genInfoModel.setAddressNc(cursor.getInt(39));
                    genInfoModel.setLandmark(cursor.getString(40));
                    genInfoModel.setLandmarkRemarks(cursor.getString(41));
                    genInfoModel.setLandmarkNc(cursor.getInt(42));
                    genInfoModel.setDistrict(cursor.getString(43));
                    genInfoModel.setDistrictRemarks(cursor.getString(44));
                    genInfoModel.setDistrictNc(cursor.getInt(45));
                    genInfoModel.setState(cursor.getString(46));
                    genInfoModel.setStateRemarks(cursor.getString(47));
                    genInfoModel.setStateNc(cursor.getInt(48));
                    genInfoModel.setTehsil(cursor.getString(49));
                    genInfoModel.setTehsilRemarks(cursor.getString(50));
                    genInfoModel.setTehsilNc(cursor.getInt(51));
                    genInfoModel.setPincode(cursor.getString(52));
                    genInfoModel.setPincodeRemarks(cursor.getString(53));
                    genInfoModel.setPincodeNc(cursor.getString(54));
                    genInfoModel.setBlock(cursor.getString(55));
                    genInfoModel.setBlockRemarks(cursor.getString(56));
                    genInfoModel.setBlockNc(cursor.getInt(57));
                    genInfoModel.setWebsite(cursor.getString(58));
                    genInfoModel.setWebsiteRemarks(cursor.getString(59));
                    genInfoModel.setWebsiteNc(cursor.getInt(60));;
                    genInfoModel.setItiName(cursor.getString(61));
                    genInfoModel.setItiNameRemarks(cursor.getString(62));
                    genInfoModel.setItiNameNc(cursor.getInt(63));
                    genInfoModel.setApplication(cursor.getString(64));
                    genInfoModel.setApplicationRemarks(cursor.getString(65));
                    genInfoModel.setApplicationNC(cursor.getInt(66));
                    genInfoModel.setItiSignage(cursor.getString(67));
                    genInfoModel.setItiSignageRemarks(cursor.getString(68));
                    genInfoModel.setItiSignageNc(cursor.getInt(69));
                    genInfoModel.setItiBharat(cursor.getString(70));
                    genInfoModel.setItiBharatRemarks(cursor.getString(71));
                    genInfoModel.setItiBharatNc(cursor.getInt(72));
                    genInfoModel.setBiometricDevice(cursor.getString(73));
                    genInfoModel.setBiometricdeviceRemarks(cursor.getString(74));
                    genInfoModel.setBiometricdeviceNc(cursor.getInt(75));
                    genInfoModel.setCombined(cursor.getString(76));
                    genInfoModel.setCombinedRemarks(cursor.getString(77));
                    genInfoModel.setCombinedNc(cursor.getInt(78));
                    genInfoModel.setDemarcated(cursor.getString(79));
                    genInfoModel.setDemarcatedRemarks(cursor.getString(80));
                    genInfoModel.setDemarcatedNc(cursor.getInt(81));
                    genInfoModel.setRequired(cursor.getString(82));
                    genInfoModel.setRequiredRemarks(cursor.getString(83));
                    genInfoModel.setRequiredNC(cursor.getInt(84));
                    genInfoModel.setAvailable(cursor.getString(85));
                    genInfoModel.setAvailableRemarks(cursor.getString(86));
                    genInfoModel.setAvailableNC(cursor.getInt(87));
                    genInfoModel.setRefId(cursor.getInt(88));
                    genInfoModel.setProc_tracker(cursor.getInt(89));

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return genInfoModel;
    }

    public EquipmentInfo getTrustInfobyYearwiseCollegeId(String YearWiseCollegeId) {

        SQLiteDatabase db = null;
        String selectQueryQues ="Select YearWiseCollegeId,instituteOwned,instituteOwnedRemarks,isRegistered,isRegisteredRemarks," +
                "isRegisteredNC" +
                ",registration,registrationNo,registrationNoRemarks,registrationNoNC,trustName,trustNameRemarks,trustNameNC," +
                "registrationDate," +
                "trustValidity,trustValidityRemarks,trustValidityNC,panNumber,panNumberRemarks,panNumberNC,experience" +
                ",experienceRemarks,experienceNC,educationalType,educationalTypeRemarks,educationalTypeNC,remarks,commonNC" +
                ",refId,proc_tracker from TrustGeneralInfo where YearWiseCollegeId = " + YearWiseCollegeId;

        EquipmentInfo equipmentInfoModel = null;
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    equipmentInfoModel = new EquipmentInfo();
                    equipmentInfoModel.setYearWiseCollegeid(cursor.getString(0));
                    equipmentInfoModel.setInstituteOwned(cursor.getString(1));
                    equipmentInfoModel.setInstituteOwnedRemrks(cursor.getString(2));
                    equipmentInfoModel.setIsRegistered(cursor.getString(3));
                    equipmentInfoModel.setIsregisteredRemarks(cursor.getString(4));
                    equipmentInfoModel.setIsregisteredNc(cursor.getInt(5));
                    equipmentInfoModel.setRegistration(cursor.getString(6));
                    equipmentInfoModel.setRegistrationNo(cursor.getString(7));
                    equipmentInfoModel.setRegistrationnoRemarks(cursor.getString(8));
                    equipmentInfoModel.setRegistrationnoNc(cursor.getInt(9));
                    equipmentInfoModel.setTrustName(cursor.getString(10));
                    equipmentInfoModel.setTrustnameRemarks(cursor.getString(11));
                    equipmentInfoModel.setTrustnameNc(cursor.getInt(12));
                    equipmentInfoModel.setRegistrationDate(cursor.getString(13));
                    equipmentInfoModel.setTrustValidity(cursor.getString(14));
                    equipmentInfoModel.setTrustvalidityRemarks(cursor.getString(15));
                    equipmentInfoModel.setTrustvalidityNc(cursor.getInt(16));
                    equipmentInfoModel.setPanNumber(cursor.getString(17));
                    equipmentInfoModel.setPannumberRemarks(cursor.getString(18));
                    equipmentInfoModel.setPannumberNc(cursor.getInt(19));
                    equipmentInfoModel.setExperience(cursor.getString(20));
                    equipmentInfoModel.setExperienceRemarks(cursor.getString(21));
                    equipmentInfoModel.setExperienceNc(cursor.getInt(22));
                    equipmentInfoModel.setType(cursor.getString(23));
                    equipmentInfoModel.setTypeRemarks(cursor.getString(24));
                    equipmentInfoModel.setTypeNc(cursor.getInt(25));
                    equipmentInfoModel.setRemarks(cursor.getString(26));
                    equipmentInfoModel.setCommonNc(cursor.getInt(27));
                    equipmentInfoModel.setRefId(cursor.getInt(28));
                    equipmentInfoModel.setProc_tracker(cursor.getInt(29));


                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return equipmentInfoModel;
    }

    public List<MaterialInfo> getOrganisationInfoList(String YearWiseCollegeId) {
        SQLiteDatabase db = null;
        String selectQueryQues ="Select yearwisecollegeid,Organization_Name,Organization_NameRemarks,Organization_NameNC," +
                "Authorized_Person , Authorized_PersonRemarks , Authorized_PersonNC , Org_Mobile_no , Org_Mobile_noRemarks ," +
                " Org_Mobile_noNC , Org_Email , Org_EmailRemarks , Org_EmailNC , Org_website , Org_websiteRemarks , " +
                "Org_websiteNC , Org_Telephone_no , Org_Telephone_noRemarks , Org_Telephone_noNC , Org_Postal_Address , " +
                "Org_Postal_AddressRemarks , Org_Postal_AddressNC , StateName , StateNameRemarks , " +
                "StateNameNC , DistrictName , DistrictNameRemarks , DistrictNameNC , MandalName , " +
                "MandalNameRemarks , MandalNameNC , Org_Pincode , Org_PincodeRemarks , Org_PincodeNC , " +
                "Org_LandMark ,Org_LandMarkRemarks , Org_LandMarkNC , Authorized_UID , Authorized_UIDRemarks , " +
                "Authorized_UIDNC , NCVT , NCVTRemarks , NCVTNC , referenceNumber , referenceNumberRemarks , referenceNumberNC , " +
                "affilationNumber , affilationNumberRemarks , affilationNumberNC , misCode , misCodeRemarks , " +
                " misCodeNC ,refId,proc_tracker from OrganisationGeneralInfo where YearWiseCollegeId = " + YearWiseCollegeId;

        List<MaterialInfo> organisationModelList = new ArrayList<MaterialInfo>();
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    MaterialInfo materialInfoModel = new MaterialInfo();
                    materialInfoModel.setYearwisecollegeid(cursor.getString(0));
                    materialInfoModel.setOrganizationName(cursor.getString(1));
                    materialInfoModel.setOrganizationnameRemarks(cursor.getString(2));
                    materialInfoModel.setOrganizationnameNc(cursor.getInt(3));
                    materialInfoModel.setAuthorizedPerson(cursor.getString(4));
                    materialInfoModel.setAuthorizedpersonRemarks(cursor.getString(5));
                    materialInfoModel.setAuthorizedpersonNc(cursor.getInt(6));
                    materialInfoModel.setOrgMobileno(cursor.getString(7));
                    materialInfoModel.setOrgmobilenoRemarks(cursor.getString(8));
                    materialInfoModel.setOrgmobilenoNc(cursor.getInt(9));
                    materialInfoModel.setOrgEmail(cursor.getString(10));
                    materialInfoModel.setOrgemailRemarks(cursor.getString(11));
                    materialInfoModel.setOrgemailNc(cursor.getInt(12));
                    materialInfoModel.setOrgWebsite(cursor.getString(13));
                    materialInfoModel.setOrgwebsiteRemarks(cursor.getString(14));
                    materialInfoModel.setOrgwebsiteNc(cursor.getInt(15));
                    materialInfoModel.setOrgTelephoneno(cursor.getString(16));
                    materialInfoModel.setOrgtelephonenoRemarks(cursor.getString(17));
                    materialInfoModel.setOrgtelephonenoNc(cursor.getInt(18));
                    materialInfoModel.setOrgPostaladdress(cursor.getString(19));
                    materialInfoModel.setOrgpostaladdressRmarks(cursor.getString(20));
                    materialInfoModel.setOrgpostaladdressNc(cursor.getInt(21));
                    materialInfoModel.setStateName(cursor.getString(22));
                    materialInfoModel.setStatenameRemarks(cursor.getString(23));
                    materialInfoModel.setStatenameNc(cursor.getInt(24));
                    materialInfoModel.setDistrictName(cursor.getString(25));
                    materialInfoModel.setDistrictnameRemarks(cursor.getString(26));
                    materialInfoModel.setDistrictnameNc(cursor.getInt(27));
                    materialInfoModel.setMandalName(cursor.getString(28));
                    materialInfoModel.setMandalnameRemarks(cursor.getString(29));
                    materialInfoModel.setMandalnameNc(cursor.getInt(30));
                    materialInfoModel.setOrgPincode(cursor.getString(31));
                    materialInfoModel.setOrgpincodeRemarks(cursor.getString(32));
                    materialInfoModel.setOrgpincodeNc(cursor.getInt(33));
                    materialInfoModel.setOrgLandmark(cursor.getString(34));
                    materialInfoModel.setOrglandmarkRemarks(cursor.getString(35));
                    materialInfoModel.setOrglandmarkNc(cursor.getInt(36));
                    materialInfoModel.setAuthorizedpersonUid(cursor.getString(37));
                    materialInfoModel.setAuthorizedpersonuidRemark(cursor.getString(38));
                    materialInfoModel.setAuthorizedpersonuidNc(cursor.getInt(39));
                    materialInfoModel.setAffilated(cursor.getString(40));
                    materialInfoModel.setAffilatedRemarks(cursor.getString(41));
                    materialInfoModel.setAffilatedNC(cursor.getInt(42));
                    materialInfoModel.setReference(cursor.getString(43));
                    materialInfoModel.setReferenceRemarks(cursor.getString(44));
                    materialInfoModel.setReferenceNC(cursor.getInt(45));
                    materialInfoModel.setAffilation(cursor.getString(46));
                    materialInfoModel.setAffilationRemarks(cursor.getString(47));
                    materialInfoModel.setAffilationNC(cursor.getInt(48));
                    materialInfoModel.setCode(cursor.getString(49));
                    materialInfoModel.setCodeRemarks(cursor.getString(50));
                    materialInfoModel.setCodeNC(cursor.getInt(51));
                    materialInfoModel.setRefId(cursor.getInt(52));
                    materialInfoModel.setProc_tracker(cursor.getInt(53));

                    organisationModelList.add(materialInfoModel);


                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return organisationModelList;
    }

    public MaterialInfo getOrganisationInfobyYearwiseCollegeId(String YearWiseCollegeId) {

        SQLiteDatabase db = null;
        String selectQueryQues ="Select yearwisecollegeid,Organization_Name,Organization_NameRemarks,Organization_NameNC," +
                "Authorized_Person , Authorized_PersonRemarks , Authorized_PersonNC , Org_Mobile_no , Org_Mobile_noRemarks ," +
                " Org_Mobile_noNC , Org_Email , Org_EmailRemarks , Org_EmailNC , Org_website , Org_websiteRemarks , " +
                "Org_websiteNC , Org_Telephone_no , Org_Telephone_noRemarks , Org_Telephone_noNC , Org_Postal_Address , " +
                "Org_Postal_AddressRemarks , Org_Postal_AddressNC , StateName , StateNameRemarks , " +
                "StateNameNC , DistrictName , DistrictNameRemarks , DistrictNameNC , MandalName , " +
                "MandalNameRemarks , MandalNameNC , Org_Pincode , Org_PincodeRemarks , Org_PincodeNC , " +
                "Org_LandMark ,Org_LandMarkRemarks , Org_LandMarkNC , Authorized_UID , Authorized_UIDRemarks , " +
                "Authorized_UIDNC , NCVT , NCVTRemarks , NCVTNC , referenceNumber , referenceNumberRemarks , referenceNumberNC , " +
                "affilationNumber , affilationNumberRemarks , affilationNumberNC , misCode , misCodeRemarks , " +
                " misCodeNC ,refId,proc_tracker from OrganisationGeneralInfo where YearWiseCollegeId = " + YearWiseCollegeId;
        MaterialInfo materialInfoModel = null;
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    materialInfoModel = new MaterialInfo();
                    materialInfoModel.setYearwisecollegeid(cursor.getString(0));
                    materialInfoModel.setOrganizationName(cursor.getString(1));
                    materialInfoModel.setOrganizationnameRemarks(cursor.getString(2));
                    materialInfoModel.setOrganizationnameNc(cursor.getInt(3));
                    materialInfoModel.setAuthorizedPerson(cursor.getString(4));
                    materialInfoModel.setAuthorizedpersonRemarks(cursor.getString(5));
                    materialInfoModel.setAuthorizedpersonNc(cursor.getInt(6));
                    materialInfoModel.setOrgMobileno(cursor.getString(7));
                    materialInfoModel.setOrgmobilenoRemarks(cursor.getString(8));
                    materialInfoModel.setOrgmobilenoNc(cursor.getInt(9));
                    materialInfoModel.setOrgEmail(cursor.getString(10));
                    materialInfoModel.setOrgemailRemarks(cursor.getString(11));
                    materialInfoModel.setOrgemailNc(cursor.getInt(12));
                    materialInfoModel.setOrgWebsite(cursor.getString(13));
                    materialInfoModel.setOrgwebsiteRemarks(cursor.getString(14));
                    materialInfoModel.setOrgwebsiteNc(cursor.getInt(15));
                    materialInfoModel.setOrgTelephoneno(cursor.getString(16));
                    materialInfoModel.setOrgtelephonenoRemarks(cursor.getString(17));
                    materialInfoModel.setOrgtelephonenoNc(cursor.getInt(18));
                    materialInfoModel.setOrgPostaladdress(cursor.getString(19));
                    materialInfoModel.setOrgpostaladdressRmarks(cursor.getString(20));
                    materialInfoModel.setOrgpostaladdressNc(cursor.getInt(21));
                    materialInfoModel.setStateName(cursor.getString(22));
                    materialInfoModel.setStatenameRemarks(cursor.getString(23));
                    materialInfoModel.setStatenameNc(cursor.getInt(24));
                    materialInfoModel.setDistrictName(cursor.getString(25));
                    materialInfoModel.setDistrictnameRemarks(cursor.getString(26));
                    materialInfoModel.setDistrictnameNc(cursor.getInt(27));
                    materialInfoModel.setMandalName(cursor.getString(28));
                    materialInfoModel.setMandalnameRemarks(cursor.getString(29));
                    materialInfoModel.setMandalnameNc(cursor.getInt(30));
                    materialInfoModel.setOrgPincode(cursor.getString(31));
                    materialInfoModel.setOrgpincodeRemarks(cursor.getString(32));
                    materialInfoModel.setOrgpincodeNc(cursor.getInt(33));
                    materialInfoModel.setOrgLandmark(cursor.getString(34));
                    materialInfoModel.setOrglandmarkRemarks(cursor.getString(35));
                    materialInfoModel.setOrglandmarkNc(cursor.getInt(36));
                    materialInfoModel.setAuthorizedpersonUid(cursor.getString(37));
                    materialInfoModel.setAuthorizedpersonuidRemark(cursor.getString(38));
                    materialInfoModel.setAuthorizedpersonuidNc(cursor.getInt(39));
                    materialInfoModel.setAffilated(cursor.getString(40));
                    materialInfoModel.setAffilatedRemarks(cursor.getString(41));
                    materialInfoModel.setAffilatedNC(cursor.getInt(42));
                    materialInfoModel.setReference(cursor.getString(43));
                    materialInfoModel.setReferenceRemarks(cursor.getString(44));
                    materialInfoModel.setReferenceNC(cursor.getInt(45));
                    materialInfoModel.setAffilation(cursor.getString(46));
                    materialInfoModel.setAffilationRemarks(cursor.getString(47));
                    materialInfoModel.setAffilationNC(cursor.getInt(48));
                    materialInfoModel.setCode(cursor.getString(49));
                    materialInfoModel.setCodeRemarks(cursor.getString(50));
                    materialInfoModel.setCodeNC(cursor.getInt(51));
                    materialInfoModel.setRefId(cursor.getInt(52));
                    materialInfoModel.setProc_tracker(cursor.getInt(53));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return materialInfoModel;
    }

    public boolean addOrganisationInfoData(JSONObject result, String yearWiseCollegeId) {

        try{

            SQLiteDatabase database = getWritableDatabase();

            int status = result.optInt("status");
            if(status == 0){
                JSONObject payload = result.optJSONObject("payload");
                JSONObject organisationInfoObj = payload.optJSONObject("OrganizationDetails");
                ContentValues values = new ContentValues();
                values.put("yearwisecollegeid",yearWiseCollegeId);
                values.put("refId",organisationInfoObj.optString("refId"));
                values.put("Organization_Name",organisationInfoObj.optString("organizationName"));
                values.put("Authorized_Person",organisationInfoObj.optString("authorizedPerson"));
                values.put("Org_Mobile_no",organisationInfoObj.optString("orgMobileno"));
                values.put("Org_Email",organisationInfoObj.optString("orgEmail"));
                values.put("Org_website",organisationInfoObj.optString("orgWebsite"));
                values.put("Org_Telephone_no",organisationInfoObj.optString("orgTelephoneno"));
                values.put("Org_Postal_Address",organisationInfoObj.optString("orgPostaladdress"));
                values.put("StateName",organisationInfoObj.optString("stateName"));
                values.put("DistrictName",organisationInfoObj.optString("districtName"));
                values.put("MandalName",organisationInfoObj.optString("mandalName"));
                values.put("Org_Pincode",organisationInfoObj.optString("orgPincode"));
                values.put("Org_LandMark",organisationInfoObj.optString("orgLandmark"));
                values.put("Authorized_UID",organisationInfoObj.optString("Authorized_UID"));
                values.put("proc_tracker", 1);
                database.insert("OrganisationGeneralInfo", null, values);
            }else {
                Toast.makeText(application_context, "No Data Found..", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    public boolean saveGeneralInfo(GenralInfo generalInfoModel, String mode) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("itiName",generalInfoModel.getItiName());
        values.put("applicationno",generalInfoModel.getApplication());
        values.put("typeInstitute",generalInfoModel.getTypeInstitute());
        values.put("typeInstituteRemarks",generalInfoModel.getTypeInstituteRemark());
        values.put("typeInstituteNC",generalInfoModel.getTypeInstituteNC());
        values.put("Date",generalInfoModel.getDate());
        values.put("DateRemarks",generalInfoModel.getDateRemarks());
        values.put("DateNC",generalInfoModel.getDateNc());
        values.put("instituteType",generalInfoModel.getInstituteType());
        values.put("instituteTypeRemarks",generalInfoModel.getInstitutetypeRemarks());
        values.put("instituteTypeNC",generalInfoModel.getInstitutetypeNc());
        values.put("phoneNo",generalInfoModel.getPhoneNo());
        values.put("phoneNoRemarks",generalInfoModel.getPhonenoRemarks());
        values.put("phoneNoNC",generalInfoModel.getPhonenoNc());
        values.put("specialStatus",generalInfoModel.getSpecialStatus());
        values.put("specialStatusRemarks",generalInfoModel.getSpecialstatusRemarks());
        values.put("specialStatusNC",generalInfoModel.getSpecialstatusNc());
        values.put("faxNo",generalInfoModel.getFaxNo());
        values.put("faxNoRemarks",generalInfoModel.getFaxnoRemarks());
        values.put("faxNoNC",generalInfoModel.getFaxnoNc());
        values.put("instituteLocation",generalInfoModel.getInstituteLocation());
        values.put("instituteLocationRemarks",generalInfoModel.getInstitutelocationRemarks());
        values.put("instituteLocationNC",generalInfoModel.getInstitutelocationNc());
        values.put("mobile",generalInfoModel.getMobile());
        values.put("mobileRemarks",generalInfoModel.getMobileRemarks());
        values.put("mobileNC",generalInfoModel.getMobileNc());
        values.put("specialLocation",generalInfoModel.getSpecialLocation());
        values.put("specialLocationRemarks",generalInfoModel.getSpeciallocationRemarks());
        values.put("specialLocationNC",generalInfoModel.getSpeciallocationNc());
        values.put("email",generalInfoModel.getEmail());
        values.put("emailRemarks",generalInfoModel.getEmailRemarks());
        values.put("emailNC",generalInfoModel.getEmailNc());
        values.put("principalName",generalInfoModel.getPrincipalName());
        values.put("principalNameRemarks",generalInfoModel.getPrincipalnameRemarks());
        values.put("principalNameNC",generalInfoModel.getPrincipalnameNc());
        values.put("instituteRunning",generalInfoModel.getInstituteRunning());
        values.put("instituteRunningRemarks",generalInfoModel.getInstituterunningRemarks());
        values.put("instituteRunningNC",generalInfoModel.getInstituterunningNC());
        values.put("address",generalInfoModel.getAddress());
        values.put("addressRemarks",generalInfoModel.getAddressRemarks());
        values.put("addressNC",generalInfoModel.getAddressNc());
        values.put("landmark",generalInfoModel.getLandmark());
        values.put("landmarkRemarks",generalInfoModel.getLandmarkRemarks());
        values.put("landmarkNC",generalInfoModel.getLandmarkNc());
        values.put("district",generalInfoModel.getDistrict());
        values.put("districtRemarks",generalInfoModel.getDistrictRemarks());
        values.put("districtNC",generalInfoModel.getDistrictNc());
        values.put("state",generalInfoModel.getState());
        values.put("stateRemarks",generalInfoModel.getStateRemarks());
        values.put("stateNC",generalInfoModel.getStateNc());
        values.put("tehsil",generalInfoModel.getTehsil());
        values.put("tehsilRemarks",generalInfoModel.getTehsilRemarks());
        values.put("tehsilNC",generalInfoModel.getTehsilNc());
        values.put("pincode",generalInfoModel.getPincode());
        values.put("pincodeRemarks",generalInfoModel.getPincodeRemarks());
        values.put("pincodeNC",generalInfoModel.getPincodeNc());
        values.put("block",generalInfoModel.getBlock());
        values.put("blockRemarks",generalInfoModel.getBlockRemarks());
        values.put("blockNC",generalInfoModel.getBlockNc());
        values.put("website",generalInfoModel.getWebsite());
        values.put("websiteRemarks",generalInfoModel.getWebsiteRemarks());
        values.put("websiteNC",generalInfoModel.getWebsiteNc());
        values.put("itiName",generalInfoModel.getItiName());
        values.put("itiNameRemarks",generalInfoModel.getItiNameRemarks());
        values.put("itiNameNC",generalInfoModel.getItiNameNc());
        values.put("applicationno",generalInfoModel.getApplication());
        values.put("applicationnoRemarks",generalInfoModel.getApplicationRemarks());
        values.put("applicationnoNC",generalInfoModel.getApplicationNC());
        values.put("signage",generalInfoModel.getItiSignage());
        values.put("signageRemarks",generalInfoModel.getItiSignageRemarks());
        values.put("signageNC",generalInfoModel.getItiSignageNc());
        values.put("bharat",generalInfoModel.getItiBharat());
        values.put("bharatRemarks",generalInfoModel.getItiBharatRemarks());
        values.put("bharatNC",generalInfoModel.getItiBharatNc());
        values.put("biometric",generalInfoModel.getBiometricDevice());
        values.put("biometricRemarks",generalInfoModel.getBiometricdeviceRemarks());
        values.put("biometricNC",generalInfoModel.getBiometricdeviceNc());
        values.put("combined",generalInfoModel.getCombined());
        values.put("combinedRemarks",generalInfoModel.getCombinedRemarks());
        values.put("combinedNC",generalInfoModel.getCombinedNc());
        values.put("demarcated",generalInfoModel.getDemarcated());
        values.put("demarcatedRemarks",generalInfoModel.getDemarcatedRemarks());
        values.put("demarcatedNC",generalInfoModel.getDemarcatedNc());
        values.put("required",generalInfoModel.getRequired());
        values.put("requiredRemarks",generalInfoModel.getRequiredRemarks());
        values.put("requiredNC",generalInfoModel.getRequiredNC());
        values.put("available",generalInfoModel.getAvailable());
        values.put("availableRemarks",generalInfoModel.getAvailableRemarks());
        values.put("availableNC",generalInfoModel.getAvailableNC());


        if (mode.equalsIgnoreCase("draft")) {
            values.put("proc_tracker", 2);
        } else {
            values.put("proc_tracker", 3);
        }
        db.update("GeneralInfo", values, " YearWiseCollegeId= " + generalInfoModel.getYearWiseCollegeid(), null);

        return true;

    }

    public List<TechincalInfo> getTechnicalInfoList(String YearWiseCollegeId) {
        SQLiteDatabase db = null;
               String selectQueryQues ="Select yearwisecollegeid  ,designation ,designationRemarks , designationNC,name ," +
                       " nameRemarks , nameNC , fatherName , fatherNameRemarks , fatherNameNC , dob , dobRemarks ," +
                       " dobNC , joinDate , joinDateRemarks , joinDateNC , qualification , qualificationRemarks , " +
                       "qualificationNC , stream , streamRemarks , streamNC , passingYear , passingYearRemarks , passingYearNC , " +
                       "totalYoe , totalYoeRemarks ,totalYoeNC , account , accountRemarks ,accountNC , bankName , bankNameRemarks , " +
                       "bankNameNC , branchName , branchNameRemarks , branchNameNC , adharNo , adharNoRemarks , adharNoNC , salary , " +
                       "salaryRemarks , salaryNC , panCard , panCardRemarks , panCardNC , remarks , commonNc,remarks_Remarks," +
                       "remarks_Nc ,refId,proc_tracker " +
                       "from TechnicalInfo where YearWiseCollegeId = " + YearWiseCollegeId;

        List<TechincalInfo> technicalModelList = new ArrayList<TechincalInfo>();
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    TechincalInfo technicalInfoModel = new TechincalInfo();
                    technicalInfoModel.setYearwisecollegeid(cursor.getString(0));
                    technicalInfoModel.setDesignation(cursor.getString(1));
                    technicalInfoModel.setDesignationRemarks(cursor.getString(2));
                    technicalInfoModel.setDesignationNc(cursor.getInt(3));
                    technicalInfoModel.setName(cursor.getString(4));
                    technicalInfoModel.setNameRemarks(cursor.getString(5));
                    technicalInfoModel.setNameNc(cursor.getInt(6));
                    technicalInfoModel.setFatherName(cursor.getString(7));
                    technicalInfoModel.setFathernameRemarks(cursor.getString(8));
                    technicalInfoModel.setFathernameNc(cursor.getInt(9));
                    technicalInfoModel.setDob(cursor.getString(10));
                    technicalInfoModel.setDobRemarks(cursor.getString(11));
                    technicalInfoModel.setDobNc(cursor.getInt(12));
                    technicalInfoModel.setJoinDate(cursor.getString(13));
                    technicalInfoModel.setJoindateRemarks(cursor.getString(14));
                    technicalInfoModel.setJoindateNc(cursor.getInt(15));
                    technicalInfoModel.setQualification(cursor.getString(16));
                    technicalInfoModel.setQualificationRemarks(cursor.getString(17));
                    technicalInfoModel.setQualificationNc(cursor.getInt(18));
                    technicalInfoModel.setStream(cursor.getString(19));
                    technicalInfoModel.setStreamRemarks(cursor.getString(20));
                    technicalInfoModel.setStreamNc(cursor.getInt(21));
                    technicalInfoModel.setPassingYear(cursor.getString(22));
                    technicalInfoModel.setPassingyearRemark(cursor.getString(23));
                    technicalInfoModel.setPassingyearNc(cursor.getInt(24));
                    technicalInfoModel.setTotalYoe(cursor.getString(25));
                    technicalInfoModel.setTotalyoeRemarks(cursor.getString(26));
                    technicalInfoModel.setTotalyoeNc(cursor.getInt(27));
                    technicalInfoModel.setAccount(cursor.getString(28));
                    technicalInfoModel.setAccountRemarks(cursor.getString(29));
                    technicalInfoModel.setAccountNc(cursor.getInt(30));
                    technicalInfoModel.setBankName(cursor.getString(31));
                    technicalInfoModel.setBanknameRemarks(cursor.getString(32));
                    technicalInfoModel.setBanknameNc(cursor.getInt(33));
                    technicalInfoModel.setBranchName(cursor.getString(34));
                    technicalInfoModel.setBranchnameRemarks(cursor.getString(35));
                    technicalInfoModel.setBranchnameNc(cursor.getInt(36));
                    technicalInfoModel.setAdharNo(cursor.getString(37));
                    technicalInfoModel.setAdharnoRemarks(cursor.getString(38));
                    technicalInfoModel.setAdharnoNc(cursor.getInt(39));
                    technicalInfoModel.setSalary(cursor.getString(40));
                    technicalInfoModel.setSalaryRemarks(cursor.getString(41));
                    technicalInfoModel.setSalaryNC(cursor.getInt(42));
                    technicalInfoModel.setPan(cursor.getString(43));
                    technicalInfoModel.setPanRemarks(cursor.getString(44));
                    technicalInfoModel.setPanNC(cursor.getInt(45));
                    technicalInfoModel.setRemarks(cursor.getString(46));
                    technicalInfoModel.setCommonNc(cursor.getInt(47));
                    technicalInfoModel.setRemarksRemarks(cursor.getString(48));
                    technicalInfoModel.setRemarksNC(cursor.getInt(49));
                    technicalInfoModel.setRefId(cursor.getInt(50));
                    technicalInfoModel.setProc_tracker(cursor.getInt(51));

                    technicalModelList.add(technicalInfoModel);


                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }

        return technicalModelList;
    }

    public boolean addTechnicalInfoData(JSONObject result, String yearWiseCollegeId) {
        try{

            SQLiteDatabase database = getWritableDatabase();

            int status = result.optInt("status");
            if(status == 0){
                JSONObject payload = result.optJSONObject("payload");
                JSONArray technicalInfoArr = payload.optJSONArray("technicalstaff");
                for (int i = 0;i<technicalInfoArr.length();i++) {
                    ContentValues values = new ContentValues();
                    JSONObject technicalInfoObj = technicalInfoArr.optJSONObject(i);
                    values.put("yearwisecollegeid", yearWiseCollegeId);
                    values.put("refId",technicalInfoObj.optString("refId"));
                    values.put("designation", technicalInfoObj.optString("designation"));
                    values.put("name", technicalInfoObj.optString("name"));
                    values.put("fatherName", technicalInfoObj.optString("fatherName"));
                    values.put("dob", technicalInfoObj.optString("dob"));
                    values.put("joinDate", technicalInfoObj.optString("joinDate"));
                    values.put("qualification", technicalInfoObj.optString("qualification"));
                    values.put("stream", technicalInfoObj.optString("stream"));
                    values.put("passingYear", technicalInfoObj.optString("passingYear"));
                    values.put("totalYoe", technicalInfoObj.optString("totalYoe"));
                    values.put("account", technicalInfoObj.optString("account"));
                    values.put("bankName", technicalInfoObj.optString("bankName"));
                    values.put("branchName", technicalInfoObj.optString("branchName"));
                    values.put("adharNo", technicalInfoObj.optString("adharNo"));
                    values.put("proc_tracker", 1);
                    database.insert("TechnicalInfo", null, values);
                }
            }else {
                Toast.makeText(application_context, "No Data Found..", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    public TechincalInfo getTechnicalInfobyYearwiseCollegeId(String YearWiseCollegeId, String refId) {

        SQLiteDatabase db = null;
        String selectQueryQues ="Select yearwisecollegeid  ,designation ,designationRemarks , designationNC ,name ," +
                " nameRemarks , nameNC , fatherName , fatherNameRemarks , fatherNameNC , dob , dobRemarks ," +
                " dobNC , joinDate , joinDateRemarks , joinDateNC , qualification , qualificationRemarks , " +
                "qualificationNC , stream , streamRemarks , streamNC , passingYear , passingYearRemarks , passingYearNC , " +
                "totalYoe , totalYoeRemarks ,totalYoeNC , account , accountRemarks ,accountNC , bankName , bankNameRemarks , " +
                "bankNameNC , branchName , branchNameRemarks , branchNameNC , adharNo , adharNoRemarks , adharNoNC , salary , " +
                "salaryRemarks , salaryNC , panCard , panCardRemarks , panCardNC , remarks , commonNc,remarks_Remarks," +
                "remarks_Nc ,refId,proc_tracker " +
                "from TechnicalInfo where YearWiseCollegeId = " + YearWiseCollegeId + " and refId= " + refId;

        TechincalInfo technicalInfoModel = null;
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    technicalInfoModel = new TechincalInfo();
                    technicalInfoModel.setYearwisecollegeid(cursor.getString(0));
                    technicalInfoModel.setDesignation(cursor.getString(1));
                    technicalInfoModel.setDesignationRemarks(cursor.getString(2));
                    technicalInfoModel.setDesignationNc(cursor.getInt(3));
                    technicalInfoModel.setName(cursor.getString(4));
                    technicalInfoModel.setNameRemarks(cursor.getString(5));
                    technicalInfoModel.setNameNc(cursor.getInt(6));
                    technicalInfoModel.setFatherName(cursor.getString(7));
                    technicalInfoModel.setFathernameRemarks(cursor.getString(8));
                    technicalInfoModel.setFathernameNc(cursor.getInt(9));
                    technicalInfoModel.setDob(cursor.getString(10));
                    technicalInfoModel.setDobRemarks(cursor.getString(11));
                    technicalInfoModel.setDobNc(cursor.getInt(12));
                    technicalInfoModel.setJoinDate(cursor.getString(13));
                    technicalInfoModel.setJoindateRemarks(cursor.getString(14));
                    technicalInfoModel.setJoindateNc(cursor.getInt(15));
                    technicalInfoModel.setQualification(cursor.getString(16));
                    technicalInfoModel.setQualificationRemarks(cursor.getString(17));
                    technicalInfoModel.setQualificationNc(cursor.getInt(18));
                    technicalInfoModel.setStream(cursor.getString(19));
                    technicalInfoModel.setStreamRemarks(cursor.getString(20));
                    technicalInfoModel.setStreamNc(cursor.getInt(21));
                    technicalInfoModel.setPassingYear(cursor.getString(22));
                    technicalInfoModel.setPassingyearRemark(cursor.getString(23));
                    technicalInfoModel.setPassingyearNc(cursor.getInt(24));
                    technicalInfoModel.setTotalYoe(cursor.getString(25));
                    technicalInfoModel.setTotalyoeRemarks(cursor.getString(26));
                    technicalInfoModel.setTotalyoeNc(cursor.getInt(27));
                    technicalInfoModel.setAccount(cursor.getString(28));
                    technicalInfoModel.setAccountRemarks(cursor.getString(29));
                    technicalInfoModel.setAccountNc(cursor.getInt(30));
                    technicalInfoModel.setBankName(cursor.getString(31));
                    technicalInfoModel.setBanknameRemarks(cursor.getString(32));
                    technicalInfoModel.setBanknameNc(cursor.getInt(33));
                    technicalInfoModel.setBranchName(cursor.getString(34));
                    technicalInfoModel.setBranchnameRemarks(cursor.getString(35));
                    technicalInfoModel.setBranchnameNc(cursor.getInt(36));
                    technicalInfoModel.setAdharNo(cursor.getString(37));
                    technicalInfoModel.setAdharnoRemarks(cursor.getString(38));
                    technicalInfoModel.setAdharnoNc(cursor.getInt(39));
                    technicalInfoModel.setSalary(cursor.getString(40));
                    technicalInfoModel.setSalaryRemarks(cursor.getString(41));
                    technicalInfoModel.setSalaryNC(cursor.getInt(42));
                    technicalInfoModel.setPan(cursor.getString(43));
                    technicalInfoModel.setPanRemarks(cursor.getString(44));
                    technicalInfoModel.setPanNC(cursor.getInt(45));
                    technicalInfoModel.setRemarks(cursor.getString(46));
                    technicalInfoModel.setCommonNc(cursor.getInt(47));
                    technicalInfoModel.setRemarksRemarks(cursor.getString(48));
                    technicalInfoModel.setRemarksNC(cursor.getInt(49));
                    technicalInfoModel.setRefId(cursor.getInt(50));
                    technicalInfoModel.setProc_tracker(cursor.getInt(51));

                    technicalInfoModel.setRefId(Integer.valueOf(refId));



                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }

        return technicalInfoModel;
    }

    public List<InstructorInfo> getInstructorInfoList(String YearWiseCollegeId) {
        SQLiteDatabase db = null;
        String selectQueryQues ="Select yearwisecollegeid,tradename, tradenameRemarks, instructor, instructorRemarks, instructorNC, " +
                "name, nameRemarks, nameNC, fatherName, fatherNameRemarks,fatherNameNC, dateofbirth, dateofbirthRemarks,dateofbirthNC," +
                " joiningdate, joiningdateRemarks, joiningdateNC, qualification, qualificationRemarks, qualificationNC," +
                " passingyear, passingyearRemarks, passingyearNC, stream, streamRemarks,streamNC, totalexp, totalexpRemarks, " +
                "totalexpNC, bankname, banknameRemarks, banknameNC,branchname, branchnameRemarks, branchnameNC, accountno," +
                " accountnoRemarks, accountnoNC,aadharno, aadharnoRemarks, aadharnoNC, salary, salaryRemarks, salaryNC," +
                " remarks, remarksRemarks, remarksNC, commonNC,refId,tradeId,proc_tracker from InstructorITIInfo " +
                "where YearWiseCollegeId = " + YearWiseCollegeId;
        List<InstructorInfo> instructorModelList = new ArrayList<InstructorInfo>();
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    InstructorInfo instructorInfoModel = new InstructorInfo();
                    instructorInfoModel.setYearwisecollegeid(cursor.getString(0));
                    instructorInfoModel.setTradeName(cursor.getString(1));
                    instructorInfoModel.setTradeNameRemarks(cursor.getString(2));
                    instructorInfoModel.setInstructor(cursor.getString(3));
                    instructorInfoModel.setInstructorRemarks(cursor.getString(4));
                    instructorInfoModel.setInstructorNc(cursor.getInt(5));
                    instructorInfoModel.setName(cursor.getString(6));
                    instructorInfoModel.setNameRemarks(cursor.getString(7));
                    instructorInfoModel.setNameNc(cursor.getInt(8));
                    instructorInfoModel.setFathername(cursor.getString(9));
                    instructorInfoModel.setFathernameRemarks(cursor.getString(10));
                    instructorInfoModel.setFathernameNc(cursor.getInt(11));
                    instructorInfoModel.setDateofbirth(cursor.getString(12));
                    instructorInfoModel.setDateofbirthRemarks(cursor.getString(13));
                    instructorInfoModel.setDateofbirthNc(cursor.getInt(14));
                    instructorInfoModel.setJoiningdate(cursor.getString(15));
                    instructorInfoModel.setJoiningdateRemarks(cursor.getString(16));
                    instructorInfoModel.setJoiningdateNc(cursor.getInt(17));
                    instructorInfoModel.setQualification(cursor.getString(18));
                    instructorInfoModel.setQualificationRemarks(cursor.getString(19));
                    instructorInfoModel.setQualificationNc(cursor.getInt(20));
                    instructorInfoModel.setPassingyear(cursor.getString(21));
                    instructorInfoModel.setPassingyearRemarks(cursor.getString(22));
                    instructorInfoModel.setPassingyearNc(cursor.getInt(23));
                    instructorInfoModel.setStream(cursor.getString(24));
                    instructorInfoModel.setStreamRemarks(cursor.getString(25));
                    instructorInfoModel.setStreamNc(cursor.getInt(26));
                    instructorInfoModel.setTotalexp(cursor.getString(27));
                    instructorInfoModel.setTotalexpRemarks(cursor.getString(28));
                    instructorInfoModel.setTotalexpNc(cursor.getInt(29));
                    instructorInfoModel.setBankname(cursor.getString(30));
                    instructorInfoModel.setBanknameRemarks(cursor.getString(31));
                    instructorInfoModel.setBanknameNc(cursor.getInt(32));
                    instructorInfoModel.setBranchname(cursor.getString(33));
                    instructorInfoModel.setBranchnameRemarks(cursor.getString(34));
                    instructorInfoModel.setBranchnameNc(cursor.getInt(35));
                    instructorInfoModel.setAccountno(cursor.getString(36));
                    instructorInfoModel.setAccountnoRemarks(cursor.getString(37));
                    instructorInfoModel.setAccountnoNc(cursor.getInt(38));
                    instructorInfoModel.setAadharno(cursor.getString(39));
                    instructorInfoModel.setAadharnoRemarks(cursor.getString(40));
                    instructorInfoModel.setAadharnoNc(cursor.getInt(41));
                    instructorInfoModel.setSalary(cursor.getString(42));
                    instructorInfoModel.setSalaryRemarks(cursor.getString(43));
                    instructorInfoModel.setSalaryNC(cursor.getInt(44));
                    instructorInfoModel.setRemarks(cursor.getString(45));
                    instructorInfoModel.setRemarksRemarks(cursor.getString(46));
                    instructorInfoModel.setRemarksNC(cursor.getInt(47));
                    instructorInfoModel.setCommonNc(cursor.getInt(48));
                    instructorInfoModel.setRefId(cursor.getInt(49));
                    instructorInfoModel.setTradeId(cursor.getString(50));
                    instructorInfoModel.setProc_tracker(cursor.getInt(51));
                    instructorModelList.add(instructorInfoModel);


                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return instructorModelList;
    }

    public boolean addInstructorInfoData(JSONObject result, String yearWiseCollegeId) {
        try{

            SQLiteDatabase database = getWritableDatabase();

            int status = result.optInt("status");
            if(status == 0){
                JSONObject payload = result.optJSONObject("payload");
                JSONArray instructorInfoArr = payload.optJSONArray("instructorstaff");
                for(int i = 0;i<instructorInfoArr.length();i++) {
                    JSONObject instructorInfoObj = instructorInfoArr.optJSONObject(i);
                    ContentValues values = new ContentValues();
                    values.put("yearwisecollegeid", instructorInfoObj.optString("yearwisecollegeid"));
                    values.put("refId",instructorInfoObj.optString("refId"));
                    values.put("tradeId",instructorInfoObj.optString("tradeId"));
                    values.put("tradename", instructorInfoObj.optString("tradename"));
                    values.put("instructor", instructorInfoObj.optString("instructor"));
                    values.put("name", instructorInfoObj.optString("name"));
                    values.put("fathername", instructorInfoObj.optString("fathername"));
                    values.put("dateofbirth", instructorInfoObj.optString("dateofbirth"));
                    values.put("joiningdate", instructorInfoObj.optString("joiningdate"));
                    values.put("qualification", instructorInfoObj.optString("qualification"));
                    values.put("passingyear", instructorInfoObj.optString("passingyear"));
                    values.put("stream", instructorInfoObj.optString("stream"));
                    values.put("totalexp", instructorInfoObj.optString("totalexp"));
                    values.put("bankname", instructorInfoObj.optString("bankname"));
                    values.put("branchname", instructorInfoObj.optString("branchname"));
                    values.put("accountno", instructorInfoObj.optString("accountno"));
                    values.put("aadharno", instructorInfoObj.optString("aadharno"));
                    values.put("proc_tracker", 1);
                    database.insert("InstructorITIInfo", null, values);
                }
            }else {
                Toast.makeText(application_context, "No Data Found..", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    public InstructorInfo getInstructorInfobyYearwiseCollegeId(String YearWiseCollegeId, String refId) {

        SQLiteDatabase db = null;
        String selectQueryQues ="Select yearwisecollegeid,tradename, tradenameRemarks, instructor, instructorRemarks, instructorNC, " +
                "name, nameRemarks, nameNC, fatherName, fatherNameRemarks,fatherNameNC, dateofbirth, dateofbirthRemarks,dateofbirthNC," +
                " joiningdate, joiningdateRemarks, joiningdateNC, qualification, qualificationRemarks, qualificationNC," +
                " passingyear, passingyearRemarks, passingyearNC, stream, streamRemarks,streamNC, totalexp, totalexpRemarks, " +
                "totalexpNC, bankname, banknameRemarks, banknameNC,branchname, branchnameRemarks, branchnameNC, accountno," +
                " accountnoRemarks, accountnoNC,aadharno, aadharnoRemarks, aadharnoNC, salary, salaryRemarks, salaryNC," +
                " remarks, remarksRemarks, remarksNC, commonNC,refId,tradeId,proc_tracker from InstructorITIInfo where YearWiseCollegeId = " + YearWiseCollegeId + " and refId= " + refId;
        InstructorInfo instructorInfoModel = null;
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    instructorInfoModel = new InstructorInfo();
                    instructorInfoModel.setYearwisecollegeid(cursor.getString(0));
                    instructorInfoModel.setTradeName(cursor.getString(1));
                    instructorInfoModel.setTradeNameRemarks(cursor.getString(2));
                    instructorInfoModel.setInstructor(cursor.getString(3));
                    instructorInfoModel.setInstructorRemarks(cursor.getString(4));
                    instructorInfoModel.setInstructorNc(cursor.getInt(5));
                    instructorInfoModel.setName(cursor.getString(6));
                    instructorInfoModel.setNameRemarks(cursor.getString(7));
                    instructorInfoModel.setNameNc(cursor.getInt(8));
                    instructorInfoModel.setFathername(cursor.getString(9));
                    instructorInfoModel.setFathernameRemarks(cursor.getString(10));
                    instructorInfoModel.setFathernameNc(cursor.getInt(11));
                    instructorInfoModel.setDateofbirth(cursor.getString(12));
                    instructorInfoModel.setDateofbirthRemarks(cursor.getString(13));
                    instructorInfoModel.setDateofbirthNc(cursor.getInt(14));
                    instructorInfoModel.setJoiningdate(cursor.getString(15));
                    instructorInfoModel.setJoiningdateRemarks(cursor.getString(16));
                    instructorInfoModel.setJoiningdateNc(cursor.getInt(17));
                    instructorInfoModel.setQualification(cursor.getString(18));
                    instructorInfoModel.setQualificationRemarks(cursor.getString(19));
                    instructorInfoModel.setQualificationNc(cursor.getInt(20));
                    instructorInfoModel.setPassingyear(cursor.getString(21));
                    instructorInfoModel.setPassingyearRemarks(cursor.getString(22));
                    instructorInfoModel.setPassingyearNc(cursor.getInt(23));
                    instructorInfoModel.setStream(cursor.getString(24));
                    instructorInfoModel.setStreamRemarks(cursor.getString(25));
                    instructorInfoModel.setStreamNc(cursor.getInt(26));
                    instructorInfoModel.setTotalexp(cursor.getString(27));
                    instructorInfoModel.setTotalexpRemarks(cursor.getString(28));
                    instructorInfoModel.setTotalexpNc(cursor.getInt(29));
                    instructorInfoModel.setBankname(cursor.getString(30));
                    instructorInfoModel.setBanknameRemarks(cursor.getString(31));
                    instructorInfoModel.setBanknameNc(cursor.getInt(32));
                    instructorInfoModel.setBranchname(cursor.getString(33));
                    instructorInfoModel.setBranchnameRemarks(cursor.getString(34));
                    instructorInfoModel.setBranchnameNc(cursor.getInt(35));
                    instructorInfoModel.setAccountno(cursor.getString(36));
                    instructorInfoModel.setAccountnoRemarks(cursor.getString(37));
                    instructorInfoModel.setAccountnoNc(cursor.getInt(38));
                    instructorInfoModel.setAadharno(cursor.getString(39));
                    instructorInfoModel.setAadharnoRemarks(cursor.getString(40));
                    instructorInfoModel.setAadharnoNc(cursor.getInt(41));
                    instructorInfoModel.setSalary(cursor.getString(42));
                    instructorInfoModel.setSalaryRemarks(cursor.getString(43));
                    instructorInfoModel.setSalaryNC(cursor.getInt(44));
                    instructorInfoModel.setRemarks(cursor.getString(45));
                    instructorInfoModel.setRemarksRemarks(cursor.getString(46));
                    instructorInfoModel.setRemarksNC(cursor.getInt(47));
                    instructorInfoModel.setCommonNc(cursor.getInt(48));
                    instructorInfoModel.setRefId(cursor.getInt(49));
                    instructorInfoModel.setTradeId(cursor.getString(50));
                    instructorInfoModel.setProc_tracker(cursor.getInt(51));

                    instructorInfoModel.setRefId(Integer.valueOf(refId));

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return instructorInfoModel;
    }

    public List<Accommodation> getTradesInfoList(String YearWiseCollegeId) {
        SQLiteDatabase db = null;
        String selectQueryQues ="Select YearWiseCollegeId,tradeid,nameofTrade,shift1,shift2,shift3,total,proc_tracker from TradeInfo where YearWiseCollegeId = " + YearWiseCollegeId;

        List<Accommodation> tradeInfoList = new ArrayList<Accommodation>();
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    Accommodation tradeInfoModel = new Accommodation();
                    tradeInfoModel.setYearWiseCollegeid(cursor.getString(0));
                    tradeInfoModel.setTradeid(cursor.getString(1));
                    tradeInfoModel.setNameofTrade(cursor.getString(2));
                    tradeInfoModel.setShift1(cursor.getString(3));
                    tradeInfoModel.setShift2(cursor.getString(4));
                    tradeInfoModel.setShift3(cursor.getString(5));
                    tradeInfoModel.setTotal(cursor.getString(6));
                    tradeInfoModel.setProc_tracker(cursor.getInt(7));
                    tradeInfoList.add(tradeInfoModel);


                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return tradeInfoList;
    }

    public List<Accommodation> getToolsAboveTenkInfoList(String YearWiseCollegeId) {
        SQLiteDatabase db = null;
        String selectQueryQues ="Select YearWiseCollegeId,tradeid,nameofTrade,shift1,shift2,shift3,total,proc_tracker from ToolsAboveTenK where YearWiseCollegeId = " + YearWiseCollegeId ;

        List<Accommodation> tradeInfoList = new ArrayList<Accommodation>();
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    Accommodation tradeInfoModel = new Accommodation();
                    tradeInfoModel.setYearWiseCollegeid(cursor.getString(0));
                    tradeInfoModel.setTradeid(cursor.getString(1));
                    tradeInfoModel.setNameofTrade(cursor.getString(2));
                    tradeInfoModel.setShift1(cursor.getString(3));
                    tradeInfoModel.setShift2(cursor.getString(4));
                    tradeInfoModel.setShift3(cursor.getString(5));
                    tradeInfoModel.setTotal(cursor.getString(6));
                    tradeInfoModel.setProc_tracker(cursor.getInt(7));
                    tradeInfoList.add(tradeInfoModel);




                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return tradeInfoList;
    }

    public boolean addTradesInfoData(JSONObject result, String yearWiseCollegeId) {
        try{

            SQLiteDatabase database = getWritableDatabase();

            int status = result.optInt("status");
            if(status == 0) {
                JSONObject payload = result.optJSONObject("payload");
                JSONArray tradeArray = payload.optJSONArray("FinalTrade");
                for (int i = 0; i < tradeArray.length(); i++) {
                    JSONObject tradeObj = tradeArray.getJSONObject(i);
                    ContentValues values = new ContentValues();
                    values.put("YearWiseCollegeId",yearWiseCollegeId);
                    values.put("tradeid", tradeObj.optString("tradeid"));
                    values.put("nameofTrade", tradeObj.optString("nameofTrade"));
                    values.put("shift1", tradeObj.optString("shift1"));
                    values.put("shift2", tradeObj.optString("shift2"));
                    values.put("shift3", tradeObj.optString("shift3"));
                    values.put("total", tradeObj.optString("total"));
                    values.put("proc_tracker", 1);
                    database.insert("TradeInfo", null, values);
                }
            }else {
                Toast.makeText(application_context, "No Data Found..", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    public boolean addToolsAboveTenKInfoData(JSONObject result, String yearWiseCollegeId) {
        try{

            SQLiteDatabase database = getWritableDatabase();

            int status = result.optInt("status");
            if(status == 0) {
                JSONObject payload = result.optJSONObject("payload");
                JSONArray tradeArray = payload.optJSONArray("FinalTrade");
                for (int i = 0; i < tradeArray.length(); i++) {
                    JSONObject tradeObj = tradeArray.getJSONObject(i);
                    ContentValues values = new ContentValues();
                    values.put("YearWiseCollegeId",yearWiseCollegeId);
                    values.put("tradeid", tradeObj.optString("tradeid"));
                    values.put("nameofTrade", tradeObj.optString("nameofTrade"));
                    values.put("shift1", tradeObj.optString("shift1"));
                    values.put("shift2", tradeObj.optString("shift2"));
                    values.put("shift3", tradeObj.optString("shift3"));
                    values.put("total", tradeObj.optString("total"));
                    values.put("proc_tracker", 1);
                    database.insert("ToolsAboveTenK", null, values);
                }
            }else {
                Toast.makeText(application_context, "No Data Found..", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    public Accommodation getTradesInfobyYearwiseCollegeId(String YearWiseCollegeId, String tradeId) {
        SQLiteDatabase db = null;
        String selectQueryQues ="Select YearWiseCollegeId,tradeid,nameofTrade,shift1,shift2,shift3,total,proc_tracker from TradeInfo where YearWiseCollegeId = " + YearWiseCollegeId + " and tradeId= " + tradeId ;

        Accommodation tradeInfoModel = null;
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    tradeInfoModel = new Accommodation();
                    tradeInfoModel.setYearWiseCollegeid(cursor.getString(0));
                    tradeInfoModel.setTradeid(cursor.getString(1));
                    tradeInfoModel.setNameofTrade(cursor.getString(2));
                    tradeInfoModel.setShift1(cursor.getString(3));
                    tradeInfoModel.setShift2(cursor.getString(4));
                    tradeInfoModel.setShift3(cursor.getString(5));
                    tradeInfoModel.setTotal(cursor.getString(6));
                    tradeInfoModel.setProc_tracker(cursor.getInt(7));

                    tradeInfoModel.setTradeid(tradeId);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return tradeInfoModel;
    }

    public List<ITLab> getITLabInfoList(String YearWiseCollegeId) {
        SQLiteDatabase db = null;
        String selectQueryQues ="Select yearWiseCollegeid ,nameofequipment ,category , quantity , refId , available , Area ," +
                " AreaRemarks , AreaNc , Lab , LabRemarks ,LabNC, Internet , InternetRemarks , InternetNC , Roof , RoofRemarks " +
                ",RoofNC , Height , HeightRemarks , HeightNC , Tin , TinRemarks , TinNC , Floor , FloorRemarks , FloorNC,"+
                "proc_tracker from LabInfo where YearWiseCollegeId = " + YearWiseCollegeId;
        List<ITLab> labModelList = new ArrayList<ITLab>();
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);

            if (cursor.moveToFirst()) {

                do {
                    ITLab labInfoModel = new ITLab();
                    labInfoModel.setYearWiseCollegeid(cursor.getString(0));
                    labInfoModel.setNameofequipment(cursor.getString(1));
                    labInfoModel.setCategory(cursor.getString(2));
                    labInfoModel.setQuantity(cursor.getString(3));
                    labInfoModel.setRefId(cursor.getInt(4));
                    labInfoModel.setAvailable(cursor.getInt(5));
                    labInfoModel.setActualArea(cursor.getString(6));
                    labInfoModel.setActualAreaRemarks(cursor.getString(7));
                    labInfoModel.setActualAreaNc(cursor.getInt(8));
                    labInfoModel.setLab(cursor.getString(9));
                    labInfoModel.setLabRemarks(cursor.getString(10));
                    labInfoModel.setLabNC(cursor.getInt(11));
                    labInfoModel.setInternet(cursor.getString(12));
                    labInfoModel.setInternetRemarks(cursor.getString(13));
                    labInfoModel.setInternetNC(cursor.getInt(14));
                    labInfoModel.setRoof(cursor.getString(15));
                    labInfoModel.setRoofRemarks(cursor.getString(16));
                    labInfoModel.setRoofNC(cursor.getInt(17));
                    labInfoModel.setHeight(cursor.getString(18));
                    labInfoModel.setHeightRemarks(cursor.getString(19));
                    labInfoModel.setHeightNC(cursor.getInt(20));
                    labInfoModel.setTin(cursor.getString(21));
                    labInfoModel.setTinRemarks(cursor.getString(22));
                    labInfoModel.setTinNC(cursor.getInt(23));
                    labInfoModel.setFloor(cursor.getString(24));
                    labInfoModel.setFloorRemarks(cursor.getString(25));
                    labInfoModel.setFloorNC(cursor.getInt(26));
                    labInfoModel.setProc_tracker(cursor.getInt(27));
                    labModelList.add(labInfoModel);


                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return labModelList;
    }

    public boolean addITLabInfoData(JSONObject result, String yearWiseCollegeId) {
        try{
            SQLiteDatabase database = getWritableDatabase();
            int status = result.optInt("status");
            if(status == 0){
                JSONObject payloadObj = result.optJSONObject("payload");
                JSONArray it_lablistArr = payloadObj.optJSONArray("it_lablist");
                for (int i = 0;i<it_lablistArr.length();i++) {
                    JSONObject it_lablistObj = it_lablistArr.optJSONObject(i);
                    values.put("nameofequipment", it_lablistObj.optString("nameofequipment"));
                    values.put("category", it_lablistObj.optString("category"));
                    values.put("quantity", it_lablistObj.optString("quantity"));
                    JSONObject it_labObj = payloadObj.optJSONObject("it_lab");
                    values.put("yearWiseCollegeid", yearWiseCollegeId);
                    values.put("refId",it_labObj.optString("refId"));
                    values.put("available",it_labObj.optString("available"));
                    values.put("Area",it_labObj.optString("actualArea"));
                    values.put("AreaRemarks",it_labObj.optString("actualAreaRemarks"));
                    values.put("AreaNc",it_labObj.optString("actualAreaNc"));
                    values.put("proc_tracker", 1);
                    database.insert("LabInfo", null, values);

                }
            }else {
                Toast.makeText(application_context, "No Data Found..", Toast.LENGTH_LONG).show();
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public List<LandandBuilding> getLandandBuildingInfoList(String YearWiseCollegeId) {
        SQLiteDatabase db = null;
        String selectQueryQues ="Select yearwisecollegeid,buildingType, buildingTypeRemarks, buildingTypeNC, dateofOccupancy," +
                " dateofOccupancyRemarks, dateofOccupancyNC, openArea, openAreaRemarks, openAreaNC, buildArea, buildAreaRemarks, " +
                "buildAreaNC, land, landRemarks, landNC, liftInstitute, liftInstituteRemarks, liftInstituteNC, noFloor, " +
                "noFloorRemarks, noFloorNC, orgInstalledLift, orgInstalledLiftRemarks, orgInstalledLiftNC, capacityofLift," +
                " capacityofLiftRemarks, capacityofLiftNC, safetyLift, safetyLiftRemarks, safetyLiftNC, workshopRoof, " +
                "workshopRoofRemarks,workshopRoofNC, buildingPlan, buildingPlanRemarks,buildingPlanNC, wallOfIti," +
                " wallOfItiRemarks, wallOfItiNC, floorisCemented, floorisCementedRemarks, floorisCementedNC, sameCampus, " +
                "sameCampusRemarks, sameCampusNC, seperateWashroom, seperateWashroomRemarks, seperateWashroomNC, washroomFunctional," +
                " washroomFunctionalRemarks, washroomFunctionalNC, fire, fireRemarks, fireNC, durationlease, durationleaseRemarks, " +
                "durationleaseNC, expiryofAgreement, expiryofAgreementRemarks, expiryofAgreementNC, safeWater , safeWaterRemarks , " +
                "safeWaterNC ,houseKeeping , houseKeepingRemarks , houseKeepingNC , placementCell , placementCellRemarks ," +
                " placementCellNC , masterPlan, masterPlanRemarks, masterPlanNC , comptent, comptentRemarks , comptentNC,sitmap," +
                " sitmapRemarks , sitmapNC, dimension,dimensionRemarks, dimensionNC, map, mapRemarks , mapNC, approachRoad," +
                " approachRoadRemarks , approachRoadNC, entranceRoad, entranceRoadRemarks, entranceRoadNC, soundProof, " +
                "soundProofRemarks , soundProofNC, shared, sharedRemarks , sharedNC, seperateEntrance, seperateEntranceRemarks, " +
                "seperateEntranceNC, switchBoard, switchBoardRemarks , switchBoardNC,ventilated,ventilatedRemarks,ventilatedNC," +
                " plotSize, plotSizeRemarks , plotSizeNC, BCC, BCCRemarks , BCCNC, staircase, staircaseRemarks, staircaseNC," +
                "refId,proc_tracker from LandInfo where YearWiseCollegeId = " + YearWiseCollegeId;
        List<LandandBuilding> labModelList = new ArrayList<LandandBuilding>();
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    LandandBuilding landandBuildingInfoModel = new LandandBuilding();
                    landandBuildingInfoModel.setYearwisecollegeid(cursor.getString(0));
                    landandBuildingInfoModel.setBuildingType(cursor.getString(1));
                    landandBuildingInfoModel.setBuildingTypeRemarks(cursor.getString(2));
                    landandBuildingInfoModel.setBuildingTypeNc(cursor.getInt(3));
                    landandBuildingInfoModel.setDateofOccupancy(cursor.getString(4));
                    landandBuildingInfoModel.setDateofOccupancyRemarks(cursor.getString(5));
                    landandBuildingInfoModel.setDateofOccupancyNc(cursor.getInt(6));
                    landandBuildingInfoModel.setTotalOpenArea(cursor.getString(7));
                    landandBuildingInfoModel.setTotalOpenAreaRemarks(cursor.getString(8));
                    landandBuildingInfoModel.setTotalOpenAreaNc(cursor.getInt(9));
                    landandBuildingInfoModel.setTotalBuildArea(cursor.getString(10));
                    landandBuildingInfoModel.setTotalBuildAreaRemarks(cursor.getString(11));
                    landandBuildingInfoModel.setTotalBuildAreaNc(cursor.getInt(12));
                    landandBuildingInfoModel.setTotalLand(cursor.getString(13));
                    landandBuildingInfoModel.setTotalLandRemarks(cursor.getString(14));
                    landandBuildingInfoModel.setTotalLandNc(cursor.getInt(15));
                    landandBuildingInfoModel.setLiftinInstitute(cursor.getString(16));
                    landandBuildingInfoModel.setLiftinInstituteRemarks(cursor.getString(17));
                    landandBuildingInfoModel.setLiftinInstituteNc(cursor.getInt(18));
                    landandBuildingInfoModel.setNoofFloors(cursor.getString(19));
                    landandBuildingInfoModel.setNoofFloorsRemarks(cursor.getString(20));
                    landandBuildingInfoModel.setNoofFloorsNc(cursor.getInt(21));
                    landandBuildingInfoModel.setOrgInstalledLift(cursor.getString(22));
                    landandBuildingInfoModel.setOrgInstalledLiftRemarks(cursor.getString(23));
                    landandBuildingInfoModel.setOrgInstalledLiftNc(cursor.getInt(24));
                    landandBuildingInfoModel.setCapacityofLift(cursor.getString(25));
                    landandBuildingInfoModel.setCapacityofLiftRemarks(cursor.getString(26));
                    landandBuildingInfoModel.setCapacityofLiftNc(cursor.getInt(27));
                    landandBuildingInfoModel.setSafetyCertificateofLift(cursor.getString(28));
                    landandBuildingInfoModel.setSafetyCertificateofLiftRemarks(cursor.getString(29));
                    landandBuildingInfoModel.setSafetyCertificateofLiftNc(cursor.getInt(30));
                    landandBuildingInfoModel.setWorkshopRoof(cursor.getString(31));
                    landandBuildingInfoModel.setWorkshopRoofRemarks(cursor.getString(32));
                    landandBuildingInfoModel.setWorkshopRoofNc(cursor.getInt(33));
                    landandBuildingInfoModel.setBuildingPlanofInstitute(cursor.getString(34));
                    landandBuildingInfoModel.setBuildingPlanofInstituteRemarks(cursor.getString(35));
                    landandBuildingInfoModel.setBuildingPlanofInstituteNc(cursor.getInt(36));
                    landandBuildingInfoModel.setWallOfIti(cursor.getString(37));
                    landandBuildingInfoModel.setWallOfItiRemarks(cursor.getString(38));
                    landandBuildingInfoModel.setWallOfItiNc(cursor.getInt(39));
                    landandBuildingInfoModel.setFloorisCemented(cursor.getString(40));
                    landandBuildingInfoModel.setFloorisCementedRemarks(cursor.getString(41));
                    landandBuildingInfoModel.setFloorisCementedNc(cursor.getInt(42));
                    landandBuildingInfoModel.setItiSituatedintheSameCampus(cursor.getString(43));
                    landandBuildingInfoModel.setItiSituatedintheSameCampusRemarks(cursor.getString(44));
                    landandBuildingInfoModel.setItiSituatedintheSameCampusNc(cursor.getInt(45));
                    landandBuildingInfoModel.setAvaibilityofSeparateMaleFemaleWashroom(cursor.getString(46));
                    landandBuildingInfoModel.setAvaibilityofSeparateMaleFemaleWashroomRemarks(cursor.getString(47));
                    landandBuildingInfoModel.setAvaibilityofSeparateMaleFemaleWashroomNc(cursor.getInt(48));
                    landandBuildingInfoModel.setAretheWashroomFunctional(cursor.getString(49));
                    landandBuildingInfoModel.setAretheWashroomFunctionalRemarks(cursor.getString(50));
                    landandBuildingInfoModel.setAretheWashroomFunctionalNc(cursor.getInt(51));
                    landandBuildingInfoModel.setAvaibiltyofFireextinguisher(cursor.getString(52));
                    landandBuildingInfoModel.setAvaibiltyofFireextinguisherRemarks(cursor.getString(53));
                    landandBuildingInfoModel.setAvaibiltyofFireextinguisherNc(cursor.getInt(54));
                    landandBuildingInfoModel.setDurationlease(cursor.getString(55));
                    landandBuildingInfoModel.setDurationleaseRemarks(cursor.getString(56));
                    landandBuildingInfoModel.setDurationleaseNc(cursor.getInt(57));
                    landandBuildingInfoModel.setExpiryofAgreement(cursor.getString(58));
                    landandBuildingInfoModel.setExpiryofagreementRemarks(cursor.getString(59));
                    landandBuildingInfoModel.setExpiryofagreementNC(cursor.getInt(60));
                    landandBuildingInfoModel.setAvaibilityofsafeDrinkingwater(cursor.getString(61));
                    landandBuildingInfoModel.setAvaibilityofsafedrinkingwaterRemarks(cursor.getString(62));
                    landandBuildingInfoModel.setAvaibilityofsafedrinkingwaterNc(cursor.getInt(63));
                    landandBuildingInfoModel.setAvaibilityHousekeepingstaff(cursor.getString(64));
                    landandBuildingInfoModel.setAvaibilityhousekeepingstaffRemarks(cursor.getString(65));
                    landandBuildingInfoModel.setAvaibilityhousekeepingstaffNc(cursor.getInt(66));
                    landandBuildingInfoModel.setAvaibilityofPlacementcell(cursor.getString(67));
                    landandBuildingInfoModel.setAvaibilityofplacementcellRemarks(cursor.getString(68));
                    landandBuildingInfoModel.setAvaibilityofplacementcellNc(cursor.getInt(69));
                    landandBuildingInfoModel.setIsMasterPlan(cursor.getString(70));
                    landandBuildingInfoModel.setIsMasterPlanRemarks(cursor.getString(71));
                    landandBuildingInfoModel.setIsMasterPlanNC(cursor.getInt(72));
                    landandBuildingInfoModel.setComptent(cursor.getString(73));
                    landandBuildingInfoModel.setComptentRemarks(cursor.getString(74));
                    landandBuildingInfoModel.setComptentNC(cursor.getInt(75));
                    landandBuildingInfoModel.setSitmap(cursor.getString(76));
                    landandBuildingInfoModel.setSitmapRemarks(cursor.getString(77));
                    landandBuildingInfoModel.setSitmapNc(cursor.getInt(78));
                    landandBuildingInfoModel.setDimension(cursor.getString(79));
                    landandBuildingInfoModel.setDimensionRemarks(cursor.getString(80));
                    landandBuildingInfoModel.setDimensionNC(cursor.getInt(81));
                    landandBuildingInfoModel.setMap(cursor.getString(82));
                    landandBuildingInfoModel.setMapRemarks(cursor.getString(83));
                    landandBuildingInfoModel.setMapNC(cursor.getInt(84));
                    landandBuildingInfoModel.setApproach(cursor.getString(85));
                    landandBuildingInfoModel.setApproachRemarks(cursor.getString(86));
                    landandBuildingInfoModel.setApproachNC(cursor.getInt(87));
                    landandBuildingInfoModel.setEntrance(cursor.getString(88));
                    landandBuildingInfoModel.setEntranceRemarks(cursor.getString(89));
                    landandBuildingInfoModel.setEntranceNC(cursor.getInt(90));
                    landandBuildingInfoModel.setSoundProofPartition(cursor.getString(91));
                    landandBuildingInfoModel.setSoundProofPartitionRemarks(cursor.getString(92));
                    landandBuildingInfoModel.setISoundProofPartitionNC(cursor.getInt(93));
                    landandBuildingInfoModel.setIsPremisesShared(cursor.getString(91));
                    landandBuildingInfoModel.setIsPremisesSharedRemarks(cursor.getString(92));
                    landandBuildingInfoModel.setIsPremisesSharedNC(cursor.getInt(93));
                    landandBuildingInfoModel.setSeperateEntrance(cursor.getString(94));
                    landandBuildingInfoModel.setSeperateEntranceRemarks(cursor.getString(95));
                    landandBuildingInfoModel.setSeperateEntranceNC(cursor.getInt(96));
                    landandBuildingInfoModel.setSwitchBoard(cursor.getString(97));
                    landandBuildingInfoModel.setSwitchBoardRemarks(cursor.getString(98));
                    landandBuildingInfoModel.setSwitchBoardNC(cursor.getInt(99));
                    landandBuildingInfoModel.setVentilation(cursor.getString(100));
                    landandBuildingInfoModel.setVentilationRemarks(cursor.getString(101));
                    landandBuildingInfoModel.setVentilationNC(cursor.getInt(102));
                    landandBuildingInfoModel.setConstructed(cursor.getString(103));
                    landandBuildingInfoModel.setConstructedRemarks(cursor.getString(104));
                    landandBuildingInfoModel.setConstructedNC(cursor.getInt(105));
                    landandBuildingInfoModel.setPrescribed(cursor.getString(106));
                    landandBuildingInfoModel.setPrescribedRemarks(cursor.getString(107));
                    landandBuildingInfoModel.setPrescribedNC(cursor.getInt(108));
                    landandBuildingInfoModel.setStaircase(cursor.getString(109));
                    landandBuildingInfoModel.setStaircaseRemarks(cursor.getString(110));
                    landandBuildingInfoModel.setStaircaseNC(cursor.getInt(111));
                    landandBuildingInfoModel.setRefId(cursor.getInt(112));
                    landandBuildingInfoModel.setProc_tracker(cursor.getInt(113));
                           labModelList.add(landandBuildingInfoModel);


                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return labModelList;
    }

    public boolean addLandandbuildingInfoData(JSONObject result, String yearWiseCollegeId) {
        try{

            SQLiteDatabase database = getWritableDatabase();

            int status = result.optInt("status");
            if(status == 0){
                JSONObject payload = result.getJSONObject("payload");
                JSONObject LandandBuildingInfoObj = payload.getJSONObject("LandandBuildingDetailsApi");
                ContentValues values = new ContentValues();
                values.put("yearwisecollegeid",yearWiseCollegeId);
                values.put("refId",LandandBuildingInfoObj.optString("refId"));
                values.put("buildingType",LandandBuildingInfoObj.optString("buildingType"));
                values.put("dateofOccupancy",LandandBuildingInfoObj.optString("dateofOccupancy"));
                values.put("openArea",LandandBuildingInfoObj.optString("totalOpenArea"));
                values.put("buildArea",LandandBuildingInfoObj.optString("totalBuildArea"));
                values.put("land",LandandBuildingInfoObj.optString("totalLand"));
                values.put("liftInstitute",LandandBuildingInfoObj.optString("liftinInstitute"));
                values.put("noFloor",LandandBuildingInfoObj.optString("noofFloors"));
                values.put("orgInstalledLift",LandandBuildingInfoObj.optString("orgInstalledLift"));
                values.put("capacityofLift",LandandBuildingInfoObj.optString("capacityofLift"));
                values.put("safetyLift",LandandBuildingInfoObj.optString("safetyCertificateofLift"));
                values.put("workshopRoof",LandandBuildingInfoObj.optString("workshopRoof"));
                values.put("buildingPlan",LandandBuildingInfoObj.optString("buildingPlanofInstitute"));
                values.put("wallOfIti",LandandBuildingInfoObj.optString("wallOfIti"));
                values.put("floorisCemented",LandandBuildingInfoObj.optString("floorisCemented"));
                values.put("sameCampus",LandandBuildingInfoObj.optString("itiSituatedintheSameCampus"));
                values.put("seperateWashroom",LandandBuildingInfoObj.optString("AvaibilityofSeparateMaleFemaleWashroom"));
                values.put("washroomFunctional",LandandBuildingInfoObj.optString("AretheWashroomFunctional"));
                values.put("fire",LandandBuildingInfoObj.optString("AvaibiltyofFireextinguisher"));
                values.put("durationlease",LandandBuildingInfoObj.optString("durationlease"));
                values.put("expiryofAgreement",LandandBuildingInfoObj.optString("expiryofAgreement"));
                values.put("safeWater ",LandandBuildingInfoObj.optString("avaibilityofsafeDrinkingwater "));
                values.put("houseKeeping ",LandandBuildingInfoObj.optString("avaibilityHousekeepingstaff "));
                values.put("placementCell ",LandandBuildingInfoObj.optString("avaibilityofPlacementcell "));
                values.put("proc_tracker", 1);
                database.insert("LandInfo", null, values);
            }else {
                Toast.makeText(application_context, "No Data Found..", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    public LandandBuilding getLandInfobyYearwiseCollegeId(String YearWiseCollegeId) {
        SQLiteDatabase db = null;
        String selectQueryQues ="Select yearwisecollegeid,buildingType, buildingTypeRemarks, buildingTypeNC, dateofOccupancy," +
                " dateofOccupancyRemarks, dateofOccupancyNC, openArea, openAreaRemarks, openAreaNC, buildArea, buildAreaRemarks, " +
                "buildAreaNC, land, landRemarks, landNC, liftInstitute, liftInstituteRemarks, liftInstituteNC, noFloor, " +
                "noFloorRemarks, noFloorNC, orgInstalledLift, orgInstalledLiftRemarks, orgInstalledLiftNC, capacityofLift," +
                " capacityofLiftRemarks, capacityofLiftNC, safetyLift, safetyLiftRemarks, safetyLiftNC, workshopRoof, " +
                "workshopRoofRemarks,workshopRoofNC, buildingPlan, buildingPlanRemarks,buildingPlanNC, wallOfIti," +
                " wallOfItiRemarks, wallOfItiNC, floorisCemented, floorisCementedRemarks, floorisCementedNC, sameCampus, " +
                "sameCampusRemarks, sameCampusNC, seperateWashroom, seperateWashroomRemarks, seperateWashroomNC, washroomFunctional," +
                " washroomFunctionalRemarks, washroomFunctionalNC, fire, fireRemarks, fireNC, durationlease, durationleaseRemarks, " +
                "durationleaseNC, expiryofAgreement, expiryofAgreementRemarks, expiryofAgreementNC, safeWater , safeWaterRemarks , " +
                "safeWaterNC ,houseKeeping , houseKeepingRemarks , houseKeepingNC , placementCell , placementCellRemarks ," +
                " placementCellNC , masterPlan, masterPlanRemarks, masterPlanNC , comptent, comptentRemarks , comptentNC,sitmap," +
                " sitmapRemarks , sitmapNC, dimension,dimensionRemarks, dimensionNC, map, mapRemarks , mapNC, approachRoad," +
                " approachRoadRemarks , approachRoadNC, entranceRoad, entranceRoadRemarks, entranceRoadNC, soundProof, " +
                "soundProofRemarks , soundProofNC, shared, sharedRemarks , sharedNC, seperateEntrance, seperateEntranceRemarks, " +
                "seperateEntranceNC, switchBoard, switchBoardRemarks , switchBoardNC,ventilated,ventilatedRemarks,ventilatedNC," +
                " plotSize, plotSizeRemarks , plotSizeNC, BCC, BCCRemarks , BCCNC, staircase, staircaseRemarks, staircaseNC," +
                "refId,proc_tracker from LandInfo where YearWiseCollegeId = " + YearWiseCollegeId;
        LandandBuilding landandBuildingInfoModel = null;
            try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    landandBuildingInfoModel = new LandandBuilding();
                    landandBuildingInfoModel.setYearwisecollegeid(cursor.getString(0));
                    landandBuildingInfoModel.setBuildingType(cursor.getString(1));
                    landandBuildingInfoModel.setBuildingTypeRemarks(cursor.getString(2));
                    landandBuildingInfoModel.setBuildingTypeNc(cursor.getInt(3));
                    landandBuildingInfoModel.setDateofOccupancy(cursor.getString(4));
                    landandBuildingInfoModel.setDateofOccupancyRemarks(cursor.getString(5));
                    landandBuildingInfoModel.setDateofOccupancyNc(cursor.getInt(6));
                    landandBuildingInfoModel.setTotalOpenArea(cursor.getString(7));
                    landandBuildingInfoModel.setTotalOpenAreaRemarks(cursor.getString(8));
                    landandBuildingInfoModel.setTotalOpenAreaNc(cursor.getInt(9));
                    landandBuildingInfoModel.setTotalBuildArea(cursor.getString(10));
                    landandBuildingInfoModel.setTotalBuildAreaRemarks(cursor.getString(11));
                    landandBuildingInfoModel.setTotalBuildAreaNc(cursor.getInt(12));
                    landandBuildingInfoModel.setTotalLand(cursor.getString(13));
                    landandBuildingInfoModel.setTotalLandRemarks(cursor.getString(14));
                    landandBuildingInfoModel.setTotalLandNc(cursor.getInt(15));
                    landandBuildingInfoModel.setLiftinInstitute(cursor.getString(16));
                    landandBuildingInfoModel.setLiftinInstituteRemarks(cursor.getString(17));
                    landandBuildingInfoModel.setLiftinInstituteNc(cursor.getInt(18));
                    landandBuildingInfoModel.setNoofFloors(cursor.getString(19));
                    landandBuildingInfoModel.setNoofFloorsRemarks(cursor.getString(20));
                    landandBuildingInfoModel.setNoofFloorsNc(cursor.getInt(21));
                    landandBuildingInfoModel.setOrgInstalledLift(cursor.getString(22));
                    landandBuildingInfoModel.setOrgInstalledLiftRemarks(cursor.getString(23));
                    landandBuildingInfoModel.setOrgInstalledLiftNc(cursor.getInt(24));
                    landandBuildingInfoModel.setCapacityofLift(cursor.getString(25));
                    landandBuildingInfoModel.setCapacityofLiftRemarks(cursor.getString(26));
                    landandBuildingInfoModel.setCapacityofLiftNc(cursor.getInt(27));
                    landandBuildingInfoModel.setSafetyCertificateofLift(cursor.getString(28));
                    landandBuildingInfoModel.setSafetyCertificateofLiftRemarks(cursor.getString(29));
                    landandBuildingInfoModel.setSafetyCertificateofLiftNc(cursor.getInt(30));
                    landandBuildingInfoModel.setWorkshopRoof(cursor.getString(31));
                    landandBuildingInfoModel.setWorkshopRoofRemarks(cursor.getString(32));
                    landandBuildingInfoModel.setWorkshopRoofNc(cursor.getInt(33));
                    landandBuildingInfoModel.setBuildingPlanofInstitute(cursor.getString(34));
                    landandBuildingInfoModel.setBuildingPlanofInstituteRemarks(cursor.getString(35));
                    landandBuildingInfoModel.setBuildingPlanofInstituteNc(cursor.getInt(36));
                    landandBuildingInfoModel.setWallOfIti(cursor.getString(37));
                    landandBuildingInfoModel.setWallOfItiRemarks(cursor.getString(38));
                    landandBuildingInfoModel.setWallOfItiNc(cursor.getInt(39));
                    landandBuildingInfoModel.setFloorisCemented(cursor.getString(40));
                    landandBuildingInfoModel.setFloorisCementedRemarks(cursor.getString(41));
                    landandBuildingInfoModel.setFloorisCementedNc(cursor.getInt(42));
                    landandBuildingInfoModel.setItiSituatedintheSameCampus(cursor.getString(43));
                    landandBuildingInfoModel.setItiSituatedintheSameCampusRemarks(cursor.getString(44));
                    landandBuildingInfoModel.setItiSituatedintheSameCampusNc(cursor.getInt(45));
                    landandBuildingInfoModel.setAvaibilityofSeparateMaleFemaleWashroom(cursor.getString(46));
                    landandBuildingInfoModel.setAvaibilityofSeparateMaleFemaleWashroomRemarks(cursor.getString(47));
                    landandBuildingInfoModel.setAvaibilityofSeparateMaleFemaleWashroomNc(cursor.getInt(48));
                    landandBuildingInfoModel.setAretheWashroomFunctional(cursor.getString(49));
                    landandBuildingInfoModel.setAretheWashroomFunctionalRemarks(cursor.getString(50));
                    landandBuildingInfoModel.setAretheWashroomFunctionalNc(cursor.getInt(51));
                    landandBuildingInfoModel.setAvaibiltyofFireextinguisher(cursor.getString(52));
                    landandBuildingInfoModel.setAvaibiltyofFireextinguisherRemarks(cursor.getString(53));
                    landandBuildingInfoModel.setAvaibiltyofFireextinguisherNc(cursor.getInt(54));
                    landandBuildingInfoModel.setDurationlease(cursor.getString(55));
                    landandBuildingInfoModel.setDurationleaseRemarks(cursor.getString(56));
                    landandBuildingInfoModel.setDurationleaseNc(cursor.getInt(57));
                    landandBuildingInfoModel.setExpiryofAgreement(cursor.getString(58));
                    landandBuildingInfoModel.setExpiryofagreementRemarks(cursor.getString(59));
                    landandBuildingInfoModel.setExpiryofagreementNC(cursor.getInt(60));
                    landandBuildingInfoModel.setAvaibilityofsafeDrinkingwater(cursor.getString(61));
                    landandBuildingInfoModel.setAvaibilityofsafedrinkingwaterRemarks(cursor.getString(62));
                    landandBuildingInfoModel.setAvaibilityofsafedrinkingwaterNc(cursor.getInt(63));
                    landandBuildingInfoModel.setAvaibilityHousekeepingstaff(cursor.getString(64));
                    landandBuildingInfoModel.setAvaibilityhousekeepingstaffRemarks(cursor.getString(65));
                    landandBuildingInfoModel.setAvaibilityhousekeepingstaffNc(cursor.getInt(66));
                    landandBuildingInfoModel.setAvaibilityofPlacementcell(cursor.getString(67));
                    landandBuildingInfoModel.setAvaibilityofplacementcellRemarks(cursor.getString(68));
                    landandBuildingInfoModel.setAvaibilityofplacementcellNc(cursor.getInt(69));
                    landandBuildingInfoModel.setIsMasterPlan(cursor.getString(70));
                    landandBuildingInfoModel.setIsMasterPlanRemarks(cursor.getString(71));
                    landandBuildingInfoModel.setIsMasterPlanNC(cursor.getInt(72));
                    landandBuildingInfoModel.setComptent(cursor.getString(73));
                    landandBuildingInfoModel.setComptentRemarks(cursor.getString(74));
                    landandBuildingInfoModel.setComptentNC(cursor.getInt(75));
                    landandBuildingInfoModel.setSitmap(cursor.getString(76));
                    landandBuildingInfoModel.setSitmapRemarks(cursor.getString(77));
                    landandBuildingInfoModel.setSitmapNc(cursor.getInt(78));
                    landandBuildingInfoModel.setDimension(cursor.getString(79));
                    landandBuildingInfoModel.setDimensionRemarks(cursor.getString(80));
                    landandBuildingInfoModel.setDimensionNC(cursor.getInt(81));
                    landandBuildingInfoModel.setMap(cursor.getString(82));
                    landandBuildingInfoModel.setMapRemarks(cursor.getString(83));
                    landandBuildingInfoModel.setMapNC(cursor.getInt(84));
                    landandBuildingInfoModel.setApproach(cursor.getString(85));
                    landandBuildingInfoModel.setApproachRemarks(cursor.getString(86));
                    landandBuildingInfoModel.setApproachNC(cursor.getInt(87));
                    landandBuildingInfoModel.setEntrance(cursor.getString(88));
                    landandBuildingInfoModel.setEntranceRemarks(cursor.getString(89));
                    landandBuildingInfoModel.setEntranceNC(cursor.getInt(90));
                    landandBuildingInfoModel.setSoundProofPartition(cursor.getString(91));
                    landandBuildingInfoModel.setSoundProofPartitionRemarks(cursor.getString(92));
                    landandBuildingInfoModel.setISoundProofPartitionNC(cursor.getInt(93));
                    landandBuildingInfoModel.setIsPremisesShared(cursor.getString(91));
                    landandBuildingInfoModel.setIsPremisesSharedRemarks(cursor.getString(92));
                    landandBuildingInfoModel.setIsPremisesSharedNC(cursor.getInt(93));
                    landandBuildingInfoModel.setSeperateEntrance(cursor.getString(94));
                    landandBuildingInfoModel.setSeperateEntranceRemarks(cursor.getString(95));
                    landandBuildingInfoModel.setSeperateEntranceNC(cursor.getInt(96));
                    landandBuildingInfoModel.setSwitchBoard(cursor.getString(97));
                    landandBuildingInfoModel.setSwitchBoardRemarks(cursor.getString(98));
                    landandBuildingInfoModel.setSwitchBoardNC(cursor.getInt(99));
                    landandBuildingInfoModel.setVentilation(cursor.getString(100));
                    landandBuildingInfoModel.setVentilationRemarks(cursor.getString(101));
                    landandBuildingInfoModel.setVentilationNC(cursor.getInt(102));
                    landandBuildingInfoModel.setConstructed(cursor.getString(103));
                    landandBuildingInfoModel.setConstructedRemarks(cursor.getString(104));
                    landandBuildingInfoModel.setConstructedNC(cursor.getInt(105));
                    landandBuildingInfoModel.setPrescribed(cursor.getString(106));
                    landandBuildingInfoModel.setPrescribedRemarks(cursor.getString(107));
                    landandBuildingInfoModel.setPrescribedNC(cursor.getInt(108));
                    landandBuildingInfoModel.setStaircase(cursor.getString(109));
                    landandBuildingInfoModel.setStaircaseRemarks(cursor.getString(110));
                    landandBuildingInfoModel.setStaircaseNC(cursor.getInt(111));
                    landandBuildingInfoModel.setRefId(cursor.getInt(112));
                    landandBuildingInfoModel.setProc_tracker(cursor.getInt(113));


                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return landandBuildingInfoModel;
    }


    public boolean addTradeWiseToolInfoData(JSONObject result, String yearWiseCollegeId,String tradeId) {

        try{
            SQLiteDatabase database = getWritableDatabase();
            int status = result.optInt("status");
            if(status == 0){
                JSONObject payloadObj = result.optJSONObject("payload");
                JSONArray tradeWiseToolsArr = payloadObj.optJSONArray("tradeWiseTools");
                for (int i = 0;i<tradeWiseToolsArr.length();i++) {
                    JSONObject tradeWiseToolsObj = tradeWiseToolsArr.optJSONObject(i);
                    values.put("yearWiseCollegeId",yearWiseCollegeId);
                    values.put("tradeId",tradeId);
                    values.put("refId",tradeWiseToolsObj.optString("refId"));
                    values.put("tradeName", tradeWiseToolsObj.optString("tradeName"));
                    values.put("equipmentName", tradeWiseToolsObj.optString("equipmentName"));
                    values.put("equipmentId", tradeWiseToolsObj.optString("equipmentId"));
                    values.put("reqUnit", tradeWiseToolsObj.optString("reqUnit"));
                    values.put("markTools", tradeWiseToolsObj.optString("markTools"));
                    values.put("requnitRemarks", tradeWiseToolsObj.optString("requnitRemarks"));
                    values.put("reqNC", tradeWiseToolsObj.optString("reqNC"));
                    values.put("qty", tradeWiseToolsObj.optString("qty"));
                    values.put("qtyRemarks", tradeWiseToolsObj.optString("qtyRemarks"));
                    values.put("qtyNc", tradeWiseToolsObj.optString("qtyNc"));
                    values.put("proc_tracker", 1);
                    database.insert("TradeWiseToolInfo", null, values);

                }
            }else {
                Toast.makeText(application_context, "No Data Found..", Toast.LENGTH_LONG).show();
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public List<TradeWiseTool> getTradesWiseInfoList(String yearWiseCollegeId,String tradeId) {

        SQLiteDatabase db = null;
        String selectQueryQues ="Select yearWiseCollegeId,refId,equipmentId,tradeId,tradeName,equipmentName,reqUnit,markTools,availableUnit,proc_tracker from TradeWiseToolInfo where YearWiseCollegeId = " + yearWiseCollegeId +" and tradeId = "+tradeId;

        List<TradeWiseTool> tradewiseInfolist = new ArrayList<>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    TradeWiseTool tradewisetoolInfoModel = new TradeWiseTool();
                    tradewisetoolInfoModel.setYearWiseCollegeid(cursor.getString(0));
                    tradewisetoolInfoModel.setRefId(cursor.getString(1));
                    tradewisetoolInfoModel.setEquipmentId(cursor.getString(2));
                    tradewisetoolInfoModel.setTradeId(cursor.getString(3));
                    tradewisetoolInfoModel.setTradeName(cursor.getString(4));
                    tradewisetoolInfoModel.setEquipmentName(cursor.getString(5));
                    tradewisetoolInfoModel.setReqUnit(cursor.getString(6));
                    tradewisetoolInfoModel.setMarkTools(cursor.getString(7));
                    tradewisetoolInfoModel.setAvailableUnit(cursor.getString(8));
                    tradewisetoolInfoModel.setProc_tracker(cursor.getInt(9));
                    tradewiseInfolist.add(tradewisetoolInfoModel);


                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return tradewiseInfolist;

    }


    public boolean addPremisesShiftingInfoData(JSONObject result, String yearWiseCollegeId) {
        try{

            SQLiteDatabase database = getWritableDatabase();

            int status = result.optInt("status");
            if(status == 0){
                JSONObject payload = result.optJSONObject("payload");
                JSONObject technicalInfoObj = payload.optJSONObject("premisesShifting");
                ContentValues values = new ContentValues();
                values.put("yearWiseCollegeId",yearWiseCollegeId);
                values.put("refId",technicalInfoObj.optString("refId"));
                values.put("toPostalAddress",technicalInfoObj.optString("toPostalAddress"));
                values.put("tolandmark",technicalInfoObj.optString("tolandmark"));
                values.put("toState",technicalInfoObj.optString("toState"));
                values.put("toDistrict",technicalInfoObj.optString("toDistrict"));
                values.put("toTehsil",technicalInfoObj.optString("toTehsil"));
                values.put("toBlock",technicalInfoObj.optString("toBlock"));
                values.put("toPincode",technicalInfoObj.optString("toPincode"));
                values.put("totelno",technicalInfoObj.optString("totelno"));
                values.put("toFaxno",technicalInfoObj.optString("toFaxno"));
                values.put("proc_tracker", 1);
                database.insert("PremisesShiftingInfo", null, values);
            }else {
                Toast.makeText(application_context, "No Data Found..", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return true;

    }

    public List<Staffing> getPremisesInfoList(String YearWiseCollegeId) {

        SQLiteDatabase db = null;
        String selectQueryQues ="Select yearWiseCollegeId ,change,changeRemarks, changeNC, toPostalAddress, toPostalAddressRemarks," +
                " toPostalAddressNC, tolandmark, tolandmarkRemarks, tolandmarkNC, toState, toStateRemarks, toStateNC, toDistrict, " +
                "toDistrictRemarks, toDistrictNC, toTehsil, toTehsilRemarks, toTehsilNC, toBlock, toBlockRemarks, toBlockNC," +
                " toPincode, toPincodeRemarks, toPincodeNC, totelno, totelnoRemarks,totelnoNC, toFaxno, toFaxnoRemarks," +
                " toFaxnoNC, remarks,refId,proc_tracker from PremisesShiftingInfo where YearWiseCollegeId = " + YearWiseCollegeId;
        List<Staffing> staffingInfoList = new ArrayList<Staffing>();
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    Staffing staffingInfoModel = new Staffing();
                    staffingInfoModel.setYearWiseCollegeid(cursor.getString(0));
                    staffingInfoModel.setChangeOfPremises(cursor.getString(1));
                    staffingInfoModel.setChangeOfPremisesRemarks(cursor.getString(2));
                    staffingInfoModel.setChangeOfPremisesNc(cursor.getInt(3));
                    staffingInfoModel.setToPostalAddress(cursor.getString(4));
                    staffingInfoModel.setToPostalAddressRemarks(cursor.getString(5));
                    staffingInfoModel.setToPostalAddressNc(cursor.getInt(6));
                    staffingInfoModel.setTolandmark(cursor.getString(7));
                    staffingInfoModel.setTolandmarkRemarks(cursor.getString(8));
                    staffingInfoModel.setTolandmarkNc(cursor.getInt(9));
                    staffingInfoModel.setToState(cursor.getString(10));
                    staffingInfoModel.setToStateRemarks(cursor.getString(11));
                    staffingInfoModel.setToStateNc(cursor.getInt(12));
                    staffingInfoModel.setToDistrict(cursor.getString(13));
                    staffingInfoModel.setToDistrictRemarks(cursor.getString(14));
                    staffingInfoModel.setToDistrictNc(cursor.getInt(15));
                    staffingInfoModel.setToTehsil(cursor.getString(16));
                    staffingInfoModel.setToTehsilRemarks(cursor.getString(17));
                    staffingInfoModel.setToTehsilNc(cursor.getInt(18));
                    staffingInfoModel.setToBlock(cursor.getString(19));
                    staffingInfoModel.setToBlockRemarks(cursor.getString(20));
                    staffingInfoModel.setToBlockNc(cursor.getInt(21));
                    staffingInfoModel.setToPincode(cursor.getString(22));
                    staffingInfoModel.setToPincodeRemarks(cursor.getString(23));
                    staffingInfoModel.setToPincodeNc(cursor.getInt(24));
                    staffingInfoModel.setTotelno(cursor.getString(25));
                    staffingInfoModel.setTotelnoRemarks(cursor.getString(26));
                    staffingInfoModel.setTotelnoNc(cursor.getInt(27));
                    staffingInfoModel.setToFaxno(cursor.getString(28));
                    staffingInfoModel.setToFaxnoRemarks(cursor.getString(29));
                    staffingInfoModel.setToFaxnoNc(cursor.getInt(30));
                    staffingInfoModel.setRefid(cursor.getInt(31));
                    staffingInfoModel.setProc_tracker(cursor.getInt(32));
                    staffingInfoList.add(staffingInfoModel);


                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return staffingInfoList;
    }

    public Staffing getPremisesInfobyYearwiseCollegeId(String YearWiseCollegeId) {
        SQLiteDatabase db = null;
        String selectQueryQues ="Select yearWiseCollegeId ,change,changeRemarks, changeNC, toPostalAddress, toPostalAddressRemarks," +
                " toPostalAddressNC, tolandmark, tolandmarkRemarks, tolandmarkNC, toState, toStateRemarks, toStateNC, toDistrict, " +
                "toDistrictRemarks, toDistrictNC, toTehsil, toTehsilRemarks, toTehsilNC, toBlock, toBlockRemarks, toBlockNC," +
                " toPincode, toPincodeRemarks, toPincodeNC, totelno, totelnoRemarks,totelnoNC, toFaxno, toFaxnoRemarks," +
                " toFaxnoNC, remarks,refId,proc_tracker from PremisesShiftingInfo where YearWiseCollegeId = " + YearWiseCollegeId;
        Staffing staffingInfoModel = null;
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    staffingInfoModel = new Staffing();
                    staffingInfoModel.setYearWiseCollegeid(cursor.getString(0));
                    staffingInfoModel.setYearWiseCollegeid(cursor.getString(0));
                    staffingInfoModel.setChangeOfPremises(cursor.getString(1));
                    staffingInfoModel.setChangeOfPremisesRemarks(cursor.getString(2));
                    staffingInfoModel.setChangeOfPremisesNc(cursor.getInt(3));
                    staffingInfoModel.setToPostalAddress(cursor.getString(4));
                    staffingInfoModel.setToPostalAddressRemarks(cursor.getString(5));
                    staffingInfoModel.setToPostalAddressNc(cursor.getInt(6));
                    staffingInfoModel.setTolandmark(cursor.getString(7));
                    staffingInfoModel.setTolandmarkRemarks(cursor.getString(8));
                    staffingInfoModel.setTolandmarkNc(cursor.getInt(9));
                    staffingInfoModel.setToState(cursor.getString(10));
                    staffingInfoModel.setToStateRemarks(cursor.getString(11));
                    staffingInfoModel.setToStateNc(cursor.getInt(12));
                    staffingInfoModel.setToDistrict(cursor.getString(13));
                    staffingInfoModel.setToDistrictRemarks(cursor.getString(14));
                    staffingInfoModel.setToDistrictNc(cursor.getInt(15));
                    staffingInfoModel.setToTehsil(cursor.getString(16));
                    staffingInfoModel.setToTehsilRemarks(cursor.getString(17));
                    staffingInfoModel.setToTehsilNc(cursor.getInt(18));
                    staffingInfoModel.setToBlock(cursor.getString(19));
                    staffingInfoModel.setToBlockRemarks(cursor.getString(20));
                    staffingInfoModel.setToBlockNc(cursor.getInt(21));
                    staffingInfoModel.setToPincode(cursor.getString(22));
                    staffingInfoModel.setToPincodeRemarks(cursor.getString(23));
                    staffingInfoModel.setToPincodeNc(cursor.getInt(24));
                    staffingInfoModel.setTotelno(cursor.getString(25));
                    staffingInfoModel.setTotelnoRemarks(cursor.getString(26));
                    staffingInfoModel.setTotelnoNc(cursor.getInt(27));
                    staffingInfoModel.setToFaxno(cursor.getString(28));
                    staffingInfoModel.setToFaxnoRemarks(cursor.getString(29));
                    staffingInfoModel.setToFaxnoNc(cursor.getInt(30));
                    staffingInfoModel.setRefid(cursor.getInt(31));
                    staffingInfoModel.setProc_tracker(cursor.getInt(32));

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return staffingInfoModel;
    }

    public List<Classroom> getClassroomInfoList(String YearWiseCollegeId) {

        SQLiteDatabase db = null;
        String selectQueryQues ="Select yearwisecollegeid,classroomName ,classroomNameRemarks , classroomNameNC , " +
                "classroomArea , classroomAreaRemarks , classroomAreaNC , available , refId , width , widthRemarks ," +
                " widthNC , flag , tradeId , ceiling , ceilingRemarks , ceilingNC , height , heightRemarks , heightNC ," +
                " wall , wallRemarks , wallNC ,proc_tracker from ClassroomInfo where YearWiseCollegeId = " + YearWiseCollegeId;
        List<Classroom> classInfoList = new ArrayList<Classroom>();
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    Classroom classInfoModel = new Classroom();
                    classInfoModel.setYearwisecollegeid(cursor.getString(0));
                    classInfoModel.setClassroomName(cursor.getString(1));
                    classInfoModel.setClassroomNameRemarks(cursor.getString(2));
                    classInfoModel.setClassroomNameNC(cursor.getInt(3));
                    classInfoModel.setClassroomArea(cursor.getString(4));
                    classInfoModel.setClassroomAreaRemarks(cursor.getString(5));
                    classInfoModel.setClassroomAreaNC(cursor.getInt(6));
                    classInfoModel.setAvailable(cursor.getInt(7));
                    classInfoModel.setRefId(cursor.getInt(8));
                    classInfoModel.setWidth(cursor.getString(9));
                    classInfoModel.setWidthRemarks(cursor.getString(10));
                    classInfoModel.setWidthNC(cursor.getInt(11));
                    classInfoModel.setFlag(cursor.getInt(12));
                    classInfoModel.setTradeId(cursor.getInt(13));
                    classInfoModel.setRoof(cursor.getString(14));
                    classInfoModel.setRoofRemarks(cursor.getString(15));
                    classInfoModel.setRoofNC(cursor.getInt(16));
                    classInfoModel.setHeight(cursor.getString(17));
                    classInfoModel.setHeightRemarks(cursor.getString(18));
                    classInfoModel.setHeightNC(cursor.getInt(19));
                    classInfoModel.setTin(cursor.getString(20));
                    classInfoModel.setTinRemarks(cursor.getString(21));
                    classInfoModel.setTinNC(cursor.getInt(22));
                    classInfoModel.setProc_tracker(cursor.getInt(23));
                    classInfoList.add(classInfoModel);


                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return classInfoList;
    }

    public boolean addClassroomInfoData(JSONObject result, String yearWiseCollegeId) {
        try{

            SQLiteDatabase database = getWritableDatabase();

            int status = result.optInt("status");
            if(status == 0){
                JSONObject payload = result.optJSONObject("payload");
                JSONArray classInfoArr = payload.optJSONArray("classroomDetails");
                for (int i = 0;i<classInfoArr.length();i++) {
                    JSONObject classInfoObj = classInfoArr.optJSONObject(i);
                    ContentValues values = new ContentValues();
                    values.put("yearwisecollegeid",yearWiseCollegeId);
                    values.put("refId", classInfoObj.optString("refId"));
                    values.put("available", classInfoObj.optString("available"));
                    values.put("classroomName", classInfoObj.optString("classroomName"));
                    values.put("classroomArea", classInfoObj.optString("classroomArea"));
                    values.put("width",classInfoObj.optString("widthOftheClassroom"));
                    values.put("flag",classInfoObj.optString("flag"));
                    values.put("tradeId",classInfoObj.optString("tradeId"));
                    values.put("proc_tracker", 1);
                    database.insert("ClassroomInfo", null, values);
                }

            }else {
                Toast.makeText(application_context, "No Data Found..", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    public Classroom getClassroomInfobyYearwiseCollegeId(String YearWiseCollegeId, String refId) {
        SQLiteDatabase db = null;
        String selectQueryQues ="Select yearwisecollegeid,classroomName ,classroomNameRemarks , classroomNameNC , " +
                "classroomArea , classroomAreaRemarks , classroomAreaNC , available , refId , width , widthRemarks ," +
                " widthNC , flag , tradeId , ceiling , ceilingRemarks , ceilingNC , height , heightRemarks , heightNC ," +
                " wall , wallRemarks , wallNC ,proc_tracker from ClassroomInfo where YearWiseCollegeId = " + YearWiseCollegeId + " and refId= " + refId;
        Classroom classInfoModel = null;
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    classInfoModel = new Classroom();
                    classInfoModel.setYearwisecollegeid(cursor.getString(0));
                    classInfoModel.setClassroomName(cursor.getString(1));
                    classInfoModel.setClassroomNameRemarks(cursor.getString(2));
                    classInfoModel.setClassroomNameNC(cursor.getInt(3));
                    classInfoModel.setClassroomArea(cursor.getString(4));
                    classInfoModel.setClassroomAreaRemarks(cursor.getString(5));
                    classInfoModel.setClassroomAreaNC(cursor.getInt(6));
                    classInfoModel.setAvailable(cursor.getInt(7));
                    classInfoModel.setRefId(cursor.getInt(8));
                    classInfoModel.setWidth(cursor.getString(9));
                    classInfoModel.setWidthRemarks(cursor.getString(10));
                    classInfoModel.setWidthNC(cursor.getInt(11));
                    classInfoModel.setFlag(cursor.getInt(12));
                    classInfoModel.setTradeId(cursor.getInt(13));
                    classInfoModel.setRoof(cursor.getString(14));
                    classInfoModel.setRoofRemarks(cursor.getString(15));
                    classInfoModel.setRoofNC(cursor.getInt(16));
                    classInfoModel.setHeight(cursor.getString(17));
                    classInfoModel.setHeightRemarks(cursor.getString(18));
                    classInfoModel.setHeightNC(cursor.getInt(19));
                    classInfoModel.setTin(cursor.getString(20));
                    classInfoModel.setTinRemarks(cursor.getString(21));
                    classInfoModel.setTinNC(cursor.getInt(22));
                    classInfoModel.setProc_tracker(cursor.getInt(23));

                    classInfoModel.setRefId(Integer.valueOf(refId));

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return classInfoModel;
    }

    public boolean isImageDataDownloaded(String instituteId) {
        SQLiteDatabase database = getReadableDatabase();
        String selectSQLUploadImages = "SELECT IsImageDownloaded FROM UploadImages where YearWiseCollegeId =" + instituteId;
        Cursor cursor = database.rawQuery(selectSQLUploadImages, null);

        while (cursor.moveToNext()) {
            int intImageDownloaded = cursor.getInt(0);
            if (intImageDownloaded == 1) {
                return true;
            }
        }
        return false;
    }

    public void addUploadImagesData(String instituteId) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("YearWiseCollegeId", instituteId);
        values.put("IsImageDownloaded", 1);
        values.put("proc_tracker", 1);

        database.insert("UploadImages", null, values);
    }

    public boolean addGenInfoUploadImageData(JSONObject result, String yearWiseCollegeId) {
        try {

            SQLiteDatabase database = getWritableDatabase();

            int status = result.optInt("status");
            if(status == 0){
                JSONObject payloadObj = result.optJSONObject("payload");
                JSONArray GeneralDetailsArr = payloadObj.optJSONArray("GeneralDetails");
                for (int i = 0;i < GeneralDetailsArr.length();i++){
                    JSONObject GeneralDetailsObj = GeneralDetailsArr.optJSONObject(i);
                    ContentValues values = new ContentValues();
                    values.put("YearWiseCollegeId",yearWiseCollegeId);
                    values.put("docId",GeneralDetailsObj.optString("docId"));
                    values.put("section",GeneralDetailsObj.optString("section"));
                    values.put("refId",GeneralDetailsObj.optString("refId"));
                    values.put("pictureType",GeneralDetailsObj.optString("pictureType"));
                    values.put("helpText",GeneralDetailsObj.optString("helpText"));
                    values.put("remarks",GeneralDetailsObj.optString("remarks"));
                    values.put("latitude",GeneralDetailsObj.optString("latitude"));
                    values.put("longitude",GeneralDetailsObj.optString("longitude"));
                    values.put("name",GeneralDetailsObj.optString("name"));
                    values.put("photo",GeneralDetailsObj.optString("photo"));
                    values.put("nc",GeneralDetailsObj.optString("nc"));
                    values.put("proc_tracker", 1);

                    database.insert("GeneralInfoImage", null, values);
                }

            }else {
                //Toast.makeText(application_context, "No images found in General Details", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }


    public Proc_Track getSyncStatusImage() {
        String facilityQuery = "Select proc_tracker,count(*) from UploadImages group by proc_tracker";
        Proc_Track obj = new Proc_Track();

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(facilityQuery, null);
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
            Log.e("UPLOAD_IMAGE_DAO", e.getMessage());
            return null;
        }
        return obj;
    }

    public boolean addOrganisationDetailsInfoUploadImageData(JSONObject result, String yearWiseCollegeId) {

        try {

            SQLiteDatabase database = getWritableDatabase();

            int status = result.optInt("status");
            if(status == 0){
                JSONObject payloadObj = result.optJSONObject("payload");
                JSONArray GeneralDetailsArr = payloadObj.optJSONArray("OrganisationDetails");
                for (int i = 0;i < GeneralDetailsArr.length();i++){
                    JSONObject GeneralDetailsObj = GeneralDetailsArr.optJSONObject(i);
                    ContentValues values = new ContentValues();
                    values.put("YearWiseCollegeId",yearWiseCollegeId);
                    values.put("docId",GeneralDetailsObj.optString("docId"));
                    values.put("section",GeneralDetailsObj.optString("section"));
                    values.put("refId",GeneralDetailsObj.optString("refId"));
                    values.put("pictureType",GeneralDetailsObj.optString("pictureType"));
                    values.put("helpText",GeneralDetailsObj.optString("helpText"));
                    values.put("remarks",GeneralDetailsObj.optString("remarks"));
                    values.put("latitude",GeneralDetailsObj.optString("latitude"));
                    values.put("longitude",GeneralDetailsObj.optString("longitude"));
                    values.put("name",GeneralDetailsObj.optString("name"));
                    values.put("photo",GeneralDetailsObj.optString("photo"));
                    values.put("nc",GeneralDetailsObj.optString("nc"));
                    values.put("proc_tracker", 1);

                    database.insert("OrganisationDetailsInfoImage", null, values);
                }

            }else {
                //Toast.makeText(application_context, "No images found in General Details", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean addLandAndBuildingInfoUploadImageData(JSONObject result, String yearWiseCollegeId) {

        try {

            SQLiteDatabase database = getWritableDatabase();

            int status = result.optInt("status");
            if(status == 0){
                JSONObject payloadObj = result.optJSONObject("payload");
                JSONArray GeneralDetailsArr = payloadObj.optJSONArray("LandAndBuildingDetails");
                for (int i = 0;i < GeneralDetailsArr.length();i++){
                    JSONObject GeneralDetailsObj = GeneralDetailsArr.optJSONObject(i);
                    ContentValues values = new ContentValues();
                    values.put("YearWiseCollegeId",yearWiseCollegeId);
                    values.put("docId",GeneralDetailsObj.optString("docId"));
                    values.put("section",GeneralDetailsObj.optString("section"));
                    values.put("refId",GeneralDetailsObj.optString("refId"));
                    values.put("pictureType",GeneralDetailsObj.optString("pictureType"));
                    values.put("helpText",GeneralDetailsObj.optString("helpText"));
                    values.put("remarks",GeneralDetailsObj.optString("remarks"));
                    values.put("latitude",GeneralDetailsObj.optString("latitude"));
                    values.put("longitude",GeneralDetailsObj.optString("longitude"));
                    values.put("name",GeneralDetailsObj.optString("name"));
                    values.put("photo",GeneralDetailsObj.optString("photo"));
                    values.put("nc",GeneralDetailsObj.optString("nc"));
                    values.put("proc_tracker", 1);

                    database.insert("LandAndBuildingInfoImage", null, values);
                }

            }else {
               // Toast.makeText(application_context, "No images found in General Details", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean addTechnicalInfoUploadImageData(JSONObject result, String instituteId) {

        try {

            SQLiteDatabase database = getWritableDatabase();

            int status = result.optInt("status");
            if(status == 0){
                JSONObject payloadObj = result.optJSONObject("payload");
                JSONArray GeneralDetailsArr = payloadObj.optJSONArray("TechnicalStaffSDoc");
                for (int i = 0;i < GeneralDetailsArr.length();i++){
                    JSONObject GeneralDetailsObj = GeneralDetailsArr.optJSONObject(i);
                    ContentValues values = new ContentValues();
                    values.put("YearWiseCollegeId",instituteId);
                    values.put("docId",GeneralDetailsObj.optString("docId"));
                    values.put("section",GeneralDetailsObj.optString("section"));
                    values.put("refId",GeneralDetailsObj.optString("refId"));
                    values.put("pictureType",GeneralDetailsObj.optString("pictureType"));
                    values.put("helpText",GeneralDetailsObj.optString("helpText"));
                    values.put("remarks",GeneralDetailsObj.optString("remarks"));
                    values.put("latitude",GeneralDetailsObj.optString("latitude"));
                    values.put("longitude",GeneralDetailsObj.optString("longitude"));
                    values.put("name",GeneralDetailsObj.optString("name"));
                    values.put("photo",GeneralDetailsObj.optString("photo"));
                    values.put("nc",GeneralDetailsObj.optString("nc"));
                    values.put("proc_tracker", 1);

                    database.insert("TechnicalInfoImage", null, values);
                }

            }else {
               // Toast.makeText(application_context, "No images found in General Details", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean addInstructorInfoUploadImageData(JSONObject result, String instituteId) {

        try {

            SQLiteDatabase database = getWritableDatabase();

            int status = result.optInt("status");
            if(status == 0){
                JSONObject payloadObj = result.optJSONObject("payload");
                JSONArray GeneralDetailsArr = payloadObj.optJSONArray("IntructorStaffDoc");
                for (int i = 0;i < GeneralDetailsArr.length();i++){
                    JSONObject GeneralDetailsObj = GeneralDetailsArr.optJSONObject(i);
                    ContentValues values = new ContentValues();
                    values.put("YearWiseCollegeId",instituteId);
                    values.put("docId",GeneralDetailsObj.optString("docId"));
                    values.put("section",GeneralDetailsObj.optString("section"));
                    values.put("refId",GeneralDetailsObj.optString("refId"));
                    values.put("pictureType",GeneralDetailsObj.optString("pictureType"));
                    values.put("helpText",GeneralDetailsObj.optString("helpText"));
                    values.put("remarks",GeneralDetailsObj.optString("remarks"));
                    values.put("latitude",GeneralDetailsObj.optString("latitude"));
                    values.put("longitude",GeneralDetailsObj.optString("longitude"));
                    values.put("name",GeneralDetailsObj.optString("name"));
                    values.put("photo",GeneralDetailsObj.optString("photo"));
                    values.put("nc",GeneralDetailsObj.optString("nc"));
                    values.put("proc_tracker", 1);

                    database.insert("InstructorInfoImage", null, values);
                }

            }else {
                //Toast.makeText(application_context, "No images found in General Details", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean additLabInfoUploadImageData(JSONObject result, String instituteId) {

        try {

            SQLiteDatabase database = getWritableDatabase();

            int status = result.optInt("status");
            if(status == 0){
                JSONObject payloadObj = result.optJSONObject("payload");
                JSONArray GeneralDetailsArr = payloadObj.optJSONArray("ITLabDetails");
                for (int i = 0;i < GeneralDetailsArr.length();i++){
                    JSONObject GeneralDetailsObj = GeneralDetailsArr.optJSONObject(i);
                    ContentValues values = new ContentValues();
                    values.put("YearWiseCollegeId",instituteId);
                    values.put("docId",GeneralDetailsObj.optString("docId"));
                    values.put("section",GeneralDetailsObj.optString("section"));
                    values.put("refId",GeneralDetailsObj.optString("refId"));
                    values.put("pictureType",GeneralDetailsObj.optString("pictureType"));
                    values.put("helpText",GeneralDetailsObj.optString("helpText"));
                    values.put("remarks",GeneralDetailsObj.optString("remarks"));
                    values.put("latitude",GeneralDetailsObj.optString("latitude"));
                    values.put("longitude",GeneralDetailsObj.optString("longitude"));
                    values.put("name",GeneralDetailsObj.optString("name"));
                    values.put("photo",GeneralDetailsObj.optString("photo"));
                    values.put("nc",GeneralDetailsObj.optString("nc"));
                    values.put("proc_tracker", 1);

                    database.insert("ITLabInfoImage", null, values);
                }

            }else {
               // Toast.makeText(application_context, "No images found in General Details", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean addPowerSupplyInfoUploadImageData(JSONObject result, String instituteId) {

        try {

            SQLiteDatabase database = getWritableDatabase();

            int status = result.optInt("status");
            if(status == 0){
                JSONObject payloadObj = result.optJSONObject("payload");
                JSONArray GeneralDetailsArr = payloadObj.optJSONArray("PowerSupplyDetails");
                for (int i = 0;i < GeneralDetailsArr.length();i++){
                    JSONObject GeneralDetailsObj = GeneralDetailsArr.optJSONObject(i);
                    ContentValues values = new ContentValues();
                    values.put("YearWiseCollegeId",instituteId);
                    values.put("docId",GeneralDetailsObj.optString("docId"));
                    values.put("section",GeneralDetailsObj.optString("section"));
                    values.put("refId",GeneralDetailsObj.optString("refId"));
                    values.put("pictureType",GeneralDetailsObj.optString("pictureType"));
                    values.put("helpText",GeneralDetailsObj.optString("helpText"));
                    values.put("remarks",GeneralDetailsObj.optString("remarks"));
                    values.put("latitude",GeneralDetailsObj.optString("latitude"));
                    values.put("longitude",GeneralDetailsObj.optString("longitude"));
                    values.put("name",GeneralDetailsObj.optString("name"));
                    values.put("photo",GeneralDetailsObj.optString("photo"));
                    values.put("nc",GeneralDetailsObj.optString("nc"));
                    values.put("proc_tracker", 1);

                    database.insert("PowerSupplyInfoImage", null, values);
                }

            }else {
               // Toast.makeText(application_context, "No images found in General Details", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public List<GeneralImageModel> getGeneralDetailsImageInfobyYearwiseCollegeId(String yearWiseCollegeId) {

        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , docId,"
                + " section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc, proc_tracker"
                + " FROM GeneralInfoImage where YearWiseCollegeId = " + yearWiseCollegeId;

        Log.e("test","test");

        List<GeneralImageModel> generalImageModelList = new ArrayList<GeneralImageModel>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                do {
                    String section = cursor.getString(2);
                    if (section != null && section.trim().equalsIgnoreCase("General Details")) {
                        GeneralImageModel general = new GeneralImageModel();
                        general.setYearWiseCollegeId(cursor.getString(0));
                        general.setDocId(cursor.getInt(1));
                        general.setSection(cursor.getString(2));
                        general.setRefId(cursor.getInt(3));
                        general.setPictureType(cursor.getString(4));
                        general.setHelpText(cursor.getString(5));
                        general.setRemarks(cursor.getString(6));
                        general.setLatitude(cursor.getString(7));
                        general.setLongitude(cursor.getString(8));
                        general.setName(cursor.getString(9));
                        general.setPhoto(cursor.getString(10));
                        general.setNc(cursor.getInt(11));
                        general.setProcTracker(cursor.getInt(12));
                        generalImageModelList.add(general);
                    }
                    Log.e("test","test");
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }

        return generalImageModelList;
    }

    public List<GeneralImageModel> getUnsavedGeneralDetailsImageInfobyYearwiseCollegeId(String yearWiseCollegeId, Integer docId) {

        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , docId , section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc, proc_tracker"
                + " FROM GeneralInfoImage where YearWiseCollegeId = " + yearWiseCollegeId + " AND docId='" + docId + "' "
                + " AND proc_tracker = " + 2;

        Log.e("test1","test1");
        List<GeneralImageModel> generalList = new ArrayList<GeneralImageModel>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                do {
                    String section = cursor.getString(2);
                    if (section != null && section.trim().equalsIgnoreCase("General Details")) {
                        GeneralImageModel general = new GeneralImageModel();
                        general.setYearWiseCollegeId(cursor.getString(0));
                        general.setDocId(cursor.getInt(1));
                        general.setSection(cursor.getString(2));
                        general.setRefId(cursor.getInt(3));
                        general.setPictureType(cursor.getString(4));
                        general.setHelpText(cursor.getString(5));
                        general.setRemarks(cursor.getString(6));
                        general.setLatitude(cursor.getString(7));
                        general.setLongitude(cursor.getString(8));
                        general.setName(cursor.getString(9));
                        general.setPhoto(cursor.getString(10));
                        general.setNc(cursor.getInt(11));
                        general.setProcTracker(cursor.getInt(12));
                        generalList.add(general);
                    }
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
        Log.e("test2","test2");

        return generalList;
    }

    public boolean updateGeneralDetails(GeneralImageModel generalImageModel) {

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        try {

            Log.e("test4","test4");

            values.put("photo", generalImageModel.getPhoto());
            values.put("remarks", generalImageModel.getRemarks());
            values.put("nc",generalImageModel.getNc());
            values.put("proc_tracker", generalImageModel.getProcTracker());

            database.update("GeneralInfoImage", values, " YearWiseCollegeId= " + generalImageModel.getYearWiseCollegeId()
                    + " AND docId=" + generalImageModel.getDocId(), null);
            Log.e("test5","test5");
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            database.close();
        }
        return true;


    }

    public boolean updateGeneralProcTracker(List<GeneralImageModel> lstGDToBeSync, String yearWiseCollegeId) {

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        if (lstGDToBeSync == null || lstGDToBeSync.size() == 0) {
            return false;
        }

        StringBuilder sbIds = new StringBuilder();
        for (GeneralImageModel fac : lstGDToBeSync) {
            sbIds.append("'").append(fac.getDocId()).append("'").append(",");
        }
        if (sbIds.length() > 0) {
            sbIds.setLength(sbIds.length() - 1);
        }

        try {
            values.put("proc_tracker", 3);
            database.update("GeneralInfoImage", values, " YearWiseCollegeId= " + yearWiseCollegeId + " AND docId IN (" + sbIds.toString() + ")", null);

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            database.close();
        }
        return true;
    }

    public boolean updateOrganisationDetails(OrganisationImage organisationImage) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        try {

            Log.e("test4","test4");

            values.put("photo", organisationImage.getPhoto());
            values.put("remarks", organisationImage.getRemarks());
            values.put("nc", organisationImage.getNc());
            values.put("proc_tracker", organisationImage.getProcTracker());

            database.update("OrganisationDetailsInfoImage", values, " YearWiseCollegeId= " + organisationImage.getYearWiseCollegeId()
                    + " AND docId=" + organisationImage.getDocId(), null);
            Log.e("test5","test5");
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            database.close();
        }
        return true;
    }

    public List<OrganisationImage> getUnsavedOrganisationImageInfobyYearwiseCollegeId(String yearWiseCollegeId, Integer docId) {

        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , docId , section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc, proc_tracker"
                + " FROM OrganisationDetailsInfoImage where YearWiseCollegeId = " + yearWiseCollegeId + " AND docId='" + docId + "' "
                + " AND proc_tracker = " + 2;

        Log.e("test1","test1");
        List<OrganisationImage> generalList = new ArrayList<OrganisationImage>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                do {
                    String section = cursor.getString(2);
                    if (section != null && section.trim().equalsIgnoreCase("Organisation Details")) {
                        OrganisationImage general = new OrganisationImage();
                        general.setYearWiseCollegeId(cursor.getString(0));
                        general.setDocId(cursor.getInt(1));
                        general.setSection(cursor.getString(2));
                        general.setRefId(cursor.getInt(3));
                        general.setPictureType(cursor.getString(4));
                        general.setHelpText(cursor.getString(5));
                        general.setRemarks(cursor.getString(6));
                        general.setLatitude(cursor.getString(7));
                        general.setLongitude(cursor.getString(8));
                        general.setName(cursor.getString(9));
                        general.setPhoto(cursor.getString(10));
                        general.setNc(cursor.getInt(11));
                        general.setProcTracker(cursor.getInt(12));
                        generalList.add(general);
                    }
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
        Log.e("test2","test2");

        return generalList;
    }

    public List<OrganisationImage> getOrganisationImageInfobyYearwiseCollegeId(String yearWiseCollegeId) {

        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , docId,"
                + " section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc, proc_tracker"
                + " FROM OrganisationDetailsInfoImage where YearWiseCollegeId = " + yearWiseCollegeId;

        List<OrganisationImage> generalImageModelList = new ArrayList<OrganisationImage>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                Log.e("test","test");
                do {
                    String section = cursor.getString(2);
                    if (section != null && section.trim().equalsIgnoreCase("Organisation Details")) {
                        OrganisationImage general = new OrganisationImage();
                        general.setYearWiseCollegeId(cursor.getString(0));
                        general.setDocId(cursor.getInt(1));
                        general.setSection(cursor.getString(2));
                        general.setRefId(cursor.getInt(3));
                        general.setPictureType(cursor.getString(4));
                        general.setHelpText(cursor.getString(5));
                        general.setRemarks(cursor.getString(6));
                        general.setLatitude(cursor.getString(7));
                        general.setLongitude(cursor.getString(8));
                        general.setName(cursor.getString(9));
                        general.setPhoto(cursor.getString(10));
                        general.setNc(cursor.getInt(11));
                        general.setProcTracker(cursor.getInt(12));
                        generalImageModelList.add(general);
                    }
                    Log.e("test","test");
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }

        return generalImageModelList;
    }

    public boolean updateOrganisationProcTracker(List<OrganisationImage> lstOrgToBeSync, String yearWiseCollegeId) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        if (lstOrgToBeSync == null || lstOrgToBeSync.size() == 0) {
            return false;
        }

        StringBuilder sbIds = new StringBuilder();
        for (OrganisationImage fac : lstOrgToBeSync) {
            sbIds.append("'").append(fac.getDocId()).append("'").append(",");
        }
        if (sbIds.length() > 0) {
            sbIds.setLength(sbIds.length() - 1);
        }

        try {
            values.put("proc_tracker", 3);
            database.update("OrganisationDetailsInfoImage", values, " YearWiseCollegeId= " + yearWiseCollegeId + " AND docId IN (" + sbIds.toString() + ")", null);

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            database.close();
        }
        return true;
    }

    public List<LandImage> getLandImageInfobyYearwiseCollegeId(String yearWiseCollegeId) {

        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , docId,"
                + " section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc, proc_tracker"
                + " FROM LandAndBuildingInfoImage where YearWiseCollegeId = " + yearWiseCollegeId;

        List<LandImage> generalImageModelList = new ArrayList<LandImage>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                Log.e("test1","test1");
                do {
                    String section = cursor.getString(2);
                    if (section != null && section.trim().equalsIgnoreCase("Land And Building Of Institute Details")) {
                        LandImage general = new LandImage();
                        general.setYearWiseCollegeId(cursor.getString(0));
                        general.setDocId(cursor.getInt(1));
                        general.setSection(cursor.getString(2));
                        general.setRefId(cursor.getInt(3));
                        general.setPictureType(cursor.getString(4));
                        general.setHelpText(cursor.getString(5));
                        general.setRemarks(cursor.getString(6));
                        general.setLatitude(cursor.getString(7));
                        general.setLongitude(cursor.getString(8));
                        general.setName(cursor.getString(9));
                        general.setPhoto(cursor.getString(10));
                        general.setNc(cursor.getInt(11));
                        general.setProcTracker(cursor.getInt(12));
                        generalImageModelList.add(general);
                    }
                    Log.e("test2","test2");
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }

        return generalImageModelList;
    }

    public boolean updateLandDetails(LandImage landImage) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        try {

            Log.e("test4","test4");

            values.put("photo", landImage.getPhoto());
            values.put("remarks", landImage.getRemarks());
            values.put("nc", landImage.getNc());
            values.put("proc_tracker", landImage.getProcTracker());

            database.update("LandAndBuildingInfoImage", values, " YearWiseCollegeId= " + landImage.getYearWiseCollegeId()
                    + " AND docId=" + landImage.getDocId(), null);
            Log.e("test5","test5");
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            database.close();
        }
        return true;
    }

    public List<LandImage> getUnsavedLandImageInfobyYearwiseCollegeId(String yearWiseCollegeId, Integer docId) {

        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , docId , section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc, proc_tracker"
                + " FROM LandAndBuildingInfoImage where YearWiseCollegeId = " + yearWiseCollegeId + " AND docId='" + docId + "' "
                + " AND proc_tracker = " + 2;

        Log.e("test1","test1");
        List<LandImage> generalList = new ArrayList<LandImage>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                do {
                    String section = cursor.getString(2);
                    if (section != null && section.trim().equalsIgnoreCase("Land And Building Of Institute Details")) {
                        LandImage general = new LandImage();
                        general.setYearWiseCollegeId(cursor.getString(0));
                        general.setDocId(cursor.getInt(1));
                        general.setSection(cursor.getString(2));
                        general.setRefId(cursor.getInt(3));
                        general.setPictureType(cursor.getString(4));
                        general.setHelpText(cursor.getString(5));
                        general.setRemarks(cursor.getString(6));
                        general.setLatitude(cursor.getString(7));
                        general.setLongitude(cursor.getString(8));
                        general.setName(cursor.getString(9));
                        general.setPhoto(cursor.getString(10));
                        general.setNc(cursor.getInt(11));
                        general.setProcTracker(cursor.getInt(12));
                        generalList.add(general);
                    }
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
        Log.e("test2","test2");

        return generalList;
    }

    public boolean updateLandProcTracker(List<LandImage> lstLandToBeSync, String yearWiseCollegeId) {

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        if (lstLandToBeSync == null || lstLandToBeSync.size() == 0) {
            return false;
        }

        StringBuilder sbIds = new StringBuilder();
        for (LandImage fac : lstLandToBeSync) {
            sbIds.append("'").append(fac.getDocId()).append("'").append(",");
        }
        if (sbIds.length() > 0) {
            sbIds.setLength(sbIds.length() - 1);
        }

        try {
            values.put("proc_tracker", 3);
            database.update("LandAndBuildingInfoImage", values, " YearWiseCollegeId= " + yearWiseCollegeId + " AND docId IN (" + sbIds.toString() + ")", null);

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            database.close();
        }
        return true;
    }

    public boolean updateTechnicalDetails(TechnicalImage technicalImage) {

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        try {

            Log.e("test4","test4");

            values.put("photo", technicalImage.getPhoto());
            values.put("remarks", technicalImage.getRemarks());
            values.put("nc", technicalImage.getNc());
            values.put("proc_tracker", technicalImage.getProcTracker());

            database.update("TechnicalInfoImage", values, " YearWiseCollegeId= " + technicalImage.getYearWiseCollegeId()
                    + " AND docId=" + technicalImage.getDocId()+" AND refId=" + technicalImage.getRefId(), null);
            Log.e("test5","test5");
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            database.close();
        }
        return true;
    }

//    public List<TechnicalImage> getUnsavedTechnicalImageInfobyYearwiseCollegeId(String yearWiseCollegeId, Integer docId, Integer id) {
//        SQLiteDatabase db = null;
//        String selectQueryQues = "select YearWiseCollegeId , docId , section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc, proc_tracker"
//                + " FROM TechnicalInfoImage where YearWiseCollegeId = " + yearWiseCollegeId + " AND refId='" + id + "' "+ " AND docId = '" + docId + "' AND proc_tracker = " + 2;
//
//        Log.e("test1","test1");
//        List<TechnicalImage> generalList = new ArrayList<TechnicalImage>();
//        try {
//            db = this.getReadableDatabase();
//            Cursor cursor = db.rawQuery(selectQueryQues, null);
//            if (cursor.moveToFirst()) {
//                do {
//                    String section = cursor.getString(2);
//                    if (section != null && section.trim().equalsIgnoreCase("Technical Staff Documents")) {
//                        TechnicalImage general = new TechnicalImage();
//                        general.setYearWiseCollegeId(cursor.getString(0));
//                        general.setDocId(cursor.getInt(1));
//                        general.setSection(cursor.getString(2));
//                        general.setRefId(cursor.getInt(3));
//                        general.setPictureType(cursor.getString(4));
//                        general.setHelpText(cursor.getString(5));
//                        general.setRemarks(cursor.getString(6));
//                        general.setLatitude(cursor.getString(7));
//                        general.setLongitude(cursor.getString(8));
//                        general.setName(cursor.getString(9));
//                        general.setPhoto(cursor.getString(10));
//                        general.setNc(cursor.getInt(11));
//                        general.setProcTracker(cursor.getInt(12));
//                        generalList.add(general);
//                    }
//                } while (cursor.moveToNext());
//            }
//        } catch (Exception e) {
//            Log.e(TAG, e.getMessage());
//            return null;
//        }
//        Log.e("test2","test2");
//
//        return generalList;
//    }

    public List<TechnicalImage> getTechnicalImageInfobyYearwiseCollegeId(String yearWiseCollegeId) {

        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , docId,"
                + " section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc, proc_tracker"
                + " FROM TechnicalInfoImage where YearWiseCollegeId = " + yearWiseCollegeId;

        List<TechnicalImage> generalImageModelList = new ArrayList<TechnicalImage>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                Log.e("test","test");
                do {
                    String section = cursor.getString(2);
                    if (section != null && section.trim().equalsIgnoreCase("Technical Staff Documents")) {
                        TechnicalImage general = new TechnicalImage();
                        general.setYearWiseCollegeId(cursor.getString(0));
                        general.setDocId(cursor.getInt(1));
                        general.setSection(cursor.getString(2));
                        general.setRefId(cursor.getInt(3));
                        general.setPictureType(cursor.getString(4));
                        general.setHelpText(cursor.getString(5));
                        general.setRemarks(cursor.getString(6));
                        general.setLatitude(cursor.getString(7));
                        general.setLongitude(cursor.getString(8));
                        general.setName(cursor.getString(9));
                        general.setPhoto(cursor.getString(10));
                        general.setNc(cursor.getInt(11));
                        general.setProcTracker(cursor.getInt(12));
                        generalImageModelList.add(general);
                    }
                    Log.e("test","test");
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }

        return generalImageModelList;
    }

    public boolean updateTechnicalProcTracker(List<TechnicalImage> lstTechnicalToBeSync, String yearWiseCollegeId) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        if (lstTechnicalToBeSync == null || lstTechnicalToBeSync.size() == 0) {
            return false;
        }

        StringBuilder sbIds = new StringBuilder();
        for (TechnicalImage fac : lstTechnicalToBeSync) {
            sbIds.append("'").append(fac.getDocId()).append("'").append(",");
        }
        if (sbIds.length() > 0) {
            sbIds.setLength(sbIds.length() - 1);
        }

        try {
            values.put("proc_tracker", 3);
            database.update("TechnicalInfoImage", values, " YearWiseCollegeId= " + yearWiseCollegeId + " AND docId IN (" + sbIds.toString() + ") AND refId = "+lstTechnicalToBeSync.get(0).getRefId(), null);

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            database.close();
        }
        return true;
    }

    public boolean updateWorkshopDetails(Workshop technicalImage) {

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        try {

            Log.e("test4","test4");

            values.put("photo", technicalImage.getPhoto());
            values.put("remarks", technicalImage.getRemarks());
            values.put("nc", technicalImage.getNc());
            values.put("proc_tracker", technicalImage.getProcTracker());

            database.update("WorkshopInfoImage", values, " YearWiseCollegeId= " + technicalImage.getYearWiseCollegeId()
                    + " AND docId=" + technicalImage.getDocId()+" AND refId=" + technicalImage.getRefId(), null);
            Log.e("test5","test5");
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            database.close();
        }
        return true;
    }

    public List<Workshop> getWorkshopImageInfobyYearwiseCollegeId(String yearWiseCollegeId) {

        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , docId,"
                + " section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc,flag, proc_tracker"
                + " FROM WorkshopInfoImage where YearWiseCollegeId = " + yearWiseCollegeId;

        List<Workshop> generalImageModelList = new ArrayList<Workshop>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                Log.e("test","test");
                do {
                    String section = cursor.getString(2);
                    if (section != null && section.trim().equalsIgnoreCase("Workshop")) {
                        Workshop general = new Workshop();
                        general.setYearWiseCollegeId(cursor.getString(0));
                        general.setDocId(cursor.getInt(1));
                        general.setSection(cursor.getString(2));
                        general.setRefId(cursor.getInt(3));
                        general.setPictureType(cursor.getString(4));
                        general.setHelpText(cursor.getString(5));
                        general.setRemarks(cursor.getString(6));
                        general.setLatitude(cursor.getString(7));
                        general.setLongitude(cursor.getString(8));
                        general.setName(cursor.getString(9));
                        general.setPhoto(cursor.getString(10));
                        general.setNc(cursor.getInt(11));
                        general.setFlag(cursor.getInt(12));
                        general.setProcTracker(cursor.getInt(13));
                        generalImageModelList.add(general);
                    }
                    Log.e("test","test");
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }

        return generalImageModelList;
    }

    public boolean updateWorkshopProcTracker(List<Workshop> lstTechnicalToBeSync, String yearWiseCollegeId) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        if (lstTechnicalToBeSync == null || lstTechnicalToBeSync.size() == 0) {
            return false;
        }

        StringBuilder sbIds = new StringBuilder();
        for (Workshop fac : lstTechnicalToBeSync) {
            sbIds.append("'").append(fac.getDocId()).append("'").append(",");
        }
        if (sbIds.length() > 0) {
            sbIds.setLength(sbIds.length() - 1);
        }

        try {
            values.put("proc_tracker", 3);
            database.update("WorkshopInfoImage", values, " YearWiseCollegeId= " + yearWiseCollegeId + " AND docId IN (" + sbIds.toString() + ") AND refId = "+lstTechnicalToBeSync.get(0).getRefId(), null);

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            database.close();
        }
        return true;
    }

    public boolean updateInstructorDetails(InstructorImage instructorImage) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        try {

            Log.e("test4","test4");

            values.put("photo", instructorImage.getPhoto());
            values.put("remarks", instructorImage.getRemarks());
            values.put("nc", instructorImage.getNc());
            values.put("proc_tracker", instructorImage.getProcTracker());

            database.update("InstructorInfoImage", values, " YearWiseCollegeId= " + instructorImage.getYearWiseCollegeId()
                    + " AND docId=" + instructorImage.getDocId()+" AND refId=" + instructorImage.getRefId(), null);
            Log.e("test5","test5");
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            database.close();
        }
        return true;
    }

//    public List<InstructorImage> getUnsavedInstructorImageInfobyYearwiseCollegeId(String yearWiseCollegeId, Integer docId, Integer id) {
//        SQLiteDatabase db = null;
//        String selectQueryQues = "select YearWiseCollegeId , docId , section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc, proc_tracker"
//                + " FROM InstructorInfoImage where YearWiseCollegeId = " + yearWiseCollegeId + " AND docId='" + docId + "' "
//                + " AND proc_tracker = " + 2;
//
//        Log.e("test1","test1");
//        List<InstructorImage> generalList = new ArrayList<InstructorImage>();
//        try {
//            db = this.getReadableDatabase();
//            Cursor cursor = db.rawQuery(selectQueryQues, null);
//            if (cursor.moveToFirst()) {
//                do {
//                    String section = cursor.getString(2);
//                    if (section != null && section.trim().equalsIgnoreCase("Instructor Staff Documents")) {
//                        InstructorImage general = new InstructorImage();
//                        general.setYearWiseCollegeId(cursor.getString(0));
//                        general.setDocId(cursor.getInt(1));
//                        general.setSection(cursor.getString(2));
//                        general.setRefId(cursor.getInt(3));
//                        general.setPictureType(cursor.getString(4));
//                        general.setHelpText(cursor.getString(5));
//                        general.setRemarks(cursor.getString(6));
//                        general.setLatitude(cursor.getString(7));
//                        general.setLongitude(cursor.getString(8));
//                        general.setName(cursor.getString(9));
//                        general.setPhoto(cursor.getString(10));
//                        general.setNc(cursor.getInt(11));
//                        general.setProcTracker(cursor.getInt(12));
//                        generalList.add(general);
//                    }
//                } while (cursor.moveToNext());
//            }
//        } catch (Exception e) {
//            Log.e(TAG, e.getMessage());
//            return null;
//        }
//        Log.e("test2","test2");
//
//        return generalList;
//    }

    public List<InstructorImage> getInstructorImageInfobyYearwiseCollegeId(String yearWiseCollegeId) {
        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , docId,"
                + " section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc, proc_tracker"
                + " FROM InstructorInfoImage where YearWiseCollegeId = " + yearWiseCollegeId;

        List<InstructorImage> generalImageModelList = new ArrayList<InstructorImage>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                Log.e("test","test");
                do {
                    String section = cursor.getString(2);
                    if (section != null && section.trim().equalsIgnoreCase("Instructor Staff Documents")) {
                        InstructorImage general = new InstructorImage();
                        general.setYearWiseCollegeId(cursor.getString(0));
                        general.setDocId(cursor.getInt(1));
                        general.setSection(cursor.getString(2));
                        general.setRefId(cursor.getInt(3));
                        general.setPictureType(cursor.getString(4));
                        general.setHelpText(cursor.getString(5));
                        general.setRemarks(cursor.getString(6));
                        general.setLatitude(cursor.getString(7));
                        general.setLongitude(cursor.getString(8));
                        general.setName(cursor.getString(9));
                        general.setPhoto(cursor.getString(10));
                        general.setNc(cursor.getInt(11));
                        general.setProcTracker(cursor.getInt(12));
                        generalImageModelList.add(general);
                    }
                    Log.e("test","test");
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }

        return generalImageModelList;
    }


    public boolean updateInstructorProcTracker(List<InstructorImage> lstInstructorToBeSync, String yearWiseCollegeId) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        if (lstInstructorToBeSync == null || lstInstructorToBeSync.size() == 0) {
            return false;
        }

        StringBuilder sbIds = new StringBuilder();
        for (InstructorImage fac : lstInstructorToBeSync) {
            sbIds.append("'").append(fac.getDocId()).append("'").append(",");
        }
        if (sbIds.length() > 0) {
            sbIds.setLength(sbIds.length() - 1);
        }

        try {
            values.put("proc_tracker", 3);
            database.update("InstructorInfoImage", values, " YearWiseCollegeId= " + yearWiseCollegeId + " AND docId IN (" + sbIds.toString() + ") AND refId = "+lstInstructorToBeSync.get(0).getRefId(), null);

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            database.close();
        }
        return true;
    }

    public boolean updateLabDetails(LabImage labImage) {

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        try {

            Log.e("test4","test4");

            values.put("photo", labImage.getPhoto());
            values.put("remarks", labImage.getRemarks());
            values.put("nc", labImage.getNc());
            values.put("proc_tracker", labImage.getProcTracker());

            database.update("ITLabInfoImage", values, " YearWiseCollegeId= " + labImage.getYearWiseCollegeId()
                    + " AND docId=" + labImage.getDocId()+" AND refId=" + labImage.getRefId(), null);
            Log.e("test5","test5");
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            database.close();
        }
        return true;
    }

//    public List<LabImage> getUnsavedLabImageInfobyYearwiseCollegeId(String yearWiseCollegeId, Integer docId, Integer id) {
//        SQLiteDatabase db = null;
//        String selectQueryQues = "select YearWiseCollegeId , docId , section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc, proc_tracker"
//                + " FROM ITLabInfoImage where YearWiseCollegeId = " + yearWiseCollegeId + " AND refId='" + id + "' "+ " AND docId = '" + docId + "' AND proc_tracker = " + 2;
//
//        Log.e("test1","test1");
//        List<LabImage> generalList = new ArrayList<LabImage>();
//        try {
//            db = this.getReadableDatabase();
//            Cursor cursor = db.rawQuery(selectQueryQues, null);
//            if (cursor.moveToFirst()) {
//                do {
//                    String section = cursor.getString(2);
//                    if (section != null && section.trim().equalsIgnoreCase("IT LAB")) {
//                        LabImage general = new LabImage();
//                        general.setYearWiseCollegeId(cursor.getString(0));
//                        general.setDocId(cursor.getInt(1));
//                        general.setSection(cursor.getString(2));
//                        general.setRefId(cursor.getInt(3));
//                        general.setPictureType(cursor.getString(4));
//                        general.setHelpText(cursor.getString(5));
//                        general.setRemarks(cursor.getString(6));
//                        general.setLatitude(cursor.getString(7));
//                        general.setLongitude(cursor.getString(8));
//                        general.setName(cursor.getString(9));
//                        general.setPhoto(cursor.getString(10));
//                        general.setNc(cursor.getInt(11));
//                        general.setProcTracker(cursor.getInt(12));
//                        generalList.add(general);
//                    }
//                } while (cursor.moveToNext());
//            }
//        } catch (Exception e) {
//            Log.e(TAG, e.getMessage());
//            return null;
//        }
//        Log.e("test2","test2");
//
//        return generalList;
//    }

    public List<LabImage> getLabImageInfobyYearwiseCollegeId(String yearWiseCollegeId) {
        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , docId,"
                + " section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc, proc_tracker"
                + " FROM ITLabInfoImage where YearWiseCollegeId = " + yearWiseCollegeId;

        List<LabImage> generalImageModelList = new ArrayList<LabImage>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                Log.e("test","test");
                do {
                    String section = cursor.getString(2);
                    if (section != null && section.trim().equalsIgnoreCase("IT LAB")) {
                        LabImage general = new LabImage();
                        general.setYearWiseCollegeId(cursor.getString(0));
                        general.setDocId(cursor.getInt(1));
                        general.setSection(cursor.getString(2));
                        general.setRefId(cursor.getInt(3));
                        general.setPictureType(cursor.getString(4));
                        general.setHelpText(cursor.getString(5));
                        general.setRemarks(cursor.getString(6));
                        general.setLatitude(cursor.getString(7));
                        general.setLongitude(cursor.getString(8));
                        general.setName(cursor.getString(9));
                        general.setPhoto(cursor.getString(10));
                        general.setNc(cursor.getInt(11));
                        general.setProcTracker(cursor.getInt(12));
                        generalImageModelList.add(general);
                    }
                    Log.e("test","test");
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }

        return generalImageModelList;
    }

    public boolean updateLabProcTracker(List<LabImage> lstlabToBeSync, String yearWiseCollegeId) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        if (lstlabToBeSync == null || lstlabToBeSync.size() == 0) {
            return false;
        }

        StringBuilder sbIds = new StringBuilder();
        for (LabImage fac : lstlabToBeSync) {
            sbIds.append("'").append(fac.getDocId()).append("'").append(",");
        }
        if (sbIds.length() > 0) {
            sbIds.setLength(sbIds.length() - 1);
        }

        try {
            values.put("proc_tracker", 3);
            database.update("ITLabInfoImage", values, " YearWiseCollegeId= " + yearWiseCollegeId + " AND docId IN (" + sbIds.toString() + ") AND refId = "+lstlabToBeSync.get(0).getRefId(), null);

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            database.close();
        }
        return true;
    }

    public boolean updateClassroomDetails(ClassroomImage labImage) {

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        try {

            Log.e("test4","test4");

            values.put("photo", labImage.getPhoto());
            values.put("remarks", labImage.getRemarks());
            values.put("nc", labImage.getNc());
            values.put("proc_tracker", labImage.getProcTracker());

            database.update("ClassroomInfoImage", values, " YearWiseCollegeId= " + labImage.getYearWiseCollegeId()
                    + " AND docId=" + labImage.getDocId()+" AND refId=" + labImage.getRefId(), null);
            Log.e("test5","test5");
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            database.close();
        }
        return true;
    }

    public List<ClassroomImage> getClassroomImageInfobyYearwiseCollegeId(String yearWiseCollegeId) {
        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , docId,"
                + " section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc,flag,tradeId, proc_tracker"
                + " FROM ClassroomInfoImage where YearWiseCollegeId = " + yearWiseCollegeId;

        List<ClassroomImage> generalImageModelList = new ArrayList<ClassroomImage>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                Log.e("test","test");
                do {
                    String section = cursor.getString(2);
                    if (section != null && section.trim().equalsIgnoreCase("Classroom")) {
                        ClassroomImage general = new ClassroomImage();
                        general.setYearWiseCollegeId(cursor.getString(0));
                        general.setDocId(cursor.getInt(1));
                        general.setSection(cursor.getString(2));
                        general.setRefId(cursor.getInt(3));
                        general.setPictureType(cursor.getString(4));
                        general.setHelpText(cursor.getString(5));
                        general.setRemarks(cursor.getString(6));
                        general.setLatitude(cursor.getString(7));
                        general.setLongitude(cursor.getString(8));
                        general.setName(cursor.getString(9));
                        general.setPhoto(cursor.getString(10));
                        general.setNc(cursor.getInt(11));
                        general.setFlag(cursor.getInt(12));
                        general.setTradeId(cursor.getInt(13));
                        general.setProcTracker(cursor.getInt(14));
                        generalImageModelList.add(general);
                    }
                    Log.e("test","test");
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }

        return generalImageModelList;
    }

    public boolean updateClassProcTracker(List<ClassroomImage> lstlabToBeSync, String yearWiseCollegeId) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        if (lstlabToBeSync == null || lstlabToBeSync.size() == 0) {
            return false;
        }

        StringBuilder sbIds = new StringBuilder();
        for (ClassroomImage fac : lstlabToBeSync) {
            sbIds.append("'").append(fac.getDocId()).append("'").append(",");
        }
        if (sbIds.length() > 0) {
            sbIds.setLength(sbIds.length() - 1);
        }

        try {
            values.put("proc_tracker", 3);
            database.update("ClassroomInfoImage", values, " YearWiseCollegeId= " + yearWiseCollegeId + " AND docId IN (" + sbIds.toString() + ") AND refId = "+lstlabToBeSync.get(0).getRefId(), null);

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            database.close();
        }
        return true;
    }

    public boolean updateToolsAboveDetails(ToolsAboveImage labImage) {

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        try {

            Log.e("test4","test4");

            values.put("photo", labImage.getPhoto());
            values.put("remarks", labImage.getRemarks());
            values.put("nc", labImage.getNc());
            values.put("proc_tracker", labImage.getProcTracker());

            database.update("ToolsAboveInfoImage", values, " YearWiseCollegeId= " + labImage.getYearWiseCollegeId()
                    + " AND docId=" + labImage.getDocId()+" AND refId=" + labImage.getRefId(), null);
            Log.e("test5","test5");
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            database.close();
        }
        return true;
    }



    public List<ToolsAboveImage> getToolsAboveImageInfobyYearwiseCollegeId(String yearWiseCollegeId) {
        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , docId,"
                + " section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc, proc_tracker"
                + " FROM ToolsAboveInfoImage where YearWiseCollegeId = " + yearWiseCollegeId;

        List<ToolsAboveImage> generalImageModelList = new ArrayList<ToolsAboveImage>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                Log.e("test","test");
                do {
                    String section = cursor.getString(5);
                    if (section != null && section.trim().equalsIgnoreCase("(Workshop wise Tools above 10000 value")) {
                        ToolsAboveImage general = new ToolsAboveImage();
                        general.setYearWiseCollegeId(cursor.getString(0));
                        general.setDocId(cursor.getInt(1));
                        general.setSection(cursor.getString(2));
                        general.setRefId(cursor.getInt(3));
                        general.setPictureType(cursor.getString(4));
                        general.setHelpText(cursor.getString(5));
                        general.setRemarks(cursor.getString(6));
                        general.setLatitude(cursor.getString(7));
                        general.setLongitude(cursor.getString(8));
                        general.setName(cursor.getString(9));
                        general.setPhoto(cursor.getString(10));
                        general.setNc(cursor.getInt(11));
                        general.setProcTracker(cursor.getInt(12));
                        generalImageModelList.add(general);
                    }
                    Log.e("test","test");
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }

        return generalImageModelList;
    }

    public boolean updateToolsAboveProcTracker(List<ToolsAboveImage> lstlabToBeSync, String yearWiseCollegeId) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        if (lstlabToBeSync == null || lstlabToBeSync.size() == 0) {
            return false;
        }

        StringBuilder sbIds = new StringBuilder();
        for (ToolsAboveImage fac : lstlabToBeSync) {
            sbIds.append("'").append(fac.getDocId()).append("'").append(",");
        }
        if (sbIds.length() > 0) {
            sbIds.setLength(sbIds.length() - 1);
        }

        try {
            values.put("proc_tracker", 3);
            database.update("ToolsAboveInfoImage", values, " YearWiseCollegeId= " + yearWiseCollegeId + " AND docId IN (" + sbIds.toString()+ ") AND refId = "+lstlabToBeSync.get(0).getRefId(), null);

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            database.close();
        }
        return true;
    }


    public boolean updatePowerDetails(PowerImage powerImage) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        try {

            Log.e("test4","test4");

            values.put("photo", powerImage.getPhoto());
            values.put("remarks", powerImage.getRemarks());
            values.put("nc", powerImage.getNc());
            values.put("proc_tracker", powerImage.getProcTracker());

            database.update("PowerSupplyInfoImage", values, " YearWiseCollegeId= " + powerImage.getYearWiseCollegeId()
                    + " AND docId=" + powerImage.getDocId(), null);
            Log.e("test5","test5");
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            database.close();
        }
        return true;
    }

    public List<PowerImage> getUnsavedPowerImageInfobyYearwiseCollegeId(String yearWiseCollegeId, Integer docId) {

        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , docId , section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc, proc_tracker"
                + " FROM PowerSupplyInfoImage where YearWiseCollegeId = " + yearWiseCollegeId + " AND docId='" + docId + "' "
                + " AND proc_tracker = " + 2;

        Log.e("test1","test1");
        List<PowerImage> generalList = new ArrayList<PowerImage>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                do {
                    String section = cursor.getString(2);
                    if (section != null && section.trim().equalsIgnoreCase("Power Supply")) {
                        PowerImage general = new PowerImage();
                        general.setYearWiseCollegeId(cursor.getString(0));
                        general.setDocId(cursor.getInt(1));
                        general.setSection(cursor.getString(2));
                        general.setRefId(cursor.getInt(3));
                        general.setPictureType(cursor.getString(4));
                        general.setHelpText(cursor.getString(5));
                        general.setRemarks(cursor.getString(6));
                        general.setLatitude(cursor.getString(7));
                        general.setLongitude(cursor.getString(8));
                        general.setName(cursor.getString(9));
                        general.setPhoto(cursor.getString(10));
                        general.setNc(cursor.getInt(11));
                        general.setProcTracker(cursor.getInt(12));
                        generalList.add(general);
                    }
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
        Log.e("test2","test2");

        return generalList;
    }

    public List<PowerImage> getPowerImageInfobyYearwiseCollegeId(String yearWiseCollegeId) {
        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , docId,"
                + " section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc, proc_tracker"
                + " FROM PowerSupplyInfoImage where YearWiseCollegeId = " + yearWiseCollegeId;

        List<PowerImage> generalImageModelList = new ArrayList<PowerImage>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                Log.e("test","test");
                do {
                    String section = cursor.getString(2);
                    if (section != null && section.trim().equalsIgnoreCase("Power Supply")) {
                        PowerImage general = new PowerImage();
                        general.setYearWiseCollegeId(cursor.getString(0));
                        general.setDocId(cursor.getInt(1));
                        general.setSection(cursor.getString(2));
                        general.setRefId(cursor.getInt(3));
                        general.setPictureType(cursor.getString(4));
                        general.setHelpText(cursor.getString(5));
                        general.setRemarks(cursor.getString(6));
                        general.setLatitude(cursor.getString(7));
                        general.setLongitude(cursor.getString(8));
                        general.setName(cursor.getString(9));
                        general.setPhoto(cursor.getString(10));
                        general.setNc(cursor.getInt(11));
                        general.setProcTracker(cursor.getInt(12));
                        generalImageModelList.add(general);
                    }
                    Log.e("test","test");
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }

        return generalImageModelList;
    }

    public boolean updatePowerProcTracker(List<PowerImage> lstPowerrToBeSync, String yearWiseCollegeId) {

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        if (lstPowerrToBeSync == null || lstPowerrToBeSync.size() == 0) {
            return false;
        }

        StringBuilder sbIds = new StringBuilder();
        for (PowerImage fac : lstPowerrToBeSync) {
            sbIds.append("'").append(fac.getDocId()).append("'").append(",");
        }
        if (sbIds.length() > 0) {
            sbIds.setLength(sbIds.length() - 1);
        }

        try {
            values.put("proc_tracker", 3);
            database.update("PowerSupplyInfoImage", values, " YearWiseCollegeId= " + yearWiseCollegeId + " AND docId IN (" + sbIds.toString() + ")", null);

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            database.close();
        }
        return true;
    }

    public boolean addClassroomInfoUploadImageData(JSONObject result, String instituteId) {
        try {

            SQLiteDatabase database = getWritableDatabase();

            int status = result.optInt("status");
            if(status == 0){
                JSONObject payloadObj = result.optJSONObject("payload");
                JSONArray GeneralDetailsArr = payloadObj.optJSONArray("classroomDetailsDoc");
                for (int i = 0;i < GeneralDetailsArr.length();i++){
                    JSONObject GeneralDetailsObj = GeneralDetailsArr.optJSONObject(i);
                    ContentValues values = new ContentValues();
                    values.put("YearWiseCollegeId",instituteId);
                    values.put("tradeId",GeneralDetailsObj.optString("tradeId"));
                    values.put("docId",GeneralDetailsObj.optString("docId"));
                    values.put("section",GeneralDetailsObj.optString("section"));
                    values.put("refId",GeneralDetailsObj.optString("refId"));
                    values.put("pictureType",GeneralDetailsObj.optString("pictureType"));
                    values.put("helpText",GeneralDetailsObj.optString("helpText"));
                    values.put("remarks",GeneralDetailsObj.optString("remarks"));
                    values.put("latitude",GeneralDetailsObj.optString("latitude"));
                    values.put("longitude",GeneralDetailsObj.optString("longitude"));
                    values.put("name",GeneralDetailsObj.optString("name"));
                    values.put("photo",GeneralDetailsObj.optString("photo"));
                    values.put("nc",GeneralDetailsObj.optString("nc"));
                    values.put("flag",GeneralDetailsObj.optString("flag"));
                    values.put("proc_tracker", 1);

                    database.insert("ClassroomInfoImage", null, values);
                }

            }else {
               // Toast.makeText(application_context, "No images found in General Details", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean addToolsAboveUploadImageData(JSONObject result, String instituteId) {

        try {

            SQLiteDatabase database = getWritableDatabase();

            int status = result.optInt("status");
            if(status == 0){
                JSONObject payloadObj = result.optJSONObject("payload");
                JSONArray GeneralDetailsArr = payloadObj.optJSONArray("workShopToolAbovePhotoDoc");
                for (int i = 0;i < GeneralDetailsArr.length();i++){
                    JSONObject GeneralDetailsObj = GeneralDetailsArr.optJSONObject(i);
                    ContentValues values = new ContentValues();
                    values.put("YearWiseCollegeId",instituteId);
                    values.put("docId",GeneralDetailsObj.optString("docId"));
                    values.put("section",GeneralDetailsObj.optString("section"));
                    values.put("refId",GeneralDetailsObj.optString("refId"));
                    values.put("pictureType",GeneralDetailsObj.optString("pictureType"));
                    values.put("helpText",GeneralDetailsObj.optString("helpText"));
                    values.put("remarks",GeneralDetailsObj.optString("remarks"));
                    values.put("latitude",GeneralDetailsObj.optString("latitude"));
                    values.put("longitude",GeneralDetailsObj.optString("longitude"));
                    values.put("name",GeneralDetailsObj.optString("name"));
                    values.put("photo",GeneralDetailsObj.optString("photo"));
                    values.put("nc",GeneralDetailsObj.optString("nc"));
                    values.put("proc_tracker", 1);

                    database.insert("ToolsAboveInfoImage", null, values);
                }

            }else {
               // Toast.makeText(application_context, "No images found in General Details", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean addWorkshopUploadImageData(JSONObject result, String instituteId) {

        try {

            SQLiteDatabase database = getWritableDatabase();

            int status = result.optInt("status");
            if(status == 0){
                JSONObject payloadObj = result.optJSONObject("payload");
                JSONArray GeneralDetailsArr = payloadObj.optJSONArray("workShopDoc");
                for (int i = 0;i < GeneralDetailsArr.length();i++){
                    JSONObject GeneralDetailsObj = GeneralDetailsArr.optJSONObject(i);
                    ContentValues values = new ContentValues();
                    values.put("YearWiseCollegeId",instituteId);
                    values.put("docId",GeneralDetailsObj.optString("docId"));
                    values.put("section",GeneralDetailsObj.optString("section"));
                    values.put("refId",GeneralDetailsObj.optString("refId"));
                    values.put("pictureType",GeneralDetailsObj.optString("pictureType"));
                    values.put("helpText",GeneralDetailsObj.optString("helpText"));
                    values.put("remarks",GeneralDetailsObj.optString("remarks"));
                    values.put("latitude",GeneralDetailsObj.optString("latitude"));
                    values.put("longitude",GeneralDetailsObj.optString("longitude"));
                    values.put("name",GeneralDetailsObj.optString("name"));
                    values.put("photo",GeneralDetailsObj.optString("photo"));
                    values.put("nc",GeneralDetailsObj.optString("nc"));
                    values.put("flag",GeneralDetailsObj.optString("flag"));
                    values.put("proc_tracker", 1);

                    database.insert("WorkshopInfoImage", null, values);
                }

            }else {
              //  Toast.makeText(application_context, "No images found in General Details", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean saveTrustInfo(EquipmentInfo trustGeneralInfo, String mode) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("refId",trustGeneralInfo.getRefId());
        values.put("instituteOwned",trustGeneralInfo.getInstituteOwned());
        values.put("instituteOwnedRemarks",trustGeneralInfo.getInstituteOwnedRemrks());
        values.put("isRegistered",trustGeneralInfo.getIsRegistered());
        values.put("isRegisteredRemarks",trustGeneralInfo.getIsregisteredRemarks());
        values.put("isRegisteredNC",trustGeneralInfo.getIsregisteredNc());
        values.put("registration",trustGeneralInfo.getRegistration());
        values.put("registrationNo",trustGeneralInfo.getRegistrationNo());
        values.put("registrationNoRemarks",trustGeneralInfo.getRegistrationnoRemarks());
        values.put("registrationNoNC",trustGeneralInfo.getRegistrationnoNc());
        values.put("trustName",trustGeneralInfo.getTrustName());
        values.put("trustNameRemarks",trustGeneralInfo.getTrustnameRemarks());
        values.put("trustNameNC",trustGeneralInfo.getTrustnameNc());
        values.put("registrationDate",trustGeneralInfo.getRegistrationDate());
        values.put("trustValidity",trustGeneralInfo.getTrustValidity());
        values.put("trustValidityRemarks",trustGeneralInfo.getTrustvalidityRemarks());
        values.put("trustValidityNC",trustGeneralInfo.getTrustvalidityNc());
        values.put("panNumber",trustGeneralInfo.getPanNumber());
        values.put("panNumberRemarks",trustGeneralInfo.getPannumberRemarks());
        values.put("panNumberNC",trustGeneralInfo.getPannumberNc());
        values.put("experience",trustGeneralInfo.getExperience());
        values.put("experienceRemarks",trustGeneralInfo.getExperienceRemarks());
        values.put("experienceNC",trustGeneralInfo.getExperienceNc());
        values.put("educationalType",trustGeneralInfo.getType());
        values.put("educationalTypeRemarks",trustGeneralInfo.getTypeRemarks());
        values.put("educationalTypeNC",trustGeneralInfo.getTypeNc());
        values.put("commonNC",trustGeneralInfo.getCommonNc());
        values.put("remarks",trustGeneralInfo.getRemarks());

        if (mode.equalsIgnoreCase("draft")) {
            values.put("proc_tracker", 2);
        } else {
            values.put("proc_tracker", 3);
        }
        db.update("TrustGeneralInfo", values, " YearWiseCollegeId= " + trustGeneralInfo.getYearWiseCollegeid(), null);

        return true;
    }

    public boolean saveOrganisationInfo(MaterialInfo materialInfo, String mode) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("refId", materialInfo.getRefId());
        values.put("Organization_Name", materialInfo.getOrganizationName());
        values.put("Organization_NameRemarks" , materialInfo.getOrganizationnameRemarks());
        values.put("Organization_NameNC", materialInfo.getOrganizationnameNc());
        values.put("Authorized_Person", materialInfo.getAuthorizedPerson());
        values.put("Authorized_PersonRemarks", materialInfo.getAuthorizedpersonRemarks());
        values.put("Authorized_PersonNC", materialInfo.getAuthorizedpersonNc());
        values.put("Org_Mobile_no", materialInfo.getOrgMobileno());
        values.put("Org_Mobile_noRemarks", materialInfo.getOrgmobilenoRemarks());
        values.put("Org_Mobile_noNC", materialInfo.getOrgmobilenoNc());
        values.put("Org_Email", materialInfo.getOrgEmail());
        values.put("Org_EmailRemarks", materialInfo.getOrgemailRemarks());
        values.put("Org_EmailNC ", materialInfo.getOrgemailNc());
        values.put("Org_website", materialInfo.getOrgWebsite());
        values.put("Org_websiteRemarks", materialInfo.getOrgwebsiteRemarks());
        values.put("Org_websiteNC", materialInfo.getOrgwebsiteNc());
        values.put("Org_Telephone_no", materialInfo.getOrgTelephoneno());
        values.put("Org_Telephone_noRemarks", materialInfo.getOrgtelephonenoRemarks());
        values.put("Org_Telephone_noNC", materialInfo.getOrgtelephonenoNc());
        values.put("Org_Postal_Address ", materialInfo.getOrgPostaladdress());
        values.put("Org_Postal_AddressRemarks", materialInfo.getOrgpostaladdressRmarks());
        values.put("Org_Postal_AddressNC", materialInfo.getOrgpostaladdressNc());
        values.put("StateName", materialInfo.getStateName());
        values.put("StateNameRemarks", materialInfo.getStatenameRemarks());
        values.put("StateNameNC", materialInfo.getStatenameNc());
        values.put("DistrictName", materialInfo.getDistrictName());
        values.put("DistrictNameRemarks", materialInfo.getDistrictnameRemarks());
        values.put("DistrictNameNC", materialInfo.getDistrictnameNc());
        values.put("MandalName", materialInfo.getMandalName());
        values.put("MandalNameRemarks", materialInfo.getMandalnameRemarks());
        values.put("MandalNameNC", materialInfo.getMandalnameNc());
        values.put("Org_Pincode", materialInfo.getOrgPincode());
        values.put("Org_PincodeRemarks", materialInfo.getOrgpincodeRemarks());
        values.put("Org_PincodeNC", materialInfo.getOrgpincodeNc());
        values.put("Org_LandMark", materialInfo.getOrgLandmark());
        values.put("Org_LandMarkRemarks", materialInfo.getOrglandmarkRemarks());
        values.put("Org_LandMarkNC ", materialInfo.getOrglandmarkNc());
        values.put("Authorized_UID ", materialInfo.getAuthorizedpersonUid());
        values.put("Authorized_UIDRemarks", materialInfo.getAuthorizedpersonuidRemark());
        values.put("Authorized_UIDNC", materialInfo.getAuthorizedpersonuidNc());
        values.put("NCVT", materialInfo.getAffilated());
        values.put("NCVTRemarks", materialInfo.getAffilatedRemarks());
        values.put("NCVTNC", materialInfo.getAffilatedNC());
        values.put("referenceNumber", materialInfo.getReference());
        values.put("referenceNumberRemarks", materialInfo.getReferenceRemarks());
        values.put("referenceNumberNC", materialInfo.getReferenceNC());
        values.put("affilationNumber", materialInfo.getAffilation());
        values.put("affilationNumberRemarks", materialInfo.getAffilationRemarks());
        values.put("affilationNumberNC ", materialInfo.getAffilationNC());
        values.put("misCode", materialInfo.getCode());
        values.put("misCodeRemarks", materialInfo.getCodeRemarks());
        values.put("misCodeNC ", materialInfo.getCodeNC());

        if (mode.equalsIgnoreCase("draft")) {
            values.put("proc_tracker", 2);
        } else {
            values.put("proc_tracker", 3);
        }
        db.update("OrganisationGeneralInfo", values, " YearWiseCollegeId= " + materialInfo.getYearwisecollegeid(), null);

        return true;
    }

    public boolean saveTechnicalInfo(TechincalInfo techincalInfo, String modes) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("refId",techincalInfo.getRefId());
        values.put("designation ",techincalInfo.getDesignation());
        values.put("designationRemarks ",techincalInfo.getDesignationRemarks());
        values.put("designationNC ",techincalInfo.getDesignationNc());
        values.put("name ",techincalInfo.getName());
        values.put("nameRemarks ",techincalInfo.getNameRemarks());
        values.put("nameNC ",techincalInfo.getNameNc());
        values.put("fatherName ",techincalInfo.getFatherName());
        values.put("fatherNameRemarks ",techincalInfo.getFathernameRemarks());
        values.put("fatherNameNC ",techincalInfo.getFathernameNc());
        values.put("dob ",techincalInfo.getDob());
        values.put("dobRemarks ",techincalInfo.getDobRemarks());
        values.put("dobNC ",techincalInfo.getDobNc());
        values.put("joinDate ",techincalInfo.getJoinDate());
        values.put("joinDateRemarks ",techincalInfo.getJoindateRemarks());
        values.put("joinDateNC ",techincalInfo.getJoindateNc());
        values.put("qualification ",techincalInfo.getQualification());
        values.put("qualificationRemarks ",techincalInfo.getQualificationRemarks());
        values.put("qualificationNC ",techincalInfo.getQualificationNc());
        values.put("stream ",techincalInfo.getStream());
        values.put("streamRemarks ",techincalInfo.getStreamRemarks());
        values.put("streamNC ",techincalInfo.getStreamNc());
        values.put("passingYear ",techincalInfo.getPassingYear());
        values.put("passingYearRemarks ",techincalInfo.getPassingyearRemark());
        values.put("passingYearNC ",techincalInfo.getPassingyearNc());
        values.put("totalYoe ",techincalInfo.getTotalYoe());
        values.put("totalYoeRemarks ",techincalInfo.getTotalyoeRemarks());
        values.put("totalYoeNC ",techincalInfo.getTotalyoeNc());
        values.put("account ",techincalInfo.getAccount());
        values.put("accountRemarks ",techincalInfo.getAccountRemarks());
        values.put("accountNC ",techincalInfo.getAccountNc());
        values.put("bankName ",techincalInfo.getBankName());
        values.put("bankNameRemarks ",techincalInfo.getBanknameRemarks());
        values.put("bankNameNC ",techincalInfo.getBanknameNc());
        values.put("branchName ",techincalInfo.getBranchName());
        values.put("branchNameRemarks ",techincalInfo.getBranchnameRemarks());
        values.put("branchNameNC ",techincalInfo.getBranchnameNc());
        values.put("adharNo ",techincalInfo.getAdharNo());
        values.put("adharNoRemarks ",techincalInfo.getAdharnoRemarks());
        values.put("adharNoNC ",techincalInfo.getAdharnoNc());
        values.put("salary ",techincalInfo.getSalary());
        values.put("salaryRemarks ",techincalInfo.getSalaryRemarks());
        values.put("salaryNC ",techincalInfo.getSalaryNC());
        values.put("panCard ",techincalInfo.getPan());
        values.put("panCardRemarks ",techincalInfo.getPanRemarks());
        values.put("panCardNC ",techincalInfo.getPanNC());
        values.put("remarks ",techincalInfo.getRemarks());
        values.put("commonNc ",techincalInfo.getCommonNc());
        values.put("remarks_Remarks ",techincalInfo.getRemarksRemarks());
        values.put("remarks_Nc",techincalInfo.getRemarksNC());


        if (modes.equalsIgnoreCase("draft")) {
   values.put("proc_tracker", 2);
        } else {
            values.put("proc_tracker", 3);
        }
        db.update("TechnicalInfo", values, " YearWiseCollegeId= " + techincalInfo.getYearwisecollegeid()  + " and refId= " + techincalInfo.getRefId(), null);

        return true;
    }

    public boolean saveInstructorInfo(InstructorInfo instructorInfo, String modes) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("refId",instructorInfo.getRefId());
        values.put("tradeId",instructorInfo.getTradeId());
        values.put("tradename",instructorInfo.getTradeName());
        values.put("tradenameRemarks",instructorInfo.getTradeNameRemarks());
        values.put("instructor",instructorInfo.getInstructor());
        values.put("instructorRemarks",instructorInfo.getInstructorRemarks());
        values.put("instructorNC",instructorInfo.getInstructorNc());
        values.put("name",instructorInfo.getName());
        values.put("nameRemarks",instructorInfo.getNameRemarks());
        values.put("nameNC",instructorInfo.getNameNc());
        values.put("fatherName",instructorInfo.getFathername());
        values.put("fatherNameRemarks",instructorInfo.getFathernameRemarks());
        values.put("fatherNameNC",instructorInfo.getFathernameNc());
        values.put("dateofbirth",instructorInfo.getDateofbirth());
        values.put("dateofbirthRemarks",instructorInfo.getDateofbirthRemarks());
        values.put("dateofbirthNC",instructorInfo.getDateofbirthNc());
        values.put("joiningdate",instructorInfo.getJoiningdate());
        values.put("joiningdateRemarks",instructorInfo.getJoiningdateRemarks());
        values.put("joiningdate",instructorInfo.getJoiningdateNc());
        values.put("qualification",instructorInfo.getQualification());
        values.put("qualificationRemarks",instructorInfo.getQualificationRemarks());
        values.put("qualificationNC",instructorInfo.getQualificationNc());
        values.put("passingyear",instructorInfo.getPassingyear());
        values.put("passingyearRemarks",instructorInfo.getPassingyearRemarks());
        values.put("passingyearNC",instructorInfo.getPassingyearNc());
        values.put("stream",instructorInfo.getStream());
        values.put("streamRemarks",instructorInfo.getStreamRemarks());
        values.put("streamNC",instructorInfo.getStreamNc());
        values.put("totalexp",instructorInfo.getTotalexp());
        values.put("totalexpRemarks",instructorInfo.getTotalexpRemarks());
        values.put("totalexpNC",instructorInfo.getTotalexpNc());
        values.put("bankname",instructorInfo.getBankname());
        values.put("banknameRemarks",instructorInfo.getBanknameRemarks());
        values.put("banknameNC",instructorInfo.getBanknameNc());
        values.put("branchname",instructorInfo.getBranchname());
        values.put("branchnameRemarks",instructorInfo.getBanknameRemarks());
        values.put("branchnameNC",instructorInfo.getBranchnameNc());
        values.put("accountno",instructorInfo.getAccountno());
        values.put("accountnoRemarks",instructorInfo.getAccountnoRemarks());
        values.put("accountnoNC",instructorInfo.getAccountnoNc());
        values.put("aadharno",instructorInfo.getAadharno());
        values.put("aadharnoRemarks",instructorInfo.getAadharnoRemarks());
        values.put("aadharnoNC",instructorInfo.getAadharnoNc());
        values.put("salary",instructorInfo.getSalary());
        values.put("salaryRemarks",instructorInfo.getSalaryRemarks());
        values.put("salaryNC",instructorInfo.getSalaryNC());
        values.put("remarks",instructorInfo.getRemarks());
        values.put("remarksRemarks",instructorInfo.getRemarksRemarks());
        values.put("remarksNC",instructorInfo.getRemarksNC());
        values.put("commonNC",instructorInfo.getCommonNc());

        if (modes.equalsIgnoreCase("draft")) {
            values.put("proc_tracker", 2);
        } else {
            values.put("proc_tracker", 3);
        }
        db.update("InstructorITIInfo", values, " YearWiseCollegeId= " + instructorInfo.getYearwisecollegeid()+ " and refId= " + instructorInfo.getRefId(), null);

        return true;
    }

    public boolean savePremisesInfo(Staffing staffingInfo, String modes) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("refId", staffingInfo.getRefid());
        values.put("change", staffingInfo.getChangeOfPremises());
        values.put("changeRemarks", staffingInfo.getChangeOfPremisesRemarks());
        values.put("changeNC", staffingInfo.getChangeOfPremisesNc());
        values.put("toPostalAddress", staffingInfo.getToPostalAddress());
        values.put("toPostalAddressRemarks", staffingInfo.getToPostalAddressRemarks());
        values.put("toPostalAddressNC", staffingInfo.getToPostalAddressNc());
        values.put("tolandmark", staffingInfo.getTolandmark());
        values.put("tolandmarkRemarks", staffingInfo.getTolandmarkRemarks());
        values.put("tolandmarkNC", staffingInfo.getTolandmarkNc());
        values.put("toState", staffingInfo.getToState());
        values.put("toStateRemarks", staffingInfo.getToStateRemarks());
        values.put("toStateNC", staffingInfo.getToStateNc());
        values.put("toDistrict", staffingInfo.getToDistrict());
        values.put("toDistrictRemarks", staffingInfo.getToDistrictRemarks());
        values.put("toDistrictNC", staffingInfo.getToDistrictNc());
        values.put("toTehsil", staffingInfo.getToTehsil());
        values.put("toTehsilRemarks", staffingInfo.getToTehsilRemarks());
        values.put("toTehsilNC", staffingInfo.getToTehsilNc());
        values.put("toBlock", staffingInfo.getToBlock());
        values.put("toBlockRemarks", staffingInfo.getToBlockRemarks());
        values.put("toBlockNC", staffingInfo.getToBlockNc());
        values.put("toPincode", staffingInfo.getToPincode());
        values.put("toPincodeRemarks", staffingInfo.getToPincodeRemarks());
        values.put("toPincodeNC", staffingInfo.getToPincodeNc());
        values.put("totelno", staffingInfo.getTotelno());
        values.put("totelnoRemarks", staffingInfo.getTotelnoRemarks());
        values.put("totelnoNC", staffingInfo.getTotelnoNc());
        values.put("toFaxno", staffingInfo.getToFaxno());
        values.put("toFaxnoRemarks", staffingInfo.getToFaxnoRemarks());
        values.put("toFaxnoNC", staffingInfo.getToFaxnoNc());
        values.put("remarks", staffingInfo.getRemarks());

        if (modes.equalsIgnoreCase("draft")) {
            values.put("proc_tracker", 2);
        } else {
            values.put("proc_tracker", 3);
        }
        db.update("PremisesShiftingInfo", values, " YearWiseCollegeId= " + staffingInfo.getYearWiseCollegeid(), null);

        return true;
    }

    public boolean saveClassroomInfo(Classroom classroomInfo, String modes) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("refId",classroomInfo.getRefId());
        values.put("classroomName",classroomInfo.getClassroomName());
        values.put("classroomNameRemarks",classroomInfo.getClassroomAreaRemarks());
        values.put("classroomNameNC",classroomInfo.getClassroomNameNC());
        values.put("classroomArea",classroomInfo.getClassroomArea());
        values.put("classroomAreaRemarks",classroomInfo.getClassroomAreaRemarks());
        values.put("classroomAreaNC",classroomInfo.getClassroomAreaNC());
        values.put("available",classroomInfo.getAvailable());
        values.put("width",classroomInfo.getWidth());
        values.put("widthRemarks",classroomInfo.getWidthRemarks());
        values.put("widthNC",classroomInfo.getWidthNC());
        values.put("flag",classroomInfo.getFlag());
        values.put("tradeId",classroomInfo.getTradeId());
        values.put("ceiling",classroomInfo.getRoof());
        values.put("ceilingRemarks",classroomInfo.getRoofRemarks());
        values.put("ceilingNC",classroomInfo.getRoofNC());
        values.put("height",classroomInfo.getHeight());
        values.put("heightRemarks",classroomInfo.getHeightRemarks());
        values.put("heightNC",classroomInfo.getHeightNC());
        values.put("wall",classroomInfo.getTin());
        values.put("wallRemarks",classroomInfo.getTinRemarks());
        values.put("wallNC",classroomInfo.getTinNC());

        if (modes.equalsIgnoreCase("draft")) {
            values.put("proc_tracker", 2);
        } else {
            values.put("proc_tracker", 3);
        }
        db.update("ClassroomInfo", values, " YearWiseCollegeId= " + classroomInfo.getYearwisecollegeid() + " and refId= " + classroomInfo.getRefId(), null);

        return true;
    }

    public boolean saveITLabInfo(ITLab labInfoList, String modes) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("quantity", labInfoList.getQuantity());
        values.put("refId",labInfoList.getRefId());
        values.put("available",labInfoList.getAvailable());
        values.put("Area",labInfoList.getActualArea());
        values.put("AreaRemarks",labInfoList.getActualArea());
        values.put("AreaNC",labInfoList.getActualArea());
        values.put("Lab",labInfoList.getLab());
        values.put("LabRemarks",labInfoList.getLabRemarks());
            if (modes.equalsIgnoreCase("draft")) {
                values.put("proc_tracker", 2);
            } else {
                values.put("proc_tracker", 3);
            }
            db.update("LabInfo", values, " YearWiseCollegeId= " + labInfoList.getYearWiseCollegeid() + " and refId= " + labInfoList.getRefId() , null);
        return true;
    }

//    public ITLab getlabInfoYearWiseCollegeId(String YearWiseCollegeId) {
//
//        SQLiteDatabase db = null;
//        String selectQueryQues ="Select yearWiseCollegeid,nameofequipment,category,quantity,refId,available" +
//                ",noOfComputer, actualTotalComputer, areaOfItLab, actualArea, noOfComputerRemarks, actualTotalComputerRemarks," +
//                " areaOfItLabRemarks, actualAreaRemarks, noOfComputerNc, actualTotalComputerNc, areaOfItLabNc, actualAreaNc," +
//                "proc_tracker from LabInfo where YearWiseCollegeId = " + YearWiseCollegeId;
//        ITLab labInfoModel = null;
//        try {
//
//            db = this.getReadableDatabase();
//            Cursor cursor = db.rawQuery(selectQueryQues, null);
//
//            if (cursor.moveToFirst()) {
//
//                do {
//                    labInfoModel = new ITLab();
//                    labInfoModel.setYearWiseCollegeid(cursor.getString(0));
//                    labInfoModel.setNameofequipment(cursor.getString(1));
//                    labInfoModel.setCategory(cursor.getString(2));
//                    labInfoModel.setQuantity(cursor.getString(3));
//                    labInfoModel.setRefId(cursor.getInt(4));
//                    labInfoModel.setAvailable(cursor.getInt(5));
//                    labInfoModel.setNoOfComputer(cursor.getString(6));
//                    labInfoModel.setActualTotalComputer(cursor.getString(7));
//                    labInfoModel.setAreaOfItLab(cursor.getString(8));
//                    labInfoModel.setActualArea(cursor.getString(9));
//                    labInfoModel.setNoOfComputerRemarks(cursor.getString(10));
//                    labInfoModel.setActualTotalComputerRemarks(cursor.getString(11));
//                    labInfoModel.setAreaOfItLabRemarks(cursor.getString(12));
//                    labInfoModel.setActualAreaRemarks(cursor.getString(13));
//                    labInfoModel.setNoOfComputerNc(cursor.getString(14));
//                    labInfoModel.setActualTotalComputerNc(cursor.getString(15));
//                    labInfoModel.setAreaOfItLabNc(cursor.getString(16));
//                    labInfoModel.setActualAreaNc(cursor.getString(17));
//                    labInfoModel.setProc_tracker(cursor.getInt(18));
//
//
//                } while (cursor.moveToNext());
//            }
//        } catch (Exception e) {
//            Log.e("error getting jobroles", e.toString());
//            Log.e(TAG, e.getMessage());
//            return null;
//
//        }finally {
//            db.close();
//        }
//
//        return labInfoModel;
//    }

    public boolean saveLandInfo(LandandBuilding landandBuildingInfo, String modes) {


        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("refId",landandBuildingInfo.getRefId());
        values.put("buildingType",landandBuildingInfo.getBuildingType() );
        values.put("buildingTypeRemarks",landandBuildingInfo.getBuildingTypeRemarks() );
        values.put("buildingTypeNC",landandBuildingInfo.getBuildingTypeNc() );
        values.put("dateofOccupancy",landandBuildingInfo.getDateofOccupancy() );
        values.put("dateofOccupancyRemarks",landandBuildingInfo.getDateofOccupancyRemarks() );
        values.put("dateofOccupancyNC",landandBuildingInfo.getDateofOccupancyNc() );
        values.put("openArea",landandBuildingInfo.getTotalOpenArea() );
        values.put("openAreaRemarks",landandBuildingInfo.getTotalOpenAreaRemarks() );
        values.put("openAreaNC",landandBuildingInfo.getTotalOpenAreaNc() );
        values.put("buildArea",landandBuildingInfo.getTotalBuildArea() );
        values.put("buildAreaRemarks",landandBuildingInfo.getTotalBuildAreaRemarks() );
        values.put("buildAreaNC",landandBuildingInfo.getTotalBuildAreaNc() );
        values.put("land",landandBuildingInfo.getTotalLand() );
        values.put("landRemarks",landandBuildingInfo.getTotalLandRemarks() );
        values.put("landNC",landandBuildingInfo.getTotalLandNc() );
        values.put("liftInstitute",landandBuildingInfo.getLiftinInstitute() );
        values.put("liftInstituteRemarks",landandBuildingInfo.getLiftinInstituteRemarks() );
        values.put("liftInstituteNC",landandBuildingInfo.getLiftinInstituteNc() );
        values.put("noFloor",landandBuildingInfo.getNoofFloors() );
        values.put("noFloorRemarks",landandBuildingInfo.getNoofFloorsRemarks() );
        values.put("noFloorNC",landandBuildingInfo.getNoofFloorsNc() );
        values.put("orgInstalledLift",landandBuildingInfo.getOrgInstalledLift() );
        values.put("orgInstalledLiftRemarks",landandBuildingInfo.getOrgInstalledLiftRemarks() );
        values.put("orgInstalledLiftNC",landandBuildingInfo.getOrgInstalledLiftNc() );
        values.put("capacityofLift",landandBuildingInfo.getCapacityofLift() );
        values.put("capacityofLiftRemarks",landandBuildingInfo.getCapacityofLiftRemarks() );
        values.put("capacityofLiftNC",landandBuildingInfo.getCapacityofLiftNc() );
        values.put("safetyLift",landandBuildingInfo.getSafetyCertificateofLift() );
        values.put("safetyLiftRemarks",landandBuildingInfo.getSafetyCertificateofLiftRemarks() );
        values.put("safetyLiftNC",landandBuildingInfo.getSafetyCertificateofLiftNc() );
        values.put("workshopRoof",landandBuildingInfo.getWorkshopRoof() );
        values.put("workshopRoofRemarks",landandBuildingInfo.getWorkshopRoofRemarks() );
        values.put("workshopRoofNC",landandBuildingInfo.getWorkshopRoofNc() );
        values.put("buildingPlan",landandBuildingInfo.getBuildingPlanofInstitute() );
        values.put("buildingPlanRemarks",landandBuildingInfo.getBuildingPlanofInstituteRemarks() );
        values.put("buildingPlanNC",landandBuildingInfo.getBuildingPlanofInstituteNc() );
        values.put("wallOfIti",landandBuildingInfo.getWallOfIti() );
        values.put("wallOfItiRemarks",landandBuildingInfo.getWallOfItiRemarks() );
        values.put("wallOfItiNC",landandBuildingInfo.getWallOfItiNc() );
        values.put("floorisCemented",landandBuildingInfo.getFloorisCemented() );
        values.put("floorisCementedRemarks",landandBuildingInfo.getFloorisCementedRemarks() );
        values.put("floorisCementedNC",landandBuildingInfo.getFloorisCementedNc() );
        values.put("sameCampus",landandBuildingInfo.getItiSituatedintheSameCampus() );
        values.put("sameCampusRemarks",landandBuildingInfo.getItiSituatedintheSameCampusRemarks() );
        values.put("sameCampusNC",landandBuildingInfo.getItiSituatedintheSameCampusNc() );
        values.put("seperateWashroom",landandBuildingInfo.getAvaibilityofSeparateMaleFemaleWashroom() );
        values.put("seperateWashroomRemarks",landandBuildingInfo.getAvaibilityofSeparateMaleFemaleWashroomRemarks() );
        values.put("seperateWashroomNC",landandBuildingInfo.getAvaibilityofSeparateMaleFemaleWashroomNc() );
        values.put("washroomFunctional",landandBuildingInfo.getAretheWashroomFunctional() );
        values.put("washroomFunctionalRemarks",landandBuildingInfo.getAretheWashroomFunctionalRemarks() );
        values.put("washroomFunctionalNC",landandBuildingInfo.getAretheWashroomFunctionalNc() );
        values.put("fire",landandBuildingInfo.getAvaibiltyofFireextinguisher() );
        values.put("fireRemarks",landandBuildingInfo.getAvaibiltyofFireextinguisherRemarks() );
        values.put("fireNC",landandBuildingInfo.getAvaibiltyofFireextinguisherNc() );
        values.put("durationlease",landandBuildingInfo.getDurationlease() );
        values.put("durationleaseRemarks",landandBuildingInfo.getDurationleaseRemarks() );
        values.put("durationleaseNC",landandBuildingInfo.getDurationleaseNc() );
        values.put("expiryofAgreement",landandBuildingInfo.getExpiryofAgreement() );
        values.put("expiryofAgreementRemarks",landandBuildingInfo.getExpiryofagreementRemarks() );
        values.put("expiryofAgreementNC",landandBuildingInfo.getExpiryofagreementNC() );
        values.put("safeWater",landandBuildingInfo.getAvaibilityofsafeDrinkingwater() );
        values.put("safeWaterRemarks",landandBuildingInfo.getAvaibilityofsafedrinkingwaterRemarks() );
        values.put("safeWaterNC",landandBuildingInfo.getAvaibilityofsafedrinkingwaterNc() );
        values.put("houseKeeping",landandBuildingInfo.getAvaibilityHousekeepingstaff() );
        values.put("houseKeepingRemarks",landandBuildingInfo.getAvaibilityhousekeepingstaffRemarks() );
        values.put("houseKeepingNC",landandBuildingInfo.getAvaibilityhousekeepingstaffNc() );
        values.put("placementCell",landandBuildingInfo.getAvaibilityofPlacementcell() );
        values.put("placementCellRemarks",landandBuildingInfo.getAvaibilityofplacementcellRemarks() );
        values.put("placementCellNC",landandBuildingInfo.getAvaibilityofplacementcellNc() );
        values.put("masterPlan",landandBuildingInfo.getIsMasterPlan() );
        values.put("masterPlanRemarks",landandBuildingInfo.getIsMasterPlanRemarks() );
        values.put("masterPlanNC",landandBuildingInfo.getIsMasterPlanNC() );
        values.put("comptent",landandBuildingInfo.getComptent() );
        values.put("comptentRemarks",landandBuildingInfo.getComptentRemarks() );
        values.put("comptentNC",landandBuildingInfo.getComptentNC() );
        values.put("sitmap",landandBuildingInfo.getSitmap() );
        values.put("sitmapRemarks",landandBuildingInfo.getSitmapRemarks() );
        values.put("sitmapNC",landandBuildingInfo.getSitmapNc() );
        values.put("dimension",landandBuildingInfo.getDimension() );
        values.put("dimensionRemarks",landandBuildingInfo.getDimensionRemarks() );
        values.put("dimensionNC",landandBuildingInfo.getDimensionNC() );
        values.put("map",landandBuildingInfo.getMap() );
        values.put("mapRemarks",landandBuildingInfo.getMapRemarks() );
        values.put("mapNC",landandBuildingInfo.getMapNC() );
        values.put("approachRoad",landandBuildingInfo.getApproach() );
        values.put("approachRoadRemarks",landandBuildingInfo.getApproachRemarks() );
        values.put("approachRoadNC",landandBuildingInfo.getApproachNC() );
        values.put("entranceRoad",landandBuildingInfo.getEntrance() );
        values.put("entranceRoadRemarks",landandBuildingInfo.getEntranceRemarks() );
        values.put("entranceRoadNC",landandBuildingInfo.getEntranceNC() );
        values.put("soundProof",landandBuildingInfo.getSoundProofPartition() );
        values.put("soundProofRemarks",landandBuildingInfo.getSoundProofPartitionRemarks() );
        values.put("soundProofNC",landandBuildingInfo.getISoundProofPartitionNC() );
        values.put("shared",landandBuildingInfo.getIsPremisesShared() );
        values.put("sharedRemarks",landandBuildingInfo.getIsPremisesSharedRemarks() );
        values.put("sharedNC",landandBuildingInfo.getIsPremisesSharedNC() );
        values.put("seperateEntrance",landandBuildingInfo.getSeperateEntrance() );
        values.put("seperateEntranceRemarks",landandBuildingInfo.getSeperateEntranceRemarks() );
        values.put("seperateEntranceNC",landandBuildingInfo.getSeperateEntranceNC() );
        values.put("switchBoard",landandBuildingInfo.getSwitchBoard() );
        values.put("switchBoardRemarks",landandBuildingInfo.getSwitchBoardRemarks() );
        values.put("switchBoardNC",landandBuildingInfo.getSwitchBoardNC() );
        values.put("ventilated",landandBuildingInfo.getVentilation() );
        values.put("ventilatedRemarks",landandBuildingInfo.getVentilationRemarks() );
        values.put("ventilatedNC",landandBuildingInfo.getVentilationNC() );
        values.put("plotSize",landandBuildingInfo.getConstructed() );
        values.put("plotSizeRemarks",landandBuildingInfo.getConstructedRemarks() );
        values.put("plotSizeNC",landandBuildingInfo.getConstructedNC() );
        values.put("BCC",landandBuildingInfo.getPrescribed() );
        values.put("BCCRemarks",landandBuildingInfo.getPrescribedRemarks() );
        values.put("BCCNC",landandBuildingInfo.getPrescribedNC() );
        values.put("staircase",landandBuildingInfo.getStaircase() );
        values.put("staircaseRemarks",landandBuildingInfo.getStaircaseRemarks() );
        values.put("staircaseNC",landandBuildingInfo.getStaircaseNC() );



        if (modes.equalsIgnoreCase("draft")) {
            values.put("proc_tracker", 2);
        } else {
            values.put("proc_tracker", 3);
        }
        db.update("LandInfo", values, " YearWiseCollegeId= " + landandBuildingInfo.getYearwisecollegeid(), null);

        return true;
    }

//    public List<ToolsAbove> getToolsAboveInfoList(String instituteId) {
//    }


    public List getPowerSupplyITIList(String YearWiseCollegeId) {
        SQLiteDatabase db = null;
        String selectQueryQues ="Select YearWiseCollegeId,isConnectionNameIti,connectionIssued,dateConnection,PowerSupplyNeededAsPer,proc_tracker " +                "from PowerSupplyITI where YearWiseCollegeId = " + YearWiseCollegeId;
        List powerSupplyITIModelList = new ArrayList();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                do {
                    PowerSupplyITIModel powerSupplyITIModel = new PowerSupplyITIModel();
                    powerSupplyITIModel.setYearWiseCollegeid(cursor.getString(0));
                    powerSupplyITIModel.setIsConnectionNameIti(cursor.getString(1));
                    powerSupplyITIModel.setConnectionIssued(cursor.getString(2));
                    powerSupplyITIModel.setDateConnection(cursor.getString(3));
                    powerSupplyITIModel.setPowerSupplyNeededAsPer(cursor.getString(4));
                    powerSupplyITIModel.setProc_tracker(cursor.getInt(5));
                    powerSupplyITIModelList.add(powerSupplyITIModel);
                } while (cursor.moveToNext());
            }        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;
        }finally {
            db.close();
        }        return powerSupplyITIModelList;
    }



    public boolean addPowerSupplyITIData(JSONObject result, String yearWiseCollegeId) {
        try{           SQLiteDatabase database = getWritableDatabase();
            int status = result.optInt("status");
            if(status == 0){
                JSONObject payload = result.optJSONObject("payload");
                JSONObject genralInfoObj = payload.optJSONObject("powerSupply");
                ContentValues values = new ContentValues();
                values.put("YearWiseCollegeId",yearWiseCollegeId);
                values.put("isConnectionNameIti",genralInfoObj.optString("isConnectionNameIti"));
                values.put("connectionIssued",genralInfoObj.optString("connectionIssued"));
                values.put("dateConnection",genralInfoObj.optString("dateConnection"));
                values.put("PowerSupplyNeededAsPer",genralInfoObj.optString("PowerSupplyNeededAsPer"));
                values.put("proc_tracker", 1);
                database.insert("PowerSupplyITI", null, values);
            }else {
                Toast.makeText(application_context, "No Data Found..", Toast.LENGTH_LONG).show();
            }       }catch (Exception e){
            e.printStackTrace();       }
        return true;
    }

    public PowerSupplyITIModel getPowerSupplyITIbyYearwiseCollegeId(String YearWiseCollegeId) {
        SQLiteDatabase db = null;
        String selectQueryQues ="Select YearWiseCollegeId,isConnectionNameIti,connectionIssued,dateConnection,PowerSupplyNeededAsPer," +                "proc_tracker from PowerSupplyITI where YearWiseCollegeId = " + YearWiseCollegeId;
        PowerSupplyITIModel powerSupplyITIModel = null;
        try {            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                do {
                    powerSupplyITIModel = new PowerSupplyITIModel();
                    powerSupplyITIModel.setYearWiseCollegeid(cursor.getString(0));
                    powerSupplyITIModel.setIsConnectionNameIti(cursor.getString(1));
                    powerSupplyITIModel.setConnectionIssued(cursor.getString(2));
                    powerSupplyITIModel.setDateConnection(cursor.getString(3));
                    powerSupplyITIModel.setPowerSupplyNeededAsPer(cursor.getString(4));
                    powerSupplyITIModel.setProc_tracker(cursor.getInt(5));
                } while (cursor.moveToNext());
            }        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;        }finally {
            db.close();        }
        return powerSupplyITIModel;
    }

    public boolean savePowerSupply(PowerSupplyITIModel powerSupplyITIModel, String mode) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put("YearWiseCollegeId",powerSupplyITIModel.getYearWiseCollegeid());
         values.put("isConnectionNameIti",powerSupplyITIModel.getIsConnectionNameIti());
        values.put("connectionIssued",powerSupplyITIModel.getConnectionIssued());
        values.put("dateConnection",powerSupplyITIModel.getDateConnection());
        values.put("PowerSupplyNeededAsPer",powerSupplyITIModel.getPowerSupplyNeededAsPer());
        if (mode.equalsIgnoreCase("draft")) {
            values.put("proc_tracker", 2);
        } else {
            values.put("proc_tracker", 3);
        }
        db.update("PowerSupplyITI", values, " YearWiseCollegeId= " + powerSupplyITIModel.getYearWiseCollegeid(), null);
        return true;    }

    //-------------------------------------------------------------------------------------------------------------------
    public List getWorkshopAreaList(String YearWiseCollegeId) {
        SQLiteDatabase db = null;
        String selectQueryQues ="Select YearWiseCollegeId,tradeId,shiftsUnit,shiftsunitRemarks,shiftsNc,ncvtNorms,ncvtnormsRemarks," +
                "ncvtNc,actualArea,actualareaRemarks,actualareaNc,shortageArea,shortageareaRemarks,shortageAreaNc,refId," +
                "istheWorkshopRectangular,istheWorkshopRectangularRemarks,istheWorkshopRectangularNc,widthOftheWorkshop," +
                "widthOftheWorkshopRemarks,widthOftheWorkshopNc,aretheWorkshopWallsofTin,aretheWorkshopWallsofTinRemarks," +
                "aretheWorkshopWallsofTinNc,istheheavyMachineryLocated,istheheavyMachineryLocatedRemarks," +
                "istheheavyMachineryLocatedNc,itihaveCombinedWorkshop,itihaveCombinedWorkshopRemarks," +
                "itihaveCombinedWorkshopNc,istheDemarcated,istheDemarcatedRemarks,istheDemarcatedNc," +
                "emergencyContactNumberDisplay,emergencyContactNumberDisplayRemarks,emergencyContactNumberDisplayNc," +
                "WorkShopRoof,WorkShopRoof_Remarks,WorkShopRoof_Nc,ceilingHeightlessthan12Feet," +
                "ceilingHeightlessthan12Feet_Remarks,ceilingHeightlessthan12Feet_Nc,ceilingHeightlessthan10Feet," +
                "ceilingHeightlessthan10Feet_Remarks,proc_tracker,refId,tradeName,isWiresandBoardsCovered," +
                "isFireExtinguisherAvailable,isEmergencyExit,isWiresandBoardsCoveredRemarks," +
                "isFireExtinguisherAvailableRemarks,isEmergencyExitRemarks,isWiresandBoardsCoveredNc," +
                "isFireExtinguisherAvailableNc,workshopName,machinaryTools, machinaryToolsRemarks, machinaryToolsNC, machineComplying " +
                ", machineComplyingRemarks, machineComplyingNC, rubberMat , rubberMatRemarks, rubberMatNC, dgRequired ," +
                " dgRequiredRemarks, dgRequiredNC, dgInstalled , dgInstalledRemarks, dgInstalledNC, latheRequired , " +
                "latheRequiredRemarks, latheRequiredNC, latheInstalled , latheInstalledRemarks, " +
                "latheInstalledNC, majorMachine , majorMachineRemarks, majorMachineNC ,flag,proc_tracker" +
                " from WorkshopArea where YearWiseCollegeId = " + YearWiseCollegeId;
        List WorkshopAreaModelList = new ArrayList();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                do {
                    WorkshopAreaModel workshopAreaObj = new WorkshopAreaModel();
                    workshopAreaObj.setYearWiseCollegeid(cursor.getString(0));
                    workshopAreaObj.setTradeId(cursor.getString(1));
                    workshopAreaObj.setShiftsUnit(cursor.getString(2));
                    workshopAreaObj.setShiftsunitRemarks(cursor.getString(3));
                    workshopAreaObj.setShiftsNc(cursor.getInt(4));
                    //workshopAreaObj.setNcvtNorms(cursor.getString(5));
                    //workshopAreaObj.setNcvtnormsRemarks(cursor.getString(6));
                    //workshopAreaObj.setNcvtNc(cursor.getString(7));
                    workshopAreaObj.setActualArea(cursor.getString(8));
                    workshopAreaObj.setActualareaRemarks(cursor.getString(9));
                    //workshopAreaObj.setActualareaNc(cursor.getString(10));
                    //workshopAreaObj.setShortageArea(cursor.getString(11));
                    //workshopAreaObj.setShortageareaRemarks(cursor.getString(12));
                    //workshopAreaObj.setShortageAreaNc(cursor.getString(13));
                    workshopAreaObj.setRefId(cursor.getInt(14));
                    workshopAreaObj.setIstheWorkshopRectangular(cursor.getString(15));
                    workshopAreaObj.setIstheWorkshopRectangularRemarks(cursor.getString(16));
                    workshopAreaObj.setIstheWorkshopRectangularNc(cursor.getInt(17));
                    workshopAreaObj.setWidthOftheWorkshop(cursor.getString(18));
                    workshopAreaObj.setWidthOftheWorkshopRemarks(cursor.getString(19));
                    workshopAreaObj.setWidthOftheWorkshopNc(cursor.getInt(20));
                    workshopAreaObj.setAretheWorkshopWallsofTin(cursor.getString(21));
                    workshopAreaObj.setAretheWorkshopWallsofTinRemarks(cursor.getString(22));
                    workshopAreaObj.setAretheWorkshopWallsofTinNc(cursor.getInt(23));
                    workshopAreaObj.setIstheheavyMachineryLocated(cursor.getString(24));
                    workshopAreaObj.setIstheheavyMachineryLocated_Remarks(cursor.getString(25));
                    //workshopAreaObj.setIstheheavyMachineryLocatedNc(cursor.getString(26));
                    workshopAreaObj.setItihaveCombinedWorkshop(cursor.getString(27));
                    workshopAreaObj.setItihaveCombinedWorkshopRemarks(cursor.getString(28));
                    //workshopAreaObj.setItihaveCombinedWorkshopNc(cursor.getString(29));
                    workshopAreaObj.setIstheDemarcated(cursor.getString(30));
                    workshopAreaObj.setIstheDemarcatedRemarks(cursor.getString(31));
                    //workshopAreaObj.setIstheDemarcatedNc(cursor.getString(32));
                    workshopAreaObj.setEmergencyContactNumberDisplay(cursor.getString(33));
                    workshopAreaObj.setEmergencyContactNumberDisplayRemarks(cursor.getString(34));
                    //workshopAreaObj.setEmergencyContactNumberDisplayNc(cursor.getString(35));
                    workshopAreaObj.setWorkShopRoof(cursor.getString(36));
                    workshopAreaObj.setWorkShopRoof_Remarks(cursor.getString(37));
                    //workshopAreaObj.setWorkShopRoof_Nc(cursor.getString(38));
                    workshopAreaObj.setCeilingHeightlessthan12Feet(cursor.getString(39));
                    workshopAreaObj.setCeilingHeightlessthan12Feet_Remarks(cursor.getString(40));
                    //workshopAreaObj.setCeilingHeightlessthan12Feet_Nc(cursor.getString(41));
                    workshopAreaObj.setCeilingHeightlessthan10Feet(cursor.getString(42));
                    workshopAreaObj.setCeilingHeightlessthan10FeetRemarks(cursor.getString(43));
                    workshopAreaObj.setProc_tracker(cursor.getInt(44));
                    workshopAreaObj.setId(cursor.getInt(45));
                    workshopAreaObj.setTradeName(cursor.getString(46));

                    workshopAreaObj.setIsWiresandBoardsCovered(cursor.getString(47));
                    workshopAreaObj.setIsFireExtinguisherAvailable(cursor.getString(48));
                    workshopAreaObj.setIsEmergencyExit(cursor.getString(49));
                    workshopAreaObj.setIsWiresandBoardsCoveredRemarks(cursor.getString(50));
                    workshopAreaObj.setIsFireExtinguisherAvailableRemarks(cursor.getString(51));
                    workshopAreaObj.setIsEmergencyExitRemarks(cursor.getString(52));
                    workshopAreaObj.setIsWiresandBoardsCoveredNc(cursor.getInt(53));
                    workshopAreaObj.setIsFireExtinguisherAvailableNc(cursor.getInt(54));
                    workshopAreaObj.setWorkshopName(cursor.getString(55));
                    workshopAreaObj.setMachinaryTools(cursor.getString(56));
                    workshopAreaObj.setMachinaryToolsRemarks(cursor.getString(57));
                    workshopAreaObj.setMachinaryToolsNC(cursor.getInt(58));
                    workshopAreaObj.setMachinesComplying(cursor.getString(59));
                    workshopAreaObj.setMachinesComplyingRemarks(cursor.getString(60));
                    workshopAreaObj.setMachinesComplyingNC(cursor.getInt(61));
                    workshopAreaObj.setRubberMats(cursor.getString(62));
                    workshopAreaObj.setRubberMatsRemarks(cursor.getString(63));
                    workshopAreaObj.setRubberMatsNC(cursor.getInt(64));
                    workshopAreaObj.setDgsetRequired(cursor.getString(65));
                    workshopAreaObj.setDgsetRequiredRemarks(cursor.getString(66));
                    workshopAreaObj.setDgsetRequiredNC(cursor.getInt(67));
                    workshopAreaObj.setDgsetInstalled(cursor.getString(68));
                    workshopAreaObj.setDgsetInstalledRemarks(cursor.getString(69));
                    workshopAreaObj.setDgsetInstalledNC(cursor.getInt(70));
                    workshopAreaObj.setLatheRequired(cursor.getString(71));
                    workshopAreaObj.setLatheRequiredRemarks(cursor.getString(72));
                    workshopAreaObj.setLatheRequiredNC(cursor.getInt(73));
                    workshopAreaObj.setLatheInstalled(cursor.getString(74));
                    workshopAreaObj.setLatheInstalledRemarks(cursor.getString(75));
                    workshopAreaObj.setLatheInstalledNC(cursor.getInt(76));
                    workshopAreaObj.setMajorMachine(cursor.getString(77));
                    workshopAreaObj.setMajorMachineRemarks(cursor.getString(78));
                    workshopAreaObj.setMajorMachineNC(cursor.getInt(79));
                    workshopAreaObj.setFlag(cursor.getInt(80));
                    workshopAreaObj.setProc_tracker(cursor.getInt(81));

                    WorkshopAreaModelList.add(workshopAreaObj);
                } while (cursor.moveToNext());
            }        } catch (Exception e) {
            Log.e("workshoparea", e.toString());
            Log.e(TAG, e.getMessage());
            return null;
        }finally {
            db.close();
        }        return WorkshopAreaModelList;
    }



    public boolean addWorkshopAreaData(JSONObject result, String yearWiseCollegeId) {
        try{           SQLiteDatabase database = getWritableDatabase();
            int status = result.optInt("status");
            if(status == 0){
                JSONObject payload = result.optJSONObject("payload");
                JSONArray jsonArr = payload.optJSONArray("workshop");
                for (int i = 0;i<jsonArr.length();i++) {
                    JSONObject jsonObj = jsonArr.optJSONObject(i);
                    ContentValues values = new ContentValues();
                    values.put("YearWiseCollegeId", yearWiseCollegeId);
                    //values.put("available",jsonObj.optString("available"));
                    values.put("tradeId", jsonObj.optString("tradeId"));
                    values.put("tradeName", jsonObj.optString("tradeName"));
                    values.put("shiftsUnit", jsonObj.optString("shiftsUnit"));
                    values.put("shiftsunitRemarks", jsonObj.optString("shiftsunitRemarks"));
                    values.put("shiftsNc", jsonObj.optString("shiftsNc"));
                    values.put("ncvtNorms", jsonObj.optString("ncvtNorms"));
                    values.put("ncvtnormsRemarks", jsonObj.optString("ncvtnormsRemarks"));
                    values.put("ncvtNc", jsonObj.optString("ncvtNc"));
                    values.put("actualArea", jsonObj.optString("actualArea"));
                    values.put("actualareaRemarks", jsonObj.optString("actualareaRemarks"));
                    values.put("actualareaNc", jsonObj.optString("actualareaNc"));
                    values.put("shortageArea", jsonObj.optString("shortageArea"));
                    values.put("shortageareaRemarks", jsonObj.optString("shortageareaRemarks"));
                    values.put("shortageAreaNc", jsonObj.optString("shortageAreaNc"));
                    values.put("refId", jsonObj.optString("refId"));
                    values.put("istheWorkshopRectangular", jsonObj.optString("istheWorkshopRectangular"));
                    values.put("istheWorkshopRectangularRemarks", jsonObj.optString("istheWorkshopRectangularRemarks"));
                    values.put("istheWorkshopRectangularNc", jsonObj.optString("istheWorkshopRectangularNc"));
                    values.put("widthOftheWorkshop", jsonObj.optString("widthOftheWorkshop"));
                    values.put("widthOftheWorkshopRemarks", jsonObj.optString("widthOftheWorkshopRemarks"));
                    values.put("widthOftheWorkshopNc", jsonObj.optString("widthOftheWorkshopNc"));
                    values.put("aretheWorkshopWallsofTin", jsonObj.optString("aretheWorkshopWallsofTin"));
                    values.put("aretheWorkshopWallsofTinRemarks", jsonObj.optString("aretheWorkshopWallsofTinRemarks"));
                    values.put("aretheWorkshopWallsofTinNc", jsonObj.optString("aretheWorkshopWallsofTinNc"));
                    values.put("istheheavyMachineryLocated", jsonObj.optString("istheheavyMachineryLocated"));
                    values.put("istheheavyMachineryLocatedRemarks", jsonObj.optString("istheheavyMachineryLocatedRemarks"));
                    values.put("istheheavyMachineryLocatedNc", jsonObj.optString("istheheavyMachineryLocatedNc"));
                    values.put("itihaveCombinedWorkshop", jsonObj.optString("itihaveCombinedWorkshop"));
                    values.put("itihaveCombinedWorkshopRemarks", jsonObj.optString("itihaveCombinedWorkshopRemarks"));
                    values.put("itihaveCombinedWorkshopNc", jsonObj.optString("itihaveCombinedWorkshopNc"));
                    values.put("istheDemarcated", jsonObj.optString("istheDemarcated"));
                    values.put("istheDemarcatedRemarks", jsonObj.optString("istheDemarcatedRemarks"));
                    values.put("istheDemarcatedNc", jsonObj.optString("istheDemarcatedNc"));
                    values.put("emergencyContactNumberDisplay", jsonObj.optString("emergencyContactNumberDisplay"));
                    values.put("emergencyContactNumberDisplayRemarks", jsonObj.optString("emergencyContactNumberDisplayRemarks"));
                    values.put("emergencyContactNumberDisplayNc", jsonObj.optString("emergencyContactNumberDisplayNc"));
                    values.put("WorkShopRoof", jsonObj.optString("WorkShopRoof"));
                    values.put("flag", jsonObj.optString("flag"));
                    values.put("WorkShopRoof_Remarks", jsonObj.optString("WorkShopRoof_Remarks"));
                    values.put("WorkShopRoof_Nc", jsonObj.optString("WorkShopRoof_Nc"));
                    values.put("ceilingHeightlessthan12Feet", jsonObj.optString("ceilingHeightlessthan12Feet"));
                    values.put("ceilingHeightlessthan12Feet_Remarks", jsonObj.optString("ceilingHeightlessthan12Feet_Remarks"));
                    values.put("ceilingHeightlessthan12Feet_Nc", jsonObj.optString("ceilingHeightlessthan12Feet_Nc"));
                    values.put("ceilingHeightlessthan10Feet", jsonObj.optString("ceilingHeightlessthan10Feet"));
                    values.put("ceilingHeightlessthan10Feet_Remarks", jsonObj.optString("ceilingHeightlessthan10Feet_Remarks"));
                    values.put("proc_tracker", 1);

                    database.insert("WorkshopArea", null, values);
                }
            }else {
                Toast.makeText(application_context, "No Data Found..", Toast.LENGTH_LONG).show();
            }       }catch (Exception e){
            e.printStackTrace();      return false; }
        return true;
    }

    public WorkshopAreaModel getWorkshopAreabyId(String YearWiseCollegeId, String Id) {
        SQLiteDatabase db = null;
        String selectQueryQues ="Select YearWiseCollegeId,tradeId,shiftsUnit,shiftsunitRemarks,shiftsNc,ncvtNorms,ncvtnormsRemarks," +
                "ncvtNc,actualArea,actualareaRemarks,actualareaNc,shortageArea,shortageareaRemarks,shortageAreaNc,refId," +
                "istheWorkshopRectangular,istheWorkshopRectangularRemarks,istheWorkshopRectangularNc,widthOftheWorkshop," +
                "widthOftheWorkshopRemarks,widthOftheWorkshopNc,aretheWorkshopWallsofTin,aretheWorkshopWallsofTinRemarks," +
                "aretheWorkshopWallsofTinNc,istheheavyMachineryLocated,istheheavyMachineryLocatedRemarks," +
                "istheheavyMachineryLocatedNc,itihaveCombinedWorkshop,itihaveCombinedWorkshopRemarks," +
                "itihaveCombinedWorkshopNc,istheDemarcated,istheDemarcatedRemarks,istheDemarcatedNc," +
                "emergencyContactNumberDisplay,emergencyContactNumberDisplayRemarks,emergencyContactNumberDisplayNc," +
                "WorkShopRoof,WorkShopRoof_Remarks,WorkShopRoof_Nc,ceilingHeightlessthan12Feet," +
                "ceilingHeightlessthan12Feet_Remarks,ceilingHeightlessthan12Feet_Nc,ceilingHeightlessthan10Feet," +
                "ceilingHeightlessthan10Feet_Remarks,proc_tracker,refId,tradeName,isWiresandBoardsCovered," +
                "isFireExtinguisherAvailable,isEmergencyExit,isWiresandBoardsCoveredRemarks," +
                "isFireExtinguisherAvailableRemarks,isEmergencyExitRemarks,isWiresandBoardsCoveredNc," +
                "isFireExtinguisherAvailableNc,workshopName,machinaryTools, machinaryToolsRemarks, machinaryToolsNC, machineComplying " +
                ", machineComplyingRemarks, machineComplyingNC, rubberMat , rubberMatRemarks, rubberMatNC, dgRequired ," +
                " dgRequiredRemarks, dgRequiredNC, dgInstalled , dgInstalledRemarks, dgInstalledNC, latheRequired , " +
                "latheRequiredRemarks, latheRequiredNC, latheInstalled , latheInstalledRemarks, " +
                "latheInstalledNC, majorMachine , majorMachineRemarks, majorMachineNC ,flag,proc_tracker" +
                " from WorkshopArea where YearWiseCollegeId = " + YearWiseCollegeId +" and refId = "+ Id;

        WorkshopAreaModel workshopAreaObj = null;
        try {            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                do {
                    workshopAreaObj = new WorkshopAreaModel();
                    workshopAreaObj.setYearWiseCollegeid(cursor.getString(0));
                    workshopAreaObj.setTradeId(cursor.getString(1));
                    workshopAreaObj.setShiftsUnit(cursor.getString(2));
                    workshopAreaObj.setShiftsunitRemarks(cursor.getString(3));
                    workshopAreaObj.setShiftsNc(cursor.getInt(4));
                    //workshopAreaObj.setNcvtNorms(cursor.getString(5));
                    //workshopAreaObj.setNcvtnormsRemarks(cursor.getString(6));
                    //workshopAreaObj.setNcvtNc(cursor.getString(7));
                    workshopAreaObj.setActualArea(cursor.getString(8));
                    workshopAreaObj.setActualareaRemarks(cursor.getString(9));
                    //workshopAreaObj.setActualareaNc(cursor.getString(10));
                    //workshopAreaObj.setShortageArea(cursor.getString(11));
                    //workshopAreaObj.setShortageareaRemarks(cursor.getString(12));
                    //workshopAreaObj.setShortageAreaNc(cursor.getString(13));
                    workshopAreaObj.setRefId(cursor.getInt(14));
                    workshopAreaObj.setIstheWorkshopRectangular(cursor.getString(15));
                    workshopAreaObj.setIstheWorkshopRectangularRemarks(cursor.getString(16));
                    workshopAreaObj.setIstheWorkshopRectangularNc(cursor.getInt(17));
                    workshopAreaObj.setWidthOftheWorkshop(cursor.getString(18));
                    workshopAreaObj.setWidthOftheWorkshopRemarks(cursor.getString(19));
                    workshopAreaObj.setWidthOftheWorkshopNc(cursor.getInt(20));
                    workshopAreaObj.setAretheWorkshopWallsofTin(cursor.getString(21));
                    workshopAreaObj.setAretheWorkshopWallsofTinRemarks(cursor.getString(22));
                    workshopAreaObj.setAretheWorkshopWallsofTinNc(cursor.getInt(23));
                    workshopAreaObj.setIstheheavyMachineryLocated(cursor.getString(24));
                    workshopAreaObj.setIstheheavyMachineryLocated_Remarks(cursor.getString(25));
                    //workshopAreaObj.setIstheheavyMachineryLocatedNc(cursor.getString(26));
                    workshopAreaObj.setItihaveCombinedWorkshop(cursor.getString(27));
                    workshopAreaObj.setItihaveCombinedWorkshopRemarks(cursor.getString(28));
                    //workshopAreaObj.setItihaveCombinedWorkshopNc(cursor.getString(29));
                    workshopAreaObj.setIstheDemarcated(cursor.getString(30));
                    workshopAreaObj.setIstheDemarcatedRemarks(cursor.getString(31));
                    //workshopAreaObj.setIstheDemarcatedNc(cursor.getString(32));
                    workshopAreaObj.setEmergencyContactNumberDisplay(cursor.getString(33));
                    workshopAreaObj.setEmergencyContactNumberDisplayRemarks(cursor.getString(34));
                    //workshopAreaObj.setEmergencyContactNumberDisplayNc(cursor.getString(35));
                    workshopAreaObj.setWorkShopRoof(cursor.getString(36));
                    workshopAreaObj.setWorkShopRoof_Remarks(cursor.getString(37));
                    //workshopAreaObj.setWorkShopRoof_Nc(cursor.getString(38));
                    workshopAreaObj.setCeilingHeightlessthan12Feet(cursor.getString(39));
                    workshopAreaObj.setCeilingHeightlessthan12Feet_Remarks(cursor.getString(40));
                    //workshopAreaObj.setCeilingHeightlessthan12Feet_Nc(cursor.getString(41));
                    workshopAreaObj.setCeilingHeightlessthan10Feet(cursor.getString(42));
                    workshopAreaObj.setCeilingHeightlessthan10FeetRemarks(cursor.getString(43));
                    workshopAreaObj.setProc_tracker(cursor.getInt(44));
                    workshopAreaObj.setId(cursor.getInt(45));
                    workshopAreaObj.setTradeName(cursor.getString(46));

                    workshopAreaObj.setIsWiresandBoardsCovered(cursor.getString(47));
                    workshopAreaObj.setIsFireExtinguisherAvailable(cursor.getString(48));
                    workshopAreaObj.setIsEmergencyExit(cursor.getString(49));
                    workshopAreaObj.setIsWiresandBoardsCoveredRemarks(cursor.getString(50));
                    workshopAreaObj.setIsFireExtinguisherAvailableRemarks(cursor.getString(51));
                    workshopAreaObj.setIsEmergencyExitRemarks(cursor.getString(52));
                    workshopAreaObj.setIsWiresandBoardsCoveredNc(cursor.getInt(53));
                    workshopAreaObj.setIsFireExtinguisherAvailableNc(cursor.getInt(54));
                    workshopAreaObj.setWorkshopName(cursor.getString(55));
                    workshopAreaObj.setMachinaryTools(cursor.getString(56));
                    workshopAreaObj.setMachinaryToolsRemarks(cursor.getString(57));
                    workshopAreaObj.setMachinaryToolsNC(cursor.getInt(58));
                    workshopAreaObj.setMachinesComplying(cursor.getString(59));
                    workshopAreaObj.setMachinesComplyingRemarks(cursor.getString(60));
                    workshopAreaObj.setMachinesComplyingNC(cursor.getInt(61));
                    workshopAreaObj.setRubberMats(cursor.getString(62));
                    workshopAreaObj.setRubberMatsRemarks(cursor.getString(63));
                    workshopAreaObj.setRubberMatsNC(cursor.getInt(64));
                    workshopAreaObj.setDgsetRequired(cursor.getString(65));
                    workshopAreaObj.setDgsetRequiredRemarks(cursor.getString(66));
                    workshopAreaObj.setDgsetRequiredNC(cursor.getInt(67));
                    workshopAreaObj.setDgsetInstalled(cursor.getString(68));
                    workshopAreaObj.setDgsetInstalledRemarks(cursor.getString(69));
                    workshopAreaObj.setDgsetInstalledNC(cursor.getInt(70));
                    workshopAreaObj.setLatheRequired(cursor.getString(71));
                    workshopAreaObj.setLatheRequiredRemarks(cursor.getString(72));
                    workshopAreaObj.setLatheRequiredNC(cursor.getInt(73));
                    workshopAreaObj.setLatheInstalled(cursor.getString(74));
                    workshopAreaObj.setLatheInstalledRemarks(cursor.getString(75));
                    workshopAreaObj.setLatheInstalledNC(cursor.getInt(76));
                    workshopAreaObj.setMajorMachine(cursor.getString(77));
                    workshopAreaObj.setMajorMachineRemarks(cursor.getString(78));
                    workshopAreaObj.setMajorMachineNC(cursor.getInt(79));
                    workshopAreaObj.setFlag(cursor.getInt(80));
                    workshopAreaObj.setProc_tracker(cursor.getInt(81));

                } while (cursor.moveToNext());
            }        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;        }finally {
            db.close();        }
        return workshopAreaObj;
    }

    public boolean saveWorkshopArea(WorkshopAreaModel workshopAreaObj, String mode) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put("YearWiseCollegeId",workshopAreaObj.getYearWiseCollegeid());
        values.put("tradeId",workshopAreaObj.getTradeId());
        values.put("shiftsUnit",workshopAreaObj.getShiftsUnit());
        values.put("shiftsunitRemarks",workshopAreaObj.getShiftsunitRemarks());
        values.put("shiftsNc",workshopAreaObj.getShiftsNc());
        ///values.put("ncvtNorms",workshopAreaObj.getNcvtNorms());
        //values.put("ncvtnormsRemarks",workshopAreaObj.getNcvtnormsRemarks());
        //values.put("ncvtNc",workshopAreaObj.getNcvtNc());
        values.put("actualArea",workshopAreaObj.getActualArea());
        values.put("actualareaRemarks",workshopAreaObj.getActualareaRemarks());
        //values.put("actualareaNc",workshopAreaObj.getActualareaNc());
        //values.put("shortageArea",workshopAreaObj.getShortageArea());
        //values.put("shortageareaRemarks",workshopAreaObj.getShortageareaRemarks());
        //values.put("shortageAreaNc",workshopAreaObj.getShortageAreaNc());
        //values.put("refId",workshopAreaObj.getRefId());
        values.put("istheWorkshopRectangular",workshopAreaObj.getIstheWorkshopRectangular());
        values.put("istheWorkshopRectangularRemarks",workshopAreaObj.getIstheWorkshopRectangularRemarks());
        values.put("istheWorkshopRectangularNc",workshopAreaObj.getIstheWorkshopRectangularNc());
        values.put("widthOftheWorkshop",workshopAreaObj.getWidthOftheWorkshop());
        values.put("widthOftheWorkshopRemarks",workshopAreaObj.getWidthOftheWorkshopRemarks());
        values.put("widthOftheWorkshopNc",workshopAreaObj.getWidthOftheWorkshopNc());
        values.put("aretheWorkshopWallsofTin",workshopAreaObj.getAretheWorkshopWallsofTin());
        values.put("aretheWorkshopWallsofTinRemarks",workshopAreaObj.getAretheWorkshopWallsofTinRemarks());
        values.put("aretheWorkshopWallsofTinNc",workshopAreaObj.getAretheWorkshopWallsofTinNc());
        values.put("istheheavyMachineryLocated",workshopAreaObj.getIstheheavyMachineryLocated());
        values.put("istheheavyMachineryLocatedRemarks",workshopAreaObj.getIstheheavyMachineryLocated_Remarks());
        //values.put("istheheavyMachineryLocatedNc",workshopAreaObj.getIstheheavyMachineryLocatedNc());
        values.put("itihaveCombinedWorkshop",workshopAreaObj.getItihaveCombinedWorkshop());
        values.put("itihaveCombinedWorkshopRemarks",workshopAreaObj.getItihaveCombinedWorkshopRemarks());
        //values.put("itihaveCombinedWorkshopNc",workshopAreaObj.getItihaveCombinedWorkshopNc());
        values.put("istheDemarcated",workshopAreaObj.getIstheDemarcated());
        values.put("istheDemarcatedRemarks",workshopAreaObj.getIstheDemarcatedRemarks());
        //values.put("istheDemarcatedNc",workshopAreaObj.getIstheDemarcatedNc());
        values.put("emergencyContactNumberDisplay",workshopAreaObj.getEmergencyContactNumberDisplay());
        values.put("emergencyContactNumberDisplayRemarks",workshopAreaObj.getEmergencyContactNumberDisplayRemarks());
        //values.put("emergencyContactNumberDisplayNc",workshopAreaObj.getEmergencyContactNumberDisplayNc());
        values.put("WorkShopRoof",workshopAreaObj.getWorkShopRoof());
        values.put("WorkShopRoof_Remarks",workshopAreaObj.getWorkShopRoof_Remarks());
        //values.put("WorkShopRoof_Nc",workshopAreaObj.getWorkShopRoof_Nc());
        values.put("ceilingHeightlessthan12Feet",workshopAreaObj.getCeilingHeightlessthan12Feet());
        values.put("ceilingHeightlessthan12Feet_Remarks",workshopAreaObj.getCeilingHeightlessthan12Feet_Remarks());
        //values.put("ceilingHeightlessthan12Feet_Nc",workshopAreaObj.getCeilingHeightlessthan12Feet_Nc());
        values.put("ceilingHeightlessthan10Feet",workshopAreaObj.getCeilingHeightlessthan10Feet());
        values.put("ceilingHeightlessthan10Feet_Remarks",workshopAreaObj.getCeilingHeightlessthan10FeetRemarks());

        values.put("isWiresandBoardsCovered",workshopAreaObj.getIsWiresandBoardsCovered());
        values.put("isFireExtinguisherAvailable",workshopAreaObj.getIsFireExtinguisherAvailable());
        values.put("isEmergencyExit",workshopAreaObj.getIsEmergencyExit());
        values.put("isWiresandBoardsCoveredRemarks",workshopAreaObj.getIsWiresandBoardsCoveredRemarks());
        values.put("isFireExtinguisherAvailableRemarks",workshopAreaObj.getIsFireExtinguisherAvailableRemarks());
        values.put("isEmergencyExitRemarks",workshopAreaObj.getIsEmergencyExitRemarks());
        values.put("isWiresandBoardsCoveredNc",workshopAreaObj.getIsWiresandBoardsCoveredNc());
        values.put("isFireExtinguisherAvailableNc",workshopAreaObj.getIsFireExtinguisherAvailableNc());
        values.put("workshopName",workshopAreaObj.getWorkshopName());


        if (mode.equalsIgnoreCase("draft")) {
            values.put("proc_tracker", 2);
        } else {
            values.put("proc_tracker", 3);
        }
        db.update("WorkshopArea", values, " YearWiseCollegeId= " + workshopAreaObj.getYearWiseCollegeid() +" and refId= "+
                workshopAreaObj.getId(), null);
        return true;    }
    //-------------------------------------------------------------------------------------------------------------------

    public boolean updateToolsBelowDetails(ToolsBelow toolsBelow) {

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        try {

            Log.e("test4","test4");

            values.put("photo", toolsBelow.getPhoto());
            values.put("remarks", toolsBelow.getRemarks());
            values.put("nc", toolsBelow.getNc());
            values.put("proc_tracker", toolsBelow.getProcTracker());

            database.update("ToolsBelowInfoImage", values, " YearWiseCollegeId= " + toolsBelow.getYearWiseCollegeId()
                    + " AND docId=" + toolsBelow.getDocId()+" AND refId=" + toolsBelow.getRefId(), null);
            Log.e("test5","test5");
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            database.close();
        }
        return true;
    }

    public List<ToolsBelow> getToolsBelowImageInfobyYearwiseCollegeId(String yearWiseCollegeId) {
        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , docId,tradeId,"
                + " section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc, proc_tracker"
                + " FROM ToolsBelowInfoImage where YearWiseCollegeId = " + yearWiseCollegeId;

        List<ToolsBelow> generalImageModelList = new ArrayList<ToolsBelow>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                Log.e("test","test");
                do {
                    String section = cursor.getString(3);
                    if (section != null && section.trim().equalsIgnoreCase("Workshop Wise Tools & Equipments")) {
                        ToolsBelow general = new ToolsBelow();
                        general.setYearWiseCollegeId(cursor.getString(0));
                        general.setDocId(cursor.getInt(1));
                        general.setTradeId(cursor.getString(2));
                        general.setSection(cursor.getString(3));
                        general.setRefId(cursor.getInt(4));
                        general.setPictureType(cursor.getString(5));
                        general.setHelpText(cursor.getString(6));
                        general.setRemarks(cursor.getString(7));
                        general.setLatitude(cursor.getString(8));
                        general.setLongitude(cursor.getString(9));
                        general.setName(cursor.getString(10));
                        general.setPhoto(cursor.getString(11));
                        general.setNc(cursor.getInt(12));
                        general.setProcTracker(cursor.getInt(13));
                        generalImageModelList.add(general);
                    }
                    Log.e("test","test");
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }

        return generalImageModelList;
    }

    public boolean updateToolsBelowProcTracker(List<ToolsBelow> lstlabToBeSync, String yearWiseCollegeId) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        if (lstlabToBeSync == null || lstlabToBeSync.size() == 0) {
            return false;
        }

        StringBuilder sbIds = new StringBuilder();
        for (ToolsBelow fac : lstlabToBeSync) {
            sbIds.append("'").append(fac.getDocId()).append("'").append(",");
        }
        if (sbIds.length() > 0) {
            sbIds.setLength(sbIds.length() - 1);
        }

        try {
            values.put("proc_tracker", 3);
            database.update("ToolsBelowInfoImage", values, " YearWiseCollegeId= " + yearWiseCollegeId + " AND docId IN (" + sbIds.toString() + ") AND refId = "+lstlabToBeSync.get(0).getRefId(), null);

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            database.close();
        }
        return true;
    }

    public boolean addToolsBelowUploadImageData(JSONObject result, String instituteId) {

        try {

            SQLiteDatabase database = getWritableDatabase();

            int status = result.optInt("status");
            if(status == 0){
                JSONObject payloadObj = result.optJSONObject("payload");
                JSONArray GeneralDetailsArr = payloadObj.optJSONArray("workShopAndToolWisePhotoDoc");
                for (int i = 0;i < GeneralDetailsArr.length();i++){
                    JSONObject GeneralDetailsObj = GeneralDetailsArr.optJSONObject(i);
                    ContentValues values = new ContentValues();
                    values.put("YearWiseCollegeId",instituteId);
                    values.put("docId",GeneralDetailsObj.optString("docId"));
                    values.put("section",GeneralDetailsObj.optString("section"));
                    values.put("refId",GeneralDetailsObj.optString("refId"));
                    values.put("pictureType",GeneralDetailsObj.optString("pictureType"));
                    values.put("helpText",GeneralDetailsObj.optString("helpText"));
                    values.put("remarks",GeneralDetailsObj.optString("remarks"));
                    values.put("latitude",GeneralDetailsObj.optString("latitude"));
                    values.put("longitude",GeneralDetailsObj.optString("longitude"));
                    values.put("name",GeneralDetailsObj.optString("name"));
                    values.put("photo",GeneralDetailsObj.optString("photo"));
                    values.put("nc",GeneralDetailsObj.optString("nc"));
                    values.put("tradeId",GeneralDetailsObj.optString("tradeId"));
                    values.put("proc_tracker", 1);

                    database.insert("ToolsBelowInfoImage", null, values);
                }

            }else {
                //  Toast.makeText(application_context, "No images found in General Details", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean addToolabove10000InfoData(JSONObject result, String yearWiseCollegeId, String tradeId) {

        try{
            SQLiteDatabase database = getWritableDatabase();
            int status = result.optInt("status");
            if(status == 0){
                JSONObject payloadObj = result.optJSONObject("payload");
                JSONArray tradeWiseToolsArr = payloadObj.optJSONArray("toolsAbove10000");
                for (int i = 0;i<tradeWiseToolsArr.length();i++) {
                    JSONObject tradeWiseToolsObj = tradeWiseToolsArr.optJSONObject(i);
                    values.put("yearWiseCollegeId",yearWiseCollegeId);
                    values.put("tradeId",tradeId);
                    values.put("refId",tradeWiseToolsObj.optString("refId"));
                    values.put("equipmentName", tradeWiseToolsObj.optString("equipmentName"));
                    values.put("equipmentId", tradeWiseToolsObj.optString("equipmentId"));
                    values.put("isGroutingandEngravingRequired",tradeWiseToolsObj.optString("isGroutingandEngravingRequired"));
                    values.put("isGroutingandengravingAvailable",tradeWiseToolsObj.optString("isGroutingandengravingAvailable"));
                    values.put("isEngravingRequired",tradeWiseToolsObj.optString("isEngravingRequired"));
                    values.put("isGroutingRequired",tradeWiseToolsObj.optString("isGroutingRequired"));
                    values.put("proc_tracker", 1);
                    database.insert("ToolsAboveInfo", null, values);

                }
            }else {
                Toast.makeText(application_context, "No Data Found..", Toast.LENGTH_LONG).show();
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public List<ToolsAbove> getToolsAbovebyTradeId(String yearWiseCollegeId, String tradeId) {

        SQLiteDatabase db = null;
        String selectQueryQues ="Select yearWiseCollegeId,tradeId,refId,equipmentName,equipmentId,isGroutingandEngravingRequired," +
                "isGroutingandengravingAvailable,isEngravingRequired,isGroutingRequired,proc_tracker from ToolsAboveInfo where YearWiseCollegeId = " + yearWiseCollegeId +" and tradeId = "+tradeId;

        List<ToolsAbove> tradewiseInfolist = new ArrayList<>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    ToolsAbove tradewisetoolInfoModel = new ToolsAbove();
                    tradewisetoolInfoModel.setYearWiseCollegeid(cursor.getString(0));
                    tradewisetoolInfoModel.setTradeId(cursor.getString(1));
                    tradewisetoolInfoModel.setRefId(cursor.getString(2));
                    tradewisetoolInfoModel.setEquipmentName(cursor.getString(3));
                    tradewisetoolInfoModel.setEquipmentId(cursor.getString(4));
                    tradewisetoolInfoModel.setGroutingAvailable(cursor.getString(5));
                    tradewisetoolInfoModel.setEngravingAvailable(cursor.getString(6));
                    tradewisetoolInfoModel.setEngravingRequired(cursor.getString(7));
                    tradewisetoolInfoModel.setGroutingRequired(cursor.getString(8));
                    tradewisetoolInfoModel.setProc_tracker(cursor.getInt(9));
                    tradewiseInfolist.add(tradewisetoolInfoModel);


                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return tradewiseInfolist;
    }

    public boolean saveTradeWiseInfo(TradeWiseTool tradeWiseToolInfo, String modes) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
            values.put("reqUnit", tradeWiseToolInfo.getReqUnit());
            values.put("markTools", tradeWiseToolInfo.getMarkTools());
//            values.put("availableUnit", tradeWiseToolInfo.getAvailableUnit());


            if (modes.equalsIgnoreCase("draft")) {
                values.put("proc_tracker", 2);
            } else {
                values.put("proc_tracker", 3);
            }
        db.update("TradeWiseToolInfo", values, " YearWiseCollegeId= " + tradeWiseToolInfo.getYearWiseCollegeid()
                + " and equipmentId= " + tradeWiseToolInfo.getEquipmentId()
                + " and tradeId= " + tradeWiseToolInfo.getTradeId(), null);

        return true;
    }

    public TradeWiseTool getTradewiseTradeId(String yearWiseCollegeId, String tradeId) {

        SQLiteDatabase db = null;
        String selectQueryQues ="Select yearWiseCollegeId,refId,equipmentId,tradeId,tradeName,equipmentName,reqUnit,markTools,proc_tracker from TradeWiseToolInfo where YearWiseCollegeId = " + yearWiseCollegeId +" and tradeId = "+tradeId;

        TradeWiseTool tradewisetoolInfoModel = null;
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    tradewisetoolInfoModel = new TradeWiseTool();
                    tradewisetoolInfoModel.setYearWiseCollegeid(cursor.getString(0));
                    tradewisetoolInfoModel.setRefId(cursor.getString(1));
                    tradewisetoolInfoModel.setEquipmentId(cursor.getString(2));
                    tradewisetoolInfoModel.setTradeId(cursor.getString(3));
                    tradewisetoolInfoModel.setTradeName(cursor.getString(4));
                    tradewisetoolInfoModel.setEquipmentName(cursor.getString(5));
                    tradewisetoolInfoModel.setReqUnit(cursor.getString(6));
                    tradewisetoolInfoModel.setMarkTools(cursor.getString(7));
                    tradewisetoolInfoModel.setProc_tracker(cursor.getInt(8));

                    tradewisetoolInfoModel.setTradeId(tradeId);


                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return tradewisetoolInfoModel;
    }

    public boolean saveTradesInfo(Accommodation tradeWiseToolInfo, String modes) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("tradeid", tradeWiseToolInfo.getTradeid());
        values.put("nameofTrade", tradeWiseToolInfo.getNameofTrade());
        values.put("name", "");
        values.put("shift1",tradeWiseToolInfo.getShift1());
        values.put("shift2",tradeWiseToolInfo.getShift2());
        values.put("shift3", tradeWiseToolInfo.getShift3());
        values.put("total", tradeWiseToolInfo.getTotal());
        if (modes.equalsIgnoreCase("draft")) {
            values.put("proc_tracker", 2);
        } else {
            values.put("proc_tracker", 3);
        }
        db.update("TradeInfo", values, " YearWiseCollegeId= " + tradeWiseToolInfo.getYearWiseCollegeid() + " and tradeId= " + tradeWiseToolInfo.getTradeid(), null);

        return true;


    }

    public Accommodation getToolsAboveTenkInfo(String YearWiseCollegeId, String tradeId) {

        SQLiteDatabase db = null;
        String selectQueryQues ="Select YearWiseCollegeId,tradeid,nameofTrade,shift1,shift2,shift3,total,proc_tracker from ToolsAboveTenK where YearWiseCollegeId = " + YearWiseCollegeId + " and tradeId= " + tradeId;

        Accommodation tradeInfoModel = null;
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    tradeInfoModel= new Accommodation();
                    tradeInfoModel.setYearWiseCollegeid(cursor.getString(0));
                    tradeInfoModel.setTradeid(cursor.getString(1));
                    tradeInfoModel.setNameofTrade(cursor.getString(2));
                    tradeInfoModel.setShift1(cursor.getString(3));
                    tradeInfoModel.setShift2(cursor.getString(4));
                    tradeInfoModel.setShift3(cursor.getString(5));
                    tradeInfoModel.setTotal(cursor.getString(6));
                    tradeInfoModel.setProc_tracker(cursor.getInt(7));

                    tradeInfoModel.setTradeid(tradeId);




                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return tradeInfoModel;
    }

    public boolean saveToolsAboveInfo(Accommodation toolsAboveInfo, String modes) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("tradeid", toolsAboveInfo.getTradeid());
        values.put("nameofTrade", toolsAboveInfo.getNameofTrade());
        values.put("name", "");
        values.put("shift1",toolsAboveInfo.getShift1());
        values.put("shift2",toolsAboveInfo.getShift2());
        values.put("shift3", toolsAboveInfo.getShift3());
        values.put("total", toolsAboveInfo.getTotal());
        if (modes.equalsIgnoreCase("draft")) {
            values.put("proc_tracker", 2);
        } else {
            values.put("proc_tracker", 3);
        }
        db.update("ToolsAboveTenK", values, " YearWiseCollegeId= " + toolsAboveInfo.getYearWiseCollegeid() + " and tradeId= " + toolsAboveInfo.getTradeid(), null);

        return true;


    }


    public boolean addTradesWiseInfoData(JSONObject result, String yearWiseCollegeId) {

        try{

            SQLiteDatabase database = getWritableDatabase();

            int status = result.optInt("status");
            if(status == 0) {
                JSONObject payload = result.optJSONObject("payload");
                JSONArray tradeArray = payload.optJSONArray("FinalTrade");
                for (int i = 0; i < tradeArray.length(); i++) {
                    JSONObject tradeObj = tradeArray.getJSONObject(i);
                    ContentValues values = new ContentValues();
                    values.put("YearWiseCollegeId",yearWiseCollegeId);
                    values.put("tradeid", tradeObj.optString("tradeid"));
                    values.put("nameofTrade", tradeObj.optString("nameofTrade"));
                    values.put("shift1", tradeObj.optString("shift1"));
                    values.put("shift2", tradeObj.optString("shift2"));
                    values.put("shift3", tradeObj.optString("shift3"));
                    values.put("total", tradeObj.optString("total"));
                    values.put("proc_tracker", 1);
                    database.insert("TradeWiseInfo", null, values);
                }
            }else {
                Toast.makeText(application_context, "No Data Found..", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    public List<Accommodation> getTradesWiseInfoList(String YearWiseCollegeId) {
        SQLiteDatabase db = null;
        String selectQueryQues ="Select YearWiseCollegeId,tradeid,nameofTrade,shift1,shift2,shift3,total,proc_tracker from TradeWiseInfo where YearWiseCollegeId = " + YearWiseCollegeId;

        List<Accommodation> tradeInfoList = new ArrayList<Accommodation>();
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    Accommodation tradeInfoModel = new Accommodation();
                    tradeInfoModel.setYearWiseCollegeid(cursor.getString(0));
                    tradeInfoModel.setTradeid(cursor.getString(1));
                    tradeInfoModel.setNameofTrade(cursor.getString(2));
                    tradeInfoModel.setShift1(cursor.getString(3));
                    tradeInfoModel.setShift2(cursor.getString(4));
                    tradeInfoModel.setShift3(cursor.getString(5));
                    tradeInfoModel.setTotal(cursor.getString(6));
                    tradeInfoModel.setProc_tracker(cursor.getInt(7));
                    tradeInfoList.add(tradeInfoModel);


                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return tradeInfoList;
    }

    public Accommodation getTradesWiseInfobyYearwiseCollegeId(String YearWiseCollegeId, String tradeId) {
        SQLiteDatabase db = null;
        String selectQueryQues ="Select YearWiseCollegeId,tradeid,nameofTrade,shift1,shift2,shift3,total,proc_tracker from TradeWiseInfo where YearWiseCollegeId = " + YearWiseCollegeId + " and tradeId= " + tradeId ;

        Accommodation tradeInfoModel = null;
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    tradeInfoModel = new Accommodation();
                    tradeInfoModel.setYearWiseCollegeid(cursor.getString(0));
                    tradeInfoModel.setTradeid(cursor.getString(1));
                    tradeInfoModel.setNameofTrade(cursor.getString(2));
                    tradeInfoModel.setShift1(cursor.getString(3));
                    tradeInfoModel.setShift2(cursor.getString(4));
                    tradeInfoModel.setShift3(cursor.getString(5));
                    tradeInfoModel.setTotal(cursor.getString(6));
                    tradeInfoModel.setProc_tracker(cursor.getInt(7));

                    tradeInfoModel.setTradeid(tradeId);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return tradeInfoModel;
    }

    public boolean saveTradesWiseInfo(Accommodation tradeWiseToolInfo, String modes) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("tradeid", tradeWiseToolInfo.getTradeid());
        values.put("nameofTrade", tradeWiseToolInfo.getNameofTrade());
        values.put("name", "");
        values.put("shift1",tradeWiseToolInfo.getShift1());
        values.put("shift2",tradeWiseToolInfo.getShift2());
        values.put("shift3", tradeWiseToolInfo.getShift3());
        values.put("total", tradeWiseToolInfo.getTotal());
        if (modes.equalsIgnoreCase("draft")) {
            values.put("proc_tracker", 2);
        } else {
            values.put("proc_tracker", 3);
        }
        db.update("TradeWiseInfo", values, " YearWiseCollegeId= " + tradeWiseToolInfo.getYearWiseCollegeid() + " and tradeId= " + tradeWiseToolInfo.getTradeid(), null);

        return true;


    }

    public List<ToolsAboveImage> getToolsAboveCheckImageInfobyYearwiseCollegeId(String yearWiseCollegeId) {
        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , docId,"
                + " section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc, proc_tracker"
                + " FROM ToolsAboveInfoImage where YearWiseCollegeId = " + yearWiseCollegeId;

        List<ToolsAboveImage> generalImageModelList = new ArrayList<ToolsAboveImage>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                Log.e("test","test");
                do {
                    String section = cursor.getString(5);
                    if (section != null && section.trim().equalsIgnoreCase("(Workshop wise Tools above 10000 value")) {
                        ToolsAboveImage general = new ToolsAboveImage();
                        general.setYearWiseCollegeId(cursor.getString(0));
                        general.setDocId(cursor.getInt(1));
                        general.setSection(cursor.getString(2));
                        general.setRefId(cursor.getInt(3));
                        general.setPictureType(cursor.getString(4));
                        general.setHelpText(cursor.getString(5));
                        general.setRemarks(cursor.getString(6));
                        general.setLatitude(cursor.getString(7));
                        general.setLongitude(cursor.getString(8));
                        general.setName(cursor.getString(9));
                        general.setPhoto(cursor.getString(10));
                        general.setNc(cursor.getInt(11));
                        general.setProcTracker(cursor.getInt(12));
                        generalImageModelList.add(general);
                    }
                    Log.e("test","test");
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }

        return generalImageModelList;
    }


    public List<TradeWiseTool> getTradesWiseToolToDataSync(String yearWiseCollegeId) {
        SQLiteDatabase db = null;
        String selectQueryQues ="Select yearWiseCollegeId,refId,equipmentId,tradeId,tradeName,equipmentName,reqUnit,markTools,proc_tracker from TradeWiseToolInfo where YearWiseCollegeId = " + yearWiseCollegeId;

        List<TradeWiseTool> tradewiseInfolist = new ArrayList<>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    TradeWiseTool tradewisetoolInfoModel = new TradeWiseTool();
                    tradewisetoolInfoModel.setYearWiseCollegeid(cursor.getString(0));
                    tradewisetoolInfoModel.setRefId(cursor.getString(1));
                    tradewisetoolInfoModel.setEquipmentId(cursor.getString(2));
                    tradewisetoolInfoModel.setTradeId(cursor.getString(3));
                    tradewisetoolInfoModel.setTradeName(cursor.getString(4));
                    tradewisetoolInfoModel.setEquipmentName(cursor.getString(5));
                    tradewisetoolInfoModel.setReqUnit(cursor.getString(6));
                    tradewisetoolInfoModel.setMarkTools(cursor.getString(7));
                    tradewisetoolInfoModel.setProc_tracker(cursor.getInt(8));
                    tradewiseInfolist.add(tradewisetoolInfoModel);


                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return tradewiseInfolist;

    }

    public TradeWiseTool getTradewiseToolsInfo(String yearWiseCollegeId, String tradeId) {


        SQLiteDatabase db = null;
        String selectQueryQues ="Select yearWiseCollegeId,refId,equipmentId,tradeId,tradeName,equipmentName," +
                "reqUnit,markTools,availableUnit,proc_tracker from TradeWiseToolInfo" +
                " where YearWiseCollegeId = " + yearWiseCollegeId +" and tradeId = "+tradeId ;

        TradeWiseTool tradewisetoolInfoModel = null;
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    tradewisetoolInfoModel= new TradeWiseTool();
                    tradewisetoolInfoModel.setYearWiseCollegeid(cursor.getString(0));
                    tradewisetoolInfoModel.setRefId(cursor.getString(1));
                    tradewisetoolInfoModel.setEquipmentId(cursor.getString(2));
                    tradewisetoolInfoModel.setTradeId(cursor.getString(3));
                    tradewisetoolInfoModel.setTradeName(cursor.getString(4));
                    tradewisetoolInfoModel.setEquipmentName(cursor.getString(5));
                    tradewisetoolInfoModel.setReqUnit(cursor.getString(6));
                    tradewisetoolInfoModel.setMarkTools(cursor.getString(7));
                    tradewisetoolInfoModel.setAvailableUnit(cursor.getString(8));
                    tradewisetoolInfoModel.setProc_tracker(cursor.getInt(9));
                    //tradewiseInfolist.add(tradewisetoolInfoModel);


                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return tradewisetoolInfoModel;


    }

    public List<ClassroomImage> getUnsavedClassroomImageInfobyYearwiseCollegeId(String yearWiseCollegeId, Integer refId, Integer docId) {

        SQLiteDatabase db = null;

        String selectQueryQues = "select YearWiseCollegeId , docId , section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc,flag,tradeId, proc_tracker"
                + " FROM ClassroomInfoImage where YearWiseCollegeId = " + yearWiseCollegeId + " AND refId='" + refId + "' "+ " AND DocId = '" + docId + "' AND proc_tracker = " + 2;

        List<ClassroomImage> generalList = new ArrayList<ClassroomImage>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                do {
                    String section = cursor.getString(2);
                    if (section != null && section.trim().equalsIgnoreCase("Classroom")) {
                        ClassroomImage general = new ClassroomImage();
                        general.setYearWiseCollegeId(cursor.getString(0));
                        general.setDocId(cursor.getInt(1));
                        general.setSection(cursor.getString(2));
                        general.setRefId(cursor.getInt(3));
                        general.setPictureType(cursor.getString(4));
                        general.setHelpText(cursor.getString(5));
                        general.setRemarks(cursor.getString(6));
                        general.setLatitude(cursor.getString(7));
                        general.setLongitude(cursor.getString(8));
                        general.setName(cursor.getString(9));
                        general.setPhoto(cursor.getString(10));
                        general.setNc(cursor.getInt(11));
                        general.setFlag(cursor.getInt(12));
                        general.setTradeId(cursor.getInt(13));
                        general.setProcTracker(cursor.getInt(14));
                        generalList.add(general);
                    }
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
        Log.e("test2","test2");

        return generalList;
    }

    public Accommodation getTradeInfo(String YearWiseCollegeId, String tradeId) {

        SQLiteDatabase db = null;
        String selectQueryQues ="Select YearWiseCollegeId,tradeid,nameofTrade,shift1,shift2,shift3,total,proc_tracker from TradeWiseInfo where YearWiseCollegeId = " + YearWiseCollegeId + " and tradeId= " + tradeId;

        Accommodation tradeInfoModel = null;
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {

                do {
                    tradeInfoModel= new Accommodation();
                    tradeInfoModel.setYearWiseCollegeid(cursor.getString(0));
                    tradeInfoModel.setTradeid(cursor.getString(1));
                    tradeInfoModel.setNameofTrade(cursor.getString(2));
                    tradeInfoModel.setShift1(cursor.getString(3));
                    tradeInfoModel.setShift2(cursor.getString(4));
                    tradeInfoModel.setShift3(cursor.getString(5));
                    tradeInfoModel.setTotal(cursor.getString(6));
                    tradeInfoModel.setProc_tracker(cursor.getInt(7));

                    tradeInfoModel.setTradeid(tradeId);




                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return tradeInfoModel;

    }

    public List<TechnicalImage> getUnsavedTechnicalImageInfobyYearwiseCollegeId(String yearWiseCollegeId, Integer refId, Integer docId) {
        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , docId , section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc, proc_tracker"
                + " FROM TechnicalInfoImage where YearWiseCollegeId = " + yearWiseCollegeId + " AND refId='" + refId + "' "+ " AND docId = '" + docId + "' AND proc_tracker = " + 2;

        Log.e("test1","test1");
        List<TechnicalImage> generalList = new ArrayList<TechnicalImage>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                do {
                    String section = cursor.getString(2);
                    if (section != null && section.trim().equalsIgnoreCase("Technical Staff Documents")) {
                        TechnicalImage general = new TechnicalImage();
                        general.setYearWiseCollegeId(cursor.getString(0));
                        general.setDocId(cursor.getInt(1));
                        general.setSection(cursor.getString(2));
                        general.setRefId(cursor.getInt(3));
                        general.setPictureType(cursor.getString(4));
                        general.setHelpText(cursor.getString(5));
                        general.setRemarks(cursor.getString(6));
                        general.setLatitude(cursor.getString(7));
                        general.setLongitude(cursor.getString(8));
                        general.setName(cursor.getString(9));
                        general.setPhoto(cursor.getString(10));
                        general.setNc(cursor.getInt(11));
                        general.setProcTracker(cursor.getInt(12));
                        generalList.add(general);
                    }
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
        Log.e("test2","test2");

        return generalList;
    }

    public List<InstructorImage> getUnsavedInstructorImageInfobyYearwiseCollegeId(String yearWiseCollegeId, Integer refId, Integer docId) {

                SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , docId , section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc, proc_tracker"
                + " FROM InstructorInfoImage where YearWiseCollegeId = " + yearWiseCollegeId + " AND refId='" + refId + "' "+ " AND docId = '" + docId + "' AND proc_tracker = " + 2;

        Log.e("test1","test1");
        List<InstructorImage> generalList = new ArrayList<InstructorImage>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                do {
                    String section = cursor.getString(2);
                    if (section != null && section.trim().equalsIgnoreCase("Instructor Staff Documents")) {
                        InstructorImage general = new InstructorImage();
                        general.setYearWiseCollegeId(cursor.getString(0));
                        general.setDocId(cursor.getInt(1));
                        general.setSection(cursor.getString(2));
                        general.setRefId(cursor.getInt(3));
                        general.setPictureType(cursor.getString(4));
                        general.setHelpText(cursor.getString(5));
                        general.setRemarks(cursor.getString(6));
                        general.setLatitude(cursor.getString(7));
                        general.setLongitude(cursor.getString(8));
                        general.setName(cursor.getString(9));
                        general.setPhoto(cursor.getString(10));
                        general.setNc(cursor.getInt(11));
                        general.setProcTracker(cursor.getInt(12));
                        generalList.add(general);
                    }
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
        Log.e("test2","test2");

        return generalList;
    }

    public List<LabImage> getUnsavedLabImageInfobyYearwiseCollegeId(String yearWiseCollegeId, Integer refId, Integer docId) {

                SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , docId , section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc, proc_tracker"
                + " FROM ITLabInfoImage where YearWiseCollegeId = " + yearWiseCollegeId + " AND refId='" + refId + "' "+ " AND docId = '" + docId + "' AND proc_tracker = " + 2;

        Log.e("test1","test1");
        List<LabImage> generalList = new ArrayList<LabImage>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                do {
                    String section = cursor.getString(2);
                    if (section != null && section.trim().equalsIgnoreCase("IT LAB")) {
                        LabImage general = new LabImage();
                        general.setYearWiseCollegeId(cursor.getString(0));
                        general.setDocId(cursor.getInt(1));
                        general.setSection(cursor.getString(2));
                        general.setRefId(cursor.getInt(3));
                        general.setPictureType(cursor.getString(4));
                        general.setHelpText(cursor.getString(5));
                        general.setRemarks(cursor.getString(6));
                        general.setLatitude(cursor.getString(7));
                        general.setLongitude(cursor.getString(8));
                        general.setName(cursor.getString(9));
                        general.setPhoto(cursor.getString(10));
                        general.setNc(cursor.getInt(11));
                        general.setProcTracker(cursor.getInt(12));
                        generalList.add(general);
                    }
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
        Log.e("test2","test2");

        return generalList;
    }

    public List<Workshop> getUnsavedWorkshopImageInfobyYearwiseCollegeId(String yearWiseCollegeId, Integer refId, Integer docId) {

                SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , docId , section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc,flag, proc_tracker"
                + " FROM WorkshopInfoImage where YearWiseCollegeId = " + yearWiseCollegeId + " AND refId='" + refId + "' "+ " AND docId = '" + docId + "' AND proc_tracker = " + 2;

        Log.e("test1","test1");
        List<Workshop> generalList = new ArrayList<Workshop>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                do {
                    String section = cursor.getString(2);
                    if (section != null && section.trim().equalsIgnoreCase("Workshop")) {
                        Workshop general = new Workshop();
                        general.setYearWiseCollegeId(cursor.getString(0));
                        general.setDocId(cursor.getInt(1));
                        general.setSection(cursor.getString(2));
                        general.setRefId(cursor.getInt(3));
                        general.setPictureType(cursor.getString(4));
                        general.setHelpText(cursor.getString(5));
                        general.setRemarks(cursor.getString(6));
                        general.setLatitude(cursor.getString(7));
                        general.setLongitude(cursor.getString(8));
                        general.setName(cursor.getString(9));
                        general.setPhoto(cursor.getString(10));
                        general.setNc(cursor.getInt(11));
                        general.setFlag(cursor.getInt(12));
                        general.setProcTracker(cursor.getInt(13));
                        generalList.add(general);
                    }
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
        Log.e("test2","test2");

        return generalList;
    }

    public List<ToolsBelow> getUnsavedToolsBelowImageInfobyYearwiseCollegeId(String yearWiseCollegeId, Integer refId, Integer docId) {

        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , docId ,tradeId, section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc, proc_tracker"
                + " FROM ToolsBelowInfoImage where YearWiseCollegeId = " + yearWiseCollegeId + " AND refId='" + refId + "' "+ " AND docId = '" + docId + "' AND proc_tracker = " + 2;

        Log.e("test1","test1");
        List<ToolsBelow> generalList = new ArrayList<ToolsBelow>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                do {
                    String section = cursor.getString(3);
                    if (section != null && section.trim().equalsIgnoreCase("Workshop Wise Tools & Equipments")) {
                        ToolsBelow general = new ToolsBelow();
                        general.setYearWiseCollegeId(cursor.getString(0));
                        general.setDocId(cursor.getInt(1));
                        general.setTradeId(cursor.getString(2));
                        general.setSection(cursor.getString(3));
                        general.setRefId(cursor.getInt(4));
                        general.setPictureType(cursor.getString(5));
                        general.setHelpText(cursor.getString(6));
                        general.setRemarks(cursor.getString(7));
                        general.setLatitude(cursor.getString(8));
                        general.setLongitude(cursor.getString(9));
                        general.setName(cursor.getString(10));
                        general.setPhoto(cursor.getString(11));
                        general.setNc(cursor.getInt(12));
                        general.setProcTracker(cursor.getInt(13));
                        generalList.add(general);
                    }
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
        Log.e("test2","test2");

        return generalList;
    }

    public List<ToolsAboveImage> getUnsavedToolsAboveImageInfobyYearwiseCollegeId(String yearWiseCollegeId, Integer refId, Integer docId) {

        SQLiteDatabase db = null;
        String selectQueryQues = "select YearWiseCollegeId , docId , section, refId, pictureType, helpText, remarks,latitude,longitude,name,photo,nc, proc_tracker"
                + " FROM ToolsAboveInfoImage where YearWiseCollegeId = " + yearWiseCollegeId + " AND refId='" + refId + "' "+ " AND docId = '" + docId + "' AND proc_tracker = " + 2;

        Log.e("test1","test1");
        List<ToolsAboveImage> generalList = new ArrayList<ToolsAboveImage>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);
            if (cursor.moveToFirst()) {
                do {
                    String section = cursor.getString(5);
                    if (section != null && section.trim().equalsIgnoreCase("(Workshop wise Tools above 10000 value")) {
                        ToolsAboveImage general = new ToolsAboveImage();
                        general.setYearWiseCollegeId(cursor.getString(0));
                        general.setDocId(cursor.getInt(1));
                        general.setSection(cursor.getString(2));
                        general.setRefId(cursor.getInt(3));
                        general.setPictureType(cursor.getString(4));
                        general.setHelpText(cursor.getString(5));
                        general.setRemarks(cursor.getString(6));
                        general.setLatitude(cursor.getString(7));
                        general.setLongitude(cursor.getString(8));
                        general.setName(cursor.getString(9));
                        general.setPhoto(cursor.getString(10));
                        general.setNc(cursor.getInt(11));
                        general.setProcTracker(cursor.getInt(12));
                        generalList.add(general);
                    }
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
        Log.e("test2","test2");

        return generalList;
    }

    public ITLab getItlabInfo(String YearWiseCollegeId) {

        SQLiteDatabase db = null;
        String selectQueryQues ="Select yearWiseCollegeid ,nameofequipment ,category , quantity , refId , available , Area ," +
                " AreaRemarks , AreaNc , Lab , LabRemarks ,LabNC, Internet , InternetRemarks , InternetNC , Roof , RoofRemarks " +
                ",RoofNC , Height , HeightRemarks , HeightNC , Tin , TinRemarks , TinNC , Floor , FloorRemarks , FloorNC,"+
                "proc_tracker from LabInfo where YearWiseCollegeId = " + YearWiseCollegeId;
        ITLab labInfoModel = null;
        try {

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQueryQues, null);

            if (cursor.moveToFirst()) {

                do {
                    labInfoModel = new ITLab();
                    labInfoModel.setYearWiseCollegeid(cursor.getString(0));
                    labInfoModel.setNameofequipment(cursor.getString(1));
                    labInfoModel.setCategory(cursor.getString(2));
                    labInfoModel.setQuantity(cursor.getString(3));
                    labInfoModel.setRefId(cursor.getInt(4));
                    labInfoModel.setAvailable(cursor.getInt(5));
                    labInfoModel.setActualArea(cursor.getString(6));
                    labInfoModel.setActualAreaRemarks(cursor.getString(7));
                    labInfoModel.setActualAreaNc(cursor.getInt(8));
                    labInfoModel.setLab(cursor.getString(9));
                    labInfoModel.setLabRemarks(cursor.getString(10));
                    labInfoModel.setLabNC(cursor.getInt(11));
                    labInfoModel.setInternet(cursor.getString(12));
                    labInfoModel.setInternetRemarks(cursor.getString(13));
                    labInfoModel.setInternetNC(cursor.getInt(14));
                    labInfoModel.setRoof(cursor.getString(15));
                    labInfoModel.setRoofRemarks(cursor.getString(16));
                    labInfoModel.setRoofNC(cursor.getInt(17));
                    labInfoModel.setHeight(cursor.getString(18));
                    labInfoModel.setHeightRemarks(cursor.getString(19));
                    labInfoModel.setHeightNC(cursor.getInt(20));
                    labInfoModel.setTin(cursor.getString(21));
                    labInfoModel.setTinRemarks(cursor.getString(22));
                    labInfoModel.setTinNC(cursor.getInt(23));
                    labInfoModel.setFloor(cursor.getString(24));
                    labInfoModel.setFloorRemarks(cursor.getString(25));
                    labInfoModel.setFloorNC(cursor.getInt(26));
                    labInfoModel.setProc_tracker(cursor.getInt(27));


                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("error getting jobroles", e.toString());
            Log.e(TAG, e.getMessage());
            return null;

        }finally {
            db.close();
        }

        return labInfoModel;
    }
}

