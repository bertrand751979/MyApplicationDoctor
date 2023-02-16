package com.example.myapplicationdoctor.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationdoctor.OnPhoneImageActionClicked;
import com.example.myapplicationdoctor.R;
import com.example.myapplicationdoctor.adapter.EmergencyAdapter;
import com.example.myapplicationdoctor.adapter.PageDoctorsAdapter;
import com.example.myapplicationdoctor.model.Emergency;
import com.example.myapplicationdoctor.repositories.RepositoryApplication;
import com.example.myapplicationdoctor.viewModel.EmergencyActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class EmergencyActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EmergencyAdapter emergencyAdapter;
    private EmergencyActivityViewModel emergencyActivityViewModel;
    private LinearLayout returnArrow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_page);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));
        }
        emergencyActivityViewModel = new ViewModelProvider(this).get(EmergencyActivityViewModel.class);
        returnArrow = findViewById(R.id.linearEmergencyArrow);
        Emergency emergency1 = new Emergency("SAMU","15");
        Emergency emergency2 = new Emergency("POMPIER","18");
        Emergency emergency3 = new Emergency("POLICE","17");
        Emergency emergency4 = new Emergency("SECOURS","112");
        RepositoryApplication.getInstance().addEmergency(emergency1,this);
        RepositoryApplication.getInstance().addEmergency(emergency2,this);
        RepositoryApplication.getInstance().addEmergency(emergency3,this);
        RepositoryApplication.getInstance().addEmergency(emergency4,this);
        RepositoryApplication.getInstance().emergencyList.add(emergency1);
        RepositoryApplication.getInstance().emergencyList.add(emergency2);
        RepositoryApplication.getInstance().emergencyList.add(emergency3);
        RepositoryApplication.getInstance().emergencyList.add(emergency4);
        returnArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmergencyActivity.this, DoctorPageActivity.class);
                startActivity(intent);
            }
        });
        recyclerView = findViewById(R.id.recyclerViewEmergency);
        OnPhoneImageActionClicked onPhoneImageActionClicked = new OnPhoneImageActionClicked() {
            @Override
            public void call(Emergency emergency) {
                String phone = emergency.getEmergencyPhoneNumber();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        emergencyAdapter = new EmergencyAdapter(onPhoneImageActionClicked);
        recyclerView.setAdapter(emergencyAdapter);
        emergencyActivityViewModel.liveDataEmergency.observe(this, new Observer<List<Emergency>>() {
            @Override
            public void onChanged(List<Emergency> emergencies) {
                emergencyAdapter.setListEmergencyAdapter(new ArrayList<>(emergencies));
                Toast.makeText(EmergencyActivity.this, "size"+emergencies.size(), Toast.LENGTH_SHORT).show();
            }
        });
        emergencyActivityViewModel.toPostEmergencyList();
    }
}
