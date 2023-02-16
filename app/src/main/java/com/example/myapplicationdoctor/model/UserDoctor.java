package com.example.myapplicationdoctor.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
@Entity
public class UserDoctor implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @ColumnInfo(name="doctorLogin")
    private String doctorLogin;
    @ColumnInfo(name="doctorPassword")
    private String doctorPassword;
    @ColumnInfo(name="doctorName")
    private String doctorName;
    @ColumnInfo(name="doctorLocation")
    private String doctorLocation;
    @ColumnInfo(name="doctorPhoneNumber")
    private String doctorPhoneNumber;
    @ColumnInfo(name="doctorSkill")
    private String doctorSkill;
    @ColumnInfo(name="doctorCity")
    private String doctorCity;
    @ColumnInfo(name="doctorDayWorked")
    private String doctorDayWorked;
    @ColumnInfo(name="doctorOpenOffice")
    private String doctorOpenOffice;
    @ColumnInfo(name="doctorCloseOffice")
    private String doctorCloseOffice;
    @ColumnInfo(name="doctorStartHolidays")
    private String doctorStartHolidays;
    @ColumnInfo(name="doctorCloseHolidays")
    private String doctorCloseHolidays;


    public UserDoctor(Integer id, String doctorLogin, String doctorPassword, String doctorName, String doctorLocation, String doctorPhoneNumber, String doctorSkill, String doctorCity, String doctorDayWorked) {
        this.id = id;
        this.doctorLogin = doctorLogin;
        this.doctorPassword = doctorPassword;
        this.doctorName = doctorName;
        this.doctorLocation = doctorLocation;
        this.doctorPhoneNumber = doctorPhoneNumber;
        this.doctorSkill = doctorSkill;
        this.doctorCity = doctorCity;
        this.doctorDayWorked = doctorDayWorked;
        //this.holidays = holidays;
    }

    public UserDoctor(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorLocation() {
        return doctorLocation;
    }

    public void setDoctorLocation(String doctorLocation) {
        this.doctorLocation = doctorLocation;
    }

    public String getDoctorPhoneNumber() {
        return doctorPhoneNumber;
    }

    public void setDoctorPhoneNumber(String doctorPhoneNumber) {
        this.doctorPhoneNumber = doctorPhoneNumber;
    }

    public String getDoctorSkill() {
        return doctorSkill;
    }

    public void setDoctorSkill(String doctorSkill) {
        this.doctorSkill = doctorSkill;
    }

    public String getDoctorCity() {
        return doctorCity;
    }

    public void setDoctorCity(String doctorCity) {
        this.doctorCity = doctorCity;
    }

    public String getDoctorDayWorked() {
        return doctorDayWorked;
    }

    public void setDoctorDayWorked(String doctorDayWorked) {
        this.doctorDayWorked = doctorDayWorked;
    }

    public String getDoctorOpenOffice() {
        return doctorOpenOffice;
    }

    public void setDoctorOpenOffice(String doctorOpenOffice) {
        this.doctorOpenOffice = doctorOpenOffice;
    }

    public String getDoctorCloseOffice() {
        return doctorCloseOffice;
    }

    public void setDoctorCloseOffice(String doctorCloseOffice) {
        this.doctorCloseOffice = doctorCloseOffice;
    }

    public String getDoctorStartHolidays() {
        return doctorStartHolidays;
    }

    public void setDoctorStartHolidays(String doctorStartHolidays) {
        this.doctorStartHolidays = doctorStartHolidays;
    }

    public String getDoctorCloseHolidays() {
        return doctorCloseHolidays;
    }

    public void setDoctorCloseHolidays(String doctorCloseHolidays) {
        this.doctorCloseHolidays = doctorCloseHolidays;
    }

    public String getDoctorLogin() {
        return doctorLogin;
    }

    public void setDoctorLogin(String doctorLogin) {
        this.doctorLogin = doctorLogin;
    }

    public String getDoctorPassword() {
        return doctorPassword;
    }

    public void setDoctorPassword(String doctorPassword) {
        this.doctorPassword = doctorPassword;
    }

    @Override
    public String toString() {
        return doctorSkill;
    }
}
