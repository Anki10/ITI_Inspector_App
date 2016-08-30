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
import com.ss.nsdc.entity.SubListOffice;
import com.ss.nsdc.main.CategoryActivity;
import com.ss.nsdc.utility.ControlsUtility;
import com.ss.nsdc.utility.UtilityService;

/**
 * @author Prashant
 */
public class OfficeFragment extends Fragment {

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

    //0 - not attempted, 1- edited 2- submitted
    private int ans1edit, ans2edit, ans3edit, ans4edit, ans5edit, ans6edit, ans7edit;

    Context context;
    UtilityService utility = UtilityService.getInstance();
    private SubListOffice getSelectedOfficeData;

    public OfficeFragment() {
        super();
    }

    public OfficeFragment(SubListOffice getSelectedOfficeData) {
        super();
        this.getSelectedOfficeData = getSelectedOfficeData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_office, container, false);
        context = container.getContext();
        initializeControls();

        /** Setting Default Values */

        if (getSelectedOfficeData != null) {
            //ANS 1
            ControlsUtility.setSpinnerData(ans1,
                    getResources().getStringArray(R.array.officeType), view);

            ControlsUtility.setDefaultSpinnerText(ans1, getSelectedOfficeData.getAreaType(),
                    getResources().getStringArray(R.array.officeType));

            //ANS 2
            ControlsUtility.setDefaultEditText(ans2,
                    (getSelectedOfficeData.getInsCarpetArea() != null) ? getSelectedOfficeData
                            .getInsCarpetArea() : getSelectedOfficeData.getCarpetArea());
            //ANS 3
            ControlsUtility.setSpinnerData(ans3,
                    getResources().getStringArray(R.array.internetAvailability), view);

            ControlsUtility.setDefaultSpinnerText(ans3, (getSelectedOfficeData.getInsInternet() != null)
                            ? getSelectedOfficeData.getInsInternet() : getSelectedOfficeData.getInternet(),
                    getResources().getStringArray(R.array.internetAvailability));

            //ANS 4
            ControlsUtility.setDefaultRadioText(
                    ans4, ans41, ans42,
                    (getSelectedOfficeData.getInsAC() != null) ? getSelectedOfficeData.getInsAC()
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
                ans2edit = 1;
            }
        });

        imageView22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView22, ans2, view, ans2edit);
                getSelectedOfficeData.setInsCarpetArea(ans2.getText().toString());
                ans2edit = 2;
            }
        });

        imageView31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView32, ans3, view);
                ans3edit = 1;
            }
        });

        imageView32.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView32, ans3, view, ans3edit);
                getSelectedOfficeData.setInsInternet(ans3.getSelectedItem().toString());
                ans3edit = 2;
            }
        });

        imageView41.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView42, ans41, ans42,
                        R.id.off_radioButton41, R.id.off_radioButton42, view);
                ans4edit = 1;
            }
        });

        imageView42.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView42, ans41, ans42,
                        R.id.off_radioButton41, R.id.off_radioButton42, view, ans4edit);
                getSelectedOfficeData.setInsAC(ControlsUtility.getSelectedRadioText(ans4));
                ans4edit = 2;
            }
        });

        imageView51.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView52, ans51, ans52,
                        R.id.off_radioButton51, R.id.off_radioButton52, view);
                ans5edit = 1;
            }
        });

        imageView52.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView52, ans51, ans52,
                        R.id.off_radioButton51, R.id.off_radioButton52, view, ans5edit);
                getSelectedOfficeData.setInsBackUp(ControlsUtility.getSelectedRadioText(ans5));
                ans5edit = 2;
            }
        });
        imageView61.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.editImageViewAction(imageView62, ans61, ans62,
                        R.id.off_radioButton61, R.id.off_radioButton62, view);
                ans6edit = 1;
            }
        });

        imageView62.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ControlsUtility.okImageViewAction(imageView62, ans61, ans62,
                        R.id.off_radioButton61, R.id.off_radioButton62, view, ans6edit);
                getSelectedOfficeData.setInsCCTV(ControlsUtility.getSelectedRadioText(ans6));
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
                getSelectedOfficeData.setInsremarks(ans2.getText().toString());
                ans7edit = 2;
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
                                        List<SubListOffice> results = new ArrayList<SubListOffice>();
                                        results.add(getSelectedOfficeData);
                                        JSONObject datatoSycClass = utility.getOfficeSycData(results);
                                        new ExecuteSyncOperation().
                                                execute(new String[]{"http://nsdc.qci.org.in/api/CAAF/Offiec_Area.php",
                                                        datatoSycClass.toString(), "bnNkYzd0ZWNoaWVzYXBp"});
                                    } else {
                                        NSDCDBController controller = new NSDCDBController(context);
                                        boolean updation_status = controller.saveOfficeData(getSelectedOfficeData, "draft");
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
                        controller.saveOfficeData(getSelectedOfficeData, "complete");
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
        if (ans2edit == 2 && ans3edit == 2 && ans4edit == 2 && ans5edit == 2 && ans6edit == 2 && ans7edit == 2) {
            return true;
        } else {
            return false;
        }
    }
}
