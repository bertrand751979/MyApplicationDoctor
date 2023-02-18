package com.example.myapplicationdoctor.activities;

import static com.example.myapplicationdoctor.activities.MainActivity.USERDOCTOR_KEY;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplicationdoctor.R;
import com.example.myapplicationdoctor.model.UserDoctor;

public class DrRegistryDetailActivity extends AppCompatActivity {
    private TextView detailOfficeName;
    private TextView detailSkill;
    private TextView detailOpen;
    private TextView detailClose;
    private TextView detailStartHolidays;
    private TextView detailEndHolidays;
    private TextView detailAdress;
    private TextView detailCity;
    private TextView detailPhoneNumber;
    private UserDoctor userDoctor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dr_register);
        userDoctor =(UserDoctor)getIntent().getSerializableExtra(USERDOCTOR_KEY);
        detailOfficeName = findViewById(R.id.desc_office_name);
        detailSkill = findViewById(R.id.desc_skill);
        detailOpen = findViewById(R.id.desc_office_open);
        detailClose = findViewById(R.id.desc_office_close);
        detailStartHolidays = findViewById(R.id.desc_holiday_start);
        detailEndHolidays = findViewById(R.id.desc_holiday_close);
        detailAdress = findViewById(R.id.desc_adress);
        detailCity = findViewById(R.id.desc_city);
        detailPhoneNumber = findViewById(R.id.desc_phone);


        detailOfficeName.setText(userDoctor.getDoctorName());
        detailSkill.setText(userDoctor.getDoctorSkill());
        detailOpen.setText(userDoctor.getDoctorOpenOffice());
        detailClose.setText(userDoctor.getDoctorCloseOffice());
        detailStartHolidays.setText(userDoctor.getDoctorStartHolidays());
        detailEndHolidays.setText(userDoctor.getDoctorCloseHolidays());
        detailAdress.setText(userDoctor.getDoctorLocation());
        detailCity.setText(userDoctor.getDoctorCity());
        detailPhoneNumber.setText(userDoctor.getDoctorPhoneNumber());


    }
}
