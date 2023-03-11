package com.example.myapplicationdoctor;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplicationdoctor.model.UserDoctor;


@Database(entities = {UserDoctor.class},version = 7)
public abstract class ApplicationDatabaseUserDoctor extends RoomDatabase {
    private static ApplicationDatabaseUserDoctor INSTANCE;
    public abstract UserDoctorDao getUserDoctorDao();
    public static synchronized ApplicationDatabaseUserDoctor getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ApplicationDatabaseUserDoctor.class, "user_doctor_app").build();
        }
        return INSTANCE;
    }
}
