package com.ss.nsdc.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mayank on 28/08/2016.
 */
public class JobRolesModel implements Parcelable {


    int id;
    String JobID;
    String jobName;
    String handbookAvailable;
    String trainees;
    int batch;
    String remark;
    String insHandbookAvailable;
    String insTrainees;
    String insbatch;
    String insRemark;

    String  yearWiseCollegeId;

    private Integer proc_tracker;

    public JobRolesModel()
    {

    }

    protected JobRolesModel(Parcel in) {
        id = in.readInt();
        jobName = in.readString();
        handbookAvailable = in.readString();
        trainees = in.readString();
        batch = in.readInt();
        remark = in.readString();
        insHandbookAvailable = in.readString();
        insTrainees = in.readString();
        insbatch = in.readString();
        insRemark = in.readString();
        yearWiseCollegeId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(jobName);
        dest.writeString(handbookAvailable);
        dest.writeString(trainees);
        dest.writeInt(batch);
        dest.writeString(remark);
        dest.writeString(insHandbookAvailable);
        dest.writeString(insTrainees);
        dest.writeString(insbatch);
        dest.writeString(insRemark);
        dest.writeString(yearWiseCollegeId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<JobRolesModel> CREATOR = new Creator<JobRolesModel>() {
        @Override
        public JobRolesModel createFromParcel(Parcel in) {
            return new JobRolesModel(in);
        }

        @Override
        public JobRolesModel[] newArray(int size) {
            return new JobRolesModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getHandbookAvailable() {
        return handbookAvailable;
    }

    public void setHandbookAvailable(String handbookAvailable) {
        this.handbookAvailable = handbookAvailable;
    }

    public String getTrainees() {
        return trainees;
    }

    public void setTrainees(String trainees) {
        this.trainees = trainees;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getInsHandbookAvailable() {
        return insHandbookAvailable;
    }

    public void setInsHandbookAvailable(String insHandbookAvailable) {
        this.insHandbookAvailable = insHandbookAvailable;
    }

    public String getInsTrainees() {
        return insTrainees;
    }

    public void setInsTrainees(String insTrainees) {
        this.insTrainees = insTrainees;
    }

    public String getInsbatch() {
        return insbatch;
    }

    public void setInsbatch(String insbatch) {
        this.insbatch = insbatch;
    }

    public String getInsRemark() {
        return insRemark;
    }

    public void setInsRemark(String insRemark) {
        this.insRemark = insRemark;
    }


    public Integer getProc_tracker() {
        return proc_tracker;
    }

    public void setProc_tracker(Integer proc_tracker) {
        this.proc_tracker = proc_tracker;
    }

    public String getYearWiseCollegeId() {
        return yearWiseCollegeId;
    }

    public void setYearWiseCollegeId(String yearWiseCollegeId) {
        this.yearWiseCollegeId = yearWiseCollegeId;
    }


    public String  getJobID() {
        return JobID;
    }

    public void setJobID(String  jobID) {
        JobID = jobID;
    }
}
