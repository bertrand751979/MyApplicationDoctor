package com.example.myapplicationdoctor;

import com.example.myapplicationdoctor.model.SkillDoctor;
import com.example.myapplicationdoctor.model.UserDoctor;

public interface OnLayoutClickedAction {
    void goToListDoctorsBySkills(SkillDoctor skillDoctor);
    //void goToListDoctorsBySkills(UserDoctor userDoctor);
}
