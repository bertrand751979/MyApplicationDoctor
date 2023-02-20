package com.example.myapplicationdoctor.activities;

import static com.example.myapplicationdoctor.activities.MainActivity.USERDOCTOR_KEY;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationdoctor.OnCircleDetailsClickedAction;
import com.example.myapplicationdoctor.OnCircleMapClickedAction;
import com.example.myapplicationdoctor.OnCirclePhoneClickedAction;
import com.example.myapplicationdoctor.R;
import com.example.myapplicationdoctor.adapter.AllergologueAdapter;
import com.example.myapplicationdoctor.model.UserDoctor;
import com.example.myapplicationdoctor.repositories.RepositoryApplication;
import com.example.myapplicationdoctor.viewModel.AllergologueActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class AllergologueActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AllergologueAdapter allergoAdapter;
    private AllergologueActivityViewModel allergologueActivityViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergo);
        RepositoryApplication.getInstance().getMyUserDoctorList();
        //RepositoryApplication.getInstance().getListAllergo();
        Log.d("Taille2", String.valueOf(RepositoryApplication.getInstance().myUserDoctorList.size()));


        RepositoryApplication.getInstance().listAllergo = RepositoryApplication.getInstance().myUserDoctorList;
        allergologueActivityViewModel = new ViewModelProvider(this).get(AllergologueActivityViewModel.class);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));
        }
        recyclerView = findViewById(R.id.recycler_view_list_choice_dr_skill);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        OnCirclePhoneClickedAction onCirclePhoneClickedAction = new OnCirclePhoneClickedAction() {
            @Override
            public void callDr(UserDoctor userDoctor) {
                String callDr = userDoctor.getDoctorPhoneNumber();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", callDr, null));
                startActivity(intent);
            }
        };

        OnCircleMapClickedAction onCircleMapClickedAction = new OnCircleMapClickedAction() {
            @Override
            public void goToDrMapLocation(UserDoctor userDoctor) {
                Toast.makeText(AllergologueActivity.this, "Map", Toast.LENGTH_SHORT).show();
            }
        };

        OnCircleDetailsClickedAction onCircleDetailsClickedAction = new OnCircleDetailsClickedAction() {
            @Override
            public void goToDrDetails(UserDoctor userDoctor) {
                Intent intent = new Intent(AllergologueActivity.this, DrRegistryDetailActivity.class);
                intent.putExtra(USERDOCTOR_KEY,userDoctor);
                startActivity(intent);
            }
        };
        allergoAdapter = new AllergologueAdapter(onCircleDetailsClickedAction, onCirclePhoneClickedAction, onCircleMapClickedAction);
        recyclerView.setAdapter(allergoAdapter);
        allergologueActivityViewModel.liveDataChoiceDrSkill.observe(this, new Observer<List<UserDoctor>>() {
            @Override
            public void onChanged(List<UserDoctor> userDoctors) {
                allergoAdapter.setListUserDoctorAdapter(new ArrayList<>(userDoctors));
                RepositoryApplication.getInstance().listAllergo = (ArrayList<UserDoctor>) userDoctors;
            }
        });
        allergologueActivityViewModel.toPostAllergologueList();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }




}
