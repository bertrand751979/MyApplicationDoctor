package com.example.myapplicationdoctor.activities;

import static com.example.myapplicationdoctor.activities.MainActivity.USERDOCTOR_KEY;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
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
import com.example.myapplicationdoctor.model.TestSkill;
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
    private String cityDr;
    private ArrayList<TestSkill> skillsDoctorsList = new ArrayList<>();
    private ArrayList<City> cityDoctorsList = new ArrayList<>();
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

        TestSkill exoSkill1 = new TestSkill("Dermatologue");
        TestSkill exoSkill2 = new TestSkill("Chirurgien");
        TestSkill exoSkill3 = new TestSkill("Pédiatre");
        TestSkill exoSkill4 = new TestSkill("Généraliste");
        TestSkill exoSkill5 = new TestSkill("Rumathologue");
        TestSkill exoSkill6= new TestSkill("Cardiologue");
        TestSkill exoSkill7 = new TestSkill("Ophtalmologue");
        TestSkill exoSkill8 = new TestSkill("Allergologue");
        TestSkill exoSkill9 = new TestSkill("Dentiste");
        TestSkill exoSkill10 = new TestSkill("Gynecologue");
        TestSkill exoSkill11 = new TestSkill("Radiologue");
        TestSkill exoSkill12 = new TestSkill("Neurologue");
        TestSkill exoSkill13 = new TestSkill("Psychiatrie");
        RepositoryApplication.getInstance().skillsDoctorsList.add(exoSkill1);
        skillsDoctorsList.add(exoSkill2);
        skillsDoctorsList.add(exoSkill3);
        skillsDoctorsList.add(exoSkill4);
        skillsDoctorsList.add(exoSkill5);
        skillsDoctorsList.add(exoSkill6);
        skillsDoctorsList.add(exoSkill7);
        skillsDoctorsList.add(exoSkill8);
        skillsDoctorsList.add(exoSkill9);
        skillsDoctorsList.add(exoSkill10);
        skillsDoctorsList.add(exoSkill11);
        skillsDoctorsList.add(exoSkill12);
        skillsDoctorsList.add(exoSkill13);

        City city1 = new City("Tlemecen");
        cityDoctorsList.add(city1);

        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.skills, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);*/
        /* ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.country, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);*/

        ArrayAdapter<TestSkill> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,skillsDoctorsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Adapter adapter = adapterView.getAdapter();
                TestSkill testSkill = (TestSkill) adapter.getItem(i);
                skillDr = testSkill.getDrSkill();
               /* UserDoctor userDoctor = new UserDoctor();
                userDoctor.setDoctorSkill(skillDr);
                Log.d("Skills",userDoctor.getDoctorSkill());*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<City> adapter2 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,cityDoctorsList);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Adapter adapter = adapterView.getAdapter();
                City city = (City) adapter.getItem(i);
                cityDr = city.getCityName();
               /* UserDoctor userDoctor = new UserDoctor();
                userDoctor.setDoctorCity(cityDr);
                Log.d("City",userDoctor.getDoctorCity());*/
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
                Toast.makeText(DoctorRegisterActivity.this, "Long"+RepositoryApplication.getInstance().myUserDoctorList.size(), Toast.LENGTH_SHORT).show();

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
