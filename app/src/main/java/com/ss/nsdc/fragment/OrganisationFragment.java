/**
 *
 */
package com.ss.nsdc.fragment;

import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.ss.nsdc.R;
import com.ss.nsdc.constant.AppConstants;
import com.ss.nsdc.dao.ITIDBController;
import com.ss.nsdc.entity.MaterialInfo;
import com.ss.nsdc.main.CategoryActivity;
import com.ss.nsdc.main.FormActivity;
import com.ss.nsdc.utility.ControlsUtility;
import com.ss.nsdc.utility.DimensionUtils;
import com.ss.nsdc.utility.UtilityService;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Vishal
 */
public class OrganisationFragment extends Fragment {

    View view;
    Context context;
    EditText editRemark;
    Button buttonRemark;
    Button buttonCancel;

    ProgressDialog ringProgressDialog;

    Dialog customDialog;

    View customDialogView;

    TextView textRemarkType;
    UtilityService utility = UtilityService.getInstance();

    private int ans1edit,ans2edit,ans3edit,ans4edit,ans5edit,ans6edit,ans7edit,ans8edit,ans9edit,
            ans10edit,ans11edit,ans12edit,ans13edit,ans14edit,ans15edit,ans16edit,ans17edit;

    private EditText editOrgName;
    private EditText editOrgAuthroizedPerson;
    private EditText editOrgAuthroizedPersonUID;
    private EditText editOrgAuthroizedPersonRole;
    private EditText editOrgMobile;
    private EditText editOrgPhone;
    private EditText editOrgEmail;
    private EditText editOrgFax;
    private EditText editOrgAddress;
    private EditText editOrgLandmark;
    private EditText editOrgState;
    private EditText editOrgDistrict;
    private EditText editOrgTehsil;
    private EditText editOrgPincode;
    private EditText editOrgWebsite;
    private EditText editAffiliation;
    private EditText editReference;
    private EditText editCode;

    private Spinner spinNCVTAffilated;


    private ImageView imgOrgNameRemarks;
    private ImageView imgOrgAuthorizedPersonnEdit;
    private ImageView imgOrgAuthorizedPersonnSave;
    private ImageView imgOrgAuthorizedPersonnRemarks;
    private ImageView imgOrgAuthorizedPersonUIDEdit;
    private ImageView imgOrgAuthorizedPersonUIDSave;
    private ImageView imgOrgAuthorizedPersonUIDRemarks;
    private ImageView imgOrgAuthorizedPersonRoleEdit;
    private ImageView imgOrgAuthorizedPersonRoleSave;
    private ImageView imgOrgAuthorizedPersonRoleRemarks;
    private ImageView imgOrgMobileEdit;
    private ImageView imgOrgMobileSave;
    private ImageView imgOrgMobileRemarks;
    private ImageView imgOrgPhoneEdit;
    private ImageView imgOrgPhoneSave;
    private ImageView imgOrgPhoneRemarks;
    private ImageView imgOrgEmailEdit;
    private ImageView imgOrgEmailRemarks;
    private ImageView imgOrgEmailSave;
    private ImageView imgOrgFaxEdit;
    private ImageView imgOrgFaxSave;
    private ImageView imgOrgFaxRemarks;
    private ImageView imgOrgAddressEdit;
    private ImageView imgOrgAddressSave;
    private ImageView imgOrgAddressRemarks;
    private ImageView imgOrgLandmarkEdit;
    private ImageView imgOrgLandmarkSave;
    private ImageView imgOrgLandmarkRemarks;
    private ImageView imgOrgStateRemarks;
    private ImageView imgOrgDistrictRemarks;
    private ImageView imgOrgTehsilEdit;
    private ImageView imgOrgTehsilSave;
    private ImageView imgOrgTehsilRemarks;
    private ImageView imgOrgPincodeEdit;
    private ImageView imgOrgPincodeSave;
    private ImageView imgOrgPincodeRemarks;
    private ImageView imgOrgWebsiteEdit;
    private ImageView imgOrgWebsiteSave;
    private ImageView imgOrgWebsiteRemarks;
    private ImageView imgNCVTAffilatedEdit;
    private ImageView imgNCVTAffilatedSave;
    private ImageView imgNCVTAffilatedRemarks;
    private ImageView imgReferenceEdit;
    private ImageView imgReferenceSave;
    private ImageView imgReferenceRemarks;
    private ImageView imgAffiliationEdit;
    private ImageView imgAffiliationSave;
    private ImageView imgAffiliationRemarks;
    private ImageView imgCodeEdit;
    private ImageView imgCodeSave;
    private ImageView imgCodeRemarks;
    private ImageView imgOrgNameEdit;
    private ImageView imgOrgNameSave;

    private LinearLayout affilation,reference,miscode;

    private ToggleButton imgOrgWebsiteNC;



    Button btn_submit;

    MaterialInfo materialInfo;

    public OrganisationFragment(MaterialInfo materialInfo) {

        this.materialInfo = materialInfo;
    }


    // ans3edit,ans7edit not their

    @Override
    public View onCreateView(LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_material, container, false);
        context = container.getContext();
        Bundle bundle = this.getArguments();
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((FormActivity) getActivity()).getSupportActionBar().setTitle("Organisation Details");
        // initViews();
       //  initValues();
        initListeners();
    }
//    private void initValues() {
//        ScrollView scrollViewGeneral = (ScrollView) view.findViewById(R.id.scrollViewGeneral);
//        scrollViewGeneral.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
//        scrollViewGeneral.setFocusable(true);
//        scrollViewGeneral.setFocusableInTouchMode(true);
//        scrollViewGeneral.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                v.requestFocusFromTouch();
//                return false;
//            }
//        });
//
//        ControlsUtility.setDefaultEditText(editOrgName, materialInfo.getOrganizationName());
//
//        ControlsUtility.setDefaultEditText(editOrgAuthroizedPerson, materialInfo.getAuthorizedPerson());
//
//        ControlsUtility.setDefaultEditText(editOrgAuthroizedPersonUID, materialInfo.getAuthorizedpersonUid());
//
//        ControlsUtility.setDefaultEditText(editOrgAuthroizedPersonRole, materialInfo.getAuthorizedRole());
//
//        ControlsUtility.setDefaultEditText(editOrgMobile, materialInfo.getOrgMobileno());
//
//        ControlsUtility.setDefaultEditText(editOrgEmail, materialInfo.getOrgEmail());
//
//        ControlsUtility.setDefaultEditText(editOrgPhone, materialInfo.getOrgTelephoneno());
//
//        ControlsUtility.setDefaultEditText(editOrgFax, materialInfo.getOrgFaxno());
//
//        ControlsUtility.setDefaultEditText(editOrgAddress, materialInfo.getOrgPostaladdress());
//
//        ControlsUtility.setDefaultEditText(editOrgLandmark, materialInfo.getOrgLandmark());
//
//        ControlsUtility.setDefaultEditText(editOrgState, materialInfo.getStateName());
//
//        ControlsUtility.setDefaultEditText(editOrgDistrict, materialInfo.getDistrictName());
//
//        ControlsUtility.setDefaultEditText(editOrgTehsil, materialInfo.getMandalName());
//
//        ControlsUtility.setDefaultEditText(editOrgPincode, materialInfo.getOrgPincode());
//
//        ControlsUtility.setDefaultEditText(editOrgWebsite, materialInfo.getOrgWebsite());
//
//        ControlsUtility.setDefaultSpinnerText(spinNCVTAffilated, materialInfo.getAffilated(),getResources().getStringArray(R.array.yes_no));
//
//        ControlsUtility.setDefaultEditText(editAffiliation, materialInfo.getAffilation());
//        ControlsUtility.setDefaultEditText(editReference, materialInfo.getReference());
//
//        ControlsUtility.setDefaultEditText(editCode, materialInfo.getCode());
//    }
//
//    private void initViews() {
//
//        customDialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_remark, null, false);
//
//
//        editRemark = (EditText) customDialogView.findViewById(R.id.editRemark);
//        buttonRemark = (Button) customDialogView.findViewById(R.id.buttonRemark);
//        buttonCancel = (Button) customDialogView.findViewById(R.id.buttonCancel);
//        textRemarkType = (TextView) customDialogView.findViewById(R.id.textRemarkType);
//
//
//        editOrgName = (EditText)view.findViewById(R.id.editOrgName);
//        editOrgAuthroizedPerson = (EditText)view.findViewById(R.id.editOrgAuthroizedPerson);
//        editOrgAuthroizedPersonUID = (EditText)view.findViewById(R.id.editOrgAuthroizedPersonUID);
//        editOrgAuthroizedPersonRole = (EditText)view.findViewById(R.id.editOrgAuthroizedPersonRole);
//        editOrgMobile = (EditText)view.findViewById(R.id.editOrgMobile);
//        editOrgPhone = (EditText)view.findViewById(R.id.editOrgPhone);
//        editOrgEmail = (EditText)view.findViewById(R.id.editOrgEmail);
//        editOrgFax = (EditText)view.findViewById(R.id.editOrgFax);
//        editOrgAddress = (EditText)view.findViewById(R.id.editOrgAddress);
//        editOrgLandmark = (EditText)view.findViewById(R.id.editOrgLandmark);
//        editOrgState = (EditText)view.findViewById(R.id.editOrgState);
//        editOrgDistrict = (EditText)view.findViewById(R.id.editOrgDistrict);
//        editOrgTehsil = (EditText)view.findViewById(R.id.editOrgTehsil);
//        editOrgPincode = (EditText)view.findViewById(R.id.editOrgPincode);
//        editOrgWebsite = (EditText)view.findViewById(R.id.editOrgWebsite);
//        editAffiliation = (EditText)view.findViewById(R.id.editAffiliation);
//        editReference = (EditText)view.findViewById(R.id.editReference);
//        editCode = (EditText)view.findViewById(R.id.editCode);
//
//        affilation = (LinearLayout)view.findViewById(R.id.affilation);
//        reference = (LinearLayout)view.findViewById(R.id.reference);
//        miscode = (LinearLayout)view.findViewById(R.id.miscode);
//
//        spinNCVTAffilated = (Spinner)view.findViewById(R.id.spinNCVTAffilated);
//
//        imgOrgWebsiteNC = (ToggleButton)view.findViewById(R.id.imgOrgWebsiteNC);
//
//        imgOrgNameEdit = (ImageView)view.findViewById(R.id.imgOrgNameEdit);
//        imgOrgNameSave = (ImageView)view.findViewById(R.id.imgOrgNameSave);
//        imgOrgNameRemarks = (ImageView)view.findViewById(R.id.imgOrgNameRemarks);
//        imgOrgAuthorizedPersonnEdit = (ImageView)view.findViewById(R.id.imgOrgAuthorizedPersonnEdit);
//        imgOrgAuthorizedPersonnSave = (ImageView)view.findViewById(R.id.imgOrgAuthorizedPersonnSave);
//        imgOrgAuthorizedPersonnRemarks = (ImageView)view.findViewById(R.id.imgOrgAuthorizedPersonnRemarks);
//        imgOrgAuthorizedPersonUIDEdit = (ImageView)view.findViewById(R.id.imgOrgAuthorizedPersonUIDEdit);
//        imgOrgAuthorizedPersonUIDSave = (ImageView)view.findViewById(R.id.imgOrgAuthorizedPersonUIDSave);
//        imgOrgAuthorizedPersonUIDRemarks = (ImageView)view.findViewById(R.id.imgOrgAuthorizedPersonUIDRemarks);
//        imgOrgAuthorizedPersonRoleEdit = (ImageView)view.findViewById(R.id.imgOrgAuthorizedPersonRoleEdit);
//        imgOrgAuthorizedPersonRoleSave = (ImageView)view.findViewById(R.id.imgOrgAuthorizedPersonRoleSave);
//        imgOrgAuthorizedPersonRoleRemarks = (ImageView)view.findViewById(R.id.imgOrgAuthorizedPersonRoleRemarks);
//        imgOrgMobileEdit = (ImageView)view.findViewById(R.id.imgOrgMobileEdit);
//        imgOrgMobileSave = (ImageView)view.findViewById(R.id.imgOrgMobileSave);
//        imgOrgMobileRemarks = (ImageView)view.findViewById(R.id.imgOrgMobileRemarks);
//        imgOrgPhoneEdit = (ImageView)view.findViewById(R.id.imgOrgPhoneEdit);
//        imgOrgPhoneSave = (ImageView)view.findViewById(R.id.imgOrgPhoneSave);
//        imgOrgPhoneRemarks = (ImageView)view.findViewById(R.id.imgOrgPhoneRemarks);
//        imgOrgEmailEdit = (ImageView)view.findViewById(R.id.imgOrgEmailEdit);
//        imgOrgEmailRemarks = (ImageView)view.findViewById(R.id.imgOrgEmailRemarks);
//        imgOrgEmailSave = (ImageView)view.findViewById(R.id.imgOrgEmailSave);
//        imgOrgFaxEdit = (ImageView)view.findViewById(R.id.imgOrgFaxEdit);
//        imgOrgFaxSave = (ImageView)view.findViewById(R.id.imgOrgFaxSave);
//        imgOrgFaxRemarks = (ImageView)view.findViewById(R.id.imgOrgFaxRemarks);
//        imgOrgAddressEdit = (ImageView)view.findViewById(R.id.imgOrgAddressEdit);
//        imgOrgAddressSave = (ImageView)view.findViewById(R.id.imgOrgAddressSave);
//        imgOrgAddressRemarks = (ImageView)view.findViewById(R.id.imgOrgAddressRemarks);
//        imgOrgLandmarkEdit = (ImageView)view.findViewById(R.id.imgOrgLandmarkEdit);
//        imgOrgLandmarkSave = (ImageView)view.findViewById(R.id.imgOrgLandmarkSave);
//        imgOrgLandmarkRemarks = (ImageView)view.findViewById(R.id.imgOrgLandmarkRemarks);
//        imgOrgStateRemarks = (ImageView)view.findViewById(R.id.imgOrgStateRemarks);
//        imgOrgDistrictRemarks = (ImageView)view.findViewById(R.id.imgOrgDistrictRemarks);
//        imgOrgTehsilEdit = (ImageView)view.findViewById(R.id.imgOrgTehsilEdit);
//        imgOrgTehsilSave = (ImageView)view.findViewById(R.id.imgOrgTehsilSave);
//        imgOrgTehsilRemarks = (ImageView)view.findViewById(R.id.imgOrgTehsilRemarks);
//        imgOrgPincodeEdit = (ImageView)view.findViewById(R.id.imgOrgPincodeEdit);
//        imgOrgPincodeSave = (ImageView)view.findViewById(R.id.imgOrgPincodeSave);
//        imgOrgPincodeRemarks = (ImageView)view.findViewById(R.id.imgOrgPincodeRemarks);
//        imgOrgWebsiteEdit = (ImageView)view.findViewById(R.id.imgOrgWebsiteEdit);
//        imgOrgWebsiteSave = (ImageView)view.findViewById(R.id.imgOrgWebsiteSave);
//        imgOrgWebsiteRemarks = (ImageView)view.findViewById(R.id.imgOrgWebsiteRemarks);
//         imgNCVTAffilatedEdit  = (ImageView)view.findViewById(R.id.imgNCVTAffilatedEdit);
//         imgNCVTAffilatedSave  = (ImageView)view.findViewById(R.id.imgNCVTAffilatedSave);
//         imgNCVTAffilatedRemarks  = (ImageView)view.findViewById(R.id.imgNCVTAffilatedRemarks);
//         imgReferenceEdit  = (ImageView)view.findViewById(R.id.imgReferenceEdit);
//         imgReferenceSave  = (ImageView)view.findViewById(R.id.imgReferenceSave);
//         imgReferenceRemarks  = (ImageView)view.findViewById(R.id.imgReferenceRemarks);
//         imgAffiliationEdit  = (ImageView)view.findViewById(R.id.imgAffiliationEdit);
//         imgAffiliationSave  = (ImageView)view.findViewById(R.id.imgAffiliationSave);
//         imgAffiliationRemarks  = (ImageView)view.findViewById(R.id.imgAffiliationRemarks);
//         imgCodeEdit  = (ImageView)view.findViewById(R.id.imgCodeEdit);
//         imgCodeSave  = (ImageView)view.findViewById(R.id.imgCodeSave);
//         imgCodeRemarks  = (ImageView)view.findViewById(R.id.imgCodeRemarks);
//
//        btn_submit = (Button)view.findViewById(R.id.btnSubmit);
//    }

    private void initListeners() {

        if(materialInfo.getOrgwebsiteNc() == 0){
            imgOrgWebsiteNC.setChecked(false);
        }else {
            imgOrgWebsiteNC.setChecked(true);
        }

        imgOrgWebsiteNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgOrgWebsiteNC.isChecked()){
                    materialInfo.setOrgwebsiteNc(1);
                }else {
                    materialInfo.setOrgwebsiteNc(0);
                }
            }
        });


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
                                    if (utility.getConnectivityStatus(context)) {
                                        materialInfo.setOrganizationnameNc(0);
                                        materialInfo.setAuthorizedpersonNc(0);
                                        materialInfo.setAuthorizedpersonuidNc(0);
                                        materialInfo.setAuthorizedroleNc(0);
                                        materialInfo.setOrgmobilenoNc(0);
                                        materialInfo.setOrgemailNc(0);
                                        materialInfo.setOrgtelephonenoNc(0);
                                        materialInfo.setOrgfaxnoNc(0);
                                        materialInfo.setOrgpostaladdressNc(0);
                                        materialInfo.setStatenameNc(0);
                                        materialInfo.setDistrictnameNc(0);
                                        materialInfo.setMandalnameNc(0);
                                        materialInfo.setOrgpincodeNc(0);
                                        materialInfo.setOrglandmarkNc(0);
                                        materialInfo.setAffilatedNC(0);
                                        materialInfo.setAffilationNC(0);
                                        materialInfo.setReferenceNC(0);
                                        materialInfo.setCodeNC(0);
                                        JSONObject dataToSyncClass = utility.getOrganisationInfoSyncData(materialInfo);
                                        Log.e("general data ", dataToSyncClass.toString());
                                        new ExecuteSyncOperation().execute(new String[]{AppConstants.URL_ORG_SET,
                                                dataToSyncClass.toString().replace("&", "and")});

                                    } else {
                                        ITIDBController controller = new ITIDBController(context);
                                        boolean updateStatus = controller.saveOrganisationInfo(materialInfo, "draft");
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

        imgOrgNameEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ControlsUtility.editImageViewAction(imgOrgNameSave,editOrgName,view);
                ans17edit = 1;
            }
        });

        imgOrgNameSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                materialInfo.setOrganizationName(editOrgName.getText().toString());
                ControlsUtility.okImageViewAction(imgOrgNameSave,editOrgName,view,ans17edit);
                ans17edit = 2;
            }
        });

        imgOrgNameRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(materialInfo.getOrganizationnameRemarks());
                textRemarkType.setText(AppConstants.KEY_ORG_NAME_REMARKS);
            }
        });

        imgOrgAuthorizedPersonnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgOrgAuthorizedPersonnSave,editOrgAuthroizedPerson,view);
                ans1edit = 1;
            }
        });

        imgOrgAuthorizedPersonnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
              materialInfo.setAuthorizedPerson(editOrgAuthroizedPerson.getText().toString());
              ControlsUtility.okImageViewAction(imgOrgAuthorizedPersonnSave,editOrgAuthroizedPerson,view,ans1edit);
                ans1edit = 2;
            }
        });
        imgOrgAuthorizedPersonnRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(materialInfo.getAuthorizedpersonRemarks());
                textRemarkType.setText(AppConstants.KEY_OR_AUTHORIZED_PERSON);
            }
        });

        imgOrgAuthorizedPersonUIDEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgOrgAuthorizedPersonUIDSave,editOrgAuthroizedPersonUID,view);
                ans2edit = 1;
            }
        });
        imgOrgAuthorizedPersonUIDSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
              materialInfo.setAuthorizedpersonUid(editOrgAuthroizedPersonUID.getText().toString());
                ControlsUtility.okImageViewAction(imgOrgAuthorizedPersonUIDSave,editOrgAuthroizedPersonUID,view,ans2edit);
                ans2edit = 2;
            }
        });
        imgOrgAuthorizedPersonUIDRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(materialInfo.getAuthorizedpersonuidRemark());
                textRemarkType.setText(AppConstants.KEY_AUTHORIZED_PERSON_UID);
            }
        });
        imgOrgAuthorizedPersonRoleEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ControlsUtility.editImageViewAction(imgOrgAuthorizedPersonRoleSave,editOrgAuthroizedPersonRole,view);
                ans3edit = 1;
            }
        });
        imgOrgAuthorizedPersonRoleSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                materialInfo.setAuthorizedRole(editOrgAuthroizedPersonRole.getText().toString());
                ControlsUtility.okImageViewAction(imgOrgAuthorizedPersonRoleSave,editOrgAuthroizedPersonRole,view,ans3edit);
                ans3edit = 2;

            }
        });
        imgOrgAuthorizedPersonRoleRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(materialInfo.getAuthorizedroleRemarks());
                textRemarkType.setText(AppConstants.KEY_AUTHORIZED_PERSON_ROLE);
            }
        });
        imgOrgMobileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              ControlsUtility.editImageViewAction(imgOrgMobileSave,editOrgMobile,view);
                ans4edit = 1;
            }
        });
        imgOrgMobileSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                materialInfo.setOrgMobileno(editOrgMobile.getText().toString());
                ControlsUtility.okImageViewAction(imgOrgMobileSave,editOrgMobile,view,ans4edit);
                ans4edit = 2;

            }
        });
        imgOrgMobileRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(materialInfo.getOrgmobilenoRemarks());
                textRemarkType.setText(AppConstants.KEY_MOBILE);

            }
        });
        imgOrgPhoneEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgOrgPhoneSave,editOrgPhone,view);
                ans5edit = 1;
            }
        });
        imgOrgPhoneSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                materialInfo.setOrgTelephoneno(editOrgPhone.getText().toString());
                ControlsUtility.okImageViewAction(imgOrgPhoneSave,editOrgPhone,view,ans5edit);
                ans5edit = 2;

            }
        });
        imgOrgPhoneRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(materialInfo.getOrgtelephonenoRemarks());
                textRemarkType.setText(AppConstants.KEY_PHONE);
            }
        });
        imgOrgFaxEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgOrgFaxSave,editOrgFax,view);
                ans6edit = 1;
            }
        });
        imgOrgFaxSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                materialInfo.setOrgFaxno(editOrgFax.getText().toString());
                ControlsUtility.okImageViewAction(imgOrgFaxSave,editOrgFax,view,ans6edit);
                ans6edit = 2;

            }
        });
        imgOrgFaxRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(materialInfo.getOrgfaxnoRemarks());
                textRemarkType.setText(AppConstants.KEY_FAX);
            }
        });
        imgOrgAddressEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgOrgAddressSave,editOrgAddress,view);
                ans7edit = 1;
            }
        });
        imgOrgAddressSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                materialInfo.setOrgPostaladdress(editOrgAddress.getText().toString());
                ControlsUtility.okImageViewAction(imgOrgAddressSave,editOrgAddress,view,ans7edit);
                ans7edit = 2;

            }
        });
        imgOrgAddressRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(materialInfo.getOrgpostaladdressRmarks());
                textRemarkType.setText(AppConstants.KEY_ADDRESS);
            }
        });
        imgOrgEmailEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgOrgEmailSave,editOrgEmail,view);
                ans8edit = 1;
            }
        });
        imgOrgEmailSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                materialInfo.setOrgEmail(editOrgEmail.getText().toString());
                ControlsUtility.okImageViewAction(imgOrgEmailSave,editOrgEmail,view,ans8edit);
                ans8edit = 2;

            }
        });
        imgOrgEmailRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(materialInfo.getOrgemailRemarks());
                textRemarkType.setText(AppConstants.KEY_EMAIL);
            }
        });
        imgOrgLandmarkEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgOrgLandmarkSave,editOrgLandmark,view);
                ans9edit = 1;
            }
        });
        imgOrgLandmarkSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                materialInfo.setOrgLandmark(editOrgLandmark.getText().toString());
                ControlsUtility.okImageViewAction(imgOrgLandmarkSave,editOrgLandmark,view,ans9edit);
                ans9edit = 2;

            }
        });
        imgOrgLandmarkRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(materialInfo.getOrglandmarkRemarks());
                textRemarkType.setText(AppConstants.KEY_LANDMARK);
            }
        });
        imgOrgStateRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(materialInfo.getStatenameRemarks());
                textRemarkType.setText(AppConstants.KEY_STATE);
            }
        });
        imgOrgDistrictRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(materialInfo.getDistrictnameRemarks());
                textRemarkType.setText(AppConstants.KEY_DISTRICT);

            }
        });
        imgOrgTehsilEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgOrgTehsilSave,editOrgTehsil,view);
                ans10edit = 1;
            }
        });
        imgOrgTehsilSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                materialInfo.setMandalName(editOrgTehsil.getText().toString());
                ControlsUtility.okImageViewAction(imgOrgTehsilSave,editOrgTehsil,view,ans10edit);
                ans10edit = 2;

            }
        });
        imgOrgTehsilRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(materialInfo.getMandalnameRemarks());
                textRemarkType.setText(AppConstants.KEY_TEHSIL);
            }
        });
        imgOrgPincodeEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgOrgPincodeSave,editOrgPincode,view);
                ans11edit = 1;
            }
        });
        imgOrgPincodeSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                materialInfo.setOrgPincode(editOrgPincode.getText().toString());
                ControlsUtility.okImageViewAction(imgOrgPincodeSave,editOrgPincode,view,ans11edit);
                ans11edit = 2;

            }
        });
        imgOrgPincodeRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(materialInfo.getOrgpincodeRemarks());
                textRemarkType.setText(AppConstants.KEY_PINCODE);

            }
        });
        imgOrgWebsiteEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgOrgWebsiteSave,editOrgWebsite,view);
                ans12edit = 1;
            }
        });
        imgOrgWebsiteSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                materialInfo.setOrgWebsite(editOrgWebsite.getText().toString());
                ControlsUtility.okImageViewAction(imgOrgWebsiteSave,editOrgWebsite,view,ans12edit);
                ans12edit = 2;

            }
        });
        imgOrgWebsiteRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(materialInfo.getOrgwebsiteRemarks());
                textRemarkType.setText(AppConstants.KEY_WEBSITE);
            }
        });

        imgNCVTAffilatedEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgNCVTAffilatedSave,spinNCVTAffilated,view);
                ans13edit = 1;
            }
        });
        imgNCVTAffilatedSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String affilatedValue = spinNCVTAffilated.getSelectedItem().toString();
                if(affilatedValue.equalsIgnoreCase("No")){
                    affilation.setVisibility(View.GONE);
                    reference.setVisibility(View.GONE);
                    miscode.setVisibility(View.GONE);
                }else {
                    affilation.setVisibility(View.VISIBLE);
                    reference.setVisibility(View.VISIBLE);
                    miscode.setVisibility(View.VISIBLE);
                }
                toHideKeyboard();
                materialInfo.setAffilated(spinNCVTAffilated.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgNCVTAffilatedSave,spinNCVTAffilated,view,ans13edit);
                ans13edit = 2;

            }
        });
        imgNCVTAffilatedRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(materialInfo.getAffilatedRemarks());
                textRemarkType.setText(AppConstants.KEY_AFFLIATED);
            }
        });

        imgAffiliationEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgAffiliationSave,editAffiliation,view);
                ans14edit = 1;
            }
        });
        imgAffiliationSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                materialInfo.setAffilation(editAffiliation.getText().toString());
                ControlsUtility.okImageViewAction(imgAffiliationSave,editAffiliation,view,ans14edit);
                ans14edit = 2;

            }
        });
        imgAffiliationRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(materialInfo.getAffilationRemarks());
                textRemarkType.setText(AppConstants.KEY_AFFILATION);
            }
        });

        imgReferenceEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgReferenceSave,editReference,view);
                ans15edit = 1;
            }
        });
        imgReferenceSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                materialInfo.setReference(editReference.getText().toString());
                ControlsUtility.okImageViewAction(imgReferenceSave,editReference,view,ans15edit);
                ans15edit = 2;

            }
        });
        imgReferenceRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(materialInfo.getReferenceRemarks());
                textRemarkType.setText(AppConstants.KEY_REFERENCE);
            }
        });

        imgCodeEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgCodeSave,editCode,view);
                ans16edit = 1;
            }
        });
        imgCodeSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                materialInfo.setCode(editCode.getText().toString());
                ControlsUtility.okImageViewAction(imgCodeSave,editCode,view,ans16edit);
                ans16edit = 2;

            }
        });
        imgCodeRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(materialInfo.getCodeRemarks());
                textRemarkType.setText(AppConstants.KEY_CODE);
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

    private boolean checkAllAtempted() {

        if(ans1edit == 2 && ans2edit == 2 &&  ans4edit == 2 && ans5edit == 2  &&  ans7edit == 2
                &&  ans8edit == 2 && ans9edit == 2 && ans10edit == 2 && ans11edit == 2 && ans12edit == 2 && ans13edit == 2  && ans17edit == 2){
            return true;
        }else {
            return false;
        }
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
            case AppConstants.KEY_ORG_NAME_REMARKS:
                materialInfo.setOrganizationnameRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_OR_AUTHORIZED_PERSON:
                materialInfo.setAuthorizedpersonRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_AUTHORIZED_PERSON_UID:
                materialInfo.setAuthorizedpersonuidRemark(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_AUTHORIZED_PERSON_ROLE:
                materialInfo.setAuthorizedroleRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_MOBILE:
                materialInfo.setOrgmobilenoRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_PHONE:
                materialInfo.setOrgtelephonenoRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_EMAIL:
                materialInfo.setOrgemailRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_FAX:
                materialInfo.setOrgfaxnoRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_ADDRESS:
                materialInfo.setOrgpostaladdressRmarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_LANDMARK:
                materialInfo.setOrglandmarkRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_STATE:
                materialInfo.setStatenameRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_DISTRICT:
                materialInfo.setDistrictnameRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_TEHSIL:
                materialInfo.setMandalnameRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_PINCODE:
                materialInfo.setOrgpincodeRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_WEBSITE:
                materialInfo.setOrgwebsiteRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_AFFLIATED:
                materialInfo.setAffilatedRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_AFFILATION:
                materialInfo.setAffilationRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_REFERENCE:
                materialInfo.setReferenceRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_CODE:
                materialInfo.setCodeRemarks(editRemark.getText().toString());
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
      /*  WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.width = Helper.toPixels(context, 200);
        layoutParams.height = Helper.toPixels(context, 200);
        dialog.getWindow().setAttributes(layoutParams);*/
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

    public void navigate() {
        Intent intent = new Intent(context, CategoryActivity.class);
        intent.putExtra("YearWiseCollegeId", materialInfo.getYearwisecollegeid());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

    }

    public void cancelCustomDialog() {
        if (customDialog != null) {
            customDialog.cancel();
        }
        toHideKeyboard();
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
                    boolean updation_status = controller.saveOrganisationInfo(materialInfo, "complete");
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




    }
