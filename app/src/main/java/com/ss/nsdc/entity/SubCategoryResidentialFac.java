package com.ss.nsdc.entity;

import java.io.Serializable;

/**
 * Created by Arjit on 31-08-2016.
 */
public class SubCategoryResidentialFac implements Serializable {

    private String yearWiseCollegeId;
    private String resFacId;
    private String resFacType;
    private String totalArea;
    private int noOfRooms;
    private int resCapacity;
    private String powerBackupAvailable;
    private String cctvCameraFacility;
    private String remarks;
    private int proc_tracker;

    private String insTotalArea;
    private int insNoOfRooms;
    private int insResCapacity;
    private String insPowerBackupAvailable;
    private String insCCTVCameraFacility;
    private String insRemarks;

    public SubCategoryResidentialFac() {
    }

    public String getYearWiseCollegeId() {
        return yearWiseCollegeId;
    }

    public void setYearWiseCollegeId(String yearWiseCollegeId) {
        this.yearWiseCollegeId = yearWiseCollegeId;
    }

    public String getResFacId() {
        return resFacId;
    }

    public void setResFacId(String resFacId) {
        this.resFacId = resFacId;
    }

    public String getResFacType() {
        return resFacType;
    }

    public void setResFacType(String resFacType) {
        this.resFacType = resFacType;
    }

    public String getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(String totalArea) {
        this.totalArea = totalArea;
    }

    public int getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(int noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public int getResCapacity() {
        return resCapacity;
    }

    public void setResCapacity(int resCapacity) {
        this.resCapacity = resCapacity;
    }

    public String getPowerBackupAvailable() {
        return powerBackupAvailable;
    }

    public void setPowerBackupAvailable(String powerBackupAvailable) {
        this.powerBackupAvailable = powerBackupAvailable;
    }

    public String getCctvCameraFacility() {
        return cctvCameraFacility;
    }

    public void setCctvCameraFacility(String cctvCameraFacility) {
        this.cctvCameraFacility = cctvCameraFacility;
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

    public String getInsTotalArea() {
        return insTotalArea;
    }

    public void setInsTotalArea(String insTotalArea) {
        this.insTotalArea = insTotalArea;
    }

    public int getInsNoOfRooms() {
        return insNoOfRooms;
    }

    public void setInsNoOfRooms(int insNoOfRooms) {
        this.insNoOfRooms = insNoOfRooms;
    }

    public int getInsResCapacity() {
        return insResCapacity;
    }

    public void setInsResCapacity(int insResCapacity) {
        this.insResCapacity = insResCapacity;
    }

    public String getInsPowerBackupAvailable() {
        return insPowerBackupAvailable;
    }

    public void setInsPowerBackupAvailable(String insPowerBackupAvailable) {
        this.insPowerBackupAvailable = insPowerBackupAvailable;
    }

    public String getInsCCTVCameraFacility() {
        return insCCTVCameraFacility;
    }

    public void setInsCCTVCameraFacility(String insCCTVCameraFacility) {
        this.insCCTVCameraFacility = insCCTVCameraFacility;
    }

    public String getInsRemarks() {
        return insRemarks;
    }

    public void setInsRemarks(String insRemarks) {
        this.insRemarks = insRemarks;
    }
}
