package com.ss.nsdc.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ss.nsdc.R;
import com.ss.nsdc.adapter.VideoSectionAdapter;
import com.ss.nsdc.entity.VideoObjectModel;
import com.ss.nsdc.entity.VideoSectionModel;
import com.ss.nsdc.entity.VideosListModel;
import com.ss.nsdc.utility.DataUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class VideoSectionActivity extends AppCompatActivity {

    List<VideoSectionModel> videoSectionList= new ArrayList<>();

    RecyclerView rclrview;
    VideoSectionAdapter videoSectionAdapter;


    String instituteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_section);

        Toolbar toolbar = (Toolbar) findViewById(R.id.cat_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Upload Video");

        Intent myIntent= getIntent();
        instituteId=myIntent.getStringExtra("YearWiseCollegeId");

        TextView formHelp=(TextView)findViewById(R.id.formHelp);
        formHelp.setVisibility(View.GONE);

        rclrview=(RecyclerView)findViewById(R.id.cat_recycler_view);
        rclrview.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(this);
        rclrview.setLayoutManager(mLayoutManager);

        try {
            HashMap<String, VideoObjectModel> videoData = DataUtils.loadMap(this);
            boolean isVideoDataDownloaded = videoData.containsKey(instituteId);
            if(isVideoDataDownloaded)
            {
                VideoObjectModel videoObjectModel= videoData.get(instituteId);
                videoSectionList= videoObjectModel.getVideoObjectModel();
            }else
            {
                Toast.makeText(this,"Data not available",Toast.LENGTH_LONG).show();
            }

            videoSectionAdapter= new VideoSectionAdapter(videoSectionList,instituteId);
            rclrview.setAdapter(videoSectionAdapter);

        }catch (Exception e)
        {
            Log.d("Error in json parsing",e.toString());
        }


    }

}
