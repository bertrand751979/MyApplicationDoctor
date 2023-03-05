package com.example.myapplicationdoctor.model;

import java.io.Serializable;

public class MyDrSkillSpinner implements Serializable {
    private String drSkill;

    public MyDrSkillSpinner(String drSkill) {
        this.drSkill = drSkill;
    }

    public String getDrSkill() {
        return drSkill;
    }

    public void setDrSkill(String drSkill) {
        this.drSkill = drSkill;
    }

    @Override
    public String toString() {
        return drSkill;
    }
}
