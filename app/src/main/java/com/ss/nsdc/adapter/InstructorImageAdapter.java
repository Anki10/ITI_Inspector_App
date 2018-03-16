package com.ss.nsdc.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ss.nsdc.R;
import com.ss.nsdc.dao.InstructorImage;
import com.ss.nsdc.dao.OrganisationImage;
import com.ss.nsdc.dao.TechnicalImage;
import com.ss.nsdc.main.FullScreenImageViewActivity;
import com.ss.nsdc.main.GeneralDetailImageActivity;
import com.ss.nsdc.main.InstructorImageActivity;
import com.ss.nsdc.main.OrganisationImageActivity;
import com.ss.nsdc.main.TechnicalImageActivity;
import com.ss.nsdc.utility.Utility;

import java.util.List;
import java.util.Map;

/**
 * Created by Radhika on 5/19/2017.
 */

public class InstructorImageAdapter extends BaseExpandableListAdapter {


    private Context context;
    private List<String> listDataHeader;
    private Map<String, List<InstructorImage>> listDataChild;

    public InstructorImageAdapter(Context context, List<String> listDataHeader,
                                 Map<String, List<InstructorImage>> listChildData) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listChildData;
//        for (String key : this.listDataChild.keySet()) {
//            List<Classroom> classrooms = this.listDataChild.get(key);
//            classrooms.add(new Classroom());
//        }
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.technical_group, null);
        }

        TextView txtCRName = (TextView) convertView.findViewById(R.id.textCRName);
        txtCRName.setTypeface(null, Typeface.BOLD);
        txtCRName.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final InstructorImage technicalImage = (InstructorImage) getChild(groupPosition, childPosition);

//        if (childPosition == getChildrenCount(groupPosition) - 1) {
//            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = infalInflater.inflate(R.layout.classroom_button_item, null);
//        } else {
        LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = infalInflater.inflate(R.layout.list_technical, null);
//        }

        TextView textCRPictureType = (TextView) convertView.findViewById(R.id.textTechName);
        final ImageView imgCRImage = (ImageView) convertView.findViewById(R.id.imgTechImage);
        final ImageView imgCRView = (ImageView) convertView.findViewById(R.id.imgTechView);
        final ImageView imageCRRemark = (ImageView) convertView.findViewById(R.id.imageTechRemark);
        final ImageView imageCRNotSubmitted = (ImageView) convertView.findViewById(R.id.imageTechNotSubmitted);
        final ImageView imageCRSubmitted = (ImageView) convertView.findViewById(R.id.imageTechSubmitted);
        final ImageView imgTechNC = (ImageView) convertView.findViewById(R.id.imgTechNC);



        textCRPictureType.setText(technicalImage.getPictureType());

        if (technicalImage.getPhoto() != null
                && !technicalImage.getPhoto().equals("")
                && !technicalImage.getPhoto().equals("null")) {

            //if (classroom.isImageChanged()) {
            Picasso.with((Context) context).load(Utility.getTempFile(context, "ins_image_" + groupPosition + "_" + childPosition))
                    .skipMemoryCache()
                    .resize(100, 100)
                    .placeholder(R.drawable.load_icon).into(imgCRImage);
                /*} else {
                    Picasso.with((Context) context).load(classroom.getImageUrl())
                            .resize(100, 100)
                            .placeholder(R.drawable.load_icon).into(imgCRImage);
                }
*/
            imgCRImage.setTag(null);
        } else {

            if (technicalImage.isImageChanged()) {
                Picasso.with((Context) context).load(Utility.getTempFile(context, "ins_image_" + groupPosition + "_" + childPosition))
                        .skipMemoryCache()
                        .resize(100, 100)
                        .placeholder(R.drawable.load_icon).into(imgCRImage);
            } else {
                imgCRImage.setImageResource(R.drawable.upload_pic_36_36);
            }

            imgCRImage.setTag(R.drawable.upload_pic_36_36);
        }

        if (imgCRImage.getTag() != null && ((Integer) imgCRImage.getTag() == R.drawable.upload_pic_36_36)) {
            imgCRView.setVisibility(View.GONE);
        } else {
            imgCRView.setVisibility(View.VISIBLE);
        }

        imgCRImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgCRImage.getTag() != null && ((Integer) imgCRImage.getTag() == R.drawable.upload_pic_36_36)) {
                    ((InstructorImageActivity) context).selectImage(imgCRImage, groupPosition, childPosition);
                } else {
                    Intent intent = new Intent(context, FullScreenImageViewActivity.class);
                    //if (classroom.isImageChanged()) {
                    intent.putExtra("imgUrl",  "ins_image_" + groupPosition + "_" + childPosition);
                        /*} else {
                            intent.putExtra("imgServerUrl", classroom.getImageUrl());
                        }*/
                    context.startActivity(intent);
                }
            }
        });

        imgCRView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgCRImage.getTag() != null && ((Integer) imgCRImage.getTag() == R.drawable.upload_pic_36_36)) {
                } else {
                    ((InstructorImageActivity) context).selectImage(imgCRImage, groupPosition, childPosition);
                }
            }
        });

        imageCRRemark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((InstructorImageActivity) context).createRemarkDialog(groupPosition, childPosition);
            }
        });

        if (technicalImage.getProcTracker() == 3) {
            imageCRNotSubmitted.setVisibility(View.GONE);
            imageCRSubmitted.setVisibility(View.VISIBLE);
        } else {
            imageCRNotSubmitted.setVisibility(View.VISIBLE);
            imageCRSubmitted.setVisibility(View.GONE);
        }

        imageCRNotSubmitted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((InstructorImageActivity) context).saveClassrooms(groupPosition, childPosition);
            }
        });



        if(listDataChild.get(this.listDataHeader.get(groupPosition)).get(childPosition).getNc()==0){
            imgTechNC.setImageResource(R.drawable.no);
        }else {
            imgTechNC.setImageResource(R.drawable.yes);
        }

        imgTechNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ncValue=0;
                if (listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).getNc()==0){

                    listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).setNc(1);

                    // ((TechnicalImage) getChild(groupPosition,childPosition)).setNc(1);
                    // generalDetails.get(position).setNc(1);
                    ncValue=1;
                }else {
                    // generalDetails.get(position).setNc(0);
                    // ((TechnicalImage) getChild(groupPosition,childPosition)).setNc(0);
                    listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).setNc(0);
                    ncValue=0;
                }

                ((InstructorImageActivity)context).changeNC(groupPosition,childPosition,ncValue);
            }
        });

//        if (!(childPosition == getChildrenCount(groupPosition) - 1)) {
//
//
//        } else {
//            Button btn = (Button) convertView.findViewById(R.id.btnCRSubmit);
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ((ClassroomsActivity) context).saveClassrooms(groupPosition, childPosition);
//                    //Toast.makeText(context, "AAA", Toast.LENGTH_SHORT).show();
//                }
//            });


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
