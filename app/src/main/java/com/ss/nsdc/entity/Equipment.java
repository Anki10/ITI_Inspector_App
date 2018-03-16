package com.ss.nsdc.entity;

import java.io.Serializable;

/**
 * Created by Arjit on 15-08-2016.
 */
public class Equipment implements Serializable {

    private String yearWiseCollegeId;
    private String applicationNo;
    private int procTracker;

    private String jobId;
    private String jobName;
    private String labName;
    private String image1;
    private String image2;
    private String image3;
    private String image4;

    private String image1Remark;
    private String image2Remark;
    private String image3Remark;
    private String image4Remark;

    private String image1Url;
    private String image2Url;
    private String image3Url;
    private String image4Url;

    private String image1Tag;
    private String image2Tag;
    private String image3Tag;
    private String image4Tag;

    private boolean isImage1Changed = false;
    private boolean isImage2Changed = false;
    private boolean isImage3Changed = false;
    private boolean isImage4Changed = false;

    private boolean isTag1Changed = false;
    private boolean isTag2Changed = false;
    private boolean isTag3Changed = false;
    private boolean isTag4Changed = false;

    private boolean isImage1RemarkChanged = false;
    private boolean isImage2RemarkChanged = false;
    private boolean isImage3RemarkChanged = false;
    private boolean isImage4RemarkChanged = false;

    public Equipment() {
    }

    public String getYearWiseCollegeId() {
        return yearWiseCollegeId;
    }

    public void setYearWiseCollegeId(String yearWiseCollegeId) {
        this.yearWiseCollegeId = yearWiseCollegeId;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public int getProcTracker() {
        return procTracker;
    }

    public void setProcTracker(int procTracker) {
        this.procTracker = procTracker;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public String getImage1Remark() {
        return image1Remark;
    }

    public void setImage1Remark(String image1Remark) {
        this.image1Remark = image1Remark;
    }

    public String getImage2Remark() {
        return image2Remark;
    }

    public void setImage2Remark(String image2Remark) {
        this.image2Remark = image2Remark;
    }

    public String getImage3Remark() {
        return image3Remark;
    }

    public void setImage3Remark(String image3Remark) {
        this.image3Remark = image3Remark;
    }

    public String getImage4Remark() {
        return image4Remark;
    }

    public void setImage4Remark(String image4Remark) {
        this.image4Remark = image4Remark;
    }

    public String getImage1Url() {
        return image1Url;
    }

    public void setImage1Url(String image1Url) {
        this.image1Url = image1Url;
    }

    public String getImage2Url() {
        return image2Url;
    }

    public void setImage2Url(String image2Url) {
        this.image2Url = image2Url;
    }

    public String getImage3Url() {
        return image3Url;
    }

    public void setImage3Url(String image3Url) {
        this.image3Url = image3Url;
    }

    public String getImage4Url() {
        return image4Url;
    }

    public void setImage4Url(String image4Url) {
        this.image4Url = image4Url;
    }

    public String getImage1Tag() {
        return image1Tag;
    }

    public void setImage1Tag(String image1Tag) {
        this.image1Tag = image1Tag;
    }

    public String getImage2Tag() {
        return image2Tag;
    }

    public void setImage2Tag(String image2Tag) {
        this.image2Tag = image2Tag;
    }

    public String getImage3Tag() {
        return image3Tag;
    }

    public void setImage3Tag(String image3Tag) {
        this.image3Tag = image3Tag;
    }

    public String getImage4Tag() {
        return image4Tag;
    }

    public void setImage4Tag(String image4Tag) {
        this.image4Tag = image4Tag;
    }

    public boolean isImage1Changed() {
        return isImage1Changed;
    }

    public void setIsImage1Changed(boolean isImage1Changed) {
        this.isImage1Changed = isImage1Changed;
    }

    public boolean isImage2Changed() {
        return isImage2Changed;
    }

    public void setIsImage2Changed(boolean isImage2Changed) {
        this.isImage2Changed = isImage2Changed;
    }

    public boolean isImage3Changed() {
        return isImage3Changed;
    }

    public void setIsImage3Changed(boolean isImage3Changed) {
        this.isImage3Changed = isImage3Changed;
    }

    public boolean isImage4Changed() {
        return isImage4Changed;
    }

    public void setIsImage4Changed(boolean isImage4Changed) {
        this.isImage4Changed = isImage4Changed;
    }

    public boolean isTag1Changed() {
        return isTag1Changed;
    }

    public void setIsTag1Changed(boolean isTag1Changed) {
        this.isTag1Changed = isTag1Changed;
    }

    public boolean isTag2Changed() {
        return isTag2Changed;
    }

    public void setIsTag2Changed(boolean isTag2Changed) {
        this.isTag2Changed = isTag2Changed;
    }

    public boolean isTag3Changed() {
        return isTag3Changed;
    }

    public void setIsTag3Changed(boolean isTag3Changed) {
        this.isTag3Changed = isTag3Changed;
    }

    public boolean isTag4Changed() {
        return isTag4Changed;
    }

    public void setIsTag4Changed(boolean isTag4Changed) {
        this.isTag4Changed = isTag4Changed;
    }

    public boolean isImage1RemarkChanged() {
        return isImage1RemarkChanged;
    }

    public void setIsImage1RemarkChanged(boolean isImage1RemarkChanged) {
        this.isImage1RemarkChanged = isImage1RemarkChanged;
    }

    public boolean isImage2RemarkChanged() {
        return isImage2RemarkChanged;
    }

    public void setIsImage2RemarkChanged(boolean isImage2RemarkChanged) {
        this.isImage2RemarkChanged = isImage2RemarkChanged;
    }

    public boolean isImage3RemarkChanged() {
        return isImage3RemarkChanged;
    }

    public void setIsImage3RemarkChanged(boolean isImage3RemarkChanged) {
        this.isImage3RemarkChanged = isImage3RemarkChanged;
    }

    public boolean isImage4RemarkChanged() {
        return isImage4RemarkChanged;
    }

    public void setIsImage4RemarkChanged(boolean isImage4RemarkChanged) {
        this.isImage4RemarkChanged = isImage4RemarkChanged;
    }
}