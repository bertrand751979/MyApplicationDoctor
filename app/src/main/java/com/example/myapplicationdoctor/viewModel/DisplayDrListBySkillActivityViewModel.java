package com.example.myapplicationdoctor.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplicationdoctor.model.Emergency;
import com.example.myapplicationdoctor.model.SkillDoctor;
import com.example.myapplicationdoctor.model.UserDoctor;
import com.example.myapplicationdoctor.repositories.RepositoryApplication;

import java.util.List;

public class DisplayDrListBySkillActivityViewModel extends ViewModel {
    private MutableLiveData<List<UserDoctor>> listChoiceDrSkillMutable = new MutableLiveData<>();
    public LiveData<List<UserDoctor>> liveDataChoiceDrSkill = listChoiceDrSkillMutable;

    public LiveData<List<UserDoctor>> getLiveDataDoctor(Context context){
        return RepositoryApplication.getInstance().getUserDoctorList(context);
    }
    public void toPostList(){
        listChoiceDrSkillMutable.postValue(RepositoryApplication.getInstance().listSortSkill);
        //listChoiceDrSkillMutable.postValue(RepositoryApplication.getInstance().searchDrSkillList);
        //listChoiceDrSkillMutable.postValue(RepositoryApplication.getInstance().listAllergo);
        //listChoiceDrSkillMutable.postValue(RepositoryApplication.getInstance().displayListDrSkill);

    }

}
