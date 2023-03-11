package com.example.myapplicationdoctor.viewModel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.myapplicationdoctor.model.UserDoctor;
import com.example.myapplicationdoctor.repositories.RepositoryApplication;

public class DoctorRegistryDetailActivityViewModel extends ViewModel {
    public void updateUserDr(UserDoctor userDoctor, Context context){
        RepositoryApplication.getInstance().updateDoctors(userDoctor,context);
    }
}
