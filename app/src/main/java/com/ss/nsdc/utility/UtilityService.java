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
import android.text.TextUtils;
import android.util.Log;

import com.ss.nsdc.entity.Classroom;
import com.ss.nsdc.entity.GenralInfo;
import com.ss.nsdc.entity.ITLab;
import com.ss.nsdc.entity.InstructorInfo;
import com.ss.nsdc.entity.LandandBuilding;
import com.ss.nsdc.entity.MaterialInfo;
import com.ss.nsdc.entity.Staffing;
import com.ss.nsdc.entity.TechincalInfo;
import com.ss.nsdc.entity.TradeWiseTool;
import com.ss.nsdc.entity.EquipmentInfo;
import com.ss.nsdc.entity.WorkshopAreaModel;

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


    public JSONObject getGeneralInfoSyncData(GenralInfo generalInfoModel) {

        JSONObject result = new JSONObject();
        JSONObject payloadObj = new JSONObject();
        JSONObject generalInfoObj = new JSONObject();
        JSONObject generalObj = new JSONObject();

        JSONArray array = new JSONArray();

        try {
            generalObj.put("refId", generalInfoModel.getRefId());

            generalObj.put("typeInstitute", TextUtils.isEmpty(generalInfoModel.getTypeInstitute()) ? "" : generalInfoModel.getTypeInstitute());
            generalObj.put("typeInstituteRemarks", TextUtils.isEmpty(generalInfoModel.getTypeInstituteRemark()) ? "" : generalInfoModel.getTypeInstituteRemark());
            generalObj.put("typeInstituteNC", generalInfoModel.getTypeInstituteNC());
            generalObj.put("date", TextUtils.isEmpty(generalInfoModel.getDate()) ? "" : generalInfoModel.getDate());
            generalObj.put("dateRemarks", TextUtils.isEmpty(generalInfoModel.getDateRemarks()) ? "" : generalInfoModel.getDateRemarks());
            generalObj.put("dateNc", generalInfoModel.getDateNc());
            generalObj.put("instituteType", TextUtils.isEmpty(generalInfoModel.getInstituteType()) ? "" : generalInfoModel.getInstituteType());
            generalObj.put("institutetypeRemarks", TextUtils.isEmpty(generalInfoModel.getInstitutetypeRemarks()) ? "" : generalInfoModel.getInstitutetypeRemarks());
            generalObj.put("institutetypeNc", generalInfoModel.getInstitutetypeNc());
//                    generalObj.put("date",generalIn);
            generalObj.put("phoneNo", TextUtils.isEmpty(generalInfoModel.getPhoneNo()) ? "" : generalInfoModel.getPhoneNo());
            generalObj.put("phonenoRemarks", TextUtils.isEmpty(generalInfoModel.getPhonenoRemarks()) ? "" : generalInfoModel.getPhonenoRemarks());
            generalObj.put("phonenoNc", generalInfoModel.getPhonenoNc());
//                    generalObj.put("date",generalIn);
            generalObj.put("specialStatus", TextUtils.isEmpty(generalInfoModel.getSpecialStatus()) ? "" : generalInfoModel.getSpecialStatus());
            generalObj.put("specialstatusRemarks", TextUtils.isEmpty(generalInfoModel.getSpecialstatusRemarks()) ? "" : generalInfoModel.getSpecialstatusRemarks());
            generalObj.put("specialstatusNc", generalInfoModel.getSpecialstatusNc());
//                    generalObj.put("date",generalIn);
            generalObj.put("faxNo", TextUtils.isEmpty(generalInfoModel.getFaxNo()) ? "" : generalInfoModel.getFaxNo());
            generalObj.put("faxnoRemarks", TextUtils.isEmpty(generalInfoModel.getFaxnoRemarks()) ? "" : generalInfoModel.getFaxnoRemarks());
            generalObj.put("faxnoNc", generalInfoModel.getFaxnoNc());
//                    generalObj.put("date",generalIn);
            generalObj.put("instituteLocation", TextUtils.isEmpty(generalInfoModel.getInstituteLocation()) ? "" : generalInfoModel.getInstituteLocation());
            generalObj.put("institutelocationRemarks", TextUtils.isEmpty(generalInfoModel.getInstitutelocationRemarks()) ? "" : generalInfoModel.getInstitutelocationRemarks());
            generalObj.put("institutelocationNc", generalInfoModel.getInstitutelocationNc());
            generalObj.put("mobile", TextUtils.isEmpty(generalInfoModel.getMobile()) ? "" : generalInfoModel.getMobile());
            generalObj.put("mobileRemarks", TextUtils.isEmpty(generalInfoModel.getMobileRemarks()) ? "" : generalInfoModel.getMobileRemarks());
            generalObj.put("mobileNc", generalInfoModel.getMobileNc());
            generalObj.put("specialLocation", TextUtils.isEmpty(generalInfoModel.getSpecialLocation()) ? "" : generalInfoModel.getSpecialLocation());
            generalObj.put("speciallocationRemarks", TextUtils.isEmpty(generalInfoModel.getSpeciallocationRemarks()) ? "" : generalInfoModel.getSpeciallocationRemarks());
            generalObj.put("speciallocationNc", generalInfoModel.getSpeciallocationNc());
            generalObj.put("email", TextUtils.isEmpty(generalInfoModel.getEmail()) ? "" : generalInfoModel.getEmail());
            generalObj.put("emailRemarks", TextUtils.isEmpty(generalInfoModel.getEmailRemarks()) ? "" : generalInfoModel.getEmailRemarks());
            generalObj.put("emailNc", generalInfoModel.getEmailNc());
            generalObj.put("principalName", TextUtils.isEmpty(generalInfoModel.getPrincipalName()) ? "" : generalInfoModel.getPrincipalName());
            generalObj.put("principalnameRemarks", TextUtils.isEmpty(generalInfoModel.getPrincipalnameRemarks()) ? "" : generalInfoModel.getPrincipalnameRemarks());
            generalObj.put("principalnameNc", generalInfoModel.getPrincipalnameNc());
            generalObj.put("instituteRunning", TextUtils.isEmpty(generalInfoModel.getInstituteRunning()) ? "" : generalInfoModel.getInstituteRunning());
            generalObj.put("instituterunningRemarks", TextUtils.isEmpty(generalInfoModel.getInstituterunningRemarks()) ? "" : generalInfoModel.getInstituterunningRemarks());
            generalObj.put("instituterunningNC", generalInfoModel.getInstituterunningNC());
            generalObj.put("address", TextUtils.isEmpty(generalInfoModel.getAddress()) ? "" : generalInfoModel.getAddress());
            generalObj.put("addressRemarks", TextUtils.isEmpty(generalInfoModel.getAddressRemarks()) ? "" : generalInfoModel.getAddressRemarks());
            String nc = String.valueOf(generalInfoModel.getAddressNc());
            Log.e("nc", nc);
            if (nc.equalsIgnoreCase("null")) {
                generalObj.put("addressNc", "0");
            } else {
                generalObj.put("addressNc", generalInfoModel.getAddressNc());
            }
            generalObj.put("landmark", TextUtils.isEmpty(generalInfoModel.getLandmark()) ? "" : generalInfoModel.getLandmark());
            generalObj.put("landmarkRemarks", TextUtils.isEmpty(generalInfoModel.getLandmarkRemarks()) ? "" : generalInfoModel.getLandmarkRemarks());
            generalObj.put("landmarkNc", generalInfoModel.getLandmarkNc());
            generalObj.put("district", TextUtils.isEmpty(generalInfoModel.getDistrict()) ? "" : generalInfoModel.getDistrict());
            generalObj.put("districtRemarks", TextUtils.isEmpty(generalInfoModel.getDistrictRemarks()) ? "" : generalInfoModel.getDistrictRemarks());
            generalObj.put("districtNc", generalInfoModel.getDistrictNc());
            generalObj.put("state", TextUtils.isEmpty(generalInfoModel.getState()) ? "" : generalInfoModel.getState());
            generalObj.put("stateRemarks", TextUtils.isEmpty(generalInfoModel.getStateRemarks()) ? "" : generalInfoModel.getStateRemarks());
            generalObj.put("stateNc", generalInfoModel.getStateNc());
            generalObj.put("tehsil", TextUtils.isEmpty(generalInfoModel.getTehsil()) ? "" : generalInfoModel.getTehsil());
            generalObj.put("tehsilRemarks", TextUtils.isEmpty(generalInfoModel.getTehsilRemarks()) ? "" : generalInfoModel.getTehsilRemarks());
            generalObj.put("tehsilNc", generalInfoModel.getTehsilNc());
            generalObj.put("pincode", TextUtils.isEmpty(generalInfoModel.getPincode()) ? "" : generalInfoModel.getPincode());
            generalObj.put("pincodeRemarks", TextUtils.isEmpty(generalInfoModel.getPincodeRemarks()) ? "" : generalInfoModel.getPincodeRemarks());
            generalObj.put("pincodeNc", generalInfoModel.getPincodeNc());
            generalObj.put("block", TextUtils.isEmpty(generalInfoModel.getBlock()) ? "" : generalInfoModel.getBlock());
            generalObj.put("blockRemarks", TextUtils.isEmpty(generalInfoModel.getBlockRemarks()) ? "" : generalInfoModel.getBlockRemarks());
            generalObj.put("blockNc", generalInfoModel.getBlockNc());
            generalObj.put("website", TextUtils.isEmpty(generalInfoModel.getWebsite()) ? "" : generalInfoModel.getWebsite());
            generalObj.put("websiteRemarks", TextUtils.isEmpty(generalInfoModel.getWebsiteRemarks()) ? "" : generalInfoModel.getWebsiteRemarks());

            String nc7 = String.valueOf(generalInfoModel.getWebsiteNc());
            Log.e("nc", nc);
            if (nc7.equalsIgnoreCase("null")) {
                generalObj.put("websiteNc", "0");
            } else {
                generalObj.put("websiteNc", generalInfoModel.getWebsiteNc());
            }

            generalObj.put("biometricDevice", TextUtils.isEmpty(generalInfoModel.getBiometricDevice()) ? "" : generalInfoModel.getBiometricDevice());
            generalObj.put("applicationNo_Remarks", TextUtils.isEmpty(generalInfoModel.getApplicationRemarks()) ? "" : generalInfoModel.getApplicationRemarks());

            generalObj.put("itiName_Remarks", TextUtils.isEmpty(generalInfoModel.getItiNameRemarks()) ? "" : generalInfoModel.getItiNameRemarks());

            String nc1 = String.valueOf(generalInfoModel.getItiNameNc());
            Log.e("nc", nc);
            if (nc1.equalsIgnoreCase("null")) {
                generalObj.put("itiName_NC", "0");
            } else {
                generalObj.put("itiName_NC", generalInfoModel.getItiNameNc());
            }

            generalObj.put("biometricdeviceRemarks", TextUtils.isEmpty(generalInfoModel.getBiometricdeviceRemarks()) ? "" : generalInfoModel.getBiometricdeviceRemarks());

            String nc3 = String.valueOf(generalInfoModel.getBiometricdeviceNc());
            Log.e("nc", nc);
            if (nc3.equalsIgnoreCase("null")) {
                generalObj.put("biometricdeviceNc", "0");
            } else {
                generalObj.put("biometricdeviceNc", generalInfoModel.getBiometricdeviceNc());
            }


            generalObj.put("itiSignageonaProperHardBoard", TextUtils.isEmpty(generalInfoModel.getItiSignage()) ? "" : generalInfoModel.getItiSignage());
            generalObj.put("itiSignageonaProperHardBoard_Remarks", TextUtils.isEmpty(generalInfoModel.getItiSignageRemarks()) ? "" : generalInfoModel.getItiSignageRemarks());

            String nc2 = String.valueOf(generalInfoModel.getItiSignageNc());
            Log.e("nc", nc);
            if (nc2.equalsIgnoreCase("null")) {
                generalObj.put("itiSignageonaProperHardBoard_Nc", "0");
            } else {
                generalObj.put("itiSignageonaProperHardBoard_Nc", generalInfoModel.getItiSignageNc());
            }

            generalObj.put("doestheITINameConsistOfBharat", TextUtils.isEmpty(generalInfoModel.getItiBharat()) ? "" : generalInfoModel.getItiBharat());
            generalObj.put("doestheITINameConsistOfBharat_Remarks", TextUtils.isEmpty(generalInfoModel.getItiBharatRemarks()) ? "" : generalInfoModel.getItiBharatRemarks());
            generalObj.put("doestheITINameConsistOfBharat_Nc", generalInfoModel.getItiBharatNc());
            generalObj.put("inst_noOfTechnicalStaffAvailable", TextUtils.isEmpty(generalInfoModel.getTechnical()) ? "" : generalInfoModel.getTechnical());
            generalObj.put("inst_noOfTechnicalStaffAvailable_Remarks", TextUtils.isEmpty(generalInfoModel.getTechnicalRemarks()) ? "" : generalInfoModel.getTechnicalRemarks());
            String nc5 = String.valueOf(generalInfoModel.getTechnicalNc());
            Log.e("nc", nc);
            if (nc5.equalsIgnoreCase("null")) {
                generalObj.put("inst_noOfTechnicalStaffAvailable_Nc", "0");
            } else {
                generalObj.put("inst_noOfTechnicalStaffAvailable_Nc", generalInfoModel.getTechnicalNc());
            }

            generalObj.put("inst_noOfClassroomsAvailable", TextUtils.isEmpty(generalInfoModel.getClassroom()) ? "" : generalInfoModel.getClassroom());
            generalObj.put("inst_noOfClassroomsAvailable_Remarks", TextUtils.isEmpty(generalInfoModel.getClassroomRemarks()) ? "" : generalInfoModel.getClassroomRemarks());
            String nc4 = String.valueOf(generalInfoModel.getClassroomNc());
            Log.e("nc", nc);
            if (nc4.equalsIgnoreCase("null")) {
                generalObj.put("inst_noOfClassroomsAvailable_Nc", "0");
            } else {
                generalObj.put("inst_noOfClassroomsAvailable_Nc", generalInfoModel.getClassroomNc());
            }


            generalObj.put("doesItihaveCombinedWorkshop", TextUtils.isEmpty(generalInfoModel.getCombined()) ? "" : generalInfoModel.getCombined());
            generalObj.put("doesItihaveCombinedWorkshop_Remarks", TextUtils.isEmpty(generalInfoModel.getCombinedRemarks()) ? "" : generalInfoModel.getCombinedRemarks());
            generalObj.put("doesItihaveCombinedWorkshop_Nc", generalInfoModel.getCombinedNc());
            generalObj.put("istheDemarcatedByYellowLine", TextUtils.isEmpty(generalInfoModel.getDemarcated()) ? "" : generalInfoModel.getDemarcated());
            generalObj.put("istheDemarcatedByYellowLine_Remarks", TextUtils.isEmpty(generalInfoModel.getDemarcatedRemarks()) ? "" : generalInfoModel.getDemarcatedRemarks());
            generalObj.put("istheDemarcatedByYellowLine_Nc", generalInfoModel.getDemarcatedNc());
            generalObj.put("isEngineeringDrawingRequired", TextUtils.isEmpty(generalInfoModel.getRequired()) ? "" : generalInfoModel.getRequired());
            generalObj.put("isEngineeringDrawingRequired_Remarks", TextUtils.isEmpty(generalInfoModel.getRequiredRemarks()) ? "" : generalInfoModel.getRequiredRemarks());
            generalObj.put("isEngineeringDrawingRequired_Nc", generalInfoModel.getRequiredNC());
            generalObj.put("isEngineeringDrawingAvailable", TextUtils.isEmpty(generalInfoModel.getAvailable()) ? "" : generalInfoModel.getAvailable());
            generalObj.put("isEngineeringDrawingAvailable_Remarks", TextUtils.isEmpty(generalInfoModel.getAvailableRemarks()) ? "" : generalInfoModel.getAvailableRemarks());
            String nc6 = String.valueOf(generalInfoModel.getAvailableNC());
            Log.e("nc", nc);
            if (nc6.equalsIgnoreCase("null")) {
                generalObj.put("isEngineeringDrawingAvailable_Nc", "0");
            } else {
                generalObj.put("isEngineeringDrawingAvailable_Nc", generalInfoModel.getAvailableNC());
            }


            array.put(generalObj);

            generalInfoObj.put("generalinfo", generalObj);
            generalInfoObj.put("yearwiseCollegeId", generalInfoModel.getYearWiseCollegeid());
            payloadObj.put("payload", generalInfoObj);

            result.put("result", payloadObj);
            Log.e("result", result.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public JSONObject getTrustInfoSyncData(EquipmentInfo trustGeneralInfo) {
        JSONObject result = new JSONObject();
        JSONObject payloadObj = new JSONObject();
        JSONObject trustInfoObj = new JSONObject();
        JSONObject trustObj = new JSONObject();

        JSONArray array = new JSONArray();

        try {
            trustObj.put("refId", trustGeneralInfo.getRefId());
            trustObj.put("instituteOwned", TextUtils.isEmpty(trustGeneralInfo.getInstituteOwned()) ? "" : trustGeneralInfo.getInstituteOwned());
            trustObj.put("instituteOwnedRemrks", TextUtils.isEmpty(trustGeneralInfo.getInstituteOwnedRemrks()) ? "" : trustGeneralInfo.getInstituteOwnedRemrks());
            trustObj.put("instituteOwnedNc", trustGeneralInfo.getInstituteOwnedNc());
            trustObj.put("isRegistered", TextUtils.isEmpty(trustGeneralInfo.getIsRegistered()) ? "" : trustGeneralInfo.getIsRegistered());
            trustObj.put("isregisteredRemarks", TextUtils.isEmpty(trustGeneralInfo.getIsregisteredRemarks()) ? "" : trustGeneralInfo.getIsregisteredRemarks());
            trustObj.put("isregisteredNc", trustGeneralInfo.getIsregisteredNc());
            trustObj.put("registration", TextUtils.isEmpty(trustGeneralInfo.getRegistration()) ? "" : trustGeneralInfo.getRegistration());
            trustObj.put("registrationRemarks", TextUtils.isEmpty(trustGeneralInfo.getRegistrationRemarks()) ? "" : trustGeneralInfo.getRegistrationRemarks());
            trustObj.put("registrationNc", trustGeneralInfo.getRegistrationNc());
            trustObj.put("registrationNo", TextUtils.isEmpty(trustGeneralInfo.getRegistrationNo()) ? "" : trustGeneralInfo.getRegistrationNo());
            trustObj.put("registrationnoRemarks", TextUtils.isEmpty(trustGeneralInfo.getRegistrationnoRemarks()) ? "" : trustGeneralInfo.getRegistrationnoRemarks());
            trustObj.put("registrationnoNc", trustGeneralInfo.getRegistrationnoNc());
            trustObj.put("trustName", TextUtils.isEmpty(trustGeneralInfo.getTrustName()) ? "" : trustGeneralInfo.getTrustName());
            trustObj.put("trustnameRemarks", TextUtils.isEmpty(trustGeneralInfo.getTrustnameRemarks()) ? "" : trustGeneralInfo.getTrustnameRemarks());
            trustObj.put("trustnameNc", trustGeneralInfo.getTrustnameNc());
            trustObj.put("registrationDate", TextUtils.isEmpty(trustGeneralInfo.getRegistrationDate()) ? "" : trustGeneralInfo.getRegistrationDate());
            trustObj.put("registrationdateRemarks", TextUtils.isEmpty(trustGeneralInfo.getRegistrationdateRemarks()) ? "" : trustGeneralInfo.getRegistrationdateRemarks());
            String nc1 = String.valueOf(trustGeneralInfo.getRegistrationdateNc());
            if (nc1.equalsIgnoreCase("null")) {
                trustObj.put("registrationdateNc", "0");
            } else {
                trustObj.put("registrationdateNc", trustGeneralInfo.getRegistrationdateNc());
            }

            trustObj.put("trustValidity", TextUtils.isEmpty(trustGeneralInfo.getTrustValidity()) ? "" : trustGeneralInfo.getTrustValidity());
            trustObj.put("trustvalidityRemarks", TextUtils.isEmpty(trustGeneralInfo.getTrustvalidityRemarks()) ? "" : trustGeneralInfo.getTrustvalidityRemarks());
            trustObj.put("trustvalidityNc", trustGeneralInfo.getTrustvalidityNc());
            trustObj.put("panNumber", TextUtils.isEmpty(trustGeneralInfo.getPanNumber()) ? "" : trustGeneralInfo.getPanNumber());
            trustObj.put("pannumberRemarks", TextUtils.isEmpty(trustGeneralInfo.getPannumberRemarks()) ? "" : trustGeneralInfo.getPannumberRemarks());
            trustObj.put("pannumberNc", trustGeneralInfo.getPannumberNc());
            String nc = String.valueOf(trustGeneralInfo.getCommonNc());
            if (nc.equalsIgnoreCase("null")) {
                trustObj.put("commonNc", "0");
            } else {
                trustObj.put("commonNc", trustGeneralInfo.getCommonNc());
            }

            trustObj.put("doestheTrustSocietyCompany", TextUtils.isEmpty(trustGeneralInfo.getExperience()) ? "" : trustGeneralInfo.getExperience());
            trustObj.put("doestheTrustSocietyCompany_Remarks", TextUtils.isEmpty(trustGeneralInfo.getExperienceRemarks()) ? "" : trustGeneralInfo.getExperienceRemarks());
            trustObj.put("doestheTrustSocietyCompany_Nc", trustGeneralInfo.getExperienceNc());
            trustObj.put("typeofEducationInstitute", TextUtils.isEmpty(trustGeneralInfo.getType()) ? "" : trustGeneralInfo.getType());
            trustObj.put("typeofEducationInstitute_Remarks", TextUtils.isEmpty(trustGeneralInfo.getTypeRemarks()) ? "" : trustGeneralInfo.getTypeRemarks());
            trustObj.put("typeofEducationInstitute_Nc", trustGeneralInfo.getTypeNc());
            trustObj.put("remarks", TextUtils.isEmpty(trustGeneralInfo.getRemarks()) ? "" : trustGeneralInfo.getRemarks());
            trustObj.put("remarks_Remarks", TextUtils.isEmpty(trustGeneralInfo.getRemarks_remarks()) ? "" : trustGeneralInfo.getRemarks_remarks());
            trustObj.put("remarks_Nc", trustGeneralInfo.getRemarksNC());


            array.put(trustObj);

            trustInfoObj.put("trust", trustObj);
            trustInfoObj.put("yearwiseCollegeId", trustGeneralInfo.getYearWiseCollegeid());
            payloadObj.put("payload", trustInfoObj);

            result.put("result", payloadObj);
            Log.e("result", result.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public JSONObject getOrganisationInfoSyncData(MaterialInfo materialInfo) {

        JSONObject result = new JSONObject();
        JSONObject payloadObj = new JSONObject();
        JSONObject orgnisationInfoObj = new JSONObject();
        JSONObject orgObj = new JSONObject();

        JSONArray array = new JSONArray();

        try {
            orgObj.put("refId", materialInfo.getRefId());
            orgObj.put("organizationName", TextUtils.isEmpty(materialInfo.getOrganizationName()) ? "" : materialInfo.getOrganizationName());
            orgObj.put("organizationnameRemarks", TextUtils.isEmpty(materialInfo.getOrganizationnameRemarks()) ? "" : materialInfo.getOrganizationnameRemarks());
            orgObj.put("organizationnameNc", materialInfo.getOrganizationnameNc());
            orgObj.put("authorizedPerson", TextUtils.isEmpty(materialInfo.getAuthorizedPerson()) ? "" : materialInfo.getAuthorizedPerson());
            orgObj.put("authorizedpersonRemarks", TextUtils.isEmpty(materialInfo.getAuthorizedpersonRemarks()) ? "" : materialInfo.getAuthorizedpersonRemarks());
            orgObj.put("authorizedpersonNc", materialInfo.getAuthorizedpersonNc());
            orgObj.put("authorizedRole", TextUtils.isEmpty(materialInfo.getAuthorizedRole()) ? "" : materialInfo.getAuthorizedRole());
            orgObj.put("authorizedroleRemarks", TextUtils.isEmpty(materialInfo.getAuthorizedroleRemarks()) ? "" : materialInfo.getAuthorizedroleRemarks());
            orgObj.put("authorizedroleNc", materialInfo.getAuthorizedroleNc());
            orgObj.put("authorizedpersonUid", TextUtils.isEmpty(materialInfo.getAuthorizedpersonUid()) ? "" : materialInfo.getAuthorizedpersonUid());
            orgObj.put("authorizedpersonuidRemark", TextUtils.isEmpty(materialInfo.getAuthorizedpersonuidRemark()) ? "" : materialInfo.getAuthorizedpersonuidRemark());
            orgObj.put("authorizedpersonuidNc", materialInfo.getAuthorizedpersonuidNc());
            orgObj.put("orgMobileno", TextUtils.isEmpty(materialInfo.getOrgMobileno()) ? "" : materialInfo.getOrgMobileno());
            orgObj.put("orgmobilenoRemarks", TextUtils.isEmpty(materialInfo.getOrgmobilenoRemarks()) ? "" : materialInfo.getOrgmobilenoRemarks());
            orgObj.put("orgmobilenoNc", materialInfo.getOrgmobilenoNc());
            orgObj.put("orgEmail", TextUtils.isEmpty(materialInfo.getOrgEmail()) ? "" : materialInfo.getOrgEmail());
            orgObj.put("orgemailRemarks", TextUtils.isEmpty(materialInfo.getOrgemailRemarks()) ? "" : materialInfo.getOrgemailRemarks());
            orgObj.put("orgemailNc", materialInfo.getOrgemailNc());
            orgObj.put("orgWebsite", TextUtils.isEmpty(materialInfo.getOrgWebsite()) ? "" : materialInfo.getOrgWebsite());
            orgObj.put("orgwebsiteRemarks", TextUtils.isEmpty(materialInfo.getOrgwebsiteRemarks()) ? "" : materialInfo.getOrgwebsiteRemarks());
            String nc = String.valueOf(materialInfo.getOrgwebsiteNc());
            if (nc.equalsIgnoreCase("null")) {
                orgObj.put("orgwebsiteNc", "0");
            } else {
                orgObj.put("orgwebsiteNc", materialInfo.getOrgwebsiteNc());
            }

            orgObj.put("orgTelephoneno", TextUtils.isEmpty(materialInfo.getOrgTelephoneno()) ? "" : materialInfo.getOrgTelephoneno());
            orgObj.put("orgtelephonenoRemarks", TextUtils.isEmpty(materialInfo.getOrgtelephonenoRemarks()) ? "" : materialInfo.getOrgtelephonenoRemarks());
            orgObj.put("orgtelephonenoNc", materialInfo.getOrgtelephonenoNc());
            orgObj.put("orgFaxno", TextUtils.isEmpty(materialInfo.getOrgFaxno()) ? "" : materialInfo.getOrgFaxno());
            orgObj.put("orgfaxnoRemarks", TextUtils.isEmpty(materialInfo.getOrgfaxnoRemarks()) ? "" : materialInfo.getOrgfaxnoRemarks());
            orgObj.put("orgfaxnoNc", materialInfo.getOrgfaxnoNc());
            orgObj.put("orgPostaladdress", TextUtils.isEmpty(materialInfo.getOrgPostaladdress()) ? "" : materialInfo.getOrgPostaladdress());
            orgObj.put("orgpostaladdressRemarks", TextUtils.isEmpty(materialInfo.getOrgpostaladdressRmarks()) ? "" : materialInfo.getOrgpostaladdressRmarks());
            orgObj.put("orgpostaladdressNc", materialInfo.getOrgpostaladdressNc());
            orgObj.put("stateName", TextUtils.isEmpty(materialInfo.getStateName()) ? "" : materialInfo.getStateName());
            orgObj.put("statenameRemarks", TextUtils.isEmpty(materialInfo.getStatenameRemarks()) ? "" : materialInfo.getStatenameRemarks());
            orgObj.put("statenameNc", materialInfo.getStatenameNc());
            orgObj.put("districtName", TextUtils.isEmpty(materialInfo.getDistrictName()) ? "" : materialInfo.getDistrictName());
            orgObj.put("districtnameRemarks", TextUtils.isEmpty(materialInfo.getDistrictnameRemarks()) ? "" : materialInfo.getDistrictnameRemarks());
            orgObj.put("districtnameNc", materialInfo.getDistrictnameNc());
            orgObj.put("mandalName", TextUtils.isEmpty(materialInfo.getMandalName()) ? "" : materialInfo.getMandalName());
            orgObj.put("mandalnameRemarks", TextUtils.isEmpty(materialInfo.getMandalnameRemarks()) ? "" : materialInfo.getMandalnameRemarks());
            orgObj.put("mandalnameNc", materialInfo.getMandalnameNc());
            orgObj.put("orgPincode", TextUtils.isEmpty(materialInfo.getOrgPincode()) ? "" : materialInfo.getOrgPincode());
            orgObj.put("orgpincodeRemarks", TextUtils.isEmpty(materialInfo.getOrgpincodeRemarks()) ? "" : materialInfo.getOrgpincodeRemarks());
            orgObj.put("orgpincodeNc", materialInfo.getOrgpincodeNc());
            orgObj.put("orgLandmark", TextUtils.isEmpty(materialInfo.getOrgLandmark()) ? "" : materialInfo.getOrgLandmark());
            orgObj.put("orglandmarkRemarks", TextUtils.isEmpty(materialInfo.getOrglandmarkRemarks()) ? "" : materialInfo.getOrglandmarkRemarks());
            orgObj.put("orglandmarkNc", materialInfo.getOrglandmarkNc());
            orgObj.put("isAnyOtherNCVTAffiliatedITI", TextUtils.isEmpty(materialInfo.getAffilated()) ? "" : materialInfo.getAffilated());
            orgObj.put("isAnyOtherNCVTAffiliatedITI_Remarks", TextUtils.isEmpty(materialInfo.getAffilatedRemarks()) ? "" : materialInfo.getAffilatedRemarks());
            orgObj.put("isAnyOtherNCVTAffiliatedITI_Nc", materialInfo.getAffilatedNC());
            orgObj.put("dgetAffiliationNo", TextUtils.isEmpty(materialInfo.getAffilation()) ? "" : materialInfo.getAffilation());
            orgObj.put("dgetReferenceNumber", TextUtils.isEmpty(materialInfo.getReference()) ? "" : materialInfo.getReference());
            orgObj.put("dgetAffiliationNo_Nc", materialInfo.getAffilationNC());
            orgObj.put("dgetAffiliationNo_Remarks", TextUtils.isEmpty(materialInfo.getAffilationRemarks()) ? "" : materialInfo.getAffilationRemarks());
            orgObj.put("dgetReferenceNumber_Remarks", TextUtils.isEmpty(materialInfo.getReferenceRemarks()) ? "" : materialInfo.getReferenceRemarks());
            orgObj.put("dgetReferenceNumber_Nc", materialInfo.getReferenceNC());
            orgObj.put("miscode", TextUtils.isEmpty(materialInfo.getCode()) ? "" : materialInfo.getCode());
            orgObj.put("miscode_Remarks", TextUtils.isEmpty(materialInfo.getCodeRemarks()) ? "" : materialInfo.getCodeRemarks());
            orgObj.put("miscode_Nc", materialInfo.getCodeNC());
            array.put(orgObj);

            orgnisationInfoObj.put("OrganizationDetails", orgObj);
            orgnisationInfoObj.put("yearwiseCollegeId", materialInfo.getYearwisecollegeid());
            payloadObj.put("payload", orgnisationInfoObj);

            result.put("result", payloadObj);
            Log.e("result", result.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    public JSONObject getTechnicalInfoSyncData(List<TechincalInfo> technicalInfolist) {

        JSONObject result = new JSONObject();
        JSONObject payloadObj = new JSONObject();
        JSONObject techInfoObj = new JSONObject();

        JSONArray techStaffarray = new JSONArray();

        try {
            for (int i = 0; i < technicalInfolist.size(); i++) {

                JSONObject techObj = new JSONObject();
                techObj.put("refId", technicalInfolist.get(i).getRefId());
                techObj.put("designation", TextUtils.isEmpty(technicalInfolist.get(i).getDesignation()) ? "" : technicalInfolist.get(i).getDesignation());
                techObj.put("designationRemarks", TextUtils.isEmpty(technicalInfolist.get(i).getDesignationRemarks()) ? "" : technicalInfolist.get(i).getDesignationRemarks());
                techObj.put("designationNc", technicalInfolist.get(i).getDesignationNc());
                techObj.put("name", TextUtils.isEmpty(technicalInfolist.get(i).getName()) ? "" : technicalInfolist.get(i).getName());
                techObj.put("nameRemarks", TextUtils.isEmpty(technicalInfolist.get(i).getNameRemarks()) ? "" : technicalInfolist.get(i).getNameRemarks());
                techObj.put("nameNc", technicalInfolist.get(i).getNameNc());
                techObj.put("fatherName", TextUtils.isEmpty(technicalInfolist.get(i).getFatherName()) ? "" : technicalInfolist.get(i).getFatherName());
                techObj.put("fathernameRemarks", TextUtils.isEmpty(technicalInfolist.get(i).getFathernameRemarks()) ? "" : technicalInfolist.get(i).getFathernameRemarks());
                techObj.put("fathernameNc", technicalInfolist.get(i).getFathernameNc());
                techObj.put("dob", TextUtils.isEmpty(technicalInfolist.get(i).getDob()) ? "" : technicalInfolist.get(i).getDob());
                techObj.put("dobRemarks", TextUtils.isEmpty(technicalInfolist.get(i).getDobRemarks()) ? "" : technicalInfolist.get(i).getDobRemarks());
                techObj.put("dobNc", technicalInfolist.get(i).getDobNc());
                techObj.put("joinDate", TextUtils.isEmpty(technicalInfolist.get(i).getJoinDate()) ? "" : technicalInfolist.get(i).getJoinDate());
                techObj.put("joindateRemarks", TextUtils.isEmpty(technicalInfolist.get(i).getJoindateRemarks()) ? "" : technicalInfolist.get(i).getJoindateRemarks());
                techObj.put("joindateNc", technicalInfolist.get(i).getJoindateNc());
                techObj.put("qualification", TextUtils.isEmpty(technicalInfolist.get(i).getQualification()) ? "" : technicalInfolist.get(i).getQualification());
                techObj.put("qualificationRemarks", TextUtils.isEmpty(technicalInfolist.get(i).getQualificationRemarks()) ? "" : technicalInfolist.get(i).getQualificationRemarks());
                techObj.put("qualificationNc", technicalInfolist.get(i).getQualificationNc());
                techObj.put("stream", TextUtils.isEmpty(technicalInfolist.get(i).getStream()) ? "" : technicalInfolist.get(i).getStream());
                techObj.put("streamRemarks", TextUtils.isEmpty(technicalInfolist.get(i).getStreamRemarks()) ? "" : technicalInfolist.get(i).getStreamRemarks());
                techObj.put("streamNc", technicalInfolist.get(i).getStreamNc());
                techObj.put("passingYear", TextUtils.isEmpty(technicalInfolist.get(i).getPassingYear()) ? "" : technicalInfolist.get(i).getPassingYear());
                techObj.put("passingyearRemarks", TextUtils.isEmpty(technicalInfolist.get(i).getPassingyearRemark()) ? "" : technicalInfolist.get(i).getPassingyearRemark());
                techObj.put("passingyearNc", technicalInfolist.get(i).getPassingyearNc());
                techObj.put("totalYoe", TextUtils.isEmpty(technicalInfolist.get(i).getTotalYoe()) ? "" : technicalInfolist.get(i).getTotalYoe());
                techObj.put("totalyoeRemarks", TextUtils.isEmpty(technicalInfolist.get(i).getTotalyoeRemarks()) ? "" : technicalInfolist.get(i).getTotalyoeRemarks());
                techObj.put("totalyoeNc", technicalInfolist.get(i).getTotalyoeNc());
                techObj.put("account", TextUtils.isEmpty(technicalInfolist.get(i).getAccount()) ? "" : technicalInfolist.get(i).getAccount());
                techObj.put("accountRemarks", TextUtils.isEmpty(technicalInfolist.get(i).getAccountRemarks()) ? "" : technicalInfolist.get(i).getAccountRemarks());
                techObj.put("accountNc", technicalInfolist.get(i).getAccountNc());
                techObj.put("bankName", TextUtils.isEmpty(technicalInfolist.get(i).getBankName()) ? "" : technicalInfolist.get(i).getBankName());
                techObj.put("banknameRemarks", TextUtils.isEmpty(technicalInfolist.get(i).getBanknameRemarks()) ? "" : technicalInfolist.get(i).getBanknameRemarks());
                techObj.put("banknameNc", technicalInfolist.get(i).getBanknameNc());
                techObj.put("branchName", TextUtils.isEmpty(technicalInfolist.get(i).getBranchName()) ? "" : technicalInfolist.get(i).getBranchName());
                techObj.put("branchnameRemarks", TextUtils.isEmpty(technicalInfolist.get(i).getBranchnameRemarks()) ? "" : technicalInfolist.get(i).getBranchnameRemarks());
                techObj.put("branchnameNc", technicalInfolist.get(i).getBranchnameNc());
                techObj.put("adharNo", TextUtils.isEmpty(technicalInfolist.get(i).getAdharNo()) ? "" : technicalInfolist.get(i).getAdharNo());
                techObj.put("adharnoRemarks", TextUtils.isEmpty(technicalInfolist.get(i).getAdharnoRemarks()) ? "" : technicalInfolist.get(i).getAdharnoRemarks());
                techObj.put("adharnoNc", technicalInfolist.get(i).getAdharnoNc());
                techObj.put("salary", TextUtils.isEmpty(technicalInfolist.get(i).getSalary()) ? "" : technicalInfolist.get(i).getSalary());
                techObj.put("salaryRemarks", TextUtils.isEmpty(technicalInfolist.get(i).getSalaryRemarks()) ? "" : technicalInfolist.get(i).getSalaryRemarks());
                techObj.put("salaryNc", technicalInfolist.get(i).getSalaryNC());
                String nc = String.valueOf(technicalInfolist.get(i).getCommonNc());
                if (nc.equalsIgnoreCase("null")) {
                    techObj.put("commonNc", "0");
                } else {
                    techObj.put("commonNc", technicalInfolist.get(i).getCommonNc());
                }

                techObj.put("panCardNo", TextUtils.isEmpty(technicalInfolist.get(i).getPan()) ? "" : technicalInfolist.get(i).getPan());
                techObj.put("panCardNo_Remarks", TextUtils.isEmpty(technicalInfolist.get(i).getPanRemarks()) ? "" : technicalInfolist.get(i).getPanRemarks());
                techObj.put("panCardNo_Nc", technicalInfolist.get(i).getPanNC());
                techObj.put("remarks", TextUtils.isEmpty(technicalInfolist.get(i).getRemarks()) ? "" : technicalInfolist.get(i).getRemarks());
                techObj.put("remarks_Remarks", TextUtils.isEmpty(technicalInfolist.get(i).getRemarksRemarks()) ? "" : technicalInfolist.get(i).getRemarksRemarks());
                techObj.put("remarks_Nc", technicalInfolist.get(i).getRemarksNC());

                techStaffarray.put(techObj);
            }

            techInfoObj.put("technicalstaff", techStaffarray);
            techInfoObj.put("yearwiseCollegeId", technicalInfolist.get(0).getYearwisecollegeid());
            payloadObj.put("payload", techInfoObj);

            result.put("result", payloadObj);
            Log.e("result", result.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    public JSONObject getInstructorInfoSyncData(List<InstructorInfo> instructorInfolist) {

        JSONObject result = new JSONObject();
        JSONObject payloadObj = new JSONObject();
        JSONObject insInfoObj = new JSONObject();

        JSONArray insStaffarray = new JSONArray();

        try {
            for (int i = 0; i < instructorInfolist.size(); i++) {

                JSONObject insObj = new JSONObject();
                insObj.put("refId", instructorInfolist.get(i).getRefId());
                insObj.put("tradeId", instructorInfolist.get(i).getTradeId());
                insObj.put("tradename", instructorInfolist.get(i).getTradeName());
                insObj.put("tradeRemarks", instructorInfolist.get(i).getTradeNameRemarks());
                insObj.put("instructor", TextUtils.isEmpty(instructorInfolist.get(i).getInstructor()) ? "" : instructorInfolist.get(i).getInstructor());
                insObj.put("instructorRemarks", TextUtils.isEmpty(instructorInfolist.get(i).getInstructorRemarks()) ? "" : instructorInfolist.get(i).getInstructorRemarks());
                insObj.put("instructorNc", instructorInfolist.get(i).getInstructorNc());
                insObj.put("name", TextUtils.isEmpty(instructorInfolist.get(i).getName()) ? "" : instructorInfolist.get(i).getName());
                insObj.put("nameRemarks", TextUtils.isEmpty(instructorInfolist.get(i).getNameRemarks()) ? "" : instructorInfolist.get(i).getNameRemarks());
                insObj.put("nameNc", instructorInfolist.get(i).getNameNc());
                insObj.put("fathername", TextUtils.isEmpty(instructorInfolist.get(i).getFathername()) ? "" : instructorInfolist.get(i).getFathername());
                insObj.put("fathernameRemarks", TextUtils.isEmpty(instructorInfolist.get(i).getFathernameRemarks()) ? "" : instructorInfolist.get(i).getFathernameRemarks());
                insObj.put("fathernameNc", instructorInfolist.get(i).getFathernameNc());
                insObj.put("dateofbirth", TextUtils.isEmpty(instructorInfolist.get(i).getDateofbirth()) ? "" : instructorInfolist.get(i).getDateofbirth());
                insObj.put("dateofbirthRemarks", TextUtils.isEmpty(instructorInfolist.get(i).getDateofbirthRemarks()) ? "" : instructorInfolist.get(i).getDateofbirthRemarks());
                insObj.put("dateofbirthNc", instructorInfolist.get(i).getDateofbirthNc());
                insObj.put("joiningdate", TextUtils.isEmpty(instructorInfolist.get(i).getJoiningdate()) ? "" : instructorInfolist.get(i).getJoiningdate());
                insObj.put("joiningdateRemarks", TextUtils.isEmpty(instructorInfolist.get(i).getJoiningdateRemarks()) ? "" : instructorInfolist.get(i).getJoiningdateRemarks());
                insObj.put("joiningdateNc", instructorInfolist.get(i).getJoiningdateNc());
                insObj.put("qualification", TextUtils.isEmpty(instructorInfolist.get(i).getQualification()) ? "" : instructorInfolist.get(i).getQualification());
                insObj.put("qualificationRemarks", TextUtils.isEmpty(instructorInfolist.get(i).getQualificationRemarks()) ? "" : instructorInfolist.get(i).getQualificationRemarks());
                String nc = String.valueOf(instructorInfolist.get(i).getQualificationNc());
                if (nc.equalsIgnoreCase("null")) {
                    insObj.put("qualificationNc", "0");
                } else {
                    insObj.put("qualificationNc", instructorInfolist.get(i).getQualificationNc());
                }
                //insObj.put("qualificationNc", instructorInfolist.get(i).getQualificationNc());
                insObj.put("stream", TextUtils.isEmpty(instructorInfolist.get(i).getStream()) ? "" : instructorInfolist.get(i).getStream());
                insObj.put("streamRemarks", TextUtils.isEmpty(instructorInfolist.get(i).getStreamRemarks()) ? "" : instructorInfolist.get(i).getStreamRemarks());
                insObj.put("streamNc", instructorInfolist.get(i).getStreamNc());
                insObj.put("passingyear", TextUtils.isEmpty(instructorInfolist.get(i).getPassingyear()) ? "" : instructorInfolist.get(i).getPassingyear());
                insObj.put("passingyearRemarks", TextUtils.isEmpty(instructorInfolist.get(i).getPassingyearRemarks()) ? "" : instructorInfolist.get(i).getPassingyearRemarks());
                insObj.put("passingyearNc", instructorInfolist.get(i).getPassingyearNc());
                insObj.put("totalexp", TextUtils.isEmpty(instructorInfolist.get(i).getTotalexp()) ? "" : instructorInfolist.get(i).getTotalexp());
                insObj.put("totalexpRemarks", TextUtils.isEmpty(instructorInfolist.get(i).getTotalexpRemarks()) ? "" : instructorInfolist.get(i).getTotalexpRemarks());
                String nc2 = String.valueOf(instructorInfolist.get(i).getTotalexpNc());
                if (nc2.equalsIgnoreCase("null")) {
                    insObj.put("totalexpNc", "0");
                } else {
                    insObj.put("totalexpNc", instructorInfolist.get(i).getTotalexpNc());
                }
                //insObj.put("totalexpNc", instructorInfolist.get(i).getTotalexpNc());
                insObj.put("accountno", TextUtils.isEmpty(instructorInfolist.get(i).getAccountno()) ? "" : instructorInfolist.get(i).getAccountno());
                insObj.put("accountnoRemarks", TextUtils.isEmpty(instructorInfolist.get(i).getAccountnoRemarks()) ? "" : instructorInfolist.get(i).getAccountnoRemarks());
                insObj.put("accountnoNc", instructorInfolist.get(i).getAccountnoNc());
                insObj.put("bankname", TextUtils.isEmpty(instructorInfolist.get(i).getBankname()) ? "" : instructorInfolist.get(i).getBankname());
                insObj.put("banknameRemarks", TextUtils.isEmpty(instructorInfolist.get(i).getBanknameRemarks()) ? "" : instructorInfolist.get(i).getBanknameRemarks());
                insObj.put("banknameNc", instructorInfolist.get(i).getBanknameNc());
                insObj.put("branchname", TextUtils.isEmpty(instructorInfolist.get(i).getBranchname()) ? "" : instructorInfolist.get(i).getBranchname());
                insObj.put("branchnameRemarks", TextUtils.isEmpty(instructorInfolist.get(i).getBranchnameRemarks()) ? "" : instructorInfolist.get(i).getBranchnameRemarks());
                insObj.put("branchnameNc", instructorInfolist.get(i).getBranchnameNc());
                insObj.put("aadharno", TextUtils.isEmpty(instructorInfolist.get(i).getAadharno()) ? "" : instructorInfolist.get(i).getAadharno());
                insObj.put("aadharnoRemarks", TextUtils.isEmpty(instructorInfolist.get(i).getAadharnoRemarks()) ? "" : instructorInfolist.get(i).getAadharnoRemarks());
                insObj.put("aadharnoNc", instructorInfolist.get(i).getAadharnoNc());
                insObj.put("salary", TextUtils.isEmpty(instructorInfolist.get(i).getSalary()) ? "" : instructorInfolist.get(i).getSalary());
                insObj.put("salaryRemarks", TextUtils.isEmpty(instructorInfolist.get(i).getSalaryRemarks()) ? "" : instructorInfolist.get(i).getSalaryRemarks());
                insObj.put("salaryNc", instructorInfolist.get(i).getSalaryNC());
                String nc1 = String.valueOf(instructorInfolist.get(i).getCommonNc());
                if (nc1.equalsIgnoreCase("null")) {
                    insObj.put("commonNc", "0");
                } else {
                    insObj.put("commonNc", instructorInfolist.get(i).getCommonNc());
                }

                insObj.put("remarks", TextUtils.isEmpty(instructorInfolist.get(i).getRemarks()) ? "" : instructorInfolist.get(i).getRemarks());
                insObj.put("remarks_Remarks", TextUtils.isEmpty(instructorInfolist.get(i).getRemarksRemarks()) ? "" : instructorInfolist.get(i).getRemarksRemarks());
                insObj.put("remarks_Nc", instructorInfolist.get(i).getRemarksNC());

                insStaffarray.put(insObj);
            }

            insInfoObj.put("instructorstaff", insStaffarray);
            insInfoObj.put("yearwiseCollegeId", instructorInfolist.get(0).getYearwisecollegeid());
            payloadObj.put("payload", insInfoObj);

            result.put("result", payloadObj);
            Log.e("result", result.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public JSONObject getPremisesInfoSyncData(Staffing landandBuildingInfo) {

        JSONObject result = new JSONObject();
        JSONObject payloadObj = new JSONObject();
        JSONObject premisesInfoObj = new JSONObject();
        JSONObject premisesObj = new JSONObject();

        JSONArray array = new JSONArray();

        try {
            premisesObj.put("refid", landandBuildingInfo.getRefid());
            premisesObj.put("changeOfPremises", TextUtils.isEmpty(landandBuildingInfo.getChangeOfPremises()) ? "" : landandBuildingInfo.getChangeOfPremises());
            premisesObj.put("changeOfPremisesRemarks", TextUtils.isEmpty(landandBuildingInfo.getChangeOfPremisesRemarks()) ? "" : landandBuildingInfo.getChangeOfPremisesRemarks());
            premisesObj.put("changeOfPremisesNc", landandBuildingInfo.getChangeOfPremisesNc());
            premisesObj.put("fromPostalAddress", TextUtils.isEmpty(landandBuildingInfo.getFromPostalAddress()) ? "" : landandBuildingInfo.getFromPostalAddress());
            premisesObj.put("fromPostalAddressRemarks", TextUtils.isEmpty(landandBuildingInfo.getFromPostalAddressRemarks()) ? "" : landandBuildingInfo.getFromPostalAddressRemarks());
            premisesObj.put("fromPostalAddressNc", landandBuildingInfo.getFromPostalAddressNc());
            premisesObj.put("toPostalAddress", TextUtils.isEmpty(landandBuildingInfo.getToPostalAddress()) ? "" : landandBuildingInfo.getToPostalAddress());
            premisesObj.put("toPostalAddressRemarks", TextUtils.isEmpty(landandBuildingInfo.getToPostalAddressRemarks()) ? "" : landandBuildingInfo.getToPostalAddressRemarks());
            premisesObj.put("toPostalAddressNc", landandBuildingInfo.getToPostalAddressNc());
            premisesObj.put("fromLandmark", TextUtils.isEmpty(landandBuildingInfo.getFromLandmark()) ? "" : landandBuildingInfo.getFromLandmark());
            premisesObj.put("fromLandmarkRemarks", TextUtils.isEmpty(landandBuildingInfo.getFromLandmarkRemarks()) ? "" : landandBuildingInfo.getFromLandmarkRemarks());
            premisesObj.put("fromLandmarkNc", landandBuildingInfo.getFromLandmarkNc());
            premisesObj.put("tolandmark", TextUtils.isEmpty(landandBuildingInfo.getTolandmark()) ? "" : landandBuildingInfo.getTolandmark());
            premisesObj.put("tolandmarkRemarks", TextUtils.isEmpty(landandBuildingInfo.getTolandmarkRemarks()) ? "" : landandBuildingInfo.getTolandmarkRemarks());
            premisesObj.put("tolandmarkNc", landandBuildingInfo.getTolandmarkNc());
            premisesObj.put("fromState", TextUtils.isEmpty(landandBuildingInfo.getFromState()) ? "" : landandBuildingInfo.getFromState());
            premisesObj.put("fromStateRemarks", TextUtils.isEmpty(landandBuildingInfo.getFromStateRemarks()) ? "" : landandBuildingInfo.getFromStateRemarks());
            premisesObj.put("fromStateNc", landandBuildingInfo.getFromStateNc());
            premisesObj.put("toState", TextUtils.isEmpty(landandBuildingInfo.getToState()) ? "" : landandBuildingInfo.getToState());
            premisesObj.put("toStateRemarks", TextUtils.isEmpty(landandBuildingInfo.getToStateRemarks()) ? "" : landandBuildingInfo.getToStateRemarks());
            premisesObj.put("toStateNc", landandBuildingInfo.getToStateNc());
            premisesObj.put("fromDistrict", TextUtils.isEmpty(landandBuildingInfo.getFromDistrict()) ? "" : landandBuildingInfo.getFromDistrict());
            premisesObj.put("fromDistrictRemarks", TextUtils.isEmpty(landandBuildingInfo.getFromDistrictRemarks()) ? "" : landandBuildingInfo.getFromDistrictRemarks());
            premisesObj.put("fromDistrictNc", landandBuildingInfo.getFromDistrictNc());
            premisesObj.put("toDistrict", TextUtils.isEmpty(landandBuildingInfo.getToDistrict()) ? "" : landandBuildingInfo.getToDistrict());
            premisesObj.put("toDistrictRemarks", TextUtils.isEmpty(landandBuildingInfo.getToDistrictRemarks()) ? "" : landandBuildingInfo.getToDistrictRemarks());
            premisesObj.put("toDistrictNc", landandBuildingInfo.getToDistrictNc());
            premisesObj.put("fromTehsil", TextUtils.isEmpty(landandBuildingInfo.getFromTehsil()) ? "" : landandBuildingInfo.getFromTehsil());
            premisesObj.put("fromTehsilRemarks", TextUtils.isEmpty(landandBuildingInfo.getFromTehsilRemarks()) ? "" : landandBuildingInfo.getFromTehsilRemarks());
            premisesObj.put("fromTehsilNc", landandBuildingInfo.getFromTehsilNc());
            premisesObj.put("toTehsil", TextUtils.isEmpty(landandBuildingInfo.getToTehsil()) ? "" : landandBuildingInfo.getToTehsil());
            premisesObj.put("toTehsilRemarks", TextUtils.isEmpty(landandBuildingInfo.getToTehsilRemarks()) ? "" : landandBuildingInfo.getToTehsilRemarks());
            premisesObj.put("toTehsilNc", landandBuildingInfo.getToTehsilNc());
            premisesObj.put("fromBlock", TextUtils.isEmpty(landandBuildingInfo.getFromBlock()) ? "" : landandBuildingInfo.getFromBlock());
            premisesObj.put("fromBlockRemarks", TextUtils.isEmpty(landandBuildingInfo.getFromBlockRemarks()) ? "" : landandBuildingInfo.getFromBlockRemarks());
            premisesObj.put("fromBlockNc", landandBuildingInfo.getFromBlockNc());
            premisesObj.put("toBlock", TextUtils.isEmpty(landandBuildingInfo.getToBlock()) ? "" : landandBuildingInfo.getToBlock());
            premisesObj.put("toBlockRemarks", TextUtils.isEmpty(landandBuildingInfo.getToBlockRemarks()) ? "" : landandBuildingInfo.getToBlockRemarks());
            premisesObj.put("toBlockNc", landandBuildingInfo.getToBlockNc());
            premisesObj.put("fromPincode", TextUtils.isEmpty(landandBuildingInfo.getFromPincode()) ? "" : landandBuildingInfo.getFromPincode());
            premisesObj.put("fromPincodeRemarks", TextUtils.isEmpty(landandBuildingInfo.getFromPincodeRemarks()) ? "" : landandBuildingInfo.getFromPincodeRemarks());
            premisesObj.put("fromPincodeNc", landandBuildingInfo.getFromPincodeNc());
            premisesObj.put("toPincode", TextUtils.isEmpty(landandBuildingInfo.getToPincode()) ? "" : landandBuildingInfo.getToPincode());
            premisesObj.put("toPincodeRemarks", TextUtils.isEmpty(landandBuildingInfo.getToPincodeRemarks()) ? "" : landandBuildingInfo.getToPincodeRemarks());
            premisesObj.put("toPincodeNc", landandBuildingInfo.getToPincodeNc());
            premisesObj.put("fromtelno", TextUtils.isEmpty(landandBuildingInfo.getFromtelno()) ? "" : landandBuildingInfo.getFromtelno());
            premisesObj.put("fromtelnoRemarks", TextUtils.isEmpty(landandBuildingInfo.getFromtelnoRemarks()) ? "" : landandBuildingInfo.getFromtelnoRemarks());
            premisesObj.put("fromtelnoNc", landandBuildingInfo.getFromtelnoNc());
            premisesObj.put("totelno", TextUtils.isEmpty(landandBuildingInfo.getTotelno()) ? "" : landandBuildingInfo.getTotelno());
            premisesObj.put("totelnoRemarks", TextUtils.isEmpty(landandBuildingInfo.getTotelnoRemarks()) ? "" : landandBuildingInfo.getTotelnoRemarks());
            premisesObj.put("totelnoNc", landandBuildingInfo.getTotelnoNc());
            premisesObj.put("fromFaxno", TextUtils.isEmpty(landandBuildingInfo.getFromFaxno()) ? "" : landandBuildingInfo.getFromFaxno());
            premisesObj.put("fromFaxnoRemarks", TextUtils.isEmpty(landandBuildingInfo.getFromFaxnoRemarks()) ? "" : landandBuildingInfo.getFromFaxnoRemarks());
            premisesObj.put("fromFaxnoNc", landandBuildingInfo.getFromFaxnoNc());
            premisesObj.put("toFaxno", TextUtils.isEmpty(landandBuildingInfo.getToFaxno()) ? "" : landandBuildingInfo.getToFaxno());
            premisesObj.put("toFaxnoRemarks", TextUtils.isEmpty(landandBuildingInfo.getToFaxnoRemarks()) ? "" : landandBuildingInfo.getToFaxnoRemarks());
            premisesObj.put("toFaxnoNc", landandBuildingInfo.getToFaxnoNc());
            premisesObj.put("remarks", TextUtils.isEmpty(landandBuildingInfo.getRemarks()) ? "" : landandBuildingInfo.getRemarks());
            premisesObj.put("remarks_Remarks", TextUtils.isEmpty(landandBuildingInfo.getRemarksRemarks()) ? "" : landandBuildingInfo.getRemarksRemarks());
            premisesObj.put("remarks_Nc", landandBuildingInfo.getRemarksNC());
            array.put(premisesObj);

            premisesInfoObj.put("premisesShifting", premisesObj);
            premisesInfoObj.put("yearwisecollegeid", landandBuildingInfo.getYearWiseCollegeid());
            payloadObj.put("payload", premisesInfoObj);

            result.put("result", payloadObj);
            Log.e("result", result.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    public JSONObject getClasroomInfoSyncData(List<Classroom> classroom) {

        JSONObject result = new JSONObject();
        JSONObject payloadObj = new JSONObject();
        JSONObject insInfoObj = new JSONObject();

        JSONArray insStaffarray = new JSONArray();

        try {
            for (int i = 0; i < classroom.size(); i++) {

                JSONObject insObj = new JSONObject();
                insObj.put("tradeId", classroom.get(i).getTradeId());
                insObj.put("flag", classroom.get(i).getFlag());
                insObj.put("refId", classroom.get(i).getRefId());
                insObj.put("available", classroom.get(i).getAvailable());
                insObj.put("classroomName", TextUtils.isEmpty(classroom.get(i).getClassroomName()) ? "" : classroom.get(i).getClassroomName());
                insObj.put("asperNCVTNorms", TextUtils.isEmpty(classroom.get(i).getAsperNCVTNorms()) ? "" : classroom.get(i).getAsperNCVTNorms());
                insObj.put("classroomArea", TextUtils.isEmpty(classroom.get(i).getClassroomArea()) ? "" : classroom.get(i).getClassroomArea());
                insObj.put("shortageArea", TextUtils.isEmpty(classroom.get(i).getShortageArea()) ? "" : classroom.get(i).getShortageArea());
                insObj.put("classroomNameRemarks", TextUtils.isEmpty(classroom.get(i).getShortageArea()) ? "" : classroom.get(i).getShortageArea());
                insObj.put("asperNCVTNormsRemarks", TextUtils.isEmpty(classroom.get(i).getAsperNCVTNormsRemarks()) ? "" : classroom.get(i).getAsperNCVTNormsRemarks());
                insObj.put("classroomAreaRemarks", TextUtils.isEmpty(classroom.get(i).getClassroomAreaRemarks()) ? "" : classroom.get(i).getClassroomAreaRemarks());
                insObj.put("shortageAreaRemarks", TextUtils.isEmpty(classroom.get(i).getShortageAreaRemarks()) ? "" : classroom.get(i).getShortageAreaRemarks());
                insObj.put("classroomNameNC", classroom.get(i).getClassroomNameNC());
                insObj.put("asperNCVTNormsNC", classroom.get(i).getAsperNCVTNormsNC());
                insObj.put("classroomAreaNC", classroom.get(i).getClassroomAreaNC());
                insObj.put("shortageAreaNC", classroom.get(i).getShortageAreaNC());
                insObj.put("widthOftheClassroom", TextUtils.isEmpty(classroom.get(i).getWidth()) ? "" : classroom.get(i).getWidth());
                insObj.put("widthOftheClassroomRemarks", TextUtils.isEmpty(classroom.get(i).getWidthRemarks()) ? "" : classroom.get(i).getWidthRemarks());
                insObj.put("widthOftheClassroomNc", classroom.get(i).getWidthNC());
                insObj.put("isWiresandBoardsCovered", TextUtils.isEmpty(classroom.get(i).getWire()) ? "" : classroom.get(i).getWire());
                insObj.put("isWiresandBoardsCovered_Remarks", TextUtils.isEmpty(classroom.get(i).getWireRemarks()) ? "" : classroom.get(i).getWireRemarks());
                String nc = String.valueOf(classroom.get(i).getWireNC());
                if (nc.equalsIgnoreCase("null")) {
                    insObj.put("isWiresandBoardsCovered_Nc", "0");
                } else {
                    insObj.put("isWiresandBoardsCovered_Nc", classroom.get(i).getWireNC());
                }

                insObj.put("roofOfClassroomMadeTin", TextUtils.isEmpty(classroom.get(i).getRoof()) ? "" : classroom.get(i).getRoof());
                insObj.put("roofOfClassroomMadeTin_Remarks", TextUtils.isEmpty(classroom.get(i).getRoofRemarks()) ? "" : classroom.get(i).getRoofRemarks());
                String nc1 = String.valueOf(classroom.get(i).getRoofNC());
                if (nc1.equalsIgnoreCase("null")) {
                    insObj.put("roofOfClassroomMadeTin_Nc", "0");
                } else {
                    insObj.put("roofOfClassroomMadeTin_Nc", classroom.get(i).getRoofNC());
                }

                insObj.put("ceilingHeightLessthan10Feet", TextUtils.isEmpty(classroom.get(i).getHeight()) ? "" : classroom.get(i).getHeight());
                insObj.put("ceilingHeightLessthan10Feet_Remarks", TextUtils.isEmpty(classroom.get(i).getHeightRemarks()) ? "" : classroom.get(i).getHeightRemarks());
                insObj.put("ceilingHeightLessthan10Feet_Nc", classroom.get(i).getHeightNC());
                insObj.put("wallsOfClassroomLabTin", TextUtils.isEmpty(classroom.get(i).getTin()) ? "" : classroom.get(i).getTin());
                insObj.put("wallsOfClassroomLabTin_Remarks", TextUtils.isEmpty(classroom.get(i).getTinRemarks()) ? "" : classroom.get(i).getTinRemarks());

                String nc2 = String.valueOf(classroom.get(i).getTinNC());
                if (nc2.equalsIgnoreCase("null")) {
                    insObj.put("wallsOfClassroomLabTin_Nc", "0");
                } else {
                    insObj.put("wallsOfClassroomLabTin_Nc", classroom.get(i).getTinNC());
                }


                insStaffarray.put(insObj);
            }

            insInfoObj.put("classroomDetails", insStaffarray);
            insInfoObj.put("yearwiseCollegeId", classroom.get(0).getYearwisecollegeid());
            payloadObj.put("payload", insInfoObj);

            result.put("result", payloadObj);
            Log.e("result", result.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public JSONObject getITLabInfoSyncData(List<ITLab> labInfoList) {

        JSONObject result = new JSONObject();
        JSONObject payload = new JSONObject();
        JSONObject labObj = new JSONObject();
        JSONArray itlabArr = new JSONArray();
        JSONObject it_labObj = new JSONObject();

        try {

            for (int i = 0; i < labInfoList.size(); i++) {
                JSONObject itlabobj = new JSONObject();

                itlabobj.put("nameofequipment", TextUtils.isEmpty(labInfoList.get(i).getNameofequipment()) ? "" : labInfoList.get(i).getNameofequipment());
                itlabobj.put("category", labInfoList.get(i).getCategory());
                itlabobj.put("quantity", labInfoList.get(i).getQuantity());


                it_labObj.put("refId", labInfoList.get(i).getRefId());
                it_labObj.put("available", labInfoList.get(i).getAvailable());
                it_labObj.put("yearWiseCollegeid", labInfoList.get(i).getYearWiseCollegeid());
                it_labObj.put("actualTotalComputer", TextUtils.isEmpty(labInfoList.get(i).getActualTotalComputer()) ? "" : labInfoList.get(i).getActualTotalComputer());
                it_labObj.put("areaOfItLab", TextUtils.isEmpty(labInfoList.get(i).getAreaOfItLab()) ? "" : labInfoList.get(i).getAreaOfItLab());
                it_labObj.put("actualArea", TextUtils.isEmpty(labInfoList.get(i).getActualArea()) ? "" : labInfoList.get(i).getActualArea());
                it_labObj.put("noOfComputer", TextUtils.isEmpty(labInfoList.get(i).getNoOfComputer()) ? "" : labInfoList.get(i).getNoOfComputer());
                it_labObj.put("noOfComputerRemarks", TextUtils.isEmpty(labInfoList.get(i).getNoOfComputerRemarks()) ? "" : labInfoList.get(i).getNoOfComputerRemarks());
                it_labObj.put("actualTotalComputerRemarks", TextUtils.isEmpty(labInfoList.get(i).getActualTotalComputerRemarks()) ? "" : labInfoList.get(i).getActualTotalComputerRemarks());
                it_labObj.put("areaOfItLabRemarks", TextUtils.isEmpty(labInfoList.get(i).getAreaOfItLabRemarks()) ? "" : labInfoList.get(i).getAreaOfItLabRemarks());
                it_labObj.put("actualAreaRemarks", TextUtils.isEmpty(labInfoList.get(i).getActualAreaRemarks()) ? "" : labInfoList.get(i).getActualAreaRemarks());
                it_labObj.put("noOfComputerNc", labInfoList.get(i).getNoOfComputerNc());
                it_labObj.put("actualTotalComputerNc", labInfoList.get(i).getActualTotalComputerNc());
                it_labObj.put("areaOfItLabNc", labInfoList.get(i).getAreaOfItLabNc());
                it_labObj.put("actualAreaNc", labInfoList.get(i).getActualAreaNc());
                it_labObj.put("availabilityofInternet", TextUtils.isEmpty(labInfoList.get(i).getInternet()) ? "" : labInfoList.get(i).getInternet());
                it_labObj.put("availabilityofinternetRemarks", TextUtils.isEmpty(labInfoList.get(i).getInternetRemarks()) ? "" : labInfoList.get(i).getInternetRemarks());
                it_labObj.put("availabilityofinternetNc", "0");
                it_labObj.put("avaibilityofITLabWithStandAlone", TextUtils.isEmpty(labInfoList.get(i).getLab()) ? "" : labInfoList.get(i).getLab());
                it_labObj.put("avaibilityofITLabWithStandAlone_Remarks", TextUtils.isEmpty(labInfoList.get(i).getLabRemarks()) ? "" : labInfoList.get(i).getLabRemarks());
                it_labObj.put("avaibilityofITLabWithStandAlone_Nc", "0");
                it_labObj.put("isthFloorOfITLabCarpeted", TextUtils.isEmpty(labInfoList.get(i).getRoof()) ? "" : labInfoList.get(i).getFloor());
                it_labObj.put("isthFloorOfITLabCarpeted_Remarks", TextUtils.isEmpty(labInfoList.get(i).getRoofRemarks()) ? "" : labInfoList.get(i).getFloorRemarks());
                it_labObj.put("isthFloorOfITLabCarpeted_Nc", "0");
                it_labObj.put("ceilingHeightLessthan10Feet", TextUtils.isEmpty(labInfoList.get(i).getHeight()) ? "" : labInfoList.get(i).getHeight());
                it_labObj.put("ceilingHeightLessthan10Feet_Remarks", TextUtils.isEmpty(labInfoList.get(i).getHeightRemarks()) ? "" : labInfoList.get(i).getHeightRemarks());
                it_labObj.put("ceilingHeightLessthan10Feet_Nc", "0");
                it_labObj.put("wallsOfITLabTin", TextUtils.isEmpty(labInfoList.get(i).getTin()) ? "" : labInfoList.get(i).getTin());
                it_labObj.put("wallsOfITLabTin_Remarks", TextUtils.isEmpty(labInfoList.get(i).getTinRemarks()) ? "" : labInfoList.get(i).getTinRemarks());
                it_labObj.put("wallsOfITLabTin_Nc", "0");
                itlabArr.put(itlabobj);

            }


            labObj.put("it_lablist", itlabArr);
            labObj.put("it_lab", it_labObj);
            labObj.put("yearwisecollegeid", labInfoList.get(0).getYearWiseCollegeid());

            payload.put("payload", labObj);
            result.put("result", payload);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public JSONObject getLandInfoSyncData(LandandBuilding landandBuildingInfo) {

        JSONObject result = new JSONObject();
        JSONObject payloadObj = new JSONObject();
        JSONObject landInfoObj = new JSONObject();
        JSONObject landObj = new JSONObject();

        JSONArray array = new JSONArray();

        try {
            landObj.put("avaibilityhousekeepingstaffRemarks", TextUtils.isEmpty(landandBuildingInfo.getAvaibilityhousekeepingstaffRemarks()) ? "" : landandBuildingInfo.getAvaibilityhousekeepingstaffRemarks());
            landObj.put("totalOpenAreaNc", landandBuildingInfo.getTotalOpenAreaNc());
            landObj.put("AretheWashroomFunctional", TextUtils.isEmpty(landandBuildingInfo.getAretheWashroomFunctional()) ? "" : landandBuildingInfo.getAretheWashroomFunctional());
            landObj.put("dimensnsionsandAreaRemarks", TextUtils.isEmpty(landandBuildingInfo.getDimensionRemarks()) ? "" : landandBuildingInfo.getDimensionRemarks());
            landObj.put("orgInstalledLiftRemarks", TextUtils.isEmpty(landandBuildingInfo.getOrgInstalledLiftRemarks()) ? "" : landandBuildingInfo.getOrgInstalledLiftRemarks());
            landObj.put("buildingPlanofInstitute", TextUtils.isEmpty(landandBuildingInfo.getBuildingPlanofInstitute()) ? "" : landandBuildingInfo.getBuildingPlanofInstitute());
            landObj.put("avaibilityofplacementcellRemarks", TextUtils.isEmpty(landandBuildingInfo.getAvaibilityofplacementcellRemarks()) ? "" : landandBuildingInfo.getAvaibilityofplacementcellRemarks());
            String nc17 = String.valueOf(landandBuildingInfo.getAvaibilityofSeparateMaleFemaleWashroomNc());
            if (nc17.equalsIgnoreCase("null")) {
                landObj.put("AvaibilityofSeparateMaleFemaleWashroomNc", "0");
            } else {
                landObj.put("AvaibilityofSeparateMaleFemaleWashroomNc", landandBuildingInfo.getAvaibilityofSeparateMaleFemaleWashroomNc());
            }
            //landObj.put("AvaibilityofSeparateMaleFemaleWashroomNc",landandBuildingInfo.getAvaibilityofSeparateMaleFemaleWashroomNc());
            landObj.put("buildingPlanofInstituteRemarks", TextUtils.isEmpty(landandBuildingInfo.getBuildingPlanofInstituteRemarks()) ? "" : landandBuildingInfo.getBuildingPlanofInstituteRemarks());
            landObj.put("totalOpenArea", TextUtils.isEmpty(landandBuildingInfo.getTotalOpenArea()) ? "" : landandBuildingInfo.getTotalOpenArea());
            landObj.put("nameofCompetentAuthority_Nc", landandBuildingInfo.getComptentNC());
            landObj.put("capacityofLiftRemarks", TextUtils.isEmpty(landandBuildingInfo.getCapacityofLiftRemarks()) ? "" : landandBuildingInfo.getCapacityofLiftRemarks());
            landObj.put("areAlltheWiresAndSwitchBoard_Remarks", TextUtils.isEmpty(landandBuildingInfo.getSwitchBoardRemarks()) ? "" : landandBuildingInfo.getSwitchBoardRemarks());
            String nc9 = String.valueOf(landandBuildingInfo.getSwitchBoardNC());
            if (nc9.equalsIgnoreCase("null")) {
                landObj.put("areAlltheWiresAndSwitchBoard_Nc", "0");
            } else {
                landObj.put("areAlltheWiresAndSwitchBoard_Nc", landandBuildingInfo.getSwitchBoardNC());
            }

            landObj.put("AvaibilityofSeparateMaleFemaleWashroom", TextUtils.isEmpty(landandBuildingInfo.getAvaibilityofSeparateMaleFemaleWashroom()) ? "" : landandBuildingInfo.getAvaibilityofSeparateMaleFemaleWashroom());
            landObj.put("capacityofLift", TextUtils.isEmpty(landandBuildingInfo.getCapacityofLift()) ? "" : landandBuildingInfo.getCapacityofLift());
            String nc5 = String.valueOf(landandBuildingInfo.getDimensionNC());
            if (nc5.equalsIgnoreCase("null")) {
                landObj.put("dimensnsionsandAreaNc", "0");
            } else {
                landObj.put("dimensnsionsandAreaNc", landandBuildingInfo.getDimensionNC());
            }

            landObj.put("expiryofagreementRemarks", TextUtils.isEmpty(landandBuildingInfo.getExpiryofagreementRemarks()) ? "" : landandBuildingInfo.getExpiryofagreementRemarks());
            landObj.put("signedbyArchitect", TextUtils.isEmpty(landandBuildingInfo.getSitmap()) ? "" : landandBuildingInfo.getSitmap());
            landObj.put("totalLandRemarks", TextUtils.isEmpty(landandBuildingInfo.getTotalLandRemarks()) ? "" : landandBuildingInfo.getTotalLandRemarks());
            landObj.put("floorisCementedRemarks", TextUtils.isEmpty(landandBuildingInfo.getFloorisCementedRemarks()) ? "" : landandBuildingInfo.getFloorisCementedRemarks());
            landObj.put("architectRegnNoRemarks", TextUtils.isEmpty(landandBuildingInfo.getArchitectRegnNoRemarks()) ? "" : landandBuildingInfo.getArchitectRegnNoRemarks());
            landObj.put("noofFloorsRemarks", TextUtils.isEmpty(landandBuildingInfo.getNoofFloorsRemarks()) ? "" : landandBuildingInfo.getNoofFloorsRemarks());
            String nc19 = String.valueOf(landandBuildingInfo.getExpiryofagreementNC());
            if (nc19.equalsIgnoreCase("null")) {
                landObj.put("expiryofagreementNC", "0");
            } else {
                landObj.put("expiryofagreementNC", landandBuildingInfo.getExpiryofagreementNC());
            }
            //landObj.put("expiryofagreementNC",landandBuildingInfo.getExpiryofagreementNC());
            landObj.put("architectNameNc", landandBuildingInfo.getArchitectNameNc());
            landObj.put("avaibilityofstaffroomNc", landandBuildingInfo.getAvaibilityofstaffroomNc());
            landObj.put("totalBuildAreaNc", landandBuildingInfo.getTotalBuildAreaNc());
            landObj.put("safetyCertificateofLiftRemarks", TextUtils.isEmpty(landandBuildingInfo.getSafetyCertificateofLiftRemarks()) ? "" : landandBuildingInfo.getSafetyCertificateofLiftRemarks());
            landObj.put("istheApproachRoadTowards", TextUtils.isEmpty(landandBuildingInfo.getApproach()) ? "" : landandBuildingInfo.getApproach());
            landObj.put("dimensnsionsandArea", TextUtils.isEmpty(landandBuildingInfo.getDimension()) ? "" : landandBuildingInfo.getDimension());
            landObj.put("nameofCompetentAuthority_Remarks", TextUtils.isEmpty(landandBuildingInfo.getComptentRemarks()) ? "" : landandBuildingInfo.getComptentRemarks());
            landObj.put("AvaibiltyofFireextinguisher", TextUtils.isEmpty(landandBuildingInfo.getAvaibiltyofFireextinguisher()) ? "" : landandBuildingInfo.getAvaibiltyofFireextinguisher());
            landObj.put("mksSystemRemarks", TextUtils.isEmpty(landandBuildingInfo.getMapRemarks()) ? "" : landandBuildingInfo.getMapRemarks());
            landObj.put("istheApproachRoadTowards_Nc", landandBuildingInfo.getApproachNC());
            landObj.put("avaibilityofPlacementcell", TextUtils.isEmpty(landandBuildingInfo.getAvaibilityofPlacementcell()) ? "" : landandBuildingInfo.getAvaibilityofPlacementcell());
            String nc4 = String.valueOf(landandBuildingInfo.getSitmapNc());
            if (nc4.equalsIgnoreCase("null")) {
                landObj.put("signedbyArchitectNc", "0");
            } else {
                landObj.put("signedbyArchitectNc", landandBuildingInfo.getSitmapNc());
            }

            landObj.put("architectRegnNo", TextUtils.isEmpty(landandBuildingInfo.getArchitectRegnNo()) ? "" : landandBuildingInfo.getArchitectRegnNo());
            String nc20 = String.valueOf(landandBuildingInfo.getAretheWashroomFunctionalNc());
            if (nc20.equalsIgnoreCase("null")) {
                landObj.put("AretheWashroomFunctionalNc", "0");
            } else {
                landObj.put("AretheWashroomFunctionalNc", landandBuildingInfo.getAretheWashroomFunctionalNc());
            }
            //landObj.put("AretheWashroomFunctionalNc",landandBuildingInfo.AretheWashroomFunctionalNc());
            landObj.put("orgInstalledLift", TextUtils.isEmpty(landandBuildingInfo.getOrgInstalledLift()) ? "" : landandBuildingInfo.getOrgInstalledLift());
            String nc21 = String.valueOf(landandBuildingInfo.getEntranceNC());
            if (nc21.equalsIgnoreCase("null")) {
                landObj.put("istheEntranceRoadTowards_Nc", "0");
            } else {
                landObj.put("istheEntranceRoadTowards_Nc", landandBuildingInfo.getEntranceNC());
            }
            //landObj.put("istheEntranceRoadTowards_Nc",landandBuildingInfo.getEntranceNC());
            landObj.put("architectRegnNoNc", landandBuildingInfo.getArchitectRegnNoNc());
            landObj.put("avaibilityofsafeDrinkingwater", TextUtils.isEmpty(landandBuildingInfo.getAvaibilityofsafeDrinkingwater()) ? "" : landandBuildingInfo.getAvaibilityofsafeDrinkingwater());
            landObj.put("wallOfItiNc", landandBuildingInfo.getWallOfItiNc());
            String nc10 = String.valueOf(landandBuildingInfo.getFloorisCementedNc());
            if (nc10.equalsIgnoreCase("null")) {
                landObj.put("floorisCementedNc", "0");
            } else {
                landObj.put("floorisCementedNc", landandBuildingInfo.getFloorisCementedNc());
            }

            landObj.put("totalLand", TextUtils.isEmpty(landandBuildingInfo.getTotalLand()) ? "" : landandBuildingInfo.getTotalLand());
            landObj.put("avaibilityofplacementcellNc", landandBuildingInfo.getAvaibilityofplacementcellNc());
            landObj.put("totalOpenAreaRemarks", TextUtils.isEmpty(landandBuildingInfo.getTotalOpenAreaRemarks()) ? "" : landandBuildingInfo.getTotalOpenAreaRemarks());
            landObj.put("floorisCemented", TextUtils.isEmpty(landandBuildingInfo.getFloorisCemented()) ? "" : landandBuildingInfo.getFloorisCemented());
            String nc18 = String.valueOf(landandBuildingInfo.getDurationleaseNc());
            if (nc18.equalsIgnoreCase("null")) {
                landObj.put("durationleaseNc", "0");
            } else {
                landObj.put("durationleaseNc", landandBuildingInfo.getDurationleaseNc());
            }
            //landObj.put("durationleaseNc",landandBuildingInfo.getDurationleaseNc());
            landObj.put("avaibilityofsafedrinkingwaterRemarks", TextUtils.isEmpty(landandBuildingInfo.getAvaibilityofsafedrinkingwaterRemarks()) ? "" : landandBuildingInfo.getAvaibilityofsafedrinkingwaterRemarks());
            landObj.put("istheApproachRoadTowards_Remarks", TextUtils.isEmpty(landandBuildingInfo.getApproachRemarks()) ? "" : landandBuildingInfo.getApproachRemarks());
            landObj.put("itiSituatedintheSameCampus", TextUtils.isEmpty(landandBuildingInfo.getItiSituatedintheSameCampus()) ? "" : landandBuildingInfo.getItiSituatedintheSameCampus());
            landObj.put("buildingPlanofInstituteNc", landandBuildingInfo.getBuildingPlanofInstituteNc());
            landObj.put("workshopRoof", TextUtils.isEmpty(landandBuildingInfo.getWorkshopRoof()) ? "" : landandBuildingInfo.getWorkshopRoof());
            landObj.put("workshopRoofRemarks", TextUtils.isEmpty(landandBuildingInfo.getWorkshopRoofRemarks()) ? "" : landandBuildingInfo.getWorkshopRoofRemarks());
            landObj.put("buildingTypeNc", landandBuildingInfo.getBuildingTypeNc());
            landObj.put("istheEntranceRoadTowards", TextUtils.isEmpty(landandBuildingInfo.getEntrance()) ? "" : landandBuildingInfo.getEntrance());
            landObj.put("AretheWashroomFunctionalRemarks", TextUtils.isEmpty(landandBuildingInfo.getAretheWashroomFunctionalRemarks()) ? "" : landandBuildingInfo.getAretheWashroomFunctionalRemarks());
            String nc13 = String.valueOf(landandBuildingInfo.getLiftinInstituteNc());
            if (nc13.equalsIgnoreCase("null")) {
                landObj.put("liftinInstituteNc", "0");
            } else {
                landObj.put("liftinInstituteNc", landandBuildingInfo.getLiftinInstituteNc());
            }

            landObj.put("avaibilityoflibrarayNc", landandBuildingInfo.getAvaibilityoflibrarayNc());
            landObj.put("totalLandNc", landandBuildingInfo.getTotalLandNc());
            String nc8 = String.valueOf(landandBuildingInfo.getVentilationNC());
            if (nc8.equalsIgnoreCase("null")) {
                landObj.put("itiProperlyVentilatedAndLighted_Nc", "0");
            } else {
                landObj.put("itiProperlyVentilatedAndLighted_Nc", landandBuildingInfo.getVentilationNC());
            }

            landObj.put("durationlease", TextUtils.isEmpty(landandBuildingInfo.getDurationlease()) ? "" : landandBuildingInfo.getDurationlease());
            landObj.put("workshopRoofNc", landandBuildingInfo.getWorkshopRoofNc());
            landObj.put("noofFloors", TextUtils.isEmpty(landandBuildingInfo.getNoofFloors()) ? "" : landandBuildingInfo.getNoofFloors());
            landObj.put("architectName", TextUtils.isEmpty(landandBuildingInfo.getArchitectName()) ? "" : landandBuildingInfo.getArchitectName());
            landObj.put("avaibilityofStaffroom", TextUtils.isEmpty(landandBuildingInfo.getAvaibilityofStaffroom()) ? "" : landandBuildingInfo.getAvaibilityofStaffroom());
            landObj.put("dateofOccupancy", TextUtils.isEmpty(landandBuildingInfo.getDateofOccupancy()) ? "" : landandBuildingInfo.getDateofOccupancy());
            landObj.put("safetyCertificateofLift", TextUtils.isEmpty(landandBuildingInfo.getSafetyCertificateofLift()) ? "" : landandBuildingInfo.getSafetyCertificateofLift());
            String nc12 = String.valueOf(landandBuildingInfo.getDateofOccupancyNc());
            if (nc12.equalsIgnoreCase("null")) {
                landObj.put("dateofOccupancyNc", "0");
            } else {
                landObj.put("dateofOccupancyNc", landandBuildingInfo.getDateofOccupancyNc());
            }

            landObj.put("architectNameRemarks", TextUtils.isEmpty(landandBuildingInfo.getArchitectNameRemarks()) ? "" : landandBuildingInfo.getArchitectNameRemarks());
            landObj.put("totalBuildArea", TextUtils.isEmpty(landandBuildingInfo.getTotalBuildArea()) ? "" : landandBuildingInfo.getTotalBuildArea());

            String nc15 = String.valueOf(landandBuildingInfo.getItiSituatedintheSameCampusNc());
            if (nc15.equalsIgnoreCase("null")) {
                landObj.put("itiSituatedintheSameCampusNc", "0");
            } else {
                landObj.put("itiSituatedintheSameCampusNc", landandBuildingInfo.getItiSituatedintheSameCampusNc());
            }

            landObj.put("itiProperlyVentilatedAndLighted", TextUtils.isEmpty(landandBuildingInfo.getVentilation()) ? "" : landandBuildingInfo.getVentilation());
            landObj.put("durationleaseRemarks", TextUtils.isEmpty(landandBuildingInfo.getDurationleaseRemarks()) ? "" : landandBuildingInfo.getDurationleaseRemarks());
            landObj.put("buildingType", TextUtils.isEmpty(landandBuildingInfo.getBuildingType()) ? "" : landandBuildingInfo.getBuildingType());
            landObj.put("itiSituatedintheSameCampusRemarks", TextUtils.isEmpty(landandBuildingInfo.getItiSituatedintheSameCampusRemarks()) ? "" : landandBuildingInfo.getItiSituatedintheSameCampusRemarks());
            landObj.put("istheEntranceRoadTowards_Remarks", TextUtils.isEmpty(landandBuildingInfo.getEntranceRemarks()) ? "" : landandBuildingInfo.getEntranceRemarks());
            landObj.put("AvaibiltyofFireextinguisherRemarks", TextUtils.isEmpty(landandBuildingInfo.getAvaibiltyofFireextinguisherRemarks()) ? "" : landandBuildingInfo.getAvaibiltyofFireextinguisherRemarks());
            landObj.put("avaibilityoflibrarayRemarks", TextUtils.isEmpty(landandBuildingInfo.getAvaibilityoflibrarayRemarks()) ? "" : landandBuildingInfo.getAvaibilityofsafedrinkingwaterRemarks());
            landObj.put("refId", landandBuildingInfo.getRefId());
            landObj.put("avaibilityofstaffroomRemarks", TextUtils.isEmpty(landandBuildingInfo.getAvaibilityofstaffroomRemarks()) ? "" : landandBuildingInfo.getAvaibilityofstaffroomRemarks());
            landObj.put("dateofOccupancyRemarks", TextUtils.isEmpty(landandBuildingInfo.getDateofOccupancyRemarks()) ? "" : landandBuildingInfo.getDateofOccupancyRemarks());
            landObj.put("signedbyArchitectRemarks", TextUtils.isEmpty(landandBuildingInfo.getSitmapRemarks()) ? "" : landandBuildingInfo.getSitmapRemarks());
            landObj.put("wallOfIti", TextUtils.isEmpty(landandBuildingInfo.getWallOfIti()) ? "" : landandBuildingInfo.getWallOfIti());
            landObj.put("avaibilityofLibraray", TextUtils.isEmpty(landandBuildingInfo.getAvaibilityofLibraray()) ? "" : landandBuildingInfo.getAvaibilityofLibraray());
            landObj.put("mksSystem", TextUtils.isEmpty(landandBuildingInfo.getMap()) ? "" : landandBuildingInfo.getMap());
            landObj.put("liftinInstituteRemarks", TextUtils.isEmpty(landandBuildingInfo.getLiftinInstituteRemarks()) ? "" : landandBuildingInfo.getLiftinInstituteRemarks());


            String nc7 = String.valueOf(landandBuildingInfo.getAvaibiltyofFireextinguisherNc());
            if (nc7.equalsIgnoreCase("null")) {
                landObj.put("AvaibiltyofFireextinguisherNc", "0");
            } else {
                landObj.put("AvaibiltyofFireextinguisherNc", landandBuildingInfo.getAvaibiltyofFireextinguisherNc());
            }

            landObj.put("buildingTypeRemarks", TextUtils.isEmpty(landandBuildingInfo.getBuildingTypeRemarks()) ? "" : landandBuildingInfo.getBuildingTypeRemarks());

            String nc16 = String.valueOf(landandBuildingInfo.getAvaibilityofsafedrinkingwaterNc());
            if (nc16.equalsIgnoreCase("null")) {
                landObj.put("avaibilityofsafedrinkingwaterNc", "0");
            } else {
                landObj.put("avaibilityofsafedrinkingwaterNc", landandBuildingInfo.getAvaibilityofsafedrinkingwaterNc());
            }
            //landObj.put("avaibilityofsafedrinkingwaterNc",landandBuildingInfo.());
            landObj.put("avaibilityHousekeepingstaff", TextUtils.isEmpty(landandBuildingInfo.getAvaibilityHousekeepingstaff()) ? "" : landandBuildingInfo.getAvaibilityHousekeepingstaff());
            landObj.put("noofFloorsNc", landandBuildingInfo.getNoofFloorsNc());
            landObj.put("areAlltheWiresAndSwitchBoard", TextUtils.isEmpty(landandBuildingInfo.getSwitchBoard()) ? "" : landandBuildingInfo.getSwitchBoard());
            String nc6 = String.valueOf(landandBuildingInfo.getMapNC());
            if (nc6.equalsIgnoreCase("null")) {
                landObj.put("mksSystemNc", "0");
            } else {
                landObj.put("mksSystemNc", landandBuildingInfo.getMapNC());
            }

            String nc14 = String.valueOf(landandBuildingInfo.getSafetyCertificateofLiftNc());
            if (nc14.equalsIgnoreCase("null")) {
                landObj.put("safetyCertificateofLiftNc", "0");
            } else {
                landObj.put("safetyCertificateofLiftNc", landandBuildingInfo.getSafetyCertificateofLiftNc());
            }

            landObj.put("totalBuildAreaRemarks", TextUtils.isEmpty(landandBuildingInfo.getTotalBuildAreaRemarks()) ? "" : landandBuildingInfo.getTotalBuildAreaRemarks());
            String nc22 = String.valueOf(landandBuildingInfo.getAvaibilityhousekeepingstaffNc());
            if (nc22.equalsIgnoreCase("null")) {
                landObj.put("avaibilityhousekeepingstaffNc", "0");
            } else {
                landObj.put("avaibilityhousekeepingstaffNc", landandBuildingInfo.getAvaibilityhousekeepingstaffNc());
            }

            //landObj.put("avaibilityhousekeepingstaffNc",landandBuildingInfo.getAvaibilityhousekeepingstaffNc());
            landObj.put("liftinInstitute", TextUtils.isEmpty(landandBuildingInfo.getLiftinInstitute()) ? "" : landandBuildingInfo.getLiftinInstitute());
            landObj.put("AvaibilityofSeparateMaleFemaleWashroomRemarks", TextUtils.isEmpty(landandBuildingInfo.getAvaibilityofSeparateMaleFemaleWashroomRemarks()) ? "" : landandBuildingInfo.getAvaibilityofSeparateMaleFemaleWashroomRemarks());
            landObj.put("nameofCompetentAuthority", TextUtils.isEmpty(landandBuildingInfo.getComptent()) ? "" : landandBuildingInfo.getComptent());
            landObj.put("wallOfItiRemarks", TextUtils.isEmpty(landandBuildingInfo.getWallOfItiRemarks()) ? "" : landandBuildingInfo.getWallOfItiRemarks());
            landObj.put("expiryofAgreement", TextUtils.isEmpty(landandBuildingInfo.getExpiryofAgreement()) ? "" : landandBuildingInfo.getExpiryofAgreement());
            landObj.put("capacityofLiftNc", landandBuildingInfo.getCapacityofLiftNc());
            landObj.put("itiProperlyVentilatedAndLighted_Remarks", TextUtils.isEmpty(landandBuildingInfo.getVentilationRemarks()) ? "" : landandBuildingInfo.getVentilationRemarks());
            landObj.put("orgInstalledLiftNc", landandBuildingInfo.getOrgInstalledLiftNc());
            landObj.put("masterPlanAvailbleforArea", TextUtils.isEmpty(landandBuildingInfo.getIsMasterPlan()) ? "" : landandBuildingInfo.getIsMasterPlan());
            landObj.put("masterPlanAvailbleforArea_Remarks", TextUtils.isEmpty(landandBuildingInfo.getIsMasterPlanRemarks()) ? "" : landandBuildingInfo.getIsMasterPlanRemarks());
            landObj.put("masterPlanAvailbleforArea_Nc", landandBuildingInfo.getIsMasterPlanNC());
            landObj.put("itiPremisesShared", TextUtils.isEmpty(landandBuildingInfo.getIsPremisesShared()) ? "" : landandBuildingInfo.getIsPremisesShared());
            landObj.put("itiPremisesShared_Remarks", TextUtils.isEmpty(landandBuildingInfo.getIsPremisesSharedRemarks()) ? "" : landandBuildingInfo.getIsPremisesSharedRemarks());
            String nc = String.valueOf(landandBuildingInfo.getIsPremisesSharedNC());
            if (nc.equalsIgnoreCase("null")) {
                landObj.put("itiPremisesShared_Nc", "0");
            } else {
                landObj.put("itiPremisesShared_Nc", landandBuildingInfo.getIsPremisesSharedNC());
            }

            landObj.put("itiHaveASeparateEntrance", TextUtils.isEmpty(landandBuildingInfo.getSeperateEntrance()) ? "" : landandBuildingInfo.getSeperateEntrance());
            landObj.put("itiHaveASeparateEntrance_Remarks", TextUtils.isEmpty(landandBuildingInfo.getSeperateEntranceRemarks()) ? "" : landandBuildingInfo.getSeperateEntranceRemarks());
            String nc1 = String.valueOf(landandBuildingInfo.getSeperateEntranceNC());
            if (nc1.equalsIgnoreCase("null")) {
                landObj.put("itiHaveASeparateEntrance_Nc", "0");
            } else {
                landObj.put("itiHaveASeparateEntrance_Nc", landandBuildingInfo.getSeperateEntranceNC());
            }

            landObj.put("availableOfSoundProofPartition", TextUtils.isEmpty(landandBuildingInfo.getSoundProofPartition()) ? "" : landandBuildingInfo.getSoundProofPartition());
            landObj.put("availableOfSoundProofPartition_Remarks", TextUtils.isEmpty(landandBuildingInfo.getSoundProofPartitionRemarks()) ? "" : landandBuildingInfo.getSoundProofPartitionRemarks());
            String nc2 = String.valueOf(landandBuildingInfo.getISoundProofPartitionNC());
            if (nc2.equalsIgnoreCase("null")) {
                landObj.put("availableOfSoundProofPartition_Nc", "0");
            } else {
                landObj.put("availableOfSoundProofPartition_Nc", landandBuildingInfo.getISoundProofPartitionNC());
            }

            landObj.put("istheStaircaseMadeofRCC", TextUtils.isEmpty(landandBuildingInfo.getStaircase()) ? "" : landandBuildingInfo.getStaircase());
            landObj.put("istheStaircaseMadeofRCC_Remarks", TextUtils.isEmpty(landandBuildingInfo.getStaircaseRemarks()) ? "" : landandBuildingInfo.getStaircaseRemarks());
            String nc3 = String.valueOf(landandBuildingInfo.getStaircaseNC());
            if (nc3.equalsIgnoreCase("null")) {
                landObj.put("istheStaircaseMadeofRCC_Nc", "0");
            } else {
                landObj.put("istheStaircaseMadeofRCC_Nc", landandBuildingInfo.getStaircaseNC());
            }

            landObj.put("nameofCompetentorgInstalledLiftNcAuthority", "");
            landObj.put("checkedplotSize", TextUtils.isEmpty(landandBuildingInfo.getConstructed()) ? "" : landandBuildingInfo.getConstructed());
            landObj.put("checkedplotsizeRemarks", TextUtils.isEmpty(landandBuildingInfo.getConstructedRemarks()) ? "" : landandBuildingInfo.getConstructedRemarks());
            landObj.put("checkedplotsizeNc", landandBuildingInfo.getConstructedNC());
            landObj.put("prescribedFormet", TextUtils.isEmpty(landandBuildingInfo.getPrescribed()) ? "" : landandBuildingInfo.getPrescribed());
            landObj.put("prescribedformetRemarks", TextUtils.isEmpty(landandBuildingInfo.getPrescribedRemarks()) ? "" : landandBuildingInfo.getPrescribedRemarks());
            landObj.put("prescribedformetNc", landandBuildingInfo.getPrescribedNC());


            array.put(landObj);

            landInfoObj.put("LandandBuildingDetailsApi", landObj);
            landInfoObj.put("yearwisecollegeid", landandBuildingInfo.getYearwisecollegeid());
            payloadObj.put("payload", landInfoObj);

            result.put("result", payloadObj);
            Log.e("result", result.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public JSONObject getTradewisetoolsSyncData(List<TradeWiseTool> getTradeWiseTools) {

        JSONObject result = new JSONObject();
        JSONObject payloadObj = new JSONObject();
        JSONObject insInfoObj = new JSONObject();

        JSONArray insStaffarray = new JSONArray();

        try {
            for (int i = 0; i < getTradeWiseTools.size(); i++) {

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("refId", getTradeWiseTools.get(i).getRefId());  //refid
                jsonObject.put("tradeId", getTradeWiseTools.get(i).getTradeId());
                jsonObject.put("equipmentId", getTradeWiseTools.get(i).getEquipmentId());  //equipment id
                jsonObject.put("reqUnit", getTradeWiseTools.get(i).getReqUnit());
                jsonObject.put("tradeName", getTradeWiseTools.get(i).getTradeName());
                jsonObject.put("equipmentName", getTradeWiseTools.get(i).getEquipmentName());
                jsonObject.put("requnitRemarks", "");
                jsonObject.put("reqNC", 0);
                jsonObject.put("qty", "");   //qty
                jsonObject.put("qtyRemarks", "");
                jsonObject.put("qtyNc", "");
                jsonObject.put("markTools", getTradeWiseTools.get(i).getMarkTools());
                jsonObject.put("availableUnit", getTradeWiseTools.get(i).getAvailableUnit());

                insStaffarray.put(jsonObject);
            }

            insInfoObj.put("tradeWiseTools", insStaffarray);
            insInfoObj.put("overallRemarks", "");
            insInfoObj.put("yearwiseCollegeId", getTradeWiseTools.get(0).getYearWiseCollegeid());
            payloadObj.put("payload", insInfoObj);

            result.put("result", payloadObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    public JSONObject getWorkshopAreaSyncData(List<WorkshopAreaModel> workshopsList) {

        JSONObject resultJsonObj = new JSONObject();
        JSONObject payloadJsonObj = new JSONObject();
        JSONObject workshopArea = new JSONObject();

        JSONArray workshopStaffarray = new JSONArray();
        try {

            for (int i = 0;i<workshopsList.size();i++){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("flag",workshopsList.get(i).getFlag());
                jsonObject.put("tradeId", workshopsList.get(i).getTradeId());
                jsonObject.put("tradeName",workshopsList.get(i).getTradeName());
                jsonObject.put("shiftsUnit", TextUtils.isEmpty(workshopsList.get(i).getShiftsUnit())?"":workshopsList.get(i).getShiftsUnit());
                jsonObject.put("shiftsunitRemarks", TextUtils.isEmpty(workshopsList.get(i).getShiftsunitRemarks())?"":workshopsList.get(i).getShiftsunitRemarks());
                jsonObject.put("shiftsNc", workshopsList.get(i).getShiftsNc());
                jsonObject.put("ncvtNorms", "");
                jsonObject.put("ncvtnormsRemarks", "");
                jsonObject.put("ncvtNc", "");
                jsonObject.put("actualArea", TextUtils.isEmpty(workshopsList.get(i).getActualArea())?"":workshopsList.get(i).getActualArea());
                jsonObject.put("actualareaRemarks", TextUtils.isEmpty(workshopsList.get(i).getActualareaRemarks())?"":workshopsList.get(i).getActualareaRemarks());
                jsonObject.put("actualareaNc", "");
                jsonObject.put("shortageArea", "");
                jsonObject.put("shortageareaRemarks", "");
                jsonObject.put("shortageAreaNc", "");
                jsonObject.put("refId", workshopsList.get(i).getRefId()); //TODO
                jsonObject.put("istheWorkshopRectangular", TextUtils.isEmpty(workshopsList.get(i).getIstheWorkshopRectangular())?"":workshopsList.get(i).getIstheWorkshopRectangular());
                jsonObject.put("istheWorkshopRectangularRemarks", TextUtils.isEmpty(workshopsList.get(i).getIstheWorkshopRectangularRemarks())?"":workshopsList.get(i).getIstheWorkshopRectangularRemarks());
                String nc = String.valueOf(workshopsList.get(i).getIstheWorkshopRectangularNc());
                if(nc.equalsIgnoreCase("null")){
                    jsonObject.put("istheWorkshopRectangularNc","0");
                }else {
                    jsonObject.put("istheWorkshopRectangularNc", workshopsList.get(i).getIstheWorkshopRectangularNc());
                }
                jsonObject.put("widthOftheWorkshop", TextUtils.isEmpty(workshopsList.get(i).getWidthOftheWorkshop())?"":workshopsList.get(i).getWidthOftheWorkshop());
                jsonObject.put("widthOftheWorkshopRemarks", TextUtils.isEmpty(workshopsList.get(i).getWidthOftheWorkshopRemarks())?"":workshopsList.get(i).getWidthOftheWorkshopRemarks());
                jsonObject.put("widthOftheWorkshopNc", "");
                jsonObject.put("aretheWorkshopWallsofTin",  TextUtils.isEmpty(workshopsList.get(i).getAretheWorkshopWallsofTin())?"":workshopsList.get(i).getAretheWorkshopWallsofTin());
                jsonObject.put("aretheWorkshopWallsofTinRemarks",  TextUtils.isEmpty(workshopsList.get(i).getAretheWorkshopWallsofTinRemarks())?"":workshopsList.get(i).getAretheWorkshopWallsofTinRemarks());
                jsonObject.put("aretheWorkshopWallsofTinNc", "");
                jsonObject.put("istheheavyMachineryLocated",  TextUtils.isEmpty(workshopsList.get(i).getHeavyMachinary())?"":workshopsList.get(i).getHeavyMachinary());
                jsonObject.put("istheheavyMachineryLocatedRemarks",  TextUtils.isEmpty(workshopsList.get(i).getHeavyMachinaryRemarks())?"":workshopsList.get(i).getHeavyMachinaryRemarks());
                String nc1 =String.valueOf(workshopsList.get(i).getHeavyMachinaryNC());
                if(nc1.equalsIgnoreCase("null")){
                    jsonObject.put("istheheavyMachineryLocatedNc","0");
                }else {
                    jsonObject.put("istheheavyMachineryLocatedNc", workshopsList.get(i).getHeavyMachinaryNC());
                }
                jsonObject.put("itihaveCombinedWorkshop",TextUtils.isEmpty(workshopsList.get(i).getItihaveCombinedWorkshop())?"":workshopsList.get(i).getItihaveCombinedWorkshop() );
                jsonObject.put("itihaveCombinedWorkshopRemarks", TextUtils.isEmpty(workshopsList.get(i).getItihaveCombinedWorkshopRemarks())?"":workshopsList.get(i).getItihaveCombinedWorkshopRemarks());
                jsonObject.put("itihaveCombinedWorkshopNc", "");
                jsonObject.put("istheDemarcated", TextUtils.isEmpty(workshopsList.get(i).getIstheDemarcated())?"":workshopsList.get(i).getIstheDemarcated());
                jsonObject.put("istheDemarcatedRemarks", TextUtils.isEmpty(workshopsList.get(i).getIstheDemarcatedRemarks())?"":workshopsList.get(i).getIstheDemarcatedRemarks());
                jsonObject.put("istheDemarcatedNc", "");
                jsonObject.put("emergencyContactNumberDisplay", TextUtils.isEmpty(workshopsList.get(i).getEmergencyContactNumberDisplay())?"":workshopsList.get(i).getEmergencyContactNumberDisplay());
                jsonObject.put("emergencyContactNumberDisplayRemarks", TextUtils.isEmpty(workshopsList.get(i).getEmergencyContactNumberDisplayRemarks())?"":workshopsList.get(i).getEmergencyContactNumberDisplayRemarks());
                String nc2 =String.valueOf(workshopsList.get(i).getEmergencyContactNC());
                if(nc2.equalsIgnoreCase("null")){
                    jsonObject.put("emergencyContactNumberDisplayNc","0");
                }else {
                    jsonObject.put("emergencyContactNumberDisplayNc", workshopsList.get(i).getEmergencyContactNC());
                }
                jsonObject.put("WorkShopRoof", TextUtils.isEmpty(workshopsList.get(i).getWorkShopRoof())?"":workshopsList.get(i).getWorkShopRoof());
                jsonObject.put("WorkShopRoof_Remarks", TextUtils.isEmpty(workshopsList.get(i).getWorkShopRoof_Remarks())?"":workshopsList.get(i).getWorkShopRoof_Remarks());
                jsonObject.put("WorkShopRoof_Nc", "");
                jsonObject.put("ceilingHeightlessthan12Feet",TextUtils.isEmpty(workshopsList.get(i).getCeilingHeightlessthan12Feet())?"":workshopsList.get(i).getCeilingHeightlessthan12Feet());
                jsonObject.put("ceilingHeightlessthan12Feet_Remarks", TextUtils.isEmpty(workshopsList.get(i).getCeilingHeightlessthan12Feet_Remarks())?"":workshopsList.get(i).getCeilingHeightlessthan12Feet_Remarks());
                jsonObject.put("ceilingHeightlessthan12Feet_Nc", "");
                jsonObject.put("ceilingHeightlessthan10Feet", TextUtils.isEmpty(workshopsList.get(i).getCeilingHeightlessthan10Feet())?"":workshopsList.get(i).getCeilingHeightlessthan10Feet());
                jsonObject.put("ceilingHeightlessthan10Feet_Remarks", TextUtils.isEmpty(workshopsList.get(i).getCeilingHeightlessthan10FeetRemarks())?"":workshopsList.get(i).getCeilingHeightlessthan10FeetRemarks());
                jsonObject.put("areAlltheMachineryToolsAndEquipment", TextUtils.isEmpty(workshopsList.get(i).getMachinaryTools())?"":workshopsList.get(i).getMachinaryTools());
                jsonObject.put("areAlltheMachineryToolsAndEquipment_Remarks", TextUtils.isEmpty(workshopsList.get(i).getMachinaryToolsRemarks())?"":workshopsList.get(i).getMachinaryToolsRemarks());
                String nc3 =String.valueOf(workshopsList.get(i).getMachinaryToolsNC());
                if(nc3.equalsIgnoreCase("null")){
                    jsonObject.put("areAlltheMachineryToolsAndEquipment_Nc","0");
                }else {
                    jsonObject.put("areAlltheMachineryToolsAndEquipment_Nc", workshopsList.get(i).getMachinaryToolsNC());
                }

                jsonObject.put("areAlltheMachinesComplying", TextUtils.isEmpty(workshopsList.get(i).getMachinesComplying())?"":workshopsList.get(i).getMachinesComplying());
                jsonObject.put("areAlltheMachinesComplying_Remarks", TextUtils.isEmpty(workshopsList.get(i).getMachinesComplyingRemarks())?"":workshopsList.get(i).getMachinesComplyingRemarks());
                String nc4 =String.valueOf(workshopsList.get(i).getMachinesComplyingNC());
                if(nc4.equalsIgnoreCase("null")){
                    jsonObject.put("areAlltheMachinesComplying_Nc","0");
                }else {
                    jsonObject.put("areAlltheMachinesComplying_Nc", workshopsList.get(i).getMachinesComplyingNC());
                }
                jsonObject.put("areRubberMatsAvailable", TextUtils.isEmpty(workshopsList.get(i).getRubberMats())?"":workshopsList.get(i).getRubberMats());
                jsonObject.put("areRubberMatsAvailable_Remarks", TextUtils.isEmpty(workshopsList.get(i).getRubberMatsRemarks())?"":workshopsList.get(i).getRubberMatsRemarks());
                String nc5 =String.valueOf(workshopsList.get(i).getRubberMatsNC());
                if(nc5.equalsIgnoreCase("null")){
                    jsonObject.put("areRubberMatsAvailable_Nc","0");
                }else {
                    jsonObject.put("areRubberMatsAvailable_Nc", workshopsList.get(i).getRubberMatsNC());
                }

                jsonObject.put("istheDGSetRequired", TextUtils.isEmpty(workshopsList.get(i).getDgsetRequired())?"":workshopsList.get(i).getDgsetRequired());
                jsonObject.put("istheDGSetRequired_Remarks", TextUtils.isEmpty(workshopsList.get(i).getDgsetRequiredRemarks())?"":workshopsList.get(i).getDgsetRequiredRemarks());
                jsonObject.put("istheDGSetRequired_Nc", "");
                jsonObject.put("istheDGSetInstalled", TextUtils.isEmpty(workshopsList.get(i).getDgsetInstalled())?"":workshopsList.get(i).getDgsetInstalled());
                jsonObject.put("istheDGSetInstalled_Remarks", TextUtils.isEmpty(workshopsList.get(i).getDgsetInstalledRemarks())?"":workshopsList.get(i).getDgsetInstalledRemarks());
                String nc6 =String.valueOf(workshopsList.get(i).getDgsetInstalledNC());
                if(nc6.equalsIgnoreCase("null")){
                    jsonObject.put("istheDGSetInstalled_Nc","0");
                }else {
                    jsonObject.put("istheDGSetInstalled_Nc", workshopsList.get(i).getDgsetInstalledNC());
                }
                jsonObject.put("ceilingHeightlessthan10Feet_Nc", "");
                jsonObject.put("isWiresandBoardsCovered", TextUtils.isEmpty(workshopsList.get(i).getIsWiresandBoardsCovered())?"":workshopsList.get(i).getIsWiresandBoardsCovered());
                jsonObject.put("isFireExtinguisherAvailable", TextUtils.isEmpty(workshopsList.get(i).getIsFireExtinguisherAvailable())?"":workshopsList.get(i).getIsFireExtinguisherAvailable());
                jsonObject.put("isEmergencyExit", TextUtils.isEmpty(workshopsList.get(i).getIsEmergencyExit())?"":workshopsList.get(i).getIsEmergencyExit());
                jsonObject.put("isWiresandBoardsCovered_Remarks",TextUtils.isEmpty(workshopsList.get(i).getIsWiresandBoardsCoveredRemarks())?"":workshopsList.get(i).getIsWiresandBoardsCoveredRemarks());
                jsonObject.put("isFireExtinguisherAvailable_Remarks",TextUtils.isEmpty(workshopsList.get(i).getIsFireExtinguisherAvailableRemarks())?"":workshopsList.get(i).getIsFireExtinguisherAvailableRemarks());
                jsonObject.put("isEmergencyExit_Remarks",TextUtils.isEmpty(workshopsList.get(i).getIsEmergencyExitRemarks())?"":workshopsList.get(i).getIsEmergencyExitRemarks());
                String nc7 =String.valueOf(workshopsList.get(i).getIsWiresandBoardsCoveredNc());
                if(nc7.equalsIgnoreCase("null")){
                    jsonObject.put("isWiresandBoardsCovered_Nc","0");
                }else {
                    jsonObject.put("isWiresandBoardsCovered_Nc", workshopsList.get(i).getIsWiresandBoardsCoveredNc());
                }
                String nc8 =String.valueOf(workshopsList.get(i).getIsFireExtinguisherAvailableNc());
                if(nc8.equalsIgnoreCase("null")){
                    jsonObject.put("isFireExtinguisherAvailable_Nc","0");
                }else {
                    jsonObject.put("isFireExtinguisherAvailable_Nc", workshopsList.get(i).getIsFireExtinguisherAvailableNc());
                }
                jsonObject.put("isEmergencyExit_Nc", "");
                jsonObject.put("latheMachineRequired", TextUtils.isEmpty(workshopsList.get(i).getLatheRequired())?"":workshopsList.get(i).getLatheRequired());
                jsonObject.put("latheMachineRequired_Remarks", TextUtils.isEmpty(workshopsList.get(i).getLatheRequiredRemarks())?"":workshopsList.get(i).getLatheRequiredRemarks());
                jsonObject.put("latheMachineRequired_Nc", "");
                jsonObject.put("latheMachineInstalledasPerNorms", TextUtils.isEmpty(workshopsList.get(i).getLatheInstalled())?"":workshopsList.get(i).getLatheInstalled());
                jsonObject.put("latheMachineInstalledasPerNorms_Remarks", TextUtils.isEmpty(workshopsList.get(i).getLatheInstalledRemarks())?"":workshopsList.get(i).getLatheInstalledRemarks());
                String nc9 =String.valueOf(workshopsList.get(i).getLatheInstalledNC());
                if(nc9.equalsIgnoreCase("null")){
                    jsonObject.put("latheMachineInstalledasPerNorms_Nc","0");
                }else {
                    jsonObject.put("latheMachineInstalledasPerNorms_Nc", workshopsList.get(i).getLatheInstalledNC());
                }
                jsonObject.put("majorMachine", TextUtils.isEmpty(workshopsList.get(i).getMajorMachine())?"":workshopsList.get(i).getMajorMachine());
                jsonObject.put("majormachineRemarks", TextUtils.isEmpty(workshopsList.get(i).getMajorMachineRemarks())?"":workshopsList.get(i).getMajorMachineRemarks());
                jsonObject.put("majormachineNc", "");


                workshopStaffarray.put(jsonObject);
            }

            workshopArea.put("workshop",workshopStaffarray);
            workshopArea.put("yearwisecollegeid", workshopsList.get(0).getYearWiseCollegeid());

            payloadJsonObj.put("payload",workshopArea);

            resultJsonObj.put("result",payloadJsonObj);

            Log.e("result",resultJsonObj.toString());

        }catch (Exception e){
            e.printStackTrace();
        }

        return resultJsonObj;
    }
}
