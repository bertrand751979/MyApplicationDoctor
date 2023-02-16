package com.example.myapplicationdoctor.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplicationdoctor.R;

public class MainActivity extends AppCompatActivity {
    public static String USERPATIENT_KEY ="user_patient_key";
    public static String USERDOCTEUR_KEY ="user_docteur_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}