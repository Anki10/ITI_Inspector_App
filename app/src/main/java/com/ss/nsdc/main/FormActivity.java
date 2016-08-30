package com.ss.nsdc.main;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
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
import com.ss.nsdc.entity.SubListOffice;
import com.ss.nsdc.fragment.ClassroomFragment;
import com.ss.nsdc.fragment.JobsFragment;
import com.ss.nsdc.fragment.LabFragment;
import com.ss.nsdc.fragment.OfficeFragment;
import com.ss.nsdc.fragment.ResidentialFacilityFragment;
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

            ResidentialFacilityFragment residentialFacilityFragment = new ResidentialFacilityFragment(getSelectedResFacility);
            fragmentTransaction.add(R.id.container, residentialFacilityFragment);
            resFacDAO.close();
        }

        fragmentTransaction.addToBackStack(null);

        controller.close();
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        /*if (id == R.id.action_settings) {
            return true;
        }*/
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
}
