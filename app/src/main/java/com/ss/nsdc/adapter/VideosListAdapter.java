package com.ss.nsdc.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ss.nsdc.R;
import com.ss.nsdc.entity.VideoSectionModel;
import com.ss.nsdc.entity.VideosListModel;
import com.ss.nsdc.main.VideoListActivity;

import java.io.File;
import java.util.List;

public class VideosListAdapter extends
        RecyclerView.Adapter<VideosListAdapter.ViewHolder> {

   // private VideoSectionModel videoSectionModel;
    private List<VideosListModel> videosListModel = null;
    private Context context;
  //  String inspectionType;
    String instituteId;

    SharedPreferences sharedPreferences;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textTechName;
        public ImageView imgTechImage;
        public ImageView imageTechRemark;
        public ImageView imageTechSubmitted;

        public ViewHolder(View v) {
            super(v);
            textTechName = (TextView) v.findViewById(R.id.textTechName);
            imgTechImage = (ImageView) v.findViewById(R.id.imgTechImage);
            imageTechRemark = (ImageView) v.findViewById(R.id.imageTechRemark);
            imageTechSubmitted = (ImageView) v.findViewById(R.id.imageTechSubmitted);
        }
    }

    public VideosListAdapter(List<VideosListModel> videosListModel, String instituteId) {
       // this.videoSectionModel = videoSectionModel;
        this.instituteId=instituteId;
        this.videosListModel=videosListModel;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        context = parent.getContext();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        // create a new view
        View v = LayoutInflater.from(context).inflate(
                R.layout.list_item_video, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.textTechName.setText(videosListModel.get(position).getVideoName());
        holder.textTechName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context,videosListModel.get(position).getVideoName(),Toast.LENGTH_LONG).show();
                return false;
            }
        });


        final String filePath = videosListModel.get(position).getVideoPath();
        if(!TextUtils.isEmpty(filePath)) {
            Bitmap bmThumbnail = ThumbnailUtils.createVideoThumbnail(filePath , MediaStore.Video.Thumbnails.FULL_SCREEN_KIND);
            holder.imgTechImage.setImageBitmap(bmThumbnail);
        }

        holder.imgTechImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(filePath))
                {
                    ((VideoListActivity)context).callVideoIntent(position);
                }
                else
                {
                    CharSequence options[] = new CharSequence[]{"Record New Video", "Play Existing Video",};
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                   // builder.setTitle(interactionEIACoordinatorList.get(Integer.parseInt(v.getTag().toString())).getName());
                    builder.setItems(options, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {

                                ((VideoListActivity)context).callVideoIntent(position);

                            } else if (which == 1) {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(filePath));
                                intent.setDataAndType(Uri.parse(filePath), "video/*");
                                context.startActivity(intent);
                            }
                        }
                    });
                    builder.show();
                }

               }
        });
        holder.imageTechRemark.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((VideoListActivity)context).showRemarksDialog(videosListModel.get(position).getRemarks(),position);
            }
        });

        if(videosListModel.get(position).isSync())
        {
            holder.imageTechSubmitted.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
        }else
        {
            holder.imageTechSubmitted.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_video_upload));
        }
        holder.imageTechSubmitted.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!videosListModel.get(position).isSync())
                {
                    if(TextUtils.isEmpty(videosListModel.get(position).getVideoPath()))
                    {
                     Toast.makeText(context,"Please capture video",Toast.LENGTH_LONG).show();
                    }
                    /*else if(TextUtils.isEmpty(videosListModel.get(position).getRemarks()))
                    {
                     Toast.makeText(context,"Please add remarks",Toast.LENGTH_LONG).show();
                    }*/
                    else
                    {
                        ((VideoListActivity)context).syncData(position);
                    }
                }
              }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return videosListModel.size();
    }


  /*  public String getPathFromURI(Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);

        //Source not from device capture or selection
        if (cursor == null) {
            return uri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.MediaColumns.DATA);
            if (idx == -1) {
                return uri.getPath();
            }

            String path = cursor.getString(idx);
            cursor.close();
            return path;
        }
    }
*/


}
