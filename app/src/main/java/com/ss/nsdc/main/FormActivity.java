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
import com.ss.nsdc.dao.NSDCDBController;
import com.ss.nsdc.dao.SubCategoryClass;
import com.ss.nsdc.dao.SubCategoryLab;
import com.ss.nsdc.fragment.ClassroomFragment;
import com.ss.nsdc.fragment.LabFragment;

public class FormActivity extends AppCompatActivity {

	public static String category,classId,applicationNo,YearWiseCollegeId;
	public static String toolbarTitle;
	private ClassroomFragment classroomFragment;
	private FragmentManager fragmentManager = getFragmentManager();
	private FragmentTransaction fragmentTransaction = fragmentManager
			.beginTransaction();
	Context context=this;
	SubCategoryClass getSelectedClassData;
	SubCategoryLab getSelectedLabData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form);

		if (getIntent().getExtras() != null) 
		{
			category = getIntent().getExtras().getString("category");
			YearWiseCollegeId=getIntent().getExtras().getString("yearWiseCollageId");
			applicationNo=getIntent().getExtras().getString("ApplicationId");
			classId= getIntent().getExtras().getString("subcategoryId");
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
		NSDCDBController controller=new NSDCDBController(context);		
		if (category.equalsIgnoreCase("class")) {
			getSelectedClassData=controller.getClassDataByClassId(YearWiseCollegeId, classId);
			ClassroomFragment classroomFragment = new ClassroomFragment(getSelectedClassData);
			fragmentTransaction.add(R.id.container, classroomFragment);
		} else if (category.equalsIgnoreCase("lab")) {
			getSelectedLabData=controller.getLabDataByLabId(YearWiseCollegeId, classId);
			LabFragment labFragment = new LabFragment(getSelectedLabData);
			fragmentTransaction.add(R.id.container, labFragment);
		}
		fragmentTransaction.addToBackStack(null);

		controller.close();
		fragmentTransaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.form, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
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
		} else {
			return null;
		}
	}
}
