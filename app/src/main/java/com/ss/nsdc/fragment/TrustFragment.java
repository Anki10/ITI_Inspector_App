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
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.ss.nsdc.R;
import com.ss.nsdc.constant.AppConstants;
import com.ss.nsdc.dao.ITIDBController;
import com.ss.nsdc.entity.EquipmentInfo;
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
 * Created by Mayank on 03/10/2016.
 */
public class TrustFragment extends Fragment{

    View view;



    Context context;

    UtilityService utility = UtilityService.getInstance();
    EquipmentInfo trustGeneralInfo;
    EditText editRemark;
    Button buttonRemark;
    Button buttonCancel;

    ProgressDialog ringProgressDialog;


    Dialog customDialog;

    View customDialogView;

    TextView textRemarkType;
    private int ans1edit,ans2edit,ans3edit,ans4edit,ans5edit,ans6edit,ans7edit,ans8edit,ans9edit,ans10edit,ans11edit,ans12edit;

    private Spinner spinInstituteOwnedTrust;
    private Spinner spinTrustRegisteredTrust;
    private Spinner spinExpTrust;
    private Spinner spinTypeTrust;

    private EditText editRegistrationNoTrust;
    private EditText editTrustName;
    private EditText editTrustValidity;
    private EditText editTrustPAN;
    private EditText editRemarksTrust;


    private ImageView imgInstituteOwnedTrustRemarks;
    private ImageView imgTrustRegisteredTrustRemarks;
    private ImageView imgRegistrationNoTrustRemarks;
    private ImageView imgTrustNameRemarks;
    private ImageView imgTrustValidityRemarks;
    private ImageView imgTrustPANRemarks;
    private ImageView imgExpTrustEdit;
    private ImageView imgExpTrustSave;
    private ImageView imgExpTrustRemarks;
    private ImageView imgTypeTrustEdit;
    private ImageView imgTypeTrustSave;
    private ImageView imgTypeTrustRemarks;
    private ImageView imgRemarksTrustEdit;
    private ImageView imgRemarksTrustSave;
    private ImageView imgTrustValidityEdit;
    private ImageView imgTrustValiditySave;
    private ImageView imgTrustPANEdit;
    private ImageView imgTrustPANSave;

    private ToggleButton imgTechNC;
    private ToggleButton imgTrustValidityNC;

    private LinearLayout trust,registration,name,date,pan,experience,type,remarks;

    Button btn_submit;
    public TrustFragment(EquipmentInfo trustGeneralInfo) {
        this.trustGeneralInfo = trustGeneralInfo;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_equipment, container, false);
        context = container.getContext();
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((FormActivity) getActivity()).getSupportActionBar().setTitle("Trust/Society/Company Details");
        initViews();
   //      initValues();
//        initListeners();

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

    /*    ControlsUtility.setDefaultSpinnerText(spinInstituteOwnedTrust,trustGeneralInfo.getInstituteOwned(),getResources().getStringArray(R.array.arrayInstituteOwned));

        ControlsUtility.setDefaultSpinnerText(spinTrustRegisteredTrust,trustGeneralInfo.getIsRegistered(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultEditText(editRegistrationNoTrust,trustGeneralInfo.getRegistrationNo());

        ControlsUtility.setDefaultEditText(editTrustName,trustGeneralInfo.getTrustName());

        ControlsUtility.setDefaultEditText(editTrustValidity,trustGeneralInfo.getTrustValidity());

        ControlsUtility.setDefaultEditText(editTrustPAN,trustGeneralInfo.getPanNumber());

        ControlsUtility.setDefaultEditText(editRemarksTrust,trustGeneralInfo.getRemarks());

        ControlsUtility.setDefaultSpinnerText(spinExpTrust,trustGeneralInfo.getExperience(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinTypeTrust,trustGeneralInfo.getType(),getResources().getStringArray(R.array.yes_no));*/


    }
    private void initViews() {

        customDialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_remark, null, false);


        editRemark = (EditText) customDialogView.findViewById(R.id.editRemark);
        buttonRemark = (Button) customDialogView.findViewById(R.id.buttonRemark);
        buttonCancel = (Button) customDialogView.findViewById(R.id.buttonCancel);
        textRemarkType = (TextView) customDialogView.findViewById(R.id.textRemarkType);

/*        spinInstituteOwnedTrust = (Spinner)view.findViewById(R.id.spinInstituteOwnedTrust);
        spinTrustRegisteredTrust = (Spinner)view.findViewById(R.id.spinTrustRegisteredTrust);
        spinExpTrust = (Spinner)view.findViewById(R.id.spinExpTrust);
        spinTypeTrust = (Spinner)view.findViewById(R.id.spinTypeTrust);

        editRegistrationNoTrust = (EditText)view.findViewById(R.id.editRegistrationNoTrust);
        editTrustName = (EditText)view.findViewById(R.id.editTrustName);
        editTrustValidity = (EditText)view.findViewById(R.id.editTrustValidity);
        editTrustPAN = (EditText)view.findViewById(R.id.editTrustPAN);
        editRemarksTrust = (EditText)view.findViewById(R.id.editRemarksTrust);

        imgInstituteOwnedTrustRemarks = (ImageView)view.findViewById(R.id.imgInstituteOwnedTrustRemarks);
        imgTrustRegisteredTrustRemarks = (ImageView)view.findViewById(R.id.imgTrustRegisteredTrustRemarks);
        imgRegistrationNoTrustRemarks= (ImageView)view.findViewById(R.id.imgRegistrationNoTrustRemarks);
        imgTrustNameRemarks= (ImageView)view.findViewById(R.id.imgTrustNameRemarks);
        imgTrustValidityRemarks= (ImageView)view.findViewById(R.id.imgTrustValidityRemarks);
        imgTrustPANRemarks= (ImageView)view.findViewById(R.id.imgTrustPANRemarks);
        imgExpTrustEdit = (ImageView)view.findViewById(R.id.imgExpTrustEdit);
        imgExpTrustSave = (ImageView)view.findViewById(R.id.imgExpTrustSave);
        imgExpTrustRemarks = (ImageView)view.findViewById(R.id.imgExpTrustRemarks);
        imgTypeTrustEdit = (ImageView)view.findViewById(R.id.imgTypeTrustEdit);
        imgTypeTrustSave = (ImageView)view.findViewById(R.id.imgTypeTrustSave);
        imgTypeTrustRemarks = (ImageView)view.findViewById(R.id.imgTypeTrustRemarks);
        imgRemarksTrustEdit = (ImageView)view.findViewById(R.id.imgRemarksTrustEdit);
        imgRemarksTrustSave = (ImageView)view.findViewById(R.id.imgRemarksTrustSave);
        imgTrustValidityEdit = (ImageView)view.findViewById(R.id.imgTrustValidityEdit);
        imgTrustValiditySave = (ImageView)view.findViewById(R.id.imgTrustValiditySave);
        imgTrustPANEdit = (ImageView)view.findViewById(R.id.imgTrustPANEdit);
        imgTrustPANSave = (ImageView)view.findViewById(R.id.imgTrustPANSave);

        trust = (LinearLayout)view.findViewById(R.id.trust);
        registration = (LinearLayout)view.findViewById(R.id.registration);
        name = (LinearLayout)view.findViewById(R.id.name);
        date = (LinearLayout)view.findViewById(R.id.date);
        pan = (LinearLayout)view.findViewById(R.id.pan);
        experience = (LinearLayout)view.findViewById(R.id.experience);
        type = (LinearLayout)view.findViewById(R.id.type);
        remarks = (LinearLayout)view.findViewById(R.id.remarks);

        if(trustGeneralInfo.getInstituteOwned().equalsIgnoreCase("Individual")){
            trust.setVisibility(View.GONE);
            registration.setVisibility(View.GONE);
            name.setVisibility(View.GONE);
            date.setVisibility(View.GONE);
            experience.setVisibility(View.GONE);
            type.setVisibility(View.GONE);
        }else {
            pan.setVisibility(View.GONE);
        }




        imgTechNC = (ToggleButton)view.findViewById(R.id.imgTechNC);
        imgTrustValidityNC = (ToggleButton)view.findViewById(R.id.imgTrustValidityNC);*/

        btn_submit = (Button)view.findViewById(R.id.btnSubmit);

    }

    private void initListeners() {

        if(trustGeneralInfo.getCommonNc() == 0){
            imgTechNC.setChecked(false);
        }else {
            imgTechNC.setChecked(true);
        }



        imgTechNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgTechNC.isChecked()){
                    trustGeneralInfo.setCommonNc(1);
                }else {
                    trustGeneralInfo.setCommonNc(0);
                }
            }
        });

        if(trustGeneralInfo.getTrustvalidityNc() == 0){
            imgTrustValidityNC.setChecked(false);
        }else {
            imgTrustValidityNC.setChecked(true);
        }


        imgTrustValidityNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgTrustValidityNC.isChecked()){
                    trustGeneralInfo.setTrustvalidityNc(1);
                }else {
                    trustGeneralInfo.setTrustvalidityNc(0);
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
                                        trustGeneralInfo.setInstituteOwnedNc(0);
                                        trustGeneralInfo.setIsregisteredNc(0);
                                        trustGeneralInfo.setRegistrationNc(0);
                                        trustGeneralInfo.setRegistrationnoNc(0);
                                        trustGeneralInfo.setTrustnameNc(0);
                                        trustGeneralInfo.setPannumberNc(0);
                                        trustGeneralInfo.setExperienceNc(0);
                                        trustGeneralInfo.setTypeNc(0);
                                        trustGeneralInfo.setRemarksNC(0);
                                        trustGeneralInfo.setRemarks_remarks("");
                                        JSONObject dataToSyncClass = utility.getTrustInfoSyncData(trustGeneralInfo);
                                        Log.e("general data ", dataToSyncClass.toString());
                                        new ExecuteSyncOperation().execute(new String[]{AppConstants.URL_TRUST_SET,
                                                dataToSyncClass.toString().replace("&", "and")});

                                    } else {
                                        ITIDBController controller = new ITIDBController(context);
                                         boolean updateStatus = controller.saveTrustInfo(trustGeneralInfo, "draft");
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

        imgInstituteOwnedTrustRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(trustGeneralInfo.getInstituteOwnedRemrks());
                textRemarkType.setText(AppConstants.KEY_INSTITUTE_OWNED_REMARKS);
            }
        });
        imgTrustRegisteredTrustRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(trustGeneralInfo.getIsregisteredRemarks());
                textRemarkType.setText(AppConstants.KEY_TRUST_REGISTERED_REMARKS);
            }
        });
        imgRegistrationNoTrustRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(trustGeneralInfo.getRegistrationnoRemarks());
                textRemarkType.setText(AppConstants.KEY_REGISTRATION_NO_REMARKS);
            }
        });
        imgTrustNameRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(trustGeneralInfo.getTrustnameRemarks());
                textRemarkType.setText(AppConstants.KEY_TRUST_NAME_REMARKS);
            }
        });
        imgTrustValidityRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(trustGeneralInfo.getTrustvalidityRemarks());
                textRemarkType.setText(AppConstants.KEY_TRUST_VALIDITY_REMARKS);
            }
        });
        imgTrustPANRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(trustGeneralInfo.getPannumberRemarks());
                textRemarkType.setText(AppConstants.KEY_PAN_NUMBER_REMARKS);
            }
        });

        imgExpTrustEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgExpTrustSave,spinExpTrust,view);
                ans1edit = 1;
            }
        });

        imgExpTrustSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinExpTrust.getSelectedItem().toString().equalsIgnoreCase("Yes")){
                    type.setVisibility(View.VISIBLE);
                }else {
                    type.setVisibility(View.GONE);
                }
                toHideKeyboard();
                trustGeneralInfo.setExperience(spinExpTrust.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgExpTrustSave,spinExpTrust,view,ans1edit);
                ans1edit = 2;
            }
        });

        imgExpTrustRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(trustGeneralInfo.getExperienceRemarks());
                textRemarkType.setText(AppConstants.KEY_EXP_REMARKS);
            }
        });

        imgTypeTrustEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgTypeTrustSave,spinTypeTrust,view);
                ans2edit = 1;
            }
        });

        imgTypeTrustSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                trustGeneralInfo.setType(spinTypeTrust.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgTypeTrustSave,spinTypeTrust,view,ans2edit);
                ans2edit = 2;
            }
        });

        imgTypeTrustRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(trustGeneralInfo.getTypeRemarks());
                textRemarkType.setText(AppConstants.KEY_TYPE_REMARKS);
            }
        });

        imgRemarksTrustEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgRemarksTrustSave,editRemarksTrust,view);
                ans3edit = 1;
            }
        });

        imgRemarksTrustSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                trustGeneralInfo.setRemarks(editRemarksTrust.getText().toString());
                ControlsUtility.okImageViewAction(imgRemarksTrustSave,editRemarksTrust,view,ans3edit);
                ans3edit = 2;
            }
        });

        imgTrustValidityEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgTrustValiditySave,editTrustValidity,view);
                ans5edit = 1;
            }
        });

        imgTrustValiditySave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                trustGeneralInfo.setTrustValidity(editTrustValidity.getText().toString());
                ControlsUtility.okImageViewAction(imgTrustValiditySave,editTrustValidity,view,ans5edit);
                ans5edit = 2;

            }
        });

        imgTrustPANEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgTrustPANSave,editTrustPAN,view);
                ans6edit = 1;
            }
        });

        imgTrustPANSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                trustGeneralInfo.setPanNumber(editTrustPAN.getText().toString());
                ControlsUtility.okImageViewAction(imgTrustPANSave,editTrustPAN,view,ans6edit);
                ans6edit = 2;
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
        if(trustGeneralInfo.getInstituteOwned().equalsIgnoreCase("Individual")) {
            if (ans3edit == 2) {
                return true;
            } else {
                return false;
            }
        }else {
            if(ans1edit == 2  && ans3edit == 2){
                return true;
            }else {
                return false;
            }
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
            case AppConstants.KEY_INSTITUTE_OWNED_REMARKS:
                trustGeneralInfo.setInstituteOwnedRemrks(editRemark.getText().toString());
                cancelCustomDialog();
               break;
            case AppConstants.KEY_TRUST_REGISTERED_REMARKS:
                trustGeneralInfo.setIsregisteredRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_REGISTRATION_NO_REMARKS:
                trustGeneralInfo.setRegistrationnoRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_TRUST_NAME_REMARKS:
                trustGeneralInfo.setTrustnameRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_TRUST_VALIDITY_REMARKS:
                trustGeneralInfo.setTrustvalidityRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_PAN_NUMBER_REMARKS:
                trustGeneralInfo.setPannumberRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_EXP_REMARKS:
                trustGeneralInfo.setExperienceRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_TYPE_REMARKS:
                trustGeneralInfo.setTypeRemarks(editRemark.getText().toString());
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
                    boolean updation_status = controller.saveTrustInfo(trustGeneralInfo, "complete");
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

    private void navigate() {
        Intent intent = new Intent(context, CategoryActivity.class);
        intent.putExtra("YearWiseCollegeId", trustGeneralInfo.getYearWiseCollegeid());
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
