package com.ss.nsdc.constant;

/**
 * Created by Mayank on 28/08/2016.
 */
public class AppConstants {

    public static final String TEXT_SUPPORT_STAFF = "SupportStaff";
    public static final String TEXT_RESIDENTIAL_FACILITY = "ResFacility";
    public static final String TEXT_SUPPORT_SPACE_STAFF = "Support Staff";
    public static final String TEXT_RESIDENTIAL_SPACE_FACILITY = "Residential Facility";

    public static final String API_URL = "http://nsdc.qci.org.in/api/CAAF/";
    public static final String API_TOKEN_VALUE = "bnNkYzd0ZWNoaWVzYXBp";

    public static final String KEY_API_RESPONSE_CODE = "responsecode";
    public static final String KEY_API_ROW_COUNT = "rowcount";

    // JOb Roles
    public static final String KEY_ID = "ID";
    public static final String KEY_JOBNAME = "Jobname";
    public static final String KEY_HANDBOOK_AVAILABLE_ = "Handbook_Available";
    public static final String KEY_TRAINEES = "Trainees";
    public static final String KEY_BATCH = "batch";
    public static final String KEY_REMARK = "remark";
    public static final String KEY_HANDBOOK_AVAILABLE = "InsHandbook_Available";
    public static final String KEY_INS_TRAINEES = "InsTrainees";
    public static final String KEY_INS_BATCH = "Insbatch";
    public static final String KEY_INS_REMARK = "Insremark";

    public static final String URL_JOB_ROLES = "http://nsdc.qci.org.in/api/CAAF/DisplayJobRolesDetails.php";
    public static final String URL_GEN_INFO = "http://nsdc.qci.org.in/api/caaf/DisplayGenInfo.php";
    public static final String URL_EQUIPMENTS = "http://nsdc.qci.org.in/api/CAAF/DisplayUserEquipmentsDetails.php";
    public static final String URL_OFFICE_DETAILS = "http://nsdc.qci.org.in/api/CAAF/DisplayOfficeAreaDetails.php";
    public static final String URL_LAB_DETAILS = "http://nsdc.qci.org.in/api/CAAF/DisplayLabDetails.php";
    public static final String URL_CLASS_DETAILS = "http://nsdc.qci.org.in/api/CAAF/DisplayClassroomDetails.php";

    //SYNC URLs
    public static final String URL_CLASS_SYNC="http://nsdc.qci.org.in/api/CAAF/Classroom_Details.php";
    public static final String URL_LAB_SYNC = "http://nsdc.qci.org.in/api/CAAF/LAB_Details.php";
    public static final String URL_OFFICE_SYNC = "http://nsdc.qci.org.in/api/CAAF/Offiec_Area.php";
    public static final String URL_EQUIPMENT_SYNC = "http://nsdc.qci.org.in/api/CAAF/Equipment.php";
}
