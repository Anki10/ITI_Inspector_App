package com.ss.nsdc.fragment;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.ss.nsdc.R;
import com.ss.nsdc.constant.AppConstants;
import com.ss.nsdc.dao.SubCategorySupportStaffDAO;
import com.ss.nsdc.entity.SubCategorySupportStaff;
import com.ss.nsdc.utility.ControlsUtility;
import com.ss.nsdc.utility.UtilityService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Arjit on 29-08-2016.
 */
public class SupportStaffFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    Spinner spinnerStaffType;
    ImageView imageStaffTypeEdit;
    ImageView imageStaffTypeSave;

    EditText editStaffName;
    ImageView imageStaffNameEdit;
    ImageView imageStaffNameSave;

    EditText editRemarks;
    ImageView imageRemarksEdit;
    ImageView imageRemarksSave;

    LinearLayout linearLayoutStaffWork;
    EditText editWork;
    ImageView imageWorkEdit;
    ImageView imageWorkSave;

    Button btnDraft;
    Button btnSave;

    View view;
    Context context;
    SubCategorySupportStaff staffData;
    ProgressDialog ringProgressDialog;
    UtilityService utility = UtilityService.getInstance();

    public SupportStaffFragment() {
        super();
    }

    public SupportStaffFragment(SubCategorySupportStaff subCategorySupportStaff) {
        super();
        this.staffData = subCategorySupportStaff;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_support_staff, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initValues();
        initListeners();
    }

    private void initViews() {
        spinnerStaffType = (Spinner) view.findViewById(R.id.spinner_ss_type);
        /*imageStaffTypeEdit = (ImageView) view.findViewById(R.id.image_ss_type_edit);
        imageStaffTypeSave = (ImageView) view.findViewById(R.id.image_ss_type_save);*/

        editStaffName = (EditText) view.findViewById(R.id.edit_ss_name);
        /*imageStaffNameEdit = (ImageView) view.findViewById(R.id.image_ss_name_edit);
        imageStaffNameSave = (ImageView) view.findViewById(R.id.image_ss_name_save);*/

        linearLayoutStaffWork = (LinearLayout) view.findViewById(R.id.linearLayoutStaffWork);
        editWork = (EditText) view.findViewById(R.id.edit_ss_work);
        imageWorkEdit = (ImageView) view.findViewById(R.id.image_ss_work_edit);
        imageWorkSave = (ImageView) view.findViewById(R.id.image_ss_work_save);

        editRemarks = (EditText) view.findViewById(R.id.edit_ss_remarks);
        imageRemarksEdit = (ImageView) view.findViewById(R.id.image_ss_remarks_edit);
        imageRemarksSave = (ImageView) view.findViewById(R.id.image_ss_remarks_save);

        btnDraft = (Button) view.findViewById(R.id.btn_ss_draft);
        btnSave = (Button) view.findViewById(R.id.btn_ss_submit);
    }

    private void initValues() {
        ControlsUtility.setDefaultSpinnerText(spinnerStaffType, (staffData.getStaffType() != null)
                        ? staffData.getStaffType() : staffData.getStaffType(),
                getResources().getStringArray(R.array.arrayStaffType));

        if (staffData.getStaffType() != null && staffData.getStaffType().equals("Other")) {
            linearLayoutStaffWork.setVisibility(View.VISIBLE);
        } else {
            linearLayoutStaffWork.setVisibility(View.GONE);
        }

        editStaffName.setText(staffData.getStaffName());
        editStaffName.setEnabled(false);

        editRemarks.setText(staffData.getRemarks());
        editRemarks.setEnabled(false);

        editWork.setText(staffData.getWork());
        editWork.setEnabled(false);
    }

    private void initListeners() {
        spinnerStaffType.setOnItemSelectedListener(this);
        /*imageStaffTypeEdit.setOnClickListener(this);
        imageStaffTypeSave.setOnClickListener(this);
*/
        imageRemarksEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageRemarksEdit, editRemarks, view);
            }
        });

        imageRemarksSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageRemarksSave, editRemarks, view,1);
                staffData.setInsRemarks(editRemarks.getText().toString());
            }
        });

        imageWorkEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageWorkEdit, editWork, view);
            }
        });

        imageWorkSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageWorkSave, editWork, view,1);
                staffData.setInsWork(editWork.getText().toString());
            }
        });

        btnDraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (utility.getConnectivityStatus(context)) {
                    JSONObject dataToSyncClass = utility.getStaffSycData(staffData);
                    new ExecuteSyncOperation().execute(new String[]{AppConstants.API_URL + "Offiec_Area.php",
                            dataToSyncClass.toString(), AppConstants.API_TOKEN_VALUE});

                } else {
                    SubCategorySupportStaffDAO staffDAO = new SubCategorySupportStaffDAO(context);
                    boolean updateStatus = staffDAO.saveStaffData(staffData, "draft");
                    staffDAO.close();

                    if (updateStatus) {
                        Toast.makeText(context, "Data saved.", Toast.LENGTH_LONG).show();
                        //navigate();
                    } else {
                        Toast.makeText(context, "Error in saving..", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        switch (adapterView.getId()) {
            case R.id.spinner_ss_type: {
                break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
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

            if (result != null && result.has(AppConstants.KEY_API_RESPONSE_CODE)) {
                try {
                    if (Integer.valueOf(result.get(AppConstants.KEY_API_RESPONSE_CODE).toString()) == 2) {
                        ringProgressDialog.cancel();
                        Toast.makeText(context, "Data Sync Successfull", Toast.LENGTH_LONG).show();
                        SubCategorySupportStaffDAO staffDAO = new SubCategorySupportStaffDAO(context);
                        staffDAO.saveStaffData(staffData, "complete");
                        staffDAO.close();

                    } else {
                        ringProgressDialog.cancel();
                        Toast.makeText(context, "Data Syc failed..." + result.getString("responsecode"), Toast.LENGTH_LONG).show();
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
