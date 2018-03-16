package com.ss.nsdc.main;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ss.nsdc.R;
import com.ss.nsdc.adapter.CategoryAdapter;
import com.ss.nsdc.adapter.VideoSectionAdapter;
import com.ss.nsdc.adapter.VideosListAdapter;
import com.ss.nsdc.constant.AppConstants;
import com.ss.nsdc.entity.VideoObjectModel;
import com.ss.nsdc.entity.VideoSectionModel;
import com.ss.nsdc.entity.VideosListModel;
import com.ss.nsdc.utility.DataUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class VideoListActivity extends AppCompatActivity {


    RecyclerView rclrview;
    String instituteId;
    int itemNo;
    VideoSectionModel videoSectionModel;
    VideosListAdapter videosListAdapter;

    List<VideosListModel> videosListModel;

    int selectedVideoPosition;
    ProgressDialog ringProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_section);

        Toolbar toolbar = (Toolbar) findViewById(R.id.cat_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Record Video");

        try {
            Intent myIntent = getIntent();
            instituteId = myIntent.getStringExtra("instituteId");
            itemNo = myIntent.getIntExtra("itemNo",0);

            HashMap<String, VideoObjectModel> videoData = DataUtils.loadMap(this);
            boolean isVideoDataDownloaded = videoData.containsKey(instituteId);
            if(isVideoDataDownloaded) {
                VideoObjectModel videoObjectModel = videoData.get(instituteId);
                videoSectionModel= videoObjectModel.getVideoObjectModel().get(itemNo);


                Log.d("video list ","");
            }

          }catch (Exception e)
        {
            Log.d("video list ",e.toString());
        }

        TextView formHelp=(TextView)findViewById(R.id.formHelp);
        formHelp.setVisibility(View.GONE);

        rclrview=(RecyclerView)findViewById(R.id.cat_recycler_view);
        rclrview.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(this);
        rclrview.setLayoutManager(mLayoutManager);

        videosListModel=videoSectionModel.getVideosListModel();
        videosListAdapter= new VideosListAdapter(videosListModel,instituteId);
        rclrview.setAdapter(videosListAdapter);



                ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO},
                1);


    }


    public void showRemarksDialog(String msg,final int position){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_remark);

        final EditText editRemark = (EditText) dialog.findViewById(R.id.editRemark);
        editRemark.setText(msg);
        Button buttonRemark = (Button) dialog.findViewById(R.id.buttonRemark);
        buttonRemark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, VideoObjectModel> videoData = DataUtils.loadMap(VideoListActivity.this);
                VideoObjectModel videoObjectModel = videoData.get(instituteId);
                videoObjectModel.getVideoObjectModel().get(itemNo).getVideosListModel().get(position).setRemarks(editRemark.getText().toString());
                videoObjectModel.getVideoObjectModel().get(itemNo).getVideosListModel().get(position).setSync(false);
                videoData.put(instituteId,videoObjectModel);
                DataUtils.saveHashMapInShared(VideoListActivity.this,videoData);

                videosListModel.get(position).setRemarks(editRemark.getText().toString());
                videosListModel.get(position).setSync(false);
                videosListAdapter.notifyDataSetChanged();

                Toast.makeText(VideoListActivity.this,"Remarks Saved",Toast.LENGTH_LONG).show();

                dialog.dismiss();
            }
        });
        Button buttonCancel = (Button) dialog.findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }


    public void callVideoIntent(int postion)
    {
      /*  this.selectedVideoPosition=postion;
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        takeVideoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 20);
        takeVideoIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
        startActivityForResult(takeVideoIntent, 106);
*/
        this.selectedVideoPosition=postion;

        /*Intent view = new Intent(this,VideoCaptureActivity.class);
        startActivityForResult(view,106);
*/
        Intent view = new Intent(this,Camera2VideoFragment.class);
        startActivityForResult(view,106);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                if(requestCode==106)
                {
                    try{
                   // Uri vid = data.getData();
                  //  String videoPath = getRealPathFromURI(vid);
                    String videoPath=data.getStringExtra("videoPath");
                //    int position=data.getIntExtra("position",0);
                    videosListModel.get(selectedVideoPosition).setVideoPath(videoPath);
                    videosListModel.get(selectedVideoPosition).setSync(false);
                  //  videosListAdapter.notifyItemChanged(selectedVideoPosition);
                 //    Toast.makeText(this,"Video Captured",Toast.LENGTH_LONG).show();

                    HashMap<String, VideoObjectModel> videoData = DataUtils.loadMap(this);
                    VideoObjectModel videoObjectModel = videoData.get(instituteId);
                    videoObjectModel.getVideoObjectModel().get(itemNo).getVideosListModel().get(selectedVideoPosition).setVideoPath(videoPath);
                    videoObjectModel.getVideoObjectModel().get(itemNo).getVideosListModel().get(selectedVideoPosition).setSync(false);
                    videoData.put(instituteId,videoObjectModel);

                    DataUtils.saveHashMapInShared(this,videoData);


                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {





                                videosListAdapter.notifyDataSetChanged();
                            }
                        }, 100);
                    }catch (Exception e)
                    {
                        Log.e("",e.toString());
                    }
                }

    }



    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    public void syncData(int position)
    {

        selectedVideoPosition=position;
        try {

            HashMap<String, VideoObjectModel> videoData = DataUtils.loadMap(VideoListActivity.this);
            VideoObjectModel videoObjectModel = videoData.get(instituteId);

            String videoID = videoObjectModel.getVideoObjectModel().get(itemNo).getVideosListModel().get(position).getVideoId();
            String videoName = videoObjectModel.getVideoObjectModel().get(itemNo).getVideosListModel().get(position).getVideoName();
            String videoPath = videoObjectModel.getVideoObjectModel().get(itemNo).getVideosListModel().get(position).getVideoPath();
            String remarks = videoObjectModel.getVideoObjectModel().get(itemNo).getVideosListModel().get(position).getRemarks();

            String equipmentId = videoObjectModel.getVideoObjectModel().get(itemNo).getVideosListModel().get(position).getEquipmentId();
            String tradeId = videoObjectModel.getVideoObjectModel().get(itemNo).getVideosListModel().get(position).getTradeId();
            String sectionId = videoObjectModel.getVideoObjectModel().get(itemNo).getSectionId();


            JSONObject jsonObject = new JSONObject();
            jsonObject.put("videoName", videoName);
            jsonObject.put("videoId", videoID);
            jsonObject.put("sectionId", sectionId);
            jsonObject.put("tradeId", tradeId);
            jsonObject.put("equipmentID", equipmentId);
            jsonObject.put("yearWiseCollegeID", instituteId);
            jsonObject.put("remarks", remarks);


            try {
                String filePath=videoPath;
              /*  Cursor cursor =getContentResolver().query(Uri.parse(fileURI), null, null, null, null);
                int idx;
                String filePath=null;
                idx = cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATA);
                if(cursor != null && cursor.moveToFirst()) {
                    filePath=cursor.getString(idx);
                }*/
                File file = new File(filePath);
                String base64Data = DataUtils.getStringFile(file);
                jsonObject.put("videoData", base64Data);
                String extension = filePath.substring(filePath.lastIndexOf("."));
                jsonObject.put("extension", extension);

            }catch (Exception e)
            {
            //    LogUtils.e("Exception in audio to base 64 conversion",e.toString());
                jsonObject.put("videoData", "");
                jsonObject.put("extension", "");
            }



            JSONObject payloadJsonObj= new JSONObject();
            payloadJsonObj.put("payload",jsonObject);

            JSONObject resultJsonObj= new JSONObject();
            resultJsonObj.put("result",payloadJsonObj);

            new SyncVideoData().execute(resultJsonObj.toString());

        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }



    class  SyncVideoData extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(VideoListActivity.this, android.R.style.Theme_Holo), null, "Please wait...", true);
            ringProgressDialog.setCancelable(false);
            ringProgressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            String response = "";
            JSONObject responseJSON = null;

            try {

                URL url = new URL(AppConstants.URL_VIDEO_UPLOAD);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
              //  conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setFixedLengthStreamingMode(params[0].getBytes().length);
                String str = params[0];
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
        protected void onPostExecute(JSONObject response) {
            super.onPostExecute(response);

            if(ringProgressDialog!=null)
                ringProgressDialog.dismiss();

            if (response != null) {

                try {

                    //response.opt("")

                    HashMap<String, VideoObjectModel> videoData = DataUtils.loadMap(VideoListActivity.this);
                    VideoObjectModel videoObjectModel = videoData.get(instituteId);
                    videoObjectModel.getVideoObjectModel().get(itemNo).getVideosListModel().get(selectedVideoPosition).setSync(true);
                    videoData.put(instituteId,videoObjectModel);
                    DataUtils.saveHashMapInShared(VideoListActivity.this,videoData);
                    videosListModel.get(selectedVideoPosition).setSync(true);
                    videosListAdapter.notifyDataSetChanged();
                    Toast.makeText(VideoListActivity.this,"Data synced",Toast.LENGTH_LONG).show();
                }catch (Exception e)
                {
                    Log.d("Error in json parsing",e.toString());
                }

            }

        }
    }

}
