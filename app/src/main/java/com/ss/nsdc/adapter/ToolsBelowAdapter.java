package com.ss.nsdc.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ss.nsdc.R;
import com.ss.nsdc.dao.LabImage;
import com.ss.nsdc.dao.ToolsBelow;
import com.ss.nsdc.main.FullScreenImageViewActivity;
import com.ss.nsdc.main.LabImageActivity;
import com.ss.nsdc.main.ToolsBelowActivity;
import com.ss.nsdc.utility.Utility;

import java.util.List;
import java.util.Map;

/**
 * Created by Radhika on 6/1/2017.
 */

public class ToolsBelowAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listDataHeader;
    private Map<String, List<ToolsBelow>> listDataChild;

    public ToolsBelowAdapter(Context context, List<String> listDataHeader,
                           Map<String, List<ToolsBelow>> listChildData) {
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
        final ToolsBelow technicalImage = (ToolsBelow) getChild(groupPosition, childPosition);

//        if (childPosition == getChildrenCount(groupPosition) - 1) {
//            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = infalInflater.inflate(R.layout.classroom_button_item, null);
//        } else {
        LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = infalInflater.inflate(R.layout.list_below, null);
//        }

        TextView textCRPictureType = (TextView) convertView.findViewById(R.id.textbelowName);
        final ImageView imgCRImage = (ImageView) convertView.findViewById(R.id.imgbelowImage);
        final ImageView imgCRView = (ImageView) convertView.findViewById(R.id.imgbelowView);
        //final ImageView imageCRRemark = (ImageView) convertView.findViewById(R.id.imagebelowRemark);
        final ImageView imageCRNotSubmitted = (ImageView) convertView.findViewById(R.id.imagebelowNotSubmitted);
        final ImageView imageCRSubmitted = (ImageView) convertView.findViewById(R.id.imagebelowSubmitted);
        final ImageView imgTechNC = (ImageView) convertView.findViewById(R.id.imgTechNC);
        final ImageView imgTechRemarks = (ImageView)convertView.findViewById(R.id.imgTechRemarks);



        textCRPictureType.setText(technicalImage.getPictureType());

        if(technicalImage.getPictureType().equalsIgnoreCase("Photograph 1 of tools below 10000")){
         imgTechNC.setVisibility(View.GONE);
        }

        if(technicalImage.getPictureType().equalsIgnoreCase("Photograph 2 of tools below 10000")){
            imgTechNC.setVisibility(View.GONE);
        }

        if(technicalImage.getPictureType().equalsIgnoreCase("Photograph 3 of tools below 10000")){
            imgTechNC.setVisibility(View.GONE);
        }

        if(technicalImage.getPictureType().equalsIgnoreCase("Photograph 4 of tools below 10000")){
            imgTechNC.setVisibility(View.GONE);
        }


        if (technicalImage.getPhoto() != null
                && !technicalImage.getPhoto().equals("")
                && !technicalImage.getPhoto().equals("null")) {

            //if (classroom.isImageChanged()) {
            Picasso.with((Context) context).load(Utility.getTempFile(context, "toolbelow_image_" + groupPosition + "_" + childPosition))
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
                Picasso.with((Context) context).load(Utility.getTempFile(context, "toolbelow_image_" + groupPosition + "_" + childPosition))
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
                    ((ToolsBelowActivity) context).selectImage(imgCRImage, groupPosition, childPosition);
                } else {
                    Intent intent = new Intent(context, FullScreenImageViewActivity.class);
                    //if (classroom.isImageChanged()) {
                    intent.putExtra("imgUrl",  "toolbelow_image_" + groupPosition + "_" + childPosition);
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
                    ((ToolsBelowActivity) context).selectImage(imgCRImage, groupPosition, childPosition);
                }
            }
        });

//        imageCRRemark.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((ToolsBelowActivity) context).createRemarkDialog(groupPosition, childPosition);
//            }
//        });

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
                ((ToolsBelowActivity) context).saveClassrooms(groupPosition, childPosition);
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

                ((ToolsBelowActivity)context).changeNC(groupPosition,childPosition,ncValue);
            }
        });

        imgTechRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ToolsBelowActivity)context).displayTags(groupPosition,childPosition);

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