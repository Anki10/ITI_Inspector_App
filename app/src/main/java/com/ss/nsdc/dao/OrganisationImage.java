package com.ss.nsdc.dao;

import java.io.Serializable;

/**
 * Created by Radhika on 5/19/2017.
 */

public class OrganisationImage implements Serializable {

    private Integer docId;
    private String section;
    private Integer refId;
    private String pictureType;
    private String helpText;
    private String remarks;
    private String latitude;
    private String longitude;
    private String name;
    private String photo;
    private Integer nc;
    private boolean isImageChanged = false;
    private boolean isRemarkChanged = false;
    private String yearWiseCollegeId;
    private int procTracker;

    public OrganisationImage(){

    }

    public String getYearWiseCollegeId() {
        return yearWiseCollegeId;
    }

    public void setYearWiseCollegeId(String yearWiseCollegeId) {
        this.yearWiseCollegeId = yearWiseCollegeId;
    }

    public boolean isImageChanged() {
        return isImageChanged;
    }

    public void setIsImageChanged(boolean isImageChanged) {
        this.isImageChanged = isImageChanged;
    }

    public boolean isRemarkChanged() {
        return isRemarkChanged;
    }

    public void setIsRemarkChanged(boolean isRemarkChanged) {
        this.isRemarkChanged = isRemarkChanged;
    }

    public int getProcTracker() {
        return procTracker;
    }

    public void setProcTracker(int procTracker) {
        this.procTracker = procTracker;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Integer getRefId() {
        return refId;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
    }

    public String getPictureType() {
        return pictureType;
    }

    public void setPictureType(String pictureType) {
        this.pictureType = pictureType;
    }

    public String getHelpText() {
        return helpText;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getNc() {
        return nc;
    }

    public void setNc(Integer nc) {
        this.nc = nc;
    }
}
