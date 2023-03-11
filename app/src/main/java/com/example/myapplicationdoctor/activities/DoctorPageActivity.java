package com.example.myapplicationdoctor.activities;

import static com.example.myapplicationdoctor.activities.MainActivity.USERPATIENT_KEY;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationdoctor.OnLayoutClickedAction;
import com.example.myapplicationdoctor.R;
import com.example.myapplicationdoctor.fragments.PopupFirstConnexionAlertDialogFragment;
import com.example.myapplicationdoctor.model.UserDoctor;
import com.example.myapplicationdoctor.viewModel.DoctorsPageActivityViewModel;
import com.example.myapplicationdoctor.viewModel.SkillsDoctorViewModel;
import com.example.myapplicationdoctor.adapter.PageDoctorsAdapter;
import com.example.myapplicationdoctor.model.SkillDoctor;
import com.example.myapplicationdoctor.model.UserPatient;
import com.example.myapplicationdoctor.repositories.RepositoryApplication;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class DoctorPageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private PageDoctorsAdapter pageDoctorsAdapter;
    private SkillsDoctorViewModel skillsDoctorViewModel;
    private GridLayout gridLayout;
    private UserPatient userPatient;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private FrameLayout frameLayout;
    private NavigationView navigationView;
    private ImageView menuDrop;
    private TextView navHeaderName;
    private SharedPreferences sharedPreferences;
    private ArrayList<SkillDoctor> skillList = new ArrayList<>();
    private DoctorsPageActivityViewModel doctorsPageActivityViewModel;
    public static String choice;
    private View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_page);
        doctorsPageActivityViewModel = new ViewModelProvider(this).get(DoctorsPageActivityViewModel.class);
        skillsDoctorViewModel = new ViewModelProvider(this).get(SkillsDoctorViewModel.class);

        RepositoryApplication.getInstance().getMyListSkillsDoctor().clear();
        RepositoryApplication.getInstance().getMyUserDoctorList();
        userPatient = (UserPatient) getIntent().getSerializableExtra(USERPATIENT_KEY);

        Log.d("Doctors", String.valueOf(RepositoryApplication.getInstance().getNewListUserDoctor().size()));
        Log.d("Doctors", String.valueOf(RepositoryApplication.getInstance().getMyUserDoctorList().size()));
        toDisplayBarColor();
        toShowPupForFirstTime();

        Toast.makeText(this, "" + userPatient.getUserPatientName(), Toast.LENGTH_SHORT).show();
        navHeaderName = findViewById(R.id.nav_header_name_id);
        drawerLayout = findViewById(R.id.drawer_layout_id);
        frameLayout = findViewById(R.id.framelayout_id);
        navigationView = findViewById(R.id.navigationview_id);
        navigationView.setNavigationItemSelectedListener(this);
        recyclerView = findViewById(R.id.recyclerViewSkillsDoctors);
        menuDrop = findViewById(R.id.menu_drop);
        gridLayout = findViewById(R.id.raw_dr_skills);
        menuDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.open();
            }
        });
        toCreateTableDrBySkill();
        doctorsPageActivityViewModel.getLiveDataDoctor(this).observe(this, new Observer<List<UserDoctor>>() {
            @Override
            public void onChanged(List<UserDoctor> userDoctors) {
                RepositoryApplication.getInstance().searchDrSkillList=(ArrayList<UserDoctor>) userDoctors;
                Log.d("search", String.valueOf(RepositoryApplication.getInstance().searchDrSkillList.size()));
            }
        });
        OnLayoutClickedAction onLayoutClickedAction = new OnLayoutClickedAction() {
            @Override
            public void goToListDoctorsBySkills(SkillDoctor skillDoctor) {
                if (skillDoctor!=null) {
                    choice=skillDoctor.getSkill();
               }
                Intent intent = new Intent(DoctorPageActivity.this, DisplayDrListBySkillActivity.class);
                startActivity(intent);
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pageDoctorsAdapter = new PageDoctorsAdapter(onLayoutClickedAction);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(pageDoctorsAdapter);

        skillsDoctorViewModel.liveDataDrSkill.observe(this, new Observer<List<SkillDoctor>>() {
            @Override
            public void onChanged(List<SkillDoctor> skillDoctors) {
                pageDoctorsAdapter.setListDrSkillAdapter(new ArrayList<>(skillDoctors));
            }
        });
        skillsDoctorViewModel.toPostMyDrSkill();
        doctorsPageActivityViewModel.getLiveDataDoctor(this).observe(this, new Observer<List<UserDoctor>>() {
            @Override
            public void onChanged(List<UserDoctor> userDoctors) {
                RepositoryApplication.getInstance().listSortSkill= (ArrayList<UserDoctor>) userDoctors;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        RepositoryApplication.getInstance().sortListToDisplay.clear();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_skill:
                drawerLayout.close();
                break;
            case R.id.nav_emergengy:
                Intent intent = new Intent(DoctorPageActivity.this, EmergencyActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_search:
                Intent intentSearch = new Intent(DoctorPageActivity.this, SearchingPageActivity.class);
                startActivity(intentSearch);
                break;
            case R.id.nav_about:
                showPopUp();
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Deconnexion", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_quite:
                Toast.makeText(this, "Fermeture de l'application", Toast.LENGTH_SHORT).show();
                this.finishAffinity();
                break;
        }
        return false;
    }

    private void showPopUp() {
        FragmentManager fmo = getSupportFragmentManager();
        PopupFirstConnexionAlertDialogFragment alertDialogFragment = PopupFirstConnexionAlertDialogFragment.newInstance("some title");
        alertDialogFragment.show(fmo, "fragment_alert");
    }

    private void toShowPupForFirstTime(){
        sharedPreferences = getSharedPreferences(USERPATIENT_KEY, 0);
        boolean dialogShown = sharedPreferences.getBoolean("dialogShown", false);
        if (dialogShown != true) {
            showPopUp();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("dialogShown", true);
            editor.commit();
        }
    }

    public void toDisplayBarColor(){
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));
        }
    }

    public void toCreateTableDrBySkill(){
        SkillDoctor skillDoctor1 = new SkillDoctor("Allergologue");
        SkillDoctor skillDoctor2 = new SkillDoctor("Cardiologue");
        SkillDoctor skillDoctor3 = new SkillDoctor("Chirurgien");
        SkillDoctor skillDoctor4 = new SkillDoctor("Dentiste");
        SkillDoctor skillDoctor5 = new SkillDoctor("Rhumatologue");
        SkillDoctor skillDoctor6 = new SkillDoctor("Pédiatre");
        SkillDoctor skillDoctor7 = new SkillDoctor("Psychiatre");
        SkillDoctor skillDoctor8 = new SkillDoctor("Neurologue");
        SkillDoctor skillDoctor9 = new SkillDoctor("Généraliste");
        SkillDoctor skillDoctor10 = new SkillDoctor("Ophtalmologue");
        SkillDoctor skillDoctor11 = new SkillDoctor("ORL");
        SkillDoctor skillDoctor12 = new SkillDoctor("Radiologue");
        SkillDoctor skillDoctor13 = new SkillDoctor("Gynecologue");
        SkillDoctor skillDoctor14 = new SkillDoctor("Dermatologue");
        RepositoryApplication.getInstance().myListSkillsDoctor.add(skillDoctor1);
        RepositoryApplication.getInstance().myListSkillsDoctor.add(skillDoctor2);
        RepositoryApplication.getInstance().myListSkillsDoctor.add(skillDoctor3);
        RepositoryApplication.getInstance().myListSkillsDoctor.add(skillDoctor4);
        RepositoryApplication.getInstance().myListSkillsDoctor.add(skillDoctor5);
        RepositoryApplication.getInstance().myListSkillsDoctor.add(skillDoctor6);
        RepositoryApplication.getInstance().myListSkillsDoctor.add(skillDoctor7);
        RepositoryApplication.getInstance().myListSkillsDoctor.add(skillDoctor8);
        RepositoryApplication.getInstance().myListSkillsDoctor.add(skillDoctor9);
        RepositoryApplication.getInstance().myListSkillsDoctor.add(skillDoctor10);
        RepositoryApplication.getInstance().myListSkillsDoctor.add(skillDoctor11);
        RepositoryApplication.getInstance().myListSkillsDoctor.add(skillDoctor12);
        RepositoryApplication.getInstance().myListSkillsDoctor.add(skillDoctor13);
        RepositoryApplication.getInstance().myListSkillsDoctor.add(skillDoctor14);
    }
}