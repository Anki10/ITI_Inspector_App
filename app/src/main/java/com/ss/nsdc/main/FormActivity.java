package com.ss.nsdc.main;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ss.nsdc.R;
import com.ss.nsdc.dao.ITIDBController;
import com.ss.nsdc.entity.Classroom;
import com.ss.nsdc.entity.GenralInfo;
import com.ss.nsdc.entity.InstructorInfo;
import com.ss.nsdc.entity.LandandBuilding;
import com.ss.nsdc.entity.MaterialInfo;
import com.ss.nsdc.entity.PowerSupplyITIModel;
import com.ss.nsdc.entity.Staffing;
import com.ss.nsdc.entity.TechincalInfo;
import com.ss.nsdc.entity.Accommodation;
import com.ss.nsdc.entity.EquipmentInfo;
import com.ss.nsdc.entity.WorkshopAreaModel;
import com.ss.nsdc.fragment.ClassroomFragment;
import com.ss.nsdc.fragment.InstructorFragment;
import com.ss.nsdc.fragment.LandandBulidingFragment;
import com.ss.nsdc.fragment.OrganisationFragment;
import com.ss.nsdc.fragment.PowerSupplyITIFragment;
import com.ss.nsdc.fragment.StaffingFragment;
import com.ss.nsdc.fragment.TechnicalFragment;
import com.ss.nsdc.fragment.AccommodationFragment;
import com.ss.nsdc.fragment.TrustFragment;
import com.ss.nsdc.fragment.WorkshopAreaFragment;

/**
 * Created by Radhika on 5/12/2017.
 */

public class FormActivity extends AppCompatActivity {

    public static String category, YearWiseCollegeId,refId,tradeId;

    private android.app.FragmentManager fragmentManager = getFragmentManager();
    private FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    Context context = this;
    GenralInfo generalInfoModel;
    EquipmentInfo trustGeneralInfo;
    MaterialInfo materialInfo;
    TechincalInfo techincalInfo;
    InstructorInfo instructorInfo;
    Accommodation accommodationInfo;
    LandandBuilding landandBuildingInfo;
    Staffing staffingInfo;
    Classroom classroomInfo;
    PowerSupplyITIModel powerSupplyITIModel;
    WorkshopAreaModel workshopAreaObj;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        if (getIntent().getExtras() != null) {
            category = getIntent().getExtras().getString("Category");
            YearWiseCollegeId = getIntent().getExtras().getString("YearWiseCollegeId");
            refId = String.valueOf(getIntent().getExtras().getInt("refId"));
            tradeId = String.valueOf(getIntent().getExtras().getInt("tradeId"));
        }

        if (savedInstanceState == null) {
            callFragment();
        }

        setupToolbar();

    }

    private void setupToolbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.form_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(toolbarTitle());
    }

    public static String toolbarTitle() {

        if(category.equalsIgnoreCase("genInfo")){
            return "ITI General Information Details";
        }else if(category.equalsIgnoreCase("trust")){
            return "Trust/Society/Company Details";
        }else if(category.equalsIgnoreCase("organisation")){
            return "Organisation Details";
        }else if(category.equalsIgnoreCase("technical")) {
            return "Technical Staff Details";
        }else if(category.equalsIgnoreCase("instructor")) {
            return "Instructor Staff Details";
        }else if(category.equalsIgnoreCase("trades")) {
            return "Accommodation Details";
        }else if(category.equalsIgnoreCase("land")) {
            return "Land and Building Details";
        }else if(category.equalsIgnoreCase("premises")){
            return "Staffing Shifting";
        }else if(category.equalsIgnoreCase("class")){
            return "Classroom";
        }else if(category.equalsIgnoreCase("powerSupplyITI")){
            return "Power Supply";
        }else if(category.equalsIgnoreCase("workshopArea")){ //Added by PN 28 May '17 - WorkshopArea
            return "Workshop Area";
        }

        return null;
    }

    private void callFragment() {
        ITIDBController controller = new ITIDBController(context);
        if(category.equalsIgnoreCase("genInfo")){
            generalInfoModel = controller.getGeneralInfobyYearwiseCollegeId(YearWiseCollegeId);
           /* GeneralInfoFragment generalInfoFragment = new GeneralInfoFragment(generalInfoModel);
            fragmentTransaction.add(R.id.container,generalInfoFragment);*/
        }else if(category.equalsIgnoreCase("trust")){
            trustGeneralInfo = controller.getTrustInfobyYearwiseCollegeId(YearWiseCollegeId);
            TrustFragment trustInfoFragment = new TrustFragment(trustGeneralInfo);
            fragmentTransaction.add(R.id.container,trustInfoFragment);
        }else if(category.equalsIgnoreCase("organisation")){
            materialInfo = controller.getOrganisationInfobyYearwiseCollegeId(YearWiseCollegeId);
            OrganisationFragment organisationInfoFragment = new OrganisationFragment(materialInfo);
            fragmentTransaction.add(R.id.container,organisationInfoFragment);
        }else if(category.equalsIgnoreCase("technical")) {
            techincalInfo = controller.getTechnicalInfobyYearwiseCollegeId(YearWiseCollegeId,refId);
            TechnicalFragment fragment = new TechnicalFragment(techincalInfo);
            fragmentTransaction.add(R.id.container,fragment);
        }else if(category.equalsIgnoreCase("instructor")) {
            instructorInfo = controller.getInstructorInfobyYearwiseCollegeId(YearWiseCollegeId,refId);
            InstructorFragment fragment = new InstructorFragment(instructorInfo);
            fragmentTransaction.add(R.id.container,fragment);
        }else if(category.equalsIgnoreCase("trades")) {
            accommodationInfo = controller.getTradesInfobyYearwiseCollegeId(YearWiseCollegeId, tradeId);
            AccommodationFragment fragment = new AccommodationFragment(accommodationInfo);
            fragmentTransaction.add(R.id.container,fragment);
        }else if(category.equalsIgnoreCase("land")) {
            landandBuildingInfo = controller.getLandInfobyYearwiseCollegeId(YearWiseCollegeId);
            LandandBulidingFragment fragment = new LandandBulidingFragment(landandBuildingInfo);
            fragmentTransaction.add(R.id.container,fragment);
        }else if(category.equalsIgnoreCase("premises")) {
            staffingInfo = controller.getPremisesInfobyYearwiseCollegeId(YearWiseCollegeId);
            StaffingFragment fragment = new StaffingFragment(staffingInfo);
            fragmentTransaction.add(R.id.container,fragment);
        }else if(category.equalsIgnoreCase("class")) {
            classroomInfo = controller.getClassroomInfobyYearwiseCollegeId(YearWiseCollegeId,refId);
            Log.e("string",classroomInfo.getClassroomName());
            ClassroomFragment fragment = new ClassroomFragment(classroomInfo);
            fragmentTransaction.add(R.id.container,fragment);
        }else if(category.equalsIgnoreCase("powerSupplyITI")){
            powerSupplyITIModel = controller.getPowerSupplyITIbyYearwiseCollegeId(YearWiseCollegeId);
            PowerSupplyITIFragment powerSupplyITIFragment = new PowerSupplyITIFragment(powerSupplyITIModel);
            fragmentTransaction.add(R.id.container,powerSupplyITIFragment);
        }else if(category.equalsIgnoreCase("workshopArea")){
            workshopAreaObj = controller.getWorkshopAreabyId(YearWiseCollegeId,refId);
            WorkshopAreaFragment workshopAreaFragment = new WorkshopAreaFragment(workshopAreaObj);
            fragmentTransaction.add(R.id.container,workshopAreaFragment);
        }



        fragmentTransaction.addToBackStack(null);
        controller.close();
        fragmentTransaction.commit();
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
                new AlertDialog.Builder(context)
                        .setTitle("Confirmation")
                        .setMessage("You will lose all the pending changes")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Intent intent = new Intent(context, CategoryActivity.class);
                                intent.putExtra("YearWiseCollegeId", YearWiseCollegeId);
                                //intent.putExtra("instituteName", context.getExtras().getString("ApplicationId"));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                context.startActivity(intent);
                            }
                        }).setNegativeButton(android.R.string.cancel, null).show();

                return true;

            case R.id.institute_list:
                new AlertDialog.Builder(context)
                        .setTitle("Confirmation")
                        .setMessage("You will lose all the pending changes")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Intent intent1 = new Intent(context, InstituteActivity.class);
                                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent1);
                            }
                        }).setNegativeButton(android.R.string.cancel, null).show();

                return true;

            case R.id.menuExit:
                new AlertDialog.Builder(context)
                        .setTitle("Confirmation")
                        .setMessage("You will lose all the pending changes")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Intent intent2 = new Intent(Intent.ACTION_MAIN);
                                intent2.addCategory(Intent.CATEGORY_HOME);
                                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent2);
                            }
                        }).setNegativeButton(android.R.string.cancel, null).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
