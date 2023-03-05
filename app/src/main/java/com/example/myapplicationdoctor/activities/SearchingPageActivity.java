package com.example.myapplicationdoctor.activities;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationdoctor.R;
import com.example.myapplicationdoctor.adapter.SearchAdapter;
import com.example.myapplicationdoctor.model.City;
import com.example.myapplicationdoctor.model.MyDrSkillSpinner;
import com.example.myapplicationdoctor.model.UserDoctor;
import com.example.myapplicationdoctor.repositories.RepositoryApplication;
import com.example.myapplicationdoctor.viewModel.DoctorRegisterActivityViewModel;
import com.example.myapplicationdoctor.viewModel.SearchingPageActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchingPageActivity extends AppCompatActivity {
    private TextView txtSearchDrName;
    private String txtSearchCity;
    private String txtSearchDrSkill;
    private Button txtSearchBtn;
    private CheckBox txtpreferences;
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private DoctorRegisterActivityViewModel doctorRegisterActivityViewModel;


                                                                                                                                                                                                                                                                   private String cityDr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        if(RepositoryApplication.getInstance().skillsDoctorsList.size()>0){
            Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
        }else{Toast.makeText(this, "NULL", Toast.LENGTH_SHORT).show();
        }
        //RepositoryApplication.getInstance().getMyUserDoctorList();
        //RepositoryApplication.getInstance().getUserDoctorList(this);
        RepositoryApplication.getInstance().getSkillsDoctorsList();
        //Log.d("transfert", String.valueOf(RepositoryApplication.getInstance().cityDoctorsList.size()));
        Log.d("transfert", String.valueOf(RepositoryApplication.getInstance().skillsDoctorsList.size()));

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));
        }

        txtSearchDrName = findViewById(R.id.activity_search_edit_dr_name);
        txtSearchBtn = findViewById(R.id.activity_btnSearch);
        recyclerView = findViewById(R.id.recyclerViewToDisplaySearchingListByParameters);

        Spinner spinner3 = findViewById(R.id.activity_spinner_search_dr_skills);
        ArrayAdapter<MyDrSkillSpinner> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, RepositoryApplication.getInstance().getSkillsDoctorsList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Adapter adapter = adapterView.getAdapter();
                MyDrSkillSpinner myDrSkillSpinner = (MyDrSkillSpinner) adapter.getItem(i);
                txtSearchDrSkill = myDrSkillSpinner.getDrSkill();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
    }
}
