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
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
import com.ss.nsdc.main.CategoryActivity;
import com.ss.nsdc.utility.ControlsUtility;
import com.ss.nsdc.utility.UtilityService;

/**
 * @author Vishal
 */
public class ClassroomFragment extends Fragment {

    View view;
    ProgressDialog ringProgressDialog;
    private EditText ans1;
    private EditText ans2;
    private RadioButton ans31;
    private RadioButton ans32;
    private RadioButton ans41;
    private RadioButton ans42;
    private RadioButton ans51;
    private RadioButton ans52;
    private RadioButton ans61;
    private RadioButton ans62;
    private RadioGroup ans3;
    private RadioGroup ans4;
    private RadioGroup ans5;
    private RadioGroup ans6;
    private Spinner ans7;
    private EditText ans8;
    private EditText ans9;
    private EditText ans10;
    private Button subButton;
    private Button draftButton;
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
    private ImageView imageView81;
    private ImageView imageView82;
    private ImageView imageView91;
    private ImageView imageView92;
    private ImageView imageView101;
    private ImageView imageView102;

    private boolean isAllAttempted = false;
    Context context;
    UtilityService utility = UtilityService.getInstance();
    private SubCategoryClass getSelectedClassData;

    private NSDCDBController controller;

    public ClassroomFragment() {
        super();
    }

    public ClassroomFragment(SubCategoryClass getSelectedClassData) {
        super();
        this.getSelectedClassData = getSelectedClassData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.classroom_form, container, false);
        context = container.getContext();
        initializeControls();
        if (getSelectedClassData.getProc_tracker() == 3) {
            subButton.setEnabled(false);
            draftButton.setEnabled(false);
        }
        ControlsUtility.setSpinnerData(ans7,
                getResources().getStringArray(R.array.internetAvailability),
                view);

        ControlsUtility.setDefaultSpinnerText(ans7, getSelectedClassData
                        .getAvail_Internet(),
                getResources().getStringArray(R.array.internetAvailability));

        /** Setting Default Values */

        if (getSelectedClassData != null) {
            ControlsUtility.setDefaultEditText(ans1,
                    getSelectedClassData.getClassroom_Name());
            ControlsUtility
                    .setDefaultEditText(
                            ans2,
                            (getSelectedClassData.getInsCarpet_Area() != null) ? getSelectedClassData
                                    .getInsCarpet_Area() : getSelectedClassData
                                    .getCarpet_Area());

            ControlsUtility
                    .setDefaultRadioText(
                            ans3,
                            ans31,
                            ans32,
                            (getSelectedClassData
                                    .getInsAvailability_Of_Power_BackUp() != null) ? getSelectedClassData
                                    .getInsAvailability_Of_Power_BackUp()
                                    : getSelectedClassData
                                    .getAvailability_Of_Power_BackUp(),
                            R.id.cls_radioButton31, R.id.cls_radioButton32);
            ControlsUtility
                    .setDefaultRadioText(
                            ans4,
                            ans41,
                            ans42,
                            (getSelectedClassData
                                    .getInsAvailability_of_Overhead_Projector() != null) ? getSelectedClassData
                                    .getInsAvailability_of_Overhead_Projector()
                                    : getSelectedClassData
                                    .getAvailability_of_Overhead_Projector(),
                            R.id.cls_radioButton41, R.id.cls_radioButton42);
            ControlsUtility
                    .setDefaultRadioText(
                            ans5,
                            ans51,
                            ans52,
                            (getSelectedClassData.getInsAvailability_Of_AC() != null) ? getSelectedClassData
                                    .getInsAvailability_Of_AC()
                                    : getSelectedClassData
                                    .getAvailability_Of_AC(),
                            R.id.cls_radioButton51, R.id.cls_radioButton52);
            ControlsUtility
                    .setDefaultRadioText(
                            ans6,
                            ans61,
                            ans62,
                            (getSelectedClassData
                                    .getInsArea_under_CCTV_Coverage() != null) ? getSelectedClassData
                                    .getInsArea_under_CCTV_Coverage()
                                    : getSelectedClassData
                                    .getArea_under_CCTV_Coverage(),
                            R.id.cls_radioButton61, R.id.cls_radioButton62);

            ControlsUtility
                    .setDefaultSpinnerText(
                            ans7,
                            (getSelectedClassData.getInsavail_Internet() != null) ? getSelectedClassData
                                    .getInsavail_Internet()
                                    : getSelectedClassData.getAvail_Internet(),
                            getResources().getStringArray(
                                    R.array.internetAvailability));

            ControlsUtility
                    .setDefaultEditText(
                            ans8,
                            (getSelectedClassData.getInsSeating_Capacity() != null) ? getSelectedClassData
                                    .getInsSeating_Capacity()
                                    : getSelectedClassData
                                    .getSeating_Capacity());
            ControlsUtility
                    .setDefaultEditText(
                            ans9,
                            (getSelectedClassData.getInsAvg_Batches() != null) ? getSelectedClassData
                                    .getInsAvg_Batches() : getSelectedClassData
                                    .getAvg_Batches());

            ControlsUtility
                    .setDefaultEditText(
                            ans10,
                            (getSelectedClassData.getInsRemarks() != null) ? getSelectedClassData
                                    .getInsRemarks() : getSelectedClassData
                                    .getRemarks());
        }
        imageView21.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView22, ans2, view);
                isAllAttempted = false;
            }
        });

        imageView22.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView22, ans2, view);
                getSelectedClassData.setInsCarpet_Area(ans2.getText()
                        .toString());
                isAllAttempted = true;
            }
        });

        imageView31.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView32, ans31, ans32,
                        R.id.cls_radioButton31, R.id.cls_radioButton32, view);
                isAllAttempted = false;
            }
        });

        imageView32.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView32, ans31, ans32,
                        R.id.cls_radioButton31, R.id.cls_radioButton32, view);
                getSelectedClassData
                        .setInsAvailability_Of_Power_BackUp(ControlsUtility
                                .getSelectedRadioText(ans3));
                isAllAttempted = true;
            }
        });

        imageView41.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView42, ans41, ans42,
                        R.id.cls_radioButton41, R.id.cls_radioButton42, view);
                isAllAttempted = false;
            }
        });

        imageView42.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView42, ans41, ans42,
                        R.id.cls_radioButton41, R.id.cls_radioButton42, view);
                getSelectedClassData
                        .setInsAvailability_of_Overhead_Projector(ControlsUtility
                                .getSelectedRadioText(ans4));
                isAllAttempted = true;
            }
        });

        imageView51.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView52, ans51, ans52,
                        R.id.cls_radioButton51, R.id.cls_radioButton52, view);
                isAllAttempted = false;
            }
        });

        imageView52.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView52, ans51, ans52,
                        R.id.cls_radioButton51, R.id.cls_radioButton52, view);
                getSelectedClassData.setInsAvailability_Of_AC(ControlsUtility
                        .getSelectedRadioText(ans5));
                isAllAttempted = true;
            }
        });
        imageView61.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView62, ans61, ans62,
                        R.id.cls_radioButton61, R.id.cls_radioButton62, view);
                isAllAttempted = false;
            }
        });

        imageView62.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView62, ans61, ans62,
                        R.id.cls_radioButton61, R.id.cls_radioButton62, view);
                getSelectedClassData
                        .setInsArea_under_CCTV_Coverage(ControlsUtility
                                .getSelectedRadioText(ans5));
                isAllAttempted = true;
            }
        });

        imageView71.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView72, ans7, view);
                isAllAttempted = false;
            }
        });

        imageView72.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView72, ans7, view);
                getSelectedClassData.setInsavail_Internet(ans7
                        .getSelectedItem().toString());
                isAllAttempted = true;
            }
        });

        imageView81.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView82, ans8, view);
                isAllAttempted = false;
            }
        });

        imageView82.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView82, ans8, view);
                getSelectedClassData.setInsAvg_Batches(ans8.getText()
                        .toString());
                isAllAttempted = true;
            }
        });

        imageView91.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView92, ans9, view);
                isAllAttempted = false;
            }
        });

        imageView92.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView92, ans9, view);
                getSelectedClassData.setInsSeating_Capacity(ans9.getText()
                        .toString());
                isAllAttempted = true;
            }
        });

        imageView101.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView102, ans10, view);
                isAllAttempted = false;
            }
        });

        imageView102.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView102, ans10, view);
                getSelectedClassData.setInsRemarks(ans10.getText().toString());
                isAllAttempted = true;
            }
        });

        subButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                JSONObject datatoSycClass = new JSONObject();
                if (isAllAttempted) {
                    if (utility.getConnectivityStatus(context)) {
                        List<SubCategoryClass> classResults = new ArrayList<SubCategoryClass>();
                        classResults.add(getSelectedClassData);
                        datatoSycClass = utility.getClassDataSync(classResults);
                        new ExecuteSyncOperation()
                                .execute(new String[]{
                                        "http://nsdc.qci.org.in/api/CAAF/Classroom_Details.php",
                                        datatoSycClass.toString(),
                                        "bnNkYzd0ZWNoaWVzYXBp"});
                    }
                } else {
                    Toast.makeText(context, "Attempt all questions",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        draftButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean updation_status = false;
                try {
                    NSDCDBController controller = new NSDCDBController(context);
                    updation_status = controller.saveClassData(getSelectedClassData, "draft");
                    controller.close();
                    if (updation_status) {
                        navigate();
                    } else {
                        Toast.makeText(context, "Error in saving draft..", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Log.e("Classroom Fragment", e.getMessage());
                }
            }
        });
        return view;
    }

    private void initializeControls() {

        ans1 = (EditText) view.findViewById(R.id.cls_edit1);
        ans2 = (EditText) view.findViewById(R.id.cls_edit2);
        ans3 = (RadioGroup) view.findViewById(R.id.cls_radioGroup3);
        ans31 = (RadioButton) view.findViewById(R.id.cls_radioButton31);
        ans32 = (RadioButton) view.findViewById(R.id.cls_radioButton32);
        ans41 = (RadioButton) view.findViewById(R.id.cls_radioButton41);
        ans42 = (RadioButton) view.findViewById(R.id.cls_radioButton42);
        ans51 = (RadioButton) view.findViewById(R.id.cls_radioButton51);
        ans52 = (RadioButton) view.findViewById(R.id.cls_radioButton52);
        ans61 = (RadioButton) view.findViewById(R.id.cls_radioButton61);
        ans62 = (RadioButton) view.findViewById(R.id.cls_radioButton62);
        ans4 = (RadioGroup) view.findViewById(R.id.cls_radioGroup4);
        ans5 = (RadioGroup) view.findViewById(R.id.cls_radioGroup5);
        ans6 = (RadioGroup) view.findViewById(R.id.cls_radioGroup6);
        ans7 = (Spinner) view.findViewById(R.id.cls_spin7);
        ans8 = (EditText) view.findViewById(R.id.cls_edit8);
        ans9 = (EditText) view.findViewById(R.id.cls_edit9);
        ans10 = (EditText) view.findViewById(R.id.cls_edit10);
        subButton = (Button) view.findViewById(R.id.cls_submit);
        draftButton = (Button) view.findViewById(R.id.cls_draft);

        imageView21 = (ImageView) view.findViewById(R.id.cls_img21);
        imageView22 = (ImageView) view.findViewById(R.id.cls_img22);
        imageView31 = (ImageView) view.findViewById(R.id.cls_img31);
        imageView32 = (ImageView) view.findViewById(R.id.cls_img32);
        imageView41 = (ImageView) view.findViewById(R.id.cls_img41);
        imageView42 = (ImageView) view.findViewById(R.id.cls_img42);
        imageView51 = (ImageView) view.findViewById(R.id.cls_img51);
        imageView52 = (ImageView) view.findViewById(R.id.cls_img52);
        imageView61 = (ImageView) view.findViewById(R.id.cls_img61);
        imageView62 = (ImageView) view.findViewById(R.id.cls_img62);
        imageView71 = (ImageView) view.findViewById(R.id.cls_img71);
        imageView72 = (ImageView) view.findViewById(R.id.cls_img72);
        imageView81 = (ImageView) view.findViewById(R.id.cls_img81);
        imageView82 = (ImageView) view.findViewById(R.id.cls_img82);
        imageView91 = (ImageView) view.findViewById(R.id.cls_img91);
        imageView92 = (ImageView) view.findViewById(R.id.cls_img92);
        imageView101 = (ImageView) view.findViewById(R.id.cls_img101);
        imageView102 = (ImageView) view.findViewById(R.id.cls_img102);
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
                        Toast.makeText(context, "Data Syc Successfull",
                                Toast.LENGTH_LONG).show();
                        NSDCDBController controller = new NSDCDBController(context);
                        boolean updation_status = controller.saveClassData(getSelectedClassData, "complete");
                        controller.close();
                        if (updation_status) {
                            subButton.setEnabled(false);
                            draftButton.setEnabled(false);
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
            } else {
                ringProgressDialog.dismiss();
                Toast.makeText(context, "Data Syc failed...",
                        Toast.LENGTH_LONG).show();
            }

        }

    }

    public void navigate() {
        Intent intent = new Intent(context, CategoryActivity.class);
        intent.putExtra("YearWiseCollegeId", getActivity().getIntent().getExtras().getString("yearWiseCollageId"));
        intent.putExtra("applicationNo", getActivity().getIntent().getExtras().getString("yearWiseCollageId"));
        intent.putExtra("instituteName", getActivity().getIntent().getExtras().getString("ApplicationId"));
        context.startActivity(intent);
    }
}
