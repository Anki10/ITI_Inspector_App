package com.ss.nsdc.entity;

/**
 * Created by sachin.arora on 5/18/2017.
 */


import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;


public class VideoSectionModel implements Parcelable
{

    private String name;
    private String sectionId;
    private List<VideosListModel> videosListModel = null;
    public final static Creator<VideoSectionModel> CREATOR = new Creator<VideoSectionModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VideoSectionModel createFromParcel(Parcel in) {
            VideoSectionModel instance = new VideoSectionModel();
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.sectionId = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.videosListModel, (VideosListModel.class.getClassLoader()));
            return instance;
        }

        public VideoSectionModel[] newArray(int size) {
            return (new VideoSectionModel[size]);
        }

    }
            ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public List<VideosListModel> getVideosListModel() {
        return videosListModel;
    }

    public void setVideosListModel(List<VideosListModel> videosListModel) {
        this.videosListModel = videosListModel;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(sectionId);
        dest.writeList(videosListModel);
    }

    public int describeContents() {
        return 0;
    }

}
