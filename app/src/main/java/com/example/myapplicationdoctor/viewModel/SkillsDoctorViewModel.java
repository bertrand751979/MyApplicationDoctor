package com.example.myapplicationdoctor.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplicationdoctor.model.SkillDoctor;
import com.example.myapplicationdoctor.model.UserDoctor;
import com.example.myapplicationdoctor.repositories.RepositoryApplication;

import java.util.List;

public class SkillsDoctorViewModel extends ViewModel {
    private MutableLiveData<List<SkillDoctor>> listSkillMutable = new MutableLiveData<>();
    public LiveData<List<SkillDoctor>> liveDataDrSkill = listSkillMutable;

    public void toPostMyDrSkill(){
        listSkillMutable.postValue(RepositoryApplication.getInstance().myListSkillsDoctor);
    }


}
