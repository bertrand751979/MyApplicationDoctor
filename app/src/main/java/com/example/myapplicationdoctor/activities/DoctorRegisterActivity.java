package com.example.myapplicationdoctor.activities;

import static com.example.myapplicationdoctor.activities.MainActivity.USERDOCTOR_KEY;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplicationdoctor.DoctorsRetrofitApi;
import com.example.myapplicationdoctor.R;
import com.example.myapplicationdoctor.model.City;
import com.example.myapplicationdoctor.model.Root;
import com.example.myapplicationdoctor.model.MyDrSkillSpinner;
import com.example.myapplicationdoctor.model.UserDoctor;
import com.example.myapplicationdoctor.repositories.RepositoryApplication;
import com.example.myapplicationdoctor.viewModel.DoctorRegisterActivityViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorRegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private EditText drPassword;
    private EditText drLogin;
    private EditText drOfficeName;
    private EditText drAdress;
    private EditText drPhoneNumber;
    private int openTimeHour;
    private int openTimeMinute;
    private int closeTimeMinute;
    private int closeTimeHour;
    private TextView startHolidays;
    private TextView endHolidays;
    private TextView startWorkHour;
    private TextView leaveWorkHour;
    private TextView dateHolidayStart;
    private TextView dateHolidayClose;
    private int startYearHoliday;
    private int startMonthHoliday;
    private int startDayHoliday;
    private int closeYearHoliday;
    private int closeMonthHoliday;
    private int closetDayHoliday;
    private int selectedYear = 2021;
    private int selectedMonth = 1;
    private int selectedDay = 1;
    private Button btnCertificate;
    private Button btnRegisterDr;
    private TextView toReturn;
    private static final int PICK_PDF_FILE = 2;
    private Uri pickerInitialUri;
    private String skillDr;
    //private ArrayList<MyDrSkillSpinner> skillsDoctorsList = new ArrayList<>();
    private ArrayList<City> cityDoctorsList = new ArrayList<>();
    private String cityDr;
    private DoctorRegisterActivityViewModel doctorRegisterActivityViewModel;
    private UserDoctor userDoctor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_register);
        doctorRegisterActivityViewModel = new ViewModelProvider(this).get(DoctorRegisterActivityViewModel.class);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));
        }
        Spinner spinner = findViewById(R.id.spiner_dr_skills);
        Spinner spinner2 = findViewById(R.id.spiner_dr_location);
        btnCertificate = findViewById(R.id.dr_register_btn_download_certificate);
        btnRegisterDr = findViewById(R.id.dr_register_btn_register);
        toReturn = findViewById(R.id.dr_register_btn_return);
        drOfficeName = findViewById(R.id.dr_register_office_name);
        drAdress = findViewById(R.id.dr_register_location);
        drLogin = findViewById(R.id.dr_register_login);
        drPassword = findViewById(R.id.dr_register_password);
        drPhoneNumber = findViewById(R.id.dr_register_phone_number);
        btnCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFile(pickerInitialUri);
            }
        });
        
        //drSkills = findViewById(R.id.raw_dr_allergo_skills);
        //drCity = findViewById(R.id.dr_register_holiday_start);
        startWorkHour = findViewById(R.id.dr_register_start);
        leaveWorkHour = findViewById(R.id.dr_register_close);
        startHolidays = findViewById(R.id.dr_register_holiday_start);
        endHolidays = findViewById(R.id.dr_register_holiday_close);
        startWorkHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            popTimePickerOpenWork();
            }
        });

        leaveWorkHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            popTimePickerCloseWork();
            }
        });
        startHolidays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popDatePickerStartHoliday();
            }
        });

        endHolidays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popDatePickerClosetHoliday();
            }
        });

        MyDrSkillSpinner exoSkill1 = new MyDrSkillSpinner("Dermatologue");
        MyDrSkillSpinner exoSkill2 = new MyDrSkillSpinner("Chirurgien");
        MyDrSkillSpinner exoSkill3 = new MyDrSkillSpinner("Pédiatre");
        MyDrSkillSpinner exoSkill4 = new MyDrSkillSpinner("Généraliste");
        MyDrSkillSpinner exoSkill5 = new MyDrSkillSpinner("Rumathologue");
        MyDrSkillSpinner exoSkill6= new MyDrSkillSpinner("Cardiologue");
        MyDrSkillSpinner exoSkill7 = new MyDrSkillSpinner("Ophtalmologue");
        MyDrSkillSpinner exoSkill8 = new MyDrSkillSpinner("Allergologue");
        MyDrSkillSpinner exoSkill9 = new MyDrSkillSpinner("Dentiste");
        MyDrSkillSpinner exoSkill10 = new MyDrSkillSpinner("Gynecologue");
        MyDrSkillSpinner exoSkill11 = new MyDrSkillSpinner("Radiologue");
        MyDrSkillSpinner exoSkill12 = new MyDrSkillSpinner("Neurologue");
        MyDrSkillSpinner exoSkill13 = new MyDrSkillSpinner("Psychiatrie");
        MyDrSkillSpinner exoSkill14 = new MyDrSkillSpinner("Orl");

        RepositoryApplication.getInstance().skillsDoctorsList.add(exoSkill1);
        RepositoryApplication.getInstance().skillsDoctorsList.add(exoSkill2);
        RepositoryApplication.getInstance().skillsDoctorsList.add(exoSkill3);
        RepositoryApplication.getInstance().skillsDoctorsList.add(exoSkill4);
        RepositoryApplication.getInstance().skillsDoctorsList.add(exoSkill5);
        RepositoryApplication.getInstance().skillsDoctorsList.add(exoSkill6);
        RepositoryApplication.getInstance().skillsDoctorsList.add(exoSkill7);
        RepositoryApplication.getInstance().skillsDoctorsList.add(exoSkill8);
        RepositoryApplication.getInstance().skillsDoctorsList.add(exoSkill9);
        RepositoryApplication.getInstance().skillsDoctorsList.add(exoSkill10);
        RepositoryApplication.getInstance().skillsDoctorsList.add(exoSkill11);
        RepositoryApplication.getInstance().skillsDoctorsList.add(exoSkill12);
        RepositoryApplication.getInstance().skillsDoctorsList.add(exoSkill13);
        RepositoryApplication.getInstance().skillsDoctorsList.add(exoSkill14);
        Log.d("Result", String.valueOf(RepositoryApplication.getInstance().skillsDoctorsList.size()));
        City city1 = new City("Tlemecen");
        RepositoryApplication.getInstance().theCityDoctorsList.add(city1);

        ArrayAdapter<MyDrSkillSpinner> adapter4 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,RepositoryApplication.getInstance().skillsDoctorsList);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter4);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Adapter adapter = adapterView.getAdapter();
                MyDrSkillSpinner myDrSkillSpinner = (MyDrSkillSpinner) adapter.getItem(i);
                skillDr = myDrSkillSpinner.getDrSkill();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ArrayAdapter<City> adapter2 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,RepositoryApplication.getInstance().theCityDoctorsList);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Adapter adapter = adapterView.getAdapter();
                City city = (City) adapter.getItem(i);
                cityDr = city.getCityName();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        btnRegisterDr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDoctor userDoctor = new UserDoctor();
                userDoctor.setDoctorName(drOfficeName.getText().toString());
                userDoctor.setDoctorLocation(drAdress.getText().toString());
                userDoctor.setDoctorPhoneNumber(drPhoneNumber.getText().toString());
                userDoctor.setDoctorLogin(drLogin.getText().toString());
                userDoctor.setDoctorPassword(drPassword.getText().toString());
                userDoctor.setDoctorCity(cityDr);
                userDoctor.setDoctorSkill(skillDr);
                userDoctor.setDoctorStartHolidays(startHolidays.getText().toString());
                userDoctor.setDoctorCloseHolidays(endHolidays.getText().toString());
                userDoctor.setDoctorOpenOffice(startWorkHour.getText().toString());
                userDoctor.setDoctorCloseOffice(leaveWorkHour.getText().toString());
                //RepositoryApplication.getInstance().addUserDoctor(userDoctor,DoctorRegisterActivity.this);
                doctorRegisterActivityViewModel.addToDoctorList(userDoctor,DoctorRegisterActivity.this);
                Intent intent = new Intent(DoctorRegisterActivity.this,LoginPageActivity.class);
                intent.putExtra(USERDOCTOR_KEY,userDoctor);
                startActivity(intent);
            }
        });

        doctorRegisterActivityViewModel.getLiveDataDoctor(this).observe(this, new Observer<List<UserDoctor>>() {
            @Override
            public void onChanged(List<UserDoctor> userDoctors) {
                RepositoryApplication.getInstance().myUserDoctorList = (ArrayList<UserDoctor>) userDoctors;
                //Toast.makeText(DoctorRegisterActivity.this, "Long"+RepositoryApplication.getInstance().myUserDoctorList.size(), Toast.LENGTH_SHORT).show();
                Log.d("Long", String.valueOf(RepositoryApplication.getInstance().myUserDoctorList.size()));
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void callService() {
        DoctorsRetrofitApi.DoctorsRetrofitService service = DoctorsRetrofitApi.getInstance().getClient().create(DoctorsRetrofitApi.DoctorsRetrofitService.class);
        Call<Root> call = service.getRoot(48.8306,"distance",false,"MMyATu4yTWIwaE1cuQ1HPUL6IsaGBKdC","paris");
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.code() == 200) {
                    processResponse(response);
                }
                Toast.makeText(DoctorRegisterActivity.this, "CONNEXION OK", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(DoctorRegisterActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void processResponse(Response<Root> response) {
        if(response.body().getResults().size()>0){
        }
    }

    private void openFile(Uri pickerInitialUri) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/pdf");
        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri);
        startActivityForResult(intent, PICK_PDF_FILE);
    }

    public void popTimePickerOpenWork(){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedAmHour, int selectedAmMinute) {
                openTimeHour = selectedAmHour;
                openTimeMinute = selectedAmMinute;
                startWorkHour.setText(String.format(Locale.getDefault(),"%02d:%02d",openTimeHour,openTimeMinute));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener,openTimeHour,openTimeMinute,true);
        timePickerDialog.setTitle("selectedTime");
        timePickerDialog.show();
    }

    public void popTimePickerCloseWork(){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedAmHour, int selectedAmMinute) {
                closeTimeHour = selectedAmHour;
                closeTimeMinute = selectedAmMinute;
                leaveWorkHour.setText(String.format(Locale.getDefault(),"%02d:%02d",closeTimeHour,closeTimeMinute));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener,closeTimeHour,closeTimeMinute,true);
        timePickerDialog.setTitle("selectedTime");
        timePickerDialog.show();
    }

    public void popDatePickerStartHoliday(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int slectedYear, int selectedMonth, int selectedDay) {
                startYearHoliday = slectedYear;
                startMonthHoliday = selectedMonth;
                startDayHoliday = selectedDay;
                startHolidays.setText(String.format(Locale.getDefault(),"Le " + startDayHoliday + " - " +(startMonthHoliday+1) + "- " +startYearHoliday));
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, startDayHoliday, startMonthHoliday, startYearHoliday);
        datePickerDialog.setTitle("selectedDate");
        datePickerDialog.show();
    }
    public void popDatePickerClosetHoliday() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int slectedYear, int selectedMonth, int selectedDay) {
                closeYearHoliday = slectedYear;
                closeMonthHoliday = selectedMonth;
                closetDayHoliday = selectedDay;
                endHolidays.setText(String.format(Locale.getDefault(),"Le " + closetDayHoliday + " - " +(closeMonthHoliday+1) + "- " +closeYearHoliday));
                //userDoctor.setDoctorCloseHolidays(endHolidays.getText().toString());
                //userDoctor.setDoctorCloseHolidays(String.format(Locale.getDefault(),"Le " + closetDayHoliday + " - " +(closeMonthHoliday+1) + "- " +closeYearHoliday));
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, closetDayHoliday, closeMonthHoliday, closeYearHoliday);
        datePickerDialog.setTitle("selectedDate");
        datePickerDialog.show();

    }

}
