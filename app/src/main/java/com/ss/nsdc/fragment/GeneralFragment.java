/*
package com.ss.nsdc.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ss.nsdc.R;
import com.ss.nsdc.dao.NSDCDBController;
import com.ss.nsdc.entity.GeneralInfo;
import com.ss.nsdc.main.CategoryActivity;
import com.ss.nsdc.utility.ControlsUtility;
import com.ss.nsdc.utility.UtilityService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * @author Prashant
 *//*

public class GeneralFragment extends Fragment {

    View view;
    ProgressDialog ringProgressDialog;
    private EditText ans1;
    private EditText ans2;
    private EditText ans3;
    private EditText ans4;
    private EditText ans5;

    private ImageView imageView31;
    private ImageView imageView32;
    private ImageView imageView51;
    private ImageView imageView52;

    private Button subButton;
    //0 - not attempted, 1- edited 2- submitted
    private int ans3edit, ans5edit;

    Context context;
    UtilityService utility = UtilityService.getInstance();
    private GeneralInfo getGeneralInfoData;

    public GeneralFragment() {
        super();
    }

    public GeneralFragment(GeneralInfo getGeneralInfoData) {
        super();
        this.getGeneralInfoData = getGeneralInfoData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_office, container, false);
        context = container.getContext();
        initializeControls();

        */
/** Setting Default Values *//*


        if (getGeneralInfoData != null) {
            //ANS 1
            ControlsUtility.setDefaultEditText(ans1,getGeneralInfoData.getJob_Name());

            //ANS 2
            ControlsUtility.setDefaultEditText(ans2,getGeneralInfoData.getEquipment_Name());
            //ANS 3
            ControlsUtility.setDefaultEditText(ans3,(getGeneralInfoData.getInsTotalNo() != null)
                            ? getGeneralInfoData.getInsTotalNo() : getGeneralInfoData.getTotalNo());

            //ANS 4
            ControlsUtility.setDefaultEditText(ans4,getGeneralInfoData.getEquipment_Name());
            //ANS 5
            ControlsUtility.setDefaultEditText(ans3,(getGeneralInfoData.getInsRemarks() != null)
                    ? getGeneralInfoData.getInsRemarks() : getGeneralInfoData.getRemarks());

        }


        imageView31.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView32, ans3, view);
                ans3edit = 1;
            }
        });

        imageView32.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView32, ans3, view, ans3edit);
                getGeneralInfoData.setInsTotalNo(ans3.getText().toString());
                ans3edit = 2;
            }
        });

        imageView51.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView52, ans5, view);
                ans5edit = 1;
            }
        });

        imageView52.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView52, ans5, view, ans3edit);
                getGeneralInfoData.setInsRemarks(ans3.getText().toString());
                ans5edit = 2;
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
                                if (checkAllAtempted()) {
                                    if (utility.getConnectivityStatus(context)) {
                                        List<GeneralInfo> results = new ArrayList<GeneralInfo>();
                                        results.add(getGeneralInfoData);
                                        JSONObject datatoSycClass = utility.getEquipmentSycData(results);
                                        new ExecuteSyncOperation().
                                                execute(new String[]{"http://nsdc.qci.org.in/api/CAAF/Offiec_Area.php",
                                                        datatoSycClass.toString(), "bnNkYzd0ZWNoaWVzYXBp"});
                                    } else {
                                        NSDCDBController controller = new NSDCDBController(context);
                                        boolean updation_status = controller.saveEquipmentData(getGeneralInfoData, "draft");
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
        return view;
    }

    private void initializeControls() {

        ans1 = (EditText) view.findViewById(R.id.equip_edit1);
        ans2 = (EditText) view.findViewById(R.id.equip_edit2);
        ans3 = (EditText) view.findViewById(R.id.equip_edit3);
        ans4 = (EditText) view.findViewById(R.id.equip_edit4);
        ans5 = (EditText) view.findViewById(R.id.equip_edit5);

        imageView31 = (ImageView) view.findViewById(R.id.equip_img31);
        imageView32 = (ImageView) view.findViewById(R.id.equip_img32);
        imageView51 = (ImageView) view.findViewById(R.id.equip_img51);
        imageView52 = (ImageView) view.findViewById(R.id.equip_img52);
    }

    class ExecuteSyncOperation extends AsyncTask<String, Integer, JSONObject> {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(context, android.R.style.Theme_Holo), null, " Data Synchronizing ...", true);
            ringProgressDialog.setCancelable(false);
            ringProgressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected JSONObject doInBackground(String... data) {
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
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                String str = "result=" + data[1] + "&Token=" + data[2];
                byte[] outputInBytes = str.getBytes("UTF-8");
                conn.getOutputStream().write(outputInBytes);
                conn.connect();
                int responsecode = conn.getResponseCode();
                if (responsecode == HttpURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
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
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            if (result != null && result.has("responsecode")) {
                try {
                    if (Integer.valueOf(result.get("responsecode").toString()) == 2) {
                        ringProgressDialog.cancel();
                        Toast.makeText(context, "Data Sync Successful", Toast.LENGTH_LONG).show();
                        NSDCDBController controller = new NSDCDBController(context);
                        controller.saveEquipmentData(getGeneralInfoData, "complete");
                        controller.close();
                    } else {
                        ringProgressDialog.cancel();
                        Toast.makeText(context, "Data Sync failed..." + result.getString("responsecode"), Toast.LENGTH_LONG).show();
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

    public void navigate() {
        Intent intent = new Intent(context, CategoryActivity.class);
        intent.putExtra("YearWiseCollegeId", getActivity().getIntent().getExtras().getString("yearWiseCollageId"));
        intent.putExtra("applicationNo", getActivity().getIntent().getExtras().getString("yearWiseCollageId"));
        intent.putExtra("instituteName", getActivity().getIntent().getExtras().getString("ApplicationId"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public boolean checkAllAtempted() {
        if (ans3edit == 2 && ans5edit == 2) {
            return true;
        } else {
            return false;
        }
    }
}
*/
