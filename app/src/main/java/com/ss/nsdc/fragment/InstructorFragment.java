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
import com.ss.nsdc.entity.InstructorInfo;
import com.ss.nsdc.entity.TechincalInfo;
import com.ss.nsdc.main.CategoryActivity;
import com.ss.nsdc.main.FormActivity;
import com.ss.nsdc.main.SubCategoryActivity;
import com.ss.nsdc.utility.ControlsUtility;
import com.ss.nsdc.utility.DateTimeUtils;
import com.ss.nsdc.utility.DimensionUtils;
import com.ss.nsdc.utility.UtilityService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mayank on 03/10/2016.
 */
public class InstructorFragment extends Fragment  {

    private View view;

    private ProgressDialog ringProgressDialog;

    EditText editRemark;
    Button buttonRemark;
    Button buttonCancel;

    Dialog customDialog;

    View customDialogView;

    TextView textRemarkType;

    private int ans1edit,ans2edit,ans3edit,ans4edit,ans5edit,ans6edit,ans7edit,ans8edit,
            ans9edit,ans10edit,ans11edit,ans12edit,ans13edit,ans14edit,ans15edit;

    public InstructorInfo instructorInfo;


    Button btn_submit;



    Context context;

    UtilityService utility = UtilityService.getInstance();
    private EditText editTradeName;
    private EditText editInstructor;
    private EditText editName;
    private EditText editFatherName;
    private EditText editDOB;
    private EditText editJoiningDate;
    private EditText editStream;
    private EditText editPassingYear;
    private EditText editExperience;
    private EditText editAccountNo;
    private EditText editBankName;
    private EditText editBranchName;
    private EditText editAadharNo;
    private EditText editSalary;
    private EditText editTechRemarks;

    private Spinner spinQualification;


    private ImageView imgTradeNameRemarks;
    private ImageView imgInstructorEdit;
    private ImageView imgInstructorSave;
    private ImageView imgInstructorRemarks;
    private ImageView imgNameEdit;
    private ImageView imgNameSave;
    private ImageView imgNameRemarks;
    private ImageView imgFatherNameEdit;
    private ImageView imgFatherNameSave;
    private ImageView imgFatherNameRemarks;
    private ImageView imgDOBEdit;
    private ImageView imgDOBSave;
    private ImageView imgDOBRemarks;
    private ImageView imgJoiningDateEdit;
    private ImageView imgJoiningDateSave;
    private ImageView imgJoiningDateRemarks;
    private ImageView imgQualificationEdit;
    private ImageView imgQualificationSave;
    private ImageView imgQualificationRemarks;
    private ImageView imgStreamEdit;
    private ImageView imgStreamSave;
    private ImageView imgStreamRemarks;
    private ImageView imgPassingYearEdit;
    private ImageView imgPassingYearSave;
    private ImageView imgPassingYearRemarks;
    private ImageView imgExperienceEdit;
    private ImageView imgExperienceSave;
    private ImageView imgExperienceRemarks;
    private ImageView imgAccountNoEdit;
    private ImageView imgAccountNoSave;
    private ImageView imgAccountNoRemarks;
    private ImageView imgBankNameEdit;
    private ImageView imgBankNameSave;
    private ImageView imgBankNameRemarks;
    private ImageView imgBranchNameEdit;
    private ImageView imgBranchNameSave;
    private ImageView imgBranchNameRemarks;
    private ImageView imgAadharNoEdit;
    private ImageView imgAadharNoSave;
    private ImageView imgAadharNoRemarks;
    private ImageView imgSalaryEdit;
    private ImageView imgSalarySave;
    private ImageView imgSalaryRemarks;
    private ImageView imgTechRemarksEdit;
    private ImageView imgTechRemarksSave;
    private ImageView imgTechRemarksRemarks;

    private ToggleButton imgTechNC;
    private ToggleButton imgQualificationNC;
    private ToggleButton imgExperienceNC;

    public InstructorFragment(){

    }

    public InstructorFragment(InstructorInfo instructorInfo) {
        this.instructorInfo = instructorInfo;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_instructor, container, false);
        context = container.getContext();
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((FormActivity) getActivity()).getSupportActionBar().setTitle("Instructor Staff Details");
        initViews();
        initValues();
        initListeners();
    }

    public void initValues() {
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




        ControlsUtility.setDefaultEditText(editTradeName,instructorInfo.getTradeName());

        ControlsUtility.setDefaultEditText(editInstructor,instructorInfo.getInstructor());

        ControlsUtility.setDefaultEditText(editName,instructorInfo.getName());

        ControlsUtility.setDefaultEditText(editFatherName,instructorInfo.getFathername());

        //ControlsUtility.setDefaultEditText(editDOB,instructorInfo.getDateofbirth());

        //ControlsUtility.setDefaultEditText(editJoiningDate,instructorInfo.getJoiningdate());

        ControlsUtility.setDefaultSpinnerText(spinQualification,instructorInfo.getQualification(),getResources().getStringArray(R.array.qualification));

        ControlsUtility.setDefaultEditText(editStream,instructorInfo.getStream());

        ControlsUtility.setDefaultEditText(editPassingYear,instructorInfo.getPassingyear());

        ControlsUtility.setDefaultEditText(editExperience,instructorInfo.getTotalexp());

        ControlsUtility.setDefaultEditText(editAccountNo,instructorInfo.getAccountno());

        ControlsUtility.setDefaultEditText(editBankName,instructorInfo.getBankname());

        ControlsUtility.setDefaultEditText(editBranchName,instructorInfo.getBranchname());

        ControlsUtility.setDefaultEditText(editAadharNo,instructorInfo.getAadharno());

        ControlsUtility.setDefaultEditText(editSalary,instructorInfo.getSalary());
    }

    private void initViews() {

        customDialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_remark, null, false);


        editRemark = (EditText) customDialogView.findViewById(R.id.editRemark);
        buttonRemark = (Button) customDialogView.findViewById(R.id.buttonRemark);
        buttonCancel = (Button) customDialogView.findViewById(R.id.buttonCancel);
        textRemarkType = (TextView) customDialogView.findViewById(R.id.textRemarkType);




        editTradeName = (EditText)view.findViewById(R.id.editTradeName);
        editInstructor = (EditText)view.findViewById(R.id.editInstructor);
        editName = (EditText)view.findViewById(R.id.editName);
        editFatherName = (EditText)view.findViewById(R.id.editFatherName);
        editJoiningDate = (EditText)view.findViewById(R.id.editJoiningDate);
        editDOB = (EditText)view.findViewById(R.id.editDOB);
        editPassingYear = (EditText)view.findViewById(R.id.editPassingYear);
        editStream = (EditText)view.findViewById(R.id.editStream);
        editExperience = (EditText)view.findViewById(R.id.editExperience);
        editAccountNo = (EditText)view.findViewById(R.id.editAccountNo);
        editBranchName = (EditText)view.findViewById(R.id.editBranchName);
        editBankName = (EditText)view.findViewById(R.id.editBankName);
        editAadharNo = (EditText)view.findViewById(R.id.editAadharNo);
        editSalary = (EditText)view.findViewById(R.id.editInsSalary);
        editTechRemarks = (EditText)view.findViewById(R.id.editTechRemarks);

        imgTechNC = (ToggleButton)view.findViewById(R.id.imgTechNC);
        imgQualificationNC = (ToggleButton)view.findViewById(R.id.imgQualificationNC);
        imgExperienceNC = (ToggleButton)view.findViewById(R.id.imgExperienceNC);


        spinQualification = (Spinner)view.findViewById(R.id.spinQualification);

        imgTradeNameRemarks = (ImageView)view.findViewById(R.id.imgTradeNameRemarks);
        imgInstructorEdit = (ImageView)view.findViewById(R.id.imgInstructorEdit);
        imgInstructorSave = (ImageView)view.findViewById(R.id.imgInstructorSave);
        imgInstructorRemarks = (ImageView)view.findViewById(R.id.imgInstructorRemarks);
        imgNameEdit = (ImageView)view.findViewById(R.id.imgNameEdit);
        imgNameSave = (ImageView)view.findViewById(R.id.imgNameSave);
        imgNameRemarks = (ImageView)view.findViewById(R.id.imgNameRemarks);
        imgFatherNameEdit = (ImageView)view.findViewById(R.id.imgFatherNameEdit);
        imgFatherNameSave = (ImageView)view.findViewById(R.id.imgFatherNameSave);
        imgFatherNameRemarks = (ImageView)view.findViewById(R.id.imgFatherNameRemarks);
        imgDOBEdit = (ImageView)view.findViewById(R.id.imgDOBEdit);
        imgDOBSave = (ImageView)view.findViewById(R.id.imgDOBSave);
        imgDOBRemarks = (ImageView)view.findViewById(R.id.imgDOBRemarks);
        imgJoiningDateEdit = (ImageView)view.findViewById(R.id.imgJoiningDateEdit);
        imgJoiningDateSave = (ImageView)view.findViewById(R.id.imgJoiningDateSave);
        imgJoiningDateRemarks = (ImageView)view.findViewById(R.id.imgJoiningDateRemarks);
        imgQualificationEdit = (ImageView)view.findViewById(R.id.imgQualificationEdit);
        imgQualificationSave = (ImageView)view.findViewById(R.id.imgQualificationSave);
        imgQualificationRemarks = (ImageView)view.findViewById(R.id.imgQualificationRemarks);
        imgStreamEdit = (ImageView)view.findViewById(R.id.imgStreamEdit);
        imgStreamSave = (ImageView)view.findViewById(R.id.imgStreamSave);
        imgStreamRemarks = (ImageView)view.findViewById(R.id.imgStreamRemarks);
        imgPassingYearEdit = (ImageView)view.findViewById(R.id.imgPassingYearEdit);
        imgPassingYearSave = (ImageView)view.findViewById(R.id.imgPassingYearSave);
        imgPassingYearRemarks = (ImageView)view.findViewById(R.id.imgPassingYearRemarks);
        imgExperienceEdit = (ImageView)view.findViewById(R.id.imgExperienceEdit);
        imgExperienceSave = (ImageView)view.findViewById(R.id.imgExperienceSave);
        imgExperienceRemarks = (ImageView)view.findViewById(R.id.imgExperienceRemarks);
        imgAccountNoEdit = (ImageView)view.findViewById(R.id.imgAccountNoEdit);
        imgAccountNoSave = (ImageView)view.findViewById(R.id.imgAccountNoSave);
        imgAccountNoRemarks = (ImageView)view.findViewById(R.id.imgAccountNoRemarks);
        imgBankNameEdit = (ImageView)view.findViewById(R.id.imgBankNameEdit);
        imgBankNameSave = (ImageView)view.findViewById(R.id.imgBankNameSave);
        imgBankNameRemarks = (ImageView)view.findViewById(R.id.imgBankNameRemarks);
        imgBranchNameEdit = (ImageView)view.findViewById(R.id.imgBranchNameEdit);
        imgBranchNameSave = (ImageView)view.findViewById(R.id.imgBranchNameSave);
        imgBranchNameRemarks = (ImageView)view.findViewById(R.id.imgBranchNameRemarks);
        imgAadharNoEdit = (ImageView)view.findViewById(R.id.imgAadharNoEdit);
        imgAadharNoSave = (ImageView)view.findViewById(R.id.imgAadharNoSave);
        imgAadharNoRemarks = (ImageView)view.findViewById(R.id.imgAadharNoRemarks);
        imgSalaryEdit = (ImageView)view.findViewById(R.id.imgInsSalaryEdit);
        imgSalarySave = (ImageView)view.findViewById(R.id.imgInsSalarySave);
        imgSalaryRemarks = (ImageView)view.findViewById(R.id.imgInsSalaryRemarks);
        imgTechRemarksEdit = (ImageView)view.findViewById(R.id.imgTechRemarksEdit);
        imgTechRemarksSave = (ImageView)view.findViewById(R.id.imgTechRemarksSave);
        imgTechRemarksRemarks = (ImageView)view.findViewById(R.id.imgTechRemarksRemarks);


        btn_submit = (Button)view.findViewById(R.id.btnSubmit);

    }

    private void initListeners() {

        if(instructorInfo.getCommonNc() == 0){
            imgTechNC.setChecked(false);
        }else {
            imgTechNC.setChecked(true);
        }

        if(instructorInfo.getQualificationNc() == 0){
            imgQualificationNC.setChecked(false);
        }else {
            imgQualificationNC.setChecked(true);
        }

        if(instructorInfo.getTotalexpNc() == 0){
            imgExperienceNC.setChecked(false);
        }else {
            imgExperienceNC.setChecked(true);
        }


        if(instructorInfo.getCommonNc() == 0){
            imgTechNC.setChecked(false);
        }else {
            imgTechNC.setChecked(true);
        }

        imgTechNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgTechNC.isChecked()){
                    instructorInfo.setCommonNc(1);
                }else {
                    instructorInfo.setCommonNc(0);
                }
            }
        });

        imgQualificationNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgQualificationNC.isChecked()){
                    instructorInfo.setQualificationNc(1);
                }else {
                    instructorInfo.setQualificationNc(0);
                }
            }
        });

        imgExperienceNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgExperienceNC.isChecked()){
                    instructorInfo.setTotalexpNc(1);
                }else {
                    instructorInfo.setTotalexpNc(0);
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
                new AlertDialog.Builder(context)
                        .setTitle("Confirmation")
                        .setMessage("This record will not be editable after submit")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                if (checkAllAtempted()) {

//                                    generalInfoModel.setAddressNc(0);

                                    if (utility.getConnectivityStatus(context)) {
                                        instructorInfo.setInstructorNc(0);
                                        instructorInfo.setNameNc(0);
                                        instructorInfo.setFathernameNc(0);
                                        instructorInfo.setDateofbirthNc(0);
                                        instructorInfo.setJoiningdateNc(0);
                                        instructorInfo.setStreamNc(0);
                                        instructorInfo.setPassingyearNc(0);
                                        instructorInfo.setAccountnoNc(0);
                                        instructorInfo.setBanknameNc(0);
                                        instructorInfo.setBranchnameNc(0);
                                        instructorInfo.setAadharnoNc(0);
                                        instructorInfo.setSalaryNC(0);
                                        instructorInfo.setRemarksNC(0);
                                        List<InstructorInfo> instructorInfolist = new ArrayList<InstructorInfo>();
                                        instructorInfolist.add(instructorInfo);
                                        JSONObject dataToSyncClass = utility.getInstructorInfoSyncData(instructorInfolist);
                                        Log.e("general data ", dataToSyncClass.toString());
                                        new ExecuteSyncOperation().execute(new String[]{AppConstants.URL_INSTRUCTOR_SET,
                                                dataToSyncClass.toString().replace("&", "and")});

                                    } else {
                                        ITIDBController controller = new ITIDBController(context);
                                        boolean updateStatus = controller.saveInstructorInfo(instructorInfo, "draft");
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

        imgTradeNameRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(instructorInfo.getTradeNameRemarks());
                textRemarkType.setText(AppConstants.KEY_TRADE_NAME_REMARKS);
            }
        });

        imgInstructorEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgInstructorSave,editInstructor,view);
                ans1edit =1;

            }
        });

        imgInstructorSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                instructorInfo.setInstructor(editInstructor.getText().toString());
                ControlsUtility.okImageViewAction(imgInstructorSave,editInstructor,view,ans1edit);
                ans1edit = 2;
            }
        });

        imgInstructorRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(instructorInfo.getInstructorRemarks());
                textRemarkType.setText(AppConstants.KEY_INSTRUCTOR_REMARKS);
            }
        });

        imgNameEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgNameSave,editName,view);
                ans2edit =1;

            }
        });

        imgNameSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                instructorInfo.setName(editName.getText().toString());
                ControlsUtility.okImageViewAction(imgNameSave,editName,view,ans2edit);
                ans2edit = 2;
            }
        });

        imgNameRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(instructorInfo.getNameRemarks());
                textRemarkType.setText(AppConstants.KEY_NAME_REMARKS);
            }
        });

        imgFatherNameEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgFatherNameSave,editFatherName,view);
                ans3edit =1;

            }
        });

        imgFatherNameSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                instructorInfo.setFathername(editFatherName.getText().toString());
                ControlsUtility.okImageViewAction(imgFatherNameSave,editFatherName,view,ans3edit);
                ans3edit = 2;
            }
        });

        imgFatherNameRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(instructorInfo.getFathernameRemarks());
                textRemarkType.setText(AppConstants.KEY_FATHER_NAME_REMARKS);
            }
        });
        imgDOBEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgDOBSave,editDOB,view);
                ans4edit =1;

            }
        });

        imgDOBSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                instructorInfo.setDateofbirth(editDOB.getText().toString());
                ControlsUtility.okImageViewAction(imgDOBSave,editDOB,view,ans4edit);
                ans4edit = 2;
            }
        });

        imgDOBRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(instructorInfo.getDateofbirthRemarks());
                textRemarkType.setText(AppConstants.KEY_DATE_BIRTH_REMARKS);
            }
        });
        imgJoiningDateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgJoiningDateSave,editJoiningDate,view);
                ans5edit =1;

            }
        });

        imgJoiningDateSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                instructorInfo.setJoiningdate(editJoiningDate.getText().toString());
                ControlsUtility.okImageViewAction(imgJoiningDateSave,editJoiningDate,view,ans5edit);
                ans5edit = 2;
            }
        });

        imgJoiningDateRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(instructorInfo.getJoiningdateRemarks());
                textRemarkType.setText(AppConstants.KEY_JOINING_DATE_REMARKS);
            }
        });
        imgQualificationEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgQualificationSave,spinQualification,view);
                ans6edit =1;

            }
        });

        imgQualificationSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                instructorInfo.setQualification(spinQualification.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgQualificationSave,spinQualification,view,ans6edit);
                ans6edit = 2;
            }
        });

        imgQualificationRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(instructorInfo.getQualificationRemarks());
                textRemarkType.setText(AppConstants.KEY_QUALIFICATION_REMARKS);
            }
        });
        imgStreamEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgStreamSave,editStream,view);
                ans7edit =1;

            }
        });

        imgStreamSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instructorInfo.setStream(editStream.getText().toString());
                ControlsUtility.okImageViewAction(imgStreamSave,editStream,view,ans7edit);
                ans7edit = 2;
            }
        });

        imgStreamRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(instructorInfo.getStreamRemarks());
                textRemarkType.setText(AppConstants.KEY_STREAM_REMARKS);
            }
        });
        imgPassingYearEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgPassingYearSave,editPassingYear,view);
                ans8edit =1;

            }
        });

        imgPassingYearSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instructorInfo.setPassingyear(editPassingYear.getText().toString());
                ControlsUtility.okImageViewAction(imgPassingYearSave,editPassingYear,view,ans8edit);
                ans8edit = 2;
            }
        });

        imgPassingYearRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(instructorInfo.getPassingyearRemarks());
                textRemarkType.setText(AppConstants.KEY_PASSING_YEAR_REMARKS);
            }
        });
        imgExperienceEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgExperienceSave,editExperience,view);
                ans9edit =1;

            }
        });

        imgExperienceSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                instructorInfo.setTotalexp(editExperience.getText().toString());
                ControlsUtility.okImageViewAction(imgExperienceSave,editExperience,view,ans9edit);
                ans9edit = 2;
            }
        });

        imgExperienceRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(instructorInfo.getTotalexpRemarks());
                textRemarkType.setText(AppConstants.KEY_EXPERIENCE_REMARKS);
            }
        });
        imgAccountNoEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgAccountNoSave,editAccountNo,view);
                ans10edit =1;

            }
        });

        imgAccountNoSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                instructorInfo.setAccountno(editAccountNo.getText().toString());
                ControlsUtility.okImageViewAction(imgAccountNoSave,editAccountNo,view,ans10edit);
                ans10edit = 2;
            }
        });

        imgAccountNoRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(instructorInfo.getAccountnoRemarks());
                textRemarkType.setText(AppConstants.KEY_ACCOUNT_REMARKS);
            }
        });
        imgBankNameEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgBankNameSave,editBankName,view);
                ans11edit =1;

            }
        });

        imgBankNameSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                instructorInfo.setBankname(editBankName.getText().toString());
                ControlsUtility.okImageViewAction(imgBankNameSave,editBankName,view,ans11edit);
                ans11edit = 2;
            }
        });

        imgBankNameRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(instructorInfo.getBanknameRemarks());
                textRemarkType.setText(AppConstants.KEY_BANK_NAME_REMARKS);
            }
        });
        imgBranchNameEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgBranchNameSave,editBranchName,view);
                ans12edit =1;

            }
        });

        imgBranchNameSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                instructorInfo.setBranchname(editBranchName.getText().toString());
                ControlsUtility.okImageViewAction(imgBranchNameSave,editBranchName,view,ans12edit);
                ans12edit = 2;
            }
        });

        imgBranchNameRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(instructorInfo.getBranchnameRemarks());
                textRemarkType.setText(AppConstants.KEY_BRANCH_NAME_REMARKS);
            }
        });
        imgAadharNoEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgAadharNoSave,editAadharNo,view);
                ans13edit =1;

            }
        });

        imgAadharNoSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                instructorInfo.setAadharno(editAadharNo.getText().toString());
                ControlsUtility.okImageViewAction(imgAadharNoSave,editAadharNo,view,ans13edit);
                ans13edit = 2;
            }
        });

        imgAadharNoRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(instructorInfo.getAadharnoRemarks());
                textRemarkType.setText(AppConstants.KEY_AADHAR_REMARKS);
            }
        });

        imgSalaryEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgSalarySave,editSalary,view);
                ans14edit =1;

            }
        });

        imgSalarySave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                instructorInfo.setSalary(editSalary.getText().toString());
                ControlsUtility.okImageViewAction(imgSalarySave,editSalary,view,ans14edit);
                ans14edit = 2;
            }
        });

        imgSalaryRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(instructorInfo.getSalaryRemarks());
                textRemarkType.setText(AppConstants.KEY_SALARY_REMARKS);
            }
        });

        imgTechRemarksEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgTechRemarksSave,editTechRemarks,view);
                ans15edit =1;

            }
        });

        imgTechRemarksSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                instructorInfo.setRemarks(editTechRemarks.getText().toString());
                ControlsUtility.okImageViewAction(imgTechRemarksSave,editTechRemarks,view,ans15edit);
                ans15edit = 2;
            }
        });

        imgTechRemarksRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(instructorInfo.getRemarksRemarks());
                textRemarkType.setText(AppConstants.KEY_REMARKS);
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

        if(ans2edit == 2 && ans3edit == 2&& ans4edit == 2&& ans5edit == 2&& ans6edit == 2
                && ans7edit == 2&& ans8edit == 2 && ans9edit == 2 && ans10edit == 2 && ans11edit ==2 && ans12edit == 2){
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

            case AppConstants.KEY_INSTRUCTOR_REMARKS:
                instructorInfo.setInstructorRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_NAME_REMARKS:
                instructorInfo.setNameRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_FATHER_NAME_REMARKS:
                instructorInfo.setFathernameRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_DATE_BIRTH_REMARKS:
                instructorInfo.setDateofbirthRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_JOINING_DATE_REMARKS:
                instructorInfo.setJoiningdateRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_QUALIFICATION_REMARKS:
                instructorInfo.setQualificationRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_STREAM_REMARKS:
                instructorInfo.setStreamRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_EXPERIENCE_REMARKS:
                instructorInfo.setTotalexpRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_PASSING_YEAR_REMARKS:
                instructorInfo.setPassingyearRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_ACCOUNT_REMARKS:
                instructorInfo.setAccountnoRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_BANK_NAME_REMARKS:
                instructorInfo.setBanknameRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_BRANCH_NAME_REMARKS:
                instructorInfo.setBranchnameRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_AADHAR_REMARKS:
                instructorInfo.setAadharnoRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_TRADE_NAME_REMARKS:
                instructorInfo.setTradeNameRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_SALARY_REMARKS:
                instructorInfo.setSalaryRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_REMARKS:
                instructorInfo.setRemarksRemarks(editRemark.getText().toString());
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
                    boolean updation_status = controller.saveInstructorInfo(instructorInfo, "complete");
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
        intent.putExtra("YearWiseCollegeId", instructorInfo.getYearwisecollegeid());
        intent.putExtra("Category","instructor");
        intent.putExtra("refId",instructorInfo.getRefId());
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
