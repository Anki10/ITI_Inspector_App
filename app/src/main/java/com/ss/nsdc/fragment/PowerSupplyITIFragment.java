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
import android.text.TextUtils;
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
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.ss.nsdc.R;
import com.ss.nsdc.constant.AppConstants;
import com.ss.nsdc.dao.ITIDBController;
import com.ss.nsdc.entity.GenralInfo;
import com.ss.nsdc.entity.PowerSupplyITIModel;
import com.ss.nsdc.entity.VideoObjectModel;
import com.ss.nsdc.main.CategoryActivity;
import com.ss.nsdc.main.FormActivity;
import com.ss.nsdc.main.VideoListActivity;
import com.ss.nsdc.utility.ControlsUtility;
import com.ss.nsdc.utility.DataUtils;
import com.ss.nsdc.utility.DimensionUtils;
import com.ss.nsdc.utility.UtilityService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by Mayank on 17/09/2016.
 */
public class PowerSupplyITIFragment extends Fragment {

    ProgressDialog ringProgressDialog;
    View customDialogView;
    Dialog customDialog;

    private int ans1edit,ans2edit,ans3edit,ans4edit,ans5edit,ans6edit;

    View view;

    Context context;

    PowerSupplyITIModel powerSupplyITIModel;

    UtilityService utility = UtilityService.getInstance();


    EditText editRemark;
    ToggleButton img_Electricity_NC;
    Button buttonRemark;
    Button buttonCancel;


    Button btn_submit;

    public PowerSupplyITIFragment(PowerSupplyITIModel powerSupplyITIModel) {
        this.powerSupplyITIModel = powerSupplyITIModel;
    }

    TextView textRemarkType;
    TextView textRemarkType1;

    Spinner spin_isconnection_nameiti;
    ImageView img_isconnection_nameiti_edit;
    ImageView img_isconnection_nameiti_save;
    ImageView img_isconnection_nameiti_remarks;
    String isconnection_nameit_remarks="";
    String electricity_remarks ="";
    String meter_remarks = "";


    EditText edt_connectionissued;
    ImageView img_connectionissued_edit;
    ImageView img_connectionissued_save;
    ImageView img_connectionissued_remarks;
    String connectionissued_remarks="";

    EditText edt_dateconnection;
    ImageView img_dateconnection_remarks;
    String dateconnection_remarks="";

    EditText edt_powersuppplyneededasper;
    Spinner edtElecricityMeter;
    ImageView img_powersuppplyneededasper_edit;
    ImageView img_powersuppplyneededasper_save;
    ImageView img_powersuppplyneededasper_remarks;
    ImageView imgElectricityEdit;
    ImageView imgElectricitySave;
    ImageView imgElectricityRemarks;
    ImageView imgdateconnectionEdit;
    ImageView imgdateconnectionSave;
    ImageView imgElecricityMeterRemarks;
    ImageView imgElecricityMeterSave;
    ImageView imgElecricityMeterEdit;
    ToggleButton img_isconnection_nameiti_NC;
    ToggleButton img_connectionissued_NC;
    String powersuppplyneededasper_remarks="";
    private Spinner editElectricity;

    String dateconnectionRemarks = "";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_powersupplyiti, container, false);
        context = container.getContext();
        Bundle bundle = this.getArguments();
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((FormActivity) getActivity()).getSupportActionBar().setTitle("ITI Power Supply");
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



    }

    private void initListeners()
    {
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                new AlertDialog.Builder(context)
                        .setTitle("Confirmation")
                        .setMessage("This record will not be editable after submit")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                if (checkAllAtempted())
                                {
                                    if (utility.getConnectivityStatus(context)) {
                                        JSONObject dataToSyncClass = getPowerSupplySyncData();
                                        Log.e("general data ", dataToSyncClass.toString());

                                        new ExecuteSyncOperation().execute(new String[]{AppConstants.URL_POWER_SUPPLY_SET,
                                                dataToSyncClass.toString().replace("&", "and")});

                                    } else {
                                        //TODO
                                        //save data in shared pref and on category activity on click of sync button
                                        //submit data

                                        ITIDBController controller = new ITIDBController(context);
                                        boolean updateStatus = controller.savePowerSupply(powerSupplyITIModel, "draft");
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

//        if(img_isconnection_nameiti_NC.isChecked()){
//            powerSupplyITIModel.setIsConnectionNameIti();
//        }

        imgElectricityRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showRemarksDialog(electricity_remarks,4);
                editRemark.setText(electricity_remarks);
            }
        });

        imgElecricityMeterRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRemarksDialog(meter_remarks,5);
            }
        });



        img_isconnection_nameiti_remarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(isconnection_nameit_remarks);
                textRemarkType.setText(AppConstants.KEY_ITI_NAME_REMARKS);
             */

                showRemarksDialog(isconnection_nameit_remarks,0);

            }
        });

        img_connectionissued_remarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(connectionissued_remarks);
                textRemarkType.setText(AppConstants.KEY_ITI_NAME_REMARKS);*/

                showRemarksDialog(connectionissued_remarks,1);
            }
        });

        img_dateconnection_remarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(dateconnection_remarks);
                textRemarkType.setText(AppConstants.KEY_ITI_NAME_REMARKS);*/

                showRemarksDialog(dateconnection_remarks,2);
            }
        });

        img_powersuppplyneededasper_remarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(powersuppplyneededasper_remarks);
                textRemarkType.setText(AppConstants.KEY_ITI_NAME_REMARKS);*/

                showRemarksDialog(powersuppplyneededasper_remarks,3);
            }
        });




        img_isconnection_nameiti_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(img_isconnection_nameiti_save,spin_isconnection_nameiti,view);
                ans1edit=1;
            }
        });

        img_connectionissued_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(img_connectionissued_save,edt_connectionissued,view);
                ans2edit=1;
            }
        });

        img_powersuppplyneededasper_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(img_powersuppplyneededasper_save,edt_powersuppplyneededasper,view);
                ans3edit=1;
            }
        });

        imgElectricityEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgElectricitySave,editElectricity,view);
                ans4edit  = 1;
            }
        });

        imgdateconnectionEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgdateconnectionSave,edt_dateconnection,view);
                ans5edit = 1;
            }
        });

        imgElecricityMeterEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgElecricityMeterSave,edtElecricityMeter,view);
                ans6edit = 1;
            }
        });


        img_isconnection_nameiti_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 /*int code=0;
                    if(spin_isconnection_nameiti.isEnabled())
                    {
                        code=1;
                    }else code=0;*/

                    ControlsUtility.okImageViewAction(img_isconnection_nameiti_save,spin_isconnection_nameiti,view, ans1edit);


                ans1edit=2;
            }
        });

        if(img_isconnection_nameiti_NC.isChecked()){
            powerSupplyITIModel.setIsConnectionNameItiNC("0");
        }else {
            powerSupplyITIModel.setIsConnectionNameItiNC("1");
        }

        img_connectionissued_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* int code=0;
                if(edt_connectionissued.isEnabled())
                {
                    code=1;
                }else code=0;*/

                ControlsUtility.okImageViewAction(img_connectionissued_save,edt_connectionissued,view, ans2edit);
                ans2edit=2;
            }
        });


        img_powersuppplyneededasper_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  int code=0;
                if(edt_powersuppplyneededasper.isEnabled())
                {
                    code=1;
                }else code=0;*/

                ControlsUtility.okImageViewAction(img_powersuppplyneededasper_save,edt_powersuppplyneededasper,view, ans3edit);
                ans3edit=2;
            }
        });

        imgElectricitySave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imgElectricitySave,editElectricity,view,ans4edit);
                ans4edit = 2;
            }
        });

        imgdateconnectionSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.okImageViewAction(imgdateconnectionSave,edt_dateconnection,view,ans5edit);
                ans5edit = 2;
            }
        });

        imgElecricityMeterSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.okImageViewAction(imgElecricityMeterSave,edtElecricityMeter,view,ans6edit);
                ans6edit = 2;
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


          edtElecricityMeter = (Spinner)view.findViewById(R.id.edtElecricityMeter);



           spin_isconnection_nameiti=(Spinner)view.findViewById(R.id.spin_isconnection_nameiti);
           editElectricity = (Spinner)view.findViewById(R.id.editElectricity);
           img_isconnection_nameiti_edit=(ImageView)view.findViewById(R.id.img_isconnection_nameiti_edit);
           img_isconnection_nameiti_save=(ImageView)view.findViewById(R.id.img_isconnection_nameiti_save);
           img_isconnection_nameiti_remarks=(ImageView)view.findViewById(R.id.img_isconnection_nameiti_remarks);
           img_isconnection_nameiti_NC = (ToggleButton)view.findViewById(R.id.img_isconnection_nameiti_NC);
          img_connectionissued_NC = (ToggleButton)view.findViewById(R.id.img_connectionissued_NC);

          img_Electricity_NC = (ToggleButton)view.findViewById(R.id.img_Electricity_NC);

           edt_connectionissued=(EditText)view.findViewById(R.id.edt_connectionissued);
           img_connectionissued_edit=(ImageView)view.findViewById(R.id.img_connectionissued_edit);
           img_connectionissued_save=(ImageView)view.findViewById(R.id.img_connectionissued_save);
           img_connectionissued_remarks=(ImageView)view.findViewById(R.id.img_connectionissued_remarks);

           edt_dateconnection=(EditText)view.findViewById(R.id.edt_dateconnection);
           img_dateconnection_remarks=(ImageView)view.findViewById(R.id.img_dateconnection_remarks);

           edt_powersuppplyneededasper=(EditText)view.findViewById(R.id.edt_powersuppplyneededasper);
           img_powersuppplyneededasper_edit=(ImageView)view.findViewById(R.id.img_powersuppplyneededasper_edit);
           img_powersuppplyneededasper_save=(ImageView)view.findViewById(R.id.img_powersuppplyneededasper_save);
           img_powersuppplyneededasper_remarks=(ImageView)view.findViewById(R.id.img_powersuppplyneededasper_remarks);
          imgElectricityEdit=(ImageView)view.findViewById(R.id.imgElectricityEdit);
          imgElectricitySave=(ImageView)view.findViewById(R.id.imgElectricitySave);
          imgElectricityRemarks=(ImageView)view.findViewById(R.id.imgElectricityRemarks);
          imgdateconnectionEdit = (ImageView)view.findViewById(R.id.imgdateconnectionEdit);
          imgdateconnectionSave = (ImageView)view.findViewById(R.id.imgdateconnectionSave);
          imgElecricityMeterEdit = (ImageView)view.findViewById(R.id.imgElecricityMeterEdit);
          imgElecricityMeterSave = (ImageView)view.findViewById(R.id.imgElecricityMeterSave);
          imgElecricityMeterRemarks = (ImageView)view.findViewById(R.id.imgElecricityMeterRemarks);



          btn_submit = (Button)view.findViewById(R.id.btnSubmit);


          ControlsUtility.setDefaultSpinnerText(spin_isconnection_nameiti,powerSupplyITIModel.getIsConnectionNameIti(),getResources().getStringArray(R.array.yes_no));
          ControlsUtility.setDefaultEditText(edt_connectionissued,powerSupplyITIModel.getConnectionIssued());
          ControlsUtility.setDefaultEditText(edt_dateconnection,powerSupplyITIModel.getDateConnection());
          ControlsUtility.setDefaultEditText(edt_powersuppplyneededasper,powerSupplyITIModel.getPowerSupplyNeededAsPer());



      }

    public void hideDialogKeyboard() {


        //\\ customDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private boolean isRemark() {

        return true;
    }

    private void addRemark(String type) {

    }

    protected void toHideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showRemarksDialog(String msg,final int position){
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_remark);

        final EditText editRemark = (EditText) dialog.findViewById(R.id.editRemark);
        editRemark.setText(msg);
        Button buttonRemark = (Button) dialog.findViewById(R.id.buttonRemark);
        buttonRemark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch(position) {
                    case 0:
                        isconnection_nameit_remarks=editRemark.getText().toString();
                        break;
                    case 1:
                        connectionissued_remarks=editRemark.getText().toString();
                        break;
                    case 2:
                        dateconnection_remarks=editRemark.getText().toString();
                        break;
                    case 3:
                        powersuppplyneededasper_remarks=editRemark.getText().toString();
                        break;
                    case 4:
                        electricity_remarks = editRemark.getText().toString();
                        break;
                    case 5:
                        meter_remarks = editRemark.getText().toString();
                        break;
                }

                dialog.dismiss();
            }
        });
        Button buttonCancel = (Button) dialog.findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }





    private boolean checkAllAtempted() {

        if(ans1edit == 2 && ans2edit == 2 && ans3edit == 2 && ans4edit == 2 && ans5edit == 2 && ans6edit == 2){
            return true;
        }else {
            return false;
        }
    }



    public JSONObject getPowerSupplySyncData() {
        JSONObject resultJsonObj= new JSONObject();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("electricalConnectionissuedinthenameof", edt_connectionissued.getText().toString());
            jsonObject.put("electricalConnectionissuedinthenameof_remarks", connectionissued_remarks);
            if(img_connectionissued_NC.isChecked()) {
                jsonObject.put("electricalConnectionissuedinthenameof_Nc", "1");
            }else {
                jsonObject.put("electricalConnectionissuedinthenameof_Nc", "0");
            }
            jsonObject.put("dateOfConnection", edt_dateconnection.getText().toString());
            jsonObject.put("dateOfConnection_Remarks", dateconnection_remarks);
            jsonObject.put("dateOfConnection_Nc", "");
            jsonObject.put("powersupplyavailableintheinstituteinKW", edt_powersuppplyneededasper.getText().toString());
            jsonObject.put("powersupplyavailableintheinstituteinKW_remarks", powersuppplyneededasper_remarks);
            jsonObject.put("powersupplyavailableintheinstituteinKW_Nc", "");
            jsonObject.put("whetherThreephaseconnction", "");
            jsonObject.put("whetherThreephaseconnction_remarks", "");
            jsonObject.put("whetherThreephaseconnction_Nc", "");
            jsonObject.put("kNooftheconnection", "");
            jsonObject.put("kNooftheconnection_remarks", "");
            jsonObject.put("kNooftheconnection_Nc", "");
            jsonObject.put("meterSealNo", "");
            jsonObject.put("meterSealNo_remarks", "");
            jsonObject.put("meterSealNo_Nc", "");
            jsonObject.put("dieselGeneratorsetavailable", "");
            jsonObject.put("dieselGeneratorsetavailale_remarks", "");
            jsonObject.put("dieselGeneratorsetavailable_Nc", "");
            jsonObject.put("totalelectricalload", "");
            jsonObject.put("totalelectricalload_remarks", "");
            jsonObject.put("totalelectricalload_Nc", "");
            jsonObject.put("powerAsperNCVT", "" );
            jsonObject.put("powerAsperNCVT_remarks", "");
            jsonObject.put("powerAsperNCVT_Nc", "");
            jsonObject.put("status", "");
            jsonObject.put("status_remarks", "");
            jsonObject.put("status_Nc", "");
            jsonObject.put("isconnectionNameofITI", spin_isconnection_nameiti.getSelectedItem());
            jsonObject.put("isconnectionNameofITI_remarks", isconnection_nameit_remarks);
            if(img_isconnection_nameiti_NC.isChecked()) {
                jsonObject.put("isconnectionNameofITI_Nc", "1");
            }else {
                jsonObject.put("isconnectionNameofITI_Nc", "0");
            }

            jsonObject.put("shortageofpowersupplyITI", "");
            jsonObject.put("shortageofpowersupplyITI_NC", "");
            jsonObject.put("shortageofpowersupplyITI_remarks", "");

            jsonObject.put("istheElectricityConnection", editElectricity.getSelectedItem().toString());
            if(img_Electricity_NC.isChecked()){
                jsonObject.put("istheElectricityConnection_NC", "1");
            }else {
                jsonObject.put("istheElectricityConnection_NC", "0");
            }
            jsonObject.put("istheElectricityConnection_remarks", electricity_remarks);

            jsonObject.put("electricityMeter", edtElecricityMeter.getSelectedItem().toString());
            jsonObject.put("electricitymeterRemarks", meter_remarks);
            jsonObject.put("electricitymeterNc", "");



            JSONObject PowerSupply= new JSONObject();
            PowerSupply.put("PowerSupply",jsonObject);
            PowerSupply.put("yearWiseCollegeId",powerSupplyITIModel.getYearWiseCollegeid());

            JSONObject payloadJsonObj= new JSONObject();
            payloadJsonObj.put("payload",PowerSupply);

             resultJsonObj.put("result",payloadJsonObj);


            Log.e("result",resultJsonObj.toString());

        }catch (Exception e){
            e.printStackTrace();
        }
        return resultJsonObj;
    }


    private void navigate() {
        Intent intent = new Intent(context, CategoryActivity.class);
        intent.putExtra("YearWiseCollegeId", powerSupplyITIModel.getYearWiseCollegeid());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

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

                 //   boolean updation_status=true;
                    ITIDBController controller = new ITIDBController(context);
                    boolean updation_status = controller.savePowerSupply(powerSupplyITIModel, "complete");
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
                    Toast.makeText(context, "Data Sync failed...", Toast.LENGTH_LONG).show();
                }

            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }



}
