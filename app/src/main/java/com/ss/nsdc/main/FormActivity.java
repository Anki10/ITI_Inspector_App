package com.ss.nsdc.main;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ss.nsdc.R;
import com.ss.nsdc.constant.AppConstants;
import com.ss.nsdc.dao.JobRolesModel;
import com.ss.nsdc.dao.NSDCDBController;
import com.ss.nsdc.dao.SubCategoryClass;
import com.ss.nsdc.dao.SubCategoryLab;
import com.ss.nsdc.dao.SubCategoryResidentialFacDAO;
import com.ss.nsdc.dao.SubCategorySupportStaffDAO;
import com.ss.nsdc.entity.SubCategoryResidentialFac;
import com.ss.nsdc.entity.SubCategorySupportStaff;
import com.ss.nsdc.entity.SubListEquipment;
import com.ss.nsdc.entity.SubListOffice;
import com.ss.nsdc.fragment.ClassroomFragment;
import com.ss.nsdc.fragment.EquipmentFragment;
import com.ss.nsdc.fragment.JobsFragment;
import com.ss.nsdc.fragment.LabFragment;
import com.ss.nsdc.fragment.OfficeFragment;
//import com.ss.nsdc.fragment.ResidentialFacilityFragment;
import com.ss.nsdc.fragment.SupportStaffFragment;

public class FormActivity extends AppCompatActivity {

    public static String category, classId, applicationNo, YearWiseCollegeId;
    public static String toolbarTitle;
    private ClassroomFragment classroomFragment;
    private FragmentManager fragmentManager = getFragmentManager();
    private FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    Context context = this;
    SubCategoryClass getSelectedClassData;
    SubCategoryLab getSelectedLabData;
    SubListOffice getSelectedOfficeData;
    SubListEquipment getSelectedEquipmentData;
    JobRolesModel jobRolesModel;
    SubCategorySupportStaff getSelectedSupportStaff;
    SubCategoryResidentialFac getSelectedResFacility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        if (getIntent().getExtras() != null) {
            category = getIntent().getExtras().getString("category");
            YearWiseCollegeId = getIntent().getExtras().getString("yearWiseCollageId");
            applicationNo = getIntent().getExtras().getString("ApplicationId");
            classId = getIntent().getExtras().getString("subcategoryId");
        }

        if (savedInstanceState == null) {
            callFragment();
        }
        /*NSDCDBController controller=new NSDCDBController(context);
        if(category.equalsIgnoreCase("class"))
		{
			getSelectedClassData=controller.getClassDataByClassId(YearWiseCollegeId, classId);
		}*/
        // Handle Toolbar
        setupToolbar();

    }

    private void callFragment() {
        NSDCDBController controller = new NSDCDBController(context);
        if (category.equalsIgnoreCase("class")) {
            getSelectedClassData = controller.getClassDataByClassId(YearWiseCollegeId, classId);
            ClassroomFragment classroomFragment = new ClassroomFragment(getSelectedClassData);
            fragmentTransaction.add(R.id.container, classroomFragment);

        } else if (category.equalsIgnoreCase("lab")) {
            getSelectedLabData = controller.getLabDataByLabId(YearWiseCollegeId, classId);
            LabFragment labFragment = new LabFragment(getSelectedLabData);
            fragmentTransaction.add(R.id.container, labFragment);

        } else if (category.equalsIgnoreCase("office")) {
            getSelectedOfficeData = controller.getOfficeDataByOfficeId(YearWiseCollegeId, classId);
            OfficeFragment officeFragment = new OfficeFragment(getSelectedOfficeData);
            fragmentTransaction.add(R.id.container, officeFragment);

        } else if (category.equalsIgnoreCase("jobRoles")) {
            jobRolesModel = controller.getJobDataByJobId(YearWiseCollegeId, classId);
            JobsFragment jobsFragment = new JobsFragment(jobRolesModel);
            fragmentTransaction.add(R.id.container, jobsFragment);

        } else if (category.equalsIgnoreCase(AppConstants.TEXT_SUPPORT_STAFF)) {
            SubCategorySupportStaffDAO staffDAO = new SubCategorySupportStaffDAO(context);
            getSelectedSupportStaff = staffDAO.getStaffDataByStaffId(YearWiseCollegeId, classId);

            SupportStaffFragment supportStaffFragment = new SupportStaffFragment(getSelectedSupportStaff);
            fragmentTransaction.add(R.id.container, supportStaffFragment);
            staffDAO.close();

        } else if (category.equalsIgnoreCase(AppConstants.TEXT_RESIDENTIAL_FACILITY)) {
            SubCategoryResidentialFacDAO resFacDAO = new SubCategoryResidentialFacDAO(context);
            getSelectedResFacility = resFacDAO.getResFacDataByStaffId(YearWiseCollegeId, classId);

           // ResidentialFacilityFragment residentialFacilityFragment = new ResidentialFacilityFragment(getSelectedResFacility);
            //fragmentTransaction.add(R.id.container, residentialFacilityFragment);
            resFacDAO.close();
        }else if (category.equalsIgnoreCase("equipment")) {
            getSelectedEquipmentData = controller.getEquipmentDataByLabId(YearWiseCollegeId, classId);
            EquipmentFragment officeFragment = new EquipmentFragment(getSelectedEquipmentData);
            fragmentTransaction.add(R.id.container, officeFragment);
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
                                intent.putExtra("YearWiseCollegeId",YearWiseCollegeId);
                                intent.putExtra("applicationNo", applicationNo);
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

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.form_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(toolbarTitle());
    }

    public static String toolbarTitle() {

        if (category.equalsIgnoreCase("class")) {
            return toolbarTitle = "Classrom Details";
        } else if (category.equalsIgnoreCase("lab")) {
            return toolbarTitle = "Laboratories Details";
        } else if (category.equalsIgnoreCase("office")) {
            return toolbarTitle = "Office Area Details";
        } else if (category.equalsIgnoreCase("equipment")) {
            return toolbarTitle = "Equipment Details";

        } else if (category.equalsIgnoreCase(AppConstants.TEXT_SUPPORT_STAFF)) {
            return toolbarTitle = AppConstants.TEXT_SUPPORT_SPACE_STAFF;

        } else if (category.equalsIgnoreCase(AppConstants.TEXT_RESIDENTIAL_FACILITY)) {
            return toolbarTitle = AppConstants.TEXT_RESIDENTIAL_SPACE_FACILITY;
        } else {
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(context)
                .setTitle("Confirmation")
                .setMessage("You will lose all the pending changes")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        FormActivity.this.finish();
                    }
                })
                .setNegativeButton(android.R.string.cancel, null).show();
    }
}
