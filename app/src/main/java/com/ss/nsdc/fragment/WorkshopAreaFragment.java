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
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
import com.ss.nsdc.entity.Workshop;
import com.ss.nsdc.entity.WorkshopAreaModel;
import com.ss.nsdc.main.CategoryActivity;
import com.ss.nsdc.main.FormActivity;
import com.ss.nsdc.utility.ControlsUtility;
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
 * Created by Prashant on 28 May '17.
 */
public class WorkshopAreaFragment extends Fragment {

    ProgressDialog ringProgressDialog;
    View customDialogView;
    Dialog customDialog;

    private int ans3edit,ans4edit,ans5edit,ans6edit,ans7edit,ans8edit,ans9edit,ans10edit,
            ans11edit,ans12edit,ans13edit,ans15edit,ans16edit,ans17edit,ans18edit,ans19edit,ans20edit,
            ans21edit,ans22edit,ans23edit,ans24edit
            ,ans25edit;

    View view;

    Context context;

    WorkshopAreaModel workshopAreaObj;

    UtilityService utility = UtilityService.getInstance();


    EditText editRemark;
    Button buttonRemark;
    Button buttonCancel;

    LinearLayout lathe,dg;


    Button btn_submit;

    public WorkshopAreaFragment(WorkshopAreaModel workshopAreaObj) {
        this.workshopAreaObj = workshopAreaObj;
    }

    TextView textRemarkType;
    TextView textRemarkType1;

    EditText editTxt_ans1;
    EditText editTxt_ans2;
    EditText editTxt_ans3;
    EditText editTxt_ans5;
    EditText editTxt_ans14;


    Spinner spin_ans4;
    Spinner spin_ans6;
    Spinner spin_ans8;
    Spinner spin_ans7;
    Spinner spin_ans9;
    Spinner spin_ans10;
    Spinner spin_ans11;
    Spinner spin_ans12;
    Spinner spin_ans13;
    Spinner spin_ans15;
    Spinner spin_ans16;
    Spinner spin_ans17;
    Spinner spin_ans18;
    Spinner spin_ans19;
    Spinner spin_ans20;
    Spinner spin_ans21;
    Spinner spin_ans22;
    Spinner spin_ans23;
    Spinner spin_ans24;
    Spinner spin_ans25;


    ImageView edit_ans3;
    ImageView edit_ans4;
    ImageView edit_ans5;
    ImageView edit_ans6;
    ImageView edit_ans7;
    ImageView edit_ans8;
    ImageView edit_ans9;
    ImageView edit_ans10;
    ImageView edit_ans11;
    ImageView edit_ans12;
    ImageView edit_ans13;
    ImageView edit_ans15;
    ImageView edit_ans16;
    ImageView edit_ans17;
    ImageView edit_ans18;
    ImageView edit_ans19;
    ImageView edit_ans20;
    ImageView edit_ans21;
    ImageView edit_ans22;
    ImageView edit_ans23;
    ImageView edit_ans24;
    ImageView edit_ans25;
    ImageView remarksTxt_ans1;
    ImageView remarksTxt_ans14;
    ImageView save_ans3;
    ImageView save_ans4;
    ImageView save_ans5;
    ImageView save_ans6;
    ImageView save_ans7;
    ImageView save_ans8;
    ImageView save_ans9;
    ImageView save_ans10;
    ImageView save_ans11;
    ImageView save_ans12;
    ImageView save_ans13;
    ImageView save_ans15;
    ImageView save_ans16;
    ImageView save_ans17;
    ImageView save_ans18;
    ImageView save_ans19;
    ImageView save_ans20;
    ImageView save_ans21;
    ImageView save_ans22;
    ImageView save_ans23;
    ImageView save_ans24;
    ImageView save_ans25;

    ImageView remarks_ans2;
    ImageView remarks_ans3;
    ImageView remarks_ans4;
    ImageView remarks_ans5;
    ImageView remarks_ans6;
    ImageView remarks_ans7;
    ImageView remarks_ans8;
    ImageView remarks_ans9;
    ImageView remarks_ans10;
    ImageView remarks_ans11;
    ImageView remarks_ans12;
    ImageView remarks_ans13;
    ImageView remarks_ans15;
    ImageView remarks_ans16;
    ImageView remarks_ans17;
    ImageView remarks_ans18;
    ImageView remarks_ans19;
    ImageView remarks_ans20;
    ImageView remarks_ans21;
    ImageView remarks_ans22;
    ImageView remarks_ans23;
    ImageView remarks_ans24;
    ImageView remarks_ans25;
    String str_remarks_ans2 = "";
    String str_remarks_ans3 = "";
    String str_remarks_ans4 = "";
    String str_remarks_ans5 = "";
    String str_remarks_ans6 = "";
    String str_remarks_ans7 = "";
    String str_remarks_ans8 = "";
    String str_remarks_ans9 = "";
    String str_remarks_ans10 = "";
    String str_remarks_ans11 = "";
    String str_remarks_ans12 = "";
    String str_remarks_ans13 = "";
    String str_remarks_ans15 = "";
    String str_remarks_ans16 = "";
    String str_remarks_ans17 = "";
    String str_remarks_ans18 = "";
    String str_remarks_ans19 = "";
    String str_remarks_ans20 = "";
    String str_remarks_ans21 = "";
    String str_remarks_ans22 = "";
    String str_remarks_ans23 = "";
    String str_remarks_ans24 = "";
    String str_remarks_ans25 = "";
    String str_remarks_ans26 = "";
    String str_remarks_ans27 = "";
    private ToggleButton nc_ans16;
    private ToggleButton nc_ans15;
    private ToggleButton nc_ans18;
    private ToggleButton nc_ans19;
    private ToggleButton nc_ans20;
    private ToggleButton nc_ans22;
    private ToggleButton nc_ans13;
    private ToggleButton nc_ans24;
    private ToggleButton nc_ans4;
    private ToggleButton nc_ans10;

    private LinearLayout ten;
    private LinearLayout twelve;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_workshop_area, container, false);
        context = container.getContext();
        Bundle bundle = this.getArguments();
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((FormActivity) getActivity()).getSupportActionBar().setTitle("Workshop Area");
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
                                        List<WorkshopAreaModel> workshopsList = new ArrayList<WorkshopAreaModel>();
                                        workshopsList.add(workshopAreaObj);
                                        JSONObject dataToSyncClass = utility.getWorkshopAreaSyncData(workshopsList);
                                        Log.e("general data ", dataToSyncClass.toString());

                                        new ExecuteSyncOperation().execute(new String[]{AppConstants.URL_WORKSHOP_AREA_SET,
                                                dataToSyncClass.toString().replace("&", "and")});

                                    } else {
                                        //TODO
                                        //save data in shared pref and on category activity on click of sync button
                                        //submit data

                                        ITIDBController controller = new ITIDBController(context);
                                        boolean updateStatus = controller.saveWorkshopArea(workshopAreaObj, "draft");
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

        remarks_ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_remarks_ans2 = workshopAreaObj.getShiftsunitRemarks();
                showRemarksDialog(str_remarks_ans2,2);
                Log.e("str",workshopAreaObj.getShiftsunitRemarks());
                editRemark.setText(workshopAreaObj.getShiftsunitRemarks());

            }
        });

        remarks_ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 str_remarks_ans3 = workshopAreaObj.getActualareaRemarks();
                 showRemarksDialog(str_remarks_ans3,3);
            }
        });

        remarks_ans4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_remarks_ans4 = workshopAreaObj.getIstheWorkshopRectangularRemarks();
                showRemarksDialog(str_remarks_ans4,4);
            }
        });
        remarks_ans5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_remarks_ans5 = workshopAreaObj.getWidthOftheWorkshopRemarks();
                showRemarksDialog(str_remarks_ans5,5);
            }
        });
        remarks_ans6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_remarks_ans6 = workshopAreaObj.getAretheWorkshopWallsofTinRemarks();
                showRemarksDialog(str_remarks_ans6,6);
            }
        });
        remarks_ans7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_remarks_ans7 = workshopAreaObj.getWorkShopRoof_Remarks();
                showRemarksDialog(str_remarks_ans7,7);
            }
        });
        remarks_ans8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_remarks_ans8 = workshopAreaObj.getCeilingHeightlessthan10FeetRemarks();
                showRemarksDialog(str_remarks_ans8,8);
            }
        });
        remarks_ans9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_remarks_ans8 = workshopAreaObj.getCeilingHeightlessthan12Feet_Remarks();
                showRemarksDialog(str_remarks_ans9,9);
            }
        });
        remarks_ans10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_remarks_ans10 = workshopAreaObj.getIstheheavyMachineryLocated_Remarks();
                showRemarksDialog(str_remarks_ans10,10);
            }
        });
        remarks_ans11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRemarksDialog(str_remarks_ans11,11);
            }
        });
        remarks_ans12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRemarksDialog(str_remarks_ans12,12);
            }
        });
        remarks_ans13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_remarks_ans13 = workshopAreaObj.getEmergencyContactNumberDisplayRemarks();
                showRemarksDialog(str_remarks_ans13,13);
            }
        });
        remarks_ans15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_remarks_ans15 = workshopAreaObj.getIsWiresandBoardsCoveredRemarks();
                showRemarksDialog(str_remarks_ans15,15);
            }
        });
        remarks_ans16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_remarks_ans16 = workshopAreaObj.getIsFireExtinguisherAvailableRemarks();
                showRemarksDialog(str_remarks_ans16,16);
            }
        });
        remarks_ans17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_remarks_ans17 = workshopAreaObj.getIsEmergencyExitRemarks();
                showRemarksDialog(str_remarks_ans17,17);
            }
        });
        remarks_ans18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_remarks_ans18 = workshopAreaObj.getMachinaryToolsRemarks();
                showRemarksDialog(str_remarks_ans18,18);
            }
        });
        remarks_ans19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_remarks_ans19 = workshopAreaObj.getMachinesComplyingRemarks();
                showRemarksDialog(str_remarks_ans19,19);
            }
        });
        remarksTxt_ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                str_remarks_ans20 = workshopAreaObj.getTradeName();
                showRemarksDialog(str_remarks_ans20,20);
            }
        });
        remarksTxt_ans14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRemarksDialog(str_remarks_ans21,21);
            }
        });
        remarks_ans20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_remarks_ans22 = workshopAreaObj.getRubberMatsRemarks();
                showRemarksDialog(str_remarks_ans22,22);
            }
        });
        remarks_ans21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_remarks_ans23 = workshopAreaObj.getDgsetRequiredRemarks();
                showRemarksDialog(str_remarks_ans23,23);
            }
        });

        remarks_ans22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_remarks_ans23 = workshopAreaObj.getDgsetInstalledRemarks();
                showRemarksDialog(str_remarks_ans24,24);
            }
        });
        remarks_ans23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_remarks_ans23 = workshopAreaObj.getLatheRequiredRemarks();
                showRemarksDialog(str_remarks_ans25,25);
            }
        });
        remarks_ans24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_remarks_ans23 = workshopAreaObj.getLatheInstalledRemarks();
                showRemarksDialog(str_remarks_ans26,26);
            }
        });
        remarks_ans25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRemarksDialog(str_remarks_ans27,27);
            }
        });



        edit_ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(save_ans3,editTxt_ans3,view);
                ans3edit=1;
            }
        });

        edit_ans4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(save_ans4,spin_ans4,view);
                ans4edit=1;
            }
        });

        edit_ans5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(save_ans5,editTxt_ans5,view);
                ans5edit=1;
            }
        });

        edit_ans6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(save_ans6,spin_ans6,view);
                ans6edit=1;
            }
        });

        edit_ans7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(save_ans7,spin_ans7,view);
                ans7edit=1;
            }
        });
        edit_ans8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(save_ans8,spin_ans8,view);
                ans8edit=1;
            }
        });

        edit_ans9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(save_ans9,spin_ans9,view);
                ans9edit=1;
            }
        });

        edit_ans10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(save_ans10,spin_ans10,view);
                ans10edit=1;
            }
        });

        edit_ans11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(save_ans11,spin_ans11,view);
                ans11edit=1;
            }
        });

        edit_ans12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(save_ans12,spin_ans12,view);
                ans12edit=1;
            }
        });

        edit_ans13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(save_ans13,spin_ans13,view);
                ans13edit=1;
            }
        });
        edit_ans15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(save_ans15,spin_ans15,view);
                ans15edit=1;
            }
        });
        edit_ans16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(save_ans16,spin_ans16,view);
                ans16edit=1;
            }
        });
        edit_ans17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(save_ans17,spin_ans17,view);
                ans17edit=1;
            }
        });
        edit_ans18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(save_ans18,spin_ans18,view);
                ans18edit=1;
            }
        });
        edit_ans19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(save_ans19,spin_ans19,view);
                ans19edit=1;
            }
        });
        edit_ans20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(save_ans20,spin_ans20,view);
                ans20edit=1;
            }
        });

        edit_ans21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(save_ans21,spin_ans21,view);
                ans21edit=1;
            }
        });

        edit_ans22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(save_ans22,spin_ans22,view);
                ans22edit=1;
            }
        });
        edit_ans23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(save_ans23,spin_ans23,view);
                ans23edit=1;
            }
        });
        edit_ans24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(save_ans24,spin_ans24,view);
                ans24edit=1;
            }
        });
        edit_ans25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(save_ans25,spin_ans25,view);
                ans25edit=1;
            }
        });


        save_ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(save_ans3,editTxt_ans3,view,ans3edit);
                ans3edit=2;
            }
        });

        save_ans4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(save_ans4,spin_ans4,view,ans4edit);
                ans4edit=2;
            }
        });

        save_ans5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(save_ans5,editTxt_ans5,view,ans5edit);
                ans5edit=2;
            }
        });

        save_ans6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(save_ans6,spin_ans6,view,ans6edit);
                ans6edit=2;
            }
        });

        save_ans7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spin_ans7.getSelectedItem().toString().equalsIgnoreCase("Flat RCC Roof")){
                    twelve.setVisibility(View.GONE);
                    ten.setVisibility(View.VISIBLE);
                }else if(spin_ans7.getSelectedItem().toString().equalsIgnoreCase("Tin Shaded")) {
                    ten.setVisibility(View.GONE);
                    twelve.setVisibility(View.VISIBLE);
                }
                ControlsUtility.okImageViewAction(save_ans7,spin_ans7,view,ans7edit);
                ans7edit=2;
            }
        });
        save_ans8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(save_ans8,spin_ans8,view,ans8edit);
                ans8edit=2;
            }
        });

        save_ans9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(save_ans9,spin_ans9,view,ans9edit);
                ans9edit=2;
            }
        });

        save_ans10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(save_ans10,spin_ans10,view,ans10edit);
                ans10edit=2;
            }
        });

        save_ans11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(save_ans11,spin_ans11,view,ans11edit);
                ans11edit=2;
            }
        });

        save_ans12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(save_ans12,spin_ans12,view,ans12edit);
                ans12edit=2;
            }
        });

        save_ans13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(save_ans13,spin_ans13,view,ans13edit);
                ans13edit=2;
            }
        });
        save_ans15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(save_ans15,spin_ans15,view,ans15edit);
                ans15edit=2;
            }
        });
        save_ans16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(save_ans16,spin_ans16,view,ans16edit);
                ans16edit=2;
            }
        });
        save_ans17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(save_ans17,spin_ans17,view,ans17edit);
                ans17edit=2;
            }
        });
        save_ans18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(save_ans18,spin_ans18,view,ans18edit);
                ans18edit=2;
            }
        });
        save_ans19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(save_ans19,spin_ans19,view,ans19edit);
                ans19edit=2;
            }
        });
        save_ans20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(save_ans20,spin_ans20,view,ans20edit);
                ans20edit=2;
            }
        });
        save_ans21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spin_ans21.getSelectedItem().toString().equalsIgnoreCase("No")){
                    dg.setVisibility(View.GONE);
                }else {
                    dg.setVisibility(View.VISIBLE);
                }
                ControlsUtility.okImageViewAction(save_ans21,spin_ans21,view,ans21edit);
                ans21edit=2;
            }
        });
        save_ans22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(save_ans22,spin_ans22,view,ans22edit);
                ans22edit=2;
            }
        });

        save_ans23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spin_ans23.getSelectedItem().toString().equalsIgnoreCase("No")){
                    lathe.setVisibility(View.GONE);
                }else {
                    lathe.setVisibility(View.VISIBLE);
                }
                ControlsUtility.okImageViewAction(save_ans23,spin_ans23,view,ans23edit);
                ans23edit=2;
            }
        });
        save_ans24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(save_ans24,spin_ans24,view,ans24edit);
                ans24edit=2;
            }
        });

        save_ans25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(save_ans25,spin_ans25,view,ans25edit);
                ans25edit=2;
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


          btn_submit = (Button)view.findViewById(R.id.btnSubmit);

          edit_ans3 = (ImageView ) view.findViewById(R.id.edit_ans3);
          edit_ans4 = (ImageView ) view.findViewById(R.id.edit_ans4);
          edit_ans5 = (ImageView ) view.findViewById(R.id.edit_ans5);
          edit_ans6 = (ImageView ) view.findViewById(R.id.edit_ans6);
          edit_ans7 = (ImageView ) view.findViewById(R.id.edit_ans7);
          edit_ans8 = (ImageView ) view.findViewById(R.id.edit_ans8);
          edit_ans9 = (ImageView ) view.findViewById(R.id.edit_ans9);
          edit_ans10 = (ImageView ) view.findViewById(R.id.edit_ans10);
          edit_ans11 = (ImageView ) view.findViewById(R.id.edit_ans11);
          edit_ans12 = (ImageView ) view.findViewById(R.id.edit_ans12);
          edit_ans13 = (ImageView ) view.findViewById(R.id.edit_ans13);
          edit_ans15 = (ImageView ) view.findViewById(R.id.edit_ans15);
          edit_ans16 = (ImageView ) view.findViewById(R.id.edit_ans16);
          edit_ans17 = (ImageView ) view.findViewById(R.id.edit_ans17);
          edit_ans18 = (ImageView ) view.findViewById(R.id.edit_ans18);
          edit_ans19 = (ImageView ) view.findViewById(R.id.edit_ans19);
          edit_ans20 = (ImageView ) view.findViewById(R.id.edit_ans20);
          edit_ans21 = (ImageView ) view.findViewById(R.id.edit_ans21);
          edit_ans22 = (ImageView ) view.findViewById(R.id.edit_ans22);
          edit_ans23 = (ImageView ) view.findViewById(R.id.edit_ans23);
          edit_ans24 = (ImageView ) view.findViewById(R.id.edit_ans24);
          edit_ans25 = (ImageView ) view.findViewById(R.id.edit_ans25);

          lathe = (LinearLayout)view.findViewById(R.id.lathe);
          dg = (LinearLayout)view.findViewById(R.id.dg);

          save_ans3 = (ImageView ) view.findViewById(R.id.save_ans3);
          save_ans25 = (ImageView ) view.findViewById(R.id.save_ans25);
          save_ans4 = (ImageView ) view.findViewById(R.id.save_ans4);
          save_ans5 = (ImageView ) view.findViewById(R.id.save_ans5);
          save_ans6 = (ImageView ) view.findViewById(R.id.save_ans6);
          save_ans7 = (ImageView ) view.findViewById(R.id.save_ans7);
          save_ans8 = (ImageView ) view.findViewById(R.id.save_ans8);
          save_ans9 = (ImageView ) view.findViewById(R.id.save_ans9);
          save_ans10 = (ImageView ) view.findViewById(R.id.save_ans10);
          save_ans11 = (ImageView ) view.findViewById(R.id.save_ans11);
          save_ans12 = (ImageView ) view.findViewById(R.id.save_ans12);
          save_ans13 = (ImageView ) view.findViewById(R.id.save_ans13);
          save_ans15 = (ImageView ) view.findViewById(R.id.save_ans15);
          save_ans16 = (ImageView ) view.findViewById(R.id.save_ans16);
          save_ans17 = (ImageView ) view.findViewById(R.id.save_ans17);
          save_ans18 = (ImageView ) view.findViewById(R.id.save_ans18);
          save_ans19 = (ImageView ) view.findViewById(R.id.save_ans19);
          save_ans20 = (ImageView ) view.findViewById(R.id.save_ans20);
          save_ans21 = (ImageView ) view.findViewById(R.id.save_ans21);
          save_ans22 = (ImageView ) view.findViewById(R.id.save_ans22);
          save_ans23 = (ImageView ) view.findViewById(R.id.save_ans23);
          save_ans24 = (ImageView ) view.findViewById(R.id.save_ans24);




          remarks_ans2 = (ImageView ) view.findViewById(R.id.remarks_ans2);
          remarks_ans3 = (ImageView ) view.findViewById(R.id.remarks_ans3);
          remarks_ans4 = (ImageView ) view.findViewById(R.id.remarks_ans4);
          remarks_ans5 = (ImageView ) view.findViewById(R.id.remarks_ans5);
          remarks_ans6 = (ImageView ) view.findViewById(R.id.remarks_ans6);
          remarks_ans7 = (ImageView ) view.findViewById(R.id.remarks_ans7);
          remarks_ans8 = (ImageView ) view.findViewById(R.id.remarks_ans8);
          remarks_ans9 = (ImageView ) view.findViewById(R.id.remarks_ans9);
          remarks_ans10 = (ImageView ) view.findViewById(R.id.remarks_ans10);
          remarks_ans11 = (ImageView ) view.findViewById(R.id.remarks_ans11);
          remarks_ans12 = (ImageView ) view.findViewById(R.id.remarks_ans12);
          remarks_ans13 = (ImageView ) view.findViewById(R.id.remarks_ans13);
          remarks_ans15 = (ImageView ) view.findViewById(R.id.remarks_ans15);
          remarks_ans16 = (ImageView ) view.findViewById(R.id.remarks_ans16);
          remarks_ans17 = (ImageView ) view.findViewById(R.id.remarks_ans17);
          remarks_ans18 = (ImageView ) view.findViewById(R.id.remarks_ans18);
          remarks_ans19 = (ImageView ) view.findViewById(R.id.remarks_ans19);
          remarks_ans20 = (ImageView ) view.findViewById(R.id.remarks_ans20);
          remarksTxt_ans1 = (ImageView)view.findViewById(R.id.remarksTxt_ans1);
          remarksTxt_ans14 = (ImageView)view.findViewById(R.id.remarksTxt_ans14);
          remarks_ans21 = (ImageView ) view.findViewById(R.id.remarks_ans21);
          remarks_ans22 = (ImageView ) view.findViewById(R.id.remarks_ans22);
          remarks_ans23 = (ImageView ) view.findViewById(R.id.remarks_ans23);
          remarks_ans24 = (ImageView ) view.findViewById(R.id.remarks_ans24);
          remarks_ans25 = (ImageView ) view.findViewById(R.id.remarks_ans25);

          spin_ans4 = (Spinner ) view.findViewById(R.id.spin_ans4);
          spin_ans6 = (Spinner ) view.findViewById(R.id.spin_ans6);
          spin_ans8 = (Spinner ) view.findViewById(R.id.spin_ans8);
          spin_ans7 = (Spinner ) view.findViewById(R.id.spin_ans7);
          spin_ans9 = (Spinner ) view.findViewById(R.id.spin_ans9);
          spin_ans10 = (Spinner ) view.findViewById(R.id.spin_ans10);
          spin_ans11 = (Spinner ) view.findViewById(R.id.spin_ans11);
          spin_ans12 = (Spinner ) view.findViewById(R.id.spin_ans12);
          spin_ans13 = (Spinner ) view.findViewById(R.id.spin_ans13);
          spin_ans15 = (Spinner ) view.findViewById(R.id.spin_ans15);
          spin_ans16 = (Spinner ) view.findViewById(R.id.spin_ans16);
          spin_ans17 = (Spinner ) view.findViewById(R.id.spin_ans17);
          spin_ans18 = (Spinner ) view.findViewById(R.id.spin_ans18);
          spin_ans19 = (Spinner ) view.findViewById(R.id.spin_ans19);
          spin_ans20 = (Spinner ) view.findViewById(R.id.spin_ans20);
          spin_ans21 = (Spinner ) view.findViewById(R.id.spin_ans21);
          spin_ans22 = (Spinner ) view.findViewById(R.id.spin_ans22);
          spin_ans23 = (Spinner ) view.findViewById(R.id.spin_ans23);
          spin_ans24 = (Spinner ) view.findViewById(R.id.spin_ans24);
          spin_ans25 = (Spinner ) view.findViewById(R.id.spin_ans25);
          editTxt_ans1 = (EditText ) view.findViewById(R.id.editTxt_ans1);
          editTxt_ans2 = (EditText ) view.findViewById(R.id.editTxt_ans2);
          editTxt_ans3 = (EditText ) view.findViewById(R.id.editTxt_ans3);
          editTxt_ans5 = (EditText ) view.findViewById(R.id.editTxt_ans5);
          editTxt_ans14 = (EditText ) view.findViewById(R.id.editTxt_ans14);

          nc_ans15 = (ToggleButton) view.findViewById(R.id.nc_ans15);
          nc_ans16 = (ToggleButton)view.findViewById(R.id.nc_ans16);
          nc_ans18 = (ToggleButton)view.findViewById(R.id.nc_ans18);
          nc_ans19 = (ToggleButton)view.findViewById(R.id.nc_ans19);
          nc_ans20 = (ToggleButton)view.findViewById(R.id.nc_ans20);
          nc_ans22 = (ToggleButton)view.findViewById(R.id.nc_ans22);
          nc_ans13 = (ToggleButton)view.findViewById(R.id.nc_ans13);
          nc_ans24 = (ToggleButton)view.findViewById(R.id.nc_ans24);
          nc_ans4 = (ToggleButton)view.findViewById(R.id.nc_ans4);
          nc_ans10 = (ToggleButton)view.findViewById(R.id.nc_ans10);

          ten = (LinearLayout)view.findViewById(R.id.ten);
          twelve = (LinearLayout)view.findViewById(R.id.twelve);

          ControlsUtility.setDefaultEditText(editTxt_ans1,workshopAreaObj.getTradeName());
          ControlsUtility.setDefaultEditText(editTxt_ans14,workshopAreaObj.getWorkshopName());
          ControlsUtility.setDefaultEditText(editTxt_ans2,workshopAreaObj.getShiftsUnit());
          ControlsUtility.setDefaultEditText(editTxt_ans3,workshopAreaObj.getActualArea());
          ControlsUtility.setDefaultSpinnerText(spin_ans4,workshopAreaObj.getIstheWorkshopRectangular(),getResources().getStringArray(R.array.yes_no));
          ControlsUtility.setDefaultEditText(editTxt_ans5,workshopAreaObj.getWidthOftheWorkshop());
          ControlsUtility.setDefaultSpinnerText(spin_ans6,workshopAreaObj.getAretheWorkshopWallsofTin(),getResources().getStringArray(R.array.yes_no));
          ControlsUtility.setDefaultSpinnerText(spin_ans7,workshopAreaObj.getWorkShopRoof(),getResources().getStringArray(R.array.yes_no));
          ControlsUtility.setDefaultSpinnerText(spin_ans8,workshopAreaObj.getCeilingHeightlessthan10Feet(),getResources().getStringArray(R.array.yes_no));
          ControlsUtility.setDefaultSpinnerText(spin_ans9,workshopAreaObj.getCeilingHeightlessthan12Feet(),getResources().getStringArray(R.array.yes_no));
          ControlsUtility.setDefaultSpinnerText(spin_ans10,workshopAreaObj.getIstheheavyMachineryLocated(),getResources().getStringArray(R.array.yes_no));
          ControlsUtility.setDefaultSpinnerText(spin_ans11,workshopAreaObj.getItihaveCombinedWorkshop(),getResources().getStringArray(R.array.yes_no));
          ControlsUtility.setDefaultSpinnerText(spin_ans12,workshopAreaObj.getIstheDemarcated(),getResources().getStringArray(R.array.yes_no));
          ControlsUtility.setDefaultSpinnerText(spin_ans13,workshopAreaObj.getEmergencyContactNumberDisplay(),getResources().getStringArray(R.array.yes_no));
          ControlsUtility.setDefaultSpinnerText(spin_ans15,workshopAreaObj.getIsWiresandBoardsCovered(),getResources().getStringArray(R.array.yes_no));
          ControlsUtility.setDefaultSpinnerText(spin_ans16,workshopAreaObj.getIsFireExtinguisherAvailable(),getResources().getStringArray(R.array.yes_no));
          ControlsUtility.setDefaultSpinnerText(spin_ans17,workshopAreaObj.getIsEmergencyExit(),getResources().getStringArray(R.array.yes_no));

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
                    case 2:
                        str_remarks_ans2 = editRemark.getText().toString();
                        workshopAreaObj.setShiftsunitRemarks(str_remarks_ans2);
                        break;
                    case 3:
                        str_remarks_ans3 = editRemark.getText().toString();
                        workshopAreaObj.setActualareaRemarks(str_remarks_ans3);
                        break;
                    case 4:
                        str_remarks_ans4=editRemark.getText().toString();
                        workshopAreaObj.setIstheWorkshopRectangularRemarks(str_remarks_ans4);
                        break;
                    case 5:
                        str_remarks_ans5=editRemark.getText().toString();
                        workshopAreaObj.setWidthOftheWorkshopRemarks(str_remarks_ans4);
                        break;
                    case 6:
                        str_remarks_ans6=editRemark.getText().toString();
                        workshopAreaObj.setAretheWorkshopWallsofTinRemarks(str_remarks_ans6);
                        break;
                    case 7:
                        str_remarks_ans7=editRemark.getText().toString();
                        workshopAreaObj.setWorkShopRoof_Remarks(str_remarks_ans7);
                        break;
                    case 8:
                        str_remarks_ans8=editRemark.getText().toString();
                        workshopAreaObj.setCeilingHeightlessthan10FeetRemarks(str_remarks_ans8);
                        break;
                    case 9:
                        str_remarks_ans9=editRemark.getText().toString();
                        workshopAreaObj.setCeilingHeightlessthan12Feet_Remarks(str_remarks_ans9);
                        break;
                    case 10:
                        str_remarks_ans10=editRemark.getText().toString();
                        workshopAreaObj.setIstheheavyMachineryLocated_Remarks(str_remarks_ans9);
                        break;
                    case 11:
                        str_remarks_ans11=editRemark.getText().toString();
                        break;
                    case 12:
                        str_remarks_ans12=editRemark.getText().toString();
                        break;
                    case 13:
                        str_remarks_ans13=editRemark.getText().toString();
                        workshopAreaObj.setEmergencyContactNumberDisplayRemarks(str_remarks_ans13);
                        break;
                    case 15:
                        str_remarks_ans15=editRemark.getText().toString();
                        workshopAreaObj.setIsWiresandBoardsCoveredRemarks(str_remarks_ans15);
                        break;
                    case 16:
                        str_remarks_ans16=editRemark.getText().toString();
                        workshopAreaObj.setIsFireExtinguisherAvailableRemarks(str_remarks_ans16);
                        break;
                    case 17:
                        str_remarks_ans17=editRemark.getText().toString();
                        workshopAreaObj.setIsEmergencyExitRemarks(str_remarks_ans17);
                        break;
                    case 18:
                        str_remarks_ans18=editRemark.getText().toString();
                        workshopAreaObj.setMachinaryToolsRemarks(str_remarks_ans18);
                        break;
                    case 19:
                        str_remarks_ans19=editRemark.getText().toString();
                        workshopAreaObj.setMachinesComplyingRemarks(str_remarks_ans18);
                        break;
                    case 20:
                        str_remarks_ans20=editRemark.getText().toString();
                        break;
                    case 21:
                        str_remarks_ans21=editRemark.getText().toString();
                        break;
                    case 22:
                        str_remarks_ans22=editRemark.getText().toString();
                        workshopAreaObj.setRubberMatsRemarks(str_remarks_ans22);
                        break;
                    case 23:
                        str_remarks_ans23=editRemark.getText().toString();
                        break;
                    case 24:
                        str_remarks_ans24=editRemark.getText().toString();
                        break;
                    case 25:
                        str_remarks_ans25=editRemark.getText().toString();
                        break;
                    case 26:
                        str_remarks_ans26=editRemark.getText().toString();
                        break;
                    case 27:
                        str_remarks_ans27=editRemark.getText().toString();
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

        if(ans3edit == 2 && ans4edit == 2 && ans5edit == 2 && ans6edit == 2 && ans7edit == 2 && ans10edit == 2  && ans13edit == 2
                && ans15edit == 2 && ans16edit == 2 && ans17edit == 2 && ans18edit == 2 && ans19edit == 2 && ans20edit == 2
                && ans21edit == 2 &&  ans23edit == 2  && ans25edit == 2){
            return true;
        }else {
            return false;
        }
    }



    public JSONObject getWorkshopAreaSyncData(List<WorkshopAreaModel> workshopAreaObj) {

        JSONObject resultJsonObj = new JSONObject();
        JSONObject payloadJsonObj = new JSONObject();
        JSONObject workshopArea = new JSONObject();

        JSONArray workshopStaffarray = new JSONArray();
        try {

            for (int i = 0;i<workshopAreaObj.size();i++){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("flag",workshopAreaObj.get(i).getFlag());
                jsonObject.put("tradeId", workshopAreaObj.get(i).getTradeId());
                jsonObject.put("tradeName",workshopAreaObj.get(i).getTradeName());
                jsonObject.put("shiftsUnit", editTxt_ans2.getText().toString());
                jsonObject.put("shiftsunitRemarks", str_remarks_ans2);
                jsonObject.put("shiftsNc", "");
                jsonObject.put("ncvtNorms", "");
                jsonObject.put("ncvtnormsRemarks", "");
                jsonObject.put("ncvtNc", "");
                jsonObject.put("actualArea", editTxt_ans3.getText().toString());
                jsonObject.put("actualareaRemarks", str_remarks_ans3);
                jsonObject.put("actualareaNc", "");
                jsonObject.put("shortageArea", "");
                jsonObject.put("shortageareaRemarks", "");
                jsonObject.put("shortageAreaNc", "");
                jsonObject.put("refId", workshopAreaObj.get(i).getRefId()); //TODO
                jsonObject.put("istheWorkshopRectangular", spin_ans4.getSelectedItem().toString());
                jsonObject.put("istheWorkshopRectangularRemarks", str_remarks_ans4);
                if(nc_ans4.isChecked()){
                    jsonObject.put("istheWorkshopRectangularNc", "1");
                }else {
                    jsonObject.put("istheWorkshopRectangularNc", "0");
                }
                //jsonObject.put("istheWorkshopRectangularNc", "");
                jsonObject.put("widthOftheWorkshop", editTxt_ans5.getText().toString());
                jsonObject.put("widthOftheWorkshopRemarks", str_remarks_ans5);
                jsonObject.put("widthOftheWorkshopNc", "");
                jsonObject.put("aretheWorkshopWallsofTin", spin_ans6.getSelectedItem().toString());
                jsonObject.put("aretheWorkshopWallsofTinRemarks", str_remarks_ans6);
                jsonObject.put("aretheWorkshopWallsofTinNc", "");
                jsonObject.put("istheheavyMachineryLocated", spin_ans10.getSelectedItem().toString());
                jsonObject.put("istheheavyMachineryLocatedRemarks", str_remarks_ans10);
                if(nc_ans10.isChecked()){
                    jsonObject.put("istheheavyMachineryLocatedNc", "1");
                }else {
                    jsonObject.put("istheheavyMachineryLocatedNc", "0");
                }

                jsonObject.put("itihaveCombinedWorkshop", spin_ans11.getSelectedItem().toString());
                jsonObject.put("itihaveCombinedWorkshopRemarks", str_remarks_ans11);
                jsonObject.put("itihaveCombinedWorkshopNc", "");
                jsonObject.put("istheDemarcated", spin_ans12.getSelectedItem().toString());
                jsonObject.put("istheDemarcatedRemarks", str_remarks_ans12);
                jsonObject.put("istheDemarcatedNc", "");
                jsonObject.put("emergencyContactNumberDisplay", spin_ans13.getSelectedItem().toString());
                jsonObject.put("emergencyContactNumberDisplayRemarks", str_remarks_ans13);
                if(nc_ans13.isChecked()){
                    jsonObject.put("emergencyContactNumberDisplayNc", "1");
                }else {
                    jsonObject.put("emergencyContactNumberDisplayNc", "0");
                }

                jsonObject.put("WorkShopRoof", spin_ans7.getSelectedItem().toString());
                jsonObject.put("WorkShopRoof_Remarks", str_remarks_ans7);
                jsonObject.put("WorkShopRoof_Nc", "");
                jsonObject.put("ceilingHeightlessthan12Feet", spin_ans9.getSelectedItem().toString());
                jsonObject.put("ceilingHeightlessthan12Feet_Remarks", str_remarks_ans9);
                jsonObject.put("ceilingHeightlessthan12Feet_Nc", "");
                jsonObject.put("ceilingHeightlessthan10Feet", spin_ans8.getSelectedItem().toString());
                jsonObject.put("ceilingHeightlessthan10Feet_Remarks", str_remarks_ans8);
                jsonObject.put("areAlltheMachineryToolsAndEquipment", spin_ans18.getSelectedItem().toString());
                jsonObject.put("areAlltheMachineryToolsAndEquipment_Remarks", str_remarks_ans18);
                if (nc_ans18.isChecked()) {
                    jsonObject.put("areAlltheMachineryToolsAndEquipment_Nc", "1");
                } else {
                    jsonObject.put("areAlltheMachineryToolsAndEquipment_Nc", "0");
                }
                jsonObject.put("areAlltheMachinesComplying", spin_ans19.getSelectedItem().toString());
                jsonObject.put("areAlltheMachinesComplying_Remarks", str_remarks_ans19);
                if (nc_ans19.isChecked()) {
                    jsonObject.put("areAlltheMachinesComplying_Nc", "1");
                } else {
                    jsonObject.put("areAlltheMachinesComplying_Nc", "0");
                }
                jsonObject.put("areRubberMatsAvailable", spin_ans20.getSelectedItem().toString());
                jsonObject.put("areRubberMatsAvailable_Remarks", str_remarks_ans22);
                if (nc_ans20.isChecked()) {
                    jsonObject.put("areRubberMatsAvailable_Nc", "1");
                } else {
                    jsonObject.put("areRubberMatsAvailable_Nc", "0");
                }
                jsonObject.put("istheDGSetRequired", spin_ans21.getSelectedItem().toString());
                jsonObject.put("istheDGSetRequired_Remarks", str_remarks_ans23);
                jsonObject.put("istheDGSetRequired_Nc", "");
                jsonObject.put("istheDGSetInstalled", spin_ans22.getSelectedItem().toString());
                jsonObject.put("istheDGSetInstalled_Remarks", str_remarks_ans24);
                if(nc_ans22.isChecked()){
                    jsonObject.put("istheDGSetInstalled_Nc", "1");
                }else {
                    jsonObject.put("istheDGSetInstalled_Nc", "0");
                }
                //jsonObject.put("istheDGSetInstalled_Nc", "");
                jsonObject.put("ceilingHeightlessthan10Feet_Nc", "");
                jsonObject.put("isWiresandBoardsCovered", spin_ans15.getSelectedItem().toString());
                jsonObject.put("isFireExtinguisherAvailable", spin_ans16.getSelectedItem().toString());
                jsonObject.put("isEmergencyExit", spin_ans17.getSelectedItem().toString());
                jsonObject.put("isWiresandBoardsCovered_Remarks",str_remarks_ans15);
                jsonObject.put("isFireExtinguisherAvailable_Remarks",str_remarks_ans16);
                jsonObject.put("isEmergencyExit_Remarks",str_remarks_ans17);
                if(nc_ans15.isChecked()) {
                    jsonObject.put("isWiresandBoardsCovered_Nc", "1");
                }else {
                    jsonObject.put("isWiresandBoardsCovered_Nc", "0");
                }
                if(nc_ans16.isChecked()) {
                    jsonObject.put("isFireExtinguisherAvailable_Nc", "1");
                }else {
                    jsonObject.put("isFireExtinguisherAvailable_Nc", "0");
                }
                jsonObject.put("isEmergencyExit_Nc", "");
                jsonObject.put("latheMachineRequired", spin_ans23.getSelectedItem().toString());
                jsonObject.put("latheMachineRequired_Remarks", str_remarks_ans25);
                jsonObject.put("latheMachineRequired_Nc", "");
                jsonObject.put("latheMachineInstalledasPerNorms", spin_ans24.getSelectedItem().toString());
                jsonObject.put("latheMachineInstalledasPerNorms_Remarks", str_remarks_ans26);
                if(nc_ans24.isChecked()) {
                    jsonObject.put("latheMachineInstalledasPerNorms_Nc", "1");
                }else {
                    jsonObject.put("latheMachineInstalledasPerNorms_Nc", "0");
                }

                jsonObject.put("majorMachine", spin_ans25.getSelectedItem().toString());
                jsonObject.put("majormachineRemarks", str_remarks_ans27);
                jsonObject.put("majormachineNc", "");


                workshopStaffarray.put(jsonObject);
            }

            workshopArea.put("workshop",workshopStaffarray);
            workshopArea.put("yearwisecollegeid", this.workshopAreaObj.getYearWiseCollegeid());

            payloadJsonObj.put("payload",workshopArea);

            resultJsonObj.put("result",payloadJsonObj);

            Log.e("result",resultJsonObj.toString());

        }catch (Exception e){
            e.printStackTrace();
        }

        return resultJsonObj;
    }


    private void navigate() {
        Intent intent = new Intent(context, CategoryActivity.class);
        intent.putExtra("YearWiseCollegeId", workshopAreaObj.getYearWiseCollegeid());
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
                    boolean updation_status = controller.saveWorkshopArea(workshopAreaObj, "complete");
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
