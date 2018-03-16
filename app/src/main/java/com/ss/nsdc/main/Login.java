package com.ss.nsdc.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*import com.crashlytics.android.Crashlytics;*/
import com.facebook.stetho.Stetho;
import com.ss.nsdc.R;
import com.ss.nsdc.constant.AppConstants;
import com.ss.nsdc.dao.ITIDBController;
import com.ss.nsdc.dao.Institute;
import com.ss.nsdc.dao.User;
import com.ss.nsdc.utility.UtilityService;

/*import io.fabric.sdk.android.Fabric;*/

public class Login extends Activity {


    Button loginButton;
    EditText username;
    EditText password;
    final Context context = this;
    ProgressDialog ringProgressDialog;
    UtilityService utilityService = UtilityService.getInstance();
    User user;
    String currDate;
    String latitude;
    String longitude;

    //  GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Fabric.with(this, new Crashlytics());

        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.input_name);
        password = (EditText) findViewById(R.id.input_password);


       /* boolean result = Utility.checkLocationPermission(Login.this);*/
      /*  if (result) {
            gps = GPSTracker.getInstance(getApplicationContext());
        }

        if (gps == null) {
            Utility.showLocationSettingsAlertInMarshmallow(Login.this);
        } else {
            if (!gps.isGPS()) {
                showAppRestartAlert(Login.this);
                // Utility.showGPSSettingsAlert(Login.this);

            } else {
                if (gps.getLocation() != null || gps.getLatitude() != 0.0 || gps.getLongitude() != 0.0) {


                } else {
                    showAppRestartAlert(Login.this);
                    //Toast.makeText(Login.this, "Location Unavailable Please wait...", Toast.LENGTH_LONG).show();
                }
            }
        }
*/

        loginButton = (Button) findViewById(R.id.btn_signup);
        getLoggedUserFromDB();
        loginButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (username.getText().toString().equalsIgnoreCase("") && password.getText().toString().equalsIgnoreCase("")) {

                    Toast.makeText(context, "Please Enter the valid UserName and Password", Toast.LENGTH_SHORT).show();
                } else {
                    if (user == null) {
                        user = new User();
                        user.setUsername(username.getText().toString());
                        user.setPassword(password.getText().toString());
                        validateUserOnline(user);
                    } else {
                        login_successfull();
                    }
                }


            }
        });

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date dateobj = new Date();
        currDate = df.format(dateobj);


        Stetho.InitializerBuilder initializerBuilder =
                Stetho.newInitializerBuilder(getApplicationContext());
        // Enable Chrome DevTools
        initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(getApplicationContext())
        );
        // Enable command line interface
        initializerBuilder.enableDumpapp(
                Stetho.defaultDumperPluginsProvider(getApplicationContext())
        );
        initializerBuilder.enableDumpapp(Stetho.defaultDumperPluginsProvider(this));
        // initializerBuilder.enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build());
        // Use the InitializerBuilder to generate an Initializer
        Stetho.Initializer initializer = initializerBuilder.build();
        // Initialize Stetho with the Initializer
        Stetho.initialize(initializer);

    }

    public void login_successfull() {
        ITIDBController sqlite = new ITIDBController(context);
        List<Institute> instituteList = sqlite.getInstituteList();
        if (instituteList != null && instituteList.size() > 0) {
            Intent intent = new Intent(context, WelcomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            new UpdateInstituteData().execute(user);
        }

    }

    private void getLoggedUserFromDB() {
        ITIDBController sqllite = new ITIDBController(context);
        user = sqllite.getLoggedUser();
        sqllite.close();

        //########## Setting logged USER
        if (user != null) {
            password.setText(user.getPassword());
            username.setText(user.getUsername());

            password.setEnabled(false);
            username.setEnabled(false);
        }
    }

    private void validateUserOnline(User newUser) {
        if (utilityService.getConnectivityStatus(context)) {

            new ExecuteLoginOperation().execute(newUser);
        } else {
            utilityService.showIntenetSettingsAlert(context);
            user = null;
        }

    }

    class ExecuteLoginOperation extends AsyncTask<User, Integer, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(context, android.R.style.Theme_Holo), null, "Logging from Server..", true);
            ringProgressDialog.setCancelable(false);
            ringProgressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected JSONObject doInBackground(User... user) {
            JSONObject response = validate_login(user);
            return response;
        }


        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);

            //ringProgressDialog.cancel();
            try {

                int status = result.optInt("status");
                Log.e("status", String.valueOf(status));

                if (status == 0) {
                    JSONObject userContext = result.optJSONObject("userContext");
                    String userId = userContext.optString("userId");
                    ITIDBController sqllite = new ITIDBController(context);
                    sqllite.addLoggedUser(user.getUsername(), user.getPassword(), Integer.parseInt(userId));
                    user = sqllite.getLoggedUser();
                    sqllite.close();
                    ringProgressDialog.cancel();
                    login_successfull();
                } else {
                    user = null;
                    ringProgressDialog.cancel();
                    Toast.makeText(context, "Invalid Username/Password", Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public JSONObject validate_login(User... user) {
        String response = "";
        JSONObject response_json = null;
        try {
            URL url = new URL(AppConstants.URL_LOGIN);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            String str = "username=" + user[0].getUsername() + "&password=" + user[0].getPassword();
            byte[] outputInBytes = str.getBytes("UTF-8");
            conn.getOutputStream().write(outputInBytes);
            Log.e("stringResults", str);
            conn.connect();
            int responsecode = conn.getResponseCode();
            Log.e("response", String.valueOf(responsecode));
            if (responsecode == HttpURLConnection.HTTP_OK) {
                Log.e("ok", "ok");
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else if (responsecode == HttpURLConnection.HTTP_CLIENT_TIMEOUT) {
                Log.e("timeout", "timeout");
                response_json = new JSONObject();
                response_json.put("error", "Connection to server lost..");
            }

            response_json = new JSONObject(response);
            Log.e("response", response_json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response_json;
    }

    public JSONObject updateInstituteList(User... user) {
        String response = "";
        JSONObject response_json = null;
        try {
            URL url = new URL(AppConstants.URL_ALLOCATION);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            String str = "UserId=" + user[0].getUserid();
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
            }
            response_json = new JSONObject(response);
        } catch (Exception e) {
            e.printStackTrace();
            return response_json;
        }
        return response_json;
    }

    class UpdateInstituteData extends AsyncTask<User, Integer, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(context, android.R.style.Theme_Holo), null, "Downloading Allocations..", true);
            ringProgressDialog.setCancelable(false);
            ringProgressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected JSONObject doInBackground(User... user) {
            JSONObject response = updateInstituteList(user);
            return response;

        }

        @Override
        protected void onPostExecute(JSONObject result) {
            try {
                int status = result.optInt("status");
                if (status == 0) {
                    ITIDBController sqllite = new ITIDBController(context);
                    boolean add_status = sqllite.addInstituteData(result);
                    sqllite.close();
                    ringProgressDialog.cancel();
                    if (add_status) {
                        //Toast.makeText(context,"Allocation Updated Successfully", Toast.LENGTH_LONG).show();
                        login_successfull();
                    }
                } else {
                    user = null;
                    ringProgressDialog.cancel();
                    Toast.makeText(context, "No Allocation Data Found..", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Log.e("Login", "Allocation Data Failed..");
            }

        }
    }

/*
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {

            case Utility.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    gps = GPSTracker.getInstance(getApplicationContext());
                } else {
                    showAppRestartAlert(Login.this);
                    //   Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access location data.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }*/


    public static void showAppRestartAlert(final Activity activity) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setTitle("Location Unavailable");
        alertDialog.setMessage("Location unavailable in the app. Restart your app for change in location settings to take effect.");

        // On pressing Settings button
        alertDialog.setPositiveButton("CLOSE APP", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

}