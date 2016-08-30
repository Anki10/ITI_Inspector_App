package com.ss.nsdc.entity;

import java.io.Serializable;

/**
 * Created by Arjit on 29-08-2016.
 */
public class SubCategorySupportStaff implements Serializable {

    private String yearWiseCollegeId;
    private String staffId;
    private String staffType;
    private String staffName;
    private String remarks;
    private int proc_tracker;

    public SubCategorySupportStaff() {
    }

    public String getYearWiseCollegeId() {
        return yearWiseCollegeId;
    }

    public void setYearWiseCollegeId(String yearWiseCollegeId) {
        this.yearWiseCollegeId = yearWiseCollegeId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getProc_tracker() {
        return proc_tracker;
    }

    public void setProc_tracker(int proc_tracker) {
        this.proc_tracker = proc_tracker;
    }
}