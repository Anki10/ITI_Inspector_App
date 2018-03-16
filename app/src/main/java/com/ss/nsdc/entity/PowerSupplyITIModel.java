package com.ss.nsdc.entity;

/**
 * Created by sachin.arora on 5/23/2017.
 */

public class PowerSupplyITIModel {

    private Integer proc_tracker;
    private String yearWiseCollegeid;

    private String isConnectionNameIti;
    private String isConnectionNameItiNC;
    private String connectionIssued;
    private String dateConnection;
    private String PowerSupplyNeededAsPer;

    public String getIsConnectionNameItiNC(){
        return isConnectionNameItiNC;
    }

    public void setIsConnectionNameItiNC(String isConnectionNameItiNC){
        this.isConnectionNameItiNC = isConnectionNameItiNC;
    }

    public Integer getProc_tracker() {
        return proc_tracker;
    }

    public void setProc_tracker(Integer proc_tracker) {
        this.proc_tracker = proc_tracker;
    }

    public String getYearWiseCollegeid() {
        return yearWiseCollegeid;
    }

    public void setYearWiseCollegeid(String yearWiseCollegeid) {
        this.yearWiseCollegeid = yearWiseCollegeid;
    }

    public String getIsConnectionNameIti() {
        return isConnectionNameIti;
    }

    public void setIsConnectionNameIti(String isConnectionNameIti) {
        this.isConnectionNameIti = isConnectionNameIti;
    }

    public String getConnectionIssued() {
        return connectionIssued;
    }

    public void setConnectionIssued(String connectionIssued) {
        this.connectionIssued = connectionIssued;
    }

    public String getDateConnection() {
        return dateConnection;
    }

    public void setDateConnection(String dateConnection) {
        this.dateConnection = dateConnection;
    }

    public String getPowerSupplyNeededAsPer() {
        return PowerSupplyNeededAsPer;
    }

    public void setPowerSupplyNeededAsPer(String powerSupplyNeededAsPer) {
        PowerSupplyNeededAsPer = powerSupplyNeededAsPer;
    }
}
