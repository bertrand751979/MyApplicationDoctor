package com.example.myapplicationdoctor;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplicationdoctor.model.Emergency;
import com.example.myapplicationdoctor.model.SkillDoctor;

@Database(entities = {Emergency.class},version = 3)
public abstract class ApplicationDatabaseEmergency extends RoomDatabase {
    private static ApplicationDatabaseEmergency INSTANCE;
    public abstract EmergencyDao getEmergencyDao();
    public static synchronized ApplicationDatabaseEmergency getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ApplicationDatabaseEmergency.class, "emergency_app").build();
        }
        return INSTANCE;
    }



}
