package com.ss.nsdc.main;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
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
import com.ss.nsdc.adapter.EquipmentTagAdapter;
import com.ss.nsdc.adapter.LabImageAdapter;
import com.ss.nsdc.adapter.ToolsBelowAdapter;
import com.ss.nsdc.constant.AppConstants;
import com.ss.nsdc.dao.ITIDBController;
import com.ss.nsdc.dao.LabImage;
import com.ss.nsdc.dao.ToolsBelow;
import com.ss.nsdc.entity.EquipmentTag;
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
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.zelory.compressor.Compressor;

/**
 * Created by Radhika on 5/22/2017.
 */

public class ToolsBelowActivity extends BaseActivity {

    static int tcIndex;
    static int tcPosition;
    static List<EquipmentTag> equipmentTags;

    int groupcount;

    List<ToolsBelow> lstBelowToBeSync= new ArrayList<>();
    Activity currentActivity;
    List<ToolsBelow> technical = null;
    String responseCode = null;
    JSONObject responseObj = null;

    Map<String, List<ToolsBelow>> toolsBelowMap;
    List<String> headers;
    String yearWiseCollegeId = null;
    String applicationId = null;

    //ListView lstTechnical;
    ExpandableListView lstToolsBelow;
    Button btnClassroomsSubmit;
    JSONObject dateTimeObj;
    String serverDateTime;
    String latitude;
    String longitude;

    GPSTracker gps;
    //ClassroomAdapter adapter;
    ToolsBelowAdapter adapter;


    EditText editRemark;
    Button buttonRemark;
    Button buttonCancel;
    TextView textRemarkType;

    int index;
    int groupposition,childposition;
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
    TextView report, heading;
    String tradeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);

        currentActivity = ToolsBelowActivity.this;
        yearWiseCollegeId = getIntent().getStringExtra("yearWiseCollegeId");
        //applicationId = getIntent().getStringExtra("applicationId");
        tradeId  = getIntent().getStringExtra("tradeId");

        new GetDateandTime().execute();

        GetToolsBelow getToolsBelow = new GetToolsBelow();
        getToolsBelow.execute();

        initDialogViews();

        lstToolsBelow = (ExpandableListView) findViewById(R.id.lstLabDetail);
        boolean result = Utility.checkLocationPermission(currentActivity);
        if (result) {
            gps = GPSTracker.getInstance(getApplicationContext());
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.sub_cat_toolbar);
        setSupportActionBar(toolbar); //
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sub Category Listing");
        actionBar.setTitle("Tools Below 10000");
    }

    private void cancelCustomDialog1() {

    }

    public boolean isMandatoryFields(int position) {
        boolean isImgOrRemarkChanged = false;
        List<ToolsBelow> classrooms = toolsBelowMap.get(headers.get(position));

        for (ToolsBelow classroom : classrooms) {
            if (classroom == null) {
                continue;
            }

            if (classroom.isImageChanged()) {
                isImgOrRemarkChanged = true;
                break;
            } else if (classroom.isRemarkChanged()) {
                isImgOrRemarkChanged = true;
                break;
            }
        }

        return isImgOrRemarkChanged;
    }

    public void saveClassrooms(int jobIndex, int imagePos) {
        if (Validator.isNetworkAvailable(currentActivity)) {
            tcIndex = jobIndex;
            tcPosition = imagePos;

            ITIDBController uploadImagesDAO = new ITIDBController(currentActivity);
            lstBelowToBeSync = uploadImagesDAO.getUnsavedToolsBelowImageInfobyYearwiseCollegeId(yearWiseCollegeId,
                    toolsBelowMap.get(headers.get(jobIndex)).get(imagePos).getRefId(),
                    toolsBelowMap.get(headers.get(jobIndex)).get(imagePos).getDocId());
            uploadImagesDAO.close();

            if (lstBelowToBeSync != null && lstBelowToBeSync.size() > 0) {
                SaveToolsBelow classrooms = new SaveToolsBelow();
                classrooms.execute(String.valueOf(jobIndex));
            } else {
                alert(currentActivity, getString(R.string.alert), getString(R.string.no_image_upload), getString(R.string.ok), getString(R.string.cancel), false, false, null);
            }

//            if(toolsBelowMap.get(headers.get(jobIndex)).get(imagePos).getProcTracker()==2)
//            {
//                lstBelowToBeSync.clear();
//                ToolsBelow technicalImage= toolsBelowMap.get(headers.get(jobIndex)).get(imagePos);
//                lstBelowToBeSync.add(technicalImage);
//                SaveToolsBelow toolsBelow = new SaveToolsBelow();
//                toolsBelow.execute(String.valueOf(jobIndex));
//            }else {
//                alert(currentActivity, getString(R.string.alert), getString(R.string.no_image_upload), getString(R.string.ok), getString(R.string.cancel), false, false, null);
//            }

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
                    toolsBelowMap.get(headers.get(index)).get(imagePosition).setIsRemarkChanged(true);
                    toolsBelowMap.get(headers.get(index)).get(imagePosition).setRemarks(editRemark.getText().toString());
                    toolsBelowMap.get(headers.get(index)).get(imagePosition).setProcTracker(2);

                    ITIDBController imagesDAO = new ITIDBController(currentActivity);
                    imagesDAO.updateToolsBelowDetails(toolsBelowMap.get(headers.get(index)).get(imagePosition));
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

        if (toolsBelowMap.get(headers.get(index)).get(imagePosition).getRemarks() != null
                && !toolsBelowMap.get(headers.get(index)).get(imagePosition).getRemarks().equals("null")) {
            editRemark.setText(toolsBelowMap.get(headers.get(index)).get(imagePosition).getRemarks());
        } else {
            editRemark.setText("");
        }
        textRemarkType.setText(toolsBelowMap.get(headers.get(index)).get(imagePosition).getPictureType());
    }

    public boolean isMandatoryFields() {
        boolean isAnyImageChanged = false;
        for (ToolsBelow classroom : technical) {
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
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(Utility.getTempFile(this, "toolbelow_image_" + index + "_" + imagePosition)));
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
        final File file = Utility.getTempFile(this, "toolbelow_image_" + index + "_" + imagePosition);
        captureBmp = Compressor.getDefault(this).compressToBitmap(file);

        toolsBelowMap.get(headers.get(index)).get(imagePosition).setIsImageChanged(true);
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

//        if(technical.get(index).getPictureType().equalsIgnoreCase("Photograph of Cumulative bill of all the equipment of value below 10000/ Declaration ")){
//            captureBmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        }else {
            captureBmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        }


        toolsBelowMap.get(headers.get(index)).get(imagePosition).setPhoto(Base64.encodeToString(bytes.toByteArray(), Base64.DEFAULT));
        toolsBelowMap.get(headers.get(index)).get(imagePosition).setProcTracker(2);
        //classrooms.get(index).setImage(Base64.encodeToString(bytes.toByteArray(), Base64.DEFAULT));

        ITIDBController imagesDAO = new ITIDBController(currentActivity);
        imagesDAO.updateToolsBelowDetails(toolsBelowMap.get(headers.get(index)).get(imagePosition));
        imagesDAO.close();

        imageView.setImageBitmap(captureBmp);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        adapter.notifyDataSetChanged();
    }

    public void emptyImageProp() {
        List<ToolsBelow> classroomList = toolsBelowMap.get(headers.get(index));
        if (classroomList == null) {
            return;
        }

        for (ToolsBelow classroom : classroomList) {
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

    public Dialog creatingDialog(boolean isCancelableBack, boolean isCancelableoutside, View view, int height, int width, boolean heightMatchParent) {

        customDialog = new Dialog(currentActivity, R.style.dialogTheme);
        //  dialog.setCancelable(isCancelableBack);
        if (view.getParent() != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            viewGroup.removeView(view);
        }
        customDialog.setCanceledOnTouchOutside(isCancelableoutside);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Window window = customDialog.getWindow();
        window.setGravity(Gravity.CENTER);
      /*  WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.width = Helper.toPixels(context, 200);
        layoutParams.height = Helper.toPixels(context, 200);
        dialog.getWindow().setAttributes(layoutParams);*/
        customDialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        customDialog.setContentView(view);
        customDialog.show();
        if (heightMatchParent) {
            customDialog.getWindow().setLayout(DimensionUtils.toPixels(currentActivity, width), WindowManager.LayoutParams.MATCH_PARENT);
        } else {
            customDialog.getWindow().setLayout(DimensionUtils.toPixels(currentActivity, width), DimensionUtils.toPixels(currentActivity, height));

        }

        return customDialog;

    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(currentActivity)
                .setTitle("Confirmation")
                .setMessage(R.string.alert_pending_changes)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(R.string.submit_later, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        ToolsBelowActivity.this.finish();
                    }
                })
                .setNegativeButton(R.string.no, null).show();
    }

    public void displayTags(int groupPosition, int childPosition) {
        groupposition = groupPosition;
        childposition = childPosition;

      new GetEquipmentTag().execute();
    }

    private class GetToolsBelow extends AsyncTask<String, String, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog(currentActivity, "Loading...", "Please Wait..", false, true);
        }

        @Override
        protected Boolean doInBackground(String... params) {
            ITIDBController uploadImageDAO = new ITIDBController(currentActivity);
            technical = uploadImageDAO.getToolsBelowImageInfobyYearwiseCollegeId(yearWiseCollegeId);
            uploadImageDAO.close();
            return true;
        }

        @Override
        protected void onPostExecute(Boolean response) {
            super.onPostExecute(response);
            cancelProgressDialog();

            if (response != null && response) {
                if (technical != null) {
                    List<ToolsBelow> classroomList;
                    toolsBelowMap = new HashMap<>();
                    headers = new ArrayList<>();
                    for (ToolsBelow classroom : technical) {
                        if (classroom == null) {
                            continue;
                        }

                        String name = classroom.getName();

                        if (classroom.getPhoto() != null
                                && !classroom.getPhoto().equals("")
                                && !classroom.getPhoto().equals("null")) {
                            String imageUrl = classroom.getPhoto();
                            //imageUrl = imageUrl.substring(imageUrl.lastIndexOf("/") + 1, imageUrl.length());
                            classroom.setPhoto(imageUrl);
                        }

                        if (toolsBelowMap.containsKey(name)) {
                            classroomList = toolsBelowMap.get(name);
                            classroomList.add(classroom);
                        } else {
                            headers.add(name);
                            classroomList = new ArrayList<>();
                            classroomList.add(classroom);
                            toolsBelowMap.put(name, classroomList);
                        }
                    }
                    //adapter = new ClassroomAdapter(currentActivity, classrooms);
                    adapter = new ToolsBelowAdapter(currentActivity, headers, toolsBelowMap);
                    lstToolsBelow.setAdapter(adapter);

                    groupcount = lstToolsBelow.getCount();

                    for (int i = 0; i < groupcount; i++) {

                        lstToolsBelow.expandGroup(i);
                    }

                }
            } else {

            }
        }
    }

    private class SaveToolsBelow extends AsyncTask<String, String, JSONObject> {

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

                JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < lstBelowToBeSync.size(); i++) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("tradeId",lstBelowToBeSync.get(i).getTradeId());
                    jsonObject.put("docId", lstBelowToBeSync.get(i).getDocId());
                    jsonObject.put("name", lstBelowToBeSync.get(i).getName());
                    jsonObject.put("photo", lstBelowToBeSync.get(i).getPhoto());
                    jsonObject.put("remarks", lstBelowToBeSync.get(i).getRemarks());
                    jsonObject.put("Latitude", lstBelowToBeSync.get(i).getLatitude());
                    jsonObject.put("Longitude", lstBelowToBeSync.get(i).getLongitude());
                    jsonObject.put("nc", lstBelowToBeSync.get(i).getNc());
                    jsonObject.put("refId", lstBelowToBeSync.get(i).getRefId());

                    jsonArray.put(jsonObject);
                }

                JSONObject GeneralDetailsJsonObj = new JSONObject();
                GeneralDetailsJsonObj.put("workShopAndToolWisePhotoDoc", jsonArray);
                GeneralDetailsJsonObj.put("yearWiseCollegeID", yearWiseCollegeId);

                JSONObject payloadJsonObj = new JSONObject();
                payloadJsonObj.put("payload", GeneralDetailsJsonObj);

                JSONObject resultJsonObj = new JSONObject();
                resultJsonObj.put("result", payloadJsonObj);

                Log.e("result",resultJsonObj.toString());


                URL url = new URL(AppConstants.URL_TOOLSBELOW_IMAGE_UPLOAD);
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
                boolean bolResult = uploadImageDAO.updateToolsBelowProcTracker(lstBelowToBeSync, yearWiseCollegeId);
                uploadImageDAO.close();

                if (bolResult) {
                    cancelProgressDialog();
                    emptyImageProp();

                    Toast.makeText(currentActivity, getString(R.string.alert_toolsbelow_saved_successfully), Toast.LENGTH_LONG).show();
//                    Intent i = new Intent(currentActivity, PhotoCategoriesActivity.class);
//                    i.putExtra("Category", AppConstants.TEXT_UPLOAD_IMAGES);
//                    i.putExtra("YearWiseCollegeId", yearWiseCollegeId);
//                    i.putExtra("applicationId", applicationId);
//                    startActivity(i);

                    GetToolsBelow getClassrooms = new GetToolsBelow();
                    getClassrooms.execute();

                } else {
                    cancelProgressDialog();
                    alert(ToolsBelowActivity.this, "", getString(R.string.alert_network_error), getString(R.string.ok), getString(R.string.cancel), false, false);
                }

            } else {
                cancelProgressDialog();
                alert(ToolsBelowActivity.this, "", getString(R.string.alert_network_error), getString(R.string.ok), getString(R.string.cancel), false, false);
            }
        }
    }


    public void changeNC(int groupPosition,int childPosition, int ncValue)
    {



        //  technicalMap.get(headers.get(index)).get(imagePosition).setIsRemarkChanged(true);
        toolsBelowMap.get(headers.get(groupPosition)).get(childPosition).setNc(ncValue);
        toolsBelowMap.get(headers.get(groupPosition)).get(childPosition).setProcTracker(2);

        ITIDBController imagesDAO = new ITIDBController(currentActivity);
        imagesDAO.updateToolsBelowDetails(toolsBelowMap.get(headers.get(groupPosition)).get(childPosition));
        imagesDAO.close();


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
                    Toast.makeText(ToolsBelowActivity.this,"Unable to get the Date and Time",Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private class GetEquipmentTag extends AsyncTask<String,Integer,JSONObject>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog(currentActivity, "Loading...", "Please Wait..", false, true);
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            String response = "";
            JSONObject response_json = null;
            try {
                URL url = new URL(AppConstants.URL_EQUIPMENT_TAG);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                String str = "YearWiseCollegeId=" + yearWiseCollegeId + "&tradeId=" + tradeId;
                Log.e("string",str);
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

        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);
            cancelProgressDialog();
            try{
                int status = result.optInt("status");
                if(status == 0){

                       equipmentTags = new ArrayList<EquipmentTag>();
                       JSONObject payload = result.getJSONObject("payload");
                    final JSONArray equipments = payload.optJSONArray("equipmentTradeWiseDetailsDoc");
                    for (int i = 0 ; i <equipments.length();i++){

                         EquipmentTag equipmentTag = new EquipmentTag();
                         JSONObject equip = equipments.optJSONObject(i);
                         equipmentTag.setEquipmentTagId(equip.optString("equipmentID"));
                         equipmentTag.setEquipmentTagName(equip.optString("equipmentName"));
                        equipmentTags.add(equipmentTag);
                        Log.e("tag",equipmentTags.toString());

                    }

                    final Dialog dialog = new Dialog(currentActivity);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.equip_tag_dialog);

                    ListView lstTags = (ListView) dialog.findViewById(R.id.lstViewTags);
                    Button btnSave = (Button) dialog.findViewById(R.id.btnSaveTag);
                    ImageView imgViewClose = (ImageView) dialog.findViewById(R.id.imgClose);

                    final EquipmentTagAdapter tagAdapter = new EquipmentTagAdapter(currentActivity, equipmentTags);
                    lstTags.setAdapter(tagAdapter);


                    imgViewClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });

                    btnSave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String strTag = "";
                            for (EquipmentTag tag : equipmentTags) {
                                if (tag == null) {
                                    continue;
                                }
                                if (tag.isChecked()) {
                                    strTag += tag.getEquipmentTagName() + ",";
                                }
                            }
                            strTag = (strTag.length() > 0) ? strTag.substring(0, strTag.length() - 1) : strTag;


                            lstToolsBelow.deferNotifyDataSetChanged();
                            dialog.cancel();
                        }
                    });

                    dialog.show();

                    dialog.show();







                }else {
                    cancelProgressDialog();
                    Toast.makeText(currentActivity,"Unable to get Equipment list",Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void progressDialog(Context context, String title, String message, boolean cancelable, boolean isTitle) {
        pdialog = new ProgressDialog(context);

        if (isTitle) {
            pdialog.setTitle(title);
        }

        pdialog.setMessage(message);
        if (!cancelable) {
            pdialog.setCancelable(false);
        }

        pdialog.show();
    }

    public void cancelProgressDialog() {
        pdialog.cancel();
    }

    public void alert(Context context, String title, String message, String positivebutton, String negativeButton, boolean isNegativeButton, boolean isCustomView, View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        dialog = null;
        //builder.setTitle(title);

        if (isCustomView) {
            builder.setView(view);
        } else {
            builder.setMessage(message);
            builder.setPositiveButton(positivebutton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            if (isNegativeButton) {
                builder.setNegativeButton(negativeButton, null);
            }
        }
        dialog = builder.show();
    }

    public void alert(Context context, String title, String message, String positiveButton, String negativeButton, boolean isNegativeButton, boolean isTitle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (isTitle) {
            builder.setTitle(title);
        }

        builder.setMessage(message);
        builder.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        if (isNegativeButton) {
            builder.setNegativeButton(negativeButton, null);
        }

        builder.show();
    }

    public Dialog creatingDialog(Activity context, boolean isCancelableBack, boolean isCancellableOutside, View view, int height, int width, boolean heightMatchParent) {

        height = 260;
        customDialog = new Dialog(context, R.style.dialogTheme);

        if (view.getParent() != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            viewGroup.removeView(view);
        }
        customDialog.setCanceledOnTouchOutside(isCancellableOutside);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Window window = customDialog.getWindow();
        window.setGravity(Gravity.CENTER);

        customDialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        customDialog.setContentView(view);
        customDialog.show();

        if (heightMatchParent) {
            customDialog.getWindow().setLayout(DimensionUtils.toPixels(context, width), WindowManager.LayoutParams.MATCH_PARENT);
        } else {
            customDialog.getWindow().setLayout(DimensionUtils.toPixels(context, width), DimensionUtils.toPixels(context, height));
        }

        return customDialog;
    }

    public void cancelCustomDialog() {
        if (customDialog != null) {
            customDialog.cancel();
        }
        //toHideKeyboard();
    }
}
