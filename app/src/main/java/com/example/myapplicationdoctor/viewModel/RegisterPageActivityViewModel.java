package com.example.myapplicationdoctor.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplicationdoctor.model.City;
import com.example.myapplicationdoctor.model.UserDoctor;
import com.example.myapplicationdoctor.model.UserPatient;
import com.example.myapplicationdoctor.repositories.RepositoryApplication;

import java.util.List;

public class RegisterPageActivityViewModel extends ViewModel {
    public LiveData<List<UserPatient>> getLiveDataUser(Context context){
        return RepositoryApplication.getInstance().getUserList(context);
    }


    public void addToPatientList(UserPatient userPatient, Context context){
        RepositoryApplication.getInstance().addUserPatient(userPatient, context);
    }

    public void addToSpinnerCityList(City city, Context context){

    }
}
