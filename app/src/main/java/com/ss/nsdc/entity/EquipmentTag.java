package com.ss.nsdc.entity;

import java.io.Serializable;

/**
 * Created by Arjit on 21-08-2016.
 */
public class EquipmentTag implements Serializable {

    private String equipmentTagId;
    private String equipmentTagName;
    private boolean isChecked;

    public EquipmentTag() {
    }

    public String getEquipmentTagId() {
        return equipmentTagId;
    }

    public void setEquipmentTagId(String equipmentTagId) {
        this.equipmentTagId = equipmentTagId;
    }

    public String getEquipmentTagName() {
        return equipmentTagName;
    }

    public void setEquipmentTagName(String equipmentTagName) {
        this.equipmentTagName = equipmentTagName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
