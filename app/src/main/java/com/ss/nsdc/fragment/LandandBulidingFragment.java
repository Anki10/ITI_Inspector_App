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
import android.support.v7.widget.LinearLayoutCompat;
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
import com.ss.nsdc.entity.LandandBuilding;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mayank on 03/10/2016.
 */
public class LandandBulidingFragment extends Fragment {

    View view;

    private ProgressDialog ringProgressDialog;
    Context context;

    EditText editRemark;
    Button buttonRemark;
    Button buttonCancel;

    Dialog customDialog;

    View customDialogView;

    TextView textRemarkType;

    private int ans1edit, ans2edit, ans3edit, ans4edit, ans5edit, ans6edit, ans7edit, ans8edit, ans9edit, ans10edit,
            ans11edit, ans12edit ,ans13edit,ans14edit,ans15edit,ans16edit,ans17edit,ans18edit,ans19edit,ans20edit,ans25edit,
    ans26edit,ans27edit,ans28edit,ans29edit,ans30edit,ans31edit,ans32edit,ans33edit,ans34edit,ans35edit,ans36edit,ans37edit;


    //UtilityService utility = UtilityService.getInstance();

    Button btn_submit;

    UtilityService utility = UtilityService.getInstance();
    LandandBuilding landandBuildingInfo;
    private int ans21edit,ans22edit,ans23edit,ans24edit;

    private EditText editLandOpenArea;
    private EditText editLandBuildUpArea;
    private EditText editLandTotalLand;
    private EditText editLandDate;
    private EditText editLandAgreement;
    private EditText editLandDurationLease;
    private EditText editLandArchitechName;
    private EditText editLandArchitechReg;
    private EditText editLandNoofFloor;
    private Spinner editLandSafetyLift;
    private EditText editLandOrgName;
    private EditText editLandCapacityLift;


    private Spinner spinLandBuildingType;
    private Spinner spinLandSitmap;
    private Spinner spinLandDimension;
    private Spinner spinLandMap;
    private Spinner spinLandFloor;
    private Spinner spinAvailblityofWashroomLand;
    private Spinner spinWashroomFunctionalLand;
    private Spinner spinLandWallsITI;
    private Spinner spinLandFloorCemented;
    private Spinner spinLandITISituated;
    private Spinner spinSafeWaterLand;
    private Spinner spinhouseKeepingStaffLand;
    private Spinner spinPlacementcellLand;
    private Spinner spinLibraryLand;
    private Spinner spinStaffRoomLand;
    private Spinner spinFireLand;
    private Spinner spinCompetentAuthority;
    private Spinner spinApproachRoad;
    private Spinner spinEntranceRoad;
    private Spinner spinLandSwitchBoard;
    private Spinner spinLandVentilation;
    private Spinner spinIsMasterPlan;
    private Spinner spinIsPremisesShared;
    private Spinner spinSeperateEntrance;
    private Spinner spinSoundProofPartition;
    private Spinner spinStaircase;
    private Spinner spinLandPrescribed;
    private Spinner spinLandConstructed;

    private ImageView imgLandOpenAreaEdit;
    private ImageView imgLandOpenAreaSave;
    private ImageView imgLandOpenAreaRemarks;
    private ImageView imgLandBuildUpAreaEdit;
    private ImageView imgLandBuildUpAreaSave;
    private ImageView imgLandBuildUpAreaRemarks;
    private ImageView imgLandTotalLandEdit;
    private ImageView imgLandTotalLandSave;
    private ImageView imgLandTotalLandRemarks;
    private ImageView imgLandBuildingTypeEdit;
    private ImageView imgLandBuildingTypeSave;
    private ImageView imgLandBuildingTypeRemarks;
    private ImageView imgLandDateRemarks;
    private ImageView imgLandDateSave;
    private ImageView imgLandDateEdit;
    private ImageView imgLandAgreementEdit;
    private ImageView imgLandAgreementSave;
    private ImageView imgLandAgreementRemarks;
    private ImageView imgLandDurationLeaseEdit;
    private ImageView imgLandDurationLeaseSave;
    private ImageView imgLandDurationLeaseRemarks;
    private ImageView imgLandSitmapEdit;
    private ImageView imgLandSitmapSave;
    private ImageView imgLandSitmapRemarks;
    private ImageView imgLandDimensionEdit;
    private ImageView imgLandDimensionSave;
    private ImageView imgLandDimensionRemarks;
    private ImageView imgLandMapEdit;
    private ImageView imgLandMapSave;
    private ImageView imgLandMapRemarks;
    private ImageView imgLandArchtechNameEdit;
    private ImageView imgLandArchtechNameSave;
    private ImageView imgLandArchtechNameRemarks;
    private ImageView imgLandNoofFloorEdit;
    private ImageView imgLandNoofFloorSave;
    private ImageView imgLandArchitechRegRemarks;
    private ImageView imgLandConstructedSave;
    private ImageView imgLandConstructedEdit;
    private ImageView imgLandNoofFloorRemarks;
    private ImageView imgLandConstructedRemarks;
    private ImageView imgLandFloorEdit;
    private ImageView imgLandFloorSave;
    private ImageView imgLandFloorRemarks;
    private ImageView imgLandOrgNameEdit;
    private ImageView imgLandOrgNameSave;
    private ImageView imgLandOrgNameRemarks;
    private ImageView imgLandCapacityLiftRemarks;
    private ImageView imgLandSafetyLiftEdit;
    private ImageView imgLandSafetyLiftSave;
    private ImageView imgLandSafetyLiftRemarks;
    private ImageView imgAvailblityofWashroomLandEdit;
    private ImageView imgAvailblityofWashroomLandSave;
    private ImageView imgAvailblityofWashroomLandRemarks;
    private ImageView imgWashroomFunctionalLandEdit;
    private ImageView imgWashroomFunctionalLandSave;
    private ImageView imgWashroomFunctionalLandRemarks;
    private ImageView imgLandWallsITIEdit;
    private ImageView imgLandWallsITISave;
    private ImageView imgLandWallsITIRemarks;
    private ImageView imgLandFloorCementedEdit;
    private ImageView imgLandFloorCementedSave;
    private ImageView imgLandFloorCementedRemarks;
    private ImageView imgLandITISituatedEdit;
    private ImageView imgLandITISituatedSave;
    private ImageView imgLandITISituatedRemarks;
    private ImageView imgSafeWaterLandEdit;
    private ImageView imgSafeWaterLandSave;
    private ImageView imgSafeWaterLandRemarks;
    private ImageView imghouseKeepingStaffLandEdit;
    private ImageView imghouseKeepingStaffLandSave;
    private ImageView imghouseKeepingStaffLandRemarks;
    private ImageView imgPlacementcellLandEdit;
    private ImageView imgPlacementcellLandSave;
    private ImageView imgPlacementcellLandRemarks;
    private ImageView imgLibraryLandEdit;
    private ImageView imgLibraryLandSave;
    private ImageView imgLibraryLandRemarks;
    private ImageView imgStaffRoomLandEdit;
    private ImageView imgStaffRoomLandSave;
    private ImageView imgStaffRoomLandRemarks;
    private ImageView imgFireLandEdit;
    private ImageView imgFireLandSave;
    private ImageView imgFireLandRemarks;
    private ImageView imgCompetentAuthorityEdit;
    private ImageView imgCompetentAuthoritySave;
    private ImageView imgCompetentAuthorityRemarks;
    private ImageView imgApproachRoadEdit;
    private ImageView imgApproachRoadSave;
    private ImageView imgApproachRoadRemarks;
    private ImageView imgEntranceRoadEdit;
    private ImageView imgEntranceRoadSave;
    private ImageView imgEntranceRoadRemarks;
    private ImageView imgLandSwitchBoardEdit;
    private ImageView imgLandSwitchBoardSave;
    private ImageView imgLandSwitchBoardRemarks;
    private ImageView imgLandVentilationEdit;
    private ImageView imgLandVentilationSave;
    private ImageView imgLandVentilationRemarks;
    private ImageView imgLandPrescribedEdit;
    private ImageView imgLandPrescribedSave;
    private ImageView imgLandPrescribedRemarks;
    private ImageView imgIsMasterPlanEdit;
    private ImageView imgIsPremisesSharedEdit;
    private ImageView imgSeperateEntranceEdit;
    private ImageView imgSoundProofPartitionEdit;
    private ImageView imgStaircaseEdit;


    private ImageView imgIsMasterPlanSave;
    private ImageView imgIsPremisesSharedSave;
    private ImageView imgSeperateEntranceSave;
    private ImageView imgSoundProofPartitionSave;
    private ImageView imgStaircaseSave;


    private ImageView imgIsMasterPlanRemarks;
    private ImageView imgIsPremisesSharedRemarks;
    private ImageView imgSeperateEntranceRemarks;
    private ImageView imgSoundProofPartitionRemarks;
    private ImageView imgStaircaseRemarks;


    private ToggleButton imgIsPremisesSharedNC;
    private ToggleButton imgSeperateEntranceNC;
    private ToggleButton imgSoundProofPartitionNC;
    private ToggleButton imgStaircaseNC;

    private ToggleButton imgLandBuildingTypeNC;
    private ToggleButton imgLandDateNC;
    private LinearLayout organistaion;
    private LinearLayout safety;
    private ToggleButton imgLandAgreementNC;
    private ToggleButton imgLandDurationLeaseNC;
    private ToggleButton imgLandFloorNC;
    private ToggleButton imgLandSafetyLiftNC;
    private ToggleButton imgLandWallsITINC;
    private ToggleButton imgLandFloorCementedNC;
    private ToggleButton imgLandITISituatedNC;
    private ToggleButton imgAvailblityofWashroomLandNC;
    private ToggleButton imgWashroomFunctionalLandNC;
    private ToggleButton imgSafeWaterLandNC;
    private ToggleButton imgLandSwitchBoardNC;
    private ToggleButton imgLandVentilationNC;
    private ToggleButton imgLandSitmapNC;
    private ToggleButton imgLandDimensionNC;
    private ToggleButton imgLandMapNC;
    private ToggleButton imgLandFireNC;
    private ToggleButton imgEntranceRoadNC;
    private ToggleButton imghouseKeepingStaffLandNC;

    private LinearLayout ll_noof_floor;
    private LinearLayout expiry;
    private LinearLayout duration;

    public LandandBulidingFragment(LandandBuilding landandBuildingInfo) {
        this.landandBuildingInfo = landandBuildingInfo;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_land_building, container, false);
        context = container.getContext();
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((FormActivity) getActivity()).getSupportActionBar().setTitle("Land and Building Details");
        Bundle bundle = this.getArguments();
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

        ControlsUtility.setDefaultEditText(editLandOpenArea, landandBuildingInfo.getTotalOpenArea());

        ControlsUtility.setDefaultEditText(editLandBuildUpArea, landandBuildingInfo.getTotalBuildArea());

        ControlsUtility.setDefaultEditText(editLandTotalLand, landandBuildingInfo.getTotalLand());

        ControlsUtility.setDefaultSpinnerText(spinLandBuildingType, landandBuildingInfo.getBuildingType(), getResources().getStringArray(R.array.building));

        ControlsUtility.setDefaultEditText(editLandDate, landandBuildingInfo.getDateofOccupancy());

        ControlsUtility.setDefaultEditText(editLandNoofFloor,landandBuildingInfo.getNoofFloors());

        ControlsUtility.setDefaultSpinnerText(spinLandFloor,landandBuildingInfo.getLiftinInstitute(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultEditText(editLandOrgName,landandBuildingInfo.getOrgInstalledLift());

        ControlsUtility.setDefaultEditText(editLandCapacityLift,landandBuildingInfo.getCapacityofLift());

        ControlsUtility.setDefaultSpinnerText(editLandSafetyLift,landandBuildingInfo.getSafetyCertificateofLift(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinLandWallsITI,landandBuildingInfo.getWallOfIti(),getResources().getStringArray(R.array.wall));

        ControlsUtility.setDefaultSpinnerText(spinLandFloorCemented,landandBuildingInfo.getFloorisCemented(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinLandITISituated,landandBuildingInfo.getItiSituatedintheSameCampus(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinAvailblityofWashroomLand,landandBuildingInfo.getAvaibilityofSeparateMaleFemaleWashroom(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinWashroomFunctionalLand,landandBuildingInfo.getAretheWashroomFunctional(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinFireLand,landandBuildingInfo.getAvaibiltyofFireextinguisher(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinSafeWaterLand,landandBuildingInfo.getAvaibilityofsafeDrinkingwater(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinhouseKeepingStaffLand,landandBuildingInfo.getAvaibilityHousekeepingstaff(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinPlacementcellLand,landandBuildingInfo.getAvaibilityofPlacementcell(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinLibraryLand,landandBuildingInfo.getAvaibilityofLibraray(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinStaffRoomLand,landandBuildingInfo.getAvaibilityofStaffroom(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultEditText(editLandDurationLease,landandBuildingInfo.getDurationlease());

        ControlsUtility.setDefaultSpinnerText(spinIsMasterPlan,landandBuildingInfo.getIsMasterPlan(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinCompetentAuthority,landandBuildingInfo.getComptent(),getResources().getStringArray(R.array.competent));

        ControlsUtility.setDefaultEditText(editLandAgreement,landandBuildingInfo.getExpiryofAgreement());

        ControlsUtility.setDefaultSpinnerText(spinLandSitmap,landandBuildingInfo.getSitmap(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinLandDimension,landandBuildingInfo.getDimension(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinLandMap,landandBuildingInfo.getMap(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinApproachRoad,landandBuildingInfo.getApproach(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinEntranceRoad,landandBuildingInfo.getEntrance(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinSoundProofPartition,landandBuildingInfo.getSoundProofPartition(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinIsPremisesShared,landandBuildingInfo.getIsPremisesShared(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinSeperateEntrance,landandBuildingInfo.getSeperateEntrance(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinLandSwitchBoard,landandBuildingInfo.getSwitchBoard(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinLandVentilation,landandBuildingInfo.getVentilation(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinLandConstructed,landandBuildingInfo.getConstructed(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinLandPrescribed,landandBuildingInfo.getPrescribed(),getResources().getStringArray(R.array.yes_no));

        ControlsUtility.setDefaultSpinnerText(spinStaircase,landandBuildingInfo.getStaircase(),getResources().getStringArray(R.array.yes_no));

    }


    public void initViews() {

        customDialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_remark, null, false);


        editRemark = (EditText) customDialogView.findViewById(R.id.editRemark);
        buttonRemark = (Button) customDialogView.findViewById(R.id.buttonRemark);
        buttonCancel = (Button) customDialogView.findViewById(R.id.buttonCancel);
        textRemarkType = (TextView) customDialogView.findViewById(R.id.textRemarkType);

        editLandOpenArea = (EditText) view.findViewById(R.id.editLandOpenArea);
        editLandBuildUpArea = (EditText) view.findViewById(R.id.editLandBuildUpArea);
        editLandTotalLand = (EditText) view.findViewById(R.id.editLandTotalLand);
        editLandDate = (EditText) view.findViewById(R.id.editLandDate);
        editLandNoofFloor = (EditText)view.findViewById(R.id.editLandNoofFloor);
        editLandOrgName = (EditText)view.findViewById(R.id.editLandOrgName);
        editLandCapacityLift = (EditText)view.findViewById(R.id.editLandCapacityLift);
        editLandSafetyLift = (Spinner) view.findViewById(R.id.editLandSafetyLift);
        editLandDurationLease = (EditText)view.findViewById(R.id.editLandDurationLease);
        editLandAgreement = (EditText)view.findViewById(R.id.editLandAgreement);

        imgEntranceRoadNC = (ToggleButton)view.findViewById(R.id.imgEntranceRoadNC);

        organistaion = (LinearLayout)view.findViewById(R.id.orgnisation);
        safety = (LinearLayout)view.findViewById(R.id.safety);
        expiry = (LinearLayout)view.findViewById(R.id.expiry);
        duration = (LinearLayout)view.findViewById(R.id.duration);

        spinLandBuildingType = (Spinner) view.findViewById(R.id.spinLandBuildingType);
        spinLandWallsITI = (Spinner)view.findViewById(R.id.spinLandWallsITI);
        spinLandFloorCemented =(Spinner)view.findViewById(R.id.spinLandFloorCemented);
        spinLandITISituated = (Spinner)view.findViewById(R.id.spinLandITISituated);
        spinAvailblityofWashroomLand = (Spinner)view.findViewById(R.id.spinAvailblityofWashroomLand);
        spinWashroomFunctionalLand = (Spinner)view.findViewById(R.id.spinWashroomFunctionalLand);
        spinSafeWaterLand = (Spinner)view.findViewById(R.id.spinSafeWaterLand);
        spinhouseKeepingStaffLand = (Spinner)view.findViewById(R.id.spinhouseKeepingStaffLand);
        spinPlacementcellLand = (Spinner)view.findViewById(R.id.spinPlacementcellLand);
        spinLibraryLand = (Spinner)view.findViewById(R.id.spinLibraryLand);
        spinStaffRoomLand = (Spinner)view.findViewById(R.id.spinStaffRoomLand);
        spinFireLand = (Spinner)view.findViewById(R.id.spinFireLand);
        spinLandSitmap = (Spinner)view.findViewById(R.id.spinLandSitmap);
        spinLandDimension = (Spinner)view.findViewById(R.id.spinLandDimension);
        spinLandMap = (Spinner)view.findViewById(R.id.spinLandMap);
        spinLandFloor = (Spinner)view.findViewById(R.id.spinLandFloor);
        spinCompetentAuthority = (Spinner)view.findViewById(R.id.spinCompetentAuthority);
        spinApproachRoad = (Spinner)view.findViewById(R.id.spinApproachRoad);
        spinEntranceRoad = (Spinner)view.findViewById(R.id.spinEntranceRoad);
        spinLandSwitchBoard = (Spinner)view.findViewById(R.id.spinLandSwitchBoard);
        spinLandVentilation = (Spinner)view.findViewById(R.id.spinLandVentilation);
        spinIsMasterPlan = (Spinner)view.findViewById(R.id.spinIsMasterPlan);
        spinIsPremisesShared = (Spinner)view.findViewById(R.id.spinIsPremisesShared);
        spinSeperateEntrance = (Spinner)view.findViewById(R.id.spinSeperateEntrance);
        spinSoundProofPartition = (Spinner)view.findViewById(R.id.spinSoundProofPartition);
        spinStaircase = (Spinner)view.findViewById(R.id.spinStaircase);
        spinLandConstructed = (Spinner)view.findViewById(R.id.spinLandConstructed);
        spinLandPrescribed = (Spinner)view.findViewById(R.id.spinLandPrescribed);

        imghouseKeepingStaffLandNC = (ToggleButton)view.findViewById(R.id.imghouseKeepingStaffLandNC);

        ll_noof_floor = (LinearLayout)view.findViewById(R.id.ll_noof_floor);

        imgLandOpenAreaEdit = (ImageView) view.findViewById(R.id.imgLandOpenAreaEdit);
        imgLandOpenAreaSave = (ImageView) view.findViewById(R.id.imgLandOpenAreaSave);
        imgLandConstructedEdit = (ImageView)view.findViewById(R.id.imgLandConstructedEdit);
        imgLandConstructedSave = (ImageView)view.findViewById(R.id.imgLandConstructedSave);
        imgLandConstructedRemarks = (ImageView)view.findViewById(R.id.imgLandConstructedRemarks);
        imgLandPrescribedEdit = (ImageView)view.findViewById(R.id.imgLandPrescribedEdit);
        imgLandPrescribedSave = (ImageView)view.findViewById(R.id.imgLandPrescribedSave);
        imgLandPrescribedRemarks = (ImageView)view.findViewById(R.id.imgLandPrescribedRemarks);
        imgLandOpenAreaRemarks = (ImageView) view.findViewById(R.id.imgLandOpenAreaRemarks);
        imgLandBuildUpAreaEdit = (ImageView) view.findViewById(R.id.imgLandBuildUpAreaEdit);
        imgLandBuildUpAreaSave = (ImageView) view.findViewById(R.id.imgLandBuildUpAreaSave);
        imgLandBuildUpAreaRemarks = (ImageView) view.findViewById(R.id.imgLandBuildUpAreaRemarks);
        imgLandTotalLandEdit = (ImageView) view.findViewById(R.id.imgLandTotalLandEdit);
        imgLandTotalLandSave = (ImageView) view.findViewById(R.id.imgLandTotalLandSave);
        imgLandTotalLandRemarks = (ImageView) view.findViewById(R.id.imgLandTotalLandRemarks);
        imgLandBuildingTypeEdit = (ImageView) view.findViewById(R.id.imgLandBuildingTypeEdit);
        imgLandBuildingTypeSave = (ImageView) view.findViewById(R.id.imgLandBuildingTypeSave);
        imgLandBuildingTypeRemarks = (ImageView) view.findViewById(R.id.imgLandBuildingTypeRemarks);
        imgLandDateEdit = (ImageView) view.findViewById(R.id.imgLandDateEdit);
        imgLandDateSave = (ImageView) view.findViewById(R.id.imgLandDateSave);
        imgLandDateRemarks = (ImageView) view.findViewById(R.id.imgLandDateRemarks);
        imgLandNoofFloorRemarks = (ImageView)view.findViewById(R.id.imgLandNoofFloorRemarks);
        imgLandAgreementEdit= (ImageView)view.findViewById(R.id.imgLandAgreementEdit);
        imgLandAgreementSave= (ImageView)view.findViewById(R.id.imgLandAgreementSave);
        imgLandAgreementRemarks= (ImageView)view.findViewById(R.id.imgLandAgreementRemarks);
        imgLandDurationLeaseEdit= (ImageView)view.findViewById(R.id.imgLandDurationLeaseEdit);
        imgLandDurationLeaseSave= (ImageView)view.findViewById(R.id.imgLandDurationLeaseSave);
        imgLandDurationLeaseRemarks= (ImageView)view.findViewById(R.id.imgLandDurationLeaseRemarks);
        imgLandSitmapEdit= (ImageView)view.findViewById(R.id.imgLandSitmapEdit);
        imgLandSitmapSave= (ImageView)view.findViewById(R.id.imgLandSitmapSave);
        imgLandSitmapRemarks= (ImageView)view.findViewById(R.id.imgLandSitmapRemarks);
        imgLandNoofFloorEdit= (ImageView)view.findViewById(R.id.imgLandNoofFloorEdit);
        imgLandNoofFloorSave= (ImageView)view.findViewById(R.id.imgLandNoofFloorSave);
        imgLandNoofFloorRemarks= (ImageView)view.findViewById(R.id.imgLandNoofFloorRemarks);
        imgLandFloorEdit= (ImageView)view.findViewById(R.id.imgLandFloorEdit);
        imgLandFloorSave= (ImageView)view.findViewById(R.id.imgLandFloorSave);
        imgLandOrgNameEdit= (ImageView)view.findViewById(R.id.imgLandOrgNameEdit);
        imgLandOrgNameSave= (ImageView)view.findViewById(R.id.imgLandOrgNameSave);
        imgLandOrgNameRemarks= (ImageView)view.findViewById(R.id.imgLandOrgNameRemarks);
        imgLandCapacityLiftRemarks= (ImageView)view.findViewById(R.id.imgLandCapacityLiftRemarks);
        imgLandSafetyLiftEdit= (ImageView)view.findViewById(R.id.imgLandSafetyLiftEdit);
        imgLandSafetyLiftSave= (ImageView)view.findViewById(R.id.imgLandSafetyLiftSave);
        imgLandSafetyLiftRemarks= (ImageView)view.findViewById(R.id.imgLandSafetyLiftRemarks);
        imgAvailblityofWashroomLandEdit= (ImageView)view.findViewById(R.id.imgAvailblityofWashroomLandEdit);
        imgAvailblityofWashroomLandSave= (ImageView)view.findViewById(R.id.imgAvailblityofWashroomLandSave);
        imgAvailblityofWashroomLandRemarks= (ImageView)view.findViewById(R.id.imgAvailblityofWashroomLandRemarks);
        imgWashroomFunctionalLandEdit= (ImageView)view.findViewById(R.id.imgWashroomFunctionalLandEdit);
        imgWashroomFunctionalLandSave= (ImageView)view.findViewById(R.id.imgWashroomFunctionalLandSave);
        imgWashroomFunctionalLandRemarks= (ImageView)view.findViewById(R.id.imgWashroomFunctionalLandRemarks);
        imgLandWallsITIEdit= (ImageView)view.findViewById(R.id.imgLandWallsITIEdit);
        imgLandWallsITISave= (ImageView)view.findViewById(R.id.imgLandWallsITISave);
        imgLandWallsITIRemarks= (ImageView)view.findViewById(R.id.imgLandWallsITIRemarks);
        imgLandFloorCementedEdit= (ImageView)view.findViewById(R.id.imgLandFloorCementedEdit);
        imgLandFloorCementedSave= (ImageView)view.findViewById(R.id.imgLandFloorCementedSave);
        imgLandFloorCementedRemarks= (ImageView)view.findViewById(R.id.imgLandFloorCementedRemarks);
        imgLandITISituatedEdit= (ImageView)view.findViewById(R.id.imgLandITISituatedEdit);
        imgLandITISituatedSave= (ImageView)view.findViewById(R.id.imgLandITISituatedSave);
        imgLandITISituatedRemarks= (ImageView)view.findViewById(R.id.imgLandITISituatedRemarks);
        imgSafeWaterLandEdit= (ImageView)view.findViewById(R.id.imgSafeWaterLandEdit);
        imgSafeWaterLandSave= (ImageView)view.findViewById(R.id.imgSafeWaterLandSave);
        imgSafeWaterLandRemarks= (ImageView)view.findViewById(R.id.imgSafeWaterLandRemarks);
        imghouseKeepingStaffLandEdit= (ImageView)view.findViewById(R.id.imghouseKeepingStaffLandEdit);
        imghouseKeepingStaffLandSave= (ImageView)view.findViewById(R.id.imghouseKeepingStaffLandSave);
        imghouseKeepingStaffLandRemarks= (ImageView)view.findViewById(R.id.imghouseKeepingStaffLandRemarks);
        imgPlacementcellLandEdit= (ImageView)view.findViewById(R.id.imgPlacementcellLandEdit);
        imgPlacementcellLandSave= (ImageView)view.findViewById(R.id.imgPlacementcellLandSave);
        imgPlacementcellLandRemarks= (ImageView)view.findViewById(R.id.imgPlacementcellLandRemarks);
        imgLibraryLandEdit= (ImageView)view.findViewById(R.id.imgLibraryLandEdit);
        imgLibraryLandSave= (ImageView)view.findViewById(R.id.imgLibraryLandSave);
        imgLibraryLandRemarks= (ImageView)view.findViewById(R.id.imgLibraryLandRemarks);
        imgStaffRoomLandEdit= (ImageView)view.findViewById(R.id.imgStaffRoomLandEdit);
        imgStaffRoomLandSave= (ImageView)view.findViewById(R.id.imgStaffRoomLandSave);
        imgStaffRoomLandRemarks= (ImageView)view.findViewById(R.id.imgStaffRoomLandRemarks);
        imgFireLandEdit= (ImageView)view.findViewById(R.id.imgFireLandEdit);
        imgFireLandSave= (ImageView)view.findViewById(R.id.imgFireLandSave);
        imgFireLandRemarks= (ImageView)view.findViewById(R.id.imgFireLandRemarks);
        imgLandSitmapEdit= (ImageView)view.findViewById(R.id.imgLandSitmapEdit);
        imgLandSitmapSave= (ImageView)view.findViewById(R.id.imgLandSitmapSave);
        imgLandSitmapRemarks= (ImageView)view.findViewById(R.id.imgLandSitmapRemarks);
        imgLandDimensionEdit= (ImageView)view.findViewById(R.id.imgLandDimensionEdit);
        imgLandDimensionSave= (ImageView)view.findViewById(R.id.imgLandDimensionSave);
        imgLandDimensionRemarks= (ImageView)view.findViewById(R.id.imgLandDimensionRemarks);
        imgLandFloorRemarks= (ImageView)view.findViewById(R.id.imgLandFloorRemarks);
        imgLandMapEdit= (ImageView)view.findViewById(R.id.imgLandMapEdit);
        imgLandMapSave= (ImageView)view.findViewById(R.id.imgLandMapSave);
        imgLandMapRemarks= (ImageView)view.findViewById(R.id.imgLandMapRemarks);
        imgCompetentAuthorityEdit= (ImageView)view.findViewById(R.id.imgCompetentAuthorityEdit);
        imgCompetentAuthoritySave= (ImageView)view.findViewById(R.id.imgCompetentAuthoritySave);
        imgCompetentAuthorityRemarks= (ImageView)view.findViewById(R.id.imgCompetentAuthorityRemarks);
        imgApproachRoadEdit= (ImageView)view.findViewById(R.id.imgApproachRoadEdit);
        imgApproachRoadSave= (ImageView)view.findViewById(R.id.imgApproachRoadSave);
        imgApproachRoadRemarks= (ImageView)view.findViewById(R.id.imgApproachRoadRemarks);
        imgEntranceRoadEdit= (ImageView)view.findViewById(R.id.imgEntranceRoadEdit);
        imgEntranceRoadSave= (ImageView)view.findViewById(R.id.imgEntranceRoadSave);
        imgEntranceRoadRemarks= (ImageView)view.findViewById(R.id.imgEntranceRoadRemarks);
        imgLandSwitchBoardEdit = (ImageView)view.findViewById(R.id.imgLandSwitchBoardEdit);
        imgLandSwitchBoardSave = (ImageView)view.findViewById(R.id.imgLandSwitchBoardSave);
        imgLandSwitchBoardRemarks = (ImageView)view.findViewById(R.id.imgLandSwitchBoardRemarks);
        imgLandVentilationEdit = (ImageView)view.findViewById(R.id.imgLandVentilationEdit);
        imgLandVentilationSave = (ImageView)view.findViewById(R.id.imgLandVentilationSave);
        imgLandVentilationRemarks = (ImageView)view.findViewById(R.id.imgLandVentilationRemarks);
        imgIsMasterPlanEdit = (ImageView)view.findViewById(R.id.imgIsMasterPlanEdit);
        imgIsPremisesSharedEdit = (ImageView)view.findViewById(R.id.imgIsPremisesSharedEdit);
        imgSeperateEntranceEdit = (ImageView)view.findViewById(R.id.imgSeperateEntranceEdit);
        imgSoundProofPartitionEdit = (ImageView)view.findViewById(R.id.imgSoundProofPartitionEdit);
        imgStaircaseEdit = (ImageView)view.findViewById(R.id.imgStaircaseEdit);
        imgIsMasterPlanSave = (ImageView)view.findViewById(R.id.imgIsMasterPlanSave);
        imgIsPremisesSharedSave = (ImageView)view.findViewById(R.id.imgIsPremisesSharedSave);
        imgSeperateEntranceSave = (ImageView)view.findViewById(R.id.imgSeperateEntranceSave);
        imgSoundProofPartitionSave = (ImageView)view.findViewById(R.id.imgSoundProofPartitionSave);
        imgStaircaseSave = (ImageView)view.findViewById(R.id.imgStaircaseSave);
        imgIsMasterPlanRemarks = (ImageView)view.findViewById(R.id.imgIsMasterPlanRemarks);
        imgIsPremisesSharedRemarks = (ImageView)view.findViewById(R.id.imgIsPremisesSharedRemarks);
        imgSeperateEntranceRemarks = (ImageView)view.findViewById(R.id.imgSeperateEntranceRemarks);
        imgSoundProofPartitionRemarks = (ImageView)view.findViewById(R.id.imgSoundProofPartitionRemarks);
        imgStaircaseRemarks = (ImageView)view.findViewById(R.id.imgStaircaseRemarks);

        btn_submit = (Button) view.findViewById(R.id.btnSubmit);

        imgLandBuildingTypeNC = (ToggleButton)view.findViewById(R.id.imgLandBuildingTypeNC);
        imgLandDateNC = (ToggleButton)view.findViewById(R.id.imgLandDateNC);
        imgLandAgreementNC = (ToggleButton)view.findViewById(R.id.imgLandAgreementNC);
        imgLandDurationLeaseNC = (ToggleButton)view.findViewById(R.id.imgLandDurationLeaseNC);
        imgLandFloorNC = (ToggleButton)view.findViewById(R.id.imgLandFloorNC);
        imgLandSafetyLiftNC = (ToggleButton)view.findViewById(R.id.imgLandSafetyLiftNC);
        imgLandWallsITINC = (ToggleButton)view.findViewById(R.id.imgLandWallsITINC);
        imgLandFloorCementedNC = (ToggleButton)view.findViewById(R.id.imgLandFloorCementedNC);
        imgLandITISituatedNC = (ToggleButton)view.findViewById(R.id.imgLandITISituatedNC);
        imgAvailblityofWashroomLandNC = (ToggleButton)view.findViewById(R.id.imgAvailblityofWashroomLandNC);
        imgWashroomFunctionalLandNC = (ToggleButton)view.findViewById(R.id.imgWashroomFunctionalLandNC);
        imgSafeWaterLandNC = (ToggleButton)view.findViewById(R.id.imgSafeWaterLandNC);
        imgLandSwitchBoardNC = (ToggleButton)view.findViewById(R.id.imgLandSwitchBoardNC);
        imgLandVentilationNC = (ToggleButton)view.findViewById(R.id.imgLandVentilationNC);
        imgLandSitmapNC = (ToggleButton)view.findViewById(R.id.imgLandSitmapNC);
        imgLandDimensionNC = (ToggleButton)view.findViewById(R.id.imgLandDimensionNC);
        imgLandMapNC = (ToggleButton)view.findViewById(R.id.imgLandMapNC);
        imgLandFireNC = (ToggleButton)view.findViewById(R.id.imgLandFireNC);
        imgIsPremisesSharedNC = (ToggleButton)view.findViewById(R.id.imgIsPremisesSharedNC);
        imgSeperateEntranceNC = (ToggleButton)view.findViewById(R.id.imgSeperateEntranceNC);
        imgSoundProofPartitionNC = (ToggleButton)view.findViewById(R.id.imgSoundProofPartitionNC);
        imgStaircaseNC = (ToggleButton)view.findViewById(R.id.imgStaircaseNC);
    }


    private void initListeners() {

        if(landandBuildingInfo.getAvaibilityhousekeepingstaffNc() == 0){
            imghouseKeepingStaffLandNC.setChecked(false);
        }else {
            imghouseKeepingStaffLandNC.setChecked(true);
        }

        imghouseKeepingStaffLandNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imghouseKeepingStaffLandNC.isChecked()){
                    landandBuildingInfo.setAvaibilityhousekeepingstaffNc(1);
                }else {
                    landandBuildingInfo.setAvaibilityhousekeepingstaffNc(0);
                }
            }
        });


        if(landandBuildingInfo.getIsPremisesSharedNC() == 0){
            imgIsPremisesSharedNC.setChecked(false);
        }else {
            imgIsPremisesSharedNC.setChecked(true);
        }

        imgIsPremisesSharedNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgIsPremisesSharedNC.isChecked()){
                    landandBuildingInfo.setIsPremisesSharedNC(1);
                }else {
                    landandBuildingInfo.setIsPremisesSharedNC(0);
                }
            }
        });

        if(landandBuildingInfo.getSeperateEntranceNC() == 0){
            imgSeperateEntranceNC.setChecked(false);
        }else {
            imgSeperateEntranceNC.setChecked(true);
        }



        imgSeperateEntranceNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgSeperateEntranceNC.isChecked()){
                    landandBuildingInfo.setSeperateEntranceNC(1);
                }else {
                    landandBuildingInfo.setSeperateEntranceNC(0);
                }

            }
        });

        if(landandBuildingInfo.getISoundProofPartitionNC() == 0){
            imgSoundProofPartitionNC.setChecked(false);
        }else {
            imgSoundProofPartitionNC.setChecked(true);
        }


        imgSoundProofPartitionNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgSoundProofPartitionNC.isChecked()){
                    landandBuildingInfo.setISoundProofPartitionNC(1);
                }else {
                    landandBuildingInfo.setISoundProofPartitionNC(0);
                }
            }
        });

        if(landandBuildingInfo.getStaircaseNC() == 0){
            imgStaircaseNC.setChecked(false);
        }else {
            imgStaircaseNC.setChecked(true);
        }




        imgStaircaseNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgStaircaseNC.isChecked()){
                    landandBuildingInfo.setStaircaseNC(1);
                }else {
                    landandBuildingInfo.setStaircaseNC(0);
                }
            }
        });

        if(landandBuildingInfo.getSitmapNc() == 0){
            imgLandSitmapNC.setChecked(false);
        }else {
            imgLandSitmapNC.setChecked(true);
        }



        imgLandSitmapNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgLandSitmapNC.isChecked()){
                    landandBuildingInfo.setSitmapNc(1);
                }else {
                    landandBuildingInfo.setSitmapNc(0);
                }
            }
        });

        if(landandBuildingInfo.getDimensionNC() == 0){
            imgLandDimensionNC.setChecked(false);
        }else {
            imgLandDimensionNC.setChecked(true);
        }


        imgLandDimensionNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgLandDimensionNC.isChecked()){
                    landandBuildingInfo.setDimensionNC(1);
                }else {
                    landandBuildingInfo.setDimensionNC(0);
                }
            }
        });

        if(landandBuildingInfo.getMapNC() == 0){
            imgLandMapNC.setChecked(false);
        }else {
            imgLandMapNC.setChecked(true);
        }


        imgLandMapNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgLandMapNC.isChecked()){
                    landandBuildingInfo.setMapNC(1);
                }else {
                    landandBuildingInfo.setMapNC(0);
                }
            }
        });

        if(landandBuildingInfo.getAvaibiltyofFireextinguisherNc() == 0){
            imgLandFireNC.setChecked(false);
        }else {
            imgLandFireNC.setChecked(true);
        }


        imgLandFireNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgLandFireNC.isChecked()){
                    landandBuildingInfo.setAvaibiltyofFireextinguisherNc(1);
                }else {
                    landandBuildingInfo.setAvaibiltyofFireextinguisherNc(0);
                }
            }
        });

        if(landandBuildingInfo.getVentilationNC() == 0){
            imgLandVentilationNC.setChecked(false);
        }else {
            imgLandVentilationNC.setChecked(true);
        }


        imgLandVentilationNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgLandVentilationNC.isChecked()){
                    landandBuildingInfo.setVentilationNC(1);
                }else {
                    landandBuildingInfo.setVentilationNC(0);
                }
            }
        });

        if(landandBuildingInfo.getSwitchBoardNC() == 0){
            imgLandSwitchBoardNC.setChecked(false);
        }else {
            imgLandSwitchBoardNC.setChecked(true);
        }


        imgLandSwitchBoardNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(imgLandSwitchBoardNC.isChecked()){
                    landandBuildingInfo.setSwitchBoardNC(1);
                }else {
                    landandBuildingInfo.setSwitchBoardNC(0);
                }
            }
        });

        if(landandBuildingInfo.getFloorisCementedNc() == 0){
            imgLandFloorCementedNC.setChecked(false);
        }else {
            imgLandFloorCementedNC.setChecked(true);
        }



        imgLandFloorCementedNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgLandFloorCementedNC.isChecked()){
                    landandBuildingInfo.setFloorisCementedNc(1);
                }else {
                    landandBuildingInfo.setFloorisCementedNc(0);
                }
            }
        });

        if(landandBuildingInfo.getDateofOccupancyNc() == 0){
            imgLandDateNC.setChecked(false);
        }else {
            imgLandDateNC.setChecked(true);
        }


        imgLandDateNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgLandDateNC.isChecked()){
                    landandBuildingInfo.setDateofOccupancyNc(1);
                }else {
                    landandBuildingInfo.setDateofOccupancyNc(0);
                }
            }
        });

        if(landandBuildingInfo.getLiftinInstituteNc() == 0){
            imgLandFloorNC.setChecked(false);
        }else {
            imgLandFloorNC.setChecked(true);
        }


        imgLandFloorNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgLandFloorNC.isChecked()){
                    landandBuildingInfo.setLiftinInstituteNc(1);
                }else {
                    landandBuildingInfo.setLiftinInstituteNc(0);
                }
            }
        });

        if(landandBuildingInfo.getSafetyCertificateofLiftNc() == 0){
            imgLandSafetyLiftNC.setChecked(false);
        }else {
            imgLandSafetyLiftNC.setChecked(true);
        }


        imgLandSafetyLiftNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgLandSafetyLiftNC.isChecked()){
                    landandBuildingInfo.setSafetyCertificateofLiftNc(1);
                }else {
                    landandBuildingInfo.setSafetyCertificateofLiftNc(0);
                }
            }
        });

        if(landandBuildingInfo.getItiSituatedintheSameCampusNc() == 0){
            imgLandITISituatedNC.setChecked(false);
        }else {
            imgLandITISituatedNC.setChecked(true);
        }


        imgLandITISituatedNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgLandITISituatedNC.isChecked()){
                    landandBuildingInfo.setItiSituatedintheSameCampusNc(1);
                }else {
                    landandBuildingInfo.setItiSituatedintheSameCampusNc(0);
                }
            }
        });

        if(landandBuildingInfo.getAvaibilityofSeparateMaleFemaleWashroomNc() == 0){
            imgAvailblityofWashroomLandNC.setChecked(false);
        }else {
            imgAvailblityofWashroomLandNC.setChecked(true);
        }


        imgAvailblityofWashroomLandNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgAvailblityofWashroomLandNC.isChecked()){
                    landandBuildingInfo.setAvaibilityofSeparateMaleFemaleWashroomNc(1);
                }else {
                    landandBuildingInfo.setAvaibilityofSeparateMaleFemaleWashroomNc(0);
                }
            }
        });

        if(landandBuildingInfo.getAvaibilityofsafedrinkingwaterNc() == 0){
            imgSafeWaterLandNC.setChecked(false);
        }else {
            imgSafeWaterLandNC.setChecked(true);
        }



        imgSafeWaterLandNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgSafeWaterLandNC.isChecked()){
                    landandBuildingInfo.setAvaibilityofsafedrinkingwaterNc(1);
                }else {
                    landandBuildingInfo.setAvaibilityofsafedrinkingwaterNc(0);
                }
            }
        });

        if(landandBuildingInfo.getDurationleaseNc() == 0){
            imgLandDurationLeaseNC.setChecked(false);
        }else {
            imgLandDurationLeaseNC.setChecked(true);
        }



        imgLandDurationLeaseNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgLandDurationLeaseNC.isChecked()){
                    landandBuildingInfo.setDurationleaseNc(1);
                }else {
                    landandBuildingInfo.setDurationleaseNc(0);
                }
            }
        });

        if(landandBuildingInfo.getExpiryofagreementNC() == 0){
            imgLandAgreementNC.setChecked(false);
        }else {
            imgLandAgreementNC.setChecked(true);
        }

        imgLandAgreementNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgLandAgreementNC.isChecked()){
                    landandBuildingInfo.setExpiryofagreementNC(1);
                }else {
                    landandBuildingInfo.setExpiryofagreementNC(0);
                }
            }
        });

        if(landandBuildingInfo.getAretheWashroomFunctionalNc() == 0){
            imgWashroomFunctionalLandNC.setChecked(false);
        }else {
            imgWashroomFunctionalLandNC.setChecked(true);
        }


        imgWashroomFunctionalLandNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgWashroomFunctionalLandNC.isChecked()){
                    landandBuildingInfo.setAretheWashroomFunctionalNc(1);
                }else {
                    landandBuildingInfo.setAretheWashroomFunctionalNc(0);
                }
            }
        });

        if(landandBuildingInfo.getEntranceNC() == 0){
            imgEntranceRoadNC.setChecked(false);
        }else {
            imgEntranceRoadNC.setChecked(true);
        }




        imgEntranceRoadNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgEntranceRoadNC.isChecked()){
                    landandBuildingInfo.setEntranceNC(1);

                }else {
                    landandBuildingInfo.setEntranceNC(0);
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

//                                    generalInfoModel.setAddressNc(0);

                                    if (utility.getConnectivityStatus(context)) {
                                        landandBuildingInfo.setTotalOpenAreaNc(0);
                                        landandBuildingInfo.setTotalBuildAreaNc(0);
                                        landandBuildingInfo.setTotalLandNc(0);
                                        //landandBuildingInfo.setAvaibiltyofFireextinguisherNc(0);
                                        //landandBuildingInfo.setFloorisCementedNc(0);
                                        landandBuildingInfo.setArchitectNameNc(0);
                                        landandBuildingInfo.setArchitectRegnNoNc(0);
                                        landandBuildingInfo.setBuildingPlanofInstituteNc(0);
                                        landandBuildingInfo.setNoofFloorsNc(0);
                                        landandBuildingInfo.setCapacityofLiftNc(0);
                                        landandBuildingInfo.setWorkshopRoofNc(0);
                                        //landandBuildingInfo.setAvaibilityhousekeepingstaffNc(0);
                                        landandBuildingInfo.setAvaibilityofplacementcellNc(0);
                                        landandBuildingInfo.setAvaibilityoflibrarayNc(0);
                                        landandBuildingInfo.setAvaibilityofstaffroomNc(0);
                                        landandBuildingInfo.setComptentNC(0);
                                        landandBuildingInfo.setApproachNC(0);
                                        //landandBuildingInfo.setEntranceNC(0);
                                        landandBuildingInfo.setOrgInstalledLiftNc(0);
                                        landandBuildingInfo.setBuildingTypeNc(0);
                                        landandBuildingInfo.setWallOfItiNc(0);
                                        landandBuildingInfo.setStaircaseNC(0);
                                        landandBuildingInfo.setIsMasterPlanNC(0);
                                        landandBuildingInfo.setPrescribedNC(0);
                                        landandBuildingInfo.setConstructedNC(0);
                                        JSONObject dataToSyncClass = utility.getLandInfoSyncData(landandBuildingInfo);
                                        Log.e("general data ", dataToSyncClass.toString());
                                        new ExecuteSyncOperation().execute(new String[]{AppConstants.URL_LAND_AND_BUILDING_SET,
                                                dataToSyncClass.toString().replace("&", "and")});

                                    } else {
                                        ITIDBController controller = new ITIDBController(context);
                                        boolean updateStatus = controller.saveLandInfo(landandBuildingInfo, "draft");
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

        imgLandConstructedEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgLandConstructedSave, spinLandConstructed, view);
                ans36edit = 1;
            }
        });

        imgLandConstructedSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                landandBuildingInfo.setConstructed(spinLandConstructed.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgLandConstructedSave, spinLandConstructed, view, ans36edit);
                ans36edit = 2;
            }
        });

        imgLandConstructedRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getConstructedRemarks());
                textRemarkType.setText(AppConstants.KEY_CONSTRUCTED_REMARKS);
            }
        });

        imgLandPrescribedEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgLandPrescribedSave, spinLandPrescribed, view);
                ans37edit = 1;
            }
        });

        imgLandPrescribedSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                landandBuildingInfo.setPrescribed(spinLandPrescribed.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgLandPrescribedSave, spinLandPrescribed, view, ans37edit);
                ans37edit = 2;
            }
        });

        imgLandPrescribedRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getPrescribedRemarks());
                textRemarkType.setText(AppConstants.KEY_PRESCRIBED_REMARKS);
            }
        });

        imgLandOpenAreaEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgLandOpenAreaSave, editLandOpenArea, view);
                ans1edit = 1;
            }
        });

        imgLandOpenAreaSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                landandBuildingInfo.setTotalOpenArea(editLandOpenArea.getText().toString());
                ControlsUtility.okImageViewAction(imgLandOpenAreaSave, editLandOpenArea, view, ans1edit);
                ans1edit = 2;
            }
        });

        imgLandOpenAreaRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getTotalOpenAreaRemarks());
                textRemarkType.setText(AppConstants.KEY_TOTAL_OPEN_AREA_REMARKS);
            }
        });

        imgLandBuildUpAreaEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgLandBuildUpAreaSave, editLandBuildUpArea, view);
                ans2edit = 1;
            }
        });

        imgLandBuildUpAreaSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int total = Integer.parseInt(editLandTotalLand.getText().toString()) - Integer.parseInt(editLandBuildUpArea.getText().toString());
                    editLandOpenArea.setText(total + "");
                }catch (Exception e){
                    e.printStackTrace();
                }
               toHideKeyboard();
                landandBuildingInfo.setTotalBuildArea(editLandBuildUpArea.getText().toString());
                ControlsUtility.okImageViewAction(imgLandBuildUpAreaSave, editLandBuildUpArea, view, ans2edit);
                ans2edit = 2;
            }
        });

        imgLandBuildUpAreaRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getTotalBuildAreaRemarks());
                textRemarkType.setText(AppConstants.KEY_TOTAL_BUILD_AREA_REMARKS);
            }
        });
        imgLandTotalLandEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgLandTotalLandSave, editLandTotalLand, view);
                ans3edit = 1;
            }
        });

        imgLandTotalLandSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                landandBuildingInfo.setTotalLand(editLandTotalLand.getText().toString());
                ControlsUtility.okImageViewAction(imgLandTotalLandSave, editLandTotalLand, view, ans3edit);
                ans3edit = 2;
            }
        });

        imgLandTotalLandRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getTotalLandRemarks());
                textRemarkType.setText(AppConstants.KEY_TOTAL_LAND_REMARKS);
            }
        });

        imgLandBuildingTypeEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgLandBuildingTypeSave, spinLandBuildingType, view);
                ans4edit = 1;
            }
        });

        imgLandBuildingTypeSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(spinLandBuildingType.getSelectedItem().toString().equalsIgnoreCase("Own Building")){
                    duration.setVisibility(View.GONE);
                }else {
                    duration.setVisibility(View.VISIBLE);
                }
                toHideKeyboard();
                landandBuildingInfo.setBuildingType(spinLandBuildingType.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgLandBuildingTypeSave, spinLandBuildingType, view, ans4edit);
                ans4edit = 2;
            }
        });

        imgLandBuildingTypeRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getBuildingTypeRemarks());
                textRemarkType.setText(AppConstants.KEY_BUILDING_TYPE_REMARKS);
            }
        });

        imgLandDateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgLandDateSave, editLandDate, view);
                ans5edit = 1;
            }
        });

        imgLandDateSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHideKeyboard();
                landandBuildingInfo.setDateofOccupancy(editLandDate.getText().toString());
                ControlsUtility.okImageViewAction(imgLandDateSave, editLandDate, view, ans5edit);
                ans5edit = 2;

            }
        });
        imgLandDateRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getDateofOccupancyRemarks());
                textRemarkType.setText(AppConstants.KEY_DATE_REMARKS);
            }
        });

        imgLandNoofFloorEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlsUtility.editImageViewAction(imgLandNoofFloorSave,editLandNoofFloor,view);
                ans35edit = 1;

            }
        });

        imgLandNoofFloorSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int no_of_floor = Integer.parseInt(editLandNoofFloor.getText().toString());
                    if(no_of_floor < 3){
                        ll_noof_floor.setVisibility(View.GONE);
                        organistaion.setVisibility(View.GONE);
                        safety.setVisibility(View.GONE);

                    }else {
                        ll_noof_floor.setVisibility(View.VISIBLE);
                        organistaion.setVisibility(View.VISIBLE);
                        safety.setVisibility(View.VISIBLE);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }


                toHideKeyboard();
                landandBuildingInfo.setNoofFloors(editLandNoofFloor.getText().toString());
                ControlsUtility.okImageViewAction(imgLandNoofFloorSave,editLandNoofFloor,view,ans35edit);
                ans35edit = 2;
            }
        });
        imgLandNoofFloorRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getNoofFloorsRemarks());
                textRemarkType.setText(AppConstants.KEY_NO_FlOOR_REMARK);
            }
        });

        imgLandOrgNameEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgLandOrgNameSave,editLandOrgName,view);
                ans6edit = 1;
            }
        });
        imgLandOrgNameSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setOrgInstalledLift(editLandOrgName.getText().toString());
                ControlsUtility.okImageViewAction(imgLandOrgNameSave,editLandOrgName,view,ans6edit);
                ans6edit = 2;
            }
        });



        imgLandOrgNameRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getOrgInstalledLiftRemarks());
                textRemarkType.setText(AppConstants.KEY_ORG_NAME_REMARK);
            }
        });

        imgLandCapacityLiftRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getCapacityofLiftRemarks());
                textRemarkType.setText(AppConstants.KEY_CAPACITY_REMARK);
            }
        });
        imgLandSafetyLiftEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgLandSafetyLiftSave,editLandSafetyLift,view);
                ans7edit = 1;
            }
        });

        imgLandSafetyLiftSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setSafetyCertificateofLift(editLandSafetyLift.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgLandSafetyLiftSave,editLandSafetyLift,view,ans7edit);
                ans7edit = 2;
            }
        });

        imgLandSafetyLiftRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getSafetyCertificateofLiftRemarks());
                textRemarkType.setText(AppConstants.KEY_SAFETY_REMARK);
            }
        });

        imgLandWallsITIEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgLandWallsITISave,spinLandWallsITI,view);
                ans8edit = 1;
            }
        });
        imgLandWallsITISave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setWallOfIti(spinLandWallsITI.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgLandWallsITISave,spinLandWallsITI,view,ans8edit);
                ans8edit = 2;
            }
        });

        imgLandWallsITIRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getWallOfItiRemarks());
                textRemarkType.setText(AppConstants.KEY_WALL_REMARK);
            }
        });

        imgLandFloorCementedEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgLandFloorCementedSave,spinLandFloorCemented,view);
                ans9edit = 1;
            }
        });
        imgLandFloorCementedSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setFloorisCemented(spinLandFloorCemented.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgLandFloorCementedSave,spinLandFloorCemented,view,ans9edit);
                ans9edit = 2;
            }
        });

        imgLandFloorCementedRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getFloorisCementedRemarks());
                textRemarkType.setText(AppConstants.KEY_CEMENTED_REMARK);
            }
        });
        imgLandITISituatedEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgLandITISituatedSave,spinLandITISituated,view);
                ans10edit = 1;
            }
        });
        imgLandITISituatedSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setItiSituatedintheSameCampus(spinLandITISituated.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgLandITISituatedSave,spinLandITISituated,view,ans10edit);
                ans10edit = 2;
            }
        });

        imgLandITISituatedRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getItiSituatedintheSameCampusRemarks());
                textRemarkType.setText(AppConstants.KEY_ITI_SITUATED_REMARK);
            }
        });
        imgWashroomFunctionalLandEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgWashroomFunctionalLandSave,spinWashroomFunctionalLand,view);
                ans11edit = 1;
            }
        });
        imgWashroomFunctionalLandSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setAretheWashroomFunctional(spinWashroomFunctionalLand.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgWashroomFunctionalLandSave,spinWashroomFunctionalLand,view,ans11edit);
                ans11edit = 2;
            }
        });

        imgWashroomFunctionalLandRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getAretheWashroomFunctionalRemarks());
                textRemarkType.setText(AppConstants.KEY_FUNCTIONAL_REMARK);
            }
        });

        imgAvailblityofWashroomLandEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgAvailblityofWashroomLandSave,spinAvailblityofWashroomLand,view);
                ans12edit = 1;
            }
        });
        imgAvailblityofWashroomLandSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setAvaibilityofSeparateMaleFemaleWashroom(spinAvailblityofWashroomLand.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgAvailblityofWashroomLandSave,spinAvailblityofWashroomLand,view,ans12edit);
                ans12edit = 2;
            }
        });

        imgAvailblityofWashroomLandRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getAvaibilityofSeparateMaleFemaleWashroomRemarks());
                textRemarkType.setText(AppConstants.KEY_WASHROOM_REMARK);
            }
        });

        imgFireLandEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgFireLandSave,spinFireLand,view);
                ans13edit = 1;
            }
        });
        imgFireLandSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setAvaibiltyofFireextinguisher(spinFireLand.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgFireLandSave,spinFireLand,view,ans13edit);
                ans13edit = 2;
            }
        });

        imgFireLandRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getAvaibiltyofFireextinguisherRemarks());
                textRemarkType.setText(AppConstants.KEY_FIRE_REMARK);
            }
        });

        imgLandDurationLeaseEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgLandDurationLeaseSave,editLandDurationLease,view);
                ans14edit = 1;
            }
        });
        imgLandDurationLeaseSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setDurationlease(editLandDurationLease.getText().toString());
                ControlsUtility.okImageViewAction(imgLandDurationLeaseSave,editLandDurationLease,view,ans14edit);
                ans14edit = 2;
            }
        });

        imgLandDurationLeaseRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getDurationleaseRemarks());
                textRemarkType.setText(AppConstants.KEY_DURATION_LEASE_REMARK);
            }
        });

        imgLandAgreementEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgFireLandSave,editLandAgreement,view);
                ans15edit = 1;
            }
        });
        imgLandAgreementSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setExpiryofAgreement(editLandAgreement.getText().toString());
                ControlsUtility.okImageViewAction(imgLandAgreementSave,editLandAgreement,view,ans15edit);
                ans15edit = 2;
            }
        });

        imgLandAgreementRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getExpiryofagreementRemarks());
                textRemarkType.setText(AppConstants.KEY_AGREEMENT_REMARK);
            }
        });

        imgSafeWaterLandEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgSafeWaterLandSave,spinSafeWaterLand,view);
                ans16edit = 1;
            }
        });
        imgSafeWaterLandSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setAvaibilityofsafeDrinkingwater(spinSafeWaterLand.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgSafeWaterLandSave,spinSafeWaterLand,view,ans16edit);
                ans16edit = 2;
            }
        });

        imgSafeWaterLandRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getAvaibilityofsafedrinkingwaterRemarks());
                textRemarkType.setText(AppConstants.KEY_SAFE_WATER_REMARK);
            }
        });

        imghouseKeepingStaffLandEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imghouseKeepingStaffLandSave,spinhouseKeepingStaffLand,view);
                ans17edit = 1;
            }
        });
        imghouseKeepingStaffLandSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setAvaibilityHousekeepingstaff(spinhouseKeepingStaffLand.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imghouseKeepingStaffLandSave,spinhouseKeepingStaffLand,view,ans17edit);
                ans17edit = 2;
            }
        });

        imghouseKeepingStaffLandRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getAvaibilityhousekeepingstaffRemarks());
                textRemarkType.setText(AppConstants.KEY_HOUSE_REMARK);
            }
        });
        imgPlacementcellLandEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgPlacementcellLandSave,spinPlacementcellLand,view);
                ans18edit = 1;
            }
        });
        imgPlacementcellLandSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setAvaibilityofPlacementcell(spinPlacementcellLand.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgPlacementcellLandSave,spinPlacementcellLand,view,ans18edit);
                ans18edit = 2;
            }
        });

        imgPlacementcellLandRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getAvaibilityofplacementcellRemarks());
                textRemarkType.setText(AppConstants.KEY_PLACEMENT_REMARK);
            }
        });
        imgLibraryLandEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgLibraryLandSave,spinLibraryLand,view);
                ans19edit = 1;
            }
        });
        imgLibraryLandSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setAvaibilityofLibraray(spinLibraryLand.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgLibraryLandSave,spinLibraryLand,view,ans19edit);
                ans19edit = 2;
            }
        });

        imgLibraryLandRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getAvaibilityoflibrarayRemarks());
                textRemarkType.setText(AppConstants.KEY_LIBRARY_REMARK);
            }
        });
        imgStaffRoomLandEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgStaffRoomLandSave,spinStaffRoomLand,view);
                ans20edit = 1;
            }
        });
        imgStaffRoomLandSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setAvaibilityofStaffroom(spinStaffRoomLand.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgStaffRoomLandSave,spinStaffRoomLand,view,ans20edit);
                ans20edit = 2;
            }
        });

        imgStaffRoomLandRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getAvaibilityofstaffroomRemarks());
                textRemarkType.setText(AppConstants.KEY_STAFF_REMARK);
            }
        });

        imgLandSitmapEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgLandSitmapSave,spinLandSitmap,view);
                ans21edit = 1;
            }
        });
        imgLandSitmapSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setSitmap(spinLandSitmap.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgLandSitmapSave,spinLandSitmap,view,ans21edit);
                ans21edit = 2;
            }
        });

        imgLandSitmapRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getSitmapRemarks());
                textRemarkType.setText(AppConstants.KEY_SITMAP_REMARKS);
            }
        });

        imgLandDimensionEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgLandDimensionSave,spinLandDimension,view);
                ans22edit = 1;
            }
        });

        imgLandDimensionSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setDimension(spinLandDimension.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgLandDimensionSave,spinLandDimension,view,ans22edit);
                ans22edit = 2;
            }
        });

        imgLandDimensionRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getDimensionRemarks());
                textRemarkType.setText(AppConstants.KEY_DIMENSION_REMARKS);
            }
        });

        imgLandMapEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgLandMapSave,spinLandMap,view);
                ans23edit = 1;
            }
        });

        imgLandMapSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              toHideKeyboard();
                landandBuildingInfo.setMap(spinLandMap.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgLandMapSave,spinLandMap,view,ans23edit);
                ans23edit = 2;
            }
        });

        imgLandMapRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getMapRemarks());
                textRemarkType.setText(AppConstants.KEY_MAP_REMARKS);
            }
        });


        imgLandFloorEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgLandFloorSave,spinLandFloor,view);
                ans24edit = 1;
            }
        });

        imgLandFloorSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(spinLandFloor.getSelectedItem().toString().equalsIgnoreCase("Yes")){
                    organistaion.setVisibility(View.VISIBLE);
                    safety.setVisibility(View.VISIBLE);
                }else {
                    organistaion.setVisibility(View.GONE);
                    safety.setVisibility(View.GONE);
                }
                toHideKeyboard();
                landandBuildingInfo.setLiftinInstitute(spinLandFloor.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgLandFloorSave,spinLandFloor,view,ans24edit);
                ans24edit = 2;
            }
        });

        imgLandFloorRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getLiftinInstituteRemarks());
                textRemarkType.setText(AppConstants.KEY_FLOOR_REMARK);

            }
        });

        imgCompetentAuthorityEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgCompetentAuthoritySave,spinCompetentAuthority,view);
                ans25edit = 1;
            }
        });

        imgCompetentAuthoritySave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setComptent(spinCompetentAuthority.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgCompetentAuthoritySave,spinCompetentAuthority,view,ans25edit);
                ans25edit = 2;
            }
        });

        imgCompetentAuthorityRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getComptentRemarks());
                textRemarkType.setText(AppConstants.KEY_COMPTENT_REMARKS);

            }
        });

        imgApproachRoadEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgApproachRoadSave,spinApproachRoad,view);
                ans27edit = 1;
            }
        });

        imgApproachRoadSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setApproach(spinApproachRoad.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgApproachRoadSave,spinApproachRoad,view,ans27edit);
                ans27edit = 2;
            }
        });

        imgApproachRoadRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getApproachRemarks());
                textRemarkType.setText(AppConstants.KEY_APPROACH_REMARKS);

            }
        });

        imgEntranceRoadEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgEntranceRoadSave,spinEntranceRoad,view);
                ans26edit = 1;
            }
        });

        imgEntranceRoadSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setEntrance(spinEntranceRoad.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgEntranceRoadSave,spinEntranceRoad,view,ans26edit);
                ans26edit = 2;
            }
        });

        imgEntranceRoadRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getEntranceRemarks());
                textRemarkType.setText(AppConstants.KEY_ENTRANCE_REMARKS);

            }
        });

        imgLandSwitchBoardEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgLandSwitchBoardSave,spinLandSwitchBoard,view);
                ans28edit = 1;
            }
        });

        imgLandSwitchBoardSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setSwitchBoard(spinLandSwitchBoard.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgLandSwitchBoardSave,spinLandSwitchBoard,view,ans28edit);
                ans28edit = 2;
            }
        });

        imgLandSwitchBoardRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getSwitchBoardRemarks());
                textRemarkType.setText(AppConstants.KEY_SWITCH_REMARKS);

            }
        });

        imgLandVentilationEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgLandVentilationSave,spinLandVentilation,view);
                ans29edit = 1;
            }
        });

        imgLandVentilationSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setVentilation(spinLandVentilation.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgLandVentilationSave,spinLandVentilation,view,ans29edit);
                ans29edit = 2;
            }
        });

        imgLandVentilationRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getVentilationRemarks());
                textRemarkType.setText(AppConstants.KEY_VENTILATION_REMARKS);

            }
        });

        imgIsMasterPlanEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgIsMasterPlanSave,spinIsMasterPlan,view);
                ans30edit = 1;
            }
        });

        imgIsMasterPlanSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setIsMasterPlan(spinIsMasterPlan.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgIsMasterPlanSave,spinIsMasterPlan,view,ans30edit);
                ans30edit = 2;
            }
        });

        imgIsMasterPlanRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getIsMasterPlanRemarks());
                textRemarkType.setText(AppConstants.KEY_MASTER_REMARKS);

            }
        });

        imgIsPremisesSharedEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgIsPremisesSharedSave,spinIsPremisesShared,view);
                ans31edit = 1;
            }
        });

        imgIsPremisesSharedSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setIsPremisesShared(spinIsPremisesShared.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgIsPremisesSharedSave,spinIsPremisesShared,view,ans31edit);
                ans31edit = 2;
            }
        });

        imgIsPremisesSharedRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getIsPremisesSharedRemarks());
                textRemarkType.setText(AppConstants.KEY_PREMISES_REMARKS);

            }
        });


        imgSeperateEntranceEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgSeperateEntranceSave,spinSeperateEntrance,view);
                ans32edit = 1;
            }
        });

        imgSeperateEntranceSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setSeperateEntrance(spinSeperateEntrance.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgSeperateEntranceSave,spinSeperateEntrance,view,ans32edit);
                ans32edit = 2;
            }
        });

        imgSeperateEntranceRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getSeperateEntranceRemarks());
                textRemarkType.setText(AppConstants.KEY_SEPERATE_REMARKS);

            }
        });

        imgSoundProofPartitionEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgSoundProofPartitionSave,spinSoundProofPartition,view);
                ans33edit = 1;
            }
        });

        imgSoundProofPartitionSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setSoundProofPartition(spinSoundProofPartition.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgSoundProofPartitionSave,spinSoundProofPartition,view,ans33edit);
                ans33edit = 2;
            }
        });

        imgSoundProofPartitionRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getSoundProofPartitionRemarks());
                textRemarkType.setText(AppConstants.KEY_SOUND_PROOF_REMARKS);

            }
        });


        imgStaircaseEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imgStaircaseSave,spinStaircase,view);
                ans34edit = 1;
            }
        });

        imgStaircaseSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHideKeyboard();
                landandBuildingInfo.setStaircase(spinStaircase.getSelectedItem().toString());
                ControlsUtility.okImageViewAction(imgStaircaseSave,spinStaircase,view,ans34edit);
                ans34edit = 2;
            }
        });

        imgStaircaseRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingDialog(false,false,customDialogView,220,300,false);
                editRemark.setText(landandBuildingInfo.getStaircaseRemarks());
                textRemarkType.setText(AppConstants.KEY_STAIRCASE_REMARKS);

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
        if(ans1edit == 2 && ans2edit == 2 && ans3edit == 2 && ans4edit == 2 && ans5edit == 2  && ans8edit == 2 && ans9edit == 2 && ans10edit == 2  && ans12edit == 2 &&
                ans13edit == 2   && ans16edit == 2 && ans17edit == 2  && ans21edit == 2
                && ans22edit == 2 && ans23edit == 2  && ans25edit == 2 && ans26edit == 2 && ans27edit == 2 && ans28edit == 2
                && ans29edit == 2 && ans30edit == 2 && ans31edit == 2 && ans32edit == 2 && ans33edit == 2 && ans34edit == 2  && ans35edit == 2){
            return true;
        } else {
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
            case AppConstants.KEY_TOTAL_OPEN_AREA_REMARKS:
                landandBuildingInfo.setTotalOpenAreaRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_TOTAL_BUILD_AREA_REMARKS:
                landandBuildingInfo.setTotalBuildAreaRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_TOTAL_LAND_REMARKS:
                landandBuildingInfo.setTotalLandRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_BUILDING_TYPE_REMARKS:
                landandBuildingInfo.setBuildingTypeRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_DATE_REMARKS:
                landandBuildingInfo.setDateofOccupancyRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_NO_FlOOR_REMARK:
                landandBuildingInfo.setNoofFloorsRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_ORG_NAME_REMARK:
                landandBuildingInfo.setOrgInstalledLiftRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_CAPACITY_REMARK:
                landandBuildingInfo.setCapacityofLiftRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_SAFETY_REMARK:
                landandBuildingInfo.setSafetyCertificateofLiftRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_WALL_REMARK:
                landandBuildingInfo.setWallOfItiRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_CEMENTED_REMARK:
                landandBuildingInfo.setFloorisCementedRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_ITI_SITUATED_REMARK:
                landandBuildingInfo.setItiSituatedintheSameCampusRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_WASHROOM_REMARK:
                landandBuildingInfo.setAvaibilityofSeparateMaleFemaleWashroomRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_FUNCTIONAL_REMARK:
                landandBuildingInfo.setAretheWashroomFunctionalRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_FIRE_REMARK:
                landandBuildingInfo.setAvaibiltyofFireextinguisherRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_DURATION_LEASE_REMARK:
                landandBuildingInfo.setDurationleaseRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_AGREEMENT_REMARK:
                landandBuildingInfo.setExpiryofagreementRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_SAFE_WATER_REMARK:
                landandBuildingInfo.setAvaibilityofsafedrinkingwaterRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_HOUSE_REMARK:
                landandBuildingInfo.setAvaibilityhousekeepingstaffRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_PLACEMENT_REMARK:
                landandBuildingInfo.setAvaibilityofplacementcellRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_LIBRARY_REMARK:
                landandBuildingInfo.setAvaibilityoflibrarayRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_STAFF_REMARK:
                landandBuildingInfo.setAvaibilityofstaffroomRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_SITMAP_REMARKS:
                landandBuildingInfo.setSitmapRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_DIMENSION_REMARKS:
                landandBuildingInfo.setDimensionRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_MAP_REMARKS:
                landandBuildingInfo.setMapRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_FLOOR_REMARK:
                landandBuildingInfo.setLiftinInstituteRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_COMPTENT_REMARKS:
                landandBuildingInfo.setComptentRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_APPROACH_REMARKS:
                landandBuildingInfo.setApproachRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_ENTRANCE_REMARKS:
                landandBuildingInfo.setEntranceRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_SWITCH_REMARKS:
                landandBuildingInfo.setSwitchBoardRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_VENTILATION_REMARKS:
                landandBuildingInfo.setVentilationRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;

            case AppConstants.KEY_MASTER_REMARKS:
                landandBuildingInfo.setIsMasterPlanRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_PREMISES_REMARKS:
                landandBuildingInfo.setIsPremisesSharedRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_SEPERATE_REMARKS:
                landandBuildingInfo.setSeperateEntranceRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_SOUND_PROOF_REMARKS:
                landandBuildingInfo.setSoundProofPartitionRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_STAIRCASE_REMARKS:
                landandBuildingInfo.setStaircaseRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_CONSTRUCTED_REMARKS:
                landandBuildingInfo.setConstructedRemarks(editRemark.getText().toString());
                cancelCustomDialog();
                break;
            case AppConstants.KEY_PRESCRIBED_REMARKS:
                landandBuildingInfo.setPrescribedRemarks(editRemark.getText().toString());
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
                    boolean updation_status = controller.saveLandInfo(landandBuildingInfo, "complete");
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
        intent.putExtra("YearWiseCollegeId", landandBuildingInfo.getYearwisecollegeid());
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
