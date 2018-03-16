package com.ss.nsdc.main;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ss.nsdc.R;
import com.ss.nsdc.adapter.GeneralDetailsImageAdapter;
import com.ss.nsdc.adapter.TechnicalImageAdapter;
import com.ss.nsdc.constant.AppConstants;
import com.ss.nsdc.dao.GeneralImageModel;
import com.ss.nsdc.dao.ITIDBController;
import com.ss.nsdc.dao.TechnicalImage;
import com.ss.nsdc.entity.Classroom;
import com.ss.nsdc.utility.DimensionUtils;
import com.ss.nsdc.utility.GPSTracker;
import com.ss.nsdc.utility.Utility;
import com.ss.nsdc.utility.Validator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.zelory.compressor.Compressor;

/**
 * Created by Radhika on 5/19/2017.
 */

public class TechnicalImageActivity extends BaseActivity {

    static int tcIndex;
    static int tcPosition;

    int groupcount;

    List<TechnicalImage> lstTechnicalToBeSync= new ArrayList<>();
    Activity currentActivity;
    List<TechnicalImage> technical = null;
    String responseCode = null;
    JSONObject responseObj = null;

    Map<String, List<TechnicalImage>> technicalMap;
    List<String> headers;
    String yearWiseCollegeId = null;
    String applicationId = null;

    //ListView lstClassroom;
    ExpandableListView lstTechnical;
    Button btnClassroomsSubmit;
    JSONObject dateTimeObj;
    String serverDateTime;
    String latitude;
    String longitude;

    GPSTracker gps;
    //ClassroomAdapter adapter;
    TechnicalImageAdapter adapter;
    int index;
    int imagePosition;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private static final int CONTENT_REQUEST = 1337;
    private String userChoosenTask;
    ImageView imageView = null;
    View customDialogView;
    View customDialogView1;
    Dialog customDialog;
    Button Cancel;
    String instituteId;
    ProgressDialog ringProgressDialog;
    TextView report,heading;

    EditText editRemark;
    Button buttonRemark;
    Button buttonCancel;
    TextView textRemarkType;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technical);

        currentActivity = TechnicalImageActivity.this;
        yearWiseCollegeId = getIntent().getStringExtra("yearWiseCollegeId");

        GetTechnical technical = new GetTechnical();
        technical.execute();

        new  GetDateandTime().execute();

        lstTechnical = (ExpandableListView)findViewById(R.id.lstTechnicalDetail);

        boolean result = Utility.checkLocationPermission(currentActivity);
        if (result) {
            gps = GPSTracker.getInstance(getApplicationContext());
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.sub_cat_toolbar);
        setSupportActionBar(toolbar); //
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sub Category Listing");
        actionBar.setTitle("Technical Staff Document");

        initDialogViews();
    }

    public void saveClassrooms(int jobIndex, int imagePos) {
        if (Validator.isNetworkAvailable(currentActivity)) {
            tcIndex = jobIndex;
            tcPosition = imagePos;


            ITIDBController uploadImagesDAO = new ITIDBController(currentActivity);
            lstTechnicalToBeSync = uploadImagesDAO.getUnsavedTechnicalImageInfobyYearwiseCollegeId(yearWiseCollegeId,
                    technicalMap.get(headers.get(jobIndex)).get(imagePos).getRefId(),
                    technicalMap.get(headers.get(jobIndex)).get(imagePos).getDocId());
            uploadImagesDAO.close();

            if (lstTechnicalToBeSync != null && lstTechnicalToBeSync.size() > 0) {
                SaveTechnical classrooms = new SaveTechnical();
                classrooms.execute(String.valueOf(jobIndex));
            } else {
                alert(currentActivity, getString(R.string.alert), getString(R.string.no_image_upload), getString(R.string.ok), getString(R.string.cancel), false, false, null);
            }

//         if(technicalMap.get(headers.get(jobIndex)).get(imagePos).getProcTracker()==2)
//         {
//             lstTechnicalToBeSync.clear();
//             TechnicalImage technicalImage= technicalMap.get(headers.get(jobIndex)).get(imagePos);
//             lstTechnicalToBeSync.add(technicalImage);
//             SaveTechnical classrooms = new SaveTechnical();
//             classrooms.execute(String.valueOf(jobIndex));
//         }else {
//             alert(currentActivity, getString(R.string.alert), getString(R.string.no_image_upload), getString(R.string.ok), getString(R.string.cancel), false, false, null);
//         }
//

        } else {
            alert(currentActivity, getString(R.string.alert), getString(R.string.messageNetwork), getString(R.string.ok), getString(R.string.cancel), false, false, null);
        }
    }

    private boolean isRemark() {
        editRemark.setError(null);
        if (editRemark.getText().toString().isEmpty()) {
            editRemark.setError(getResources().getString(R.string.labelEmptyRemark));
            return false;
        }
        return true;
    }

    public void initDialogViews() {
        customDialogView = LayoutInflater.from(currentActivity).inflate(R.layout.dialog_remark, null, false);

        editRemark = (EditText) customDialogView.findViewById(R.id.editRemark);
        buttonRemark = (Button) customDialogView.findViewById(R.id.buttonRemark);
        buttonCancel = (Button) customDialogView.findViewById(R.id.buttonCancel);
        textRemarkType = (TextView) customDialogView.findViewById(R.id.textRemarkType);

        buttonRemark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isRemark()) {
                    technicalMap.get(headers.get(index)).get(imagePosition).setIsRemarkChanged(true);
                    technicalMap.get(headers.get(index)).get(imagePosition).setRemarks(editRemark.getText().toString());
                    technicalMap.get(headers.get(index)).get(imagePosition).setProcTracker(2);

                    ITIDBController imagesDAO = new ITIDBController(currentActivity);
                    imagesDAO.updateTechnicalDetails(technicalMap.get(headers.get(index)).get(imagePosition));
                    imagesDAO.close();
                    cancelCustomDialog();
                }
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelCustomDialog();
            }
        });
    }

    public void createRemarkDialog(int jobIndex, int imagePos) {
        index = jobIndex;
        imagePosition = imagePos;
        creatingDialog(currentActivity, false, false, customDialogView, 220, 300, false);

        if (technicalMap.get(headers.get(index)).get(imagePosition).getRemarks() != null
                && !technicalMap.get(headers.get(index)).get(imagePosition).getRemarks().equals("null")) {
            editRemark.setText(technicalMap.get(headers.get(index)).get(imagePosition).getRemarks());
        } else {
            editRemark.setText("");
        }
        textRemarkType.setText(technicalMap.get(headers.get(index)).get(imagePosition).getPictureType());
    }

    public void changeNC(int groupPosition,int childPosition, int ncValue)
    {

        //OrgDetails.get(index).setIsRemarkChanged(true);
      //  landDetails.get(position).setNc(ncValue);
     //   landDetails.get(position).setProcTracker(2);
//
     /*   ITIDBController imagesDAO = new ITIDBController(currentActivity);
        imagesDAO.updateLandDetails(landDetails.get(position));
        imagesDAO.close();
        cancelCustomDialog();*/


      //  technicalMap.get(headers.get(index)).get(imagePosition).setIsRemarkChanged(true);
        technicalMap.get(headers.get(groupPosition)).get(childPosition).setNc(ncValue);
        technicalMap.get(headers.get(groupPosition)).get(childPosition).setProcTracker(2);

        ITIDBController imagesDAO = new ITIDBController(currentActivity);
        imagesDAO.updateTechnicalDetails(technicalMap.get(headers.get(groupPosition)).get(childPosition));
        imagesDAO.close();


        adapter.notifyDataSetChanged();
    }



    public boolean isMandatoryFields() {
        boolean isAnyImageChanged = false;
        for (TechnicalImage classroom : technical) {
            if (classroom == null) {
                continue;
            }

            if (classroom.isImageChanged()) {
                isAnyImageChanged = true;
                break;
            }
        }

        if (isAnyImageChanged) {
            return true;
        } else {
            return false;
        }
    }

    public void selectImage(ImageView imgView, int jobIndex, int imagePos) {
        imageView = imgView;
        index = jobIndex;
        imagePosition = imagePos;

        if (gps == null) {
            Utility.showLocationSettingsAlertInMarshmallow(currentActivity);

        } else {
            if (!gps.isGPS()) {
                Utility.showGPSSettingsAlert(currentActivity);

            } else {
                if (gps.getLocation() != null || gps.getLatitude() != 0.0 || gps.getLongitude() != 0.0) {
                    latitude = String.valueOf(gps.getLatitude());
                    longitude = String.valueOf(gps.getLongitude());

                    Resources res = currentActivity.getResources();
                    final String[] items = res.getStringArray(R.array.upload_image_actions);

                    AlertDialog.Builder builder = new AlertDialog.Builder(currentActivity);
                    builder.setTitle("Add Photo!");
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int item) {
                            boolean result = Utility.checkPermission(currentActivity);

                            if (items[item].equals("Take Photo")) {
                                userChoosenTask = "Take Photo";
                                if (result)
                                    cameraIntent();

                            } else if (items[item].equals("Choose from Library")) {
                                userChoosenTask = "Choose from Library";
                                if (result)
                                    galleryIntent();

                            } else if (items[item].equals("Cancel")) {
                                dialog.dismiss();
                            }
                        }
                    });
                    builder.show();

                } else {
                    Toast.makeText(currentActivity, "Location Unavailable Please wait...", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(Utility.getTempFile(this, "tech_image_" + index + "_" + imagePosition)));
        startActivityForResult(intent, CONTENT_REQUEST);
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        currentActivity.startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
            case Utility.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    gps = GPSTracker.getInstance(getApplicationContext());
                } else {
                    Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access location data.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(currentActivity)
                .setTitle("Confirmation")
                .setMessage(R.string.alert_pending_changes)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(R.string.submit_later, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        TechnicalImageActivity.this.finish();
                    }
                })
                .setNegativeButton(R.string.no, null).show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                //onSelectFromGalleryResult(data);
            } else if (requestCode == CONTENT_REQUEST)
                onCaptureImageResult(data);

//            Intent intent = new Intent(currentActivity, TechnicalImageActivity.class);
//            intent.putExtra("yearWiseCollegeId", yearWiseCollegeId);
//            currentActivity.startActivity(intent);
//            finish();


        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap captureBmp = null;
        final File file = Utility.getTempFile(this, "tech_image_" + index + "_" + imagePosition);

           captureBmp = Compressor.getDefault(this).compressToBitmap(file);

        technicalMap.get(headers.get(index)).get(imagePosition).setIsImageChanged(true);
        //classrooms.get(index).setIsImageChanged(true);

        if (!captureBmp.isMutable()) {
            captureBmp = Utility.convertToMutable(currentActivity, captureBmp);
        }

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        String text = serverDateTime + " " + latitude + " " + longitude;
        Canvas c = new Canvas(captureBmp);
        c.drawBitmap(captureBmp, 0, 0, null);

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(AppConstants.DATE_TIME_TEXT_SIZE);
        c.drawRect(0, c.getHeight() - 60, c.getWidth(), c.getHeight(), paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        paint.setAlpha(0);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        c.drawText(text, 0, c.getHeight() - 15, paint);

        captureBmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        technicalMap.get(headers.get(index)).get(imagePosition).setPhoto(Base64.encodeToString(bytes.toByteArray(), Base64.DEFAULT));
        technicalMap.get(headers.get(index)).get(imagePosition).setProcTracker(2);
        //classrooms.get(index).setImage(Base64.encodeToString(bytes.toByteArray(), Base64.DEFAULT));

        ITIDBController imagesDAO = new ITIDBController(currentActivity);
        imagesDAO.updateTechnicalDetails(technicalMap.get(headers.get(index)).get(imagePosition));
        imagesDAO.close();

        imageView.setImageBitmap(captureBmp);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        adapter.notifyDataSetChanged();
    }

    public void emptyImageProp() {
        List<TechnicalImage> classroomList = technicalMap.get(headers.get(index));
        if (classroomList == null) {
            return;
        }

        for (TechnicalImage classroom : classroomList) {
            if (classroom == null) {
                continue;
            }
            classroom.setPhoto(null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sub_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.category_list:
                new AlertDialog.Builder(currentActivity)
                        .setTitle("Confirmation")
                        .setMessage(R.string.alert_pending_changes)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(R.string.submit_later, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Intent intent = new Intent(currentActivity, CategoryActivity.class);
                                intent.putExtra("YearWiseCollegeId", yearWiseCollegeId);
                                intent.putExtra("applicationNo", applicationId);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                currentActivity.startActivity(intent);
                            }
                        }).setNegativeButton(android.R.string.ok, null).show();

                return true;

            case R.id.institute_list:

                new AlertDialog.Builder(currentActivity)
                        .setTitle("Confirmation")
                        .setMessage(R.string.alert_pending_changes)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(R.string.submit_later, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Intent intent1 = new Intent(currentActivity, InstituteActivity.class);
                                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                currentActivity.startActivity(intent1);
                            }
                        }).setNegativeButton(android.R.string.ok, null).show();

                return true;

            case R.id.menuExit:

                new AlertDialog.Builder(currentActivity)
                        .setTitle("Confirmation")
                        .setMessage(R.string.alert_pending_changes)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(R.string.submit_later, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Intent intent2 = new Intent(Intent.ACTION_MAIN);
                                intent2.addCategory(Intent.CATEGORY_HOME);
                                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                currentActivity.startActivity(intent2);
                            }
                        }).setNegativeButton(android.R.string.ok, null).show();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private class GetTechnical extends AsyncTask<String, String, Boolean>  {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog(currentActivity, "Loading...", "Please Wait..", false, true);
        }

        @Override
        protected Boolean doInBackground(String... params) {
            ITIDBController uploadImageDAO = new ITIDBController(currentActivity);
            technical = uploadImageDAO.getTechnicalImageInfobyYearwiseCollegeId(yearWiseCollegeId);
            uploadImageDAO.close();
            return true;
        }

        @Override
        protected void onPostExecute(Boolean response) {
            super.onPostExecute(response);
            cancelProgressDialog();

            if (response != null && response) {
                if (technical != null) {
                    List<TechnicalImage> technicalImageList;
                    technicalMap = new HashMap<>();
                    headers = new ArrayList<>();
                    for (TechnicalImage technicalList : technical) {
                        if (technicalList == null) {
                            continue;
                        }

                        String name = technicalList.getName();

                        if (technicalList.getPhoto() != null
                                && !technicalList.getPhoto().equals("")
                                && !technicalList.getPhoto().equals("null")) {
                            String imageUrl = technicalList.getPhoto();
                            //imageUrl = imageUrl.substring(imageUrl.lastIndexOf("/") + 1, imageUrl.length());
                            technicalList.setPhoto(imageUrl);
                        }

                        if (technicalMap.containsKey(name)) {
                            technicalImageList = technicalMap.get(name);
                            technicalImageList.add(technicalList);
                        } else {
                            headers.add(name);
                            technicalImageList = new ArrayList<>();
                            technicalImageList.add(technicalList);
                            technicalMap.put(name, technicalImageList);
                        }
                    }
                    //adapter = new ClassroomAdapter(currentActivity, classrooms);
                    adapter = new TechnicalImageAdapter(currentActivity, headers, technicalMap);
                    lstTechnical.setAdapter(adapter);

                    groupcount = lstTechnical.getCount();

                    for (int i = 0;i< groupcount;i++){

                        lstTechnical.expandGroup(i);
                    }

                }
            } else {

            }
        }
    }

    private class SaveTechnical extends AsyncTask<String, String, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog(currentActivity, "Loading...", "Please Wait..", false, true);
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            String response = "";
            JSONObject responseJSON = null;
            try {
                JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < lstTechnicalToBeSync.size(); i++) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("docId", lstTechnicalToBeSync.get(i).getDocId());
                    jsonObject.put("name", lstTechnicalToBeSync.get(i).getName());
                    jsonObject.put("photo", lstTechnicalToBeSync.get(i).getPhoto());
                    jsonObject.put("remarks", lstTechnicalToBeSync.get(i).getRemarks());
                    jsonObject.put("Latitude", lstTechnicalToBeSync.get(i).getLatitude());
                    jsonObject.put("Longitude", lstTechnicalToBeSync.get(i).getLongitude());
                    jsonObject.put("nc", lstTechnicalToBeSync.get(i).getNc());
                    jsonObject.put("refId", lstTechnicalToBeSync.get(i).getRefId());

                    jsonArray.put(jsonObject);
                }

                JSONObject GeneralDetailsJsonObj = new JSONObject();
                GeneralDetailsJsonObj.put("TechnicalStaffSDoc", jsonArray);
                GeneralDetailsJsonObj.put("yearWiseCollegeID", yearWiseCollegeId);

                JSONObject payloadJsonObj = new JSONObject();
                payloadJsonObj.put("payload", GeneralDetailsJsonObj);

                JSONObject resultJsonObj = new JSONObject();
                resultJsonObj.put("result", payloadJsonObj);


                URL url = new URL(AppConstants.URL_TECHNICALSTAFF_IMAGE_UPLOAD);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //  conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json");
                //   conn.setFixedLengthStreamingMode(params[0].getBytes().length);
                String str = resultJsonObj.toString();
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
                }
                responseJSON = new JSONObject(response);

            }catch (Exception e) {
                Log.e("ERROR_SAVE_FACILITIES", e.toString());
            }

            return responseJSON;
        }

        @Override
        protected void onPostExecute(JSONObject responseCode) {
            super.onPostExecute(responseCode);
            if (responseCode != null && responseCode.optString("status").equals("0")) {
                ITIDBController uploadImageDAO = new ITIDBController(currentActivity);
                boolean bolResult = uploadImageDAO.updateTechnicalProcTracker(lstTechnicalToBeSync, yearWiseCollegeId);
                uploadImageDAO.close();

                if (bolResult) {
                    cancelProgressDialog();
                    emptyImageProp();

                    Toast.makeText(currentActivity, getString(R.string.alert_tech_saved_successfully), Toast.LENGTH_LONG).show();
//                    Intent i = new Intent(currentActivity, PhotoCategoriesActivity.class);
//                    i.putExtra("Category", AppConstants.TEXT_UPLOAD_IMAGES);
//                    i.putExtra("YearWiseCollegeId", yearWiseCollegeId);
//                    i.putExtra("applicationId", applicationId);
//                    startActivity(i);

                    GetTechnical getClassrooms = new GetTechnical();
                    getClassrooms.execute();

                } else {
                    cancelProgressDialog();
                    alert(TechnicalImageActivity.this, "", getString(R.string.alert_network_error), getString(R.string.ok), getString(R.string.cancel), false, false);
                }

            } else {
                cancelProgressDialog();
                alert(TechnicalImageActivity.this, "", getString(R.string.alert_network_error), getString(R.string.ok), getString(R.string.cancel), false, false);
            }
        }
    }

    class GetDateandTime extends AsyncTask<String, Integer, JSONObject> {


        @Override
        protected JSONObject doInBackground(String... params) {
            String response = "";
            JSONObject responseJSON = null;

            try {

                URL url = new URL(AppConstants.URL_DATE_TIME);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                String str = "";
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
                }
                responseJSON = new JSONObject(response);

            } catch (Exception ex) {
                Log.e("DOWNLOAD_CD_IMAGE", ex.toString());
            }

            return responseJSON;
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);

            try {

                int status = result.optInt("status");
                if(status == 0){
                    JSONObject payload = result.getJSONObject("payload");
                    JSONObject date = payload.getJSONObject("Datetime");
                    String Datetime = date.optString("Datetime");
                    serverDateTime = Datetime;
                    Log.e("time",serverDateTime);

                }else {
                    Toast.makeText(TechnicalImageActivity.this,"Unable to get the Date and Time",Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
