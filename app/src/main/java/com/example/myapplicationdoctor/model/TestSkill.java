package com.example.myapplicationdoctor.model;

import java.io.Serializable;

public class TestSkill implements Serializable {
    private String drSkill;

    public TestSkill(String drSkill) {
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
