package com.example.myapplicationdoctor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.myapplicationdoctor.model.UserDoctor;
import java.util.List;


@Dao
public interface UserDoctorDao {
    @Query("SELECT * FROM UserDoctor ")
    LiveData<List<UserDoctor>> getUsersDoctor();

    @Insert
    void addDr(UserDoctor userDoctor);

}
