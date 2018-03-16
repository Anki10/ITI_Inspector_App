package com.ss.nsdc.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ss.nsdc.R;
import com.ss.nsdc.dao.ITIDBController;
import com.ss.nsdc.dao.Institute;
import com.ss.nsdc.entity.VideoSectionModel;
import com.ss.nsdc.entity.VideosListModel;
import com.ss.nsdc.main.CategoryActivity;
import com.ss.nsdc.main.VideoListActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class VideoSectionAdapter extends
        RecyclerView.Adapter<VideoSectionAdapter.ViewHolder> {

    private List<VideoSectionModel> videoSectionsList;
    private Context context;
  //  String inspectionType;
    String instituteId;

    SharedPreferences sharedPreferences;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tv_name;
        public RelativeLayout rl_wholeview;

        public ViewHolder(View v) {
            super(v);
            tv_name = (TextView) v.findViewById(R.id.tv_name);
            rl_wholeview = (RelativeLayout) v.findViewById(R.id.rl_wholeview);
        }
    }

    public VideoSectionAdapter(List<VideoSectionModel> videoSectionsList,String instituteId) {
        this.videoSectionsList = videoSectionsList;
        this.instituteId=instituteId;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        context = parent.getContext();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        // create a new view
        View v = LayoutInflater.from(context).inflate(
                R.layout.list_item_videosection, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.tv_name.setText(videoSectionsList.get(position).getName());

        holder.rl_wholeview.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                VideoSectionModel videoSectionModel= videoSectionsList.get(position);
                Intent myIntent= new Intent(context, VideoListActivity.class);
                myIntent.putExtra("instituteId",instituteId);
                myIntent.putExtra("itemNo",position);

                context.startActivity(myIntent);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return videoSectionsList.size();
    }
}
