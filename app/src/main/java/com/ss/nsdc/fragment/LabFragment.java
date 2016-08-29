/**
 *
 */
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
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.ss.nsdc.R;
import com.ss.nsdc.dao.NSDCDBController;
import com.ss.nsdc.dao.SubCategoryClass;
import com.ss.nsdc.dao.SubCategoryLab;
import com.ss.nsdc.fragment.ClassroomFragment.ExecuteSyncOperation;
import com.ss.nsdc.main.CategoryActivity;
import com.ss.nsdc.utility.ControlsUtility;
import com.ss.nsdc.utility.UtilityService;

/**
 * @author Vishal
 */
public class LabFragment extends Fragment {

    private View view;

    private Spinner ans1;
    private Spinner ans2;
    private EditText ans3;
    private Spinner ans4;
    private RadioGroup ans5;
    private EditText ans6;
    private EditText ans7;
    private EditText ans8;
    private RadioGroup ans9;
    private RadioGroup ans11;
    private EditText ans12;
    private RadioButton ans51;
    private RadioButton ans52;
    private RadioButton ans91;
    private RadioButton ans92;
    private RadioButton ans111;
    private RadioButton ans112;
    private CheckBox ans101;
    private CheckBox ans102;
    private CheckBox ans103;
    private CheckBox ans104;

    private Button subButton;
    //private Button draftButton;

    private ImageView imageView121;
    private ImageView imageView122;
    private ImageView imageView111;
    private ImageView imageView112;
    private ImageView imageView11;
    private ImageView imageView12;
    private ImageView imageView21;
    private ImageView imageView22;
    private ImageView imageView41;
    private ImageView imageView42;
    private ImageView imageView51;
    private ImageView imageView52;
    private ImageView imageView61;
    private ImageView imageView62;
    private ImageView imageView71;
    private ImageView imageView72;
    private ImageView imageView81;
    private ImageView imageView82;
    private ImageView imageView91;
    private ImageView imageView92;
    private ImageView imageView101;
    private ImageView imageView102;
    private ProgressDialog ringProgressDialog;
    //0 - not attempted, 1- edited 2- submitted
    private int ans1edit, ans2edit, ans3edit, ans4edit, ans5edit, ans6edit, ans7edit, ans8edit, ans9edit, ans10edit, ans11edit, ans12edit;
    private boolean isAllAttempted = false;
    Context context;
    private SubCategoryLab getSelectedLabData;
    public static String category, classId, applicationNo, YearWiseCollegeId;
    UtilityService utility = UtilityService.getInstance();

    public LabFragment() {
        super();
    }

    public LabFragment(SubCategoryLab getSelectedLabData) {
        super();
        this.getSelectedLabData = getSelectedLabData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lab_form, container, false);
        context = container.getContext();
        initializeControls();

        subButton = (Button) view.findViewById(R.id.lab_submit);
        if (getSelectedLabData.getProc_tracker() == 3) {
            subButton.setEnabled(false);
            //draftButton.setEnabled(false);
        }

        ControlsUtility.setDefaultEditText(ans3, "Computer Lab");
        /*	if (container.getIntent().getExtras() != null)
		{
			category = getIntent().getExtras().getString("category");
			YearWiseCollegeId=getIntent().getExtras().getString("yearWiseCollageId");
			applicationNo=getIntent().getExtras().getString("ApplicationId");
			classId= getIntent().getExtras().getString("subcategoryId");
		}*/
        /** Spinner1 */
        ControlsUtility.setSpinnerData(ans1,
                getResources().getStringArray(R.array.labType), view);

        /** Spinner2 */
        ControlsUtility.setSpinnerData(ans2,
                getResources().getStringArray(R.array.yesNo), view);

        /** Spinner3 */
        ControlsUtility.setSpinnerData(ans4,
                getResources().getStringArray(R.array.internetAvailability),
                view);

        if (getSelectedLabData != null) {

            ControlsUtility.setDefaultSpinnerText(ans1, getSelectedLabData
                            .getLab_type(),
                    getResources().getStringArray(R.array.labType));

            ControlsUtility
                    .setDefaultSpinnerText(
                            ans2,
                            (getSelectedLabData.getInslabSameAsClass() != null) ? getSelectedLabData
                                    .getInslabSameAsClass()
                                    : getSelectedLabData.getLabSameAsClass(),
                            getResources().getStringArray(R.array.yesNo));

            ControlsUtility.setDefaultEditText(ans3,
                    getSelectedLabData.getLAB_Name());

            ControlsUtility
                    .setDefaultSpinnerText(
                            ans4,
                            (getSelectedLabData
                                    .getInsAvailability_Of_Internet_WIFI_connection() != null) ? getSelectedLabData
                                    .getInsAvailability_Of_Internet_WIFI_connection()
                                    : getSelectedLabData
                                    .getAvailability_Of_Internet_WIFI_connection(),
                            getResources().getStringArray(
                                    R.array.internetAvailability));

            ControlsUtility
                    .setDefaultRadioText(
                            ans5,
                            ans51,
                            ans52,
                            (getSelectedLabData.getInsAvailability_Of_AC() != null) ? getSelectedLabData
                                    .getInsAvailability_Of_AC()
                                    : getSelectedLabData
                                    .getAvailability_Of_AC(),
                            R.id.lab_radioButton51, R.id.lab_radioButton52);

            ControlsUtility
                    .setDefaultEditText(
                            ans6,
                            (getSelectedLabData.getInsCarpet_Area() != null) ? getSelectedLabData
                                    .getInsCarpet_Area() : getSelectedLabData
                                    .getCarpet_Area());

            ControlsUtility
                    .setDefaultEditText(
                            ans7,
                            (getSelectedLabData.getInsSeating_Capacity() != null) ? getSelectedLabData
                                    .getInsSeating_Capacity()
                                    : getSelectedLabData.getSeating_Capacity());

            ControlsUtility
                    .setDefaultEditText(
                            ans8,
                            (getSelectedLabData.getInsNum_IT_Com_lab() != null) ? getSelectedLabData
                                    .getInsNum_IT_Com_lab()
                                    : getSelectedLabData.getInsNum_IT_Com_lab());

            ControlsUtility
                    .setDefaultRadioText(
                            ans9,
                            ans91,
                            ans92,
                            (getSelectedLabData
                                    .getInsAvailability_Of_Power_BackUp() != null) ? getSelectedLabData
                                    .getInsAvailability_Of_Power_BackUp()
                                    : getSelectedLabData
                                    .getAvailability_Of_Power_BackUp(),
                            R.id.lab_radioButton91, R.id.lab_radioButton92);

            ControlsUtility.setDefaultCheck(new CheckBox[]{ans101, ans102,
                    ans103, ans104}, getSelectedLabData.getJOB_role());

            ControlsUtility
                    .setDefaultRadioText(
                            ans11,
                            ans111,
                            ans112,
                            (getSelectedLabData
                                    .getInsArea_under_CCTV_Coverage() != null) ? getSelectedLabData
                                    .getInsArea_under_CCTV_Coverage()
                                    : getSelectedLabData
                                    .getArea_under_CCTV_Coverage(),
                            R.id.lab_radioButton111, R.id.lab_radioButton112);

            ControlsUtility
                    .setDefaultEditText(
                            ans12,
                            (getSelectedLabData.getInsRemarks() != null) ? getSelectedLabData
                                    .getInsRemarks() : getSelectedLabData
                                    .getRemarks());
        }

        imageView11.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView12, ans1, view);
                ans1edit = 1;
            }
        });

        imageView12.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView12, ans1, view, ans1edit);
                ans1edit = 2;
                getSelectedLabData.setInsAvailability_Of_Power_BackUp(ans1.getSelectedItem().toString());
            }
        });

        imageView21.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView22, ans2, view);
                ans2edit = 1;
            }
        });

        imageView22.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView22, ans2, view, ans2edit);
                getSelectedLabData.setInslabSameAsClass(ans2.getSelectedItem()
                        .toString());
                ans2edit = 2;
            }
        });

        imageView41.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView42, ans4, view);
                ans4edit = 1;
            }
        });

        imageView42.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView42, ans4, view, ans4edit);
                getSelectedLabData
                        .setInsAvailability_Of_Internet_WIFI_connection(ans4
                                .getSelectedItem().toString());
                ans4edit = 2;
            }
        });

        imageView51.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView52, ans51, ans52,
                        R.id.lab_radioButton51, R.id.lab_radioButton52, view);
                ans5edit = 1;
            }
        });

        imageView52.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView52, ans51, ans52,
                        R.id.lab_radioButton51, R.id.lab_radioButton52, view, ans5edit);
                getSelectedLabData.setInsAvailability_Of_AC(ControlsUtility
                        .getSelectedRadioText(ans5));
                ans5edit = 2;
            }
        });

        imageView61.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView62, ans6, view);
                ans6edit = 1;
            }
        });

        imageView62.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView62, ans6, view, ans6edit);
                getSelectedLabData.setInsCarpet_Area(ans6.getText().toString());
                ans6edit = 2;
            }
        });

        imageView71.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView72, ans7, view);
                ans7edit = 1;
            }
        });

        imageView72.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView72, ans7, view, ans7edit);
                getSelectedLabData.setInsSeating_Capacity(ans7.getText().toString());
                ans7edit = 2;
            }
        });

        imageView81.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView82, ans8, view);
                ans7edit = 1;
            }
        });

        imageView82.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView82, ans8, view, ans8edit);
                getSelectedLabData.setInsNum_IT_Com_lab(ans8.getText().toString());
                ans8edit = 2;
            }
        });

        imageView91.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView92, ans91, ans92,
                        R.id.lab_radioButton91, R.id.lab_radioButton92, view);
                ans9edit = 1;
            }
        });

        imageView92.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView92, ans91, ans92,
                        R.id.lab_radioButton91, R.id.lab_radioButton92, view, ans9edit);
                getSelectedLabData.setInsAvailability_Of_Power_BackUp(ControlsUtility
                        .getSelectedRadioText(ans9));
                ans9edit = 2;
            }
        });

        imageView101.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility
                        .editImageViewAction(imageView102, new CheckBox[]{
                                ans101, ans102, ans103, ans104}, view);
                ans10edit = 1;
            }
        });

        imageView102.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView102, new CheckBox[]{
                        ans101, ans102, ans103, ans104}, view, ans10edit);
                getSelectedLabData.setInsJobRole((ans101.isChecked()) ? ans101.getText().toString() : "");
                getSelectedLabData.setInsJobRole((ans102.isChecked()) ? getSelectedLabData.getInsJobRole() + " ," + ans102.getText().toString() : "");
                getSelectedLabData.setInsJobRole((ans103.isChecked()) ? getSelectedLabData.getInsJobRole() + " ," + ans103.getText().toString() : "");
                getSelectedLabData.setInsJobRole((ans104.isChecked()) ? getSelectedLabData.getInsJobRole() + " ," + ans104.getText().toString() : "");

                ans10edit = 2;
            }
        });

        imageView111.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView112, ans111, ans112, R.id.lab_radioButton111,
                        R.id.lab_radioButton112, view);
                ans11edit = 1;
            }
        });

        imageView112.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView112, ans111, ans112,
                        R.id.lab_radioButton111, R.id.lab_radioButton112, view, ans11edit);
                getSelectedLabData
                        .setInsArea_under_CCTV_Coverage(ControlsUtility
                                .getSelectedRadioText(ans9));
                ans11edit = 2;
            }
        });

        imageView121.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView122, ans12, view);
                ans12edit = 1;
            }
        });

        imageView122.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView122, ans12, view, ans12edit);
                getSelectedLabData.setInsRemarks(ans12.getText().toString());
                ans12edit = 2;
            }
        });
        subButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Confirmation")
                        .setMessage("This record will not be editable after submit")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                JSONObject datatoSycLab = new JSONObject();
                                if (checkAllAtempted()) {
                                    if (utility.getConnectivityStatus(context)) {
                                        List<SubCategoryLab> labResults = new ArrayList<SubCategoryLab>();
                                        labResults.add(getSelectedLabData);
                                        datatoSycLab = utility.getLabSycData(labResults);
                                        new ExecuteSyncOperation()
                                                .execute(new String[]{"http://nsdc.qci.org.in/api/CAAF/LAB_Details.php",
                                                        datatoSycLab.toString(), "bnNkYzd0ZWNoaWVzYXBp"});
                                    } else {
                                        NSDCDBController controller = new NSDCDBController(context);
                                        boolean updation_status = controller.saveLabData(getSelectedLabData, "draft");
                                        controller.close();
                                        if (updation_status) {
                                            Toast.makeText(context, "Data Saved.", Toast.LENGTH_LONG).show();
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

/*		draftButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				boolean updation_status=false;
				try {
					NSDCDBController controller = new NSDCDBController(context);
					updation_status=controller.saveLabData(getSelectedLabData, "draft");
					controller.close();
					if(updation_status){
						navigate();
					}
					else
					{
						Toast.makeText(context, "Error in saving draft..", Toast.LENGTH_LONG).show();
					}
				} catch (Exception e) {
					Log.e("LAB Fragment", e.getMessage());
				}
			}
		});*/
        return view;
    }

    private void initializeControls() {
        ans1 = (Spinner) view.findViewById(R.id.lab_spin1);
        ans2 = (Spinner) view.findViewById(R.id.lab_spin2);
        ans3 = (EditText) view.findViewById(R.id.lab_edit3);
        ans4 = (Spinner) view.findViewById(R.id.lab_spin4);
        ans5 = (RadioGroup) view.findViewById(R.id.lab_radioGroup5);
        ans6 = (EditText) view.findViewById(R.id.lab_edit6);
        ans7 = (EditText) view.findViewById(R.id.lab_edit7);
        ans8 = (EditText) view.findViewById(R.id.lab_edit8);
        ans9 = (RadioGroup) view.findViewById(R.id.lab_radioGroup9);

        ans51 = (RadioButton) view.findViewById(R.id.lab_radioButton51);
        ans52 = (RadioButton) view.findViewById(R.id.lab_radioButton52);
        ans91 = (RadioButton) view.findViewById(R.id.lab_radioButton91);
        ans92 = (RadioButton) view.findViewById(R.id.lab_radioButton92);
        ans111 = (RadioButton) view.findViewById(R.id.lab_radioButton111);
        ans112 = (RadioButton) view.findViewById(R.id.lab_radioButton112);

        ans11 = (RadioGroup) view.findViewById(R.id.lab_radioGroup11);
        ans12 = (EditText) view.findViewById(R.id.lab_edit12);

        ans101 = (CheckBox) view.findViewById(R.id.lab_check11);
        ans102 = (CheckBox) view.findViewById(R.id.lab_check12);
        ans103 = (CheckBox) view.findViewById(R.id.lab_check13);
        ans104 = (CheckBox) view.findViewById(R.id.lab_check14);

        subButton = (Button) view.findViewById(R.id.lab_submit);
        //draftButton = (Button) view.findViewById(R.id.lab_draft);

        imageView11 = (ImageView) view.findViewById(R.id.lab_img11);
        imageView12 = (ImageView) view.findViewById(R.id.lab_img12);
        imageView21 = (ImageView) view.findViewById(R.id.lab_img21);
        imageView22 = (ImageView) view.findViewById(R.id.lab_img22);
        imageView41 = (ImageView) view.findViewById(R.id.lab_img41);
        imageView42 = (ImageView) view.findViewById(R.id.lab_img42);
        imageView51 = (ImageView) view.findViewById(R.id.lab_img51);
        imageView52 = (ImageView) view.findViewById(R.id.lab_img52);
        imageView61 = (ImageView) view.findViewById(R.id.lab_img61);
        imageView62 = (ImageView) view.findViewById(R.id.lab_img62);
        imageView71 = (ImageView) view.findViewById(R.id.lab_img71);
        imageView72 = (ImageView) view.findViewById(R.id.lab_img72);
        imageView81 = (ImageView) view.findViewById(R.id.lab_img81);
        imageView82 = (ImageView) view.findViewById(R.id.lab_img82);
        imageView91 = (ImageView) view.findViewById(R.id.lab_img91);
        imageView92 = (ImageView) view.findViewById(R.id.lab_img92);
        imageView101 = (ImageView) view.findViewById(R.id.lab_img101);
        imageView102 = (ImageView) view.findViewById(R.id.lab_img102);
        imageView111 = (ImageView) view.findViewById(R.id.lab_img111);
        imageView112 = (ImageView) view.findViewById(R.id.lab_img112);
        imageView121 = (ImageView) view.findViewById(R.id.lab_img121);
        imageView122 = (ImageView) view.findViewById(R.id.lab_img122);
    }

    class ExecuteSyncOperation extends AsyncTask<String, Integer, JSONObject> {
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(
                            context, android.R.style.Theme_Holo), null,
                    " Data Synchronizing ...", true);
            ringProgressDialog.setCancelable(false);
            ringProgressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected JSONObject doInBackground(String... data) {

            String response = "";
            JSONObject response_json = null;
            try {
                URL url = new URL(data[0]);
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                String str = "result=" + data[1] + "&Token=" + data[2];
                byte[] outputInBytes = str.getBytes("UTF-8");
                conn.getOutputStream().write(outputInBytes);
                conn.connect();
                int responsecode = conn.getResponseCode();
                if (responsecode == HttpURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }
                } else if (responsecode == HttpURLConnection.HTTP_CLIENT_TIMEOUT) {
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

            if (result != null && result.has("responsecode")) {
                try {
                    if (Integer.valueOf(result.get("responsecode").toString()) == 2) {

                        ringProgressDialog.cancel();
                        Toast.makeText(context, "Data Sync Successful",
                                Toast.LENGTH_LONG).show();
                        NSDCDBController controller = new NSDCDBController(context);
                        boolean updation_status = controller.saveLabData(getSelectedLabData, "complete");
                        controller.close();
                        if (updation_status) {
                            subButton.setEnabled(false);
                            //draftButton.setEnabled(false);
                            navigate();
                        } else {
                            Toast.makeText(context, "Error in updation of data..", Toast.LENGTH_LONG).show();
                        }


                    } else {
                        ringProgressDialog.cancel();
                        Toast.makeText(context, "Data Syc failed...",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void navigate() {
        Intent intent = new Intent(context, CategoryActivity.class);
        intent.putExtra("YearWiseCollegeId", getActivity().getIntent().getExtras().getString("yearWiseCollageId"));
        intent.putExtra("applicationNo", getActivity().getIntent().getExtras().getString("yearWiseCollageId"));
        intent.putExtra("instituteName", getActivity().getIntent().getExtras().getString("ApplicationId"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public boolean checkAllAtempted() {
        if (ans2edit == 2 && ans1edit == 2 && ans4edit == 2 && ans5edit == 2 && ans6edit == 2 && ans7edit == 2 &&
                ans8edit == 2 && ans9edit == 2 && ans10edit == 2 && ans11edit == 2 && ans12edit == 2) {
            return true;
        } else {
            return false;
        }
    }
}
