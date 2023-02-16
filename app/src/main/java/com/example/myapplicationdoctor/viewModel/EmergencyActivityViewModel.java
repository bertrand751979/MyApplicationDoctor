package com.example.myapplicationdoctor.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.ColumnInfo;

import com.example.myapplicationdoctor.model.Emergency;
import com.example.myapplicationdoctor.model.SkillDoctor;
import com.example.myapplicationdoctor.model.UserPatient;
import com.example.myapplicationdoctor.repositories.RepositoryApplication;

import java.util.List;

public class EmergencyActivityViewModel extends ViewModel {
    private MutableLiveData<List<Emergency>> listEmergencyMutable = new MutableLiveData<>();
    public LiveData<List<Emergency>> liveDataEmergency = listEmergencyMutable;


    public void toPostEmergencyList(){
        listEmergencyMutable.postValue(RepositoryApplication.getInstance().emergencyList);
    }

}
