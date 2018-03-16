package com.ss.nsdc.main;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ss.nsdc.R;
import com.ss.nsdc.adapter.GeneralDetailsImageAdapter;
import com.ss.nsdc.adapter.LandImageAdapter;
import com.ss.nsdc.constant.AppConstants;
import com.ss.nsdc.dao.GeneralImageModel;
import com.ss.nsdc.dao.ITIDBController;
import com.ss.nsdc.dao.LandImage;
import com.ss.nsdc.utility.GPSTracker;
import com.ss.nsdc.utility.Utility;
import com.ss.nsdc.utility.Validator;

import org.json.JSONArray;
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
import java.util.List;

import id.zelory.compressor.Compressor;

/**
 * Created by Radhika on 5/19/2017.
 */

public class LandBuildingDetails extends BaseActivity {

    static int tcIndex;
    ListView lstLandDetail;
    List<LandImage> landDetails;
    List<LandImage> lstLandToBeSync;

    String yearWiseCollegeId = null;
    LandImageAdapter adapter;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    ImageView imageView = null;
    int index;

    Activity currentActivity;
    String responseCode = null;
    JSONObject responseObj = null;

    View customDialogView;
    EditText editRemark;
    Button buttonRemark;
    Button buttonCancel;
    TextView textRemarkType;

    JSONObject dateTimeObj;
    String serverDateTime;
    String latitude;
    String longitude;

    GPSTracker gps;
    private static final int CONTENT_REQUEST = 1337;
    private File output = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land);

        new GetDateandTime().execute();

        Toolbar toolbar = (Toolbar) findViewById(R.id.sub_cat_toolbar);
        setSupportActionBar(toolbar); //
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sub Category Listing");

        actionBar.setTitle("Land and Building Details");

        currentActivity = LandBuildingDetails.this;
        yearWiseCollegeId = getIntent().getStringExtra("yearWiseCollegeId");

        GetLandImageActivity generalDetailImageActivity = new GetLandImageActivity();
        generalDetailImageActivity.execute();

        lstLandDetail = (ListView) findViewById(R.id.lstLandDetail);

        boolean result = Utility.checkLocationPermission(currentActivity);
        if (result) {
            gps = GPSTracker.getInstance(getApplicationContext());
        }

        initDialogViews();
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
                    landDetails.get(index).setIsRemarkChanged(true);
                    landDetails.get(index).setRemarks(editRemark.getText().toString());
                    landDetails.get(index).setProcTracker(2);

                    ITIDBController imagesDAO = new ITIDBController(currentActivity);
                    imagesDAO.updateLandDetails(landDetails.get(index));
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

    private boolean isRemark() {
        editRemark.setError(null);
        if (editRemark.getText().toString().isEmpty()) {
            editRemark.setError(getResources().getString(R.string.labelEmptyRemark));
            return false;
        }
        return true;
    }

    public void createRemarkDialog(int jobIndex) {
        index = jobIndex;
        creatingDialog(currentActivity, false, false, customDialogView, 220, 300, false);

        if (landDetails.get(index).getRemarks() != null
                && !landDetails.get(index).getRemarks().equals("null")) {
            editRemark.setText(landDetails.get(index).getRemarks());
        } else {
            editRemark.setText("");
        }
        textRemarkType.setText(landDetails.get(index).getPictureType());
    }

    public void saveGeneralDetails(int jobIndex) {
        if(Validator.isNetworkAvailable(currentActivity)){
            tcIndex = jobIndex;
            ITIDBController controller = new ITIDBController(currentActivity);
            lstLandToBeSync = controller.getUnsavedLandImageInfobyYearwiseCollegeId(yearWiseCollegeId,landDetails.get(jobIndex).getDocId());
            controller.close();

            if (lstLandToBeSync != null && lstLandToBeSync.size() > 0) {
                SaveLandImages saveGeneralImages = new SaveLandImages();
                saveGeneralImages.execute(String.valueOf(jobIndex));
            } else {
                alert(currentActivity, getString(R.string.alert), getString(R.string.no_image_upload), getString(R.string.ok), getString(R.string.cancel), false, false, null);
            }


        }else {
            alert(currentActivity, getString(R.string.alert), getString(R.string.messageNetwork), getString(R.string.ok), getString(R.string.cancel), false, false, null);
        }
    }

    public void selectImage(ImageView imgView, int jobIndex) {
        imageView = imgView;
        index = jobIndex;

        if (gps == null) {
            Utility.showLocationSettingsAlertInMarshmallow(currentActivity);
        } else {
            if (!gps.isGPS()) {
                Utility.showGPSSettingsAlert(currentActivity);

            } else {
                if(gps.getLocation()!=null || gps.getLatitude()!=0.0 ||gps.getLongitude()!=0.0){
                    latitude = String.valueOf(gps.getLatitude());
                    longitude = String.valueOf(gps.getLongitude());

                    Resources res = getResources();
                    final String[] items = res.getStringArray(R.array.upload_image_actions);

                    AlertDialog.Builder builder = new AlertDialog.Builder(currentActivity);
                    builder.setTitle("Add Photo!");
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int item) {
                            boolean result = Utility.checkPermission(currentActivity);

                            if (items[item].equals("Take Photo")) {
                                userChoosenTask = "Take Photo";
                                if(result)
                                    cameraIntent();

                            } else if (items[item].equals("Choose from Library")) {
                                userChoosenTask = "Choose from Library";
                                if(result)
                                    galleryIntent();

                            } else if (items[item].equals("Cancel")) {
                                dialog.dismiss();
                            }
                        }
                    });
                    builder.show();

                } else {
                    Toast.makeText(currentActivity, getString(R.string.alert_unable_to_get_location), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivityForResult(intent, REQUEST_CAMERA);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(Utility.getTempFile(this, "land_image_" + index)));
        startActivityForResult(intent, CONTENT_REQUEST);
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;

            case Utility.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    gps = GPSTracker.getInstance(getApplicationContext());
                } else {
                    Toast.makeText(getApplicationContext(),"Permission Denied, You cannot access location data.",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                //onSelectFromGalleryResult(data);
            } else if (requestCode == CONTENT_REQUEST)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap captureBmp = null;
        final File file = Utility.getTempFile(this, "land_image_" + index);
        captureBmp = Compressor.getDefault(this).compressToBitmap(file);

        landDetails.get(index).setIsImageChanged(true);

        if (!captureBmp.isMutable()) {
            captureBmp = Utility.convertToMutable(currentActivity, captureBmp);
        }

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        String text = serverDateTime+ ""+ latitude + " " + longitude;
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

//        if(landDetails.get(index).getPictureType().equalsIgnoreCase("Building Plan Of Institute Part 1")){
//            captureBmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//
//        }else if(landDetails.get(index).getPictureType().equalsIgnoreCase("Building Plan Of Institute Part 2")){
//            captureBmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        }else if(landDetails.get(index).getPictureType().equalsIgnoreCase("Building Plan Of Institute Part 3")){
//            captureBmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        }else if(landDetails.get(index).getPictureType().equalsIgnoreCase("Building Plan Of Institute Part 4")){
//            captureBmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        }else if(landDetails.get(index).getPictureType().equalsIgnoreCase("Building Plan Of Institute Part 5")){
//            captureBmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        }else if(landDetails.get(index).getPictureType().equalsIgnoreCase("Photograph of Bill of Lift (With TIN No.)")){
//            captureBmp.compress(Bitmap.CompressFormat.JPEG, 20, bytes);
//        }else if(landDetails.get(index).getPictureType().equalsIgnoreCase("Photograph of Installation Certificate")){
//            captureBmp.compress(Bitmap.CompressFormat.JPEG, 20, bytes);
//        }else if(landDetails.get(index).getPictureType().equalsIgnoreCase("Photograph of view from the approach road towards the ITI")){
//            captureBmp.compress(Bitmap.CompressFormat.JPEG, 20, bytes);
//        }else if(landDetails.get(index).getPictureType().equalsIgnoreCase("Photograph from entrance towards the main building of the ITI")){
//            captureBmp.compress(Bitmap.CompressFormat.JPEG, 20, bytes);
//        }else if(landDetails.get(index).getPictureType().equalsIgnoreCase("Photograph of Male Washroom")){
//            captureBmp.compress(Bitmap.CompressFormat.JPEG, 20, bytes);
//        }else if(landDetails.get(index).getPictureType().equalsIgnoreCase("Photograph of Female Washroom")){
//            captureBmp.compress(Bitmap.CompressFormat.JPEG, 20, bytes);
//        }else if(landDetails.get(index).getPictureType().equalsIgnoreCase("Photograph of Water availability")){
//            captureBmp.compress(Bitmap.CompressFormat.JPEG, 20, bytes);
//        }else if(landDetails.get(index).getPictureType().equalsIgnoreCase("Photograph of Fire extinguisher")){
//            captureBmp.compress(Bitmap.CompressFormat.JPEG, 20, bytes);
//        }else if(landDetails.get(index).getPictureType().equalsIgnoreCase("Photograph of placement cell")){
//            captureBmp.compress(Bitmap.CompressFormat.JPEG, 20, bytes);
//        }else {
            captureBmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        }


        landDetails.get(index).setPhoto(Base64.encodeToString(bytes.toByteArray(), Base64.DEFAULT));
        landDetails.get(index).setProcTracker(2);

        ITIDBController imagesDAO = new ITIDBController(currentActivity);
        imagesDAO.updateLandDetails(landDetails.get(index));
        imagesDAO.close();

        imageView.setImageBitmap(captureBmp);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        adapter.notifyDataSetChanged();
    }

    public void emptyImageProp() {
        if (landDetails == null) {
            return;
        }

        for (LandImage facility : landDetails) {
            if (facility == null) {
                continue;
            }
            facility.setPhoto(null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sub_category, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(currentActivity)
                .setTitle("Confirmation")
                .setMessage(R.string.alert_pending_changes)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(R.string.submit_later, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        LandBuildingDetails.this.finish();
                    }
                })
                .setNegativeButton(R.string.no, null).show();
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

    public class GetLandImageActivity extends AsyncTask<String, String, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog(currentActivity, "Loading...", "Please Wait..", false, true);
        }

        @Override
        protected Boolean doInBackground(String... params) {
            ITIDBController controller = new ITIDBController(currentActivity);
            landDetails = controller.getLandImageInfobyYearwiseCollegeId(yearWiseCollegeId);
            controller.close();
            return true;
        }

        @Override
        protected void onPostExecute(Boolean response) {
            super.onPostExecute(response);
            cancelProgressDialog();

            if (response != null && response) {
                if (landDetails != null) {
                    for (LandImage generalImageModel : landDetails) {
                        if (landDetails == null) {
                            continue;
                        }
                        if (generalImageModel.getPhoto() != null
                                && !generalImageModel.getPhoto().equals("")
                                && !generalImageModel.getPhoto().equals("null")) {
                            String imageUrl = generalImageModel.getPhoto();
                            // imageUrl = imageUrl.substring(imageUrl.lastIndexOf("/") + 1, imageUrl.length());
                            generalImageModel.setPhoto(imageUrl);
                        }
                    }

                    adapter = new LandImageAdapter(currentActivity, landDetails);
                    lstLandDetail.setAdapter(adapter);

                }

            }
        }
    }

    private class SaveLandImages extends AsyncTask<String, String, JSONObject> {

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
                for (int i = 0; i < lstLandToBeSync.size(); i++) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("docId", lstLandToBeSync.get(i).getDocId());
                    jsonObject.put("name", lstLandToBeSync.get(i).getName());
                    jsonObject.put("photo", lstLandToBeSync.get(i).getPhoto());
                    jsonObject.put("remarks", lstLandToBeSync.get(i).getRemarks());
                    jsonObject.put("Latitude", lstLandToBeSync.get(i).getLatitude());
                    jsonObject.put("Longitude", lstLandToBeSync.get(i).getLongitude());
                    jsonObject.put("nc", lstLandToBeSync.get(i).getNc());
                    jsonObject.put("refId", lstLandToBeSync.get(i).getRefId());

                    jsonArray.put(jsonObject);
                }

                JSONObject GeneralDetailsJsonObj = new JSONObject();
                GeneralDetailsJsonObj.put("LandAndBuildingDetails", jsonArray);
                GeneralDetailsJsonObj.put("yearWiseCollegeID", yearWiseCollegeId);

                JSONObject payloadJsonObj = new JSONObject();
                payloadJsonObj.put("payload", GeneralDetailsJsonObj);

                JSONObject resultJsonObj = new JSONObject();
                resultJsonObj.put("result", payloadJsonObj);


                URL url = new URL(AppConstants.URL_LANDBUILDING_IMAGE_UPLOAD);
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
                boolean bolResult = uploadImageDAO.updateLandProcTracker(lstLandToBeSync, yearWiseCollegeId);
                uploadImageDAO.close();

                if (bolResult) {
                    cancelProgressDialog();
                    emptyImageProp();

                    Toast.makeText(currentActivity, getString(R.string.alert_land_saved_successfully), Toast.LENGTH_LONG).show();

                    GetLandImageActivity generalDetailImageActivity = new GetLandImageActivity();
                    generalDetailImageActivity.execute();

                }
            } else if (responseCode != null && responseCode.equals("1")) {
                cancelProgressDialog();
                alert(currentActivity, "", getString(R.string.alert_data_not_submitted), getString(R.string.ok), getString(R.string.cancel), false, false);
            } else {
                cancelProgressDialog();
                alert(currentActivity, "", getString(R.string.alert_network_error), getString(R.string.ok), getString(R.string.cancel), false, false);
            }
        }
    }


    public void changeNC(int position, int ncValue)
    {

        //OrgDetails.get(index).setIsRemarkChanged(true);
        landDetails.get(position).setNc(ncValue);
        landDetails.get(position).setProcTracker(2);

        ITIDBController imagesDAO = new ITIDBController(currentActivity);
        imagesDAO.updateLandDetails(landDetails.get(position));
        imagesDAO.close();
        cancelCustomDialog();

        adapter.notifyDataSetChanged();
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
                    Toast.makeText(LandBuildingDetails.this,"Unable to get the Date and Time",Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
