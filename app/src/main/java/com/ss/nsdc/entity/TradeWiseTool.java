package com.ss.nsdc.entity;

/**
 * Created by Radhika on 5/15/2017.
 */

public class TradeWiseTool {

    private Integer proc_tracker;
    private String yearWiseCollegeid;
    private String refId;
    private String equipmentId="";
    private String tradeId;
    private String tradeName;
    private String equipmentName;
    private String reqUnit;
    private String markTools;
    private String availableUnit="";

    public String getAvailableUnit(){
        return availableUnit;
    }

    public void setAvailableUnit(String availableUnit){
        this.availableUnit = availableUnit;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getReqUnit() {
        return reqUnit;
    }

    public void setReqUnit(String reqUnit) {
        this.reqUnit = reqUnit;
    }

    public String getMarkTools() {
        return markTools;
    }

    public void setMarkTools(String markTools) {
        this.markTools = markTools;
    }
    public String getYearWiseCollegeid() {
        return yearWiseCollegeid;
    }

    public void setYearWiseCollegeid(String yearWiseCollegeid) {
        this.yearWiseCollegeid = yearWiseCollegeid;
    }


    public Integer getProc_tracker() {
        return proc_tracker;
    }


    public void setProc_tracker(Integer proc_tracker) {
        this.proc_tracker = proc_tracker;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }
}
