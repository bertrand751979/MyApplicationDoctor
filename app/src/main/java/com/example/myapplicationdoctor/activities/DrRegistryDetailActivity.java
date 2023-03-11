package com.example.myapplicationdoctor.activities;

import static com.example.myapplicationdoctor.activities.MainActivity.USERDOCTOR_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplicationdoctor.R;
import com.example.myapplicationdoctor.model.UserDoctor;
import com.example.myapplicationdoctor.viewModel.DoctorRegistryDetailActivityViewModel;

public class DrRegistryDetailActivity extends AppCompatActivity {
    private EditText detailOfficeName;
    private EditText detailSkill;
    private EditText detailOpen;
    private EditText detailClose;
    private EditText detailStartHolidays;
    private EditText detailEndHolidays;
    private EditText detailAdress;
    private EditText detailCity;
    private EditText detailPhoneNumber;
    private UserDoctor userDoctor;
    private Button btnDetailUpdate;
    private Button btnDetailCancel;
    private DoctorRegistryDetailActivityViewModel doctorRegistryDetailActivityViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dr_register);
        doctorRegistryDetailActivityViewModel =new ViewModelProvider(this).get(DoctorRegistryDetailActivityViewModel.class);
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
        btnDetailUpdate = findViewById(R.id.btn_detail_update_register);
        btnDetailCancel = findViewById(R.id.btn_detail_update_cancel);
        btnDetailUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDoctor.setDoctorSkill(detailSkill.getText().toString());
                userDoctor.setDoctorCity(detailCity.getText().toString());
                userDoctor.setDoctorName(detailOfficeName.getText().toString());
                userDoctor.setDoctorCloseOffice(detailClose.getText().toString());
                userDoctor.setDoctorOpenOffice(detailOpen.getText().toString());
                userDoctor.setDoctorStartHolidays(detailStartHolidays.getText().toString());
                userDoctor.setDoctorCloseHolidays(detailEndHolidays.getText().toString());
                userDoctor.setDoctorLocation(detailAdress.getText().toString());
                userDoctor.setDoctorPhoneNumber(detailPhoneNumber.getText().toString());
                doctorRegistryDetailActivityViewModel.updateUserDr(userDoctor,DrRegistryDetailActivity.this);
                Log.d("update",userDoctor.getDoctorName());
                finish();
            }
        });
        btnDetailCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
