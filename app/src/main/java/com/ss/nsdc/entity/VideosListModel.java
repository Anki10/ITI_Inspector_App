package com.ss.nsdc.entity;

/**
 * Created by sachin.arora on 5/18/2017.
 */

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;


public class VideosListModel implements Parcelable
{

    private String videoName;
    private String visibleComponents;
    private String videoId;
    private String videoPath;
    private String remarks="";
    private String equipmentId;
    private String tradeId;
    private boolean isSync;

    public final static Creator<VideosListModel> CREATOR = new Creator<VideosListModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VideosListModel createFromParcel(Parcel in) {
            VideosListModel instance = new VideosListModel();
            instance.videoName = ((String) in.readValue((String.class.getClassLoader())));
            instance.visibleComponents = ((String) in.readValue((String.class.getClassLoader())));
            instance.videoId = ((String) in.readValue((String.class.getClassLoader())));
            instance.videoPath = ((String) in.readValue((String.class.getClassLoader())));
            instance.equipmentId = ((String) in.readValue((String.class.getClassLoader())));
            instance.tradeId = ((String) in.readValue((String.class.getClassLoader())));
            instance.remarks = ((String) in.readValue((String.class.getClassLoader())));
            instance.isSync = in.readByte() != 0;
            return instance;
        }

        public VideosListModel[] newArray(int size) {
            return (new VideosListModel[size]);
        }

    }
            ;

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVisibleComponents() {
        return visibleComponents;
    }

    public void setVisibleComponents(String visibleComponents) {
        this.visibleComponents = visibleComponents;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(videoName);
        dest.writeValue(visibleComponents);
        dest.writeValue(videoId);
        dest.writeValue(videoPath);
        dest.writeValue(equipmentId);
        dest.writeValue(tradeId);
        dest.writeByte((byte) (isSync ? 1 : 0));
    }

    public int describeContents() {
        return 0;
    }


    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean isSync() {
        return isSync;
    }

    public void setSync(boolean sync) {
        isSync = sync;
    }


    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }
}

