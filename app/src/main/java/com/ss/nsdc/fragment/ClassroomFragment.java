package com.ss.nsdc.fragment;


import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
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
import com.ss.nsdc.entity.Classroom;
import com.ss.nsdc.entity.LandandBuilding;
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
 * Created by Mayank on 03/10/2016.
 */
public class ClassroomFragment extends Fragment {

    View view;

    Context context;
    private ProgressDialog ringProgressDialog;

    UtilityService utility = UtilityService.getInstance();
    Classroom classroomInfo;

    EditText editRemark;
    Button buttonRemark;
    Button buttonCancel;

    Dialog customDialog;

    View customDialogView;

    TextView textRemarkType;

    private int ans1edit,ans2edit,ans3edit,ans4edit,ans5edit,ans6edit,ans7edit,ans8edit,ans9edit,ans10edit,ans11edit,ans12edit;
    Button btn_submit;

    EditText editClassName;
    EditText editNCVTNorms;
    EditText editClassroomArea;
    EditText editShortageArea;
    Spinner spinWire,spinFloor,spinRoof,spinHeight,spinTin;;

    ImageView imgClassNameRemarks;
    ImageView imgNCVTNormsRemarks;
    ImageView imgClassroomAreaEdit;
    ImageView imgClassroomAreaSave;
    ImageView imgClassroomAreaRemarks;
    ImageView imgShortageAreaEdit;
    ImageView imgShortageAreaSave;
    ImageView imgShortageAreaRemarks;
    ImageView imgwidthEdit;
    ImageView imgwidthSave;
    ImageView imgwidthRemarks;
    ImageView imgWireEdit;
    ImageView imgWireSave;
    ImageView imgWireRemarks;
    private ImageView imgRoofEdit,imgRoofSave,imgRoofRemarks,imgHeightEdit,imgHeightSave,
            imgHeightRemarks,imgTinEdit,imgTinSave,imgTinRemarks;
    private  ToggleButton imgWireNC;
    private  ToggleButton imgTinNC;
    ToggleButton imgRoofNC;

    private EditText editwidth;


    public ClassroomFragment(Classroom classroomInfo) {
        this.classroomInfo = classroomInfo;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_class, container, false);
        context = container.getContext();
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((FormActivity) getActivity()).getSupportActionBar().setTitle("Classroom Details");
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

        ControlsUtility.setDefaultEditText(editClassName, classroomInfo.getClassroomName());

        ControlsUtility.setDefaultEditText(editNCVTNorms, classroomInfo.getAsperNCVTNorms());

        ControlsUtility.setDefaultEditText(editClassroomArea, classroomInfo.getClassroomArea());

        ControlsUtility.setDefaultEditText(editShortageArea, classroomInfo.getShortageArea());

        ControlsUtility.setDefaultEditText(editwidth,classroomInfo.getWidth());

        ControlsUtility.setDefaultSpinnerText(spinRoof,classroomInfo.getRoof(),getResources().getStringArray(R.array.roof));

        ControlsUtility.setDefaultSpinnerText(spinTin,classroomInfo.getTin(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinHeight,classroomInfo.getHeight(),getResources().getStringArray(R.array.yes_no));

    }

    private void initViews() {

        customDialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_remark, null, false);


        editRemark = (EditText) customDialogView.findViewById(R.id.editRemark);
        buttonRemark = (Button) customDialogView.findViewById(R.id.buttonRemark);
        buttonCancel = (Button) customDialogView.findViewById(R.id.buttonCancel);
        textRemarkType = (TextView) customDialogView.findViewById(R.id.textRemarkType);

        editClassName = (EditText)view.findViewById(R.id.editClassName);
        editNCVTNorms = (EditText)view.findViewById(R.id.editNCVTNorms);
        editClassroomArea = (EditText)view.findViewById(R.id.editClassroomArea);
        editShortageArea = (EditText)view.findViewById(R.id.editShortageArea);
        editwidth = (EditText)view.findViewById(R.id.editwidth);
        spinWire = (Spinner)view.findViewById(R.id.spinWire);
        spinRoof = (Spinner)view.findViewById(R.id.spinRoof);
        imgRoofEdit = (ImageView)view.findViewById(R.id.imgRoofEdit);
        imgRoofSave = (ImageView)view.findViewById(R.id.imgRoofSave);
        imgRoofRemarks = (ImageView)view.findViewById(R.id.imgRoofRemarks);
        spinHeight = (Spinner)view.findViewById(R.id.spinHeight);
        imgHeightEdit = (ImageView)view.findViewById(R.id.imgHeightEdit);
        imgHeightSave = (ImageView)view.findViewById(R.id.imgHeightSave);
        imgHeightRemarks = (ImageView)view.findViewById(R.id.imgHeightRemarks);
        spinTin = (Spinner)view.findViewById(R.id.spinTin);
        imgTinEdit = (ImageView)view.findViewById(R.id.imgTinEdit);
        imgTinSave = (ImageView)view.findViewById(R.id.imgTinSave);
        imgTinRemarks = (ImageView)view.findViewById(R.id.imgTinRemarks);

        imgClassNameRemarks= (ImageView)view.findViewById(R.id.imgClassNameRemarks);
        imgNCVTNormsRemarks= (ImageView)view.findViewById(R.id.imgNCVTNormsRemarks);
        imgClassroomAreaEdit= (ImageView)view.findViewById(R.id.imgClassroomAreaEdit);
        imgClassroomAreaSave= (ImageView)view.findViewById(R.id.imgClassroomAreaSave);
        imgClassroomAreaRemarks= (ImageView)view.findViewById(R.id.imgClassroomAreaRemarks);
        imgShortageAreaEdit= (ImageView)view.findViewById(R.id.imgShortageAreaEdit);
        imgShortageAreaSave= (ImageView)view.findViewById(R.id.imgShortageAreaSave);
        imgShortageAreaRemarks = (ImageView)view.findViewById(R.id.imgShortageAreaRemarks);
        imgwidthEdit = (ImageView)view.findViewById(R.id.imgwidthEdit);
        imgwidthSave = (ImageView)view.findViewById(R.id.imgwidthSave);
        imgwidthRemarks = (ImageView)view.findViewById(R.id.imgwidthRemarks);
        imgWireEdit = (ImageView)view.findViewById(R.id.imgWireEdit);
        imgWireSave = (ImageView)view.findViewById(R.id.imgWireSave);
        imgWireRemarks = (ImageView)view.findViewById(R.id.imgWireRemarks);

        imgWireNC = (ToggleButton)view.findViewById(R.id.imgWireNC);
        imgTinNC = (ToggleButton)view.findViewById(R.id.imgTinNC);
        imgRoofNC = (ToggleButton)view.findViewById(R.id.imgRoofNC);

        btn_submit = (Button)view.findViewById(R.id.btnSubmit);
    }


    private void initListeners() {

        if(classroomInfo.getRoofNC() == 0){
            imgRoofNC.setChecked(false);
        }else {
            imgRoofNC.setChecked(true);
        }

        if(classroomInfo.getTinNC() == 0){
            imgTinNC.setChecked(false);
        }else {
            imgTinNC.setChecked(true);
        }

        imgWireNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgWireNC.isChecked()){
                    classroomInfo.setWireNC(0);
                }else {
                    classroomInfo.setWireNC(1);
                }
            }
        });

        imgTinNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgTinNC.isChecked()){
                    classroomInfo.setTinNC(1);
                }else {
                    classroomInfo.setTinNC(0);
                }
            }
        });


        imgRoofNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgRoofNC.isChecked()){
                    Log.e("text","text1");
                    classroomInfo.setRoofNC(1);
                }else {
                    Log.e("text","text2");
                    classroomInfo.setRoofNC(0);
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
                                    classroomInfo.setClassroomNameNC(0);
                                    classroomInfo.setAsperNCVTNormsNC(0);
                                    classroomInfo.setShortageAreaNC(0);
                                    classroomInfo.setClassroomAreaNC(0);
                                    classroomInfo.setWidthNC(0);
                                    classroomInfo.setHeightNC(0);


                                    if (utility.getConnectivityStatus(context)) {
                                        List<Classroom> classroom = new ArrayList<Classroom>();
                                        classroom.add(classroomInfo);
                                        JSONObject dataToSyncClass = utility.getClasroomInfoSyncData(classroom);
                                        Log.e("general data ", dataToSyncClass.toString());
                                        new ExecuteSyncOperation().execute(new String[]{AppConstants.URL_ClASSROOM_SET,
                                                dataToSyncClass.toString().replace("&", "and")});

                                    } else {
                                        ITIDBController controller = new ITIDBController(context);
                                        boolean updateStatus = controller.saveClassroomInfo(classroomInfo, "draft");
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

        imgClassNameRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(classroomInfo.getClassroomNameRemarks());
                textRemarkType.setText(AppConstants.KEY_CLASSROOM_NAME_REMARKS);
            }
        });

        imgNCVTNormsRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(classroomInfo.getAsperNCVTNormsRemarks());
                textRemarkType.setText(AppConstants.KEY_NCVT_REMARKS);
            }
        });
        imgClassroomAreaEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgClassroomAreaSave,editClassroomArea,view);
                ans1edit =1;

            }
        });

        imgClassroomAreaSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                classroomInfo.setClassroomArea(editClassroomArea.getText().toString());
                ControlsUtility.okImageViewAction(imgClassroomAreaSave,editClassroomArea,view,ans1edit);
                ans1edit = 2;
            }
        });

        imgClassroomAreaRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(classroomInfo.getClassroomAreaRemarks());
                textRemarkType.setText(AppConstants.KEY_CLASS_AREA_REMARKS);
            }
        });
        imgShortageAreaEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgShortageAreaSave,editShortageArea,view);
                ans2edit =1;

            }
        });

        imgShortageAreaSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                classroomInfo.setShortageArea(editShortageArea.getText().toString());
                ControlsUtility.okImageViewAction(imgShortageAreaSave,editShortageArea,view,ans2edit);
                ans2edit = 2;
            }
        });

        imgShortageAreaRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(classroomInfo.getShortageAreaRemarks());
                textRemarkType.setText(AppConstants.KEY_SHORTAGE_AREA_REMARKS);
            }
        });

        imgwidthEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgwidthSave,editwidth,view);
                ans3edit =1;

            }
        });

        imgwidthSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                classroomInfo.setWidth(editwidth.getText().toString());
                ControlsUtility.okImageViewAction(imgwidthSave,editwidth,view,ans3edit);
                ans3edit = 2;
            }
        });

        imgwidthRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(classroomInfo.getWidthRemarks());
                textRemarkType.setText(AppConstants.KEY_WIDTH_REMARKS);
            }
        });

        imgWireEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgWireSave,spinWire,view);
                ans4edit =1;

            }
        });

        imgWireSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                classroomInfo.setWire(spinWire.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgWireSave,spinWire,view,ans4edit);
                ans4edit = 2;
            }
        });

        imgWireRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(classroomInfo.getWireRemarks());
                textRemarkType.setText(AppConstants.KEY_WIRE_REMARKS);
            }
        });

        imgRoofEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgRoofSave,spinRoof,view);
                ans5edit = 1;
            }
        });

        imgRoofSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                classroomInfo.setRoof(spinRoof.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgRoofSave,spinRoof,view,ans5edit);
                ans5edit = 2;

            }
        });

        imgRoofRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(classroomInfo.getRoofRemarks());
                textRemarkType.setText(AppConstants.KEY_ROOF_REMARK);
            }
        });


        imgHeightEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgHeightSave,spinHeight,view);
                ans6edit = 1;
            }
        });

        imgHeightSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                classroomInfo.setHeight(spinHeight.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgHeightSave,spinHeight,view,ans6edit);
                ans6edit = 2;

            }
        });

        imgHeightRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(classroomInfo.getHeightRemarks());
                textRemarkType.setText(AppConstants.KEY_HEIGHT_REMARK);
            }
        });


        imgTinEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgTinSave,spinTin,view);
                ans7edit = 1;
            }
        });

        imgTinSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                classroomInfo.setTin(spinTin.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgTinSave,spinTin,view,ans7edit);
                ans7edit = 2;

            }
        });

        imgTinRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(classroomInfo.getTinRemarks());
                textRemarkType.setText(AppConstants.KEY_TIN_REMARK);
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

        if(ans1edit == 2 &&  ans3edit == 2  && ans5edit == 2 && ans6edit == 2 && ans7edit == 2){
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
            case AppConstants.KEY_CLASSROOM_NAME_REMARKS:
                classroomInfo.setClassroomNameRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_NCVT_REMARKS:
                classroomInfo.setAsperNCVTNormsRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_CLASS_AREA_REMARKS:
                classroomInfo.setClassroomAreaRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_SHORTAGE_AREA_REMARKS:
                classroomInfo.setShortageAreaRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_WIDTH_REMARKS:
                classroomInfo.setWidthRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_WIRE_REMARKS:
                classroomInfo.setWireRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_ROOF_REMARK:
                classroomInfo.setRoofRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_HEIGHT_REMARK:
                classroomInfo.setHeightRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_TIN_REMARK:
                classroomInfo.setTinRemarks(editRemark.getText().toString());
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
                    boolean updation_status = controller.saveClassroomInfo(classroomInfo, "complete");
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
        intent.putExtra("YearWiseCollegeId", classroomInfo.getYearwisecollegeid());
        intent.putExtra("Category","class");
        //intent.putExtra("refId",classroomInfo.getRefId());
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
