package com.example.myapplicationdoctor.activities;

import static com.example.myapplicationdoctor.activities.MainActivity.USERDOCTOR_KEY;
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
import com.example.myapplicationdoctor.viewModel.SkillsDoctorViewModel;
import com.example.myapplicationdoctor.adapter.PageDoctorsAdapter;
import com.example.myapplicationdoctor.model.SkillDoctor;
import com.example.myapplicationdoctor.model.UserPatient;
import com.example.myapplicationdoctor.repositories.RepositoryApplication;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class DoctorPageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_page);
        userPatient = (UserPatient) getIntent().getSerializableExtra(USERPATIENT_KEY);
        RepositoryApplication.getInstance().getMyUserDoctorList();
        sharedPreferences = getSharedPreferences(USERPATIENT_KEY, 0);
        boolean dialogShown = sharedPreferences.getBoolean("dialogShown", false);
        if (dialogShown != true) {
            showPopUp();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("dialogShown", true);
            editor.commit();
        }

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

            skillsDoctorViewModel = new ViewModelProvider(this).get(SkillsDoctorViewModel.class);
            SkillDoctor skillDoctor1 = new SkillDoctor("Allergologue");
            SkillDoctor skillDoctor2 = new SkillDoctor("Cardiologue");
            SkillDoctor skillDoctor3 = new SkillDoctor("Chirurgien");
            SkillDoctor skillDoctor4 = new SkillDoctor("Dentiste");
            SkillDoctor skillDoctor5 = new SkillDoctor("Rhumatologue");
            SkillDoctor skillDoctor6 = new SkillDoctor("Pediatre");
            SkillDoctor skillDoctor7 = new SkillDoctor("Psychiatre");
            SkillDoctor skillDoctor8 = new SkillDoctor("Neurologue");
            SkillDoctor skillDoctor9 = new SkillDoctor("Generaliste");
            SkillDoctor skillDoctor10 = new SkillDoctor("Ophtalmologue");
            SkillDoctor skillDoctor11 = new SkillDoctor("ORL");
            SkillDoctor skillDoctor12 = new SkillDoctor("Radiologue");
            //SkillDoctor skillDoctor15 = new SkillDoctor("");
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
           // RepositoryApplication.getInstance().myListSkillsDoctor.add(skillDoctor15);
            RepositoryApplication.getInstance().myListSkillsDoctor.add(skillDoctor13);
            RepositoryApplication.getInstance().myListSkillsDoctor.add(skillDoctor14);
            RepositoryApplication.getInstance().addUserDoctorSkill(skillDoctor1, this);
            RepositoryApplication.getInstance().addUserDoctorSkill(skillDoctor2, this);
            RepositoryApplication.getInstance().addUserDoctorSkill(skillDoctor3, this);
            RepositoryApplication.getInstance().addUserDoctorSkill(skillDoctor4, this);
            RepositoryApplication.getInstance().addUserDoctorSkill(skillDoctor5, this);
            RepositoryApplication.getInstance().addUserDoctorSkill(skillDoctor6, this);
            RepositoryApplication.getInstance().addUserDoctorSkill(skillDoctor7, this);
            RepositoryApplication.getInstance().addUserDoctorSkill(skillDoctor8, this);
            RepositoryApplication.getInstance().addUserDoctorSkill(skillDoctor9, this);
            RepositoryApplication.getInstance().addUserDoctorSkill(skillDoctor10, this);
            RepositoryApplication.getInstance().addUserDoctorSkill(skillDoctor11, this);
            RepositoryApplication.getInstance().addUserDoctorSkill(skillDoctor12, this);
            //RepositoryApplication.getInstance().addUserDoctorSkill(skillDoctor15, this);
            RepositoryApplication.getInstance().addUserDoctorSkill(skillDoctor13, this);
            RepositoryApplication.getInstance().addUserDoctorSkill(skillDoctor14, this);

            OnLayoutClickedAction onLayoutClickedAction = new OnLayoutClickedAction() {
                @Override
                public void goToListDoctorsBySkills(SkillDoctor skillDoctor) {
                    if (skillDoctor.getSkill() == "Allergologue") {
                        for(UserDoctor userDoctor1:RepositoryApplication.getInstance().newListUserDoctor) {
                            if (skillDoctor.getSkill().equals(userDoctor1.getDoctorSkill())) {
                                Log.d("name",skillDoctor.getSkill()+userDoctor1.getDoctorSkill());
                              // Log.d( RepositoryApplication.getInstance().newListUserDoctor.size());
                                RepositoryApplication.getInstance().myUserDoctorList.add(userDoctor1);
                                //Log.d("drList", String.valueOf(RepositoryApplication.getInstance().myUserDoctorList.size()));

                        }

                            //Log.d("drList", String.valueOf(RepositoryApplication.getInstance().newListUserDoctor.size()));
                            Log.d("List", String.valueOf(RepositoryApplication.getInstance().myUserDoctorList.size()));
                              Intent intent = new Intent(DoctorPageActivity.this, AllergologueActivity.class);
                              startActivity(intent);
                        }
                      //  Log.d("drList", String.valueOf(RepositoryApplication.getInstance().newListUserDoctor.size()));

                      //  Intent intent = new Intent(DoctorPageActivity.this, AllergologueActivity.class);
                      //  startActivity(intent);


                    //Intent intent = new Intent(DoctorPageActivity.this, AllergologueActivity.class);
                    // startActivity(intent);
                    Toast.makeText(DoctorPageActivity.this, "Allergo", Toast.LENGTH_SHORT).show();
                }
                   /* if(skillDoctor.getSkill()=="Cardiologue"){
                        Toast.makeText(DoctorPageActivity.this, "Cardiologue", Toast.LENGTH_SHORT).show();
                    }
                    if(skillDoctor.getSkill()=="Chirurgien"){
                        Toast.makeText(DoctorPageActivity.this, "Chirurgien", Toast.LENGTH_SHORT).show();
                    }
                    if(skillDoctor.getSkill()=="Dentiste"){
                        Toast.makeText(DoctorPageActivity.this, "Dentiste", Toast.LENGTH_SHORT).show();
                    }
                    if(skillDoctor.getSkill()=="Rhumatologue"){
                        Toast.makeText(DoctorPageActivity.this, "Rhumatologue", Toast.LENGTH_SHORT).show();
                    }
                    if(skillDoctor.getSkill()=="Pediatre"){
                        Toast.makeText(DoctorPageActivity.this, "Pediatre", Toast.LENGTH_SHORT).show();
                    }
                    if(skillDoctor.getSkill()=="Psychiatre"){
                        Toast.makeText(DoctorPageActivity.this, "Psychiatre", Toast.LENGTH_SHORT).show();
                    }

                    if(skillDoctor.getSkill()=="Neurologue"){
                        Toast.makeText(DoctorPageActivity.this, "Neurologue", Toast.LENGTH_SHORT).show();
                    }

                    if(skillDoctor.getSkill()=="Generaliste"){
                        Toast.makeText(DoctorPageActivity.this, "Generaliste", Toast.LENGTH_SHORT).show();
                    }

                    if(skillDoctor.getSkill()=="Ophtalmologue"){
                        Toast.makeText(DoctorPageActivity.this, "Ophtalmologue", Toast.LENGTH_SHORT).show();
                    }

                    if(skillDoctor.getSkill()=="ORL"){
                        Toast.makeText(DoctorPageActivity.this, "ORL", Toast.LENGTH_SHORT).show();
                    }

                    if(skillDoctor.getSkill()=="Radiologue"){
                        Toast.makeText(DoctorPageActivity.this, "Radiologue", Toast.LENGTH_SHORT).show();
                    }

                    if(skillDoctor.getSkill()=="Gynecologue"){
                        Toast.makeText(DoctorPageActivity.this, "Gynecologue", Toast.LENGTH_SHORT).show();
                    }

                    if(skillDoctor.getSkill()=="Dermatologue"){
                        Toast.makeText(DoctorPageActivity.this, "Dermatologue", Toast.LENGTH_SHORT).show();
                    }*/

                   /* for(SkillDoctor skillDoctor1 : RepositoryApplication.getInstance().myListSkillsDoctor){
                            if(skillDoctor1.getSkill().equalsIgnoreCase("Allergologue")){
                                Intent intent = new Intent(DoctorPageActivity.this,AllergologueActivity.class);
                                startActivity(intent);
                        }
                    }*/

                      /*  for(UserDoctor userDoctor:RepositoryApplication.getInstance().myUserDoctorList){
                            for(SkillDoctor skillDoctor16:RepositoryApplication.getInstance().myListSkillsDoctor){
                            if(skillDoctor16.getSkill().equalsIgnoreCase("Allergologue") &&(userDoctor.getDoctorSkill().equalsIgnoreCase("Allergologue"))){
                                RepositoryApplication.getInstance().listAllergo.add(userDoctor);
                                Intent intent = new Intent(DoctorPageActivity.this,AllergologueActivity.class);
                                startActivity(intent);
                            }
                        }
                    }*/
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

            if (Build.VERSION.SDK_INT >= 21) {
                Window window = this.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));
            }
        skillList = RepositoryApplication.getInstance().getMyListSkillsDoctor();
    }


        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem menuItem){
            switch (menuItem.getItemId()) {
                case R.id.nav_skill:
                    drawerLayout.close();
                    break;
                case R.id.nav_emergengy:
                    Intent intent = new Intent(DoctorPageActivity.this, EmergencyActivity.class);
                    startActivity(intent);
                    break;
                case R.id.nav_search:
                    Intent intentSearch = new Intent(DoctorPageActivity.this, SearchActivity.class);
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

}