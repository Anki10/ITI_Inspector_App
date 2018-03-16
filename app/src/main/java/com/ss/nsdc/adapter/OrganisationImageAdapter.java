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
import com.ss.nsdc.dao.OrganisationImage;
import com.ss.nsdc.main.FullScreenImageViewActivity;
import com.ss.nsdc.main.OrganisationImageActivity;
import com.ss.nsdc.utility.Utility;

import java.util.List;

/**
 * Created by Radhika on 5/19/2017.
 */

public class OrganisationImageAdapter extends BaseAdapter {

    List<OrganisationImage> orgDetails;
    private static LayoutInflater inflater = null;
    Activity currentActivity;


    public OrganisationImageAdapter(Activity currentActivity, List<OrganisationImage> orgDetails) {
        this.currentActivity = currentActivity;
        this.orgDetails = orgDetails;
        inflater = (LayoutInflater) currentActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return orgDetails.size();
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
        ImageView imageGDNotSubmitted,imageGDSubmitted;
        ImageView imgTechNC;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Holder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_material, null);

            holder = new Holder();
            holder.tv = (TextView) convertView.findViewById(R.id.textOrgName);
            holder.img1 = (ImageView) convertView.findViewById(R.id.imgOrgImage);
            holder.imgView = (ImageView) convertView.findViewById(R.id.imgOrgView);
            holder.imgRemark = (ImageView) convertView.findViewById(R.id.imageOrgRemark);
            holder.imageGDNotSubmitted = (ImageView) convertView.findViewById(R.id.imageOrgNotSubmitted);
            holder.imageGDSubmitted = (ImageView) convertView.findViewById(R.id.imageOrgSubmitted);
            holder.imgTechNC = (ImageView) convertView.findViewById(R.id.imgTechNC);


            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        Log.e("test1",orgDetails.get(position).getPictureType());
        holder.tv.setText(orgDetails.get(position).getPictureType());

        if (orgDetails.get(position).getPhoto() != null
                && !orgDetails.get(position).getPhoto().equals("")
                && !orgDetails.get(position).getPhoto().equals("null")) {

            //if (orgDetails.get(position).isImageChanged()) {
            Picasso.with((Context) currentActivity)
                    .load(Utility.getTempFile(currentActivity, "org_image_" + position))
                    .skipMemoryCache()
                    .resize(100, 100)
                    .placeholder(R.drawable.load_icon).into(holder.img1);
            /*} else {

                Picasso.with((Context) currentActivity).load(orgDetails.get(position).getImageUrl())
                        .resize(100, 100)
                        .placeholder(R.drawable.load_icon).into(holder.img1);
            }*/

            holder.img1.setTag(null);
        } else {

            if (orgDetails.get(position).isImageChanged()) {
                Picasso.with((Context) currentActivity).load(Utility.getTempFile(currentActivity, "org_image_" + position))
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
                    ((OrganisationImageActivity) currentActivity).selectImage(holder.img1, position);
                } else {
                    Intent intent = new Intent(currentActivity, FullScreenImageViewActivity.class);

                    //if (orgDetails.get(position).isImageChanged()) {
                    intent.putExtra("imgUrl",  "org_image_" + position);
                    /*} else {
                        intent.putExtra("imgServerUrl", orgDetails.get(position).getImageUrl());
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
                    ((OrganisationImageActivity) currentActivity).selectImage(holder.img1, position);
                }
            }
        });

        holder.imgRemark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((OrganisationImageActivity) currentActivity).createRemarkDialog(position);
            }
        });

        if(orgDetails.get(position).getProcTracker() == 3){
            holder.imageGDNotSubmitted.setVisibility(View.GONE);
            holder.imageGDSubmitted.setVisibility(View.VISIBLE);
        } else {
            holder.imageGDNotSubmitted.setVisibility(View.VISIBLE);
            holder.imageGDSubmitted.setVisibility(View.GONE);
        }

        holder.imageGDNotSubmitted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((OrganisationImageActivity)currentActivity).saveGeneralDetails(position);
            }
        });


        if(orgDetails.get(position).getNc()==0){
            holder.imgTechNC.setImageResource(R.drawable.no);
        }else {
            holder.imgTechNC.setImageResource(R.drawable.yes);
        }

        holder.imgTechNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ncValue=0;
                if(orgDetails.get(position).getNc()==0){
                    orgDetails.get(position).setNc(1);
                    ncValue=1;
                }else {
                    orgDetails.get(position).setNc(0);
                    ncValue=0;
                }

                ((OrganisationImageActivity)currentActivity).changeNC(position,ncValue);
            }
        });



        return convertView;
    }
}
