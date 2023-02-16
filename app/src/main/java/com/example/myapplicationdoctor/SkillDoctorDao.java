package com.example.myapplicationdoctor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplicationdoctor.model.SkillDoctor;
import com.example.myapplicationdoctor.model.UserDoctor;

import java.util.List;
@Dao
public interface SkillDoctorDao {
    @Query("SELECT * FROM SkillDoctor ")
    LiveData<List<SkillDoctor>> getSkillDoctor();

    @Insert
    void addSkill(SkillDoctor skillDoctor);




}
