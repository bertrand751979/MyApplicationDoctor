package com.example.myapplicationdoctor;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplicationdoctor.model.UserDoctor;
import com.example.myapplicationdoctor.model.UserPatient;

import java.util.List;


@Dao
public interface UserPatientDao {

    @Query("SELECT * FROM UserPatient ")
    LiveData<List<UserPatient>> getUsersPatient();

    @Insert
    void add(UserPatient userPatient);

  /*  @Delete
    void delete(Worker worker);

    @Update
    void update(Worker worker);*/



}
