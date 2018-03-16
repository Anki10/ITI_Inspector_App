/*
package com.ss.nsdc.fragment;

import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.ss.nsdc.R;
import com.ss.nsdc.constant.AppConstants;
import com.ss.nsdc.dao.ITIDBController;
import com.ss.nsdc.entity.GenralInfo;
import com.ss.nsdc.main.CategoryActivity;
import com.ss.nsdc.main.FormActivity;
import com.ss.nsdc.utility.ControlsUtility;
import com.ss.nsdc.utility.DimensionUtils;
import com.ss.nsdc.utility.UtilityService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;

*/
/**
 * Created by Mayank on 17/09/2016.
 *//*

public class GeneralInfoFragment extends Fragment {

    View view;

    Context context;

    GenralInfo generalInfoModel;

    UtilityService utility = UtilityService.getInstance();

    EditText editRemark;
    Button buttonRemark;
    Button buttonCancel;

    EditText editRemark1;
    Button buttonRemark1;
    Button buttonCancel1;

    ProgressDialog ringProgressDialog;


    Dialog customDialog;

    View customDialogView;
    View customDialogView1;

    TextView textRemarkType;
    TextView textRemarkType1;

    private int ans1edit,ans2edit,ans3edit,ans4edit,ans5edit,ans6edit,ans7edit,ans8edit,ans9edit,ans10edit,
            ans11edit,ans12edit,ans13edit,ans14edit,ans15edit,ans16edit,ans17edit,ans18edit;

    private int ans19edit,ans20edit;

    private EditText editITINameGen;
    private EditText editApplicationNoGen;
    private EditText editTypeInstituteGen;
    private EditText editInstituteTypeGen;
    private Spinner editBiometricDevicesGen;
    private EditText editPhoneNoGen;
    private EditText editFaxNoGen;
    private EditText editMobileNoGen;
    private EditText editEmailGen;
    private EditText editPrincipalNameGen;
    private EditText editAddressGen;
    private EditText editLandmarkGen;
    private EditText editstateGen;
    private EditText editDistrictGen;
    private EditText editTehsilGen;
    private EditText editPincodeGen;
    private EditText editBlockGen;
    private EditText editWebsiteGen;

    private Spinner spinSpecialStatusGen;
    private Spinner spinInstituteLocationGen;
    private Spinner spinSpecialLocationGen;
    private Spinner spinInstituteRunningGen;
    private Spinner editITISignageGen;
    private Spinner editITIBharatGen;
    private Spinner spinTechnical;
    private Spinner spinClassrooms;
    private Spinner spinCombinedWorkshop;
    private Spinner spinDemarcated;
    private Spinner editEngineeringAvailable;
    private Spinner editEngineeringRequired;

    private EditText editEstablishmentDate;


    private ImageView imgITINameGenRemarks;
    private ImageView imgEstablishmentDateRemarks;
    private ImageView imgApplicationNoGenRemarks;
    private ImageView imgTypeInstituteGenRemarks;
    private ImageView imgInstituteTypeGenRemarks;
    private ImageView imgBiometricDevicesGenEdit;
    private ImageView imgBiometricDevicesGenSave;
    private ImageView imgBiometricDevicesGenRemarks;
    private ImageView imgPhoneNoGenEdit;
    private ImageView imgPhoneNoGenSave;
    private ImageView imgPhoneNoGenRemarks;
    private ImageView imgSpecialStatusGenRemarks;
    private ImageView imgFaxNoGenEdit;
    private ImageView imgFaxNoGenSave;
    private ImageView imgFaxNoGenRemarks;
    private ImageView imgInstituteLocationGenRemarks;
    private ImageView imgMobileNoGenEdit;
    private ImageView imgMobileNoGenSave;
    private ImageView imgMobileNoGenRemarks;
    private ImageView imgCombinedWorkshopEdit;
    private ImageView imgCombinedWorkshopSave;
    private ImageView imgCombinedWorkshopRemarks;
    private ImageView imgDemarcatedEdit;
    private ImageView imgDemarcatedSave;
    private ImageView imgDemarcatedRemarks;
    private ImageView imgSpecialLocationGenRemarks;
    private ImageView imgEmailGenEdit;
    private ImageView imgEmailGenSave;
    private ImageView imgEmailGenRemarks;
    private ImageView imgPrincipalNameGenEdit;
    private ImageView imgPrincipalNameGenSave;
    private ImageView imgPrincipalNameGenRemarks;
    private ImageView imgInstituteRunningGenEdit;
    private ImageView imgInstituteRunningGenSave;
    private ImageView imgInstituteRunningGenRemarks;
    private ImageView imgAddressGenEdit;
    private ImageView imgAddressGenSave;
    private ImageView imgAddressGenRemarks;
    private ImageView imgLandmarkGenEdit;
    private ImageView imgLandmarkGenSave;
    private ImageView imgLandmarkGenRemarks;
    private ImageView imgStateGenRemarks;
    private ImageView imgDistrictGenRemarks;
    private ImageView imgTehsilGenEdit;
    private ImageView imgTehsilGenSave;
    private ImageView imgTehsilGenRemarks;
    private ImageView imgPincodeGenEdit;
    private ImageView imgPincodeGenSave;
    private ImageView imgPincodeGenRemarks;
    private ImageView imgBlockGenEdit;
    private ImageView imgBlockGenSave;
    private ImageView imgBlockGenRemarks;
    private ImageView imgWebsiteGenEdit;
    private ImageView imgWebsiteGenSave;
    private ImageView imgWebsiteGenRemarks;
    private ImageView imgITISignageGenEdit;
    private ImageView imgITISignageGenSave;
    private ImageView imgITISignageGenRemarks;
    private ImageView imgITIBharatGenEdit;
    private ImageView imgITIBharatGenSave;
    private ImageView imgITIBharatGenRemarks;
    private ImageView imgTechnicalEdit;
    private ImageView imgTechnicalSave;
    private ImageView imgTechnicalRemarks;
    private ImageView imgClassroomsEdit;
    private ImageView imgClassroomsSave;
    private ImageView imgClassroomsRemarks;
    private ImageView imgEstablishmentDateGenEdit;
    private ImageView imgEstablishmentDateGenSave;
    private ToggleButton imgAddressGenNC;
    private ToggleButton imgITINameGenNC;
    private ToggleButton imgITISignageGenNC;
    private ToggleButton imgBiometricDevicesGenNC;
    private ToggleButton imgWebsiteGenNC;
    private ToggleButton imgTechnicalNC;
    private ToggleButton imgClassroomNC;
    private ToggleButton imgEngineeringRequiredNC;
    private ToggleButton imgEngineeringAvailableNC;
    private LinearLayout demarcated;
    private LinearLayout available;

    private ImageView imgEngineeringRequiredEdit;
    private ImageView imgEngineeringRequiredSave;
    private ImageView imgEngineeringRequiredRemarks;
    private ImageView imgEngineeringAvailableEdit;
    private ImageView imgEngineeringAvailableSave;
    private ImageView imgEngineeringAvailableRemarks;

    public int ans23edit,ans24edit,ans25edit;


    Button btn_submit;

    public GeneralInfoFragment(GenralInfo generalInfoModel) {
        this.generalInfoModel = generalInfoModel;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_general, container, false);
        context = container.getContext();
        Bundle bundle = this.getArguments();
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((FormActivity) getActivity()).getSupportActionBar().setTitle("ITI General Information Details");
        initViews();
        initValues();
        initListeners();

    }

    private void initValues() {


        ScrollView scrollViewGeneral = (ScrollView) view.findViewById(R.id.scrollViewGeneral);
        scrollViewGeneral.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        scrollViewGeneral.setFocusable(true);
        scrollViewGeneral.setFocusableInTouchMode(true);
        scrollViewGeneral.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.requestFocusFromTouch();
                return false;
            }
        });

        if(generalInfoModel.getItiNameNc() == 0){
            imgITINameGenNC.setChecked(false);
        }else {
            imgITINameGenNC.setChecked(true);
        }

        if(generalInfoModel.getItiSignageNc() == 0){
            imgITISignageGenNC.setChecked(false);
        }else {
            imgITISignageGenNC.setChecked(true);
        }

        if(generalInfoModel.getBiometricdeviceNc() == 0){
            imgBiometricDevicesGenNC.setChecked(false);
        }else {
            imgBiometricDevicesGenNC.setChecked(true);
        }

        if(generalInfoModel.getAddressNc() == 0){
            imgAddressGenNC.setChecked(false);
        }else {
            imgAddressGenNC.setChecked(true);
        }

        if(generalInfoModel.getWebsiteNc() == 0){
            imgWebsiteGenNC.setChecked(false);
        }else {
            imgWebsiteGenNC.setChecked(true);
        }

        if(generalInfoModel.getAvailableNC() == 0){
            imgEngineeringAvailableNC.setChecked(false);
        }else {
            imgEngineeringAvailableNC.setChecked(true);
        }


        ControlsUtility.setDefaultEditText(editITINameGen,generalInfoModel.getItiName());

        ControlsUtility.setDefaultEditText(editApplicationNoGen,generalInfoModel.getApplication());

        ControlsUtility.setDefaultEditText(editTypeInstituteGen,generalInfoModel.getTypeInstitute());

        ControlsUtility.setDefaultEditText(editInstituteTypeGen,generalInfoModel.getInstituteType());

        ControlsUtility.setDefaultSpinnerText(editBiometricDevicesGen,generalInfoModel.getBiometricDevice(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultEditText(editPhoneNoGen,generalInfoModel.getPhoneNo());

        ControlsUtility.setDefaultSpinnerText(spinSpecialStatusGen,generalInfoModel.getSpecialStatus(),getResources().getStringArray(R.array.arraySpecialStatus));

        ControlsUtility.setDefaultEditText(editFaxNoGen,generalInfoModel.getFaxNo());

        ControlsUtility.setDefaultSpinnerText(spinInstituteLocationGen,generalInfoModel.getInstituteLocation(),getResources().getStringArray(R.array.arrayInstituteLocation));

        ControlsUtility.setDefaultEditText(editMobileNoGen,generalInfoModel.getMobile());

        ControlsUtility.setDefaultSpinnerText(spinSpecialLocationGen,generalInfoModel.getSpecialLocation(),getResources().getStringArray(R.array.arraySpecialLocation));

        ControlsUtility.setDefaultEditText(editEmailGen,generalInfoModel.getEmail());

        ControlsUtility.setDefaultEditText(editPrincipalNameGen,generalInfoModel.getPrincipalName());

        ControlsUtility.setDefaultSpinnerText(spinInstituteRunningGen,generalInfoModel.getInstituteRunning(),getResources().getStringArray(R.array.arrauInstituteExcellence));

        ControlsUtility.setDefaultEditText(editPrincipalNameGen,generalInfoModel.getPrincipalName());

        ControlsUtility.setDefaultEditText(editAddressGen,generalInfoModel.getAddress());

        ControlsUtility.setDefaultEditText(editEstablishmentDate,generalInfoModel.getDate());

        ControlsUtility.setDefaultEditText(editLandmarkGen,generalInfoModel.getLandmark());

        ControlsUtility.setDefaultEditText(editstateGen,generalInfoModel.getState());

        ControlsUtility.setDefaultEditText(editDistrictGen,generalInfoModel.getDistrict());

        ControlsUtility.setDefaultEditText(editTehsilGen,generalInfoModel.getTehsil());

        ControlsUtility.setDefaultEditText(editPincodeGen,generalInfoModel.getPincode());

        ControlsUtility.setDefaultEditText(editBlockGen,generalInfoModel.getBlock());

        ControlsUtility.setDefaultEditText(editWebsiteGen,generalInfoModel.getWebsite());

        ControlsUtility.setDefaultSpinnerText(editITIBharatGen,generalInfoModel.getItiBharat(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(editITISignageGen,generalInfoModel.getItiSignage(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinCombinedWorkshop,generalInfoModel.getCombined(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinDemarcated,generalInfoModel.getDemarcated(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(editEngineeringRequired,generalInfoModel.getRequired(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(editEngineeringAvailable,generalInfoModel.getAvailable(),getResources().getStringArray(R.array.yes_no));

    }

    private void initListeners() {



        buttonRemark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideDialogKeyboard();
                if (isRemark()) {
                    addRemark(textRemarkType.getText().toString());

                }

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                toHideKeyboard();
                new AlertDialog.Builder(context)
                        .setTitle("Confirmation")
                        .setMessage("This record will not be editable after submit")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                if (checkAllAtempted()) {
                                    generalInfoModel.setTypeInstituteNC(0);
                                    generalInfoModel.setClassroomNc(0);
                                    generalInfoModel.setTechnicalNc(0);
                                    generalInfoModel.setInstitutetypeNc(0);
                                    generalInfoModel.setInstituterunningNC(0);
                                    generalInfoModel.setSpecialstatusNc("0");
                                    generalInfoModel.setDateNc(0);
                                    generalInfoModel.setPhonenoNc(0);
                                    generalInfoModel.setFaxnoNc("0");
                                    generalInfoModel.setInstitutelocationNc("0");
                                    generalInfoModel.setMobileNc(0);
                                    generalInfoModel.setSpeciallocationNc(0);
                                    generalInfoModel.setEmailNc(0);
                                    generalInfoModel.setPrincipalnameNc(0);
                                    generalInfoModel.setSpeciallocationNc(0);
                                    generalInfoModel.setLandmarkNc(0);
                                    generalInfoModel.setStateNc(0);
                                    generalInfoModel.setDistrictNc(0);
                                    generalInfoModel.setTehsilNc(0);
                                    generalInfoModel.setPincodeNc("0");
                                    generalInfoModel.setBlockNc(0);
                                    generalInfoModel.setItiBharatNc(0);
                                    generalInfoModel.setCombinedNc(0);
                                    generalInfoModel.setDemarcatedNc(0);
                                    generalInfoModel.setRequiredNC(0);

                                    if (utility.getConnectivityStatus(context)) {
                                        JSONObject dataToSyncClass = utility.getGeneralInfoSyncData(generalInfoModel);
                                        Log.e("general data ", dataToSyncClass.toString());
                                        new ExecuteSyncOperation().execute(new String[]{AppConstants.URL_GEN_INFO_SET,
                                                dataToSyncClass.toString().replace("&", "and")});

                                    } else {
                                        ITIDBController controller = new ITIDBController(context);
                                        boolean updateStatus = controller.saveGeneralInfo(generalInfoModel, "draft");
                                        controller.close();

                                        if (updateStatus) {
                                            Toast.makeText(context, "Data saved in local storage. Syncup when Internet Connectivity is Available.", Toast.LENGTH_LONG).show();
                                            navigate();
                                        } else {
                                            Toast.makeText(context, "Error in saving..", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                } else {
                                    Toast.makeText(context, "Attempt all questions", Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, null).show();


            }
        });

        imgEstablishmentDateGenEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgEstablishmentDateGenSave,editEstablishmentDate,view);
                ans25edit = 1;
            }
        });

        imgEstablishmentDateGenSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                generalInfoModel.setDate(editEstablishmentDate.getText().toString());
                ControlsUtility.okImageViewAction(imgEstablishmentDateGenSave,editEstablishmentDate,view,ans25edit);
                ans25edit = 2;
            }
        });

        imgEngineeringRequiredEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgEngineeringRequiredSave,editEngineeringRequired,view);
                ans23edit = 1;
            }
        });
        imgEngineeringRequiredSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editEngineeringRequired.getSelectedItem().toString().equalsIgnoreCase("Yes")){
                    available.setVisibility(View.VISIBLE);
                }else {
                    available.setVisibility(View.GONE);
                }
                toHideKeyboard();
                generalInfoModel.setRequired(editEngineeringRequired.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgEngineeringRequiredSave,editEngineeringRequired,view,ans23edit);
                ans23edit = 2;
            }
        });
        imgEngineeringRequiredRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getRequiredRemarks());
                textRemarkType.setText(AppConstants.KEY_REMARKS1);
            }
        });

        imgEngineeringAvailableEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgEngineeringAvailableSave,editEngineeringAvailable,view);
                ans24edit = 1;
            }
        });
        imgEngineeringAvailableSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                generalInfoModel.setAvailable(editEngineeringAvailable.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgEngineeringAvailableSave,editEngineeringAvailable,view,ans24edit);
                ans24edit = 2;
            }
        });
        imgEngineeringAvailableRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getAvailableRemarks());
                textRemarkType.setText(AppConstants.KEY_REMARKS2);
            }
        });

        imgEstablishmentDateRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getDateRemarks());
                textRemarkType.setText(AppConstants.KEY_DATE_REMARKS);
            }
        });

        imgITINameGenRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getItiNameRemarks());
                textRemarkType.setText(AppConstants.KEY_ITI_NAME_REMARKS);
            }
        });


        imgApplicationNoGenRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getApplicationRemarks());
                textRemarkType.setText(AppConstants.KEY_APPLICATION_NO_REMARKS);
            }
        });
        imgTypeInstituteGenRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getTypeInstituteRemark());
                textRemarkType.setText(AppConstants.KEY_TYPE_INSTITUTE);
            }
        });

        imgInstituteTypeGenRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getInstitutetypeRemarks());
                textRemarkType.setText(AppConstants.KEY_INSTITUTE_TYPE_REMARKS);
            }
        });
        imgBiometricDevicesGenEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ControlsUtility.editImageViewAction(imgBiometricDevicesGenSave,editBiometricDevicesGen,view);
                ans1edit = 1;
            }
        });
        imgBiometricDevicesGenSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                generalInfoModel.setBiometricDevice(editBiometricDevicesGen.getSelectedItem().toString());
               ControlsUtility.okImageViewAction(imgBiometricDevicesGenSave,editBiometricDevicesGen,view,ans1edit);
                ans1edit = 2;
            }
        });
        imgBiometricDevicesGenRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getBiometricdeviceRemarks());
                textRemarkType.setText(AppConstants.KEY_BIOMETRIC_REMARKS);
            }
        });
        imgPhoneNoGenEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              ControlsUtility.editImageViewAction(imgPhoneNoGenSave,editPhoneNoGen,view);
                ans2edit =1;
            }
        });
        imgPhoneNoGenSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                generalInfoModel.setPhoneNo(editPhoneNoGen.getText().toString());
                ControlsUtility.okImageViewAction(imgPhoneNoGenSave,editPhoneNoGen,view,ans2edit);
                ans2edit = 2;

            }
        });
        imgPhoneNoGenRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getPhonenoRemarks());
                textRemarkType.setText(AppConstants.KEY_PHONE_REMARKS);
            }
        });

        imgSpecialStatusGenRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getSpecialstatusRemarks());
                textRemarkType.setText(AppConstants.KEY_SPECIAL_STATUS_REMARKS);
            }
        });
        imgFaxNoGenEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgFaxNoGenSave,editFaxNoGen,view);
                ans3edit =1;
            }
        });

        imgFaxNoGenSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                generalInfoModel.setFaxNo(editFaxNoGen.getText().toString());
                ControlsUtility.okImageViewAction(imgFaxNoGenSave,editFaxNoGen,view,ans3edit);
                ans3edit = 2;
            }
        });

        imgFaxNoGenRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getFaxnoRemarks());
                textRemarkType.setText(AppConstants.KEY_FAX_NO_REMARKS);

            }
        });
        imgInstituteLocationGenRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getInstitutelocationRemarks());
                textRemarkType.setText(AppConstants.KEY_INSTITUTE_LOC_REMARKS);
            }
        });

        imgMobileNoGenEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgMobileNoGenSave,editMobileNoGen,view);
                ans4edit = 1;
            }
        });
        imgMobileNoGenSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                generalInfoModel.setMobile(editMobileNoGen.getText().toString());
                ControlsUtility.okImageViewAction(imgMobileNoGenSave,editMobileNoGen,view,ans4edit);
                ans4edit = 2;
            }
        });
        imgMobileNoGenRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getMobileRemarks());
                textRemarkType.setText(AppConstants.KEY_MOBILE_NO_REMARKS);
            }
        });
        imgSpecialLocationGenRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getSpeciallocationRemarks());
                textRemarkType.setText(AppConstants.KEY_SPECIAL_LOC_REMARKS);
            }
        });
        imgEmailGenEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgEmailGenSave,editEmailGen,view);
                ans5edit = 1;
            }
        });
        imgEmailGenSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                generalInfoModel.setEmail(editEmailGen.getText().toString());
                ControlsUtility.okImageViewAction(imgEmailGenSave,editEmailGen,view,ans5edit);
                ans5edit = 2;
            }
        });
        imgEmailGenRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getEmailRemarks());
                textRemarkType.setText(AppConstants.KEY_EMAILS_REMARKS);
            }
        });
        imgPrincipalNameGenEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgPrincipalNameGenSave,editPrincipalNameGen,view);
                ans6edit = 1;
            }
        });
        imgPrincipalNameGenSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                generalInfoModel.setPrincipalName(editPrincipalNameGen.getText().toString());
                ControlsUtility.okImageViewAction(imgPrincipalNameGenSave,editPrincipalNameGen,view,ans6edit);
                ans6edit = 2;
            }
        });
        imgPrincipalNameGenRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getPrincipalnameRemarks());
                textRemarkType.setText(AppConstants.KEY_PRINCIPAL_NAME_REMARKS);
            }
        });
        imgInstituteRunningGenEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgInstituteRunningGenSave,spinInstituteRunningGen,view);
                ans7edit = 1;
            }
        });
        imgInstituteRunningGenSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                generalInfoModel.setInstituteRunning(spinInstituteRunningGen.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgInstituteRunningGenSave,spinInstituteRunningGen,view,ans7edit);
                ans7edit = 2;
            }
        });
        imgInstituteRunningGenRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getInstituterunningRemarks());
                textRemarkType.setText(AppConstants.KEY_INSTITUTE_RUN_REMARKS);
            }
        });
        imgAddressGenEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgAddressGenSave,editAddressGen,view);
                ans8edit = 1;
            }
        });
        imgAddressGenSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                generalInfoModel.setAddress(editAddressGen.getText().toString());
                ControlsUtility.okImageViewAction(imgAddressGenSave,editAddressGen,view,ans8edit);
                ans8edit = 2;
            }
        });
        imgAddressGenRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getAddressRemarks());
                textRemarkType.setText(AppConstants.KEY_ADDRESS_REMARKS);
            }
        });
        imgLandmarkGenEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgLandmarkGenSave,editLandmarkGen,view);
                ans9edit = 1;
            }
        });
        imgLandmarkGenSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                generalInfoModel.setLandmark(editLandmarkGen.getText().toString());
                ControlsUtility.okImageViewAction(imgLandmarkGenSave,editLandmarkGen,view,ans9edit);
                ans9edit = 2;
            }
        });
        imgLandmarkGenRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getLandmarkRemarks());
                textRemarkType.setText(AppConstants.KEY_LANDMARK_REMARKS);
            }
        });

        imgStateGenRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getStateRemarks());
                textRemarkType.setText(AppConstants.KEY_STATE_REMARSK);

            }
        });
        imgDistrictGenRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getDistrictRemarks());
                textRemarkType.setText(AppConstants.KEY_DISTRICT_REMARKS);

            }
        });

        imgTehsilGenEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgTehsilGenSave,editTehsilGen,view);
                ans10edit = 1;
            }
        });
        imgTehsilGenSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                generalInfoModel.setTehsil(editTehsilGen.getText().toString());
                ControlsUtility.okImageViewAction(imgTehsilGenSave,editTehsilGen,view,ans10edit);
                ans10edit = 2;
            }
        });
        imgTehsilGenRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getTehsilRemarks());
                textRemarkType.setText(AppConstants.KEY_TEHSIL_REMARKS);
            }
        });
        imgPincodeGenEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgPincodeGenSave,editPincodeGen,view);
                ans11edit = 1;
            }
        });
        imgPincodeGenSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                generalInfoModel.setPincode(editPincodeGen.getText().toString());
                ControlsUtility.okImageViewAction(imgPincodeGenSave,editPincodeGen,view,ans11edit);
                ans11edit = 2;
            }
        });
        imgPincodeGenRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getPincodeRemarks());
                textRemarkType.setText(AppConstants.KEY_PINCODE_REMARKS);
            }
        });
        imgBlockGenEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgBlockGenSave,editBlockGen,view);
                ans12edit = 1;
            }
        });
        imgBlockGenSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                generalInfoModel.setBlock(editBlockGen.getText().toString());
                ControlsUtility.okImageViewAction(imgBlockGenSave,editBlockGen,view,ans12edit);
                ans12edit = 2;
            }
        });
        imgBlockGenRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getBlockRemarks());
                textRemarkType.setText(AppConstants.KEY_BLOCK_REMARKS);
            }
        });
        imgWebsiteGenEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgWebsiteGenSave,editWebsiteGen,view);
                ans13edit = 1;
            }
        });
        imgWebsiteGenSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                generalInfoModel.setWebsite(editWebsiteGen.getText().toString());
                ControlsUtility.okImageViewAction(imgWebsiteGenSave,editWebsiteGen,view,ans13edit);
                ans13edit = 2;
            }
        });
        imgWebsiteGenRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getWebsiteRemarks());
                textRemarkType.setText(AppConstants.KEY_WEBSITE_REMARKS);
            }
        });
        imgITIBharatGenEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgITIBharatGenSave,editITIBharatGen,view);
                ans15edit = 1;
            }
        });

        imgITIBharatGenSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                generalInfoModel.setItiBharat(editITIBharatGen.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgITIBharatGenSave,editITIBharatGen,view,ans15edit);
                ans15edit = 2;
            }
        });

        imgITIBharatGenRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getItiBharatRemarks());
                textRemarkType.setText(AppConstants.KEY_BHARAT_REMARKS);
            }
        });

        imgITISignageGenEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgITISignageGenSave,editITISignageGen,view);
                ans14edit = 1;
            }
        });

        imgITISignageGenSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                generalInfoModel.setItiSignage(editITISignageGen.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgITISignageGenSave,editITISignageGen,view,ans14edit);
                ans14edit = 2;
            }
        });

        imgITISignageGenRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getItiSignageRemarks());
                textRemarkType.setText(AppConstants.KEY_SIGNAGE_REMARKS);
            }
        });

        imgTechnicalEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgTechnicalSave,spinTechnical,view);
                ans17edit = 1;
            }
        });

        imgTechnicalSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                generalInfoModel.setTechnical(spinTechnical.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgTechnicalSave,spinTechnical,view,ans17edit);
                ans17edit = 2;
            }
        });

        imgTechnicalRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getTechnicalRemarks());
                textRemarkType.setText(AppConstants.KEY_TECHNICAL_REMARKS);
            }
        });

        imgClassroomsEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgClassroomsSave,spinClassrooms,view);
                ans18edit = 1;
            }
        });

        imgClassroomsSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                generalInfoModel.setClassroom(spinClassrooms.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgClassroomsSave,spinClassrooms,view,ans18edit);
                ans18edit = 2;
            }
        });

        imgClassroomsRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getClassroomRemarks());
                textRemarkType.setText(AppConstants.KEY_CLASSROOM_REMARKS);
            }
        });

        imgCombinedWorkshopEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgCombinedWorkshopSave,spinCombinedWorkshop,view);
                ans19edit = 1;
            }
        });

        imgCombinedWorkshopSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinCombinedWorkshop.getSelectedItem().toString().equalsIgnoreCase("No")){
                    demarcated.setVisibility(View.GONE);
                }else {
                    demarcated.setVisibility(View.VISIBLE);
                }
                toHideKeyboard();
                generalInfoModel.setCombined(spinCombinedWorkshop.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgCombinedWorkshopSave,spinCombinedWorkshop,view,ans19edit);
                ans19edit = 2;
            }
        });

        imgCombinedWorkshopRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getCombinedRemarks());
                textRemarkType.setText(AppConstants.KEY_COMBINED_REMARKS);
            }
        });

        imgDemarcatedEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgDemarcatedSave,spinDemarcated,view);
                ans20edit = 1;
            }
        });

        imgDemarcatedSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                generalInfoModel.setDemarcated(spinDemarcated.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgDemarcatedSave,spinDemarcated,view,ans20edit);
                ans20edit = 2;
            }
        });

        imgDemarcatedRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(generalInfoModel.getDemarcatedRemarks());
                textRemarkType.setText(AppConstants.KEY_DEMARCATED_REMARKS);
            }
        });





        imgAddressGenNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgAddressGenNC.isChecked()){
                    generalInfoModel.setAddressNc(1);
                }else {
                    generalInfoModel.setAddressNc(0);
                }
            }
        });



        imgITINameGenNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgITINameGenNC.isChecked()){
                    generalInfoModel.setItiNameNc(1);
                }else {
                    generalInfoModel.setItiNameNc(0);
                }
            }
        });


        imgITISignageGenNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgITISignageGenNC.isChecked()){
                    generalInfoModel.setItiSignageNc(1);
                }else {
                    generalInfoModel.setItiSignageNc(0);
                }
            }
        });

        imgBiometricDevicesGenNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgBiometricDevicesGenNC.isChecked()){
                    generalInfoModel.setBiometricdeviceNc(1);
                }else {
                    generalInfoModel.setBiometricdeviceNc(0);
                }
            }
        });

        imgWebsiteGenNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgWebsiteGenNC.isChecked()){
                    generalInfoModel.setWebsiteNc(1);
                }else {
                    generalInfoModel.setWebsiteNc(0);
                }
            }
        });

        imgEngineeringRequiredNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgEngineeringRequiredNC.isChecked()){
                    generalInfoModel.setRequiredNC(1);
                }else {
                    generalInfoModel.setRequiredNC(0);
                }
            }
        });

        imgEngineeringAvailableNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgEngineeringAvailableNC.isChecked()){
                    generalInfoModel.setAvailableNC(1);
                }else {
                    generalInfoModel.setAvailableNC(0);
                }
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideDialogKeyboard();
                cancelCustomDialog();
            }
        });

    }


      private void initViews() {

         customDialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_remark, null, false);


        editRemark = (EditText) customDialogView.findViewById(R.id.editRemark);
        buttonRemark = (Button) customDialogView.findViewById(R.id.buttonRemark);
        buttonCancel = (Button) customDialogView.findViewById(R.id.buttonCancel);
        textRemarkType = (TextView) customDialogView.findViewById(R.id.textRemarkType);
          textRemarkType1 = (TextView) customDialogView.findViewById(R.id.remarks);
          demarcated = (LinearLayout)view.findViewById(R.id.demarcated);
        editITINameGen = (EditText)view.findViewById(R.id.editITINameGen);
        editApplicationNoGen = (EditText)view.findViewById(R.id.editApplicationNoGen);
        editTypeInstituteGen = (EditText)view.findViewById(R.id.editTypeInstituteGen);
        editInstituteTypeGen = (EditText)view.findViewById(R.id.editInstituteTypeGen);
        editBiometricDevicesGen = (Spinner) view.findViewById(R.id.editBiometricDevicesGen);
        editPhoneNoGen = (EditText)view.findViewById(R.id.editPhoneNoGen);
        editFaxNoGen = (EditText)view.findViewById(R.id.editFaxNoGen);
        editMobileNoGen = (EditText)view.findViewById(R.id.editMobileNoGen);
        editEmailGen = (EditText)view.findViewById(R.id.editEmailGen);
        editPrincipalNameGen = (EditText)view.findViewById(R.id.editPrincipalNameGen);
        editAddressGen = (EditText)view.findViewById(R.id.editAddressGen);
        editLandmarkGen = (EditText)view.findViewById(R.id.editLandmarkGen);
        editstateGen = (EditText)view.findViewById(R.id.editstateGen);
        editDistrictGen = (EditText)view.findViewById(R.id.editDistrictGen);
        editTehsilGen = (EditText)view.findViewById(R.id.editTehsilGen);
        editPincodeGen = (EditText)view.findViewById(R.id.editPincodeGen);
        editBlockGen = (EditText)view.findViewById(R.id.editBlockGen);
        editWebsiteGen = (EditText)view.findViewById(R.id.editWebsiteGen);
          editITISignageGen = (Spinner)view.findViewById(R.id.editITISignageGen);
          editITIBharatGen = (Spinner)view.findViewById(R.id.editITIBharatGen);
          spinTechnical = (Spinner)view.findViewById(R.id.spinTechnical);
          spinClassrooms = (Spinner)view.findViewById(R.id.spinClassrooms);
          spinCombinedWorkshop = (Spinner)view.findViewById(R.id.spinCombinedWorkshop);
          spinDemarcated = (Spinner)view.findViewById(R.id.spinDemarcated);
          editEstablishmentDate = (EditText)view.findViewById(R.id.editEstablishmentDateGen);




        spinSpecialStatusGen = (Spinner)view.findViewById(R.id.spinSpecialStatusGen);
        spinInstituteLocationGen = (Spinner)view.findViewById(R.id.spinInstituteLocationGen);
        spinSpecialLocationGen = (Spinner)view.findViewById(R.id.spinSpecialLocationGen);
        spinInstituteRunningGen = (Spinner)view.findViewById(R.id.spinInstituteRunningGen);

          editEngineeringAvailable = (Spinner)view.findViewById(R.id.editEngineeringAvailable);
          editEngineeringRequired = (Spinner)view.findViewById(R.id.editEngineeringRequired);


          imgEngineeringRequiredEdit = (ImageView)view.findViewById(R.id.imgEngineeringRequiredEdit);
          imgEngineeringRequiredSave = (ImageView)view.findViewById(R.id.imgEngineeringRequiredSave);
          imgEngineeringRequiredRemarks = (ImageView)view.findViewById(R.id.imgEngineeringRequiredRemarks);
          imgEngineeringAvailableEdit = (ImageView)view.findViewById(R.id.imgEngineeringAvailableEdit);
          imgEngineeringAvailableSave = (ImageView)view.findViewById(R.id.imgEngineeringAvailableSave);
          imgEngineeringAvailableRemarks = (ImageView)view.findViewById(R.id.imgEngineeringAvailableRemarks);


        imgITINameGenRemarks = (ImageView)view.findViewById(R.id.imgITINameGenRemarks);
          imgEstablishmentDateRemarks = (ImageView)view.findViewById(R.id.imgEstablishmentDateGenRemarks);
        imgApplicationNoGenRemarks = (ImageView)view.findViewById(R.id.imgApplicationNoGenRemarks);
        imgTypeInstituteGenRemarks = (ImageView)view.findViewById(R.id.imgTypeInstituteGenRemarks);
        imgInstituteTypeGenRemarks = (ImageView)view.findViewById(R.id.imgInstituteTypeGenRemarks);
        imgBiometricDevicesGenEdit = (ImageView)view.findViewById(R.id.imgBiometricDevicesGenEdit);
        imgBiometricDevicesGenSave = (ImageView)view.findViewById(R.id.imgBiometricDevicesGenSave);
        imgBiometricDevicesGenRemarks = (ImageView)view.findViewById(R.id.imgBiometricDevicesGenRemarks);
        imgPhoneNoGenEdit = (ImageView)view.findViewById(R.id.imgPhoneNoGenEdit);
        imgPhoneNoGenSave = (ImageView)view.findViewById(R.id.imgPhoneNoGenSave);
        imgPhoneNoGenRemarks = (ImageView)view.findViewById(R.id.imgPhoneNoGenRemarks);
        imgSpecialStatusGenRemarks = (ImageView)view.findViewById(R.id.imgSpecialStatusGenRemarks);
        imgFaxNoGenEdit = (ImageView)view.findViewById(R.id.imgFaxNoGenEdit);
        imgFaxNoGenSave = (ImageView)view.findViewById(R.id.imgFaxNoGenSave);
        imgFaxNoGenRemarks = (ImageView)view.findViewById(R.id.imgFaxNoGenRemarks);
          imgInstituteLocationGenRemarks = (ImageView)view.findViewById(R.id.imgInstituteLocationGenRemarks);
        imgMobileNoGenEdit = (ImageView)view.findViewById(R.id.imgMobileNoGenEdit);
        imgMobileNoGenSave = (ImageView)view.findViewById(R.id.imgMobileNoGenSave);
        imgMobileNoGenRemarks = (ImageView)view.findViewById(R.id.imgMobileNoGenRemarks);
        imgSpecialLocationGenRemarks = (ImageView)view.findViewById(R.id.imgSpecialLocationGenRemarks);
        imgEmailGenEdit = (ImageView)view.findViewById(R.id.imgEmailGenEdit);
        imgEmailGenSave = (ImageView)view.findViewById(R.id.imgEmailGenSave);
        imgEmailGenRemarks = (ImageView)view.findViewById(R.id.imgEmailGenRemarks);
        imgPrincipalNameGenEdit = (ImageView)view.findViewById(R.id.imgPrincipalNameGenEdit);
        imgPrincipalNameGenSave = (ImageView)view.findViewById(R.id.imgPrincipalNameGenSave);
        imgPrincipalNameGenRemarks = (ImageView)view.findViewById(R.id.imgPrincipalNameGenRemarks);
        imgInstituteRunningGenEdit = (ImageView)view.findViewById(R.id.imgInstituteRunningGenEdit);
        imgInstituteRunningGenSave = (ImageView)view.findViewById(R.id.imgInstituteRunningGenSave);
        imgInstituteRunningGenRemarks = (ImageView)view.findViewById(R.id.imgInstituteRunningGenRemarks);
        imgAddressGenEdit = (ImageView)view.findViewById(R.id.imgAddressGenEdit);
        imgAddressGenSave = (ImageView)view.findViewById(R.id.imgAddressGenSave);
        imgAddressGenRemarks = (ImageView)view.findViewById(R.id.imgAddressGenRemarks);
        imgLandmarkGenEdit = (ImageView)view.findViewById(R.id.imgLandmarkGenEdit);
        imgLandmarkGenSave = (ImageView)view.findViewById(R.id.imgLandmarkGenSave);
        imgLandmarkGenRemarks = (ImageView)view.findViewById(R.id.imgLandmarkGenRemarks);
        imgStateGenRemarks = (ImageView)view.findViewById(R.id.imgStateGenRemarks);
        imgDistrictGenRemarks = (ImageView)view.findViewById(R.id.imgDistrictGenRemarks);
        imgTehsilGenEdit = (ImageView)view.findViewById(R.id.imgTehsilGenEdit);
        imgTehsilGenSave = (ImageView)view.findViewById(R.id.imgTehsilGenSave);
        imgTehsilGenRemarks = (ImageView)view.findViewById(R.id.imgTehsilGenRemarks);
        imgPincodeGenEdit = (ImageView)view.findViewById(R.id.imgPincodeGenEdit);
        imgPincodeGenSave = (ImageView)view.findViewById(R.id.imgPincodeGenSave);
        imgPincodeGenRemarks = (ImageView)view.findViewById(R.id.imgPincodeGenRemarks);
        imgBlockGenEdit = (ImageView)view.findViewById(R.id.imgBlockGenEdit);
        imgBlockGenSave = (ImageView)view.findViewById(R.id.imgBlockGenSave);
        imgBlockGenRemarks = (ImageView)view.findViewById(R.id.imgBlockGenRemarks);
        imgWebsiteGenEdit = (ImageView)view.findViewById(R.id.imgWebsiteGenEdit);
        imgWebsiteGenSave = (ImageView)view.findViewById(R.id.imgWebsiteGenSave);
        imgWebsiteGenRemarks = (ImageView)view.findViewById(R.id.imgWebsiteGenRemarks);
          imgCombinedWorkshopEdit = (ImageView)view.findViewById(R.id.imgCombinedWorkshopEdit);
          imgCombinedWorkshopSave = (ImageView)view.findViewById(R.id.imgCombinedWorkshopSave);
          imgCombinedWorkshopRemarks = (ImageView)view.findViewById(R.id.imgCombinedWorkshopRemarks);
          imgDemarcatedEdit = (ImageView)view.findViewById(R.id.imgDemarcatedEdit);
          imgDemarcatedSave = (ImageView)view.findViewById(R.id.imgDemarcatedSave);
          imgDemarcatedRemarks = (ImageView)view.findViewById(R.id.imgDemarcatedRemarks);
          imgTechnicalEdit = (ImageView)view.findViewById(R.id.imgTechnicalEdit);
          imgTechnicalSave = (ImageView)view.findViewById(R.id.imgTechnicalSave);
          imgTechnicalRemarks = (ImageView)view.findViewById(R.id.imgTechnicalRemarks);
          imgEstablishmentDateGenEdit = (ImageView)view.findViewById(R.id.imgEstablishmentDateGenEdit);
          imgEstablishmentDateGenSave = (ImageView)view.findViewById(R.id.imgEstablishmentDateGenSave);

          imgEngineeringAvailableNC = (ToggleButton)view.findViewById(R.id.imgEngineeringAvailableNC);
          imgEngineeringRequiredNC = (ToggleButton)view.findViewById(R.id.imgEngineeringRequiredNC);

          available = (LinearLayout)view.findViewById(R.id.available);
          imgClassroomsEdit = (ImageView)view.findViewById(R.id.imgClassroomsEdit);
          imgClassroomsSave = (ImageView)view.findViewById(R.id.imgClassroomsSave);
          imgClassroomsRemarks = (ImageView)view.findViewById(R.id.imgClassroomsRemarks);
          imgITISignageGenEdit = (ImageView)view.findViewById(R.id.imgITISignageGenEdit);
          imgITISignageGenSave = (ImageView)view.findViewById(R.id.imgITISignageGenSave);
          imgITISignageGenRemarks = (ImageView)view.findViewById(R.id.imgITISignageGenRemarks);
          imgITIBharatGenEdit = (ImageView)view.findViewById(R.id.imgITIBharatGenEdit);
          imgITIBharatGenSave = (ImageView)view.findViewById(R.id.imgITIBharatGenSave);
          imgITIBharatGenRemarks = (ImageView)view.findViewById(R.id.imgITIBharatGenRemarks);
          imgAddressGenNC = (ToggleButton) view.findViewById(R.id.imgAddressGenNC);
          imgITINameGenNC = (ToggleButton)view.findViewById(R.id.imgITINameGenNC);
          imgITISignageGenNC = (ToggleButton)view.findViewById(R.id.imgITISignageGenNC);
          imgBiometricDevicesGenNC = (ToggleButton)view.findViewById(R.id.imgBiometricDevicesGenNC);
          imgWebsiteGenNC = (ToggleButton)view.findViewById(R.id.imgWebsiteGenNC);
          imgTechnicalNC = (ToggleButton)view.findViewById(R.id.imgTechnicalNC);
          imgClassroomNC = (ToggleButton)view.findViewById(R.id.imgClassroomsNC);



        btn_submit = (Button)view.findViewById(R.id.btnSubmit);

    }

    public void hideDialogKeyboard() {

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editRemark.getWindowToken(), 0);

        // customDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private boolean isRemark() {
        editRemark.setError(null);
        if (editRemark.getText().toString().isEmpty()) {
            editRemark.setError(getResources().getString(R.string.labelEmptyRemark));
            return false;
        }

        return true;
    }

    private void addRemark(String type) {

        switch (type){
            case AppConstants.KEY_ITI_NAME_REMARKS:
                generalInfoModel.setItiNameRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_APPLICATION_NO_REMARKS:
                generalInfoModel.setApplicationRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_BIOMETRIC_REMARKS:
                generalInfoModel.setBiometricdeviceRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_TYPE_INSTITUTE:
                generalInfoModel.setTypeInstituteRemark(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_DATE_REMARKS:
                generalInfoModel.setDateRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_INSTITUTE_TYPE_REMARKS:
                generalInfoModel.setInstitutetypeRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_PHONE_REMARKS:
                generalInfoModel.setPhonenoRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_SPECIAL_STATUS_REMARKS:
                generalInfoModel.setSpecialstatusRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_FAX_NO_REMARKS:
                generalInfoModel.setFaxnoRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_INSTITUTE_LOC_REMARKS:
                generalInfoModel.setInstitutelocationRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_MOBILE_NO_REMARKS:
                generalInfoModel.setMobileRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_EMAILS_REMARKS:
                generalInfoModel.setEmailRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_PRINCIPAL_NAME_REMARKS:
                generalInfoModel.setPrincipalnameRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_ADDRESS_REMARKS:
                generalInfoModel.setAddressRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_INSTITUTE_RUN_REMARKS:
                generalInfoModel.setInstituterunningRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_LANDMARK_REMARKS:
                generalInfoModel.setLandmarkRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_STATE_REMARSK:
                generalInfoModel.setStateRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_DISTRICT_REMARKS:
                generalInfoModel.setDistrictRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_TEHSIL_REMARKS:
                generalInfoModel.setTehsilRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_PINCODE_REMARKS:
                generalInfoModel.setPincodeRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_BLOCK_REMARKS:
                generalInfoModel.setBlockRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_WEBSITE_REMARKS:
                generalInfoModel.setWebsiteRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_SIGNAGE_REMARKS:
                generalInfoModel.setItiSignageRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_BHARAT_REMARKS:
                generalInfoModel.setItiBharatRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_TECHNICAL_REMARKS:
                generalInfoModel.setTechnicalRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_CLASSROOM_REMARKS:
                generalInfoModel.setClassroomRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_SPECIAL_LOC_REMARKS:
                generalInfoModel.setSpeciallocationRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_COMBINED_REMARKS:
                generalInfoModel.setCombinedRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_DEMARCATED_REMARKS:
                generalInfoModel.setDemarcatedRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_REMARKS1:
                generalInfoModel.setRequiredRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_REMARKS2:
                generalInfoModel.setAvailableRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;



        }

    }

    protected void toHideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    public Dialog creatingDialog(boolean isCancelableBack, boolean isCancelableoutside, View view, int height, int width, boolean heightMatchParent) {
        height = 260;

        if (editRemark != null) {
            editRemark.setError(null);
        }
        customDialog = new Dialog(context, R.style.dialogTheme);
        //  dialog.setCancelable(isCancelableBack);
        if (view.getParent() != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            viewGroup.removeView(view);
        }
        customDialog.setCanceledOnTouchOutside(isCancelableoutside);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Window window = customDialog.getWindow();
        window.setGravity(Gravity.CENTER);
      */
/*  WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.width = Helper.toPixels(context, 200);
        layoutParams.height = Helper.toPixels(context, 200);
        dialog.getWindow().setAttributes(layoutParams);*//*

        customDialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        customDialog.setContentView(view);
        customDialog.show();
        if (heightMatchParent) {
            customDialog.getWindow().setLayout(DimensionUtils.toPixels(context, width), WindowManager.LayoutParams.MATCH_PARENT);
        } else {
            customDialog.getWindow().setLayout(DimensionUtils.toPixels(context, width), DimensionUtils.toPixels(context, height));

        }

        return customDialog;

    }

    private boolean checkAllAtempted() {

        if(ans1edit == 2 && ans2edit == 2&& ans4edit == 2&& ans5edit == 2&& ans6edit == 2
                && ans8edit == 2 && ans9edit == 2 && ans10edit == 2 && ans11edit ==2 && ans12edit == 2 && ans13edit == 2){
            return true;
        }else {
            return false;
        }
    }

    class ExecuteSyncOperation extends AsyncTask<String, Integer, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(context, android.R.style.Theme_Holo), null, " Data Synchronizing ...", true);
            ringProgressDialog.setCancelable(false);
            ringProgressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected JSONObject doInBackground(String... data) {
            Log.e("job submit json", data[1]);
            String response = "";
            JSONObject response_json = null;
            try {
                URL url = new URL(data[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json");

                String str = data[1];
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
                } else if (responseCode == HttpURLConnection.HTTP_CLIENT_TIMEOUT) {
                    response_json = new JSONObject();
                    response_json.put("error", "Connection to server lost..");
                }

                response_json = new JSONObject(response);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response_json;
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
                    boolean updation_status = controller.saveGeneralInfo(generalInfoModel, "complete");
                    controller.close();
                    if (updation_status) {
                        btn_submit.setEnabled(false);
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

    public void navigate() {
        Intent intent = new Intent(context, CategoryActivity.class);
        intent.putExtra("YearWiseCollegeId", generalInfoModel.getYearWiseCollegeid());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

    }

    public void cancelCustomDialog() {
        if (customDialog != null) {
            customDialog.cancel();
        }
        toHideKeyboard();
    }

}
*/
