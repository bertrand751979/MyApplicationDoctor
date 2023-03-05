package com.example.myapplicationdoctor.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplicationdoctor.model.UserDoctor;
import com.example.myapplicationdoctor.model.UserPatient;
import com.example.myapplicationdoctor.repositories.RepositoryApplication;

import java.util.List;

public class LoginPageActivityViewModel extends ViewModel {
    public LiveData<List<UserPatient>> getLiveDataUser(Context context){
        return RepositoryApplication.getInstance().getUserList(context);
    }




    public LiveData<List<UserDoctor>> getLiveDataDoctor(Context context){
        return RepositoryApplication.getInstance().getUserDoctorList(context);
    }


}
