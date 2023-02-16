package com.example.myapplicationdoctor.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class UserPatient implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @ColumnInfo(name="userPatientName")
    private String userPatientName;
    @ColumnInfo(name="userPatientLogin")
    private String userPatientLogin;
    @ColumnInfo(name="userPatientPassword")
    private String userPatientPassword;

    public UserPatient(String userPatientName, String userPatientLogin, String userPatientPassword) {
        this.userPatientName = userPatientName;
        this.userPatientLogin = userPatientLogin;
        this.userPatientPassword = userPatientPassword;
    }
    public UserPatient(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserPatientName() {
        return userPatientName;
    }

    public void setUserPatientName(String userPatientName) {
        this.userPatientName = userPatientName;
    }

    public String getUserPatientLogin() {
        return userPatientLogin;
    }

    public void setUserPatientLogin(String userPatientLogin) {
        this.userPatientLogin = userPatientLogin;
    }

    public String getUserPatientPassword() {
        return userPatientPassword;
    }

    public void setUserPatientPassword(String userPatientPassword) {
        this.userPatientPassword = userPatientPassword;
    }
}
