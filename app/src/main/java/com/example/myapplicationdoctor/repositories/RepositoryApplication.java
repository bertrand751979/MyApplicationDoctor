package com.example.myapplicationdoctor.repositories;

import android.content.Context;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;

import com.example.myapplicationdoctor.ApplicationDatabaseEmergency;
import com.example.myapplicationdoctor.ApplicationDatabaseSkill;
import com.example.myapplicationdoctor.ApplicationDatabaseUserDoctor;
import com.example.myapplicationdoctor.ApplicationDatabaseUserPatient;
import com.example.myapplicationdoctor.fragments.PopupFirstConnexionAlertDialogFragment;
import com.example.myapplicationdoctor.model.City;
import com.example.myapplicationdoctor.model.Emergency;
import com.example.myapplicationdoctor.model.SkillDoctor;
import com.example.myapplicationdoctor.model.TestSkill;
import com.example.myapplicationdoctor.model.UserDoctor;
import com.example.myapplicationdoctor.model.UserPatient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class RepositoryApplication {
    public ArrayList<UserDoctor> myUserDoctorList = new ArrayList<>();
    public ArrayList<UserDoctor> newListUserDoctor = new ArrayList<>();
    public ArrayList<UserPatient> newListUserPatient = new ArrayList<>();

    public ArrayList<UserPatient> myUserPatientList = new ArrayList<>();
    public ArrayList<SkillDoctor> myListSkillsDoctor = new ArrayList<>();
    public ArrayList<Emergency> emergencyList = new ArrayList<>();
    public ArrayList<TestSkill> skillsDoctorsList = new ArrayList<>();
    public ArrayList<City> cityDoctorsList = new ArrayList<>();
    public ArrayList<UserDoctor> listAllergo = new ArrayList<>();

    private RepositoryApplication(){}
    public static RepositoryApplication INSTANCE = null;
    public static RepositoryApplication getInstance(){
        if(INSTANCE == null){
            INSTANCE = new RepositoryApplication();
        }
        return INSTANCE;
    }

    public ArrayList<UserDoctor> getMyUserDoctorList() {
        return myUserDoctorList;
    }

    public void setMyUserDoctorList(ArrayList<UserDoctor> myUserDoctorList) {
        this.myUserDoctorList = myUserDoctorList;
    }

    public ArrayList<UserDoctor> getNewListUserDoctor() {
        return newListUserDoctor;
    }

    public void setNewListUserDoctor(ArrayList<UserDoctor> newListUserDoctor) {
        this.newListUserDoctor = newListUserDoctor;
    }

    public ArrayList<TestSkill> getSkillsDoctorsList() {
        return skillsDoctorsList;
    }

    public void setSkillsDoctorsList(ArrayList<TestSkill> skillsDoctorsList) {
        this.skillsDoctorsList = skillsDoctorsList;
    }

    public ArrayList<City> getCityDoctorsList() {
        return cityDoctorsList;
    }

    public void setCityDoctorsList(ArrayList<City> cityDoctorsList) {
        this.cityDoctorsList = cityDoctorsList;
    }

    public ArrayList<UserDoctor> getListAllergo() {
        return listAllergo;
    }

    public void setListAllergo(ArrayList<UserDoctor> listAllergo) {
        this.listAllergo = listAllergo;
    }

    public ArrayList<UserPatient> getMyUserPatientList() {
        return myUserPatientList;
    }

    public void setMyUserPatientList(ArrayList<UserPatient> myUserPatientList) {
        this.myUserPatientList = myUserPatientList;
    }

    public ArrayList<UserPatient> getNewListUserPatient() {
        return newListUserPatient;
    }

    public void setNewListUserPatient(ArrayList<UserPatient> newListUserPatient) {
        this.newListUserPatient = newListUserPatient;
    }

    public ArrayList<SkillDoctor> getMyListSkillsDoctor() {
        return myListSkillsDoctor;
    }

    public void setMyListSkillsDoctor(ArrayList<SkillDoctor> myListSkillsDoctor) {
        this.myListSkillsDoctor = myListSkillsDoctor;
    }

    public ArrayList<Emergency> getEmergencyList() {
        return emergencyList;
    }

    public void setEmergencyList(ArrayList<Emergency> emergencyList) {
        this.emergencyList = emergencyList;
    }

    public void addUserDoctorSkill(SkillDoctor skillDoctor, Context context){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ApplicationDatabaseSkill.getInstance(context).getSkillDao().addSkill(skillDoctor);
            }
        });
    }

    public void addUserPatient(UserPatient userPatient, Context context){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ApplicationDatabaseUserPatient.getInstance(context).getUserPatientDao().add(userPatient);
            }
        });
    }

    public void addUserDoctor(UserDoctor userDoctor, Context context){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ApplicationDatabaseUserDoctor.getInstance(context).getUserDoctorDao().addDr(userDoctor);
            }
        });
    }


    public void addEmergency(Emergency emergency, Context context){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ApplicationDatabaseEmergency.getInstance(context).getEmergencyDao().addAllEmergencies(emergency);
            }
        });
    }

    public LiveData<List<UserPatient>> getUserList (Context context){
        return ApplicationDatabaseUserPatient.getInstance(context).getUserPatientDao().getUsersPatient();
    }

    public LiveData<List<UserDoctor>> getUserDoctorList (Context context){
        return ApplicationDatabaseUserDoctor.getInstance(context).getUserDoctorDao().getUsersDoctor();
    }

    public boolean isSameSkill(String skillSelected){
        boolean result=  false;
        for(UserDoctor resultSkill: newListUserDoctor){
            if(resultSkill.getDoctorSkill().equalsIgnoreCase(skillSelected)){
                result =true;
            }
        }
        return result;
    }

}
