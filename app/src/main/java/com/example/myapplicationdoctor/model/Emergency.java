package com.example.myapplicationdoctor.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class Emergency implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @ColumnInfo(name="emergencyName")
    private String emergencyName;
    @ColumnInfo(name="emergencyPhoneNumber")
    private String emergencyPhoneNumber;

    public Emergency(String emergencyName, String emergencyPhoneNumber) {
        this.emergencyName = emergencyName;
        this.emergencyPhoneNumber = emergencyPhoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmergencyName() {
        return emergencyName;
    }

    public void setEmergencyName(String emergencyName) {
        this.emergencyName = emergencyName;
    }

    public String getEmergencyPhoneNumber() {
        return emergencyPhoneNumber;
    }

    public void setEmergencyPhoneNumber(String emergencyPhoneNumber) {
        this.emergencyPhoneNumber = emergencyPhoneNumber;
    }
}
