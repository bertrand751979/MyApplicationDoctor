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
public interface UserDoctorDao {
    @Query("SELECT * FROM UserDoctor ")
    LiveData<List<UserDoctor>> getUsersDoctor();

    @Insert
    void addDr(UserDoctor userDoctor);
  /*  @Delete
    void deletePatient(UserPatient userPatient);

    @Update
    void updatePatient(UserPatient userPatient);*/
}
