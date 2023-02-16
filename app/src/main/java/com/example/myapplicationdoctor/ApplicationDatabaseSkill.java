package com.example.myapplicationdoctor;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplicationdoctor.model.SkillDoctor;

@Database(entities = {SkillDoctor.class},version = 1)
public abstract class ApplicationDatabaseSkill extends RoomDatabase {
    private static ApplicationDatabaseSkill INSTANCE;
    public abstract SkillDoctorDao getSkillDao();
    public static synchronized ApplicationDatabaseSkill getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ApplicationDatabaseSkill.class, "skill_app").build();
        }
        return INSTANCE;
    }



}
