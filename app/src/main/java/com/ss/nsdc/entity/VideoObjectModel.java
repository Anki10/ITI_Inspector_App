package com.ss.nsdc.entity;

/**
 * Created by sachin.arora on 5/18/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


public class VideoObjectModel implements Parcelable
{

    private List<VideoSectionModel> videoSectionModels = null;

    public final static Creator<VideoObjectModel> CREATOR = new Creator<VideoObjectModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VideoObjectModel createFromParcel(Parcel in) {
            VideoObjectModel instance = new VideoObjectModel();
            in.readList(instance.videoSectionModels, (VideoSectionModel.class.getClassLoader()));

            return instance;
        }

        public VideoObjectModel[] newArray(int size) {
            return (new VideoObjectModel[size]);
        }

    }
            ;




    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(videoSectionModels);
    }

    public int describeContents() {
        return 0;
    }




    public List<VideoSectionModel> getVideoObjectModel() {
        return videoSectionModels;
    }

    public void setVideoObjectModel(List<VideoSectionModel> videoSectionModels) {
        this.videoSectionModels = videoSectionModels;
    }

}

