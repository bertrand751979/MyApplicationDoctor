package com.example.myapplicationdoctor.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.myapplicationdoctor.ApplicationDatabaseEmergency;
import com.example.myapplicationdoctor.ApplicationDatabaseSkill;
import com.example.myapplicationdoctor.ApplicationDatabaseUserDoctor;
import com.example.myapplicationdoctor.ApplicationDatabaseUserPatient;
import com.example.myapplicationdoctor.model.City;
import com.example.myapplicationdoctor.model.Emergency;
import com.example.myapplicationdoctor.model.SkillDoctor;
import com.example.myapplicationdoctor.model.MyDrSkillSpinner;
import com.example.myapplicationdoctor.model.UserDoctor;
import com.example.myapplicationdoctor.model.UserPatient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class RepositoryApplication {
    public ArrayList<UserDoctor> myUserDoctorList = new ArrayList<>();
    public ArrayList<UserDoctor> newListUserDoctor = new ArrayList<>();
    public ArrayList<UserPatient> newListUserPatient = new ArrayList<>();
    public ArrayList<UserDoctor> displayListDrSkill = new ArrayList<>();
    public ArrayList<UserDoctor> searchDrSkillList = new ArrayList<>();
    public ArrayList<UserPatient> myUserPatientList = new ArrayList<>();
    public ArrayList<SkillDoctor> myListSkillsDoctor = new ArrayList<>();
    public ArrayList<Emergency> emergencyList = new ArrayList<>();
    public ArrayList<MyDrSkillSpinner> skillsDoctorsList = new ArrayList<>();
    public ArrayList<City> cityDoctorsList = new ArrayList<>();
    public ArrayList<UserDoctor> listSortSkill = new ArrayList<>();
    public ArrayList<UserDoctor> sortListToDisplay = new ArrayList<>();
    public ArrayList<City> theCityDoctorsList = new ArrayList<>();
    public ArrayList<MyDrSkillSpinner> testList = new ArrayList<>();
    public ArrayList<UserDoctor> myDrSkillSearch = new ArrayList<>();


    private RepositoryApplication(){}


    public static RepositoryApplication INSTANCE = null;
    public static RepositoryApplication getInstance(){
        if(INSTANCE == null){
            INSTANCE = new RepositoryApplication();
        }
        return INSTANCE;
    }

    public ArrayList<UserDoctor> getMyDrSkillSearch() {
        return myDrSkillSearch;
    }

    public void setMyDrSkillSearch(ArrayList<UserDoctor> myDrSkillSearch) {
        this.myDrSkillSearch = myDrSkillSearch;
    }

    public ArrayList<UserDoctor> getDisplayListDrSkill() {
        return displayListDrSkill;
    }

    public void setDisplayListDrSkill(ArrayList<UserDoctor> displayListDrSkill) {
        this.displayListDrSkill = displayListDrSkill;
    }

    public ArrayList<UserDoctor> getMyUserDoctorList() {
        return myUserDoctorList;
    }

    public void setMyUserDoctorList(ArrayList<UserDoctor> myUserDoctorList) {
        this.myUserDoctorList = myUserDoctorList;
    }

    public ArrayList<City> getTheCityDoctorsList() {
        return theCityDoctorsList;
    }

    public void setTheCityDoctorsList(ArrayList<City> theCityDoctorsList) {
        this.theCityDoctorsList = theCityDoctorsList;
    }

    public ArrayList<UserDoctor> getNewListUserDoctor() {
        return newListUserDoctor;
    }

    public void setNewListUserDoctor(ArrayList<UserDoctor> newListUserDoctor) {
        this.newListUserDoctor = newListUserDoctor;
    }

    public ArrayList<MyDrSkillSpinner> getSkillsDoctorsList() {
        return skillsDoctorsList;
    }

    public void setSkillsDoctorsList(ArrayList<MyDrSkillSpinner> skillsDoctorsList) {
        this.skillsDoctorsList = skillsDoctorsList;
    }

    public ArrayList<UserDoctor> getSearchDrSkillList() {
        return searchDrSkillList;
    }

    public void setSearchDrSkillList(ArrayList<UserDoctor> searchDrSkillList) {
        this.searchDrSkillList = searchDrSkillList;
    }

    public ArrayList<City> getCityDoctorsList() {
        return cityDoctorsList;
    }

    public void setCityDoctorsList(ArrayList<City> cityDoctorsList) {
        this.cityDoctorsList = cityDoctorsList;
    }

    public ArrayList<UserDoctor> getListSortSkill() {
        return listSortSkill;
    }

    public void setListSortSkill(ArrayList<UserDoctor> listSortSkill) {
        this.listSortSkill = listSortSkill;
    }

    public ArrayList<MyDrSkillSpinner> getTestList() {
        return testList;
    }

    public void setTestList(ArrayList<MyDrSkillSpinner> testList) {
        this.testList = testList;
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

    public ArrayList<UserDoctor> getSortListToDisplay() {
        return sortListToDisplay;
    }

    public void setSortListToDisplay(ArrayList<UserDoctor> sortListToDisplay) {
        this.sortListToDisplay = sortListToDisplay;
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
            if(resultSkill.getDoctorSkill().toLowerCase().contains(skillSelected)){
                result =true;
                searchDrSkillList.add(resultSkill);
            }

        }
        return result;
    }


    public void updateDoctors(UserDoctor userDoctor , Context context){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ApplicationDatabaseUserDoctor.getInstance(context).getUserDoctorDao().userDoctorUpdate(userDoctor);
            }
        });
    }
    public boolean choiceSkill(String skillSearch,ArrayList<UserDoctor>list) {
        boolean res = false;
        for (UserDoctor userDoctor : list) {
            if (userDoctor.getDoctorSkill().toLowerCase().contains(skillSearch)) {
                res = true;
                list.add(userDoctor);
            }
        }
        return res;
    }
}
