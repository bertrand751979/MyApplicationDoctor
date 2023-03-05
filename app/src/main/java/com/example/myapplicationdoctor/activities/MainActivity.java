package com.example.myapplicationdoctor.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplicationdoctor.R;
import com.example.myapplicationdoctor.repositories.RepositoryApplication;

public class MainActivity extends AppCompatActivity {
    public static String USERPATIENT_KEY ="user_patient_key";
    public static String USERDOCTOR_KEY ="user_doctor_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}