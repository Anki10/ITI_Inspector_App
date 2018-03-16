package com.ss.nsdc.adapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ss.nsdc.R;
import com.ss.nsdc.activity.AccommodationActivity;
import com.ss.nsdc.activity.EquipmentActivity;
import com.ss.nsdc.activity.GeneralCategoryActivity;
import com.ss.nsdc.constant.AppConstants;
import com.ss.nsdc.dao.ITIDBController;
import com.ss.nsdc.entity.Accommodation;
import com.ss.nsdc.entity.Classroom;
import com.ss.nsdc.entity.GenralInfo;
import com.ss.nsdc.entity.ITLab;
import com.ss.nsdc.entity.InstructorInfo;
import com.ss.nsdc.entity.LandandBuilding;
import com.ss.nsdc.entity.MaterialInfo;
import com.ss.nsdc.entity.PowerSupplyITIModel;
import com.ss.nsdc.entity.Staffing;
import com.ss.nsdc.entity.Proc_Track;
import com.ss.nsdc.entity.TechincalInfo;
import com.ss.nsdc.entity.TradeWiseTool;
import com.ss.nsdc.entity.EquipmentInfo;
import com.ss.nsdc.entity.VideoObjectModel;
import com.ss.nsdc.entity.VideoSectionModel;
import com.ss.nsdc.entity.VideosListModel;
import com.ss.nsdc.entity.WorkshopAreaModel;
import com.ss.nsdc.main.FormActivity;
import com.ss.nsdc.main.PhotoCategoriesActivity;
import com.ss.nsdc.main.SubCategoryActivity;
import com.ss.nsdc.main.SubCategoryITLabActivity;
import com.ss.nsdc.main.SubCategoryAccommodationActivity;
import com.ss.nsdc.main.VideoSectionActivity;
import com.ss.nsdc.utility.DataUtils;
import com.ss.nsdc.utility.UtilityService;

import org.json.JSONArray;
import org.json.JSONObject;

public class CategoryAdapter extends
        RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<String> instituteList;
    private Context context;
    String instituteId, applicationId,inspectionType;
    ProgressDialog ringProgressDialog;
    int imageposition;
    int value;
    ImageView tmpImageView_sync = null;
    UtilityService utility = UtilityService.getInstance();

    UtilityService utilityService = UtilityService.getInstance();

    SharedPreferences sharedPreferences;
    List videoSectionList= new ArrayList<>();




    String yearWiseCollegeIdTrade="";
    boolean  status;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtCat;
        public ImageView imageView1;
        public ImageView imageView_sync;

        public ViewHolder(View v) {
            super(v);
            txtCat = (TextView) v.findViewById(R.id.category);
            imageView1 = (ImageView) v.findViewById(R.id.cat_status);
            imageView_sync = (ImageView) v.findViewById(R.id.cat_sync);
        }
    }

    public CategoryAdapter(ArrayList<String> instituteList, String instituteId) {

        this.instituteList = instituteList;
        this.instituteId = instituteId;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {

        context = parent.getContext();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        // create a new view
        View v = LayoutInflater.from(context).inflate(
                R.layout.category_list_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.txtCat.setText(instituteList.get(position));
        final ITIDBController controller = new ITIDBController(context);
        holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.download));
        if(position == 2){//General
            List<GenralInfo> generalInfoList = controller.getGenInfoList(instituteId);
            Proc_Track syncStatus = controller.getSyncStatus("genInfo");
            if (generalInfoList != null && generalInfoList.size() != 0) {

                if (syncStatus.getProc_track1Count() == 0) {
                    Log.e("status","success");
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
                } else {
                    Log.e("status","fail");
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
                }

            }
            if (syncStatus.getProc_track2Count() > 0) {
                holder.imageView_sync.setVisibility(View.VISIBLE);
            } else {
                holder.imageView_sync.setVisibility(View.GONE);
            }

        }else if(position == 3){//trust
            List<EquipmentInfo> equipmentInfoList = controller.getTrustInfoList(instituteId);
            Proc_Track syncStatus = controller.getSyncStatus("trust");
            if (equipmentInfoList != null && equipmentInfoList.size() != 0) {

                if (syncStatus.getProc_track1Count() == 0) {
                    Log.e("test","ok");
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
                } else {
                    Log.e("test","bye");
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
                }

            }
            if (syncStatus.getProc_track2Count() > 0) {
                Log.e("test","ok1");
                holder.imageView_sync.setVisibility(View.VISIBLE);
            } else {
                Log.e("test","bye1");
                holder.imageView_sync.setVisibility(View.GONE);
            }
        }else if(position == 4){//organization
            List<MaterialInfo> materialInfoList = controller.getOrganisationInfoList(instituteId);
            Proc_Track syncStatus = controller.getSyncStatus("organisation");
            if (materialInfoList != null && materialInfoList.size() != 0) {

                if (syncStatus.getProc_track1Count() == 0) {
                    Log.e("test","ok");
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
                } else {
                    Log.e("test","bye");
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
                }

            }
            if (syncStatus.getProc_track2Count() > 0) {
                Log.e("test","ok1");
                holder.imageView_sync.setVisibility(View.VISIBLE);
            } else {
                Log.e("test","bye1");
                holder.imageView_sync.setVisibility(View.GONE);
            }

           }else if(position == 5) {//premises
            List<Staffing> staffingList = controller.getPremisesInfoList(instituteId);
            Proc_Track syncStatus = controller.getSyncStatus("premises");
            if (staffingList != null && staffingList.size() != 0) {

                if (syncStatus.getProc_track1Count() == 0) {
                    Log.e("test","ok");
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
                } else {
                    Log.e("test","bye");
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
                }

            }
            if (syncStatus.getProc_track2Count() > 0) {
                Log.e("test","ok1");
                holder.imageView_sync.setVisibility(View.VISIBLE);
            } else {
                Log.e("test","bye1");
                holder.imageView_sync.setVisibility(View.GONE);
            }
           }else  if(position == 6){//classroom
            List<Classroom> classroomList = controller.getClassroomInfoList(instituteId);
            Proc_Track syncStatus = controller.getSyncStatus("class");
            if (classroomList != null && classroomList.size() != 0) {

                if (syncStatus.getProc_track1Count() == 0) {
                    Log.e("test","ok");
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
                } else {
                    Log.e("test","bye");
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
                }

            }
            if (syncStatus.getProc_track2Count() > 0) {
                Log.e("test","ok1");
                holder.imageView_sync.setVisibility(View.VISIBLE);
            } else {
                Log.e("test","bye1");
                holder.imageView_sync.setVisibility(View.GONE);
            }

          } else if(position == 0){//trades
            List<Accommodation> accommodationInfoList = controller.getTradesInfoList(instituteId);
            Proc_Track syncStatus = controller.getSyncStatus("trades");
            if (accommodationInfoList != null && accommodationInfoList.size() != 0) {

                if (syncStatus.getProc_track1Count() == 0) {
                    Log.e("test","ok");
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
                } else {
                    Log.e("test","bye");
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
                }

            }
            if (syncStatus.getProc_track2Count() > 0) {
                Log.e("test","ok1"


                );
                holder.imageView_sync.setVisibility(View.VISIBLE);
            } else {
                Log.e("test","bye1");
                holder.imageView_sync.setVisibility(View.GONE);
            }

        }else if(position == 7){//technical
            List<TechincalInfo> techincalInfoList = controller.getTechnicalInfoList(instituteId);
            Proc_Track syncStatus = controller.getSyncStatus("technical");
            if (techincalInfoList != null && techincalInfoList.size() != 0) {

                if (syncStatus.getProc_track1Count() == 0) {
                    Log.e("test","ok");
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
                } else {
                    Log.e("test","bye");
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
                }

            }
            if (syncStatus.getProc_track2Count() > 0) {
                Log.e("test","ok1");
                holder.imageView_sync.setVisibility(View.VISIBLE);
            } else {
                Log.e("test","bye1");
                holder.imageView_sync.setVisibility(View.GONE);
            }
        }else if(position == 8){//instructor
            List<InstructorInfo> instructorInfoList = controller.getInstructorInfoList(instituteId);
            Proc_Track syncStatus = controller.getSyncStatus("instructor");
            if (instructorInfoList != null && instructorInfoList.size() != 0) {

                if (syncStatus.getProc_track1Count() == 0) {
                    Log.e("test","ok");
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
                } else {
                    Log.e("test","bye");
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
                }

            }
            if (syncStatus.getProc_track2Count() > 0) {
                Log.e("test","ok1");
                holder.imageView_sync.setVisibility(View.VISIBLE);
            } else {
                Log.e("test","bye1");
                holder.imageView_sync.setVisibility(View.GONE);
            }
        }else if(position == 9){//land and building
            List<LandandBuilding> landandBuildingInfoList = controller.getLandandBuildingInfoList(instituteId);
            Proc_Track syncStatus = controller.getSyncStatus("landandbuilding");
            if (landandBuildingInfoList != null && landandBuildingInfoList.size() != 0) {

                if (syncStatus.getProc_track1Count() == 0) {
                    Log.e("test","ok");
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
                } else {
                    Log.e("test","bye");
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
                }

            }
            if (syncStatus.getProc_track2Count() > 0) {
                Log.e("test","ok1");
                holder.imageView_sync.setVisibility(View.VISIBLE);
            } else {
                Log.e("test","bye1");
                holder.imageView_sync.setVisibility(View.GONE);
            }

        }else if(position == 10){//lab
            List<ITLab> instructorInfoList = controller.getITLabInfoList(instituteId);
            Proc_Track syncStatus = controller.getSyncStatus("lab");
            if (instructorInfoList != null && instructorInfoList.size() != 0) {

                if (syncStatus.getProc_track1Count() == 0) {
                    Log.e("test","ok");
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
                } else {
                    Log.e("test","bye");
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
                }

            }
            if (syncStatus.getProc_track2Count() > 0) {
                Log.e("test","ok1");
                holder.imageView_sync.setVisibility(View.VISIBLE);
            } else {
                Log.e("test","bye1");
                holder.imageView_sync.setVisibility(View.GONE);
            }
        }else if(position == 11){//power supply
            List powerSupplyITIList = controller.getPowerSupplyITIList(instituteId);
            Proc_Track syncStatus = controller.getSyncStatus("powerSupplyITI");
            if (powerSupplyITIList != null && powerSupplyITIList.size() != 0) {
                if (syncStatus.getProc_track1Count() == 0) { // New Data
                       holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
                } else {
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
                }
            }
            if (syncStatus.getProc_track2Count() > 0) {
                holder.imageView_sync.setVisibility(View.VISIBLE);
            } else {



                holder.imageView_sync.setVisibility(View.GONE);
            }

        }else if(position == 12){
            List<WorkshopAreaModel> workshopAreaList = controller.getWorkshopAreaList(instituteId);
            Proc_Track syncStatus = controller.getSyncStatus("workshopArea");
            if (workshopAreaList != null && workshopAreaList.size() != 0) {

                if (syncStatus.getProc_track1Count() == 0) {
                    Log.e("test","ok");
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
                } else {
                    Log.e("test","bye");
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
                }

            }
            if (syncStatus.getProc_track2Count() > 0) {
                Log.e("test","ok1");
                holder.imageView_sync.setVisibility(View.VISIBLE);
            } else {
                Log.e("test","bye1");
                holder.imageView_sync.setVisibility(View.GONE);
            }

        }else if(position == 1){// trade wise tools
            List<Accommodation> accommodationInfoList = controller.getTradesWiseInfoList(instituteId);
            Proc_Track syncStatus = controller.getSyncStatus("tradesWise");
            if (accommodationInfoList != null && accommodationInfoList.size() != 0) {

                if (syncStatus.getProc_track1Count() == 0) {
                    status = true;
                    Log.e("test",String.valueOf(status));
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
                } else {
                    Log.e("test","bye");

                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
                }

            }
            if (syncStatus.getProc_track2Count() > 0) {
                Log.e("test","ok1");
                holder.imageView_sync.setVisibility(View.VISIBLE);
            } else {
                Log.e("test","bye1");
                holder.imageView_sync.setVisibility(View.GONE);
            }
        }else if(position == 13){// Tools Above 10k
            List<Accommodation> toolsAboveTenkInfoList = controller.getToolsAboveTenkInfoList(instituteId);
            Proc_Track syncStatus = controller.getSyncStatus("toolsAbove10K");
            if (toolsAboveTenkInfoList != null && toolsAboveTenkInfoList.size() != 0) {

                if (syncStatus.getProc_track1Count() == 0) {
                    Log.e("test","ok");
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
                } else {
                    Log.e("test","bye");
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
                }

            }
            if (syncStatus.getProc_track2Count() > 0) {
                Log.e("test","ok1");
                holder.imageView_sync.setVisibility(View.VISIBLE);
            } else {
                Log.e("test","bye1");
                holder.imageView_sync.setVisibility(View.GONE);
            }
        }else if(position == 15){//video upload

            //holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.download));

            HashMap videoData = DataUtils.loadMap(context);
            boolean isVideoDataDownloaded = videoData.containsKey(instituteId);
            if(isVideoDataDownloaded)
            {    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start
            ));
            }else
            {
                holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.download));
            }

        } else if(position == 14){//photograph uplaod

            ITIDBController imagesDAO = new ITIDBController(context);
            boolean isImageDataDownloaded = imagesDAO.isImageDataDownloaded(instituteId);
            Proc_Track syncStatus = imagesDAO.getSyncStatusImage();
            if (isImageDataDownloaded) {

                if (syncStatus.getProc_track1Count() == 0) { // New Data
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
                } else {
                    holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.start));
                }

            }
            if (syncStatus.getProc_track2Count() > 0) {
                holder.imageView_sync.setVisibility(View.VISIBLE);
            } else {
                holder.imageView_sync.setVisibility(View.GONE);
            }
            imagesDAO.close();

        }



        controller.close();

        holder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == 2){
                    ITIDBController controller = new ITIDBController(context);
                    List<GenralInfo> generalInfoList = controller.getGenInfoList(instituteId);
                    controller.close();
                    if (generalInfoList != null && generalInfoList.size() != 0) {

//                        if(generalInfoList.get(0).getProc_tracker() == 1) {

                        Intent intent = new Intent(context, EquipmentActivity.class);
                        context.startActivity(intent);

    /*                        if (!sharedPreferences.getBoolean("isStarted", false)) {
                                Toast.makeText(context, "Please start inspection first", Toast.LENGTH_SHORT).show();
                            } else if (sharedPreferences.getBoolean("isCompleted", false)) {
                                Toast.makeText(context, "You have completed the inspection", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intent = new Intent(context, FormActivity.class);
                                intent.putExtra("Category", "genInfo");
                                intent.putExtra("YearWiseCollegeId", instituteId);
                                context.startActivity(intent);
                            }*/

//                        }else if(generalInfoList.get(0).getProc_tracker() == 2){
//                            holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.progress));
//                        }else if(generalInfoList.get(0).getProc_tracker() == 3){
//                            holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
//                        }

                    } else {
                        imageposition = position;
                        new UpdateData().execute(new String[]{AppConstants.URL_GEN_INFO, "genralInfo", instituteId});
                    }
                }else if(position == 3){
                    ITIDBController controller = new ITIDBController(context);
                    List<EquipmentInfo> equipmentInfoList = controller.getTrustInfoList(instituteId);
                    controller.close();
                    if (equipmentInfoList != null && equipmentInfoList.size() != 0) {

                        Intent intent = new Intent(context, FormActivity.class);
                        intent.putExtra("Category", "trust");
                        intent.putExtra("YearWiseCollegeId", instituteId);
                        context.startActivity(intent);

              /*          if (!sharedPreferences.getBoolean("isStarted", false)) {
                            Toast.makeText(context, "Please start inspection first", Toast.LENGTH_SHORT).show();
                        } else if (sharedPreferences.getBoolean("isCompleted", false)) {
                            Toast.makeText(context, "You have completed the inspection", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(context, FormActivity.class);
                            intent.putExtra("Category", "trust");
                            intent.putExtra("YearWiseCollegeId", instituteId);
                            context.startActivity(intent);

                        }*/

                    } else {
                        imageposition = position;
                        new UpdateData().execute(new String[]{AppConstants.URL_TRUST_INFO, "trust", instituteId});
                    }
                }else if(position == 4){
                    ITIDBController controller = new ITIDBController(context);
                    List<MaterialInfo> materialInfoList = controller.getOrganisationInfoList(instituteId);
                    controller.close();
                    if (materialInfoList != null && materialInfoList.size() != 0) {

                        Intent intent = new Intent(context, FormActivity.class);
                        intent.putExtra("Category", "organisation");
                        intent.putExtra("YearWiseCollegeId", instituteId);
                        context.startActivity(intent);

              /*          if (!sharedPreferences.getBoolean("isStarted", false)) {
                            Toast.makeText(context, "Please start inspection first", Toast.LENGTH_SHORT).show();
                        } else if (sharedPreferences.getBoolean("isCompleted", false)) {
                            Toast.makeText(context, "You have completed the inspection", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(context, FormActivity.class);
                            intent.putExtra("Category", "organisation");
                            intent.putExtra("YearWiseCollegeId", instituteId);
                            context.startActivity(intent);

                        }*/

                    } else {
                        imageposition = position;
                        new UpdateData().execute(new String[]{AppConstants.URL_ORGANISATION_INFO, "organisation", instituteId});
                    }
                }else if(position == 5){
                    ITIDBController controller = new ITIDBController(context);
                    List<Staffing> staffingList = controller.getPremisesInfoList(instituteId);
                    controller.close();
                    if (staffingList != null && staffingList.size() != 0) {

                        Intent intent = new Intent(context, FormActivity.class);
                        intent.putExtra("Category", "premises");
                        intent.putExtra("YearWiseCollegeId", instituteId);
                        context.startActivity(intent);

                    /*    if (!sharedPreferences.getBoolean("isStarted", false)) {
                            Toast.makeText(context, "Please start inspection first", Toast.LENGTH_SHORT).show();
                        } else if (sharedPreferences.getBoolean("isCompleted", false)) {
                            Toast.makeText(context, "You have completed the inspection", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(context, FormActivity.class);
                            intent.putExtra("Category", "premises");
                            intent.putExtra("YearWiseCollegeId", instituteId);
                            context.startActivity(intent);

                       }*/

                    } else {
                        imageposition = position;
                        new UpdateData().execute(new String[]{AppConstants.URL_PREMISES_SHIFTING, "premises", instituteId});
                    }
                }else if(position == 6){
                    ITIDBController controller = new ITIDBController(context);
                    List<Classroom> premisesList = controller.getClassroomInfoList(instituteId);
                    controller.close();
                    if (premisesList != null && premisesList.size() != 0) {

                        Intent intent = new Intent(context, SubCategoryActivity.class);
                        intent.putExtra("Category", "class");
                        intent.putExtra("YearWiseCollegeId", instituteId);
                        context.startActivity(intent);

           /*             if (!sharedPreferences.getBoolean("isStarted", false)) {
                            Toast.makeText(context, "Please start inspection first", Toast.LENGTH_SHORT).show();
                        } else if (sharedPreferences.getBoolean("isCompleted", false)) {
                            Toast.makeText(context, "You have completed the inspection", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(context, SubCategoryActivity.class);
                            intent.putExtra("Category", "class");
                            intent.putExtra("YearWiseCollegeId", instituteId);
                            context.startActivity(intent);

                       }*/

                    } else {
                        imageposition = position;
                        new UpdateData().execute(new String[]{AppConstants.URL_CLASSROOM, "class", instituteId});
                    }
                }else if(position == 0){
                    ITIDBController controller = new ITIDBController(context);
                    List<Accommodation> accommodationInfoList = controller.getTradesInfoList(instituteId);
                    controller.close();
                    if (accommodationInfoList != null && accommodationInfoList.size() != 0) {

                        Intent intent = new Intent(context, AccommodationActivity.class);
                        context.startActivity(intent);

                    /*    if (!sharedPreferences.getBoolean("isStarted", false)) {
                            Toast.makeText(context, "Please start inspection first", Toast.LENGTH_SHORT).show();
                        } else if (sharedPreferences.getBoolean("isCompleted", false)) {
                            Toast.makeText(context, "You have completed the inspection", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(context, SubCategoryAccommodationActivity.class);
                            intent.putExtra("Category", "trades");
                            intent.putExtra("YearWiseCollegeId", instituteId);
                            context.startActivity(intent);

                       }*/

                    } else {
                        imageposition = position;
                        new UpdateData().execute(new String[]{AppConstants.URL_TRADES_INFO, "trades", instituteId});
                    }

                }else if(position == 7){
                    ITIDBController controller = new ITIDBController(context);
                    List<TechincalInfo> technicalInfoList = controller.getTechnicalInfoList(instituteId);
                    controller.close();
                    if (technicalInfoList != null && technicalInfoList.size() != 0) {

                        if (!sharedPreferences.getBoolean("isStarted", false)) {
                            Toast.makeText(context, "Please start inspection first", Toast.LENGTH_SHORT).show();
                        } else if (sharedPreferences.getBoolean("isCompleted", false)) {
                            Toast.makeText(context, "You have completed the inspection", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(context, SubCategoryActivity.class);
                            intent.putExtra("Category", "technical");
                            intent.putExtra("YearWiseCollegeId", instituteId);
                            context.startActivity(intent);

                        }

                    } else {
                        imageposition = position;
                        new UpdateData().execute(new String[]{AppConstants.URL_TECHNICAL_STAFF_INFO, "technical", instituteId});
                    }
                }else if(position == 8){
                    ITIDBController controller = new ITIDBController(context);
                    List<InstructorInfo> instructorInfoList = controller.getInstructorInfoList(instituteId);
                    controller.close();
                    if (instructorInfoList != null && instructorInfoList.size() != 0) {

                        if (!sharedPreferences.getBoolean("isStarted", false)) {
                            Toast.makeText(context, "Please start inspection first", Toast.LENGTH_SHORT).show();
                        } else if (sharedPreferences.getBoolean("isCompleted", false)) {
                            Toast.makeText(context, "You have completed the inspection", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(context, SubCategoryActivity.class);
                            intent.putExtra("Category", "instructor");
                            intent.putExtra("YearWiseCollegeId", instituteId);
                            context.startActivity(intent);

                      }

                    } else {
                        imageposition = position;
                        new UpdateData().execute(new String[]{AppConstants.URL_INSTRUCTOR_STAFF_INFO, "instructor", instituteId});
                    }
                }else if(position == 9){
                    ITIDBController controller = new ITIDBController(context);
                    List<LandandBuilding> landandBuildingInfoList = controller.getLandandBuildingInfoList(instituteId);
                    controller.close();
                    if (landandBuildingInfoList != null && landandBuildingInfoList.size() != 0) {

                        if (!sharedPreferences.getBoolean("isStarted", false)) {
                            Toast.makeText(context, "Please start inspection first", Toast.LENGTH_SHORT).show();
                        } else if (sharedPreferences.getBoolean("isCompleted", false)) {
                            Toast.makeText(context, "You have completed the inspection", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(context, FormActivity.class);
                            intent.putExtra("Category", "land");
                            intent.putExtra("YearWiseCollegeId", instituteId);
                            context.startActivity(intent);

                       }

                    } else {
                        imageposition = position;
                        new UpdateData().execute(new String[]{AppConstants.URL_LAND_AND_BUILDING_DETAILS, "land", instituteId});
                    }

                }else if(position == 10){
                    ITIDBController controller = new ITIDBController(context);
                    List<ITLab> itLabInfoList = controller.getITLabInfoList(instituteId);
                    controller.close();
                    if (itLabInfoList != null && itLabInfoList.size() != 0) {

                        if (!sharedPreferences.getBoolean("isStarted", false)) {
                            Toast.makeText(context, "Please start inspection first", Toast.LENGTH_SHORT).show();
                        } else if (sharedPreferences.getBoolean("isCompleted", false)) {
                            Toast.makeText(context, "You have completed the inspection", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(context, SubCategoryITLabActivity.class);
                            intent.putExtra("Category", "lab");
                            intent.putExtra("YearWiseCollegeId", instituteId);
                            context.startActivity(intent);

                        }

                    } else {
                        imageposition = position;
                        new UpdateData().execute(new String[]{AppConstants.URL_IT_LAB_INFO, "lab", instituteId});
                    }
                }else if(position == 11){
                    ITIDBController controller = new ITIDBController(context);
                    List<PowerSupplyITIModel> powerSupplyITIList = controller.getPowerSupplyITIList(instituteId);
                    controller.close();
                    if (powerSupplyITIList != null && powerSupplyITIList.size() != 0) {
//                        if(powerSupplyITIList.get(0).getProc_tracker() == 1) {
                            if (!sharedPreferences.getBoolean("isStarted", false)) {
                                Toast.makeText(context, "Please start inspection first", Toast.LENGTH_SHORT).show();
                            } else if (sharedPreferences.getBoolean("isCompleted", false)) {
                                Toast.makeText(context, "You have completed the inspection", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intent = new Intent(context, FormActivity.class);
                                intent.putExtra("Category", "powerSupplyITI");
                                intent.putExtra("YearWiseCollegeId", instituteId);
                                context.startActivity(intent);
                           }
//                        }else if(powerSupplyITIList.get(0).getProc_tracker() == 2){
//                            holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.progress));
//                        }else if(powerSupplyITIList.get(0).getProc_tracker() == 3){
//                            holder.imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.complete));
//                        }
                    } else {
                        imageposition = position;
                        new UpdateData().execute(new String[]{AppConstants.URL_POWER_SUPPLY, "powerSupplyITI", instituteId});
                    }

                }else if(position == 12){
                    ITIDBController controller = new ITIDBController(context);
                    List<WorkshopAreaModel> WorkshopAreaList = controller.getWorkshopAreaList(instituteId);
                    controller.close();
                    if (WorkshopAreaList != null && WorkshopAreaList.size() != 0) {

                        if (!sharedPreferences.getBoolean("isStarted", false)) {
                            Toast.makeText(context, "Please start inspection first", Toast.LENGTH_SHORT).show();
                        } else if (sharedPreferences.getBoolean("isCompleted", false)) {
                            Toast.makeText(context, "You have completed the inspection", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(context, SubCategoryActivity.class);
                            intent.putExtra("Category", "workshopArea");
                            intent.putExtra("YearWiseCollegeId", instituteId);
                            context.startActivity(intent);
                        }

                    } else {
                        imageposition = position;
                        new UpdateData().execute(new String[]{AppConstants.URL_WORKSHOP_AREA, "workshopArea", instituteId});
                    }

                }else if(position == 1){
                    ITIDBController controller = new ITIDBController(context);
                    List<Accommodation> accommodationInfoList = controller.getTradesWiseInfoList(instituteId);
                    controller.close();
                    if (accommodationInfoList != null && accommodationInfoList.size() != 0) {



                        if (!sharedPreferences.getBoolean("isStarted", false)) {
                            Toast.makeText(context, "Please start inspection first", Toast.LENGTH_SHORT).show();
                        } else if (sharedPreferences.getBoolean("isCompleted", false)) {
                            Toast.makeText(context, "You have completed the inspection", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(context, SubCategoryActivity.class);
                            intent.putExtra("Category", "tradesWise");
                            intent.putExtra("YearWiseCollegeId", instituteId);
                            context.startActivity(intent);

                       }

                    } else {
                        imageposition = position;
                        new UpdateData().execute(new String[]{AppConstants.URL_TRADESWISE_INFO, "tradesWise", instituteId});
                    }
                }else if(position == 13){

                    ITIDBController controller = new ITIDBController(context);
                    List<Accommodation> accommodationInfoList = controller.getToolsAboveTenkInfoList(instituteId);
                    controller.close();
                    if (accommodationInfoList != null && accommodationInfoList.size() != 0) {

                        if (!sharedPreferences.getBoolean("isStarted", false)) {
                            Toast.makeText(context, "Please start inspection first", Toast.LENGTH_SHORT).show();
                        } else if (sharedPreferences.getBoolean("isCompleted", false)) {
                            Toast.makeText(context, "You have completed the inspection", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent intent = new Intent(context, SubCategoryActivity.class);
                            intent.putExtra("Category", "toolsAbove10K");
                            intent.putExtra("YearWiseCollegeId", instituteId);
                            context.startActivity(intent);

                       }

                    } else
                    {
//                        Log.e("status",String.valueOf(status));
//                        if(status) {
                        imageposition = position;

                            new UpdateData().execute(new String[]{AppConstants.URL_TRADESWISE_INFO, "toolsAbove10k", instituteId});
//                        }else {
//                            Toast.makeText(context,"Please submit Tradewise Tools before downloading Tools Above 10k",Toast.LENGTH_SHORT).show();
//                        }
                    }
                }else if(position == 14){
                   ITIDBController controller = new ITIDBController(context);
                    boolean isImageDataDownloaded = controller.isImageDataDownloaded(instituteId);
                    if(isImageDataDownloaded){

                        if (!sharedPreferences.getBoolean("isStarted", false)) {
                            Toast.makeText(context, "Please start inspection first", Toast.LENGTH_SHORT).show();

                        } else if (sharedPreferences.getBoolean("isCompleted", false)) {
                            Toast.makeText(context, "You have completed the inspection", Toast.LENGTH_SHORT).show();
                        } else {

                            Intent intent = new Intent(context, PhotoCategoriesActivity.class);
                            intent.putExtra("Category", AppConstants.TEXT_UPLOAD_IMAGES);
                            intent.putExtra("YearWiseCollegeId", instituteId);
                            intent.putExtra("status",status);
                            Log.e("status", String.valueOf(status));
                            context.startActivity(intent);
                       }
                    }else {

                            new DownloadGeneralInfoImageData().execute();
                            controller.addUploadImagesData(instituteId);
                    }
                }else if(position == 15){
                    HashMap videoData = DataUtils.loadMap(context);
                    boolean isVideoDataDownloaded = videoData.containsKey(instituteId);
                    if(isVideoDataDownloaded) {
                        if (!sharedPreferences.getBoolean("isStarted", false)) {
                            Toast.makeText(context, "Please start inspection first", Toast.LENGTH_SHORT).show();
                        } else if (sharedPreferences.getBoolean("isCompleted", false)) {
                            Toast.makeText(context, "You have completed the inspection", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(context, VideoSectionActivity.class);
                            intent.putExtra("Category", "");
                            intent.putExtra("YearWiseCollegeId", instituteId);
                            intent.putExtra("applicationId", applicationId);
                            intent.putExtra("inspectionType", inspectionType);
                            context.startActivity(intent);
                       }
                    }else
                    {
//                       if(status) {

                            new DownloadVideoData().execute();
//                        }else {
//                            Toast.makeText(context,"Please submit Tools above 10K in Trade wise Tools section before downloading Video section",Toast.LENGTH_SHORT).show();
//                        }

                    }
                }
            }
        });

        holder.imageView_sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tmpImageView_sync = holder.imageView_sync;

                if(position == 0){//Accommodation


                }

                if(position == 1){//TradeWiseTools

                    if(utility.getConnectivityStatus(context)){
                        ITIDBController controller = new ITIDBController(context);
                        List<TradeWiseTool> tradeWiseToolList = controller.getTradesWiseToolToDataSync(instituteId);

                    }

                }

                if(position == 2){

                    if(utility.getConnectivityStatus(context)){
                        ITIDBController controller = new ITIDBController(context);
                        List<GenralInfo> genralInfoList = controller.getGenInfoList(instituteId);
                        JSONObject datatoSync = utility.getGeneralInfoSyncData(genralInfoList.get(0));
                        new ExecuteSyncOperation().execute(new String[]{AppConstants.URL_GEN_INFO_SET,
                                datatoSync.toString(), "general"});
                    }
                }

                if(position == 3){
                    if(utility.getConnectivityStatus(context)){
                        ITIDBController controller = new ITIDBController(context);
                        List<EquipmentInfo> equipmentInfoList = controller.getTrustInfoList(instituteId);
                        JSONObject datatoSync = utility.getTrustInfoSyncData(equipmentInfoList.get(0));
                        new ExecuteSyncOperation().execute(new String[]{AppConstants.URL_TRUST_SET,
                                datatoSync.toString(), "trust"});
                    }
                }
                if(position == 4){
                    if(utility.getConnectivityStatus(context)){
                        ITIDBController controller = new ITIDBController(context);
                        List<MaterialInfo> materialInfoList = controller.getOrganisationInfoList(instituteId);
                        JSONObject datatoSync = utility.getOrganisationInfoSyncData(materialInfoList.get(0));
                        new ExecuteSyncOperation().execute(new String[]{AppConstants.URL_ORG_SET,
                                datatoSync.toString(), "Org"});
                    }
                }
                if(position == 5){
                    if(utility.getConnectivityStatus(context)){
                        ITIDBController controller = new ITIDBController(context);
                        List<Staffing> staffingInfoList = controller.getPremisesInfoList(instituteId);
                        JSONObject datatoSync = utility.getPremisesInfoSyncData(staffingInfoList.get(0));
                        new ExecuteSyncOperation().execute(new String[]{AppConstants.URL_PREMISES_SET,
                                datatoSync.toString(), "premises"});
                    }
                }

                if(position == 6){
                    if(utility.getConnectivityStatus(context)){
                        ITIDBController controller = new ITIDBController(context);
                        List<Classroom> classroomList = controller.getClassroomInfoList(instituteId);
                        JSONObject datatoSync = utility.getClasroomInfoSyncData(classroomList);
                        new ExecuteSyncOperation().execute(new String[]{AppConstants.URL_ClASSROOM_SET,
                                datatoSync.toString(), "class"});
                    }
                }

                if(position == 7){
                    if(utility.getConnectivityStatus(context)){
                        ITIDBController controller = new ITIDBController(context);
                        List<TechincalInfo> techincalInfoList = controller.getTechnicalInfoList(instituteId);
                        JSONObject datatoSync = utility.getTechnicalInfoSyncData(techincalInfoList);
                        new ExecuteSyncOperation().execute(new String[]{AppConstants.URL_TECHNICAL_SET,
                                datatoSync.toString(), "technical"});
                    }
                }


                if(position == 8){
                    if(utility.getConnectivityStatus(context)){
                        ITIDBController controller = new ITIDBController(context);
                        List<InstructorInfo> instructorInfoList = controller.getInstructorInfoList(instituteId);
                        JSONObject datatoSync = utility.getInstructorInfoSyncData(instructorInfoList);
                        new ExecuteSyncOperation().execute(new String[]{AppConstants.URL_INSTRUCTOR_SET,
                                datatoSync.toString(), "instructor"});
                    }
                }
                if(position == 9){
                    if(utility.getConnectivityStatus(context)){
                        ITIDBController controller = new ITIDBController(context);
                        List<LandandBuilding> landandBuildingList = controller.getLandandBuildingInfoList(instituteId);
                        JSONObject datatoSync = utility.getLandInfoSyncData(landandBuildingList.get(0));
                        new ExecuteSyncOperation().execute(new String[]{AppConstants.URL_LAND_AND_BUILDING_SET,
                                datatoSync.toString(), "land"});
                    }
                }

            }
        });





    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return instituteList.size();
    }

    //=========================================================OFFLINE SYNCHRONIZING ++++++++++++++++++++++++++++++++++++++++++++++++++++++


    class ExecuteSyncOperation extends AsyncTask<String, Integer, JSONObject> {
        String type;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(context, android.R.style.Theme_Holo), null,
                    " Data Synchronizing ...", true);
            ringProgressDialog.setCancelable(false);
            ringProgressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected JSONObject doInBackground(String... data) {
            String response = "";
            type = data[2];
            JSONObject response_json = null;
            try {
                URL url = new URL(data[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json");
                String str = data[1];
                Log.e("RESULT","RESULT IS "+ str);
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
                } else if (responsecode == HttpURLConnection.HTTP_CLIENT_TIMEOUT) {
                    response_json = new JSONObject();
                    response_json.put("error", "Connection to server lost..");
                }
                response_json = new JSONObject(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response_json;

        }

        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);
            ringProgressDialog.cancel();
            try {

                int status = result.optInt("status");
                if(status == 0){
                    Toast.makeText(context, "Data Sync Successful", Toast.LENGTH_LONG).show();
                    ITIDBController controller = new ITIDBController(context);
                    boolean updation_status = controller.updateSyncDataStatus(type, instituteId);
                    controller.close();
                    if (updation_status) {
                        if (tmpImageView_sync != null)
                            tmpImageView_sync.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(context, "Error in syncing data..", Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(context,"Data Sync Failed .. ",Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

//------------------------------------------------------------Update data get api-----------------------------------------------------

    class UpdateData extends AsyncTask<String, Integer, JSONObject> {
        String yearWiseCollegeId;
        String type;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(
                    context, android.R.style.Theme_Holo), null, "Downloading data....", true);
            ringProgressDialog.setCancelable(false);
            ringProgressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected JSONObject doInBackground(String... urldetail) {
            yearWiseCollegeId = urldetail[2];
            Log.e("yearwisecollegId",yearWiseCollegeId);
            type = urldetail[1];

            JSONObject response = updateSubCategoryList(urldetail);
            return response;
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);
            try {
                ringProgressDialog.cancel();
                int status = result.optInt("status");
                if (status == 0) {
                    if (type.equalsIgnoreCase("genralInfo")) {
                        ITIDBController sqlite = new ITIDBController(context);
                        sqlite.addGenInfoData(result, yearWiseCollegeId);
                        sqlite.close();
                    }else if (type.equalsIgnoreCase("trust")) {
                        ITIDBController sqlite = new ITIDBController(context);
                        sqlite.addTrustInfoData(result, yearWiseCollegeId);
                        sqlite.close();
                    }else if (type.equalsIgnoreCase("organisation")) {
                        ITIDBController sqlite = new ITIDBController(context);
                        sqlite.addOrganisationInfoData(result, yearWiseCollegeId);
                        sqlite.close();
                    }else if (type.equalsIgnoreCase("technical")) {
                        ITIDBController sqlite = new ITIDBController(context);
                        sqlite.addTechnicalInfoData(result, yearWiseCollegeId);
                        sqlite.close();
                    }else if (type.equalsIgnoreCase("instructor")) {
                        ITIDBController sqlite = new ITIDBController(context);
                        sqlite.addInstructorInfoData(result, yearWiseCollegeId);
                        sqlite.close();
                    }else if (type.equalsIgnoreCase("trades")) {
                        ITIDBController sqlite = new ITIDBController(context);
                        sqlite.addTradesInfoData(result, yearWiseCollegeId);
                        sqlite.close();
                    }else if (type.equalsIgnoreCase("lab")) {
                        ITIDBController sqlite = new ITIDBController(context);
                        sqlite.addITLabInfoData(result, yearWiseCollegeId);
                        sqlite.close();
                    }else if (type.equalsIgnoreCase("land")) {
                        ITIDBController sqlite = new ITIDBController(context);
                        sqlite.addLandandbuildingInfoData(result, yearWiseCollegeId);
                        sqlite.close();
                    }else if (type.equalsIgnoreCase("premises")) {
                        ITIDBController sqlite = new ITIDBController(context);
                        sqlite.addPremisesShiftingInfoData(result, yearWiseCollegeId);
                        sqlite.close();
                    }else if (type.equalsIgnoreCase("class")) {
                        ITIDBController sqlite = new ITIDBController(context);
                        sqlite.addClassroomInfoData(result, yearWiseCollegeId);
                        sqlite.close();
                    }else if (type.equalsIgnoreCase("tradesWise")) {
                        ITIDBController sqlite = new ITIDBController(context);
                        sqlite.addTradesWiseInfoData(result, yearWiseCollegeId);
                        sqlite.close();

                        yearWiseCollegeIdTrade=yearWiseCollegeId;
                        new UpdateTradeWiseTools().execute(result.toString());

                    }else if (type.equalsIgnoreCase("toolsAbove10k")) {

                        ITIDBController sqlite = new ITIDBController(context);
                        sqlite.addToolsAboveTenKInfoData(result, yearWiseCollegeId);
                        sqlite.close();
                        yearWiseCollegeIdTrade=yearWiseCollegeId;
                        ITIDBController controller = new ITIDBController(context);
                        controller.deleteToolsAbove(context);
                        new UpdateToolsAboveTen().execute(result.toString());

                    }else if (type.equalsIgnoreCase("powerSupplyITI")) {
                        ITIDBController sqlite = new ITIDBController(context);
                        sqlite.addPowerSupplyITIData(result, yearWiseCollegeId);
                        sqlite.close();
                    }else if(type.equalsIgnoreCase("workshopArea")){
                        ITIDBController sqlite = new ITIDBController(context);
                        sqlite.addWorkshopAreaData(result, yearWiseCollegeId);
                        sqlite.close();
                    }
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Data is not available for selected section in Application form of Institute.", Toast.LENGTH_LONG).show();
                }

            }catch (Exception e){
                e.printStackTrace();
            }



        }
    }







    class UpdateTradeWiseTools extends AsyncTask<String,Integer,JSONObject> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(
                    context, android.R.style.Theme_Holo), null, "Please wait...", true);
            ringProgressDialog.setCancelable(false);
            ringProgressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected JSONObject doInBackground(String... user) {

            JSONObject responseJSON = null;


            try {
                responseJSON= new JSONObject(user[0]);
                JSONObject payload = responseJSON.optJSONObject("payload");
                JSONArray tradeArray = payload.optJSONArray("FinalTrade");
                //List<String> tradeIDList= new ArrayList<>();
                for (int i = 0; i < tradeArray.length(); i++) {
                    JSONObject tradeObj = tradeArray.getJSONObject(i);
                    String tradeId=tradeObj.optString("tradeid");
                    String response = "";

                    URL url = new URL(AppConstants.URL_TRADE_WISE_DETAILS);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                    String str = "YearWiseCollegeId=" + yearWiseCollegeIdTrade + "&tradeId=" + tradeId;
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

                    ITIDBController sqlite = new ITIDBController(context);
                    sqlite.addTradeWiseToolInfoData(responseJSON, yearWiseCollegeIdTrade,tradeId);
                    sqlite.close();


                }

            }catch (Exception e)
            {
                ringProgressDialog.cancel();
                ringProgressDialog.dismiss();

            }



            return responseJSON;
        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);

        //    Toast.makeText(context,"done",Toast.LENGTH_LONG).show();
            ringProgressDialog.cancel();
            ringProgressDialog.dismiss();

        }
    }


    class UpdateToolsAboveTen extends AsyncTask<String,Integer,JSONObject> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(
                    context, android.R.style.Theme_Holo), null, "Please wait...", true);
            ringProgressDialog.setCancelable(false);
            ringProgressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected JSONObject doInBackground(String... user) {

            JSONObject responseJSON = null;


            try {
                responseJSON= new JSONObject(user[0]);
                JSONObject payload = responseJSON.optJSONObject("payload");
                JSONArray tradeArray = payload.optJSONArray("FinalTrade");
                //List<String> tradeIDList= new ArrayList<>();
                for (int i = 0; i < tradeArray.length(); i++) {
                    JSONObject tradeObj = tradeArray.getJSONObject(i);
                    String tradeId=tradeObj.optString("tradeid");
                    String response = "";

                    URL url = new URL(AppConstants.URL_TOOLSABOVE_INFO);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                    String str = "YearWiseCollegeId=" + yearWiseCollegeIdTrade + "&tradeId=" + tradeId;
                    Log.e("str",str);
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

          /*          ITIDBController sqlite = new ITIDBController(context);
                    sqlite.addTradeWiseToolInfoData(responseJSON, yearWiseCollegeIdTrade,tradeId);
                    sqlite.close();
*/
                   ITIDBController sqlite = new ITIDBController(context);
                    sqlite.addToolabove10000InfoData(responseJSON,yearWiseCollegeIdTrade,tradeId);
                    sqlite.close();

                }

            }catch (Exception e)
            {
                ringProgressDialog.cancel();
                ringProgressDialog.dismiss();

            }



            return responseJSON;
        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);

            //    Toast.makeText(context,"done",Toast.LENGTH_LONG).show();
            ringProgressDialog.cancel();
            ringProgressDialog.dismiss();


        }
    }





    public JSONObject updateSubCategoryList(String[] urldetail) {
        String response = "";
        JSONObject response_json = null;
        try {
            URL url = new URL(urldetail[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            String str = "YearWiseCollegeId=" + urldetail[2];
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

    //------------------------------------------------------DOWNLOAD PHOTO DATA-----------------------------------------------------

    class  DownloadGeneralInfoImageData extends AsyncTask<String, Integer, JSONObject>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(
                    context, android.R.style.Theme_Holo), null, "Downloading data....", true);
            ringProgressDialog.setCancelable(false);
            ringProgressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            String response = "";
            JSONObject responseJSON = null;

            try {

                URL url = new URL(AppConstants.URL_GEN_INFO_UPLOAD);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                String str = "YearWiseCollegeId=" + instituteId;
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
            if(result != null){
                ITIDBController controller = new ITIDBController(context);
                controller.addGenInfoUploadImageData(result,instituteId);
                controller.close();
                Log.e("success","success");
            }

            new DownloadLandBuildingUploadImageData().execute();

        }
    }

    class  DownloadLandBuildingUploadImageData extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
            String response = "";
            JSONObject responseJSON = null;

            try {

                URL url = new URL(AppConstants.URL_LAND_BUILDING_UPLOAD);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                String str = "YearWiseCollegeId=" + instituteId;
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
            if (result != null) {
                ITIDBController controller = new ITIDBController(context);
                controller.addLandAndBuildingInfoUploadImageData(result, instituteId);
                controller.close();
                Log.e("success1","success1");
            }
            new DownloadOrganisationDetailsUploadImageData().execute();
        }
    }

    class  DownloadOrganisationDetailsUploadImageData extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
            String response = "";
            JSONObject responseJSON = null;

            try {

                URL url = new URL(AppConstants.URL_ORGANISATION_UPLOAD);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                String str = "YearWiseCollegeId=" + instituteId;
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
            if (result != null) {
                ITIDBController controller = new ITIDBController(context);
                controller.addOrganisationDetailsInfoUploadImageData(result, instituteId);
                controller.close();
                Log.e("success2","success2");
            }

            new DownloadTechnicalUploadImageData().execute();
        }
    }

    class  DownloadTechnicalUploadImageData extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
            String response = "";
            JSONObject responseJSON = null;

            try {

                URL url = new URL(AppConstants.URL_TECHNICAL_UPLOAD);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                String str = "YearWiseCollegeId=" + instituteId;
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
            if (result != null) {
                ITIDBController controller = new ITIDBController(context);
                controller.addTechnicalInfoUploadImageData(result, instituteId);
                controller.close();
                Log.e("success3","success3");
            }

            new DownloadInstructorUploadImageData().execute();
        }
    }

    class  DownloadInstructorUploadImageData extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
            String response = "";
            JSONObject responseJSON = null;

            try {

                URL url = new URL(AppConstants.URL_INSTRUCTOR_UPLOAD);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                String str = "YearWiseCollegeId=" + instituteId;
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
            if (result != null) {
                ITIDBController controller = new ITIDBController(context);
                controller.addInstructorInfoUploadImageData(result, instituteId);
                controller.close();
                Log.e("success4","succes4s");
            }

            new DownloaditLabUploadImageData().execute();
        }
    }

    class  DownloaditLabUploadImageData extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
            String response = "";
            JSONObject responseJSON = null;

            try {

                URL url = new URL(AppConstants.URL_ITLAB_UPLOAD);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                String str = "YearWiseCollegeId=" + instituteId;
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
            if (result != null) {
                ITIDBController controller = new ITIDBController(context);
                controller.additLabInfoUploadImageData(result, instituteId);
                controller.close();
                Log.e("success5","success5");
            }

            new DownloadPowerSupplyUploadImageData().execute();
        }
    }

    class  DownloadPowerSupplyUploadImageData extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
            String response = "";
            JSONObject responseJSON = null;

            try {

                URL url = new URL(AppConstants.URL_POWERSUPPLY_UPLOAD);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                String str = "YearWiseCollegeId=" + instituteId;
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
            if (result != null) {
                ITIDBController controller = new ITIDBController(context);
                controller.addPowerSupplyInfoUploadImageData(result, instituteId);
                controller.close();
                Log.e("success6","success6");
            }
            new DownloadClassroomUploadImageData().execute();
        }
    }

    class  DownloadClassroomUploadImageData extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
            String response = "";
            JSONObject responseJSON = null;

            try {

                URL url = new URL(AppConstants.URL_CLASSROOM_UPLOAD);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
               // conn.setReadTimeout(10000);
               // conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                String str = "YearWiseCollegeId=" + instituteId;
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
            if (result != null) {
                ITIDBController controller = new ITIDBController(context);
                controller.addClassroomInfoUploadImageData(result, instituteId);
                controller.close();
                Log.e("success6", "success6");

                new DownloadWorkshopUploadImageData().execute();
            }
        }
    }



    class DownloadWorkshopUploadImageData extends AsyncTask<String, Integer, JSONObject> {

            @Override
            protected JSONObject doInBackground(String... params) {
                String response = "";
                JSONObject responseJSON = null;

                try {

                    URL url = new URL(AppConstants.URL_WORKSHOP_UPLOAD);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                    String str = "YearWiseCollegeId=" + instituteId;
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
                if (result != null) {
                    ITIDBController controller = new ITIDBController(context);
                    controller.addWorkshopUploadImageData(result, instituteId);
                    controller.close();
                    Log.e("success6", "success6");
                }

                new DownloadToolsBelowUploadImageData().execute();
            }
        }


    class DownloadToolsBelowUploadImageData extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
            String response = "";
            JSONObject responseJSON = null;

            try {

                URL url = new URL(AppConstants.URL_TOOLBELOW_UPLOAD);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                String str = "YearWiseCollegeId=" + instituteId;
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
            if (result != null) {
                ITIDBController controller = new ITIDBController(context);
                controller.addToolsBelowUploadImageData(result, instituteId);
                controller.close();
                Log.e("success6", "success6");
            }

//            ringProgressDialog.cancel();
//            notifyDataSetChanged();

           new DownloadToolsAboveUploadImageData().execute();

        }
    }

    class DownloadToolsAboveUploadImageData extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
            String response = "";
            JSONObject responseJSON = null;

            try {

                URL url = new URL(AppConstants.URL_TOOLABOVE_UPLOAD);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                String str = "YearWiseCollegeId=" + instituteId;
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
            if (result != null) {
                ITIDBController controller = new ITIDBController(context);
                controller.addToolsAboveUploadImageData(result, instituteId);
                controller.close();
                Log.e("success6", "success6");

                ringProgressDialog.dismiss();
                notifyDataSetChanged();

            }
        }
    }

    class DownloadVideoData extends AsyncTask<String, Integer, JSONObject> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(
                        context, android.R.style.Theme_Holo), null, "Downloading data....", true);
                ringProgressDialog.setCancelable(false);
                ringProgressDialog.setCanceledOnTouchOutside(false);
            }

            @Override
            protected JSONObject doInBackground(String... params) {
                String response = "";
                JSONObject responseJSON = null;

                try {

                    URL url = new URL(AppConstants.URL_VIDEO_DOWNLOAD);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                    String str = "YearWiseCollegeId=" + instituteId;
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

                if (ringProgressDialog != null)
                    ringProgressDialog.dismiss();

                if (response != null) {

                    try {
                        // JSONObject response = new JSONObject(result);

                        JSONObject payloadobj = response.optJSONObject("payload");
                        JSONArray sectionArr = payloadobj.optJSONArray("section");

                        for (int i = 0; i < sectionArr.length(); i++) {
                            VideoSectionModel videoSectionModel = new VideoSectionModel();

                            JSONObject videoSectionObj = sectionArr.getJSONObject(i);
                            String name = videoSectionObj.optString("name");
                            String sectionId = videoSectionObj.optString("sectionId");
                            JSONArray videoArr = videoSectionObj.optJSONArray("video");


                            List<VideosListModel> videosListModelList = new ArrayList<>();
                            for (int j = 0; j < videoArr.length(); j++) {
                                VideosListModel videosListModel = new VideosListModel();

                                JSONObject videoObj = videoArr.getJSONObject(j);
                                String videoName = videoObj.optString("videoName");
                                String visibleComponents = videoObj.optString("visibleComponents");
                                String videoId = videoObj.optString("videoId");
                                String equipmentId = videoObj.optString("equipmentId");
                                String tradeId = videoObj.optString("tradeId");

                                videosListModel.setVideoName(videoName);
                                videosListModel.setVisibleComponents(visibleComponents);
                                videosListModel.setVideoId(videoId);
                                videosListModel.setEquipmentId(equipmentId);
                                videosListModel.setTradeId(tradeId);

                                videosListModelList.add(videosListModel);
                            }

                            videoSectionModel.setName(name);
                            videoSectionModel.setSectionId(sectionId);
                            videoSectionModel.setVideosListModel(videosListModelList);
                            videoSectionList.add(videoSectionModel);
                        }


                        HashMap<String, VideoObjectModel> mapper = new HashMap<String, VideoObjectModel>();
                        VideoObjectModel videoSectionModel = new VideoObjectModel();
                        videoSectionModel.setVideoObjectModel(videoSectionList);
                        mapper.put(instituteId, videoSectionModel);
                        DataUtils.saveHashMapInShared(context, mapper);

                        notifyDataSetChanged();


                    } catch (Exception e) {
                        Log.d("Error in json parsing", e.toString());
                    }

                }

            }
        }


}
