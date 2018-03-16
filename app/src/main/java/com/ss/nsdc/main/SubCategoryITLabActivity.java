package com.ss.nsdc.main;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.ss.nsdc.R;
import com.ss.nsdc.adapter.SubCategoryITLabAdapter;
import com.ss.nsdc.constant.AppConstants;
import com.ss.nsdc.dao.ITIDBController;
import com.ss.nsdc.entity.ITLab;
import com.ss.nsdc.utility.UtilityService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radhika on 5/12/2017.
 */

public class SubCategoryITLabActivity extends AppCompatActivity {
    View view;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public String category, yearWiseCollegeId, applicationId, classId, filter;
    ProgressDialog ringProgressDialog;
    Context context = this;
    List<ITLab> getLabInfoList = new ArrayList<>();
    EditText editLabArea;
    Spinner spinLabInternet,spinLab,spinLabFloor,spinLabRoof,spinLabHeight,spinLabTin;

    ToggleButton imgLabNC,imgLabFloorNC,imgHeightNC,imgTinNc;
//    ITLab labInfo;
    ImageView imgLabAreaEdit,imgLabAreaSave,imgLabAreaRemarks,imgLabInternetEdit,imgLabInternetSave,
            imgLabInternetRemarks,imgLabEdit,imgLabSave,imgLabRemarks,imgLabFloorEdit,imgLabFloorSave,imgLabFloorRemarks,
             imgLabRoofEdit,imgLabRoofSave,imgLabRoofRemarks,imgLabHeightEdit,imgLabHeightSave,
            imgLabHeightRemarks,imgLabTinEdit,imgLabTinSave,imgLabTinRemarks;
    private int ans1edit,ans2edit,ans3edit,ans4edit,ans5edit,ans6edit,ans7edit;
    UtilityService utility = UtilityService.getInstance();
    ITLab LabInfo;
    Button submit;
    Dialog customDialog;

    View customDialogView;
    View customDialogView1;

    TextView textRemarkType;

    EditText editRemark;
    Button buttonRemark;
    Button buttonCancel;
    String str_remarks1 = "";
    String str_remarks2 = "";
    String str_remarks3 = "";
    String str_remarks4 = "";
    String str_remarks5 = "";
    String str_remarks6 = "";
    String str_remarks7 = "";

    ITLab getLabInfo;

    LinearLayout area,internet,roof,height,floor,tin;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_lab);

        customDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_remark, null, false);


        editRemark = (EditText) customDialogView.findViewById(R.id.editRemark);
        buttonRemark = (Button) customDialogView.findViewById(R.id.buttonRemark);
        buttonCancel = (Button) customDialogView.findViewById(R.id.buttonCancel);
        textRemarkType = (TextView) customDialogView.findViewById(R.id.textRemarkType);




        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            category = bundle.getString("Category");
            yearWiseCollegeId = bundle.getString("YearWiseCollegeId");
        } else {
            category = "none";
        }



        Toolbar toolbar = (Toolbar) findViewById(R.id.sub_cat_toolbar);
        setSupportActionBar(toolbar); //
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sub Category Listing");

        actionBar.setTitle("IT Lab");

         ITIDBController controller = new ITIDBController(context);
        getLabInfo = controller.getItlabInfo(yearWiseCollegeId);

        controller.close();

        ITIDBController controller1 = new ITIDBController(context);
        getLabInfoList = controller1.getITLabInfoList(yearWiseCollegeId);
        controller1.close();


        editLabArea = (EditText)findViewById(R.id.editLabArea);
        imgLabAreaEdit = (ImageView)findViewById(R.id.imgLabAreaEdit);
        imgLabAreaSave = (ImageView)findViewById(R.id.imgLabAreaSave);
        imgLabAreaRemarks = (ImageView)findViewById(R.id.imgLabAreaRemarks);
        spinLabInternet = (Spinner)findViewById(R.id.spinLabInternet);
        imgLabInternetEdit = (ImageView)findViewById(R.id.imgLabInternetEdit);
        imgLabInternetSave = (ImageView)findViewById(R.id.imgLabInternetSave);
        imgLabInternetRemarks = (ImageView)findViewById(R.id.imgLabInternetRemarks);
        spinLab= (Spinner)findViewById(R.id.spinLab);
        imgLabEdit = (ImageView)findViewById(R.id.imgLabEdit);
        imgLabSave = (ImageView)findViewById(R.id.imgLabSave);
        imgLabRemarks = (ImageView)findViewById(R.id.imgLabRemarks);
        spinLabFloor = (Spinner)findViewById(R.id.spinLabFloor);
        imgLabFloorEdit = (ImageView)findViewById(R.id.imgLabFloorEdit);
        imgLabFloorSave = (ImageView)findViewById(R.id.imgLabFloorSave);
        imgLabFloorRemarks = (ImageView)findViewById(R.id.imgLabFloorRemarks);
        spinLabRoof = (Spinner)findViewById(R.id.spinLabRoof);
        imgLabRoofEdit = (ImageView)findViewById(R.id.imgLabRoofEdit);
        imgLabRoofSave = (ImageView)findViewById(R.id.imgLabRoofSave);
        imgLabRoofRemarks = (ImageView)findViewById(R.id.imgLabRoofRemarks);
        spinLabHeight = (Spinner)findViewById(R.id.spinLabHeight);
        imgLabHeightEdit = (ImageView)findViewById(R.id.imgLabHeightEdit);
        imgLabHeightSave = (ImageView)findViewById(R.id.imgLabHeightSave);
        imgLabHeightRemarks = (ImageView)findViewById(R.id.imgLabHeightRemarks);
        spinLabTin = (Spinner)findViewById(R.id.spinLabTin);
        imgLabTinEdit = (ImageView)findViewById(R.id.imgLabTinEdit);
        imgLabTinSave = (ImageView)findViewById(R.id.imgLabTinSave);
        imgLabTinRemarks = (ImageView)findViewById(R.id.imgLabTinRemarks);
        imgLabNC = (ToggleButton)findViewById(R.id.imgLabNC);
        imgLabFloorNC = (ToggleButton)findViewById(R.id.imgLabFloorNC);
        imgHeightNC = (ToggleButton)findViewById(R.id.imgLabHeightNC);
        imgTinNc = (ToggleButton)findViewById(R.id.imgLabTinNC);
        area = (LinearLayout)findViewById(R.id.area);
        internet = (LinearLayout)findViewById(R.id.internet);
        roof = (LinearLayout)findViewById(R.id.roof);
        height = (LinearLayout)findViewById(R.id.height);
        tin = (LinearLayout)findViewById(R.id.tin);
        floor = (LinearLayout)findViewById(R.id.floor);





        editLabArea.setText(getLabInfo.getActualArea());
        editLabArea.setEnabled(false);

        imgLabAreaEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editLabArea.setEnabled(true);
                imgLabAreaSave.setImageDrawable(getResources().getDrawable(R.drawable.ok_grey));
                ans1edit = 1;
            }
        });

        imgLabAreaSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                editLabArea.setEnabled(false);
                getLabInfo.setActualArea(editLabArea.getText().toString());
                if (ans1edit == 0)
                    imgLabAreaSave.setImageDrawable(getResources().getDrawable(R.drawable.ok_green));
                else if (ans1edit == 1)
                    imgLabAreaSave.setImageDrawable(getResources().getDrawable(R.drawable.flag));
                ans1edit = 2;

            }
        });

        imgLabAreaRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showRemarksDialog(str_remarks1,1);
                Log.e("succes1","success1");
            }
        });


        spinLabInternet.setEnabled(false);
        int selection = spinLabInternet.getSelectedItemPosition();
        spinLabInternet.setSelection(selection);

        imgLabInternetEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinLabInternet.setEnabled(true);
                imgLabInternetSave.setImageDrawable(getResources().getDrawable(R.drawable.ok_grey));
                ans2edit = 1;
            }
        });

        imgLabInternetSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                spinLabInternet.setEnabled(false);
                getLabInfo.setInternet(spinLabInternet.getSelectedItem().toString());
                if (ans2edit == 0)
                    imgLabInternetSave.setImageDrawable(getResources().getDrawable(R.drawable.ok_green));
                else if (ans2edit == 1)
                    imgLabInternetSave.setImageDrawable(getResources().getDrawable(R.drawable.flag));
                ans2edit = 2;

            }
        });

        imgLabInternetRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRemarksDialog(str_remarks2,2);
                Log.e("succes2","success2");
            }
        });

        spinLab.setEnabled(false);
        int selection1 = spinLab.getSelectedItemPosition();
        spinLabInternet.setSelection(selection1);

        imgLabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinLab.setEnabled(true);
                imgLabSave.setImageDrawable(getResources().getDrawable(R.drawable.ok_grey));
                ans3edit = 1;
            }
        });

        imgLabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinLab.getSelectedItem().toString().equalsIgnoreCase("No")){
                    area.setVisibility(View.GONE);
                    internet.setVisibility(View.GONE);
                    roof.setVisibility(View.GONE);
                    height.setVisibility(View.GONE);
                    tin.setVisibility(View.GONE);
                    floor.setVisibility(View.GONE);
                }else {
                    area.setVisibility(View.VISIBLE);
                    internet.setVisibility(View.VISIBLE);
                    roof.setVisibility(View.VISIBLE);
                    height.setVisibility(View.VISIBLE);
                    tin.setVisibility(View.VISIBLE);
                    floor.setVisibility(View.VISIBLE);
                }
                toHideKeyboard();
                spinLab.setEnabled(false);
                if (ans3edit == 0)
                    imgLabSave.setImageDrawable(getResources().getDrawable(R.drawable.ok_green));
                else if (ans3edit == 1)
                    imgLabSave.setImageDrawable(getResources().getDrawable(R.drawable.flag));
                ans3edit = 2;

            }
        });

        imgLabRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRemarksDialog(str_remarks3,3);
            }
        });

        spinLabFloor.setEnabled(false);
        int selection2 = spinLabFloor.getSelectedItemPosition();
        spinLabFloor.setSelection(selection2);

        imgLabFloorEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinLabFloor.setEnabled(true);
                imgLabFloorSave.setImageDrawable(getResources().getDrawable(R.drawable.ok_grey));
                ans4edit = 1;
            }
        });

        imgLabFloorSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                spinLabFloor.setEnabled(false);
                getLabInfo.setFloor(spinLabFloor.getSelectedItem().toString());
                if (ans4edit == 0)
                    imgLabFloorSave.setImageDrawable(getResources().getDrawable(R.drawable.ok_green));
                else if (ans4edit == 1)
                    imgLabFloorSave.setImageDrawable(getResources().getDrawable(R.drawable.flag));
                ans4edit = 2;

            }
        });

        imgLabFloorRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showRemarksDialog(str_remarks4,4);
            }
        });


        spinLabRoof.setEnabled(false);

        imgLabRoofEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinLabRoof.setEnabled(true);
                imgLabRoofSave.setImageDrawable(getResources().getDrawable(R.drawable.ok_grey));
                ans5edit = 1;
            }
        });

        imgLabRoofSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                spinLabRoof.setEnabled(false);
                getLabInfo.setRoof(spinLabRoof.getSelectedItem().toString());
                if (ans5edit == 0)
                    imgLabRoofSave.setImageDrawable(getResources().getDrawable(R.drawable.ok_green));
                else if (ans5edit == 1)
                    imgLabRoofSave.setImageDrawable(getResources().getDrawable(R.drawable.flag));
                ans5edit = 2;

            }
        });

        imgLabRoofRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRemarksDialog(str_remarks5,5);
            }
        });



        spinLabHeight.setEnabled(false);

        imgLabHeightEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinLabHeight.setEnabled(true);
                imgLabHeightSave.setImageDrawable(getResources().getDrawable(R.drawable.ok_grey));
                ans6edit = 1;
            }
        });

        imgLabHeightSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                spinLabHeight.setEnabled(false);
                getLabInfo.setHeight(spinLabHeight.getSelectedItem().toString());
                if (ans6edit == 0)
                    imgLabHeightSave.setImageDrawable(getResources().getDrawable(R.drawable.ok_green));
                else if (ans6edit == 1)
                    imgLabHeightSave.setImageDrawable(getResources().getDrawable(R.drawable.flag));
                ans6edit = 2;

            }
        });

        imgLabHeightRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRemarksDialog(str_remarks6,6);
            }
        });



        spinLabTin.setEnabled(false);

        imgLabTinEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinLabTin.setEnabled(true);
                imgLabTinSave.setImageDrawable(getResources().getDrawable(R.drawable.ok_grey));
                ans7edit = 1;
            }
        });

        imgLabTinSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                spinLabTin.setEnabled(false);
                getLabInfo.setTin(spinLabTin.getSelectedItem().toString());
                if (ans7edit == 0)
                    imgLabTinSave.setImageDrawable(getResources().getDrawable(R.drawable.ok_green));
                else if (ans7edit == 1)
                    imgLabTinSave.setImageDrawable(getResources().getDrawable(R.drawable.flag));
                ans7edit = 2;

            }
        });

        imgLabTinRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              showRemarksDialog(str_remarks7,7);
            }
        });


//        ControlsUtility.setDefaultEditText(editLabArea,labInfo.getAreaOfItLab());

        submit = (Button)findViewById(R.id.btn_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                        JSONObject dataToSyncClass = getITLabInfoSyncData(getLabInfoList);
                                        Log.e("general data ", dataToSyncClass.toString());
                                        new ExecuteSyncOperation().execute(new String[]{AppConstants.URL_LAB_SET,
                                                dataToSyncClass.toString().replace("&", "and")});

                                    } else {
                                        ITIDBController controller = new ITIDBController(context);
                                        boolean updateStatus = controller.saveITLabInfo(getLabInfo, "draft");
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


        mRecyclerView = (RecyclerView) findViewById(R.id.sub_cat_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
     //   mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
       // mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.getItemAnimator().setChangeDuration(300);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            actionBar.setTitle("IT Lab");
            mAdapter = new SubCategoryITLabAdapter(getLabInfoList,category,context);

        mRecyclerView.setAdapter(mAdapter);

    }

    private JSONObject getITLabInfoSyncData(List<ITLab> labInfo) {

        JSONObject result = new JSONObject();
        JSONObject payload = new JSONObject();
        JSONObject labObj = new JSONObject();
        JSONArray itlabArr = new JSONArray();
        JSONObject it_labObj = new JSONObject();

        try {

            for (int i = 0; i < labInfo.size(); i++) {
                JSONObject itlabobj = new JSONObject();
                itlabobj.put("nameofequipment", TextUtils.isEmpty(labInfo.get(i).getNameofequipment())?"":labInfo.get(i).getNameofequipment());
                itlabobj.put("category",labInfo.get(i).getCategory());
                itlabobj.put("quantity",labInfo.get(i).getQuantity());
                itlabArr.put(itlabobj);

                it_labObj.put("refId",labInfo.get(i).getRefId());
                it_labObj.put("available",labInfo.get(i).getAvailable());
                it_labObj.put("yearWiseCollegeid",labInfo.get(i).getYearWiseCollegeid());
                it_labObj.put("actualTotalComputer","");
                it_labObj.put("areaOfItLab","");
                it_labObj.put("actualArea",editLabArea.getText().toString());
                it_labObj.put("noOfComputer","");
                it_labObj.put("noOfComputerRemarks","");
                it_labObj.put("actualTotalComputerRemarks","");
                it_labObj.put("areaOfItLabRemarks","");
                it_labObj.put("actualAreaRemarks",str_remarks1);
                it_labObj.put("noOfComputerNc","");
                it_labObj.put("actualTotalComputerNc","");
                it_labObj.put("areaOfItLabNc","");
                it_labObj.put("actualAreaNc","");
                it_labObj.put("availabilityofInternet",spinLabInternet.getSelectedItem().toString());
                it_labObj.put("availabilityofinternetRemarks",str_remarks2);
                it_labObj.put("availabilityofinternetNc","0");
                it_labObj.put("avaibilityofITLabWithStandAlone",spinLab.getSelectedItem().toString());
                it_labObj.put("avaibilityofITLabWithStandAlone_Remarks",str_remarks3);
                if(imgLabNC.isChecked()) {
                    it_labObj.put("avaibilityofITLabWithStandAlone_Nc", "1");
                }else {
                    it_labObj.put("avaibilityofITLabWithStandAlone_Nc", "0");
                }
                it_labObj.put("isthFloorOfITLabCarpeted",spinLab.getSelectedItem().toString());
                it_labObj.put("isthFloorOfITLabCarpeted_Remarks",str_remarks4);
                if(imgLabFloorNC.isChecked()) {
                    it_labObj.put("isthFloorOfITLabCarpeted_Nc", "1");
                }else {
                    it_labObj.put("isthFloorOfITLabCarpeted_Nc", "0");
                }
                it_labObj.put("ceilingHeightLessthan10Feet",spinLabHeight.getSelectedItem().toString());
                it_labObj.put("ceilingHeightLessthan10Feet_Remarks",str_remarks6);
                if(imgHeightNC.isChecked()) {
                    it_labObj.put("ceilingHeightLessthan10Feet_Nc","1");
                }else {
                    it_labObj.put("ceilingHeightLessthan10Feet_Nc","0");
                }
                it_labObj.put("wallsOfITLabTin",spinLabTin.getSelectedItem().toString());
                it_labObj.put("wallsOfITLabTin_Remarks",str_remarks7);
                if(imgTinNc.isChecked()) {
                    it_labObj.put("wallsOfITLabTin_Nc","1");
                }else {
                    it_labObj.put("wallsOfITLabTin_Nc","0");
                }

            }
            labObj.put("it_lablist",itlabArr);
            labObj.put("it_lab",it_labObj);
            labObj.put("yearwisecollegeid",yearWiseCollegeId);

            payload.put("payload",labObj);
            result.put("result",payload);
            Log.e("result",result.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private void addRemark(String s) {
    }

    private boolean checkAllAtempted() {
        if(spinLab.getSelectedItem().toString().equalsIgnoreCase("No")){
            if(ans3edit == 2){
                return true;
            }else return false;
        }else if(ans1edit == 2 && ans3edit == 2 && ans2edit == 2 && ans4edit == 2 && ans5edit == 2 &&
                ans6edit == 2 && ans7edit == 2 ){
            return true;
        }else {
            return false;
        }
    }

    protected void toHideKeyboard() {
        View view = SubCategoryITLabActivity.this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) SubCategoryITLabActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sub_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.category_list:
                Intent intent = new Intent(context, CategoryActivity.class);
                intent.putExtra("YearWiseCollegeId", yearWiseCollegeId);
                intent.putExtra("applicationNo", applicationId);
                //intent.putExtra("instituteName", context.getExtras().getString("ApplicationId"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
                return true;

            case R.id.institute_list:
                Intent intent1 = new Intent(context, InstituteActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
                return true;

            case R.id.menuExit:
                Intent intent2 = new Intent(Intent.ACTION_MAIN);
                intent2.addCategory(Intent.CATEGORY_HOME);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
                return true;
        }

        return super.onOptionsItemSelected(item);
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
                    boolean updation_status = controller.saveITLabInfo(getLabInfo , "complete");
                    controller.close();
                    if (updation_status) {
                        submit.setEnabled(false);
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
        intent.putExtra("YearWiseCollegeId", getLabInfo.getYearWiseCollegeid());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

    }


    public void hideDialogKeyboard() {

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
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

    public void cancelCustomDialog() {
        if (customDialog != null) {
            customDialog.cancel();
        }
        toHideKeyboard();
    }

    public void showRemarksDialog(String msg,final int position){
        final Dialog dialog = new Dialog(context);
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
                    case 1:
                        str_remarks1 = editRemark.getText().toString();
                        break;
                    case 2:
                        str_remarks2 = editRemark.getText().toString();
                        break;
                    case 3:
                        str_remarks3 = editRemark.getText().toString();
                        break;
                    case 4:
                        str_remarks4 = editRemark.getText().toString();
                        break;
                    case 5:
                        str_remarks5 = editRemark.getText().toString();
                        break;
                    case 6:
                        str_remarks6 = editRemark.getText().toString();
                        break;
                    case 7:
                        str_remarks7 = editRemark.getText().toString();
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
}
