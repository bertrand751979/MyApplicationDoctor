package com.example.myapplicationdoctor.activities;

import static com.example.myapplicationdoctor.activities.MainActivity.USERDOCTOR_KEY;
import static com.example.myapplicationdoctor.activities.MainActivity.USERPATIENT_KEY;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplicationdoctor.R;
import com.example.myapplicationdoctor.model.MyDrSkillSpinner;
import com.example.myapplicationdoctor.model.UserDoctor;
import com.example.myapplicationdoctor.model.UserPatient;
import com.example.myapplicationdoctor.repositories.RepositoryApplication;
import com.example.myapplicationdoctor.viewModel.DoctorRegisterActivityViewModel;
import com.example.myapplicationdoctor.viewModel.LoginPageActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class LoginPageActivity extends AppCompatActivity {
        private EditText editLogin;
        private EditText editPassword;
        private Button btnRegister;
        private Button btnConnected;
        private UserDoctor loginUserDoctor;
        private UserPatient loginUserPatient;
        private LoginPageActivityViewModel loginPageActivityViewModel;
        private DoctorRegisterActivityViewModel doctorRegisterActivityViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_login_page);

        loginPageActivityViewModel = new ViewModelProvider(this).get(LoginPageActivityViewModel.class);
        RepositoryApplication.getInstance().getSkillsDoctorsList();
        Log.d("Spinner", String.valueOf(RepositoryApplication.getInstance().getMyListSkillsDoctor().size()));
        Log.d("Spiner", String.valueOf(RepositoryApplication.getInstance().getSkillsDoctorsList().size()));

        editLogin = findViewById(R.id.login_page_edit_login_);
        editPassword = findViewById(R.id.login_page_edit_password_);
        btnRegister = findViewById(R.id.login_page_btn_go_to_register);
        btnConnected = findViewById(R.id.login_page_btn_connected);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (LoginPageActivity.this, RegisterPageActivity.class);
                startActivity(intent);
            }
        });

        btnConnected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkingEditZone();
                isRegisterPatient();
                isRegisterDoctor();
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));
        }

        loginPageActivityViewModel.getLiveDataUser(this).observe(this, new Observer<List<UserPatient>>() {
            @Override
            public void onChanged(List<UserPatient> userPatients) {
                RepositoryApplication.getInstance().newListUserPatient = (ArrayList<UserPatient>) userPatients ;
            }
        });
       /* loginPageActivityViewModel.getLiveDataDoctor(this).observe(this, new Observer<List<UserDoctor>>() {
            @Override
            public void onChanged(List<UserDoctor> userDoctors) {
                RepositoryApplication.getInstance().newListUserDoctor = (ArrayList<UserDoctor>) userDoctors;
            }
        });*/
    }

    public void checkingEditZone(){
        if(editLogin.getText().toString() == ""){
            Toast.makeText(this, "Login Error", Toast.LENGTH_SHORT).show();
        }
        if(editPassword.getText().toString() == ""){
            Toast.makeText(this, "Password Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void isRegisterPatient() {
        boolean resultat = false;
        for (UserPatient userPatient : RepositoryApplication.getInstance().newListUserPatient) {
            if (editLogin.getText().toString().equalsIgnoreCase(userPatient.getUserPatientLogin()) &&
                    editPassword.getText().toString().equalsIgnoreCase(userPatient.getUserPatientPassword()))
            {
                resultat = true;
                loginUserPatient =userPatient;
            }
        }

        if (resultat == true) {
            Intent intent = new Intent(LoginPageActivity.this, DoctorPageActivity.class);
            intent.putExtra(USERPATIENT_KEY, loginUserPatient);
            Log.d("log", loginUserPatient.getUserPatientLogin());
            startActivity(intent);
            Toast.makeText(LoginPageActivity.this,"connecté",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginPageActivity.this, "Inconnu", Toast.LENGTH_SHORT).show();
        }
    }

    private void isRegisterDoctor() {
        boolean resultat = false;
        for (UserDoctor userDoctor : RepositoryApplication.getInstance().newListUserDoctor) {
            if (editLogin.getText().toString().equalsIgnoreCase(userDoctor.getDoctorLogin()) &&
                    editPassword.getText().toString().equalsIgnoreCase(userDoctor.getDoctorPassword()))
            {
                resultat = true;
                loginUserDoctor =userDoctor;
            }
        }

        if (resultat == true) {
            Intent intent = new Intent(LoginPageActivity.this, DrRegistryDetailActivity.class);
            intent.putExtra(USERDOCTOR_KEY, loginUserDoctor);
            Log.d("log", loginUserDoctor.getDoctorLogin());
            startActivity(intent);
            Toast.makeText(LoginPageActivity.this,"Dr connecté",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginPageActivity.this, "Inconnu", Toast.LENGTH_SHORT).show();
        }
    }



}
