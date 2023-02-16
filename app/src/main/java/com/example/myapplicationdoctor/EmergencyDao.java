package com.example.myapplicationdoctor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplicationdoctor.model.Emergency;
import com.example.myapplicationdoctor.model.SkillDoctor;

import java.util.List;

@Dao
public interface EmergencyDao {
    @Query("SELECT * FROM Emergency ")
    LiveData<List<Emergency>> getEmergencys();

    @Insert
    void addAllEmergencies(Emergency emergency);




}
