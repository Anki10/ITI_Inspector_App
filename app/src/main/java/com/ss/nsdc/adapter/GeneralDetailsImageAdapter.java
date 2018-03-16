package com.ss.nsdc.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ss.nsdc.R;
import com.ss.nsdc.dao.GeneralImageModel;
import com.ss.nsdc.main.FullScreenImageViewActivity;
import com.ss.nsdc.main.GeneralDetailImageActivity;
import com.ss.nsdc.main.OrganisationImageActivity;
import com.ss.nsdc.utility.Utility;

import java.util.List;

/**
 * Created by Radhika on 5/19/2017.
 */

public class GeneralDetailsImageAdapter extends BaseAdapter {

    List<GeneralImageModel> generalDetails;
    private static LayoutInflater inflater = null;
    Activity currentActivity;



    public GeneralDetailsImageAdapter(Activity currentActivity, List<GeneralImageModel> generalDetails) {
        this.currentActivity = currentActivity;
        this.generalDetails = generalDetails;
        inflater = (LayoutInflater) currentActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        return generalDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        TextView tv;
        ImageView img1;
        ImageView imgView;
        ImageView imgRemark;
        ImageView imageGDNotSubmitted,imageGDSubmitted,imgTechNC;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Holder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_general, null);

            holder = new Holder();
            holder.tv = (TextView) convertView.findViewById(R.id.textGDName);
            holder.img1 = (ImageView) convertView.findViewById(R.id.imgGDImage);
            holder.imgView = (ImageView) convertView.findViewById(R.id.imgGDView);
            holder.imgRemark = (ImageView) convertView.findViewById(R.id.imageGDRemark);
            holder.imageGDNotSubmitted = (ImageView) convertView.findViewById(R.id.imageGDNotSubmitted);
            holder.imageGDSubmitted = (ImageView) convertView.findViewById(R.id.imageGDSubmitted);
            holder.imgTechNC = (ImageView)convertView.findViewById(R.id.imgTechNC);



            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        Log.e("test1",generalDetails.get(position).getPictureType());
        holder.tv.setText(generalDetails.get(position).getPictureType());

        if(generalDetails.get(position).getPictureType().equals("Photograph of Address Proof")){
            holder.imgTechNC.setVisibility(View.VISIBLE);
        }else if(generalDetails.get(position).getPictureType().equalsIgnoreCase("Mandatory Assessor Selfie")){
            holder.imgTechNC.setVisibility(View.GONE);
        }else if(generalDetails.get(position).getPictureType().equalsIgnoreCase("Photograph of ITI")){
            holder.imgTechNC.setVisibility(View.VISIBLE);
        }else if(generalDetails.get(position).getPictureType().equalsIgnoreCase("Photograph of Bill of Biometric Device")){
            holder.imgTechNC.setVisibility(View.VISIBLE);
        }

        if(generalDetails.get(position).getNc()==0){
            holder.imgTechNC.setImageResource(R.drawable.no);
        }else {
            holder.imgTechNC.setImageResource(R.drawable.yes);
        }

        holder.imgTechNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ncValue=0;
                if(generalDetails.get(position).getNc()==0){
                    generalDetails.get(position).setNc(1);
                    ncValue=1;
                }else {
                    generalDetails.get(position).setNc(0);
                    ncValue=0;
                }

                ((GeneralDetailImageActivity)currentActivity).changeNC(position,ncValue);
            }
        });

        if (generalDetails.get(position).getPhoto() != null
                && !generalDetails.get(position).getPhoto().equals("")
                && !generalDetails.get(position).getPhoto().equals("null")) {

            //if (generalDetails.get(position).isImageChanged()) {
            Picasso.with((Context) currentActivity)
                    .load(Utility.getTempFile(currentActivity, "gen_image_" + position))
                    .skipMemoryCache()
                    .resize(100, 100)
                    .placeholder(R.drawable.load_icon).into(holder.img1);
            /*} else {

                Picasso.with((Context) currentActivity).load(generalDetails.get(position).getImageUrl())
                        .resize(100, 100)
                        .placeholder(R.drawable.load_icon).into(holder.img1);
            }*/

            holder.img1.setTag(null);
        } else {

            if (generalDetails.get(position).isImageChanged()) {
                Picasso.with((Context) currentActivity).load(Utility.getTempFile(currentActivity, "gen_image_" + position))
                        .skipMemoryCache()
                        .resize(100, 100)
                        .placeholder(R.drawable.load_icon).into(holder.img1);
            } else {
                holder.img1.setImageResource(R.drawable.upload_pic_36_36);
            }

            holder.img1.setTag(R.drawable.upload_pic_36_36);
        }

        if (holder.img1.getTag() != null && ((Integer) holder.img1.getTag() == R.drawable.upload_pic_36_36)) {
            holder.imgView.setVisibility(View.GONE);
        } else {
            holder.imgView.setVisibility(View.VISIBLE);
        }

        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.img1.getTag() != null && ((Integer) holder.img1.getTag() == R.drawable.upload_pic_36_36)) {
                    ((GeneralDetailImageActivity) currentActivity).selectImage(holder.img1, position);
                } else {
                    Intent intent = new Intent(currentActivity, FullScreenImageViewActivity.class);

                    //if (generalDetails.get(position).isImageChanged()) {
                    intent.putExtra("imgUrl",  "gen_image_" + position);
                    /*} else {
                        intent.putExtra("imgServerUrl", generalDetails.get(position).getImageUrl());
                    }*/
                    currentActivity.startActivity(intent);
                }
            }
        });

        holder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.img1.getTag() != null && ((Integer) holder.img1.getTag() == R.drawable.upload_pic_36_36)) {
                } else {
                    ((GeneralDetailImageActivity) currentActivity).selectImage(holder.img1, position);
                }
            }
        });

        holder.imgRemark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GeneralDetailImageActivity) currentActivity).createRemarkDialog(position);
            }
        });

        if(generalDetails.get(position).getProcTracker() == 3){
            holder.imageGDNotSubmitted.setVisibility(View.GONE);
            holder.imageGDSubmitted.setVisibility(View.VISIBLE);
        } else {
            holder.imageGDNotSubmitted.setVisibility(View.VISIBLE);
            holder.imageGDSubmitted.setVisibility(View.GONE);
        }

        holder.imageGDNotSubmitted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((GeneralDetailImageActivity)currentActivity).saveGeneralDetails(position);
            }
        });

        return convertView;
    }
}
