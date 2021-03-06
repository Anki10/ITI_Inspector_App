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
import com.ss.nsdc.constant.AppConstants;
import com.ss.nsdc.dao.GeneralImageModel;
import com.ss.nsdc.dao.ITIDBController;
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

public class GeneralDetailImageActivity extends BaseActivity {

    static int tcIndex;
    ListView lstGeneralDetail;
    List<GeneralImageModel> generalDetails;
    List<GeneralImageModel> lstGDToBeSync;

    String yearWiseCollegeId = null;
    GeneralDetailsImageAdapter adapter;

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
        setContentView(R.layout.activity_general_image);

        Toolbar toolbar = (Toolbar) findViewById(R.id.sub_cat_toolbar);
        setSupportActionBar(toolbar); //
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        new GetDateandTime().execute();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sub Category Listing");

        actionBar.setTitle("General Details");

        currentActivity = GeneralDetailImageActivity.this;
        yearWiseCollegeId = getIntent().getStringExtra("yearWiseCollegeId");

        GetGeneralDetailImageActivity generalDetailImageActivity = new GetGeneralDetailImageActivity();
        generalDetailImageActivity.execute();

        lstGeneralDetail = (ListView) findViewById(R.id.lstGeneralDetail);

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
                    generalDetails.get(index).setIsRemarkChanged(true);
                    generalDetails.get(index).setRemarks(editRemark.getText().toString());
                    generalDetails.get(index).setProcTracker(2);

                    ITIDBController imagesDAO = new ITIDBController(currentActivity);
                    imagesDAO.updateGeneralDetails(generalDetails.get(index));
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

        if (generalDetails.get(index).getRemarks() != null
                && !generalDetails.get(index).getRemarks().equals("null")) {
            editRemark.setText(generalDetails.get(index).getRemarks());
        } else {
            editRemark.setText("");
        }
        textRemarkType.setText(generalDetails.get(index).getPictureType());
    }

    public void saveGeneralDetails(int jobIndex) {
        if(Validator.isNetworkAvailable(currentActivity)){
            tcIndex = jobIndex;
            ITIDBController controller = new ITIDBController(currentActivity);
            lstGDToBeSync = controller.getUnsavedGeneralDetailsImageInfobyYearwiseCollegeId(yearWiseCollegeId,generalDetails.get(jobIndex).getDocId());
            controller.close();

            if (lstGDToBeSync != null && lstGDToBeSync.size() > 0) {
                SaveGeneralDetailsImages saveGeneralImages = new SaveGeneralDetailsImages();
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
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(Utility.getTempFile(this, "gen_image_" + index)));
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
        final File file = Utility.getTempFile(this, "gen_image_" + index);
        captureBmp = Compressor.getDefault(this).compressToBitmap(file);

        generalDetails.get(index).setIsImageChanged(true);

        if (!captureBmp.isMutable()) {
            captureBmp = Utility.convertToMutable(currentActivity, captureBmp);
        }

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        String text = serverDateTime + "" + latitude + " " + longitude;
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

//        if(generalDetails.get(index).getPictureType().equalsIgnoreCase("Mandatory Assessor Selfie")){
//            captureBmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        }else if(generalDetails.get(index).getPictureType().equalsIgnoreCase("Photograph of Bill of Biometric device")){
//            captureBmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        } else if(generalDetails.get(index).getPictureType().equalsIgnoreCase("Photograph of Address Proof")){
//            captureBmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        }else {
            captureBmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        }
        generalDetails.get(index).setPhoto(Base64.encodeToString(bytes.toByteArray(), Base64.DEFAULT));
        generalDetails.get(index).setProcTracker(2);

        ITIDBController imagesDAO = new ITIDBController(currentActivity);
        imagesDAO.updateGeneralDetails(generalDetails.get(index));
        imagesDAO.close();

        imageView.setImageBitmap(captureBmp);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        adapter.notifyDataSetChanged();
    }

    public void emptyImageProp() {
        if (generalDetails == null) {
            return;
        }

        for (GeneralImageModel facility : generalDetails) {
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
                        GeneralDetailImageActivity.this.finish();
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

    public void changeNC(int position, int ncValue) {

        generalDetails.get(position).setNc(ncValue);
        generalDetails.get(position).setProcTracker(2);

        ITIDBController imagesDAO = new ITIDBController(currentActivity);
        imagesDAO.updateGeneralDetails(generalDetails.get(position));
        imagesDAO.close();
        cancelCustomDialog();

        adapter.notifyDataSetChanged();
    }

    public class GetGeneralDetailImageActivity extends AsyncTask<String, String, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog(currentActivity, "Loading...", "Please Wait..", false, true);
        }

        @Override
        protected Boolean doInBackground(String... params) {
            ITIDBController controller = new ITIDBController(currentActivity);
            generalDetails = controller.getGeneralDetailsImageInfobyYearwiseCollegeId(yearWiseCollegeId);
            controller.close();
            return true;
        }

        @Override
        protected void onPostExecute(Boolean response) {
            super.onPostExecute(response);
            cancelProgressDialog();

            if (response != null && response) {
                if (generalDetails != null) {
                    for (GeneralImageModel generalImageModel : generalDetails) {
                        if (generalDetails == null) {
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

                    adapter = new GeneralDetailsImageAdapter(currentActivity, generalDetails);
                    lstGeneralDetail.setAdapter(adapter);

                }

            }
        }
    }

    private class SaveGeneralDetailsImages extends AsyncTask<String, String, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog(currentActivity, "Loading...", "Please Wait..", false, true);
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            String response = "";
            JSONObject responseJSON = null;
            //   String data = null;
            try {

               /* List<Integer> lstIds = new ArrayList<>();
                List<String> lstImages = new ArrayList<String>();
                List<String> lstRemarks = new ArrayList<String>();

                for (GeneralImageModel general : lstGDToBeSync) {
                    if (general == null) {
                        continue;
                    }

                    lstIds.add(Integer.parseInt(String.valueOf(general.getDocId())));
                    lstImages.add(general.getPhoto());
                    lstRemarks.add(general.getRemarks());
                }

                int[] ids = new int[lstIds.size()];
                for (int i = 0; i < lstIds.size(); i++) {
                    ids[i] = lstIds.get(i);
                }
                String[] images = lstImages.toArray(new String[lstImages.size()]);
                String[] remarks = lstRemarks.toArray(new String[lstRemarks.size()]);

                String strIds = Arrays.toString(ids);
                strIds = strIds.replaceFirst("\\[", "(");
                strIds = strIds.replaceFirst("\\]", ")");

                String strImages = Arrays.toString(images);
                strImages = strImages.replaceFirst("\\[", "(");
                strImages = strImages.replaceFirst("\\]", ")");

                String strRemarks = Arrays.toString(remarks);
                strRemarks = strRemarks.replaceFirst("\\[", "(");
                strRemarks = strRemarks.replaceFirst("\\]", ")");*/

             /*   data = URLEncoder.encode(AppConstants.KEY_API_YEAR_WISE_COLLEGE_ID, "UTF-8")
                        + "=" + URLEncoder.encode(yearWiseCollegeId, "UTF-8");

                data += "&" + URLEncoder.encode(AppConstants.KEY_API_RESPONSE_CD_ID, "UTF-8")
                        + "=" + URLEncoder.encode(strIds, "UTF-8");

                data += "&" + URLEncoder.encode(AppConstants.KEY_API_RESPONSE_CD_PATH, "UTF-8")
                        + "=" + URLEncoder.encode(strImages, "UTF-8");

                data += "&" + URLEncoder.encode(AppConstants.KEY_API_RESPONSE_CD_REMARK, "UTF-8")
                        + "=" + URLEncoder.encode(strRemarks, "UTF-8");*/


                JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < lstGDToBeSync.size(); i++) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("docId", lstGDToBeSync.get(i).getDocId());
                    jsonObject.put("name", lstGDToBeSync.get(i).getName());
                    jsonObject.put("photo", lstGDToBeSync.get(i).getPhoto());
                    jsonObject.put("remarks", lstGDToBeSync.get(i).getRemarks());
                    jsonObject.put("Latitude", lstGDToBeSync.get(i).getLatitude());
                    jsonObject.put("Longitude", lstGDToBeSync.get(i).getLongitude());
                    jsonObject.put("nc",  lstGDToBeSync.get(i).getNc());
                    jsonObject.put("refId", lstGDToBeSync.get(i).getRefId());

                    jsonArray.put(jsonObject);
                }

                JSONObject GeneralDetailsJsonObj = new JSONObject();
                GeneralDetailsJsonObj.put("GeneralDetails", jsonArray);
                GeneralDetailsJsonObj.put("yearWiseCollegeID", yearWiseCollegeId);

                JSONObject payloadJsonObj = new JSONObject();
                payloadJsonObj.put("payload", GeneralDetailsJsonObj);

                JSONObject resultJsonObj = new JSONObject();
                resultJsonObj.put("result", payloadJsonObj);
                Log.e("string",resultJsonObj.toString());


                URL url = new URL(AppConstants.URL_GENERALIMAGE_UPLOAD);
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
                boolean bolResult = uploadImageDAO.updateGeneralProcTracker(lstGDToBeSync, yearWiseCollegeId);
                uploadImageDAO.close();

                if (bolResult) {
                    cancelProgressDialog();
                    emptyImageProp();

                    Toast.makeText(currentActivity, getString(R.string.alert_general_saved_successfully), Toast.LENGTH_LONG).show();

                    GetGeneralDetailImageActivity generalDetailImageActivity = new GetGeneralDetailImageActivity();
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
                    Toast.makeText(GeneralDetailImageActivity.this,"Unable to get the Date and Time",Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
