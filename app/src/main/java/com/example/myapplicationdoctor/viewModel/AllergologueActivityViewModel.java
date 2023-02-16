package com.example.myapplicationdoctor.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplicationdoctor.model.Emergency;
import com.example.myapplicationdoctor.model.SkillDoctor;
import com.example.myapplicationdoctor.model.UserDoctor;
import com.example.myapplicationdoctor.repositories.RepositoryApplication;

import java.util.List;

public class AllergologueActivityViewModel extends ViewModel {
    private MutableLiveData<List<UserDoctor>> listChoiceDrSkillMutable = new MutableLiveData<>();
    public LiveData<List<UserDoctor>> liveDataChoiceDrSkill = listChoiceDrSkillMutable;

    public void toPostAllergologueList(){
        listChoiceDrSkillMutable.postValue(RepositoryApplication.getInstance().myUserDoctorList);
    }

}