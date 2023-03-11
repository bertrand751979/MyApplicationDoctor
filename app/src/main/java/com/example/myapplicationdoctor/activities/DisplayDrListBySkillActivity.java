package com.example.myapplicationdoctor.activities;

import static com.example.myapplicationdoctor.activities.DoctorPageActivity.choice;
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

import com.example.myapplicationdoctor.InterfaceEditTextDialog;
import com.example.myapplicationdoctor.OnCircleDetailsClickedAction;
import com.example.myapplicationdoctor.OnCircleMapClickedAction;
import com.example.myapplicationdoctor.OnCirclePhoneClickedAction;
import com.example.myapplicationdoctor.R;
import com.example.myapplicationdoctor.adapter.UserDoctorAdapter;
import com.example.myapplicationdoctor.model.UserDoctor;
import com.example.myapplicationdoctor.repositories.RepositoryApplication;
import com.example.myapplicationdoctor.viewModel.DisplayDrListBySkillActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class DisplayDrListBySkillActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserDoctorAdapter userDoctorAdapter;
    private DisplayDrListBySkillActivityViewModel displayDrListBySkillActivityViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergo);
        Log.d("Sort", String.valueOf(RepositoryApplication.getInstance().listSortSkill.size()));

        displayDrListBySkillActivityViewModel = new ViewModelProvider(this).get(DisplayDrListBySkillActivityViewModel.class);
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
                Toast.makeText(DisplayDrListBySkillActivity.this, "Map", Toast.LENGTH_SHORT).show();
            }
        };

        OnCircleDetailsClickedAction onCircleDetailsClickedAction = new OnCircleDetailsClickedAction() {
            @Override
            public void goToDrDetails(UserDoctor userDoctor) {
                Intent intent = new Intent(DisplayDrListBySkillActivity.this, DrRegistryDetailActivity.class);
                intent.putExtra(USERDOCTOR_KEY, userDoctor);
                startActivity(intent);
            }
        };

        RepositoryApplication.getInstance().listSortSkill=RepositoryApplication.getInstance().myDrSkillSearch;
        Log.d("listSize", String.valueOf(RepositoryApplication.getInstance().listSortSkill.size()));
        userDoctorAdapter = new UserDoctorAdapter(onCircleDetailsClickedAction, onCirclePhoneClickedAction, onCircleMapClickedAction);

        recyclerView.setAdapter(userDoctorAdapter);
        displayDrListBySkillActivityViewModel.getLiveDataDoctor(this).observe(this, new Observer<List<UserDoctor>>() {
            @Override
            public void onChanged(List<UserDoctor> userDoctors) {
                RepositoryApplication.getInstance().listSortSkill = (ArrayList<UserDoctor>) userDoctors;
                userDoctorAdapter.setListUserDoctorAdapter(sortMySkillList());
                Log.d("choice", String.valueOf(choice));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        RepositoryApplication.getInstance().myDrSkillSearch.clear();
        userDoctorAdapter.setListUserDoctorAdapter(RepositoryApplication.getInstance().listSortSkill);
    }

    private ArrayList<UserDoctor> sortMySkillList(){
        for(UserDoctor userDoctor:RepositoryApplication.getInstance().listSortSkill) {
            if(userDoctor.getDoctorSkill().contains(choice)){
                RepositoryApplication.getInstance().myDrSkillSearch.add(userDoctor);
                Log.d("list", String.valueOf(RepositoryApplication.getInstance().myDrSkillSearch.size()));
            }
        }
        return RepositoryApplication.getInstance().myDrSkillSearch;
    }


}
