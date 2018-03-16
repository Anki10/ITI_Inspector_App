/**
 *
 */
package com.ss.nsdc.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.ss.nsdc.R;
import com.ss.nsdc.constant.AppConstants;
import com.ss.nsdc.dao.ITIDBController;
import com.ss.nsdc.entity.TechincalInfo;
import com.ss.nsdc.main.CategoryActivity;
import com.ss.nsdc.main.FormActivity;
import com.ss.nsdc.main.SubCategoryActivity;
import com.ss.nsdc.utility.ControlsUtility;
import com.ss.nsdc.utility.DimensionUtils;
import com.ss.nsdc.utility.UtilityService;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vishal
 */
public class TechnicalFragment extends Fragment {

    private View view;

    private ProgressDialog ringProgressDialog;
    Context context;

    EditText editRemark;
    Button buttonRemark;
    Button buttonCancel;

    Dialog customDialog;

    View customDialogView;

    TextView textRemarkType;

    private int ans1edit,ans2edit,ans3edit,ans4edit,ans5edit,ans6edit,ans7edit,ans8edit,ans9edit,
            ans10edit,ans11edit,ans12edit,ans13edit,ans14edit,ans15edit;

    private EditText editTechDesignation;
    private EditText editTechName;
    private EditText editTechFatherName;
    private EditText editTechDOB;
    private EditText editTechJoiningDate;
    private EditText editTechStream;
    private EditText editTechPassingYear;
    private EditText editTechExperience;
    private EditText editTechAccountNo;
    private EditText editTechBankName;
    private EditText editTechBranchName;
    private EditText editTechAadharNo;
    private EditText editTechSalary;
    private EditText editTechPan;
    private EditText editTechRemarks;

    private Spinner spinTechQualification;


    private ImageView imgTechDesignationRemarks;
    private ImageView imgTechNameEdit;
    private ImageView imgTechNameSave;
    private ImageView imgTechNameRemarks;
    private ImageView imgTechFatherNameEdit;
    private ImageView imgTechFatherNameSave;
    private ImageView imgTechFatherNameRemarks;
    private ImageView imgTechDOBEdit;
    private ImageView imgTechDOBSave;
    private ImageView imgTechDOBRemarks;
    private ImageView imgTechJoiningDateEdit;
    private ImageView imgTechJoiningDateSave;
    private ImageView imgTechJoiningDateRemarks;
    private ImageView imgTechQualificationEdit;
    private ImageView imgTechQualificationSave;
    private ImageView imgTechQualificationRemarks;
    private ImageView imgTechStreamEdit;
    private ImageView imgTechStreamSave;
    private ImageView imgTechStreamRemarks;
    private ImageView imgTechPassingYearEdit;
    private ImageView imgTechPassingYearSave;
    private ImageView imgTechPassingYearRemarks;
    private ImageView imgTechExperienceEdit;
    private ImageView imgTechExperienceSave;
    private ImageView imgTechExperienceRemarks;
    private ImageView imgTechAccountNoEdit;
    private ImageView imgTechAccountNoSave;
    private ImageView imgTechAccountNoRemarks;
    private ImageView imgTechBankNameEdit;
    private ImageView imgTechBankNameSave;
    private ImageView imgTechBankNameRemarks;
    private ImageView imgTechBranchNameEdit;
    private ImageView imgTechBranchNameSave;
    private ImageView imgTechBranchNameRemarks;
    private ImageView imgTechAadharNoEdit;
    private ImageView imgTechAadharNoSave;
    private ImageView imgTechAadharNoRemarks;
    private ImageView imgTechSalaryEdit;
    private ImageView imgTechSalarySave;
    private ImageView imgTechSalaryRemarks;
    private ImageView imgTechPanEdit;
    private ImageView imgTechPanSave;
    private ImageView imgTechPanRemarks;
    private ImageView imgTechRemarksEdit;
    private ImageView imgTechRemarksSave;
    private ImageView imgTechRemarksRemarks;

    private ToggleButton imgTechNC;


    UtilityService utility = UtilityService.getInstance();

    Button btn_submit;

    TechincalInfo techincalInfo;

    public TechnicalFragment(TechincalInfo techincalInfo) {
        this.techincalInfo = techincalInfo;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_technical, container, false);
        context = container.getContext();
        Bundle bundle = this.getArguments();
        return view;


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        ((FormActivity) getActivity()).getSupportActionBar().setTitle("Technical Staff Details");
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
            //Log.e("string",String.valueOf(techincalInfo.getDesignation()));

       ControlsUtility.setDefaultEditText(editTechDesignation,techincalInfo.getDesignation());

        ControlsUtility.setDefaultEditText(editTechName,techincalInfo.getName());

        ControlsUtility.setDefaultEditText(editTechFatherName,techincalInfo.getFatherName());

        ControlsUtility.setDefaultEditText(editTechDOB,techincalInfo.getDob());

        ControlsUtility.setDefaultEditText(editTechJoiningDate,techincalInfo.getJoinDate());

        ControlsUtility.setDefaultSpinnerText(spinTechQualification,techincalInfo.getQualification(),getResources().getStringArray(R.array.qualification));

        ControlsUtility.setDefaultEditText(editTechStream,techincalInfo.getStream());

        ControlsUtility.setDefaultEditText(editTechPassingYear,techincalInfo.getPassingYear());

        ControlsUtility.setDefaultEditText(editTechExperience,techincalInfo.getTotalYoe());

        ControlsUtility.setDefaultEditText(editTechAccountNo,techincalInfo.getAccount());

        ControlsUtility.setDefaultEditText(editTechBankName,techincalInfo.getBankName());

        ControlsUtility.setDefaultEditText(editTechBranchName,techincalInfo.getBranchName());

        ControlsUtility.setDefaultEditText(editTechAadharNo,techincalInfo.getAdharNo());

        ControlsUtility.setDefaultEditText(editTechSalary,techincalInfo.getSalary());

        ControlsUtility.setDefaultEditText(editTechPan,techincalInfo.getPan());

        ControlsUtility.setDefaultEditText(editTechRemarks,techincalInfo.getRemarks());
    }

    private void initViews() {

        customDialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_remark, null, false);


        editRemark = (EditText) customDialogView.findViewById(R.id.editRemark);
        buttonRemark = (Button) customDialogView.findViewById(R.id.buttonRemark);
        buttonCancel = (Button) customDialogView.findViewById(R.id.buttonCancel);
        textRemarkType = (TextView) customDialogView.findViewById(R.id.textRemarkType);



        editTechDesignation = (EditText)view.findViewById(R.id.editTechDesignation);
        editTechName = (EditText)view.findViewById(R.id.editTechName);
        editTechFatherName = (EditText)view.findViewById(R.id.editTechFatherName);
        editTechJoiningDate = (EditText)view.findViewById(R.id.editTechJoiningDate);
        editTechDOB = (EditText)view.findViewById(R.id.editTechDOB);
        editTechPassingYear = (EditText)view.findViewById(R.id.editTechPassingYear);
        editTechStream = (EditText)view.findViewById(R.id.editTechStream);
        editTechExperience = (EditText)view.findViewById(R.id.editTechExperience);
        editTechAccountNo = (EditText)view.findViewById(R.id.editTechAccountNo);
        editTechBranchName = (EditText)view.findViewById(R.id.editTechBranchName);
        editTechBankName = (EditText)view.findViewById(R.id.editTechBankName);
        editTechAadharNo = (EditText)view.findViewById(R.id.editTechAadharNo);
        editTechSalary = (EditText)view.findViewById(R.id.editTechSalary);
        editTechPan = (EditText)view.findViewById(R.id.editTechPan);
        editTechRemarks = (EditText)view.findViewById(R.id.editTechRemarks);

        spinTechQualification = (Spinner)view.findViewById(R.id.spinTechQualification);

        imgTechDesignationRemarks = (ImageView)view.findViewById(R.id.imgTechDesignationRemarks);
        imgTechNameEdit = (ImageView)view.findViewById(R.id.imgTechNameEdit);
        imgTechNameSave = (ImageView)view.findViewById(R.id.imgTechNameSave);
        imgTechNameRemarks = (ImageView)view.findViewById(R.id.imgTechNameRemarks);
        imgTechFatherNameEdit = (ImageView)view.findViewById(R.id.imgTechFatherNameEdit);
        imgTechFatherNameSave = (ImageView)view.findViewById(R.id.imgTechFatherNameSave);
        imgTechFatherNameRemarks = (ImageView)view.findViewById(R.id.imgTechFatherNameRemarks);
        imgTechDOBEdit = (ImageView)view.findViewById(R.id.imgTechDOBEdit);
        imgTechDOBSave = (ImageView)view.findViewById(R.id.imgTechDOBSave);
        imgTechDOBRemarks = (ImageView)view.findViewById(R.id.imgTechDOBRemarks);
        imgTechJoiningDateEdit = (ImageView)view.findViewById(R.id.imgTechJoiningDateEdit);
        imgTechJoiningDateSave = (ImageView)view.findViewById(R.id.imgTechJoiningDateSave);
        imgTechJoiningDateRemarks = (ImageView)view.findViewById(R.id.imgTechJoiningDateRemarks);
        imgTechQualificationEdit = (ImageView)view.findViewById(R.id.imgTechQualificationEdit);
        imgTechQualificationSave = (ImageView)view.findViewById(R.id.imgTechQualificationSave);
        imgTechQualificationRemarks = (ImageView)view.findViewById(R.id.imgTechQualificationRemarks);
        imgTechStreamEdit = (ImageView)view.findViewById(R.id.imgTechStreamEdit);
        imgTechStreamSave = (ImageView)view.findViewById(R.id.imgTechStreamSave);
        imgTechStreamRemarks = (ImageView)view.findViewById(R.id.imgTechStreamRemarks);
        imgTechPassingYearEdit = (ImageView)view.findViewById(R.id.imgTechPassingYearEdit);
        imgTechPassingYearSave = (ImageView)view.findViewById(R.id.imgTechPassingYearSave);
        imgTechPassingYearRemarks = (ImageView)view.findViewById(R.id.imgTechPassingYearRemarks);
        imgTechExperienceEdit = (ImageView)view.findViewById(R.id.imgTechExperienceEdit);
        imgTechExperienceSave = (ImageView)view.findViewById(R.id.imgTechExperienceSave);
        imgTechExperienceRemarks = (ImageView)view.findViewById(R.id.imgTechExperienceRemarks);
        imgTechAccountNoEdit = (ImageView)view.findViewById(R.id.imgTechAccountNoEdit);
        imgTechAccountNoSave = (ImageView)view.findViewById(R.id.imgTechAccountNoSave);
        imgTechAccountNoRemarks = (ImageView)view.findViewById(R.id.imgTechAccountNoRemarks);
        imgTechBankNameEdit = (ImageView)view.findViewById(R.id.imgTechBankNameEdit);
        imgTechBankNameSave = (ImageView)view.findViewById(R.id.imgTechBankNameSave);
        imgTechBankNameRemarks = (ImageView)view.findViewById(R.id.imgTechBankNameRemarks);
        imgTechBranchNameEdit = (ImageView)view.findViewById(R.id.imgTechBranchNameEdit);
        imgTechBranchNameSave = (ImageView)view.findViewById(R.id.imgTechBranchNameSave);
        imgTechBranchNameRemarks = (ImageView)view.findViewById(R.id.imgTechBranchNameRemarks);
        imgTechAadharNoEdit = (ImageView)view.findViewById(R.id.imgTechAadharNoEdit);
        imgTechAadharNoSave = (ImageView)view.findViewById(R.id.imgTechAadharNoSave);
        imgTechAadharNoRemarks = (ImageView)view.findViewById(R.id.imgTechAadharNoRemarks);
        imgTechSalaryEdit = (ImageView)view.findViewById(R.id.imgTechSalaryEdit);
        imgTechSalarySave = (ImageView)view.findViewById(R.id.imgTechSalarySave);
        imgTechSalaryRemarks = (ImageView)view.findViewById(R.id.imgTechSalaryRemarks);
        imgTechPanEdit = (ImageView)view.findViewById(R.id.imgTechPanEdit);
        imgTechPanSave = (ImageView)view.findViewById(R.id.imgTechPanSave);
        imgTechPanRemarks = (ImageView)view.findViewById(R.id.imgTechPanRemarks);
        imgTechRemarksEdit = (ImageView)view.findViewById(R.id.imgTechRemarksEdit);
        imgTechRemarksSave = (ImageView)view.findViewById(R.id.imgTechRemarksSave);
        imgTechRemarksRemarks = (ImageView)view.findViewById(R.id.imgTechRemarksRemarks);
        imgTechNC = (ToggleButton)view.findViewById(R.id.imgTechNC);

        btn_submit = (Button)view.findViewById(R.id.btnSubmit);

    }

    private void initListeners() {

        if(techincalInfo.getCommonNc() == 0){
            imgTechNC.setChecked(false);
        }else {
            imgTechNC.setChecked(true);
        }

        imgTechNC.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgTechNC.isChecked()){
                    techincalInfo.setCommonNc(1);
                }else {
                    techincalInfo.setCommonNc(0);
                }
            }
        });


        buttonRemark.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                hideDialogKeyboard();
                if (isRemark()) {
                    addRemark(textRemarkType.getText().toString());

                }

            }
        });

        btn_submit.setOnClickListener(new OnClickListener() {
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

//                                    generalInfoModel.setAddressNc(0);

                                    if (utility.getConnectivityStatus(context)) {
                                        techincalInfo.setDesignationNc(0);
                                        techincalInfo.setNameNc(0);
                                        techincalInfo.setFathernameNc(0);
                                        techincalInfo.setDobNc(0);
                                        techincalInfo.setJoindateNc(0);
                                        techincalInfo.setQualificationNc(0);
                                        techincalInfo.setStreamNc(0);
                                        techincalInfo.setPassingyearNc(0);
                                        techincalInfo.setTotalyoeNc(0);
                                        techincalInfo.setAccountNc(0);
                                        techincalInfo.setBanknameNc(0);
                                        techincalInfo.setBranchnameNc(0);
                                        techincalInfo.setAdharnoNc(0);
                                        techincalInfo.setSalaryNC(0);
                                        techincalInfo.setRemarksNC(0);
                                        techincalInfo.setPanNC(0);
                                        List<TechincalInfo> technicalInfolist = new ArrayList<TechincalInfo>();
                                        technicalInfolist.add(techincalInfo);
                                        JSONObject dataToSyncClass = utility.getTechnicalInfoSyncData(technicalInfolist);
                                        Log.e("general data ", dataToSyncClass.toString());
                                        new ExecuteSyncOperation().execute(new String[]{AppConstants.URL_TECHNICAL_SET,
                                                dataToSyncClass.toString().replace("&", "and")});

                                    } else {
                                        ITIDBController controller = new ITIDBController(context);
                                        boolean updateStatus = controller.saveTechnicalInfo(techincalInfo, "draft");
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

        imgTechDesignationRemarks.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(techincalInfo.getDesignationRemarks());
                textRemarkType.setText(AppConstants.KEY_DESIGNATION_REMARKS);
            }
        });

        imgTechNameEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgTechNameSave,editTechName,view);
                ans1edit =1;

            }
        });

        imgTechNameSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                techincalInfo.setName(editTechName.getText().toString());
                ControlsUtility.okImageViewAction(imgTechNameSave,editTechName,view,ans1edit);
                ans1edit = 2;
            }
        });

        imgTechNameRemarks.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(techincalInfo.getNameRemarks());
                textRemarkType.setText(AppConstants.KEY_NAME_REMARKS);
            }
        });

        imgTechFatherNameEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgTechFatherNameSave,editTechFatherName,view);
                ans2edit =1;

            }
        });

        imgTechFatherNameSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                techincalInfo.setFatherName(editTechFatherName.getText().toString());
                ControlsUtility.okImageViewAction(imgTechFatherNameSave,editTechFatherName,view,ans2edit);
                ans2edit = 2;
            }
        });

        imgTechFatherNameRemarks.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(techincalInfo.getFathernameRemarks());
                textRemarkType.setText(AppConstants.KEY_FATHER_NAME_REMARKS);
            }
        });
        imgTechDOBEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgTechDOBSave,editTechDOB,view);
                ans3edit =1;

            }
        });

        imgTechDOBSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                techincalInfo.setDob(editTechDOB.getText().toString());
                ControlsUtility.okImageViewAction(imgTechDOBSave,editTechDOB,view,ans3edit);
                ans3edit = 2;
            }
        });

        imgTechDOBRemarks.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(techincalInfo.getDobRemarks());
                textRemarkType.setText(AppConstants.KEY_DATE_BIRTH_REMARKS);
            }
        });
        imgTechJoiningDateEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgTechJoiningDateSave,editTechJoiningDate,view);
                ans4edit =1;

            }
        });

        imgTechJoiningDateSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                techincalInfo.setJoinDate(editTechJoiningDate.getText().toString());
                ControlsUtility.okImageViewAction(imgTechJoiningDateSave,editTechJoiningDate,view,ans4edit);
                ans4edit = 2;
            }
        });

        imgTechJoiningDateRemarks.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(techincalInfo.getJoindateRemarks());
                textRemarkType.setText(AppConstants.KEY_JOINING_DATE_REMARKS);
            }
        });
        imgTechQualificationEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgTechQualificationSave,spinTechQualification,view);
                ans5edit =1;

            }
        });

        imgTechQualificationSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                techincalInfo.setQualification(spinTechQualification.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgTechQualificationSave,spinTechQualification,view,ans5edit);
                ans5edit = 2;
            }
        });

        imgTechQualificationRemarks.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(techincalInfo.getQualificationRemarks());
                textRemarkType.setText(AppConstants.KEY_QUALIFICATION_REMARKS);
            }
        });
        imgTechStreamEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgTechStreamSave,editTechStream,view);
                ans6edit =1;

            }
        });

        imgTechStreamSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                techincalInfo.setStream(editTechStream.getText().toString());
                ControlsUtility.okImageViewAction(imgTechStreamSave,editTechStream,view,ans6edit);
                ans6edit = 2;
            }
        });

        imgTechStreamRemarks.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(techincalInfo.getStreamRemarks());
                textRemarkType.setText(AppConstants.KEY_STREAM_REMARKS);
            }
        });
        imgTechPassingYearEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgTechPassingYearSave,editTechPassingYear,view);
                ans7edit =1;

            }
        });

        imgTechPassingYearSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                techincalInfo.setPassingYear(editTechPassingYear.getText().toString());
                ControlsUtility.okImageViewAction(imgTechPassingYearSave,editTechPassingYear,view,ans7edit);
                ans7edit = 2;
            }
        });

        imgTechPassingYearRemarks.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(techincalInfo.getPassingyearRemark());
                textRemarkType.setText(AppConstants.KEY_PASSING_YEAR_REMARKS);
            }
        });
        imgTechExperienceEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgTechExperienceSave,editTechExperience,view);
                ans8edit =1;

            }
        });

        imgTechExperienceSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                techincalInfo.setTotalYoe(editTechExperience.getText().toString());
                ControlsUtility.okImageViewAction(imgTechExperienceSave,editTechExperience,view,ans8edit);
                ans8edit = 2;
            }
        });

        imgTechExperienceRemarks.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(techincalInfo.getTotalyoeRemarks());
                textRemarkType.setText(AppConstants.KEY_EXPERIENCE_REMARKS);
            }
        });
        imgTechAccountNoEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgTechAccountNoSave,editTechAccountNo,view);
                ans9edit =1;

            }
        });

        imgTechAccountNoSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                techincalInfo.setAccount(editTechAccountNo.getText().toString());
                ControlsUtility.okImageViewAction(imgTechAccountNoSave,editTechAccountNo,view,ans9edit);
                ans9edit = 2;
            }
        });

        imgTechAccountNoRemarks.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(techincalInfo.getAccountRemarks());
                textRemarkType.setText(AppConstants.KEY_ACCOUNT_REMARKS);
            }
        });
        imgTechBankNameEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgTechBankNameSave,editTechBankName,view);
                ans10edit =1;

            }
        });

        imgTechBankNameSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                techincalInfo.setBankName(editTechBankName.getText().toString());
                ControlsUtility.okImageViewAction(imgTechBankNameSave,editTechBankName,view,ans10edit);
                ans10edit = 2;
            }
        });

        imgTechBankNameRemarks.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(techincalInfo.getBanknameRemarks());
                textRemarkType.setText(AppConstants.KEY_BANK_NAME_REMARKS);
            }
        });
        imgTechBranchNameEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgTechBranchNameSave,editTechBranchName,view);
                ans11edit =1;

            }
        });

        imgTechBranchNameSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                techincalInfo.setBranchName(editTechBranchName.getText().toString());
                ControlsUtility.okImageViewAction(imgTechBranchNameSave,editTechBranchName,view,ans11edit);
                ans11edit = 2;
            }
        });

        imgTechBranchNameRemarks.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(techincalInfo.getBranchnameRemarks());
                textRemarkType.setText(AppConstants.KEY_BRANCH_NAME_REMARKS);
            }
        });

        imgTechAadharNoEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgTechAadharNoSave,editTechAadharNo,view);
                ans12edit =1;

            }
        });

        imgTechAadharNoSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                techincalInfo.setAdharNo(editTechAadharNo.getText().toString());
                ControlsUtility.okImageViewAction(imgTechAadharNoSave,editTechAadharNo,view,ans12edit);
                ans12edit = 2;
            }
        });

        imgTechAadharNoRemarks.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(techincalInfo.getAdharnoRemarks());
                textRemarkType.setText(AppConstants.KEY_AADHAR_REMARKS);
            }
        });

        imgTechSalaryEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgTechSalarySave,editTechSalary,view);
                ans13edit =1;

            }
        });

        imgTechSalarySave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                techincalInfo.setSalary(editTechSalary.getText().toString());
                ControlsUtility.okImageViewAction(imgTechSalarySave,editTechSalary,view,ans13edit);
                ans13edit = 2;
            }
        });

       imgTechSalaryRemarks.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(techincalInfo.getSalaryRemarks());
                textRemarkType.setText(AppConstants.KEY_SALARY_REMARKS);
            }
        });

        imgTechPanEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgTechPanSave,editTechPan,view);
                ans14edit =1;

            }
        });

        imgTechPanSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                techincalInfo.setPan(editTechPan.getText().toString());
                ControlsUtility.okImageViewAction(imgTechPanSave,editTechPan,view,ans14edit);
                ans14edit = 2;
            }
        });

        imgTechPanRemarks.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(techincalInfo.getPanRemarks());
                textRemarkType.setText(AppConstants.KEY_PAN_REMARKS);
            }
        });

        imgTechRemarksEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgTechRemarksSave,editTechRemarks,view);
                ans15edit =1;

            }
        });

        imgTechRemarksSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                techincalInfo.setRemarks(editTechRemarks.getText().toString());
                ControlsUtility.okImageViewAction(imgTechRemarksSave,editTechRemarks,view,ans15edit);
                ans15edit = 2;
            }
        });

        imgTechRemarksRemarks.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(techincalInfo.getRemarksRemarks());
                textRemarkType.setText(AppConstants.KEY_REMARKS);
            }
        });




        buttonCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                hideDialogKeyboard();
                cancelCustomDialog();
            }
        });
    }

    private boolean checkAllAtempted() {
        if(ans1edit == 2 && ans2edit == 2 && ans3edit == 2&& ans4edit == 2&& ans5edit == 2&& ans6edit == 2
                && ans7edit == 2&& ans8edit == 2 && ans9edit == 2 && ans10edit == 2 && ans11edit ==2 && ans12edit == 2 && ans13edit == 2){
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

        switch (type) {
            case AppConstants.KEY_DESIGNATION_REMARKS:
                techincalInfo.setDesignationRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_NAME_REMARKS:
                techincalInfo.setNameRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_FATHER_NAME_REMARKS:
                techincalInfo.setFathernameRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_DATE_BIRTH_REMARKS:
                techincalInfo.setDobRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_JOINING_DATE_REMARKS:
                techincalInfo.setJoindateRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_QUALIFICATION_REMARKS:
                techincalInfo.setQualificationRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_STREAM_REMARKS:
                techincalInfo.setStreamRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_EXPERIENCE_REMARKS:
                techincalInfo.setTotalyoeRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_PASSING_YEAR_REMARKS:
                techincalInfo.setPassingyearRemark(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_ACCOUNT_REMARKS:
                techincalInfo.setAccountRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_BANK_NAME_REMARKS:
                techincalInfo.setBanknameRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_BRANCH_NAME_REMARKS:
                techincalInfo.setBranchnameRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_AADHAR_REMARKS:
                techincalInfo.setAdharnoRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_SALARY_REMARKS:
                techincalInfo.setSalaryRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_PAN_REMARKS:
                techincalInfo.setPanRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_REMARKS:
                techincalInfo.setRemarksRemarks(editRemark.getText().toString());
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
                    boolean updation_status = controller.saveTechnicalInfo(techincalInfo, "complete");
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
        Intent intent = new Intent(context, SubCategoryActivity.class);
        intent.putExtra("YearWiseCollegeId", techincalInfo.getYearwisecollegeid());
        intent.putExtra("Category","technical");
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
