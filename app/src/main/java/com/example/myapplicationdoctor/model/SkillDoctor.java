package com.example.myapplicationdoctor.model;

import android.widget.TextView;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SkillDoctor {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @ColumnInfo(name="skill")
    private String skill;

    public SkillDoctor( String skill) {
        this.skill = skill;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
