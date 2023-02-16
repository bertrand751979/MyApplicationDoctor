package com.example.myapplicationdoctor;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplicationdoctor.model.UserDoctor;
import com.example.myapplicationdoctor.model.UserPatient;


@Database(entities = {UserPatient.class},version = 2)
public abstract class ApplicationDatabaseUserPatient extends RoomDatabase {
    private static ApplicationDatabaseUserPatient INSTANCE;
    public abstract UserPatientDao getUserPatientDao();
    public static synchronized ApplicationDatabaseUserPatient getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ApplicationDatabaseUserPatient.class, "user_patient_app").build();
        }
        return INSTANCE;
    }
}
