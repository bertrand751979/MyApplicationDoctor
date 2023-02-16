package com.example.myapplicationdoctor.activities;

import static com.example.myapplicationdoctor.activities.MainActivity.USERPATIENT_KEY;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplicationdoctor.R;
import com.example.myapplicationdoctor.model.UserPatient;
import com.example.myapplicationdoctor.viewModel.RegisterPageActivityViewModel;

public class RegisterPageActivity extends AppCompatActivity {
    private RadioButton btnDoctor;
    private RadioButton btnPatient;
    private TextView linkToRegister;
    private EditText editNameUserPatient;
    private EditText editLoginUserPatient;
    private EditText editPasswordUserPatient;
    private RegisterPageActivityViewModel registerPageActivityViewModel;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        registerPageActivityViewModel = new ViewModelProvider(this).get(RegisterPageActivityViewModel.class);
        editNameUserPatient = findViewById(R.id.register_page_edit_name_patient);
        editLoginUserPatient = findViewById(R.id.register_page_edit_login_patient);
        editPasswordUserPatient = findViewById(R.id.register_page_edit_password_patient);
        btnDoctor = findViewById(R.id.radioButton1);
        btnPatient = findViewById(R.id.radioButton2);
        linkToRegister = findViewById(R.id.registerConnectTxt);
        linkToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterPageActivity.this,LoginPageActivity.class);
                startActivity(intent);
            }
        });
        btnPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserPatient userPatient = new UserPatient();
                userPatient.setUserPatientName(editNameUserPatient.getText().toString());
                userPatient.setUserPatientLogin(editLoginUserPatient.getText().toString());
                userPatient.setUserPatientPassword(editPasswordUserPatient.getText().toString());
                registerPageActivityViewModel.addToPatientList(userPatient,RegisterPageActivity.this);
                Intent intent = new Intent(RegisterPageActivity.this,LoginPageActivity.class);
                intent.putExtra(USERPATIENT_KEY,userPatient);
                startActivity(intent);
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));
        }
        btnDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterPageActivity.this,DoctorRegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
