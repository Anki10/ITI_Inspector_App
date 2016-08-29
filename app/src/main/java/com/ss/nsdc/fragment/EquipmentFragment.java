/**
 * 
 *//*
package com.ss.nsdc.fragment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.ss.nsdc.R;
import com.ss.nsdc.dao.NSDCDBController;
import com.ss.nsdc.dao.SubCategoryClass;
import com.ss.nsdc.dao.User;
import com.ss.nsdc.entity.SubListEquipment;
import com.ss.nsdc.entity.SubListOffice;
import com.ss.nsdc.utility.ControlsUtility;
import com.ss.nsdc.utility.UtilityService;

*//**
 * @author Prashant
 * 
 *//*
public class EquipmentFragment extends Fragment {

	View view;
	ProgressDialog ringProgressDialog;
	private Spinner ans1;
	private EditText ans2;
	private Spinner ans3;
	private RadioButton ans41;
	private RadioButton ans42;
	private RadioButton ans51;
	private RadioButton ans52;
	private RadioButton ans61;
	private RadioButton ans62;
	private RadioGroup ans4;
	private RadioGroup ans5;
	private RadioGroup ans6;
	private EditText ans7;
	private Button subButton;

	private ImageView imageView21;
	private ImageView imageView22;
	private ImageView imageView31;
	private ImageView imageView32;
	private ImageView imageView41;
	private ImageView imageView42;
	private ImageView imageView51;
	private ImageView imageView52;
	private ImageView imageView61;
	private ImageView imageView62;
	private ImageView imageView71;
	private ImageView imageView72;
	Context context;
	UtilityService utility=UtilityService.getInstance();
	private SubListEquipment getSelectedOfficeData;

	public EquipmentFragment() {
		super();
	}

	public EquipmentFragment(SubListEquipment getSelectedEquipmentData) {
		super();
		this.getSelectedOfficeData = getSelectedEquipmentData;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, final ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_equipment, container, false);
		context=container.getContext();
		initializeControls();

		// Setting Default Values 

		if (getSelectedOfficeData != null) {
			//ANS 1
			ControlsUtility.setSpinnerData(ans1,
					getResources().getStringArray(R.array.officeType),view);

			ControlsUtility.setDefaultSpinnerText(ans1, getSelectedOfficeData.getAreaType(),
					getResources().getStringArray(R.array.officeType));
			
			//ANS 2			
			ControlsUtility.setDefaultEditText(ans2,
							(getSelectedOfficeData.getInsCarpetArea() != null) ? getSelectedOfficeData
									.getInsCarpetArea() : getSelectedOfficeData.getCarpetArea());
			//ANS 3
			ControlsUtility.setSpinnerData(ans3,
					getResources().getStringArray(R.array.internetAvailability),view);

			ControlsUtility.setDefaultSpinnerText(ans3, (getSelectedOfficeData.getInsInternet() != null) 
					? getSelectedOfficeData.getInsInternet() :getSelectedOfficeData.getInternet(),
					getResources().getStringArray(R.array.internetAvailability));
			
			//ANS 4
			ControlsUtility.setDefaultRadioText(
							ans4,ans41,ans42,
							(getSelectedOfficeData.getInsAC() != null ) ? getSelectedOfficeData.getInsAC()
									: getSelectedOfficeData.getAC(),
			
									R.id.off_radioButton41, R.id.off_radioButton42);
			//ANS 5 
			ControlsUtility
					.setDefaultRadioText(
							ans5,
							ans51,
							ans52,
							(getSelectedOfficeData.getInsBackUp() != null) ? getSelectedOfficeData.getInsBackUp()
									: getSelectedOfficeData.getBackUp(),
							R.id.off_radioButton51, R.id.off_radioButton52);
			// ANS 6
			ControlsUtility
					.setDefaultRadioText(
							ans6,
							ans61,
							ans62,
							(getSelectedOfficeData.getInsCCTV() != null) 
							? getSelectedOfficeData.getInsCCTV()
									: getSelectedOfficeData.getCCTV(),
							R.id.off_radioButton61, R.id.off_radioButton62);
			//ANS 7
			ControlsUtility.setDefaultEditText(ans7,
					(getSelectedOfficeData.getInsremarks() != null) ? getSelectedOfficeData
							.getInsremarks() : getSelectedOfficeData.getRemarks());

		}
		
		imageView21.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ControlsUtility.editImageViewAction(imageView22, ans2, view);
			}
		});

		imageView22.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ControlsUtility.okImageViewAction(imageView22, ans2, view);
				getSelectedOfficeData.setInsCarpetArea(ans2.getText().toString());
			}
		});

		imageView31.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ControlsUtility.editImageViewAction(imageView32, ans3,view);
			}
		});

		imageView32.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ControlsUtility.okImageViewAction(imageView32, ans3,view);
				getSelectedOfficeData.setInsInternet(ans3.getSelectedItem().toString());
			}
		});

		imageView41.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ControlsUtility.editImageViewAction(imageView42, ans41, ans42,
						R.id.off_radioButton41, R.id.off_radioButton42, view);
			}
		});

		imageView42.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ControlsUtility.okImageViewAction(imageView42, ans41, ans42,
						R.id.off_radioButton41, R.id.off_radioButton42, view);
				getSelectedOfficeData.setInsAC(ControlsUtility.getSelectedRadioText(ans4));
			}
		});

		imageView51.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ControlsUtility.editImageViewAction(imageView52, ans51, ans52,
						R.id.off_radioButton51, R.id.off_radioButton52, view);
			}
		});

		imageView52.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ControlsUtility.okImageViewAction(imageView52, ans51, ans52,
						R.id.off_radioButton51, R.id.off_radioButton52, view);
				getSelectedOfficeData.setInsBackUp(ControlsUtility.getSelectedRadioText(ans5));
			}
		});
		imageView61.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ControlsUtility.editImageViewAction(imageView62, ans61, ans62,
						R.id.off_radioButton61, R.id.off_radioButton62, view);
			}
		});

		imageView62.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ControlsUtility.okImageViewAction(imageView62, ans61, ans62,
						R.id.off_radioButton61, R.id.off_radioButton62, view);
				getSelectedOfficeData.setInsCCTV(ControlsUtility.getSelectedRadioText(ans6));
			}
		});

		imageView71.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ControlsUtility.editImageViewAction(imageView72, ans7, view);
			}
		});

		imageView72.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ControlsUtility.okImageViewAction(imageView72, ans7, view);
				getSelectedOfficeData.setInsremarks(ans2.getText().toString());
			}
		});

		subButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//JSONObject datatoSycClass= new JSONObject();
				if(utility.getConnectivityStatus(context))
				{
					//List<SubListOffice> offResult=new ArrayList<SubListOffice>();
					//offResult.add(getSelectedOfficeData);
					//datatoSycClass= new JSONObject();
					JSONObject datatoSycClass= utility.getOfficeSycData(getSelectedOfficeData);
					new ExecuteSyncOperation().execute(new String[]{"http://nsdc.qci.org.in/api/CAAF/Offiec_Area.php",datatoSycClass.toString(),"bnNkYzd0ZWNoaWVzYXBp"});
				}			
			}
		});
		return view;
	}

	private void initializeControls() {

		ans1 = (Spinner) view.findViewById(R.id.off_spin1);
		ans2 = (EditText) view.findViewById(R.id.off_edit2);
		ans3 = (Spinner) view.findViewById(R.id.off_spin3);
		ans41 = (RadioButton) view.findViewById(R.id.off_radioButton41);
		ans42 = (RadioButton) view.findViewById(R.id.off_radioButton42);
		ans51 = (RadioButton) view.findViewById(R.id.off_radioButton51);
		ans52 = (RadioButton) view.findViewById(R.id.off_radioButton52);
		ans61 = (RadioButton) view.findViewById(R.id.off_radioButton61);
		ans62 = (RadioButton) view.findViewById(R.id.off_radioButton62);
		ans4 = (RadioGroup) view.findViewById(R.id.off_radioGroup4);
		ans5 = (RadioGroup) view.findViewById(R.id.off_radioGroup5);
		ans6 = (RadioGroup) view.findViewById(R.id.off_radioGroup6);
		ans7 = (EditText) view.findViewById(R.id.off_edit7);
		subButton = (Button) view.findViewById(R.id.off_submit);

		imageView21 = (ImageView) view.findViewById(R.id.off_img21);
		imageView22 = (ImageView) view.findViewById(R.id.off_img22);
		imageView31 = (ImageView) view.findViewById(R.id.off_img31);
		imageView32 = (ImageView) view.findViewById(R.id.off_img32);
		imageView41 = (ImageView) view.findViewById(R.id.off_img41);
		imageView42 = (ImageView) view.findViewById(R.id.off_img42);
		imageView51 = (ImageView) view.findViewById(R.id.off_img51);
		imageView52 = (ImageView) view.findViewById(R.id.off_img52);
		imageView61 = (ImageView) view.findViewById(R.id.off_img61);
		imageView62 = (ImageView) view.findViewById(R.id.off_img62);
		imageView71 = (ImageView) view.findViewById(R.id.off_img71);
		imageView72 = (ImageView) view.findViewById(R.id.off_img72);

	}
	class ExecuteSyncOperation extends AsyncTask<String, Integer, JSONObject>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(context, android.R.style.Theme_Holo), null," Data Synchronizing ...", true);
			ringProgressDialog.setCancelable(false);
			ringProgressDialog.setCanceledOnTouchOutside(false);
		}

		@Override
		protected JSONObject doInBackground(String ...data) {
			String response="";
			JSONObject response_json=null;
			try{
				URL url = new URL(data[0]);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setReadTimeout(10000);
				conn.setConnectTimeout(15000);
				conn.setRequestMethod("POST");
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				String str="result="+data[1]+"&Token="+data[2];
				byte[] outputInBytes = str.getBytes("UTF-8");
				conn.getOutputStream().write(outputInBytes);
				conn.connect();
				int responsecode=conn.getResponseCode();
				 if (responsecode == HttpURLConnection.HTTP_OK) {
		             String line;
		             BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
		             while ((line=br.readLine()) != null) {
		                 response+=line;
		             }
		         }else if(responsecode == HttpURLConnection.HTTP_CLIENT_TIMEOUT)
		         {
		        	 response_json=new JSONObject();
		        	 response_json.put("error", "Connection to server lost..");
		         }
				 
				 response_json=new JSONObject(response);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return response_json;
			
		}

		
		@Override
		protected void onPostExecute(JSONObject result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			if(result!=null &&	result.has("responsecode"))
			{
				try {
					if(Integer.valueOf(result.get("responsecode").toString())==2)
					{
					 
						ringProgressDialog.cancel();
						Toast.makeText(context,"Data Syc Successfull", Toast.LENGTH_LONG).show();
					}
					else{
						ringProgressDialog.cancel();
						Toast.makeText(context,"Data Syc failed..."+result.getString("responsecode"), Toast.LENGTH_LONG).show();
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			 
			
		}
		

		
	}
}
*/