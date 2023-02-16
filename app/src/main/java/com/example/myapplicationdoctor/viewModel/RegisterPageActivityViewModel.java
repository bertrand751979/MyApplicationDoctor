package com.example.myapplicationdoctor.viewModel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.myapplicationdoctor.model.UserPatient;
import com.example.myapplicationdoctor.repositories.RepositoryApplication;

public class RegisterPageActivityViewModel extends ViewModel {
    public void addToPatientList(UserPatient userPatient, Context context){
        RepositoryApplication.getInstance().addUserPatient(userPatient, context);
    }


}
