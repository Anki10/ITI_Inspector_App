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
import com.ss.nsdc.dao.LandImage;
import com.ss.nsdc.dao.PowerImage;
import com.ss.nsdc.main.FullScreenImageViewActivity;
import com.ss.nsdc.main.LandBuildingDetails;
import com.ss.nsdc.main.PowerSupplyImageActivity;
import com.ss.nsdc.utility.Utility;

import java.util.List;

/**
 * Created by Radhika on 5/19/2017.
 */

public class PowerImageAdapter extends BaseAdapter {

    List<PowerImage> generalDetails;
    private static LayoutInflater inflater = null;
    Activity currentActivity;


    public PowerImageAdapter(Activity currentActivity, List<PowerImage> powerDetails) {
    this.currentActivity = currentActivity;
    this.generalDetails = powerDetails;
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
        ImageView imagelandNotSubmitted,imagelandSubmitted;
        ImageView imgTechNC;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Holder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_power, null);

            holder = new Holder();
            holder.tv = (TextView) convertView.findViewById(R.id.textpowerName);
            holder.img1 = (ImageView) convertView.findViewById(R.id.imgpowerImage);
            holder.imgView = (ImageView) convertView.findViewById(R.id.imgpowerView);
            holder.imgRemark = (ImageView) convertView.findViewById(R.id.imagepowerRemark);
            holder.imagelandNotSubmitted = (ImageView) convertView.findViewById(R.id.imagepowerNotSubmitted);
            holder.imagelandSubmitted = (ImageView) convertView.findViewById(R.id.imagepowerSubmitted);
            holder.imgTechNC = (ImageView) convertView.findViewById(R.id.imgTechNC);


            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        Log.e("test1",generalDetails.get(position).getPictureType());
        holder.tv.setText(generalDetails.get(position).getPictureType());

        if (generalDetails.get(position).getPhoto() != null
                && !generalDetails.get(position).getPhoto().equals("")
                && !generalDetails.get(position).getPhoto().equals("null")) {

            //if (generalDetails.get(position).isImageChanged()) {
            Picasso.with((Context) currentActivity)
                    .load(Utility.getTempFile(currentActivity, "power_image_" + position))
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
                Picasso.with((Context) currentActivity).load(Utility.getTempFile(currentActivity, "power_image_" + position))
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
                    ((PowerSupplyImageActivity) currentActivity).selectImage(holder.img1, position);
                } else {
                    Intent intent = new Intent(currentActivity, FullScreenImageViewActivity.class);

                    //if (generalDetails.get(position).isImageChanged()) {
                    intent.putExtra("imgUrl",  "power_image_" + position);
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
                    ((PowerSupplyImageActivity) currentActivity).selectImage(holder.img1, position);
                }
            }
        });

        holder.imgRemark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((PowerSupplyImageActivity) currentActivity).createRemarkDialog(position);
            }
        });

        if(generalDetails.get(position).getProcTracker() == 3){
            holder.imagelandNotSubmitted.setVisibility(View.GONE);
            holder.imagelandSubmitted.setVisibility(View.VISIBLE);
        } else {
            holder.imagelandNotSubmitted.setVisibility(View.VISIBLE);
            holder.imagelandSubmitted.setVisibility(View.GONE);
        }

        holder.imagelandNotSubmitted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((PowerSupplyImageActivity)currentActivity).saveGeneralDetails(position);
            }
        });

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

                ((PowerSupplyImageActivity)currentActivity).changeNC(position,ncValue);
            }
        });



        return convertView;
    }





}
