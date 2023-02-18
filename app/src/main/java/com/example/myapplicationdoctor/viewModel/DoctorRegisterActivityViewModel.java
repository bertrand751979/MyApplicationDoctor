package com.example.myapplicationdoctor.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplicationdoctor.model.UserDoctor;
import com.example.myapplicationdoctor.model.UserPatient;
import com.example.myapplicationdoctor.repositories.RepositoryApplication;

import java.util.ArrayList;
import java.util.List;

public class DoctorRegisterActivityViewModel extends ViewModel {
    public LiveData<List<UserDoctor>> getLiveDataDoctor(Context context){
        return RepositoryApplication.getInstance().getUserDoctorList(context);
    }

    public void addToDoctorList(UserDoctor userDoctor, Context context){
        RepositoryApplication.getInstance().addUserDoctor(userDoctor,context);
    }







}
